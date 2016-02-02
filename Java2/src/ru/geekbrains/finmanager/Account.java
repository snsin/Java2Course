package ru.geekbrains.finmanager;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.LinkedList;
import java.util.List;

public class Account {
	private final int accountId;
	private String description = "";
	private double balance;
	private List<Record> records = new LinkedList<>();
	
	public Account(String description) {
		this.balance = 0.0;
		if (description != null) {
			this.description = description;
		}
		this.accountId = super.hashCode();
	}
	
	public boolean conduct(Record transaction) {
		double amount = transaction.getAmount();
		boolean result = Double.isFinite(amount) & (! records.contains(transaction));
		if (result) {
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
	
	public List<Record> getRecords() {
		return records;
	}
	
	@Override
	public int hashCode() {
		return accountId;
	}

	public Record escape(Record record) {
		Record result = null;
		if (records.remove(record)) {
			balance -= record.sign() * record.getAmount();
			result = record;
		}	
		return result;
	}

	@Override
	public String toString() {
		return description + "\t: " 
				+ new BigDecimal(balance).setScale(2, RoundingMode.HALF_UP).floatValue();
	}
}