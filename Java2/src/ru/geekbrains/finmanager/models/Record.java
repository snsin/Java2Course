package ru.geekbrains.finmanager.models;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Set;;

public final class Record implements JdbcCrud<Record>{
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

	@Override
	public boolean create(Connection conn, int id) {
		boolean result = true;
		String sqlQuery = "INSERT INTO records(account_id, transfer, date, "
				+ "amount, description)" + " VALUES (?, ?, ?, ?, ?);";
		try {
			conn.setAutoCommit(false);

			PreparedStatement stm = conn.prepareStatement(sqlQuery);
			stm.setInt(1, id);
			stm.setInt(2, this.sign());
			stm.setTimestamp(3, this.getDate());
			stm.setBigDecimal(4, this.getAmount());
			stm.setString(5, this.getDescription());
			stm.executeUpdate();
			conn.commit();
		} catch (SQLException e) {
			e.printStackTrace();
			result = false;
		}
		return result;
	}

	@Override
	public Set<Record> read(Connection conn, int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean update(Connection conn) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean delete(Connection conn) {
		// TODO Auto-generated method stub
		return false;
	}
}
