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
 *  $RCSfile: ASTInnerClassAccessTest.java,v $
 *  $Revision: 1.1 $  $Date: 2004/01/23 22:53:36 $ 
 */
package org.eclipse.jem.tests.proxy.initParser.tree;

import org.eclipse.jem.tests.proxy.initParser.AbstractInitParserTestCase;
 
/**
 * This is for testing inner class access.
 * @since 1.0.0
 */
public class ASTInnerClassAccessTest extends AbstractInitParserTestCase {

	/**
	 * Construct with name.
	 * @param name
	 * 
	 * @since 1.0.0
	 */
	public ASTInnerClassAccessTest(String name) {
		super(name);
		// TODO Auto-generated constructor stub
	}

	protected ASTTreeInitStringParserTestHelper getTreeParser() {
		return (ASTTreeInitStringParserTestHelper) testHelper;
	}
	
	public void testInnerFieldAccess() throws Throwable {
		getTreeParser().testInitString("InnerClassTestData.InnerInnerClass.GREEN", new String[] {"org.eclipse.jem.tests.proxy.initParser.tree.*"}, InnerClassTestData.InnerInnerClass.GREEN);
	}
	
	public void testInnerFieldAccess2() throws Throwable {
		// Test where the inner class is the top level listed.
		getTreeParser().testInitString("InnerInnerClass.GREEN", new String[] {"org.eclipse.jem.tests.proxy.initParser.tree.InnerClassTestData.InnerInnerClass"}, InnerClassTestData.InnerInnerClass.GREEN);
	}	
	
	public void testInnerInnerFieldAccess() throws Throwable {
		getTreeParser().testInitString("InnerClassTestData.InnerInnerClass.InnerInnerInnerClass.RED", new String[] {"org.eclipse.jem.tests.proxy.initParser.tree.*"}, InnerClassTestData.InnerInnerClass.InnerInnerInnerClass.RED);
	}

	public void testInnerClassCreation() throws Throwable {
		// Create static inner class
		getTreeParser().testInitString("new InnerClassTestData.InnerInnerClass()", new String[] {"org.eclipse.jem.tests.proxy.initParser.tree.*"}, new InnerClassTestData.InnerInnerClass());
	}
	
}
