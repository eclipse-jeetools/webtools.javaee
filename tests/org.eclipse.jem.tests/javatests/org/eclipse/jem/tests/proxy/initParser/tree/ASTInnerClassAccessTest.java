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
 *  $Revision: 1.2 $  $Date: 2004/02/03 23:18:13 $ 
 */
package org.eclipse.jem.tests.proxy.initParser.tree;

import org.eclipse.jem.internal.proxy.core.IExpression;
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
	}

	protected ASTTreeInitStringParserTestHelper getTreeParser() {
		return (ASTTreeInitStringParserTestHelper) testHelper;
	}
	
	public void testInnerFieldAccess() throws Throwable {
		IExpression exp = getTreeParser().getRegistry().getBeanProxyFactory().createExpression();
		exp.createFieldAccess(IExpression.ROOTEXPRESSION, "GREEN", true);
		exp.createTypeReceiver("org.eclipse.jem.tests.proxy.initParser.tree.InnerClassTestData$InnerInnerClass");
		getTreeParser().testInitString("InnerClassTestData.InnerInnerClass.GREEN", new String[] {"org.eclipse.jem.tests.proxy.initParser.tree.*"}, exp.getExpressionValue());
	}
	
	public void testInnerFieldAccess2() throws Throwable {
		IExpression exp = getTreeParser().getRegistry().getBeanProxyFactory().createExpression();
		exp.createFieldAccess(IExpression.ROOTEXPRESSION, "GREEN", true);
		exp.createTypeReceiver("org.eclipse.jem.tests.proxy.initParser.tree.InnerClassTestData$InnerInnerClass");
		
		// Test where the inner class is the top level listed.
		getTreeParser().testInitString("InnerInnerClass.GREEN", new String[] {"org.eclipse.jem.tests.proxy.initParser.tree.InnerClassTestData.InnerInnerClass"}, exp.getExpressionValue());
	}	
	
	public void testInnerInnerFieldAccess() throws Throwable {
		IExpression exp = getTreeParser().getRegistry().getBeanProxyFactory().createExpression();
		exp.createFieldAccess(IExpression.ROOTEXPRESSION, "RED", true);
		exp.createTypeReceiver("org.eclipse.jem.tests.proxy.initParser.tree.InnerClassTestData$InnerInnerClass$InnerInnerInnerClass");
		
		getTreeParser().testInitString("InnerClassTestData.InnerInnerClass.InnerInnerInnerClass.RED", new String[] {"org.eclipse.jem.tests.proxy.initParser.tree.*"}, exp.getExpressionValue());
	}

	public void testInnerClassCreation() throws Throwable {
		IExpression exp = getTreeParser().getRegistry().getBeanProxyFactory().createExpression();
		exp.createClassInstanceCreation(IExpression.ROOTEXPRESSION, "org.eclipse.jem.tests.proxy.initParser.tree.InnerClassTestData$InnerInnerClass", 0);
		// Create static inner class
		getTreeParser().testInitString("new InnerClassTestData.InnerInnerClass()", new String[] {"org.eclipse.jem.tests.proxy.initParser.tree.*"}, exp.getExpressionValue());
	}
	
}
