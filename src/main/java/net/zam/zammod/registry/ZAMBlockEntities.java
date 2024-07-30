package net.zam.zammod.registry;

import com.mojang.datafixers.types.Type;
import net.minecraft.Util;
import net.minecraft.util.datafix.fixes.References;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import net.zam.zammod.ZAMMod;
import net.zam.zammod.block.arcade.ArcadeTileEntity;
import net.zam.zammod.block.beer.workstations.KegEntity;
import net.zam.zammod.block.chest.LostBountyBlockEntity;
import net.zam.zammod.block.sellingbin.bins.ShippingBinBlockEntity;

import javax.annotation.Nonnull;
import java.util.function.Supplier;

public class ZAMBlockEntities {
    public static final DeferredRegister<BlockEntityType<?>> BLOCK_ENTITIES =
            DeferredRegister.create(ForgeRegistries.BLOCK_ENTITY_TYPES, ZAMMod.MOD_ID);

    public static final RegistryObject<BlockEntityType<LostBountyBlockEntity>> LOST_BOUNTY = register("lost_bounty", () -> BlockEntityType.Builder.of(LostBountyBlockEntity::new, ZAMBlocks.LOST_BOUNTY.get()));
    public static final RegistryObject<BlockEntityType<ArcadeTileEntity>> ARCADE_MACHINE = register("arcade_machine", () -> BlockEntityType.Builder.of(ArcadeTileEntity::new, ZAMBlocks.ARCADE_MACHINE.get()));
    public static final RegistryObject<BlockEntityType<KegEntity>> KEG_ENTITY = BLOCK_ENTITIES.register("keg_entity", () -> BlockEntityType.Builder.of(KegEntity::new, ZAMBlocks.KEG.get()).build(null));
    public static final RegistryObject<BlockEntityType<ShippingBinBlockEntity>> SHIPPING_BIN_BLOCK_ENTITY = BLOCK_ENTITIES.register("shipping_bin", () -> BlockEntityType.Builder.of(ShippingBinBlockEntity::new, ZAMBlocks.SHIPPING_BIN_BLOCK.get()).build(null));

    public static <T extends BlockEntity> RegistryObject<BlockEntityType<T>> register(@Nonnull String name, @Nonnull Supplier<BlockEntityType.Builder<T>> initializer) {
        Type<?> type = Util.fetchChoiceType(References.BLOCK_ENTITY, ZAMMod.MOD_ID + ":" + name);
        return BLOCK_ENTITIES.register(name, () -> initializer.get().build(type));
    }

    public static void register(IEventBus eventBus) {
        BLOCK_ENTITIES.register(eventBus);
    }
}
