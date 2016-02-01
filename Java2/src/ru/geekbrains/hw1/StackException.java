package ru.geekbrains.hw1;

public class StackException extends Exception {

	private static final long serialVersionUID = 7925826990110154550L;
	String message;
	
	StackException() {};
	StackException(String message) {
		this.message = message;
	}
	
	@Override
	public String toString() {
		return "\t" + super.toString() + " " + this.message;
	}
}
