package net.zam.zammod.event.handler;

import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.projectile.ThrownTrident;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.EntityHitResult;
import net.minecraftforge.event.entity.ProjectileImpactEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.zam.zammod.registry.ZAMEnchantments;

import java.lang.reflect.Field;

public class ProjectileImpactHandler {

    @SubscribeEvent
    public void onProjectileImpact(ProjectileImpactEvent event) {
        if (event.getEntity() instanceof ThrownTrident) {
            ThrownTrident trident = (ThrownTrident) event.getEntity();
            if (trident.getOwner() instanceof LivingEntity) {
                LivingEntity owner = (LivingEntity) trident.getOwner();
                ItemStack tridentStack = getTridentItemStack(trident);
                int frostbiteLevel = EnchantmentHelper.getItemEnchantmentLevel(ZAMEnchantments.FROSTBITE.get(), tridentStack);

                if (frostbiteLevel > 0 && event.getRayTraceResult() instanceof EntityHitResult) {
                    EntityHitResult entityHitResult = (EntityHitResult) event.getRayTraceResult();
                    Entity hitEntity = entityHitResult.getEntity();
                    if (hitEntity instanceof LivingEntity) {
                        LivingEntity targetEntity = (LivingEntity) hitEntity;
                        applyFrostbiteEffect(targetEntity, frostbiteLevel, owner);

                        // Check if the owner hit themselves
                        if (owner.equals(targetEntity)) {
                            applyFrostbiteEffect(owner, frostbiteLevel, owner);
                        }
                    }
                }
            }
        }
    }

    private ItemStack getTridentItemStack(ThrownTrident trident) {
        try {
            Field field = ThrownTrident.class.getDeclaredField("tridentItem");
            field.setAccessible(true);
            return (ItemStack) field.get(trident);
        } catch (NoSuchFieldException | IllegalAccessException e) {
            e.printStackTrace();
            return ItemStack.EMPTY;
        }
    }

    private void applyFrostbiteEffect(LivingEntity target, int level, LivingEntity attacker) {
        int frozenTicks = 100 * level;

        target.setTicksFrozen(frozenTicks);

        Level world = target.getCommandSenderWorld();
        if (world instanceof ServerLevel) {
            ServerLevel serverWorld = (ServerLevel) world;
            boolean soundEffects = true; // Replace with config value if applicable
            boolean particles = true; // Replace with config value if applicable

            if (soundEffects) {
                serverWorld.playSound(null, target.blockPosition(), SoundEvents.SNOW_HIT, SoundSource.NEUTRAL, 0.75F, 1.0F);
            }
            if (particles) {
                RandomSource random = serverWorld.random;
                serverWorld.sendParticles(ParticleTypes.SNOWFLAKE, target.getX(), target.getY() + target.getBbHeight() / 2.0, target.getZ(), 10, 0.5, 0.5, 0.5, 0.02);
            }
        }
    }
}
