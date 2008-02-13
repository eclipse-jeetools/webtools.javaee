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

import org.eclipse.wst.common.tests.OperationTestCase;
import org.eclipse.wtp.j2ee.headless.tests.ear.operations.EARProjectCreationOperationTest;

import junit.framework.Test;
import junit.framework.TestSuite;

/**
 * @author Ian Tewksbury (ictewksb@us.ibm.com)
 */
public class JEEProjectCreationOperationTest extends OperationTestCase {

	public JEEProjectCreationOperationTest() {
		super("JEEProjectCreationOperationTests");
	}
		
	public JEEProjectCreationOperationTest(String name) {
		super(name);
	}
	
	public static Test suite() {
        TestSuite suite = new TestSuite("All JEE Project Creation Operation Tests");
        suite.addTest(ModuleProjectCreationOperationTest.suite());
        suite.addTest(EARProjectCreationOperationTest.suite());

        return suite;
    }
}
