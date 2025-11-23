package ru.gikexe.the8086mc.block;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.Explosion;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.HorizontalDirectionalBlock;
import net.minecraft.world.level.block.Mirror;
import net.minecraft.world.level.block.Rotation;
import net.minecraft.world.level.block.SimpleWaterloggedBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.block.state.properties.EnumProperty;
import net.minecraft.world.level.material.FluidState;
import net.minecraft.world.level.material.Fluids;
import net.minecraft.world.phys.Vec3;
import ru.gikexe.the8086mc.misc.IgnoreWaterExplosionDamageCalculator;

public class ComputerTowerBlock extends Block implements SimpleWaterloggedBlock {
	public static final EnumProperty<Direction> FACING = HorizontalDirectionalBlock.FACING;
	public static final BooleanProperty WATERLOGGED = BlockStateProperties.WATERLOGGED;

	public ComputerTowerBlock(Properties properties) {
		super(properties);
		this.registerDefaultState(
			this.stateDefinition.any().setValue(FACING, Direction.NORTH).setValue(WATERLOGGED, false)
		);
	}

	@Override
	public BlockState getStateForPlacement(BlockPlaceContext blockPlaceContext) {
		BlockPos blockPos = blockPlaceContext.getClickedPos();
		FluidState fluidState = blockPlaceContext.getLevel().getFluidState(blockPos);
		return this.defaultBlockState()
			.setValue(WATERLOGGED, fluidState.getType() == Fluids.WATER)
			.setValue(FACING, blockPlaceContext.getHorizontalDirection().getOpposite());
	}

	@Override
	public boolean placeLiquid(LevelAccessor levelAccessor, BlockPos blockPos, BlockState blockState, FluidState fluidState) {
		if (fluidState.getType() == Fluids.WATER) explodeThis((Level)levelAccessor, blockPos.getCenter());
		return false;
	}

	@Override
	protected void onPlace(BlockState blockState, Level level, BlockPos blockPos, BlockState blockState2, boolean bl) {
		super.onPlace(blockState, level, blockPos, blockState2, bl);
		if (level.getFluidState(blockPos).getType() == Fluids.WATER) explodeThis(level, blockPos.getCenter());
	}

	private void explodeThis(Level level, Vec3 blockPos) {
		level.explode(
			null,
			Explosion.getDefaultDamageSource(level, null),
			new IgnoreWaterExplosionDamageCalculator(),
			blockPos.x(),
			blockPos.y(),
			blockPos.z(),
			6.0F,
			false,
			Level.ExplosionInteraction.TNT
		);
	}

	@Override
	protected BlockState rotate(BlockState blockState, Rotation rotation) {
		return blockState.setValue(FACING, rotation.rotate(blockState.getValue(FACING)));
	}

	@Override
	protected BlockState mirror(BlockState blockState, Mirror mirror) {
		return blockState.rotate(mirror.getRotation(blockState.getValue(FACING)));
	}

	@Override
	protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
		builder.add(FACING, WATERLOGGED);
	}

	@Override
	protected FluidState getFluidState(BlockState blockState) {
		return blockState.getValue(WATERLOGGED) ? Fluids.WATER.getSource(false) : super.getFluidState(blockState);
	}
}
