/*
 * Created on Feb 2, 2004
 *
 * To change the template for this generated file go to
 * Window - Preferences - Java - Code Generation - Code and Comments
 */
package org.eclipse.wtp.j2ee.headless.tests.ear.operations;

import junit.framework.Test;
import junit.framework.TestSuite;

/**
 * @author jsholl
 *
 * To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Generation - Code and Comments
 */
public class AllTests extends TestSuite {

    public static Test suite(){
        return new AllTests();
    }
    
    public AllTests(){
        super("EAR Tests");
     //   addTest(EARExportOperationTest.suite());
     //   addTest(EARImportOperationTest.suite());
        addTest(EARProjectCreationOperationTest.suite());
        addTest(EARProjectCreationTest.suite());
    }
    
}
