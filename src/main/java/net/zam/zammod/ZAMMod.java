package net.zam.zammod;

import net.minecraft.advancements.CriteriaTriggers;
import net.minecraft.client.gui.screens.MenuScreens;
import net.minecraft.client.renderer.ItemBlockRenderTypes;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.EntityRenderers;
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
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.DistExecutor;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.config.ModConfig;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;
import net.zam.zammod.advancement.FishingCatchTrigger;
import net.zam.zammod.event.StardropEffectHandler;
import net.zam.zammod.gui.arcade.ArcadeManager;
import net.zam.zammod.block.chest.ClientHandler;
//import net.zam.zammod.entity.client.OtterRenderer;
import net.zam.zammod.entity.skins.FrogSkins;
import net.zam.zammod.entity.skins.WolfSkins;
import net.zam.zammod.event.ClientEventHandler;
import net.zam.zammod.gui.keg.KegScreen;
import net.zam.zammod.gui.MusicDiscLootBoxScreen;
//import net.zam.zammod.item.records.cases.CastleCrashersAlbumCase;
//import net.zam.zammod.item.records.cases.PokemonAlbumCase;
import net.zam.zammod.potion.BetterBrewingRecipe;
import net.zam.zammod.registry.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@Mod(ZAMMod.MOD_ID)
public class ZAMMod {

    public static final String MOD_ID = "zammod";
    public static final Logger LOGGER = LogManager.getLogger();
    ModLoadingContext context = ModLoadingContext.get();

    public ZAMMod() {
        IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();

        ZAMItems.register(modEventBus);
        ZAMBlocks.register(modEventBus);
        ZAMBlockEntities.register(modEventBus);
        ZAMEntities.register(modEventBus);
        ZAMSounds.register(modEventBus);
        ZAMPotions.register(modEventBus);
        ZAMCreativeModeTab.register(modEventBus);
        ZAMEffects.register(modEventBus);
        ZAMMenuTypes.register(modEventBus);
        ZAMParticles.register(modEventBus);
        ZAMRecipes.register(modEventBus);
        ZAMFoliagePlacerTypes.register(modEventBus);
        ZAMTrunkPlacerTypes.register(modEventBus);
        FROG_VARIANTS.register(modEventBus);
        modEventBus.addListener(this::setupClient);
        modEventBus.addListener(this::onCommonSetup);
        modEventBus.addListener(this::commonSetup);

        MinecraftForge.EVENT_BUS.register(this);
        MinecraftForge.EVENT_BUS.register(new StardropEffectHandler());
        DistExecutor.unsafeRunWhenOn(Dist.CLIENT, () -> () -> {
            modEventBus.addListener(this::registerRenderers);
            ArcadeManager.init();
        });
        context.registerConfig(ModConfig.Type.COMMON, ZAMConfig.spec);

    }

    public static final DeferredRegister<FrogVariant> FROG_VARIANTS = DeferredRegister.create(Registries.FROG_VARIANT, ZAMMod.MOD_ID);
    public static final RegistryObject<FrogVariant> ANCIENT_FROG = FROG_VARIANTS.register("ancient_frog", () -> new FrogVariant(new ResourceLocation(ZAMMod.MOD_ID, "textures/entity/frog/ancient_frog.png")));
    public static final RegistryObject<FrogVariant> PURPUR_FROG = FROG_VARIANTS.register("purpur_frog", () -> new FrogVariant(new ResourceLocation(ZAMMod.MOD_ID, "textures/entity/frog/purpur_frog.png")));
    public static final RegistryObject<FrogVariant> STRAWBERRY_FROG = FROG_VARIANTS.register("strawberry_frog", () -> new FrogVariant(new ResourceLocation(ZAMMod.MOD_ID, "textures/entity/frog/strawberry_frog.png")));


    private void setupClient(FMLClientSetupEvent event) {
        event.enqueueWork(ClientHandler::setupClient);
        ClientEventHandler.clientRegistry();


    }

    private void commonSetup(final FMLCommonSetupEvent event) {
        event.enqueueWork(() -> {
            BrewingRecipeRegistry.addRecipe(new BetterBrewingRecipe(Potions.AWKWARD, Items.PRISMARINE_CRYSTALS, ZAMPotions.MINING_FATIGUE_POTION.get()));
            BrewingRecipeRegistry.addRecipe(new BetterBrewingRecipe(ZAMPotions.MINING_FATIGUE_POTION.get(), Items.REDSTONE, ZAMPotions.LONG_MINING_FATIGUE_POTION.get()));
            BrewingRecipeRegistry.addRecipe(new BetterBrewingRecipe(Potions.AWKWARD, ZAMItems.EMERALD_SHARD.get(), ZAMPotions.HASTE_POTION.get()));
            BrewingRecipeRegistry.addRecipe(new BetterBrewingRecipe(ZAMPotions.HASTE_POTION.get(), Items.REDSTONE, ZAMPotions.LONG_HASTE_POTION.get()));
            BrewingRecipeRegistry.addRecipe(new BetterBrewingRecipe(ZAMPotions.HASTE_POTION.get(), Items.GLOWSTONE, ZAMPotions.POWERFUL_HASTE_POTION.get()));

        });
    }


    public void onCommonSetup(FMLCommonSetupEvent event) {
     //   event.enqueueWork(PokemonAlbumCase::initAllowedItems);
     //   event.enqueueWork(CastleCrashersAlbumCase::initAllowedItems);
    }

