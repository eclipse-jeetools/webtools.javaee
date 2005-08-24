/*******************************************************************************
 * Copyright (c) 2004, 2005 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *******************************************************************************/
/*
 *  $RCSfile: ASTArraysTest.java,v $
 *  $Revision: 1.5 $  $Date: 2005/08/24 20:58:54 $ 
 */
package org.eclipse.jem.tests.proxy.initParser.tree;

import org.eclipse.jem.internal.proxy.core.IExpression;
import org.eclipse.jem.internal.proxy.initParser.tree.ForExpression;
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
	
	protected ASTTreeInitStringParserTestHelper getTreeParser() {
		return (ASTTreeInitStringParserTestHelper) testHelper;
	}
	
	public void testUndefinedSecondDim() throws Throwable {
		IExpression exp = getTreeParser().getRegistry().getBeanProxyFactory().createExpression();
		exp.createArrayCreation(ForExpression.ROOTEXPRESSION, "int[][]", 1);
		exp.createPrimitiveLiteral(ForExpression.ARRAYCREATION_DIMENSION, 3);
		testHelper.testInitString("new int[3][]", exp.getExpressionValue());
	}
	
	public void testArrayAccess() throws Throwable {
		IExpression exp = getTreeParser().getRegistry().getBeanProxyFactory().createExpression();
		exp.createArrayAccess(ForExpression.ROOTEXPRESSION, 1);
		exp.createArrayCreation(ForExpression.ARRAYACCESS_ARRAY, "java.lang.Integer[]", 1);
		exp.createPrimitiveLiteral(ForExpression.ARRAYCREATION_DIMENSION, 3);
		exp.createPrimitiveLiteral(ForExpression.ARRAYACCESS_INDEX, 0);
		testHelper.testInitString("(new Integer[3])[0]", exp.getExpressionValue()); 
	}
	
	public void testMutltiArrayAccess() throws Throwable {
		IExpression exp = getTreeParser().getRegistry().getBeanProxyFactory().createExpression();
		exp.createArrayAccess(ForExpression.ROOTEXPRESSION, 2);
		exp.createArrayCreation(ForExpression.ARRAYACCESS_ARRAY, "int[][]", 0);
		exp.createArrayInitializer(2);
		exp.createArrayInitializer(1);
		exp.createPrimitiveLiteral(ForExpression.ARRAYINITIALIZER_EXPRESSION, 2);
		exp.createArrayInitializer(1);
		exp.createPrimitiveLiteral(ForExpression.ARRAYINITIALIZER_EXPRESSION, 4);
		exp.createPrimitiveLiteral(ForExpression.ARRAYACCESS_INDEX, 0);
		exp.createPrimitiveLiteral(ForExpression.ARRAYACCESS_INDEX, 0);		
		testHelper.testInitString("(new int[][] {{2}, {4}})[0][0]", exp.getExpressionValue()); 
	}	
}
