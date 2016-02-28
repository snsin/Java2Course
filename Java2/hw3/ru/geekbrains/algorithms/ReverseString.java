package ru.geekbrains.algorithms;

public class ReverseString {
	public static String reverse(String suorce) {
		String result = null;
		if (suorce != null){
			char[] reciever = suorce.toCharArray();
			int gap = reciever.length / 2;
			int lastNumber = reciever.length - 1;
			for (int i = 0; i < gap; i++) {
				char temp = reciever[lastNumber - i];
				reciever[lastNumber - i] = reciever[i];
				reciever[i] = temp;
			}
			result = String.valueOf(reciever);
		}
		return result;
	}
}
