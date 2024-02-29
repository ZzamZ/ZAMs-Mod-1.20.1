package net.zam.zamaquaticadditions.registry;

import net.minecraft.core.particles.ParticleType;
import net.minecraft.core.particles.SimpleParticleType;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import net.zam.zamaquaticadditions.ZAMAquaticAdditions;

public class ZAMParticles {
    public static final DeferredRegister<ParticleType<?>> PARTICLES = DeferredRegister.create(ForgeRegistries.PARTICLE_TYPES, ZAMAquaticAdditions.MOD_ID);




    public static final RegistryObject<SimpleParticleType> MILK_BUBBLE = PARTICLES.register("milk_bubble", () -> new SimpleParticleType(true));



    public static void register(IEventBus eventBus) {
        PARTICLES.register(eventBus);
    }

}
