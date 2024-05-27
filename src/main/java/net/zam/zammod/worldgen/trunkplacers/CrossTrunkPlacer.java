package net.zam.zammod.worldgen.trunkplacers;

import com.google.common.collect.ImmutableList;
import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.LevelSimulatedReader;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.feature.TreeFeature;
import net.minecraft.world.level.levelgen.feature.configurations.TreeConfiguration;
import net.minecraft.world.level.levelgen.feature.foliageplacers.FoliagePlacer;
import net.minecraft.world.level.levelgen.feature.trunkplacers.TrunkPlacer;
import net.minecraft.world.level.levelgen.feature.trunkplacers.TrunkPlacerType;
import net.zam.zammod.registry.ZAMBlocks;
import net.zam.zammod.registry.ZAMTrunkPlacerTypes;

import java.util.List;
import java.util.function.BiConsumer;

public class CrossTrunkPlacer extends TrunkPlacer {
    public static final Codec<CrossTrunkPlacer> CODEC = RecordCodecBuilder.create((instance) ->
            trunkPlacerParts(instance).apply(instance, CrossTrunkPlacer::new)
    );

    public CrossTrunkPlacer(int baseHeight, int heightVariationA, int heightVariationB) {
        super(baseHeight, heightVariationA, heightVariationB);
    }

    @Override
    protected TrunkPlacerType<?> type() {
        return ZAMTrunkPlacerTypes.CROSS_TRUNK_PLACER.get();
    }

    @Override
    public List<FoliagePlacer.FoliageAttachment> placeTrunk(LevelSimulatedReader reader, BiConsumer<BlockPos, BlockState> replacer, RandomSource random, int trunkHeight, BlockPos startPos, TreeConfiguration config) {
        setDirtAt(reader, replacer, random, startPos.below(), config);

        BlockPos.MutableBlockPos mutablePos = startPos.mutable().move(Direction.DOWN);

        for (int i = 0; i < trunkHeight; i++) {
            placeLog(reader, replacer, random, mutablePos.move(Direction.UP), config);

            // Add branches one block below the second to last log
            if (i == trunkHeight - 3) {
                addBranches(reader, replacer, random, mutablePos, config);
            }
        }

        // Return the position for foliage placement, adjusted to account for the new starting position
        return ImmutableList.of(new FoliagePlacer.FoliageAttachment(mutablePos.immutable(), 0, false));
    }

    private void addBranches(LevelSimulatedReader reader, BiConsumer<BlockPos, BlockState> replacer, RandomSource random, BlockPos pos, TreeConfiguration config) {
      //  BlockState branchState = ZAMBlocks.AVOCADO_WOOD.get().defaultBlockState(); // Change this to your desired branch block state
        // Randomly place branches
    //    for (Direction direction : Direction.Plane.HORIZONTAL) {
    //        if (random.nextBoolean()) { // 50% chance to place a branch
      //          BlockPos branchPos = pos.relative(direction);
       //         BlockPos diagonalBranchPos = branchPos.relative(direction.getClockWise()); // Get diagonal position
         //       if (TreeFeature.validTreePos(reader, branchPos)) {
           //         replacer.accept(branchPos, branchState);
           //         if (TreeFeature.validTreePos(reader, diagonalBranchPos)) {
          //              replacer.accept(diagonalBranchPos, branchState); // Place diagonal branch
                    }
                }
       //     }
     //   }
  //  }
//}
