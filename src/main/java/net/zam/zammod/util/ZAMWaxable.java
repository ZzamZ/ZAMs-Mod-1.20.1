package net.zam.zammod.util;

import com.google.common.base.Suppliers;
import com.google.common.collect.BiMap;
import com.google.common.collect.ImmutableBiMap;
import net.minecraft.world.level.block.Block;
import net.zam.zammod.registry.ZAMBlocks;

import java.util.function.Supplier;

public class ZAMWaxable {
    public static final Supplier<BiMap<Block, Block>> WAXABLES = Suppliers.memoize(() -> {
        return ImmutableBiMap.<Block, Block>builder()
                .put(ZAMBlocks.COPPER_DOOR.get(), ZAMBlocks.WAXED_COPPER_DOOR.get())
                .put(ZAMBlocks.EXPOSED_COPPER_DOOR.get(), ZAMBlocks.WAXED_EXPOSED_COPPER_DOOR.get())
                .put(ZAMBlocks.WEATHERED_COPPER_DOOR.get(), ZAMBlocks.WAXED_WEATHERED_COPPER_DOOR.get())
                .put(ZAMBlocks.OXIDIZED_COPPER_DOOR.get(), ZAMBlocks.WAXED_OXIDIZED_COPPER_DOOR.get())
                .put(ZAMBlocks.COPPER_TRAPDOOR.get(), ZAMBlocks.WAXED_COPPER_TRAPDOOR.get())
                .put(ZAMBlocks.EXPOSED_COPPER_TRAPDOOR.get(), ZAMBlocks.WAXED_EXPOSED_COPPER_TRAPDOOR.get())
                .put(ZAMBlocks.WEATHERED_COPPER_TRAPDOOR.get(), ZAMBlocks.WAXED_WEATHERED_COPPER_TRAPDOOR.get())
                .put(ZAMBlocks.OXIDIZED_COPPER_TRAPDOOR.get(), ZAMBlocks.WAXED_OXIDIZED_COPPER_TRAPDOOR.get())
                .put(ZAMBlocks.COPPER_GRATE.get(), ZAMBlocks.WAXED_COPPER_GRATE.get())
                .put(ZAMBlocks.EXPOSED_COPPER_GRATE.get(), ZAMBlocks.WAXED_EXPOSED_COPPER_GRATE.get())
                .put(ZAMBlocks.WEATHERED_COPPER_GRATE.get(), ZAMBlocks.WAXED_WEATHERED_COPPER_GRATE.get())
                .put(ZAMBlocks.OXIDIZED_COPPER_GRATE.get(), ZAMBlocks.WAXED_OXIDIZED_COPPER_GRATE.get())
                .put(ZAMBlocks.COPPER_BULB.get(), ZAMBlocks.WAXED_COPPER_BULB.get())
                .put(ZAMBlocks.EXPOSED_COPPER_BULB.get(), ZAMBlocks.WAXED_EXPOSED_COPPER_BULB.get())
                .put(ZAMBlocks.WEATHERED_COPPER_BULB.get(), ZAMBlocks.WAXED_WEATHERED_COPPER_BULB.get())
                .put(ZAMBlocks.OXIDIZED_COPPER_BULB.get(), ZAMBlocks.WAXED_OXIDIZED_COPPER_BULB.get())
                .put(ZAMBlocks.CHISELED_COPPER.get(), ZAMBlocks.WAXED_CHISELED_COPPER.get())
                .put(ZAMBlocks.EXPOSED_CHISELED_COPPER.get(), ZAMBlocks.WAXED_EXPOSED_CHISELED_COPPER.get())
                .put(ZAMBlocks.WEATHERED_CHISELED_COPPER.get(), ZAMBlocks.WAXED_WEATHERED_CHISELED_COPPER.get())
                .put(ZAMBlocks.OXIDIZED_CHISELED_COPPER.get(), ZAMBlocks.WAXED_OXIDIZED_CHISELED_COPPER.get())
                .build();
    });

    public static final Supplier<BiMap<Block, Block>> SCRAPABLE = Suppliers.memoize(() -> {
        return ImmutableBiMap.<Block, Block>builder()
                .put(ZAMBlocks.WAXED_COPPER_DOOR.get(), ZAMBlocks.COPPER_DOOR.get())
                .put(ZAMBlocks.WAXED_EXPOSED_COPPER_DOOR.get(), ZAMBlocks.EXPOSED_COPPER_DOOR.get())
                .put(ZAMBlocks.WAXED_WEATHERED_COPPER_DOOR.get(), ZAMBlocks.WEATHERED_COPPER_DOOR.get())
                .put(ZAMBlocks.WAXED_OXIDIZED_COPPER_DOOR.get(), ZAMBlocks.OXIDIZED_COPPER_DOOR.get())
                .put(ZAMBlocks.WAXED_COPPER_TRAPDOOR.get(), ZAMBlocks.COPPER_TRAPDOOR.get())
                .put(ZAMBlocks.WAXED_EXPOSED_COPPER_TRAPDOOR.get(), ZAMBlocks.EXPOSED_COPPER_TRAPDOOR.get())
                .put(ZAMBlocks.WAXED_WEATHERED_COPPER_TRAPDOOR.get(), ZAMBlocks.WEATHERED_COPPER_TRAPDOOR.get())
                .put(ZAMBlocks.WAXED_OXIDIZED_COPPER_TRAPDOOR.get(), ZAMBlocks.OXIDIZED_COPPER_TRAPDOOR.get())
                .put(ZAMBlocks.WAXED_COPPER_GRATE.get(), ZAMBlocks.COPPER_GRATE.get())
                .put(ZAMBlocks.WAXED_EXPOSED_COPPER_GRATE.get(), ZAMBlocks.EXPOSED_COPPER_GRATE.get())
                .put(ZAMBlocks.WAXED_WEATHERED_COPPER_GRATE.get(), ZAMBlocks.WEATHERED_COPPER_GRATE.get())
                .put(ZAMBlocks.WAXED_OXIDIZED_COPPER_GRATE.get(), ZAMBlocks.OXIDIZED_COPPER_GRATE.get())
                .put(ZAMBlocks.WAXED_COPPER_BULB.get(), ZAMBlocks.COPPER_BULB.get())
                .put(ZAMBlocks.WAXED_EXPOSED_COPPER_BULB.get(), ZAMBlocks.EXPOSED_COPPER_BULB.get())
                .put(ZAMBlocks.WAXED_WEATHERED_COPPER_BULB.get(), ZAMBlocks.WEATHERED_COPPER_BULB.get())
                .put(ZAMBlocks.WAXED_OXIDIZED_COPPER_BULB.get(), ZAMBlocks.OXIDIZED_COPPER_BULB.get())
                .put(ZAMBlocks.WAXED_CHISELED_COPPER.get(), ZAMBlocks.CHISELED_COPPER.get())
                .put(ZAMBlocks.WAXED_EXPOSED_CHISELED_COPPER.get(), ZAMBlocks.EXPOSED_CHISELED_COPPER.get())
                .put(ZAMBlocks.WAXED_WEATHERED_CHISELED_COPPER.get(), ZAMBlocks.WEATHERED_CHISELED_COPPER.get())
                .put(ZAMBlocks.WAXED_OXIDIZED_CHISELED_COPPER.get(), ZAMBlocks.OXIDIZED_CHISELED_COPPER.get())
                .build();
    });
}
