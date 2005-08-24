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
 *  $RCSfile: ASTFieldAccessTest.java,v $
 *  $Revision: 1.5 $  $Date: 2005/08/24 20:58:54 $ 
 */
package org.eclipse.jem.tests.proxy.initParser.tree;

import org.eclipse.jem.internal.proxy.core.IExpression;
import org.eclipse.jem.internal.proxy.initParser.tree.ForExpression;
import org.eclipse.jem.tests.proxy.initParser.AbstractInitParserTestCase;
 
/**
 * 
 * @since 1.0.0
 */
public class ASTFieldAccessTest extends AbstractInitParserTestCase {

	/**
	 * Create with name.
	 * @param name
	 * 
	 * @since 1.0.0
	 */
	public ASTFieldAccessTest(String name) {
		super(name);
	}
	
	protected ASTTreeInitStringParserTestHelper getTreeParser() {
		return (ASTTreeInitStringParserTestHelper) testHelper;
	}
	
	public void testNonQualifiedFieldAccess() throws Throwable {
		getTreeParser().testInitString("Color.red", new String[] {"java.awt.*"}, getTreeParser().getRegistry().getBeanTypeProxyFactory().getBeanTypeProxy("java.awt.Color").newInstance("java.awt.Color.red") );
	}
	
	public void testExpressionFieldAccess() throws Throwable {
		getTreeParser().testInitString("(Color.red).red", new String[] {"java.awt.*"}, getTreeParser().getRegistry().getBeanTypeProxyFactory().getBeanTypeProxy("java.awt.Color").newInstance("java.awt.Color.red") );	// Should not use this form, but it is valid, use valid form for expected results so no warnings.
	}
	
	public void testNestedFieldAccess() throws Throwable {
		getTreeParser().testInitString("ASTNestFieldAccessTestData.acolor.red", new String[] {"org.eclipse.jem.tests.proxy.initParser.tree.ASTNestFieldAccessTestData"},  getTreeParser().getRegistry().getBeanTypeProxyFactory().getBeanTypeProxy("java.awt.Color").newInstance("java.awt.Color.red"));	// Should not use this form, but it is valid, use valid form for expected results so no warnings.
	}

	public void testNestedFieldExpressionAccess() throws Throwable {
		getTreeParser().testInitString("new ASTNestFieldAccessTestData().acolor.red", new String[] {"org.eclipse.jem.tests.proxy.initParser.tree.ASTNestFieldAccessTestData"},  getTreeParser().getRegistry().getBeanTypeProxyFactory().getBeanTypeProxy("java.awt.Color").newInstance("java.awt.Color.red"));	// Should not use this form, but it is valid, use valid form for expected results so no warnings.
	}
	
	public void testNonstaticFieldAccess() throws Throwable {
		IExpression exp = getTreeParser().getRegistry().getBeanProxyFactory().createExpression();
		exp.createFieldAccess(ForExpression.ROOTEXPRESSION, "arect", true);
		exp.createClassInstanceCreation(ForExpression.FIELD_RECEIVER, "org.eclipse.jem.tests.proxy.initParser.tree.ASTNestFieldAccessTestData", 0);
		getTreeParser().testInitString("new ASTNestFieldAccessTestData().arect", new String[] {"org.eclipse.jem.tests.proxy.initParser.tree.ASTNestFieldAccessTestData"},  exp.getExpressionValue());
	}
	
	public void testNonstaticNestedFieldAccess() throws Throwable {
		IExpression exp = getTreeParser().getRegistry().getBeanProxyFactory().createExpression();
		exp.createFieldAccess(ForExpression.ROOTEXPRESSION, "x", true);
		exp.createFieldAccess(ForExpression.FIELD_RECEIVER, "arect", true);
		exp.createClassInstanceCreation(ForExpression.FIELD_RECEIVER, "org.eclipse.jem.tests.proxy.initParser.tree.ASTNestFieldAccessTestData", 0);
		getTreeParser().testInitString("new ASTNestFieldAccessTestData().arect.x", new String[] {"org.eclipse.jem.tests.proxy.initParser.tree.ASTNestFieldAccessTestData"},  exp.getExpressionValue());
	}
	
}
