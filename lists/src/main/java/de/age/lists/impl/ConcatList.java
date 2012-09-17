package de.age.lists.impl;

import java.util.Iterator;

import de.age.lists.ImmutableList;

public class ConcatList<E> extends ImmutableList<E> {

	private final ImmutableList<E> firstList;
	private final ImmutableList<E> secondList;
	
	public ConcatList(ImmutableList<E> firstList, ImmutableList<E> secondList) {
		this.firstList = firstList;
		this.secondList = secondList;
	}
	
	@Override
	public Iterator<E> iterator() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int size() {
		return firstList.size() + secondList.size();
	}

}
