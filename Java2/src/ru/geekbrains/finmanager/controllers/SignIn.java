package ru.geekbrains.finmanager.controllers;

import ru.geekbrains.finmanager.models.DataStore;
import ru.geekbrains.finmanager.models.User;

public class SignIn {
	private Password password;
	private DataStore storage;
	
	public SignIn(Password password, DataStore storage) {
		this.password = password;
		this.storage = storage;
	}
	
	public boolean isNameExist(String name) {
		return storage.getUserNames().contains(name);
	}
	
	public User createUser(String name, String password) {
		User result = null;
		if (!isNameExist(name)) {
			String passHash = this.password.passHash(password);
			storage.addUser(new User(name, passHash));
			result = storage.getUser(name);
		}
		return result;	
	}

}
