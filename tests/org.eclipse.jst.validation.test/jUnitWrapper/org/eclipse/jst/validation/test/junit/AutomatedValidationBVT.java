/*
 * Created on Feb 9, 2005
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package org.eclipse.jst.validation.test.junit;

/**
 * @author jialin
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */

import java.io.File;
import java.io.IOException;
import java.net.URL;

import junit.framework.Test;
import junit.framework.TestSuite;
import junit.textui.TestRunner;

import org.eclipse.core.runtime.IPluginDescriptor;
import org.eclipse.core.runtime.Platform;


/**
 * @author jsholl
 *
 * To change this generated comment go to 
 * Window>Preferences>Java>Code Generation>Code and Comments
 */
public class AutomatedValidationBVT extends TestSuite {

    public static String baseDirectory = null;
    
//    static {
//        try {
//            Bundle plugin = Platform.getBundle("org.eclipse.jst.validation.test");
//            URL url = plugin.getEntry("/");
//        	AutomatedValidationBVT.baseDirectory = Platform.asLocalURL(url).getFile() + "validationResources" + File.separatorChar;
//		} catch (Exception e) { 
//			System.err.println("Using working directory since a workspace URL could not be located.");
//		} 
//    }

    public static int unimplementedMethods;

    public static void main(String[] args) {
        unimplementedMethods = 0;
        TestRunner.run(suite());
        if (unimplementedMethods > 0) {
            System.out.println("\nCalls to warnUnimpl: " + unimplementedMethods);
        }
    }

    public AutomatedValidationBVT() {
		

		super();
		IPluginDescriptor pluginDescriptor = Platform.getPluginRegistry().getPluginDescriptor("org.eclipse.jst.validation.test");
        URL url = pluginDescriptor.getInstallURL();
        try {
        	AutomatedValidationBVT.baseDirectory = Platform.asLocalURL(url).getFile() + "validationResources" + File.separatorChar;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
        TestSuite suite = (TestSuite) AutomatedValidationBVT.suite();
        for (int i = 0; i < suite.testCount(); i++) {
            addTest(suite.testAt(i));
        }
    }

    public static Test suite() {
        TestSuite suite = new TestSuite("Test for org.eclipse.jst.validation.test");
        suite.addTest(new BVTSuite());
        return suite;
    }
}
