/**********************************************************************
 * Copyright (c) 2004, 2005 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
�*
 * Contributors:
 *    IBM Corporation - Initial API and implementation
 **********************************************************************/
package org.eclipse.jst.j2ee.ejb.generation.tests;

import junit.framework.Test;
import junit.framework.TestSuite;

import org.eclipse.jst.j2ee.ejb.generation.tests.common.EjbModuleCreationTest;
import org.eclipse.jst.j2ee.ejb.generation.tests.common.EnterpriseBeanModelTest;
import org.eclipse.jst.j2ee.ejb.generation.tests.common.ExistenceTest;
import org.eclipse.jst.j2ee.ejb.generation.tests.common.TestSettings;

public class AllTests {
	public static Test suite() {
		TestSuite suite = new TestSuite("Test for org.eclipse.jst.j2ee.ejb.generation.tests");
		//$JUnit-BEGIN$
		System.setProperty("wtp.autotest.noninteractive", "true");
		
		//$JUnit-BEGIN$
		String s = System.getProperty(TestSettings.runtimeid); 
		//s = "D:\\Tools\\tomcat\\jakarta-tomcat-3.2.4";
		if (s != null && s.length() > 0) {
			TestSettings.serverlocation = s;
		}
		s = System.getProperty("org.eclipse.jst.xdoclet.version"); 
		//s = "1.2.3";
		if (s != null && s.length() > 0) {
			TestSettings.xdocletversion = s;
		}
		
		s = System.getProperty("org.eclipse.jst.xdoclet.location"); 
		//s = "D:\\Tools\\tomcat\\jakarta-tomcat-3.2.4";
		if (s != null && s.length() > 0) {
			TestSettings.xdocletlocation = s;
		}

		suite.addTestSuite(EnterpriseBeanModelTest.class);
		suite.addTest(new OrderedTestSuite(EjbModuleCreationTest.class));
		suite.addTestSuite(ExistenceTest.class);
		
		s = System.getProperty("org.eclipse.jst.server.generic.jonas4"); 
		if (s != null && s.length() > 0) {
		} 
		return suite;
	}
}