package net.zam.zammod.block;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.RandomSource;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.item.FallingBlockEntity;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.Block;
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

//public class ScaffinityBlock extends Block implements SimpleWaterloggedBlock {
//    private static final VoxelShape STABLE_SHAPE;
//    private static final VoxelShape UNSTABLE_SHAPE;
//    private static final VoxelShape UNSTABLE_SHAPE_BOTTOM = Block.box(0.0D, 0.0D, 0.0D, 16.0D, 2.0D, 16.0D);
//    private static final VoxelShape BELOW_BLOCK = Shapes.block().move(0.0D, -1.0D, 0.0D);
//    public static final BooleanProperty WATERLOGGED = BlockStateProperties.WATERLOGGED;
//    public static final BooleanProperty BOTTOM = BlockStateProperties.BOTTOM;
//
//    public ScaffinityBlock(BlockBehaviour.Properties properties) {
//        super(properties);
//        this.registerDefaultState(this.stateDefinition.any()
//                .setValue(WATERLOGGED, Boolean.FALSE)
//                .setValue(BOTTOM, Boolean.FALSE));
//    }
//
//    @Override
//    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
//        builder.add(WATERLOGGED, BOTTOM);
//    }
//
//    @Override
//    public VoxelShape getShape(BlockState state, BlockGetter level, BlockPos pos, CollisionContext context) {
//        if (!context.isHoldingItem(state.getBlock().asItem())) {
//            return state.getValue(BOTTOM) ? UNSTABLE_SHAPE : STABLE_SHAPE;
//        } else {
//            return Shapes.block();
//        }
//    }
//
//    @Override
//    public VoxelShape getInteractionShape(BlockState state, BlockGetter level, BlockPos pos) {
//        return Shapes.block();
//    }
//
//    @Override
//    public boolean canBeReplaced(BlockState state, BlockPlaceContext context) {
//        return context.getItemInHand().is(this.asItem());
//    }
//
//    @Override
//    public BlockState getStateForPlacement(BlockPlaceContext context) {
//        BlockPos blockpos = context.getClickedPos();
//        Level level = context.getLevel();
//        return this.defaultBlockState().setValue(WATERLOGGED, level.getFluidState(blockpos).getType() == Fluids.WATER)
//                .setValue(BOTTOM, this.isBottom(level, blockpos));
//    }
//
//    @Override
//    public void onPlace(BlockState state, Level level, BlockPos pos, BlockState oldState, boolean isMoving) {
//        if (!level.isClientSide) {
//            level.scheduleTick(pos, this, 1);
//        }
//    }
//
//    @Override
//    public BlockState updateShape(BlockState state, Direction facing, BlockState facingState, LevelAccessor level, BlockPos currentPos, BlockPos facingPos) {
//        if (state.getValue(WATERLOGGED)) {
//            level.scheduleTick(currentPos, Fluids.WATER, Fluids.WATER.getTickDelay(level));
//        }
//
//        if (!level.isClientSide()) {
//            level.scheduleTick(currentPos, this, 1);
//        }
//
//        return state;
//    }
//
//    @Override
//    public void tick(BlockState state, ServerLevel level, BlockPos pos, RandomSource random) {
//        if (!canSurvive(state, level, pos)) {
//            breakConnectedBlocks(level, pos);
//        }
//    }
//
//    private void breakConnectedBlocks(ServerLevel level, BlockPos pos) {
//        level.destroyBlock(pos, false);
//
//        for (Direction direction : Direction.Plane.HORIZONTAL) {
//            BlockPos neighborPos = pos.relative(direction);
//            BlockState neighborState = level.getBlockState(neighborPos);
//            if (neighborState.is(this)) {
//                breakConnectedBlocks(level, neighborPos);
//            }
//        }
//
//        BlockPos abovePos = pos.above();
//        BlockState aboveState = level.getBlockState(abovePos);
//        if (aboveState.is(this)) {
//            breakConnectedBlocks(level, abovePos);
//        }
//    }
//
//    @Override
//    public boolean canSurvive(BlockState state, LevelReader world, BlockPos pos) {
//        BlockState belowState = world.getBlockState(pos.below());
//        return belowState.isFaceSturdy(world, pos.below(), Direction.UP) || belowState.is(this);
//    }
//
//    @Override
//    public VoxelShape getCollisionShape(BlockState state, BlockGetter level, BlockPos pos, CollisionContext context) {
//        if (context.isAbove(Shapes.block(), pos, true) && !context.isDescending()) {
//            return STABLE_SHAPE;
//        } else {
//            return state.getValue(BOTTOM) && context.isAbove(BELOW_BLOCK, pos, true) ? UNSTABLE_SHAPE_BOTTOM : Shapes.empty();
//        }
//    }
//
//    @Override
//    public FluidState getFluidState(BlockState state) {
//        return state.getValue(WATERLOGGED) ? Fluids.WATER.getSource(false) : super.getFluidState(state);
//    }
//
//    @Override
//    public void entityInside(BlockState state, Level world, BlockPos pos, net.minecraft.world.entity.Entity entity) {
//        if (entity instanceof LivingEntity && entity.getDeltaMovement().y < 0.0D) {
//            entity.setDeltaMovement(entity.getDeltaMovement().multiply(1.0D, 0.2D, 1.0D));
//        }
//    }
//
//    @Override
//    public void fallOn(Level world, BlockState state, BlockPos pos, net.minecraft.world.entity.Entity entity, float fallDistance) {
//        entity.causeFallDamage(fallDistance, 0.2F, DamageSource.FALL);
//    }
//
//    private boolean isBottom(BlockGetter level, BlockPos pos) {
//        return !level.getBlockState(pos.below()).is(this);
//    }
//
//    static {
//        VoxelShape voxelshape = Block.box(0.0D, 14.0D, 0.0D, 16.0D, 16.0D, 16.0D);
//        VoxelShape voxelshape1 = Block.box(0.0D, 0.0D, 0.0D, 2.0D, 16.0D, 2.0D);
//        VoxelShape voxelshape2 = Block.box(14.0D, 0.0D, 0.0D, 16.0D, 16.0D, 2.0D);
//        VoxelShape voxelshape3 = Block.box(0.0D, 0.0D, 14.0D, 2.0D, 16.0D, 16.0D);
//        VoxelShape voxelshape4 = Block.box(14.0D, 0.0D, 14.0D, 16.0D, 16.0D, 16.0D);
//        STABLE_SHAPE = Shapes.or(voxelshape, voxelshape1, voxelshape2, voxelshape3, voxelshape4);
//        VoxelShape voxelshape5 = Block.box(0.0D, 0.0D, 0.0D, 2.0D, 2.0D, 16.0D);
//        VoxelShape voxelshape6 = Block.box(14.0D, 0.0D, 0.0D, 16.0D, 2.0D, 16.0D);
//        VoxelShape voxelshape7 = Block.box(0.0D, 0.0D, 14.0D, 16.0D, 2.0D, 16.0D);
//        VoxelShape voxelshape8 = Block.box(0.0D, 0.0D, 0.0D, 16.0D, 2.0D, 2.0D);
//        UNSTABLE_SHAPE = Shapes.or(UNSTABLE_SHAPE_BOTTOM, STABLE_SHAPE, voxelshape6, voxelshape5, voxelshape8, voxelshape7);
//    }
//}
//