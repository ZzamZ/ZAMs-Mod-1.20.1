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
    private static float fadedProgress = 0;
    private static float prevFadedProgress = 0;
    private static final float MAX_FADED = 40;

    @SubscribeEvent
    public static void adjustSynesthesiaFOV(ViewportEvent.ComputeFov event) {
        if (ZAMConfig.fadedShader) {
            if (prevFadedProgress > 0) {
                float prog = (prevFadedProgress + (fadedProgress - prevFadedProgress) * Minecraft.getInstance().getPartialTick());
                float renderProg;
                if (prevFadedProgress <= fadedProgress) {
                    renderProg = (float) Math.sin(prog / MAX_FADED * Math.PI) * 40.0F;
                } else {
                    renderProg = -(float) Math.sin(prog / MAX_FADED * Math.PI) * 40.0F;
                }
                event.setFOV(event.getFOV() + (float) Mth.lerp(Minecraft.getInstance().options.fovEffectScale().get(), 1.0F, renderProg));
            }
        }
    }

    @SubscribeEvent
    public static void onLivingUpdate(LivingEvent.LivingTickEvent event) {
        if (ZAMConfig.fadedShader) {
            if (event.getEntity() == Minecraft.getInstance().player) {
                GameRenderer renderer = Minecraft.getInstance().gameRenderer;
                boolean active = event.getEntity().hasEffect(ZAMEffects.FADED_EFFECT.get());
                try {
                    if (active && renderer.currentEffect() == null) {
                        renderer.loadEffect(FADED);
                    }
                    if (!active && renderer.currentEffect() != null && FADED.toString().equals(Objects.requireNonNull(renderer.currentEffect()).getName())) {
                        renderer.shutdownEffect();
                    }
                } catch (Exception e) {
                    ZAMMod.LOGGER.warn("Game tried to crash when applying shader");
                }

                if (prevFadedProgress == 2 && active) {
                    event.getEntity().level().playLocalSound(event.getEntity().blockPosition(), ZAMSounds.FADED_EFFECT_BEGIN.get(), SoundSource.NEUTRAL, 16.0F, 1.0F, false);
                }
                if (prevFadedProgress == 38 && !active) {
                    event.getEntity().level().playLocalSound(event.getEntity().blockPosition(), ZAMSounds.FADED_EFFECT_END.get(), SoundSource.NEUTRAL, 16.0F, 1.0F, false);
                }
                prevFadedProgress = fadedProgress;
                if (active && fadedProgress < MAX_FADED) {
                    fadedProgress += 2F;
                } else if (!active && fadedProgress > 0.0F) {
                    fadedProgress -= 2F;
                }
            }
        }
    }
}

