package net.zam.zammod.registry;

import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;
import net.zam.zammod.ZAMMod;

public class ZAMCreativeModeTab {
    public static final DeferredRegister<CreativeModeTab> CREATIVE_MODE_TABS = DeferredRegister.create(Registries.CREATIVE_MODE_TAB,
            ZAMMod.MOD_ID);


    public static RegistryObject<CreativeModeTab> AQUATIC_TAB = CREATIVE_MODE_TABS.register("aquatic_tab", () ->
            CreativeModeTab.builder().icon(() -> new ItemStack(ZAMItems.GUARDIAN_CHESTPLATE.get())).title(Component.literal("ZAM Aquatic Additions")).displayItems((displayParameters, output) -> {
                output.accept(ZAMBlocks.LOST_BOUNTY.get());
                output.accept(ZAMItems.STARDROP.get());
                output.accept(ZAMItems.DOUBLOON.get());
                output.accept(ZAMItems.LOST_KEY.get());
                output.accept(ZAMItems.AQUAMARINE_FISHING_ROD.get());

                //   output.accept(ZAMItems.OLD_RECORD_BOX.get());
                output.accept(ZAMBlocks.ARCADE_MACHINE.get());
                output.accept(ZAMBlocks.SCAFFINITY.get());
                output.accept(ZAMItems.MARLIN_LANCE.get());
                output.accept(ZAMItems.MARINE_BUBBLE.get());
                output.accept(ZAMItems.GUARDIAN_HELMET.get());
                output.accept(ZAMItems.GUARDIAN_CHESTPLATE.get());
                output.accept(ZAMItems.GUARDIAN_LEGGING.get());
                output.accept(ZAMItems.GUARDIAN_BOOTS.get());
                output.accept(ZAMItems.CORAL_FRUIT.get());
             //   output.accept(ZAMItems.OTTER_SPAWN_EGG.get());

                output.accept(ZAMItems.WOOD_MEDAL.get());
                output.accept(ZAMItems.BRONZE_MEDAL.get());
                output.accept(ZAMItems.SILVER_MEDAL.get());
                output.accept(ZAMItems.GOLD_MEDAL.get());
                output.accept(ZAMItems.LEGENDARY_MEDAL.get());

                output.accept(ZAMItems.EMERALD_HORSE_ARMOR.get());

             //   output.accept(ZAMItems.BASIC_BADGE.get());
              //  output.accept(ZAMItems.TRIO_BADGE.get());
              //  output.accept(ZAMItems.INSECT_BADGE.get());
              //  output.accept(ZAMItems.BOLT_BADGE.get());
              //  output.accept(ZAMItems.QUAKE_BADGE.get());
              //  output.accept(ZAMItems.JET_BADGE.get());
              //  output.accept(ZAMItems.FREEZE_BADGE.get());
              //  output.accept(ZAMItems.LEGEND_BADGE.get());
               // output.accept(ZAMItems.TOXIC_BADGE.get());
              //  output.accept(ZAMItems.WAVE_BADGE.get());
              //  output.accept(ZAMItems.GEAR_BADGE.get());
              //  output.accept(ZAMItems.NATURE_BADGE.get());
             //   output.accept(ZAMItems.KNUCKLE_BADGE.get());
             //   output.accept(ZAMItems.HEAT_BADGE.get());
             //   output.accept(ZAMItems.BOULDER_BADGE.get());




                output.accept(ZAMItems.EMERALD_SHARD.get());

                output.accept(ZAMItems.OPAL.get());
                output.accept(ZAMBlocks.OPAL_ORE.get());
                output.accept(ZAMBlocks.DEEPSLATE_OPAL_ORE.get());
                output.accept(ZAMBlocks.OPAL_BLOCK.get());
                output.accept(ZAMItems.AQUAMARINE.get());
                output.accept(ZAMBlocks.AQUAMARINE_ORE.get());
                output.accept(ZAMBlocks.DEEPSLATE_AQUAMARINE_ORE.get());
                output.accept(ZAMBlocks.AQUAMARINE_BLOCK.get());

                output.accept(ZAMBlocks.AZURE_FROGLIGHT.get());
                output.accept(ZAMBlocks.RUDDY_FROGLIGHT.get());
                output.accept(ZAMBlocks.EBON_FROGLIGHT.get());

                output.accept(ZAMBlocks.AZURE_FROGLIGHT.get());
                output.accept(ZAMBlocks.RUDDY_FROGLIGHT.get());
                output.accept(ZAMBlocks.EBON_FROGLIGHT.get());

                output.accept(ZAMBlocks.WHITE_CONCRETE_SLAB.get());
                output.accept(ZAMBlocks.LIGHT_GRAY_CONCRETE_SLAB.get());
                output.accept(ZAMBlocks.GRAY_CONCRETE_SLAB.get());
                output.accept(ZAMBlocks.BLACK_CONCRETE_SLAB.get());
                output.accept(ZAMBlocks.BROWN_CONCRETE_SLAB.get());
                output.accept(ZAMBlocks.RED_CONCRETE_SLAB.get());
                output.accept(ZAMBlocks.ORANGE_CONCRETE_SLAB.get());
                output.accept(ZAMBlocks.YELLOW_CONCRETE_SLAB.get());
                output.accept(ZAMBlocks.LIME_CONCRETE_SLAB.get());
                output.accept(ZAMBlocks.GREEN_CONCRETE_SLAB.get());
                output.accept(ZAMBlocks.CYAN_CONCRETE_SLAB.get());
                output.accept(ZAMBlocks.LIGHT_BLUE_CONCRETE_SLAB.get());
                output.accept(ZAMBlocks.BLUE_CONCRETE_SLAB.get());
                output.accept(ZAMBlocks.PURPLE_CONCRETE_SLAB.get());
                output.accept(ZAMBlocks.MAGENTA_CONCRETE_SLAB.get());
                output.accept(ZAMBlocks.PINK_CONCRETE_SLAB.get());

                output.accept(ZAMBlocks.WHITE_CONCRETE_STAIRS.get());
                output.accept(ZAMBlocks.LIGHT_GRAY_CONCRETE_STAIRS.get());
                output.accept(ZAMBlocks.GRAY_CONCRETE_STAIRS.get());
                output.accept(ZAMBlocks.BLACK_CONCRETE_STAIRS.get());
                output.accept(ZAMBlocks.BROWN_CONCRETE_STAIRS.get());
                output.accept(ZAMBlocks.RED_CONCRETE_STAIRS.get());
                output.accept(ZAMBlocks.ORANGE_CONCRETE_STAIRS.get());
                output.accept(ZAMBlocks.YELLOW_CONCRETE_STAIRS.get());
                output.accept(ZAMBlocks.LIME_CONCRETE_STAIRS.get());
                output.accept(ZAMBlocks.GREEN_CONCRETE_STAIRS.get());
                output.accept(ZAMBlocks.CYAN_CONCRETE_STAIRS.get());
                output.accept(ZAMBlocks.LIGHT_BLUE_CONCRETE_STAIRS.get());
                output.accept(ZAMBlocks.BLUE_CONCRETE_STAIRS.get());
                output.accept(ZAMBlocks.PURPLE_CONCRETE_STAIRS.get());
                output.accept(ZAMBlocks.MAGENTA_CONCRETE_STAIRS.get());
                output.accept(ZAMBlocks.PINK_CONCRETE_STAIRS.get());

                output.accept(ZAMBlocks.PACKED_ICE_SLAB.get());
                output.accept(ZAMBlocks.PACKED_ICE_STAIRS.get());

                output.accept(ZAMBlocks.SAND_SLAB.get());
                output.accept(ZAMBlocks.SAND_STAIRS.get());

            //    output.accept(ZAMItems.POKEMON_ALBUM_CASE.get());
            //    output.accept(ZAMItems.POKEMON_STRIATON_CITY.get());
            //    output.accept(ZAMItems.POKEMON_CASTELIA_CITY.get());
            //    output.accept(ZAMItems.POKEMON_NIMBASA_CITY.get());
            //    output.accept(ZAMItems.POKEMON_DRIFTVEIL_CITY.get());
            //    output.accept(ZAMItems.POKEMON_MISTRALTON_CITY.get());
            //    output.accept(ZAMItems.POKEMON_ICIRRUS_CITY.get());
            //    output.accept(ZAMItems.POKEMON_VIRBANK_CITY.get());
            //    output.accept(ZAMItems.POKEMON_HUMILAU_CITY.get());
            //    output.accept(ZAMItems.POKEMON_GEAR_STATION.get());
            //    output.accept(ZAMItems.POKEMON_EAST_PROVINCE.get());
            //    output.accept(ZAMItems.POKEMON_NS_CASTLE.get());
            //    output.accept(ZAMItems.POKEMON_VOLO.get());
//
            //    output.accept(ZAMItems.POKEMON_ALBUM_CASE.get());
            //    output.accept(ZAMItems.CASTLE_CRASHERS_DARK_SKIES.get());
            //    output.accept(ZAMItems.CASTLE_CRASHERS_RAGE_OF_THE_CHAMPION.get());
            //    output.accept(ZAMItems.CASTLE_CRASHERS_WINTER_BLISS.get());
            //    output.accept(ZAMItems.CASTLE_CRASHERS_SPACE_PIRATES.get());
            //    output.accept(ZAMItems.CASTLE_CRASHERS_PIERCING_LAZER.get());
            //    output.accept(ZAMItems.CASTLE_CRASHERS_SPANISH_WALTZ.get());
            //    output.accept(ZAMItems.CASTLE_CRASHERS_JUMPER.get());
            //    output.accept(ZAMItems.CASTLE_CRASHERS_THE_SHOW.get());
            //    output.accept(ZAMItems.CASTLE_CRASHERS_THE_FINAL_CONFRONTATION.get());
            //    output.accept(ZAMItems.CASTLE_CRASHERS_MUDHOLES.get());
            //    output.accept(ZAMItems.CASTLE_CRASHERS_FLUTEY.get());
            //    output.accept(ZAMItems.CASTLE_CRASHERS_FOUR_BRAVE_CHAMPIONS.get());
            //    output.accept(ZAMItems.CASTLE_CRASHERS_PLEASE_DONT.get());
            //    output.accept(ZAMItems.CASTLE_CRASHERS_RACE_AROUND_THE_WORLD.get());


             //   output.accept(ZAMBlocks.AVOCADO_PLANKS.get());

                output.accept(ZAMBlocks.GRIMM_SOIL.get());



                output.accept(ZAMItems.STARDEW_SMITHING_TEMPLATE.get());
                output.accept(ZAMItems.STARDEW_SWORD.get());
                output.accept(ZAMItems.STARDEW_AXE.get());
                output.accept(ZAMItems.STARDEW_PICKAXE.get());
                output.accept(ZAMItems.STARDEW_SHOVEL.get());
                output.accept(ZAMItems.STARDEW_HOE.get());



            }).build());


