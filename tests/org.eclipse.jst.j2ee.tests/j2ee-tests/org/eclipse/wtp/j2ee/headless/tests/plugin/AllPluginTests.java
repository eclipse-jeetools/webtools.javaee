/*******************************************************************************
 * Copyright (c) 2005, 2007 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *******************************************************************************/
/*
 * Created on Feb 2, 2004
 * 
 * To change the template for this generated file go to Window - Preferences - Java - Code Generation - Code and
 * Comments
 */
package org.eclipse.wtp.j2ee.headless.tests.plugin;

import junit.framework.Test;
import junit.framework.TestSuite;

import org.eclipse.core.runtime.Path;
import org.eclipse.jst.common.annotations.tests.AnnotationProviderTest;
import org.eclipse.jst.j2ee.defect.tests.DefectVerificationTestsSuite;
import org.eclipse.jst.j2ee.flexible.project.fvtests.ProjectMigrationTest;
import org.eclipse.jst.jee.model.tests.ModelProviderTest;
import org.eclipse.wst.server.core.IRuntime;
import org.eclipse.wst.server.core.IRuntimeType;
import org.eclipse.wst.server.core.IRuntimeWorkingCopy;
import org.eclipse.wst.server.core.ServerCore;
import org.eclipse.wtp.j2ee.headless.tests.web.container.WebAppLibrariesContainerTests;

/**
 * @author jsholl
 * 
 * To change the template for this generated type comment go to Window - Preferences - Java - Code Generation - Code
 * and Comments
 */
public class AllPluginTests extends TestSuite {
	
	public static IRuntime JONAS_SERVER = createJONASRuntime();

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
	      addTest(org.eclipse.wtp.j2ee.headless.tests.utility.operations.AllTests.suite());
	      addTest(org.eclipse.jst.j2ee.tests.modulecore.AllTests.suite());
	      // model provider
		  addTest(ModelProviderTest.suite());
	      addTest(ProjectMigrationTest.suite());
	      addTest(AnnotationProviderTest.suite());
	      addTest(org.eclipse.jst.j2ee.dependency.tests.AllTests.suite());
	      addTest(org.eclipse.jst.j2ee.classpath.tests.AllTests.suite());
          addTest(WebAppLibrariesContainerTests.suite());
          addTest(DefectVerificationTestsSuite.suite());
    }
    
    public static IRuntime createJONASRuntime()  {
    	String s = System.getProperty("org.eclipse.jst.server.jonas.432");
    	if (s == null || s.length() == 0)
    		return null;
    	try {
    		IRuntimeType rt = ServerCore.findRuntimeType("org.eclipse.jst.server.core.runtimeType");
    		IRuntimeWorkingCopy wc = rt.createRuntime(null, null);
    		wc.setLocation(new Path(s));
    		return wc.save(true, null);
    	} catch (Exception e) {
    		e.printStackTrace();
    		return null;
    	}
    }

}
