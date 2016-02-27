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

	private static void createTables() throws SQLException {
		
		PreparedStatement stm = conn.prepareStatement("CREATE TABLE IF NOT EXISTS public.users ("
				+ " id SERIAL PRIMARY KEY,"
				+ "	login TEXT NOT NULL UNIQUE,"
				+ "	pass TEXT NOT NULL);");	
		stm.executeUpdate();
		
		stm = conn.prepareStatement("CREATE TABLE IF NOT EXISTS public.accounts ("
				+ "	id SERIAL PRIMARY KEY,"
				+ "	user_id integer REFERENCES public.users,"
				+ "	balance numeric,"
				+ "	description text);");
		stm.executeUpdate();
		stm = conn.prepareStatement("CREATE TABLE IF NOT EXISTS public.records ("
				+ "	id SERIAL PRIMARY KEY,"
				+ "	account_id integer REFERENCES public.accounts,"
				+ "	transfer integer CHECK(transfer = -1 OR transfer = 1),"
				+ "	date timestamp,"
				+ "	amount numeric,"
				+ "	description text,"
				+ " category_name text,"
				+ " category_description text);");
		stm.executeUpdate();	
		
	}

	public static void closeConnection() {
		if (conn != null) {
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
				System.exit(SysErrs.DB_CONNECTION_ERROR.ordinal());
			}
		}
		
	}

}
