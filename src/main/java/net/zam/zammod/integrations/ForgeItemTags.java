package net.zam.zammod.integrations;

import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;

public class ForgeItemTags {
    public static final TagKey<Item> OPAL = bind("gems/opal");
    public static final TagKey<Item> AQUAMARINE = bind("gems/aquamarine");

    private static TagKey<Item> bind(String path) {
        return TagKey.create(Registries.ITEM, new ResourceLocation("forge", path));
    }
}
