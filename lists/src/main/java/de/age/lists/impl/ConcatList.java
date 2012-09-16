package de.age.lists.impl;

import java.util.Iterator;

import de.age.lists.ImmutableList;

public class ConcatList<E> extends ImmutableList<E> {

	private final ImmutableList<E>[] sublists;
	
	public ConcatList(ImmutableList<E> firstList, ImmutableList<E> secondList) {
		sublists = new ImmutableList[2];
		sublists[0] = firstList;
		sublists[1] = secondList;
	}
	
	@Override
	public Iterator<E> iterator() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int size() {
		// TODO Auto-generated method stub
		return 0;
	}

}
