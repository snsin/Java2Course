package ru.geekbrains.finmanager.models;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class DbStorage implements DataStore {

	private String user = "test_user";
	private String password = "12345";
	private String url = "jdbc:postgresql://localhost:5432/test_db";
	private String driver = "org.postgresql.Driver";

	private Connection getConnection() throws SQLException {
		return DriverManager.getConnection(url, user, password);
	}

	public DbStorage() {
		try {
			Class.forName(driver);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

	// TODO rewrite classes add id parametr to constructor args
	// TODO change table to provide unique user name and make drop_all.sql
	// which delete all tables
	@Override
	public User getUser(String name) {
		User result = null;
		String sqlQuery = "SELECT users.id, users.login, users.pass "
				+ "FROM users WHERE users.login = ?;";
		try (Connection conn = getConnection()) {
			conn.setAutoCommit(false);
			PreparedStatement stm = conn.prepareStatement(sqlQuery);
			stm.setString(1, name);
			ResultSet res = stm.executeQuery();
			if (res.next()) {
				result = new User(res.getInt(1), res.getString(2), res.getString(3));
			}
			conn.commit();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}

	@Override
	public Set<String> getUserNames() {
		Set<String> result = new HashSet<>();
		String sqlQuery = "SELECT users.login FROM users;";
		try (Connection conn = getConnection()) {
			conn.setAutoCommit(false);
			PreparedStatement stm = conn.prepareStatement(sqlQuery);
			ResultSet res = stm.executeQuery();
			while (res.next()) {
				result.add(res.getString(1));
			}
			conn.commit();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}

	@Override
	public Set<Account> getAccounts(User owner) {
		Set<Account> result = new HashSet<>();
		String sqlQuery = "SELECT accounts.id, accounts.balance, accounts.description"
				+ " FROM accounts WHERE user_id = ?;";
		try (Connection conn = getConnection()) {
			conn.setAutoCommit(false);
			PreparedStatement stm = conn.prepareStatement(sqlQuery);
			stm.setInt(1, owner.getUserId());
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
	public Set<Record> getRecords(Account account) {
		Set<Record> result = new HashSet<>();
		String sqlQuery = "SELECT records.id, records.transfer, records.date,"
				+ " records.amount, records.description"
				+ " FROM records WHERE account_id = ?;";
		try (Connection conn = getConnection()) {
			conn.setAutoCommit(false);
			PreparedStatement stm = conn.prepareStatement(sqlQuery);
			stm.setInt(1, account.getId());
			ResultSet res = stm.executeQuery();
			while (res.next()) {
				result.add(new Record(res.getInt(1), Transfer.getTransfer(res.getInt(2)),
						res.getTimestamp(3), res.getBigDecimal(4), res.getString(5),
						getCategory(res.getInt(1))));
			}
			conn.commit();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}

	private Category getCategory(int recordId) {
		Category result = new Category();
		String sqlQuery = "SELECT categories.name, categories.description "
				+ "FROM categories WHERE categories.record_id = ?;";
		try (Connection conn = getConnection()) {
			conn.setAutoCommit(false);
			PreparedStatement stm = conn.prepareStatement(sqlQuery);
			stm.setInt(1, recordId);
			ResultSet res = stm.executeQuery();
			if (res.next()) {
				result = new Category(res.getString(1), res.getString(2));
			}
			conn.commit();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}

	@Override
	public void addUser(User user) {
		/*
		 * if (!signedUsers.add(user.getName())) { return; }
		 */
		if (getUser(user.getName()) != null) {
			return;
		}
		String sqlQuery = "INSERT INTO users(login, pass)" + " VALUES (?, ?);";
		try (Connection conn = getConnection()) {
			conn.setAutoCommit(false);
			PreparedStatement stm = conn.prepareStatement(sqlQuery);
			stm.setString(1, user.getName());
			stm.setString(2, user.getName());
			stm.executeUpdate();
			conn.commit();
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	@Override
	public void addAccount(User user, Account account) {
		if (getAccounts(user).contains(account)) {
			return;
		}
		String sqlQuery = "INSERT INTO accounts(user_id, balance, description)"
				+ " VALUES (?, ?, ?);";
		try (Connection conn = getConnection()) {
			conn.setAutoCommit(false);
			PreparedStatement stm = conn.prepareStatement(sqlQuery);
			stm.setInt(1, user.getUserId());
			stm.setBigDecimal(2, account.getBalance());
			stm.setString(3, account.getDescription());
			stm.executeUpdate();
			conn.commit();
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	@Override
	public void addRecord(Account account, Record record) {
		if (getRecords(account).contains(record)) {
			return;
		}
		String sqlQuery = "INSERT INTO records(account_id, transfer, date, "
				+ "amount, description)" + " VALUES (?, ?, ?, ?, ?);";
		account.conduct(record);
		try (Connection conn = getConnection()) {
			conn.setAutoCommit(false);

			PreparedStatement stm = conn.prepareStatement(sqlQuery);
			stm.setInt(1, account.getId());
			stm.setInt(2, record.sign());
			stm.setTimestamp(3, record.getDate());
			stm.setBigDecimal(4, record.getAmount());
			stm.setString(5, record.getDescription());
			stm.executeUpdate();
			account.update(conn);
			conn.commit();
		} catch (SQLException e) {
			account.escape(record);
			e.printStackTrace();
		}
	}

	@Override
	public User removeUser(String name) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Account removeAccount(User owner, Account account) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Record removeRecord(Account from, Record record) {
		// TODO Auto-generated method stub
		return null;
	}

}