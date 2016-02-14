package ru.geekbrains.finmanager;

import java.util.Date;



public final class Record {
	private final int recordId;
	private final Transfer transfer;
	private final Date date;
	private final double amount;
	private final String description;
	private final Category category;
	
	public Record(Transfer transfer, double amount, String description, Category category) {
		this.transfer = transfer;
		this.amount = amount;
		this.description = description;
		this.date = new Date(System.currentTimeMillis());	
		this.recordId = super.hashCode();
		this.category = category;
	}
	
	public int sign() {
		return transfer.sign();
	}
	
	public double getAmount() {
		return amount;
	}
	
	public Date getDate() {
		return new Date(date.getTime());
	}
	
	public int getId() {
		return recordId;
	}
	
	public String getDescription() {
		return description;
	}
	
	@Override
	public int hashCode() {
		return recordId;
	}
}
