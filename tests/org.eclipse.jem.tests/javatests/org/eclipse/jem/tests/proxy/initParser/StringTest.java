/*******************************************************************************
 * Copyright (c) 2003, 2004 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *******************************************************************************/
package org.eclipse.jem.tests.proxy.initParser;
/*
 *  $RCSfile: StringTest.java,v $
 *  $Revision: 1.5 $  $Date: 2005/02/15 23:00:16 $ 
 */

/**
 * @author jmyers
 *
 * To change the template for this generated type comment go to
 * Window>Preferences>Java>Code Generation>Code and Comments
 */
public class StringTest extends AbstractInitParserTestCase {

	/**
	 * Constructor for StringTest.
	 * @param name
	 */
	public StringTest(String name) {
		super(name);
	}

	public void testBasic() throws Throwable {
		testHelper.testInitString("\"Frog/123\"", "Frog/123");
	}
	public void testIntValue() throws Throwable {
		testHelper.testInitString("String.valueOf(10)", "10");	
	}
	public void testParens() throws Throwable {
		testHelper.testInitString("\"Frog(123)prince\"", "Frog(123)prince");	
	}
	public void testDoubleQuotes() throws Throwable {
		testHelper.testInitString("\"Frog\\\"prince\\\"123\"", "Frog\"prince\"123");	
	}
	public void testBackSlashes() throws Throwable {
		testHelper.testInitString("\"Frog\\\\prince\\\\123\"", "Frog\\prince\\123");	
	}
	public void testBackSlash() throws Throwable {
		testHelper.testInitString("\"\\\\Frog\"", "\\Frog");	
	}
	public void testStringFunction() throws Throwable {
		testHelper.testInitString("\"Frog\".length()", new Integer(4));	
	}
}
