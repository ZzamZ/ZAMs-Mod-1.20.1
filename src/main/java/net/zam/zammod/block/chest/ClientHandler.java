package net.zam.zammod.block.chest;

import net.minecraft.client.renderer.blockentity.BlockEntityRenderers;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.fml.common.Mod;
import net.zam.zammod.ZAMMod;
import net.zam.zammod.block.chest.renderer.tileentity.LostBountyRenderer;
import net.zam.zammod.registry.ZAMBlockEntities;

@Mod.EventBusSubscriber(modid = ZAMMod.MOD_ID, value = Dist.CLIENT, bus = Mod.EventBusSubscriber.Bus.MOD)
public class ClientHandler {


    public static void setupClient() {
        BlockEntityRenderers.register(ZAMBlockEntities.LOST_BOUNTY.get(), LostBountyRenderer::new);
    }
}