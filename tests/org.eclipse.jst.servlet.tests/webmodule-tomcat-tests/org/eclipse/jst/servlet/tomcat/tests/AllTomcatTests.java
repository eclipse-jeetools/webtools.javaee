/*
 * Created on Feb 2, 2004
 *
 * To change the template for this generated file go to
 * Window - Preferences - Java - Code Generation - Code and Comments
 */
package org.eclipse.jst.servlet.tomcat.tests;
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
 * To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Generation - Code and Comments
 */
public class AllTomcatTests extends TestSuite {
	
	public static IRuntime TOMCAT_RUNTIME = createRuntime();
	
    public static Test suite(){
        return new AllTomcatTests();
    }
    
    public AllTomcatTests(){
        super("WEB Tests");
        addTest(WebProjectCreationTomcatTest.suite());
        addTest(WebImportOperationTomcatTest.suite());
        addTest(WebExportOperationTomcatTest.suite());
        
    }
    
    public static IRuntime createRuntime()  {
    	String s = System.getProperty("org.eclipse.jst.server.tomcat.50");
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
