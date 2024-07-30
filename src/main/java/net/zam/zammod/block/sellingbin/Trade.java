package net.zam.zammod.block.sellingbin;

import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.material.MapColor;
import net.minecraftforge.registries.ForgeRegistries;

public class Trade {
    private String name;
    private String currency;
    private int sellPrice;
    private int sellAmount;
    private String color;
    private static final String DEFAULT_COLOR = "FF555555";

    // Default constructor
    public Trade() {
        this.color = Integer.toHexString(MapColor.WOOD.col).toUpperCase();
    }

    // Constructor for deserialization
    public Trade(FriendlyByteBuf buf) {
        this.name = buf.readUtf(32767);
        this.currency = buf.readUtf(32767);
        this.sellPrice = buf.readInt();
        this.sellAmount = buf.readInt();
        this.color = buf.readUtf(32767);
    }

    // Serialization method
    public void toNetwork(FriendlyByteBuf buf) {
        buf.writeUtf(this.name);
        buf.writeUtf(this.currency);
        buf.writeInt(this.sellPrice);
        buf.writeInt(this.sellAmount);
        buf.writeUtf(this.color);
    }

    // Getters
    public String getName() {
        return this.name;
    }

    public String getCurrency() {
        return this.currency;
    }

    public int getSellPrice() {
        return this.sellPrice;
    }

    public int getSellAmount() {
        return this.sellAmount;
    }

    // Get color as integer
    public int getColor() {
        if (this.color.equals("8")) {
            this.color = DEFAULT_COLOR;
        }
        return Integer.parseUnsignedInt(this.color, 16);
    }

    // Setters
    public void setName(String name) {
        this.name = name;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public void setSellPrice(int sellPrice) {
        this.sellPrice = sellPrice;
    }

    public void setSellAmount(int sellAmount) {
        this.sellAmount = sellAmount;
    }

    // Set color from integer
    public void setColor(int color) {
        this.color = Integer.toHexString(color).toUpperCase();
    }

    // Match ItemStack with trade item
    public boolean matches(ItemStack stack) {
        return ForgeRegistries.ITEMS.getKey(stack.getItem()).equals(new ResourceLocation(this.name));
    }
}
