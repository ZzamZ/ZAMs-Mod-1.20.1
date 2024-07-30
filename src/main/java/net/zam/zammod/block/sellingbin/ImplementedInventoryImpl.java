package net.zam.zammod.block.sellingbin;

import net.minecraft.world.item.ItemStack;
import net.minecraftforge.items.ItemStackHandler;

import java.util.List;

public class ImplementedInventoryImpl extends ItemStackHandler implements ImplementedInventory {
    public ImplementedInventoryImpl(int size) {
        super(size);
    }

    public ImplementedInventoryImpl(ItemStackHandler items) {
        super(items.getSlots());
        for (int i = 0; i < items.getSlots(); i++) {
            setStackInSlot(i, items.getStackInSlot(i));
        }
    }

    @Override
    public List<ItemStack> getItems() {
        return stacks;
    }

    @Override
    public void onContentsChanged(int slot) {
        // This method is called every time the contents of the inventory change
        super.onContentsChanged(slot);
    }
}
