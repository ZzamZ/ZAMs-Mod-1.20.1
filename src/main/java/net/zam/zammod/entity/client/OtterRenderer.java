package net.zam.zammod.entity.client;

//import com.mojang.blaze3d.vertex.PoseStack;
//import net.minecraft.client.renderer.MultiBufferSource;
//import net.minecraft.client.renderer.entity.EntityRendererProvider;
//import net.minecraft.client.renderer.entity.MobRenderer;
//import net.minecraft.resources.ResourceLocation;
//import net.zam.zammod.ZAMMod;
//import net.zam.zammod.entity.mob.OtterEntity;
//import net.zam.zammod.entity.layers.ZAMModelLayers;
//
//public class OtterRenderer extends MobRenderer<OtterEntity, OtterModel<OtterEntity>> {
//    private static final ResourceLocation OTTER_LOCATION = new ResourceLocation(ZAMMod.MOD_ID,"textures/entity/otter.png");
//
//    public OtterRenderer(EntityRendererProvider.Context pContext) {
//        super(pContext, new OtterModel<>(pContext.bakeLayer(ZAMModelLayers.OTTER_LAYER)), 0.5f);
//    }
//
//    @Override
//    public ResourceLocation getTextureLocation(OtterEntity pEntity) {
//        return OTTER_LOCATION;
//    }
//
//    @Override
//    public void render(OtterEntity pEntity, float pEntityYaw, float pPartialTicks,
//                       PoseStack pMatrixStack, MultiBufferSource pBuffer, int pPackedLight) {
//        if(pEntity.isBaby()) {
//            pMatrixStack.scale(0.75f, 0.75f, 0.75f);
//        }
//
//        super.render(pEntity, pEntityYaw, pPartialTicks, pMatrixStack, pBuffer, pPackedLight);
//    }
//}
//