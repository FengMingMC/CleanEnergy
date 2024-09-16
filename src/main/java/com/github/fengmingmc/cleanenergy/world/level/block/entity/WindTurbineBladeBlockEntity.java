package com.github.fengmingmc.cleanenergy.world.level.block.entity;

import com.github.fengmingmc.cleanenergy.world.level.block.WindTurbineBladeBlock;
import net.industrybase.api.transmit.MechanicalTransmit;
import net.industrybase.api.transmit.TransmitNetwork;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.HolderLookup;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.util.Mth;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;

import javax.annotation.Nullable;

public class WindTurbineBladeBlockEntity extends BlockEntity {
	private final MechanicalTransmit transmit = new MechanicalTransmit(this);
	private boolean subscribed = false;

	public WindTurbineBladeBlockEntity(BlockPos pPos, BlockState pBlockState) {
		super(BlockEntityTypeList.WIND_TURBINE_BLADE.get(), pPos, pBlockState);
	}
	@Override
	public void onLoad() {
		super.onLoad();
		this.transmit.register(); // 通过 EP 管理器，向 EP 能量网络注册这个方块
		this.transmit.setResistance(5);
		int height = this.worldPosition.getY();
		if (height > 0) {
			int power = height / 8;
			int windPower = Mth.clamp(power,1,8);
			this.transmit.setPower(windPower); // 更新输出能量
		}
	}

	@Nullable
	public MechanicalTransmit getElectricPower(Direction side) {
		if (side == this.getBlockState().getValue(WindTurbineBladeBlock.FACING).getOpposite()) {
			return this.transmit;
		}
		return null;
	}

	@Override
	public void setRemoved() {
		this.transmit.remove(); // 从 EP 能量网络中移除这个方块
		super.setRemoved();
	}

	@Override
	public void loadAdditional(CompoundTag tag, HolderLookup.Provider registries) {
		super.loadAdditional(tag, registries);
		this.transmit.readFromNBT(tag); // EP 的能量数据由 EP 管理器负责
	}

	@Override
	protected void saveAdditional(CompoundTag tag, HolderLookup.Provider registries) {
		super.saveAdditional(tag, registries);
		this.transmit.writeToNBT(tag); // EP 的能量数据由 EP 管理器负责
	}

	public boolean isSubscribed() {
		return this.subscribed;
	}

	public void setSubscribed() {
		this.subscribed = true;
	}

	public TransmitNetwork.RotateContext getRotate() {
		if (this.transmit.getNetwork() != null) {
			return this.transmit.getNetwork().getRotateContext(this.worldPosition);
		}
		return TransmitNetwork.RotateContext.NULL;
	}
}
