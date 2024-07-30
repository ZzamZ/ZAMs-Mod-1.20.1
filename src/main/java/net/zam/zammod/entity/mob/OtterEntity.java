package net.zam.zammod.entity.mob;

import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.ItemParticleOption;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.tags.FluidTags;
import net.minecraft.tags.ItemTags;
import net.minecraft.tags.TagKey;
import net.minecraft.util.Mth;
import net.minecraft.util.RandomSource;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.control.LookControl;
import net.minecraft.world.entity.ai.control.MoveControl;
import net.minecraft.world.entity.ai.goal.*;
import net.minecraft.world.entity.ai.goal.target.HurtByTargetGoal;
import net.minecraft.world.entity.ai.goal.target.NearestAttackableTargetGoal;
import net.minecraft.world.entity.ai.navigation.PathNavigation;
import net.minecraft.world.entity.ai.navigation.WaterBoundPathNavigation;
import net.minecraft.world.entity.animal.AbstractFish;
import net.minecraft.world.entity.animal.AbstractSchoolingFish;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.ServerLevelAccessor;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.Fluid;
import net.minecraft.world.level.pathfinder.AmphibiousNodeEvaluator;
import net.minecraft.world.level.pathfinder.BlockPathTypes;
import net.minecraft.world.level.pathfinder.PathFinder;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.common.ForgeMod;
import net.minecraftforge.event.ForgeEventFactory;
import net.zam.zammod.registry.ZAMEntities;
import net.zam.zammod.registry.ZAMItems;
import net.zam.zammod.registry.ZAMSounds;

import java.util.EnumSet;
import java.util.List;

public class OtterEntity extends TamableAnimal {
    private static final EntityDataAccessor<Boolean> FLOATING = SynchedEntityData.defineId(OtterEntity.class, EntityDataSerializers.BOOLEAN);
    private static final EntityDataAccessor<Boolean> EATING = SynchedEntityData.defineId(OtterEntity.class, EntityDataSerializers.BOOLEAN);

    public final AnimationState idleAnimationState = new AnimationState();
    private int idleAnimationTimeout = 0;
    public final AnimationState sitAnimationState = new AnimationState();
    public final AnimationState swimAnimationState = new AnimationState();

    private boolean needsSurface;
    private int huntDelay;
    private int eatDelay;
    private int eatTime;
    private int floatTime;

    public OtterEntity(EntityType<? extends TamableAnimal> entityType, Level level) {
        super(entityType, level);
        this.moveControl = new OtterMoveControl(this);
        this.lookControl = new OtterLookControl(this);
        this.setPathfindingMalus(BlockPathTypes.WATER, 0.0F);
        this.setCanPickUpLoot(true);
    }

    public static AttributeSupplier.Builder createAttributes() {
        return Mob.createMobAttributes()
                .add(Attributes.MAX_HEALTH, 16.0D)
                .add(Attributes.MOVEMENT_SPEED, 0.25D)
                .add(Attributes.ATTACK_DAMAGE, 3.0D);
    }

    public static boolean checkOtterSpawnRules(EntityType<OtterEntity> entityType, LevelAccessor levelAccessor, MobSpawnType spawnType, BlockPos blockPos, RandomSource random) {
        return blockPos.getY() > levelAccessor.getSeaLevel() - 16;
    }

    @Override
    protected void defineSynchedData() {
        super.defineSynchedData();
        this.entityData.define(FLOATING, false);
        this.entityData.define(EATING, false);
    }

