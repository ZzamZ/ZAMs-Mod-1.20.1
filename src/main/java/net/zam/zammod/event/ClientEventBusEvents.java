package net.zam.zammod.event;

import net.minecraft.advancements.CriteriaTriggers;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.EntityRenderersEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.zam.zammod.ZAMMod;
import net.zam.zammod.advancement.FishingCatchTrigger;
import net.zam.zammod.entity.layers.ZAMModelLayers;

@Mod.EventBusSubscriber(modid = ZAMMod.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public class ClientEventBusEvents {

    @SubscribeEvent
    public static void registerAdvancementTriggers(FMLClientSetupEvent event) {
        CriteriaTriggers.register(FishingCatchTrigger.INSTANCE);
    }
}