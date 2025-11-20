package ru.gikexe.the8086mc.components;

public abstract class Motherboard {
	public abstract int getMaxRam();
	public abstract byte getRamValue(int addr);
	public abstract void setRamValue(int addr, byte value);
}
