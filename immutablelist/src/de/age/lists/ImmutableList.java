package de.age.lists;

import java.util.AbstractList;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

public class ImmutableList<E> implements Iterable<E> {

	private final List<E> elements;
	
	public ImmutableList(List<E> elements) {
		if (elements == null) {
			throw new NullPointerException();
		}
		List<E> temp = new ArrayList<>();
		temp.addAll(elements);
		this.elements = Collections.unmodifiableList(temp);
	}
	
	@SafeVarargs
	public ImmutableList(E ... elements) {
		this(wrap(elements));
	}
	
	public ImmutableList<E> range(int start, int end) {
		// TODO Auto-generated method stub
		return null;
	}

	public ImmutableList<E> append(E newElement) {
		// TODO Auto-generated method stub
		return null;
	}

	public ImmutableList<E> append(ImmutableList<E> newElements) {
		// TODO Auto-generated method stub
		return null;
	}
	
	public ImmutableList<E> prepend(E newElement) {
		// TODO Auto-generated method stub
		return null;
	}
	
	public ImmutableList<E> prepend(ImmutableList<E> newElements) {
		// TODO Auto-generated method stub
		return null;
	}
	
	public int size() {
		return elements.size();
	}

	@Override
	public Iterator<E> iterator() {
		return elements.iterator();
	}
	
	private static <E> List<E> wrap(E[] array) {
		if (array == null) {
			return null;
		} else {
			return new ArrayWrapper<>(array);
		}
	}
	
	private static class ArrayWrapper<E> extends AbstractList<E> {

		private final E[] wrappedArray;
		
		public ArrayWrapper(E[] wrappedArray) {
			super();
			this.wrappedArray = wrappedArray;
		}

		@Override
		public E get(int index) {
			return wrappedArray[index];
		}

		@Override
		public int size() {
			return wrappedArray.length;
		}
		
	}

}
