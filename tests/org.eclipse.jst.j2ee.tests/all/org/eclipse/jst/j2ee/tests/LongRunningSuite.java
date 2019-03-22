/*******************************************************************************
 * Copyright (c) 2018 Red Hat Inc and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 * 
 *******************************************************************************/
package org.eclipse.jst.j2ee.tests;

import org.eclipse.jst.jee.model.tests.ModelSuite;

import junit.framework.Test;
import junit.framework.TestSuite;

public class LongRunningSuite extends TestSuite {
	public static Test suite() {
		return new LongRunningSuite();
	}

	public LongRunningSuite() {
		super("Long-Running Tests");
		addTest(org.eclipse.jst.j2ee.dependency.tests.AllTests.suite()); 
		addTest(ModelSuite.suite());  // Tests run: 42, Failures: 2, Errors: 3,   6 minutes
		addTest(org.eclipse.wtp.j2ee.headless.tests.appclient.operations.AllTests.suite()); // run: 85, success, < 7 min
		addTest(org.eclipse.wtp.j2ee.headless.tests.ear.operations.AllTests.suite()); // Total time: 19:02 min
		addTest(org.eclipse.wtp.j2ee.headless.tests.ejb.operations.AllTests.suite());
		addTest(org.eclipse.wtp.j2ee.headless.tests.jca.operations.AllTests.suite());
		addTest(org.eclipse.wtp.j2ee.headless.tests.web.operations.AllTests.suite());
		addTest(org.eclipse.wtp.j2ee.headless.tests.utility.operations.AllTests.suite());
	}

}
