package net.zam.zammod.loot;

//import net.minecraft.advancements.critereon.EntityPredicate;
//import net.minecraft.advancements.critereon.FishingHookPredicate;
//import net.minecraft.resources.ResourceLocation;
//import net.minecraft.world.level.storage.loot.LootContext;
//import net.minecraft.world.level.storage.loot.LootPool;
//import net.minecraft.world.level.storage.loot.entries.LootPoolEntryContainer;
//import net.minecraft.world.level.storage.loot.entries.LootTableReference;
//import net.minecraft.world.level.storage.loot.predicates.LootItemEntityPropertyCondition;
//import net.minecraftforge.event.LootTableLoadEvent;
//import net.minecraftforge.eventbus.api.SubscribeEvent;
//import net.minecraftforge.fml.common.Mod;
//import net.minecraftforge.fml.util.ObfuscationReflectionHelper;
//import net.zam.zammod.ZAMMod;
//import net.zam.zammod.ZAMConfig;
//
//import java.lang.reflect.Field;
//import java.util.ArrayList;
//import java.util.List;
//
//@Mod.EventBusSubscriber(modid = ZAMMod.MOD_ID)
//public class ZAMLootTables {
//    //Boxes
//    public static final ResourceLocation OLD_RECORD_BOX = register("box/old_record_box");
//    public static final ResourceLocation LOST_BOUNTY = register("box/lost_bounty");
//    public static final ResourceLocation LOST = register("gameplay/fishing/lost_bounty");
//
//
//
//
//    private static ResourceLocation register(String path) {
//        return ZAMBuiltInLootTables.register(new ResourceLocation(ZAMMod.MOD_ID, path));
//    }
//
//
//    @SubscribeEvent
//    public static void onLootTableLoad(LootTableLoadEvent event) {
//        ResourceLocation name = event.getName();
//        if (name.equals(net.minecraft.world.level.storage.loot.BuiltInLootTables.FISHING)) {
//            LootPool pool = event.getTable().getPool("main");
//            if (pool != null) {
//                ZAMMod.LOGGER.info("Fishing loot pool found, modifying...");
//                if (ZAMConfig.LOST_OPTIONS.addLostBountyToLoot.get()) {
//                    LootPoolEntryContainer neptuniumEntry = LootTableReference.lootTableReference(LOST).setWeight(1).setQuality(2)
//                            .when(LootItemEntityPropertyCondition.hasProperties(LootContext.EntityTarget.THIS,
//                                    EntityPredicate.Builder.entity().subPredicate(FishingHookPredicate.inOpenWater(true)))).build();
//                    addEntry(pool, neptuniumEntry);
//                }
//            } else {
//                ZAMMod.LOGGER.warn("No 'main' pool found in fishing loot table");
//            }
//        }
//    }
//
//
//    private static void addEntry(LootPool pool, LootPoolEntryContainer entry) {
//        try {
//            Field entries = ObfuscationReflectionHelper.findField(LootPool.class, "f_79023_");
//            entries.setAccessible(true);
//
//            LootPoolEntryContainer[] lootPoolEntriesArray = (LootPoolEntryContainer[]) entries.get(pool);
//            ArrayList<LootPoolEntryContainer> newLootEntries = new ArrayList<>(List.of(lootPoolEntriesArray));
//
//            if (newLootEntries.stream().anyMatch(e -> e == entry)) {
//                throw new RuntimeException("Attempted to add a duplicate entry to pool: " + entry);
//            }
//
//            newLootEntries.add(entry);
//
//            LootPoolEntryContainer[] newLootEntriesArray = new LootPoolEntryContainer[newLootEntries.size()];
//            newLootEntries.toArray(newLootEntriesArray);
//            entries.set(pool, newLootEntriesArray);
//        } catch (IllegalAccessException e) {
//            ZAMMod.LOGGER.error("Error occurred when attempting to add a new entry, to the fishing loot table");
//            e.printStackTrace();
//        }
//    }
//}
//