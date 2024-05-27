package net.zam.zammod.registry;

import net.minecraft.core.registries.Registries;
import net.minecraft.world.level.levelgen.feature.trunkplacers.TrunkPlacerType;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;
import net.zam.zammod.ZAMMod;
import net.zam.zammod.worldgen.trunkplacers.CrossTrunkPlacer;
import net.zam.zammod.worldgen.trunkplacers.SlantedTrunkPlacer;

public class ZAMTrunkPlacerTypes {
    public static final DeferredRegister<TrunkPlacerType<?>> TRUNK_PLACERS =
            DeferredRegister.create(Registries.TRUNK_PLACER_TYPE, ZAMMod.MOD_ID);

    public static final RegistryObject<TrunkPlacerType<SlantedTrunkPlacer>> SLANTED_TRUNK_PLACER =
            TRUNK_PLACERS.register("slanted_trunk_placer", () -> new TrunkPlacerType<>(SlantedTrunkPlacer.CODEC));

    public static final RegistryObject<TrunkPlacerType<CrossTrunkPlacer>> CROSS_TRUNK_PLACER =
            TRUNK_PLACERS.register("cross_trunk_placer", () -> new TrunkPlacerType<>(CrossTrunkPlacer.CODEC));

    public static void register(IEventBus eventBus) {
        TRUNK_PLACERS.register(eventBus);
    }
}