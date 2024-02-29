package net.zam.zamaquaticadditions.registry;

import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.TagKey;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.block.Block;
import net.zam.zamaquaticadditions.ZAMAquaticAdditions;

import java.util.Locale;

public class ZAMTags {

    public static class Biomes {
        public static final TagKey<Biome> SPAWNS_ANCIENT_VARIANT_FROGS = tag("spawns_ancient_variant_frogs");
        public static final TagKey<Biome> SPAWNS_PURPUR_VARIANT_FROGS = tag("spawns_purpur_variant_frogs");
        public static final TagKey<Biome> SPAWNS_STRAWBERRY_VARIANT_FROGS = tag("spawns_strawberry_variant_frogs");

        private static TagKey<Biome> tag(String name) {
            return TagKey.create(Registries.BIOME, new ResourceLocation(ZAMAquaticAdditions.MOD_ID, name.toLowerCase(Locale.ROOT)));

        }
    }
}
