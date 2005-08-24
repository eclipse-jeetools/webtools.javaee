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
 *  $RCSfile: CharTest.java,v $
 *  $Revision: 1.4 $  $Date: 2005/08/24 20:58:54 $ 
 */

/**
 * @author jmyers
 *
 * To change the template for this generated type comment go to
 * Window>Preferences>Java>Code Generation>Code and Comments
 */
public class CharTest extends AbstractInitParserTestCase {

	/**
	 * Constructor for CharTest.
	 * @param name
	 */
	public CharTest(String name) {
		super(name);
	}
	
	public void testChar() throws Throwable {
		testHelper.testInitString("'a'", new Character('a'));	
	}
	public void testCharacter() throws Throwable {
		testHelper.testInitString("new Character('a')", new Character('a'));	
	}
	public void testDoubleQuote() throws Throwable {
		testHelper.testInitString("'\"'", new Character('\"'));	
	}
	public void testSingleQuote() throws Throwable {
		testHelper.testInitString("'\\''", new Character('\''));	
	}
	public void testBackslash() throws Throwable {
		testHelper.testInitString("'\\\\'", new Character('\\'));		
	}
	public void testCharException() throws Throwable {
		testHelper.testInitString("'asdf'", null, true, true);	
	}
}
