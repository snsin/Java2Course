package ru.geekbrains.hw1;

public class Element<E> {
	E element;
	Element(E element) {
		this.element = element;		
	}
	
	public  E getElem() {
		return this.element;
	}

}
