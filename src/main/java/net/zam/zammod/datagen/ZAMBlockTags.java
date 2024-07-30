package net.zam.zammod.datagen;

import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.tags.BlockTags;
import net.minecraftforge.common.data.BlockTagsProvider;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.zam.zammod.ZAMMod;
import net.zam.zammod.registry.ZAMBlocks;
import org.jetbrains.annotations.Nullable;

import java.util.concurrent.CompletableFuture;

public class ZAMBlockTags extends BlockTagsProvider {
    public ZAMBlockTags(PackOutput output, CompletableFuture<HolderLookup.Provider> lookupProvider, @Nullable ExistingFileHelper existingFileHelper) {
        super(output, lookupProvider, ZAMMod.MOD_ID, existingFileHelper);
    }

    @Override
    protected void addTags(HolderLookup.Provider pProvider) {


        this.tag(BlockTags.CLIMBABLE)
                .add(ZAMBlocks.SCAFFINITY.get());

        this.tag(BlockTags.MINEABLE_WITH_SHOVEL)
                .add(ZAMBlocks.GRIMM_SOIL.get())
                .add(ZAMBlocks.SAND_STAIRS.get())
                .add(ZAMBlocks.SAND_SLAB.get());

        this.tag(BlockTags.MINEABLE_WITH_AXE).add(
                ZAMBlocks.KEG.get(),
                ZAMBlocks.MUG_EMPTY.get(),
                ZAMBlocks.MUG_OF_SUN_PALE_ALE.get(),
                ZAMBlocks.MUG_OF_DIGGER_BITTER.get(),
                ZAMBlocks.MUG_OF_WITHER_STOUT.get(),
                ZAMBlocks.MUG_OF_MAGNET_PILSNER.get(),
                ZAMBlocks.MUG_OF_DROWNED_ALE.get(),
                ZAMBlocks.MUG_OF_NIGHT_RAUCH.get(),
                ZAMBlocks.MUG_OF_ICE_BEER.get(),
                ZAMBlocks.MUG_OF_KVASS.get(),
                ZAMBlocks.MUG_OF_LEPRECHAUN_CIDER.get(),
                ZAMBlocks.MUG_OF_CHORUS_ALE.get(),
                ZAMBlocks.MUG_OF_NETHER_PORTER.get(),
                ZAMBlocks.MUG_OF_NIMBUS_NECTAR.get(),
                ZAMBlocks.MUG_OF_STARDROP_SPARKLE.get());



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

                ZAMBlocks.LOST_BOUNTY.get(),


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

                ZAMBlocks.OPAL_ORE.get(),
                ZAMBlocks.DEEPSLATE_OPAL_ORE.get(),
                ZAMBlocks.OPAL_BLOCK.get(),
                ZAMBlocks.AQUAMARINE_ORE.get(),
                ZAMBlocks.DEEPSLATE_AQUAMARINE_ORE.get(),
                ZAMBlocks.AQUAMARINE_BLOCK.get(),


                ZAMBlocks.PACKED_ICE_SLAB.get(),
                ZAMBlocks.PACKED_ICE_STAIRS.get());


                this.tag(BlockTags.FIRE).add(
                        ZAMBlocks.GRIMM_FIRE.get());

                this.tag(BlockTags.NEEDS_IRON_TOOL).add(
                        ZAMBlocks.OPAL_ORE.get(),
                        ZAMBlocks.DEEPSLATE_OPAL_ORE.get(),
                        ZAMBlocks.AQUAMARINE_ORE.get(),
                        ZAMBlocks.DEEPSLATE_AQUAMARINE_ORE.get());


    }
}
