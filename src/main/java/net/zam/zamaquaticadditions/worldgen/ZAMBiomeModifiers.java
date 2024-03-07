package net.zam.zamaquaticadditions.worldgen;

import net.minecraft.core.HolderSet;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstapContext;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.levelgen.GenerationStep;
import net.minecraftforge.common.world.BiomeModifier;
import net.minecraftforge.common.world.ForgeBiomeModifiers;
import net.minecraftforge.registries.ForgeRegistries;
import net.zam.zamaquaticadditions.ZAMAquaticAdditions;
import net.zam.zamaquaticadditions.registry.ZAMTags;

public class ZAMBiomeModifiers {

    public static final ResourceKey<BiomeModifier> ADD_EMERALD_GEODE = registerKey("add_emerald_geode");


    public static void bootstrap(BootstapContext<BiomeModifier> context) {
        var placedFeatures = context.lookup(Registries.PLACED_FEATURE);
        var biomes = context.lookup(Registries.BIOME);

        context.register(ADD_EMERALD_GEODE, new ForgeBiomeModifiers.AddFeaturesBiomeModifier(biomes.getOrThrow(ZAMTags.Biomes.HAS_EMERALD_GEODE),
                HolderSet.direct(placedFeatures.getOrThrow(ZAMPlacedFeatures.EMERALD_GEODE_PLACED_KEY)), GenerationStep.Decoration.LOCAL_MODIFICATIONS));


    }
    private static ResourceKey<BiomeModifier> registerKey(String name) {
        return ResourceKey.create(ForgeRegistries.Keys.BIOME_MODIFIERS, new ResourceLocation(ZAMAquaticAdditions.MOD_ID, name));
    }
}
