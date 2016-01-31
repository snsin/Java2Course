package ru.geekbrains.finmanager;

import java.util.LinkedList;
import java.util.List;

public class User {
	private String name;
	private int pass;
	private boolean loggedIn = false;
	private List<Account> accounts = new LinkedList<>();
	
	public String getName() {
		return name;
	}
	
	public boolean login(String pass) {
		this.loggedIn = this.pass == pass.hashCode();
		return this.loggedIn;
	}

}
