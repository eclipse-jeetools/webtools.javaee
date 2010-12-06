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

package org.eclipse.wtp.jee.headless.tests.appclient.operations;

import junit.framework.Test;

import org.eclipse.wst.common.tests.SimpleTestSuite;
import org.eclipse.wtp.j2ee.headless.tests.appclient.operations.AppClientImportOperationBaseTest;

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
	    
    public void testACImport50_Defaults() throws Exception {
    	runImportTests_All("AC50_Defaults");
    }
    
    //TODO -- annotations to scan?
    //EE6TODO 
//    public void testACImport60_Defaults() throws Exception {
//    	runImportTests_All("AC60_Defaults");
//    }
    
    public void testACImport50_NoDefaultClass() throws Exception {
    	runImportTests_All("AC50_NoDefaultClass");
    }
    
    //TODO -- annotations to scan?
    //EE6TODO 
//    public void testACImport60_NoDefaultClass() throws Exception {
//    	runImportTests_All("AC60_NoDefaultClass");
//    }
    
    
    public void testACImport50_AddToEAR() throws Exception {
    	runImportTests_All("AC50_AddToEAR");
    }

    //TODO -- annotations to scan?
    //EE6TODO 
//    public void testACImport60_AddToEAR() throws Exception {
//    	runImportTests_All("AC60_AddToEAR");
//    }
    
    
    public void testACImport50_InterestingName() throws Exception{
    	runImportTests_All("AC50_InterestingName");
    }

    //TODO -- annotations to scan?
    //EE6TODO 
//    public void testACImport60_InterestingName() throws Exception{
//    	runImportTests_All("AC60_InterestingName");
//    }

    
    public void testACImport50_AddToEAR_InterestingName() throws Exception{
    	runImportTests_All("AC50_AddToEAR_InterestingName");
    }

    //TODO -- annotations to scan?
    //EE6TODO 
//    public void testACImport60_AddToEAR_InterestingName() throws Exception{
//    	runImportTests_All("AC60_AddToEAR_InterestingName");
//    }
    

    public void testACImport60_WithDD() throws Exception {
    	runImportTests_All("AC60_WithDD");
    }

    public void testACImport60_NoDefaultClass_WithDD() throws Exception {
    	runImportTests_All("AC60_NoDefaultClass_WithDD");
    }

    public void testACImport60_AddToEAR_WithDD() throws Exception {
    	runImportTests_All("AC60_AddToEAR_WithDD");
    }
    
    public void testACImport60_AddToEAR_InterestingName_WithDD() throws Exception {
    	runImportTests_All("AC60_AddToEAR_InterestingName_WithDD");
    }
}