package org.eclipse.jem.tests.proxy.initParser;
/*******************************************************************************
 * Copyright (c)  2003 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials 
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *******************************************************************************/
/*
 *  $RCSfile: MultiArgStaticMethodTest.java,v $
 *  $Revision: 1.3 $  $Date: 2005/02/15 23:00:16 $ 
 */

/**
 * @author jmyers
 *
 * To change the template for this generated type comment go to
 * Window>Preferences>Java>Code Generation>Code and Comments
 */
public class MultiArgStaticMethodTest extends AbstractInitParserTestCase {

	/**
	 * Constructor for MultiArgStaticMethodTest.
	 * @param name
	 */
	public MultiArgStaticMethodTest(String name) {
		super(name);
	}

	public void testTwoBooleans() throws Throwable {
		testHelper.testInitString("org.eclipse.jem.tests.proxy.initParser.NavigationParameters.get(false, false)",org.eclipse.jem.tests.proxy.initParser.NavigationParameters.get(false,false));	
	}
	public void testTwoInts() throws Throwable {
		testHelper.testInitString("org.eclipse.jem.tests.proxy.initParser.NavigationParameters.get(12, 24)",org.eclipse.jem.tests.proxy.initParser.NavigationParameters.get(12,24));	
	}
	public void testThreeFloats() throws Throwable {
		testHelper.testInitString("org.eclipse.jem.tests.proxy.initParser.NavigationParameters.get((float)12,(float)24,(float)50)",org.eclipse.jem.tests.proxy.initParser.NavigationParameters.get((float)12,(float)24,(float)50));		
	}
	public void testTwoDoubles() throws Throwable {
		testHelper.testInitString("org.eclipse.jem.tests.proxy.initParser.NavigationParameters.get(12.5d, 24.5d)",org.eclipse.jem.tests.proxy.initParser.NavigationParameters.get(12.5d,24.5d));	
	}
	public void testTwoStrings() throws Throwable {
		testHelper.testInitString("org.eclipse.jem.tests.proxy.initParser.NavigationParameters.get(\"a\", \"b\")",org.eclipse.jem.tests.proxy.initParser.NavigationParameters.get("a","b"));	
	}
	public void testTwoColors() throws Throwable {
		testHelper.testInitString("org.eclipse.jem.tests.proxy.initParser.NavigationParameters.get(java.awt.Color.red, java.awt.Color.red)",org.eclipse.jem.tests.proxy.initParser.NavigationParameters.get(java.awt.Color.red,java.awt.Color.red));		
	}
	public void testTwoNewColors() throws Throwable {
		testHelper.testInitString("org.eclipse.jem.tests.proxy.initParser.NavigationParameters.get(new java.awt.Color(255,0,0), new java.awt.Color(255,0,0))",org.eclipse.jem.tests.proxy.initParser.NavigationParameters.get(new java.awt.Color(255,0,0),new java.awt.Color(255,0,0)));		
	}
}
