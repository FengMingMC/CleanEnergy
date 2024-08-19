package com.github.fengmingmc.cleanenergy.world.level.block;

import com.github.fengmingmc.cleanenergy.CleanEnergy;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.neoforged.neoforge.registries.DeferredBlock;
import net.neoforged.neoforge.registries.DeferredRegister;

import javax.annotation.Nullable;
import java.util.Properties;
import java.util.function.Function;

public class BlockList {
    public static final DeferredRegister.Blocks BLOCK = DeferredRegister.createBlocks(CleanEnergy.MODID);

    public static final DeferredBlock<Block> TESTING_BLOCK = BLOCK.register(
            "testing_block",
            () -> new Block(BlockBehaviour.Properties.of()
                    .destroyTime(2.0f)
                    .explosionResistance(10.0f)
                    .sound(SoundType.GRAVEL)
                    .lightLevel(state -> 7)
                )
    );
    public static final DeferredBlock<Block> SOLAR_PANEL = BLOCK.register("solar_panel",SolarPanel::new);

    private BlockList() {
    }
    private static ResourceKey<Block> createKey(String id) {
        return ResourceKey.create(Registries.BLOCK, ResourceLocation.withDefaultNamespace(id));
    }

}
