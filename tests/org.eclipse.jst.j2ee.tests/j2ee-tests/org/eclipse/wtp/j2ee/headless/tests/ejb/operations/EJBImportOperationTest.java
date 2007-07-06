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
import junit.framework.TestSuite;

import org.eclipse.jst.j2ee.datamodel.properties.IJ2EEComponentImportDataModelProperties;
import org.eclipse.jst.j2ee.internal.archive.operations.IOverwriteHandler;
import org.eclipse.jst.j2ee.internal.ejb.project.operations.EJBComponentImportDataModelProvider;
import org.eclipse.wst.common.frameworks.datamodel.DataModelFactory;
import org.eclipse.wst.common.frameworks.datamodel.IDataModel;
import org.eclipse.wtp.j2ee.headless.tests.j2ee.operations.ModuleImportOperationTest;

/**
 * @author Administrator
 * @author Ian Tewksbury (ictewksb@us.ibm.com)
 * 
 * To change the template for this generated type comment go to Window - Preferences - Java - Code
 * Generation - Code and Comments
 */
public class EJBImportOperationTest extends ModuleImportOperationTest {
	public EJBImportOperationTest() {
		super("EJBImportOperationTests");
	}
	
	public EJBImportOperationTest(String name) {
		super(name);
	}
	
	public static Test suite() {
		TestSuite suite = new TestSuite();
		suite.addTestSuite(EJBImportOperationTest.class);
		return suite;
	}
	
	@Override
	protected String getModuleExtension() {
		return ".jar";
	}
	
	public void testEJBImport11_Defaults() throws Exception {
		runImportTests_All();
    }
    
    public void testEJBImport20_Defaults() throws Exception {
		runImportTests_All();
    }
    
    public void testEJBImport21_Defaults() throws Exception {
		runImportTests_All();
    }
    
    public void testEJBImport30_Defaults() throws Exception {
		runImportTests_All();
    }
    
    
    public void testEJBImport11_AddToEAR_Defaults() throws Exception {
		runImportTests_All();
    }
    
    public void testEJBImport20_AddToEAR_Defaults() throws Exception {
		runImportTests_All();
    }
    
    public void testEJBImport21_AddToEAR_Defaults() throws Exception {
		runImportTests_All();
    }
    
    public void testEJBImport30_AddToEAR_Defaults() throws Exception {
		runImportTests_All();
    }
    
    
    public void testEJBImport11_AddToEAR_NoClient() throws Exception {
		runImportTests_All();
    }
    
    public void testEJBImport20_AddToEAR_NoClient() throws Exception {
		runImportTests_All();
    }
    
    public void testEJBImport21_AddToEAR_NoClient() throws Exception {
		runImportTests_All();
    }
    
    public void testEJBImport30_AddToEAR_NoClient() throws Exception {
		runImportTests_All();
    }
    
    
    public void testEJBImport11_AddToEAR_ChangedEJBClientName() throws Exception {
		runImportTests_All();
    }
    
    public void testEJBImport20_AddToEAR_ChangedEJBClientName() throws Exception {
		runImportTests_All();
    }
    
    public void testEJBImport21_AddToEAR_ChangedEJBClientName() throws Exception {
		runImportTests_All();
    }
    
    public void testEJBImport30_AddToEAR_ChangedEJBClientName() throws Exception {
		runImportTests_All();
    }
    
    
    public void testEJBImport11_AddToEAR_ChangedClientSourceFolder() throws Exception {
		runImportTests_All();
    }
    
    public void testEJBImport20_AddToEAR_ChangedClientSourceFolder() throws Exception {
		runImportTests_All();
    }
    
    public void testEJBImport21_AddToEAR_ChangedClientSourceFolder() throws Exception {
		runImportTests_All();
    }
    
    public void testEJBImport30_AddToEAR_ChangedClientSourceFolder() throws Exception {
		runImportTests_All();
    }
    
    
    public void testEJBImport11A_ddToEAR_ChangedEJBClientName_ChangedClientSourceFolder() throws Exception {
		runImportTests_All();
    }
    
    public void testEJBImport20_AddToEAR_ChangedEJBClientName_ChangedClientSourceFolder() throws Exception {
		runImportTests_All();
    }
    
    public void testEJBImport21_AddToEAR_ChangedEJB_ClientNameChangedClientSourceFolder() throws Exception {
		runImportTests_All();
    }
    
    public void testEJBImport30_AddToEARChangedEJB_ClientName_ChangedClientSourceFolder() throws Exception {
		runImportTests_All();
    }
    
    
    
    public void testEJBImport30_Defaults_WithDD() throws Exception {
		runImportTests_All();
    }
    
    public void testEJBImport30_AddToEAR_Defaults_WithDD() throws Exception {
		runImportTests_All();
    }
    
    public void testEJBImport30_AddToEAR_NoClient_WithDD() throws Exception {
		runImportTests_All();
    }
    
    public void testEJBImport30_AddToEAR_ChangedEJBClientName_WithDD() throws Exception {
		runImportTests_All();
    }
    
    public void testEJBImport30_AddToEAR_ChangedClientSourceFolder_WithDD() throws Exception {
		runImportTests_All();
    }
    
    public void testEJBImport30_AddToEAR_ChangedEJBClientName_ChangedClientSourceFolder_WithDD() throws Exception {
		runImportTests_All();
    }
	
    
	@Override
	protected IDataModel getImportDataModel(String filePath, String projectName, IOverwriteHandler overwriteHandler, IDataModel creationModel, boolean closeArchiveOnDispose) {
		return getEJBImportDataModel(filePath, projectName, overwriteHandler, creationModel, closeArchiveOnDispose);
	}
	
    public static IDataModel getEJBImportDataModel(String filePath, String projectName, IOverwriteHandler overwriteHandler, IDataModel creationModel, boolean closeArchiveOnDispose) {
    	IDataModel importModel = DataModelFactory.createDataModel(new EJBComponentImportDataModelProvider());
    	
    	importModel.setProperty(IJ2EEComponentImportDataModelProperties.FILE_NAME, filePath);
    	importModel.setProperty(IJ2EEComponentImportDataModelProperties.PROJECT_NAME, projectName);
    	importModel.setProperty(IJ2EEComponentImportDataModelProperties.CLOSE_ARCHIVE_ON_DISPOSE, closeArchiveOnDispose);
    	
    	if(overwriteHandler != null) {
    		importModel.setProperty(IJ2EEComponentImportDataModelProperties.OVERWRITE_HANDLER, overwriteHandler);
    	}
    	
    	if(creationModel != null) {
    		importModel.setProperty(IJ2EEComponentImportDataModelProperties.NESTED_MODEL_J2EE_COMPONENT_CREATION, creationModel);
    	}
    	
    	return importModel;
    }
	
	@Override
	protected IDataModel getExportDataModel(String projectName, String destination, boolean exportSource, boolean runBuild, boolean overwriteExisting) {
		return EJBExportOperationTest.getEJBExportDataModel(projectName, destination, exportSource, runBuild, overwriteExisting);
	}
}
