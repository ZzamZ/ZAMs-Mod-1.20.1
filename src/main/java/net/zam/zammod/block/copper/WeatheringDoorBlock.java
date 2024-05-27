package net.zam.zammod.block.copper;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.Direction.Axis;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.DoorBlock;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.BlockSetType;
import net.minecraft.world.level.block.state.properties.DoubleBlockHalf;
import net.zam.zammod.util.ZAMOxidizable;
import org.jetbrains.annotations.NotNull;

public class WeatheringDoorBlock extends DoorBlock implements ZAMOxidizable {
    private final ZAMOxidizable.CopperOxidizableLevel oxidationLevel;

    public WeatheringDoorBlock(BlockSetType type, ZAMOxidizable.CopperOxidizableLevel oxidationLevel, BlockBehaviour.Properties settings) {
        super(settings, type);
        this.oxidationLevel = oxidationLevel;
    }

    @Override
    public void randomTick(BlockState state, @NotNull ServerLevel world, @NotNull BlockPos pos, @NotNull RandomSource random) {
        if (state.getValue(HALF) == DoubleBlockHalf.LOWER) {
            this.onRandomTick(state, world, pos, random);
        }
    }

    @Override
    public boolean isRandomlyTicking(BlockState state) {
        return ZAMOxidizable.getNext(state.getBlock()).isPresent();
    }

    @Override
    @NotNull
    public BlockState updateShape(BlockState state, Direction direction, @NotNull BlockState neighborState, @NotNull LevelAccessor world, @NotNull BlockPos pos, @NotNull BlockPos neighborPos) {
        DoubleBlockHalf doubleBlockHalf = state.getValue(HALF);
        if (direction.getAxis() == Axis.Y && doubleBlockHalf == DoubleBlockHalf.LOWER == (direction == Direction.UP)) {
            if (neighborState.getBlock() instanceof DoorBlock && neighborState.getValue(HALF) != doubleBlockHalf) {
                return neighborState.setValue(HALF, doubleBlockHalf);
            } else {
                return Blocks.AIR.defaultBlockState();
            }
        } else {
            return doubleBlockHalf == DoubleBlockHalf.LOWER && direction == Direction.DOWN && !state.canSurvive(world, pos)
                    ? Blocks.AIR.defaultBlockState()
                    : super.updateShape(state, direction, neighborState, world, pos, neighborPos);
        }
    }

    @Override
    @NotNull
    public ZAMOxidizable.@NotNull CopperOxidizableLevel getAge() {
        return this.oxidationLevel;
    }
}
