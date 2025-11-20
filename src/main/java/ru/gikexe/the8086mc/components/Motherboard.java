package ru.gikexe.the8086mc.components;

public interface Motherboard {
	int getMaxRam();
	byte getRamValue(int addr);
	void setRamValue(int addr, byte value);
}
