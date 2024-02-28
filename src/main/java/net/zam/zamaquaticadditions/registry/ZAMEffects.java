package net.zam.zamaquaticadditions.registry;

import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import net.zam.zamaquaticadditions.ZAMAquaticAdditions;
import net.zam.zamaquaticadditions.effect.SerenityEffect;

public class ZAMEffects {

    public static final DeferredRegister<MobEffect> MOB_EFFECTS =
            DeferredRegister.create(ForgeRegistries.MOB_EFFECTS, ZAMAquaticAdditions.MOD_ID);



    public static final RegistryObject<MobEffect> SERENITY = MOB_EFFECTS.register("serenity",
            () -> new SerenityEffect(MobEffectCategory.BENEFICIAL, 15494786));


    public static void register(IEventBus eventBus) {
        MOB_EFFECTS.register(eventBus);
    }

}
