package net.zam.zamaquaticadditions.datagen;

import net.minecraft.core.HolderLookup;
import net.minecraft.core.RegistrySetBuilder;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.PackOutput;
import net.minecraftforge.common.data.DatapackBuiltinEntriesProvider;
import net.minecraftforge.registries.ForgeRegistries;
import net.zam.zamaquaticadditions.ZAMAquaticAdditions;
import net.zam.zamaquaticadditions.worldgen.ZAMBiomeModifiers;
import net.zam.zamaquaticadditions.worldgen.ZAMConfiguredFeatures;
import net.zam.zamaquaticadditions.worldgen.ZAMPlacedFeatures;

import java.util.Set;
import java.util.concurrent.CompletableFuture;

public class ZAMWorldGenProvider extends DatapackBuiltinEntriesProvider {
    public static final RegistrySetBuilder BUILDER = new RegistrySetBuilder()
            .add(Registries.CONFIGURED_FEATURE, ZAMConfiguredFeatures::bootstrap)
            .add(Registries.PLACED_FEATURE, ZAMPlacedFeatures::bootstrap)
            .add(ForgeRegistries.Keys.BIOME_MODIFIERS, ZAMBiomeModifiers::bootstrap);


    public ZAMWorldGenProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> registries) {
        super(output, registries, BUILDER, Set.of(ZAMAquaticAdditions.MOD_ID));
    }
}

