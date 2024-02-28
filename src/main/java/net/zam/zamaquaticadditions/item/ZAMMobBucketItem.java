package net.zam.zamaquaticadditions.item;

import net.minecraft.sounds.SoundEvent;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.MobBucketItem;
import net.minecraft.world.level.material.Fluid;
import net.zam.zamaquaticadditions.util.TargetedItemCategoryFiller;

import java.util.function.Supplier;

/**
 * A {@link MobBucketItem} extension that fills itself after the latest vanilla fish bucket item.
 */
public class ZAMMobBucketItem extends MobBucketItem {
    private static final TargetedItemCategoryFiller FILLER = new TargetedItemCategoryFiller(() -> Items.TADPOLE_BUCKET);

    public ZAMMobBucketItem(Supplier<? extends EntityType<?>> entitySupplier, Supplier<? extends Fluid> fluidSupplier, Supplier<? extends SoundEvent> soundSupplier, Properties properties) {
        super(entitySupplier, fluidSupplier, soundSupplier, properties);
    }
}