    @Override
    protected void registerGoals() {
        this.goalSelector.addGoal(0, new AvoidEntityGoal<>(this, Player.class, 32.0F, 0.9D, 1.5D, (livingEntity -> livingEntity.equals(this.getLastHurtMob()))));
        this.goalSelector.addGoal(1, new MeleeAttackGoal(this, 1.2D, true));
        this.goalSelector.addGoal(1, new SitWhenOrderedToGoal(this));
        this.goalSelector.addGoal(2, new FollowOwnerGoal(this, 1.25d, 18f, 7f, false));
        this.goalSelector.addGoal(2, new GoToSurfaceGoal(60));
        this.goalSelector.addGoal(3, new BreedGoal(this, 1.0D));
        this.goalSelector.addGoal(4, new SearchFoodGoal());
        this.goalSelector.addGoal(5, new FollowParentGoal(this, 1.2D));
        this.goalSelector.addGoal(6, new RandomStrollGoal(this, 1.0F));
        this.goalSelector.addGoal(7, new LookAtPlayerGoal(this, Player.class, 8.0F));
        this.goalSelector.addGoal(8, new RandomLookAroundGoal(this));

        this.targetSelector.addGoal(0, new HurtByTargetGoal(this).setAlertOthers());
        this.targetSelector.addGoal(1, new NearestAttackableTargetGoal<>(this, AbstractFish.class, 20, false, false, (fish) -> fish instanceof AbstractSchoolingFish && this.getHuntDelay() <= 0));
    }

    @Override
    public void addAdditionalSaveData(CompoundTag compound) {
        super.addAdditionalSaveData(compound);
        compound.putInt("HuntDelay", this.getHuntDelay());
        compound.putBoolean("Floating", this.isFloating());
        compound.putInt("FloatTime", this.floatTime);
        compound.putBoolean("Eating", this.isEating());
        compound.putInt("EatTime", this.eatTime);
        compound.putInt("EatDelay", this.eatDelay);
    }

    @Override
    public void readAdditionalSaveData(CompoundTag compound) {
        super.readAdditionalSaveData(compound);
        this.huntDelay = compound.getInt("HuntDelay");
        this.setFloating(compound.getBoolean("Floating"));
        this.floatTime = compound.getInt("FloatTime");
        this.setEating(compound.getBoolean("Eating"));
        this.eatTime = compound.getInt("EatTime");
        this.eatDelay = compound.getInt("EatDelay");
    }

    @Override
    public void awardKillScore(Entity killedEntity, int i, DamageSource damageSource) {
        super.awardKillScore(killedEntity, i, damageSource);
        if (killedEntity instanceof AbstractSchoolingFish) {
            this.huntDelay = 6000;
        }
    }

    @Override
    public int getExperienceReward() {
        return this.random.nextInt(3, 7);
    }

    @Override
    public void baseTick() {
        super.baseTick();
        if (this.getLastHurtMob() != null) {
            if (this.tickCount - this.getLastHurtMobTimestamp() > 100) {
                this.setLastHurtMob(null);
            }
        }
    }

    @Override
    public void aiStep() {
        super.aiStep();
        if (this.isAlive() && this.isEffectiveAi()) {
            if (this.isFloating()) {
                this.setDeltaMovement(this.getDeltaMovement().multiply(1.0D, 0.0D, 1.0D));
                this.setYya(0.0F);
                this.setAirSupply(this.getMaxAirSupply());

                if (--this.floatTime <= 0) {
                    this.setFloating(false);
                }
            }

            if (this.isUnderWater() && (this.getAirSupply() < 200 || this.random.nextFloat() <= 0.001F)) {
                this.setNeedsSurface(true);
            }

            if (this.isEating()) {
                if (this.eatDelay > 0) {
                    --this.eatDelay;
                } else {
                    Vec3 mouthPos = this.calculateMouthPos();
                    ((ServerLevel) this.level()).sendParticles(new ItemParticleOption(ParticleTypes.ITEM, this.getMainHandItem()), mouthPos.x(), mouthPos.y(), mouthPos.z(), 2, 0.0D, 0.1D, 0.0D, 0.05D);

                    if (this.getRandom().nextDouble() < 0.5D) {
                        this.playSound(ZAMSounds.OTTER_EAT.get(), 1.2F, 1.0F);
                    }
                    if (--this.eatTime <= 0) {
                        this.eat(this.level(), this.getMainHandItem());
                        this.setEating(false);
                    }
                }
            } else {
                if (this.isFood(this.getMainHandItem())) {
                    if (this.isInWater()) {
                        if (this.isFloating()) {
                            this.startEating();
                        } else {
                            this.setNeedsSurface(true);
                        }
                    } else if (this.onGround()) {
                        this.startEating();
                    }
                }
            }

            if (this.huntDelay > 0) {
                --this.huntDelay;
            }
        }
    }

