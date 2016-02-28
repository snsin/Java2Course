package ru.geekbrains.algorithms;

public class MaxPositiveInteger {
	static int max(int[] arr) {
		int result = 0;
		for (int i : arr) {
			result = i > result ? i : result;
		}
		return result;
	}

}
