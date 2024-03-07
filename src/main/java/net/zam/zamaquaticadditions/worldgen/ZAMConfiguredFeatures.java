package net.zam.zamaquaticadditions.worldgen;

import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstapContext;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BlockTags;
import net.minecraft.util.valueproviders.UniformInt;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.levelgen.GeodeBlockSettings;
import net.minecraft.world.level.levelgen.GeodeCrackSettings;
import net.minecraft.world.level.levelgen.GeodeLayerSettings;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.configurations.FeatureConfiguration;
import net.minecraft.world.level.levelgen.feature.configurations.GeodeConfiguration;
import net.minecraft.world.level.levelgen.feature.stateproviders.BlockStateProvider;
import net.zam.zamaquaticadditions.ZAMAquaticAdditions;
import net.zam.zamaquaticadditions.registry.ZAMBlocks;

import java.util.List;

public class ZAMConfiguredFeatures {
    public static final ResourceKey<ConfiguredFeature<?, ?>> EMERALD_GEODE_KEY = registerKey("emerald_geode");

    public static void bootstrap(BootstapContext<ConfiguredFeature<?, ?>> context) {
        register(context, EMERALD_GEODE_KEY, Feature.GEODE,
                new GeodeConfiguration(new GeodeBlockSettings(BlockStateProvider.simple(Blocks.AIR),
                        BlockStateProvider.simple(ZAMBlocks.EMERALD_CRYSTAL_BLOCK.get()),
                        BlockStateProvider.simple(ZAMBlocks.BUDDING_EMERALD.get()),
                        BlockStateProvider.simple(Blocks.CALCITE),
                        BlockStateProvider.simple(Blocks.SMOOTH_BASALT),
                        List.of(ZAMBlocks.SMALL_EMERALD_BUD.get().defaultBlockState(), ZAMBlocks.MEDIUM_EMERALD_BUD.get().defaultBlockState(), ZAMBlocks.LARGE_EMERALD_BUD.get().defaultBlockState(), ZAMBlocks.EMERALD_CLUSTER.get().defaultBlockState()),
                        BlockTags.FEATURES_CANNOT_REPLACE, BlockTags.GEODE_INVALID_BLOCKS),
                        new GeodeLayerSettings(1.7D, 2.2D, 3.2D, 4.2D),
                        new GeodeCrackSettings(0.95D, 2.0D, 2), 0.35D, 0.083D,
                        true, UniformInt.of(4, 6),
                        UniformInt.of(3, 4),
                        UniformInt.of(1, 2), -16, 16, 0.05D, 1));
    }

    public static ResourceKey<ConfiguredFeature<?, ?>> registerKey(String name){
            return ResourceKey.create(Registries.CONFIGURED_FEATURE, new ResourceLocation(ZAMAquaticAdditions.MOD_ID, name));
        }

    private static <FC extends FeatureConfiguration, F extends Feature<FC>> void register(BootstapContext<ConfiguredFeature<?, ?>> context, ResourceKey<ConfiguredFeature<?, ?>> key, F feature, FC configuration) {
        context.register(key, new ConfiguredFeature<>(feature, configuration));
    }
}

