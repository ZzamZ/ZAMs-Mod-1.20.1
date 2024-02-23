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








    //Music Tracks
    public static RegistryObject<SoundEvent> C418_DOG = registerSoundEvent("c418_dog");
    public static RegistryObject<SoundEvent> LUPUS_NOCTE_HOWLING = registerSoundEvent("lupus_nocte_howling");
    public static RegistryObject<SoundEvent> CAT_INDEV = registerSoundEvent("cat_indev");
    public static RegistryObject<SoundEvent> HALO_MASHUP = registerSoundEvent("halo_mashup");
    public static RegistryObject<SoundEvent> STAR_WARS = registerSoundEvent("star_wars");
    public static RegistryObject<SoundEvent> CALL_OF_DUTY = registerSoundEvent("call_of_duty");
    public static RegistryObject<SoundEvent> POKEMON_EAST_PROVINCE = registerSoundEvent("pokemon_east_province");
    public static RegistryObject<SoundEvent> POKEMON_NIMBASA_CITY = registerSoundEvent("pokemon_nimbasa_city");
    public static RegistryObject<SoundEvent> POKEMON_DRIFTVEIL_CITY = registerSoundEvent("pokemon_driftveil_city");
    public static RegistryObject<SoundEvent> POKEMON_VIRBANK_CITY = registerSoundEvent("pokemon_virbank_city");
    public static RegistryObject<SoundEvent> POKEMON_HUMILAU_CITY = registerSoundEvent("pokemon_humilau_city");
    public static RegistryObject<SoundEvent> POKEMON_GEAR_STATION = registerSoundEvent("pokemon_gear_station");
    public static RegistryObject<SoundEvent> POKEMON_HUMILAU_CITY_GYM = registerSoundEvent("pokemon_humilau_city_gym");
    public static RegistryObject<SoundEvent> HOLLOW_KNIGHT_CITY_OF_TEARS = registerSoundEvent("hollow_knight_city_of_tears");
    public static RegistryObject<SoundEvent> HOLLOW_KNIGHT_GREENPATH = registerSoundEvent("hollow_knight_greenpath");
    public static RegistryObject<SoundEvent> HOLLOW_KNIGHT_DIRTMOUTH = registerSoundEvent("hollow_knight_dirtmouth");
    public static RegistryObject<SoundEvent> HOLLOW_KNIGHT_QUEENS_GARDEN = registerSoundEvent("hollow_knight_queens_garden");
    public static RegistryObject<SoundEvent> HOLLOW_KNIGHT_NIGHTMARE_GRIMM = registerSoundEvent("hollow_knight_nightmare_grimm");
    public static RegistryObject<SoundEvent> HOLLOW_KNIGHT_PURE_VESSEL = registerSoundEvent("hollow_knight_pure_vessel");
    public static RegistryObject<SoundEvent> MARIO_INSIDE_THE_CASTLE_WALLS = registerSoundEvent("mario_inside_the_castle_walls");
    public static RegistryObject<SoundEvent> MARIO_PIRANHA_PLANT_LULLABY = registerSoundEvent("mario_piranha_plants_lullaby");
    public static RegistryObject<SoundEvent> MARIO_STEAM_GARDENS = registerSoundEvent("mario_steam_gardens");
    public static RegistryObject<SoundEvent> MARIO_DIRE_DIRE_DOCKS = registerSoundEvent("mario_dire_dire_docks");
    public static RegistryObject<SoundEvent> MARIO_BUBBLAINE = registerSoundEvent("mario_bubblaine");
    public static RegistryObject<SoundEvent> CASTLE_CRASHERS_RAGE_OF_THE_CHAMPION = registerSoundEvent("castle_crashers_rage_of_the_champion");
    public static RegistryObject<SoundEvent> CASTLE_CRASHERS_WINTER_BLISS = registerSoundEvent("castle_crashers_winter_bliss");
    public static RegistryObject<SoundEvent> CASTLE_CRASHERS_SPACE_PIRATES = registerSoundEvent("castle_crashers_space_pirates");
    public static RegistryObject<SoundEvent> CASTLE_CRASHERS_DARK_SKIES = registerSoundEvent("castle_crashers_dark_skies");
    public static RegistryObject<SoundEvent> ZELDA_DRAGON_ROOST = registerSoundEvent("zelda_dragon_roost");
    public static RegistryObject<SoundEvent> ZELDA_GERUDO_VALLEY = registerSoundEvent("zelda_gerudo_valley");
    public static RegistryObject<SoundEvent> ZELDA_RITO_VILLAGE = registerSoundEvent("zelda_rito_village");
    public static RegistryObject<SoundEvent> ZELDA_ZORA_DOMAIN = registerSoundEvent("zelda_zora_domain");
    public static RegistryObject<SoundEvent> SPONGEBOB = registerSoundEvent("spongebob");
    public static RegistryObject<SoundEvent> UNO = registerSoundEvent("uno");
    public static RegistryObject<SoundEvent> JJK = registerSoundEvent("jjk");



    private static RegistryObject<SoundEvent> registerSoundEvent(String name) {
        ResourceLocation id = new ResourceLocation(ZAMAquaticAdditions.MOD_ID, name);
        return SOUND_EVENTS.register(name, () -> SoundEvent.createVariableRangeEvent(id));
    }

    public static void register(IEventBus eventBus) {
        SOUND_EVENTS.register(eventBus);
    }
}
