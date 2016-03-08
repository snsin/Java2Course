package ru.geekbrains.algorithms;

import java.util.HashMap;

public class Main {

	public static void main(String[] args) {
		Integer[] arr = {1,10,12,2,4,7,8};
		for (Integer integer : arr) {
			System.out.print(integer + " ");
		}
		System.out.println();
		
		Heap<Integer> h = new Heap<>(arr);
		for (Integer integer : h.getAsAray()) {
			System.out.print(integer + " ");
		}
		System.out.println();
		
		h.insert(43);
	
		for (Integer integer : h.getAsAray()) {
			System.out.print(integer + " ");
		}
		System.out.println();
		
		h.insert(11);
		
		for (Integer integer : h.getAsAray()) {
			System.out.print(integer + " ");
		}
		System.out.println();
		
		new HashMap<String, Integer>();

	}

}
