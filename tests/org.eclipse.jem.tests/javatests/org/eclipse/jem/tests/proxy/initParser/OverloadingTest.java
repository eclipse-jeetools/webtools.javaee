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
 *  $Revision: 1.2 $  $Date: 2003/10/27 17:32:36 $ 
 */

/**
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

	public void testShort() throws Throwable {
		testHelper.testInitString("(short) 3", new Short((short) 3));
	}
	public void testParen1() throws Throwable {
		testHelper.testInitString("(new String(\"Frog\")).length()",new Integer(4));
	}
	public void testParen2() throws Throwable {
		testHelper.testInitString("((new String(\"Frog\"))).length()",new Integer(4));
	}
	public void testParen2b() throws Throwable {
		testHelper.testInitString("((new String(\"Frog\")).length())",new Integer(4));
	}
	public void testParen3() throws Throwable {
		testHelper.testInitString("(((new String(\"Frog\")).length()))",new Integer(4));
	}
}
