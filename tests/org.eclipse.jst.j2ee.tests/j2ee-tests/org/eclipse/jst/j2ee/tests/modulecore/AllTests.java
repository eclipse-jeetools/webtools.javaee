/*
 * Created on Feb 18, 2005
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package org.eclipse.jst.j2ee.tests.modulecore;


import junit.framework.Test;
import junit.framework.TestSuite;

import org.eclipse.core.runtime.Path;
import org.eclipse.wst.server.core.IRuntime;
import org.eclipse.wst.server.core.IRuntimeType;
import org.eclipse.wst.server.core.IRuntimeWorkingCopy;
import org.eclipse.wst.server.core.ServerCore;
import org.eclipse.wtp.j2ee.headless.tests.j2ee.operations.J2EEFlexibleProjectCreationOperationTest;

/**
 * To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Generation - Code and Comments
 */
public class AllTests extends TestSuite {
	
	public static IRuntime JONAS_TOMCAT_RUNTIME = createJONASRuntime();

    public static Test suite(){
        return new AllTests();
    }
    
    public AllTests(){
        super("ModuleCore Tests");
        addTest(J2EEFlexibleProjectCreationOperationTest.suite());
    }
	public static IRuntime createJONASRuntime() { 
	       String s = "D:/JOnAS-4.3.2/lib";
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
