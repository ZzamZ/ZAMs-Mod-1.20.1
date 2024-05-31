package net.zam.zammod.mixin;

import java.util.Optional;
import net.minecraft.core.BlockPos;
import net.minecraft.world.item.AxeItem;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.zam.zammod.util.ZAMOxidizable;
import net.zam.zammod.util.ZAMWaxable;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyVariable;

@Mixin(AxeItem.class)
public abstract class AxeItemMixin {
    public AxeItemMixin() {
    }

    @ModifyVariable(
            method = "useOn",
            ordinal = 1,
            at = @At("STORE")
    )
    private Optional<BlockState> zammod_modifyOxidizedBlock(Optional<BlockState> originalBlockState, UseOnContext context) {
        if (originalBlockState.isPresent()) {
            return originalBlockState;
        } else {
            Level world = context.getLevel();
            BlockPos blockPos = context.getClickedPos();
            BlockState blockState = world.getBlockState(blockPos);
            return ZAMOxidizable.getPrevious(blockState);
        }
    }

    @ModifyVariable(
            method = "useOn",
            ordinal = 2,
            at = @At("STORE")
    )
    private Optional<BlockState> zammod_modifyWaxedBlock(Optional<BlockState> originalBlockState, UseOnContext context) {
        if (originalBlockState.isPresent()) {
            return originalBlockState;
        } else {
            Level world = context.getLevel();
            BlockPos blockPos = context.getClickedPos();
            BlockState blockState = world.getBlockState(blockPos);
            return Optional.ofNullable(ZAMWaxable.SCRAPABLE.get().get(blockState.getBlock()))
                    .map(block -> block.defaultBlockState());
        }
    }
}
