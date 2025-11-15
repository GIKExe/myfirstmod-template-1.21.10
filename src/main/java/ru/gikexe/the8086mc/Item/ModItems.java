package ru.gikexe.the8086mc.Item;

import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Rarity;
import ru.gikexe.the8086mc.The8086mc;

public class ModItems {
	public static final Item PURE_CRYSTAL = registerItem("pure_crystal", new Item.Properties().rarity(Rarity.RARE));

	private static Item registerItem(String name, Item.Properties properties) {
		ResourceLocation id = ResourceLocation.fromNamespaceAndPath(The8086mc.MOD_ID, name);
		ResourceKey<Item> key = ResourceKey.create(Registries.ITEM, id);
		Item item = new Item(properties.setId(key));
		return Registry.register(BuiltInRegistries.ITEM, key, item);
	}

	public static void registerModItems() {
		The8086mc.LOGGER.info("Registering Mod Items for " + The8086mc.MOD_ID);
	}
}
