package ru.geekbrains.algorithms;

public interface PriorityQueue<K extends Comparable<K>, T> {
	T getMax();
	void insert(K k, T t);
}
