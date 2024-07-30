package net.zam.zammod.registry;

import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.MenuType;
import net.minecraftforge.common.extensions.IForgeMenuType;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.network.IContainerFactory;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import net.zam.zammod.ZAMMod;
import net.zam.zammod.gui.arcade.container.ArcadeContainer;
import net.zam.zammod.gui.bin.ShippingBinMenu;
import net.zam.zammod.gui.keg.KegMenu;
import net.zam.zammod.gui.MusicDiscLootBoxMenu;

public class ZAMMenuTypes {
    public static final DeferredRegister<MenuType<?>> MENUS =
            DeferredRegister.create(ForgeRegistries.MENU_TYPES, ZAMMod.MOD_ID);

    public static final RegistryObject<MenuType<ArcadeContainer>> ARCADE_CONTAINER = registerMenuType(ArcadeContainer::create, "arcade_container");
    public static final RegistryObject<MenuType<KegMenu>> KEG_MENU = registerMenuType(KegMenu::new, "keg_menu");
    public static final RegistryObject<MenuType<MusicDiscLootBoxMenu>> MUSIC_DISC_LOOT_BOX_MENU = registerMenuType((id, inv, data) -> new MusicDiscLootBoxMenu(id, inv), "music_disc_loot_box_menu");
    public static final RegistryObject<MenuType<ShippingBinMenu>> SHIPPING_BIN_MENU = registerMenuType((IContainerFactory<ShippingBinMenu>) ShippingBinMenu::new, "shipping_bin_menu");

    private static <T extends AbstractContainerMenu> RegistryObject<MenuType<T>> registerMenuType(IContainerFactory<T> factory, String name) {
        return MENUS.register(name, () -> IForgeMenuType.create(factory));
    }

    public static void register(IEventBus eventBus) {
        MENUS.register(eventBus);
    }
}
