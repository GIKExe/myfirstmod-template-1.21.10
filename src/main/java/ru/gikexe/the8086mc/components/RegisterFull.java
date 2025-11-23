package ru.gikexe.the8086mc.components;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class RegisterFull implements Register {
	protected short value = 0;

	public RegisterFull(short value) {
		this.value = value;
	}
}
