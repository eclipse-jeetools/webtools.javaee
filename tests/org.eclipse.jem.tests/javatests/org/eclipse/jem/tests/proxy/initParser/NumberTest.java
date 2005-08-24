/*******************************************************************************
 * Copyright (c) 2003, 2005 IBM Corporation and others.
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
 *  $RCSfile: NumberTest.java,v $
 *  $Revision: 1.4 $  $Date: 2005/08/24 20:58:54 $ 
 */

/**
 * @author jmyers
 *
 * To change the template for this generated type comment go to
 * Window>Preferences>Java>Code Generation>Code and Comments
 */
public class NumberTest extends AbstractInitParserTestCase {

	/**
	 * Constructor for NumberTest.
	 * @param name
	 */
	public NumberTest(String name) {
		super(name);
	}

	public void testIntPrim() throws Throwable {
		testHelper.testInitString("10", new Integer(10));	
	}
	public void testShortPrim() throws Throwable {
		testHelper.testInitString("(short)10", new Short((short)10));	
	}
	public void testNegativeShortPrim() throws Throwable {
		testHelper.testInitString("(short)-10", new Short((short)-10));	
	}	
	public void testBytePrim() throws Throwable {
		testHelper.testInitString("(byte)10", new Byte((byte)10));	
	}
	public void testDoublePrimExplicit() throws Throwable {
		testHelper.testInitString("10d", new Double(10d));	
	}
	public void testNegativeDoublePrimExplicit() throws Throwable {
		testHelper.testInitString("-10d", new Double(-10d));	
	}
	public void testFloatPrimExplicit() throws Throwable {
		testHelper.testInitString("10f", new Float(10f));	
	}
	public void testLongPrimExplicit() throws Throwable {
		testHelper.testInitString("10l", new Long(10l));	
	}
	public void testDoublePrimImplicit() throws Throwable {
		testHelper.testInitString("10.75", new Double(10.75));	
	}
	public void testNegativeDoublePrimImplicit() throws Throwable {
		testHelper.testInitString("-10.75", new Double(-10.75));	
	}
	public void testSpacedDoublePrimImplicit() throws Throwable {
		testHelper.testInitString(" 10.75 ", new Double(10.75));	
	}
	public void testNegativeDoublePrimExplicit2() throws Throwable {
		testHelper.testInitString("-10.75d", new Double(-10.75d));	
	}
}
