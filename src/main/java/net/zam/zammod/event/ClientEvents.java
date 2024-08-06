package net.zam.zammod.event;

import net.minecraft.client.Minecraft;
import net.minecraft.client.resources.sounds.SimpleSoundInstance;
import net.minecraft.client.resources.sounds.SoundInstance;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.sound.PlaySoundSourceEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.zam.zammod.ZAMMod;
import net.zam.zammod.gui.minigame.FishingScreen;
import net.zam.zammod.item.aquamarinetools.AquamarineFishingRodItem;
import net.zam.zammod.registry.ZAMSounds;
import net.zam.zammod.util.network.FishBehavior;

public class ClientEvents {
    public static void openFishingScreen(FishBehavior behavior) {
        Minecraft.getInstance().setScreen(new FishingScreen(behavior));
    }

    @Mod.EventBusSubscriber(modid = ZAMMod.MOD_ID, value = Dist.CLIENT)
    public static class ForgeBus {
        @SubscribeEvent
        public static void onSoundPlayed(final PlaySoundSourceEvent event) {
            if (event.getSound() instanceof SimpleSoundInstance instance) {
                String namespace = event.getSound().getLocation().getNamespace();
                String path = event.getSound().getLocation().getPath();

                if (namespace.equals("minecraft")) {
                    // Check if the player is using the custom fishing rod
                    Minecraft minecraft = Minecraft.getInstance();
                    if (minecraft.level != null) {
                        Player player = minecraft.level.getNearestPlayer(
                                event.getSound().getX(), event.getSound().getY(), event.getSound().getZ(), 1, false
                        );
                        if (player != null && isUsingCustomFishingRod(player)) {
                            SoundEvent newEvent = switch (path) {
                                case "entity.fishing_bobber.throw" -> ZAMSounds.CAST.get();
                                case "entity.fishing_bobber.retrieve" -> {
                                    yield player.fishing == null ? ZAMSounds.PULL_ITEM.get() : ZAMSounds.FISH_HIT.get();
                                }
                                case "entity.fishing_bobber.splash" -> ZAMSounds.FISH_BITE.get();
                                default -> null;
                            };

                            if (newEvent != null) {
                                event.getEngine().stop(instance);
                                event.getEngine().play(new SimpleSoundInstance(
                                        newEvent,
                                        instance.getSource(),
                                        1.0F,
                                        1.0F,
                                        SoundInstance.createUnseededRandom(),
                                        instance.getX(),
                                        instance.getY(),
                                        instance.getZ()
                                ));
                            }
                        }
                    }
                }
            }
        }

        private static boolean isUsingCustomFishingRod(Player player) {
            ItemStack mainHandItem = player.getMainHandItem();
            ItemStack offHandItem = player.getOffhandItem();
            return isCustomFishingRod(mainHandItem) || isCustomFishingRod(offHandItem);
        }

        private static boolean isCustomFishingRod(ItemStack itemStack) {
            return itemStack.getItem() instanceof AquamarineFishingRodItem;
        }
    }
}
