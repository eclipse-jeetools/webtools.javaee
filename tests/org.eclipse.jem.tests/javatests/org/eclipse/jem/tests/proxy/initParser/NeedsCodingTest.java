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
 *  $RCSfile: NeedsCodingTest.java,v $
 *  $Revision: 1.6 $  $Date: 2005/08/24 20:58:54 $ 
 */

/**
 * @author jmyers
 *
 * To change the template for this generated type comment go to
 * Window>Preferences>Java>Code Generation>Code and Comments
 */
public class NeedsCodingTest extends AbstractInitParserTestCase {

	/**
	 * Constructor for NeedsCodingTest.
	 * @param name
	 */
	public NeedsCodingTest(String name) {
		super(name);
	}	
	
	// TODO: enable this test when math works
	/*
	public void testMath() {
		// Doing math is not coded yet.  We don't expect to encounter any strings like this as part of GA
		testHelper.testInitString("10 + 20", new Integer(10 + 20));	
	}
	*/
	
	public void testInnerClasses() throws Throwable {
         //	Inner classes need coding but we have a test to make sure we throw an explicit exception
		 testHelper.testInitString("new javax.swing.table.DefaultTableModel(){}",new javax.swing.table.DefaultTableModel(){

			/**
			 * Comment for <code>serialVersionUID</code>
			 * 
			 * @since 1.1.0
			 */
			private static final long serialVersionUID = 1L;},true, true);	
	}
	public void testParamsThreeFloats() throws Throwable {
		testHelper.testInitString("org.eclipse.jem.tests.proxy.initParser.NavigationParameters.get((float)12,(float)24,(float)50)",org.eclipse.jem.tests.proxy.initParser.NavigationParameters.get(12,24,50));		
	}
	public void testConstructThreeFloats() throws Throwable {
		testHelper.testInitString("new org.eclipse.jem.tests.proxy.initParser.NavigationParameters((float)12,(float)24,(float)50)",new org.eclipse.jem.tests.proxy.initParser.NavigationParameters(12,24,50));	
	}
	public void testNewParamsThreeFloats() throws Throwable {
		testHelper.testInitString("new org.eclipse.jem.tests.proxy.initParser.NavigationParameters().set((float)12,(float)24,(float)50)",new org.eclipse.jem.tests.proxy.initParser.NavigationParameters().set(12,24,50));	
	}
}
