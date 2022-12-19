package net.cerulan.blockofsky;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.BaseEntityBlock;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.RenderShape;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.material.Material;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import javax.annotation.ParametersAreNonnullByDefault;

@ParametersAreNonnullByDefault
public class SkyBlock extends BaseEntityBlock {
    public static final BooleanProperty ACTIVE = BooleanProperty.create("active");
    protected SkyBlock() {
        super(Properties.of(Material.STONE));
        registerDefaultState(defaultBlockState().setValue(ACTIVE, true));
    }

    @Nullable
    @Override
    public BlockEntity newBlockEntity(@NotNull BlockPos blockPos, @NotNull BlockState blockState) {
        return new SkyBlockEntity(SkyBlockEntity.SkyType.Overworld, blockPos, blockState);
    }

    @Nullable
    @Override
    public BlockState getStateForPlacement(BlockPlaceContext ctx) {
        final boolean powered = ctx.getLevel().hasNeighborSignal(ctx.getClickedPos());
        return this.defaultBlockState().setValue(ACTIVE, !powered);
    }

    @NotNull
    @Override
    @SuppressWarnings("deprecation")
    public BlockState updateShape(BlockState state, Direction direction, BlockState neighborState, LevelAccessor level, BlockPos pos, BlockPos neighborPos) {
        final BlockEntity blockEntity = level.getBlockEntity(pos);

        if (blockEntity instanceof SkyBlockEntity skyBlockEntity) {
            skyBlockEntity.neighborChanged();
        }

        return super.updateShape(state, direction, neighborState, level, pos, neighborPos);
    }

    @Override
    @SuppressWarnings("deprecation")
    public void neighborChanged(BlockState state, Level level, BlockPos pos, Block block, BlockPos fromPos, boolean notify) {
        super.neighborChanged(state, level, pos, block, fromPos, notify);

        final BlockEntity blockEntity = level.getBlockEntity(pos);

        if (blockEntity instanceof SkyBlockEntity skyBlockEntity) {
            skyBlockEntity.neighborChanged();
        }

        if (!level.isClientSide) {
            var hasSignal = level.hasNeighborSignal(pos);
            if (state.getValue(ACTIVE) == hasSignal) {
                level.setBlock(pos, state.setValue(ACTIVE, !hasSignal), 2);
            }
        }
    }

    @Override
    public boolean propagatesSkylightDown(BlockState state, BlockGetter world, BlockPos pos) {
        return true;
    }

    @Override
    @SuppressWarnings("deprecation")
    public int getLightBlock(BlockState state, BlockGetter world, BlockPos pos) {
        return 0;
    }

    @NotNull
    @Override
    public RenderShape getRenderShape(BlockState state) {
        return state.getValue(ACTIVE) ? RenderShape.INVISIBLE : RenderShape.MODEL;
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(ACTIVE);
    }

    public static class VoidBlock extends SkyBlock {

        @Override
        public @Nullable BlockEntity newBlockEntity(@NotNull BlockPos blockPos, @NotNull BlockState blockState) {
            return new SkyBlockEntity(SkyBlockEntity.SkyType.Void, blockPos, blockState);
        }

        @Override
        public int getLightBlock(BlockState state, BlockGetter world, BlockPos pos) {
            if (state.isSolidRender(world, pos)) {
                return world.getMaxLightLevel();
            } else {
                return state.propagatesSkylightDown(world, pos) ? 0 : 1;
            }
        }
    }

}
