package net.cerulan.blockofsky;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;

import java.util.Arrays;

public class SkyBlockEntity extends BlockEntity {

    public enum SkyType {
        Overworld,
        Void,
    }

    public SkyBlockEntity(BlockPos blockPos, BlockState blockState) {
        super(BlockOfSkyMod.SKY_BE_TYPE.get(), blockPos, blockState);
    }

    public SkyBlockEntity(SkyType skyType, BlockPos pos, BlockState state) {
        this(pos, state);
        this.skyType = skyType;
    }

    private SkyType skyType = SkyType.Overworld;

    public SkyType getSkyType() {
        return skyType;
    }

    @Override
    protected void saveAdditional(CompoundTag compoundTag) {
        compoundTag.putString("skyType", this.skyType.name());
    }

    @Override
    public void load(CompoundTag compoundTag) {
        if (!compoundTag.contains("skyType")) {
            return;
        }
        this.skyType = SkyType.valueOf(compoundTag.getString("skyType"));
    }

    private final Boolean[] shouldRender = new Boolean[6];

    public boolean shouldRenderFace(Direction direction) {
        int index = direction.ordinal();

        if (shouldRender[index] == null) {
            shouldRender[index] = level == null || Block.shouldRenderFace(getBlockState(), level, getBlockPos(), direction, getBlockPos().relative(direction));
        }

        return shouldRender[index];
    }

    public void neighborChanged() {
        Arrays.fill(shouldRender, null);
    }
}
