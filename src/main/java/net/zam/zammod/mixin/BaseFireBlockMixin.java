package net.zam.zammod.mixin;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.BaseFireBlock;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.zam.zammod.block.grimm.GrimmFireBlock;
import net.zam.zammod.registry.ZAMBlocks;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(BaseFireBlock.class)
public final class BaseFireBlockMixin extends Block {

    private BaseFireBlockMixin(Properties properties) {
        super(properties);
    }

    @Inject(at = @At("HEAD"), method = "getState", cancellable = true)
    private static void cursedFirePlacement(BlockGetter reader, BlockPos pos, CallbackInfoReturnable<BlockState> info) {
        if (GrimmFireBlock.canSurviveOnBlock(reader.getBlockState(pos.below()))) {
            info.setReturnValue(ZAMBlocks.GRIMM_FIRE.get().defaultBlockState());
        }
    }
}