package ru.gikexe.the8086mc;

import java.util.Optional;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Explosion;
import net.minecraft.world.level.ExplosionDamageCalculator;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.FluidState;

public class IgnoreWaterExplosionDamageCalculator extends ExplosionDamageCalculator {
	@Override
	public Optional<Float> getBlockExplosionResistance(
		Explosion explosion,
		BlockGetter blockGetter,
		BlockPos blockPos,
		BlockState blockState,
		FluidState fluidState
	) {
		return (blockState.isAir() ? Optional.empty() : Optional.of(Math.max(blockState.getBlock().getExplosionResistance(), 0.0F)));
	}
}