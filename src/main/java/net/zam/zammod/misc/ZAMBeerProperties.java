package net.zam.zammod.misc;

import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.food.FoodProperties;
import net.zam.zammod.registry.ZAMEffects;

public class ZAMBeerProperties {
    public static final FoodProperties CHORUS_ALE = new FoodProperties.Builder()
            .nutrition(5)
            .saturationMod(0.6f)
            .alwaysEat()
            .effect(() -> new MobEffectInstance(MobEffects.LEVITATION, 1200, 0), 1.0f)
            .effect(() -> new MobEffectInstance(MobEffects.CONFUSION, 400, 0), 1.0f)
            .build();

    public static final FoodProperties DIGGER_BITTER = new FoodProperties.Builder()
            .nutrition(5)
            .saturationMod(0.6f)
            .alwaysEat()
            .effect(() -> new MobEffectInstance(MobEffects.DIG_SPEED, 1200, 1), 1.0f)
            .effect(() -> new MobEffectInstance(MobEffects.CONFUSION, 400, 0), 1.0f)
            .build();

    public static final FoodProperties DROWNED_ALE = new FoodProperties.Builder()
            .nutrition(5)
            .saturationMod(0.6f)
            .alwaysEat()
            .effect(() -> new MobEffectInstance(MobEffects.WATER_BREATHING, 6000, 0), 1.0f)
            .effect(() -> new MobEffectInstance(MobEffects.DOLPHINS_GRACE, 3000, 0), 1.0f)
            .effect(() -> new MobEffectInstance(MobEffects.CONFUSION, 400, 0), 1.0f)
            .build();

    public static final FoodProperties ICE_BEER = new FoodProperties.Builder()
            .nutrition(5)
            .saturationMod(0.6f)
            .alwaysEat()
            .effect(() -> new MobEffectInstance(ZAMEffects.FREEZE.get(), 6000, 0), 1.0f)
            .effect(() -> new MobEffectInstance(MobEffects.CONFUSION, 400, 0), 1.0f)
            .build();

    public static final FoodProperties KVASS = new FoodProperties.Builder()
            .nutrition(5)
            .saturationMod(0.6f)
            .alwaysEat()
            .effect(() -> new MobEffectInstance(MobEffects.SATURATION, 2400, 0), 1.0f)
            .effect(() -> new MobEffectInstance(MobEffects.HEALTH_BOOST, 600, 1), 1.0f)
            .effect(() -> new MobEffectInstance(MobEffects.CONFUSION, 400, 0), 1.0f)
            .build();

    public static final FoodProperties LEPRECHAUN_CIDER = new FoodProperties.Builder()
            .nutrition(5)
            .saturationMod(0.6f)
            .alwaysEat()
            .effect(() -> new MobEffectInstance(MobEffects.LUCK, 1200, 0), 1.0f)
            .effect(() -> new MobEffectInstance(MobEffects.CONFUSION, 400, 0), 1.0f)
            .build();

    public static final FoodProperties MAGNET_PILSNER = new FoodProperties.Builder()
            .nutrition(5)
            .saturationMod(0.6f)
            .alwaysEat()
            .effect(() -> new MobEffectInstance(ZAMEffects.ATTRACT.get(), 6000, 0), 1.0f)
            .effect(() -> new MobEffectInstance(MobEffects.CONFUSION, 400, 0), 1.0f)
            .build();

    public static final FoodProperties NETHER_PORTER = new FoodProperties.Builder()
            .nutrition(5)
            .saturationMod(0.6f)
            .alwaysEat()
            .effect(() -> new MobEffectInstance(MobEffects.FIRE_RESISTANCE, 6000, 1), 1.0f)
            .effect(() -> new MobEffectInstance(MobEffects.CONFUSION, 400, 0), 1.0f)
            .build();

    public static final FoodProperties NIGHT_RAUCH = new FoodProperties.Builder()
            .nutrition(5)
            .saturationMod(0.6f)
            .alwaysEat()
            .effect(() -> new MobEffectInstance(ZAMEffects.PHANTOM.get(), 12000, 0), 1.0f)
            .effect(() -> new MobEffectInstance(MobEffects.NIGHT_VISION, 2400, 0), 1.0f)
            .effect(() -> new MobEffectInstance(MobEffects.CONFUSION, 400, 0), 1.0f)
            .build();

    public static final FoodProperties SUN_PALE_ALE = new FoodProperties.Builder()
            .nutrition(5)
            .saturationMod(0.6f)
            .alwaysEat()
            .effect(() -> new MobEffectInstance(MobEffects.REGENERATION, 3600, 0), 1.0f)
            .build();

    public static final FoodProperties WITHER_STOUT = new FoodProperties.Builder()
            .nutrition(5)
            .saturationMod(0.6f)
            .alwaysEat()
            .effect(() -> new MobEffectInstance(ZAMEffects.WITHER.get(), 2400, 0), 1.0f)
            .effect(() -> new MobEffectInstance(MobEffects.CONFUSION, 400, 0), 1.0f)
            .build();

    public static final FoodProperties NIMBUS_NECTAR = new FoodProperties.Builder()
            .nutrition(5)
            .saturationMod(0.6f)
            .alwaysEat()
            .effect(() -> new MobEffectInstance(ZAMEffects.FLIGHT.get(), 6000, 0), 1.0f)
            .effect(() -> new MobEffectInstance(MobEffects.CONFUSION, 400, 0), 1.0f)
            .build();
    public static final FoodProperties HOP = new FoodProperties.Builder()
            .nutrition(5)
            .saturationMod(0.6f)
            .fast()
            .effect(() -> new MobEffectInstance(MobEffects.CONFUSION, 250, 0), 1.0f)
            .effect(() -> new MobEffectInstance(MobEffects.DARKNESS, 100, 0), 1.0f)
            .build();
}