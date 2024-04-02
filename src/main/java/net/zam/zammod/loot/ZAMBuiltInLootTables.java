package net.zam.zammod.loot;

import com.google.common.collect.Sets;
import java.util.Set;
import net.minecraft.resources.ResourceLocation;

public class ZAMBuiltInLootTables {
    private static final Set<ResourceLocation> LOCATIONS = Sets.newHashSet();

    public static ResourceLocation register(String pId) {
        return register(new ResourceLocation(pId));
    }

    static ResourceLocation register(ResourceLocation pId) {
        if (LOCATIONS.add(pId)) {
            return pId;
        } else {
            throw new IllegalArgumentException(pId + " is already a registered built-in loot table");
        }
    }
}
