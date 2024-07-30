package net.zam.zammod.entity.client;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.model.HierarchicalModel;
import net.minecraft.client.model.ModelUtils;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;
import net.zam.zammod.ZAMMod;
import net.zam.zammod.entity.mob.OtterEntity;

public class OtterModel<T extends OtterEntity> extends HierarchicalModel<T> {
	public static final ResourceLocation LAYER_LOCATION = new ResourceLocation(ZAMMod.MOD_ID, "main");

	private final ModelPart root;
	private final ModelPart head;
	private final ModelPart body;
	private final ModelPart rightHindLeg;
	private final ModelPart leftHindLeg;
	private final ModelPart rightFrontLeg;
	private final ModelPart leftFrontLeg;

	public OtterModel(ModelPart modelPart) {
		super(RenderType::entityCutoutNoCull);
		this.root = modelPart;
		this.head = modelPart.getChild("head");
		this.body = modelPart.getChild("body");
		this.rightHindLeg = modelPart.getChild("right_hind_leg");
		this.leftHindLeg = modelPart.getChild("left_hind_leg");
		this.rightFrontLeg = modelPart.getChild("right_front_leg");
		this.leftFrontLeg = modelPart.getChild("left_front_leg");
	}

	public static LayerDefinition createBodyLayer() {
		MeshDefinition meshDefinition = new MeshDefinition();
		PartDefinition partDefinition = meshDefinition.getRoot();

		partDefinition.addOrReplaceChild("head", CubeListBuilder.create().texOffs(0, 0).addBox(-2.5F, -2.0F, -4.0F, 5.0F, 4.0F, 5.0F).texOffs(0, 24).addBox("nose", -1.5F, 0.0F, -6.0F, 3.0F, 2.0F, 2.0F).texOffs(9, 24).addBox("left_ear", 0.5F, -3.0F, -2.0F, 1.0F, 1.0F, 2.0F).texOffs(9, 24).mirror().addBox("right_ear", -1.5F, -3.0F, -2.0F, 1.0F, 1.0F, 2.0F), PartPose.offset(0.0F, 15.0F, -9.0F));

		partDefinition.addOrReplaceChild("body", CubeListBuilder.create().texOffs(20, 0).addBox(-3.0F, -3.5F, -3.5F, 6.0F, 7.0F, 10.0F), PartPose.offsetAndRotation(0.0F, 14.0F, -3.0F, ((float) Math.PI / 2F), 0.0F, 0.0F));

		CubeListBuilder cubeListBuilder = CubeListBuilder.create().texOffs(50, 0).addBox(-1.0F, -1.0F, -2.0F, 2.0F, 5.0F, 2.0F);
		partDefinition.addOrReplaceChild("right_hind_leg", cubeListBuilder, PartPose.offset(-2.5F, 19.0F, 3.0F));
		partDefinition.addOrReplaceChild("left_hind_leg", cubeListBuilder, PartPose.offset(2.5F, 19.0F, 3.0F));
		partDefinition.addOrReplaceChild("right_front_leg", cubeListBuilder, PartPose.offset(-2.5F, 19.0F, -4.0F));
		partDefinition.addOrReplaceChild("left_front_leg", cubeListBuilder, PartPose.offset(2.5F, 19.0F, -4.0F));

		return LayerDefinition.create(meshDefinition, 64, 32);
	}

	@Override
	public void setupAnim(T otterEntity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
		this.head.xRot = headPitch * ((float) Math.PI / 180F);
		this.head.yRot = netHeadYaw * ((float) Math.PI / 180F);

		if (otterEntity.isInSittingPose()) {
			this.body.setPos(0.0F, 16.0F, -3.0F);
			this.body.xRot = ((float) Math.PI / 4F);
			this.rightHindLeg.setPos(-2.5F, 19.0F, 1.0F);
			this.leftHindLeg.setPos(2.5F, 19.0F, 1.0F);
			this.rightFrontLeg.setPos(-2.5F, 19.0F, -3.0F);
			this.leftFrontLeg.setPos(2.5F, 19.0F, -3.0F);

			this.rightHindLeg.xRot = -1.5F;
			this.leftHindLeg.xRot = -1.5F;
			this.rightFrontLeg.xRot = -0.5F;
			this.leftFrontLeg.xRot = -0.5F;
		} else {
			this.body.setPos(0.0F, 14.0F, -3.0F);
			this.body.xRot = ((float) Math.PI / 2F);
			this.rightHindLeg.setPos(-2.5F, 19.0F, 3.0F);
			this.leftHindLeg.setPos(2.5F, 19.0F, 3.0F);
			this.rightFrontLeg.setPos(-2.5F, 19.0F, -4.0F);
			this.leftFrontLeg.setPos(2.5F, 19.0F, -4.0F);

			this.rightHindLeg.xRot = Mth.cos(limbSwing * 0.6662F) * 1.4F * limbSwingAmount;
			this.leftHindLeg.xRot = Mth.cos(limbSwing * 0.6662F + (float) Math.PI) * 1.4F * limbSwingAmount;
			this.rightFrontLeg.xRot = Mth.cos(limbSwing * 0.6662F + (float) Math.PI) * 1.4F * limbSwingAmount;
			this.leftFrontLeg.xRot = Mth.cos(limbSwing * 0.6662F) * 1.4F * limbSwingAmount;
		}
	}

	@Override
	public void renderToBuffer(PoseStack poseStack, VertexConsumer vertexConsumer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
		this.root.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
	}

	@Override
	public ModelPart root() {
		return this.root;
	}
}
