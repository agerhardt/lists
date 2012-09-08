package de.age.lists;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import de.age.lists.impl.RangeIteratorTest;

@RunWith(Suite.class)
@SuiteClasses({ ImmutableListConstructorTest.class, ImmutableListBaseFunctionalityTest.class, ImmutableListNewListCreationTest.class, RangeIteratorTest.class })
public class AllTests {

}
