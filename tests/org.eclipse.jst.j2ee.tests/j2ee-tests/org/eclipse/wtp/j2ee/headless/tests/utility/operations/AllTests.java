package org.eclipse.wtp.j2ee.headless.tests.utility.operations;

import junit.framework.Test;
import junit.framework.TestSuite;

/**
 * @author itewk
 */
public class AllTests extends TestSuite {
    public static Test suite(){
        return new AllTests();
    }
    
    public AllTests(){
        super("Java Utility Operation Tests");
        addTest(UtilityProjectCreationOperationTest.suite());
        addTest(UtilityExportOperationTest.suite());
        addTest(UtilityImportOperationTest.suite());
    }
}
