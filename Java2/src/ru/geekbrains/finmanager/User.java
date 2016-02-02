package ru.geekbrains.finmanager;

import java.util.LinkedList;
import java.util.List;

public class User {
	private String name;
	private int pass;
	private boolean loggedIn = false;
	private List<Account> accounts = new LinkedList<>();
	
	public User(String name, String pass) {
		this.name = name;
		this.pass = pass.hashCode();
	}
	
	public boolean login(String pass) {
		this.loggedIn = this.pass == pass.hashCode();
		return this.loggedIn;
	}

	public String getName() {
		return name;
	}
	
	public List<Account> getAccounts() {
		return accounts;	
	}

	public boolean addAccount(Account account) {
		boolean isNewAcc = ! this.accounts.contains(account);
		if (isNewAcc) {
			this.accounts.add(account);
		}
		return isNewAcc;
	}

	public Account removeAccount(Account account) {
		Account result = null;
		if (accounts.remove(account)) {
			result = account;
		}
		return result;
	}
}
