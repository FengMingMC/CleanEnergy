// Made with Blockbench 4.10.4
// Exported for Minecraft version 1.17 or later with Mojang mappings
// Paste this class into your mod and generate all required imports


public class wind_turbine_blade<T extends Entity> extends EntityModel<T> {
	// This layer location should be baked with EntityRendererProvider.Context in the entity renderer and passed into this model's constructor
	public static final ModelLayerLocation LAYER_LOCATION = new ModelLayerLocation(new ResourceLocation("modid", "wind_turbine_blade"), "main");
	private final ModelPart main;
	private final ModelPart blade_top;
	private final ModelPart blade_left;
	private final ModelPart blade_right;

	public wind_turbine_blade(ModelPart root) {
		this.main = root.getChild("main");
		this.blade_top = root.getChild("blade_top");
		this.blade_left = root.getChild("blade_left");
		this.blade_right = root.getChild("blade_right");
	}

	public static LayerDefinition createBodyLayer() {
		MeshDefinition meshdefinition = new MeshDefinition();
		PartDefinition partdefinition = meshdefinition.getRoot();

		PartDefinition main = partdefinition.addOrReplaceChild("main", CubeListBuilder.create().texOffs(110, 0).addBox(-3.0F, -3.0F, 5.0F, 6.0F, 6.0F, 3.0F, new CubeDeformation(0.0F))
		.texOffs(0, 0).addBox(-5.0F, -5.0F, -4.0F, 10.0F, 10.0F, 9.0F, new CubeDeformation(0.0F))
		.texOffs(110, 119).addBox(-4.0F, -4.0F, -5.0F, 8.0F, 8.0F, 1.0F, new CubeDeformation(0.0F))
		.texOffs(114, 112).addBox(-3.0F, -3.0F, -6.0F, 6.0F, 6.0F, 1.0F, new CubeDeformation(0.0F))
		.texOffs(118, 107).addBox(-2.0F, -2.0F, -7.0F, 4.0F, 4.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 24.0F, 0.0F));

		PartDefinition blade_top = main.addOrReplaceChild("blade_top", CubeListBuilder.create().texOffs(22, 75).addBox(-1.0F, 52.0F, -2.0F, 2.0F, 48.0F, 5.0F, new CubeDeformation(0.0F))
		.texOffs(0, 73).addBox(-2.0F, 4.0F, -3.0F, 4.0F, 48.0F, 7.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, 0.0F));

		PartDefinition blade_left = main.addOrReplaceChild("blade_left", CubeListBuilder.create().texOffs(22, 75).addBox(-1.0F, 52.0F, -2.0F, 2.0F, 48.0F, 5.0F, new CubeDeformation(0.0F))
		.texOffs(0, 73).addBox(-2.0F, 4.0F, -3.0F, 4.0F, 48.0F, 7.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 2.0944F));

		PartDefinition blade_right = main.addOrReplaceChild("blade_right", CubeListBuilder.create().texOffs(22, 75).addBox(-1.0F, 52.0F, -2.0F, 2.0F, 48.0F, 5.0F, new CubeDeformation(0.0F))
		.texOffs(0, 73).addBox(-2.0F, 4.0F, -3.0F, 4.0F, 48.0F, 7.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, 0.0F, -2.0944F));

		return LayerDefinition.create(meshdefinition, 128, 128);
	}

	@Override
	public void setupAnim(Entity entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {

	}

	@Override
	public void renderToBuffer(PoseStack poseStack, VertexConsumer vertexConsumer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
		main.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
	}
}