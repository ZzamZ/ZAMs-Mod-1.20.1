package net.zam.zammod.block.arcade;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.MenuProvider;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.EntityBlock;
import net.minecraft.world.level.block.HorizontalDirectionalBlock;
import net.minecraft.world.level.block.RenderShape;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityTicker;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition.Builder;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.DirectionProperty;
import net.minecraft.world.level.material.PushReaction;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;
import net.minecraftforge.network.NetworkHooks;
import net.zam.zammod.block.BaseBlock;
import net.zam.zammod.registry.ZAMBlockEntities;
import net.zam.zammod.util.Util;

import javax.annotation.Nullable;

public class ArcadeMachineBlock extends BaseBlock implements EntityBlock {
    public static final DirectionProperty FACING = HorizontalDirectionalBlock.FACING;

    public ArcadeMachineBlock() {
        super("arcade", Properties.copy(Blocks.IRON_BLOCK)
                .isSuffocating((state, world, pos) -> true)
                .isViewBlocking((state, world, pos) -> true)
                .noOcclusion()
                .requiresCorrectToolForDrops()
                .strength(5.0F));
        this.registerDefaultState(this.defaultBlockState().setValue(FACING, Direction.NORTH));
    }

    public ArcadeMachineBlock(String name) {
        super("arcade_" + name, Properties.copy(Blocks.OAK_PLANKS)
                .isSuffocating((state, world, pos) -> true)
                .isViewBlocking((state, world, pos) -> true)
                .noOcclusion()
                .requiresCorrectToolForDrops()
                .lightLevel((state) -> 15)
                .strength(5.0F));
        this.registerDefaultState(this.defaultBlockState().setValue(FACING, Direction.NORTH));
    }

    public <T extends BlockEntity> BlockEntityTicker<T> getTicker(Level world, BlockState state, BlockEntityType<T> type) {
        return world.isClientSide() ? Util.createTickerHelper(type, ZAMBlockEntities.ARCADE_MACHINE.get(), ArcadeTileEntity::tick) : null;
    }

    @Override
    public PushReaction getPistonPushReaction(BlockState state) {
        return PushReaction.BLOCK;
    }

    @Override
    public VoxelShape getShape(BlockState state, BlockGetter worldIn, BlockPos pos, CollisionContext context) {
        return Block.box(0.0D, 0.0D, 0.0D, 16.0D, 32.0D, 16.0D); // Hitbox is a single block
    }


    @Nullable
    @Override
    public BlockState getStateForPlacement(BlockPlaceContext context) {
        return this.defaultBlockState().setValue(FACING, context.getHorizontalDirection().getOpposite());
    }

    @Override
    protected void createBlockStateDefinition(Builder<Block, BlockState> builder) {
        builder.add(FACING);
    }

    @Override
    public InteractionResult use(BlockState state, Level world, BlockPos pos, Player player, InteractionHand hand, BlockHitResult hit) {
        if (player.isSpectator()) {
            return InteractionResult.PASS;
        }
        if (world.isClientSide()) {
            return InteractionResult.SUCCESS;
        }

        ArcadeTileEntity mte = getTE(world, pos);
        NetworkHooks.openScreen((ServerPlayer) player, (MenuProvider) mte, extraData -> {
            extraData.writeBlockPos(pos);
        });
        return InteractionResult.SUCCESS;
    }

    @Override
    public void onRemove(BlockState state, Level worldIn, BlockPos pos, BlockState newState, boolean isMoving) {
        if (!state.is(newState.getBlock())) {
            ArcadeTileEntity tileentity = getTE(worldIn, pos);
            if (tileentity != null) {
                worldIn.removeBlockEntity(pos);
            }
            super.onRemove(state, worldIn, pos, newState, isMoving);
        }
    }

    private ArcadeTileEntity getTE(Level worldIn, BlockPos pos) {
        BlockEntity tileentity = worldIn.getBlockEntity(pos);
        if (tileentity instanceof ArcadeTileEntity)
            return (ArcadeTileEntity) tileentity;
        return null;
    }



    @Override
    public RenderShape getRenderShape(BlockState state) {
        return RenderShape.MODEL;
    }

    @Override
    public boolean triggerEvent(BlockState state, Level world, BlockPos pos, int id, int param) {
        BlockEntity tileentity = world.getBlockEntity(pos);
        return tileentity != null && tileentity.triggerEvent(id, param);
    }

    @Override
    public BlockEntity newBlockEntity(BlockPos pos, BlockState state) {
        return new ArcadeTileEntity(pos, state);
    }
}
