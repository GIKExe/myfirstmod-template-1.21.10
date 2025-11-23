package ru.gikexe.the8086mc.block;

import java.util.function.Function;

import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour.Properties;
import ru.gikexe.the8086mc.The8086mc;

public class ModBlocks {
	public static final Block METALLURGICAL_SILICON_BLOCK = registerBlock("metallurgical_silicon_block",
		newProperties().strength(2.0F, 6.0F).requiresCorrectToolForDrops());
	public static final Block COMPUTER_TOWER = registerBlock("computer_tower",
		newProperties().strength(5.0F, 6.0F).sound(SoundType.IRON).requiresCorrectToolForDrops(), ComputerTowerBlock::new);

	private static Properties newProperties() {
		return Properties.of();
	}

	private static Block registerBlock(String name, Properties properties) {
		return registerBlock(name, properties, Block::new);
	}

	private static Block registerBlock(String name, Properties properties, Function<Properties, Block> function) {
		ResourceKey<Block> key = The8086mc.getBlockKey(name);
		Block block = (Block) function.apply(properties.setId(key));
		return Registry.register(BuiltInRegistries.BLOCK, key, block);
	}

	public static void registerModBlocks() {
		The8086mc.LOGGER.info("Registering Mod Block for " + The8086mc.MOD_ID);
	}
}
