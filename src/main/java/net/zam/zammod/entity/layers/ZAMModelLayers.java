package net.zam.zammod.entity.layers;

import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.resources.ResourceLocation;
import net.zam.zammod.ZAMMod;

public class ZAMModelLayers {
    public static final ModelLayerLocation OTTER_LAYER = new ModelLayerLocation(
            new ResourceLocation(ZAMMod.MOD_ID, "otter_layer"), "otter_layer");
    public static final ModelLayerLocation WOLF_ARMOR_LAYER = new ModelLayerLocation(
            new ResourceLocation(ZAMMod.MOD_ID, "wolf_armor_layer"), "wolf_armor_layer");
}
