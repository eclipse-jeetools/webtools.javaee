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
 *  $RCSfile: ASTOperationsTest.java,v $
 *  $Revision: 1.2 $  $Date: 2004/02/03 23:18:13 $ 
 */
package org.eclipse.jem.tests.proxy.initParser.tree;

import org.eclipse.jem.tests.proxy.initParser.AbstractInitParserTestCase;
 
/**
 * 
 * @since 1.0.0
 */
public class ASTOperationsTest extends AbstractInitParserTestCase {

	/**
	 * Create with name.
	 * @param name
	 * 
	 * @since 1.0.0
	 */
	public ASTOperationsTest(String name) {
		super(name);
	}
	
	protected ASTTreeInitStringParserTestHelper getTreeParser() {
		return (ASTTreeInitStringParserTestHelper) testHelper;
	}
	
	public void testTwoOpers() throws Throwable {
		testHelper.testInitString("3+4", getTreeParser().getRegistry().getBeanProxyFactory().createBeanProxyWith(3+4));
	}
	
	public void testFourOpersExtended() throws Throwable {
		testHelper.testInitString("3+4+5+6", getTreeParser().getRegistry().getBeanProxyFactory().createBeanProxyWith(3+4+5+6));
	}	

	public void testMixedOpers() throws Throwable {
		testHelper.testInitString("3+4-5", getTreeParser().getRegistry().getBeanProxyFactory().createBeanProxyWith(3+4-5));
	}
	
	public void testMixedOpersPrecedence() throws Throwable {
		testHelper.testInitString("6 | 3+4 | 8", getTreeParser().getRegistry().getBeanProxyFactory().createBeanProxyWith(6 | 3+4 | 8));
	}
	
	public void testConditional() throws Throwable {
		testHelper.testInitString("java.awt.Color.red != null ? 3 : 5", getTreeParser().getRegistry().getBeanProxyFactory().createBeanProxyWith(java.awt.Color.red != null ? 3 : 5));
	}

	public void testInstanceof() throws Throwable {
		testHelper.testInitString("java.awt.Color.red instanceof java.awt.Color", getTreeParser().getRegistry().getBeanProxyFactory().createBeanProxyWith(java.awt.Color.red instanceof java.awt.Color));
	}

	public void testTypeLiteral() throws Throwable {
		testHelper.testInitString("Object.class", getTreeParser().getRegistry().getBeanTypeProxyFactory().getBeanTypeProxy("java.lang.Object"));
	}
	
	public void testStringPlusOperations() throws Throwable {
		testHelper.testInitString("3+\"a\"", getTreeParser().getRegistry().getBeanProxyFactory().createBeanProxyWith(3+"a"));
	}
	
	public void testStringPlusOperations1() throws Throwable {
		testHelper.testInitString("\"a\"+3", getTreeParser().getRegistry().getBeanProxyFactory().createBeanProxyWith("a"+3));
	}
	
	public void testStringPlusExtendedOperations() throws Throwable {
		testHelper.testInitString("3+4+\"a\"", getTreeParser().getRegistry().getBeanProxyFactory().createBeanProxyWith(3+4+"a"));
	}
	
	public void testStringPlusExtendedOperations1() throws Throwable {
		testHelper.testInitString("3+\"a\"+4", getTreeParser().getRegistry().getBeanProxyFactory().createBeanProxyWith(3+"a"+4));
	}
	
	
}
