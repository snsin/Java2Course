package ru.geekbrains.algorithms.tests;

import static org.junit.Assert.*;

import java.util.Random;

import org.junit.Before;
import org.junit.Test;

import ru.geekbrains.algorithms.CountingSort;

public class TestCountingSort {
	Random rnd;
	int[] bestCaseArr;
	int[] stupidArr;
	int[] zeroLengthArr;
	int[] negativeElementArray;
	
	@Before
	public void setUp() throws Exception {
		rnd = new Random();
		int max = 100;
		bestCaseArr = new int[1000*max];
		for (int i = 0; i < bestCaseArr.length; i++) {
			bestCaseArr[i] = rnd.nextInt(max);
		}
		negativeElementArray = new int[bestCaseArr.length];
		System.arraycopy(bestCaseArr, 0, negativeElementArray, 0, bestCaseArr.length);
		negativeElementArray[bestCaseArr.length / 2] = -1;
		stupidArr = new int[] {0, 100 * max};
		zeroLengthArr = new int[0];
	}

	@Test
	public void testBestCase() {	
		assertTrue(isSorted(CountingSort.sort(bestCaseArr)));
	}
	
	@Test
	public void testStupidButShouldWork() {
		assertTrue(isSorted(CountingSort.sort(stupidArr)));
	}
	
	@Test
	public void testNullPointer() {
		assertNull(CountingSort.sort(null));
	}
	
	@Test
	public void testZeroLength() {
		assertNull(CountingSort.sort(zeroLengthArr));
	}
	
	@Test
	public void testNegativeElement() {
		assertNull(CountingSort.sort(negativeElementArray));
	}
	
	private boolean isSorted(int[] array) {
		boolean result =false;
		if (array != null && array.length > 0) {
			int previos = array[0];
			result = true;
			for (int e : array) {
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
