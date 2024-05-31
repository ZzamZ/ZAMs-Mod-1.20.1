package net.zam.zammod.block.market;

import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.protocol.game.ClientboundBlockEntityDataPacket;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.network.PacketDistributor;

public abstract class BaseBlockEntityUpdateType extends BlockEntity {

    public enum UpdateType {
        SERVER,
        INITIAL_PACKET,
        UPDATE_PACKET
    }

    public BaseBlockEntityUpdateType(BlockEntityType<?> type, BlockPos pos, BlockState state) {
        super(type, pos, state);
    }

    public abstract void writeNbt(CompoundTag tag, UpdateType type);

    public abstract void readNbt(CompoundTag tag, UpdateType type);

    @Override
    public void saveAdditional(CompoundTag tag) {
        super.saveAdditional(tag);
        this.writeNbt(tag, UpdateType.SERVER);
    }

    @Override
    public void load(CompoundTag tag) {
        super.load(tag);
        this.readNbt(tag, UpdateType.SERVER);
    }

    @Override
    public CompoundTag getUpdateTag() {
        CompoundTag tag = super.getUpdateTag();
        this.writeNbt(tag, UpdateType.INITIAL_PACKET);
        return tag;
    }

    @Override
    public ClientboundBlockEntityDataPacket getUpdatePacket() {
        CompoundTag tag = new CompoundTag();
        this.writeNbt(tag, UpdateType.UPDATE_PACKET);
        return ClientboundBlockEntityDataPacket.create(this, (blockEntity) -> tag);
    }

    public void sendUpdatesToClient() {
        this.setChanged();
        if (this.level != null) {
            this.level.sendBlockUpdated(this.worldPosition, this.getBlockState(), this.getBlockState(), 3);
            this.level.blockEntityChanged(this.worldPosition);
            if (!this.level.isClientSide) {
                PacketDistributor.TargetPoint targetPoint = new PacketDistributor.TargetPoint(
                        this.worldPosition.getX(), this.worldPosition.getY(), this.worldPosition.getZ(),
                        64, this.level.dimension());
                // Replace with your packet handler and packet
                // PacketHandler.INSTANCE.send(PacketDistributor.NEAR.with(() -> targetPoint), new YourUpdatePacket(this));
            }
        }
    }
}
