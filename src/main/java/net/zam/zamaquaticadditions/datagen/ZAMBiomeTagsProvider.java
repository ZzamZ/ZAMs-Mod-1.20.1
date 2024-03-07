package net.zam.zamaquaticadditions.datagen;

import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.tags.BiomeTagsProvider;
import net.minecraft.tags.BiomeTags;
import net.minecraft.world.level.biome.Biomes;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.zam.zamaquaticadditions.ZAMAquaticAdditions;
import net.zam.zamaquaticadditions.registry.ZAMTags;

import java.util.concurrent.CompletableFuture;

public class ZAMBiomeTagsProvider extends BiomeTagsProvider {


    public ZAMBiomeTagsProvider(PackOutput pOutput, CompletableFuture<HolderLookup.Provider> pProvider, ExistingFileHelper existingFileHelper) {
        super(pOutput, pProvider, ZAMAquaticAdditions.MOD_ID, existingFileHelper);
    }

    @Override
    protected void addTags(HolderLookup.Provider pProvider) {
        this.tag(ZAMTags.Biomes.HAS_EMERALD_GEODE).addTag(BiomeTags.IS_MOUNTAIN).add(Biomes.GROVE);
    }
}

