package net.zam.zamaquaticadditions;

import com.mojang.logging.LogUtils;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.animal.FrogVariant;
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
import net.minecraftforge.fml.event.config.ModConfigEvent;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;
import net.zam.zamaquaticadditions.block.chest.ClientHandler;
import net.zam.zamaquaticadditions.entity.model.KoiModel;
import net.zam.zamaquaticadditions.entity.renderer.KoiRenderer;
import net.zam.zamaquaticadditions.entity.renderer.skins.Frog;
import net.zam.zamaquaticadditions.item.albums.PokemonAlbumCase;
import net.zam.zamaquaticadditions.potion.BetterBrewingRecipe;
import net.zam.zamaquaticadditions.registry.*;
import net.zam.zamaquaticadditions.util.config.ConfigHolder;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@Mod(ZAMAquaticAdditions.MOD_ID)
public class ZAMAquaticAdditions {
    public static final String MOD_ID = "zamaquaticadditions";

    public static ResourceLocation id(String path) {
        return new ResourceLocation(MOD_ID, path);
    }
    public static final Logger LOGGER = LogManager.getLogger();
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
        FROG_VARIANTS.register(modEventBus);
        modEventBus.addListener(this::commonSetup);
        modEventBus.addListener(this::setup);
        modEventBus.addListener(this::setupClient);
        modEventBus.addListener(this::reloadConfigs);


        MinecraftForge.EVENT_BUS.register(this);
        DistExecutor.unsafeRunWhenOn(Dist.CLIENT, () -> () -> {
            modEventBus.addListener(this::registerRenderers);
            modEventBus.addListener(this::registerLayerDefinitions);

        });
        context.registerConfig(ModConfig.Type.COMMON, ZAMConfig.COMMON_SPEC);

    }


    public static final DeferredRegister<FrogVariant> FROG_VARIANTS = DeferredRegister.create(Registries.FROG_VARIANT, ZAMAquaticAdditions.MOD_ID);
    public static final RegistryObject<FrogVariant> ANCIENT_FROG = FROG_VARIANTS.register("ancient_frog", () -> new FrogVariant(new ResourceLocation(ZAMAquaticAdditions.MOD_ID, "textures/entity/frog/ancient_frog.png")));
    public static final RegistryObject<FrogVariant> PURPUR_FROG = FROG_VARIANTS.register("purpur_frog", () -> new FrogVariant(new ResourceLocation(ZAMAquaticAdditions.MOD_ID, "textures/entity/frog/purpur_frog.png")));
    public static final RegistryObject<FrogVariant> STRAWBERRY_FROG = FROG_VARIANTS.register("strawberry_frog", () -> new FrogVariant(new ResourceLocation(ZAMAquaticAdditions.MOD_ID, "textures/entity/frog/strawberry_frog.png")));


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

    public void reloadConfigs(ModConfigEvent event) {
        if (event.getConfig().getSpec() == ConfigHolder.SERVER_SPEC) {
            ZAMConfig.bakeServer();
            LOGGER.debug("Reloading ZAM Server Config!");
        }
        if (event.getConfig().getSpec() == ConfigHolder.CLIENT_SPEC) {
            ZAMConfig.bakeClient();
            LOGGER.debug("Reloading ZAM Client Config!");
        }
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
