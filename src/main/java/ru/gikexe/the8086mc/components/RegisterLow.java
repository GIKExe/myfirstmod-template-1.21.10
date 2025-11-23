package ru.gikexe.the8086mc.components;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RegisterLow implements Register {
	protected Register parent;

	public RegisterLow(Register reg) {
		this.parent = reg;
	}

	public short getValue() {
		return (short) (parent.getValue() & 0xFF);
	}

	public void setValue(short value) {
		parent.setValue((short) (parent.getValue() & 0xFF00 + value));
	}
}
