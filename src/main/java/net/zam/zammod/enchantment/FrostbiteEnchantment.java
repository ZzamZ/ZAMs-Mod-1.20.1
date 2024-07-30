package net.zam.zammod.enchantment;

import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.EnchantmentCategory;
import net.minecraft.world.level.Level;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.util.RandomSource;

public class FrostbiteEnchantment extends Enchantment {

    public FrostbiteEnchantment(Rarity rarity, EquipmentSlot... slots) {
        super(rarity, EnchantmentCategory.WEAPON, slots);
    }

    @Override
    public int getMinCost(int level) {
        return 10 + 20 * (level - 1);
    }

    @Override
    public int getMaxCost(int level) {
        return this.getMinCost(level) + 50;
    }

    @Override
    public int getMaxLevel() {
        return 2;
    }

    @Override
    public void doPostAttack(LivingEntity user, Entity target, int level) {
        if (target instanceof LivingEntity) {
            LivingEntity targetEntity = (LivingEntity) target;
            int frozenTicks = 100 * level;

            targetEntity.setTicksFrozen(frozenTicks);

            Level world = user.getCommandSenderWorld();
            if (world instanceof ServerLevel) {
                ServerLevel serverWorld = (ServerLevel) world;
                boolean soundEffects = true; // You can replace this with a config value
                boolean particles = true; // You can replace this with a config value

                if (soundEffects) {
                    serverWorld.playSound(null, user.blockPosition(), SoundEvents.SNOW_HIT, SoundSource.NEUTRAL, 0.75F, 1.0F);
                }
                if (particles) {
                    RandomSource random = serverWorld.random;
                    serverWorld.sendParticles(ParticleTypes.SNOWFLAKE, target.getX(), target.getY() + target.getBbHeight() / 2.0, target.getZ(), 10, 0.5, 0.5, 0.5, 0.02);
                }
            }
        }
    }
}
