/*******************************************************************************
 * Copyright (c) 2004 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials 
 * are made available under the terms of the Common Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/cpl-v10.html
 * 
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *******************************************************************************/
/*
 *  $RCSfile: ASTArraysTest.java,v $
 *  $Revision: 1.1 $  $Date: 2004/01/19 22:50:22 $ 
 */
package org.eclipse.jem.tests.proxy.initParser.tree;

import org.eclipse.jem.tests.proxy.initParser.ArrayConstructorTest;
 
/**
 * Test Arrays for AST.
 * 
 * @since 1.0.0
 */
public class ASTArraysTest extends ArrayConstructorTest {

	public ASTArraysTest(String name) {
		super(name);
	}
	
	public void testUndefinedSecondDim() throws Throwable {
		testHelper.testInitString("new int[3][]", new int[3][]);
	}
	
	public void testArrayAccess() throws Throwable {
		testHelper.testInitString("(new Integer[3])[0]", (new Integer[3])[0]); 
	}
	
	public void testMutltiArrayAccess() throws Throwable {
		testHelper.testInitString("(new int[][] {{2}, {4}})[0][0]", new Integer(((new int[][] {{2}, {4}})[0][0]))); 
	}	
}
