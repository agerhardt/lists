package de.age.lists;

import org.junit.Test;

public class ImmutableListBaseFunctionalityTest {
	
	@Test(expected=UnsupportedOperationException.class)
	public void iteratorIsImmutable() {
		Object theObject = new Object();
		ImmutableList<Object> list = new ImmutableList<>(theObject);
		list.iterator().next();
		list.iterator().remove();
	}

}
