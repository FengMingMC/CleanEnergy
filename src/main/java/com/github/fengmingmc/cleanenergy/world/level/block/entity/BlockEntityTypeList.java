package com.github.fengmingmc.cleanenergy.world.level.block.entity;

import com.github.fengmingmc.cleanenergy.CleanEnergy;
import com.github.fengmingmc.cleanenergy.world.level.block.BlockList;
import com.mojang.datafixers.DSL;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

public class BlockEntityTypeList {
	public static final DeferredRegister<BlockEntityType<?>> BLOCK_ENTITY_TYPE = DeferredRegister.create(BuiltInRegistries.BLOCK_ENTITY_TYPE, CleanEnergy.MODID);

	public static final DeferredHolder<BlockEntityType<?>, BlockEntityType<SolarPanelBlockEntity>> SOLAR_PANEL = register("solar_panel", SolarPanelBlockEntity::new, BlockList.SOLAR_PANEL);
	public static final DeferredHolder<BlockEntityType<?>, BlockEntityType<WindTurbineBladeBlockEntity>> WIND_TURBINE_BLADE = register("wind_turbine_blade", WindTurbineBladeBlockEntity::new, BlockList.WIND_TURBINE_BLADE);

	private static <T extends BlockEntity> DeferredHolder<BlockEntityType<?>, BlockEntityType<T>> register(String name, BlockEntityType.BlockEntitySupplier<T> blockEntity, DeferredHolder<Block, ? extends Block> block) {
		return BLOCK_ENTITY_TYPE.register(name, () -> BlockEntityType.Builder.of(blockEntity, block.get()).build(DSL.remainderType()));
	}
}
