package net.zam.zammod.util.network;

import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.projectile.FishingHook;
import net.minecraftforge.event.AddReloadListenerEvent;
import net.minecraftforge.event.AttachCapabilitiesEvent;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.zam.zammod.ZAMMod;
import net.zam.zammod.util.server.FishBehaviorReloadListener;

public class CommonEvents {
    @Mod.EventBusSubscriber(modid = ZAMMod.MOD_ID)
    public static class ForgeBus {
        @SubscribeEvent
        public static void onAttachCapabilitiesPlayer(final AttachCapabilitiesEvent<Entity> event) {
            if (event.getObject() instanceof FishingHook) {
                FishingHookLogic.attachCap(event);
            }
        }

        @SubscribeEvent
        public static void onAddReloadListeners(final AddReloadListenerEvent event) {
            event.addListener(FishBehaviorReloadListener.create());
        }
    }
}