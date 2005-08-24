/*******************************************************************************
 * Copyright (c) 2003, 2005 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *******************************************************************************/
package org.eclipse.jem.tests;
/*
 *  $RCSfile: AllSuites.java,v $
 *  $Revision: 1.7 $  $Date: 2005/08/24 20:58:54 $ 
 */
import org.eclipse.jem.tests.beaninfo.BeanInfoSuite;
import org.eclipse.jem.tests.instantiation.InstantiationSuite;
import org.eclipse.jem.tests.modelListeners.ListenersSuite;
import org.eclipse.jem.tests.proxy.ide.IDEProxySuite;
import org.eclipse.jem.tests.proxy.initParser.InitParserSuite;
import org.eclipse.jem.tests.proxy.initParser.tree.ASTParserSuite;
import org.eclipse.jem.tests.proxy.remote.RemoteProxySuite;

import junit.framework.Test;
import junit.framework.TestSuite;

public class AllSuites extends TestSuite {
	// Testa cases to be include in the suite
	private static Class suitesList[] = {
		InitParserSuite.class,  
		ASTParserSuite.class,
		RemoteProxySuite.class,
		IDEProxySuite.class,
		BeanInfoSuite.class,
		InstantiationSuite.class,
		ListenersSuite.class,
		                                 } ;
	public static String pkgName = "Java EMF Model jUnit Test Suite" ;
    
	/**
	 * Constructor for PackageSuite.
	 */
	public AllSuites() {
		super();
		populateSuite() ;
	}

	/**
	 * Constructor for PackageSuite.
	 * @param theClass
	 */
	public AllSuites(Class theClass) {
		super(theClass);
		populateSuite() ;
	}

	/**
	 * Constructor for PackageSuite.
	 * @param name
	 */
	public AllSuites(String name) {
		super(name);
		populateSuite() ;
	}

    private void populateSuite () {
    	for (int i=0; i<suitesList.length; i++)
    	  try {
			Test ts = (Test) suitesList[i].newInstance() ;
			addTest(ts) ;
		  }
		  catch (Exception e) {}		 
    }
    
	public static Test suite() {
		return new AllSuites(pkgName);
	}

}
