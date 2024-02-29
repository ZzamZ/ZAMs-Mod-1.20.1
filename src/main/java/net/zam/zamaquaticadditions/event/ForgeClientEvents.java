package net.zam.zamaquaticadditions.event;

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
import net.zam.zamaquaticadditions.ZAMAquaticAdditions;
import net.zam.zamaquaticadditions.ZAMConfig;
import net.zam.zamaquaticadditions.registry.ZAMEffects;
import net.zam.zamaquaticadditions.registry.ZAMSounds;

import java.util.Objects;

@Mod.EventBusSubscriber(modid = ZAMAquaticAdditions.MOD_ID, value = Dist.CLIENT)
public class ForgeClientEvents {
    private static final ResourceLocation SYNESTHESIA = new ResourceLocation(ZAMAquaticAdditions.MOD_ID, "shaders/post/synesthesia.json");
    private static float synesthesiaProgress = 0;
    private static float prevSynesthesiaProgress = 0;
    private static final float MAX_SYNESTESIA = 40;

    @SubscribeEvent
    public static void onLivingUpdate(LivingEvent.LivingTickEvent event) {
        if (ZAMConfig.synesthesiaShader) {
            if (event.getEntity() == Minecraft.getInstance().player) {
                GameRenderer renderer = Minecraft.getInstance().gameRenderer;
                boolean active = event.getEntity().hasEffect(ZAMEffects.SYNESTHESIA.get());
                try {
                    if (active && renderer.currentEffect() == null) {
                        renderer.loadEffect(SYNESTHESIA);
                    }
                    if (!active && renderer.currentEffect() != null && SYNESTHESIA.toString().equals(Objects.requireNonNull(renderer.currentEffect()).getName())) {
                        renderer.shutdownEffect();
                    }
                } catch (Exception e) {
                    ZAMAquaticAdditions.LOGGER.warn("Game tried to crash when applying shader");
                }

                if (prevSynesthesiaProgress == 2 && active) {
                    event.getEntity().level().playLocalSound(event.getEntity().blockPosition(), ZAMSounds.POTION_EFFECT_BEGIN.get(), SoundSource.NEUTRAL, 16.0F, 1.0F, false);
                }
                if (prevSynesthesiaProgress == 38 && !active) {
                    event.getEntity().level().playLocalSound(event.getEntity().blockPosition(), ZAMSounds.POTION_EFFECT_END.get(), SoundSource.NEUTRAL, 16.0F, 1.0F, false);
                }
                prevSynesthesiaProgress = synesthesiaProgress;
                if (active && synesthesiaProgress < MAX_SYNESTESIA) {
                    synesthesiaProgress += 2F;
                } else if (!active && synesthesiaProgress > 0.0F) {
                    synesthesiaProgress -= 2F;
                }
            }
        }
    }

    @SubscribeEvent
    public static void adjustSynesthesiaFOV(ViewportEvent.ComputeFov event) {
        if (ZAMConfig.synesthesiaShader) {
            if (prevSynesthesiaProgress > 0) {
                float prog = (prevSynesthesiaProgress + (synesthesiaProgress - prevSynesthesiaProgress) * Minecraft.getInstance().getPartialTick());
                float renderProg;
                if (prevSynesthesiaProgress <= synesthesiaProgress) {
                    renderProg = (float) Math.sin(prog / MAX_SYNESTESIA * Math.PI) * 40.0F;
                } else {
                    renderProg = -(float) Math.sin(prog / MAX_SYNESTESIA * Math.PI) * 40.0F;
                }
                event.setFOV(event.getFOV() + (float) Mth.lerp(Minecraft.getInstance().options.fovEffectScale().get(), 1.0F, renderProg));
            }
        }
    }
}
