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
public class ExceptionTest extends AbstractInitParserTestCase {

	/**
	 * Constructor for ExceptionTest.
	 * @param name
	 */
	public ExceptionTest(String name) {
		super(name);
	}

	public void testTooManyParams() throws Throwable {
		testHelper.testInitString("new Integer(\"3,3\")", null, true, true);	
	}
	public void testUnresolvedClass() throws Throwable {
		testHelper.testInitString("new ABCD(4)", null, true, true);	
	}
	public void testUnresolvedMethod() throws Throwable {
		testHelper.testInitString("getMethod()",null,true, true);	
	}
	public void testUnresolvedLiteral() throws Throwable {
		testHelper.testInitString("unresolvedLiteral",null,true, true);	
	}
}
