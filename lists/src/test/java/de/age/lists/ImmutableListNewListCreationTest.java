package de.age.lists;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.CoreMatchers.sameInstance;
import static org.junit.Assert.assertThat;

import org.junit.Ignore;
import org.junit.Test;

public class ImmutableListNewListCreationTest {

	@Test
	public void concatSingleElement() {
		ImmutableList<Object> list = ImmutableList.createList();
		Object object = new Object();
		ImmutableList<Object> resultingList = list.append(object);
		assertThat(resultingList, is(notNullValue()));
		assertThat(resultingList.size(), is(1));
		assertThat(resultingList.iterator().next(), is(sameInstance(object)));
	}
	
	@Test
	@Ignore
	public void concatTwoEmptyLists() {
		ImmutableList<Object> list1 = ImmutableList.createList();
		ImmutableList<Object> list2 = ImmutableList.createList();
		ImmutableList<Object> resultingList = list1.append(list2);
		assertThat(resultingList, is(notNullValue()));
		assertThat(resultingList.size(), is(0));
	}
	
	@Test
	public void wholeRangeReturnsSameInstance() {
		ImmutableList<Object> list = ImmutableList.createList(new Object());
		ImmutableList<Object> returnedList = list.range(0, 1);
		assertThat(returnedList, is(sameInstance(list)));
	}
	
	@Test
	public void emptyRange() {
		ImmutableList<Object> list = ImmutableList.createList(new Object());
		ImmutableList<Object> returnedList = list.range(0, 0);
		assertThat(returnedList, is(notNullValue()));
		assertThat(returnedList.size(), is(0));
	}
	
	@Test
	public void simpleRange() {
		Object object1 = new Object();
		Object object2 = new Object();
		ImmutableList<Object> list = ImmutableList.createList(object1, object2);
		ImmutableList<Object> returnedList = list.range(1, 2);
		assertThat(returnedList, is(notNullValue()));
		assertThat(returnedList.size(), is(1));
		assertThat(returnedList.iterator().next(), is(sameInstance(object2)));
	}
}
