/*
 * Created on Feb 2, 2004
 * 
 * To change the template for this generated file go to Window - Preferences - Java - Code Generation - Code and
 * Comments
 */
package org.eclipse.wtp.j2ee.headless.tests.plugin;

import org.eclipse.core.runtime.Path;
import org.eclipse.wst.server.core.IRuntime;
import org.eclipse.wst.server.core.IRuntimeType;
import org.eclipse.wst.server.core.IRuntimeWorkingCopy;
import org.eclipse.wst.server.core.ServerCore;

import junit.framework.Test;
import junit.framework.TestSuite;

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
    }
    
    public static IRuntime createJONASRuntime()  {
    	String s = "D:/JOnAS-4.3.2";
    	if (s == null || s.length() == 0)
    		return null;
    	try {
    		IRuntimeType rt = ServerCore.findRuntimeType("the jonas plugin name to be entered here");
    		IRuntimeWorkingCopy wc = rt.createRuntime(null, null);
    		wc.setLocation(new Path(s));
    		return wc.save(true, null);
    	} catch (Exception e) {
    		e.printStackTrace();
    		return null;
    	}
    }

}
