package net.zam.zammod.registry;

import net.minecraft.core.particles.ParticleType;
import net.minecraft.core.particles.SimpleParticleType;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import net.zam.zammod.ZAMMod;

public class ZAMParticles {
    public static final DeferredRegister<ParticleType<?>> PARTICLE_TYPES =
            DeferredRegister.create(ForgeRegistries.PARTICLE_TYPES, ZAMMod.MOD_ID);

    public static final RegistryObject<SimpleParticleType> YELLOW_BUBBLES = PARTICLE_TYPES.register("yellow_bubbles",
            () -> new SimpleParticleType(true));

    public static final RegistryObject<SimpleParticleType> PURPLE_BUBBLES = PARTICLE_TYPES.register("purple_bubbles",
            () -> new SimpleParticleType(true));

    public static final RegistryObject<SimpleParticleType> WHITE_BUBBLES = PARTICLE_TYPES.register("white_bubbles",
            () -> new SimpleParticleType(true));

    public static void register(IEventBus eventBus) {
        PARTICLE_TYPES.register(eventBus);
    }
}
