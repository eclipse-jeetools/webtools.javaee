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
 *  $RCSfile: BlockTest.java,v $
 *  $Revision: 1.4 $  $Date: 2005/02/15 23:00:16 $ 
 */

/**
 * @author jmyers
 *
 * To change the template for this generated type comment go to
 * Window>Preferences>Java>Code Generation>Code and Comments
 */
public class BlockTest extends AbstractInitParserTestCase {

	/**
	 * Constructor for BlockTest.
	 * @param name
	 */
	public BlockTest(String name) {
		super(name);
	}

/*
 * TODO These three don't work for now. Later they may be fixed if it shows up as a general problem for customers. So far they haven't.
        public void testSetElements1() throws Throwable {
		testHelper.testInitString(
			"(((new org.eclipse.jem.tests.proxy.initParser.NavigationParameters(3)).setElemAt(\"accountStatementDetails\",0)).setElemAt(new org.eclipse.jem.tests.proxy.initParser.NavigationParameters(\"OpnBalance\",\"OpnBalance\",null,false,false,true,50),1)).setElemAt(new org.eclipse.jem.tests.proxy.initParser.NavigationParameters(\"OpnDate\",\"OpnDate\",null,false,false,true,50),2);",
			(((new org.eclipse.jem.tests.proxy.initParser.NavigationParameters(3)).setElemAt("accountStatementDetails",0)).setElemAt(new NavigationParameters("OpnBalance","OpnBalance",null,false,false,true,50),1)).setElemAt(new org.eclipse.jem.tests.proxy.initParser.NavigationParameters("OpnDate","OpnDate",null,false,false,true,50),2));    	
    }
	public void testSetElements2() throws Throwable {
		testHelper.testInitString(
				"(((new org.eclipse.jem.tests.proxy.initParser.NavigationParameters(3)).setElemAt(\"accountStatementDetails\",0)).setElemAt(new org.eclipse.jem.tests.proxy.initParser.NavigationParameters(\"OpnBalance\",\"OpnBalance\",null,false,false,true,50),1))",
				(((new org.eclipse.jem.tests.proxy.initParser.NavigationParameters(3)).setElemAt("accountStatementDetails",0)).setElemAt(new NavigationParameters("OpnBalance","OpnBalance",null,false,false,true,50),1)));	
	}
	public void testSetElements3() throws Throwable {
		testHelper.testInitString(
			"((new org.eclipse.jem.tests.proxy.initParser.NavigationParameters(3)).setElemAt(new org.eclipse.jem.tests.proxy.initParser.NavigationParameters(\"OpnBalance\",\"OpnBalance\",null,false,false,true,50),1))",
			((new org.eclipse.jem.tests.proxy.initParser.NavigationParameters(3)).setElemAt(new org.eclipse.jem.tests.proxy.initParser.NavigationParameters("OpnBalance","OpnBalance",null,false,false,true,50),1)));    	
	}
*/
	public void testSetElement4() throws Throwable {
		testHelper.testInitString(
				"((new org.eclipse.jem.tests.proxy.initParser.NavigationParameters(3)).setElemAt(\"accountStatementDetails\",0))",
				((new org.eclipse.jem.tests.proxy.initParser.NavigationParameters(3)).setElemAt("accountStatementDetails",0)));	
	}
	public void testStringReplacement1() throws Throwable {
		testHelper.testInitString(
				"(((new String(\"Frog\")).replace('o','i')).replace('i','a')).replace('a','o')",
				(((new String("Frog")).replace('o','i')).replace('i','a')).replace('a','o'));	
	}
	public void testStringReplacement2() throws Throwable {
		testHelper.testInitString(
				"((((new String(\"Frog\")).replace('o','i')).replace('i','a')).replace('a','o')).replace('o','e')",
				((((new String("Frog")).replace('o','i')).replace('i','a')).replace('a','o')).replace('o','e'));	
	}
	public void testStringCases1() throws Throwable {
		testHelper.testInitString(
				"(new String(\"Frog\").toLowerCase()).toUpperCase()",
				(new String("Frog").toLowerCase()).toUpperCase()
				);	
	}
	public void testStringCases2() throws Throwable {
		testHelper.testInitString(
				"((new String(\"Frog\").toLowerCase()).toUpperCase()).toLowerCase()",
				((new String("Frog").toLowerCase()).toUpperCase()).toLowerCase()
				);	
	}
	public void testStringCases3() throws Throwable {
		testHelper.testInitString(
				"(((new String(\"Frog\").toLowerCase()).toUpperCase()).toLowerCase()).toUpperCase()",
				(((new String("Frog").toLowerCase()).toUpperCase()).toLowerCase()).toUpperCase()
				);	
	}
	public void testStringConcat1() throws Throwable {
		testHelper.testInitString(
				"(new String(\"Frogs\").concat(\"are\")).concat(\"cool\")",
				(new String("Frogs").concat("are")).concat("cool")
				);	
	}
	public void testStringConcat2() throws Throwable {
		testHelper.testInitString(
				"((new String(\"Frogs\").concat(\"are\")).concat(\"very\")).concat(\"cool\")",
				((new String("Frogs").concat("are")).concat("very")).concat("cool")
				);	
	}
	public void testStringConcat3() throws Throwable {
		testHelper.testInitString(
				"(((new String(\"Frogs\").concat(\"just\")).concat(\"rule\")).concat(\"cool\")).concat(\"kingdom\")",
				(((new String("Frogs").concat("just")).concat("rule")).concat("cool")).concat("kingdom")
				);	
	}
	public void testStringConcat4() throws Throwable {
		testHelper.testInitString(
				"((new String(\"Frogs\").concat(\"just\")).concat(new String(\"totally\")).concat(new String(\"Rock\")))",
				((new String("Frogs").concat("just")).concat(new String("totally")).concat(new String("Rock")))
				);	
	}
}
