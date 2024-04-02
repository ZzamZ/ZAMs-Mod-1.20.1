package net.zam.zammod.item;

import net.minecraft.ChatFormatting;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.Component;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.Nullable;

import java.util.List;
import java.util.function.Supplier;

public class KrabbyPattyItem extends CharmItem {

    public KrabbyPattyItem(FoodProperties food, Supplier<Integer> eatingCooldown, Supplier<Boolean> isEnabled) {
        super(new Item.Properties().stacksTo(1).fireResistant().food(food));
        this.eatingCooldown = eatingCooldown;
        this.isEnabled = isEnabled;
    }

    @Override
    public void appendHoverText(ItemStack pStack, @Nullable Level pLevel, List<Component> pTooltipComponents, TooltipFlag pIsAdvanced) {
        if (Screen.hasShiftDown()) {
            pTooltipComponents.add(Component.literal("Endless Food Source").withStyle(ChatFormatting.GREEN));

        } else {
            pTooltipComponents.add(Component.literal("Press SHIFT for more info").withStyle(ChatFormatting.AQUA));

            super.appendHoverText(pStack, pLevel, pTooltipComponents, pIsAdvanced);
        }

    }

    private final Supplier<Integer> eatingCooldown;
    private final Supplier<Boolean> isEnabled;


    @Override
    protected boolean isCosmetic() {
        return !isEnabled.get();
    }

    @Override
    public ItemStack finishUsingItem(ItemStack stack, Level world, LivingEntity entity) {
        if (isEdible()) {
            entity.eat(world, stack.copy());
            if (!world.isClientSide && entity instanceof Player player) {
                int cooldown = eatingCooldown.get() * 20;
                if (cooldown > 0) {
                    player.getCooldowns().addCooldown(this, cooldown);
                }
            }
        }

        return stack;
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level level, Player player, InteractionHand hand) {
        if (!isEnabled.get()) {
            return InteractionResultHolder.pass(player.getItemInHand(hand));
        }
        return super.use(level, player, hand);
    }

    @Override
    public boolean isEdible() {
        if (!isEnabled.get()) {
            return false;
        }
        return super.isEdible();
    }
}
