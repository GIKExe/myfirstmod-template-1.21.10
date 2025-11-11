package ru.gikexe.myfirstmod;

import java.util.Random;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.event.player.PlayerBlockBreakEvents;

import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Explosion;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import org.jetbrains.annotations.Nullable;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MyFirstMod implements ModInitializer {
	public static final String MOD_ID = "myfirstmod";

	// This logger is used to write text to the console and the log file.
	// It is considered best practice to use your mod id as the logger's name.
	// That way, it's clear which mod wrote info, warnings, and errors.
	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);
	private static final Random random = new Random();

	@Override
	public void onInitialize() {
		// This code runs as soon as Minecraft is in a mod-load-ready state.
		// However, some things (like resources) may still be uninitialized.
		// Proceed with mild caution.

		LOGGER.info("initializing...");
		PlayerBlockBreakEvents.BEFORE.register((Level world, Player player, BlockPos pos, BlockState state, @Nullable BlockEntity blockEntity) -> {
			if (random.nextDouble() >= 0.97857206208770D) {
				world.explode(
					null,
					Explosion.getDefaultDamageSource(world, null),
					null,
					pos.getX(),
					pos.getY(),
					pos.getZ(),
					4.0F,
					false,
					Level.ExplosionInteraction.TNT
				); return false;
			}; return true;
		});
		// // Запускаем native-программу при инициализации мода
		// try {
		// 	NativeProcessLauncher.launchNativeProgram();
		// } catch (Exception e) {
		// 	LOGGER.error("Failed to launch native program", e);
		// }
	}
}