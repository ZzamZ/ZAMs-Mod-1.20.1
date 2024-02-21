package net.zam.zamaquaticadditions.registry;

import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.zam.zamaquaticadditions.ZAMAquaticAdditions;

public class ZAMEnchantments {
    public static final DeferredRegister<Enchantment> ENCHANTMENTS = DeferredRegister.create(ForgeRegistries.ENCHANTMENTS, ZAMAquaticAdditions.MOD_ID);


    public static void register(IEventBus eventBus) {
        ENCHANTMENTS.register(eventBus);
    }
}
