/*
 * Created on Feb 2, 2004
 * 
 * To change the template for this generated file go to Window - Preferences - Java - Code Generation - Code and
 * Comments
 */
package org.eclipse.wtp.j2ee.headless.tests.plugin;

import junit.framework.Test;
import junit.framework.TestSuite;

/**
 * @author jsholl
 * 
 * To change the template for this generated type comment go to Window - Preferences - Java - Code Generation - Code
 * and Comments
 */
public class AllPluginTests extends TestSuite {

    public static Test suite() {
        return new AllPluginTests();
    }

    public AllPluginTests() {
        super();
        addTest(org.eclipse.wtp.j2ee.headless.tests.ejb.operations.AllTests.suite());
        addTest(org.eclipse.wtp.j2ee.headless.tests.web.operations.AllTests.suite());
        addTest(org.eclipse.wtp.j2ee.headless.tests.jca.operations.AllTests.suite());
        addTest(org.eclipse.wtp.j2ee.headless.tests.ear.operations.AllTests.suite());
        addTest(org.eclipse.wtp.j2ee.headless.tests.appclient.operations.AllTests.suite());
    }

}
