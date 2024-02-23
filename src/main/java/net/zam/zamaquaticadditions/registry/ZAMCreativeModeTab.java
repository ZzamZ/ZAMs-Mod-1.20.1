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
            CreativeModeTab.builder().icon(() -> new ItemStack(ZAMItems.C418_DOG.get())).title(Component.literal("ZAM Aquatic Additions")).displayItems((displayParameters, output) -> {
                output.accept(ZAMBlocks.LOST_BOUNTY.get());
                output.accept(ZAMItems.MARLIN_LANCE.get());

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
                output.accept(ZAMItems.MELODY_BADGE.get());
                output.accept(ZAMItems.SHADOW_BADGE.get());
                output.accept(ZAMItems.COSMIC_BADGE.get());
                output.accept(ZAMItems.VOID_BADGE.get());
                output.accept(ZAMItems.PIXIE_BADGE.get());


                output.accept(ZAMItems.C418_DOG.get());
                output.accept(ZAMItems.LUPUS_NOCTE_HOWLING.get());
                output.accept(ZAMItems.CAT_INDEV.get());
                output.accept(ZAMItems.HALO_MASHUP.get());
                output.accept(ZAMItems.STAR_WARS.get());
                output.accept(ZAMItems.CALL_OF_DUTY.get());
                output.accept(ZAMItems.POKEMON_EAST_PROVINCE.get());
                output.accept(ZAMItems.POKEMON_NIMBASA_CITY.get());
                output.accept(ZAMItems.POKEMON_DRIFTVEIL_CITY.get());
                output.accept(ZAMItems.POKEMON_VIRBANK_CITY.get());
                output.accept(ZAMItems.POKEMON_HUMILAU_CITY.get());
                output.accept(ZAMItems.POKEMON_GEAR_STATION.get());
                output.accept(ZAMItems.POKEMON_HUMILAU_CITY_GYM.get());
                output.accept(ZAMItems.HOLLOW_KNIGHT_CITY_OF_TEARS.get());
                output.accept(ZAMItems.HOLLOW_KNIGHT_GREENPATH.get());
                output.accept(ZAMItems.HOLLOW_KNIGHT_DIRTMOUTH.get());
                output.accept(ZAMItems.HOLLOW_KNIGHT_QUEENS_GARDEN.get());
                output.accept(ZAMItems.HOLLOW_KNIGHT_NIGHMARE_GRIMM.get());
                output.accept(ZAMItems.HOLLOW_KNIGHT_PURE_VESSEL.get());
                output.accept(ZAMItems.MARIO_INSIDE_THE_CASTLE_WALLS.get());
                output.accept(ZAMItems.MARIO_PIRANHA_PLANTS_LULLABY.get());
                output.accept(ZAMItems.MARIO_STEAM_GARDENS.get());
                output.accept(ZAMItems.MARIO_DIRE_DIRE_DOCKS.get());
                output.accept(ZAMItems.MARIO_BUBBLAINE.get());
                output.accept(ZAMItems.CASTLE_CRASHERS_DARK_SKIES.get());
                output.accept(ZAMItems.CASTLE_CRASHERS_RAGE_OF_THE_CHAMPION.get());
                output.accept(ZAMItems.CASTLE_CRASHERS_WINTER_BLISS.get());
                output.accept(ZAMItems.CASTLE_CRASHERS_SPACE_PIRATES.get());
                output.accept(ZAMItems.ZELDA_DRAGON_ROOST.get());
                output.accept(ZAMItems.ZELDA_GERUDO_VALLEY.get());
                output.accept(ZAMItems.ZELDA_RITO_VILLAGE.get());
                output.accept(ZAMItems.ZELDA_ZORA_DOMAIN.get());
                output.accept(ZAMItems.SPONGEBOB.get());
                output.accept(ZAMItems.UNO.get());
                output.accept(ZAMItems.JJK.get());
            }).build());




    public static void register(IEventBus eventBus) {
        CREATIVE_MODE_TABS.register(eventBus);
    }

}
