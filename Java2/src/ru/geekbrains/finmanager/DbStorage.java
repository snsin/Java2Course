package ru.geekbrains.finmanager;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Set;

public class DbStorage implements DataStore {
    private String user = "postgres";
    private String password = "sergey";
    private String url = "jdbc:postgresql://localhost:5432/test_db"; 
    private String driver = "org.postgresql.Driver";
    
    private Connection getConnection() throws SQLException {
        return DriverManager.getConnection(url, user, password);
    }

    public DbStorage() {
        try {
            Class.forName(driver); 
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
//TODO rewrite classes add id parametr to constructor args
//TODO change table to provide unique user name and make drop_all.sql
    // which delete all tables
    @Override
    public User getUser(String name) {
        User result = null;
        String sqlReqest = "SELECT * FROM users WHERE login = ?;";
        try (Connection  conn = getConnection()) {
            conn.setAutoCommit(false);
            PreparedStatement stm = conn.prepareStatement(sqlReqest);
            stm.setString(1, name);
            ResultSet res = stm.executeQuery();
            if (!res.wasNull()) {
                result = new User(res.getString(2), res.getString(3));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        i
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