    public static ResourceLocation id(String s) {
        return new ResourceLocation(ZAMMod.MOD_ID);
    }


    private void registerRenderers(EntityRenderersEvent.RegisterRenderers event) {
        event.registerEntityRenderer(EntityType.FROG, FrogSkins::new);
        event.registerEntityRenderer(EntityType.WOLF, WolfSkins::new);
    }

    @Mod.EventBusSubscriber(modid = MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
    public static class ClientModEvents {
        @SubscribeEvent
        public static void onClientSetup(FMLClientSetupEvent event) {
            event.enqueueWork(() -> {
          //      EntityRenderers.register(ZAMEntities.OTTER.get(), OtterRenderer::new);
                MenuScreens.register(ZAMMenuTypes.KEG_MENU.get(), KegScreen::new);
                MenuScreens.register(ZAMMenuTypes.MUSIC_DISC_LOOT_BOX_MENU.get(), MusicDiscLootBoxScreen::new);
                ItemBlockRenderTypes.setRenderLayer(ZAMBlocks.KEG.get(), RenderType.cutout());
                ItemBlockRenderTypes.setRenderLayer(ZAMBlocks.HOP.get(), RenderType.cutout());
                ItemBlockRenderTypes.setRenderLayer(ZAMBlocks.HOP_PLANT.get(), RenderType.cutout());
                ItemBlockRenderTypes.setRenderLayer(ZAMBlocks.MUG_OF_SUN_PALE_ALE.get(), RenderType.cutout());
                ItemBlockRenderTypes.setRenderLayer(ZAMBlocks.MUG_EMPTY.get(), RenderType.cutout());
                ItemBlockRenderTypes.setRenderLayer(ZAMBlocks.COPPER_DOOR.get(), RenderType.cutout());
                ItemBlockRenderTypes.setRenderLayer(ZAMBlocks.COPPER_GRATE.get(), RenderType.cutout());
                ItemBlockRenderTypes.setRenderLayer(ZAMBlocks.COPPER_TRAPDOOR.get(), RenderType.cutout());
                ItemBlockRenderTypes.setRenderLayer(ZAMBlocks.EXPOSED_COPPER_DOOR.get(), RenderType.cutout());
                ItemBlockRenderTypes.setRenderLayer(ZAMBlocks.EXPOSED_COPPER_GRATE.get(), RenderType.cutout());
                ItemBlockRenderTypes.setRenderLayer(ZAMBlocks.EXPOSED_COPPER_TRAPDOOR.get(), RenderType.cutout());
                ItemBlockRenderTypes.setRenderLayer(ZAMBlocks.OXIDIZED_COPPER_DOOR.get(), RenderType.cutout());
                ItemBlockRenderTypes.setRenderLayer(ZAMBlocks.OXIDIZED_COPPER_GRATE.get(), RenderType.cutout());
                ItemBlockRenderTypes.setRenderLayer(ZAMBlocks.OXIDIZED_COPPER_TRAPDOOR.get(), RenderType.cutout());
                ItemBlockRenderTypes.setRenderLayer(ZAMBlocks.WEATHERED_COPPER_DOOR.get(), RenderType.cutout());
                ItemBlockRenderTypes.setRenderLayer(ZAMBlocks.WEATHERED_COPPER_GRATE.get(), RenderType.cutout());
                ItemBlockRenderTypes.setRenderLayer(ZAMBlocks.WEATHERED_COPPER_TRAPDOOR.get(), RenderType.cutout());
                ItemBlockRenderTypes.setRenderLayer(ZAMBlocks.WAXED_COPPER_DOOR.get(), RenderType.cutout());
                ItemBlockRenderTypes.setRenderLayer(ZAMBlocks.WAXED_COPPER_GRATE.get(), RenderType.cutout());
                ItemBlockRenderTypes.setRenderLayer(ZAMBlocks.WAXED_COPPER_TRAPDOOR.get(), RenderType.cutout());
                ItemBlockRenderTypes.setRenderLayer(ZAMBlocks.WAXED_EXPOSED_COPPER_DOOR.get(), RenderType.cutout());
                ItemBlockRenderTypes.setRenderLayer(ZAMBlocks.WAXED_EXPOSED_COPPER_GRATE.get(), RenderType.cutout());
                ItemBlockRenderTypes.setRenderLayer(ZAMBlocks.WAXED_EXPOSED_COPPER_TRAPDOOR.get(), RenderType.cutout());
                ItemBlockRenderTypes.setRenderLayer(ZAMBlocks.WAXED_OXIDIZED_COPPER_DOOR.get(), RenderType.cutout());
                ItemBlockRenderTypes.setRenderLayer(ZAMBlocks.WAXED_OXIDIZED_COPPER_GRATE.get(), RenderType.cutout());
                ItemBlockRenderTypes.setRenderLayer(ZAMBlocks.WAXED_OXIDIZED_COPPER_TRAPDOOR.get(), RenderType.cutout());
                ItemBlockRenderTypes.setRenderLayer(ZAMBlocks.WAXED_WEATHERED_COPPER_DOOR.get(), RenderType.cutout());
                ItemBlockRenderTypes.setRenderLayer(ZAMBlocks.WAXED_WEATHERED_COPPER_GRATE.get(), RenderType.cutout());
                ItemBlockRenderTypes.setRenderLayer(ZAMBlocks.WAXED_WEATHERED_COPPER_TRAPDOOR.get(), RenderType.cutout());
            });
        }
    }
}

