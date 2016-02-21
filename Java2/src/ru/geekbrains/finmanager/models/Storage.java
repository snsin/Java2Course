package ru.geekbrains.finmanager.models;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class Storage implements DataStore {
	static int userId = 1;
	static int accountId = 1;
	static int recordId = 1;
	
	private Map<String, User> users = new HashMap<>();
	private Map<User, Set<Account>> accounts = new HashMap<>();
	private Map<Account, Set<Record>> records = new HashMap<>();

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
		Set<Account> result = accounts.get(owner);
		if (result == null) {
			result = new HashSet<>();
		}
		return result;
	}

	@Override
	public Set<Record> getRecords(Account account) {
		Set<Record> result = records.get(account);
		if (result == null){
			result = new HashSet<>();
		}
		return result;

	}

	@Override
	public void addUser(User user) {
		if (!users.containsKey(user.getName())) {
			user.setId(userId);
			userId++;
			users.put(user.getName(), user);
			accounts.put(user, new HashSet<>());
		}
	}

	@Override
	public void addAccount(User user, Account account) {
		Set<Account> accs = accounts.get(user);
		if (!accs.contains(account)){
			account.setId(accountId);
			accountId++;
			accs.add(account);
			records.put(account, new HashSet<>());
		}
		
	}

	@Override
	public void addRecord(Account account, Record record) {
		Set<Record> recs = records.get(account);
		if (!recs.contains(record)){
			recs.add(record);
			record.setId(recordId);
			recordId++;
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
		Set<Account> accs = accounts.get(owner);
		if (accs.remove(account)) {
			result = account;
		}
		return result;
	}

	@Override
	public Record removeRecord(Account from, Record record) {
		Record result =  null;
		Set<Record> recs = records.get(from);
		if (recs.remove(record)) {
			result = record;
			from.escape(record);
		}
		return result;		
	}

}
