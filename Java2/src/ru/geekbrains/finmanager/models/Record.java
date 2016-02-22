package ru.geekbrains.finmanager.models;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Set;;

public final class Record {
	private int recordId;
	private final Transfer transfer;
	private final Timestamp date;
	private final BigDecimal amount;
	private final String description;
	private final Category category;

	public Record(Transfer transfer, BigDecimal amount, String description,
			Category category) {
		this.transfer = transfer;
		this.amount = amount;
		this.description = description;
		this.date = new Timestamp(System.currentTimeMillis());
		this.recordId = super.hashCode();
		this.category = category;
	}

	Record(int id, Transfer transfer, Timestamp date, BigDecimal amount, String description,
			Category category) {
		this.transfer = transfer;
		this.amount = amount;
		this.description = description;
		this.date = date;
		this.recordId = super.hashCode();
		this.category = category;
	}

	public int sign() {
		return transfer.sign();
	}

	public BigDecimal getAmount() {
		return amount;
	}

	public Timestamp getDate() {
		return new Timestamp(date.getTime());
	}

	public int getId() {
		return recordId;
	}

	public String getDescription() {
		return description;
	}
	
	void setId(int id) {
		this.recordId = id;
	}

	@Override
	public int hashCode() {
		return recordId;
	}
	

	public Category getCategory() {
		return category;
	}

}
