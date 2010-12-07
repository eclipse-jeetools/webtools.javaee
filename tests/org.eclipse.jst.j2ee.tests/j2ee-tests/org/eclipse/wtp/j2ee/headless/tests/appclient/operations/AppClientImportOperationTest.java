/*
 * Created on Jan 6, 2004
 *
 * To change the template for this generated file go to
 * Window - Preferences - Java - Code Generation - Code and Comments
 */
package org.eclipse.wtp.j2ee.headless.tests.appclient.operations;

import junit.framework.Test;

import org.eclipse.wst.common.tests.SimpleTestSuite;

/**
 * @author Administrator
 * @author Ian Tewksbury (ictewksb@us.ibm.com)
 * 
 * To change the template for this generated type comment go to Window - Preferences - Java - Code
 * Generation - Code and Comments
 */
public class AppClientImportOperationTest extends AppClientImportOperationBaseTest {
	
	public AppClientImportOperationTest() {
		super("AppClientImportOperationTests");
	}
	
	public AppClientImportOperationTest(String name) {
		super(name);
	}
	
	public static Test suite() {
		return new SimpleTestSuite(AppClientImportOperationTest.class);
	}	
	
    public void testACImport12_Defaults() throws Exception{
    	runImportTests_All("AC12_Defaults");
    }
    
    public void testACImport13_Defaults() throws Exception {
    	runImportTests_All("AC13_Defaults");
    }
    
    public void testACImport14_Defaults() throws Exception {
    	runImportTests_All("AC14_Defaults");
    }
    
    public void testACImport12_NoDefaultClass() throws Exception{
    	runImportTests_All("AC12_NoDefaultClass");
    }
    
    public void testACImport13_NoDefaultClass() throws Exception {
    	runImportTests_All("AC13_NoDefaultClass");
    }
    
    public void testACImport14_NoDefaultClass() throws Exception {
    	runImportTests_All("AC14_NoDefaultClass");
    }
  
    public void testACImport12_AddToEAR() throws Exception {
    	runImportTests_All("AC12_AddToEAR");
    }
    
    public void testACImport13_AddToEAR() throws Exception {
    	runImportTests_All("AC13_AddToEAR");
    }
    
    public void testACImport14_AddToEAR() throws Exception {
    	runImportTests_All("AC14_AddToEAR");
    }
        
    public void testACImport12_InterestingName() throws Exception{
    	runImportTests_All("AC12_InterestingName");
    }
    
    public void testACImport13_InterestingName() throws Exception{
    	runImportTests_All("AC13_InterestingName");
    }
    
    public void testACImport14_InterestingName() throws Exception{
    	runImportTests_All("AC14_InterestingName");
    }
    
    public void testACImport12_AddToEAR_InterestingName() throws Exception{
    	runImportTests_All("AC12_AddToEAR_InterestingName");
    }
    
    public void testACImport13_AddToEAR_InterestingName() throws Exception{
    	runImportTests_All("AC13_AddToEAR_InterestingName");
    }
    
    public void testACImport14_AddToEAR_InterestingName() throws Exception{
    	runImportTests_All("AC14_AddToEAR_InterestingName");
    }
        
    public void testACImport50_WithDD() throws Exception {
    	runImportTests_All("AC50_WithDD");
    }

    public void testACImport50_NoDefaultClass_WithDD() throws Exception {
    	runImportTests_All("AC50_NoDefaultClass_WithDD");
    }

    public void testACImport50_AddToEAR_WithDD() throws Exception {
    	runImportTests_All("AC50_AddToEAR_WithDD");
    }

    public void testACImport50_AddToEAR_InterestingName_WithDD() throws Exception {
    	runImportTests_All("AC50_AddToEAR_InterestingName_WithDD");
    }
}
