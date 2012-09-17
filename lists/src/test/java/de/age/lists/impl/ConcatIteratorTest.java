package de.age.lists.impl;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import java.util.Collections;
import java.util.Iterator;

import org.junit.Test;

public class ConcatIteratorTest {
	
	@Test(expected=IllegalArgumentException.class)
	public void constructorNullArgumentThrowsException() {
		new ConcatIterator<String>((Iterator<String>[]) null);
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void constructorNullIteratorsThrowException() {
		new ConcatIterator<String>((Iterator<String>) null);
	}
	
	@Test
	public void emptyIteratorsDontHaveNextElement() {
		Iterator<String> iter = Collections.emptyIterator();
		ConcatIterator<String> concatIter = new ConcatIterator<String>(iter);
		assertThat(concatIter.hasNext(), is(false));
	}
}
