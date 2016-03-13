package ru.geekbrains.algorithms.tests;

import static org.junit.Assert.*;

import java.util.Arrays;
import java.util.Comparator;
import java.util.Random;

import org.junit.Before;
import org.junit.Test;

import ru.geekbrains.algorithms.BinarySearch;

public class TestBinarySearch {
	Random rnd;
	BinarySearch searcher;
	Double[] evenSizeArr;
	Double[] oddSizeArr;
	Double[] zeroLengthArr;
	Double[] oneElementArr;
	Double key;

	
	@Before
	public void setUp() throws Exception {
		int size = 10000;
		rnd = new Random();
		key = Double.valueOf(0.5);
		searcher = new BinarySearch();
		evenSizeArr = new Double[size];
		oddSizeArr = new Double[size + 1];
		zeroLengthArr = new Double[0];
		oneElementArr = new Double[] {key};
		
		evenSizeArr[0] = oddSizeArr[0] = key;
		for (int i = 1; i < evenSizeArr.length; i++) {
			evenSizeArr[i] = rnd.nextDouble();
		}
		for (int i = 1; i < oddSizeArr.length; i++) {
			oddSizeArr[i] = rnd.nextDouble();
		}
		Arrays.sort(evenSizeArr);
		Arrays.sort(oddSizeArr);		
	}

	private Double createNewKey(Double[] array) {
		Double result;
		int index = keyIndex(array, key);
		if (index < array.length - 1) {
			result = (array[index] + array[index + 1]) / 2;
		} else {
			result = (array[index] + array[index - 1]) / 2;
		}
		return result;
	}
	
	private Double createHigestKey(Double[] array) {
		Double result;
		int index = array.length -1;
		result = array[index] + 0.1;
		return result;
	}
	
	private Double createLowestKey(Double[] array) {
		return array[0] - 0.1;
	}

	private int keyIndex(Double[] array, Double key) {
		return Arrays.binarySearch(array, key, new Comparator<Double>() {

			@Override
			public int compare(Double o1, Double o2) {
				return o1.compareTo(o2);
			}
		});
	}

	@Test
	public void testNullPointer() {
		assertEquals(-1, searcher.search(null, key));
	}
	
	@Test
	public void testZeroLength() {
		assertEquals(-1, searcher.search(zeroLengthArr, key));
	}
	
	@Test
	public void testOneElementKeyInArray() {
		assertEquals(0, searcher.search(oneElementArr, key));
	}
	
	@Test
	public void testOneElementKeyIsLowest() {
		assertEquals(keyIndex(oneElementArr, Double.valueOf(key - 0.1)), 
				searcher.search(oneElementArr, Double.valueOf(key - 0.1)));
	}
	
	@Test
	public void testOneElementKeyIsHighest() {
		assertEquals(keyIndex(oneElementArr, Double.valueOf(key + 0.1)), 
				searcher.search(oneElementArr, Double.valueOf(key + 0.1)));
	}
	
	@Test
	public void testEvenSizeKeyInArray() {
		assertEquals(keyIndex(evenSizeArr, key), searcher.search(evenSizeArr, key));
	}
	
	@Test
	public void testEvenSizeKeyNotInArray() {
		assertEquals(keyIndex(evenSizeArr, createNewKey(evenSizeArr)), 
				searcher.search(evenSizeArr, createNewKey(evenSizeArr)));
	}
	
	@Test
	public void testEvenSizeKeyIsLowest() {
		assertEquals(keyIndex(evenSizeArr, createLowestKey(evenSizeArr)), 
				searcher.search(evenSizeArr, createLowestKey(evenSizeArr)));
	}
	
	@Test
	public void testEvenSizeKeyIsHighest() {
		assertEquals(keyIndex(evenSizeArr, createHigestKey(evenSizeArr)), 
				searcher.search(evenSizeArr, createHigestKey(evenSizeArr)));
	}
	
	@Test
	public void testOddSizeKeyInArray() {
		assertEquals(keyIndex(oddSizeArr, key), searcher.search(oddSizeArr, key));
	}
	
	@Test
	public void testOddSizeKeyNotInArray() {
		assertEquals(keyIndex(oddSizeArr, createNewKey(oddSizeArr)), 
				searcher.search(oddSizeArr, createNewKey(oddSizeArr)));
	}
	
	@Test
	public void testOddSizeKeyIsLowest() {
		assertEquals(keyIndex(oddSizeArr, createLowestKey(oddSizeArr)), 
				searcher.search(oddSizeArr, createLowestKey(oddSizeArr)));
	}
	
	@Test
	public void testOddSizeKeyIsHighest() {
		assertEquals(keyIndex(oddSizeArr, createHigestKey(oddSizeArr)), 
				searcher.search(oddSizeArr, createHigestKey(oddSizeArr)));
	}

}
