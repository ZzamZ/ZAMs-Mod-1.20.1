package net.zam.zammod.event;

import net.minecraft.client.gui.screens.MenuScreens;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.fml.common.Mod;
import net.zam.zammod.ZAMMod;
import net.zam.zammod.arcade.ArcadeGui;
import net.zam.zammod.registry.ZAMMenuTypes;

@Mod.EventBusSubscriber(modid = ZAMMod.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public class ClientEventHandler {

    public static void clientRegistry() {
        MenuScreens.register(ZAMMenuTypes.ARCADE_CONTAINER.get(), ArcadeGui::new);

    }
}