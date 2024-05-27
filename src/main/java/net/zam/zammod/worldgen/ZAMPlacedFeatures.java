package net.zam.zammod.worldgen;

import net.minecraft.core.Holder;
import net.minecraft.core.HolderGetter;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstapContext;
import net.minecraft.data.worldgen.placement.PlacementUtils;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.levelgen.VerticalAnchor;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.placement.*;
import net.zam.zammod.ZAMMod;
import net.zam.zammod.registry.ZAMBlocks;

import java.util.List;

public class ZAMPlacedFeatures {
    public static final ResourceKey<PlacedFeature> EMERALD_GEODE_PLACED_KEY = registerKey("emerald_geode_placed");
    public static final ResourceKey<PlacedFeature> AVOCADO_TREE_PLACED_KEY = registerKey("avocado_tree_placed");
    public static void bootstrap(BootstapContext<PlacedFeature> context) {
        HolderGetter<ConfiguredFeature<?, ?>> configuredFeatures = context.lookup(Registries.CONFIGURED_FEATURE);

        register(context, EMERALD_GEODE_PLACED_KEY, configuredFeatures.getOrThrow(ZAMConfiguredFeatures.EMERALD_GEODE_KEY),
                List.of(RarityFilter.onAverageOnceEvery(24), InSquarePlacement.spread(),
                        HeightRangePlacement.uniform(VerticalAnchor.aboveBottom(6), VerticalAnchor.absolute(64)),
                        BiomeFilter.biome()), InSquarePlacement.spread(), SurfaceWaterDepthFilter.forMaxDepth(0), PlacementUtils.filteredByBlockSurvival(Blocks.OAK_SAPLING), BiomeFilter.biome());


    //    register(context, AVOCADO_TREE_PLACED_KEY, configuredFeatures.getOrThrow(ZAMConfiguredFeatures.AVOCADO_TREE_KEY),
  //             List.of(CountPlacement.of(1)), InSquarePlacement.spread(), SurfaceWaterDepthFilter.forMaxDepth(0),
   //             PlacementUtils.filteredByBlockSurvival(Blocks.OAK_SAPLING), BiomeFilter.biome());
   }

    private static ResourceKey<PlacedFeature> registerKey(String name) {
        return ResourceKey.create(Registries.PLACED_FEATURE, new ResourceLocation(ZAMMod.MOD_ID, name));
    }

    private static void register(BootstapContext<PlacedFeature> context, ResourceKey<PlacedFeature> key, Holder<ConfiguredFeature<?, ?>> configuration,
                                 List<PlacementModifier> modifiers, InSquarePlacement spread, SurfaceWaterDepthFilter surfaceWaterDepthFilter, BlockPredicateFilter blockPredicateFilter, BiomeFilter biome) {
        context.register(key, new PlacedFeature(configuration, List.copyOf(modifiers)));
    }
}