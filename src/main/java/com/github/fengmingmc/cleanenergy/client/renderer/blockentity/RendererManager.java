package com.github.fengmingmc.cleanenergy.client.renderer.blockentity;

import com.github.fengmingmc.cleanenergy.CleanEnergy;
import com.github.fengmingmc.cleanenergy.world.level.block.entity.BlockEntityTypeList;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.client.event.EntityRenderersEvent;

@EventBusSubscriber(modid = CleanEnergy.MODID, bus = EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public class RendererManager {
	@SubscribeEvent
	public static void registerRenderers(EntityRenderersEvent.RegisterRenderers event) {
		event.registerBlockEntityRenderer(BlockEntityTypeList.WIND_TURBINE_BLADE.get(), WindTurbineBladeRenderer::new);
	}

	@SubscribeEvent
	public static void registerLayerDefinitions(EntityRenderersEvent.RegisterLayerDefinitions event) {
		event.registerLayerDefinition(WindTurbineBladeRenderer.MAIN, WindTurbineBladeRenderer::createBodyLayer);
	}
}
