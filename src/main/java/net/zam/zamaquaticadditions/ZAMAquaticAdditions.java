package net.zam.zamaquaticadditions;

import com.mojang.logging.LogUtils;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.alchemy.Potions;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.EntityRenderersEvent;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.brewing.BrewingRecipeRegistry;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.DistExecutor;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.config.ModConfig;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.zam.zamaquaticadditions.block.chest.ClientHandler;
import net.zam.zamaquaticadditions.entity.model.KoiModel;
import net.zam.zamaquaticadditions.entity.renderer.KoiRenderer;
import net.zam.zamaquaticadditions.entity.renderer.skins.Frog;
import net.zam.zamaquaticadditions.item.albums.PokemonAlbumCase;
import net.zam.zamaquaticadditions.potion.BetterBrewingRecipe;
import net.zam.zamaquaticadditions.registry.*;
import org.slf4j.Logger;

@Mod(ZAMAquaticAdditions.MOD_ID)
public class ZAMAquaticAdditions {
    public static final String MOD_ID = "zamaquaticadditions";

    public static ResourceLocation id(String path) {
        return new ResourceLocation(MOD_ID, path);
    }
    private static final Logger LOGGER = LogUtils.getLogger();
    ModLoadingContext context = ModLoadingContext.get();

    public ZAMAquaticAdditions() {
        IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();

        ZAMItems.register(modEventBus);
        ZAMBlocks.register(modEventBus);
        ZAMBlockEntities.register(modEventBus);
        ZAMSounds.register(modEventBus);
        ZAMEnchantments.register(modEventBus);
        ZAMPotions.register(modEventBus);
        ZAMCreativeModeTab.register(modEventBus);
        ZAMEntities.register(modEventBus);
        ZAMEffects.register(modEventBus);
        modEventBus.addListener(this::commonSetup);
        modEventBus.addListener(this::setup);
        modEventBus.addListener(this::setupClient);

        MinecraftForge.EVENT_BUS.register(this);
        DistExecutor.unsafeRunWhenOn(Dist.CLIENT, () -> () -> {
            modEventBus.addListener(this::registerRenderers);
            modEventBus.addListener(this::registerLayerDefinitions);

        });
        context.registerConfig(ModConfig.Type.COMMON, ZAMConfig.COMMON_SPEC);

    }

    private void commonSetup(final FMLCommonSetupEvent event) {
        PokemonAlbumCase.initAllowedItems();
    }

    private void setupClient(FMLClientSetupEvent event) {
        event.enqueueWork(ClientHandler::setupClient);
    }


    private void setup(final FMLCommonSetupEvent event) {
        event.enqueueWork(() -> {
            BrewingRecipeRegistry.addRecipe(new BetterBrewingRecipe(Potions.AWKWARD, Items.PRISMARINE_CRYSTALS, ZAMPotions.MINING_FATIGUE_POTION.get()));
            BrewingRecipeRegistry.addRecipe(new BetterBrewingRecipe(ZAMPotions.MINING_FATIGUE_POTION.get(), Items.REDSTONE, ZAMPotions.LONG_MINING_FATIGUE_POTION.get()));
        });
    }

    private void registerLayerDefinitions(EntityRenderersEvent.RegisterLayerDefinitions event) {
        event.registerLayerDefinition(KoiModel.LOCATION, KoiModel::createBodyLayer);
    }


    private void registerRenderers(EntityRenderersEvent.RegisterRenderers event) {
        event.registerEntityRenderer(ZAMEntities.KOI.get(), KoiRenderer::new);
        event.registerEntityRenderer(EntityType.FROG, Frog::new);

    }

    @Mod.EventBusSubscriber(modid = MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
    public static class ClientModEvents {


    }
}
