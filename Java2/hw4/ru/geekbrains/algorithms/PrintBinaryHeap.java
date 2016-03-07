package ru.geekbrains.algorithms;

public class PrintBinaryHeap<T extends Heap> {
	int firstIndent;
	
	public PrintBinaryHeap(T heap) {
		int heigth = (int) (Math.log(heap.getSize())/Math.log(Math.E));
		firstIndent = heigth * 3 + heap.getMax().toString().length();
	}
	public void print(T heap) {

	}

}
