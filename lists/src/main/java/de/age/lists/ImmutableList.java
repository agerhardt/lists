package de.age.lists;

import java.util.AbstractList;
import java.util.List;

import de.age.lists.impl.ConcatList;
import de.age.lists.impl.EmptyList;
import de.age.lists.impl.RangeList;
import de.age.lists.impl.WholeContentList;

public abstract class ImmutableList<E> implements Iterable<E> {

	public static <E> ImmutableList<E> createList(List<E> elements) {
		return new WholeContentList<>(elements);
	}
	
	@SafeVarargs
	public static <E> ImmutableList<E> createList(E ... elements) {
		return createList(wrap(elements));
	}
	
	public ImmutableList<E> range(int start, int end) {
		if (start == 0 && end == size()) {
			return this;
		} else if (end - start == 0) {
			return new EmptyList<E>();
		}
		return new RangeList<>(this, start, end);
	}

	public ImmutableList<E> append(E newElement) {
		return new ConcatList<>(this, createList(newElement));
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
	
	public abstract int size();
	
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
