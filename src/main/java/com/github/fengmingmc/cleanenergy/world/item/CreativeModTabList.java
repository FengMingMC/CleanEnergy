package com.github.fengmingmc.cleanenergy.world.item;

import com.github.fengmingmc.cleanenergy.CleanEnergy;
import com.github.fengmingmc.cleanenergy.world.level.block.BlockList;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

public class CreativeModTabList {
    public static final DeferredRegister<CreativeModeTab> CREATIVE_MODE_TABS = DeferredRegister.create(Registries.CREATIVE_MODE_TAB, CleanEnergy.MODID);
    public static final DeferredHolder<CreativeModeTab,CreativeModeTab> CLEANENERGY = CREATIVE_MODE_TABS.register("cleanenergy", () -> CreativeModeTab.builder()
            .title(Component.translatable("itemGroup.cleanenergy"))
            .icon(() -> ItemList.SOLAR_PANEL.get().getDefaultInstance())
            .displayItems((parameters, output) -> {
                output.accept(ItemList.SILICON_INGOT.get());
                output.accept(ItemList.SOLAR_PANEL_TOP.get());
                output.accept(ItemList.FAN.get());

                output.accept(ItemList.SOLAR_PANEL.get());
                output.accept(ItemList.WIND_TURBINE_BLADE.get());
            }).build()
    );
}