    public static RegistryObject<CreativeModeTab> BEER_TAB = CREATIVE_MODE_TABS.register("beer_tab", () ->
            CreativeModeTab.builder().icon(() -> new ItemStack(ZAMItems.MUG_OF_ICE_BEER.get())).title(Component.literal("Beer & Stuff")).displayItems((displayParameters, output) -> {
                output.accept(ZAMItems.HOP.get());
                output.accept(ZAMItems.HOP_SEEDS.get());
                output.accept(ZAMItems.DRY_SEEDS.get());
                output.accept(ZAMItems.MUG_EMPTY.get());
                output.accept(ZAMItems.MUG_OF_CHORUS_ALE.get());
                output.accept(ZAMItems.MUG_OF_DIGGER_BITTER.get());
                output.accept(ZAMItems.MUG_OF_DROWNED_ALE.get());
                output.accept(ZAMItems.MUG_OF_ICE_BEER.get());
                output.accept(ZAMItems.MUG_OF_KVASS.get());
                output.accept(ZAMItems.MUG_OF_LEPRECHAUN_CIDER.get());
                output.accept(ZAMItems.MUG_OF_MAGNET_PILSNER.get());
                output.accept(ZAMItems.MUG_OF_NETHER_PORTER.get());
                output.accept(ZAMItems.MUG_OF_NIGHT_RAUCH.get());
                output.accept(ZAMItems.MUG_OF_SUN_PALE_ALE.get());
                output.accept(ZAMItems.MUG_OF_WITHER_STOUT.get());
                output.accept(ZAMItems.MUG_OF_NIMBUS_NECTAR.get());
                output.accept(ZAMItems.MUG_OF_STARDROP_SPARKLE.get());
                output.accept(ZAMBlocks.KEG.get());
            }).build());

    public static void register(IEventBus eventBus) {
        CREATIVE_MODE_TABS.register(eventBus);
    }

}
