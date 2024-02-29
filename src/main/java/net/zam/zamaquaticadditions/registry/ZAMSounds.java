package net.zam.zamaquaticadditions.registry;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import net.zam.zamaquaticadditions.ZAMAquaticAdditions;

public class ZAMSounds {
    public static final DeferredRegister<SoundEvent> SOUND_EVENTS = DeferredRegister.create(ForgeRegistries.SOUND_EVENTS, ZAMAquaticAdditions.MOD_ID);


    //Koi
    public static final RegistryObject<SoundEvent> KOI_FLOP = registerSoundEvent("entity.koi.flop");


    //Cheese
    public static final RegistryObject<SoundEvent> BLUE_CHEESE_MADE = registerSoundEvent("block.zamaquaticadditions.cheese_cauldron.blue_cheese_made");
    public static final RegistryObject<SoundEvent> CHEESE_CAULDRON_EMPTY = registerSoundEvent("block.zamaquaticadditions.cheese_cauldron.empty");
    public static final RegistryObject<SoundEvent> CHEESE_MADE = registerSoundEvent("block.zamaquaticadditions.cheese_cauldron.cheese_made");
    public static final RegistryObject<SoundEvent> NETHER_CHEESE_MADE = registerSoundEvent("block.zamaquaticadditions.cheese_cauldron.nether_cheese_made");
    public static final RegistryObject<SoundEvent> POTION_EFFECT_BEGIN = registerSoundEvent("effect.zamaquaticadditions.potion_effect.begin");
    public static final RegistryObject<SoundEvent> POTION_EFFECT_END = registerSoundEvent("effect.zamaquaticadditions.potion_effect.end");



    //Devil Fruits
    public static RegistryObject<SoundEvent> BOING = registerSoundEvent("boing");


    //Music Tracks
    public static RegistryObject<SoundEvent> POKEMON_EAST_PROVINCE = registerSoundEvent("pokemon_east_province");
    public static RegistryObject<SoundEvent> POKEMON_NIMBASA_CITY = registerSoundEvent("pokemon_nimbasa_city");
    public static RegistryObject<SoundEvent> POKEMON_DRIFTVEIL_CITY = registerSoundEvent("pokemon_driftveil_city");
    public static RegistryObject<SoundEvent> POKEMON_STRIATON_CITY = registerSoundEvent("pokemon_striaton_city");
    public static RegistryObject<SoundEvent> POKEMON_ACCUMULA_TOWN = registerSoundEvent("pokemon_accumula_town");
    public static RegistryObject<SoundEvent> POKEMON_VIRBANK_CITY = registerSoundEvent("pokemon_virbank_city");
    public static RegistryObject<SoundEvent> POKEMON_ICIRRUS_CITY = registerSoundEvent("pokemon_icirrus_city");
    public static RegistryObject<SoundEvent> POKEMON_LUMIOSE_CITY = registerSoundEvent("pokemon_lumiose_city");
    public static RegistryObject<SoundEvent> POKEMON_HUMILAU_CITY = registerSoundEvent("pokemon_humilau_city");
    public static RegistryObject<SoundEvent> POKEMON_GEAR_STATION = registerSoundEvent("pokemon_gear_station");
    public static RegistryObject<SoundEvent> POKEMON_NS_CASTLE = registerSoundEvent("pokemon_ns_castle");
    public static RegistryObject<SoundEvent> POKEMON_WATER = registerSoundEvent("pokemon_water");
    public static RegistryObject<SoundEvent> POKEMON_GROUND = registerSoundEvent("pokemon_dive");
    public static RegistryObject<SoundEvent> POKEMON_UNOVA_ROAD = registerSoundEvent("pokemon_unova_road");
    public static RegistryObject<SoundEvent> POKEMON_HOENN_ROAD = registerSoundEvent("pokemon_hoenn_road");
    public static RegistryObject<SoundEvent> POKEMON_VOLO = registerSoundEvent("pokemon_volo");
    public static RegistryObject<SoundEvent> POKEMON_SKYARROW_BRIDGE = registerSoundEvent("pokemon_skyarrow_bridge");
    public static RegistryObject<SoundEvent> POKEMON_CASTELIA_CITY = registerSoundEvent("pokemon_castelia_city");
    public static RegistryObject<SoundEvent> POKEMON_MISTRALTON_CITY = registerSoundEvent("pokemon_mistralton_city");
    public static RegistryObject<SoundEvent> POKEMON_MISTRALTON_GYM = registerSoundEvent("pokemon_mistralton_gym");
    public static RegistryObject<SoundEvent> POKEMON_BLACK_TOWER = registerSoundEvent("pokemon_black_tower");
    public static RegistryObject<SoundEvent> POKEMON_LOREKEERER_ZINNIA = registerSoundEvent("pokemon_lorekeeper_zinnia");
    public static RegistryObject<SoundEvent> POKEMON_ALDER_THEME = registerSoundEvent("pokemon_alder_theme");
    public static RegistryObject<SoundEvent> POKEMON_NIMBASA_JAZZ = registerSoundEvent("pokemon_nimbasa_jazz");



