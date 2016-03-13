package ru.geekbrains.algorithms;

public class BinarySearch {
	public <E extends Comparable<E>> int search(E[] arr, E key) {
		int result = -1;
		if (arr != null && key !=null && arr.length > 0) {
			int lowBound = 0;
			int highBound = arr.length;
			int gap;
			do {
				gap = (highBound - lowBound) / 2;
				int cmp = key.compareTo(arr[lowBound + gap]);
				if (cmp > 0) {
					lowBound += gap;
				} else if (cmp < 0) {
					highBound -= gap;
				} else {
					result = lowBound + gap;
					break;
				}
			} while (gap > 0);
			
		}
		return result;
	}
}
