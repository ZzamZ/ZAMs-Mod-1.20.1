package net.zam.zammod.event;

import net.minecraft.advancements.CriteriaTriggers;
import net.minecraft.world.entity.npc.WanderingTrader;
import net.minecraftforge.client.event.EntityRenderersEvent;
import net.minecraftforge.event.entity.EntityAttributeCreationEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegisterEvent;
import net.zam.zammod.ZAMMod;
import net.zam.zammod.advancement.FishingCatchTrigger;
import net.zam.zammod.entity.mob.LostMerchantEntity;
//import net.zam.zammod.entity.mob.OtterEntity;
//import net.zam.zammod.entity.client.OtterModel;
import net.zam.zammod.entity.layers.ZAMModelLayers;
import net.zam.zammod.loot.DungeonChests;
import net.zam.zammod.loot.HopSeeds;
import net.zam.zammod.recipes.KegRecipe;
import net.zam.zammod.registry.ZAMEntities;

@Mod.EventBusSubscriber(modid = ZAMMod.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class ZAMEventBusEvents {
   // @SubscribeEvent
   // public static void registerLayers(EntityRenderersEvent.RegisterLayerDefinitions event) {
  //      event.registerLayerDefinition(ZAMModelLayers.OTTER_LAYER, OtterModel::createBodyLayer);
  //  }

  //  @SubscribeEvent
  //  public static void registerAttributes(EntityAttributeCreationEvent event) {
   //     event.put(ZAMEntities.OTTER.get(), OtterEntity.createAttributes().build());
  //  }

    @SubscribeEvent
    public static void registerRecipeTypes(final RegisterEvent event) {
        event.register(ForgeRegistries.Keys.RECIPE_TYPES, (helper) -> {
            helper.register(KegRecipe.Type.ID, KegRecipe.Type.INSTANCE);
        });
    }

    @SubscribeEvent
    public static void registerAdvancementTriggers(FMLCommonSetupEvent event) {
        CriteriaTriggers.register(FishingCatchTrigger.INSTANCE);
    }

    @SubscribeEvent
    public static void registerModifierSerializers(final RegisterEvent event) {
        event.register(ForgeRegistries.Keys.GLOBAL_LOOT_MODIFIER_SERIALIZERS, (helper) -> {
            helper.register("hop_seeds", HopSeeds.CODEC);
            helper.register("add_item", DungeonChests.CODEC);
        });
    }
}
