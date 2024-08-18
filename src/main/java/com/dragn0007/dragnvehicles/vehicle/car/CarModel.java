package com.dragn0007.dragnvehicles.vehicle.car;


import com.dragn0007.dragnvehicles.Animation;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;

public class CarModel extends EntityModel<Car> {
	private final ModelPart Body;
	private final ModelPart FrontWheels;
	private final ModelPart BackWheels;

	public CarModel(ModelPart root) {
		this.Body = root.getChild("Body");
		this.FrontWheels = root.getChild("FrontWheels");
		this.BackWheels = root.getChild("BackWheels");
	}

	public static LayerDefinition createBodyLayer() {
		MeshDefinition meshdefinition = new MeshDefinition();
		PartDefinition partdefinition = meshdefinition.getRoot();

		PartDefinition Body = partdefinition.addOrReplaceChild("Body", CubeListBuilder.create().texOffs(0, 0).addBox(-22.0F, -12.0F, -47.0F, 44.0F, 5.0F, 102.0F, new CubeDeformation(0.0F))
		.texOffs(0, 249).addBox(-19.0F, -14.0F, 20.0F, 38.0F, 2.0F, 14.0F, new CubeDeformation(0.0F))
		.texOffs(0, 68).addBox(-19.0F, -27.0F, 34.0F, 38.0F, 15.0F, 2.0F, new CubeDeformation(0.0F))
		.texOffs(0, 108).addBox(3.0F, -14.0F, -9.0F, 16.0F, 2.0F, 17.0F, new CubeDeformation(0.0F))
		.texOffs(80, 140).addBox(3.0F, -27.0F, 8.0F, 16.0F, 15.0F, 2.0F, new CubeDeformation(0.0F))
		.texOffs(80, 120).addBox(-19.0F, -14.0F, -9.0F, 16.0F, 2.0F, 17.0F, new CubeDeformation(0.0F))
		.texOffs(117, 140).addBox(-19.0F, -27.0F, 8.0F, 16.0F, 15.0F, 2.0F, new CubeDeformation(0.0F))
		.texOffs(31, 86).addBox(17.0F, -20.1242F, 27.8423F, 6.0F, 2.0F, 9.0F, new CubeDeformation(0.0F))
		.texOffs(191, 0).addBox(19.5F, -37.0F, -16.0F, 0.0F, 12.0F, 61.0F, new CubeDeformation(0.0F))
		.texOffs(174, 159).addBox(-19.5F, -37.0F, -16.0F, 0.0F, 12.0F, 61.0F, new CubeDeformation(0.0F))
		.texOffs(0, 86).addBox(-23.0F, -20.1242F, 27.8423F, 6.0F, 2.0F, 9.0F, new CubeDeformation(0.0F))
		.texOffs(236, 192).addBox(-23.0F, -20.1242F, -25.1577F, 46.0F, 2.0F, 9.0F, new CubeDeformation(0.0F))
		.texOffs(160, 108).addBox(-20.0F, -25.0F, -44.0F, 40.0F, 15.0F, 35.0F, new CubeDeformation(0.0F))
		.texOffs(210, 233).addBox(-15.0F, -26.0F, -43.0F, 30.0F, 1.0F, 26.0F, new CubeDeformation(0.0F))
		.texOffs(80, 124).addBox(19.0F, -25.0F, -21.0F, 2.0F, 15.0F, 75.0F, new CubeDeformation(0.0F))
		.texOffs(72, 199).addBox(19.0F, -36.6F, 9.0F, 1.0F, 12.0F, 2.0F, new CubeDeformation(0.0F))
		.texOffs(93, 81).addBox(-20.0F, -36.6F, 25.0F, 1.0F, 12.0F, 2.0F, new CubeDeformation(0.0F))
		.texOffs(58, 199).addBox(-20.0F, -36.6F, 9.0F, 1.0F, 12.0F, 2.0F, new CubeDeformation(0.0F))
		.texOffs(65, 199).addBox(19.0F, -36.6F, 25.0F, 1.0F, 12.0F, 2.0F, new CubeDeformation(0.0F))
		.texOffs(0, 108).addBox(-21.0F, -25.0F, -21.0F, 2.0F, 15.0F, 75.0F, new CubeDeformation(0.0F))
		.texOffs(236, 159).addBox(-19.0F, -25.0F, 36.0F, 38.0F, 15.0F, 17.0F, new CubeDeformation(0.0F))
		.texOffs(0, 128).addBox(-13.0F, -25.0F, -46.0F, 26.0F, 17.0F, 2.0F, new CubeDeformation(0.0F))
		.texOffs(0, 215).addBox(-20.0F, -39.5255F, -2.9056F, 40.0F, 3.0F, 30.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 24.0F, 0.0F));

		PartDefinition cube_r1 = Body.addOrReplaceChild("cube_r1", CubeListBuilder.create().texOffs(37, 0).addBox(-20.0F, -4.5F, 4.5F, 3.0F, 3.0F, 26.0F, new CubeDeformation(0.0F))
		.texOffs(37, 34).addBox(17.0F, -4.5F, 4.5F, 3.0F, 3.0F, 26.0F, new CubeDeformation(0.0F))
		.texOffs(191, 74).addBox(-19.0F, -3.6F, 4.4F, 38.0F, 0.0F, 26.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -16.5F, -23.5F, 0.6981F, 0.0F, 0.0F));

		PartDefinition cube_r2 = Body.addOrReplaceChild("cube_r2", CubeListBuilder.create().texOffs(0, 0).addBox(-1.5F, -5.5F, -12.0F, 3.0F, 3.0F, 30.0F, new CubeDeformation(0.0F))
		.texOffs(0, 34).addBox(35.5F, -5.5F, -12.0F, 3.0F, 3.0F, 30.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-18.5F, -28.4353F, 34.1676F, -0.5672F, 0.0F, 0.0F));

		PartDefinition cube_r3 = Body.addOrReplaceChild("cube_r3", CubeListBuilder.create().texOffs(182, 187).addBox(-23.0F, -30.3F, -23.5F, 6.0F, 2.0F, 9.0F, new CubeDeformation(0.0F))
		.texOffs(27, 199).addBox(17.0F, -30.3F, -23.5F, 6.0F, 2.0F, 9.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -7.5F, 59.0F, 0.7418F, 0.0F, 0.0F));

		PartDefinition cube_r4 = Body.addOrReplaceChild("cube_r4", CubeListBuilder.create().texOffs(62, 86).addBox(-23.0F, -2.5F, -16.0F, 6.0F, 2.0F, 9.0F, new CubeDeformation(0.0F))
		.texOffs(160, 184).addBox(17.0F, -2.5F, -16.0F, 6.0F, 2.0F, 9.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -7.5F, 47.0F, -0.7418F, 0.0F, 0.0F));

		PartDefinition Window_r1 = Body.addOrReplaceChild("Window_r1", CubeListBuilder.create().texOffs(113, 233).addBox(-17.0F, 0.0F, -15.5F, 34.0F, 0.0F, 28.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -29.6328F, 39.7413F, -0.5672F, 0.0F, 0.0F));

		PartDefinition cube_r5 = Body.addOrReplaceChild("cube_r5", CubeListBuilder.create().texOffs(236, 204).addBox(-23.0F, -30.3F, -23.5F, 46.0F, 2.0F, 9.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -7.5F, 6.0F, 0.7418F, 0.0F, 0.0F));

		PartDefinition cube_r6 = Body.addOrReplaceChild("cube_r6", CubeListBuilder.create().texOffs(80, 108).addBox(-23.0F, -2.5F, -16.0F, 46.0F, 2.0F, 9.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -7.5F, -6.0F, -0.7418F, 0.0F, 0.0F));

		PartDefinition FrontWheels = partdefinition.addOrReplaceChild("FrontWheels", CubeListBuilder.create().texOffs(187, 159).addBox(13.0F, -8.5F, -3.5F, 6.0F, 17.0F, 7.0F, new CubeDeformation(0.0F))
		.texOffs(187, 159).addBox(-27.0F, -8.5F, -3.5F, 6.0F, 17.0F, 7.0F, new CubeDeformation(0.0F))
		.texOffs(253, 11).addBox(-26.5F, -2.5F, -2.5F, 45.0F, 5.0F, 5.0F, new CubeDeformation(0.0F)), PartPose.offset(4.0F, 15.5F, -20.5F));

		PartDefinition cube_r7 = FrontWheels.addOrReplaceChild("cube_r7", CubeListBuilder.create().texOffs(187, 159).addBox(-2.0F, -8.5F, -3.5F, 6.0F, 17.0F, 7.0F, new CubeDeformation(0.0F))
		.texOffs(187, 159).addBox(-42.0F, -8.5F, -3.5F, 6.0F, 17.0F, 7.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(15.0F, 0.0F, 0.0F, -0.7854F, 0.0F, 0.0F));

		PartDefinition cube_r8 = FrontWheels.addOrReplaceChild("cube_r8", CubeListBuilder.create().texOffs(187, 159).addBox(-2.0F, -8.5F, -3.5F, 6.0F, 17.0F, 7.0F, new CubeDeformation(0.0F))
		.texOffs(187, 159).addBox(-42.0F, -8.5F, -3.5F, 6.0F, 17.0F, 7.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(15.0F, 0.0F, 0.0F, -1.5708F, 0.0F, 0.0F));

		PartDefinition cube_r9 = FrontWheels.addOrReplaceChild("cube_r9", CubeListBuilder.create().texOffs(187, 159).addBox(-2.0F, -8.5F, -3.5F, 6.0F, 17.0F, 7.0F, new CubeDeformation(0.0F))
		.texOffs(187, 159).addBox(-42.0F, -8.5F, -3.5F, 6.0F, 17.0F, 7.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(15.0F, 0.0F, 0.0F, -2.3562F, 0.0F, 0.0F));

		PartDefinition BackWheels = partdefinition.addOrReplaceChild("BackWheels", CubeListBuilder.create().texOffs(187, 159).addBox(13.0F, -8.5F, -3.5F, 6.0F, 17.0F, 7.0F, new CubeDeformation(0.0F))
		.texOffs(187, 159).addBox(-27.0F, -8.5F, -3.5F, 6.0F, 17.0F, 7.0F, new CubeDeformation(0.0F))
		.texOffs(253, 0).addBox(-26.4F, -2.5F, -2.5F, 45.0F, 5.0F, 5.0F, new CubeDeformation(0.0F)), PartPose.offset(4.0F, 15.5F, 32.5F));

		PartDefinition cube_r10 = BackWheels.addOrReplaceChild("cube_r10", CubeListBuilder.create().texOffs(187, 159).addBox(-2.0F, -8.5F, -3.5F, 6.0F, 17.0F, 7.0F, new CubeDeformation(0.0F))
		.texOffs(187, 159).addBox(-42.0F, -8.5F, -3.5F, 6.0F, 17.0F, 7.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(15.0F, 0.0F, 0.0F, -0.7854F, 0.0F, 0.0F));

		PartDefinition cube_r11 = BackWheels.addOrReplaceChild("cube_r11", CubeListBuilder.create().texOffs(187, 159).addBox(-2.0F, -8.5F, -3.5F, 6.0F, 17.0F, 7.0F, new CubeDeformation(0.0F))
		.texOffs(187, 159).addBox(-42.0F, -8.5F, -3.5F, 6.0F, 17.0F, 7.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(15.0F, 0.0F, 0.0F, -1.5708F, 0.0F, 0.0F));

		PartDefinition cube_r12 = BackWheels.addOrReplaceChild("cube_r12", CubeListBuilder.create().texOffs(187, 159).addBox(-2.0F, -8.5F, -3.5F, 6.0F, 17.0F, 7.0F, new CubeDeformation(0.0F))
		.texOffs(187, 159).addBox(-42.0F, -8.5F, -3.5F, 6.0F, 17.0F, 7.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(15.0F, 0.0F, 0.0F, -2.3562F, 0.0F, 0.0F));

		return LayerDefinition.create(meshdefinition, 512, 512);
	}

	@Override
	public void renderToBuffer(PoseStack poseStack, VertexConsumer vertexConsumer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
		this.Body.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
		this.FrontWheels.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
		this.BackWheels.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
	}

	@Override
	public void prepareMobModel(Car car, float p_102615_, float p_102616_, float partialTick) {
		Animation.animate(this.Body, CarRender.BODY_ANIMATION, car.driveTick, car.lastDrivePartialTick, car.forwardMotion);
		Animation.animate(this.FrontWheels, CarRender.FRONT_WHEEL_ANIMATION, car.driveTick, car.lastDrivePartialTick, car.forwardMotion);
		Animation.animate(this.BackWheels, CarRender.BACK_WHEEL_ANIMATION, car.driveTick, car.lastDrivePartialTick, car.forwardMotion);
	}

	@Override
	public void setupAnim(Car car, float partialTick, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
		car.updateLastDrivePartialTick(partialTick);
		Animation.animate(this.Body, CarRender.BODY_ANIMATION, car.driveTick, car.lastDrivePartialTick, car.forwardMotion);
		Animation.animate(this.FrontWheels, CarRender.FRONT_WHEEL_ANIMATION, car.driveTick, car.lastDrivePartialTick, car.forwardMotion);
		Animation.animate(this.BackWheels, CarRender.BACK_WHEEL_ANIMATION, car.driveTick, car.lastDrivePartialTick, car.forwardMotion);

		this.FrontWheels.yRot = car.getFrontWheelRotation(partialTick);
	}
}