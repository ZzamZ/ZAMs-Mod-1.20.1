package net.zam.zammod.registry;

import it.unimi.dsi.fastutil.objects.ObjectArraySet;
import java.util.Set;
import java.util.stream.Stream;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.properties.BlockSetType;

public class ZAMBlockSetTypes {
    private static final Set<BlockSetType> VALUES = new ObjectArraySet<>();
    public static final BlockSetType COPPER;

    private ZAMBlockSetTypes() {
        // Private constructor to prevent instantiation
    }

    public static BlockSetType register(BlockSetType value) {
        VALUES.add(value);
        return value;
    }

    public static Stream<BlockSetType> values() {
        return VALUES.stream();
    }

    static {
        COPPER = register(new BlockSetType(
                "copper",
                true,
                SoundType.METAL,
                ZAMSounds.BLOCK_COPPER_DOOR_CLOSE.get(),
                ZAMSounds.BLOCK_COPPER_TRAPDOOR_CLOSE.get(),
                ZAMSounds.BLOCK_COPPER_DOOR_OPEN.get(),
                ZAMSounds.BLOCK_COPPER_TRAPDOOR_OPEN.get(),
                SoundEvents.IRON_DOOR_OPEN,
                SoundEvents.IRON_DOOR_CLOSE,
                SoundEvents.IRON_TRAPDOOR_OPEN,
                SoundEvents.IRON_TRAPDOOR_CLOSE
        ));
    }
}
