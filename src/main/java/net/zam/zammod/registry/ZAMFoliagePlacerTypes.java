package net.zam.zammod.registry;

import net.minecraft.core.registries.Registries;
import net.minecraft.world.level.levelgen.feature.foliageplacers.FoliagePlacerType;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;
import net.zam.zammod.ZAMMod;
import net.zam.zammod.worldgen.foliageplacers.AvocadoFoliagePlacer;

public class ZAMFoliagePlacerTypes {
    public static final DeferredRegister<FoliagePlacerType<?>> FOLIAGE_PLACERS =
            DeferredRegister.create(Registries.FOLIAGE_PLACER_TYPE, ZAMMod.MOD_ID);

    public static final RegistryObject<FoliagePlacerType<AvocadoFoliagePlacer>> AVOCADO_FOLIAGE_PLACER =
            FOLIAGE_PLACERS.register("walnut_foliage_placer", () -> new FoliagePlacerType<>(AvocadoFoliagePlacer.CODEC));

    public static void register(IEventBus eventBus) {
        FOLIAGE_PLACERS.register(eventBus);
    }
}