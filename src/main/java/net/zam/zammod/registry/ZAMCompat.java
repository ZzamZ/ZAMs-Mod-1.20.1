package net.zam.zammod.registry;

import net.minecraft.core.dispenser.DispenseItemBehavior;
import net.minecraft.world.level.block.DispenserBlock;
import net.zam.zammod.block.dispenser.AquamarineBucketDispenseBehavior;
import net.zam.zammod.block.dispenser.FilledAquamarineBucketDispenseBehavior;

public class ZAMCompat {

    public static void registerCompat() {
        registerDispenserBehaviors();
    }

    private static void registerDispenserBehaviors() {
        DispenserBlock.registerBehavior(ZAMItems.AQUAMARINE_BUCKET.get(), new AquamarineBucketDispenseBehavior());
        DispenseItemBehavior goldenBucketDispenseBehavior = new FilledAquamarineBucketDispenseBehavior();
        DispenserBlock.registerBehavior(ZAMItems.AQUAMARINE_LAVA_BUCKET.get(), goldenBucketDispenseBehavior);
        DispenserBlock.registerBehavior(ZAMItems.AQUAMARINE_WATER_BUCKET.get(), goldenBucketDispenseBehavior);
        DispenserBlock.registerBehavior(ZAMItems.AQUAMARINE_POWDER_SNOW_BUCKET.get(), goldenBucketDispenseBehavior);
    }
}
