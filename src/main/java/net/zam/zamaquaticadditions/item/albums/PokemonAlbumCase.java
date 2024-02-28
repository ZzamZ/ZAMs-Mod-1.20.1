package net.zam.zamaquaticadditions.item.albums;

import net.minecraft.ChatFormatting;
import net.minecraft.core.NonNullList;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.ListTag;
import net.minecraft.nbt.Tag;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.stats.Stats;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.SlotAccess;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.ClickAction;
import net.minecraft.world.inventory.Slot;
import net.minecraft.world.inventory.tooltip.BundleTooltip;
import net.minecraft.world.inventory.tooltip.TooltipComponent;
import net.minecraft.world.item.*;
import net.minecraft.world.level.Level;
import net.zam.zamaquaticadditions.registry.ZAMItems;

import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Stream;

public class PokemonAlbumCase extends Item {
    private static final String TAG_ITEMS = "Items";
    public static final int MAX_RECORDS = 9;
    private static final int BUNDLE_IN_BUNDLE_WEIGHT = 4;
    private static Set<Item> ALLOWED_ITEMS;

    public PokemonAlbumCase(Item.Properties pProperties) {
        super(pProperties);
    }

    public static void initAllowedItems() {
        ALLOWED_ITEMS = Set.of(
                ZAMItems.POKEMON_NIMBASA_CITY.get(),
                ZAMItems.POKEMON_DRIFTVEIL_CITY.get(),
                ZAMItems.POKEMON_GEAR_STATION.get(),
                ZAMItems.POKEMON_EAST_PROVINCE.get(),
                ZAMItems.POKEMON_HUMILAU_CITY.get(),
                ZAMItems.POKEMON_VIRBANK_CITY.get(),
                ZAMItems.POKEMON_ICIRRUS_CITY.get(),
                ZAMItems.POKEMON_VOLO.get(),
                ZAMItems.POKEMON_NS_CASTLE.get());
    }

    private static boolean isItemAllowed(Item item) {
        return ALLOWED_ITEMS.contains(item); // Strictly check against allowed items
    }

    // Adapted method to add a record to the case, respecting the MAX_RECORDS limit
    private static boolean addRecord(ItemStack caseStack, ItemStack recordStack) {
        if (!isItemAllowed(recordStack.getItem()) || recordStack.getCount() != 1) return false;

        CompoundTag compoundTag = caseStack.getOrCreateTag();
        ListTag recordsListTag;
        if (!compoundTag.contains(TAG_ITEMS)) {
            recordsListTag = new ListTag();
        } else {
            recordsListTag = compoundTag.getList(TAG_ITEMS, Tag.TAG_COMPOUND);
        }

        // Check for duplicates
        for (int i = 0; i < recordsListTag.size(); i++) {
            ItemStack existingStack = ItemStack.of(recordsListTag.getCompound(i));
            if (ItemStack.isSameItemSameTags(existingStack, recordStack)) {
                return false; // This record is already in the case
            }
        }

        if (recordsListTag.size() >= MAX_RECORDS) {
            return false; // Prevent adding more than the max limit
        }

        CompoundTag recordTag = new CompoundTag();
        recordStack.save(recordTag);
        recordsListTag.add(recordTag);
        compoundTag.put(TAG_ITEMS, recordsListTag);
        caseStack.setTag(compoundTag);
        return true;
    }

    @Override
    public boolean overrideStackedOnOther(ItemStack caseStack, Slot slot, ClickAction action, Player player) {
        if (action != ClickAction.SECONDARY || caseStack.getCount() != 1) {
            return false;
        }

        ItemStack itemInSlot = slot.getItem();
        if (itemInSlot.isEmpty()) {
            removeOne(caseStack).ifPresent(removedRecord -> {
                this.playRemoveOneSound(player);
                slot.safeInsert(removedRecord);
            });
        } else if (isItemAllowed(itemInSlot.getItem())) {
            if (addRecord(caseStack, itemInSlot)) {
                this.playInsertSound(player);
                itemInSlot.shrink(1);
                return true;
            }
        }

        return false;
    }

    @Override
    public boolean overrideOtherStackedOnMe(ItemStack caseStack, ItemStack otherStack, Slot slot, ClickAction action, Player player, SlotAccess slotAccess) {
        if (action != ClickAction.SECONDARY || caseStack.getCount() != 1) {
            return false;
        }

        if (otherStack.isEmpty()) {
            // Logic to remove a record from the case when another item (otherStack) is empty
            removeOne(caseStack).ifPresent(removedRecord -> {
                this.playRemoveOneSound(player);
                slotAccess.set(removedRecord);
            });
        } else if (isItemAllowed(otherStack.getItem())) {
            // Logic to add a record to the case from another stack
            if (addRecord(caseStack, otherStack)) {
                this.playInsertSound(player);
                otherStack.shrink(1);
                return true;
            }
        }

        return false;
    }

