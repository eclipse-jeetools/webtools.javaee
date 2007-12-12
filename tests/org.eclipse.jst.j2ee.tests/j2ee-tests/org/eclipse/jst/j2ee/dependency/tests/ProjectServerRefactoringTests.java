/*******************************************************************************
 * Copyright (c) 2007 BEA Systems, Inc and others
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    BEA Systems, Inc - initial API and implementation
 *******************************************************************************/
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
