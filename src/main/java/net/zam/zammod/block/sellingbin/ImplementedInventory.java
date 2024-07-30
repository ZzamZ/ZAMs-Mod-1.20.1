package net.zam.zammod.block.sellingbin;

import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.ItemStackHandler;

import java.util.List;

public interface ImplementedInventory extends IItemHandler {
    List<ItemStack> getItems();

    static ImplementedInventory of(ItemStackHandler items) {
        return new ImplementedInventoryImpl(items);
    }

    static ImplementedInventory ofSize(int size) {
        return of(new ItemStackHandler(size));
    }

    default int getContainerSize() {
        return getSlots();
    }

    default boolean isEmpty() {
        for (int i = 0; i < getContainerSize(); ++i) {
            ItemStack stack = getStackInSlot(i);
            if (!stack.isEmpty()) {
                return false;
            }
        }
        return true;
    }

    default ItemStack getItem(int slot) {
        return getStackInSlot(slot);
    }

    default ItemStack removeItem(int slot, int count) {
        return extractItem(slot, count, false);
    }

    default ItemStack removeItemNoUpdate(int slot) {
        return extractItem(slot, getStackInSlot(slot).getCount(), false);
    }

    default void setItem(int slot, ItemStack stack) {
        if (slot >= 0 && slot < getSlots()) {
            getItems().set(slot, stack);
            onContentsChanged(slot);
        }
    }

    default void clearContent() {
        for (int i = 0; i < getContainerSize(); ++i) {
            setItem(i, ItemStack.EMPTY);
        }
    }

    default void setChanged() {
    }

    default boolean stillValid(Player player) {
        return true;
    }

    default void onContentsChanged(int slot) {
        // Default implementation is empty
    }
}
