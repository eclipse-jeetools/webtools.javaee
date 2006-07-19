package org.eclipse.jst.j2ee.dependency.tests;

import junit.framework.Test;
import junit.framework.TestSuite;

/**
 * Tests refactoring logic that updates server instances when associated modules are
 * renamed or deleted. 
 */
public class ProjectServerRefactoringTests extends AbstractTests {
	
	public ProjectServerRefactoringTests(String name) {
		super(name);
	}
	
	public static Test suite(){
        final TestSuite suite = new TestSuite();
        suite.setName("Project Server Refactoring Tests" );
        //suite.addTest(new ProjectServerRefactoringTests("testEARWebDependency"));
        return suite;
    }

}
