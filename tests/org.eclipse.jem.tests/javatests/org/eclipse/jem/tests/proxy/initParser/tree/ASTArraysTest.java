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
 *  $Revision: 1.2 $  $Date: 2004/02/03 23:18:13 $ 
 */
package org.eclipse.jem.tests.proxy.initParser.tree;

import org.eclipse.jem.internal.proxy.core.IExpression;
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
		exp.createArrayCreation(IExpression.ROOTEXPRESSION, "int[][]", 1);
		exp.createPrimitiveLiteral(IExpression.ARRAYCREATION_DIMENSION, 3);
		testHelper.testInitString("new int[3][]", exp.getExpressionValue());
	}
	
	public void testArrayAccess() throws Throwable {
		IExpression exp = getTreeParser().getRegistry().getBeanProxyFactory().createExpression();
		exp.createArrayAccess(IExpression.ROOTEXPRESSION, 1);
		exp.createArrayCreation(IExpression.ARRAYACCESS_ARRAY, "java.lang.Integer[]", 1);
		exp.createPrimitiveLiteral(IExpression.ARRAYCREATION_DIMENSION, 3);
		exp.createPrimitiveLiteral(IExpression.ARRAYACCESS_INDEX, 0);
		testHelper.testInitString("(new Integer[3])[0]", exp.getExpressionValue()); 
	}
	
	public void testMutltiArrayAccess() throws Throwable {
		IExpression exp = getTreeParser().getRegistry().getBeanProxyFactory().createExpression();
		exp.createArrayAccess(IExpression.ROOTEXPRESSION, 2);
		exp.createArrayCreation(IExpression.ARRAYACCESS_ARRAY, "int[][]", 0);
		exp.createArrayInitializer(2);
		exp.createArrayInitializer(1);
		exp.createPrimitiveLiteral(IExpression.ARRAYINITIALIZER_EXPRESSION, 2);
		exp.createArrayInitializer(1);
		exp.createPrimitiveLiteral(IExpression.ARRAYINITIALIZER_EXPRESSION, 4);
		exp.createPrimitiveLiteral(IExpression.ARRAYACCESS_INDEX, 0);
		exp.createPrimitiveLiteral(IExpression.ARRAYACCESS_INDEX, 0);		
		testHelper.testInitString("(new int[][] {{2}, {4}})[0][0]", exp.getExpressionValue()); 
	}	
}
