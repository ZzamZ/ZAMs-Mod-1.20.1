package net.zam.zammod.util.network;

import net.minecraft.core.Direction;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundSource;
import net.minecraft.stats.Stats;
import net.minecraft.tags.ItemTags;
import net.minecraft.util.Mth;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.ExperienceOrb;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.FishingHook;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.ToolActions;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.CapabilityManager;
import net.minecraftforge.common.capabilities.CapabilityToken;
import net.minecraftforge.common.capabilities.ICapabilityProvider;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.event.AttachCapabilitiesEvent;
import net.minecraftforge.event.entity.player.ItemFishedEvent;
import net.zam.zammod.ZAMMod;
import net.zam.zammod.registry.ZAMSounds;
import net.zam.zammod.registry.ZAMTags;
import net.zam.zammod.util.network.packet.S2CStartMinigamePacket;
import net.zam.zammod.util.server.FishBehaviorReloadListener;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class FishingHookLogic {
    private final ArrayList<ItemStack> rewards = new ArrayList<>();

    public static void attachCap(AttachCapabilitiesEvent<Entity> event) {
        if (!event.getObject().getCapability(FishingHookLogic.CapProvider.CAP).isPresent()) {
            event.addCapability(CapProvider.NAME, new CapProvider());
        }
    }

    public static Optional<List<ItemStack>> getStoredRewards(FishingHook entity) {
        return entity.getCapability(CapProvider.CAP).map(cap -> cap.rewards);
    }

    public static void startMinigame(ServerPlayer player) {
        if (player.fishing == null) return;

        NetworkHandler.sendToPlayer(player, new S2CStartMinigamePacket(
                FishBehaviorReloadListener.getBehavior(
                        getStoredRewards(player.fishing)
                                .flatMap(rewards -> rewards.stream()
                                        .filter(stack -> stack.is(ZAMTags.STARTS_MINIGAME))
                                        .findFirst()
                                ).orElse(null))
        ));
    }

    public static void endMinigame(ServerPlayer player, boolean success, double accuracy) {
        if (success && !player.level().isClientSide) {
            modifyRewards(player, accuracy);
            giveRewards(player, accuracy);
        }

        reelInFishingLine(player);
    }

    private static void reelInFishingLine(ServerPlayer player) {
        if (player.fishing != null) {
            player.fishing.retrieve(player.getMainHandItem());
            player.fishing.discard();
        }
    }

    public static void modifyRewards(ServerPlayer player, double accuracy) {
        if (player.fishing == null) return;
        getStoredRewards(player.fishing).ifPresent(rewards -> modifyRewards(rewards, accuracy));
    }

    public static void modifyRewards(List<ItemStack> rewards, double accuracy) {
        // Implement reward modification logic if needed
    }

    public static void giveRewards(ServerPlayer player, double accuracy) {
        if (player.fishing == null) return;

        InteractionHand hand = getRodHand(player);
        if (hand == null) return;

        FishingHook hook = player.fishing;
        getStoredRewards(hook).ifPresent(rewards -> {
            ItemFishedEvent event = new ItemFishedEvent(rewards, hook.onGround() ? 2 : 1, hook);
            MinecraftForge.EVENT_BUS.post(event);
            if (event.isCanceled()) {
                hook.discard();
                return;
            }

            player.getItemInHand(hand).hurtAndBreak(event.getRodDamage(), player, p -> p.broadcastBreakEvent(hand));

            ServerLevel level = player.serverLevel();
            for (ItemStack reward : rewards) {
                if (reward.is(ItemTags.FISHES)) {
                    player.awardStat(Stats.FISH_CAUGHT);
                }

                ItemEntity itementity = new ItemEntity(level, hook.getX(), hook.getY(), hook.getZ(), reward);
                double dx = player.getX() - hook.getX();
                double dy = player.getY() - hook.getY();
                double dz = player.getZ() - hook.getZ();
                double scale = 0.1;
                itementity.setDeltaMovement(dx * scale, dy * scale + Math.sqrt(Math.sqrt(dx * dx + dy * dy + dz * dz)) * 0.08, dz * scale);
                level.addFreshEntity(itementity);

                int exp = Mth.floor((player.getRandom().nextInt(6) + 1) * accuracy);
                level.addFreshEntity(new ExperienceOrb(level, player.getX(), player.getY() + 0.5, player.getZ() + 0.5, exp));
            }

            player.level().playSound(null, player, ZAMSounds.PULL_ITEM.get(), SoundSource.PLAYERS, 1.0F, 1.0F);
        });
    }

    public static InteractionHand getRodHand(Player player) {
        boolean mainHand = player.getItemInHand(InteractionHand.MAIN_HAND).canPerformAction(ToolActions.FISHING_ROD_CAST);
        if (mainHand) return InteractionHand.MAIN_HAND;

        boolean offHand = player.getItemInHand(InteractionHand.OFF_HAND).canPerformAction(ToolActions.FISHING_ROD_CAST);
        if (offHand) return InteractionHand.OFF_HAND;

        return null;
    }

    private static class CapProvider implements ICapabilityProvider {
        private static final Capability<FishingHookLogic> CAP = CapabilityManager.get(new CapabilityToken<>() {});
        private static final ResourceLocation NAME = new ResourceLocation(ZAMMod.MOD_ID, "hook");

        private final LazyOptional<FishingHookLogic> optional = LazyOptional.of(FishingHookLogic::new);

        @Override
        public @NotNull <T> LazyOptional<T> getCapability(@NotNull Capability<T> cap, @Nullable Direction side) {
            return getCapability(cap);
        }

        @Override
        public @NotNull <T> LazyOptional<T> getCapability(@NotNull Capability<T> cap) {
            return cap == CAP ? optional.cast() : LazyOptional.empty();
        }
    }
}
