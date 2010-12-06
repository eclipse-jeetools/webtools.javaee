/*******************************************************************************
 * Copyright (c) 2010 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *******************************************************************************/

package org.eclipse.jst.jee.tests.bvt;

import java.net.URL;

import junit.framework.Test;
import junit.framework.TestSuite;
import junit.textui.TestRunner;

import org.eclipse.core.runtime.IPluginDescriptor;
import org.eclipse.core.runtime.Platform;
import org.eclipse.wtp.jee.headless.tests.plugin.AllPluginTests;


public class AutomatedBVT extends TestSuite {

    public static String baseDirectory = System.getProperty("user.dir") + java.io.File.separatorChar + "TestData" + java.io.File.separatorChar;
    
    static {
        try {
            IPluginDescriptor pluginDescriptor = Platform.getPluginRegistry().getPluginDescriptor("org.eclipse.jst.jee.tests");
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

    public AutomatedBVT() {
        super();
        TestSuite suite = (TestSuite) AutomatedBVT.suite();
        for (int i = 0; i < suite.testCount(); i++) {
            addTest(suite.testAt(i));
        }
    }

    public static Test suite() {
        TestSuite suite = new TestSuite("Test for org.eclipse.jst.jee.test.bvt");
        suite.addTest(AllPluginTests.suite());
        return suite;
    }
}
