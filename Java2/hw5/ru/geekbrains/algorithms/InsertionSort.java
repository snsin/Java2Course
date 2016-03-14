package ru.geekbrains.algorithms;

public class InsertionSort {
	public <V extends Comparable<V>> void sort(V[] array) {
		if (array != null) {
			for (int j = 1; j < array.length; j++) {
				V key = array[j];
				int i = j - 1;
				while (i >= 0 && array[i].compareTo(key) > 0) {
					array[i + 1] = array[i];
					i--;
				}
				array[i] = key;
			} 
		}
	}
 
}
