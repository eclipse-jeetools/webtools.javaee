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
 *  $RCSfile: ASTTreeInitStringParserTestHelper.java,v $
 *  $Revision: 1.1 $  $Date: 2004/01/19 22:50:22 $ 
 */
package org.eclipse.jem.tests.proxy.initParser.tree;

import java.text.MessageFormat;

import junit.framework.Assert;

import org.eclipse.jdt.core.compiler.IProblem;
import org.eclipse.jdt.core.dom.*;
import org.eclipse.jdt.core.dom.AST;
import org.eclipse.jdt.core.dom.CompilationUnit;

import org.eclipse.jem.tests.proxy.initParser.AbstractInitStringParserTestHelper;
 
/**
 * Init String parser helper for working with AST trees.
 *  
 * @since 1.0.0
 */
public class ASTTreeInitStringParserTestHelper extends AbstractInitStringParserTestHelper {

	private static final String TEMPLATE_CLASS = "public class TEMPLATE '{'\n  public Object test() '{'\n    return {0};\n  }\n}";
	/* (non-Javadoc)
	 * @see org.eclipse.jem.tests.proxy.initParser.AbstractInitStringParserTestHelper#testInitString(java.lang.String, java.lang.Object, boolean, boolean)
	 */
	public void testInitString(String aString, Object expectedResult, boolean throwsException, boolean equalsOnly) throws Throwable {
		String testClass = MessageFormat.format(TEMPLATE_CLASS, new Object[] {aString});
		CompilationUnit cu = AST.parseCompilationUnit(testClass.toCharArray());
		IProblem[] problems = cu.getProblems();
		if (problems.length > 0) {
			StringBuffer buf = new StringBuffer(100);
			for (int i = 0; i < problems.length; i++) {
				buf.append(" "+problems[i].getMessage());
			}
			Assert.fail("Problems with \""+aString+"\": "+ buf);
		}
		Expression exp = ((ReturnStatement) ((TypeDeclaration) cu.types().get(0)).getMethods()[0].getBody().statements().get(0)).getExpression();
	}

}
