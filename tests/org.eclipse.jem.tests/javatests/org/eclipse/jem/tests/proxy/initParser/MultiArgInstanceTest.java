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
 *  $RCSfile: MultiArgInstanceTest.java,v $
 *  $Revision: 1.4 $  $Date: 2005/05/11 22:41:39 $ 
 */

/**
 * @author jmyers
 *
 * To change the template for this generated type comment go to
 * Window>Preferences>Java>Code Generation>Code and Comments
 */
public class MultiArgInstanceTest extends AbstractInitParserTestCase {

	/**
	 * Constructor for MultiArgInstanceTest.
	 * @param name
	 */
	public MultiArgInstanceTest(String name) {
		super(name);
	}
	
	public void testTwoBooleans() throws Throwable {
		testHelper.testInitString("new org.eclipse.jem.tests.proxy.initParser.NavigationParameters().set(false, false)",new org.eclipse.jem.tests.proxy.initParser.NavigationParameters().set(false,false));		
	}
	public void testTwoInts() throws Throwable {
		testHelper.testInitString("new org.eclipse.jem.tests.proxy.initParser.NavigationParameters().set(12, 24)",new org.eclipse.jem.tests.proxy.initParser.NavigationParameters().set(12,24));		
	}
	public void testThreeFloats() throws Throwable {
		testHelper.testInitString("new org.eclipse.jem.tests.proxy.initParser.NavigationParameters().set((float)12,(float)24,(float)50)",new org.eclipse.jem.tests.proxy.initParser.NavigationParameters().set(12,24,50));	
	}
	public void testTwoDoubles() throws Throwable {
		testHelper.testInitString("new org.eclipse.jem.tests.proxy.initParser.NavigationParameters().set(12.5d, 24.5d)",new org.eclipse.jem.tests.proxy.initParser.NavigationParameters().set(12.5d,24.5d));	
	}
	public void testTwoStrings() throws Throwable {
		testHelper.testInitString("new org.eclipse.jem.tests.proxy.initParser.NavigationParameters().set(\"a\", \"b\")",new org.eclipse.jem.tests.proxy.initParser.NavigationParameters().set("a","b"));	
	}
	public void testTwoColors() throws Throwable {
		testHelper.testInitString("new org.eclipse.jem.tests.proxy.initParser.NavigationParameters().set(java.awt.Color.red, java.awt.Color.red)",new org.eclipse.jem.tests.proxy.initParser.NavigationParameters().set(java.awt.Color.red,java.awt.Color.red));	
	}
	public void testTwoNewColors() throws Throwable {
		testHelper.testInitString("new org.eclipse.jem.tests.proxy.initParser.NavigationParameters().set(new java.awt.Color(255,0,0), new java.awt.Color(255,0,0))",new org.eclipse.jem.tests.proxy.initParser.NavigationParameters().set(new java.awt.Color(255,0,0),new java.awt.Color(255,0,0)));	
	}
}
