package ru.gikexe.the8086mc.components;

import lombok.Getter;

public class Memory {
	@Getter
	protected int size;
	protected int mask;
	protected byte[] mem;

	public Memory(int size, int mask, byte[] mem) {
		this.size = size;
		this.mask = mask;
		this.mem = mem;
	}
	
	public Memory(int size) {
		this.size = size;
		this.mask = size - 1;
		this.mem = new byte[size];
	}

	public byte getByte(int addr) {
		return mem[addr & mask];
	}

	public void setByte(int addr, byte value) {
		mem[addr & mask] = value;
	}

	public static int MEGABYTE = 1048576;

	public static Memory getMegabytes(int num) {
		return new Memory(MEGABYTE * num);
	}
}
