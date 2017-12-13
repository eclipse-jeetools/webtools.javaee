/*
 * Created on Feb 9, 2005
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package org.eclipse.jst.validation.test.junit;


import java.io.File;
import java.net.URL;

import junit.framework.Test;
import junit.framework.TestSuite;
import junit.textui.TestRunner;

import org.eclipse.core.runtime.FileLocator;
import org.eclipse.jst.validation.api.test.ApiTestsSuite;
import org.eclipse.jst.validation.test.BVTValidationPlugin;


/**
 * @author jsholl
 * @author jialin
 *
 */
public class AutomatedValidationBVT extends TestSuite {

    public static String baseDirectory = null;
    
    static {
        try {
        	URL url = BVTValidationPlugin.getPlugin().getBundle().getEntry("/");
        	AutomatedValidationBVT.baseDirectory = FileLocator.toFileURL(url).getFile() + "validationResources" + File.separatorChar;
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

    public AutomatedValidationBVT() {
        super();
        TestSuite suite = (TestSuite) AutomatedValidationBVT.suite();
        for (int i = 0; i < suite.testCount(); i++) {
            addTest(suite.testAt(i));
        }
    }

    public static Test suite() {
        TestSuite suite = new TestSuite("Test for org.eclipse.jst.validation.test");
        //suite.addTest(BVTSuite.suite());
		suite.addTest(ApiTestsSuite.suite());
        return suite;
    }
}
