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
 *  $RCSfile: CastTest.java,v $
 *  $Revision: 1.4 $  $Date: 2005/05/11 22:41:39 $ 
 */

/**
 * @author jmyers
 *
 * To change the template for this generated type comment go to
 * Window>Preferences>Java>Code Generation>Code and Comments
 */
public class CastTest extends AbstractInitParserTestCase {

	/**
	 * Constructor for CastTest.
	 * @param name
	 */
	public CastTest(String name) {
		super(name);
	}

	public void testIntToFloat() throws Throwable {
		testHelper.testInitString("new Float((float)10)", new Float(10));	
	}
	public void testIntToFloatSpace() throws Throwable {
		testHelper.testInitString("new Float( (float) 10 )", new Float(10));	
	}
	public void testFloatToFloat() throws Throwable {
		testHelper.testInitString("new Float((float)10.3)", new Float((float) 10.3));	
	}
	public void testFloatToFloat2() throws Throwable {
		testHelper.testInitString("new Float((float)5.3)", new Float((float) 5.3));	
	}
	public void testFloatToFloatSpace() throws Throwable {
		testHelper.testInitString("new Float( (float) 10.3 )", new Float((float) 10.3));	
	}
	public void testNullToString() throws Throwable {
		testHelper.testInitString("(String)null", (String) null);	
	}
	public void testStringLiteralToString() throws Throwable {
		testHelper.testInitString("(String)\"hi\"", "hi");	
	}
	public void testNullToStringParam() throws Throwable {
		testHelper.testInitString("new javax.swing.JLabel( (String) null)", new javax.swing.JLabel((String) null));	
	}
	public void testIntToShort() throws Throwable {
		testHelper.testInitString("(short)10", new Short((short) 10));		
	}
	public void testFloatToFloatPrim() throws Throwable {
		testHelper.testInitString("(float)10.3", new Float((float)10.3));	
	}
	
    //	Casts statements with qualified expression used to fail
	public void testQualifiedStringReturn() throws Throwable {
		testHelper.testInitString("(java.lang.String)org.eclipse.jem.tests.proxy.initParser.NavigationParameters.getReversed(\"Frog\")",org.eclipse.jem.tests.proxy.initParser.NavigationParameters.getReversed("Frog"));	
	}
	public void testStringReturn() throws Throwable {
		testHelper.testInitString("(String)org.eclipse.jem.tests.proxy.initParser.NavigationParameters.getReversed(\"Frog\")",org.eclipse.jem.tests.proxy.initParser.NavigationParameters.getReversed("Frog"));	
	}
	public void testQualifiedColorReturn() throws Throwable {
		testHelper.testInitString("(java.awt.Color)org.eclipse.jem.tests.proxy.initParser.NavigationParameters.getColor(\"red\")",org.eclipse.jem.tests.proxy.initParser.NavigationParameters.getColor("red"));	
	}
	
	public void testCastUpColor() throws Throwable {
         //		Test where the method is typed to return Color and we cast to the more generailized subclass of SystemColor
		 testHelper.testInitString("(java.awt.SystemColor)org.eclipse.jem.tests.proxy.initParser.NavigationParameters.getColor(\"window\")",org.eclipse.jem.tests.proxy.initParser.NavigationParameters.getColor("window"));	
	}
}
