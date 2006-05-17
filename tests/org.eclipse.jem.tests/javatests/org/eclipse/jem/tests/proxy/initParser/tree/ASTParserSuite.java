/*******************************************************************************
 * Copyright (c) 2003, 2006 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *******************************************************************************/
package org.eclipse.jem.tests.proxy.initParser.tree;
/*
 *  $RCSfile: ASTParserSuite.java,v $
 *  $Revision: 1.12 $  $Date: 2006/05/17 20:13:56 $ 
 */
import junit.extensions.TestSetup;
import junit.framework.*;
import junit.framework.Assert;

import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.*;
import org.eclipse.jdt.core.IJavaProject;
import org.eclipse.jdt.core.JavaCore;

import org.eclipse.jem.internal.proxy.core.*;
import org.eclipse.jem.tests.JavaProjectUtil;
import org.eclipse.jem.tests.JavaTestsPlugin;
import org.eclipse.jem.tests.proxy.ProxySuite;
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
		ASTMiscTest.class,
		TestCompatibleMethods.class,
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

	ProxyFactoryRegistry registry;
	
	private void populateSuite () {
		TestSuite suite = (TestSuite) getTest();
		for (int i=0; i<testsList.length; i++)
		  suite.addTestSuite(testsList[i]) ;
		
		try {
			IProject project = JavaProjectUtil.createEmptyJavaProject(ResourcesPlugin.getWorkspace(), new Path(AST_PROJECT), null);
			IJavaProject jproject = JavaCore.create(project);
			// Add javatests.jar (i.e. this plugins jar) so that the classes within the tests jar are available
			// to the jdt parser for referencing.
			JavaProjectUtil.addBundleJarToPath(JavaTestsPlugin.getPlugin().getBundle(), "javatests.jar", jproject, null);
			
			// We're going to use the remote proxy, because it is easier to start up, but we aren't really testing
			// the remote proxy expression evaluation. We are really testing AST->ParseTree->BeanProxy. If any expression
			// type errors show up (i.e. the proxy expression itself is not working correctly). Then that should be fixed
			// and a junit test in the proxy suite should be created instead.
			registry = ProxyLaunchSupport.startImplementation(
					project,
					"JUnit Parse Tree Test",
					new IConfigurationContributor[] { ProxySuite.getProxySuiteContributor()},
					new NullProgressMonitor());
			
			AbstractInitParserTestCase.initSuite(suite, new ASTTreeInitStringParserTestHelper(project, registry));
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
		if (registry != null) {
			registry.terminateRegistry();
			Thread.sleep(5000);	// Give it five seconds to REALLY go away. There is a small window between 
			// terminate request and true shutdown of the remote vm. We return immediately and let a
			// job wait for the actual true termination.
		}
		
		JavaProjectUtil.deleteProject(JavaProjectUtil.getProject(AST_PROJECT));
	}

}
