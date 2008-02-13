/*******************************************************************************
 * Copyright (c) 2005, 2006 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *******************************************************************************/
package org.eclipse.wtp.j2ee.headless.tests.j2ee.operations;

import junit.framework.Test;
import junit.framework.TestSuite;

import org.eclipse.wtp.j2ee.headless.tests.appclient.operations.AppClientProjectCreationOperationTest;
import org.eclipse.wtp.j2ee.headless.tests.ejb.operations.EJBProjectCreationOperationTest;
import org.eclipse.wtp.j2ee.headless.tests.jca.operations.JCAProjectCreationOperationTest;
import org.eclipse.wtp.j2ee.headless.tests.utility.operations.UtilityProjectCreationOperationTest;
import org.eclipse.wtp.j2ee.headless.tests.web.operations.WebProjectCreationOperationTest;

public abstract class ModuleProjectCreationOperationTest extends JEEProjectCreationOperationTest {
   
	public ModuleProjectCreationOperationTest() {
	   super("ModuleProjectCreationOperationTests");
   }
	
	public ModuleProjectCreationOperationTest(String name) {
		super(name);
	}

	public static Test suite() {
        TestSuite suite = new TestSuite("All Module Project Creation Operation Tests");
        suite.addTestSuite(AppClientProjectCreationOperationTest.class);
        suite.addTestSuite(EJBProjectCreationOperationTest.class);
        suite.addTestSuite(WebProjectCreationOperationTest.class); 
        suite.addTestSuite(JCAProjectCreationOperationTest.class);
        suite.addTestSuite(UtilityProjectCreationOperationTest.class);
        return suite;
    }
}
