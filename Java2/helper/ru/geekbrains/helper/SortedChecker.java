package ru.geekbrains.helper;

public class SortedChecker {
	public static boolean isSorted(long[] array) {
		boolean result =false;
		if (array != null && array.length > 0) {
			long previos = array[0];
			result = true;
			for (long e : array) {
				if (e < previos) {
					result = false;
					break;					
				} else {
					previos = e;
				}
			}
		}
		return result;
	}

}
