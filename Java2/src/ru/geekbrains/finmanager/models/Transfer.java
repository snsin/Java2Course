package ru.geekbrains.finmanager.models;

public enum Transfer {
	DEBIT(1), CREDIT(-1);
	private int sign;

	Transfer(int sign) {
		this.sign = sign;
	}

	public int sign() {
		return sign;
	}

	public static Transfer getTransfer(int value) {
		if (value >= 0) {
			return DEBIT;
		} else {
			return CREDIT;
		}

	}
}