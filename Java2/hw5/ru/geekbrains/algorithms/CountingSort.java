package ru.geekbrains.algorithms;

import java.util.Arrays;

public class CountingSort {
	public static int[] sort(int[] srcArray) {
		int[] result = null;
		int[] helper = helperOrFail(srcArray);
		if (helper != null) {
			result = new int[srcArray.length];
			for (int i = 0; i < srcArray.length; i++) {
				helper[srcArray[i]]++;
			}
			for (int i = 1; i < helper.length; i++) {
				helper[i] += helper[i-1];
			}
			for (int i = srcArray.length - 1; i >= 0; i--) {
				result[helper[srcArray[i]] - 1] = srcArray[i];
				helper[srcArray[i]]--;
			}
		}
		return result;
	}
	
	private static int[] helperOrFail(int[] srcArray) {
		int result[] = null;
		if (srcArray != null && srcArray.length > 0) {
			int helperLength = max(srcArray);
			result = helperLength < 0 ? null : new int[helperLength+1];
			Arrays.fill(result, 0);
		}
		return result;
	}
	
	private static int max(int[] srcArray) {
		int max = -1;
		max = srcArray[0];
		for (int i : srcArray) {
			max = i > max ? i :max;
			if (i < 0) {
				max = -1;
				break;
			}
		}
		return max;
	}
}