    public InteractionResultHolder<ItemStack> use(Level pLevel, Player pPlayer, InteractionHand pUsedHand) {
        ItemStack itemstack = pPlayer.getItemInHand(pUsedHand);
        if (dropContents(itemstack, pPlayer)) {
            this.playDropContentsSound(pPlayer);
            pPlayer.awardStat(Stats.ITEM_USED.get(this));
            return InteractionResultHolder.sidedSuccess(itemstack, pLevel.isClientSide());
        } else {
            return InteractionResultHolder.fail(itemstack);
        }
    }
    @Override
    public void appendHoverText(ItemStack stack, @Nullable Level level, List<Component> tooltip, TooltipFlag flag) {
        super.appendHoverText(stack, level, tooltip, flag);

        List<ItemStack> records = getRecords(stack); // Assuming this method returns the list of records
        tooltip.add(Component.literal(records.size() + "/" + MAX_RECORDS).withStyle(ChatFormatting.GRAY));

        for (ItemStack record : records) {
            tooltip.add(Component.literal("- " + record.getHoverName().getString()).withStyle(ChatFormatting.DARK_AQUA));
        }
    }

    public static List<ItemStack> getRecords(ItemStack stack) {
        List<ItemStack> records = new ArrayList<>();
        CompoundTag tag = stack.getTag();
        if (tag != null && tag.contains(TAG_ITEMS, Tag.TAG_LIST)) {
            ListTag listTag = tag.getList(TAG_ITEMS, 10);
            for (int i = 0; i < listTag.size(); ++i) {
                records.add(ItemStack.of(listTag.getCompound(i)));
            }
        }
        return records;
    }

    private static Optional<ItemStack> removeOne(ItemStack pStack) {
        CompoundTag compoundtag = pStack.getOrCreateTag();
        if (!compoundtag.contains("Items")) {
            return Optional.empty();
        } else {
            ListTag listtag = compoundtag.getList("Items", 10);
            if (listtag.isEmpty()) {
                return Optional.empty();
            } else {
                int i = 0;
                CompoundTag compoundtag1 = listtag.getCompound(0);
                ItemStack itemstack = ItemStack.of(compoundtag1);
                listtag.remove(0);
                if (listtag.isEmpty()) {
                    pStack.removeTagKey("Items");
                }

                return Optional.of(itemstack);
            }
        }
    }

    private static boolean dropContents(ItemStack itemStack, Player player) {
        CompoundTag tag = itemStack.getOrCreateTag();
        if (!tag.contains("Records")) {
            return false;
        }

        if (player instanceof ServerPlayer) {
            ListTag listTag = tag.getList("Records", Tag.TAG_COMPOUND);

            for (int i = 0; i < listTag.size(); i++) {
                player.getInventory().placeItemBackInInventory(ItemStack.of(listTag.getCompound(i)));
            }
        }

        itemStack.removeTagKey("Records");
        return true;
    }

    private static Stream<ItemStack> getContents(ItemStack pStack) {
        CompoundTag compoundtag = pStack.getTag();
        if (compoundtag == null) {
            return Stream.empty();
        } else {
            ListTag listtag = compoundtag.getList("Items", 10);
            return listtag.stream().map(CompoundTag.class::cast).map(ItemStack::of);
        }
    }


    public void onDestroyed(ItemEntity pItemEntity) {
        ItemUtils.onContainerDestroyed(pItemEntity, getContents(pItemEntity.getItem()));
    }

    private void playRemoveOneSound(Entity pEntity) {
        pEntity.playSound(SoundEvents.BUNDLE_REMOVE_ONE, 0.8F, 0.8F + pEntity.level().getRandom().nextFloat() * 0.4F);
    }

    private void playInsertSound(Entity pEntity) {
        pEntity.playSound(SoundEvents.BUNDLE_INSERT, 0.8F, 0.8F + pEntity.level().getRandom().nextFloat() * 0.4F);
    }

    private void playDropContentsSound(Entity pEntity) {
        pEntity.playSound(SoundEvents.BUNDLE_DROP_CONTENTS, 0.8F, 0.8F + pEntity.level().getRandom().nextFloat() * 0.4F);
    }
}