package de.age.lists;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.hamcrest.CoreMatchers.sameInstance;
import static org.junit.Assert.assertThat;

import java.util.Iterator;
import java.util.NoSuchElementException;

import org.junit.Test;

public class DoubeLinkDecoratorTest {

	@Test
	public void constructorArgumentGetsReturned() {
		Object expectedObject = new Object();
		DoubleLinkDecorator<Object> dec = new DoubleLinkDecorator<Object>(expectedObject);
		assertThat(dec.getObject(), is(sameInstance(expectedObject)));
	}
	
	@Test
	public void nextAndPreviousAreInitiallyNull() {
		DoubleLinkDecorator<Object> dec = new DoubleLinkDecorator<Object>(new Object());
		assertThat(dec.next(), is(nullValue()));
		assertThat(dec.previous(), is(nullValue()));
	}
	
	@Test
	public void appendSetsLinksCorrectly() {
		DoubleLinkDecorator<String> dec = new DoubleLinkDecorator<>("first");
		dec.append("next");
		assertThat(dec.next(), is(notNullValue()));
		assertThat(dec.next().previous(), is(sameInstance(dec)));
	}
	
	@Test
	public void appendReturnsAppendedElementsDecorator() {
		DoubleLinkDecorator<String> dec = new DoubleLinkDecorator<>("first");
		DoubleLinkDecorator<String> next = dec.append("next");
		assertThat(dec.next(), is(sameInstance(next)));
	}
	
	@Test
	public void appendInsertsAfter() {
		DoubleLinkDecorator<String> dec = new DoubleLinkDecorator<>("first");
		DoubleLinkDecorator<String> last = dec.append("last");
		DoubleLinkDecorator<String> middle = dec.append("middle");
		assertThat(dec.next(), is(sameInstance(middle)));
		assertThat(middle.next(), is(sameInstance(last)));
		assertThat(last.previous(), is(sameInstance(middle)));
	}

	@Test
	public void insertSetsLinksCorrectly() {
		DoubleLinkDecorator<String> dec = new DoubleLinkDecorator<>("last");
		dec.insert("previous");
		assertThat(dec.previous(), is(notNullValue()));
		assertThat(dec.previous().next(), is(sameInstance(dec)));
	}
	
	@Test
	public void insertReturnsInsertedElementsDecorator() {
		DoubleLinkDecorator<String> dec = new DoubleLinkDecorator<>("last");
		DoubleLinkDecorator<String> previous = dec.insert("previous");
		assertThat(dec.previous(), is(sameInstance(previous)));
	}
	
	@Test
	public void insertInsertsBefore() {
		DoubleLinkDecorator<String> last = new DoubleLinkDecorator<>("last");
		DoubleLinkDecorator<String> first = last.insert("first");
		DoubleLinkDecorator<String> middle = last.insert("middle");
		assertThat(last.previous(), is(sameInstance(middle)));
		assertThat(middle.previous(), is(sameInstance(first)));
		assertThat(first.next(), is(sameInstance(middle)));
	}

	@Test
	public void removeJoinsSurroundingElements() {
		DoubleLinkDecorator<String> first = new DoubleLinkDecorator<>("first");
		DoubleLinkDecorator<String> last = first.append("last");
		DoubleLinkDecorator<String> middle = first.append("middle");
		middle.remove();
		assertThat(first.next(), is(sameInstance(last)));
		assertThat(last.previous(), is(sameInstance(first)));
	}
	
	@Test
	public void removeSetsNextAndPreviousToNull() {
		DoubleLinkDecorator<String> first = new DoubleLinkDecorator<>("first");
		first.append("last");
		DoubleLinkDecorator<String> middle = first.append("middle");
		middle.remove();
		assertThat(middle.next(), is(nullValue()));
		assertThat(middle.previous(), is(nullValue()));
	}
	
	@Test
	public void removeReturnsThis() {
		DoubleLinkDecorator<String> first = new DoubleLinkDecorator<>("first");
		first.append("last");
		DoubleLinkDecorator<String> middle = first.append("middle");
		assertThat(middle.remove(), is(sameInstance(middle)));
	}
	
	@Test
	public void headReturnsFirstElement() {
		DoubleLinkDecorator<String> first = new DoubleLinkDecorator<>("first");
		DoubleLinkDecorator<String> last = first.append("last");
		first.append("middle");
		assertThat(last.head(), is(sameInstance(first)));
	}
	
	@Test
	public void tailReturnsLastElement() {
		DoubleLinkDecorator<String> first = new DoubleLinkDecorator<>("first");
		DoubleLinkDecorator<String> last = first.append("last");
		first.append("middle");
		assertThat(first.tail(), is(sameInstance(last)));
	}
	
	@Test
	public void forwardReturnsCorrectIterator() {
		DoubleLinkDecorator<String> first = new DoubleLinkDecorator<>("first");
		DoubleLinkDecorator<String> last = first.append("last");
		DoubleLinkDecorator<String> middle = first.append("middle");
		Iterator<String> iter = first.forward();
		assertThat(iter, is(notNullValue()));
		assertThat(iter.hasNext(), is(true));
		assertThat(iter.next(), is(sameInstance(middle.getObject())));
		assertThat(iter.hasNext(), is(true));
		assertThat(iter.next(), is(sameInstance(last.getObject())));
		assertThat(iter.hasNext(), is(false));
	}

	@Test
	public void backwardReturnsCorrectIterator() {
		DoubleLinkDecorator<String> first = new DoubleLinkDecorator<>("first");
		DoubleLinkDecorator<String> last = first.append("last");
		DoubleLinkDecorator<String> middle = first.append("middle");
		Iterator<String> iter = last.backward();
		assertThat(iter, is(notNullValue()));
		assertThat(iter.hasNext(), is(true));
		assertThat(iter.next(), is(sameInstance(middle.getObject())));
		assertThat(iter.hasNext(), is(true));
		assertThat(iter.next(), is(sameInstance(first.getObject())));
		assertThat(iter.hasNext(), is(false));
	}
	
	@Test(expected=NoSuchElementException.class)
	public void iteratorThrowsCorrectExceptionWhenNoFurtherElements() {
		DoubleLinkDecorator<String> first = new DoubleLinkDecorator<>("first");
		Iterator<String> iter = first.forward();
		assertThat(iter, is(notNullValue()));
		assertThat(iter.hasNext(), is(false));
		iter.next();
	}

}
