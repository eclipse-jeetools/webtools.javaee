/*******************************************************************************
 * Copyright (c) 2003, 2004 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Common Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/cpl-v10.html
 * 
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *******************************************************************************/
package org.eclipse.jem.tests.proxy.initParser;
/*
 *  $RCSfile: OverloadingTest.java,v $
 *  $Revision: 1.5 $  $Date: 2004/08/27 15:33:39 $ 
 */

/**
 * Test that correct overloaded method is called.
 * @author jmyers
 *
 * To change the template for this generated type comment go to
 * Window>Preferences>Java>Code Generation>Code and Comments
 */
public class OverloadingTest extends AbstractInitParserTestCase {

	/**
	 * Constructor for OverloadingTest.
	 * @param name
	 */
	public OverloadingTest(String name) {
		super(name);
	}

	public void testOverloadString() throws Throwable {
		testHelper.testInitString("new Integer(\"3\")", new Integer(3));
	}
	public void testOverloadShort() throws Throwable {
		testHelper.testInitString("new Integer(5)", new Integer(5));
	}
}
