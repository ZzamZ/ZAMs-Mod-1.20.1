package net.zam.zamaquaticadditions.registry;

import net.minecraft.world.item.Item;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import net.zam.zamaquaticadditions.ZAMAquaticAdditions;

import javax.annotation.Nonnull;
import java.util.function.Supplier;

public class ZAMItems {
    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, ZAMAquaticAdditions.MOD_ID);




    public static RegistryObject<Item> register(@Nonnull Supplier<Item> initializer, @Nonnull String name) {
        return ITEMS.register(name, initializer);
    }
    public static void register(IEventBus eventBus) {
        ITEMS.register(eventBus);
    }
}
