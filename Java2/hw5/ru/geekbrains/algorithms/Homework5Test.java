package ru.geekbrains.algorithms;

import java.util.Random;

public class Homework5Test {
	static Random rnd;
	static int[] bestCaseArr;
	static int[] stupidArr;
	
	public static void setUp() {
		rnd = new Random();
		int max = 10;
		bestCaseArr = new int[100*max];
		for (int i = 0; i < bestCaseArr.length; i++) {
			bestCaseArr[i] = rnd.nextInt(max);
		}
		stupidArr = new int[] {0, 100 * max};
	}
	public static void main(String[] args){
		setUp();
		printArr(CountingSort.sort(bestCaseArr));
		printArr(CountingSort.sort(stupidArr));
		
	}

	public static void printArr(int[] arr) {
		int counter = 1;
		for (int e : arr) {
			System.out.printf("%3d ", e);
			if (counter % 16 == 0) {
				System.out.println();
			}
			counter++;
		}
		System.out.println();
		
	}
}
