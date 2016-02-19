package ru.geekbrains.finmanager;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.LinkedList;
import java.util.List;

public class Account {
	private final int accountId;
	private String description = "";
	private BigDecimal balance = BigDecimal.ZERO;
	private List<Record> records = new LinkedList<>();
	
	public Account(String description) {
		if (description != null) {
			this.description = description;
		}
		this.accountId = 0;
	}
	
    Account(int id, BigDecimal balance, String description) {
        if (description != null) {
            this.description = description;
        }
        this.accountId = id;
        this.balance = balance;
    }
	
	public boolean conduct(Record transaction) {
		boolean result = (! records.contains(transaction));
		if (result) {
		    BigDecimal amount = transaction.getAmount()
		                .multiply(BigDecimal.valueOf(transaction.sign()));
			balance = balance.add(amount);
			records.add(transaction);
		}	
		return result;
	}
	
	public int getId() {
		return accountId;
	}
	
	public String getDescription() {
		return description;
	}
	
	public BigDecimal getBalance() {
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
		    BigDecimal amount = record.getAmount()
		                .multiply(BigDecimal.valueOf(record.sign()));
			balance = balance.subtract(amount);
			result = record;
		}	
		return result;
	}

	@Override
	public String toString() {
		return description + "\t: " 
				+ balance.setScale(2, RoundingMode.HALF_UP);
	}
}
