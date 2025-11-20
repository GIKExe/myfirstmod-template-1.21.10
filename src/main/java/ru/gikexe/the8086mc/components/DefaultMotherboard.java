package ru.gikexe.the8086mc.components;

public class DefaultMotherboard extends Motherboard {
	private Processor processor;
	private Ram ram;

	public DefaultMotherboard() {
		this(new Processor(), new Ram1M());
	}

	public DefaultMotherboard(Processor processor) {
		this(processor, new Ram1M());
	}

	public DefaultMotherboard(Processor processor, Ram ram) {
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
