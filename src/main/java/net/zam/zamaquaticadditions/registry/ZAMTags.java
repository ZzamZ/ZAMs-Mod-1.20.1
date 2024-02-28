package net.zam.zamaquaticadditions.registry;

import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.TagKey;
import net.minecraft.world.level.block.Block;

public class ZAMTags {

    public static final TagKey<Block> SCAFFOLDING = registerBlockTag("scaffolding");



    private static TagKey<Block> registerBlockTag(String name) {
        return TagKey.create(Registries.BLOCK, new ResourceLocation("zamaquaticadditions", name));
    }
}