    @Override
    public ItemStack eat(Level level, ItemStack itemStack) {
        if (itemStack.is(ZAMItems.CLAM.get())) {
            if (this.random.nextFloat() <= 0.07F) {
                Vec3 mouthPos = this.calculateMouthPos();
                ItemEntity pearl = new ItemEntity(level, mouthPos.x(), mouthPos.y(), mouthPos.z(), new ItemStack(ZAMItems.DOUBLOON.get()));

                pearl.setDeltaMovement(this.getRandom().nextGaussian() * 0.05D, this.getRandom().nextGaussian() * 0.05D + 0.2D, this.getRandom().nextGaussian() * 0.05D);
                level.addFreshEntity(pearl);
            }
            level.playSound(null, this.getX(), this.getY(), this.getZ(), SoundEvents.TURTLE_EGG_BREAK, SoundSource.NEUTRAL, 0.8F, 1.5F);
            itemStack.shrink(1);
            return itemStack;
        } else {
            return super.eat(level, itemStack);
        }
    }

    @Override
    public float getScale() {
        return this.isBaby() ? 0.6F : 1.0F;
    }

    @Override
    protected void pickUpItem(ItemEntity itemEntity) {
        ItemStack itemStack = itemEntity.getItem();

        if (this.rejectedItem(itemEntity)) {
            return;
        }

        ItemStack itemsEquipped = this.equipItemIfPossible(itemStack);
        if (!itemsEquipped.isEmpty()) {
            int count = itemsEquipped.getCount();

            if (count > 1) {
                var leftover = itemStack.copy();
                leftover.shrink(itemsEquipped.getCount());
                ItemEntity extraItems = new ItemEntity(this.level(), this.getX(), this.getY(), this.getZ(), leftover);
                this.level().addFreshEntity(extraItems);
            }
            this.onItemPickup(itemEntity);
            this.take(itemEntity, itemsEquipped.getCount());
            itemEntity.discard();
        }
    }

    @Override
    protected PathNavigation createNavigation(Level level) {
        return new OtterPathNavigation(this, level);
    }

    @Override
    public int getMaxAirSupply() {
        return 9600;
    }

    @Override
    protected void jumpInLiquid(TagKey<Fluid> fluidTag) {
        this.setDeltaMovement(this.getDeltaMovement().add(0.0D, (double) 0.08F * this.getAttribute(ForgeMod.SWIM_SPEED.get()).getValue(), 0.0D));
    }

    @Override
    public void travel(Vec3 speed) {
        if (this.isEffectiveAi() && this.isInWater()) {
            this.moveRelative(this.getSpeed(), speed);
            this.move(MoverType.SELF, this.getDeltaMovement());
            this.setDeltaMovement(this.getDeltaMovement().scale(0.9D));
            this.calculateEntityAnimation(false);
        } else {
            super.travel(speed);
        }
    }

    @Override
    public InteractionResult mobInteract(Player player, InteractionHand interactionHand) {
        ItemStack handStack = player.getItemInHand(interactionHand);
        Item item = handStack.getItem();
        Item itemForTaming = Items.TROPICAL_FISH;
        if (item == itemForTaming && !isTame()) {
            if (this.level().isClientSide()) {
                return InteractionResult.CONSUME;
            } else {
                if (!player.getAbilities().instabuild) {
                    handStack.shrink(1);
                }

                if (!ForgeEventFactory.onAnimalTame(this, player)) {
                    if (!this.level().isClientSide) {
                        super.tame(player);
                        this.navigation.recomputePath();
                        this.setTarget(null);
                        this.level().broadcastEntityEvent(this, (byte) 7);
                        setOrderedToSit(true);
                        this.setInSittingPose(true);
                    }
                }

                if (isTame() && interactionHand == InteractionHand.MAIN_HAND) {
                    setOrderedToSit(!isOrderedToSit());
                    setInSittingPose(!isOrderedToSit());
                    return InteractionResult.SUCCESS;
                }
            }
        }
        if (!this.isEating() && this.isFood(handStack)) {
            this.setItemInHand(InteractionHand.MAIN_HAND, handStack.split(1));
            handStack.shrink(1);
            return super.mobInteract(player, interactionHand);
        }
        return InteractionResult.PASS;
    }

