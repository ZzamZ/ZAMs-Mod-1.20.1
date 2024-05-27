package net.zam.zammod.entity.client;// Made with Blockbench 4.9.4


import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.model.HierarchicalModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import net.zam.zammod.entity.mob.OtterEntity;
import net.zam.zammod.entity.animations.ZAMAnimationDefinitions;

public class OtterModel<T extends OtterEntity> extends HierarchicalModel<T> {
	private final ModelPart otter;

	public OtterModel(ModelPart root) {
		this.otter = root.getChild("otter");
	}

	public static LayerDefinition createBodyLayer() {
		MeshDefinition meshdefinition = new MeshDefinition();
		PartDefinition partdefinition = meshdefinition.getRoot();

		PartDefinition otter = partdefinition.addOrReplaceChild("otter", CubeListBuilder.create(), PartPose.offset(0.0F, 24.0F, -2.4F));

		PartDefinition head = otter.addOrReplaceChild("head", CubeListBuilder.create().texOffs(0, 17).addBox(-5.0F, -8.0F, -1.0F, 6.0F, 5.0F, 6.0F, new CubeDeformation(0.0F))
				.texOffs(0, 8).addBox(-4.0F, -5.0F, -2.0F, 4.0F, 2.0F, 1.0F, new CubeDeformation(0.0F))
				.texOffs(18, 20).addBox(1.0F, -7.0F, 3.0F, 1.0F, 2.0F, 1.0F, new CubeDeformation(0.0F))
				.texOffs(0, 20).addBox(-6.0F, -7.0F, 3.0F, 1.0F, 2.0F, 1.0F, new CubeDeformation(0.0F))
				.texOffs(18, 17).addBox(1.0F, -5.0F, 1.0F, 3.0F, 3.0F, 0.0F, new CubeDeformation(0.0F))
				.texOffs(0, 17).addBox(-8.0F, -5.0F, 1.0F, 3.0F, 3.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offset(2.0F, -3.0F, -7.0F));

		PartDefinition body = otter.addOrReplaceChild("body", CubeListBuilder.create().texOffs(0, 0).addBox(-2.5F, -8.0F, -4.0F, 5.0F, 5.0F, 12.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, 0.0F));

		PartDefinition legs = otter.addOrReplaceChild("legs", CubeListBuilder.create().texOffs(0, 0).addBox(-3.45F, -5.0F, -3.0F, 3.0F, 5.0F, 3.0F, new CubeDeformation(0.0F))
				.texOffs(28, 17).addBox(0.45F, -5.0F, -3.0F, 3.0F, 5.0F, 3.0F, new CubeDeformation(0.0F))
				.texOffs(0, 28).addBox(-3.5F, -5.0F, 4.55F, 3.0F, 5.0F, 3.0F, new CubeDeformation(0.0F))
				.texOffs(22, 0).addBox(0.45F, -5.0F, 4.6F, 3.0F, 5.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, 0.0F));

		PartDefinition tail = otter.addOrReplaceChild("tail", CubeListBuilder.create().texOffs(15, 19).addBox(-1.0F, -7.0F, 8.0F, 2.0F, 3.0F, 9.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, 0.0F));

		return LayerDefinition.create(meshdefinition, 64, 64);
	}

	@Override
	public void setupAnim(OtterEntity entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
		this.root().getAllParts().forEach(ModelPart::resetPose);
		this.animateWalk(ZAMAnimationDefinitions.OTTER_WALK, limbSwing, limbSwingAmount, 2f, 2.5f);
		this.animate(entity.idleAnimationState, ZAMAnimationDefinitions.OTTER_IDLE, ageInTicks, 1f);
		this.animate(entity.sitAnimationState, ZAMAnimationDefinitions.OTTER_SIT, ageInTicks, 1f);


	}

	@Override
	public void renderToBuffer(PoseStack poseStack, VertexConsumer vertexConsumer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
		otter.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
	}

	@Override
	public ModelPart root() {
		return otter;
	}
}