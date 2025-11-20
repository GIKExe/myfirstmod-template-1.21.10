package ru.gikexe.the8086mc.components;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RegisterHigh implements Register {
	protected Register parent;

	public RegisterHigh(Register reg) {
		this.parent = reg;
	}

	public short getValue() {
		return (short) (parent.getValue() >>> 8);
	}
	public void setValue(short value) {
		parent.setValue((short) (parent.getValue() & 0xFF + value));
	}
}
