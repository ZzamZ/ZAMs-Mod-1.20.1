package net.zam.zamaquaticadditions.registry;

import net.minecraft.world.level.block.Block;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.zam.zamaquaticadditions.ZAMAquaticAdditions;

public class ZAMBlocks {
    public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, ZAMAquaticAdditions.MOD_ID);





    public static void register(IEventBus eventBus) {
        BLOCKS.register(eventBus);
    }
}
