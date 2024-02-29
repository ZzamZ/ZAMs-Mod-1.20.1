package net.zam.zamaquaticadditions.mixin;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Holder;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.entity.MobSpawnType;
import net.minecraft.world.entity.SpawnGroupData;
import net.minecraft.world.entity.animal.frog.Frog;
import net.minecraft.world.level.ServerLevelAccessor;
import net.minecraft.world.level.biome.Biome;
import net.zam.zamaquaticadditions.ZAMAquaticAdditions;
import net.zam.zamaquaticadditions.registry.ZAMTags;
import net.zam.zamaquaticadditions.util.IEntityDataSaver;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(Frog.class)
public abstract class FrogVariantsMixin implements IEntityDataSaver {

    @Inject(
            method = {"finalizeSpawn"},
            at = {@At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/world/entity/animal/frog/FrogAi;initMemories(Lnet/minecraft/world/entity/animal/frog/Frog;Lnet/minecraft/util/RandomSource;)V"
            )}
    )

    private void changeFrogs(ServerLevelAccessor serverLevelAccessor, DifficultyInstance difficultyInstance, MobSpawnType spawnType, SpawnGroupData spawnGroupData, CompoundTag compoundTag, CallbackInfoReturnable<SpawnGroupData> cir) {

        Frog frog = (Frog) (Object) this;
        BlockPos spawnPos = frog.blockPosition();
        Holder<Biome> holder = serverLevelAccessor.getBiome(spawnPos);
        if (holder.is(ZAMTags.Biomes.SPAWNS_ANCIENT_VARIANT_FROGS))
            frog.setVariant(ZAMAquaticAdditions.ANCIENT_FROG.get());
        if (holder.is(ZAMTags.Biomes.SPAWNS_PURPUR_VARIANT_FROGS))
            frog.setVariant(ZAMAquaticAdditions.PURPUR_FROG.get());
        if (holder.is(ZAMTags.Biomes.SPAWNS_STRAWBERRY_VARIANT_FROGS))
            frog.setVariant(ZAMAquaticAdditions.STRAWBERRY_FROG.get());
    }
}