package ru.geekbrains.finmanager;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class Storage implements DataStore {
	private Map<String, User> users = new HashMap<>();
	private Map<String, Set<Account>> accounts = new HashMap<>();
	private Map<String, Set<Record>> records = new HashMap<>();

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
		result.addAll(accounts.get(owner.getName()));
		return result;
	}

	@Override
	public Set<Record> getRecords(Account account) {
		Set<Record> result = new HashSet<>();
		result.addAll(records.get(account.getId()));
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
		Set<Account> accs = accounts.get(user.getName());
		if (!accs.contains(account)){
			accs.add(account);
		}
		
	}

	@Override
	public void addRecord(Account account, Record record) {
		Set<Record> recs = records.get(account.getId());
		if (!recs.contains(record)){
			recs.add(record);
			account.conduct(record);
		}
	}

	@Override
	public User removeUser(String name) {
		return users.remove(name);
	}

	@Override
	public Account removeAccount(User owner, Account account) {
		Account result = null;
		Set<Account> accs = accounts.get(owner.getName());
		if (accs.remove(account)) {
			result = account;
		}
		return result;
	}

	@Override
	public Record removeRecord(Account from, Record record) {
		Record result =  null;
		Set<Record> recs = records.get(from.getId());
		if (recs.remove(record)) {
			result = record;
			from.escape(record);
		}
		return result;		
	}

}
