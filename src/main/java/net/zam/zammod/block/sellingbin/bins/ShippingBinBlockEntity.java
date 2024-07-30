package net.zam.zammod.block.sellingbin.bins;

import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.MenuProvider;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.registries.ForgeRegistries;
import net.zam.zammod.ZAMMod;
import net.zam.zammod.block.sellingbin.ImplementedInventory;
import net.zam.zammod.block.sellingbin.Trade;
import net.zam.zammod.gui.bin.ShippingBinMenu;
import net.zam.zammod.registry.ZAMBlockEntities;

import java.util.ArrayList;
import java.util.List;

public class ShippingBinBlockEntity extends BlockEntity implements MenuProvider {
    private long lastSellDay = -1L;
    private final ImplementedInventory inventory;

    public ShippingBinBlockEntity(BlockPos pos, BlockState state) {
        super(ZAMBlockEntities.SHIPPING_BIN_BLOCK_ENTITY.get(), pos, state);
        this.inventory = ImplementedInventory.ofSize(27);
    }

    @Override
    public Component getDisplayName() {
        return Component.translatable("block.selling-bin.wooden_bin");
    }

    @Override
    public AbstractContainerMenu createMenu(int syncId, Inventory playerInventory, Player player) {
        return new ShippingBinMenu(syncId, playerInventory, this.inventory);
    }

    public void sellItems(Player player) {
        Level world = this.level;
        if (world != null) {
            long day = world.getDayTime() / 24000L % Integer.MAX_VALUE;
            if (this.lastSellDay < day) {
                List<ItemStack> inventoryCopy = new ArrayList<>(inventory.getItems());
                List<Trade> trades = ZAMMod.trades;
                inventory.clearContent();
                int i = 0;

                for (ItemStack itemStack : inventoryCopy) {
                    boolean matched = false;
                    for (Trade trade : trades) {
                        if (ForgeRegistries.ITEMS.getKey(itemStack.getItem()).equals(new ResourceLocation(trade.getName()))) {
                            int sellAmount = trade.getSellAmount();
                            String currencyName = trade.getCurrency();
                            int currencyAmount = trade.getSellPrice();
                            if (itemStack.getCount() >= sellAmount) {
                                matched = true;
                                int remainingAmount = itemStack.getCount() % sellAmount;
                                if (remainingAmount != 0) {
                                    if (i > 26) {
                                        this.dropItem(world, itemStack.split(remainingAmount));
                                    } else {
                                        inventory.setItem(i, new ItemStack(itemStack.getItem(), remainingAmount));
                                    }
                                    ++i;
                                }
                                if (i > 26) {
                                    this.dropItem(world, new ItemStack(ForgeRegistries.ITEMS.getValue(new ResourceLocation(currencyName)), currencyAmount * (itemStack.getCount() / sellAmount)));
                                } else {
                                    inventory.setItem(i, new ItemStack(ForgeRegistries.ITEMS.getValue(new ResourceLocation(currencyName)), currencyAmount * (itemStack.getCount() / sellAmount)));
                                }
                            }
                        }
                    }

                    if (!matched) {
                        if (i > 26) {
                            this.dropItem(world, itemStack);
                        } else {
                            inventory.setItem(i, itemStack);
                        }
                    }
                    ++i;
                }
                this.lastSellDay = day;
            }
        }
    }

    private void dropItem(Level world, ItemStack itemStack) {
        // Drop the item in the world (implementation needed)
    }
}
