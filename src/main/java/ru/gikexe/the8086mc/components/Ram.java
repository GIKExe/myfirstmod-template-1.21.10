package ru.gikexe.the8086mc.components;

public abstract class Ram {
	protected byte[] mem;
	protected int size;
	protected int mask;

	public int getSize() {
		return size;
	}

	public byte getValue(int addr) {
		return mem[addr & mask];
	}

	public void setValue(int addr, byte value) {
		mem[addr & mask] = value;
	}
}
