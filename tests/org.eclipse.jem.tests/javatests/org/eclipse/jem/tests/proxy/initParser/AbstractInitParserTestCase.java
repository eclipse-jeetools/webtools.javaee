package org.eclipse.jem.tests.proxy.initParser;
/*******************************************************************************
 * Copyright (c)  2003 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials 
 * are made available under the terms of the Common Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/cpl-v10.html
 * 
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *******************************************************************************/
/*
 *  $RCSfile: AbstractInitParserTestCase.java,v $
 *  $Revision: 1.3 $  $Date: 2004/01/19 22:50:22 $ 
 */
import java.util.Enumeration;

import junit.framework.*;
import junit.framework.TestCase;
import junit.framework.TestSuite;

/**
 * @author richkulp
 *
 * To change the template for this generated type comment go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
public abstract class AbstractInitParserTestCase extends TestCase {

	/**
	 * Initialize the test helper for all of the tests within the given suite.
	 * 
	 * @param suite
	 * @param testHelper
	 * 
	 * @since 1.0.0
	 */
	public static void initSuite(TestSuite suite, AbstractInitStringParserTestHelper testHelper) {
		Enumeration tests = suite.tests();
		while (tests.hasMoreElements()) {
			Test test = (Test) tests.nextElement();
			if (test instanceof AbstractInitParserTestCase)
				((AbstractInitParserTestCase) test).setTestHelper(testHelper);
			else if (test instanceof TestSuite)
				initSuite((TestSuite) test, testHelper);
		}
	}
	
	public AbstractInitParserTestCase() {
		super();
	}

	public AbstractInitParserTestCase(String name) {
		super(name);
	}

	protected AbstractInitStringParserTestHelper testHelper;
	
	/**
	 * Set the test helper to use.
	 * 
	 * @param testHelper
	 * 
	 * @since 1.0.0
	 */
	public void setTestHelper(AbstractInitStringParserTestHelper testHelper) {
		this.testHelper = testHelper;
	}
	
}
