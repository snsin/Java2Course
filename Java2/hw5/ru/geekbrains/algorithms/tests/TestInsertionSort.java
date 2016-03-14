package ru.geekbrains.algorithms.tests;

import static org.junit.Assert.*;

import java.util.Random;

import org.junit.Before;
import org.junit.Test;

public class TestInsertionSort {
	Random rnd;
	Integer[] oneElemArr;
	Integer[] array;

	@Before
	public void setUp() throws Exception {
		int length = 1000;
		rnd = new Random();
		oneElemArr = new Integer[] {length};
		array = new Integer[length];
		for (int i = 0; i < array.length; i++) {
			array[i] = rnd.nextInt();
		}
	}

	@Test
	public void testNullPointer() {
		assertFalse(isSorted(null));
	}
	
	@Test
	public void testOneElement() {
		assertTrue(isSorted(oneElemArr));
	}
	
	@Test
	public void testArray() {
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
				}
			}
		}
		return result;
		
	}

}
