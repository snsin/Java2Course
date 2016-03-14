package ru.geekbrains.algorithms.tests;

import static org.junit.Assert.*;

import java.util.Random;

import org.junit.Before;
import org.junit.Test;

import ru.geekbrains.algorithms.InsertionSort;

public class TestInsertionSort {
	InsertionSort sorter;
	Random rnd;
	Integer[] zeroSizeArr;
	Integer[] oneElemArr;
	Integer[] array;

	@Before
	public void setUp() throws Exception {
		sorter = new InsertionSort();
		int length = 1000;
		rnd = new Random();
		zeroSizeArr = new Integer[0];
		oneElemArr = new Integer[] {length};
		array = new Integer[length];
		for (int i = 0; i < array.length; i++) {
			array[i] = rnd.nextInt();
		}
	}

	@Test
	public void testNullPointer() {
		sorter.sort(null);
	}
	
	@Test
	public void testZeroSize() {
		sorter.sort(zeroSizeArr);
		assertFalse(isSorted(zeroSizeArr));
	}
	
	@Test
	public void testOneElement() {
		sorter.sort(oneElemArr);
		assertTrue(isSorted(oneElemArr));
	}
	
	@Test
	public void testArray() {
		sorter.sort(array);
		assertTrue(isSorted(array));
	}
	
	private boolean isSorted(Integer[] array) { 
		boolean result = false;
		if (array != null && array.length > 0) {
			Integer prev = array[0];
			for (Integer value : array) {
				result = true;
				if (value < prev) {
					result = false;
					break;
				} else {
					prev = value;
				}
			}
		}
		return result;
		
	}

}
