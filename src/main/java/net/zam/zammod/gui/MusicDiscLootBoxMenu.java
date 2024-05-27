package net.zam.zammod.gui;

import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.MenuType;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.zam.zammod.registry.ZAMMenuTypes;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class MusicDiscLootBoxMenu extends AbstractContainerMenu {
    public static final MenuType<MusicDiscLootBoxMenu> MUSIC_DISC_LOOT_BOX_MENU = ZAMMenuTypes.MUSIC_DISC_LOOT_BOX_MENU.get();

    private final List<ItemStack> discs;
    private ItemStack currentDisc = ItemStack.EMPTY;

    public MusicDiscLootBoxMenu(int id, Inventory playerInventory) {
        super(MUSIC_DISC_LOOT_BOX_MENU, id);

        // Initialize the list of discs as a mutable list
        this.discs = new ArrayList<>(List.of(
                new ItemStack(Items.MUSIC_DISC_13),
                new ItemStack(Items.MUSIC_DISC_CAT),
                new ItemStack(Items.MUSIC_DISC_BLOCKS),
                new ItemStack(Items.MUSIC_DISC_CHIRP),
                new ItemStack(Items.MUSIC_DISC_FAR),
                new ItemStack(Items.MUSIC_DISC_MALL),
                new ItemStack(Items.MUSIC_DISC_MELLOHI),
                new ItemStack(Items.MUSIC_DISC_STAL),
                new ItemStack(Items.MUSIC_DISC_STRAD),
                new ItemStack(Items.MUSIC_DISC_WARD),
                new ItemStack(Items.MUSIC_DISC_11),
                new ItemStack(Items.MUSIC_DISC_WAIT),
                new ItemStack(Items.MUSIC_DISC_PIGSTEP),
                new ItemStack(Items.MUSIC_DISC_OTHERSIDE),
                new ItemStack(Items.MUSIC_DISC_5)
        ));
    }

    @Override
    public boolean stillValid(Player player) {
        return true;
    }

    public void spinDisc() {
        Collections.shuffle(discs); // Shuffle the mutable list each time the spin starts
        int randomIndex = (int) (Math.random() * discs.size());
        currentDisc = discs.get(randomIndex);
    }

    public ItemStack getCurrentDisc() {
        return currentDisc;
    }

    public List<ItemStack> getDiscItems() {
        return discs;
    }

    public boolean consumeKeyItem(Player player, ItemStack keyItem) {
        Inventory inventory = player.getInventory();
        for (int i = 0; i < inventory.getContainerSize(); i++) {
            ItemStack stack = inventory.getItem(i);
            if (ItemStack.isSameItem(stack, keyItem) && stack.getCount() >= keyItem.getCount()) {
                stack.shrink(keyItem.getCount());
                return true;
            }
        }
        return false;
    }

    @Override
    public ItemStack quickMoveStack(Player playerIn, int index) {
        // No-op since we don't have slots to transfer items to/from
        return ItemStack.EMPTY;
    }
}
