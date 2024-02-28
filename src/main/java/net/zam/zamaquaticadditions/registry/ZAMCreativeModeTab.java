package net.zam.zamaquaticadditions.registry;

import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;
import net.zam.zamaquaticadditions.ZAMAquaticAdditions;

public class ZAMCreativeModeTab {
    public static final DeferredRegister<CreativeModeTab> CREATIVE_MODE_TABS = DeferredRegister.create(Registries.CREATIVE_MODE_TAB,
            ZAMAquaticAdditions.MOD_ID);


    public static RegistryObject<CreativeModeTab> AQUATIC_TAB = CREATIVE_MODE_TABS.register("aquatic_tab", () ->
            CreativeModeTab.builder().icon(() -> new ItemStack(ZAMItems.GUARDIAN_CHESTPLATE.get())).title(Component.literal("ZAM Aquatic Additions")).displayItems((displayParameters, output) -> {

                output.accept(ZAMBlocks.LOST_BOUNTY.get());
                output.accept(ZAMBlocks.SCAFFINITY.get());
                output.accept(ZAMItems.MARLIN_LANCE.get());
                output.accept(ZAMItems.GUARDIAN_HELMET.get());
                output.accept(ZAMItems.GUARDIAN_CHESTPLATE.get());
                output.accept(ZAMItems.GUARDIAN_LEGGING.get());
                output.accept(ZAMItems.GUARDIAN_BOOTS.get());
                output.accept(ZAMItems.KOI_BUCKET.get());
                output.accept(ZAMItems.KOI.get());
                output.accept(ZAMItems.KOI_SPAWN_EGG.get());

                output.accept(ZAMItems.POKEMON_ALBUM_CASE.get());
                output.accept(ZAMItems.POKEMON_NIMBASA_CITY.get());
                output.accept(ZAMItems.POKEMON_DRIFTVEIL_CITY.get());
                output.accept(ZAMItems.POKEMON_ICIRRUS_CITY.get());
                output.accept(ZAMItems.POKEMON_VIRBANK_CITY.get());
                output.accept(ZAMItems.POKEMON_HUMILAU_CITY.get());
                output.accept(ZAMItems.POKEMON_EAST_PROVINCE.get());
                output.accept(ZAMItems.POKEMON_GEAR_STATION.get());
                output.accept(ZAMItems.POKEMON_VOLO.get());
                output.accept(ZAMItems.POKEMON_NS_CASTLE.get());
                output.accept(ZAMItems.POKEMON_ALBUM_CASE.get());


                output.accept(ZAMItems.WOOD_MEDAL.get());
                output.accept(ZAMItems.BRONZE_MEDAL.get());
                output.accept(ZAMItems.SILVER_MEDAL.get());
                output.accept(ZAMItems.GOLD_MEDAL.get());
                output.accept(ZAMItems.LEGENDARY_MEDAL.get());

                output.accept(ZAMItems.EMERALD_HORSE_ARMOR.get());
                output.accept(ZAMItems.NETHERITE_HORSE_ARMOR.get());

                output.accept(ZAMItems.BASIC_BADGE.get());
                output.accept(ZAMItems.TRIO_BADGE.get());
                output.accept(ZAMItems.INSECT_BADGE.get());
                output.accept(ZAMItems.BOLT_BADGE.get());
                output.accept(ZAMItems.QUAKE_BADGE.get());
                output.accept(ZAMItems.JET_BADGE.get());
                output.accept(ZAMItems.FREEZE_BADGE.get());
                output.accept(ZAMItems.LEGEND_BADGE.get());
                output.accept(ZAMItems.TOXIC_BADGE.get());
                output.accept(ZAMItems.WAVE_BADGE.get());
                output.accept(ZAMItems.GEAR_BADGE.get());
                output.accept(ZAMItems.NATURE_BADGE.get());
                output.accept(ZAMItems.KNUCKLE_BADGE.get());
                output.accept(ZAMItems.HEAT_BADGE.get());
                output.accept(ZAMItems.BOULDER_BADGE.get());



                output.accept(ZAMBlocks.EMERALD_CRYSTAL_BLOCK.get());
                output.accept(ZAMBlocks.BUDDING_EMERALD.get());
                output.accept(ZAMBlocks.EMERALD_CLUSTER.get());
                output.accept(ZAMBlocks.LARGE_EMERALD_BUD.get());
                output.accept(ZAMBlocks.MEDIUM_EMERALD_BUD.get());
                output.accept(ZAMBlocks.SMALL_EMERALD_BUD.get());

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
                output.accept(ZAMBlocks.GRASS_SLAB.get());
                output.accept(ZAMBlocks.GRASS_STAIRS.get());
                output.accept(ZAMBlocks.DIRT_SLAB.get());
                output.accept(ZAMBlocks.DIRT_STAIRS.get());
                output.accept(ZAMBlocks.COARSE_DIRT_SLAB.get());
                output.accept(ZAMBlocks.COARSE_DIRT_STAIRS.get());
                output.accept(ZAMBlocks.SAND_SLAB.get());
                output.accept(ZAMBlocks.SAND_STAIRS.get());

                output.accept(ZAMBlocks.GYM_SIGN1.get());
                output.accept(ZAMBlocks.GYM_SIGN2.get());
                output.accept(ZAMBlocks.GYM_SIGN3.get());
                output.accept(ZAMBlocks.GYM_SIGN4.get());
                output.accept(ZAMBlocks.GYM_SIGN5.get());
                output.accept(ZAMBlocks.GYM_SIGN6.get());
                output.accept(ZAMBlocks.GYM_SIGN7.get());
                output.accept(ZAMBlocks.GYM_SIGN8.get());
                output.accept(ZAMBlocks.GYM_SIGN9.get());
                output.accept(ZAMBlocks.GYM_SIGN10.get());
                output.accept(ZAMBlocks.GYM_SIGN11.get());
                output.accept(ZAMBlocks.GYM_SIGN12.get());
                output.accept(ZAMBlocks.GYM_SIGN13.get());
                output.accept(ZAMBlocks.GYM_SIGN14.get());
                output.accept(ZAMBlocks.GYM_SIGN15.get());
                output.accept(ZAMBlocks.GYM_SIGN16.get());

                output.accept(ZAMBlocks.SUS.get());




            }).build());




    public static void register(IEventBus eventBus) {
        CREATIVE_MODE_TABS.register(eventBus);
    }

}
