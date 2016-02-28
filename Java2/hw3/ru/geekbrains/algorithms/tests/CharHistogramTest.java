package ru.geekbrains.algorithms.tests;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import ru.geekbrains.algorithms.CharHistogram;

public class CharHistogramTest {
	private String fiveAStr;
	private String nullStr;
	private String emptyStr;

	@Before
	public void setUp() throws Exception {
		fiveAStr = "This is A sAmple And AA";
		nullStr = null;
		emptyStr = "";
		
	}

	@Test
	public void testHist() {
		assertEquals(5, (int) CharHistogram.hist(fiveAStr).get("A"));
	}
	
	@Test
	public void testNull() {
		assertNull(CharHistogram.hist(nullStr));
	}
	
	public void testEmpty() {
		assertEquals(0, CharHistogram.hist(emptyStr).size());
	}

}
