package com.github.fengmingmc.cleanenergy.world.level.block;

import com.github.fengmingmc.cleanenergy.world.level.block.entity.WindGeneratorBlockEntity;
import com.mojang.serialization.MapCodec;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.block.BaseEntityBlock;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.DirectionProperty;
import org.jetbrains.annotations.Nullable;



public class WindGeneratorBlock extends BaseEntityBlock {
    public static final MapCodec<WindGeneratorBlock> CODEC = simpleCodec((properties) -> new WindGeneratorBlock());

    public static DirectionProperty FACING;

    public WindGeneratorBlock() {
        super(Properties.ofFullCopy(Blocks.IRON_BLOCK));// 铁做的底座
        this.registerDefaultState(this.stateDefinition.any().setValue(FACING, Direction.NORTH));
    }

    @Nullable
    @Override
    public BlockEntity newBlockEntity(BlockPos pos, BlockState state) {
        return new WindGeneratorBlockEntity(pos, state); // 创建对应的 BlockEntity
        // 世界中的每个太阳能板都绑定到了一个 BlockEntity
        // 这个方法，每次放置新的太阳能板时都会调用
        // 所以每次调用，都要返回一个新的 BlockEntity
    }

    public @Nullable BlockState getStateForPlacement(BlockPlaceContext context) {
        return this.defaultBlockState().setValue(FACING, context.getClickedFace().getOpposite());
    }

    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(FACING);
    }

    @Override
    protected MapCodec<? extends BaseEntityBlock> codec() {
        // 这个好像是 1.20.4 加入的，作用不明
        // 估计是 bugjang 招了个 codec 仙人
        // 依葫芦画瓢就是了
        return CODEC;
    }

    static {
        FACING = BlockStateProperties.FACING;
    }
}