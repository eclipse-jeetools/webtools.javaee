/*
 * Created on Apr 1, 2003
 *
 * To change this generated comment go to 
 * Window>Preferences>Java>Code Generation>Code and Comments
 */
package org.eclipse.jst.j2ee.core.api.test;

import org.eclipse.jst.j2ee.core.tests.api.AllAPITest;

import junit.framework.Test;
import junit.framework.TestSuite;

/**
 * To change this generated comment go to 
 * Window>Preferences>Java>Code Generation>Code and Comments
 */
public class AllCoreAPITests {

    public static Test suite() {
        TestSuite suite = new TestSuite("Test for J2EE Core Api Test");
        //$JUnit-BEGIN$
		suite.addTest(AllAPITest.suite());

		//$JUnit-END$
		return suite;
	}
	
	public static void main(java.lang.String[] args) {
		junit.textui.TestRunner.main(new String[] { AllCoreAPITests.class.getName() });
	}

}