    public static RegistryObject<SoundEvent> HOLLOW_KNIGHT_CITY_OF_TEARS = registerSoundEvent("hollow_knight_city_of_tears");
    public static RegistryObject<SoundEvent> HOLLOW_KNIGHT_GREENPATH = registerSoundEvent("hollow_knight_greenpath");
    public static RegistryObject<SoundEvent> HOLLOW_KNIGHT_DIRTMOUTH = registerSoundEvent("hollow_knight_dirtmouth");
    public static RegistryObject<SoundEvent> HOLLOW_KNIGHT_QUEENS_GARDEN = registerSoundEvent("hollow_knight_queens_garden");
    public static RegistryObject<SoundEvent> HOLLOW_KNIGHT_NIGHTMARE_GRIMM = registerSoundEvent("hollow_knight_nightmare_grimm");
    public static RegistryObject<SoundEvent> HOLLOW_KNIGHT_PURE_VESSEL = registerSoundEvent("hollow_knight_pure_vessel");
    public static RegistryObject<SoundEvent> MARIO_INSIDE_THE_CASTLE_WALLS = registerSoundEvent("mario_inside_the_castle_walls");
    public static RegistryObject<SoundEvent> MARIO_DIRE_DOCKS = registerSoundEvent("mario_dire_docks");
    public static RegistryObject<SoundEvent> MARIO_PIRANHA_PLANT_LULLABY = registerSoundEvent("mario_piranha_plants_lullaby");
    public static RegistryObject<SoundEvent> MARIO_STEAM_GARDENS = registerSoundEvent("mario_steam_gardens");
    public static RegistryObject<SoundEvent> MARIO_BUBBLAINE = registerSoundEvent("mario_bubblaine");
    public static RegistryObject<SoundEvent> MARIO_FOSSIL_FALLS = registerSoundEvent("mario_fossil_falls");
    public static RegistryObject<SoundEvent> CASTLE_CRASHERS_RAGE_OF_THE_CHAMPION = registerSoundEvent("castle_crashers_rage_of_the_champion");
    public static RegistryObject<SoundEvent> CASTLE_CRASHERS_WINTER_BLISS = registerSoundEvent("castle_crashers_winter_bliss");
    public static RegistryObject<SoundEvent> CASTLE_CRASHERS_SPACE_PIRATES = registerSoundEvent("castle_crashers_space_pirates");
    public static RegistryObject<SoundEvent> CASTLE_CRASHERS_DARK_SKIES = registerSoundEvent("castle_crashers_dark_skies");
    public static RegistryObject<SoundEvent> CASTLE_CRASHERS_PIERCING_LAZER = registerSoundEvent("castle_crashers_piercing_lazer");
    public static RegistryObject<SoundEvent> CASTLE_CRASHERS_JUMPER = registerSoundEvent("castle_crashers_jumper");
    public static RegistryObject<SoundEvent> CASTLE_CRASHERS_SPANISH_WALTZ = registerSoundEvent("castle_crashers_spanish_waltz");
    public static RegistryObject<SoundEvent> ZELDA_DRAGON_ROOST = registerSoundEvent("zelda_dragon_roost");
    public static RegistryObject<SoundEvent> ZELDA_GERUDO_VALLEY = registerSoundEvent("zelda_gerudo_valley");
    public static RegistryObject<SoundEvent> SPONGEBOB = registerSoundEvent("spongebob");
    public static RegistryObject<SoundEvent> JJK = registerSoundEvent("jjk");
    public static RegistryObject<SoundEvent> UNO = registerSoundEvent("uno");
    public static RegistryObject<SoundEvent> CALL_OF_DUTY_SNAKESKIN_BOOTS = registerSoundEvent("call_of_duty_snakeskin_boots");
    public static RegistryObject<SoundEvent> HXH_LEORIO_THEME = registerSoundEvent("hxh_leorio_theme");
    public static RegistryObject<SoundEvent> DB_CHA_LA = registerSoundEvent("db_cha_la");
    public static RegistryObject<SoundEvent> DONKEY_KONG = registerSoundEvent("donkey_kong");
    public static RegistryObject<SoundEvent> STAR_WARS_DUEL_FATES = registerSoundEvent("star_wars_duel_fates");




    private static RegistryObject<SoundEvent> registerSoundEvent(String name) {
        ResourceLocation id = new ResourceLocation(ZAMAquaticAdditions.MOD_ID, name);
        return SOUND_EVENTS.register(name, () -> SoundEvent.createVariableRangeEvent(id));
    }

    public static void register(IEventBus eventBus) {
        SOUND_EVENTS.register(eventBus);
    }
}
