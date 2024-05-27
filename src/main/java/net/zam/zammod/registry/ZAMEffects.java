package net.zam.zammod.registry;

import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import net.zam.zammod.ZAMMod;
//import net.zam.zammod.effect.GumGumCurseEffect;
import net.zam.zammod.effect.*;

public class ZAMEffects {

    public static final DeferredRegister<MobEffect> MOB_EFFECTS =
            DeferredRegister.create(ForgeRegistries.MOB_EFFECTS, ZAMMod.MOD_ID);

   // public static final RegistryObject<MobEffect> GUM_GUM_CURSE = MOB_EFFECTS.register("gum_gum_curse",
   //         () -> new GumGumCurseEffect(MobEffectCategory.NEUTRAL, 0x36ebab));

    public static final RegistryObject<MobEffect> FREEZE = MOB_EFFECTS.register("freeze",
            () -> new FreezeEffect(MobEffectCategory.BENEFICIAL, rawColorFromRGB(143, 181, 246)));

    public static final RegistryObject<MobEffect> PHANTOM = MOB_EFFECTS.register("phantom",
            () -> new PhantomEffect(MobEffectCategory.BENEFICIAL, rawColorFromRGB(58, 70, 123)));

    public static final RegistryObject<MobEffect> ATTRACT = MOB_EFFECTS.register("attract",
            () -> new AttractEffect(MobEffectCategory.BENEFICIAL, rawColorFromRGB(170, 14,1)));

    public static final RegistryObject<MobEffect> WITHER = MOB_EFFECTS.register("wither",
            () -> new WitherEffect(MobEffectCategory.BENEFICIAL, rawColorFromRGB(29, 5, 3)));

    public static final RegistryObject<MobEffect> MIPHAS_GRACE_EFFECT = MOB_EFFECTS.register("miphas_grace",
            () -> new MiphasGraceEffect(MobEffectCategory.BENEFICIAL, 0xADD8E6));

    public static final RegistryObject<MobEffect> FLIGHT = MOB_EFFECTS.register("flight",
            () -> new FlightEffect(MobEffectCategory.BENEFICIAL, rawColorFromRGB(199, 183, 255)));

    public static final RegistryObject<MobEffect> FADED_EFFECT = MOB_EFFECTS.register("faded", FadedEffect::new);


    public static int rawColorFromRGB(int red, int green, int blue) {
        int rgb = Math.max(Math.min(0xFF, red), 0);
        rgb = (rgb << 8) + Math.max(Math.min(0xFF, green), 0);
        rgb = (rgb << 8) + Math.max(Math.min(0xFF, blue), 0);
        return rgb;
    }

    public static void register(IEventBus eventBus) {
        MOB_EFFECTS.register(eventBus);
    }

}