    @Override
    public boolean canHoldItem(ItemStack itemStack) {
        return this.isFood(itemStack) && this.isHungryAt(itemStack);
    }

    @Override
    public boolean isFood(ItemStack stack) {
        return (stack.isEdible() && stack.is(ItemTags.FISHES)) || stack.is(ZAMItems.CLAM.get());
    }

    @Override
    public boolean canBreed() {
        return !this.isBaby();
    }

    @Override
    public AgeableMob getBreedOffspring(ServerLevel level, AgeableMob ageableMob) {
        return ZAMEntities.OTTER.get().create(level);
    }

    @Override
    public boolean doHurtTarget(Entity entity) {
        if (super.doHurtTarget(entity)) {
            this.playSound(ZAMSounds.BITE_ATTACK.get(), this.getSoundVolume(), this.getVoicePitch());
            return true;
        } else {
            return false;
        }
    }

    @Override
    protected SoundEvent getAmbientSound() {
        return ZAMSounds.OTTER_AMBIENT.get();
    }

    @Override
    protected SoundEvent getSwimSound() {
        return ZAMSounds.OTTER_SWIM.get();
    }

    @Override
    protected SoundEvent getHurtSound(DamageSource damageSource) {
        return ZAMSounds.OTTER_HURT.get();
    }

    @Override
    protected SoundEvent getDeathSound() {
        return ZAMSounds.OTTER_DEATH.get();
    }

    @Override
    public SpawnGroupData finalizeSpawn(ServerLevelAccessor levelAccessor, DifficultyInstance difficultyInstance, MobSpawnType mobSpawnType, SpawnGroupData spawnGroupData, CompoundTag p_146750_) {
        spawnGroupData = super.finalizeSpawn(levelAccessor, difficultyInstance, mobSpawnType, spawnGroupData, p_146750_);
        if (mobSpawnType.equals(MobSpawnType.SPAWNER) && this.random.nextFloat() <= 0.2F) {
            for (int i = 0; i < this.random.nextInt(1, 4); i++) {
                OtterEntity baby = ZAMEntities.OTTER.get().create(this.level());
                baby.moveTo(this.getX(), this.getY(), this.getZ(), this.getYRot(), 0.0F);
                baby.setBaby(true);
                levelAccessor.addFreshEntity(baby);
            }
        }
        return spawnGroupData;
    }

    private void manageEating() {
        if (this.eatDelay <= 0 && this.getMainHandItem().is(ZAMItems.CLAM.get())) {
            // Start eating clam animation
            eatDelay = 20; // Example delay
        } else {
            // Other eating logic
            eatDelay = 20; // Reset eat delay for other items
        }
    }

    public boolean isHungryAt(ItemStack foodStack) {
        return foodStack.is(ZAMItems.CLAM.get()) || this.getInLoveTime() <= 0;
    }

    private void rejectFood() {
        if (!this.getMainHandItem().isEmpty()) {
            ItemEntity itemEntity = new ItemEntity(this.level(), this.getX(), this.getY(), this.getZ(), this.getMainHandItem().copy());
            itemEntity.setPickUpDelay(40);
            itemEntity.setThrower(this.getUUID());
            this.getMainHandItem().shrink(1);
            this.level().addFreshEntity(itemEntity);
        }
    }

    public boolean rejectedItem(ItemEntity itemEntity) {
        if (itemEntity.getOwner() != null) {
            return itemEntity.getOwner().equals(this.getUUID());
        }
        return false;
    }

    private void startEating() {
        if (this.isFood(this.getMainHandItem())) {
            this.eatDelay = this.getMainHandItem().is(ZAMItems.CLAM.get()) ? 35 : 12;
            this.eatTime = 20;
            this.setEating(true);
        }
    }

