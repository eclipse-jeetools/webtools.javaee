/*
 * Created on Apr 25, 2003
 *
 * To change the template for this generated file go to
 * Window>Preferences>Java>Code Generation>Code and Comments
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


/**
 * @author jmyers
 *
 * To change the template for this generated type comment go to
 * Window>Preferences>Java>Code Generation>Code and Comments
 */
public class LiteralTest extends AbstractInitParserTestCase {

	/**
	 * Constructor for LiteralTest.
	 * @param name
	 */
	public LiteralTest(String name) {
		super(name);
	}
	
	public void testString() throws Throwable {
		testHelper.testInitString("\"Frog\"", "Frog");	
	}
	public void testNull() throws Throwable {
		testHelper.testInitString("null", null);	
	}
	public void testFalse() throws Throwable {
		testHelper.testInitString("false", Boolean.FALSE);	
	}
	public void testTrue() throws Throwable {
		testHelper.testInitString("true", Boolean.TRUE);	
	}
	public void testBooleanTrue() throws Throwable {
		testHelper.testInitString("Boolean.TRUE", Boolean.TRUE);	
	}
}
