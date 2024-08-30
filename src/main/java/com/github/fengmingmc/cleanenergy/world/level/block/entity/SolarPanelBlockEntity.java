package com.github.fengmingmc.cleanenergy.world.level.block.entity;

import com.github.fengmingmc.cleanenergy.event.handler.BlockCapabilityRegister;
import com.github.fengmingmc.cleanenergy.world.level.block.SolarPanelBlock;
import net.industrybase.api.electric.ElectricPower;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.HolderLookup;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;

import javax.annotation.Nullable;

public class SolarPanelBlockEntity extends BlockEntity {
	// 模仿 InB 的其他方块，在这里创建一个 EP 管理器
	private final ElectricPower electricPower = new ElectricPower(this);
	private double oldPower;

	public SolarPanelBlockEntity(BlockPos pPos, BlockState pBlockState) {
		super(BlockEntityTypeList.SOLAR_PANEL.get(), pPos, pBlockState);
	}

	/**
	 * 这个方法在世界加载完成后，或者方块刚被放下的第一个 tick 时会被调用
	 */
	@Override
	public void onLoad() {
		// 父类（BlockEntity.java）在这里执行了 requestModelDataUpdate();
		// 可能有什么用处，所以用 super 语句保留父类的执行
		super.onLoad();
		this.electricPower.register(); // 通过 EP 管理器，向 EP 能量网络注册这个方块
	}

	/**
	 * 在 {@link SolarPanelBlock#getTicker} 中引用这个方法后，服务端每个 tick（0.05s）就会调用一次这个方法
	 * @param level 执行 tick 时，方块所在存档
	 * @param pos 方块坐标
	 * @param state 方块状态
	 * @param blockEntity 执行 tick 时的方块实体（就是这个 SolarPanelBlockEntity）
	 */
	public static void serverTick(Level level, BlockPos pos, BlockState state, SolarPanelBlockEntity blockEntity) {
		// TODO 通过 level 获取存档的时间，然后计算此时应当输出的能量
	}

	/**
	 * 供自己或别的模组获取能力用的，在 {@link BlockCapabilityRegister#registerCapabilities} 会被引用
	 */
	@Nullable
	public ElectricPower getElectricPower(Direction side) {
		if (side == Direction.DOWN) { // 根据太阳能板的模型，能量输出口更适合在下面
			// 如果要在前后左右也能输出，就需要把底座加高，否则不美观

			// 如果请求的方向正确，则返回对应的 EP
			return this.electricPower;
		}
		return null; // 否则返回 null
	}

	/**
	 * BlockEntity 在被移除时会调用这个方法
	 */
	@Override
	public void setRemoved() {
		this.electricPower.remove(); // 从 EP 能量网络中移除这个方块
		super.setRemoved();
	}

	/**
	 * 读取 NBT 时调用，从传入的 tag 中读取 NBT 数据，然后加载到 BlockEntity 中
	 * @param tag 由 MC 从硬盘读取，然后传入的 NBT 数据
	 * @param registries 不知道干啥用的
	 */
	@Override
	public void loadAdditional(CompoundTag tag, HolderLookup.Provider registries) {
		super.loadAdditional(tag, registries);
		this.oldPower = tag.getDouble("OldPower"); // 把 OldPower 读取出来
		this.electricPower.readFromNBT(tag); // EP 的能量数据由 EP 管理器负责
	}

	/**
	 * 保存 NBT 时调用，将 BlockEntity 的数据保存到传入的 tag 中
	 * @param tag 传入的 tag
	 * @param registries 不知道干啥用的
	 */
	@Override
	protected void saveAdditional(CompoundTag tag, HolderLookup.Provider registries) {
		super.saveAdditional(tag, registries);
		tag.putDouble("OldPower", this.oldPower); // 把 OldPower 的数值保存起来
		this.electricPower.writeToNBT(tag); // EP 的能量数据由 EP 管理器负责
	}
}
