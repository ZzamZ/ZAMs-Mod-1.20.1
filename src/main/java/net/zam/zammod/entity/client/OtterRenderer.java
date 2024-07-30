package net.zam.zammod.entity.client;

import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.model.OcelotModel;
import net.minecraft.client.model.geom.ModelLayers;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;
import net.zam.zammod.ZAMMod;
import net.zam.zammod.entity.mob.OtterEntity;

public class OtterRenderer extends MobRenderer<OtterEntity, OtterModel<OtterEntity>> {
    private static final ResourceLocation TEXTURE = new ResourceLocation(ZAMMod.MOD_ID, "textures/entity/otter.png");

    public OtterRenderer(EntityRendererProvider.Context context) {
        super(context, new OtterModel<>(context.bakeLayer(ModelLayers.OCELOT)), 0.4F);
    }

    @Override
    public ResourceLocation getTextureLocation(OtterEntity otterEntity) {
        return TEXTURE;
    }

    @Override
    protected void scale(OtterEntity otterEntity, PoseStack poseStack, float partialTickTime) {
        super.scale(otterEntity, poseStack, partialTickTime);
    }
}
