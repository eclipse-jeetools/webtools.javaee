/*
 * Created on Apr 1, 2003
 *
 * To change this generated comment go to 
 * Window>Preferences>Java>Code Generation>Code and Comments
 */
package org.eclipse.jst.j2ee.java.testing;

import junit.framework.Test;
import junit.framework.TestSuite;

/**
 * @author jsholl
 *
 * To change this generated comment go to 
 * Window>Preferences>Java>Code Generation>Code and Comments
 */
public class AllJavaTestsJava {

    public static Test suite() {
        TestSuite suite = new TestSuite("Test for org.eclipse.jem.internal.java.testing");
        //$JUnit-BEGIN$
        suite.addTest(JavaBasicTests.suite());
        //$JUnit-END$
        return suite;
    }
}
