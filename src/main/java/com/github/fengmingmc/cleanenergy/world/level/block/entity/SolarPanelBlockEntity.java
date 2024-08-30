package com.github.fengmingmc.cleanenergy.world.level.block.entity;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;

public class SolarPanelBlockEntity extends BlockEntity {
	public SolarPanelBlockEntity(BlockPos pPos, BlockState pBlockState) {
		super(BlockEntityTypeList.SOLAR_PANEL.get(), pPos, pBlockState);
	}
}
