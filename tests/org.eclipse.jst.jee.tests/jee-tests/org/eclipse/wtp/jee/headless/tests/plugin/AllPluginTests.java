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


package org.eclipse.wtp.jee.headless.tests.plugin;

import junit.framework.Test;
import junit.framework.TestSuite;

import org.eclipse.core.runtime.Path;
import org.eclipse.wst.server.core.IRuntime;
import org.eclipse.wst.server.core.IRuntimeType;
import org.eclipse.wst.server.core.IRuntimeWorkingCopy;
import org.eclipse.wst.server.core.ServerCore;


public class AllPluginTests extends TestSuite {
	
	public static IRuntime JONAS_SERVER = createJONASRuntime();

    public static Test suite() {
        return new AllPluginTests();
    }

	public AllPluginTests() {
		super();
		addTest(org.eclipse.jst.jee.tests.bvt.EnterpriseBVT.suite());
		addTest(org.eclipse.jst.jee.tests.bvt.EJBBVT.suite());
		addTest(org.eclipse.jst.jee.tests.bvt.WebBVT.suite());
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
