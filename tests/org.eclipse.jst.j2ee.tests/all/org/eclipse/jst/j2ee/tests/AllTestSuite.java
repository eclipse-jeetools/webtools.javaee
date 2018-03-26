/*******************************************************************************
 * Copyright (c) 2018 Red Hat Inc and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 *******************************************************************************/
package org.eclipse.jst.j2ee.tests;

import junit.framework.Test;
import junit.framework.TestSuite;

public class AllTestSuite extends TestSuite {
	/**
	 * A system property to indicate whether we should run the long-running tests or not
	 */
	public static final String PROP_LONG_RUNNING = "org.eclipse.jst.j2ee.tests.LONG_RUNNING";
	public static Test suite() {
		return new AllTestSuite();
	}

	public AllTestSuite() {
		super("All Tests");
		addTest(new QuickSuite());
		addTest(new LongRunningSuite());
	}

}
