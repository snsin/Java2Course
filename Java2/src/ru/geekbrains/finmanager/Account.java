package ru.geekbrains.finmanager;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

public class Account implements JdbcCrud<Account> {
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
                result.add(new Account(res.getInt(1), res.getBigDecimal(2), res.getString(3)));
            }
            conn.commit();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    @Override
    public boolean update(Connection conn) {
        boolean result = false;
        String sqlQuery = "UPDATE ONLY accounts"
                + " SET balance = ?, description = ?"
                + " WHERE id = ?;";
        try {
            conn.setAutoCommit(false);
            PreparedStatement stm = conn.prepareStatement(sqlQuery);
            stm.setBigDecimal(1, balance);
            stm.setString(2, description);
            stm.setInt(3, getId());
            result  = (stm.executeUpdate() == 1);
            conn.commit();
        } catch (SQLException e) {
            e.printStackTrace();
            result = false;
        }
        return result;
    }

    @Override
    public boolean delete(Connection conn) {
        boolean result = false;
        String sqlQuery = "DELETE ONLY accounts WHERE account.id = ?;";
        try {
            conn.setAutoCommit(false);
            PreparedStatement stm = conn.prepareStatement(sqlQuery);
            stm.setInt(1, getId());
            result  = (stm.executeUpdate() == 1);
            conn.commit();
        } catch (SQLException e) {
            e.printStackTrace();
            result = false;
        }
        return result;
    }
}
