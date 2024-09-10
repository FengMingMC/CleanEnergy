package com.github.fengmingmc.cleanenergy.world.item;

import com.github.fengmingmc.cleanenergy.CleanEnergy;
import com.github.fengmingmc.cleanenergy.world.level.block.BlockList;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.neoforged.neoforge.registries.DeferredItem;
import net.neoforged.neoforge.registries.DeferredRegister;

public class ItemList {
    public static final DeferredRegister.Items ITEM = DeferredRegister.createItems(CleanEnergy.MODID);

    public static final  DeferredItem<Item> SILICON_INGOT = ITEM.register("silicon_ingot", () -> new Item(new Item.Properties()));
    public static final  DeferredItem<Item> SOLAR_PANEL_TOP = ITEM.register("solar_panel_top", () -> new Item(new Item.Properties()));
    public static final  DeferredItem<Item> FAN = ITEM.register("fan", () -> new Item(new Item.Properties()));

    public static final DeferredItem<BlockItem> SOLAR_PANEL = ITEM.register("solar_panel", () -> new BlockItem(BlockList.SOLAR_PANEL.get(), new Item.Properties()));
    public static final DeferredItem<BlockItem> WIND_GENERATOR = ITEM.register("wind_generator", () -> new BlockItem(BlockList.WIND_GENERATOR.get(), new Item.Properties()));

    private ItemList() {
    }
}
