package net.zam.zammod.block;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.item.FallingBlockEntity;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.SimpleWaterloggedBlock;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.block.state.properties.IntegerProperty;
import net.minecraft.world.level.material.FluidState;
import net.minecraft.world.level.material.Fluids;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;
import net.zam.zammod.registry.ZAMBlocks;

public class ScaffinityBlock extends Block implements SimpleWaterloggedBlock {
    private static final VoxelShape STABLE_SHAPE;
    private static final VoxelShape UNSTABLE_SHAPE;
    private static final VoxelShape UNSTABLE_SHAPE_BOTTOM = Block.box(0.0D, 0.0D, 0.0D, 16.0D, 2.0D, 16.0D);
    private static final VoxelShape BELOW_BLOCK = Shapes.block().move(0.0D, -1.0D, 0.0D);
    public static final int STABILITY_MAX_DISTANCE = 14;
    public static final IntegerProperty DISTANCE = IntegerProperty.create("distance", 0, 14);
    public static final BooleanProperty WATERLOGGED = BlockStateProperties.WATERLOGGED;
    public static final BooleanProperty BOTTOM = BlockStateProperties.BOTTOM;

    public ScaffinityBlock(Properties properties) {
        super(properties);
        this.registerDefaultState(this.stateDefinition.any().setValue(DISTANCE, 0)
                .setValue(WATERLOGGED, Boolean.FALSE)
                .setValue(BOTTOM, Boolean.FALSE));
    }

    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> pBuilder) {
        pBuilder.add(DISTANCE, WATERLOGGED, BOTTOM);
    }

    public VoxelShape getShape(BlockState pState, BlockGetter pLevel, BlockPos pPos, CollisionContext pContext) {
        if (!pContext.isHoldingItem(pState.getBlock().asItem())) {
            return pState.getValue(BOTTOM) ? UNSTABLE_SHAPE : STABLE_SHAPE;
        } else {
            return Shapes.block();
        }
    }

    public VoxelShape getInteractionShape(BlockState pState, BlockGetter pLevel, BlockPos pPos) {
        return Shapes.block();
    }

    public boolean canBeReplaced(BlockState pState, BlockPlaceContext pUseContext) {
        return pUseContext.getItemInHand().is(this.asItem());
    }

    public BlockState getStateForPlacement(BlockPlaceContext pContext) {
        BlockPos blockpos = pContext.getClickedPos();
        Level level = pContext.getLevel();
        int i = getDistance(level, blockpos);
        return this.defaultBlockState().setValue(WATERLOGGED, Boolean.valueOf(level.getFluidState(blockpos).getType() == Fluids.WATER)).setValue(DISTANCE, Integer.valueOf(i)).setValue(BOTTOM, Boolean.valueOf(this.isBottom(level, blockpos, i)));
    }

    public void onPlace(BlockState pState, Level pLevel, BlockPos pPos, BlockState pOldState, boolean pIsMoving) {
        if (!pLevel.isClientSide) {
            pLevel.scheduleTick(pPos, this, 1);
        }

    }

    public boolean isScaffolding(BlockState state, LevelReader level, BlockPos pos, LivingEntity entity) {
        return true;
    }

    /**
     * Update the provided state given the provided neighbor direction and neighbor state, returning a new state.
     * For example, fences make their connections to the passed in state if possible, and wet concrete powder immediately
     * returns its solidified counterpart.
     * Note that this method should ideally consider only the specific direction passed in.
     */
    public BlockState updateShape(BlockState pState, Direction pFacing, BlockState pFacingState, LevelAccessor pLevel, BlockPos pCurrentPos, BlockPos pFacingPos) {
        if (pState.getValue(WATERLOGGED)) {
            pLevel.scheduleTick(pCurrentPos, Fluids.WATER, Fluids.WATER.getTickDelay(pLevel));
        }

        if (!pLevel.isClientSide()) {
            pLevel.scheduleTick(pCurrentPos, this, 1);
        }

        return pState;
    }

    public void tick(BlockState blockState, ServerLevel level, BlockPos pos, RandomSource randomSource) {
        int i = getDistance(level, pos);
        BlockState blockstate = (BlockState)((BlockState)blockState.setValue(DISTANCE, i)).setValue(BOTTOM, this.isBottom(level, pos, i));
        if ((Integer)blockstate.getValue(DISTANCE) == 14) {
            if ((Integer)blockState.getValue(DISTANCE) == 14) {
                FallingBlockEntity.fall(level, pos, blockstate);
            } else {
                level.destroyBlock(pos, true);
            }
        } else if (blockState != blockstate) {
            level.setBlock(pos, blockstate, 3);
        }

    }

    @Override
    public boolean canSurvive(BlockState state, LevelReader world, BlockPos pos) {
        return getDistance(world, pos) < STABILITY_MAX_DISTANCE;
    }

    public VoxelShape getCollisionShape(BlockState pState, BlockGetter pLevel, BlockPos pPos, CollisionContext pContext) {
        if (pContext.isAbove(Shapes.block(), pPos, true) && !pContext.isDescending()) {
            return STABLE_SHAPE;
        } else {
            return pState.getValue(DISTANCE) != 0 && pState.getValue(BOTTOM) && pContext.isAbove(BELOW_BLOCK, pPos, true) ? UNSTABLE_SHAPE_BOTTOM : Shapes.empty();
        }
    }

    public FluidState getFluidState(BlockState pState) {
        return pState.getValue(WATERLOGGED) ? Fluids.WATER.getSource(false) : super.getFluidState(pState);
    }

    private boolean isBottom(BlockGetter pLevel, BlockPos pPos, int pDistance) {
        return pDistance > 0 && !pLevel.getBlockState(pPos.below()).is(this);
    }

    public static int getDistance(BlockGetter pLevel, BlockPos pPos) {
        BlockPos.MutableBlockPos blockpos$mutableblockpos = pPos.mutable().move(Direction.DOWN);
        BlockState blockstate = pLevel.getBlockState(blockpos$mutableblockpos);
        int i = 14;
        if (blockstate.is(ZAMBlocks.SCAFFINITY.get())) {
            i = blockstate.getValue(DISTANCE);
        } else if (blockstate.isFaceSturdy(pLevel, blockpos$mutableblockpos, Direction.UP)) {
            return 0;
        }

        for(Direction direction : Direction.Plane.HORIZONTAL) {
            BlockState blockstate1 = pLevel.getBlockState(blockpos$mutableblockpos.setWithOffset(pPos, direction));
            if (blockstate1.is(ZAMBlocks.SCAFFINITY.get())) {
                i = Math.min(i, blockstate1.getValue(DISTANCE) + 1);
                if (i == 1) {
                    break;
                }
            }
        }

        return i;
    }

    static {
        VoxelShape voxelshape = Block.box(0.0D, 14.0D, 0.0D, 16.0D, 16.0D, 16.0D);
        VoxelShape voxelshape1 = Block.box(0.0D, 0.0D, 0.0D, 2.0D, 16.0D, 2.0D);
        VoxelShape voxelshape2 = Block.box(14.0D, 0.0D, 0.0D, 16.0D, 16.0D, 2.0D);
        VoxelShape voxelshape3 = Block.box(0.0D, 0.0D, 14.0D, 2.0D, 16.0D, 16.0D);
        VoxelShape voxelshape4 = Block.box(14.0D, 0.0D, 14.0D, 16.0D, 16.0D, 16.0D);
        STABLE_SHAPE = Shapes.or(voxelshape, voxelshape1, voxelshape2, voxelshape3, voxelshape4);
        VoxelShape voxelshape5 = Block.box(0.0D, 0.0D, 0.0D, 2.0D, 2.0D, 16.0D);
        VoxelShape voxelshape6 = Block.box(14.0D, 0.0D, 0.0D, 16.0D, 2.0D, 16.0D);
        VoxelShape voxelshape7 = Block.box(0.0D, 0.0D, 14.0D, 16.0D, 2.0D, 16.0D);
        VoxelShape voxelshape8 = Block.box(0.0D, 0.0D, 0.0D, 16.0D, 2.0D, 2.0D);
        UNSTABLE_SHAPE = Shapes.or(UNSTABLE_SHAPE_BOTTOM, STABLE_SHAPE, voxelshape6, voxelshape5, voxelshape8, voxelshape7);
    }
}