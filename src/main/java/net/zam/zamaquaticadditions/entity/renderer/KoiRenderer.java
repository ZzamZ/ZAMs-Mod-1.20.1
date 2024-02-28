package net.zam.zamaquaticadditions.entity.renderer;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Axis;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.zam.zamaquaticadditions.ZAMAquaticAdditions;
import net.zam.zamaquaticadditions.entity.animal.Koi;
import net.zam.zamaquaticadditions.entity.model.KoiModel;

@OnlyIn(Dist.CLIENT)
public class KoiRenderer extends MobRenderer<Koi, KoiModel<Koi>> {

    public KoiRenderer(EntityRendererProvider.Context context) {
        super(context, new KoiModel<>(context.bakeLayer(KoiModel.LOCATION)), 0.3F);
    }

    @Override
    public ResourceLocation getTextureLocation(Koi entity) {
        return new ResourceLocation(ZAMAquaticAdditions.MOD_ID, "textures/entity/koi.png");
    }

    @Override
    public void setupRotations(Koi entityLiving, PoseStack matrixStackIn, float ageInTicks, float rotationYaw, float partialTicks) {
        super.setupRotations(entityLiving, matrixStackIn, ageInTicks, rotationYaw, partialTicks);
        matrixStackIn.mulPose(Axis.XP.rotationDegrees(Mth.lerp(partialTicks, entityLiving.xRotO, entityLiving.getXRot())));
        float f = 4.3F * Mth.sin(0.6F * ageInTicks);
        if (!entityLiving.isInWater()) {
            matrixStackIn.mulPose(Axis.YP.rotationDegrees(f));
            matrixStackIn.translate(0.1F, 0.1F, -0.1F);
            matrixStackIn.mulPose(Axis.ZP.rotationDegrees(90.0F));
        }
    }
}