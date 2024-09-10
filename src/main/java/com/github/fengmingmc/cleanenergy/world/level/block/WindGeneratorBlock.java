package com.github.fengmingmc.cleanenergy.world.level.block;

import com.github.fengmingmc.cleanenergy.world.level.block.entity.WindGeneratorBlockEntity;
import com.mojang.serialization.MapCodec;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.BaseEntityBlock;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import org.jetbrains.annotations.Nullable;



public class WindGeneratorBlock extends BaseEntityBlock {
    public static final MapCodec<WindGeneratorBlock> CODEC = simpleCodec((properties) -> new WindGeneratorBlock());

    public WindGeneratorBlock() {
        super(Properties.ofFullCopy(Blocks.IRON_BLOCK));// 铁做的底座
    }

    @Nullable
    @Override
    public BlockEntity newBlockEntity(BlockPos pos, BlockState state) {
        return new WindGeneratorBlockEntity(pos, state); // 创建对应的 BlockEntity
        // 世界中的每个太阳能板都绑定到了一个 BlockEntity
        // 这个方法，每次放置新的太阳能板时都会调用
        // 所以每次调用，都要返回一个新的 BlockEntity
    }

    @Override
    protected MapCodec<? extends BaseEntityBlock> codec() {
        // 这个好像是 1.20.4 加入的，作用不明
        // 估计是 bugjang 招了个 codec 仙人
        // 依葫芦画瓢就是了
        return CODEC;
    }
}