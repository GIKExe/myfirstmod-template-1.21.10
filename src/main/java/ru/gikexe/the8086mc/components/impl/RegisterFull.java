package ru.gikexe.the8086mc.components.impl;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ru.gikexe.the8086mc.components.Register;

@Getter
@Setter
@NoArgsConstructor
public class RegisterFull implements Register {
	protected short value = 0;

	public RegisterFull(short value) {
		this.value = value;
	}
}
