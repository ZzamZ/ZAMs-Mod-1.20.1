package net.zam.zammod.registry;

import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.*;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.material.Fluids;
import net.minecraftforge.common.ForgeSpawnEggItem;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import net.zam.zammod.ZAMMod;
import net.zam.zammod.item.*;
//import net.zam.zammod.item.records.cases.PokemonAlbumCase;
import net.zam.zammod.item.aquamarinetools.*;
import net.zam.zammod.item.records.sets.pokemon.PokemonBlackWhiteCollection;
import net.zam.zammod.misc.ZAMBeerProperties;

import javax.annotation.Nonnull;
import java.util.ArrayList;
import java.util.Collection;
import java.util.function.Supplier;

public class ZAMItems {
    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, ZAMMod.MOD_ID);
    public static final DeferredRegister<Item> VANILLA_QUALITY = DeferredRegister.create(ForgeRegistries.ITEMS, ZAMMod.MOD_ID);

    public static final Collection<RegistryObject<Item>> ITEMS_FOR_TAB_LIST = new ArrayList<>();

    //Aquatic Utilities
    public static final RegistryObject<Item> MARLIN_LANCE = ITEMS.register("marlin_lance", MarlinLanceItem::new);
    public static final RegistryObject<Item> MARINE_BUBBLE = ITEMS.register("marine_bubble", () -> new MarineBubbleItem(new Item.Properties()));
    public static final RegistryObject<Item> EMERALD_SHARD = ITEMS.register("emerald_shard", () -> new Item(new Item.Properties().food(new FoodProperties.Builder().nutrition(3).saturationMod(0.3F).effect(() -> new MobEffectInstance(ZAMEffects.FADED_EFFECT.get(), 2400), 1.0F).alwaysEat().build())));
    public static final RegistryObject<Item> MUSIC_DISC_LOOT_BOX = ITEMS.register("music_disc_loot_box", () -> new MusicDiscLootBoxItem(new Item.Properties()));
    public static final RegistryObject<Item> AQUAMARINE_FISHING_ROD = ITEMS.register("aquamarine_fishing_rod", () -> new AquamarineFishingRodItem(new Item.Properties().durability(334).rarity(Rarity.RARE)));
    public static final RegistryObject<Item> AQUAMARINE_HOE = ITEMS.register("aquamarine_hoe", () -> new AquamarineHoe(Tiers.DIAMOND, -3, 0.0F));

    //Buckets
    public static final RegistryObject<Item> AQUAMARINE_BUCKET = ITEMS.register("aquamarine_bucket", () -> new AquamarineBucketItem(() -> Fluids.EMPTY, new Item.Properties().stacksTo(16)));
    public static final RegistryObject<Item> AQUAMARINE_WATER_BUCKET = ITEMS.register("aquamarine_water_bucket", () -> new AquamarineBucketItem(() -> Fluids.WATER, new Item.Properties().craftRemainder(AQUAMARINE_BUCKET.get()).stacksTo(1)));
    public static final RegistryObject<Item> AQUAMARINE_LAVA_BUCKET = ITEMS.register("aquamarine_lava_bucket", () -> new AquamarineBucketItem(() -> Fluids.LAVA, new Item.Properties().craftRemainder(AQUAMARINE_BUCKET.get()).stacksTo(1)));
    public static final RegistryObject<Item> AQUAMARINE_POWDER_SNOW_BUCKET = ITEMS.register("aquamarine_powder_snow_bucket", () -> new AquamarineSolidBucketItem(Blocks.POWDER_SNOW, SoundEvents.POWDER_SNOW_PLACE, new Item.Properties().craftRemainder(AQUAMARINE_BUCKET.get()).stacksTo(1)));
    public static final RegistryObject<Item> AQUAMARINE_MILK_BUCKET = ITEMS.register("aquamarine_milk_bucket", () -> new AquamarineMilkBucketItem(new Item.Properties().craftRemainder(AQUAMARINE_BUCKET.get()).stacksTo(1)));


    //Food
    public static final RegistryObject<Item> CORAL_FRUIT = ITEMS.register("coral_fruit", () -> new CoralFruitItem(new Item.Properties().food(new FoodProperties.Builder().nutrition(3).saturationMod(0.0F).alwaysEat().build()), true));
  //  public static final RegistryObject<Item> KRABBY_PATTY = ITEMS.register("krabby_patty", () -> new KrabbyPattyItem(new FoodProperties.Builder().nutrition(8).saturationMod(0.8F).build()));

    public static final RegistryObject<Item> STARDROP = ITEMS.register("stardrop", () -> new Item(new Item.Properties().food(new FoodProperties.Builder().nutrition(6).saturationMod(1.2F).effect(() -> new MobEffectInstance(ZAMEffects.STARDROP.get(), 3600), 1.0F).alwaysEat().build()).rarity(Rarity.EPIC)));
    public static final RegistryObject<Item> DOUBLOON = ITEMS.register("doubloon", () -> new Item(new Item.Properties().rarity(Rarity.UNCOMMON)));
    public static final RegistryObject<Item> LOST_KEY = ITEMS.register("lost_key", () -> new Item(new Item.Properties().rarity(Rarity.RARE)));




    //Stardew Valley Template & Tools
    public static final RegistryObject<Item> STARDEW_SMITHING_TEMPLATE = ITEMS.register("stardew_smithing_template", ToolsSmithingTemplateItem::new);
    public static final RegistryObject<Item> STARDEW_SWORD = ITEMS.register("stardew_sword", () -> new SwordItem(Tiers.NETHERITE, 3, -2.4F, (new Item.Properties()).fireResistant()));
    public static final RegistryObject<Item> STARDEW_SHOVEL = ITEMS.register("stardew_shovel", () -> new ShovelItem(Tiers.NETHERITE, 1.5F, -3.0F, (new Item.Properties()).fireResistant()));
    public static final RegistryObject<Item> STARDEW_PICKAXE = ITEMS.register("stardew_pickaxe", () -> new PickaxeItem(Tiers.NETHERITE, 1, -2.8F, (new Item.Properties()).fireResistant()));
    public static final RegistryObject<Item> STARDEW_AXE = ITEMS.register("stardew_axe", () -> new AxeItem(Tiers.NETHERITE, 5.0F, -3.0F, (new Item.Properties()).fireResistant()));
    public static final RegistryObject<Item> STARDEW_HOE = ITEMS.register("stardew_hoe", () -> new HoeItem(Tiers.NETHERITE, -4, 0.0F, (new Item.Properties()).fireResistant()));

    //Fishing Medals
    public static final RegistryObject<Item> WOOD_MEDAL = ITEMS.register("wood_medal", () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> BRONZE_MEDAL = ITEMS.register("bronze_medal", () -> new Item(new Item.Properties().rarity(Rarity.COMMON)));
    public static final RegistryObject<Item> SILVER_MEDAL = ITEMS.register("silver_medal", () -> new Item(new Item.Properties().rarity(Rarity.RARE)));
    public static final RegistryObject<Item> GOLD_MEDAL = ITEMS.register("gold_medal", () -> new Item(new Item.Properties().rarity(Rarity.UNCOMMON)));
    public static final RegistryObject<Item> LEGENDARY_MEDAL = ITEMS.register("legendary_medal", () -> new Item(new Item.Properties().rarity(Rarity.EPIC)));


    //Gems
    public static final RegistryObject<Item> OPAL = ITEMS.register("opal", () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> AQUAMARINE = ITEMS.register("aquamarine", () -> new Item(new Item.Properties()));





    //Armor
    public static final RegistryObject<Item> GUARDIAN_HELMET = ITEMS.register("guardian_helmet",
            () -> new ZAMArmorItem(ZAMArmorMaterials.GUARDIAN, ArmorItem.Type.HELMET, new Item.Properties()));
    public static final RegistryObject<Item> GUARDIAN_CHESTPLATE = ITEMS.register("guardian_chestplate",
            () -> new ZAMArmorItem(ZAMArmorMaterials.GUARDIAN, ArmorItem.Type.CHESTPLATE, new Item.Properties()));
    public static final RegistryObject<Item> GUARDIAN_LEGGING = ITEMS.register("guardian_leggings",
            () -> new ZAMArmorItem(ZAMArmorMaterials.GUARDIAN, ArmorItem.Type.LEGGINGS, new Item.Properties()));
    public static final RegistryObject<Item> GUARDIAN_BOOTS = ITEMS.register("guardian_boots",
            () -> new ZAMArmorItem(ZAMArmorMaterials.GUARDIAN, ArmorItem.Type.BOOTS, new Item.Properties()));


    //Horse Armor
    public static final RegistryObject<Item> EMERALD_HORSE_ARMOR = ITEMS.register("emerald_horse_armor", () -> new HorseArmorItem(13, "emerald", new Item.Properties().stacksTo(1)));


    public static final RegistryObject<Item> CLAM = ITEMS.register("clam", () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> OTTER_SPAWN_EGG = ITEMS.register("otter_spawn_egg", () -> new ForgeSpawnEggItem(ZAMEntities.OTTER, 0x352C34, 0xB49494, new Item.Properties()));


    //Badges (WIP)
//    public static final RegistryObject<Item> BASIC_BADGE = ITEMS.register("basic_badge", () -> new Item(new Item.Properties()));
 //   public static final RegistryObject<Item> TRIO_BADGE = ITEMS.register("trio_badge", () -> new Item(new Item.Properties()));
 //   public static final RegistryObject<Item> INSECT_BADGE = ITEMS.register("insect_badge", () -> new Item(new Item.Properties()));
   // public static final RegistryObject<Item> BOLT_BADGE = ITEMS.register("bolt_badge", () -> new Item(new Item.Properties()));
  //  public static final RegistryObject<Item> QUAKE_BADGE = ITEMS.register("quake_badge", () -> new Item(new Item.Properties()));
 //   public static final RegistryObject<Item> JET_BADGE = ITEMS.register("jet_badge", () -> new Item(new Item.Properties()));
 //   public static final RegistryObject<Item> FREEZE_BADGE = ITEMS.register("freeze_badge", () -> new Item(new Item.Properties()));
 //   public static final RegistryObject<Item> LEGEND_BADGE = ITEMS.register("legend_badge", () -> new Item(new Item.Properties()));
 //   public static final RegistryObject<Item> TOXIC_BADGE = ITEMS.register("toxic_badge", () -> new Item(new Item.Properties()));
 //   public static final RegistryObject<Item> WAVE_BADGE = ITEMS.register("wave_badge", () -> new Item(new Item.Properties()));
  //  public static final RegistryObject<Item> NATURE_BADGE = ITEMS.register("nature_badge", () -> new Item(new Item.Properties()));
  //  public static final RegistryObject<Item> HEAT_BADGE = ITEMS.register("heat_badge", () -> new Item(new Item.Properties()));



    //Beer
    public static final RegistryObject<Item> HOP = ITEMS.register("hop", () -> new Item(new Item.Properties().food(ZAMBeerProperties.HOP)));
    public static final RegistryObject<Item> HOP_SEEDS = ITEMS.register("hop_seeds", () -> new ItemNameBlockItem(ZAMBlocks.HOP.get(), new Item.Properties()));
    public static final RegistryObject<Item> DRY_SEEDS = ITEMS.register("dry_seeds", () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> MUG_EMPTY = ITEMS.register("mug_empty", () -> new MugItem(ZAMBlocks.MUG_EMPTY.get(), new Item.Properties()));
    public static final RegistryObject<Item> MUG_OF_CHORUS_ALE = ITEMS.register("mug_of_chorus_ale", () -> new MugItem(ZAMBlocks.MUG_OF_CHORUS_ALE.get(), new Item.Properties().stacksTo(16).food(ZAMBeerProperties.CHORUS_ALE)));
    public static final RegistryObject<Item> MUG_OF_DIGGER_BITTER = ITEMS.register("mug_of_digger_bitter", () -> new MugItem(ZAMBlocks.MUG_OF_DIGGER_BITTER.get(), new Item.Properties().stacksTo(16).food(ZAMBeerProperties.DIGGER_BITTER)));
    public static final RegistryObject<Item> MUG_OF_DROWNED_ALE = ITEMS.register("mug_of_drowned_ale", () -> new MugItem(ZAMBlocks.MUG_OF_DROWNED_ALE.get(), new Item.Properties().stacksTo(16).food(ZAMBeerProperties.DROWNED_ALE)));
    public static final RegistryObject<Item> MUG_OF_ICE_BEER = ITEMS.register("mug_of_ice_beer", () -> new MugItem(ZAMBlocks.MUG_OF_ICE_BEER.get(), new Item.Properties().stacksTo(16).food(ZAMBeerProperties.ICE_BEER)));
    public static final RegistryObject<Item> MUG_OF_KVASS = ITEMS.register("mug_of_kvass", () -> new MugItem(ZAMBlocks.MUG_OF_KVASS.get(), new Item.Properties().stacksTo(16).food(ZAMBeerProperties.KVASS)));
    public static final RegistryObject<Item> MUG_OF_LEPRECHAUN_CIDER = ITEMS.register("mug_of_leprechaun_cider", () -> new MugItem(ZAMBlocks.MUG_OF_LEPRECHAUN_CIDER.get(), new Item.Properties().stacksTo(16).food(ZAMBeerProperties.LEPRECHAUN_CIDER)));
    public static final RegistryObject<Item> MUG_OF_MAGNET_PILSNER = ITEMS.register("mug_of_magnet_pilsner", () -> new MugItem(ZAMBlocks.MUG_OF_MAGNET_PILSNER.get(), new Item.Properties().stacksTo(16).food(ZAMBeerProperties.MAGNET_PILSNER)));
    public static final RegistryObject<Item> MUG_OF_NETHER_PORTER = ITEMS.register("mug_of_nether_porter", () -> new MugItem(ZAMBlocks.MUG_OF_NETHER_PORTER.get(), new Item.Properties().stacksTo(16).food(ZAMBeerProperties.NETHER_PORTER)));
    public static final RegistryObject<Item> MUG_OF_NIGHT_RAUCH = ITEMS.register("mug_of_night_rauch", () -> new MugItem(ZAMBlocks.MUG_OF_NIGHT_RAUCH.get(), new Item.Properties().stacksTo(16).food(ZAMBeerProperties.NIGHT_RAUCH)));
    public static final RegistryObject<Item> MUG_OF_SUN_PALE_ALE = ITEMS.register("mug_of_sun_pale_ale", () -> new MugItem(ZAMBlocks.MUG_OF_SUN_PALE_ALE.get(), new Item.Properties().stacksTo(16).food(ZAMBeerProperties.SUN_PALE_ALE)));
    public static final RegistryObject<Item> MUG_OF_WITHER_STOUT = ITEMS.register("mug_of_wither_stout", () -> new MugItem(ZAMBlocks.MUG_OF_WITHER_STOUT.get(), new Item.Properties().stacksTo(16).food(ZAMBeerProperties.WITHER_STOUT)));
    public static final RegistryObject<Item> MUG_OF_NIMBUS_NECTAR = ITEMS.register("mug_of_nimbus_nectar", () -> new MugItem(ZAMBlocks.MUG_OF_NIMBUS_NECTAR.get(), new Item.Properties().stacksTo(16).food(ZAMBeerProperties.NIMBUS_NECTAR)));
    public static final RegistryObject<Item> MUG_OF_STARDROP_SPARKLE = ITEMS.register("mug_of_stardrop_sparkle", () -> new MugItem(ZAMBlocks.MUG_OF_STARDROP_SPARKLE.get(), new Item.Properties().stacksTo(16).food(ZAMBeerProperties.STARDROP_SPARKLE)));

    //Pokemon Black & White Collection 12
     // public static final RegistryObject<Item> POKEMON_ALBUM_CASE = ITEMS.register("pokemon_album_case", () -> new PokemonAlbumCase(new Item.Properties().stacksTo(1).rarity(Rarity.UNCOMMON)));
      public static final RegistryObject<Item> POKEMON_ACCUMULA_TOWN = ITEMS.register("pokemon_accumula_town", () -> new PokemonBlackWhiteCollection(4, ZAMSounds.POKEMON_STRIATON_CITY, new Item.Properties().rarity(Rarity.RARE).stacksTo(1), 10160));
      public static final RegistryObject<Item> POKEMON_STRIATON_CITY = ITEMS.register("pokemon_striaton_city", () -> new PokemonBlackWhiteCollection(4, ZAMSounds.POKEMON_STRIATON_CITY, new Item.Properties().rarity(Rarity.RARE).stacksTo(1), 10160));
      public static final RegistryObject<Item> POKEMON_NACRENE_CITY = ITEMS.register("pokemon_nacrene_city", () -> new PokemonBlackWhiteCollection(4, ZAMSounds.POKEMON_STRIATON_CITY, new Item.Properties().rarity(Rarity.RARE).stacksTo(1), 10160));
      public static final RegistryObject<Item> POKEMON_CASTELIA_CITY = ITEMS.register("pokemon_castelia_city", () -> new PokemonBlackWhiteCollection(4, ZAMSounds.POKEMON_CASTELIA_CITY, new Item.Properties().rarity(Rarity.RARE).stacksTo(1), 3640));
      public static final RegistryObject<Item> POKEMON_NIMBASA_CITY = ITEMS.register("pokemon_nimbasa_city", () -> new PokemonBlackWhiteCollection(4, ZAMSounds.POKEMON_NIMBASA_CITY, new Item.Properties().rarity(Rarity.RARE).stacksTo(1), 4480));
      public static final RegistryObject<Item> POKEMON_DRIFTVEIL_CITY = ITEMS.register("pokemon_driftveil_city", () -> new PokemonBlackWhiteCollection(4, ZAMSounds.POKEMON_DRIFTVEIL_CITY, new Item.Properties().rarity(Rarity.RARE).stacksTo(1), 8400));
      public static final RegistryObject<Item> POKEMON_MISTRALTON_CITY = ITEMS.register("pokemon_mistralton_city", () -> new PokemonBlackWhiteCollection(4, ZAMSounds.POKEMON_MISTRALTON_CITY, new Item.Properties().rarity(Rarity.RARE).stacksTo(1), 4160));
      public static final RegistryObject<Item> POKEMON_ICIRRUS_CITY = ITEMS.register("pokemon_icirrus_city", () -> new PokemonBlackWhiteCollection(4, ZAMSounds.POKEMON_ICIRRUS_CITY, new Item.Properties().rarity(Rarity.RARE).stacksTo(1), 3520));
      public static final RegistryObject<Item> POKEMON_VIRBANK_CITY = ITEMS.register("pokemon_virbank_city", () -> new PokemonBlackWhiteCollection(4, ZAMSounds.POKEMON_VIRBANK_CITY, new Item.Properties().rarity(Rarity.RARE).stacksTo(1), 10120));
      public static final RegistryObject<Item> POKEMON_HUMILAU_CITY = ITEMS.register("pokemon_humilau_city", () -> new PokemonBlackWhiteCollection(4, ZAMSounds.POKEMON_HUMILAU_CITY, new Item.Properties().rarity(Rarity.RARE).stacksTo(1), 4840));
      public static final RegistryObject<Item> POKEMON_OPELUCID_CITY = ITEMS.register("pokemon_opelucid_city", () -> new PokemonBlackWhiteCollection(4, ZAMSounds.POKEMON_HUMILAU_CITY, new Item.Properties().rarity(Rarity.RARE).stacksTo(1), 4840));
      public static final RegistryObject<Item> POKEMON_UNDELLA_TOWN = ITEMS.register("pokemon_undella_town", () -> new PokemonBlackWhiteCollection(4, ZAMSounds.POKEMON_HUMILAU_CITY, new Item.Properties().rarity(Rarity.RARE).stacksTo(1), 4840));
      public static final RegistryObject<Item> POKEMON_UNDELLA_TOWN_SUMMER = ITEMS.register("pokemon_undella_town_summer", () -> new PokemonBlackWhiteCollection(4, ZAMSounds.POKEMON_HUMILAU_CITY, new Item.Properties().rarity(Rarity.RARE).stacksTo(1), 4840));


      public static final RegistryObject<Item> POKEMON_EAST_PROVINCE = ITEMS.register("pokemon_east_province", () -> new PokemonBlackWhiteCollection(4, ZAMSounds.POKEMON_EAST_PROVINCE, new Item.Properties().rarity(Rarity.RARE).stacksTo(1), 4040));
      public static final RegistryObject<Item> POKEMON_GEAR_STATION = ITEMS.register("pokemon_gear_station", () -> new PokemonBlackWhiteCollection(4, ZAMSounds.POKEMON_GEAR_STATION, new Item.Properties().rarity(Rarity.RARE).stacksTo(1), 4640));
      public static final RegistryObject<Item> POKEMON_NS_CASTLE = ITEMS.register("pokemon_ns_castle", () -> new PokemonBlackWhiteCollection(4, ZAMSounds.POKEMON_NS_CASTLE, new Item.Properties().rarity(Rarity.RARE).stacksTo(1), 4440));
      public static final RegistryObject<Item> POKEMON_VOLO = ITEMS.register("pokemon_volo", () -> new PokemonBlackWhiteCollection(4, ZAMSounds.POKEMON_VOLO, new Item.Properties().rarity(Rarity.RARE).stacksTo(1), 6000));
      public static final RegistryObject<Item> POKEMON_LOREKEEPER_ZINNIA = ITEMS.register("pokemon_lorekeeper_zinnia", () -> new PokemonBlackWhiteCollection(4, ZAMSounds.POKEMON_EAST_PROVINCE, new Item.Properties().rarity(Rarity.RARE).stacksTo(1), 4040));
      public static final RegistryObject<Item> POKEMON_RIVAL_MUSIC = ITEMS.register("pokemon_rival_music", () -> new PokemonBlackWhiteCollection(4, ZAMSounds.POKEMON_GEAR_STATION, new Item.Properties().rarity(Rarity.RARE).stacksTo(1), 4640));
      public static final RegistryObject<Item> POKEMON_LUMIOSE_CITY = ITEMS.register("pokemon_lumiose_city", () -> new PokemonBlackWhiteCollection(4, ZAMSounds.POKEMON_NS_CASTLE, new Item.Properties().rarity(Rarity.RARE).stacksTo(1), 4440));
      public static final RegistryObject<Item> POKEMON_HOENN_VICTORY_ROAD = ITEMS.register("pokemon_hoenn_victory_road", () -> new PokemonBlackWhiteCollection(4, ZAMSounds.POKEMON_VOLO, new Item.Properties().rarity(Rarity.RARE).stacksTo(1), 6000));
      public static final RegistryObject<Item> POKEMON_UNOVA_VICOTRY_ROAD = ITEMS.register("pokemon_unova_victory_road", () -> new PokemonBlackWhiteCollection(4, ZAMSounds.POKEMON_EAST_PROVINCE, new Item.Properties().rarity(Rarity.RARE).stacksTo(1), 4040));
      public static final RegistryObject<Item> POKEMON_POKESTAR_STUDIOS = ITEMS.register("pokemon_pokestar_studios", () -> new PokemonBlackWhiteCollection(4, ZAMSounds.POKEMON_GEAR_STATION, new Item.Properties().rarity(Rarity.RARE).stacksTo(1), 4640));
      public static final RegistryObject<Item> POKEMON_GATE = ITEMS.register("pokemon_gate", () -> new PokemonBlackWhiteCollection(4, ZAMSounds.POKEMON_NS_CASTLE, new Item.Properties().rarity(Rarity.RARE).stacksTo(1), 4440));
      public static final RegistryObject<Item> POKEMON_POKEMON_CENTER = ITEMS.register("pokemon_pokemon_center", () -> new PokemonBlackWhiteCollection(4, ZAMSounds.POKEMON_VOLO, new Item.Properties().rarity(Rarity.RARE).stacksTo(1), 6000));
     // public static final RegistryObject<Item> POKEMON_POKEMON_CENTER = ITEMS.register("pokemon_pokemon_center", () -> new PokemonBlackWhiteCollection(4, ZAMSounds.POKEMON_VOLO, new Item.Properties().rarity(Rarity.RARE).stacksTo(1), 6000));



 // public static final RegistryObject<Item> STARDEW_VALLEY_ALBUM_CASE = ITEMS.register("stardew_album_case", () -> new PokemonAlbumCase(new Item.Properties().stacksTo(1).rarity(Rarity.UNCOMMON)));
 // public static final RegistryObject<Item> STARDEW_VALLEY_OVERTURE = ITEMS.register("stardew_valley_overture", () -> new PokemonBlackWhiteCollection(4, ZAMSounds.POKEMON_STRIATON_CITY, new Item.Properties().rarity(Rarity.RARE).stacksTo(1), 10160));
 // public static final RegistryObject<Item> STARDEW_VALLEY_SPING = ITEMS.register("stardew_valley_spring", () -> new PokemonBlackWhiteCollection(4, ZAMSounds.POKEMON_CASTELIA_CITY, new Item.Properties().rarity(Rarity.RARE).stacksTo(1), 3640));
 //  public static final RegistryObject<Item> STARDEW_VALLEY_PELICAN_TOWN = ITEMS.register("stardew_valley_pelican_town", () -> new PokemonBlackWhiteCollection(4, ZAMSounds.POKEMON_NIMBASA_CITY, new Item.Properties().rarity(Rarity.RARE).stacksTo(1), 4480));
 //  public static final RegistryObject<Item> STARDEW_VALLEY_COUNTRY_SHOP = ITEMS.register("stardew_valley_country_shop", () -> new PokemonBlackWhiteCollection(4, ZAMSounds.POKEMON_DRIFTVEIL_CITY, new Item.Properties().rarity(Rarity.RARE).stacksTo(1), 8400));
 //  public static final RegistryObject<Item> STARDEW_VALLEY_FAIR = ITEMS.register("stardew_valley_fair", () -> new PokemonBlackWhiteCollection(4, ZAMSounds.POKEMON_DRIFTVEIL_CITY, new Item.Properties().rarity(Rarity.RARE).stacksTo(1), 8400));
 // public static final RegistryObject<Item> STARDEW_VALLEY_SUMMER = ITEMS.register("stardew_valley_summer", () -> new PokemonBlackWhiteCollection(4, ZAMSounds.POKEMON_MISTRALTON_CITY, new Item.Properties().rarity(Rarity.RARE).stacksTo(1), 4160));
 //  public static final RegistryObject<Item> STARDEW_VALLEY_STARDROP_SALOON = ITEMS.register("stardew_valley_stardrop_saloon", () -> new PokemonBlackWhiteCollection(4, ZAMSounds.POKEMON_ICIRRUS_CITY, new Item.Properties().rarity(Rarity.RARE).stacksTo(1), 3520));
 // public static final RegistryObject<Item> STARDEW_VALLEY_ADVENTURE_GUILD = ITEMS.register("stardew_valley_adventure_guild", () -> new PokemonBlackWhiteCollection(4, ZAMSounds.POKEMON_VIRBANK_CITY, new Item.Properties().rarity(Rarity.RARE).stacksTo(1), 10120));
 // public static final RegistryObject<Item> STARDEW_VALLEY_FALL = ITEMS.register("stardew_valley_fall", () -> new PokemonBlackWhiteCollection(4, ZAMSounds.POKEMON_HUMILAU_CITY, new Item.Properties().rarity(Rarity.RARE).stacksTo(1), 4840));
 //  public static final RegistryObject<Item> STARDEW_VALLEY_LIBRARY_MUSEUM = ITEMS.register("stardew_valley_library_museum", () -> new PokemonBlackWhiteCollection(4, ZAMSounds.POKEMON_EAST_PROVINCE, new Item.Properties().rarity(Rarity.RARE).stacksTo(1), 4040));
 // public static final RegistryObject<Item> STARDEW_VALLEY_SPIRITS_EVE_FESTIVAL = ITEMS.register("stardew_valley_spirits_eve_festival", () -> new PokemonBlackWhiteCollection(4, ZAMSounds.POKEMON_GEAR_STATION, new Item.Properties().rarity(Rarity.RARE).stacksTo(1), 4640));
 //  public static final RegistryObject<Item> STARDEW_VALLEY_WINTER = ITEMS.register("stardew_valley_winter", () -> new PokemonBlackWhiteCollection(4, ZAMSounds.POKEMON_NS_CASTLE, new Item.Properties().rarity(Rarity.RARE).stacksTo(1), 4440));
 //  public static final RegistryObject<Item> STARDEW_VALLEY_WINTER_FESTIVAL = ITEMS.register("stardew_valley_winter_festival", () -> new PokemonBlackWhiteCollection(4, ZAMSounds.POKEMON_VOLO, new Item.Properties().rarity(Rarity.RARE).stacksTo(1), 6000));

 // public static final RegistryObject<Item> STARDEW_VALLEY_ALBUM_CASE_TWO = ITEMS.register("stardew_album_case_two", () -> new PokemonAlbumCase(new Item.Properties().stacksTo(1).rarity(Rarity.UNCOMMON)));
 // public static final RegistryObject<Item> STARDEW_VALLEY_GOLDEN_STAR = ITEMS.register("stardew_valley_golden_star", () -> new PokemonBlackWhiteCollection(4, ZAMSounds.POKEMON_STRIATON_CITY, new Item.Properties().rarity(Rarity.RARE).stacksTo(1), 10160));
 // public static final RegistryObject<Item> STARDEW_VALLEY_DISTANT_BANJO = ITEMS.register("stardew_valley_distant_banjo", () -> new PokemonBlackWhiteCollection(4, ZAMSounds.POKEMON_CASTELIA_CITY, new Item.Properties().rarity(Rarity.RARE).stacksTo(1), 3640));
 //  public static final RegistryObject<Item> STARDEW_VALLEY_LUAU_FESTIVAL = ITEMS.register("stardew_valley_luau_festival", () -> new PokemonBlackWhiteCollection(4, ZAMSounds.POKEMON_NIMBASA_CITY, new Item.Properties().rarity(Rarity.RARE).stacksTo(1), 4480));
 //  public static final RegistryObject<Item> STARDEW_VALLEY_MOONLIGHT_JELLIES = ITEMS.register("stardew_valley_moonlight_jellies", () -> new PokemonBlackWhiteCollection(4, ZAMSounds.POKEMON_DRIFTVEIL_CITY, new Item.Properties().rarity(Rarity.RARE).stacksTo(1), 8400));
 // public static final RegistryObject<Item> STARDEW_VALLEY_SUMMIT_CELEBRATION = ITEMS.register("stardew_valley_summit_celebration", () -> new PokemonBlackWhiteCollection(4, ZAMSounds.POKEMON_MISTRALTON_CITY, new Item.Properties().rarity(Rarity.RARE).stacksTo(1), 4160));
 //  public static final RegistryObject<Item> STARDEW_VALLEY_ECHOS = ITEMS.register("stardew_valley_echos", () -> new PokemonBlackWhiteCollection(4, ZAMSounds.POKEMON_ICIRRUS_CITY, new Item.Properties().rarity(Rarity.RARE).stacksTo(1), 3520));
 // public static final RegistryObject<Item> STARDEW_VALLEY_PROFESSOR_SNAIL_RADIO = ITEMS.register("stardew_valley_professor_snail_radio", () -> new PokemonBlackWhiteCollection(4, ZAMSounds.POKEMON_VIRBANK_CITY, new Item.Properties().rarity(Rarity.RARE).stacksTo(1), 10120));
 // public static final RegistryObject<Item> STARDEW_VALLEY_CRYSTAL_BELLS = ITEMS.register("stardew_valley_crystal_bells", () -> new PokemonBlackWhiteCollection(4, ZAMSounds.POKEMON_HUMILAU_CITY, new Item.Properties().rarity(Rarity.RARE).stacksTo(1), 4840));
 //  public static final RegistryObject<Item> STARDEW_VALLEY_NIGHT_MARKET = ITEMS.register("stardew_valley_night_market", () -> new PokemonBlackWhiteCollection(4, ZAMSounds.POKEMON_EAST_PROVINCE, new Item.Properties().rarity(Rarity.RARE).stacksTo(1), 4040));
 // public static final RegistryObject<Item> STARDEW_VALLEY_SUBMARINE_THEME = ITEMS.register("stardew_valley_submarine_theme", () -> new PokemonBlackWhiteCollection(4, ZAMSounds.POKEMON_GEAR_STATION, new Item.Properties().rarity(Rarity.RARE).stacksTo(1), 4640));
 //  public static final RegistryObject<Item> STARDEW_VALLEY_MOVIE_THEATER = ITEMS.register("stardew_valley_movie_theater", () -> new PokemonBlackWhiteCollection(4, ZAMSounds.POKEMON_NS_CASTLE, new Item.Properties().rarity(Rarity.RARE).stacksTo(1), 4440));
 //  public static final RegistryObject<Item> STARDEW_VALLEY_GINGER_ISLAND = ITEMS.register("stardew_valley_ginger_island", () -> new PokemonBlackWhiteCollection(4, ZAMSounds.POKEMON_VOLO, new Item.Properties().rarity(Rarity.RARE).stacksTo(1), 6000));
//??

 //Pokemon Explorers Collection 12

    //Castle Crashers Story Collection 12
   // public static final RegistryObject<Item> CASTLE_CRASHERS_DARK_SKIES = ITEMS.register("castle_crashers_dark_skies", () -> new RecordItem(4, ZAMSounds.CASTLE_CRASHERS_DARK_SKIES, new Item.Properties().rarity(Rarity.RARE).stacksTo(1), 5360));
  //  public static final RegistryObject<Item> CASTLE_CRASHERS_RAGE_OF_THE_CHAMPION = ITEMS.register("castle_crashers_rage_of_the_champion", () -> new RecordItem(4, ZAMSounds.CASTLE_CRASHERS_RAGE_OF_THE_CHAMPION, new Item.Properties().rarity(Rarity.RARE).stacksTo(1), 5360));
  //  public static final RegistryObject<Item> CASTLE_CRASHERS_WINTER_BLISS = ITEMS.register("castle_crashers_winter_bliss", () -> new RecordItem(4, ZAMSounds.CASTLE_CRASHERS_WINTER_BLISS, new Item.Properties().rarity(Rarity.RARE).stacksTo(1), 5360));
  //  public static final RegistryObject<Item> CASTLE_CRASHERS_SPACE_PIRATES = ITEMS.register("castle_crashers_space_pirates", () -> new RecordItem(4, ZAMSounds.CASTLE_CRASHERS_SPACE_PIRATES, new Item.Properties().rarity(Rarity.RARE).stacksTo(1), 5360));
   // public static final RegistryObject<Item> CASTLE_CRASHERS_PIERCING_LAZER = ITEMS.register("castle_crashers_piercing_lazer", () -> new RecordItem(4, ZAMSounds.CASTLE_CRASHERS_PIERCING_LAZER, new Item.Properties().rarity(Rarity.RARE).stacksTo(1), 5360));
   // public static final RegistryObject<Item> CASTLE_CRASHERS_SPANISH_WALTZ = ITEMS.register("castle_crashers_spanish_waltz", () -> new RecordItem(4, ZAMSounds.CASTLE_CRASHERS_SPANISH_WALTZ, new Item.Properties().rarity(Rarity.RARE).stacksTo(1), 5360));
   // public static final RegistryObject<Item> CASTLE_CRASHERS_JUMPER = ITEMS.register("castle_crashers_jumper", () -> new RecordItem(4, ZAMSounds.CASTLE_CRASHERS_JUMPER, new Item.Properties().rarity(Rarity.RARE).stacksTo(1), 5360));
   // public static final RegistryObject<Item> CASTLE_CRASHERS_THE_SHOW = ITEMS.register("castle_crashers_the_show", () -> new RecordItem(4, ZAMSounds.CASTLE_CRASHERS_JUMPER, new Item.Properties().rarity(Rarity.RARE).stacksTo(1), 5360));
   // public static final RegistryObject<Item> CASTLE_CRASHERS_THE_FINAL_CONFRONTATION = ITEMS.register("castle_crashers_the_final_confrontation", () -> new RecordItem(4, ZAMSounds.CASTLE_CRASHERS_JUMPER, new Item.Properties().rarity(Rarity.RARE).stacksTo(1), 5360));
  //  public static final RegistryObject<Item> CASTLE_CRASHERS_MUDHOLES = ITEMS.register("castle_crashers_mudholes", () -> new RecordItem(4, ZAMSounds.CASTLE_CRASHERS_JUMPER, new Item.Properties().rarity(Rarity.RARE).stacksTo(1), 5360));
  //  public static final RegistryObject<Item> CASTLE_CRASHERS_FLUTEY = ITEMS.register("castle_crashers_flutey", () -> new RecordItem(4, ZAMSounds.CASTLE_CRASHERS_JUMPER, new Item.Properties().rarity(Rarity.RARE).stacksTo(1), 5360));
  //  public static final RegistryObject<Item> CASTLE_CRASHERS_FOUR_BRAVE_CHAMPIONS = ITEMS.register("castle_crashers_four_brave_champions", () -> new RecordItem(4, ZAMSounds.CASTLE_CRASHERS_JUMPER, new Item.Properties().rarity(Rarity.RARE).stacksTo(1), 5360));
   //// public static final RegistryObject<Item> CASTLE_CRASHERS_PLEASE_DONT = ITEMS.register("castle_crashers_please_dont", () -> new RecordItem(4, ZAMSounds.CASTLE_CRASHERS_JUMPER, new Item.Properties().rarity(Rarity.RARE).stacksTo(1), 5360));
  //  public static final RegistryObject<Item> CASTLE_CRASHERS_RACE_AROUND_THE_WORLD = ITEMS.register("castle_crashers_race_around_the_world", () -> new RecordItem(4, ZAMSounds.CASTLE_CRASHERS_JUMPER, new Item.Properties().rarity(Rarity.RARE).stacksTo(1), 5360));


    //Castle Crashers Bonus Collection


    //Sonic Collection 4
//  public static final RegistryObject<Item> SONIC_LIVE_AND_LEARN = ITEMS.register("sonic_live_and_learn", () -> new RecordItem(4, ZAMSounds.CASTLE_CRASHERS_SPACE_PIRATES, new Item.Properties().rarity(Rarity.RARE).stacksTo(1), 5360));
//  public static final RegistryObject<Item> SONIC_KNIGHT_OF_THE_WIND = ITEMS.register("sonic_knight_of_the_wind", () -> new RecordItem(4, ZAMSounds.CASTLE_CRASHERS_SPACE_PIRATES, new Item.Properties().rarity(Rarity.RARE).stacksTo(1), 5360));
//  public static final RegistryObject<Item> SONIC_HEROS = ITEMS.register("sonic_heros", () -> new RecordItem(4, ZAMSounds.CASTLE_CRASHERS_SPACE_PIRATES, new Item.Properties().rarity(Rarity.RARE).stacksTo(1), 5360));
//  public static final RegistryObject<Item> SONIC_ESCAPE_FROM_THE_CITY = ITEMS.register("sonic_escape_from_the_city", () -> new RecordItem(4, ZAMSounds.CASTLE_CRASHERS_SPACE_PIRATES, new Item.Properties().rarity(Rarity.RARE).stacksTo(1), 5360));

//  //JJK Collection 6
//  public static final RegistryObject<Item> JJK_LOST_IN_PARADISE = ITEMS.register("jjk_lost_in_paradise", () -> new RecordItem(4, ZAMSounds.CASTLE_CRASHERS_SPACE_PIRATES, new Item.Properties().rarity(Rarity.RARE).stacksTo(1), 5360));
//  public static final RegistryObject<Item> JJK_SPECIALZ = ITEMS.register("jjk_specialz", () -> new RecordItem(4, ZAMSounds.CASTLE_CRASHERS_SPACE_PIRATES, new Item.Properties().rarity(Rarity.RARE).stacksTo(1), 5360));
//  public static final RegistryObject<Item> JJK_KING_GNU = ITEMS.register("jjk_king_gnu", () -> new RecordItem(4, ZAMSounds.CASTLE_CRASHERS_SPACE_PIRATES, new Item.Properties().rarity(Rarity.RARE).stacksTo(1), 5360));
//  public static final RegistryObject<Item> JJK_KITANI = ITEMS.register("jjk_kitani", () -> new RecordItem(4, ZAMSounds.CASTLE_CRASHERS_SPACE_PIRATES, new Item.Properties().rarity(Rarity.RARE).stacksTo(1), 5360));
//  public static final RegistryObject<Item> JJK_KAI_KAI_KITAN = ITEMS.register("jjk_kai_kai_kitan", () -> new RecordItem(4, ZAMSounds.CASTLE_CRASHERS_SPACE_PIRATES, new Item.Properties().rarity(Rarity.RARE).stacksTo(1), 5360));
//  public static final RegistryObject<Item> JJK_VIVID_VICE = ITEMS.register("jjk_vivid_vice", () -> new RecordItem(4, ZAMSounds.CASTLE_CRASHERS_SPACE_PIRATES, new Item.Properties().rarity(Rarity.RARE).stacksTo(1), 5360));


//  //Avenged Sevenfold Collection 6
//  public static final RegistryObject<Item> A7X_NOBODY = ITEMS.register("a7x_nobody", () -> new RecordItem(4, ZAMSounds.CASTLE_CRASHERS_SPACE_PIRATES, new Item.Properties().rarity(Rarity.RARE).stacksTo(1), 5360));
//  public static final RegistryObject<Item> A7X_MAD_HATTER = ITEMS.register("a7x_mad_hatter", () -> new RecordItem(4, ZAMSounds.CASTLE_CRASHERS_SPACE_PIRATES, new Item.Properties().rarity(Rarity.RARE).stacksTo(1), 5360));
//  public static final RegistryObject<Item> A7X_SO_FAR_AWAY = ITEMS.register("a7x_so_far_away", () -> new RecordItem(4, ZAMSounds.CASTLE_CRASHERS_SPACE_PIRATES, new Item.Properties().rarity(Rarity.RARE).stacksTo(1), 5360));
//  public static final RegistryObject<Item> A7X_SHEPHERD_OF_FIRE = ITEMS.register("a7x_shepherd_of_fire", () -> new RecordItem(4, ZAMSounds.CASTLE_CRASHERS_SPACE_PIRATES, new Item.Properties().rarity(Rarity.RARE).stacksTo(1), 5360));
//  public static final RegistryObject<Item> A7X_HAIL_TO_THE_KING = ITEMS.register("a7x_hail_to_the_king", () -> new RecordItem(4, ZAMSounds.CASTLE_CRASHERS_SPACE_PIRATES, new Item.Properties().rarity(Rarity.RARE).stacksTo(1), 5360));
//  public static final RegistryObject<Item> A7X_THE_STAGE = ITEMS.register("a7x_the_stage", () -> new RecordItem(4, ZAMSounds.CASTLE_CRASHERS_SPACE_PIRATES, new Item.Properties().rarity(Rarity.RARE).stacksTo(1), 5360));
//  public static final RegistryObject<Item> A7X_BURIED_ALIVE = ITEMS.register("a7x_buried_alive", () -> new RecordItem(4, ZAMSounds.CASTLE_CRASHERS_SPACE_PIRATES, new Item.Properties().rarity(Rarity.RARE).stacksTo(1), 5360));

//  //Daft Punk Collection 4
//  public static final RegistryObject<Item> ROBOT_ROCK = ITEMS.register("robot_rock", () -> new RecordItem(4, ZAMSounds.CASTLE_CRASHERS_SPANISH_WALTZ, new Item.Properties().rarity(Rarity.RARE).stacksTo(1), 5360));
//  public static final RegistryObject<Item> GET_LUCKY = ITEMS.register("get_lucky", () -> new RecordItem(4, ZAMSounds.CASTLE_CRASHERS_SPANISH_WALTZ, new Item.Properties().rarity(Rarity.RARE).stacksTo(1), 5360));
//  public static final RegistryObject<Item> AERODYNAMIC = ITEMS.register("aerodynamic", () -> new RecordItem(4, ZAMSounds.CASTLE_CRASHERS_SPANISH_WALTZ, new Item.Properties().rarity(Rarity.RARE).stacksTo(1), 5360));
//  public static final RegistryObject<Item> AROUND_THE_WORLD = ITEMS.register("around_the_world", () -> new RecordItem(4, ZAMSounds.CASTLE_CRASHERS_SPANISH_WALTZ, new Item.Properties().rarity(Rarity.RARE).stacksTo(1), 5360));

//  //Ghost Collection 6
//  public static final RegistryObject<Item> GHOST_SQUARE_HAMMER = ITEMS.register("ghost_square_hammer", () -> new RecordItem(4, ZAMSounds.CASTLE_CRASHERS_SPACE_PIRATES, new Item.Properties().rarity(Rarity.RARE).stacksTo(1), 5360));
//  public static final RegistryObject<Item> GHOST_MARY_ON_A_CROSS = ITEMS.register("ghost_mary_on_a_cross", () -> new RecordItem(4, ZAMSounds.CASTLE_CRASHERS_SPACE_PIRATES, new Item.Properties().rarity(Rarity.RARE).stacksTo(1), 5360));
//  public static final RegistryObject<Item> GHOST_CIRICE = ITEMS.register("ghost_cirice", () -> new RecordItem(4, ZAMSounds.CASTLE_CRASHERS_SPACE_PIRATES, new Item.Properties().rarity(Rarity.RARE).stacksTo(1), 5360));
//  public static final RegistryObject<Item> GHOST_SPILLWAYS = ITEMS.register("ghost_spillways", () -> new RecordItem(4, ZAMSounds.CASTLE_CRASHERS_SPACE_PIRATES, new Item.Properties().rarity(Rarity.RARE).stacksTo(1), 5360));
//  public static final RegistryObject<Item> GHOST_LIFE_ETERNAL = ITEMS.register("ghost_life_eternal", () -> new RecordItem(4, ZAMSounds.CASTLE_CRASHERS_SPACE_PIRATES, new Item.Properties().rarity(Rarity.RARE).stacksTo(1), 5360));
//  public static final RegistryObject<Item> GHOST_RATS = ITEMS.register("ghost_rats", () -> new RecordItem(4, ZAMSounds.CASTLE_CRASHERS_SPACE_PIRATES, new Item.Properties().rarity(Rarity.RARE).stacksTo(1), 5360));

//  //Zelda Collection 9
//  public static final RegistryObject<Item> ZELDA_GERUDO_VALLEY = ITEMS.register("zelda_gerudo_valley", () -> new RecordItem(4, ZAMSounds.CASTLE_CRASHERS_SPACE_PIRATES, new Item.Properties().rarity(Rarity.RARE).stacksTo(1), 5360));
//  public static final RegistryObject<Item> ZELDA_DRAGON_ROOST = ITEMS.register("zelda_dragon_roost", () -> new RecordItem(4, ZAMSounds.CASTLE_CRASHERS_SPACE_PIRATES, new Item.Properties().rarity(Rarity.RARE).stacksTo(1), 5360));
//  public static final RegistryObject<Item> ZELDA_ZORA_DOMAIN = ITEMS.register("zelda_zora_domain", () -> new RecordItem(4, ZAMSounds.CASTLE_CRASHERS_SPACE_PIRATES, new Item.Properties().rarity(Rarity.RARE).stacksTo(1), 5360));
//  public static final RegistryObject<Item> ZELDA_RITO_VILLAGE = ITEMS.register("zelda_rito_village", () -> new RecordItem(4, ZAMSounds.CASTLE_CRASHERS_SPACE_PIRATES, new Item.Properties().rarity(Rarity.RARE).stacksTo(1), 5360));
//  public static final RegistryObject<Item> ZELDA_MAIN_THEME = ITEMS.register("zelda_main_theme", () -> new RecordItem(4, ZAMSounds.CASTLE_CRASHERS_SPACE_PIRATES, new Item.Properties().rarity(Rarity.RARE).stacksTo(1), 5360));
//  public static final RegistryObject<Item> ZELDA_SIDON_THEME = ITEMS.register("zelda_sidon_theme", () -> new RecordItem(4, ZAMSounds.CASTLE_CRASHERS_SPACE_PIRATES, new Item.Properties().rarity(Rarity.RARE).stacksTo(1), 5360));
//  public static final RegistryObject<Item> ZELDA_MIPHA_THEME = ITEMS.register("zelda_mipha_theme", () -> new RecordItem(4, ZAMSounds.CASTLE_CRASHERS_SPACE_PIRATES, new Item.Properties().rarity(Rarity.RARE).stacksTo(1), 5360));
////  public static final RegistryObject<Item> ZELDA_KOROK_FOREST_RECORD = ITEMS.register("zelda_korok_forest_music_disc", () -> new RecordItem(4, ZAMSounds.ZELDA_KOROK_FOREST, new Item.Properties().rarity(Rarity.RARE).stacksTo(1), 4520));
////  public static final RegistryObject<Item> ZELDA_SHRINE_BATTLE_RECORD = ITEMS.register("zelda_shrine_battle_music_disc", () -> new RecordItem(4, ZAMSounds.ZELDA_SHRINE_BATTLE, new Item.Properties().rarity(Rarity.RARE).stacksTo(1), 4280));

//  //Spongebob Collection 5
//  public static final RegistryObject<Item> SPONGEBOB_AWARD_WINNERS_A = ITEMS.register("spongebob_award_winners_a", () -> new RecordItem(4, ZAMSounds.CASTLE_CRASHERS_SPACE_PIRATES, new Item.Properties().rarity(Rarity.RARE).stacksTo(1), 5360));
//  public static final RegistryObject<Item> SPONGEBOB_CHA_CHA_NOVA = ITEMS.register("spongebob_cha_cha_nova", () -> new RecordItem(4, ZAMSounds.CASTLE_CRASHERS_SPACE_PIRATES, new Item.Properties().rarity(Rarity.RARE).stacksTo(1), 5360));
//  public static final RegistryObject<Item> SPONGEBOB_JELLYFISH_JAM = ITEMS.register("spongebob_jellyfish_jam", () -> new RecordItem(4, ZAMSounds.CASTLE_CRASHERS_SPACE_PIRATES, new Item.Properties().rarity(Rarity.RARE).stacksTo(1), 5360));
//  public static final RegistryObject<Item> SPONGEBOB_PUKA_A = ITEMS.register("spongebob_puka_a", () -> new RecordItem(4, ZAMSounds.CASTLE_CRASHERS_SPACE_PIRATES, new Item.Properties().rarity(Rarity.RARE).stacksTo(1), 5360));
//  public static final RegistryObject<Item> SPONGEBOB_SWEET_VICTORY = ITEMS.register("spongebob_sweet_victory", () -> new RecordItem(4, ZAMSounds.CASTLE_CRASHERS_SPACE_PIRATES, new Item.Properties().rarity(Rarity.RARE).stacksTo(1), 5360));

//  //HXH Collection 6
//  public static final RegistryObject<Item> HXH_ALL_I_NEED_IS_MONEY = ITEMS.register("hxh_all_i_need_is_money", () -> new RecordItem(4, ZAMSounds.CASTLE_CRASHERS_SPACE_PIRATES, new Item.Properties().rarity(Rarity.RARE).stacksTo(1), 5360));
//  public static final RegistryObject<Item> HXH_DEPARTURE = ITEMS.register("hxh_departure", () -> new RecordItem(4, ZAMSounds.CASTLE_CRASHERS_SPACE_PIRATES, new Item.Properties().rarity(Rarity.RARE).stacksTo(1), 5360));
//  public static final RegistryObject<Item> HXH_HUNTING_FOR_YOUR_DREAM = ITEMS.register("hxh_hunting_for_your_dream", () -> new RecordItem(4, ZAMSounds.CASTLE_CRASHERS_SPACE_PIRATES, new Item.Properties().rarity(Rarity.RARE).stacksTo(1), 5360));
//  public static final RegistryObject<Item> HXH_WORLD_ADVENTURE = ITEMS.register("hxh_world_adventure", () -> new RecordItem(4, ZAMSounds.CASTLE_CRASHERS_SPACE_PIRATES, new Item.Properties().rarity(Rarity.RARE).stacksTo(1), 5360));
//  public static final RegistryObject<Item> HXH_MARTIAL_ARTIST = ITEMS.register("hxh_martial_artist", () -> new RecordItem(4, ZAMSounds.CASTLE_CRASHERS_SPACE_PIRATES, new Item.Properties().rarity(Rarity.RARE).stacksTo(1), 5360));
//  public static final RegistryObject<Item> HXH_HISOKA = ITEMS.register("hxh_hisoka", () -> new RecordItem(4, ZAMSounds.CASTLE_CRASHERS_SPACE_PIRATES, new Item.Properties().rarity(Rarity.RARE).stacksTo(1), 5360));

//  //Call of Duty Collection 13
//  public static final RegistryObject<Item> COD_SNAKESKIN_BOOTS = ITEMS.register("cod_snakeskin_boots", () -> new RecordItem(4, ZAMSounds.CASTLE_CRASHERS_SPACE_PIRATES, new Item.Properties().rarity(Rarity.RARE).stacksTo(1), 5360));
//  public static final RegistryObject<Item> COD_GREEN_RUN = ITEMS.register("cod_green_run", () -> new RecordItem(4, ZAMSounds.CASTLE_CRASHERS_SPACE_PIRATES, new Item.Properties().rarity(Rarity.RARE).stacksTo(1), 5360));
//  public static final RegistryObject<Item> COD_115 = ITEMS.register("cod_115", () -> new RecordItem(4, ZAMSounds.CASTLE_CRASHERS_SPACE_PIRATES, new Item.Properties().rarity(Rarity.RARE).stacksTo(1), 5360));
//  public static final RegistryObject<Item> COD_WIDOWS_WINE = ITEMS.register("cod_widows_wine", () -> new RecordItem(4, ZAMSounds.CASTLE_CRASHERS_SPACE_PIRATES, new Item.Properties().rarity(Rarity.RARE).stacksTo(1), 5360));
//  public static final RegistryObject<Item> COD_SPEED_COLA = ITEMS.register("cod_speed_cola", () -> new RecordItem(4, ZAMSounds.CASTLE_CRASHERS_SPACE_PIRATES, new Item.Properties().rarity(Rarity.RARE).stacksTo(1), 5360));
//  public static final RegistryObject<Item> COD_ALWAYS_RUNNING = ITEMS.register("cod_always_running", () -> new RecordItem(4, ZAMSounds.CASTLE_CRASHERS_SPACE_PIRATES, new Item.Properties().rarity(Rarity.RARE).stacksTo(1), 5360));
///   public static final RegistryObject<Item> COD_MYSTERY = ITEMS.register("cod_mystery", () -> new RecordItem(4, ZAMSounds.CASTLE_CRASHERS_SPACE_PIRATES, new Item.Properties().rarity(Rarity.RARE).stacksTo(1), 5360));
////  public static final RegistryObject<Item> COD_ECHOES_DAMNED = ITEMS.register("cod_echoes_damned", () -> new RecordItem(4, ZAMSounds.COD_ECHOES_DAMNED, new Item.Properties().rarity(Rarity.RARE).stacksTo(1), 6040));
////  public static final RegistryObject<Item> COD_RUSTY_CAGE = ITEMS.register("cod_rusty_cage", () -> new RecordItem(4, ZAMSounds.COD_RUSTY_CAGE, new Item.Properties().rarity(Rarity.RARE).stacksTo(1), 4320));
////  public static final RegistryObject<Item> COD_WE_ALL_FALL_DOWN = ITEMS.register("cod_we_all_fall_down", () -> new RecordItem(4, ZAMSounds.COD_WE_ALL_FALL_DOWN, new Item.Properties().rarity(Rarity.RARE).stacksTo(1), 4000));
////  public static final RegistryObject<Item> COD_WHERE_ARE_WE = ITEMS.register("cod_where_are_we", () -> new RecordItem(4, ZAMSounds.COD_WHERE_ARE_WE, new Item.Properties().rarity(Rarity.RARE).stacksTo(1), 6360));
////  public static final RegistryObject<Item> COD_SHADOWS_JAZZ = ITEMS.register("cod_shadows_jazz", () -> new RecordItem(4, ZAMSounds.COD_SHADOWS_JAZZ, new Item.Properties().rarity(Rarity.RARE).stacksTo(1), 3280));
///   public static final RegistryObject<Item> COD_MOTD = ITEMS.register("cod_motd", () -> new RecordItem(4, ZAMSounds.COD_MOB_OF_THE_DEAD_MONO, new Item.Properties().rarity(Rarity.RARE).stacksTo(1), 1080));

//  //Entertainment Collection 8
//  public static final RegistryObject<Item> AOT_GUREN_NO_YUMIYA = ITEMS.register("aot_guren_no_yumiya", () -> new RecordItem(4, ZAMSounds.CASTLE_CRASHERS_SPACE_PIRATES, new Item.Properties().rarity(Rarity.RARE).stacksTo(1), 5360));
////  public static final RegistryObject<Item> HTTDY_TEST_DRIVE_RECORD = ITEMS.register("httyd_test_drive_music_disc", () -> new RecordItem(4, ZAMSounds.HTTYD_TEST_DRIVE, new Item.Properties().rarity(Rarity.RARE).stacksTo(1), 3840));
// //// public static final RegistryObject<Item> HTTYD_MAP_THE_WORLD_RECORD = ITEMS.register("httyd_map_the_world_music_disc", () -> new RecordItem(4, ZAMSounds.HTTYD_MAP_THE_WORLD, new Item.Properties().rarity(Rarity.RARE).stacksTo(1), 3120));
////  public static final RegistryObject<Item> CARS_RECORD = ITEMS.register("cars_music_disc", () -> new RecordItem(4, ZAMSounds.CARS, new Item.Properties().rarity(Rarity.RARE).stacksTo(1), 6240));
////  public static final RegistryObject<Item> CARS2_RECORD = ITEMS.register("cars2_music_disc", () -> new RecordItem(4, ZAMSounds.CARS2, new Item.Properties().rarity(Rarity.RARE).stacksTo(1), 4920));
//  public static final RegistryObject<Item> JERMA = ITEMS.register("jerma", () -> new RecordItem(4, ZAMSounds.CASTLE_CRASHERS_SPACE_PIRATES, new Item.Properties().rarity(Rarity.RARE).stacksTo(1), 5360));
//  public static final RegistryObject<Item> GRAVITY_FALLS = ITEMS.register("gravity_falls", () -> new RecordItem(4, ZAMSounds.CASTLE_CRASHERS_SPACE_PIRATES, new Item.Properties().rarity(Rarity.RARE).stacksTo(1), 5360));
//  public static final RegistryObject<Item> STAR_WARS_DUEL_OF_THE_FATES = ITEMS.register("star_wars_duel_of_the_fates", () -> new RecordItem(4, ZAMSounds.CASTLE_CRASHERS_SPACE_PIRATES, new Item.Properties().rarity(Rarity.RARE).stacksTo(1), 5360));

//  // Collection 8
//  public static final RegistryObject<Item> STIR_FRY = ITEMS.register("stir_fry", () -> new RecordItem(4, ZAMSounds.CASTLE_CRASHERS_SPACE_PIRATES, new Item.Properties().rarity(Rarity.RARE).stacksTo(1), 5360));
//  public static final RegistryObject<Item> TELEPHONE = ITEMS.register("telephone", () -> new RecordItem(4, ZAMSounds.CASTLE_CRASHERS_SPACE_PIRATES, new Item.Properties().rarity(Rarity.RARE).stacksTo(1), 5360));
//  public static final RegistryObject<Item> WE_DIDNT_START_THE_FIRE = ITEMS.register("we_didnt_start_the_fire", () -> new RecordItem(4, ZAMSounds.CASTLE_CRASHERS_SPACE_PIRATES, new Item.Properties().rarity(Rarity.RARE).stacksTo(1), 5360));
//  public static final RegistryObject<Item> HOTEL_CALIFORNIA = ITEMS.register("hotel_california", () -> new RecordItem(4, ZAMSounds.CASTLE_CRASHERS_SPACE_PIRATES, new Item.Properties().rarity(Rarity.RARE).stacksTo(1), 5360));
//  public static final RegistryObject<Item> IM_FREE = ITEMS.register("im_free", () -> new RecordItem(4, ZAMSounds.CASTLE_CRASHERS_SPACE_PIRATES, new Item.Properties().rarity(Rarity.RARE).stacksTo(1), 5360));
//  public static final RegistryObject<Item> RAPP_SNITCHES = ITEMS.register("rapp_snitches", () -> new RecordItem(4, ZAMSounds.CASTLE_CRASHERS_SPACE_PIRATES, new Item.Properties().rarity(Rarity.RARE).stacksTo(1), 5360));
//  public static final RegistryObject<Item> LIE_LIE_LIE = ITEMS.register("lie_lie_lie", () -> new RecordItem(4, ZAMSounds.CASTLE_CRASHERS_SPACE_PIRATES, new Item.Properties().rarity(Rarity.RARE).stacksTo(1), 5360));
//  public static final RegistryObject<Item> MICROWAVE_POPCORN = ITEMS.register("microwave_popcorn", () -> new RecordItem(4, ZAMSounds.CASTLE_CRASHERS_SPACE_PIRATES, new Item.Properties().rarity(Rarity.RARE).stacksTo(1), 5360));

//  //Gaming Collection 10
//  public static final RegistryObject<Item> DONKEY_KONG = ITEMS.register("donkey_long", () -> new RecordItem(4, ZAMSounds.CASTLE_CRASHERS_SPACE_PIRATES, new Item.Properties().rarity(Rarity.RARE).stacksTo(1), 5360));
//  public static final RegistryObject<Item> TETRIS = ITEMS.register("tetris", () -> new RecordItem(4, ZAMSounds.CASTLE_CRASHERS_SPACE_PIRATES, new Item.Properties().rarity(Rarity.RARE).stacksTo(1), 5360));
//  public static final RegistryObject<Item> EARTHBOUND = ITEMS.register("earthbound", () -> new RecordItem(4, ZAMSounds.CASTLE_CRASHERS_SPACE_PIRATES, new Item.Properties().rarity(Rarity.RARE).stacksTo(1), 5360));
//  public static final RegistryObject<Item> RUNESCAPE = ITEMS.register("runescape", () -> new RecordItem(4, ZAMSounds.CASTLE_CRASHERS_SPACE_PIRATES, new Item.Properties().rarity(Rarity.RARE).stacksTo(1), 5360));
//  public static final RegistryObject<Item> FORTNITE = ITEMS.register("fortnite", () -> new RecordItem(4, ZAMSounds.CASTLE_CRASHERS_SPACE_PIRATES, new Item.Properties().rarity(Rarity.RARE).stacksTo(1), 5360));
//  public static final RegistryObject<Item> KIRBY = ITEMS.register("kirby", () -> new RecordItem(4, ZAMSounds.CASTLE_CRASHERS_SPACE_PIRATES, new Item.Properties().rarity(Rarity.RARE).stacksTo(1), 5360));
//  public static final RegistryObject<Item> TERARRIA = ITEMS.register("terarria", () -> new RecordItem(4, ZAMSounds.CASTLE_CRASHERS_SPACE_PIRATES, new Item.Properties().rarity(Rarity.RARE).stacksTo(1), 5360));
//  public static final RegistryObject<Item> UNO = ITEMS.register("uno", () -> new RecordItem(4, ZAMSounds.CASTLE_CRASHERS_SPACE_PIRATES, new Item.Properties().rarity(Rarity.RARE).stacksTo(1), 5360));

//  //Terrific Tunes Collection 5
//  public static final RegistryObject<Item> CAT_INDEV = ITEMS.register("cat_indev", () -> new RecordItem(4, ZAMSounds.CASTLE_CRASHERS_SPACE_PIRATES, new Item.Properties().rarity(Rarity.RARE).stacksTo(1), 5360));
//  public static final RegistryObject<Item> LUPUS_NOCTE_HOWLING = ITEMS.register("lupus_nocte_howling", () -> new RecordItem(4, ZAMSounds.CASTLE_CRASHERS_SPACE_PIRATES, new Item.Properties().rarity(Rarity.RARE).stacksTo(1), 5360));
//  public static final RegistryObject<Item> C418_DOG = ITEMS.register("c418_dog", () -> new RecordItem(4, ZAMSounds.CASTLE_CRASHERS_SPACE_PIRATES, new Item.Properties().rarity(Rarity.RARE).stacksTo(1), 5360));
//  public static final RegistryObject<Item> LE_FISHE = ITEMS.register("le_fishe", () -> new RecordItem(4, ZAMSounds.CASTLE_CRASHERS_SPACE_PIRATES, new Item.Properties().rarity(Rarity.RARE).stacksTo(1), 5360));

//  //Animal Crossing Collection 6
//  public static final RegistryObject<Item> AC_SPACE_KK = ITEMS.register("ac_space_kk", () -> new RecordItem(4, ZAMSounds.CASTLE_CRASHERS_SPACE_PIRATES, new Item.Properties().rarity(Rarity.RARE).stacksTo(1), 5360));
//  public static final RegistryObject<Item> AC_LUCKY_KK = ITEMS.register("ac_lucky_kk", () -> new RecordItem(4, ZAMSounds.CASTLE_CRASHERS_SPACE_PIRATES, new Item.Properties().rarity(Rarity.RARE).stacksTo(1), 5360));
//  public static final RegistryObject<Item> AC_BUBBLEGUM_KK = ITEMS.register("ac_bubblegum_kk", () -> new RecordItem(4, ZAMSounds.CASTLE_CRASHERS_SPACE_PIRATES, new Item.Properties().rarity(Rarity.RARE).stacksTo(1), 5360));
//  public static final RegistryObject<Item> AC_DODO_AIRLINES = ITEMS.register("ac_dodo_airlines", () -> new RecordItem(4, ZAMSounds.CASTLE_CRASHERS_SPACE_PIRATES, new Item.Properties().rarity(Rarity.RARE).stacksTo(1), 5360));
//  public static final RegistryObject<Item> AC_ISLAND_TOUR = ITEMS.register("ac_island_tour", () -> new RecordItem(4, ZAMSounds.CASTLE_CRASHERS_SPACE_PIRATES, new Item.Properties().rarity(Rarity.RARE).stacksTo(1), 5360));
//  public static final RegistryObject<Item> AC_HARVS_ISLAND = ITEMS.register("ac_harvs_island", () -> new RecordItem(4, ZAMSounds.CASTLE_CRASHERS_SPACE_PIRATES, new Item.Properties().rarity(Rarity.RARE).stacksTo(1), 5360));

//  //Halo Collection 5
//  public static final RegistryObject<Item> HALO_MASHUP = ITEMS.register("halo_mashup", () -> new RecordItem(4, ZAMSounds.CASTLE_CRASHERS_SPACE_PIRATES, new Item.Properties().rarity(Rarity.RARE).stacksTo(1), 5360));
//  public static final RegistryObject<Item> HALO_OVERTURE = ITEMS.register("halo_overture", () -> new RecordItem(4, ZAMSounds.CASTLE_CRASHERS_SPACE_PIRATES, new Item.Properties().rarity(Rarity.RARE).stacksTo(1), 5360));
//  public static final RegistryObject<Item> HALO_MAIN_THEME = ITEMS.register("halo_main_theme", () -> new RecordItem(4, ZAMSounds.CASTLE_CRASHERS_SPACE_PIRATES, new Item.Properties().rarity(Rarity.RARE).stacksTo(1), 5360));
//  public static final RegistryObject<Item> HALO_WARTHOG_RUN = ITEMS.register("halo_warthog_run", () -> new RecordItem(4, ZAMSounds.CASTLE_CRASHERS_SPACE_PIRATES, new Item.Properties().rarity(Rarity.RARE).stacksTo(1), 5360));
//  public static final RegistryObject<Item> HALO_ATONEMENT = ITEMS.register("halo_atonement", () -> new RecordItem(4, ZAMSounds.CASTLE_CRASHERS_SPACE_PIRATES, new Item.Properties().rarity(Rarity.RARE).stacksTo(1), 5360));

    //Deep Rock Collection 4
  //  public static final RegistryObject<Item> DEEP_ROCK_CODE_TO_THE_FALLEN_RECORD = ITEMS.register("deep_rock_code_to_the_fallen_music_disc", () -> new RecordItem(4, ZAMSounds.DEEP_ROCK_CODE_TO_THE_FALLEN, new Item.Properties().rarity(Rarity.RARE).stacksTo(7200), 7240));
 ////   public static final RegistryObject<Item> DEEP_ROCK_THEYRE_HERE_RECORD = ITEMS.register("deep_rock_theyre_here_music_disc", () -> new RecordItem(4, ZAMSounds.DEEP_ROCK_THEYRE_HERE, new Item.Properties().rarity(Rarity.RARE).stacksTo(1), 4200));
 //   public static final RegistryObject<Item> DEEP_ROCK_ROBOT_GETAWAY_RECORD = ITEMS.register("deep_rock_robot_getaway_music_disc", () -> new RecordItem(4, ZAMSounds.DEEP_ROCK_ROBOT_GETAWAY, new Item.Properties().rarity(Rarity.RARE).stacksTo(1), 4640));
//    public static final RegistryObject<Item> DEEP_ROCK_GALACTIC_RUN_RECORD = ITEMS.register("deep_rock_galactic_run_music_disc", () -> new RecordItem(4, ZAMSounds.DEEP_ROCK_GALACTIC_RUN, new Item.Properties().rarity(Rarity.RARE).stacksTo(1), 3520));

    //Omori Collection 5
//    public static final RegistryObject<Item> OMORI_GOLDENVENGEANCE_RECORD = ITEMS.register("omori_goldenvengeance_music_disc", () -> new RecordItem(4, ZAMSounds.OMORI_GOLDENVENEANCE, new Item.Properties().rarity(Rarity.RARE).stacksTo(1), 3560));
 //   public static final RegistryObject<Item> OMORI_MY_TIME_RECORD = ITEMS.register("omori_my_time_music_disc", () -> new RecordItem(4, ZAMSounds.OMORI_MY_TIME, new Item.Properties().rarity(Rarity.RARE).stacksTo(1), 4920));
//    public static final RegistryObject<Item> OMORI_WORLD_VALENTINE_RECORD = ITEMS.register("omori_world_valentine_music_disc", () -> new RecordItem(4, ZAMSounds.OMORI_WORLD_VALENTINE, new Item.Properties().rarity(Rarity.RARE).stacksTo(1), 3480));
 //   public static final RegistryObject<Item> OMORI_BREADY_STEADY_RECORD = ITEMS.register("omori_bready_steady_music_disc", () -> new RecordItem(4, ZAMSounds.OMORI_BREADY_STEADY, new Item.Properties().rarity(Rarity.RARE).stacksTo(1), 2600));
  //  public static final RegistryObject<Item> OMORI_BY_YOUR_SIDE_RECORD = ITEMS.register("omori_by_your_side_music_disc", () -> new RecordItem(4, ZAMSounds.OMORI_BY_YOUR_SIDE, new Item.Properties().rarity(Rarity.RARE).stacksTo(1), 2640));

    //Skyrim Collection 5
 //   public static final RegistryObject<Item> SKYRIM_DRAGONBORN_RECORD = ITEMS.register("skyrim_dragonborn_music_disc", () -> new RecordItem(4, ZAMSounds.SKYRIM_DRAGONBORN, new Item.Properties().rarity(Rarity.RARE).stacksTo(1), 5800));
  //  public static final RegistryObject<Item> SKYRIM_PAST_TO_PRESENT_RECORD = ITEMS.register("skyrim_past_to_present_music_disc", () -> new RecordItem(4, ZAMSounds.SKYRIM_PAST_TO_PRESENT, new Item.Properties().rarity(Rarity.RARE).stacksTo(1), 6160));
   // public static final RegistryObject<Item> SKYRIM_FAR_HORIZONS_RECORD = ITEMS.register("skyrim_far_horizons_music_disc", () -> new RecordItem(4, ZAMSounds.SKYRIM_FAR_HORIZONS, new Item.Properties().rarity(Rarity.RARE).stacksTo(1), 7240));
  //  public static final RegistryObject<Item> SKYRIM_STEEL_ON_STEEL_RECORD = ITEMS.register("skyrim_steel_on_steel_music_disc", () -> new RecordItem(4, ZAMSounds.SKYRIM_STEEL_ON_STEEL, new Item.Properties().rarity(Rarity.RARE).stacksTo(1), 2920));
 //   public static final RegistryObject<Item> SKYRIM_WATCH_SKIES_RECORD = ITEMS.register("skyrim_watch_skies_music_disc", () -> new RecordItem(4, ZAMSounds.SKYRIM_WATCH_SKIES, new Item.Properties().rarity(Rarity.RARE).stacksTo(1), 3200));

    //Dragon Ball Collection 7
  //  public static final RegistryObject<Item> DB_ULTRA_INSTINCT_RECORD = ITEMS.register("db_ultra_instinct_music_disc", () -> new RecordItem(4, ZAMSounds.DB_ULTRA_INSTINCT, new Item.Properties().rarity(Rarity.RARE).stacksTo(1), 4280));
  //  public static final RegistryObject<Item> DB_ULTIMATE_BATTLE_RECORD = ITEMS.register("db_ultimate_battle_music_disc", () -> new RecordItem(4, ZAMSounds.DB_ULTIMATE_BATTLE, new Item.Properties().rarity(Rarity.RARE).stacksTo(1), 5920));
  //  public static final RegistryObject<Item> DB_GT_RECAP_RECORD = ITEMS.register("db_gt_recap_music_disc", () -> new RecordItem(4, ZAMSounds.DB_GT_RECAP, new Item.Properties().rarity(Rarity.RARE).stacksTo(1), 2440));
    public static final RegistryObject<Item> DB_CHA_LA_RECORD = ITEMS.register("db_cha_la_music_disc", () -> new RecordItem(4, ZAMSounds.DB_CHA_LA, new Item.Properties().rarity(Rarity.RARE).stacksTo(1), 4360));
  //  public static final RegistryObject<Item> DB_DRAGON_SOUL_RECORD = ITEMS.register("db_dragon_soul_music_disc", () -> new RecordItem(4, ZAMSounds.DB_DRAGON_SOUL, new Item.Properties().rarity(Rarity.RARE).stacksTo(1), 5840));
  //  public static final RegistryObject<Item> DB_BLUE_RECORD = ITEMS.register("db_blue_music_disc", () -> new RecordItem(4, ZAMSounds.DB_BLUE, new Item.Properties().rarity(Rarity.RARE).stacksTo(1), 1840));
  //  public static final RegistryObject<Item> DB_119_RECORD = ITEMS.register("db_119_music_disc", () -> new RecordItem(4, ZAMSounds.DB_119, new Item.Properties().rarity(Rarity.RARE).stacksTo(1), 2000));

    //One Piece Collection 7
    public static final RegistryObject<Item> ONE_PIECE_CHOPPER = ITEMS.register("one_piece_chopper", () -> new RecordItem(4, ZAMSounds.CASTLE_CRASHERS_SPACE_PIRATES, new Item.Properties().rarity(Rarity.RARE).stacksTo(1), 5360));
   // public static final RegistryObject<Item> ONE_PIECE_SANJI = ITEMS.register("one_piece_sanji", () -> new RecordItem(4, ZAMSounds.ONE_PIECE_SANJI, new Item.Properties().rarity(Rarity.RARE).stacksTo(1), 3320));
  //  public static final RegistryObject<Item> ONE_PIECE_ROBIN = ITEMS.register("one_piece_robin", () -> new RecordItem(4, ZAMSounds.ONE_PIECE_ROBIN, new Item.Properties().rarity(Rarity.RARE).stacksTo(1), 2880));
  //  public static final RegistryObject<Item> ONE_PIECE_NAMI = ITEMS.register("one_piece_nami", () -> new RecordItem(4, ZAMSounds.ONE_PIECE_NAMI, new Item.Properties().rarity(Rarity.RARE).stacksTo(1), 4480));
  //  public static final RegistryObject<Item> ONE_PIECE_LUFFY = ITEMS.register("one_piece_luffy", () -> new RecordItem(4, ZAMSounds.ONE_PIECE_LUFFY, new Item.Properties().rarity(Rarity.RARE).stacksTo(1), 6480));
  //  public static final RegistryObject<Item> ONE_PIECE_FRANKY = ITEMS.register("one_piece_franky", () -> new RecordItem(4, ZAMSounds.ONE_PIECE_FRANKY, new Item.Properties().rarity(Rarity.RARE).stacksTo(1), 7000));
 //   public static final RegistryObject<Item> ONE_PIECE_ZORO = ITEMS.register("one_piece_zoro", () -> new RecordItem(4, ZAMSounds.ONE_PIECE_ZORO, new Item.Properties().rarity(Rarity.RARE).stacksTo(1), 7000));

    //Smash Bros Collection 6
  //  public static final RegistryObject<Item> SMASH_BATTLEFIELD_BRAWL = ITEMS.register("smash_battlefield_brawl", () -> new RecordItem(4, ZAMSounds.SMASH_BATTLEFIELD_BRAWL, new Item.Properties().rarity(Rarity.RARE).stacksTo(1), 2840));
 //   public static final RegistryObject<Item> SMASH_BATTLEFIELD_ULTIMATE = ITEMS.register("smash_battlefield_ultimate", () -> new RecordItem(4, ZAMSounds.SMASH_BATTLEFIELD_ULTIMATE, new Item.Properties().rarity(Rarity.RARE).stacksTo(1), 4160));
 //   public static final RegistryObject<Item> SMASH_BRAWL = ITEMS.register("smash_brawl", () -> new RecordItem(4, ZAMSounds.SMASH_BRAWL, new Item.Properties().rarity(Rarity.RARE).stacksTo(1), 2400));
 //   public static final RegistryObject<Item> SMASH_MELEE = ITEMS.register("smash_melee", () -> new RecordItem(4, ZAMSounds.SMASH_MELEE, new Item.Properties().rarity(Rarity.RARE).stacksTo(1), 2440));
 //   public static final RegistryObject<Item> SMASH_MELEE_MENU = ITEMS.register("smash_melee_menu", () -> new RecordItem(4, ZAMSounds.SMASH_MELEE_MENU, new Item.Properties().rarity(Rarity.RARE).stacksTo(1), 3400));
 //   public static final RegistryObject<Item> SMASH_LIFELIGHT = ITEMS.register("smash_lifelight", () -> new RecordItem(4, ZAMSounds.SMASH_LIFELIGHT, new Item.Properties().rarity(Rarity.RARE).stacksTo(1), 5560));

    //Classical Collection 7
 //   public static final RegistryObject<Item> TOCCATA = ITEMS.register("tocatta", () -> new RecordItem(4, ZAMSounds.TOCCATA, new Item.Properties().rarity(Rarity.RARE).stacksTo(1), 11600));
 //   public static final RegistryObject<Item> BEETHOVEN_MOONLIGHT_SONATA = ITEMS.register("beethoven_moonlight_sonata", () -> new RecordItem(4, ZAMSounds.TOCCATA, new Item.Properties().rarity(Rarity.RARE).stacksTo(1), 11600));
 ///   public static final RegistryObject<Item> BACH_PRELUDE_C = ITEMS.register("bach_prelude_c", () -> new RecordItem(4, ZAMSounds.TOCCATA, new Item.Properties().rarity(Rarity.RARE).stacksTo(1), 11600));
 //   public static final RegistryObject<Item> MOZART_SERENADE = ITEMS.register("mozart_serenade", () -> new RecordItem(4, ZAMSounds.TOCCATA, new Item.Properties().rarity(Rarity.RARE).stacksTo(1), 11600));
   // public static final RegistryObject<Item> BEETHOVEN_FUR_ELISE = ITEMS.register("beethoven_fur_elise", () -> new RecordItem(4, ZAMSounds.TOCCATA, new Item.Properties().rarity(Rarity.RARE).stacksTo(1), 11600));
  //  public static final RegistryObject<Item> CHOPIN_PRELUDE_E = ITEMS.register("chopin_prelude_e", () -> new RecordItem(4, ZAMSounds.TOCCATA, new Item.Properties().rarity(Rarity.RARE).stacksTo(1), 11600));
  //  public static final RegistryObject<Item> BACH_CELLO = ITEMS.register("bach_cello", () -> new RecordItem(4, ZAMSounds.TOCCATA, new Item.Properties().rarity(Rarity.RARE).stacksTo(1), 11600));
    //  public static final RegistryObject<Item> RUSH_E = ITEMS.register("rush_e", () -> new RecordItem(4, ZAMSounds.TOCCATA, new Item.Properties().rarity(Rarity.RARE).stacksTo(1), 11600));

    //Personal







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

    @Override
    protected Object clone() throws CloneNotSupportedException {
        return super.clone();
    }
}
