/*******************************************************************************
 * Copyright (c) 2003, 2004 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *******************************************************************************/
package org.eclipse.jem.tests.proxy.initParser;
/*
 *  $RCSfile: InitParserSuite.java,v $
 *  $Revision: 1.6 $  $Date: 2005/02/15 23:00:16 $ 
 */
import junit.framework.Test;
import junit.framework.TestSuite;

/**
 * @author jmyers
 *
 * To change the template for this generated type comment go to
 * Window>Preferences>Java>Code Generation>Code and Comments
 */
public class InitParserSuite extends TestSuite {

	// Test cases to be include in the suite
	private static Class testsList[] = {
		NeedsCodingTest.class,
		BlockTest.class,
		ArrayConstructorTest.class,
		MultiArgStaticMethodTest.class,
		MultiArgConstructorTest.class,
		MultiArgInstanceTest.class,
		NumberTest.class,
		CastTest.class,
		ExceptionTest.class,
		LiteralTest.class,
		StringTest.class,
		CharTest.class,
		JFCTest.class,
		BorderTest.class,
		OverloadingTest.class,
		SameName46376Test.class
		                               } ;
	public static String pkgName = "org.eclipse.jem.tests.proxy.initParser" ;
	    
	/**
	 * Constructor for PackageSuite.
	 */
	public InitParserSuite() {
		super();
		populateSuite() ;
	}

	/**
	 * Constructor for PackageSuite.
	 * @param theClass
	 */
	public InitParserSuite(Class theClass) {
		super(theClass);
		populateSuite() ;
	}

	/**
	 * Constructor for PackageSuite.
	 * @param name
	 */
	public InitParserSuite(String name) {
		super(name);
		populateSuite() ;
	}

	private void populateSuite () {
		for (int i=0; i<testsList.length; i++)
		  addTestSuite(testsList[i]) ;
		
		AbstractInitParserTestCase.initSuite(this, new InitStringParserTestHelper(this.getClass().getClassLoader()));
	}
    
	public static Test suite() {
		return new InitParserSuite("Test for: "+pkgName);
	}

}
