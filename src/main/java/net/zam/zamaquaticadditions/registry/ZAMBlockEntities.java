package net.zam.zamaquaticadditions.registry;

import com.mojang.datafixers.types.Type;
import net.minecraft.Util;
import net.minecraft.util.datafix.fixes.References;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import net.zam.zamaquaticadditions.ZAMAquaticAdditions;
import net.zam.zamaquaticadditions.block.lostbounty.LostBountyBlockEntity;

import javax.annotation.Nonnull;
import java.util.function.Supplier;

public class ZAMBlockEntities {
    public static final DeferredRegister<BlockEntityType<?>> BLOCK_ENTITIES =
            DeferredRegister.create(ForgeRegistries.BLOCK_ENTITY_TYPES, ZAMAquaticAdditions.MOD_ID);

    public static final RegistryObject<BlockEntityType<LostBountyBlockEntity>> LOST_BOUNTY = register("lost_bounty", () -> BlockEntityType.Builder.of(LostBountyBlockEntity::new, ZAMBlocks.LOST_BOUNTY.get()));


    public static <T extends BlockEntity> RegistryObject<BlockEntityType<T>> register(@Nonnull String name, @Nonnull Supplier<BlockEntityType.Builder<T>> initializer) {
        Type<?> type = Util.fetchChoiceType(References.BLOCK_ENTITY, ZAMAquaticAdditions.MOD_ID + ":" + name);
        return BLOCK_ENTITIES.register(name, () -> initializer.get().build(type));
    }

    public static void register(IEventBus eventBus) {
        BLOCK_ENTITIES.register(eventBus);
    }
}
