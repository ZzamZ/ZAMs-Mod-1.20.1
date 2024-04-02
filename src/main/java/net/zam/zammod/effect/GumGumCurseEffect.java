package net.zam.zammod.effect;

import net.minecraft.core.BlockPos;
import net.minecraft.resources.ResourceKey;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraftforge.common.ForgeMod;
import net.zam.zammod.registry.ZAMSounds;

import java.util.*;

//public class GumGumCurseEffect extends MobEffect {

   // private final Map<UUID, ResourceKey<Level>> lastDimensionMap = new HashMap<>();

  //  public GumGumCurseEffect(MobEffectCategory pCategory, int pColor) {
    //    super(pCategory, pColor);
        // Attribute modifiers as previously defined
     //   this.addAttributeModifier(Attributes.MAX_HEALTH, "a4fc1f87-2d44-4422-987a-c38f9bbaad32", 10.0D, AttributeModifier.Operation.ADDITION);
     //   this.addAttributeModifier(Attributes.ATTACK_DAMAGE, "16df1c4e-96a4-4a8b-9e6f-e3c0dce52b2d", 1.0D, AttributeModifier.Operation.ADDITION);
    //    this.addAttributeModifier(ForgeMod.BLOCK_REACH.get(), "3a45e1a3-77b8-49dd-9eb6-e11c0f8a2c18", 2.0D, AttributeModifier.Operation.ADDITION);
    //    this.addAttributeModifier(ForgeMod.ENTITY_REACH.get(), "9f5c6ee0-e0a7-4850-9a3a-335f3e1d2a23", 2.0D, AttributeModifier.Operation.ADDITION);
  //  }

  // // @Override
   // public boolean isDurationEffectTick(int pDuration, int pAmplifier) {
  //      return true;
  //  }

  //  @Override
  //  public List<ItemStack> getCurativeItems() {
  //      return new ArrayList<>();
  //  }

   // @Override
  //  public void applyEffectTick(LivingEntity entity, int amplifier) {
  //      super.applyEffectTick(entity, amplifier);

   //     // Directly checking for water contact for debugging
    //    if (!entity.level().isClientSide) { // Ensure this runs only on the server side
    ///        boolean isInWater = entity.isInWaterOrRain() || entity.isInWater();
    //        System.out.println("Is entity in water: " + isInWater); // Debugging log

       //     if (isInWater) {
         //       System.out.println("Applying effects due to water contact"); // Debugging log

        //        // Apply effects directly for testing
          //      entity.addEffect(new MobEffectInstance(MobEffects.MOVEMENT_SLOWDOWN, 200, 3, false, false, true));
          //      entity.addEffect(new MobEffectInstance(MobEffects.DIG_SLOWDOWN, 200, 2, false, false, true));

                // Apply damage for testing
          //      entity.damageSources().magic();
          //  }
       // }

        // Existing logic to cap entity's health
       // if (entity.getHealth() > entity.getMaxHealth()) {
      //      entity.setHealth(entity.getMaxHealth());
     //   }
   // }

  //  private void checkAndReduceBubbleColumnBoost(LivingEntity entity) {
    //    BlockPos entityPos = entity.blockPosition();
   //     Block blockBelow = entity.level().getBlockState(entityPos.below()).getBlock();
     //   if (blockBelow == Blocks.SOUL_SAND && entity.level().getBlockState(entityPos.below(1)).is(Blocks.BUBBLE_COLUMN)) {
      //      double reductionFactor = -0.1D;
      //      double newY = entity.getDeltaMovement().y * reductionFactor;
       //     entity.setDeltaMovement(entity.getDeltaMovement().x, newY, entity.getDeltaMovement().z);
     //   }
   // }

 //   private void simulateUnderwaterBounce(LivingEntity entity) {
   //     if (!entity.onGround() && entity.getDeltaMovement().y < 0) {
   //         BlockPos blockUnderEntity = new BlockPos((int) entity.getX(), (int) (entity.getY() - 0.5), (int) entity.getZ());
    //        boolean isGroundNearby = false;
     //       for (int i = 0; i < 3; i++) {
     //           if (!entity.level().isEmptyBlock(blockUnderEntity.below(i))) {
     //               isGroundNearby = true;
     //               break;
     //           }
     //       }
     //       if (isGroundNearby && entity.fallDistance > 2.5) {
      //          double bounceVelocity = -entity.getDeltaMovement().y * 0.6;
      //          entity.setDeltaMovement(entity.getDeltaMovement().x, bounceVelocity, entity.getDeltaMovement().z);
      //          entity.fallDistance = 0;
       //         entity.level().playSound(null, entity.getX(), entity.getY(), entity.getZ(), ZAMSounds.BOING.get(), SoundSource.PLAYERS, 0.1F, 1.0F);
       //     }
     //   }
   // }
//}
