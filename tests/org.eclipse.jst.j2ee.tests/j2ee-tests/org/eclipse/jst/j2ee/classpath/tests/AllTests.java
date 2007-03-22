package org.eclipse.jst.j2ee.classpath.tests;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

public class AllTests extends TestCase {
    public static Test suite() {
        final TestSuite suite = new TestSuite();
        
        suite.setName("All Classpath Dependency Tests");
        suite.addTest(ClasspathDependencyCreationTests.suite());
        //suite.addTest(ClasspathDependencyValidationTests.suite());
        //suite.addTest(ClasspathDependencyEARTests.suite());
        suite.addTest(ClasspathDependencyWebTests.suite());
        
        return suite;
    }

}
