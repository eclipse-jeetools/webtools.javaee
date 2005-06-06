package org.eclipse.jst.j2ee.ejb.generation.tests.common;

import junit.framework.Test;
import junit.framework.TestSuite;

public class AllEJBGenerationTests {

	public static Test suite() {
		TestSuite suite = new TestSuite("Test for org.eclipse.jst.j2ee.ejb.generation.tests.common");
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
		suite.addTestSuite(EjbModuleCreationTest.class);
		suite.addTestSuite(ExistenceTest.class);
		
		
		//$JUnit-END$
		return suite;
	}
	
	

}
