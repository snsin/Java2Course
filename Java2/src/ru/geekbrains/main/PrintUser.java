package ru.geekbrains.main;

import ru.geekbrains.finmanager.models.Account;
import ru.geekbrains.finmanager.models.DataStore;
import ru.geekbrains.finmanager.models.Record;
import ru.geekbrains.finmanager.models.Storage;
import ru.geekbrains.finmanager.models.User;

public class PrintUser {
	DataStore storage = new Storage();
	
	PrintUser(DataStore storage) {
		if (storage != null) {
			this.storage = storage;
		}
	}
	void printUser(User user) {
		System.out.println("hahahaha");
		System.out.println(user.getName());
		for (Account acc : storage.getAccounts(user)) {
			int allign = 10 +  acc.toString().length();
			System.out.printf("%" + allign + "s\n", acc);
			for (Record rec : storage.getRecords(acc)) {
				int recAllign = 20 + rec.toString().length();
				System.out.printf("%" + recAllign + "s\n", rec);
			}
		}
	}

}
