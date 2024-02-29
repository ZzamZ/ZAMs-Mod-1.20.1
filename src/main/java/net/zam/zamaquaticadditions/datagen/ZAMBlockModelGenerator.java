package net.zam.zamaquaticadditions.datagen;

import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.SlabBlock;
import net.minecraft.world.level.block.StairBlock;
import net.minecraftforge.client.model.generators.BlockStateProvider;
import net.minecraftforge.client.model.generators.ModelFile;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import net.zam.zamaquaticadditions.ZAMAquaticAdditions;
import net.zam.zamaquaticadditions.registry.ZAMBlocks;

public class ZAMBlockModelGenerator extends BlockStateProvider {
    public ZAMBlockModelGenerator(PackOutput output, ExistingFileHelper exFileHelper) {
        super(output, ZAMAquaticAdditions.MOD_ID, exFileHelper);
    }

    @Override
    protected void registerStatesAndModels() {

        this.simpleBlock(ZAMBlocks.SUS.get());
        this.simpleBlock(ZAMBlocks.BLOCK_OF_BLUE_CHEESE.get());
        this.simpleBlock(ZAMBlocks.BLOCK_OF_NETHER_CHEESE.get());
        this.simpleBlock(ZAMBlocks.BLOCK_OF_CHEESE.get());
        this.simpleBlock(ZAMBlocks.CHEESE_CAULDRON.get(), this.models().withExistingParent("cauldron_cheese", new ResourceLocation(ZAMAquaticAdditions.MOD_ID, "block/template_cauldron_with_transforms"))
                .texture("bottom", new ResourceLocation("block/cauldron_bottom"))
                .texture("content", this.blockPrefix("block_of_cheese"))
                .texture("inside", new ResourceLocation("block/cauldron_inner"))
                .texture("particle", new ResourceLocation("block/cauldron_side"))
                .texture("side", new ResourceLocation("block/cauldron_side"))
                .texture("top", new ResourceLocation("block/cauldron_top")));
        this.simpleBlock(ZAMBlocks.BLUE_CHEESE_CAULDRON.get(), this.models().withExistingParent("cauldron_blue_cheese", new ResourceLocation(ZAMAquaticAdditions.MOD_ID, "block/template_cauldron_with_transforms"))
                .texture("bottom", new ResourceLocation("block/cauldron_bottom"))
                .texture("content", this.blockPrefix("block_of_blue_cheese"))
                .texture("inside", new ResourceLocation("block/cauldron_inner"))
                .texture("particle", new ResourceLocation("block/cauldron_side"))
                .texture("side", new ResourceLocation("block/cauldron_side"))
                .texture("top", new ResourceLocation("block/cauldron_top")));
        this.simpleBlock(ZAMBlocks.NETHER_CHEESE_CAULDRON.get(), this.models().withExistingParent("cauldron_nether_cheese", new ResourceLocation(ZAMAquaticAdditions.MOD_ID, "block/template_cauldron_with_transforms"))
                .texture("bottom", new ResourceLocation("block/cauldron_bottom"))
                .texture("content", this.blockPrefix("block_of_nether_cheese"))
                .texture("inside", new ResourceLocation("block/cauldron_inner"))
                .texture("particle", new ResourceLocation("block/cauldron_side"))
                .texture("side", new ResourceLocation("block/cauldron_side"))
                .texture("top", new ResourceLocation("block/cauldron_top")));
        this.simpleBlock(ZAMBlocks.MILK_CAULDRON.get(), this.models().withExistingParent("cauldron_milk", new ResourceLocation(ZAMAquaticAdditions.MOD_ID, "block/template_cauldron_with_transforms"))
                .texture("bottom", new ResourceLocation("block/cauldron_bottom"))
                .texture("content", new ResourceLocation("forge", "block/milk_still"))
                .texture("inside", new ResourceLocation("block/cauldron_inner"))
                .texture("particle", new ResourceLocation("block/cauldron_side"))
                .texture("side", new ResourceLocation("block/cauldron_side"))
                .texture("top", new ResourceLocation("block/cauldron_top")));
        this.simpleBlock(ZAMBlocks.FISH_BARREL.get(), this.models().cube("fish_barrel",
                        new ResourceLocation("block/barrel_bottom"), this.blockPrefix("fish_barrel_top"),
                        new ResourceLocation("block/barrel_side"), new ResourceLocation("block/barrel_side"),
                        new ResourceLocation("block/barrel_side"), new ResourceLocation("block/barrel_side"))
                .texture("particle", new ResourceLocation("block/barrel_side")));
        this.horizontalBlock(ZAMBlocks.JACK_O_RATERN.get(), this.models().orientable("jack_o_ratern",
                new ResourceLocation("block/pumpkin_side"),
                this.blockPrefix("jack_o_ratern"),
                new ResourceLocation("block/pumpkin_top")));

        stairsBlock((StairBlock) ZAMBlocks.PACKED_ICE_STAIRS.get(), blockTexture(Blocks.PACKED_ICE));
        slabBlock((SlabBlock) ZAMBlocks.PACKED_ICE_SLAB.get(), blockTexture(Blocks.PACKED_ICE), blockTexture(Blocks.PACKED_ICE));
        blockItem(ZAMBlocks.PACKED_ICE_SLAB);
        blockItem(ZAMBlocks.PACKED_ICE_STAIRS);

        stairsBlock((StairBlock) ZAMBlocks.WHITE_CONCRETE_STAIRS.get(), blockTexture(Blocks.WHITE_CONCRETE));
        slabBlock((SlabBlock) ZAMBlocks.WHITE_CONCRETE_SLAB.get(), blockTexture(Blocks.WHITE_CONCRETE), blockTexture(Blocks.WHITE_CONCRETE));
        stairsBlock((StairBlock) ZAMBlocks.LIGHT_GRAY_CONCRETE_STAIRS.get(), blockTexture(Blocks.LIGHT_GRAY_CONCRETE));
        slabBlock((SlabBlock) ZAMBlocks.LIGHT_GRAY_CONCRETE_SLAB.get(), blockTexture(Blocks.LIGHT_GRAY_CONCRETE), blockTexture(Blocks.LIGHT_GRAY_CONCRETE));
        stairsBlock((StairBlock) ZAMBlocks.GRAY_CONCRETE_STAIRS.get(), blockTexture(Blocks.GRAY_CONCRETE));
        slabBlock((SlabBlock) ZAMBlocks.GRAY_CONCRETE_SLAB.get(), blockTexture(Blocks.GRAY_CONCRETE), blockTexture(Blocks.GRAY_CONCRETE));
        stairsBlock((StairBlock) ZAMBlocks.BLACK_CONCRETE_STAIRS.get(), blockTexture(Blocks.BLACK_CONCRETE));
        slabBlock((SlabBlock) ZAMBlocks.BLACK_CONCRETE_SLAB.get(), blockTexture(Blocks.BLACK_CONCRETE), blockTexture(Blocks.BLACK_CONCRETE));
        stairsBlock((StairBlock) ZAMBlocks.BROWN_CONCRETE_STAIRS.get(), blockTexture(Blocks.BROWN_CONCRETE));
        slabBlock((SlabBlock) ZAMBlocks.BROWN_CONCRETE_SLAB.get(), blockTexture(Blocks.BROWN_CONCRETE), blockTexture(Blocks.BROWN_CONCRETE));
        stairsBlock((StairBlock) ZAMBlocks.RED_CONCRETE_STAIRS.get(), blockTexture(Blocks.RED_CONCRETE));
        slabBlock((SlabBlock) ZAMBlocks.RED_CONCRETE_SLAB.get(), blockTexture(Blocks.RED_CONCRETE), blockTexture(Blocks.RED_CONCRETE));
        stairsBlock((StairBlock) ZAMBlocks.ORANGE_CONCRETE_STAIRS.get(), blockTexture(Blocks.ORANGE_CONCRETE));
        slabBlock((SlabBlock) ZAMBlocks.ORANGE_CONCRETE_SLAB.get(), blockTexture(Blocks.ORANGE_CONCRETE), blockTexture(Blocks.ORANGE_CONCRETE));
        stairsBlock((StairBlock) ZAMBlocks.YELLOW_CONCRETE_STAIRS.get(), blockTexture(Blocks.YELLOW_CONCRETE));
        slabBlock((SlabBlock) ZAMBlocks.YELLOW_CONCRETE_SLAB.get(), blockTexture(Blocks.YELLOW_CONCRETE), blockTexture(Blocks.YELLOW_CONCRETE));
        stairsBlock((StairBlock) ZAMBlocks.LIME_CONCRETE_STAIRS.get(), blockTexture(Blocks.LIME_CONCRETE));
        slabBlock((SlabBlock) ZAMBlocks.LIME_CONCRETE_SLAB.get(), blockTexture(Blocks.LIME_CONCRETE), blockTexture(Blocks.LIME_CONCRETE));
        stairsBlock((StairBlock) ZAMBlocks.GREEN_CONCRETE_STAIRS.get(), blockTexture(Blocks.GREEN_CONCRETE));
        slabBlock((SlabBlock) ZAMBlocks.GREEN_CONCRETE_SLAB.get(), blockTexture(Blocks.GREEN_CONCRETE), blockTexture(Blocks.GREEN_CONCRETE));
        stairsBlock((StairBlock) ZAMBlocks.CYAN_CONCRETE_STAIRS.get(), blockTexture(Blocks.CYAN_CONCRETE));
        slabBlock((SlabBlock) ZAMBlocks.CYAN_CONCRETE_SLAB.get(), blockTexture(Blocks.CYAN_CONCRETE), blockTexture(Blocks.CYAN_CONCRETE));
        stairsBlock((StairBlock) ZAMBlocks.LIGHT_BLUE_CONCRETE_STAIRS.get(), blockTexture(Blocks.LIGHT_BLUE_CONCRETE));
        slabBlock((SlabBlock) ZAMBlocks.LIGHT_BLUE_CONCRETE_SLAB.get(), blockTexture(Blocks.LIGHT_BLUE_CONCRETE), blockTexture(Blocks.LIGHT_BLUE_CONCRETE));
        stairsBlock((StairBlock) ZAMBlocks.BLUE_CONCRETE_STAIRS.get(), blockTexture(Blocks.BLUE_CONCRETE));
        slabBlock((SlabBlock) ZAMBlocks.BLUE_CONCRETE_SLAB.get(), blockTexture(Blocks.BLUE_CONCRETE), blockTexture(Blocks.BLUE_CONCRETE));
        stairsBlock((StairBlock) ZAMBlocks.PURPLE_CONCRETE_STAIRS.get(), blockTexture(Blocks.PURPLE_CONCRETE));
        slabBlock((SlabBlock) ZAMBlocks.PURPLE_CONCRETE_SLAB.get(), blockTexture(Blocks.PURPLE_CONCRETE), blockTexture(Blocks.PURPLE_CONCRETE));
        stairsBlock((StairBlock) ZAMBlocks.MAGENTA_CONCRETE_STAIRS.get(), blockTexture(Blocks.MAGENTA_CONCRETE));
        slabBlock((SlabBlock) ZAMBlocks.MAGENTA_CONCRETE_SLAB.get(), blockTexture(Blocks.MAGENTA_CONCRETE), blockTexture(Blocks.MAGENTA_CONCRETE));
        stairsBlock((StairBlock) ZAMBlocks.PINK_CONCRETE_STAIRS.get(), blockTexture(Blocks.PINK_CONCRETE));
        slabBlock((SlabBlock) ZAMBlocks.PINK_CONCRETE_SLAB.get(), blockTexture(Blocks.PINK_CONCRETE), blockTexture(Blocks.PINK_CONCRETE));
        blockItem(ZAMBlocks.WHITE_CONCRETE_SLAB);
        blockItem(ZAMBlocks.WHITE_CONCRETE_STAIRS);
        blockItem(ZAMBlocks.LIGHT_GRAY_CONCRETE_SLAB);
        blockItem(ZAMBlocks.LIGHT_GRAY_CONCRETE_STAIRS);
        blockItem(ZAMBlocks.GRAY_CONCRETE_SLAB);
        blockItem(ZAMBlocks.GRAY_CONCRETE_STAIRS);
        blockItem(ZAMBlocks.BLACK_CONCRETE_SLAB);
        blockItem(ZAMBlocks.BLACK_CONCRETE_STAIRS);
        blockItem(ZAMBlocks.BROWN_CONCRETE_SLAB);
        blockItem(ZAMBlocks.BROWN_CONCRETE_STAIRS);
        blockItem(ZAMBlocks.RED_CONCRETE_SLAB);
        blockItem(ZAMBlocks.RED_CONCRETE_STAIRS);
        blockItem(ZAMBlocks.ORANGE_CONCRETE_SLAB);
        blockItem(ZAMBlocks.ORANGE_CONCRETE_STAIRS);
        blockItem(ZAMBlocks.YELLOW_CONCRETE_SLAB);
        blockItem(ZAMBlocks.YELLOW_CONCRETE_STAIRS);
        blockItem(ZAMBlocks.LIME_CONCRETE_SLAB);
        blockItem(ZAMBlocks.LIME_CONCRETE_STAIRS);
        blockItem(ZAMBlocks.GREEN_CONCRETE_SLAB);
        blockItem(ZAMBlocks.GREEN_CONCRETE_STAIRS);
        blockItem(ZAMBlocks.CYAN_CONCRETE_SLAB);
        blockItem(ZAMBlocks.CYAN_CONCRETE_STAIRS);
        blockItem(ZAMBlocks.LIGHT_BLUE_CONCRETE_SLAB);
        blockItem(ZAMBlocks.LIGHT_BLUE_CONCRETE_STAIRS);
        blockItem(ZAMBlocks.BLUE_CONCRETE_SLAB);
        blockItem(ZAMBlocks.BLUE_CONCRETE_STAIRS);
        blockItem(ZAMBlocks.PURPLE_CONCRETE_SLAB);
        blockItem(ZAMBlocks.PURPLE_CONCRETE_STAIRS);
        blockItem(ZAMBlocks.MAGENTA_CONCRETE_STAIRS);
        blockItem(ZAMBlocks.MAGENTA_CONCRETE_SLAB);
        blockItem(ZAMBlocks.PINK_CONCRETE_SLAB);
        blockItem(ZAMBlocks.PINK_CONCRETE_STAIRS);
        
        
        
        
    }

    private void blockItem(RegistryObject<Block> blockRegistryObject) {
        simpleBlockItem(blockRegistryObject.get(), new ModelFile.UncheckedModelFile("zamaquaticadditions:block/" + ForgeRegistries.BLOCKS.getKey(blockRegistryObject.get()).getPath()));
    }

    private ResourceLocation blockPrefix(String name) {
        return new ResourceLocation(ZAMAquaticAdditions.MOD_ID, "block/" + name);
    }

}
