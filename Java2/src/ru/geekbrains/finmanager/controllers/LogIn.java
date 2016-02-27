package ru.geekbrains.finmanager.controllers;

import ru.geekbrains.finmanager.models.DataStore;
import ru.geekbrains.finmanager.models.User;

public class LogIn {
	private Password password;
	private DataStore storage;
	
	public LogIn(Password password, DataStore store) {
		this.password = password;
		this.storage = store;
	}
	
	public User login(String userName, String password) {
		User result = storage.getUser(userName);
		if (result != null) {
			String currentHash = this.password.passHash(password);
			result = currentHash.equals(result.getPassHash()) ? result : null;
		}
		return result;
	}

}
