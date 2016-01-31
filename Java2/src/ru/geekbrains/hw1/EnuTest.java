package ru.geekbrains.hw1;

public enum EnuTest {NET("Net"), THIS("This"), IS("is"), TEST("test!");
	private String str;
	
	EnuTest(String str) {
		this.str = str;
	}
	@Override
	public String toString() {
		return str;
	}

}
