package net.zam.zammod.gui;

import net.minecraft.world.Container;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.MenuType;
import net.minecraft.world.inventory.Slot;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.zam.zammod.registry.ZAMMenuTypes;
import net.zam.zammod.util.Rarity;
import net.zam.zammod.util.RarityItem;
import net.zam.zammod.util.network.NetworkHandler;
import net.zam.zammod.util.network.packet.ConsumeLootBoxItemsPacket;

import java.util.ArrayList;
import java.util.List;

public class MusicDiscLootBoxMenu extends AbstractContainerMenu {
    public static final MenuType<MusicDiscLootBoxMenu> MUSIC_DISC_LOOT_BOX_MENU = ZAMMenuTypes.MUSIC_DISC_LOOT_BOX_MENU.get();
    private final ReadOnlyInventory discInventory;
    private final List<RarityItem> discs;

    public MusicDiscLootBoxMenu(int id, Inventory playerInventory) {
        super(MUSIC_DISC_LOOT_BOX_MENU, id);
        this.discs = new ArrayList<>(List.of(
                new RarityItem(new ItemStack(Items.MUSIC_DISC_13), Rarity.COMMON),
                new RarityItem(new ItemStack(Items.MUSIC_DISC_CAT), Rarity.COMMON)
                // Add more discs here
        ));

        this.discInventory = new ReadOnlyInventory(this.discs.size());
        for (int i = 0; i < this.discs.size(); i++) {
            this.discInventory.setItem(i, this.discs.get(i).getItemStack());
        }

        // Add slots for displaying discs (5 rows of 9 columns)
        for (int row = 0; row < 5; row++) {
            for (int col = 0; col < 9; col++) {
                addSlot(new ReadOnlySlot(discInventory, row * 9 + col, 8 + col * 18, 70 + row * 18));
            }
        }
    }

    @Override
    public boolean stillValid(Player player) {
        return true;
    }

    public List<RarityItem> getDiscItems() {
        return discs;
    }

    public boolean consumeItems(Player player, ItemStack keyItem, ItemStack caseItem) {
        Inventory inventory = player.getInventory();
        boolean keyConsumed = false;
        boolean caseConsumed = false;

        for (int i = 0; i < inventory.getContainerSize(); i++) {
            ItemStack stack = inventory.getItem(i);
            if (!keyConsumed && ItemStack.isSameItem(stack, keyItem) && stack.getCount() >= keyItem.getCount()) {
                stack.shrink(keyItem.getCount());
                keyConsumed = true;
            }
            if (!caseConsumed && ItemStack.isSameItem(stack, caseItem) && stack.getCount() >= caseItem.getCount()) {
                stack.shrink(caseItem.getCount());
                caseConsumed = true;
            }

            if (keyConsumed && caseConsumed) {
                if (player.level().isClientSide()) {
                    NetworkHandler.sendToServer(new ConsumeLootBoxItemsPacket(keyItem, caseItem));
                }
                return true;
            }
        }
        return false;
    }

    @Override
    public ItemStack quickMoveStack(Player playerIn, int index) {
        return ItemStack.EMPTY;
    }

    // ReadOnlySlot class to prevent interaction
    private static class ReadOnlySlot extends Slot {
        public ReadOnlySlot(Container inventoryIn, int index, int xPosition, int yPosition) {
            super(inventoryIn, index, xPosition, yPosition);
        }

        @Override
        public boolean mayPlace(ItemStack stack) {
            return false;
        }

        @Override
        public boolean mayPickup(Player playerIn) {
            return false;
        }
    }
}
