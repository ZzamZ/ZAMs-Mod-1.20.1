package net.zam.zammod.effect;

import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.AttributeMap;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.player.Player;
import net.minecraft.nbt.CompoundTag;
import net.zam.zammod.registry.ZAMSounds;

public class StardropEffect extends MobEffect {
    public StardropEffect() {
        super(MobEffectCategory.BENEFICIAL, 0xFFD147A3);
        this.addAttributeModifier(Attributes.MAX_HEALTH, "91AEAA56-376B-4498-935B-2F7F68070635", 10.0D, AttributeModifier.Operation.ADDITION);
        this.addAttributeModifier(Attributes.MOVEMENT_SPEED, "91AEAA56-376B-4498-935B-2F7F68070636", 0.3D, AttributeModifier.Operation.MULTIPLY_TOTAL);
    }

    @Override
    public void applyEffectTick(LivingEntity entity, int amplifier) {
        if (!entity.level().isClientSide) {
            if (entity instanceof Player player) {
                CompoundTag data = player.getPersistentData();
                String effectKey = "hasStardropEffect";

                if (!data.getBoolean(effectKey)) {
                    data.putBoolean(effectKey, true); // Mark that the effect has been applied
                    player.getFoodData().eat(1, 1.0F);
                    player.setHealth(player.getMaxHealth());
                    entity.level().playSound(null, entity.getX(), entity.getY(), entity.getZ(), ZAMSounds.STARDROP.get(), SoundSource.PLAYERS, 1.0F, 1.0F);
                }
            }
        }
        super.applyEffectTick(entity, amplifier);
    }

    @Override
    public boolean isDurationEffectTick(int duration, int amplifier) {
        return true;
    }


    @Override
    public void removeAttributeModifiers(LivingEntity pLivingEntity, AttributeMap pAttributeMap, int pAmplifier) {
        super.removeAttributeModifiers(pLivingEntity, pAttributeMap, pAmplifier);
        if (pLivingEntity instanceof Player player) {
            player.getPersistentData().remove("hasStardropEffect");
        }
    }
}
