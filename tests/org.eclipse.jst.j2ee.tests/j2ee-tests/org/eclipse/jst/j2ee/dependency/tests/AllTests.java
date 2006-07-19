package org.eclipse.jst.j2ee.dependency.tests;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

public class AllTests extends TestCase {
    public static Test suite() {
        final TestSuite suite = new TestSuite();
        
        suite.setName("All J2EE Dependency/Refactoring Tests");
        
        suite.addTest(ProjectCreationTests.suite());
        suite.addTest(ProjectDependencyTests.suite());
        suite.addTest(ProjectEARRefactoringTests.suite());
        suite.addTest(ProjectModuleRefactoringTests.suite());
        suite.addTest(ProjectWebLibRefactoringTests.suite());
        suite.addTest(ProjectClasspathRefactoringTests.suite());
        suite.addTest(ProjectServerRefactoringTests.suite());
        
        return suite;
    }

}
