package ru.geekbrains.algorithms;

public class PrintBinaryHeap {
	int firstIndent;
	
	public <T extends Heap> PrintBinaryHeap(T heap) {
		int heigth = (int) (Math.log(heap.getSize())/Math.log(Math.E));
		firstIndent = heigth * 3 + heap.getMax().toString().length();
	}
	public <T> void printElem(T heapElem) {
		System.out.println(heapElem);
		System.out.println();
		System.out.printf("%s", "a");
	}
	
	private int left(int i) {
		return 2 * i + 1;
	}

	private int rigth(int i) {
		return 2 * i + 2;
	}

}
