package ru.geekbrains.finmanager;

import java.math.BigDecimal;
import java.sql.Date;

public final class Record {
	private final int recordId;
	private final Transfer transfer;
	private final Date date;
	private final BigDecimal amount;
	private final String description;
	private final Category category;

	public Record(Transfer transfer, BigDecimal amount, String description,
			Category category) {
		this.transfer = transfer;
		this.amount = amount;
		this.description = description;
		this.date = new Date(System.currentTimeMillis());
		this.recordId = super.hashCode();
		this.category = category;
	}

	Record(int id, Transfer transfer, Date date, BigDecimal amount, String description,
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

	public Category getCategory() {
		return category;
	}
}
