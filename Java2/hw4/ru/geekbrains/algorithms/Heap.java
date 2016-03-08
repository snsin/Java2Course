package ru.geekbrains.algorithms;

public class Heap<T extends Comparable<T>> {
	public static final int DEFAULT_SIZE = 16;
	private int heapSize = 0;
	private T[] heap;
	
	public Heap() {
		heap = (T[]) createArray(DEFAULT_SIZE);
	}
	
	public Heap(int size) {
		size = size < DEFAULT_SIZE ? DEFAULT_SIZE : size;
		heap = (T[]) createArray(size);
	}
	
	public Heap(T[] arr) {
		heap = arr;
		heapSize = arr.length;
		for (int i = ((heapSize - 1) / 2); i >= 0; i--) {
			maxHeapify(i);
		}
		
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
		int i = heapSize;
		incrementSize();
		heap[i] = elem;
		while ((i > 0) && (heap[parent(i)].compareTo(heap[i]) < 0)) {
			swap(parent(i), i);
			i = parent(i);
		}
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
		T[] newHeap = createArray(heap.length + DEFAULT_SIZE);
		System.arraycopy(heap, 0, newHeap, 0, heap.length);
		heap = newHeap;
	}
	
	@SuppressWarnings("unchecked")
	private T[] createArray(int size){
		return (T[]) new Integer[size];
	}
	
	//TODO remove its only to debug
	T[] getAsAray() {
		return heap;
	}

}
