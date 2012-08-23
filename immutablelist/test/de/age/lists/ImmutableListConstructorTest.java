package de.age.lists;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.CoreMatchers.sameInstance;
import static org.junit.Assert.assertThat;

import java.util.List;

import org.junit.Test;

public class ImmutableListConstructorTest {

	@Test
	public void canConstructWithNoElements() {
		ImmutableList<Object> list = ImmutableList.createList();
		assertThat(list, is(notNullValue()));
		assertThat(list.size(), is(0));
	}
	
	@Test
	public void constructorWithOneElement() {
		Object theObject = new Object();
		ImmutableList<Object> list = ImmutableList.createList(theObject);
		assertThat(list, is(notNullValue()));
		assertThat(list.size(), is(1));
		assertThat(list.iterator().next(), is(sameInstance(theObject)));
	}

	@Test(expected=NullPointerException.class)
	public void constructorWithNullListThrowsException() {
		ImmutableList.createList((List<Object>) null);
	}
}
