package net.zam.zammod.worldgen.tree.grower;

import net.minecraft.resources.ResourceKey;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.block.grower.AbstractTreeGrower;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.zam.zammod.worldgen.ZAMConfiguredFeatures;

import javax.annotation.Nullable;

public class AvocadoTreeGrower extends AbstractTreeGrower {
    @Nullable
    @Override
    protected ResourceKey<ConfiguredFeature<?, ?>> getConfiguredFeature(RandomSource pRandom, boolean pHasFlowers) {
        return ZAMConfiguredFeatures.AVOCADO_TREE_KEY;
    }
}