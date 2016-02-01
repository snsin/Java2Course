package ru.geekbrains.hw1;

import java.util.Collection;

public class GenericStack<E> implements Stack<E> {
	private static final int DEFAULT_STACK_SIZE = 16;
	private E[] stack;
	private int topOfStack = 0;
	
	@SuppressWarnings("unchecked")
	GenericStack(){
		this.stack = (E[]) new Object[DEFAULT_STACK_SIZE];
	}
	@SuppressWarnings("unchecked")
	GenericStack(int size){
		this.stack = (E[]) new Object[size];
	}

	@Override
	public void push(E element) throws StackException {
		if (topOfStack < stack.length) {
			stack[topOfStack++] = element;
		} else {
			throw new StackException("Stack is full");
		}
		
	}

	@Override
	public E pop() throws StackException {
		if (topOfStack < 1) {
			throw new StackException("Stack is empty");
		} else { 
			return stack[--topOfStack];
		}
	}

	@Override
	public E peek() {
		return stack[topOfStack - 1];
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
