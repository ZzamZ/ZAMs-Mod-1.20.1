package net.zam.zammod.registry;

import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import net.zam.zammod.ZAMMod;
import net.zam.zammod.entity.AquamarineFishingHook;
import net.zam.zammod.entity.mob.OtterEntity;

public class ZAMEntities {
    public static final DeferredRegister<EntityType<?>> ENTITY_TYPES =
            DeferredRegister.create(ForgeRegistries.ENTITY_TYPES, ZAMMod.MOD_ID);

    public static final RegistryObject<EntityType<OtterEntity>> OTTER =
            ENTITY_TYPES.register("otter", () -> EntityType.Builder.of(OtterEntity::new, MobCategory.WATER_CREATURE)
                    .sized(0.6F, 0.3F).build("otter"));

    public static final RegistryObject<EntityType<AquamarineFishingHook>> AQUAMARINE_FISHING_HOOK = ENTITY_TYPES.register("aquamarine_fishing_hook",
            () -> EntityType.Builder.<AquamarineFishingHook>of(AquamarineFishingHook::new, MobCategory.MISC)
                    .sized(0.25F, 0.25F)
                    .build(ZAMMod.MOD_ID + ":aquamarine_fishing_hook"));

    public static void register(IEventBus eventBus) {
        ENTITY_TYPES.register(eventBus);
    }
}
