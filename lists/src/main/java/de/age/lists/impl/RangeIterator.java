package de.age.lists.impl;

import java.util.Iterator;
import java.util.NoSuchElementException;

class RangeIterator<E> implements Iterator<E> {

	private final Iterator<E> baseIterator;
	private final int startIndex;
	private final int endIndex;
	
	private int currentIndex = 0;
	
	public RangeIterator(Iterable<E> iterable, int startIndex, int endIndex) {
		super();
		this.baseIterator = iterable.iterator();
		this.startIndex = startIndex;
		this.endIndex = endIndex;
	}

	@Override
	public boolean hasNext() {
		return currentIndex + startIndex < endIndex;
	}

	@Override
	public E next() {
		if (!hasNext()) {
			throw new NoSuchElementException();
		}
		if (currentIndex == 0) {
			for (int i = 0; i < startIndex; i++) {
				baseIterator.next();
			}
		}
		currentIndex++;
		return baseIterator.next();
	}

	@Override
	public void remove() {
		throw new UnsupportedOperationException();
	}

}
