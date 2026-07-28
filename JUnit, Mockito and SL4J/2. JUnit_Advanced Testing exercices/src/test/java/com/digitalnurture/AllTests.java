package com.digitalnurture;

import org.junit.platform.suite.api.SelectClasses;
import org.junit.platform.suite.api.Suite;
import org.junit.platform.suite.api.SuiteDisplayName;

/**
 * Exercise 2: A test suite that groups related test classes and runs them
 * together via @Suite + @SelectClasses.
 */
@Suite
@SuiteDisplayName("All Advanced JUnit Tests")
@SelectClasses({
        EvenCheckerTest.class,
        ExceptionThrowerTest.class,
        OrderedTests.class,
        PerformanceTesterTest.class
})
public class AllTests {
    // No body — the annotations drive the suite.
}
