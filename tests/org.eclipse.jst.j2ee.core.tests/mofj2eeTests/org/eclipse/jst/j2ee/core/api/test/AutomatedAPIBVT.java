/*
 * Created on Apr 1, 2003
 *
 * To change this generated comment go to 
 * Window>Preferences>Java>Code Generation>Code and Comments
 */
package org.eclipse.jst.j2ee.core.api.test;

import java.net.URL;

import junit.framework.Test;
import junit.framework.TestSuite;
import junit.textui.TestRunner;

import org.eclipse.core.runtime.IPluginDescriptor;
import org.eclipse.core.runtime.Platform;
import org.eclipse.jst.j2ee.archive.emftests.AllTests;


/**
 * @author jsholl
 *
 * To change this generated comment go to 
 * Window>Preferences>Java>Code Generation>Code and Comments
 */
public class AutomatedAPIBVT extends TestSuite {

    public static String baseDirectory = System.getProperty("user.dir") + java.io.File.separatorChar + "commonArchiveResources" + java.io.File.separatorChar;
    
    static {
        try {
            IPluginDescriptor pluginDescriptor = Platform.getPluginRegistry().getPluginDescriptor("org.eclipse.jst.j2ee.core.tests");
            URL url = pluginDescriptor.getInstallURL(); 
        	AutomatedAPIBVT.baseDirectory = Platform.asLocalURL(url).getFile() + "commonArchiveResources"+ java.io.File.separatorChar;
		} catch (Exception e) { 
			System.err.println("Using working directory since a workspace URL could not be located.");
		} 
    }

    public static int unimplementedMethods;

    public static void main(String[] args) {
        unimplementedMethods = 0;
        TestRunner.run(suite());
        if (unimplementedMethods > 0) {
            System.out.println("\nCalls to warnUnimpl: " + unimplementedMethods);
        }
    }

    public AutomatedAPIBVT() {
        super();
        TestSuite suite = (TestSuite) AutomatedAPIBVT.suite();
        for (int i = 0; i < suite.testCount(); i++) {
            addTest(suite.testAt(i));
        }
    }

    public static Test suite() {
        TestSuite suite = new TestSuite("Test for org.eclipse.jst.j2ee.core.api.bvt");
        //$JUnit-BEGIN$
        suite.addTest(AllCoreAPITests.suite());
        //$JUnit-END$
        return suite;
    }
}
