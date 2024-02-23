package net.zam.zamaquaticadditions.registry;

import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.MapColor;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import net.zam.zamaquaticadditions.ZAMAquaticAdditions;
import net.zam.zamaquaticadditions.block.ScaffinityBlock;
import net.zam.zamaquaticadditions.block.ScaffinityBlockItem;
import net.zam.zamaquaticadditions.block.lostbounty.LostBounty;
import net.zam.zamaquaticadditions.item.renderer.BlockItemWithoutLevelRenderer;
import net.zam.zamaquaticadditions.util.BlockItemWithSupplier;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.function.Supplier;

public class ZAMBlocks {
    public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, ZAMAquaticAdditions.MOD_ID);


    public static final RegistryObject<Block> LOST_BOUNTY = registerWithRenderer(LostBounty::new, "lost_bounty", new Item.Properties().stacksTo(16));
    public static final RegistryObject<Block> SCAFFINITY = registerBlockAndItem("scaffinity", () -> new ScaffinityBlock(BlockBehaviour.Properties.copy(Blocks.SCAFFOLDING).mapColor(MapColor.COLOR_LIGHT_BLUE)), 1);


    public static RegistryObject<Block> registerWithRenderer(Supplier<Block> supplier, @Nonnull String name, @Nullable Item.Properties properties) {
        RegistryObject<Block> block = BLOCKS.register(name, supplier);

        if (properties == null) {
            ZAMItems.register(() -> new BlockItemWithoutLevelRenderer(block.get(), new Item.Properties()), name);
        } else {
            ZAMItems.registerWithTab(() -> new BlockItemWithoutLevelRenderer(block.get(), properties), name);
        }

        return block;
    }

    private static RegistryObject<Block> registerBlockAndItem(String name, Supplier<Block> block, int itemType) {
        RegistryObject<Block> blockObj = ZAMBlocks.BLOCKS.register(name, block);
        ZAMItems.ITEMS.register(name, getBlockSupplier(itemType, blockObj));
        return blockObj;
    }

    private static Supplier<? extends BlockItemWithSupplier> getBlockSupplier(int itemType, RegistryObject<Block> blockObj) {
        switch (itemType) {
            default:
                return () -> new BlockItemWithSupplier(blockObj, new Item.Properties());
            case 1:

                return () -> new ScaffinityBlockItem(blockObj, new Item.Properties());
        }
    }

    public static void register(IEventBus eventBus) {
        BLOCKS.register(eventBus);
    }
}
