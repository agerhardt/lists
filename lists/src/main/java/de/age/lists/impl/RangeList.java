package de.age.lists.impl;

import java.util.Iterator;

import de.age.lists.ImmutableList;

public class RangeList<E> extends ImmutableList<E> {
	
	private final ImmutableList<E> underlyingList;
	private final int startIndex;
	private final int endIndex;

	public RangeList(ImmutableList<E> underlyingList, int startIndex,
			int endIndex) {
		super();
		this.underlyingList = underlyingList;
		this.startIndex = startIndex;
		this.endIndex = endIndex;
	}

	@Override
	public Iterator<E> iterator() {
		return new RangeIterator<>(underlyingList, startIndex, endIndex);
	}

	@Override
	public int size() {
		return endIndex - startIndex;
	}

}
