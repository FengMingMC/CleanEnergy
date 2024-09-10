package com.github.fengmingmc.cleanenergy.world.level.block;

import com.github.fengmingmc.cleanenergy.CleanEnergy;
import net.minecraft.world.level.block.Block;
import net.neoforged.neoforge.registries.DeferredBlock;
import net.neoforged.neoforge.registries.DeferredRegister;

public class BlockList {
	public static final DeferredRegister.Blocks BLOCK = DeferredRegister.createBlocks(CleanEnergy.MODID);

	public static final DeferredBlock<Block> SOLAR_PANEL = BLOCK.register("solar_panel", SolarPanelBlock::new);
	public static final DeferredBlock<Block> WIND_GENERATOR = BLOCK.register("wind_generator", WindGeneratorBlock::new);

	private BlockList() {
	}
}
