package de.age.lists.impl;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.sameInstance;
import static org.junit.Assert.assertThat;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;

import org.junit.Test;

public class RangeIteratorTest {

	@Test
	public void emptyRange() {
		List<Object> list = new ArrayList<>();
		list.add(new Object());
		list.add(new Object());
		Iterator<Object> iterator = new RangeIterator<>(list, 0, 0);
		assertThat(iterator.hasNext(), is(false));
	}
	
	@Test
	public void wholeRange() {
		List<Object> list = new ArrayList<>();
		Object object1 = new Object();
		Object object2 = new Object();
		list.add(object1);
		list.add(object2);
		Iterator<Object> iterator = new RangeIterator<>(list, 0, 2);
		assertThat(iterator.hasNext(), is(true));
		assertThat(iterator.next(), is(sameInstance(object1)));
		assertThat(iterator.hasNext(), is(true));
		assertThat(iterator.next(), is(sameInstance(object2)));
		assertThat(iterator.hasNext(), is(false));
	}
	
	@Test
	public void limitedRange() {
		List<Object> list = new ArrayList<>();
		Object object1 = new Object();
		Object object2 = new Object();
		list.add(object1);
		list.add(object2);
		Iterator<Object> iterator = new RangeIterator<>(list, 0, 1);
		assertThat(iterator.hasNext(), is(true));
		assertThat(iterator.next(), is(sameInstance(object1)));
		assertThat(iterator.hasNext(), is(false));
	}
	
	@Test(expected=NoSuchElementException.class)
	public void throwsNoSuchElementExceptionWhenNoNext() {
		List<Object> list = new ArrayList<>();
		Iterator<Object> iterator = new RangeIterator<>(list, 0, 0);
		iterator.next();
	}
	
}