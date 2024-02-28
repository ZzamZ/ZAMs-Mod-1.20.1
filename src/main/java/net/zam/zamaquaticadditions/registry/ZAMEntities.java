package net.zam.zamaquaticadditions.registry;

import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.minecraftforge.event.entity.EntityAttributeCreationEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import net.zam.zamaquaticadditions.ZAMAquaticAdditions;
import net.zam.zamaquaticadditions.entity.animal.Koi;

@Mod.EventBusSubscriber(modid = ZAMAquaticAdditions.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)

public class ZAMEntities {

    public static final DeferredRegister<EntityType<?>> ENTITY_TYPES =
            DeferredRegister.create(ForgeRegistries.ENTITY_TYPES, ZAMAquaticAdditions.MOD_ID);


    public static final RegistryObject<EntityType<Koi>> KOI =
            ENTITY_TYPES.register("koi", () -> EntityType.Builder.<Koi>of(Koi::new, MobCategory.WATER_AMBIENT)
                    .sized(0.6F, 0.3F).build("koi"));



    @SubscribeEvent
    public static void registerAttributes(EntityAttributeCreationEvent event) {
        event.put(KOI.get(), Koi.createAttributes().build());

    }


    public static void register(IEventBus eventBus) {
        ENTITY_TYPES.register(eventBus);
    }
}
