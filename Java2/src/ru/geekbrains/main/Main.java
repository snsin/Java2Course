package ru.geekbrains.main;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.Set;

import ru.geekbrains.finmanager.*;
import ru.geekbrains.hw1.*;

public class Main {
	public static final Random rnd = new Random();
	
	public static void main(String[] args) {
//		tryStack();
//		tryLengthComp();
//		tryFinManager();
/*	    Category.addCategory("Health");
	    Category cat = new Category("heaLth", "Pills");
	    System.out.println(cat.getName() + " " + cat.getDescription());*/
	    try {
	        System.out.println("Trying JDBC");
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
	    System.out.println("Sucsess");
	    
	    tryDbStorage();

	}
	public static void tryDbStorage() {
        DataStore my = new DbStorage();
        System.out.println(my.getUserNames());
        my.addUser(new User("Serg", "111"));
        System.out.println(my.getUserNames());
        my.addUser(new User("First", "222"));
        System.out.println(my.getUserNames());
        my.addUser(new User("Second", "222"));
        System.out.println(my.getUserNames());
        my.addUser(new User("Serg", "23322"));
        System.out.println(my.getUserNames());
        User current = my.getUser("Serg");	
        System.out.println(current.getName() + "\t" + current.getUserId());
//        my.addAccount(current, new Account("salary"));
        Account salaryAcc = my.getAccounts(current).iterator().next();
        System.out.println(my.getAccounts(current));
        my.addRecord(salaryAcc, new Record(Transfer.DEBIT, BigDecimal.valueOf(1000.0), "salary for month", 
                    new Category()));
        System.out.println(my.getAccounts(current));
        
        //Set<Account> accs = my.getAccounts(current);
	}
	public static void tryFinManager() {
		DataStore my = new Storage();
		my.addUser(new User("Serg", "111"));
		my.addUser(new User("First", "222"));
		my.addUser(new User("Second", "222"));
		my.addUser(new User("Serg", "222"));
		System.out.println(my.getUserNames());
		User current = my.getUser("Serg");
		System.out.println("Accounts before\t" + my.getAccounts(current));
		current.addAccount(new Account("MyAccount"));
		current.addAccount(new Account("GreyMoney"));
		current.addAccount(new Account("legal"));
		System.out.println(current.getAccounts().get(0));
		current.addAccount(current.getAccounts().get(0));
		for (Account acc : current.getAccounts()) {
			acc.conduct(new Record(Transfer.DEBIT, BigDecimal.valueOf(1000.0), "salary", 
			        new Category()));
			acc.conduct(new Record(Transfer.CREDIT, BigDecimal.valueOf(rnd.nextInt(10000) * rnd.nextDouble()), 
			        "present", new Category()));
		}
		System.out.println("Accounts after\t" + my.getAccounts(current));
		current.getAccounts().get(2).escape(current.getAccounts().get(2).getRecords().get(1));
		System.out.println("Accounts very after\t" + my.getAccounts(current));
		for (int i = 0; i < 20; i++) {
			current.getAccounts().get(0).conduct(new Record(Transfer.DEBIT, BigDecimal.valueOf(1000.0),
			        "salary", new Category()));
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {};
			
		}
		List<Record> toSort = current.getAccounts().get(0).getRecords();
		Collections.shuffle(toSort);
		for (Record rec : toSort) {
			System.out.println(rec.getDate() + " : " + rec.getId());
		}
		Collections.sort(toSort, new RecordDateComparator());
		System.out.println("Sorted!");
		for (Record rec : toSort) {
			System.out.println(rec.getDate() + " : " + rec.getId());
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
			while (! ststr.isEmpty()) {
				System.out.println(ststr.pop());
			}
		} catch (StackException e) {
			e.printStackTrace();
		}
	}

	
}
