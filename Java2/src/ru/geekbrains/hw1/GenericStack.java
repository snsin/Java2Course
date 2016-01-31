package ru.geekbrains.hw1;

import java.util.Collection;

public class GenericStack<E> implements Stack<E> {
	private static final int DEFAULT_STACK_SIZE = 16;
	private Element<?>[] stack;
	private int topOfStack = 0;
	
	GenericStack(){
		this.stack = new Element<?>[DEFAULT_STACK_SIZE];
	}
	GenericStack(int size){
		this.stack = new Element<?>[size];
	}

	@Override
	public void push(E element) throws StackException {
		if (topOfStack < stack.length) {
			stack[topOfStack++] = new Element<E>(element);
		} else {
			throw new StackException();
		}
		
	}

	@SuppressWarnings("unchecked")
	@Override
	public E pop() throws StackException {
		if (topOfStack < 0) {
			throw new StackException();
		} else { 
			return (E) stack[--topOfStack].getElem();
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public E peek() {
		return (E) stack[topOfStack - 1].getElem();
	}

	@Override
	public int getSize() {
		return topOfStack;
	}

	@Override
	public boolean isEmpty() {
		return topOfStack == 0;
	}

	@Override
	public boolean isFull() {
		return topOfStack == stack.length;
	}

	@Override
	public void pushAll(Collection<? extends E> src) throws StackException {
		for (E elem : src) {
			push(elem);
		}	
	}

	@Override
	public void popAll(Collection<? super E> dst) throws StackException {
		int counter = dst.size();
		while (counter-- > 0){
			dst.add(pop());
		}
	}

}
