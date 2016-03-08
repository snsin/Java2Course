package ru.geekbrains.algorithms;

import java.util.ArrayList;
import java.util.Collection;

public class MyMap {
	public <R, T> Collection<R> map(Collection<T> collection, Operator<R, T> operator) {
		Collection<R> result = new ArrayList<>();
		for (T elem : collection) {
			result.add(operator.apply(elem));
		}
		return result;
	}


}
