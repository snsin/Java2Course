package ru.geekbrains.algorithms;

import java.lang.reflect.Array;

public class HeapQueue<K extends Comparable<K>, T> implements PriorityQueue<K, T> {
	
	private class Pair {
		private K key;
		private T value;
	}
	
	public static final int DEFAULT_SIZE = 16;
	private int heapSize = 0;
	private Pair[] heap;	
	
	public HeapQueue() {
		heap = createArray(DEFAULT_SIZE);
	}
	
	public HeapQueue(int size) {
		size = size < DEFAULT_SIZE ? DEFAULT_SIZE : size;
		heap =  createArray(size);
	}
	
	public int getSize() {
		return heapSize;
	}

	@Override
	public T getMax() {
		T result = null;
		if (heapSize > 0) {
			result = heap[0].value;
			heap[0] = heap[--heapSize];
			maxHeapify(0);
		}
		return result;
	}

	@Override
	public void insert(K key, T elem){
		int i = heapSize;
		incrementSize();
//		heap[i] = new Pair();
		heap[i].value = elem;
		heap[i].key = key;
		while ((i > 0) && (heap[parent(i)].key.compareTo(heap[i].key) < 0)) {
			swap(parent(i), i);
			i = parent(i);
		}
	}
	

	public T pickMax() {
		return heap[0].value;
	}
	
	private void maxHeapify (int i) {
		int left = left(i);
		int right = rigth(i);
		int largest = -1;
		if (isCorrectIndex(left)) {
			largest = heap[left].key.compareTo(heap[i].key) > 0 ? left : i;
		}
		if (isCorrectIndex(right)) {
			largest = heap[right].key.compareTo(heap[largest].key) > 0 ? right : largest;
		}
		if (isCorrectIndex(largest) && (largest != i)) {
			swap(i, largest);
			maxHeapify(largest);
		}
	}

	private int parent(int i) {
		return (i - 1) / 2;
	}

	private int left(int i) {
		return 2 * i + 1;
	}

	private int rigth(int i) {
		return 2 * i + 2;
	}
	
	private boolean isCorrectIndex(int i) {
		return ((i >= 0) && (i < heapSize));
	}
	
	private void swap(int i, int k) {
		if (i != k) {
			Pair temp = heap[i];
			heap[i] = heap[k];
			heap[k] = temp;
		}
	}
	
	private void incrementSize() {
		if (heapSize < heap.length) {
			heapSize++;
		} else {
			increaseSize();
			heapSize++;
		}
		
	}

	private void increaseSize() {
		Pair[] newHeap = createArray(heap.length + DEFAULT_SIZE);
		System.arraycopy(heap, 0, newHeap, 0, heap.length);
		heap = newHeap;
	}
	
	@SuppressWarnings("unchecked")
	private Pair[] createArray(int size){
		Pair dummy = new Pair();
		Pair[] result = (Pair[]) Array.newInstance(dummy.getClass(), size);
		for (int i = 0; i < result.length; i++) {
			result[i] = new Pair();
		}
		return result;
	}
	
}
