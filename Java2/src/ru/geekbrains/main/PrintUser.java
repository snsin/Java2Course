package ru.geekbrains.main;

import ru.geekbrains.finmanager.DataStore;
import ru.geekbrains.finmanager.Storage;
import ru.geekbrains.finmanager.User;

public class PrintUser {
	DataStore storage = new Storage();
	
	PrintUser(DataStore storage) {
		if (storage != null) {
			this.storage = storage;
		}
	}
	void printUser(User user) {


	}

}
