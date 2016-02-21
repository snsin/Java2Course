package ru.geekbrains.finmanager;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import ru.geekbrains.finmanager.models.SysErrs;

public class DbHelper {
	private static Connection conn = null;
	private static boolean tablesCreated = false;
	private static String user = "test_user";
	private static String password = "12345";
	private static String url = "jdbc:postgresql://localhost:5432/test_db";
	private static String driver = "org.postgresql.Driver";

	
	private DbHelper() throws SQLException {
		try {
			Class.forName(driver);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			System.exit(SysErrs.DB_DRIVER_ERROR.ordinal());
		}
		conn = DriverManager.getConnection(url, user, password);	
	}
	
	public static Connection getConnection() throws SQLException {
		if (conn == null) {
			new DbHelper();
		}
		if (!tablesCreated) {
			createTables();
		}
		tablesCreated = true;
		return conn;
		
	}

	private static void createTables() {
		
		PreparedStatement stm = conn.prepareStatement(sql);		
	}

}
