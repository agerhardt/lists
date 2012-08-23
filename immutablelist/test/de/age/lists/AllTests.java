package de.age.lists;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@SuiteClasses({ ImmutableListConstructorTest.class, ImmutableListBaseFunctionalityTest.class, ImmutableListNewListCreationTest.class})
public class AllTests {

}
