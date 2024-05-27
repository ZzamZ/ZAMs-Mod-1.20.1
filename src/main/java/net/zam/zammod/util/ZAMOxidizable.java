package net.zam.zammod.util;

import com.google.common.base.Suppliers;
import com.google.common.collect.BiMap;
import com.google.common.collect.ImmutableBiMap;
import java.util.Optional;
import java.util.function.Supplier;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.ChangeOverTimeBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.zam.zammod.registry.ZAMBlocks;
import org.jetbrains.annotations.NotNull;

public interface ZAMOxidizable extends ChangeOverTimeBlock<ZAMOxidizable.CopperOxidizableLevel> {
    Supplier<BiMap<Block, Block>> NEXT_BY_BLOCK = Suppliers.memoize(() -> {
        return ImmutableBiMap.<Block, Block>builder()
                .put(ZAMBlocks.COPPER_DOOR.get(), ZAMBlocks.EXPOSED_COPPER_DOOR.get())
                .put(ZAMBlocks.EXPOSED_COPPER_DOOR.get(), ZAMBlocks.WEATHERED_COPPER_DOOR.get())
                .put(ZAMBlocks.WEATHERED_COPPER_DOOR.get(), ZAMBlocks.OXIDIZED_COPPER_DOOR.get())
                .put(ZAMBlocks.COPPER_TRAPDOOR.get(), ZAMBlocks.EXPOSED_COPPER_TRAPDOOR.get())
                .put(ZAMBlocks.EXPOSED_COPPER_TRAPDOOR.get(), ZAMBlocks.WEATHERED_COPPER_TRAPDOOR.get())
                .put(ZAMBlocks.WEATHERED_COPPER_TRAPDOOR.get(), ZAMBlocks.OXIDIZED_COPPER_TRAPDOOR.get())
                .put(ZAMBlocks.COPPER_GRATE.get(), ZAMBlocks.EXPOSED_COPPER_GRATE.get())
                .put(ZAMBlocks.EXPOSED_COPPER_GRATE.get(), ZAMBlocks.WEATHERED_COPPER_GRATE.get())
                .put(ZAMBlocks.WEATHERED_COPPER_GRATE.get(), ZAMBlocks.OXIDIZED_COPPER_GRATE.get())
                .put(ZAMBlocks.COPPER_BULB.get(), ZAMBlocks.EXPOSED_COPPER_BULB.get())
                .put(ZAMBlocks.EXPOSED_COPPER_BULB.get(), ZAMBlocks.WEATHERED_COPPER_BULB.get())
                .put(ZAMBlocks.WEATHERED_COPPER_BULB.get(), ZAMBlocks.OXIDIZED_COPPER_BULB.get())
                .put(ZAMBlocks.CHISELED_COPPER.get(), ZAMBlocks.EXPOSED_CHISELED_COPPER.get())
                .put(ZAMBlocks.EXPOSED_CHISELED_COPPER.get(), ZAMBlocks.WEATHERED_CHISELED_COPPER.get())
                .put(ZAMBlocks.WEATHERED_CHISELED_COPPER.get(), ZAMBlocks.OXIDIZED_CHISELED_COPPER.get())
                .build();
    });

    Supplier<BiMap<Block, Block>> PREVIOUS_BY_BLOCK = Suppliers.memoize(() -> NEXT_BY_BLOCK.get().inverse());

    static Optional<Block> getPrevious(Block block) {
        return Optional.ofNullable(PREVIOUS_BY_BLOCK.get().get(block));
    }

    static Block getFirst(Block block) {
        Block firstBlock = block;
        Block previousBlock = PREVIOUS_BY_BLOCK.get().get(block);

        while (previousBlock != null) {
            firstBlock = previousBlock;
            previousBlock = PREVIOUS_BY_BLOCK.get().get(previousBlock);
        }

        return firstBlock;
    }

    static Optional<BlockState> getPrevious(BlockState state) {
        return getPrevious(state.getBlock()).map(Block::defaultBlockState);
    }

    static Optional<Block> getNext(Block block) {
        return Optional.ofNullable(NEXT_BY_BLOCK.get().get(block));
    }

    static BlockState getFirst(BlockState state) {
        return getFirst(state.getBlock()).defaultBlockState();
    }

    default @NotNull Optional<BlockState> getNext(BlockState state) {
        return getNext(state.getBlock()).map(Block::defaultBlockState);
    }

    default float getChanceModifier() {
        return this.getAge() == CopperOxidizableLevel.UNAFFECTED ? 0.75F : 1.0F;
    }

    enum CopperOxidizableLevel {
        UNAFFECTED,
        EXPOSED,
        WEATHERED,
        OXIDIZED;
    }
}
