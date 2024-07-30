package net.zam.zammod.event;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.GameRenderer;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundSource;
import net.minecraft.util.Mth;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.ViewportEvent;
import net.minecraftforge.event.entity.living.LivingEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.zam.zammod.ZAMConfig;
import net.zam.zammod.ZAMMod;
import net.zam.zammod.registry.ZAMEffects;
import net.zam.zammod.registry.ZAMSounds;

import java.util.Objects;

@Mod.EventBusSubscriber(modid = ZAMMod.MOD_ID, value = Dist.CLIENT)
public class ForgeClientEvents {

    private static final ResourceLocation FADED = new ResourceLocation(ZAMMod.MOD_ID, "shaders/post/faded.json");
    private static final ResourceLocation STARDROP = new ResourceLocation(ZAMMod.MOD_ID, "shaders/post/stardrop.json");
    private static float fadedProgress = 0;
    private static float prevFadedProgress = 0;
    private static final float MAX_FADED = 40;
    private static float stardropProgress = 0;
    private static float prevStardropProgress = 0;
    private static final float MAX_STARDROP = 140; // 7 seconds * 20 ticks per second (20 ticks = 1 second)
    private static final float FOV_CHANGE_AMOUNT = 10.0F; // Amount to change FOV by
    private static boolean stardropEffectApplied = false;

    @SubscribeEvent
    public static void adjustFadedFOV(ViewportEvent.ComputeFov event) {
        Minecraft minecraft = Minecraft.getInstance();

        if (prevFadedProgress > 0) {
            float prog = (prevFadedProgress + (fadedProgress - prevFadedProgress) * minecraft.getPartialTick());
            float renderProg;
            if (prevFadedProgress <= fadedProgress) {
                renderProg = (float) Math.sin(prog / MAX_FADED * Math.PI) * 40.0F;
            } else {
                renderProg = -(float) Math.sin(prog / MAX_FADED * Math.PI) * 40.0F;
            }
            event.setFOV(event.getFOV() + (float) Mth.lerp(minecraft.options.fovEffectScale().get(), 1.0F, renderProg));
        }
    }

    @SubscribeEvent
    public static void onLivingUpdate(LivingEvent.LivingTickEvent event) {
        if (event.getEntity() == Minecraft.getInstance().player) {
            GameRenderer renderer = Minecraft.getInstance().gameRenderer;
            boolean fadedEffectActive = event.getEntity().hasEffect(ZAMEffects.FADED_EFFECT.get());

            try {
                // Handle FADED shader
                if (ZAMConfig.COMMON.fadedShader.get()) { // Accessing the configuration value correctly
                    if (fadedEffectActive && renderer.currentEffect() == null) {
                        renderer.loadEffect(FADED);
                    }
                    if (!fadedEffectActive && renderer.currentEffect() != null && FADED.toString().equals(Objects.requireNonNull(renderer.currentEffect()).getName())) {
                        renderer.shutdownEffect();
                    }

                    if (prevFadedProgress == 2 && fadedEffectActive) {
                        event.getEntity().level().playLocalSound(event.getEntity().blockPosition(), ZAMSounds.FADED_EFFECT_BEGIN.get(), SoundSource.NEUTRAL, 16.0F, 1.0F, false);
                    }
                    if (prevFadedProgress == 38 && !fadedEffectActive) {
                        event.getEntity().level().playLocalSound(event.getEntity().blockPosition(), ZAMSounds.FADED_EFFECT_END.get(), SoundSource.NEUTRAL, 16.0F, 1.0F, false);
                    }

                    prevFadedProgress = fadedProgress;
                    if (fadedEffectActive && fadedProgress < MAX_FADED) {
                        fadedProgress += 2F;
                    } else if (!fadedEffectActive && fadedProgress > 0.0F) {
                        fadedProgress -= 2F;
                    }
                }
            } catch (Exception e) {
                ZAMMod.LOGGER.warn("Game tried to crash when applying shader", e);
            }
        }
    }
}