    private void startFloating(int time) {
        this.floatTime = time;
        this.setFloating(true);
    }

    public Vec3 calculateMouthPos() {
        Vec3 viewVector = this.getViewVector(0.0F).scale(this.isFloating() ? 0.3D : 0.6D).add(0.0D, this.isFloating() ? 0.55D : 0.0D, 0.0D).scale(this.getScale());
        return new Vec3(this.getX() + viewVector.x(), this.getY() + viewVector.y(), this.getZ() + viewVector.z());
    }

    public int getHuntDelay() {
        return huntDelay;
    }

    public boolean needsSurface() {
        return this.needsSurface;
    }

    public void setNeedsSurface(boolean needsSurface) {
        this.needsSurface = needsSurface;
    }

    public boolean isEating() {
        return this.entityData.get(EATING);
    }

    public void setEating(boolean eating) {
        this.entityData.set(EATING, eating);
    }

    public boolean isFloating() {
        return this.entityData.get(FLOATING);
    }

    public void setFloating(boolean floating) {
        this.entityData.set(FLOATING, floating);
    }

    static class OtterMoveControl extends MoveControl {
        private final OtterEntity otter;

        public OtterMoveControl(OtterEntity otterEntity) {
            super(otterEntity);
            this.otter = otterEntity;
        }

        @Override
        public void tick() {
            if (this.otter.isInWater()) {
                if (!this.otter.needsSurface()) {
                    this.otter.setDeltaMovement(this.otter.getDeltaMovement().add(this.otter.getLookAngle().scale(this.otter.isFloating() ? 0.002F : 0.005F)));
                }

                if (!this.otter.isFloating()) {
                    if (this.operation == Operation.MOVE_TO && !this.mob.getNavigation().isDone()) {
                        double d0 = this.wantedX - this.mob.getX();
                        double d1 = this.wantedY - this.mob.getY();
                        double d2 = this.wantedZ - this.mob.getZ();
                        double distanceSqr = d0 * d0 + d1 * d1 + d2 * d2;

                        if (distanceSqr < (double) 2.5000003E-7F) {
                            this.mob.setZza(0.0F);
                        } else {
                            float yRot = (float) (Mth.atan2(d2, d0) * (double) (180F / (float) Math.PI)) - 90.0F;
                            this.mob.setYRot(this.rotlerp(this.mob.getYRot(), yRot, 40.0F));
                            this.mob.yBodyRot = this.mob.getYRot();
                            this.mob.yHeadRot = this.mob.getYRot();
                            float speed = (float) (this.speedModifier * this.mob.getAttributeValue(Attributes.MOVEMENT_SPEED));
                            this.mob.setSpeed(speed * 0.2F);

                            double horizontalDistance = Math.sqrt(d0 * d0 + d2 * d2);
                            if (Math.abs(d1) > (double) 1.0E-5F || Math.abs(horizontalDistance) > (double) 1.0E-5F) {
                                float xRot = -((float) (Mth.atan2(d1, horizontalDistance) * (double) (180F / (float) Math.PI)));
                                xRot = Mth.clamp(Mth.wrapDegrees(xRot), -180.0F, 180.0F);
                                this.mob.setXRot(this.rotlerp(this.mob.getXRot(), xRot, 45.0F));
                            }

                            BlockPos wantedPos = BlockPos.containing(this.wantedX, this.wantedY, this.wantedZ);
                            BlockState wantedBlockState = this.mob.level().getBlockState(wantedPos);

                            if (d1 > (double) this.mob.maxUpStep() && d0 * d0 + d2 * d2 < 4.0F && d1 <= 1.0D && wantedBlockState.getFluidState().isEmpty()) {
                                this.mob.getJumpControl().jump();
                                this.mob.setSpeed(speed);
                            }

                            float f0 = Mth.cos(this.mob.getXRot() * ((float) Math.PI / 180F));
                            float f1 = Mth.sin(this.mob.getXRot() * ((float) Math.PI / 180F));
                            this.mob.zza = f0 * speed;
                            this.mob.yya = -f1 * (speed);
                        }
                    } else {
                        this.mob.setSpeed(0.0F);
                        this.mob.setXxa(0.0F);
                        this.mob.setYya(0.0F);
                        this.mob.setZza(0.0F);
                    }
                }
            } else {
                super.tick();
            }
        }
    }

