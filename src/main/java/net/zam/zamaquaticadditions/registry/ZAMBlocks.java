package net.zam.zamaquaticadditions.registry;

import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import net.zam.zamaquaticadditions.ZAMAquaticAdditions;
import net.zam.zamaquaticadditions.block.lostbounty.LostBounty;
import net.zam.zamaquaticadditions.item.renderer.BlockItemWithoutLevelRenderer;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.function.Supplier;

public class ZAMBlocks {
    public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, ZAMAquaticAdditions.MOD_ID);


    public static final RegistryObject<Block> LOST_BOUNTY = registerWithRenderer(LostBounty::new, "lost_bounty", new Item.Properties().stacksTo(16));



    public static RegistryObject<Block> registerWithRenderer(Supplier<Block> supplier, @Nonnull String name, @Nullable Item.Properties properties) {
        RegistryObject<Block> block = BLOCKS.register(name, supplier);

        if (properties == null) {
            ZAMItems.register(() -> new BlockItemWithoutLevelRenderer(block.get(), new Item.Properties()), name);
        } else {
            ZAMItems.registerWithTab(() -> new BlockItemWithoutLevelRenderer(block.get(), properties), name);
        }

        return block;
    }

    public static void register(IEventBus eventBus) {
        BLOCKS.register(eventBus);
    }
}
