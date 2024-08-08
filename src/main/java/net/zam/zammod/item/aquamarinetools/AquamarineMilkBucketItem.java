package net.zam.zammod.item.aquamarinetools;

import com.google.common.collect.ImmutableList;
import net.minecraft.advancements.CriteriaTriggers;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.stats.Stats;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.ItemUtils;
import net.minecraft.world.item.UseAnim;
import net.minecraft.world.level.Level;
import net.minecraftforge.common.capabilities.ICapabilityProvider;
import net.minecraftforge.fluids.capability.wrappers.FluidBucketWrapper;
import net.zam.zammod.registry.ZAMItems;

public class AquamarineMilkBucketItem extends Item {
    public AquamarineMilkBucketItem(Item.Properties builder) {
        super(builder);
    }

    @Override
    public ItemStack finishUsingItem(ItemStack stack, Level worldIn, LivingEntity entityLiving) {
        if (!worldIn.isClientSide) {
            ImmutableList<MobEffectInstance> effects = ImmutableList.copyOf(entityLiving.getActiveEffects());
            for (int i = 0; i < effects.size(); ++i) {
                entityLiving.removeEffect(effects.get(i).getEffect());
            }
        }

        int level = stack.getOrCreateTag().getInt("FluidLevel");

        if (entityLiving instanceof ServerPlayer player) {
            CriteriaTriggers.CONSUME_ITEM.trigger(player, stack);
            player.awardStat(Stats.ITEM_USED.get(this));
        }

        if (entityLiving instanceof Player && !((Player) entityLiving).getAbilities().instabuild) {
            if (level > 0) stack.getOrCreateTag().putInt("FluidLevel", level - 1);
            else stack.shrink(1);
        }

        return stack.isEmpty() ? AquamarineBucketItem.getEmptyBucket() : stack;
    }

    @Override
    public int getUseDuration(ItemStack stack) {
        return 32;
    }

    @Override
    public UseAnim getUseAnimation(ItemStack stack) {
        return UseAnim.DRINK;
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level worldIn, Player playerIn, InteractionHand handIn) {
        return ItemUtils.startUsingInstantly(worldIn, playerIn, handIn);
    }

    @Override
    public ICapabilityProvider initCapabilities(ItemStack stack, @javax.annotation.Nullable net.minecraft.nbt.CompoundTag nbt) {
        return new FluidBucketWrapper(stack);
    }

    @Override
    public ItemStack getCraftingRemainingItem(ItemStack stack) {
        int level = stack.getOrCreateTag().getInt("FluidLevel");
        if (level > 0) {
            ItemStack newStack = new ItemStack(ZAMItems.AQUAMARINE_MILK_BUCKET.get());
            newStack.getOrCreateTag().putInt("FluidLevel", level - 1);
            return newStack;
        }
        return AquamarineBucketItem.getEmptyBucket();
    }
}