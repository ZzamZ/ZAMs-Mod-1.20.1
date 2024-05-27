package net.zam.zammod.block.copper;

import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.zam.zammod.util.ZAMOxidizable;
import org.jetbrains.annotations.NotNull;

public class WeatheringCopperBulbBlock extends CopperBulbBlock implements ZAMOxidizable {
    private final ZAMOxidizable.CopperOxidizableLevel oxidationLevel;

    public WeatheringCopperBulbBlock(ZAMOxidizable.CopperOxidizableLevel oxidationLevel, BlockBehaviour.Properties settings) {
        super(settings);
        this.oxidationLevel = oxidationLevel;
    }

    @Override
    public void randomTick(@NotNull BlockState state, @NotNull ServerLevel world, @NotNull BlockPos pos, @NotNull RandomSource random) {
        this.onRandomTick(state, world, pos, random);
    }

    @Override
    public boolean isRandomlyTicking(BlockState state) {
        return ZAMOxidizable.getNext(state.getBlock()).isPresent();
    }

    @NotNull
    @Override
    public ZAMOxidizable.@NotNull CopperOxidizableLevel getAge() {
        return this.oxidationLevel;
    }
}
