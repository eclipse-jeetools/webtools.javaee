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
 *  $Revision: 1.2 $  $Date: 2004/01/23 22:53:36 $ 
 */
package org.eclipse.jem.tests.proxy.initParser.tree;

import java.text.MessageFormat;

import junit.framework.Assert;

import org.eclipse.core.resources.IProject;
import org.eclipse.jdt.core.IJavaProject;
import org.eclipse.jdt.core.JavaCore;
import org.eclipse.jdt.core.compiler.IProblem;
import org.eclipse.jdt.core.dom.*;

import org.eclipse.jem.internal.instantiation.PTExpression;
import org.eclipse.jem.tests.proxy.initParser.AbstractInitStringParserTestHelper;
import org.eclipse.jem.workbench.utility.ASTBoundResolver;
import org.eclipse.jem.workbench.utility.ParseTreeCreationFromAST;
 
/**
 * Init String parser helper for working with AST trees.
 *  
 * @since 1.0.0
 */
public class ASTTreeInitStringParserTestHelper extends AbstractInitStringParserTestHelper {

	private static final String TEMPLATE_CLASS = "public class TEMPLATE '{'\n  public void test() '{'\n    String.valueOf({0});\n  }\n}";
	private static final String TEMPLATE_CLASS_IMPORTS = "{0}\npublic class TEMPLATE '{'\n  public void test() '{'\n    String.valueOf({1});\n  }\n}";
	
	private IJavaProject project;
	private ParseTreeCreationFromAST parser = new ParseTreeCreationFromAST(new ASTBoundResolver());
	
	public ASTTreeInitStringParserTestHelper() {
	}
	
	public ASTTreeInitStringParserTestHelper(IProject project) {
		this.project = JavaCore.create(project);
	}
	/* (non-Javadoc)
	 * @see org.eclipse.jem.tests.proxy.initParser.AbstractInitStringParserTestHelper#testInitString(java.lang.String, java.lang.Object, boolean, boolean)
	 */
	public void testInitString(String aString, Object expectedResult, boolean throwsException, boolean equalsOnly) throws Throwable {
		String testClass = MessageFormat.format(TEMPLATE_CLASS, new Object[] {aString});
		CompilationUnit cu = project != null ? AST.parseCompilationUnit(testClass.toCharArray(), "TEMPLATE.java", project) : AST.parseCompilationUnit(testClass.toCharArray());
		testInitString(aString, cu, expectedResult, throwsException, equalsOnly);
	}

	public void testInitString(String aString, String[] imports, Object expectedResult) throws Throwable {
		testInitString(aString, imports,expectedResult, false, true);
	}
	
	public void testInitString(String aString, String[] imports, Object expectedResult, boolean throwsException, boolean equalsOnly) throws Throwable {
		StringBuffer importLines = new StringBuffer(100);
		for (int i = 0; i < imports.length; i++) {
			importLines.append("import ");
			importLines.append(imports[i]);
			importLines.append(";\n");
		}
		String testClass = MessageFormat.format(TEMPLATE_CLASS_IMPORTS, new Object[] {importLines, aString});
		CompilationUnit cu = project != null ? AST.parseCompilationUnit(testClass.toCharArray(), "TEMPLATE.java", project) : AST.parseCompilationUnit(testClass.toCharArray());
		testInitString(aString, cu, expectedResult, throwsException, equalsOnly);
	}
	
	protected void testInitString(String aString, CompilationUnit cu, Object expectedResult, boolean throwsException, boolean equalsOnly) throws Throwable {
		IProblem[] problems = cu.getProblems();
		if (problems.length > 0) {
			boolean errors = false;
			StringBuffer buf = new StringBuffer(100);
			for (int i = 0; i < problems.length; i++) {
				errors = errors | problems[i].isError();
				buf.append(" "+problems[i].getMessage());
			}
			// If only warnings, try going on. Only errors should cause a failure.
			if (errors)
				Assert.fail("Problems with \""+aString+"\": "+ buf);
			else {
				// Else just log the warnings.
				System.err.println("Warnings ocurred for \""+aString+"\":"+buf);
			}
		}
		
		TypeDeclaration td = (TypeDeclaration) cu.types().get(0);
		ExpressionStatement es = (ExpressionStatement) td.getMethods()[0].getBody().statements().get(0);
		MethodInvocation mi = (MethodInvocation) es.getExpression();
		org.eclipse.jdt.core.dom.Expression exp = (org.eclipse.jdt.core.dom.Expression) mi.arguments().get(0);
		PTExpression parseExp = parser.createExpression(exp);
	}

}
