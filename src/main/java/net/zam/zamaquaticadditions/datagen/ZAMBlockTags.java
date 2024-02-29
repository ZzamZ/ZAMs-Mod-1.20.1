package net.zam.zamaquaticadditions.datagen;

import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.tags.BlockTags;
import net.minecraftforge.common.data.BlockTagsProvider;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.zam.zamaquaticadditions.ZAMAquaticAdditions;
import net.zam.zamaquaticadditions.registry.ZAMBlocks;
import org.jetbrains.annotations.Nullable;

import java.util.concurrent.CompletableFuture;

public class ZAMBlockTags extends BlockTagsProvider {
    public ZAMBlockTags(PackOutput output, CompletableFuture<HolderLookup.Provider> lookupProvider, @Nullable ExistingFileHelper existingFileHelper) {
        super(output, lookupProvider, ZAMAquaticAdditions.MOD_ID, existingFileHelper);
    }

    @Override
    protected void addTags(HolderLookup.Provider pProvider) {

        this.tag(BlockTags.CLIMBABLE)
                .add(ZAMBlocks.SCAFFINITY.get());


        this.tag(BlockTags.MINEABLE_WITH_PICKAXE).add(
                ZAMBlocks.WHITE_CONCRETE_SLAB.get(),
                ZAMBlocks.LIGHT_GRAY_CONCRETE_SLAB.get(),
                ZAMBlocks.GRAY_CONCRETE_SLAB.get(),
                ZAMBlocks.BLACK_CONCRETE_SLAB.get(),
                ZAMBlocks.BROWN_CONCRETE_SLAB.get(),
                ZAMBlocks.RED_CONCRETE_SLAB.get(),
                ZAMBlocks.ORANGE_CONCRETE_SLAB.get(),
                ZAMBlocks.YELLOW_CONCRETE_SLAB.get(),
                ZAMBlocks.LIME_CONCRETE_SLAB.get(),
                ZAMBlocks.GREEN_CONCRETE_SLAB.get(),
                ZAMBlocks.CYAN_CONCRETE_SLAB.get(),
                ZAMBlocks.LIGHT_BLUE_CONCRETE_SLAB.get(),
                ZAMBlocks.BLUE_CONCRETE_SLAB.get(),
                ZAMBlocks.PURPLE_CONCRETE_SLAB.get(),
                ZAMBlocks.MAGENTA_CONCRETE_SLAB.get(),
                ZAMBlocks.PINK_CONCRETE_SLAB.get(),

                ZAMBlocks.WHITE_CONCRETE_STAIRS.get(),
                ZAMBlocks.LIGHT_GRAY_CONCRETE_STAIRS.get(),
                ZAMBlocks.GRAY_CONCRETE_STAIRS.get(),
                ZAMBlocks.BLACK_CONCRETE_STAIRS.get(),
                ZAMBlocks.BROWN_CONCRETE_STAIRS.get(),
                ZAMBlocks.RED_CONCRETE_STAIRS.get(),
                ZAMBlocks.ORANGE_CONCRETE_STAIRS.get(),
                ZAMBlocks.YELLOW_CONCRETE_STAIRS.get(),
                ZAMBlocks.LIME_CONCRETE_STAIRS.get(),
                ZAMBlocks.GREEN_CONCRETE_STAIRS.get(),
                ZAMBlocks.CYAN_CONCRETE_STAIRS.get(),
                ZAMBlocks.LIGHT_BLUE_CONCRETE_STAIRS.get(),
                ZAMBlocks.BLUE_CONCRETE_STAIRS.get(),
                ZAMBlocks.PURPLE_CONCRETE_STAIRS.get(),
                ZAMBlocks.MAGENTA_CONCRETE_STAIRS.get(),
                ZAMBlocks.PINK_CONCRETE_STAIRS.get(),

                ZAMBlocks.PACKED_ICE_SLAB.get(),
                ZAMBlocks.PACKED_ICE_STAIRS.get());
    }
}
