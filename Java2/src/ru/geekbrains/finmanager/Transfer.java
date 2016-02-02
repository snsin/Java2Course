package ru.geekbrains.finmanager;

public enum Transfer {DEBIT(1), CDREDIT(-1);
	private int sign;
	
	Transfer(int sign) {
		this.sign = sign;
	}
	
	public int sign() {
		return sign;
	}
}