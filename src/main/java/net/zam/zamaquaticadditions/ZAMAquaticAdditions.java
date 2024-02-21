package net.zam.zamaquaticadditions;

import com.mojang.logging.LogUtils;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.BuildCreativeModeTabContentsEvent;
import net.minecraftforge.event.server.ServerStartingEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.zam.zamaquaticadditions.registry.ZAMBlocks;
import net.zam.zamaquaticadditions.registry.ZAMEnchantments;
import net.zam.zamaquaticadditions.registry.ZAMItems;
import net.zam.zamaquaticadditions.registry.ZAMSounds;
import org.slf4j.Logger;

@Mod(ZAMAquaticAdditions.MOD_ID)
public class ZAMAquaticAdditions {
    public static final String MOD_ID = "zamaquaticadditions";
    private static final Logger LOGGER = LogUtils.getLogger();

    public ZAMAquaticAdditions() {
        IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();

        ZAMItems.register(modEventBus);
        ZAMBlocks.register(modEventBus);
        ZAMSounds.register(modEventBus);
        ZAMEnchantments.register(modEventBus);

        modEventBus.addListener(this::commonSetup);

        MinecraftForge.EVENT_BUS.register(this);

        modEventBus.addListener(this::addCreative);

    }

    private void commonSetup(final FMLCommonSetupEvent event) {

    }

    private void addCreative(BuildCreativeModeTabContentsEvent event) {

    }

    @SubscribeEvent
    public void onServerStarting(ServerStartingEvent event) {
    }

    @Mod.EventBusSubscriber(modid = MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
    public static class ClientModEvents {


    }
}
