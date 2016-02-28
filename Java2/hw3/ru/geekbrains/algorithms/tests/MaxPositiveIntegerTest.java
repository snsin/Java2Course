package ru.geekbrains.algorithms.tests;

import static org.junit.Assert.*;

import java.util.Arrays;

import org.junit.Before;
import org.junit.Test;

import ru.geekbrains.algorithms.MaxPositiveInteger;

public class MaxPositiveIntegerTest {
	private int[] testArray;
	
	@Before
	public void createArray() {
		testArray = new int[15];
		for (int i = 1; i < testArray.length; i++) {
			testArray[i] = i + 10;
		}
		testArray[0] = 0;

	}

	@Test
	public void test() {
		assertEquals(testArray.length + 9, MaxPositiveInteger.max(testArray));

	}

}