    static class OtterLookControl extends LookControl {
        private final OtterEntity otter;

        public OtterLookControl(OtterEntity otterEntity) {
            super(otterEntity);
            this.otter = otterEntity;
        }

        @Override
        public void tick() {
            if (this.otter.isInWater()) {
                if (this.lookAtCooldown > 0) {
                    --this.lookAtCooldown;
                    this.getYRotD().ifPresent((p_181134_) -> {
                        this.mob.yHeadRot = this.rotateTowards(this.mob.yHeadRot, p_181134_ + 20.0F, this.yMaxRotSpeed);
                    });
                    this.getXRotD().ifPresent((p_181132_) -> {
                        this.mob.setXRot(this.rotateTowards(this.mob.getXRot(), p_181132_ + 10.0F, this.xMaxRotAngle));
                    });
                } else {
                    if (this.mob.getNavigation().isDone()) {
                        this.mob.setXRot(this.rotateTowards(this.mob.getXRot(), 0.0F, 5.0F));
                    }

                    this.mob.yHeadRot = this.rotateTowards(this.mob.yHeadRot, this.mob.yBodyRot, this.yMaxRotSpeed);
                }
            } else {
                super.tick();
            }
        }
    }

    private void setupAnimationStates() {
        if (this.idleAnimationTimeout <= 0) {
            this.idleAnimationTimeout = this.random.nextInt(40) + 80;
            this.idleAnimationState.start(this.tickCount);
        } else {
            --this.idleAnimationTimeout;
        }

        if (this.isInSittingPose()) {
            sitAnimationState.startIfStopped(this.tickCount);
        } else {
            sitAnimationState.stop();
        }

        if (this.isInWater()) {
            swimAnimationState.startIfStopped(this.tickCount);
        } else {
            swimAnimationState.stop();
        }
    }

    protected void updateWalkAnimation(float v) {
        float f;
        if (this.getPose() == Pose.STANDING) {
            f = Math.min(v * 6.0f, 1.0f);
        } else {
            f = 0.0f;
        }
    }

    static class OtterPathNavigation extends WaterBoundPathNavigation {
        private final OtterEntity otter;

        public OtterPathNavigation(OtterEntity otterEntity, Level level) {
            super(otterEntity, level);
            this.otter = otterEntity;
        }

        @Override
        protected PathFinder createPathFinder(int p_26531_) {
            this.nodeEvaluator = new AmphibiousNodeEvaluator(true);
            return new PathFinder(this.nodeEvaluator, p_26531_);
        }

        @Override
        protected Vec3 getTempMobPos() {
            return new Vec3(this.otter.getX(), this.otter.getY(0.5D), this.otter.getZ());
        }

        @Override
        protected boolean canUpdatePath() {
            return true;
        }

        @Override
        public boolean isStableDestination(BlockPos destination) {
            if (this.otter.isInWater() && this.level.getBlockState(destination).isAir()) {
                return !(this.level.getBlockState(destination.below()).isAir() || this.level.getBlockState(destination.below()).getFluidState().is(FluidTags.WATER));
            } else {
                return !this.level.getBlockState(destination.below()).isAir();
            }
        }
    }

    static class BreedGoal extends net.minecraft.world.entity.ai.goal.BreedGoal {
        private final OtterEntity otter;

        public BreedGoal(OtterEntity otterEntity, double speed) {
            super(otterEntity, speed);
            this.otter = otterEntity;
        }

        @Override
        public boolean canUse() {
            return super.canUse() && !this.otter.isEating();
        }
    }

    static class RandomLookAroundGoal extends net.minecraft.world.entity.ai.goal.RandomLookAroundGoal {
        private final OtterEntity otter;

