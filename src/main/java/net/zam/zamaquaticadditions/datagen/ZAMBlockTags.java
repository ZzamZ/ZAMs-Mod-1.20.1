package net.zam.zamaquaticadditions.datagen;

import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraftforge.common.data.BlockTagsProvider;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.zam.zamaquaticadditions.ZAMAquaticAdditions;
import org.jetbrains.annotations.Nullable;

import java.util.concurrent.CompletableFuture;

public class ZAMBlockTags extends BlockTagsProvider {
    public ZAMBlockTags(PackOutput output, CompletableFuture<HolderLookup.Provider> provider, @Nullable ExistingFileHelper helper) {
        super(output, provider, ZAMAquaticAdditions.MOD_ID, helper);
    }

    @Override
    protected void addTags(HolderLookup.Provider pProvider) {

    }
}
