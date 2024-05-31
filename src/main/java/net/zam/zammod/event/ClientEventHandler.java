package net.zam.zammod.event;

import net.minecraft.client.gui.screens.MenuScreens;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.RegisterParticleProvidersEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.zam.zammod.ZAMMod;
import net.zam.zammod.gui.arcade.ArcadeGui;
import net.zam.zammod.particles.YellowBubbleParticles;
import net.zam.zammod.registry.ZAMMenuTypes;
import net.zam.zammod.registry.ZAMParticles;

@Mod.EventBusSubscriber(modid = ZAMMod.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public class ClientEventHandler {

    @SubscribeEvent
    public static void registerParticleFactories(final RegisterParticleProvidersEvent event) {
        event.registerSpriteSet(ZAMParticles.YELLOW_BUBBLES.get(), YellowBubbleParticles.Provider::new);
        event.registerSpriteSet(ZAMParticles.PURPLE_BUBBLES.get(), YellowBubbleParticles.Provider::new);
        event.registerSpriteSet(ZAMParticles.WHITE_BUBBLES.get(), YellowBubbleParticles.Provider::new);
    }

    public static void clientRegistry() {
        MenuScreens.register(ZAMMenuTypes.ARCADE_CONTAINER.get(), ArcadeGui::new);
    }
}
