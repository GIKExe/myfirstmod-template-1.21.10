package ru.gikexe.the8086mc.components;

public class Register {
	protected short value;

	public Register(short value) {
		setValue(value);
	}

	public Register() {
		setValue((byte)0);
	}

	public short getValue() {
		return value;
	}

	public void setValue(short value) {
		this.value = value;
	}

	public void inc() {
		value++;
	}

	public void dec() {
		value--;
	}
}
