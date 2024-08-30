package com.github.fengmingmc.cleanenergy.world.level.block;

import com.github.fengmingmc.cleanenergy.world.level.block.entity.BlockEntityTypeList;
import com.github.fengmingmc.cleanenergy.world.level.block.entity.SolarPanelBlockEntity;
import com.mojang.serialization.MapCodec;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.BaseEntityBlock;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.RenderShape;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityTicker;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;
import org.jetbrains.annotations.Nullable;



public class SolarPanelBlock extends BaseEntityBlock {
	public static final MapCodec<SolarPanelBlock> CODEC = simpleCodec((properties) -> new SolarPanelBlock());
	// 创建基座的 VoxelShape，从 0,0,0 到 16,5,16，即 16x5x16，单位是像素
	private static final VoxelShape PEDESTAL = Block.box(0.0D, 0.0D, 0.0D, 16.0D, 5.0D, 16.0D);
	// 创建棍子（？）的 VoxelShape，从 7.5,5,7.5 到 8.5,13,8.5，即 1x8x1
	private static final VoxelShape STICK = Block.box(7.5D, 5.0D, 7.5D, 8.5D, 13.0D, 8.5D);
	// 创建板子的 VoxelShape，从 -8,13,-8 到 24,14,24，即 32x1x32
	private static final VoxelShape PANEL = Block.box(-8.0D, 13.0D, -8.0D, 24.0D, 14.0D, 24.0D);
	// 将棍子、板子、基座组合起来
	private static final VoxelShape SHAPE = Shapes.or(PEDESTAL, STICK, PANEL);

	public SolarPanelBlock() {
		super(Properties.ofFullCopy(Blocks.IRON_BLOCK)); // 铁做的底座
	}

	@Nullable
	public <T extends BlockEntity> BlockEntityTicker<T> getTicker(Level level, BlockState state, BlockEntityType<T> serverType) {
		return level.isClientSide ? null : createTickerHelper(serverType, BlockEntityTypeList.SOLAR_PANEL.get(), SolarPanelBlockEntity::serverTick);
	}

	@Override
	protected RenderShape getRenderShape(BlockState pState) {
		return RenderShape.MODEL;
	}

	@Override
	protected VoxelShape getShape(BlockState pState, BlockGetter pLevel, BlockPos pPos, CollisionContext pContext) {
		return SHAPE; // 返回一个之前组合的 VoxelShape
		// CollisionShape、VisualShape在不覆写的情况下，会直接用这里返回的 VoxelShape
		// 至于为什么，可以看 {@link BlockBehaviour#getCollisionShape}
		// 和 {@link BlockBehaviour#getVisualShape} 怎么写的
	}

	@Nullable
	@Override
	public BlockEntity newBlockEntity(BlockPos pos, BlockState state) {
		return new SolarPanelBlockEntity(pos, state); // 创建对应的 BlockEntity
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