package ru.geekbrains.algorithms;

import java.util.Random;

public class Main {
	static Random rnd = new Random();

	public static void main(String[] args) {

		PriorityQueue<Integer, String> h = new HeapQueue<>();
		
		for (int i = 0; i < 20; i++) {
			Integer val = rnd.nextInt(300);
			h.insert(val, "s" + val.toString());
		}
		
		h.insert(43, "One");
	
		System.out.println();
		
		h.insert(11, "Two");
		
		System.out.println();
		
		String value;
		while ((value = h.getMax()) != null){
			System.out.println(value);
		}
		

	}

}
