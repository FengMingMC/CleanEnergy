package com.github.fengmingmc.cleanenergy;

import com.github.fengmingmc.cleanenergy.world.item.CreativeModTabList;
import com.github.fengmingmc.cleanenergy.world.item.ItemList;
import com.github.fengmingmc.cleanenergy.world.level.block.BlockList;
import com.github.fengmingmc.cleanenergy.world.level.block.entity.BlockEntityTypeList;
import com.mojang.logging.LogUtils;
import net.industrybase.api.IndustryBaseApi;
import net.minecraft.world.item.CreativeModeTab;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.event.lifecycle.FMLClientSetupEvent;
import net.neoforged.fml.event.lifecycle.FMLCommonSetupEvent;
import net.neoforged.neoforge.data.event.GatherDataEvent;
import org.slf4j.Logger;

@Mod(CleanEnergy.MODID)
public class CleanEnergy {
	public static final String MODID = "cleanenergy";

	public static final Logger LOGGER = LogUtils.getLogger();

	public CleanEnergy(IEventBus modEventBus, ModContainer modContainer) {
		BlockList.BLOCK.register(modEventBus);
		BlockEntityTypeList.BLOCK_ENTITY_TYPE.register(modEventBus);
		ItemList.ITEM.register(modEventBus);
		CreativeModTabList.CREATIVE_MODE_TABS.register(modEventBus);

		modEventBus.addListener(this::setup);
		modEventBus.addListener(this::doClientStuff);
	}

	private void setup(final FMLCommonSetupEvent event) {
	}

	private void doClientStuff(final FMLClientSetupEvent event) {
	}

}
