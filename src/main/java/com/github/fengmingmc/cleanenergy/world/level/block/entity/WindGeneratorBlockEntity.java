package com.github.fengmingmc.cleanenergy.world.level.block.entity;

import com.github.fengmingmc.cleanenergy.world.level.block.WindGeneratorBlock;
import net.industrybase.api.electric.ElectricPower;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.HolderLookup;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.util.Mth;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;

import javax.annotation.Nullable;

public class WindGeneratorBlockEntity extends BlockEntity {
    // 模仿 InB 的其他方块，在这里创建一个 EP 管理器
    private final ElectricPower electricPower = new ElectricPower(this);
    private double oldPower;

    public WindGeneratorBlockEntity(BlockPos pPos, BlockState pBlockState) {
        super(BlockEntityTypeList.WIND_GENERATOR.get(), pPos, pBlockState);
    }

    /**
     * 这个方法在世界加载完成后，或者方块刚被放下的第一个 tick 时会被调用
    */
    @Override
    public void onLoad() {
        // 父类（BlockEntity.java）在这里执行了 requestModelDataUpdate();
        // 可能有什么用处，所以用 super 语句保留父类的执行
        super.onLoad();
        electricPower.register(); // 通过 EP 管理器，向 EP 能量网络注册这个方块
        int heights = this.worldPosition.getY();
        if (heights > 0) {
            int power = heights / 8;
            int windpower = Mth.clamp(power,1,15);
            this.electricPower.setOutputPower(windpower); // 更新输出能量
        } else {
            this.electricPower.setOutputPower(0);
        }
    }

    @Nullable
    public ElectricPower getElectricPower(Direction side) {
        if (side == this.getBlockState().getValue(WindGeneratorBlock.FACING).getOpposite()) { // 选择侧面输出
            // 如果请求的方向正确，则返回对应的 EP
            return this.electricPower;
        }
        return null; // 否则返回 null
    }

    @Override
    public void setRemoved() {
        this.electricPower.remove(); // 从 EP 能量网络中移除这个方块
        super.setRemoved();
    }

    @Override
    public void loadAdditional(CompoundTag tag, HolderLookup.Provider registries) {
        super.loadAdditional(tag, registries);
        this.oldPower = tag.getDouble("OldPower"); // 把 OldPower 读取出来
        this.electricPower.readFromNBT(tag); // EP 的能量数据由 EP 管理器负责
    }

    @Override
    protected void saveAdditional(CompoundTag tag, HolderLookup.Provider registries) {
        super.saveAdditional(tag, registries);
        tag.putDouble("OldPower", this.oldPower); // 把 OldPower 的数值保存起来
        this.electricPower.writeToNBT(tag); // EP 的能量数据由 EP 管理器负责
    }
}
