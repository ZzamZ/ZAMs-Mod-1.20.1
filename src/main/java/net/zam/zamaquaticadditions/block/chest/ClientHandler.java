package net.zam.zamaquaticadditions.block.chest;

import net.minecraft.client.renderer.blockentity.BlockEntityRenderers;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.fml.common.Mod;
import net.zam.zamaquaticadditions.ZAMAquaticAdditions;
import net.zam.zamaquaticadditions.block.chest.renderer.tileentity.LostBountyRenderer;
import net.zam.zamaquaticadditions.registry.ZAMBlockEntities;

@Mod.EventBusSubscriber(modid = ZAMAquaticAdditions.MOD_ID, value = Dist.CLIENT, bus = Mod.EventBusSubscriber.Bus.MOD)
public class ClientHandler {


    public static void setupClient() {
        BlockEntityRenderers.register(ZAMBlockEntities.LOST_BOUNTY.get(), LostBountyRenderer::new);
    }
}