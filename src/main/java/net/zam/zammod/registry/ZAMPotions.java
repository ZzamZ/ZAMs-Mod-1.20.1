package net.zam.zammod.registry;

import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.item.alchemy.Potion;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import net.zam.zammod.ZAMMod;

public class ZAMPotions {
    public static final DeferredRegister<Potion> POTIONS
            = DeferredRegister.create(ForgeRegistries.POTIONS, ZAMMod.MOD_ID);

    public static final RegistryObject<Potion> MINING_FATIGUE_POTION = POTIONS.register("mining_fatigue_potion",
            () -> new Potion(new MobEffectInstance(MobEffects.DIG_SLOWDOWN, 18000, 4)));
    public static final RegistryObject<Potion> LONG_MINING_FATIGUE_POTION = POTIONS.register("long_mining_fatigue_potion",
            () -> new Potion(new MobEffectInstance(MobEffects.DIG_SLOWDOWN, 36000, 4)));

    public static final RegistryObject<Potion> HASTE_POTION = POTIONS.register("haste_potion",
            () -> new Potion(new MobEffectInstance(MobEffects.DIG_SPEED, 3600, 1)));
    public static final RegistryObject<Potion> POWERFUL_HASTE_POTION = POTIONS.register("powerful_haste_potion",
            () -> new Potion(new MobEffectInstance(MobEffects.DIG_SPEED, 3600, 2)));
    public static final RegistryObject<Potion> LONG_HASTE_POTION = POTIONS.register("long_haste_potion",
            () -> new Potion(new MobEffectInstance(MobEffects.DIG_SPEED, 9600, 1)));

    public static void register(IEventBus eventBus) {
        POTIONS.register(eventBus);
    }
}