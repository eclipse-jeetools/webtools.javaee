/*
 * Created on Apr 1, 2003
 *
 * To change this generated comment go to 
 * Window>Preferences>Java>Code Generation>Code and Comments
 */
package org.eclipse.wtp.j2ee.headless.tests.plugin;

import java.net.URL;

import junit.framework.Test;
import junit.framework.TestSuite;
import junit.textui.TestRunner;

import org.eclipse.core.runtime.IPluginDescriptor;
import org.eclipse.core.runtime.Platform;
import org.eclipse.jst.j2ee.archive.emftests.AllTests;
import org.eclipse.jst.j2ee.tests.bvt.AutomatedBVT;
import org.eclipse.wst.common.frameworks.artifactedit.tests.ArtifactEditAPITests;
import org.eclipse.wst.common.frameworks.datamodel.tests.DataModelAPITests;
import org.eclipse.wtp.j2ee.headless.tests.plugin.AllPluginTests;


/**
 * @author jsholl
 *
 * To change this generated comment go to 
 * Window>Preferences>Java>Code Generation>Code and Comments
 */
public class AllTestsSuite extends TestSuite {

    public static String baseDirectory = System.getProperty("user.dir") + java.io.File.separatorChar + "TestData" + java.io.File.separatorChar;
    
    static {
        try {
            IPluginDescriptor pluginDescriptor = Platform.getPluginRegistry().getPluginDescriptor("org.eclipse.jst.j2ee.tests");
            URL url = pluginDescriptor.getInstallURL(); 
        	AutomatedBVT.baseDirectory = Platform.asLocalURL(url).getFile() + "TestData"+ java.io.File.separatorChar;
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

    public AllTestsSuite() {
        super();
        TestSuite suite = (TestSuite) AutomatedBVT.suite();
        for (int i = 0; i < suite.testCount(); i++) {
            addTest(suite.testAt(i));
        }
    }

    public static Test suite() {
        TestSuite suite = new TestSuite("Test for org.eclipse.jst.j2ee.test.bvt");
        //WST common
        suite.addTest(org.eclipse.wst.common.frameworks.componentcore.tests.AllTests.suite());
		suite.addTest(DataModelAPITests.suite());
		suite.addTest(ArtifactEditAPITests.suite());
		//j2ee.core
        suite.addTest(AllTests.suite());
        //j2ee
        suite.addTest(AllPluginTests.suite());
        return suite;
    }
}
