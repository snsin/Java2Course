package ru.geekbrains.hw1;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Random;

import ru.geekbrains.finmanager.*;

public class Main {
	public static final Random rnd = new Random();
	
	public static void main(String[] args) {
		tryStack();
		tryLengthComp();
		tryFinManager();
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
			acc.conduct(new Record(Transfer.DEBIT, 1000.0, "salary"));
			acc.conduct(new Record(Transfer.CDREDIT, rnd.nextInt(10000) * rnd.nextDouble(), "present"));
		}
		System.out.println("Accounts after\t" + my.getAccounts(current));
		current.getAccounts().get(2).escape(current.getAccounts().get(2).getRecords().get(1));
		System.out.println("Accounts very after\t" + my.getAccounts(current));
		
	}

	public static void tryLengthComp() {
		List<String> ls = Arrays.asList("aaa", "bb", "c", "d", "a");
		System.out.println("Before sort\t" + ls);
		Collections.sort(ls, new LengthComparator());
		System.out.println("After sort\t" + ls);	
	}
	
	public static void tryStack() {
		GenericStack<String> ststr = new GenericStack<>();
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
