package ru.gikexe.the8086mc.components;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SampleMotherboard implements Motherboard {
	private Processor processor;
	private Ram ram;

	public SampleMotherboard() {
		this(new Processor(), new Ram1M());
	}
	public SampleMotherboard(Processor processor) {
		this(processor, new Ram1M());
	}
	public SampleMotherboard(Processor processor, Ram ram) {
		this.processor = processor.setMotherboard(this);
		this.ram = ram;
	}

	public int getMaxRam() {
		return ram.getSize();
	}
	public byte getRamValue(int addr) {
		return ram.getValue(addr);
	}
	public void setRamValue(int addr, byte value) {
		ram.setValue(addr, value);
	}
}
