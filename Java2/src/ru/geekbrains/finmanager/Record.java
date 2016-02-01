package ru.geekbrains.finmanager;

import java.util.Date;

enum Transfer {DEBIT(-1), CDREDIT(1);
	private int sign;
	
	Transfer(int sign) {
		this.sign = sign;
	}
	
	public int sign() {
		return sign;
	}
}

public class Record {
	private long recordId;
	private Transfer transfer;
	private Date date;
	private double amount;
	private String description;
	
	Record(Transfer transf, double amount, String description) {
		this.transfer = transf;
		this.amount = amount;
		this.description = description;
		this.date = new Date(System.currentTimeMillis());	
		this.recordId = this.hashCode();
	}
	
	public int sign() {
		return transfer.sign();
	}
	
	public double getAmount() {
		return amount;
	}
	
}
