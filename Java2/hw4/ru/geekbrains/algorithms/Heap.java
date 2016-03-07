package ru.geekbrains.algorithms;

public class Heap<T extends Comparable<T>> {
	public static final int DEFAULT_SIZE = 16;
	
	private int heapSize = 0;
	private T[] heap;
	
	@SuppressWarnings("unchecked")
	public Heap() {
		heap = (T[]) new Object[DEFAULT_SIZE];
	}
	
	@SuppressWarnings("unchecked")
	public Heap(int size) {
		size = size < DEFAULT_SIZE ? DEFAULT_SIZE : size;
		heap = (T[]) new Object[size];
	}
	
	public int getSize() {
		return heapSize;
	}

	void maxHeapify (int i) {
		int left = left(i);
		int right = rigth(i);
		int largest = -1;
		if (isCorrectIndex(left)) {
			largest = heap[left].compareTo(heap[i]) > 0 ? left : i;
		}
		if (isCorrectIndex(right)) {
			largest = heap[right].compareTo(heap[largest]) > 0 ? right : largest;
		}
		if (isCorrectIndex(largest) && (largest != i)) {
			swap(i, largest);
			maxHeapify(largest);
		}
	}

	T extractMax() {
		T result = null;
		if (heapSize > 0) {
			result = heap[0];
			heap[0] = heap[--heapSize];
			maxHeapify(0);
		}
		return result;
	}

	void insert(T elem){
		incrementSize();
		heap[heapSize - 1] = heap[0];
		heap[0] = elem;
		maxHeapify(0);
	}

	T getMax() {
		return heap[0];
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
			T temp = heap[i];
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
		@SuppressWarnings("unchecked")
		T[] newHeap = (T[]) new Object[heap.length + DEFAULT_SIZE];
		System.arraycopy(heap, 0, newHeap, 0, heap.length);
		heap = newHeap;
	}

}
