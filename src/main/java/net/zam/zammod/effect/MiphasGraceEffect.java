package net.zam.zammod.effect;

import net.minecraft.core.BlockPos;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.phys.Vec3;

public class MiphasGraceEffect extends MobEffect {


    private static final double SWIM_SPEED_BOOST = 0.03; // Change this value to adjust the swim speed boost

    public MiphasGraceEffect(MobEffectCategory pCategory, int pColor) {
        super(pCategory, pColor);
        this.addAttributeModifier(Attributes.MAX_HEALTH, "2b43157a-a3d4-4e2b-8d58-95cfd1d8e750", 4.0D, AttributeModifier.Operation.ADDITION);
    }

    @Override
    public void applyEffectTick(LivingEntity entity, int amplifier) {
        super.applyEffectTick(entity, amplifier);

        // Check if the entity is in water
        if (entity.isInWater()) {
            applySwimmingSpeedBoost(entity, SWIM_SPEED_BOOST);
        }

        // Your existing effect code here...
        // ...
    }

    // You should override this method if you want the effect to be reapplied every tick.
    @Override
    public boolean isDurationEffectTick(int duration, int amplifier) {
        return true;
    }

    private void applySwimmingSpeedBoost(LivingEntity entity, double boost) {
        // Check if the entity is actually swimming
        if (entity.isSwimming()) {
            // Simulate Dolphin's Grace by increasing the entity's speed when swimming
            BlockPos entityPos = entity.blockPosition();
            if (entity.level().getBlockState(entityPos).is(Blocks.WATER)) {
                // Get the direction vector the entity is facing
                Vec3 lookVector = entity.getViewVector(1.0F);

                // Normalize the vector to get the direction without magnitude
                Vec3 direction = lookVector.normalize();

                // Apply the speed boost in the direction the entity is facing
                Vec3 boostedVelocity = entity.getDeltaMovement().add(
                        direction.x * boost,
                        direction.y * boost,
                        direction.z * boost
                );

                // Make sure the boosted velocity isn't exceeding a certain threshold
                // You can set the maxSpeed to the maximum velocity you want to allow
                double maxSpeed = 1.0;
                if (boostedVelocity.length() > maxSpeed) {
                    boostedVelocity = boostedVelocity.normalize().scale(maxSpeed);
                }

                entity.setDeltaMovement(boostedVelocity);
            }
        }
    }
}