package ru.gikexe.the8086mc.components;

public interface Register {
	short getValue();
	void setValue(short value);

	default void inc() {
		setValue((short) (getValue() + 1));
	}
	default void dec() {
		setValue((short) (getValue() - 1));
	}
}
