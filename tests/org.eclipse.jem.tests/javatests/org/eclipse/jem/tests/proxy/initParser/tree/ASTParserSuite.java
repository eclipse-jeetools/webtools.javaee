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
 *  $Revision: 1.1 $  $Date: 2004/01/19 22:50:22 $ 
 */
import junit.framework.Test;
import junit.framework.TestSuite;

import org.eclipse.jem.tests.proxy.initParser.*;

/**
 * @author jmyers
 *
 * To change the template for this generated type comment go to
 * Window>Preferences>Java>Code Generation>Code and Comments
 */
public class ASTParserSuite extends TestSuite {

	// Test cases to be include in the suite
	private static Class testsList[] = {
//		NeedsCodingTest.class,	// tested
//		BlockTest.class,	// tested
		ASTArraysTest.class,
//		MultiArgStaticMethodTest.class,	// tested
//		MultiArgConstructorTest.class,	// tested
//		MultiArgInstanceTest.class,	// tested
//		NumberTest.class,	// tested
//		CastTest.class,	// tested
//		ExceptionTest.class,
//		LiteralTest.class,	// tested
//		StringTest.class,	// tested
//		CharTest.class,	// tested
//		JFCTest.class,
//		BorderTest.class,
//		OverloadingTest.class,
//		SameName46376Test.class
		                               } ;
	public static String pkgName = "org.eclipse.jem.tests.proxy.initParser.tree" ;
	    
	/**
	 * Constructor for PackageSuite.
	 */
	public ASTParserSuite() {
		super();
		populateSuite() ;
	}

	/**
	 * Constructor for PackageSuite.
	 * @param theClass
	 */
	public ASTParserSuite(Class theClass) {
		super(theClass);
		populateSuite() ;
	}

	/**
	 * Constructor for PackageSuite.
	 * @param name
	 */
	public ASTParserSuite(String name) {
		super(name);
		populateSuite() ;
	}

	private void populateSuite () {
		for (int i=0; i<testsList.length; i++)
		  addTestSuite(testsList[i]) ;
		
		AbstractInitParserTestCase.initSuite(this, new ASTTreeInitStringParserTestHelper());
	}
    
	public static Test suite() {
		return new ASTParserSuite("Test for: "+pkgName);
	}

}
