package ru.geekbrains.finmanager.models;



public class User {
	private int userId = 0;
	private String name;
	private String passHash;



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


/*	public boolean login(String pass) {
		this.loggedIn = this.passHash.equals(password(pass));
		return this.loggedIn;
	}*/

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
