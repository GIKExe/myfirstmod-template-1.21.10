package ru.gikexe.the8086mc.components;

public class RegisterL extends Register {
	Register reg;

	public RegisterL(Register reg) {
		this.reg = reg;
	}

	public short getValue() {
		return (short) (reg.getValue() & 0xFF);
	}

	public void setValue(short value) {
		reg.setValue((short) (reg.getValue() & 0xFF00 + value));
	}

	public void inc() {
		reg.inc();
	}

	public void dec() {
		reg.dec();
	}
}
