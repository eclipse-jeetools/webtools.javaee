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
 * Created on Jan 5, 2004
 * 
 * To change the template for this generated file go to Window - Preferences - Java - Code
 * Generation - Code and Comments
 */
package org.eclipse.wtp.j2ee.headless.tests.ejb.operations;

import junit.framework.Test;

import org.eclipse.wst.common.tests.SimpleTestSuite;

/**
 * @author Ian Tewksbury (ictewksb@us.ibm.com)
 * 
 * To change the template for this generated type comment go to Window - Preferences - Java - Code
 * Generation - Code and Comments
 */
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
	
	public void testEJBImport11_Defaults() throws Exception {
		runImportTests_All("EJB11_Defaults");
    }
    
    public void testEJBImport20_Defaults() throws Exception {
		runImportTests_All("EJB20_Defaults");
    }
    
    public void testEJBImport21_Defaults() throws Exception {
		runImportTests_All("EJB21_Defaults");
    }
    
    public void testEJBImport11_AddToEAR_Defaults() throws Exception {
		runImportTests_All("EJB11_AddToEAR_Defaults");
    }
    
    public void testEJBImport20_AddToEAR_Defaults() throws Exception {
		runImportTests_All("EJB20_AddToEAR_Defaults");
    }
    
    public void testEJBImport21_AddToEAR_Defaults() throws Exception {
		runImportTests_All("EJB21_AddToEAR_Defaults");
    }
      
    public void testEJBImport11_AddToEAR_NoClient() throws Exception {
		runImportTests_All("EJB11_AddToEAR_NoClient");
    }
    
    public void testEJBImport20_AddToEAR_NoClient() throws Exception {
		runImportTests_All("EJB20_AddToEAR_NoClient");
    }
    
    public void testEJBImport21_AddToEAR_NoClient() throws Exception {
		runImportTests_All("EJB21_AddToEAR_NoClient");
    }
    
    public void testEJBImport11_AddToEAR_DiffClientName() throws Exception {
		runImportTests_All("EJB11_AddToEAR_DiffClientName");
    }
    
    public void testEJBImport20_AddToEAR_DiffClientName() throws Exception {
		runImportTests_All("EJB20_AddToEAR_DiffClientName");
    }
    
    public void testEJBImport21_AddToEAR_DiffClientName() throws Exception {
		runImportTests_All("EJB21_AddToEAR_DiffClientName");
    }
    
    public void testEJBImport11_AddToEAR_DiffClientSourceFolder() throws Exception {
		runImportTests_All("EJB11_AddToEAR_DiffClientSourceFolder");
    }
    
    public void testEJBImport20_AddToEAR_DiffClientSourceFolder() throws Exception {
		runImportTests_All("EJB20_AddToEAR_DiffClientSourceFolder");
    }
    
    public void testEJBImport21_AddToEAR_DiffClientSourceFolder() throws Exception {
		runImportTests_All("EJB21_AddToEAR_DiffClientSourceFolder");
    }
    
    public void testEJBImport11_AddToEAR_DiffClientName_DiffClientSourceFolder() throws Exception {
		runImportTests_All("EJB11_AddToEAR_DiffClientName_DiffClientSourceFolder");
    }
    
    public void testEJBImport20_AddToEAR_DiffClientName_DiffClientSourceFolder() throws Exception {
		runImportTests_All("EJB20_AddToEAR_DiffClientName_DiffClientSourceFolder");
    }
    
    public void testEJBImport21_AddToEAR_DiffClientName_DiffClientSourceFolder() throws Exception {
		runImportTests_All("EJB21_AddToEAR_DiffClientName_DiffClientSourceFolder");
    }
    
    public void testEJBImport30_Defaults_WithDD() throws Exception {
		runImportTests_All("EJB30_Defaults_WithDD");
    }
    
    public void testEJBImport30_AddToEAR_Defaults_WithDD() throws Exception {
		runImportTests_All("EJB30_AddToEAR_Defaults_WithDD");
    }
        
    public void testEJBImport30_AddToEAR_NoClient_WithDD() throws Exception {
		runImportTests_All("EJB30_AddToEAR_NoClient_WithDD");
    }
    
    public void testEJBImport30_AddToEAR_DiffClientName_WithDD() throws Exception {
		runImportTests_All("EJB30_AddToEAR_DiffClientName_WithDD");
    }
    
    public void testEJBImport30_AddToEAR_DiffClientSourceFolder_WithDD() throws Exception {
		runImportTests_All("EJB30_AddToEAR_DiffClientSourceFolder_WithDD");
    }
        
    public void testEJBImport30_AddToEAR_DiffClientName_DiffClientSourceFolder_WithDD() throws Exception {
		runImportTests_All("EJB30_AddToEAR_DiffClientName_DiffClientSourceFolder_WithDD");
    }	
}
