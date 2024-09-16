package com.github.fengmingmc.cleanenergy.client.renderer.blockentity;

import com.github.fengmingmc.cleanenergy.CleanEnergy;
import com.github.fengmingmc.cleanenergy.world.level.block.entity.WindTurbineBladeBlockEntity;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Axis;
import net.industrybase.api.network.client.SubscribeSpeedPacket;
import net.industrybase.api.transmit.TransmitNetwork;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderer;
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
import net.minecraft.core.BlockPos;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.phys.AABB;
import net.neoforged.neoforge.network.PacketDistributor;

public class WindTurbineBladeRenderer implements BlockEntityRenderer<WindTurbineBladeBlockEntity> {
	public static final ModelLayerLocation MAIN = new ModelLayerLocation(ResourceLocation.fromNamespaceAndPath(CleanEnergy.MODID, "wind_turbine_blade"), "main");
	public static final ResourceLocation TEXTURE = ResourceLocation.fromNamespaceAndPath(CleanEnergy.MODID, "textures/entity/wind_turbine_blade.png");
	private final ModelPart main;

	public WindTurbineBladeRenderer(BlockEntityRendererProvider.Context context) {
		ModelPart root = context.bakeLayer(MAIN);
		this.main = root.getChild("main");
	}

	public static LayerDefinition createBodyLayer() {
		MeshDefinition meshdefinition = new MeshDefinition();
		PartDefinition partdefinition = meshdefinition.getRoot();

		PartDefinition main = partdefinition.addOrReplaceChild("main", CubeListBuilder.create().texOffs(110, 0).addBox(-3.0F, -3.0F, 5.0F, 6.0F, 6.0F, 3.0F, new CubeDeformation(0.0F))
				.texOffs(0, 0).addBox(-5.0F, -5.0F, -4.0F, 10.0F, 10.0F, 9.0F, new CubeDeformation(0.0F))
				.texOffs(110, 119).addBox(-4.0F, -4.0F, -5.0F, 8.0F, 8.0F, 1.0F, new CubeDeformation(0.0F))
				.texOffs(114, 112).addBox(-3.0F, -3.0F, -6.0F, 6.0F, 6.0F, 1.0F, new CubeDeformation(0.0F))
				.texOffs(118, 107).addBox(-2.0F, -2.0F, -7.0F, 4.0F, 4.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.ZERO);

		PartDefinition blade_top = main.addOrReplaceChild("blade_top", CubeListBuilder.create().texOffs(22, 75).addBox(-1.0F, 52.0F, -2.0F, 2.0F, 48.0F, 5.0F, new CubeDeformation(0.0F))
				.texOffs(0, 73).addBox(-2.0F, 4.0F, -3.0F, 4.0F, 48.0F, 7.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, 0.0F));

		PartDefinition blade_left = main.addOrReplaceChild("blade_left", CubeListBuilder.create().texOffs(22, 75).addBox(-1.0F, 52.0F, -2.0F, 2.0F, 48.0F, 5.0F, new CubeDeformation(0.0F))
				.texOffs(0, 73).addBox(-2.0F, 4.0F, -3.0F, 4.0F, 48.0F, 7.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 2.0944F));

		PartDefinition blade_right = main.addOrReplaceChild("blade_right", CubeListBuilder.create().texOffs(22, 75).addBox(-1.0F, 52.0F, -2.0F, 2.0F, 48.0F, 5.0F, new CubeDeformation(0.0F))
				.texOffs(0, 73).addBox(-2.0F, 4.0F, -3.0F, 4.0F, 48.0F, 7.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, 0.0F, -2.0944F));

		return LayerDefinition.create(meshdefinition, 128, 128);
	}

	@Override
	public int getViewDistance() {
		return 256;
	}

	@Override
	public AABB getRenderBoundingBox(WindTurbineBladeBlockEntity blockEntity) {
		BlockPos pos = blockEntity.getBlockPos();
		return new AABB(pos.offset(-256, -256, -256).getCenter(), pos.offset(256, 256, 256).getCenter());
	}

	@Override
	public void render(WindTurbineBladeBlockEntity blockEntity, float partialTick, PoseStack poseStack, MultiBufferSource bufferSource, int packedLight, int packedOverlay) {
		subscribeSpeed(blockEntity);
		poseStack.pushPose();
		poseStack.translate(0.5D, 0.5D, 0.5D);
		rodRotate(blockEntity, partialTick, poseStack);
		main.render(poseStack, bufferSource.getBuffer(RenderType.entityCutout(TEXTURE)), packedLight, packedOverlay);
		poseStack.popPose();
	}

	public static void subscribeSpeed(WindTurbineBladeBlockEntity blockEntity) {
		if (blockEntity.hasLevel() && !blockEntity.isSubscribed()) {
			PacketDistributor.sendToServer(new SubscribeSpeedPacket(blockEntity.getBlockPos()));
			blockEntity.setSubscribed();
		}
	}

	public static void rodRotate(WindTurbineBladeBlockEntity blockEntity, float partialTick, PoseStack poseStack) {
		// 按照轴旋转模型
		switch (blockEntity.getBlockState().getValue(BlockStateProperties.HORIZONTAL_FACING)) {
			case EAST -> poseStack.mulPose(Axis.YP.rotationDegrees(-90.0F));
			case WEST -> poseStack.mulPose(Axis.YP.rotationDegrees(90.0F));
			case SOUTH -> poseStack.mulPose(Axis.YP.rotationDegrees(180.0F));
		}
		Level level = blockEntity.getLevel();
		if (level != null) {
			TransmitNetwork.RotateContext context = blockEntity.getRotate();
			// 根据速度设定传动杆的旋转角度
			poseStack.mulPose(Axis.ZN.rotationDegrees(Mth.rotLerp(partialTick, context.getOldDegree(), context.getDegree())));
		}
	}
}
