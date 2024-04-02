package net.zam.zammod.item;

import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Rarity;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraftforge.registries.ForgeRegistries;
import net.zam.zammod.ZAMMod;

import java.util.List;

public class CharmItem extends Item {

    public CharmItem(Properties properties) {
        super(properties.stacksTo(1).rarity(Rarity.RARE).fireResistant());
    }

    public CharmItem() {
        this(new Properties());
    }

    @Override
    public boolean canApplyAtEnchantingTable(ItemStack stack, Enchantment enchantment) {
        return enchantment != Enchantments.MENDING && super.canApplyAtEnchantingTable(stack, enchantment);
    }

    protected void addEffectsTooltip(List<MutableComponent> tooltip) {
        tooltip.add(net.minecraft.network.chat.Component.translatable("%s.tooltip.item.%s".formatted(ZAMMod.MOD_ID, getTooltipItemName())));
    }

    protected MutableComponent tooltipLine(String lineId, Object... args) {
        return Component.translatable("%s.tooltip.item.%s.%s".formatted(ZAMMod.MOD_ID, getTooltipItemName(), lineId), args);
    }

    protected String getTooltipItemName() {
        // noinspection ConstantConditions
        return ForgeRegistries.ITEMS.getKey(this).getPath();
    }

    protected boolean isCosmetic() {
        return false;
    }
}
