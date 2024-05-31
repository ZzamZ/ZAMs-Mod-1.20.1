package net.zam.zammod.mixin;

import com.google.common.collect.BiMap;
import java.util.Optional;
import net.minecraft.world.item.HoneycombItem;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.zam.zammod.util.ZAMWaxable;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(HoneycombItem.class)
public abstract class HoneycombItemMixin {
    public HoneycombItemMixin() {
    }

    @Inject(
            method = "getWaxed",
            at = @At("RETURN"),
            cancellable = true
    )
    private static void zammod_getWaxedState(BlockState state, CallbackInfoReturnable<Optional<BlockState>> callbackInfo) {
        Optional<BlockState> blockState = callbackInfo.getReturnValue();
        if (blockState.isEmpty()) {
            blockState = Optional.ofNullable(ZAMWaxable.WAXABLES.get().get(state.getBlock()))
                    .map(block -> block.defaultBlockState());
            callbackInfo.setReturnValue(blockState);
        }
    }
}
