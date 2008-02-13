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
 * To change the template for this generated file go to
 * Window - Preferences - Java - Code Generation - Code and Comments
 */
package org.eclipse.wtp.j2ee.headless.tests.web.operations;

import junit.framework.Test;
import junit.framework.TestSuite;

import org.eclipse.core.runtime.Path;
import org.eclipse.jst.j2ee.flexible.project.fvtests.WebDeployTest;
import org.eclipse.wst.server.core.IRuntime;
import org.eclipse.wst.server.core.IRuntimeType;
import org.eclipse.wst.server.core.IRuntimeWorkingCopy;
import org.eclipse.wst.server.core.ServerCore;

/**
 * @author jsholl
 *
 * To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Generation - Code and Comments
 */
public class AllTests extends TestSuite {
	
	public static IRuntime TOMCAT_RUNTIME = createRuntime();
	public static IRuntime JONAS_TOMCAT_RUNTIME = createJONASRuntime();
	
	
    public static Test suite(){
        return new AllTests();
    }
    
    public AllTests(){
        super("Web Operation, Servlet, & Deploy Tests");
       addTest(WebImportOperationTest.suite());
       addTest(WebExportOperationTest.suite());
       addTest(WebProjectCreationOperationTest.suite());
       
       addTest(WebDeployTest.suite());
       //addTest(StaticWebProjectCreationOperationTest.suite());
       addTest(AddServletOperationTest.suite());
       addTest(UrlPatternTest.suite());
       // addTest(WebComponentCreationTest.suite());
		//addTest(new SimpleTestSuite(WebSaveStrategyTests.class));
    }
    
    public static IRuntime createRuntime()  {
    	String s = "D:/Program Files/Apache Software Foundation/Tomcat 5.0";
    	if (s == null || s.length() == 0)
    		return null;
    	try {
    		IRuntimeType rt = ServerCore.findRuntimeType("org.eclipse.jst.server.tomcat.runtime.50");
    		IRuntimeWorkingCopy wc = rt.createRuntime(null, null);
    		wc.setLocation(new Path(s));
    		return wc.save(true, null);
    	} catch (Exception e) {
    		e.printStackTrace();
    		return null;
    	}
    }
    
    public static IRuntime createJONASRuntime()  {
    	String s = "D:/JOnAS-4.3.2";
    	if (s == null || s.length() == 0)
    		return null;
    	try {
    		IRuntimeType rt = ServerCore.findRuntimeType("org.eclipse.jst.server.tomcat.runtime.50");
    		IRuntimeWorkingCopy wc = rt.createRuntime(null, null);
    		wc.setLocation(new Path(s));
    		return wc.save(true, null);
    	} catch (Exception e) {
    		e.printStackTrace();
    		return null;
    	}
    }
    
}
