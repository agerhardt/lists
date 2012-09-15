package de.age.lists.impl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

import de.age.lists.ImmutableList;

public class WholeContentList<E> extends ImmutableList<E> {

	private final List<E> elements;
	
	public WholeContentList(List<E> elements) {
		if (elements == null) {
			throw new NullPointerException();
		}
		List<E> temp = new ArrayList<>();
		temp.addAll(elements);
		this.elements = Collections.unmodifiableList(temp);
	}

	@Override
	public Iterator<E> iterator() {
		return elements.iterator();
	}

	@Override
	public int size() {
		return elements.size();
	}
	
}
