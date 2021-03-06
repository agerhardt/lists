package de.age.lists.impl;

import java.util.Collections;
import java.util.Iterator;

import de.age.lists.DoubleLinkDecorator;

public class ConcatIterator<E> implements Iterator<E> {

	private DoubleLinkDecorator<Iterator<E>> currentIterator;

	@SafeVarargs
	public ConcatIterator(Iterable<E> ... iterables) {
		if (iterables == null) {
			throw new IllegalArgumentException();
		}
		Iterator<E> start = Collections.emptyIterator();
		currentIterator = new DoubleLinkDecorator<>(start);
		for (Iterable<E> iter : iterables) {
			if (iter == null) {
				throw new IllegalArgumentException();
			}
			currentIterator = currentIterator.insertAfter(iter.iterator());
		}
		currentIterator = currentIterator.head();
	}

	@Override
	public boolean hasNext() {
		nextNonEmptyIterator();
		return currentIterator.get().hasNext();
	}

	private void nextNonEmptyIterator() {
		while (!currentIterator.get().hasNext() && currentIterator.next() != null) {
			currentIterator = currentIterator.next();
		}
	}

	@Override
	public E next() {
		nextNonEmptyIterator();
		return currentIterator.get().next();
	}

	@Override
	public void remove() {
		throw new UnsupportedOperationException();
	}
	
}
