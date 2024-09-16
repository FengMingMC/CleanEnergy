package com.github.fengmingmc.cleanenergy.world.level.block;

import com.github.fengmingmc.cleanenergy.world.level.block.entity.WindTurbineBladeBlockEntity;
import com.google.common.collect.ImmutableMap;
import com.mojang.serialization.MapCodec;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.BaseEntityBlock;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.RenderShape;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.DirectionProperty;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;
import org.jetbrains.annotations.Nullable;

import java.util.EnumMap;
import java.util.HashMap;
import java.util.Map;

public class WindTurbineBladeBlock extends BaseEntityBlock {
	public static final MapCodec<WindTurbineBladeBlock> CODEC = simpleCodec((properties) -> new WindTurbineBladeBlock());
	public static final DirectionProperty FACING = BlockStateProperties.HORIZONTAL_FACING;

	private static final VoxelShape CORE = Block.box(4.0D, 4.0D, 4.0D, 12.0D, 12.0D, 12.0D);
	private static final Map<Direction, VoxelShape> SHAPES_DIRECTION = new EnumMap<>(ImmutableMap.of(
			Direction.NORTH, Block.box(4.0D, 4.0D, 0.0D, 12.0D, 12.0D, 4.0D),
			Direction.EAST, Block.box(12.0D, 4.0D, 4.0D, 16.0D, 12.0D, 12.0D),
			Direction.SOUTH, Block.box(4.0D, 4.0D, 12.0D, 12.0D, 12.0D, 16.0D),
			Direction.WEST, Block.box(0.0D, 4.0D, 4.0D, 4.0D, 12.0D, 12.0D),
			Direction.UP, Block.box(4.0D, 12.0D, 4.0D, 12.0D, 16.0D, 12.0D),
			Direction.DOWN, Block.box(4.0D, 0.0D, 4.0D, 12.0D, 4.0D, 12.0D)));
	private static final Map<BlockState, VoxelShape> SHAPES = new HashMap<>();

	public WindTurbineBladeBlock() {
		super(Properties.ofFullCopy(Blocks.OAK_PLANKS));
		this.registerDefaultState(this.stateDefinition.any().setValue(FACING, Direction.NORTH));

		for (BlockState state : this.getStateDefinition().getPossibleStates()) {
			SHAPES.put(state, Shapes.or(CORE, SHAPES_DIRECTION.get(state.getValue(FACING).getOpposite())));
		}
	}

	@Override
	public VoxelShape getShape(BlockState state, BlockGetter level, BlockPos pos, CollisionContext context) {
		return SHAPES.get(state);
	}

	@Override
	protected RenderShape getRenderShape(BlockState pState) {
		return RenderShape.ENTITYBLOCK_ANIMATED;
	}

	@Nullable
	@Override
	public BlockState getStateForPlacement(BlockPlaceContext context) {
		return this.defaultBlockState().setValue(FACING, context.getHorizontalDirection().getOpposite());
	}

	@Override
	protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
		builder.add(FACING);
	}

	@Nullable
	@Override
	public BlockEntity newBlockEntity(BlockPos pos, BlockState state) {
		return new WindTurbineBladeBlockEntity(pos, state);
	}

	@Override
	protected MapCodec<? extends BaseEntityBlock> codec() {
		return CODEC;
	}
}