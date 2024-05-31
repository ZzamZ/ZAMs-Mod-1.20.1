package net.zam.zammod.datagen.loot;

import net.minecraft.advancements.critereon.ItemPredicate;
import net.minecraft.data.loot.BlockLootSubProvider;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.flag.FeatureFlags;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.storage.loot.LootPool;
import net.minecraft.world.level.storage.loot.LootTable;
import net.minecraft.world.level.storage.loot.entries.LootItem;
import net.minecraft.world.level.storage.loot.functions.ApplyBonusCount;
import net.minecraft.world.level.storage.loot.functions.SetItemCountFunction;
import net.minecraft.world.level.storage.loot.predicates.MatchTool;
import net.minecraft.world.level.storage.loot.providers.number.ConstantValue;
import net.minecraftforge.registries.RegistryObject;
import net.zam.zammod.registry.ZAMBlocks;
import net.zam.zammod.registry.ZAMItems;

import java.util.Set;

public class ZAMBlockLootTables extends BlockLootSubProvider {
    public ZAMBlockLootTables() {
        super(Set.of(), FeatureFlags.REGISTRY.allFlags());
    }

    @Override
   protected void generate() {
     this.dropSelf(ZAMBlocks.LOST_BOUNTY.get());

     this.dropSelf(ZAMBlocks.EMERALD_CRYSTAL_BLOCK.get());
     this.add(ZAMBlocks.EMERALD_CLUSTER.get(), (p_252201_) -> {
         return createSilkTouchDispatchTable(p_252201_, LootItem.lootTableItem(ZAMItems.EMERALD_SHARD.get()).apply(SetItemCountFunction.setCount(ConstantValue.exactly(4.0F))).apply(ApplyBonusCount.addOreBonusCount(Enchantments.BLOCK_FORTUNE)).when(MatchTool.toolMatches(ItemPredicate.Builder.item().of(ItemTags.CLUSTER_MAX_HARVESTABLES))).otherwise(this.applyExplosionDecay(p_252201_, LootItem.lootTableItem(ZAMItems.EMERALD_SHARD.get()).apply(SetItemCountFunction.setCount(ConstantValue.exactly(2.0F))))));
     });
     this.dropWhenSilkTouch(ZAMBlocks.SMALL_EMERALD_BUD.get());
     this.dropWhenSilkTouch(ZAMBlocks.MEDIUM_EMERALD_BUD.get());
     this.dropWhenSilkTouch(ZAMBlocks.LARGE_EMERALD_BUD.get());
     this.add(ZAMBlocks.BUDDING_EMERALD.get(), noDrop());

   //  this.add(ZAMBlocks.SCAFFINITY.get(), noDrop());

     this.dropSelf(ZAMBlocks.RUDDY_FROGLIGHT.get());
     this.dropSelf(ZAMBlocks.AZURE_FROGLIGHT.get());
     this.dropSelf(ZAMBlocks.EBON_FROGLIGHT.get());

     this.dropSelf(ZAMBlocks.WHITE_CONCRETE_SLAB.get());
     this.dropSelf(ZAMBlocks.LIGHT_BLUE_CONCRETE_SLAB.get());
     this.dropSelf(ZAMBlocks.GRAY_CONCRETE_SLAB.get());
     this.dropSelf(ZAMBlocks.BLACK_CONCRETE_SLAB.get());
     this.dropSelf(ZAMBlocks.BROWN_CONCRETE_SLAB.get());
     this.dropSelf(ZAMBlocks.RED_CONCRETE_SLAB.get());
     this.dropSelf(ZAMBlocks.ORANGE_CONCRETE_SLAB.get());
     this.dropSelf(ZAMBlocks.YELLOW_CONCRETE_SLAB.get());
     this.dropSelf(ZAMBlocks.LIME_CONCRETE_SLAB.get());
     this.dropSelf(ZAMBlocks.GREEN_CONCRETE_SLAB.get());
     this.dropSelf(ZAMBlocks.CYAN_CONCRETE_SLAB.get());
     this.dropSelf(ZAMBlocks.LIGHT_GRAY_CONCRETE_SLAB.get());
     this.dropSelf(ZAMBlocks.BLUE_CONCRETE_SLAB.get());
     this.dropSelf(ZAMBlocks.PURPLE_CONCRETE_SLAB.get());
     this.dropSelf(ZAMBlocks.MAGENTA_CONCRETE_SLAB.get());
     this.dropSelf(ZAMBlocks.PINK_CONCRETE_SLAB.get());

     this.dropSelf(ZAMBlocks.WHITE_CONCRETE_STAIRS.get());
     this.dropSelf(ZAMBlocks.LIGHT_BLUE_CONCRETE_STAIRS.get());
     this.dropSelf(ZAMBlocks.GRAY_CONCRETE_STAIRS.get());
     this.dropSelf(ZAMBlocks.BLACK_CONCRETE_STAIRS.get());
     this.dropSelf(ZAMBlocks.BROWN_CONCRETE_STAIRS.get());
     this.dropSelf(ZAMBlocks.RED_CONCRETE_STAIRS.get());
     this.dropSelf(ZAMBlocks.ORANGE_CONCRETE_STAIRS.get());
     this.dropSelf(ZAMBlocks.YELLOW_CONCRETE_STAIRS.get());
     this.dropSelf(ZAMBlocks.LIME_CONCRETE_STAIRS.get());
     this.dropSelf(ZAMBlocks.GREEN_CONCRETE_STAIRS.get());
     this.dropSelf(ZAMBlocks.CYAN_CONCRETE_STAIRS.get());
     this.dropSelf(ZAMBlocks.LIGHT_GRAY_CONCRETE_STAIRS.get());
     this.dropSelf(ZAMBlocks.BLUE_CONCRETE_STAIRS.get());
     this.dropSelf(ZAMBlocks.PURPLE_CONCRETE_STAIRS.get());
     this.dropSelf(ZAMBlocks.MAGENTA_CONCRETE_STAIRS.get());
     this.dropSelf(ZAMBlocks.PINK_CONCRETE_STAIRS.get());

     this.dropSelf(ZAMBlocks.PACKED_ICE_SLAB.get());
     this.dropSelf(ZAMBlocks.PACKED_ICE_STAIRS.get());
     this.dropSelf(ZAMBlocks.SAND_SLAB.get());
     this.dropSelf(ZAMBlocks.SAND_STAIRS.get());

     //this.dropSelf(ZAMBlocks.AVOCADO_PLANKS.get());
     this.dropSelf(ZAMBlocks.ARCADE_MACHINE.get());


    }

        @Override
        protected Iterable<Block> getKnownBlocks() {
            return ZAMBlocks.BLOCKS.getEntries().stream().map(RegistryObject::get)::iterator;
        }
    }