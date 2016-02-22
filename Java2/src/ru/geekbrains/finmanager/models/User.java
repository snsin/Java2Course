package ru.geekbrains.finmanager.models;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class User {
	private int userId = 0;
	private String name;
	private String passHash;
	private boolean loggedIn = false;


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

	public int getUserId() {
		return userId;
	}

	public String getPassHash() {
		return passHash;
	}

	void setId(int id) {
		this.userId = id;
		
	}
	
	@Override
	public int hashCode() {
		return userId;
	}
	
}
