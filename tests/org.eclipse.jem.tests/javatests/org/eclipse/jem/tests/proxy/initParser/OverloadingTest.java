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
 *  $RCSfile: OverloadingTest.java,v $
 *  $Revision: 1.3 $  $Date: 2004/01/23 22:53:36 $ 
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
		testHelper.testInitString("new String(\"Frog\")", "Frog");
	}
	public void testOverloadShort() throws Throwable {
		testHelper.testInitString("new String((short)3)", "3");
	}
}
