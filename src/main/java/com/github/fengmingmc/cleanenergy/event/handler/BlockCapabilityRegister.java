package com.github.fengmingmc.cleanenergy.event.handler;

import com.github.fengmingmc.cleanenergy.CleanEnergy;
import com.github.fengmingmc.cleanenergy.world.level.block.entity.BlockEntityTypeList;
import com.github.fengmingmc.cleanenergy.world.level.block.entity.SolarPanelBlockEntity;
import com.github.fengmingmc.cleanenergy.world.level.block.entity.WindTurbineBladeBlockEntity;
import net.industrybase.api.CapabilityList;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.capabilities.Capabilities;
import net.neoforged.neoforge.capabilities.RegisterCapabilitiesEvent;

@EventBusSubscriber(modid = CleanEnergy.MODID, bus = EventBusSubscriber.Bus.MOD)
public class BlockCapabilityRegister {
	@SubscribeEvent
	private static void registerCapabilities(final RegisterCapabilitiesEvent event) {
		event.registerBlockEntity(CapabilityList.MECHANICAL_TRANSMIT, BlockEntityTypeList.WIND_TURBINE_BLADE.get(), WindTurbineBladeBlockEntity::getElectricPower);
		// 给太阳能板注册 EP 能力
		event.registerBlockEntity(CapabilityList.ELECTRIC_POWER, BlockEntityTypeList.SOLAR_PANEL.get(), SolarPanelBlockEntity::getElectricPower);
		// 给太阳能板注册 FE 能力
		event.registerBlockEntity(Capabilities.EnergyStorage.BLOCK, BlockEntityTypeList.SOLAR_PANEL.get(), SolarPanelBlockEntity::getElectricPower);
	}
}
