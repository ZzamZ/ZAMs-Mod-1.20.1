package net.zam.zammod.item;

import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.stats.Stats;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.FishingRodItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;
import net.zam.zammod.entity.AquamarineFishingHook;

public class AquamarineFishingRodItem extends FishingRodItem {
    public AquamarineFishingRodItem(Properties pProperties) {
        super(pProperties);
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level pLevel, Player pPlayer, InteractionHand pHand) {
        ItemStack itemstack = pPlayer.getItemInHand(pHand);

        if (pPlayer.fishing != null) {
            pPlayer.fishing.retrieve(itemstack);
            pPlayer.fishing = null;
            return InteractionResultHolder.success(itemstack);
        } else {
            pPlayer.awardStat(Stats.ITEM_USED.get(this));
            pLevel.playSound(null, pPlayer.getX(), pPlayer.getY(), pPlayer.getZ(), SoundEvents.FISHING_BOBBER_THROW, SoundSource.PLAYERS, 0.5F, 0.4F / (pLevel.random.nextFloat() * 0.4F + 0.8F));

            if (!pLevel.isClientSide) {
                Vec3 look = pPlayer.getLookAngle();
                double d0 = look.x * 0.4;
                double d1 = look.y * 0.4;
                double d2 = look.z * 0.4;

                AquamarineFishingHook fishingHook = new AquamarineFishingHook(EntityType.FISHING_BOBBER, pLevel);
                fishingHook.absMoveTo(pPlayer.getX(), pPlayer.getEyeY() - (double)0.1F, pPlayer.getZ(), pPlayer.getYRot(), pPlayer.getXRot());
                fishingHook.setOwner(pPlayer);
                fishingHook.setDeltaMovement(d0, d1, d2);
                pLevel.addFreshEntity(fishingHook);
            }

            return InteractionResultHolder.success(itemstack);
        }
    }
}
