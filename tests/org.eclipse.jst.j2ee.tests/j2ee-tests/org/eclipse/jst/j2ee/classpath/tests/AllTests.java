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
        suite.addTest(ClasspathDependencyEARTests.suite());
        suite.addTest(ClasspathDependencyWebTests.suite());
        
        return suite;
    }

}