        public RandomLookAroundGoal(OtterEntity otterEntity) {
            super(otterEntity);
            this.otter = otterEntity;
        }

        @Override
        public boolean canUse() {
            return super.canUse() && !this.otter.isInWater() && !this.otter.isEating();
        }

        @Override
        public boolean canContinueToUse() {
            return super.canContinueToUse() && !this.otter.isInWater() && !this.otter.isEating();
        }
    }

    static class RandomStrollGoal extends net.minecraft.world.entity.ai.goal.RandomStrollGoal {
        private final OtterEntity otter;

        public RandomStrollGoal(OtterEntity otterEntity, double speed) {
            super(otterEntity, speed);
            this.otter = otterEntity;
        }

        @Override
        public boolean canUse() {
            return super.canUse() && !(this.otter.isFloating() || this.otter.needsSurface() || this.otter.isEating());
        }

        @Override
        public boolean canContinueToUse() {
            return super.canContinueToUse() && !(this.otter.isFloating() || this.otter.needsSurface() || this.otter.isEating());
        }
    }

    static class FollowParentGoal extends net.minecraft.world.entity.ai.goal.FollowParentGoal {
        private final OtterEntity otter;

        public FollowParentGoal(OtterEntity otterEntity, double speed) {
            super(otterEntity, speed);
            this.otter = otterEntity;
        }

        @Override
        public boolean canUse() {
            return super.canUse() && !this.otter.isEating();
        }

        @Override
        public boolean canContinueToUse() {
            return super.canContinueToUse() && !this.otter.isEating();
        }
    }

    class SearchFoodGoal extends Goal {
        private int searchCooldown = this.adjustedTickDelay(200);

        public SearchFoodGoal() {
            this.setFlags(EnumSet.of(Flag.MOVE, Flag.LOOK));
        }

        @Override
        public boolean canUse() {
            if (OtterEntity.this.getMainHandItem().isEmpty() && !OtterEntity.this.isBaby() && !OtterEntity.this.isInSittingPose()) {
                if (this.searchCooldown > 0) {
                    --this.searchCooldown;
                    return false;
                } else {
                    return OtterEntity.this.getTarget() == null && OtterEntity.this.getRandom().nextInt(reducedTickDelay(10)) == 0;
                }
            } else {
                return false;
            }
        }

        @Override
        public void start() {
            this.searchCooldown = this.adjustedTickDelay(200);
            List<ItemEntity> items = OtterEntity.this.level().getEntitiesOfClass(ItemEntity.class, OtterEntity.this.getBoundingBox().inflate(8.0D, 8.0D, 8.0D), item -> OtterEntity.this.canHoldItem(item.getItem()));
            if (!items.isEmpty()) {
                OtterEntity.this.getNavigation().moveTo(items.get(0), 1.2D);
            }
        }
    }


    class GoToSurfaceGoal extends Goal {
        private final int interval;
        private int intervalCounter;

        public GoToSurfaceGoal(int interval) {
            this.interval = interval;
            this.intervalCounter = interval;
            this.setFlags(EnumSet.of(Flag.MOVE, Flag.LOOK));
        }

        @Override
        public boolean canUse() {
            if (OtterEntity.this.needsSurface() && OtterEntity.this.isInWater() && !OtterEntity.this.isFloating()) {
                return OtterEntity.this.getAirSupply() < OtterEntity.this.getMaxAirSupply() - (0.1 * OtterEntity.this.getMaxAirSupply()) && this.intervalCounter-- <= 0;
            } else {
                return false;
            }
        }

        @Override
        public void start() {
            OtterEntity.this.getNavigation().moveTo(OtterEntity.this.getX(), OtterEntity.this.getY() + 1.0D, OtterEntity.this.getZ(), 1.0D);
            this.intervalCounter = this.interval;
        }

        @Override
        public boolean canContinueToUse() {
            return OtterEntity.this.needsSurface() && OtterEntity.this.isInWater() && !OtterEntity.this.isFloating();
        }

        @Override
        public void stop() {
            OtterEntity.this.setNeedsSurface(false);
            OtterEntity.this.startFloating(200);
        }
    }
}
