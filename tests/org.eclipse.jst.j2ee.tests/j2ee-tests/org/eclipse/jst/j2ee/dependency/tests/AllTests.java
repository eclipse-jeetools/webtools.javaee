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
import junit.framework.TestCase;
import junit.framework.TestSuite;

public class AllTests extends TestCase {
    public static Test suite() {
        final TestSuite suite = new TestSuite();
        
        suite.setName("All J2EE Dependency/Refactoring Tests");
        
        suite.addTest(ProjectCreationTests.suite());
        suite.addTest(ProjectDependencyTests.suite());
        suite.addTest(ProjectEARRefactoringTests.suite());
        //[Bug 234409] Temporarily removing these tests until underlying issue is fixed
//        suite.addTest(ProjectModuleRefactoringTests.suite());
//        suite.addTest(ProjectWebLibRefactoringTests.suite());
        suite.addTest(ProjectClasspathRefactoringTests.suite());
        suite.addTest(ProjectServerRefactoringTests.suite());
        suite.addTest(EarLibDirContainerTests.suite());
        
        return suite;
    }

}
