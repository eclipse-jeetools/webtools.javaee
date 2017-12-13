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
 *  $RCSfile: SameName46376Test.java,v $
 *  $Revision: 1.3 $  $Date: 2005/08/24 20:58:54 $ 
 */
 
/**
 * This is to test for defect [46376].
 * 
 * SameName test. This is test where you have this:
 * 	InitParserTest46376.SameNameTestClass.java
 * 	InitParserTest46376.java
 * 
 * and
 * 
 * 	new org.eclipse.jem.tests.proxy.initParser.SameNameTestClass.RealClass()
 * 
 * Before [46376] the Static parser would find SameNameTestClass.java instead of the RealClass and would of failed.
 * To compile in Eclipse we need to have one of the classes be in the default package. Eclipse complains if we didn't.
 * But there is nothing to stop this from happening with packages too if they are spread across compile groups.  
 */
public class SameName46376Test extends AbstractInitParserTestCase {
	
	public SameName46376Test(String name) {
		super(name);
	}	

	public void test46376() throws Throwable {
		testHelper.testInitString("new initParserTest46376.SameNameTestClass()", new initParserTest46376.SameNameTestClass());
	}
}
