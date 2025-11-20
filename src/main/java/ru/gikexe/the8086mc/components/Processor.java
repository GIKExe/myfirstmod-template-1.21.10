package ru.gikexe.the8086mc.components;

public class Processor {
	private Register ax, bx, cx, dx;
	private RegisterL al, bl, cl, dl;
	private RegisterH ah, bh, ch, dh;
	private Register sp, bp;
	private Register si, di;
	private Register es, cs, ss, ds;
	private Register ip;
	private Motherboard motherboard;

	public Processor() {
		init();
	}

	public Processor(Motherboard motherboard) {
		setMotherboard(motherboard);
	}

	// является переустановкой процессора, состояние должно сброситься.
	public Processor setMotherboard(Motherboard motherboard) {
		this.motherboard = motherboard;
		init();
		return this;
	}

	private void init() {
		ax = new Register();
		al = new RegisterL(ax);
		ah = new RegisterH(ax);

		bx = new Register();
		bl = new RegisterL(bx);
		bh = new RegisterH(bx);

		cx = new Register();
		cl = new RegisterL(cx);
		ch = new RegisterH(cx);

		dx = new Register();
		dl = new RegisterL(dx);
		dh = new RegisterH(dx);

		sp = new Register();
		bp = new Register();
		ip = new Register();

		si = new Register();
		di = new Register();

		es = new Register();
		cs = new Register();
		ss = new Register();
		ds = new Register();
	}

	public void tick() {
		byte op = readNextOpcode();
		ip.inc();
		switch (op) {

		}
	}

	private Register selectRegister(char number) {
		switch (number) {
			case 0: return al;
			case 1: return cl;
			case 2: return dl;
			case 3: return bl;
			case 4: return ah;
			case 5: return ch;
			case 6: return dh;
			case 7: return bh;
			default: return null;
		}
	}

	private byte readNextOpcode() {
		return motherboard.getRamValue(getRealAddr(cs.getValue(), ip.getValue()));
	}

	private int getRealAddr(short base, short shift) {
		return (((int)base << 4) + shift) & 0xFFFFF;
	}
}
