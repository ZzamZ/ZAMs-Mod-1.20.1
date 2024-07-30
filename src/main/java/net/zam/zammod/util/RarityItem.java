package net.zam.zammod.util;

import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.world.item.ItemStack;

public class RarityItem {
    private final ItemStack itemStack;
    private final Rarity rarity;

    public RarityItem(ItemStack itemStack, Rarity rarity) {
        this.itemStack = itemStack;
        this.rarity = rarity;
    }

    public ItemStack getItemStack() {
        return itemStack;
    }

    public Rarity getRarity() {
        return rarity;
    }

    public void toBuffer(FriendlyByteBuf buffer) {
        buffer.writeItem(itemStack);
        buffer.writeEnum(rarity);
    }

    public static RarityItem fromBuffer(FriendlyByteBuf buffer) {
        ItemStack itemStack = buffer.readItem();
        Rarity rarity = buffer.readEnum(Rarity.class);
        return new RarityItem(itemStack, rarity);
    }
}
