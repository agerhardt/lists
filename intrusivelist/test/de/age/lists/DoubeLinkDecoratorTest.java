package de.age.lists;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.hamcrest.CoreMatchers.sameInstance;
import static org.junit.Assert.assertThat;

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
	
}
