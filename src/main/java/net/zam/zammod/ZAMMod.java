package net.zam.zammod;

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
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;
import net.zam.zammod.arcade.ArcadeManager;
import net.zam.zammod.block.chest.ClientHandler;
import net.zam.zammod.entity.skins.FrogSkins;
import net.zam.zammod.entity.skins.WolfSkins;
import net.zam.zammod.event.ClientEventHandler;
import net.zam.zammod.item.records.cases.CastleCrashersAlbumCase;
import net.zam.zammod.item.records.cases.PokemonAlbumCase;
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
        ZAMSounds.register(modEventBus);
        ZAMEnchantments.register(modEventBus);
        ZAMPotions.register(modEventBus);
        ZAMCreativeModeTab.register(modEventBus);
        ZAMEffects.register(modEventBus);
        ZAMMenuTypes.register(modEventBus);
        FROG_VARIANTS.register(modEventBus);
        modEventBus.addListener(this::setupClient);
        modEventBus.addListener(this::onCommonSetup);
        modEventBus.addListener(this::commonSetup);

        ArcadeManager.init();

        MinecraftForge.EVENT_BUS.register(this);
        DistExecutor.unsafeRunWhenOn(Dist.CLIENT, () -> () -> {
            modEventBus.addListener(this::registerRenderers);

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
        event.enqueueWork(PokemonAlbumCase::initAllowedItems);
        event.enqueueWork(CastleCrashersAlbumCase::initAllowedItems);
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

    }
}
