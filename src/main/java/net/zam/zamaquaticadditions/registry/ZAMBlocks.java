package net.zam.zamaquaticadditions.registry;

import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.MapColor;
import net.minecraft.world.level.material.PushReaction;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import net.zam.zamaquaticadditions.ZAMAquaticAdditions;
import net.zam.zamaquaticadditions.block.BuddingEmeraldBlock;
import net.zam.zamaquaticadditions.block.GymSignBlock;
import net.zam.zamaquaticadditions.block.ScaffinityBlock;
import net.zam.zamaquaticadditions.block.ScaffinityBlockItem;
import net.zam.zamaquaticadditions.block.chest.LostBounty;
import net.zam.zamaquaticadditions.block.milk.CheeseCauldronBlock;
import net.zam.zamaquaticadditions.block.milk.MilkCauldronBlock;
import net.zam.zamaquaticadditions.block.milk.SetupHorizontalBlock;
import net.zam.zamaquaticadditions.item.renderer.BlockItemWithoutLevelRenderer;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.function.Supplier;

public class ZAMBlocks {
    public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, ZAMAquaticAdditions.MOD_ID);

    //Ocean
    public static final RegistryObject<Block> LOST_BOUNTY = registerWithRenderer(LostBounty::new, "lost_bounty", new Item.Properties());
    public static final RegistryObject<Block> SCAFFINITY = registerBlockAndItem("scaffinity", () -> new ScaffinityBlock(BlockBehaviour.Properties.copy(Blocks.SCAFFOLDING).mapColor(MapColor.COLOR_LIGHT_BLUE)), 1);

    //Emerald Geode
    public static final RegistryObject<Block> EMERALD_CRYSTAL_BLOCK = registerBlock("emerald_crystal_block", () -> new AmethystBlock(BlockBehaviour.Properties.copy(Blocks.AMETHYST_BLOCK).mapColor(MapColor.EMERALD).randomTicks().strength(1.5F).sound(SoundType.AMETHYST).requiresCorrectToolForDrops().pushReaction(PushReaction.DESTROY)));
    public static final RegistryObject<Block> BUDDING_EMERALD = registerBlock("budding_emerald", () -> new BuddingEmeraldBlock(BlockBehaviour.Properties.of().mapColor(MapColor.EMERALD).randomTicks().strength(1.5F).sound(SoundType.AMETHYST).requiresCorrectToolForDrops().pushReaction(PushReaction.DESTROY)));
    public static final RegistryObject<Block> EMERALD_CLUSTER = registerBlock("emerald_cluster", () -> new AmethystClusterBlock(7, 3, BlockBehaviour.Properties.of().mapColor(MapColor.EMERALD).forceSolidOn().noOcclusion().randomTicks().sound(SoundType.AMETHYST_CLUSTER).strength(1.5F).pushReaction(PushReaction.DESTROY).lightLevel((p_152632_) -> 8)));
    public static final RegistryObject<AmethystClusterBlock> LARGE_EMERALD_BUD = registerBlock("large_emerald_bud", () -> new AmethystClusterBlock(5, 3, BlockBehaviour.Properties.copy(ZAMBlocks.EMERALD_CLUSTER.get()).sound(SoundType.MEDIUM_AMETHYST_BUD).forceSolidOn().pushReaction(PushReaction.DESTROY).lightLevel((p_152629_) -> 4)));
    public static final RegistryObject<AmethystClusterBlock> MEDIUM_EMERALD_BUD = registerBlock("medium_emerald_bud", () -> new AmethystClusterBlock(4, 3, BlockBehaviour.Properties.copy(ZAMBlocks.EMERALD_CLUSTER.get()).sound(SoundType.LARGE_AMETHYST_BUD).forceSolidOn().pushReaction(PushReaction.DESTROY).lightLevel((p_152617_) -> 2)));
    public static final RegistryObject<AmethystClusterBlock> SMALL_EMERALD_BUD = registerBlock("small_emerald_bud", () -> new AmethystClusterBlock(3, 4, BlockBehaviour.Properties.copy(ZAMBlocks.EMERALD_CLUSTER.get()).sound(SoundType.SMALL_AMETHYST_BUD).forceSolidOn().pushReaction(PushReaction.DESTROY)));


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
    public static final RegistryObject<Block> DIRT_SLAB = registerBlock("dirt_slab", () -> new SlabBlock(BlockBehaviour.Properties.copy(Blocks.DIRT)));
    public static final RegistryObject<Block> DIRT_STAIRS = registerBlock("dirt_stairs", () -> new StairBlock(Blocks.DIRT::defaultBlockState, BlockBehaviour.Properties.copy(Blocks.DIRT)));
    public static final RegistryObject<Block> GRASS_SLAB = registerBlock("grass_slab", () -> new SlabBlock(BlockBehaviour.Properties.copy(Blocks.GRASS_BLOCK)));
    public static final RegistryObject<Block> GRASS_STAIRS = registerBlock("grass_stairs", () -> new StairBlock(Blocks.GRASS_BLOCK::defaultBlockState, BlockBehaviour.Properties.copy(Blocks.GRASS_BLOCK)));
    public static final RegistryObject<Block> COARSE_DIRT_SLAB = registerBlock("coarse_dirt_slab", () -> new SlabBlock(BlockBehaviour.Properties.copy(Blocks.COARSE_DIRT)));
    public static final RegistryObject<Block> COARSE_DIRT_STAIRS = registerBlock("coarse_dirt_stairs", () -> new StairBlock(Blocks.COARSE_DIRT::defaultBlockState, BlockBehaviour.Properties.copy(Blocks.COARSE_DIRT)));
    public static final RegistryObject<Block> SAND_SLAB = registerBlock("sand_slab", () -> new SlabBlock(BlockBehaviour.Properties.copy(Blocks.SAND)));
    public static final RegistryObject<Block> SAND_STAIRS = registerBlock("sand_stairs", () -> new StairBlock(Blocks.SAND::defaultBlockState, BlockBehaviour.Properties.copy(Blocks.SAND)));



    //Gym Signs
    public static RegistryObject<Block> GYM_SIGN1 = registerBlock("gym_sign1", () -> new GymSignBlock(BlockBehaviour.Properties.copy(Blocks.IRON_BLOCK).strength(5.0f).requiresCorrectToolForDrops().noOcclusion()));
    public static RegistryObject<Block> GYM_SIGN2 = registerBlock("gym_sign2", () -> new GymSignBlock(BlockBehaviour.Properties.copy(Blocks.IRON_BLOCK).strength(5.0f).requiresCorrectToolForDrops().noOcclusion()));
    public static RegistryObject<Block> GYM_SIGN3 = registerBlock("gym_sign3", () -> new GymSignBlock(BlockBehaviour.Properties.copy(Blocks.IRON_BLOCK).strength(5.0f).requiresCorrectToolForDrops().noOcclusion()));
    public static RegistryObject<Block> GYM_SIGN4 = registerBlock("gym_sign4", () -> new GymSignBlock(BlockBehaviour.Properties.copy(Blocks.IRON_BLOCK).strength(5.0f).requiresCorrectToolForDrops().noOcclusion()));
    public static RegistryObject<Block> GYM_SIGN5 = registerBlock("gym_sign5", () -> new GymSignBlock(BlockBehaviour.Properties.copy(Blocks.IRON_BLOCK).strength(5.0f).requiresCorrectToolForDrops().noOcclusion()));
    public static RegistryObject<Block> GYM_SIGN6 = registerBlock("gym_sign6", () -> new GymSignBlock(BlockBehaviour.Properties.copy(Blocks.IRON_BLOCK).strength(5.0f).requiresCorrectToolForDrops().noOcclusion()));
    public static RegistryObject<Block> GYM_SIGN7 = registerBlock("gym_sign7", () -> new GymSignBlock(BlockBehaviour.Properties.copy(Blocks.IRON_BLOCK).strength(5.0f).requiresCorrectToolForDrops().noOcclusion()));
    public static RegistryObject<Block> GYM_SIGN8 = registerBlock("gym_sign8", () -> new GymSignBlock(BlockBehaviour.Properties.copy(Blocks.IRON_BLOCK).strength(5.0f).requiresCorrectToolForDrops().noOcclusion()));
    public static RegistryObject<Block> GYM_SIGN9 = registerBlock("gym_sign9", () -> new GymSignBlock(BlockBehaviour.Properties.copy(Blocks.IRON_BLOCK).strength(5.0f).requiresCorrectToolForDrops().noOcclusion()));
    public static RegistryObject<Block> GYM_SIGN10 = registerBlock("gym_sign10", () -> new GymSignBlock(BlockBehaviour.Properties.copy(Blocks.IRON_BLOCK).strength(5.0f).requiresCorrectToolForDrops().noOcclusion()));
    public static RegistryObject<Block> GYM_SIGN11 = registerBlock("gym_sign11", () -> new GymSignBlock(BlockBehaviour.Properties.copy(Blocks.IRON_BLOCK).strength(5.0f).requiresCorrectToolForDrops().noOcclusion()));
    public static RegistryObject<Block> GYM_SIGN12 = registerBlock("gym_sign12", () -> new GymSignBlock(BlockBehaviour.Properties.copy(Blocks.IRON_BLOCK).strength(5.0f).requiresCorrectToolForDrops().noOcclusion()));
    public static RegistryObject<Block> GYM_SIGN13 = registerBlock("gym_sign13", () -> new GymSignBlock(BlockBehaviour.Properties.copy(Blocks.IRON_BLOCK).strength(5.0f).requiresCorrectToolForDrops().noOcclusion()));
    public static RegistryObject<Block> GYM_SIGN14 = registerBlock("gym_sign14", () -> new GymSignBlock(BlockBehaviour.Properties.copy(Blocks.IRON_BLOCK).strength(5.0f).requiresCorrectToolForDrops().noOcclusion()));
    public static RegistryObject<Block> GYM_SIGN15 = registerBlock("gym_sign15", () -> new GymSignBlock(BlockBehaviour.Properties.copy(Blocks.IRON_BLOCK).strength(5.0f).requiresCorrectToolForDrops().noOcclusion()));
    public static RegistryObject<Block> GYM_SIGN16 = registerBlock("gym_sign16", () -> new GymSignBlock(BlockBehaviour.Properties.copy(Blocks.IRON_BLOCK).strength(5.0f).requiresCorrectToolForDrops().noOcclusion()));


    //Cheese
    public static final RegistryObject<Block> BLOCK_OF_CHEESE = registerBlock("block_of_cheese", () -> new Block(Block.Properties.of().mapColor(MapColor.COLOR_YELLOW).sound(SoundType.SLIME_BLOCK).strength(0.6F, 0.0F)));
    public static final RegistryObject<Block> MILK_CAULDRON = registerBlock("cauldron_milk", () -> new MilkCauldronBlock(Block.Properties.copy(Blocks.CAULDRON)));
    public static final RegistryObject<Block> CHEESE_CAULDRON = registerBlock("cauldron_cheese", () -> new CheeseCauldronBlock(Block.Properties.copy(Blocks.CAULDRON), ZAMBlocks.BLOCK_OF_CHEESE, ZAMCauldronRegistry.CHEESE));
    public static final RegistryObject<Block> BLUE_CHEESE_CAULDRON = registerBlock("cauldron_blue_cheese", () -> new CheeseCauldronBlock(Block.Properties.copy(Blocks.CAULDRON), ZAMBlocks.BLOCK_OF_BLUE_CHEESE, ZAMCauldronRegistry.BLUE_CHEESE));
    public static final RegistryObject<Block> NETHER_CHEESE_CAULDRON = registerBlock("cauldron_nether_cheese", () -> new CheeseCauldronBlock(Block.Properties.copy(Blocks.CAULDRON), ZAMBlocks.BLOCK_OF_NETHER_CHEESE, ZAMCauldronRegistry.NETHER_CHEESE));
    public static final RegistryObject<Block> BLOCK_OF_BLUE_CHEESE = registerBlock("block_of_blue_cheese", () -> new Block(Block.Properties.of().strength(0.6F, 0.0F).sound(SoundType.SLIME_BLOCK)));
    public static final RegistryObject<Block> BLOCK_OF_NETHER_CHEESE = registerBlock("block_of_nether_cheese", () -> new Block(Block.Properties.of().strength(0.6F, 0.0F).sound(SoundType.SLIME_BLOCK)));
    public static final RegistryObject<Block> JACK_O_RATERN = registerBlock("jack_o_ratern", () -> new SetupHorizontalBlock(Block.Properties.of().sound(SoundType.WOOD).strength(1.0F, 0).lightLevel(value -> 15)));
    public static final RegistryObject<Block> FISH_BARREL = registerBlock("fish_barrel", () -> new Block(BlockBehaviour.Properties.of().strength(2.0F, 10.0F).sound(SoundType.WOOD)));


    public static final RegistryObject<Block> SUS = registerBlock("sus", () -> new RotatedPillarBlock(BlockBehaviour.Properties.copy(Blocks.SLIME_BLOCK).strength(0.3f).sound(SoundType.SLIME_BLOCK)));


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
