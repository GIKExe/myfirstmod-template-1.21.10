package ru.gikexe.the8086mc.components;

public class RegisterH extends Register {
	Register reg;

	public RegisterH(Register reg) {
		this.reg = reg;
	}

	public short getValue() {
		return (short) (reg.getValue() >>> 8);
	}

	public void setValue(short value) {
		reg.setValue((short) (reg.getValue() & 0xFF + value));
	}

	public void inc() {
		reg.inc();
	}

	public void dec() {
		reg.dec();
	}
}
