/*
 * Created on Aug 27, 2003
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
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

import junit.framework.TestCase;

/**
 * @author richkulp
 *
 * To change the template for this generated type comment go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
public abstract class AbstractInitParserTestCase extends TestCase {

	/**
	 * 
	 */
	public AbstractInitParserTestCase() {
		super();
	}

	/**
	 * @param name
	 */
	public AbstractInitParserTestCase(String name) {
		super(name);
	}

	protected InitStringParserTestHelper testHelper;
	
	/* (non-Javadoc)
	 * @see junit.framework.TestCase#setUp()
	 */
	protected void setUp() throws Exception {
		super.setUp();
		// Setup testHelper with classLoader that loaded this class.
		testHelper = new InitStringParserTestHelper(this.getClass().getClassLoader());
	}
}
