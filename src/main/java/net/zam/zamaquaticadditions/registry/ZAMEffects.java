package net.zam.zamaquaticadditions.registry;

import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import net.zam.zamaquaticadditions.ZAMAquaticAdditions;
//import net.zam.zamaquaticadditions.effect.GumGumCurseEffect;
import net.zam.zamaquaticadditions.effect.MiphasGraceEffect;

public class ZAMEffects {

    public static final DeferredRegister<MobEffect> MOB_EFFECTS =
            DeferredRegister.create(ForgeRegistries.MOB_EFFECTS, ZAMAquaticAdditions.MOD_ID);

   // public static final RegistryObject<MobEffect> GUM_GUM_CURSE = MOB_EFFECTS.register("gum_gum_curse",
   //         () -> new GumGumCurseEffect(MobEffectCategory.NEUTRAL, 0x36ebab));



    public static final RegistryObject<MobEffect> MIPHAS_GRACE_EFFECT = MOB_EFFECTS.register("miphas_grace",
            () -> new MiphasGraceEffect(MobEffectCategory.BENEFICIAL, 0xADD8E6));


    public static void register(IEventBus eventBus) {
        MOB_EFFECTS.register(eventBus);
    }

}
