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
		assertThat(dec.get(), is(sameInstance(expectedObject)));
	}
	
	@Test
	public void nextAndPreviousAreInitiallyNull() {
		DoubleLinkDecorator<Object> dec = new DoubleLinkDecorator<Object>(new Object());
		assertThat(dec.next(), is(nullValue()));
		assertThat(dec.previous(), is(nullValue()));
	}
	
	@Test
	public void insertAfterSetsLinksCorrectly() {
		DoubleLinkDecorator<String> dec = new DoubleLinkDecorator<>("first");
		dec.insertAfter("next");
		assertThat(dec.next(), is(notNullValue()));
		assertThat(dec.next().previous(), is(sameInstance(dec)));
	}
	
	@Test
	public void insertAfterReturnsInsertedElementsDecorator() {
		DoubleLinkDecorator<String> dec = new DoubleLinkDecorator<>("first");
		DoubleLinkDecorator<String> next = dec.insertAfter("next");
		assertThat(dec.next(), is(sameInstance(next)));
	}
	
	@Test
	public void insertAfterPlacesElementCorrectly() {
		DoubleLinkDecorator<String> dec = new DoubleLinkDecorator<>("first");
		DoubleLinkDecorator<String> last = dec.insertAfter("last");
		DoubleLinkDecorator<String> middle = dec.insertAfter("middle");
		assertThat(dec.next(), is(sameInstance(middle)));
		assertThat(middle.next(), is(sameInstance(last)));
		assertThat(last.previous(), is(sameInstance(middle)));
	}

	@Test
	public void insertBeforeSetsLinksCorrectly() {
		DoubleLinkDecorator<String> dec = new DoubleLinkDecorator<>("last");
		dec.insertBefore("previous");
		assertThat(dec.previous(), is(notNullValue()));
		assertThat(dec.previous().next(), is(sameInstance(dec)));
	}
	
	@Test
	public void insertBeforeReturnsInsertedElementsDecorator() {
		DoubleLinkDecorator<String> dec = new DoubleLinkDecorator<>("last");
		DoubleLinkDecorator<String> previous = dec.insertBefore("previous");
		assertThat(dec.previous(), is(sameInstance(previous)));
	}
	
	@Test
	public void insertBeforeWorksCorrectly() {
		DoubleLinkDecorator<String> last = new DoubleLinkDecorator<>("last");
		DoubleLinkDecorator<String> first = last.insertBefore("first");
		DoubleLinkDecorator<String> middle = last.insertBefore("middle");
		assertThat(last.previous(), is(sameInstance(middle)));
		assertThat(middle.previous(), is(sameInstance(first)));
		assertThat(first.next(), is(sameInstance(middle)));
	}

	@Test
	public void removeJoinsSurroundingElements() {
		DoubleLinkDecorator<String> first = new DoubleLinkDecorator<>("first");
		DoubleLinkDecorator<String> last = first.insertAfter("last");
		DoubleLinkDecorator<String> middle = first.insertAfter("middle");
		middle.remove();
		assertThat(first.next(), is(sameInstance(last)));
		assertThat(last.previous(), is(sameInstance(first)));
	}
	
	@Test
	public void removeSetsNextAndPreviousToNull() {
		DoubleLinkDecorator<String> first = new DoubleLinkDecorator<>("first");
		first.insertAfter("last");
		DoubleLinkDecorator<String> middle = first.insertAfter("middle");
		middle.remove();
		assertThat(middle.next(), is(nullValue()));
		assertThat(middle.previous(), is(nullValue()));
	}
	
	@Test
	public void removeReturnsThis() {
		DoubleLinkDecorator<String> first = new DoubleLinkDecorator<>("first");
		first.insertAfter("last");
		DoubleLinkDecorator<String> middle = first.insertAfter("middle");
		assertThat(middle.remove(), is(sameInstance(middle)));
	}
	
	@Test
	public void headReturnsFirstElement() {
		DoubleLinkDecorator<String> first = new DoubleLinkDecorator<>("first");
		DoubleLinkDecorator<String> last = first.insertAfter("last");
		first.insertAfter("middle");
		assertThat(last.head(), is(sameInstance(first)));
	}
	
	@Test
	public void tailReturnsLastElement() {
		DoubleLinkDecorator<String> first = new DoubleLinkDecorator<>("first");
		DoubleLinkDecorator<String> last = first.insertAfter("last");
		first.insertAfter("middle");
		assertThat(first.tail(), is(sameInstance(last)));
	}
	
	@Test
	public void forwardReturnsCorrectIterator() {
		DoubleLinkDecorator<String> first = new DoubleLinkDecorator<>("first");
		DoubleLinkDecorator<String> last = first.insertAfter("last");
		DoubleLinkDecorator<String> middle = first.insertAfter("middle");
		Iterator<String> iter = first.forward();
		assertThat(iter, is(notNullValue()));
		assertThat(iter.hasNext(), is(true));
		assertThat(iter.next(), is(sameInstance(middle.get())));
		assertThat(iter.hasNext(), is(true));
		assertThat(iter.next(), is(sameInstance(last.get())));
		assertThat(iter.hasNext(), is(false));
	}

	@Test
	public void backwardReturnsCorrectIterator() {
		DoubleLinkDecorator<String> first = new DoubleLinkDecorator<>("first");
		DoubleLinkDecorator<String> last = first.insertAfter("last");
		DoubleLinkDecorator<String> middle = first.insertAfter("middle");
		Iterator<String> iter = last.backward();
		assertThat(iter, is(notNullValue()));
		assertThat(iter.hasNext(), is(true));
		assertThat(iter.next(), is(sameInstance(middle.get())));
		assertThat(iter.hasNext(), is(true));
		assertThat(iter.next(), is(sameInstance(first.get())));
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
	
	@Test
	public void findUnknownElementReturnsNull() {
		DoubleLinkDecorator<String> first = new DoubleLinkDecorator<>("first");
		assertThat(first.find("unknown"), is(nullValue()));
	}
	
	@Test
	public void findReturnsElementOnSameNode() {
		DoubleLinkDecorator<String> first = new DoubleLinkDecorator<>("first");
		assertThat(first.find("first"), is(sameInstance(first)));
	}
	
	@Test
	public void findReturnsElementAfterCurrentNode() {
		DoubleLinkDecorator<String> first = new DoubleLinkDecorator<>("first");
		DoubleLinkDecorator<String> last = first.insertAfter("last");
		assertThat(first.find("last"), is(sameInstance(last)));
	}
	
	@Test
	public void findReturnsElementBeforeCurrentNode() {
		DoubleLinkDecorator<String> first = new DoubleLinkDecorator<>("first");
		DoubleLinkDecorator<String> last = first.insertAfter("last");
		assertThat(last.find("first"), is(sameInstance(first)));
	}

}
