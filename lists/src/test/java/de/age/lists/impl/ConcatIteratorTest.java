package de.age.lists.impl;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.sameInstance;
import static org.junit.Assert.assertThat;

import java.util.Arrays;
import java.util.Collections;
import java.util.NoSuchElementException;

import org.junit.Test;

public class ConcatIteratorTest {
	
	@Test(expected=IllegalArgumentException.class)
	public void constructorNullArgumentThrowsException() {
		new ConcatIterator<String>((Iterable<String>[]) null);
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void constructorNullIteratorsThrowException() {
		new ConcatIterator<String>((Iterable<String>) null);
	}
	
	@Test
	public void emptyIteratorsDontHaveNextElement() {
		Iterable<Object> iter = Collections.emptyList();
		ConcatIterator<Object> concatIter = new ConcatIterator<Object>(iter);
		assertThat(concatIter.hasNext(), is(false));
	}
	
	@Test
	public void singleElementIterator() {
		Object object = new Object();
		Iterable<Object> iter = Arrays.asList(object);
		ConcatIterator<Object> concatIter = new ConcatIterator<Object>(iter);
		assertThat(concatIter.hasNext(), is(true));
		assertThat(concatIter.next(), is(sameInstance(object)));
	}
	
	@Test(expected=UnsupportedOperationException.class)
	public void removeIsNotSupported() {
		Object object = new Object();
		Iterable<Object> iter = Arrays.asList(object);
		ConcatIterator<Object> concatIter = new ConcatIterator<Object>(iter);
		concatIter.next();
		concatIter.remove();
	}
	
	@Test(expected=NoSuchElementException.class)
	public void nextWithoutFurtherElementThrowsException() {
		Iterable<Object> iter = Collections.emptyList();
		ConcatIterator<Object> concatIter = new ConcatIterator<Object>(iter);
		concatIter.next();
	}
	
	@Test
	public void multipleIterators() {
		Object object1 = new Object();
		Iterable<Object> iter1 = Arrays.asList(object1);
		Object object2 = new Object();
		Iterable<Object> iter2 = Arrays.asList(object2);
		ConcatIterator<Object> concatIter = new ConcatIterator<Object>(iter1, iter2);
		assertThat(concatIter.next(), is(sameInstance(object1)));
		assertThat(concatIter.next(), is(sameInstance(object2)));
		assertThat(concatIter.hasNext(), is(false));
	}
}
