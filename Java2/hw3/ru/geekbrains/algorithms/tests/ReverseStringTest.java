package ru.geekbrains.algorithms.tests;

import static org.junit.Assert.*;

import org.junit.Test;

import ru.geekbrains.algorithms.ReverseString;

public class ReverseStringTest {

	@Test
	public void testEvenLengthString() {
		assertEquals("fedcba", ReverseString.reverse("abcdef"));;
	}
	
	@Test
	public void testOddLengthString() {
		assertEquals("gfedcba", ReverseString.reverse("abcdefg"));
	}
	
	@Test
	public void testNull() {
		assertNull(ReverseString.reverse(null));
	}
	
	@Test
	public void testEmptyString() {
		assertEquals("", ReverseString.reverse(""));
	}

}
