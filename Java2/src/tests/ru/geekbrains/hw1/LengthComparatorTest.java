package tests.ru.geekbrains.hw1;

import static org.junit.Assert.*;

import org.junit.Test;

import ru.geekbrains.hw1.LengthComparator;

public class LengthComparatorTest {

	@Test(expected = NullPointerException.class)
	public void testCompareNPE0() {
		LengthComparator cmp = new LengthComparator();
		cmp.compare("abc", null);
	}

	@Test(expected = NullPointerException.class)
	public void testCompareNPE1() {
		LengthComparator cmp = new LengthComparator();
		cmp.compare(null, "abc");
	}

	@Test(expected = ClassCastException.class)
	public void testCastExeption1() {
		LengthComparator cmp = new LengthComparator();
		Object dig = new Double(43);
		cmp.compare("ooo", (String) dig);

	}

	@Test
	public void testCompare() {
		LengthComparator cmp = new LengthComparator();
		String[] values = new String[] { "aaaaaaaaaaaaa", "bbbbb", "ccc" };
		assertTrue("A and B", 0 < cmp.compare(values[0], values[1]));
		assertTrue("B and A", 0 > cmp.compare(values[1], values[0]));
		assertTrue("A and B = - B and A", cmp.compare(values[0], values[1]) == -1
				* cmp.compare(values[1], values[0]));
		assertTrue("A and B = - B and A", cmp.compare(values[0], values[1]) == -1
				* cmp.compare(values[1], values[0]));
		assertTrue("B and C", 0 < cmp.compare(values[1], values[2]));
		assertTrue("A and C", 0 < cmp.compare(values[0], values[2]));
	}

}
