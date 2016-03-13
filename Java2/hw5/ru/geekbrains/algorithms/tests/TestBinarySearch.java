package ru.geekbrains.algorithms.tests;

import static org.junit.Assert.*;

import java.util.Arrays;
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
	

	@Before
	public void setUp() throws Exception {
		searcher = new BinarySearch();
		
		
	}

	@Test
	public void testSearch() {
		fail("Not yet implemented");
	}

}
