package org.eclipse.jem.tests.proxy;
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
 *  $RCSfile: ProxySuite.java,v $
 *  $Revision: 1.2 $  $Date: 2003/10/27 17:32:36 $ 
 */
import java.util.List;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.jem.internal.proxy.core.*;
import org.eclipse.jem.internal.proxy.core.IConfigurationContributor;
import org.eclipse.jem.tests.JavaTestsPlugin;
import org.eclipse.jdt.launching.VMRunnerConfiguration;

import junit.framework.TestSuite;

/**
 * @author richkulp
 * 
 * This suite is for testing straight Proxy independent of the type of proxy (e.g. Remote or IDE).
 * It can't run by itself. It needs to have the registry creater object stored in it so that
 * the appropriate registry can be created and recreated as necessary by the test.
 * 
 * If the tests inherit from AbstractTestProxy the setRegistryHandler will be called on it.
 */
public class ProxySuite extends TestSuite {


	// Test cases to be include in the suite
	private static Class testsList[] = {
		TestStandard.class,
		TestAWTProxy.class,
									   } ;
									   
	public static String pkgName = "org.eclipse.jem.tests.proxy" ;
	    
	/**
	 * Constructor for PackageSuite.
	 */
	public ProxySuite() {
		super();
		populateSuite();
	}

	/**
	 * Constructor for PackageSuite.
	 * @param theClass
	 */
	public ProxySuite(Class theClass) {
		super(theClass);		
		populateSuite();
	}

	/**
	 * Constructor for PackageSuite.
	 * @param name
	 */
	public ProxySuite(String name) {
		super(name);		
		populateSuite() ;
	}

	private void populateSuite () {
		for (int i=0; i<testsList.length; i++)
		  addTestSuite(testsList[i]) ;
	}
	
	/**
	 * Return a contributor to be used when starting tests so that ProxySuite can contribute to it.
	 * @return A contributor for Proxy Suite tests
	 */
	public static IConfigurationContributor getProxySuiteContributor() {
		return new IConfigurationContributor() {
			public void contributeClasspaths(List classpaths, IClasspathContributionController controller) throws CoreException {
				controller.contributeClasspath(ProxyPlugin.getPlugin().localizeFromPlugin(JavaTestsPlugin.getPlugin(), "vm/tests.jar"), classpaths, 0);
			}

			public void contributeToConfiguration(VMRunnerConfiguration config) {
			}

			public void contributeToRegistry(ProxyFactoryRegistry registry) {
			}
		};
	}
}
