package net.zam.zamaquaticadditions.mixin;

import net.minecraft.server.level.ServerPlayer;
import net.zam.zamaquaticadditions.enchantment.SoulboundEnchantment;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(ServerPlayer.class)
public class ServerPlayerMixin {

    @Inject(method = "restoreFrom", at = @At("TAIL"))
    public void restoreFrom(ServerPlayer oldPlayer, boolean alive, CallbackInfo ci) {
        SoulboundEnchantment.copySoulBoundItems(oldPlayer, (ServerPlayer) (Object) this, alive);
    }
}