package net.zam.zamaquaticadditions.item.records.cases;

import net.minecraft.ChatFormatting;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.ListTag;
import net.minecraft.nbt.Tag;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.stats.Stats;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.SlotAccess;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.ClickAction;
import net.minecraft.world.inventory.Slot;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.ItemUtils;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import net.zam.zamaquaticadditions.registry.ZAMItems;

import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Stream;

public class CastleCrashersAlbumCase extends Item {
    private static final String TAG_ITEMS = "Items";
    public static final int MAX_RECORDS = 14;
    private static Set<Item> ALLOWED_ITEMS;

    public CastleCrashersAlbumCase(Properties pProperties) {
        super(pProperties);
    }

    public static void initAllowedItems() {
        ALLOWED_ITEMS = Set.of(
                ZAMItems.CASTLE_CRASHERS_DARK_SKIES.get(),
                ZAMItems.CASTLE_CRASHERS_RAGE_OF_THE_CHAMPION.get(),
                ZAMItems.CASTLE_CRASHERS_WINTER_BLISS.get(),
                ZAMItems.CASTLE_CRASHERS_SPACE_PIRATES.get(),
                ZAMItems.CASTLE_CRASHERS_PIERCING_LAZER.get(),
                ZAMItems.CASTLE_CRASHERS_SPANISH_WALTZ.get(),
                ZAMItems.CASTLE_CRASHERS_JUMPER.get(),
                ZAMItems.CASTLE_CRASHERS_THE_SHOW.get(),
                ZAMItems.CASTLE_CRASHERS_THE_FINAL_CONFRONTATION.get(),
                ZAMItems.CASTLE_CRASHERS_MUDHOLES.get(),
                ZAMItems.CASTLE_CRASHERS_FLUTEY.get(),
                ZAMItems.CASTLE_CRASHERS_FOUR_BRAVE_CHAMPIONS.get(),
                ZAMItems.CASTLE_CRASHERS_PLEASE_DONT.get(),
                ZAMItems.CASTLE_CRASHERS_RACE_AROUND_THE_WORLD.get());



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
    public void inventoryTick(ItemStack stack, Level world, Entity entity, int itemSlot, boolean isSelected) {
        if (world.isClientSide) {
            return; // Ensure logic runs only on the server side.
        }

        if (entity instanceof Player player && isSelected) {
            String translationKey = stack.getItem().getDescriptionId(); // Use the translation key as a unique identifier.
            CompoundTag playerData = player.getPersistentData();
            final String key = "ZAMCrouchState" + translationKey; // Construct a unique key for persistent data.

            boolean wasCrouching = playerData.getBoolean(key);

            if (player.isCrouching() && !wasCrouching) {
                cycleRecords(stack, player);
                playerData.putBoolean(key, true);
            } else if (!player.isCrouching()) {
                playerData.putBoolean(key, false);
            }
        }
    }

    private void cycleRecords(ItemStack stack, Player player) {
        CompoundTag compoundTag = stack.getOrCreateTag();
        if (!compoundTag.contains(TAG_ITEMS, Tag.TAG_LIST)) return;

        ListTag recordsListTag = compoundTag.getList(TAG_ITEMS, 10);
        if (recordsListTag.size() <= 1) return; // No need to cycle for one or no records

        // Cycle the records
        CompoundTag firstRecord = recordsListTag.getCompound(0);
        recordsListTag.remove(0);
        recordsListTag.add(firstRecord);

        compoundTag.put(TAG_ITEMS, recordsListTag);
        stack.setTag(compoundTag);

        // Optional: provide feedback to the player
        player.level().playSound(null, player.blockPosition(), SoundEvents.ITEM_FRAME_ROTATE_ITEM, SoundSource.PLAYERS, 0.5F, 1.0F);
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
            // Fetch the item's registry name
            String registryName = record.getItem().getDescriptionId();
            // Construct the description key by appending ".desc" to the registry name
            String descriptionKey = registryName + ".desc";
            // Fetch the description using the key, assuming you have a method to do so. Here, it's just a placeholder.
            String trackDescription = Component.translatable(descriptionKey).getString();

            // Add the fetched description to the tooltip
            tooltip.add(Component.literal("- " + trackDescription).withStyle(ChatFormatting.DARK_AQUA));
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