/*
 * Created on Apr 1, 2003
 *
 * To change this generated comment go to 
 * Window>Preferences>Java>Code Generation>Code and Comments
 */
package org.eclipse.jst.j2ee.archive.emftests;

import org.eclipse.jst.j2ee.core.tests.api.AllAPITest;

import junit.framework.Test;
import junit.framework.TestSuite;

/**
 * @author jsholl
 *
 * To change this generated comment go to 
 * Window>Preferences>Java>Code Generation>Code and Comments
 */
public class AllTests {

    public static Test suite() {
        TestSuite suite = new TestSuite("Test for com.ibm.etools.archive.emftest");
        //$JUnit-BEGIN$

		suite.addTest(AllDOMTests.suite());
		suite.addTest(AllSAXTests.suite());
		suite.addTest(AllAPITest.suite());

		//$JUnit-END$
		return suite;
	}
	
	public static void main(java.lang.String[] args) {
		junit.textui.TestRunner.main(new String[] { AllTests.class.getName() });
	}

}
