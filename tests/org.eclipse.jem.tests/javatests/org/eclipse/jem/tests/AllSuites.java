package org.eclipse.jem.tests;
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
 *  $RCSfile: AllSuites.java,v $
 *  $Revision: 1.2 $  $Date: 2003/10/27 17:32:36 $ 
 */
import org.eclipse.jem.tests.beaninfo.BeanInfoSuite;
import org.eclipse.jem.tests.instantiation.InstantiationSuite;
import org.eclipse.jem.tests.proxy.ide.IDEProxySuite;
import org.eclipse.jem.tests.proxy.initParser.InitParserSuite;
import org.eclipse.jem.tests.proxy.remote.RemoteProxySuite;

import junit.framework.Test;
import junit.framework.TestSuite;

public class AllSuites extends TestSuite {
	// Testa cases to be include in the suite
	private static Class suitesList[] = {
		InitParserSuite.class,  
		RemoteProxySuite.class,
		IDEProxySuite.class,
		BeanInfoSuite.class,
		InstantiationSuite.class,
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
