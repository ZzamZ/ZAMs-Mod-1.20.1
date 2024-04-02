package net.zam.zammod.block;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.block.AmethystBlock;
import net.minecraft.world.level.block.AmethystClusterBlock;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.Fluids;
import net.zam.zammod.registry.ZAMBlocks;

public class BuddingEmeraldBlock extends AmethystBlock {
    public static final int GROWTH_CHANCE = 5;
    private static final Direction[] DIRECTIONS = Direction.values();

    public BuddingEmeraldBlock(BlockBehaviour.Properties pProperties) {
        super(pProperties);
    }

    /**
     * Performs a random tick on a block.
     */
    public void randomTick(BlockState pState, ServerLevel pLevel, BlockPos pPos, RandomSource pRandom) {
        if (pRandom.nextInt(5) == 0) {
            Direction direction = DIRECTIONS[pRandom.nextInt(DIRECTIONS.length)];
            BlockPos blockpos = pPos.relative(direction);
            BlockState blockstate = pLevel.getBlockState(blockpos);
            Block block = null;
            if (canClusterGrowAtState(blockstate)) {
                block = ZAMBlocks.SMALL_EMERALD_BUD.get();
            } else if (blockstate.is(ZAMBlocks.SMALL_EMERALD_BUD.get()) && blockstate.getValue(AmethystClusterBlock.FACING) == direction) {
                block = ZAMBlocks.MEDIUM_EMERALD_BUD.get();
            } else if (blockstate.is(ZAMBlocks.MEDIUM_EMERALD_BUD.get()) && blockstate.getValue(AmethystClusterBlock.FACING) == direction) {
                block = ZAMBlocks.LARGE_EMERALD_BUD.get();
            } else if (blockstate.is(ZAMBlocks.LARGE_EMERALD_BUD.get()) && blockstate.getValue(AmethystClusterBlock.FACING) == direction) {
                block = ZAMBlocks.EMERALD_CLUSTER.get();
            }

            if (block != null) {
                BlockState blockstate1 = block.defaultBlockState().setValue(AmethystClusterBlock.FACING, direction).setValue(AmethystClusterBlock.WATERLOGGED, Boolean.valueOf(blockstate.getFluidState().getType() == Fluids.WATER));
                pLevel.setBlockAndUpdate(blockpos, blockstate1);
            }

        }
    }

    public static boolean canClusterGrowAtState(BlockState pState) {
        return pState.isAir() || pState.is(Blocks.WATER) && pState.getFluidState().getAmount() == 8;
    }
}