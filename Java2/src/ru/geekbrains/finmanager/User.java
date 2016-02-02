package ru.geekbrains.finmanager;

import java.util.HashSet;
import java.util.Set;

public class User {
	private String name;
	private int pass;
	private boolean loggedIn = false;
	private Set<Account> accounts = new HashSet<>();
	
	public boolean login(String pass) {
		this.loggedIn = this.pass == pass.hashCode();
		return this.loggedIn;
	}

	public String getName() {
		return name;
	}
	
	public Set<Account> getAccounts() {
		return accounts;	
	}
}
