package ru.geekbrains.main;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

import ru.geekbrains.finmanager.models.Account;
import ru.geekbrains.finmanager.models.Category;
import ru.geekbrains.finmanager.models.DataStore;
import ru.geekbrains.finmanager.models.DbStorage;
import ru.geekbrains.finmanager.models.Record;
import ru.geekbrains.finmanager.models.RecordDateComparator;
import ru.geekbrains.finmanager.models.Storage;
import ru.geekbrains.finmanager.models.Transfer;
import ru.geekbrains.finmanager.models.User;
import ru.geekbrains.hw1.*;

public class Main {
	public static final Random rnd = new Random();

	private static String user = "test_user";
	private static String password = "12345";
	private static String url = "jdbc:postgresql://localhost:5432/test_db";
	private static String driver = "org.postgresql.Driver";

	public static Connection getConnection() throws SQLException {
		return DriverManager.getConnection(url, user, password);
	}

	public static void main(String[] args) {

		// tryStack();
		// tryLengthComp();
		// tryFinManager();
		/*
		 * Category.addCategory("Health"); Category cat = new Category("heaLth",
		 * "Pills"); System.out.println(cat.getName() + " " +
		 * cat.getDescription());
		 */
		try {
			System.out.println("Trying JDBC");
			Class.forName("org.postgresql.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		System.out.println("Sucsess");

		//tryDbStorage();
		tryFinManager();

	}

	public static void tryDbStorage() {
		DataStore my = new DbStorage();
		System.out.println(my.getUserNames());
		User currentUser = my.getUser("Serg");
		System.out.println(currentUser.getName() + "\t" + currentUser.getUserId());
		Account salaryAcc = my.getAccounts(currentUser).iterator().next();
		System.out.println(my.getAccounts(currentUser));
		Record rub1000 = new Record(Transfer.DEBIT, BigDecimal.valueOf(1000.0),
				"salary for month", new Category());
		Record rub9000 = new Record(Transfer.DEBIT, BigDecimal.valueOf(9000.0),
				"salary for month", new Category());

		my.addRecord(salaryAcc, rub9000);

		Iterator<Account> accIter = my.getAccounts(currentUser).iterator();
		while (accIter.hasNext()) {
			salaryAcc = accIter.next();
		}
		my.addRecord(salaryAcc, rub1000);
		accIter = my.getAccounts(currentUser).iterator();
		try (Connection conn = getConnection()) {
			while (accIter.hasNext()) {
				Account account = (Account) accIter.next();
				account.update(conn);

			}
			salaryAcc.update(conn);
		} catch (SQLException e) {
			e.printStackTrace();
		}

		System.out.println(my.getAccounts(currentUser));

		// Set<Account> accs = my.getAccounts(current);
	}

	public static void tryFinManager() {
		DataStore my = new Storage();
		System.out.println(my.getUserNames());
		my.addUser(new User("Serg", "111"));
		my.addUser(new User("First", "222"));
		my.addUser(new User("Second", "222"));
		my.addUser(new User("Serg", "222"));
		System.out.println(my.getUserNames());
		User current = my.getUser("Serg");
		System.out.println("Accounts before\t" + my.getAccounts(current));
		my.addAccount(current, new Account("MyAccount"));
		my.addAccount(current, new Account("GreyMoney"));
		my.addAccount(current, new Account("legal"));
		System.out.println(my.getAccounts(current));
		my.addAccount(current, my.getAccounts(current).iterator().next());
		for (Account acc : my.getAccounts(current)) {
			my.addRecord(acc, new Record(Transfer.DEBIT, BigDecimal.valueOf(1000.0), "salary",
					new Category()));
			my.addRecord(acc, new Record(Transfer.CREDIT,
					BigDecimal.valueOf(rnd.nextInt(10000) * rnd.nextDouble()), "present",
					new Category()));
		}
		PrintUser printer = new PrintUser(my);
		printer.printUser(current);
		Iterator<Account> myAccsIter = my.getAccounts(current).iterator();
		Iterator<Record> recordIter; 
		Account currentAcc = myAccsIter.next();
		currentAcc = myAccsIter.next();
		currentAcc = myAccsIter.next();
		recordIter = my.getRecords(currentAcc).iterator();
		my.removeRecord(currentAcc, recordIter.next());
		System.out.println("Accounts very after\t" + my.getAccounts(current));
		myAccsIter = my.getAccounts(current).iterator();
		currentAcc = myAccsIter.next();
		for (int i = 0; i < 20; i++) {
			my.addRecord(currentAcc,new Record(Transfer.DEBIT,
					BigDecimal.valueOf(990900.0), "salary", new Category()));
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {}

		}
		List<Record> toSort = new ArrayList<>();
		toSort.addAll(my.getRecords(currentAcc));
		Collections.shuffle(toSort);
		for (Record rec : toSort) {
			System.out.println(rec.getDate().getTime() + " : " + rec.getId());
		}
		Collections.sort(toSort, new RecordDateComparator());
		System.out.println("Sorted!");
		for (Record rec : toSort) {
			System.out.println(rec.getDate().getTime() + " : " + rec.getId());
		}

	}

	public static void tryLengthComp() {
		List<String> ls = Arrays.asList("aaa", "bb", "c", "d", "a");
		System.out.println("Before sort\t" + ls);
		Collections.sort(ls, new LengthComparator());
		System.out.println("After sort\t" + ls);
	}

	public static void tryStack() {
		Stack<String> ststr = new GenericStack<>();
		try {
			ststr.push("test!");
			ststr.push("is");
			ststr.push("This");
			while (!ststr.isEmpty()) {
				System.out.println(ststr.pop());
			}
		} catch (StackException e) {
			e.printStackTrace();
		}
	}

}
