package ru.geekbrains.finmanager.models;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.Set;

public class Account implements JdbcCrud<Account> {
	private int accountId = 0;
	private String description = "";
	private BigDecimal balance = BigDecimal.ZERO;

	public Account(String description) {
		if (description != null) {
			this.description = description;
		}
	}

	Account(int id, BigDecimal balance, String description) {
		if (description != null) {
			this.description = description;
		}
		this.accountId = id;
		this.balance = balance;
	}

	boolean conduct(Record transaction) {
		BigDecimal amount = 
				transaction.getAmount().multiply(BigDecimal.valueOf(transaction.sign()));
		balance = balance.add(amount);
		return true;
	}

	Record escape(Record record) {
		BigDecimal amount =
				record.getAmount().multiply(BigDecimal.valueOf(record.sign()));
		balance = balance.subtract(amount);
		return record;
	}

	public int getId() {
		return accountId;
	}
	
	void setId(int id) {
		this.accountId = id;		
	}

	public String getDescription() {
		return description;
	}

	public BigDecimal getBalance() {
		return balance;
	}

	@Override
	public int hashCode() {
		return accountId;
	}

	@Override
	public String toString() {
		return description + "\t: " + balance.setScale(2, RoundingMode.HALF_UP);
	}

	@Override
	public boolean create(Connection conn, int userId) {
		boolean result = false;
		String sqlQuery = "INSERT INTO accounts(user_id, balance, description)"
				+ " VALUES (?, ?, ?);";
		try {
			conn.setAutoCommit(false);
			PreparedStatement stm = conn.prepareStatement(sqlQuery);
			stm.setInt(1, userId);
			stm.setBigDecimal(2, getBalance());
			stm.setString(3, getDescription());
			result = (stm.executeUpdate() == 1);
			conn.commit();
		} catch (SQLException e) {
			e.printStackTrace();
			result = false;
		}
		return result;
	}

	@Override
	public Set<Account> read(Connection conn, int userId) {
		Set<Account> result = new HashSet<>();
		String sqlQuery = "SELECT accounts.id, accounts.balance, accounts.description"
				+ " FROM accounts WHERE user_id = ?;";
		try {
			conn.setAutoCommit(false);
			PreparedStatement stm = conn.prepareStatement(sqlQuery);
			stm.setInt(1, userId);
			ResultSet res = stm.executeQuery();
			while (res.next()) {
				result.add(new Account(res.getInt(1), res.getBigDecimal(2),
						res.getString(3)));
			}
			conn.commit();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}

	@Override
	public boolean update(Connection conn) {
		boolean result = true;
		String sqlQuery = "UPDATE ONLY accounts" + " SET balance = ?, description = ?"
				+ " WHERE id = ?;";
		try {
			conn.setAutoCommit(false);
			PreparedStatement stm = conn.prepareStatement(sqlQuery);
			stm.setBigDecimal(1, balance);
			stm.setString(2, description);
			stm.setInt(3, getId());
			result = (stm.executeUpdate() == 1);
			conn.commit();
		} catch (SQLException e) {
			e.printStackTrace();
			result = false;
		}
		return result;
	}

	@Override
	public boolean delete(Connection conn) {
		boolean result = true;
		String sqlQuery = "DELETE ONLY accounts WHERE account.id = ?;";
		try {
			conn.setAutoCommit(false);
			PreparedStatement stm = conn.prepareStatement(sqlQuery);
			stm.setInt(1, getId());
			result = (stm.executeUpdate() == 1);
			conn.commit();
		} catch (SQLException e) {
			e.printStackTrace();
			result = false;
		}
		return result;
	}
}
