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
 *  $RCSfile: ASTFieldAccessTest.java,v $
 *  $Revision: 1.1 $  $Date: 2004/01/23 22:53:36 $ 
 */
package org.eclipse.jem.tests.proxy.initParser.tree;

import java.awt.Color;

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
		getTreeParser().testInitString("Color.red", new String[] {"java.awt.*"}, Color.red);
	}
	
	public void testExpressionFieldAccess() throws Throwable {
		getTreeParser().testInitString("(Color.red).red", new String[] {"java.awt.*"}, Color.red);	// Should not use this form, but it is valid, use valid form for expected results so no warnings.
	}
	
	public void testNestedFieldAccess() throws Throwable {
		getTreeParser().testInitString("ASTNestFieldAccessTestData.acolor.red", new String[] {"org.eclipse.jem.tests.proxy.initParser.tree.ASTNestFieldAccessTestData"},  Color.red);	// Should not use this form, but it is valid, use valid form for expected results so no warnings.
	}

	public void testNestedFieldExpressionAccess() throws Throwable {
		getTreeParser().testInitString("new ASTNestFieldAccessTestData().acolor.red", new String[] {"org.eclipse.jem.tests.proxy.initParser.tree.ASTNestFieldAccessTestData"},  Color.red);	// Should not use this form, but it is valid, use valid form for expected results so no warnings.
	}
	
	public void testNonstaticFieldAccess() throws Throwable {
		getTreeParser().testInitString("new ASTNestFieldAccessTestData().arect", new String[] {"org.eclipse.jem.tests.proxy.initParser.tree.ASTNestFieldAccessTestData"},  new ASTNestFieldAccessTestData().arect);
	}
	
	public void testNonstaticNestedFieldAccess() throws Throwable {
		getTreeParser().testInitString("new ASTNestFieldAccessTestData().arect.x", new String[] {"org.eclipse.jem.tests.proxy.initParser.tree.ASTNestFieldAccessTestData"},  new Integer(new ASTNestFieldAccessTestData().arect.x));
	}
	
}
