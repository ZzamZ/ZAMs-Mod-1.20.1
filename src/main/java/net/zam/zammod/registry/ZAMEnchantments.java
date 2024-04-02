package net.zam.zammod.registry;

import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import net.zam.zammod.ZAMMod;
import net.zam.zammod.enchantment.SoulboundEnchantment;

public class ZAMEnchantments {
    public static final DeferredRegister<Enchantment> ENCHANTMENTS = DeferredRegister.create(ForgeRegistries.ENCHANTMENTS, ZAMMod.MOD_ID);

    public static final RegistryObject<Enchantment> SOUL_BOUND_ENCHANTMENT = ENCHANTMENTS.register("soulbound", SoulboundEnchantment::new);

    public ZAMEnchantments() {
        MinecraftForge.EVENT_BUS.register(this);
        SoulboundEnchantment.addCuriosDropListener();
    }
    public static void register(IEventBus eventBus) {
        ENCHANTMENTS.register(eventBus);
    }
}
