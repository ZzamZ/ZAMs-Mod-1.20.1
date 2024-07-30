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

      this.add(ZAMBlocks.HOP.get(), noDrop());
      this.dropSelf(ZAMBlocks.KEG.get());
      this.dropSelf(ZAMBlocks.MUG_EMPTY.get());
      this.dropSelf(ZAMBlocks.MUG_OF_SUN_PALE_ALE.get());
      this.dropSelf(ZAMBlocks.MUG_OF_DIGGER_BITTER.get());
      this.dropSelf(ZAMBlocks.MUG_OF_WITHER_STOUT.get());
      this.dropSelf(ZAMBlocks.MUG_OF_MAGNET_PILSNER.get());
      this.dropSelf(ZAMBlocks.MUG_OF_DROWNED_ALE.get());
      this.dropSelf(ZAMBlocks.MUG_OF_NIGHT_RAUCH.get());
      this.dropSelf(ZAMBlocks.MUG_OF_ICE_BEER.get());
      this.dropSelf(ZAMBlocks.MUG_OF_KVASS.get());
      this.dropSelf(ZAMBlocks.MUG_OF_LEPRECHAUN_CIDER.get());
      this.dropSelf(ZAMBlocks.MUG_OF_CHORUS_ALE.get());
      this.dropSelf(ZAMBlocks.MUG_OF_NETHER_PORTER.get());
      this.dropSelf(ZAMBlocks.MUG_OF_NIMBUS_NECTAR.get());
      this.dropSelf(ZAMBlocks.MUG_OF_STARDROP_SPARKLE.get());


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

     this.dropWhenSilkTouch(ZAMBlocks.PACKED_ICE_SLAB.get());
     this.dropWhenSilkTouch(ZAMBlocks.PACKED_ICE_STAIRS.get());
     this.dropSelf(ZAMBlocks.SAND_SLAB.get());
     this.dropSelf(ZAMBlocks.SAND_STAIRS.get());

     this.dropSelf(ZAMBlocks.GRIMM_SOIL.get());
     this.add(ZAMBlocks.GRIMM_FIRE.get(), noDrop());

        //this.dropSelf(ZAMBlocks.AVOCADO_PLANKS.get());
     this.dropSelf(ZAMBlocks.ARCADE_MACHINE.get());

     this.dropSelf(ZAMBlocks.OPAL_BLOCK.get());
     this.dropSelf(ZAMBlocks.AQUAMARINE_BLOCK.get());
     this.add(ZAMBlocks.OPAL_ORE.get(), block -> createOreDrop(ZAMBlocks.OPAL_ORE.get(), ZAMItems.OPAL.get()));
     this.add(ZAMBlocks.DEEPSLATE_OPAL_ORE.get(), block -> createOreDrop(ZAMBlocks.DEEPSLATE_OPAL_ORE.get(), ZAMItems.OPAL.get()));
     this.add(ZAMBlocks.AQUAMARINE_ORE.get(), block -> createOreDrop(ZAMBlocks.AQUAMARINE_ORE.get(), ZAMItems.AQUAMARINE.get()));
     this.add(ZAMBlocks.DEEPSLATE_AQUAMARINE_ORE.get(), block -> createOreDrop(ZAMBlocks.DEEPSLATE_AQUAMARINE_ORE.get(), ZAMItems.AQUAMARINE.get()));

    }

        @Override
        protected Iterable<Block> getKnownBlocks() {
            return ZAMBlocks.BLOCKS.getEntries().stream().map(RegistryObject::get)::iterator;
        }
    }