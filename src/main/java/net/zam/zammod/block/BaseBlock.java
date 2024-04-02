package net.zam.zammod.block;

import net.minecraft.world.level.block.Block;
import net.zam.zammod.util.BlockOptions;

import java.util.Properties;

public class BaseBlock extends Block {

    public BaseBlock(String name, Properties block) {
        super(block);
        //Index.BLOCKS.register(name, () -> this);
        //Index.ITEMS.register(name, () -> new BlockItem(this, new Item.Properties().tab(ModGroup.MAIN)));
        //this.setRegistryName(name);
        //BlockRegistry.instance.register(this, new BlockOptions());
    }

    public BaseBlock(String name, Properties block, BlockOptions opts) {
        super(block);
        //Index.BLOCKS.register(name, () -> this);
        //Index.ITEMS.register(name, () -> new BlockItem(this, new Item.Properties().tab(ModGroup.MAIN)));
        //this.setRegistryName(name);
        //BlockRegistry.instance.register(this, opts);
    }
}