package ru.geekbrains.algorithms;

import java.util.HashMap;
import java.util.Map;

public class CharHistogram {
	
	public static Map<String, Integer> hist(String source) {
		Map<String, Integer> result = null;
		if (source != null) {
			result = new HashMap<>();
			char[] chars = source.toCharArray();
			for (char c : chars) {
				Integer count = result.put(String.valueOf(c), 1);
				if (count != null) {
					result.put(String.valueOf(c), count + 1);
				}
			}
		}
		return result;
	}

}
