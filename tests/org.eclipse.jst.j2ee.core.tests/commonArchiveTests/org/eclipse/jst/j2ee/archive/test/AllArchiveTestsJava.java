package org.eclipse.jst.j2ee.archive.test;

 /*
 * Licensed Material - Property of IBM
 * (C) Copyright IBM Corp. 2001 - All Rights Reserved.
 * US Government Users Restricted Rights - Use, duplication or disclosure
 * restricted by GSA ADP Schedule Contract with IBM Corp.
 */

import junit.framework.TestCase;
import junit.framework.TestSuite;
import junit.swingui.TestRunner;
/**
 * Insert the type's description here.
 * Creation date: (12/19/00 4:44:04 PM)
 * @author: Administrator
 */
public class AllArchiveTestsJava extends TestCase {
	private final static String copyright = "(c) Copyright IBM Corporation 2001.";//$NON-NLS-1$
/**
 * AllTests constructor comment.
 * @param name java.lang.String
 */
public AllArchiveTestsJava(String name) {
	super(name);
}
/**
 * Starts the application.
 * @param args an array of command-line arguments
 */
public static void main(java.lang.String[] args) {
	String[] className = {"com.ibm.etools.archive.test.AllTests", "-noloading"};
	TestRunner.main(className);
}
public static junit.framework.Test suite() {
	TestSuite suite = new TestSuite("Test for com.ibm.etools.archive.test");
	suite.addTest(DiscriminatorTest.suite());
	suite.addTest(LooseArchiveTests.suite());
	suite.addTest(InitializationTests.suite());
	suite.addTest(SimpleTests.suite());
	suite.addTest(CopyTests.suite());
//	suite.addTest(EJBJarConversionTests.suite());
	suite.addTest(WARFileTests.suite());
	suite.addTest(SaveTests.suite());
	suite.addTest(JavaTests.suite());
//	suite.addTest(IDTests.suite());
	//suite.addTest(AltDDTests.suite());
	suite.addTest(SecurityRoleTests.suite());
	suite.addTest(RarFileTests.suite());
	suite.addTest(ResourceTests.suite());
	suite.addTest(ClientContainerResourceLoadTest.suite());
	suite.addTest(TestModuleClassLoading.suite());
	suite.addTest(TestInvalidXmlMultiplicity.suite());
	suite.addTest(DefectVerificationTests.suite());
	
	return suite;
}
}
