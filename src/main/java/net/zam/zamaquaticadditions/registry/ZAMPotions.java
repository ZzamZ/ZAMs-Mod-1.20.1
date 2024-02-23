package net.zam.zamaquaticadditions.registry;

import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.item.alchemy.Potion;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import net.zam.zamaquaticadditions.ZAMAquaticAdditions;

public class ZAMPotions {
    public static final DeferredRegister<Potion> POTIONS
            = DeferredRegister.create(ForgeRegistries.POTIONS, ZAMAquaticAdditions.MOD_ID);

    public static final RegistryObject<Potion> MINING_FATIGUE_POTION = POTIONS.register("mining_fatigue_potion",
            () -> new Potion(new MobEffectInstance(MobEffects.DIG_SLOWDOWN, 18000, 4)));
    public static final RegistryObject<Potion> LONG_MINING_FATIGUE_POTION = POTIONS.register("long_mining_fatigue_potion",
            () -> new Potion(new MobEffectInstance(MobEffects.DIG_SLOWDOWN, 36000, 4)));


    public static void register(IEventBus eventBus) {
        POTIONS.register(eventBus);
    }
}