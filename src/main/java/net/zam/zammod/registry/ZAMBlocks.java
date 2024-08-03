package net.zam.zammod.registry;

import net.minecraft.util.valueproviders.UniformInt;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.properties.NoteBlockInstrument;
import net.minecraft.world.level.material.MapColor;
import net.minecraft.world.level.material.PushReaction;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import net.zam.zammod.ZAMMod;
import net.zam.zammod.block.*;
import net.zam.zammod.block.arcade.ArcadeMachineBlock;
import net.zam.zammod.block.beer.mugs.MugBlock;
import net.zam.zammod.block.beer.mugs.beer.*;
import net.zam.zammod.block.beer.plants.Hop;
import net.zam.zammod.block.beer.plants.HopPlant;
import net.zam.zammod.block.beer.workstations.Keg;
import net.zam.zammod.block.chest.LostBounty;
import net.zam.zammod.block.grimm.GrimmFireBlock;
import net.zam.zammod.block.grimm.GrimmSoilBlock;
import net.zam.zammod.item.renderer.BlockItemWithoutLevelRenderer;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.function.Supplier;

public class ZAMBlocks {
    public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, ZAMMod.MOD_ID);

    //Arcade
    public static final RegistryObject<Block> ARCADE_MACHINE = registerBlock("arcade_machine", () -> new ArcadeMachineBlock(BlockBehaviour.Properties.copy(Blocks.IRON_BLOCK).mapColor(MapColor.METAL).strength(2.0F).sound(SoundType.METAL).noOcclusion()));


    public static final RegistryObject<Block> CONVERSION_TABLE = registerBlock("conversion_table", () -> new Block(BlockBehaviour.Properties.copy(Blocks.SMITHING_TABLE)));

    //Ocean
    public static final RegistryObject<Block> LOST_BOUNTY = registerWithRenderer(LostBounty::new, "lost_bounty", new Item.Properties());
    public static final RegistryObject<Block> SCAFFINITY = registerBlockAndItem("scaffinity", () -> new ScaffinityBlock(BlockBehaviour.Properties.copy(Blocks.SCAFFOLDING).mapColor(MapColor.COLOR_LIGHT_BLUE).noLootTable()), 1);

    //Froglights
    public static final RegistryObject<Block> RUDDY_FROGLIGHT = registerBlock("ruddy_froglight", () -> new RotatedPillarBlock(BlockBehaviour.Properties.copy(Blocks.OCHRE_FROGLIGHT).mapColor(MapColor.COLOR_RED).strength(0.3f).lightLevel(b -> 15).sound(SoundType.FROGLIGHT)));
    public static final RegistryObject<Block> AZURE_FROGLIGHT = registerBlock("azure_froglight", () -> new RotatedPillarBlock(BlockBehaviour.Properties.copy(Blocks.OCHRE_FROGLIGHT).mapColor(MapColor.COLOR_BLUE).strength(0.3f).lightLevel(b -> 15).sound(SoundType.FROGLIGHT)));
    public static final RegistryObject<Block> EBON_FROGLIGHT = registerBlock("ebon_froglight", () -> new RotatedPillarBlock(BlockBehaviour.Properties.copy(Blocks.OCHRE_FROGLIGHT).mapColor(MapColor.COLOR_BLACK).strength(0.3f).lightLevel(b -> 15).sound(SoundType.FROGLIGHT)));

    //Concrete
    public static final RegistryObject<Block> WHITE_CONCRETE_SLAB = registerBlock("white_concrete_slab", () -> new SlabBlock(BlockBehaviour.Properties.copy(Blocks.WHITE_CONCRETE)));
    public static final RegistryObject<Block> LIGHT_GRAY_CONCRETE_SLAB = registerBlock("light_gray_concrete_slab", () -> new SlabBlock(BlockBehaviour.Properties.copy(Blocks.LIGHT_GRAY_CONCRETE)));
    public static final RegistryObject<Block> GRAY_CONCRETE_SLAB = registerBlock("gray_concrete_slab", () -> new SlabBlock(BlockBehaviour.Properties.copy(Blocks.GRAY_CONCRETE)));
    public static final RegistryObject<Block> BLACK_CONCRETE_SLAB = registerBlock("black_concrete_slab", () -> new SlabBlock(BlockBehaviour.Properties.copy(Blocks.BLACK_CONCRETE)));
    public static final RegistryObject<Block> BROWN_CONCRETE_SLAB = registerBlock("brown_concrete_slab", () -> new SlabBlock(BlockBehaviour.Properties.copy(Blocks.BROWN_CONCRETE)));
    public static final RegistryObject<Block> RED_CONCRETE_SLAB = registerBlock("red_concrete_slab", () -> new SlabBlock(BlockBehaviour.Properties.copy(Blocks.RED_CONCRETE)));
    public static final RegistryObject<Block> ORANGE_CONCRETE_SLAB = registerBlock("orange_concrete_slab", () -> new SlabBlock(BlockBehaviour.Properties.copy(Blocks.ORANGE_CONCRETE)));
    public static final RegistryObject<Block> YELLOW_CONCRETE_SLAB = registerBlock("yellow_concrete_slab", () -> new SlabBlock(BlockBehaviour.Properties.copy(Blocks.YELLOW_CONCRETE)));
    public static final RegistryObject<Block> LIME_CONCRETE_SLAB = registerBlock("lime_concrete_slab", () -> new SlabBlock(BlockBehaviour.Properties.copy(Blocks.LIME_CONCRETE)));
    public static final RegistryObject<Block> GREEN_CONCRETE_SLAB = registerBlock("green_concrete_slab", () -> new SlabBlock(BlockBehaviour.Properties.copy(Blocks.GREEN_CONCRETE)));
    public static final RegistryObject<Block> CYAN_CONCRETE_SLAB = registerBlock("cyan_concrete_slab", () -> new SlabBlock(BlockBehaviour.Properties.copy(Blocks.CYAN_CONCRETE)));
    public static final RegistryObject<Block> LIGHT_BLUE_CONCRETE_SLAB = registerBlock("light_blue_concrete_slab", () -> new SlabBlock(BlockBehaviour.Properties.copy(Blocks.LIGHT_BLUE_CONCRETE)));
    public static final RegistryObject<Block> BLUE_CONCRETE_SLAB = registerBlock("blue_concrete_slab", () -> new SlabBlock(BlockBehaviour.Properties.copy(Blocks.BLUE_CONCRETE)));
    public static final RegistryObject<Block> PURPLE_CONCRETE_SLAB = registerBlock("purple_concrete_slab", () -> new SlabBlock(BlockBehaviour.Properties.copy(Blocks.PURPLE_CONCRETE)));
    public static final RegistryObject<Block> MAGENTA_CONCRETE_SLAB = registerBlock("magenta_concrete_slab", () -> new SlabBlock(BlockBehaviour.Properties.copy(Blocks.MAGENTA_CONCRETE)));
    public static final RegistryObject<Block> PINK_CONCRETE_SLAB = registerBlock("pink_concrete_slab", () -> new SlabBlock(BlockBehaviour.Properties.copy(Blocks.PINK_CONCRETE)));

    public static final RegistryObject<Block> WHITE_CONCRETE_STAIRS = registerBlock("white_concrete_stairs", () -> new StairBlock(Blocks.WHITE_CONCRETE::defaultBlockState, BlockBehaviour.Properties.copy(Blocks.WHITE_CONCRETE)));
    public static final RegistryObject<Block> LIGHT_GRAY_CONCRETE_STAIRS = registerBlock("light_gray_concrete_stairs", () -> new StairBlock(Blocks.LIGHT_GRAY_CONCRETE::defaultBlockState, BlockBehaviour.Properties.copy(Blocks.LIGHT_GRAY_CONCRETE)));
    public static final RegistryObject<Block> GRAY_CONCRETE_STAIRS = registerBlock("gray_concrete_stairs", () -> new StairBlock(Blocks.GRAY_CONCRETE::defaultBlockState, BlockBehaviour.Properties.copy(Blocks.GRAY_CONCRETE)));
    public static final RegistryObject<Block> BLACK_CONCRETE_STAIRS = registerBlock("black_concrete_stairs", () -> new StairBlock(Blocks.BLACK_CONCRETE::defaultBlockState, BlockBehaviour.Properties.copy(Blocks.BLACK_CONCRETE)));
    public static final RegistryObject<Block> BROWN_CONCRETE_STAIRS = registerBlock("brown_concrete_stairs", () -> new StairBlock(Blocks.BROWN_CONCRETE::defaultBlockState, BlockBehaviour.Properties.copy(Blocks.BROWN_CONCRETE)));
    public static final RegistryObject<Block> RED_CONCRETE_STAIRS = registerBlock("red_concrete_stairs", () -> new StairBlock(Blocks.RED_CONCRETE::defaultBlockState, BlockBehaviour.Properties.copy(Blocks.RED_CONCRETE)));
    public static final RegistryObject<Block> ORANGE_CONCRETE_STAIRS = registerBlock("orange_concrete_stairs", () -> new StairBlock(Blocks.ORANGE_CONCRETE::defaultBlockState, BlockBehaviour.Properties.copy(Blocks.ORANGE_CONCRETE)));
    public static final RegistryObject<Block> YELLOW_CONCRETE_STAIRS = registerBlock("yellow_concrete_stairs", () -> new StairBlock(Blocks.YELLOW_CONCRETE::defaultBlockState, BlockBehaviour.Properties.copy(Blocks.YELLOW_CONCRETE)));
    public static final RegistryObject<Block> LIME_CONCRETE_STAIRS = registerBlock("lime_concrete_stairs", () -> new StairBlock(Blocks.LIME_CONCRETE::defaultBlockState, BlockBehaviour.Properties.copy(Blocks.LIME_CONCRETE)));
    public static final RegistryObject<Block> GREEN_CONCRETE_STAIRS = registerBlock("green_concrete_stairs", () -> new StairBlock(Blocks.GREEN_CONCRETE::defaultBlockState, BlockBehaviour.Properties.copy(Blocks.GREEN_CONCRETE)));
    public static final RegistryObject<Block> CYAN_CONCRETE_STAIRS = registerBlock("cyan_concrete_stairs", () -> new StairBlock(Blocks.CYAN_CONCRETE::defaultBlockState, BlockBehaviour.Properties.copy(Blocks.CYAN_CONCRETE)));
    public static final RegistryObject<Block> LIGHT_BLUE_CONCRETE_STAIRS = registerBlock("light_blue_concrete_stairs", () -> new StairBlock(Blocks.LIGHT_GRAY_CONCRETE::defaultBlockState, BlockBehaviour.Properties.copy(Blocks.LIGHT_GRAY_CONCRETE)));
    public static final RegistryObject<Block> BLUE_CONCRETE_STAIRS = registerBlock("blue_concrete_stairs", () -> new StairBlock(Blocks.BLUE_CONCRETE::defaultBlockState, BlockBehaviour.Properties.copy(Blocks.BLUE_CONCRETE)));
    public static final RegistryObject<Block> PURPLE_CONCRETE_STAIRS = registerBlock("purple_concrete_stairs", () -> new StairBlock(Blocks.PURPLE_CONCRETE::defaultBlockState, BlockBehaviour.Properties.copy(Blocks.PURPLE_CONCRETE)));
    public static final RegistryObject<Block> MAGENTA_CONCRETE_STAIRS = registerBlock("magenta_concrete_stairs", () -> new StairBlock(Blocks.MAGENTA_CONCRETE::defaultBlockState, BlockBehaviour.Properties.copy(Blocks.MAGENTA_CONCRETE)));
    public static final RegistryObject<Block> PINK_CONCRETE_STAIRS = registerBlock("pink_concrete_stairs", () -> new StairBlock(Blocks.PINK_CONCRETE::defaultBlockState, BlockBehaviour.Properties.copy(Blocks.PINK_CONCRETE)));


    //Slab & Stair Misc
    public static final RegistryObject<Block> PACKED_ICE_SLAB = registerBlock("packed_ice_slab", () -> new SlabBlock(BlockBehaviour.Properties.copy(Blocks.PACKED_ICE)));
    public static final RegistryObject<Block> PACKED_ICE_STAIRS = registerBlock("packed_ice_stairs", () -> new StairBlock(Blocks.PACKED_ICE::defaultBlockState, BlockBehaviour.Properties.copy(Blocks.PACKED_ICE)));
    public static final RegistryObject<Block> SAND_SLAB = registerBlock("sand_slab", () -> new SlabBlock(BlockBehaviour.Properties.copy(Blocks.SAND)));
    public static final RegistryObject<Block> SAND_STAIRS = registerBlock("sand_stairs", () -> new StairBlock(Blocks.SAND::defaultBlockState, BlockBehaviour.Properties.copy(Blocks.SAND)));


    //Selling Bins



    //Beer
    public static final RegistryObject<Block> KEG = registerBlock("keg", () -> new Keg(BlockBehaviour.Properties.copy(Blocks.BARREL).noOcclusion()));
    public static final RegistryObject<Block> HOP = registerBlockWithoutItem("hop", () -> new Hop(BlockBehaviour.Properties.copy(Blocks.CAVE_VINES).lightLevel(CaveVines.emission(0)).noOcclusion()));
    public static final RegistryObject<Block> HOP_PLANT = registerBlockWithoutItem("hop_plant", () -> new HopPlant(BlockBehaviour.Properties.copy(Blocks.CAVE_VINES).lightLevel(CaveVines.emission(0)).noOcclusion().noLootTable()));
    public static final RegistryObject<Block> MUG_EMPTY = registerBlockWithoutItem("mug_empty", () -> new MugBlock(BlockBehaviour.Properties.copy(Blocks.BARREL).noOcclusion()));
    public static final RegistryObject<Block> MUG_OF_CHORUS_ALE = registerBlockWithoutItem("mug_of_chorus_ale", () -> new ChorusAleMug(BlockBehaviour.Properties.copy(Blocks.BARREL).noOcclusion()));
    public static final RegistryObject<Block> MUG_OF_DIGGER_BITTER = registerBlockWithoutItem("mug_of_digger_bitter",() -> new DiggerBitterMug(BlockBehaviour.Properties.copy(Blocks.BARREL).noOcclusion()));
    public static final RegistryObject<Block> MUG_OF_DROWNED_ALE = registerBlockWithoutItem("mug_of_drowned_ale",() -> new DrownedAleMug(BlockBehaviour.Properties.copy(Blocks.BARREL).noOcclusion()));
    public static final RegistryObject<Block> MUG_OF_ICE_BEER = registerBlockWithoutItem("mug_of_ice_beer",() -> new IceBeerMug(BlockBehaviour.Properties.copy(Blocks.BARREL).noOcclusion()));
    public static final RegistryObject<Block> MUG_OF_KVASS = registerBlockWithoutItem("mug_of_kvass",() -> new KvassBeerMug(BlockBehaviour.Properties.copy(Blocks.BARREL).noOcclusion()));
    public static final RegistryObject<Block> MUG_OF_LEPRECHAUN_CIDER = registerBlockWithoutItem("mug_of_leprechaun_cider",() -> new LeprechaunCiderMug(BlockBehaviour.Properties.copy(Blocks.BARREL).noOcclusion()));
    public static final RegistryObject<Block> MUG_OF_MAGNET_PILSNER = registerBlockWithoutItem("mug_of_magnet_pilsner",() -> new MagnetPilsnerMug(BlockBehaviour.Properties.copy(Blocks.BARREL).noOcclusion()));
    public static final RegistryObject<Block> MUG_OF_NETHER_PORTER = registerBlockWithoutItem("mug_of_nether_porter",() -> new NetherPorterMug(BlockBehaviour.Properties.copy(Blocks.BARREL).noOcclusion()));
    public static final RegistryObject<Block> MUG_OF_NIGHT_RAUCH = registerBlockWithoutItem("mug_of_night_rauch", () -> new NightRauchMug(BlockBehaviour.Properties.copy(Blocks.BARREL).noOcclusion()));
    public static final RegistryObject<Block> MUG_OF_SUN_PALE_ALE = registerBlockWithoutItem("mug_of_sun_pale_ale",() -> new SunPaleAleMug(BlockBehaviour.Properties.copy(Blocks.BARREL).noOcclusion()));
    public static final RegistryObject<Block> MUG_OF_WITHER_STOUT = registerBlockWithoutItem("mug_of_wither_stout",() -> new WitherStoutMug(BlockBehaviour.Properties.copy(Blocks.BARREL).noOcclusion()));
    public static final RegistryObject<Block> MUG_OF_NIMBUS_NECTAR = registerBlockWithoutItem("mug_of_nimbus_nectar",() -> new NimbusNectarMug(BlockBehaviour.Properties.copy(Blocks.BARREL).noOcclusion()));
    public static final RegistryObject<Block> MUG_OF_STARDROP_SPARKLE = registerBlockWithoutItem("mug_of_stardrop_sparkle",() -> new StardropSparkleMug(BlockBehaviour.Properties.copy(Blocks.BARREL).noOcclusion()));

    //Avocado Woodset
   // public static final RegistryObject<Block> AVOCADO_LOG = registerBlock("avocado_log", () -> new ModFlammableRotatedPillarBlock(BlockBehaviour.Properties.copy(Blocks.OAK_LOG).noLootTable()));
   // public static final RegistryObject<Block> AVOCADO_WOOD = registerBlock("avocado_wood", () -> new ModFlammableRotatedPillarBlock(BlockBehaviour.Properties.copy(Blocks.OAK_WOOD).noLootTable()));
   // public static final RegistryObject<Block> STRIPPED_AVOCADO_LOG = registerBlock("stripped_avocado_log", () -> new ModFlammableRotatedPillarBlock(BlockBehaviour.Properties.copy(Blocks.STRIPPED_OAK_LOG).noLootTable()));
  //  public static final RegistryObject<Block> STRIPPED_AVOCADO_WOOD = registerBlock("stripped_avocado_wood", () -> new ModFlammableRotatedPillarBlock(BlockBehaviour.Properties.copy(Blocks.STRIPPED_OAK_WOOD).noLootTable()));
  //  public static final RegistryObject<Block> AVOCADO_PLANKS = registerBlock("avocado_planks", () -> new Block(BlockBehaviour.Properties.copy(Blocks.OAK_PLANKS)) {@Override public boolean isFlammable(BlockState state, BlockGetter level, BlockPos pos, Direction direction) {return true;}@Override public int getFlammability(BlockState state, BlockGetter level, BlockPos pos, Direction direction) {return 20;}@Override public int getFireSpreadSpeed(BlockState state, BlockGetter level, BlockPos pos, Direction direction) {return 5;}});
  //  public static final RegistryObject<Block> AVOCADO_LEAVES = registerBlock("avocado_leaves", () -> new LeavesBlock(BlockBehaviour.Properties.copy(Blocks.OAK_LEAVES).noLootTable()) {@Override public boolean isFlammable(BlockState state, BlockGetter level, BlockPos pos, Direction direction) {return true;}@Override public int getFlammability(BlockState state, BlockGetter level, BlockPos pos, Direction direction) {return 60;}@Override public int getFireSpreadSpeed(BlockState state, BlockGetter level, BlockPos pos, Direction direction) {return 30;}});
  //  public static final RegistryObject<Block> AVOCADO_SAPLING = registerBlock("avocado_sapling", () -> new SaplingBlock(new AvocadoTreeGrower(), BlockBehaviour.Properties.copy(Blocks.OAK_SAPLING)));

    //Marine Woodset
   //// public static final RegistryObject<Block> MARINE_LOG = registerBlock("marine_log", () -> new ModFlammableRotatedPillarBlock(BlockBehaviour.Properties.copy(Blocks.OAK_LOG)));
  //  public static final RegistryObject<Block> MARINE_WOOD = registerBlock("marine_wood", () -> new ModFlammableRotatedPillarBlock(BlockBehaviour.Properties.copy(Blocks.OAK_WOOD)));
   // public static final RegistryObject<Block> STRIPPED_MARINE_LOG = registerBlock("stripped_marine_log", () -> new ModFlammableRotatedPillarBlock(BlockBehaviour.Properties.copy(Blocks.STRIPPED_OAK_LOG)));
 //   public static final RegistryObject<Block> STRIPPED_MARINE_WOOD = registerBlock("stripped_marine_wood", () -> new ModFlammableRotatedPillarBlock(BlockBehaviour.Properties.copy(Blocks.STRIPPED_OAK_WOOD)));
  //  public static final RegistryObject<Block> MARINE_PLANKS = registerBlock("marine_planks", () -> new Block(BlockBehaviour.Properties.copy(Blocks.OAK_PLANKS)) {@Override public boolean isFlammable(BlockState state, BlockGetter level, BlockPos pos, Direction direction) {return true;}@Override public int getFlammability(BlockState state, BlockGetter level, BlockPos pos, Direction direction) {return 20;}@Override public int getFireSpreadSpeed(BlockState state, BlockGetter level, BlockPos pos, Direction direction) {return 5;}});
  //  public static final RegistryObject<Block> MARINE_LEAVES = registerBlock("marine_leaves", () -> new LeavesBlock(BlockBehaviour.Properties.copy(Blocks.OAK_LEAVES)) {@Override public boolean isFlammable(BlockState state, BlockGetter level, BlockPos pos, Direction direction) {return true;}@Override public int getFlammability(BlockState state, BlockGetter level, BlockPos pos, Direction direction) {return 60;}@Override public int getFireSpreadSpeed(BlockState state, BlockGetter level, BlockPos pos, Direction direction) {return 30;}});
  //  public static final RegistryObject<Block> MARINE_SAPLING = registerBlock("marine_sapling", () -> new SaplingBlock(null, BlockBehaviour.Properties.copy(Blocks.OAK_SAPLING)));

    //Grimm Stuff
    public static final RegistryObject<Block> GRIMM_SOIL = registerBlock("grimm_soil", () -> new GrimmSoilBlock(BlockBehaviour.Properties.of().mapColor(MapColor.TERRACOTTA_RED).instrument(NoteBlockInstrument.COW_BELL).requiresCorrectToolForDrops().strength(1.25F, 4.2F).sound(SoundType.SOUL_SOIL)));
    public static final RegistryObject<Block> GRIMM_FIRE = registerBlock("grimm_fire", () -> new GrimmFireBlock(BlockBehaviour.Properties.of().mapColor(MapColor.COLOR_RED).replaceable().noCollission().instabreak().lightLevel((p_152605_) -> 8).sound(SoundType.WOOL).pushReaction(PushReaction.DESTROY)));


    //Ore Blocks
    public static final RegistryObject<Block> OPAL_ORE = registerBlock("opal_ore", () -> new DropExperienceBlock(BlockBehaviour.Properties.of().mapColor(MapColor.STONE).instrument(NoteBlockInstrument.BASEDRUM).requiresCorrectToolForDrops().strength(3.0F, 3.0F), UniformInt.of(3, 7)));
    public static final RegistryObject<Block> DEEPSLATE_OPAL_ORE = registerBlock("deepslate_opal_ore", () -> new DropExperienceBlock(BlockBehaviour.Properties.of().mapColor(MapColor.DEEPSLATE).instrument(NoteBlockInstrument.BASEDRUM).requiresCorrectToolForDrops().strength(3.0F, 3.0F), UniformInt.of(3, 7)));
    public static final RegistryObject<Block> OPAL_BLOCK = registerBlock("opal_block", () -> new Block(BlockBehaviour.Properties.of().mapColor(MapColor.QUARTZ).requiresCorrectToolForDrops().strength(6.0F, 6.0F).sound(SoundType.METAL)));

    public static final RegistryObject<Block> AQUAMARINE_ORE = registerBlock("aquamarine_ore", () -> new DropExperienceBlock(BlockBehaviour.Properties.of().mapColor(MapColor.STONE).instrument(NoteBlockInstrument.BASEDRUM).requiresCorrectToolForDrops().strength(3.0F, 3.0F), UniformInt.of(3, 7)));
    public static final RegistryObject<Block> DEEPSLATE_AQUAMARINE_ORE = registerBlock("deepslate_aquamarine_ore", () -> new DropExperienceBlock(BlockBehaviour.Properties.of().mapColor(MapColor.DEEPSLATE).instrument(NoteBlockInstrument.BASEDRUM).requiresCorrectToolForDrops().strength(3.0F, 3.0F), UniformInt.of(3, 7)));
    public static final RegistryObject<Block> AQUAMARINE_BLOCK = registerBlock("aquamarine_block", () -> new Block(BlockBehaviour.Properties.of().mapColor(MapColor.DIAMOND).requiresCorrectToolForDrops().strength(8.0F, 6.0F).sound(SoundType.METAL)));


    public static RegistryObject<Block> registerWithRenderer(Supplier<Block> supplier, @Nonnull String name, @Nullable Item.Properties properties) {
        RegistryObject<Block> block = BLOCKS.register(name, supplier);

        if (properties == null) {
            ZAMItems.register(() -> new BlockItemWithoutLevelRenderer(block.get(), new Item.Properties()), name);
        } else {
            ZAMItems.registerWithTab(() -> new BlockItemWithoutLevelRenderer(block.get(), properties), name);
        }
        return block;
    }

   private static RegistryObject<Block> registerBlockAndItem(String name, Supplier<Block> block, int itemType) {
       RegistryObject<Block> blockObj = ZAMBlocks.BLOCKS.register(name, block);
      ZAMItems.ITEMS.register(name, getBlockSupplier(itemType, blockObj));
       return blockObj;
   }

  private static Supplier<? extends BlockItem> getBlockSupplier(int itemType, RegistryObject<Block> blockObj) {
     switch (itemType) {
          default:
             return () -> new BlockItem(blockObj.get(), new Item.Properties());
          case 1:
              return () -> new ScaffinityBlockItem(blockObj.get(), new Item.Properties());
      }
  }

    private static <T extends Block> RegistryObject<Block> registerBlockWithoutItem(String name, Supplier<T> block) {
        return BLOCKS.register(name, block);
    }

    private static <T extends Block> RegistryObject<T> registerBlock(String name, Supplier<T> block) {
        RegistryObject<T> toReturn = BLOCKS.register(name, block);
        registerBlockItem(name, toReturn);
        return toReturn;
    }

    private static <T extends Block> RegistryObject<Item> registerBlockItem(String name, RegistryObject<T> block) {
        return ZAMItems.ITEMS.register(name, () -> new BlockItem(block.get(), new Item.Properties()));
    }


    public static void register(IEventBus eventBus) {
        BLOCKS.register(eventBus);
    }
}
