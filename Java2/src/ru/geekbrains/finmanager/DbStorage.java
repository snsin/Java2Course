package ru.geekbrains.finmanager;

import java.util.Set;

public class DbStorage implements DataStore {
    private String user = "postgres";
    private String password = "sergey";
    private String url = "jdbc:postgresql://localhost:5432/test_db"; 
    private String driver = "org.postgresql.Driver";
    
    @Override
    public User getUser(String name) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Set<String> getUserNames() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Set<Account> getAccounts(User owner) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Set<Record> getRecords(Account account) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public void addUser(User user) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void addAccount(User user, Account account) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void addRecord(Account account, Record record) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public User removeUser(String name) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Account removeAccount(User owner, Account account) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Record removeRecord(Account from, Record record) {
        // TODO Auto-generated method stub
        return null;
    }
    

}
