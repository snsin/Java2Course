package ru.geekbrains.finmanager;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class Storage implements DataStore {
	private Map<String, User> users = new HashMap<>();

	@Override
	public User getUser(String name) {
		return users.get(name);
	}

	@Override
	public Set<String> getUserNames() {
		return users.keySet();
	}

	@Override
	public Set<Account> getAccounts(User owner) {
		Set<Account> result = new HashSet<>();
		result.addAll(owner.getAccounts());
		return result;
	}

	@Override
	public Set<Record> getRecords(Account account) {
		Set<Record> result = new HashSet<>();
		result.addAll(account.getRecords());
		return result;

	}

	@Override
	public void addUser(User user) {
		if (!users.containsKey(user.getName())) {
			users.put(user.getName(), user);
		}
	}

	@Override
	public void addAccount(User user, Account account) {
		user.addAccount(account);
	}

	@Override
	public void addRecord(Account account, Record record) {
		account.conduct(record);
	}

	@Override
	public User removeUser(String name) {
		return users.remove(name);
	}

	@Override
	public Account removeAccount(User owner, Account account) {
		return owner.removeAccount(account);
	}

	@Override
	public Record removeRecord(Account from, Record record) {
		return from.escape(record);
	}

}
