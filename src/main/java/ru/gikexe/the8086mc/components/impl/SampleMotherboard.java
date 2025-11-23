package ru.gikexe.the8086mc.components.impl;

import lombok.Getter;
import lombok.Setter;
import ru.gikexe.the8086mc.components.Memory;
import ru.gikexe.the8086mc.components.Motherboard;
import ru.gikexe.the8086mc.components.Processor;

@Getter
@Setter
public class SampleMotherboard implements Motherboard {
	private Processor processor;
	private Memory memory;

	public SampleMotherboard() {
		this(new Processor());
	}
	public SampleMotherboard(Processor processor) {
		this(processor, Memory.getMegabytes(1));
	}
	public SampleMotherboard(Processor processor, Memory memory) {
		this.processor = processor.setMotherboard(this);
		this.memory = memory;
	}

	public int getMaxRam() {
		return memory.getSize();
	}
	public byte getRamValue(int addr) {
		return memory.getByte(addr);
	}
	public void setRamValue(int addr, byte value) {
		memory.setByte(addr, value);
	}
}
