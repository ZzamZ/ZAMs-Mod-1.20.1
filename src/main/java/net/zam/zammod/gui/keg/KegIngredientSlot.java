package net.zam.zammod.gui.keg;

import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.SlotItemHandler;

public class KegIngredientSlot extends SlotItemHandler {
    public KegIngredientSlot(IItemHandler itemHandler, int index, int x, int y) {
        super(itemHandler, index, x, y);
    }
}