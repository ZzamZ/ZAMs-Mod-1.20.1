package net.zam.zamaquaticadditions.registry;

import net.minecraft.world.item.HorseArmorItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Rarity;
import net.minecraft.world.item.RecordItem;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import net.zam.zamaquaticadditions.ZAMAquaticAdditions;
import net.zam.zamaquaticadditions.item.MarlinLanceItem;

import javax.annotation.Nonnull;
import java.util.ArrayList;
import java.util.Collection;
import java.util.function.Supplier;

public class ZAMItems {
    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, ZAMAquaticAdditions.MOD_ID);
    public static final Collection<RegistryObject<Item>> ITEMS_FOR_TAB_LIST = new ArrayList<>();

    //Aquatic Utilities
    public static final RegistryObject<Item> MARLIN_LANCE = ITEMS.register("marlin_lance", MarlinLanceItem::new);



    //Fishing Medals
    public static final RegistryObject<Item> WOOD_MEDAL = ITEMS.register("wood_medal", () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> BRONZE_MEDAL = ITEMS.register("bronze_medal", () -> new Item(new Item.Properties().rarity(Rarity.COMMON)));
    public static final RegistryObject<Item> SILVER_MEDAL = ITEMS.register("silver_medal", () -> new Item(new Item.Properties().rarity(Rarity.RARE)));
    public static final RegistryObject<Item> GOLD_MEDAL = ITEMS.register("gold_medal", () -> new Item(new Item.Properties().rarity(Rarity.UNCOMMON)));
    public static final RegistryObject<Item> LEGENDARY_MEDAL = ITEMS.register("legendary_medal", () -> new Item(new Item.Properties().rarity(Rarity.EPIC)));


    //Horse Armor
    public static final RegistryObject<Item> EMERALD_HORSE_ARMOR = ITEMS.register("emerald_horse_armor", () -> new HorseArmorItem(13, "emerald", new Item.Properties().stacksTo(1)));
    public static final RegistryObject<Item> NETHERITE_HORSE_ARMOR = ITEMS.register("netherite_horse_armor", () -> new HorseArmorItem(12, "netherite", new Item.Properties().fireResistant().stacksTo(1)));


    //Lost Relics
    public static final RegistryObject<Item> BASIC_BADGE = ITEMS.register("basic_badge", () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> TRIO_BADGE = ITEMS.register("trio_badge", () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> INSECT_BADGE = ITEMS.register("insect_badge", () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> BOLT_BADGE = ITEMS.register("bolt_badge", () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> QUAKE_BADGE = ITEMS.register("quake_badge", () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> JET_BADGE = ITEMS.register("jet_badge", () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> FREEZE_BADGE = ITEMS.register("freeze_badge", () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> LEGEND_BADGE = ITEMS.register("legend_badge", () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> TOXIC_BADGE = ITEMS.register("toxic_badge", () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> WAVE_BADGE = ITEMS.register("wave_badge", () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> GEAR_BADGE = ITEMS.register("gear_badge", () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> NATURE_BADGE = ITEMS.register("nature_badge", () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> KNUCKLE_BADGE = ITEMS.register("knuckle_badge", () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> HEAT_BADGE = ITEMS.register("heat_badge", () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> BOULDER_BADGE = ITEMS.register("boulder_badge", () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> MELODY_BADGE = ITEMS.register("melody_badge", () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> SHADOW_BADGE = ITEMS.register("shadow_badge", () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> COSMIC_BADGE = ITEMS.register("cosmic_badge", () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> VOID_BADGE = ITEMS.register("void_badge", () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> PIXIE_BADGE = ITEMS.register("pixie_badge", () -> new Item(new Item.Properties()));



    //Music: 35 Records || 15 in the loot pool ||  20 through badges
    public static final RegistryObject<Item> C418_DOG = ITEMS.register("c418_dog", () -> new RecordItem(4, ZAMSounds.C418_DOG, new Item.Properties().rarity(Rarity.RARE).stacksTo(1), 37160));
    public static final RegistryObject<Item> LUPUS_NOCTE_HOWLING = ITEMS.register("lupus_nocte_howling", () -> new RecordItem(4, ZAMSounds.LUPUS_NOCTE_HOWLING, new Item.Properties().rarity(Rarity.RARE).stacksTo(1), 3920));
    public static final RegistryObject<Item> CAT_INDEV = ITEMS.register("cat_indev", () -> new RecordItem(4, ZAMSounds.CAT_INDEV, new Item.Properties().rarity(Rarity.RARE).stacksTo(1), 3920));
    public static final RegistryObject<Item> HALO_MASHUP = ITEMS.register("halo_mashup", () -> new RecordItem(4, ZAMSounds.HALO_MASHUP, new Item.Properties().rarity(Rarity.RARE).stacksTo(1), 5000));
    public static final RegistryObject<Item> STAR_WARS = ITEMS.register("star_wars", () -> new RecordItem(4, ZAMSounds.STAR_WARS, new Item.Properties().rarity(Rarity.RARE).stacksTo(1), 5080));
    public static final RegistryObject<Item> CALL_OF_DUTY = ITEMS.register("call_of_duty", () -> new RecordItem(4, ZAMSounds.CALL_OF_DUTY, new Item.Properties().rarity(Rarity.RARE).stacksTo(1), 8440));
    public static final RegistryObject<Item> POKEMON_EAST_PROVINCE = ITEMS.register("pokemon_east_province", () -> new RecordItem(4, ZAMSounds.POKEMON_EAST_PROVINCE, new Item.Properties().rarity(Rarity.RARE).stacksTo(1), 3640));
    public static final RegistryObject<Item> POKEMON_NIMBASA_CITY = ITEMS.register("pokemon_nimbasa_city", () -> new RecordItem(4, ZAMSounds.POKEMON_NIMBASA_CITY, new Item.Properties().rarity(Rarity.RARE).stacksTo(1), 4280));
    public static final RegistryObject<Item> POKEMON_DRIFTVEIL_CITY = ITEMS.register("pokemon_driftveil_city", () -> new RecordItem(4, ZAMSounds.POKEMON_DRIFTVEIL_CITY, new Item.Properties().rarity(Rarity.RARE).stacksTo(1), 2800));
    public static final RegistryObject<Item> POKEMON_VIRBANK_CITY = ITEMS.register("pokemon_virbank_city", () -> new RecordItem(4, ZAMSounds.POKEMON_VIRBANK_CITY, new Item.Properties().rarity(Rarity.RARE).stacksTo(1), 3800));
    public static final RegistryObject<Item> POKEMON_HUMILAU_CITY = ITEMS.register("pokemon_humilau_city", () -> new RecordItem(4, ZAMSounds.POKEMON_HUMILAU_CITY, new Item.Properties().rarity(Rarity.RARE).stacksTo(1), 3520));
    public static final RegistryObject<Item> POKEMON_GEAR_STATION = ITEMS.register("pokemon_gear_station", () -> new RecordItem(4, ZAMSounds.POKEMON_GEAR_STATION, new Item.Properties().rarity(Rarity.RARE).stacksTo(1), 3520));
    public static final RegistryObject<Item> POKEMON_HUMILAU_CITY_GYM = ITEMS.register("pokemon_humilau_city", () -> new RecordItem(4, ZAMSounds.POKEMON_HUMILAU_CITY_GYM, new Item.Properties().rarity(Rarity.RARE).stacksTo(1), 3520));
    public static final RegistryObject<Item> HOLLOW_KNIGHT_CITY_OF_TEARS = ITEMS.register("hollow_knight_city_of_tears", () -> new RecordItem(4, ZAMSounds.HOLLOW_KNIGHT_CITY_OF_TEARS, new Item.Properties().rarity(Rarity.RARE).stacksTo(1), 4600));
    public static final RegistryObject<Item> HOLLOW_KNIGHT_GREENPATH = ITEMS.register("hollow_knight_greenpath", () -> new RecordItem(4, ZAMSounds.HOLLOW_KNIGHT_GREENPATH, new Item.Properties().rarity(Rarity.RARE).stacksTo(1), 3400));
    public static final RegistryObject<Item> HOLLOW_KNIGHT_DIRTMOUTH = ITEMS.register("hollow_knight_dirtmouth", () -> new RecordItem(4, ZAMSounds.HOLLOW_KNIGHT_DIRTMOUTH, new Item.Properties().rarity(Rarity.RARE).stacksTo(1), 4600));
    public static final RegistryObject<Item> HOLLOW_KNIGHT_QUEENS_GARDEN = ITEMS.register("hollow_knight_queens_garden", () -> new RecordItem(4, ZAMSounds.HOLLOW_KNIGHT_QUEENS_GARDEN, new Item.Properties().rarity(Rarity.RARE).stacksTo(1), 3400));
    public static final RegistryObject<Item> HOLLOW_KNIGHT_NIGHMARE_GRIMM = ITEMS.register("hollow_knight_nightmare_grimm", () -> new RecordItem(4, ZAMSounds.HOLLOW_KNIGHT_NIGHTMARE_GRIMM, new Item.Properties().rarity(Rarity.RARE).stacksTo(1), 4600));
    public static final RegistryObject<Item> HOLLOW_KNIGHT_PURE_VESSEL = ITEMS.register("hollow_knight_pure_vessel", () -> new RecordItem(4, ZAMSounds.HOLLOW_KNIGHT_PURE_VESSEL, new Item.Properties().rarity(Rarity.RARE).stacksTo(1), 3400));
    public static final RegistryObject<Item> MARIO_INSIDE_THE_CASTLE_WALLS = ITEMS.register("mario_inside_the_castle_walls", () -> new RecordItem(4, ZAMSounds.MARIO_INSIDE_THE_CASTLE_WALLS, new Item.Properties().rarity(Rarity.RARE).stacksTo(1), 5360));
    public static final RegistryObject<Item> MARIO_PIRANHA_PLANTS_LULLABY = ITEMS.register("mario_piranha_plants_lullaby", () -> new RecordItem(4, ZAMSounds.MARIO_PIRANHA_PLANT_LULLABY, new Item.Properties().rarity(Rarity.RARE).stacksTo(1), 5360));
    public static final RegistryObject<Item> MARIO_STEAM_GARDENS = ITEMS.register("mario_steam_gardens", () -> new RecordItem(4, ZAMSounds.MARIO_STEAM_GARDENS, new Item.Properties().rarity(Rarity.RARE).stacksTo(1), 5360));
    public static final RegistryObject<Item> MARIO_DIRE_DIRE_DOCKS = ITEMS.register("mario_dire_dire_docks", () -> new RecordItem(4, ZAMSounds.MARIO_DIRE_DIRE_DOCKS, new Item.Properties().rarity(Rarity.RARE).stacksTo(1), 5360));
    public static final RegistryObject<Item> MARIO_BUBBLAINE = ITEMS.register("mario_bubblaine", () -> new RecordItem(4, ZAMSounds.MARIO_BUBBLAINE, new Item.Properties().rarity(Rarity.RARE).stacksTo(1), 5360));
    public static final RegistryObject<Item> CASTLE_CRASHERS_DARK_SKIES = ITEMS.register("castle_crashers_dark_skies", () -> new RecordItem(4, ZAMSounds.CASTLE_CRASHERS_DARK_SKIES, new Item.Properties().rarity(Rarity.RARE).stacksTo(1), 5360));
    public static final RegistryObject<Item> CASTLE_CRASHERS_RAGE_OF_THE_CHAMPION = ITEMS.register("castle_crashers_rage_of_the_champion", () -> new RecordItem(4, ZAMSounds.CASTLE_CRASHERS_RAGE_OF_THE_CHAMPION, new Item.Properties().rarity(Rarity.RARE).stacksTo(1), 5360));
    public static final RegistryObject<Item> CASTLE_CRASHERS_WINTER_BLISS = ITEMS.register("castle_crashers_winter_bliss", () -> new RecordItem(4, ZAMSounds.CASTLE_CRASHERS_WINTER_BLISS, new Item.Properties().rarity(Rarity.RARE).stacksTo(1), 5360));
    public static final RegistryObject<Item> CASTLE_CRASHERS_SPACE_PIRATES = ITEMS.register("castle_crashers_space_pirates", () -> new RecordItem(4, ZAMSounds.CASTLE_CRASHERS_SPACE_PIRATES, new Item.Properties().rarity(Rarity.RARE).stacksTo(1), 5360));
    public static final RegistryObject<Item> ZELDA_DRAGON_ROOST = ITEMS.register("zelda_dragon_roost", () -> new RecordItem(4, ZAMSounds.ZELDA_DRAGON_ROOST, new Item.Properties().rarity(Rarity.RARE).stacksTo(1), 5360));
    public static final RegistryObject<Item> ZELDA_GERUDO_VALLEY = ITEMS.register("zelda_gerudo_valley", () -> new RecordItem(4, ZAMSounds.ZELDA_GERUDO_VALLEY, new Item.Properties().rarity(Rarity.RARE).stacksTo(1), 5360));
    public static final RegistryObject<Item> ZELDA_RITO_VILLAGE = ITEMS.register("zelda_rito_village", () -> new RecordItem(4, ZAMSounds.ZELDA_RITO_VILLAGE, new Item.Properties().rarity(Rarity.RARE).stacksTo(1), 5360));
    public static final RegistryObject<Item> ZELDA_ZORA_DOMAIN = ITEMS.register("zelda_zora_domain", () -> new RecordItem(4, ZAMSounds.ZELDA_ZORA_DOMAIN, new Item.Properties().rarity(Rarity.RARE).stacksTo(1), 5360));
    public static final RegistryObject<Item> SPONGEBOB = ITEMS.register("spongebob", () -> new RecordItem(4, ZAMSounds.SPONGEBOB, new Item.Properties().rarity(Rarity.RARE).stacksTo(1), 3400));
    public static final RegistryObject<Item> UNO = ITEMS.register("uno", () -> new RecordItem(4, ZAMSounds.UNO, new Item.Properties().rarity(Rarity.RARE).stacksTo(1), 4720));
    public static final RegistryObject<Item> JJK = ITEMS.register("jjk", () -> new RecordItem(4, ZAMSounds.JJK, new Item.Properties().rarity(Rarity.RARE).stacksTo(1), 4720));

    //30 Records
    //10 in the loot pool
    //20 through badges

    public static RegistryObject<Item> registerWithTab(@Nonnull Supplier<Item> initializer, @Nonnull String name) {
        RegistryObject<Item> registryObject = register(initializer, name);
        ITEMS_FOR_TAB_LIST.add(registryObject);
        return registryObject;
    }

    public static RegistryObject<Item> register(@Nonnull Supplier<Item> initializer, @Nonnull String name) {
        return ITEMS.register(name, initializer);
    }
    public static void register(IEventBus eventBus) {
        ITEMS.register(eventBus);
    }
}
