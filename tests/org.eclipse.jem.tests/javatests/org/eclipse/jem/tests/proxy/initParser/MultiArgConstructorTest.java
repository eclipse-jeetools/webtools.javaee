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
 *  $RCSfile: MultiArgConstructorTest.java,v $
 *  $Revision: 1.4 $  $Date: 2004/08/27 15:33:39 $ 
 */

/**
 * @author jmyers
 *
 * To change the template for this generated type comment go to
 * Window>Preferences>Java>Code Generation>Code and Comments
 */
public class MultiArgConstructorTest extends AbstractInitParserTestCase {

	/**
	 * Constructor for MultiArgConstructorTest.
	 * @param name
	 */
	public MultiArgConstructorTest(String name) {
		super(name);
	}

	public void testTwoBooleans() throws Throwable {
		testHelper.testInitString("new org.eclipse.jem.tests.proxy.initParser.NavigationParameters(false, false)",new org.eclipse.jem.tests.proxy.initParser.NavigationParameters(false,false));	
	}
	public void testTwoInts() throws Throwable {
		testHelper.testInitString("new org.eclipse.jem.tests.proxy.initParser.NavigationParameters(12, 24)",new org.eclipse.jem.tests.proxy.initParser.NavigationParameters(12,24));		
	}
	public void testThreeFloats() throws Throwable {
		testHelper.testInitString("new org.eclipse.jem.tests.proxy.initParser.NavigationParameters((float)12,(float)24,(float)50)",new org.eclipse.jem.tests.proxy.initParser.NavigationParameters((float)12,(float)24,(float)50));		
	}
	public void testTwoDoubles() throws Throwable {
		testHelper.testInitString("new org.eclipse.jem.tests.proxy.initParser.NavigationParameters(12.5d, 24.5d)",new org.eclipse.jem.tests.proxy.initParser.NavigationParameters(12.5d,24.5d));	
	}
	public void testTwoStrings() throws Throwable {
		testHelper.testInitString("new org.eclipse.jem.tests.proxy.initParser.NavigationParameters(\"a\", \"b\")",new org.eclipse.jem.tests.proxy.initParser.NavigationParameters("a","b"));	
	}
	public void testTwoColors() throws Throwable {
		testHelper.testInitString("new org.eclipse.jem.tests.proxy.initParser.NavigationParameters(java.awt.Color.red, java.awt.Color.red)",new org.eclipse.jem.tests.proxy.initParser.NavigationParameters(java.awt.Color.red,java.awt.Color.red));		
	}
	public void testTwoNewColors() throws Throwable {
		testHelper.testInitString("new org.eclipse.jem.tests.proxy.initParser.NavigationParameters(new java.awt.Color(255,0,0), new java.awt.Color(255,0,0))",new org.eclipse.jem.tests.proxy.initParser.NavigationParameters(new java.awt.Color(255,0,0),new java.awt.Color(255,0,0)));	
	}
	public void testLots() throws Throwable {
		testHelper.testInitString("new org.eclipse.jem.tests.proxy.initParser.NavigationParameters(\"processor\", 0, \"customerSearchClientOp\", 0, \"2343434\", \"3443234234\", \"depositRepFmt\", \"depositRepFmt\", \"\", \"\", 0, 0, 0, 0, false, false)",new org.eclipse.jem.tests.proxy.initParser.NavigationParameters("processor", 0, "customerSearchClientOp", 0, "2343434", "3443234234", "depositRepFmt", "depositRepFmt", "", "", 0, 0, 0, 0, false, false));	
	}

// Testcase for Bugzilla bug #58854 - not yet fixed.
//	public void testNestedNested() throws Throwable {
//		testHelper.testInitString("new org.eclipse.jem.tests.proxy.initParser.NavigationParameters(new java.lang.String(new String(\"s\")), \"a\")",new org.eclipse.jem.tests.proxy.initParser.NavigationParameters(new java.lang.String(new String("s")), "a"));	
//	}
}
