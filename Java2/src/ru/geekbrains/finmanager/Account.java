package ru.geekbrains.finmanager;

import java.util.HashSet;
import java.util.Set;

public class Account {
	private final int accountId;
	private String description;
	private double balance;
	private Set<Record> records = new HashSet<>();
	
	Account(double balance, String description) {
		this.balance = 0.0;
		this.description = description;
		this.accountId = super.hashCode();
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
	
	public long getId() {
		return accountId;
	}
	
	public String getDescription() {
		return description;
	}
	
	public double getBalance() {
		return balance;
	}
	
	public Set<Record> getRecords() {
		return records;
	}
	
	@Override
	public int hashCode() {
		return accountId;
	}

}
