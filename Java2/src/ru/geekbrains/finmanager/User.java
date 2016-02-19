package ru.geekbrains.finmanager;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.util.LinkedList;
import java.util.List;

public class User implements JdbcCrud {
    private static final String[] COLUMN_NAMES = {
            "id",
            "login",
            "pass"
    };
    private final int userId;
    private final String name;
    private String passHash;
    private boolean loggedIn = false;
    private List<Account> accounts = new LinkedList<>();
    
    public User(String name, String passHash) {
        this.userId = 0;
        this.name = name;
        this.passHash = passHash;
    }
    
    User(int id, String name, String passHash) {
        this.userId = id;
        this.name = name;
        this.passHash = passHash;
    };
    
    public static String password(String pass) {
        MessageDigest md5sum = null;
        try {
            md5sum = MessageDigest.getInstance("MD5");
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            System.exit(SysErrs.PASS_GENERATE_ERR.ordinal());
        }
        byte[] rawSum = md5sum.digest(pass.getBytes());
        StringBuilder passHash = new StringBuilder();
        for (byte b : rawSum) {
            passHash.append(String.format("%02x", b));
        }
        return passHash.toString();
    }
    
    public boolean login(String pass) {
        this.loggedIn = this.passHash.equals(password(pass));
        return this.loggedIn;
    }

    public String getName() {
        return name;
    }
    
    public List<Account> getAccounts() {
        return accounts;    
    }

    public boolean addAccount(Account account) {
        boolean isNewAcc = ! this.accounts.contains(account);
        if (isNewAcc) {
            this.accounts.add(account);
        }
        return isNewAcc;
    }

    public Account removeAccount(Account account) {
        Account result = null;
        if (accounts.remove(account)) {
            result = account;
        }
        return result;
    }

    public int getUserId() {
        return userId;
    }

    @Override
    public boolean create(Connection conn, int id) {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public boolean read(Connection conn, int id) {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public boolean update(Connection conn, int id) {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public boolean delete(Connection conn, int id) {
        // TODO Auto-generated method stub
        return false;
    }
    
    
}
