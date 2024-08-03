package net.zam.zammod.item;

import net.minecraft.world.item.Item;

import java.util.Properties;

public class QualityItem extends Item {

    public final int fishQuality;

    public QualityItem(Properties properties, int fishQuality) {
        super(properties);
        this.fishQuality = fishQuality;
    }


}