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

package org.eclipse.wtp.jee.headless.tests.ejb.operations;

import junit.framework.Test;

import org.eclipse.wst.common.tests.SimpleTestSuite;
import org.eclipse.wtp.j2ee.headless.tests.ejb.operations.EJBImportOperationBaseTest;


public class EJBImportOperationTest extends EJBImportOperationBaseTest {
	public EJBImportOperationTest() {
		super("EJBImportOperationTests");
	}
	
	public EJBImportOperationTest(String name) {
		super(name);
	}
	
	public static Test suite() {
		return new SimpleTestSuite(EJBImportOperationTest.class);
	}
	    
    public void testEJBImport30_Defaults() throws Exception {
		runImportTests_All("EJB30_Defaults");
    }
    //TODO -- annotations to scan?
    //EE6TODO 
//    public void testEJBImport31_Defaults() throws Exception {
//		runImportTests_All("EJB31_Defaults");
//    }
    
    public void testEJBImport30_AddToEAR_Defaults() throws Exception {
		runImportTests_All("EJB30_AddToEAR_Defaults");
    }
    
    public void testEJBImport31_AddToEAR_Defaults() throws Exception {
		runImportTests_All("EJB30_AddToEAR_Defaults");
    }

    public void testEJBImport30_AddToEAR_NoClient() throws Exception {
		runImportTests_All("EJB30_AddToEAR_NoClient");
    }
    
    //TODO -- annotations to scan?
    //EE6TODO 
//    public void testEJBImport31_AddToEAR_NoClient() throws Exception {
//		runImportTests_All("EJB31_AddToEAR_NoClient");
//    }
    
    public void testEJBImport30_AddToEAR_DifClientName() throws Exception {
		runImportTests_All("EJB30_AddToEAR_DiffClientName");
    }
    
    //TODO -- annotations to scan?
    //EE6TODO 
//    public void testEJBImport31_AddToEAR_DifClientName() throws Exception {
//		runImportTests_All("EJB31_AddToEAR_DiffClientName");
//    }
    
    public void testEJBImport30_AddToEAR_DiffClientSourceFolder() throws Exception {
		runImportTests_All("EJB30_AddToEAR_DiffClientSourceFolder");
    }
    
    //TODO -- annotations to scan?
    //EE6TODO 
//    public void testEJBImport31_AddToEAR_DiffClientSourceFolder() throws Exception {
//		runImportTests_All("EJB31_AddToEAR_DiffClientSourceFolder");
//    }
    
    public void testEJBImport30_AddToEAR_DiffClientName_DiffClientSourceFolder() throws Exception {
		runImportTests_All("EJB30_AddToEAR_DiffClientName_DiffClientSourceFolder");
    }

    //TODO -- annotations to scan?
    //EE6TODO 
//    public void testEJBImport31_AddToEAR_DiffClientName_DiffClientSourceFolder() throws Exception {
//		runImportTests_All("EJB31_AddToEAR_DiffClientName_DiffClientSourceFolder");
//    }
    
    public void testEJBImport31_Defaults_WithDD() throws Exception {
		runImportTests_All("EJB31_Defaults_WithDD");
    }
    
    public void testEJBImport31_AddToEAR_Defaults_WithDD() throws Exception {
		runImportTests_All("EJB31_AddToEAR_Defaults_WithDD");
    }

    public void testEJBImport31_AddToEAR_NoClient_WithDD() throws Exception {
		runImportTests_All("EJB31_AddToEAR_NoClient_WithDD");
    }
    
    public void testEJBImport31_AddToEAR_DiffClientName_WithDD() throws Exception {
		runImportTests_All("EJB31_AddToEAR_DiffClientName_WithDD");
    }
    
    public void testEJBImport31_AddToEAR_DiffClientSourceFolder_WithDD() throws Exception {
		runImportTests_All("EJB31_AddToEAR_DiffClientSourceFolder_WithDD");
    }
    
    public void testEJBImport31_AddToEAR_DiffClientName_DiffClientSourceFolder_WithDD() throws Exception {
		runImportTests_All("EJB31_AddToEAR_DiffClientName_DiffClientSourceFolder_WithDD");
    }
}
