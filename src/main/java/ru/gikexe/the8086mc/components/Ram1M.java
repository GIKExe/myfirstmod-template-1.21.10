package ru.gikexe.the8086mc.components;

public class Ram1M extends Ram {
	public Ram1M() {
		size = 1048576;
		mask = size-1;
		mem = new byte[size];
	}
}
