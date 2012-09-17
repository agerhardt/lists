package de.age.lists.impl;

import java.util.Collections;
import java.util.Iterator;

import de.age.lists.DoubleLinkDecorator;

public class ConcatIterator<E> implements Iterator<E> {

	private DoubleLinkDecorator<Iterator<E>> currentIterator;

	@SafeVarargs
	public ConcatIterator(Iterator<E> ... iterators) {
		if (iterators == null) {
			throw new IllegalArgumentException();
		}
		Iterator<E> start = Collections.emptyIterator();
		currentIterator = new DoubleLinkDecorator<>(start);
		for (Iterator<E> iter : iterators) {
			if (iter == null) {
				throw new IllegalArgumentException();
			}
			if (iter.hasNext()) {
				currentIterator = currentIterator.append(iter);
			}
		}
		currentIterator = currentIterator.head();
	}

	@Override
	public boolean hasNext() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public E next() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void remove() {
		// TODO Auto-generated method stub
		
	}
	
}
