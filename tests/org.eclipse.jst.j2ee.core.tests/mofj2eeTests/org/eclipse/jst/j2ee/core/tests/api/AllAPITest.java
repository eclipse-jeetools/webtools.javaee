/*
 * Created on Mar 18, 2005
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package org.eclipse.jst.j2ee.core.tests.api;

import junit.framework.Test;
import junit.framework.TestSuite;

import org.eclipse.jst.j2ee.application.test.ApplicationFactoryTest;
import org.eclipse.jst.j2ee.client.test.ApplicationClientTest;
import org.eclipse.jst.j2ee.client.test.ClientFactoryTest;



/**
 * @author Admin
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class AllAPITest  extends TestSuite {

	 public static Test suite(){
        return new AllAPITest();
    }
    
    public AllAPITest(){
        super("J2EE Core API Tests");
        addTest(ApplicationFactoryTest.suite());
        addTest(ApplicationClientTest.suite());
        addTest(ClientFactoryTest.suite());
        
        
    }
	
}
