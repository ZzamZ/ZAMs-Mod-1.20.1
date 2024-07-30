package net.zam.zammod.worldgen;

import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstapContext;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BlockTags;
import net.minecraft.util.valueproviders.ConstantInt;
import net.minecraft.util.valueproviders.UniformInt;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.levelgen.GeodeBlockSettings;
import net.minecraft.world.level.levelgen.GeodeCrackSettings;
import net.minecraft.world.level.levelgen.GeodeLayerSettings;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.configurations.FeatureConfiguration;
import net.minecraft.world.level.levelgen.feature.configurations.GeodeConfiguration;
import net.minecraft.world.level.levelgen.feature.configurations.TreeConfiguration;
import net.minecraft.world.level.levelgen.feature.featuresize.TwoLayersFeatureSize;
import net.minecraft.world.level.levelgen.feature.stateproviders.BlockStateProvider;
import net.minecraft.world.level.levelgen.feature.trunkplacers.StraightTrunkPlacer;
import net.zam.zammod.ZAMMod;
import net.zam.zammod.registry.ZAMBlocks;
import net.zam.zammod.worldgen.foliageplacers.AvocadoFoliagePlacer;
import net.zam.zammod.worldgen.trunkplacers.CrossTrunkPlacer;

import java.util.List;

public class ZAMConfiguredFeatures {
    public static final ResourceKey<ConfiguredFeature<?, ?>> EMERALD_GEODE_KEY = registerKey("emerald_geode");
    public static final ResourceKey<ConfiguredFeature<?, ?>> AVOCADO_TREE_KEY = registerKey("avocado_tree");

   public static void bootstrap(BootstapContext<ConfiguredFeature<?, ?>> context) {
       //      register(context, AVOCADO_TREE_KEY, Feature.TREE,
       //              new TreeConfiguration.TreeConfigurationBuilder(
       //                      BlockStateProvider.simple(ZAMBlocks.AVOCADO_LOG.get()),
       //                      new CrossTrunkPlacer(4, 2, 0),
       //                      BlockStateProvider.simple(ZAMBlocks.AVOCADO_LEAVES.get()),
       //                      new AvocadoFoliagePlacer(ConstantInt.of(3), ConstantInt.of(0)),
       //                      new TwoLayersFeatureSize(1, 0, 1))
       //                      .dirt(BlockStateProvider.simple(Blocks.DIRT))
       //                      .ignoreVines()
       //                      .forceDirt()
       //                      .build());

   }

    public static ResourceKey<ConfiguredFeature<?, ?>> registerKey(String name){
            return ResourceKey.create(Registries.CONFIGURED_FEATURE, new ResourceLocation(ZAMMod.MOD_ID, name));
        }

    private static <FC extends FeatureConfiguration, F extends Feature<FC>> void register(BootstapContext<ConfiguredFeature<?, ?>> context, ResourceKey<ConfiguredFeature<?, ?>> key, F feature, FC configuration) {
        context.register(key, new ConfiguredFeature<>(feature, configuration));
    }

}

