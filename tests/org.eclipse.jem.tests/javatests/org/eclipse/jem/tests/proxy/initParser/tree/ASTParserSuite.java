package org.eclipse.jem.tests.proxy.initParser.tree;
/*******************************************************************************
 * Copyright (c)  2003 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials 
 * are made available under the terms of the Common Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/cpl-v10.html
 * 
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *******************************************************************************/
/*
 *  $RCSfile: ASTParserSuite.java,v $
 *  $Revision: 1.2 $  $Date: 2004/01/23 22:53:36 $ 
 */
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.Path;
import org.eclipse.jdt.core.IJavaProject;
import org.eclipse.jdt.core.JavaCore;

import junit.extensions.TestSetup;
import junit.framework.*;
import junit.framework.Test;
import junit.framework.TestSuite;

import org.eclipse.jem.tests.JavaProjectUtil;
import org.eclipse.jem.tests.JavaTestsPlugin;
import org.eclipse.jem.tests.proxy.initParser.*;

/**
 * @author jmyers
 *
 * To change the template for this generated type comment go to
 * Window>Preferences>Java>Code Generation>Code and Comments
 */
public class ASTParserSuite extends TestSetup {

	// Test cases to be include in the suite
	private static Class testsList[] = {
		NeedsCodingTest.class,
		BlockTest.class,
		ASTFieldAccessTest.class,
		ASTArraysTest.class,
		MultiArgStaticMethodTest.class,
		MultiArgConstructorTest.class,
		MultiArgInstanceTest.class,
		NumberTest.class,
		CastTest.class,
//		ExceptionTest.class,	// mixes parse error w/execution errors. Can't test parse errors. Need specific test for exec errors like method not found.
		LiteralTest.class,
		StringTest.class,
		CharTest.class,
		JFCTest.class,
		BorderTest.class,
		OverloadingTest.class,
		SameName46376Test.class,
		ASTOperationsTest.class,
		ASTInnerClassAccessTest.class,
		ASTMiscTest.class
		                               } ;
	
	public static String pkgName = "org.eclipse.jem.tests.proxy.initParser.tree" ;
	    
	/**
	 * Constructor for PackageSuite.
	 */
	public ASTParserSuite() {
		this("AST Parser Suite");
	}

	/**
	 * Constructor for PackageSuite.
	 * @param name
	 */
	public ASTParserSuite(String name) {
		super(new TestSuite(name));
		populateSuite() ;
	}

	private void populateSuite () {
		TestSuite suite = (TestSuite) getTest();
		for (int i=0; i<testsList.length; i++)
		  suite.addTestSuite(testsList[i]) ;
		
		try {
			IProject project = JavaProjectUtil.createEmptyJavaProject(ResourcesPlugin.getWorkspace(), new Path(AST_PROJECT), null);
			IJavaProject jproject = JavaCore.create(project);
			// Add javatests.jar (i.e. this plugins jar) so that the classes within the tests jar are available
			// to the jdt parser for referencing.
			JavaProjectUtil.addPluginJarToPath(JavaTestsPlugin.getPlugin(), "javatests.jar", jproject, null);
			AbstractInitParserTestCase.initSuite(suite, new ASTTreeInitStringParserTestHelper(project));
		} catch (CoreException e) {
			Assert.assertNotNull(e);
		}
	}
    
	public static Test suite() {
		return new ASTParserSuite("Test for: "+pkgName);
	}

	private static final String AST_PROJECT = "/AST tests/";

	/* (non-Javadoc)
	 * @see junit.extensions.TestSetup#tearDown()
	 */
	protected void tearDown() throws Exception {
		JavaProjectUtil.deleteProject(JavaProjectUtil.getProject(AST_PROJECT));
	}

}
