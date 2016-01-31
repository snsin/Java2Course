package ru.geekbrains.hw1;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class Main {

	public static void main(String[] args) {
		GenericStack<String> ststr = new GenericStack<>();
		try {
			ststr.push("ОГО");
			ststr.push(new Integer(983240189).toString());
			while (! ststr.isEmpty()) {
				System.out.println(ststr.pop());
			}
		} catch (StackException e) {
			e.printStackTrace();
		}
		List<String> ls = Arrays.asList("aaa", "bb", "c");
		System.out.println(ls);
		Collections.sort(ls, new LengthComparator());
		System.out.println(ls);
		System.out.println(EnuTest.THIS);
		System.out.println(Double.compare(Double.MAX_VALUE, Double.POSITIVE_INFINITY));
		Double.compare(0.0, Double.NaN);
		
	}
	

}
