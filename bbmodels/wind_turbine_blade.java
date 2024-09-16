// Made with Blockbench 4.10.4
// Exported for Minecraft version 1.17 or later with Mojang mappings
// Paste this class into your mod and generate all required imports


public class wind_turbine_blade<T extends Entity> extends EntityModel<T> {
	// This layer location should be baked with EntityRendererProvider.Context in the entity renderer and passed into this model's constructor
	public static final ModelLayerLocation LAYER_LOCATION = new ModelLayerLocation(new ResourceLocation("modid", "wind_turbine_blade"), "main");
	private final ModelPart main;
	private final ModelPart blade1;
	private final ModelPart blade2;
	private final ModelPart blade3;

	public wind_turbine_blade(ModelPart root) {
		this.main = root.getChild("main");
		this.blade1 = root.getChild("blade1");
		this.blade2 = root.getChild("blade2");
		this.blade3 = root.getChild("blade3");
	}

	public static LayerDefinition createBodyLayer() {
		MeshDefinition meshdefinition = new MeshDefinition();
		PartDefinition partdefinition = meshdefinition.getRoot();

		PartDefinition main = partdefinition.addOrReplaceChild("main", CubeListBuilder.create().texOffs(110, 0).addBox(-3.0F, -3.0F, 5.0F, 6.0F, 6.0F, 3.0F, new CubeDeformation(0.0F))
		.texOffs(0, 0).addBox(-4.0F, -4.0F, -4.0F, 8.0F, 8.0F, 9.0F, new CubeDeformation(0.0F))
		.texOffs(114, 121).addBox(-3.0F, -3.0F, -5.0F, 6.0F, 6.0F, 1.0F, new CubeDeformation(0.0F))
		.texOffs(118, 116).addBox(-2.0F, -2.0F, -6.0F, 4.0F, 4.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 24.0F, 0.0F));

		PartDefinition blade1 = main.addOrReplaceChild("blade1", CubeListBuilder.create().texOffs(0, 50).addBox(-1.0F, 26.0F, -2.0F, 2.0F, 24.0F, 5.0F, new CubeDeformation(0.0F))
		.texOffs(0, 98).addBox(-2.0F, 3.0F, -3.0F, 4.0F, 23.0F, 7.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, 0.0F));

		PartDefinition blade2 = main.addOrReplaceChild("blade2", CubeListBuilder.create().texOffs(0, 50).addBox(-1.0F, 26.0F, -2.0F, 2.0F, 24.0F, 5.0F, new CubeDeformation(0.0F))
		.texOffs(0, 98).addBox(-2.0F, 3.0F, -3.0F, 4.0F, 23.0F, 7.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 2.0944F));

		PartDefinition blade3 = main.addOrReplaceChild("blade3", CubeListBuilder.create().texOffs(0, 50).addBox(-1.0F, 26.0F, -2.0F, 2.0F, 24.0F, 5.0F, new CubeDeformation(0.0F))
		.texOffs(0, 98).addBox(-2.0F, 3.0F, -3.0F, 4.0F, 23.0F, 7.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, 0.0F, -2.0944F));

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