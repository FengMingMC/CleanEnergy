package com.github.fengmingmc.cleanenergy.world.item;

import com.github.fengmingmc.cleanenergy.CleanEnergy;
import com.github.fengmingmc.cleanenergy.world.level.block.BlockList;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.neoforged.neoforge.registries.DeferredItem;
import net.neoforged.neoforge.registries.DeferredRegister;

public class ItemList {
    public static final DeferredRegister.Items ITEM = DeferredRegister.createItems(CleanEnergy.MODID);

    public static final DeferredItem<BlockItem> SOLAR_PANEL = ITEM.register("solar_panel", () -> new BlockItem(BlockList.SOLAR_PANEL.get(), new Item.Properties()));

    private ItemList() {
    }
}
