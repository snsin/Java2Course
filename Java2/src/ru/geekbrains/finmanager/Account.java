package ru.geekbrains.finmanager;

import java.util.ArrayList;
import java.util.List;

public class Account {
	private long accountId;
	private String description;
	private double balance;
	private List<Record> records = new ArrayList<>();
	
	Account(double balance, String description) {
		this.balance = 0.0;
		this.description = description;
		this.accountId = this.hashCode();
	}
	
	public boolean doTransaction(Record transaction) {
		double amount = transaction.getAmount();
		boolean result = false;
		if (Double.isFinite(amount)) {
			balance += transaction.sign() * amount;
			records.add(transaction);
			result = true;
		}	
		return result;
	}
	
	public long getAccountId() {
		return accountId;
	}
	
	public String getDescription() {
		return description;
	}
	
	public double getBalance() {
		return balance;
	}
	
	public List<Record> getRecords() {
		return records;
	}
	

}
