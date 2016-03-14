package ru.geekbrains.algorithms;

import java.util.Arrays;
import java.util.Random;

import ru.geekbrains.helper.SortedChecker;

public class Homework5Test {
	static Random rnd;
	static int[] bestCaseArr;
	static int[] stupidArr;
	static Double[] bigArr;
	
	public static void setUp() {
		rnd = new Random();
		int max = 10;
		bestCaseArr = new int[100*max];
		for (int i = 0; i < bestCaseArr.length; i++) {
			bestCaseArr[i] = rnd.nextInt(max);
		}
		stupidArr = new int[] {0, 100 * max};
		bigArr = new Double[1000001];
		for (int i = 0; i < bigArr.length; i++) {
			bigArr[i] = rnd.nextDouble();
		}

	}
	private static <T> void printPart(T[] array, int index, int count) {
		int from = index - (count / 2);
		from = from >= 0 ? from : 0;
		int to = from + count;
		to = to < array.length ? to : array.length;
		for (int i = from; i < to; i++) {
			System.out.printf("%s ", array[i].toString());
		}
		System.out.println();
		
	}
	public static void main(String[] args){
		setUp();
		/*		printArr(CountingSort.sort(bestCaseArr));
		printArr(CountingSort.sort(stupidArr));*/
		bigArr[bigArr.length - 1] = Double.valueOf(0.5);
		Arrays.sort(bigArr);
		int index = new BinarySearch().search(bigArr, bigArr[0]);
		System.out.println(index);
		if (index >= 0) {
			printPart(bigArr, index, 5);
			System.out.println(bigArr[index]);
		}
		index = new BinarySearch().search(bigArr, bigArr[bigArr.length - 1]);
		System.out.println(index);
		if (index >= 0) {
			printPart(bigArr, index, 5);
			System.out.println(bigArr[index]);
		}
		
		/*SortedChecker.isSorted((long[])arr);*/
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
