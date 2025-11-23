package ru.gikexe.the8086mc.item;

import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;

public class MetalShearsItem extends Item{
	public MetalShearsItem(Properties properties) {
		super(properties);
	}

	@Override
	public ItemStack getRecipeRemainder(ItemStack stack) {
		ItemStack damagedStack = stack.copy();
		damagedStack.setDamageValue(damagedStack.getDamageValue() + 1);
		return (damagedStack.getDamageValue() >= damagedStack.getMaxDamage() ? ItemStack.EMPTY : damagedStack);
	}
}
