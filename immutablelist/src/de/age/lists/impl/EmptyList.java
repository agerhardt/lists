package de.age.lists.impl;

import java.util.Collections;
import java.util.Iterator;

import de.age.lists.ImmutableList;

public class EmptyList<E> extends ImmutableList<E> {
	
	public EmptyList() {
	}

	@Override
	public Iterator<E> iterator() {
		return Collections.emptyIterator();
	}

	@Override
	public int size() {
		return 0;
	}

}
