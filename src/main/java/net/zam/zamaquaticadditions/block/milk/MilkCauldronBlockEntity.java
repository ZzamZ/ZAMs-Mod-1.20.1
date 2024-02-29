package net.zam.zamaquaticadditions.block.milk;

import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.zam.zamaquaticadditions.ZAMConfig;
import net.zam.zamaquaticadditions.registry.ZAMBlockEntities;
import net.zam.zamaquaticadditions.registry.ZAMBlocks;
import net.zam.zamaquaticadditions.registry.ZAMParticles;
import net.zam.zamaquaticadditions.registry.ZAMSounds;

public class MilkCauldronBlockEntity extends BlockEntity {
    int tickCount;

    public MilkCauldronBlockEntity(BlockPos pos, BlockState state) {
        super(ZAMBlockEntities.MILK_CAULDRON.get(), pos, state);
    }

    public static void tick(Level level, BlockPos pos, BlockState state, MilkCauldronBlockEntity te) {
        te.tickCount++;
        if (ZAMConfig.cheesemaking && te.tickCount >= ZAMConfig.milkCauldronTime) {
            level.setBlockAndUpdate(pos, ZAMBlocks.CHEESE_CAULDRON.get().defaultBlockState());
            level.playSound(null, pos, ZAMSounds.CHEESE_MADE.get(), SoundSource.BLOCKS, 1, 1);
            if (level.isClientSide()) {
                for (int i = 0; i < 10; i++) {
                    level.addParticle(ZAMParticles.MILK_BUBBLE.get(),
                            pos.getX() + level.getRandom().nextFloat(),
                            pos.getY() + 0.9375F,
                            pos.getZ() + level.getRandom().nextFloat(),
                            0.0D, 0.0D, 0.0D);
                }
            }
        }
    }

    @Override
    public void saveAdditional(CompoundTag compound) {
        compound.putInt("TicksExisted", tickCount);
        super.saveAdditional(compound);
    }

    @Override
    public void load(CompoundTag compound) {
        super.load(compound);
        tickCount = compound.getInt("TicksExisted");
    }

}
