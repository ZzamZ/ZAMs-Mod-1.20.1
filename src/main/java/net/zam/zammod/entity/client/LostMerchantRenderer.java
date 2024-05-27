package net.zam.zammod.entity.client;

import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.model.VillagerModel;
import net.minecraft.client.model.geom.ModelLayers;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;
import net.zam.zammod.ZAMMod;
import net.zam.zammod.entity.mob.LostMerchantEntity;

public class LostMerchantRenderer extends MobRenderer<LostMerchantEntity, VillagerModel<LostMerchantEntity>> {

    private static final ResourceLocation TEXTURE = new ResourceLocation(ZAMMod.MOD_ID, "textures/entity/lost_merchant.png");

    public LostMerchantRenderer(EntityRendererProvider.Context context) {
        super(context, new VillagerModel<>(context.bakeLayer(ModelLayers.WANDERING_TRADER)), 0.5F);
    }

    @Override
    public ResourceLocation getTextureLocation(LostMerchantEntity entity) {
        return TEXTURE;
    }

    @Override
    protected void scale(LostMerchantEntity entity, PoseStack matrixStack, float partialTickTime) {
        super.scale(entity, matrixStack, partialTickTime);
    }
}
