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
 * Created on Jan 6, 2004
 *
 * To change the template for this generated file go to
 * Window - Preferences - Java - Code Generation - Code and Comments
 */
package org.eclipse.wtp.j2ee.headless.tests.ejb.operations;

import junit.framework.Test;

import org.eclipse.jst.j2ee.datamodel.properties.IJ2EEComponentExportDataModelProperties;
import org.eclipse.jst.j2ee.internal.ejb.project.operations.EJBComponentExportDataModelProvider;
import org.eclipse.wst.common.componentcore.datamodel.properties.IFacetProjectCreationDataModelProperties;
import org.eclipse.wst.common.frameworks.datamodel.DataModelFactory;
import org.eclipse.wst.common.frameworks.datamodel.IDataModel;
import org.eclipse.wst.common.tests.SimpleTestSuite;
import org.eclipse.wtp.j2ee.headless.tests.j2ee.operations.JavaEEFacetConstants;
import org.eclipse.wtp.j2ee.headless.tests.j2ee.operations.JavaFileTestingUtilities;
import org.eclipse.wtp.j2ee.headless.tests.j2ee.operations.ModuleExportOperationTest;

/**
 * @author Administrator
 * @author Ian Tewksbury (ictewksb@us.ibm.com)
 *
 * To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Generation - Code and Comments
 */
public class EJBExportOperationTest extends ModuleExportOperationTest {  
	public EJBExportOperationTest() {
		super("EJBExportOperationTests");
	}
	
	public EJBExportOperationTest(String name) {
		super(name);
	}
	
	public static Test suite() {
		return new SimpleTestSuite(EJBExportOperationTest.class);
	}
	
	public void testEJBExport11_Defaults() throws Exception {
    	IDataModel dm = EJBProjectCreationOperationTest.getEJBDataModel("insaneEJB", null, null, null, JavaEEFacetConstants.EJB_11, true);
    	runDataModel(dm);
    	
    	runExportTests_All(dm);
    }
    
    public void testEJBExport20_Defaults() throws Exception {
    	IDataModel dm = EJBProjectCreationOperationTest.getEJBDataModel("fooEJB", null, null, null, JavaEEFacetConstants.EJB_2, true);
    	runDataModel(dm);
    	
    	runExportTests_All(dm);
    }
    
    public void testEJBExport21_Defaults() throws Exception {
    	IDataModel dm = EJBProjectCreationOperationTest.getEJBDataModel("barEJB", null, null, null, JavaEEFacetConstants.EJB_21, true);
    	runDataModel(dm);
    	
    	runExportTests_All(dm);
    }
    
    //TODO
    //NOT sure why, but the annotated beans are not being compiled correctly
    //so that during validation, their annotations are not read, and this test fails
    /*
    public void testEJBExport30_Defaults() throws Exception {
    	IDataModel dm = EJBProjectCreationOperationTest.getEJBDataModel("penEJB", null, null, null, JavaEEFacetConstants.EJB_3, false);
    	runDataModel(dm);
    	addStateless(dm);
    	
    	runExportTests_All(dm);
    }
    */
    
    private void addStateless(IDataModel dm) throws Exception {
    	String projectName = dm.getStringProperty(IFacetProjectCreationDataModelProperties.FACET_PROJECT_NAME);
		String packageName = "test";
		String className = "AnnotatedStateless";
		String classContents = "package test;\n\nimport javax.ejb.Stateless;\n\n@Stateless\npublic class AnnotatedStateless {\n\n}";
		JavaFileTestingUtilities.addJavaFileToEJB(projectName, className, packageName, classContents);
	}

	public void testEJBExport11_AddToEAR_Defaults() throws Exception {
    	IDataModel dm = EJBProjectCreationOperationTest.getEJBDataModel("testEJB", null, null, "testEAR", JavaEEFacetConstants.EJB_11, true);
    	runDataModel(dm);
    	
    	runExportTests_All(dm);
    }
    
    public void testEJBExport20_AddToEAR_Defaults() throws Exception {
    	IDataModel dm = EJBProjectCreationOperationTest.getEJBDataModel("coolEJB", null, null, "booEAR", JavaEEFacetConstants.EJB_2, true);
    	runDataModel(dm);
    	
    	runExportTests_All(dm);
    }
    
    public void testEJBExport21_AddToEAR_Defaults() throws Exception {
    	IDataModel dm = EJBProjectCreationOperationTest.getEJBDataModel("crazyEJB", null, null, "starEAR", JavaEEFacetConstants.EJB_21, true);
    	runDataModel(dm);
    	
    	runExportTests_All(dm);
    }
    
    //TODO
    //NOT sure why, but the annotated beans are not being compiled correctly
    //so that during validation, their annotations are not read, and this test fails
    /*
    public void testEJBExport30_AddToEAR_Defaults() throws Exception {
    	IDataModel dm = EJBProjectCreationOperationTest.getEJBDataModel("netoEJB", null, null, "myEAR", JavaEEFacetConstants.EJB_3, false);
    	runDataModel(dm);
    	addStateless(dm);
    	
    	runExportTests_All(dm);
    }
    */
    
    
    public void testEJBExport11_AddToEAR_NoClient() throws Exception {
    	IDataModel dm = EJBProjectCreationOperationTest.getEJBDataModel("testEJB", null, null, "testEAR", JavaEEFacetConstants.EJB_11, false, true);
    	runDataModel(dm);
    	
    	runExportTests_All(dm);
    }
    
    public void testEJBExport20_AddToEAR_NoClient() throws Exception {
    	IDataModel dm = EJBProjectCreationOperationTest.getEJBDataModel("coolEJB", null, null, "booEAR", JavaEEFacetConstants.EJB_2, false, true);
    	runDataModel(dm);
    	
    	runExportTests_All(dm);
    }
    
    public void testEJBExport21_AddToEAR_NoClient() throws Exception {
    	IDataModel dm = EJBProjectCreationOperationTest.getEJBDataModel("crazyEJB", null, null, "starEAR", JavaEEFacetConstants.EJB_21, false, true);
    	runDataModel(dm);
    	
    	runExportTests_All(dm);
    }
    
    //TODO
    //NOT sure why, but the annotated beans are not being compiled correctly
    //so that during validation, their annotations are not read, and this test fails
    /*
    public void testEJBExport30_AddToEAR_NoClient() throws Exception {
    	IDataModel dm = EJBProjectCreationOperationTest.getEJBDataModel("netoEJB", null, null, "myEAR", JavaEEFacetConstants.EJB_3, false, false);
    	runDataModel(dm);
    	addStateless(dm);
    	
    	runExportTests_All(dm);
    }
    */
    
    
    public void testEJBExport11_AddToEAR_ChangedEJBClientName() throws Exception {
    	IDataModel dm = EJBProjectCreationOperationTest.getEJBDataModel("fooBarEJB", "testEJBExportClient", null, "theirEAR", JavaEEFacetConstants.EJB_11, true);
    	runDataModel(dm);
    	
    	runExportTests_All(dm);
    }
    
    public void testEJBExport20_AddToEAR_ChangedEJBClientName() throws Exception {
    	IDataModel dm = EJBProjectCreationOperationTest.getEJBDataModel("saneEJB", "coolEJBClient", null, "yourEAR", JavaEEFacetConstants.EJB_2, true);
    	runDataModel(dm);
    	
    	runExportTests_All(dm);
    }
    
    public void testEJBExport21_AddToEAR_ChangedEJBClientName() throws Exception {
    	IDataModel dm = EJBProjectCreationOperationTest.getEJBDataModel("clipEJB", "crazyEJBClient", null, "ourEAR", JavaEEFacetConstants.EJB_21, true);
    	runDataModel(dm);
    	
    	runExportTests_All(dm);
    }
    
    //TODO
    //NOT sure why, but the annotated beans are not being compiled correctly
    //so that during validation, their annotations are not read, and this test fails
    /*
    public void testEJBExport30_AddToEAR_ChangedEJBClientName() throws Exception {
    	IDataModel dm = EJBProjectCreationOperationTest.getEJBDataModel("phoneEJB", "netoEJBClient", null, "waterEAR", JavaEEFacetConstants.EJB_3, false);
    	runDataModel(dm);
    	addStateless(dm);
    	
    	runExportTests_All(dm);
    }
    */
    
    public void testEJBExport11_AddToEAR_ChangedClientSourceFolder() throws Exception {
    	IDataModel dm = EJBProjectCreationOperationTest.getEJBDataModel("cupEJB", null, "src", "openEAR", JavaEEFacetConstants.EJB_11, true);
    	runDataModel(dm);
    	
    	runExportTests_All(dm);
    }
    
    public void testEJBExport20_AddToEAR_ChangedClientSourceFolder() throws Exception {
    	IDataModel dm = EJBProjectCreationOperationTest.getEJBDataModel("mouseEJB", null, "files", "closedEAR", JavaEEFacetConstants.EJB_2, true);
    	runDataModel(dm);
    	
    	runExportTests_All(dm);
    }
    
    public void testEJBExport21_AddToEAR_ChangedClientSourceFolder() throws Exception {
    	IDataModel dm = EJBProjectCreationOperationTest.getEJBDataModel("pcEJB", null, "stuff", "batEAR", JavaEEFacetConstants.EJB_21, true);
    	runDataModel(dm);
    	
    	runExportTests_All(dm);
    }
    
    //TODO
    //NOT sure why, but the annotated beans are not being compiled correctly
    //so that during validation, their annotations are not read, and this test fails
    /*
    public void testEJBExport30_AddToEAR_ChangedClientSourceFolder() throws Exception {
    	IDataModel dm = EJBProjectCreationOperationTest.getEJBDataModel("keyEJB", null, "foo", "keyEAR", JavaEEFacetConstants.EJB_3, false);
    	runDataModel(dm);
    	addStateless(dm);
    	
    	runExportTests_All(dm);
    }
    */
    
    public void testEJBExport11A_ddToEAR_ChangedEJBClientName_ChangedClientSourceFolder() throws Exception {
    	IDataModel dm = EJBProjectCreationOperationTest.getEJBDataModel("cupEJB", "superClient", "src", "openEAR", JavaEEFacetConstants.EJB_11, true);
    	runDataModel(dm);
    	
    	runExportTests_All(dm);
    }
    
    public void testEJBExport20_AddToEAR_ChangedEJBClientName_ChangedClientSourceFolder() throws Exception {
    	IDataModel dm = EJBProjectCreationOperationTest.getEJBDataModel("mouseEJB", "dudeClient", "files", "closedEAR", JavaEEFacetConstants.EJB_2, true);
    	runDataModel(dm);
    	
    	runExportTests_All(dm);
    }
    
    public void testEJBExport21_AddToEAR_ChangedEJB_ClientNameChangedClientSourceFolder() throws Exception {
    	IDataModel dm = EJBProjectCreationOperationTest.getEJBDataModel("pcEJB", "fireClient", "stuff", "batEAR", JavaEEFacetConstants.EJB_21, true);
    	runDataModel(dm);
    	
    	runExportTests_All(dm);
    }
    
    //TODO
    //NOT sure why, but the annotated beans are not being compiled correctly
    //so that during validation, their annotations are not read, and this test fails
    /*
    public void testEJBExport30_AddToEARChangedEJB_ClientName_ChangedClientSourceFolder() throws Exception {
    	IDataModel dm = EJBProjectCreationOperationTest.getEJBDataModel("keyEJB", "wireClient", "foo", "keyEAR", JavaEEFacetConstants.EJB_3, false);
    	runDataModel(dm);
    	addStateless(dm);
    	
    	runExportTests_All(dm);
    }
    */
    
    public void testEJBExport30_Defaults_WithDD() throws Exception {
    	IDataModel dm = EJBProjectCreationOperationTest.getEJBDataModel("tigerEJB", null, null, null, JavaEEFacetConstants.EJB_3, true);
    	runDataModel(dm);
    	
    	runExportTests_All(dm);
    }
    
    public void testEJBExport31_Defaults_WithDD() throws Exception {
    	IDataModel dm = EJBProjectCreationOperationTest.getEJBDataModel("EE6tigerEJB", null, null, null, JavaEEFacetConstants.EJB_31, true);
    	runDataModel(dm);
    	
    	runExportTests_All(dm);
    }
    
    public void testEJBExport30_AddToEAR_Defaults_WithDD() throws Exception {
    	IDataModel dm = EJBProjectCreationOperationTest.getEJBDataModel("pandaEJB", null, null, "roundEAR", JavaEEFacetConstants.EJB_3, true);
    	runDataModel(dm);
    	
    	runExportTests_All(dm);
    }
    
    public void testEJBExport31_AddToEAR_Defaults_WithDD() throws Exception {
    	IDataModel dm = EJBProjectCreationOperationTest.getEJBDataModel("EE6pandaEJB", null, null, "EE6roundEAR", JavaEEFacetConstants.EJB_31, true);
    	runDataModel(dm);
    	
    	runExportTests_All(dm);
    }
    
    public void testEJBExport30_AddToEAR_NoClient_WithDD() throws Exception {
    	IDataModel dm = EJBProjectCreationOperationTest.getEJBDataModel("netoEJB", null, null, "myEAR", JavaEEFacetConstants.EJB_3, false, true);
    	runDataModel(dm);
    	
    	runExportTests_All(dm);
    }
    
    public void testEJBExport31_AddToEAR_NoClient_WithDD() throws Exception {
    	IDataModel dm = EJBProjectCreationOperationTest.getEJBDataModel("EE6netoEJB", null, null, "EE6myEAR", JavaEEFacetConstants.EJB_31, false, true);
    	runDataModel(dm);
    	
    	runExportTests_All(dm);
    }

    public void testEJBExport30_AddToEAR_ChangedEJBClientName_WithDD() throws Exception {
    	IDataModel dm = EJBProjectCreationOperationTest.getEJBDataModel("snakeEJB", "client", null, "groundEAR", JavaEEFacetConstants.EJB_3, true);
    	runDataModel(dm);
    	
    	runExportTests_All(dm);
    }
    
    public void testEJBExport31_AddToEAR_ChangedEJBClientName_WithDD() throws Exception {
    	IDataModel dm = EJBProjectCreationOperationTest.getEJBDataModel("EE6snakeEJB", "EE6client", null, "EE6groundEAR", JavaEEFacetConstants.EJB_31, true);
    	runDataModel(dm);
    	
    	runExportTests_All(dm);
    }

    public void testEJBExport30_AddToEAR_ChangedClientSourceFolder_WithDD() throws Exception {
    	IDataModel dm = EJBProjectCreationOperationTest.getEJBDataModel("lionEJB", null, "barSrc", "pinEAR", JavaEEFacetConstants.EJB_3, true);
    	runDataModel(dm);
    	
    	runExportTests_All(dm);
    }
    
    public void testEJBExport31_AddToEAR_ChangedClientSourceFolder_WithDD() throws Exception {
    	IDataModel dm = EJBProjectCreationOperationTest.getEJBDataModel("EE6lionEJB", null, "EE6barSrc", "EE6pinEAR", JavaEEFacetConstants.EJB_31, true);
    	runDataModel(dm);
    	
    	runExportTests_All(dm);
    }
    
    public void testEJBExport30_AddToEAR_ChangedEJBClientName_ChangedClientSourceFolder_WithDD() throws Exception {
    	IDataModel dm = EJBProjectCreationOperationTest.getEJBDataModel("monkeyEJB", "fooFooClient", "fooSrc", "outsideEAR", JavaEEFacetConstants.EJB_3, true);
    	runDataModel(dm);
    	
    	runExportTests_All(dm);
    }
    
    public void testEJBExport31_AddToEAR_ChangedEJBClientName_ChangedClientSourceFolder_WithDD() throws Exception {
    	IDataModel dm = EJBProjectCreationOperationTest.getEJBDataModel("EE6monkeyEJB", "EE6fooFooClient", "EE6fooSrc", "EE6outsideEAR", JavaEEFacetConstants.EJB_31, true);
    	runDataModel(dm);
    	
    	runExportTests_All(dm);
    }

    @Override
    protected String getModuleExtension() {
    	return ".jar";
    }

	@Override
	protected IDataModel getExportDataModel(String projectName, String destination, boolean exportSource, boolean runBuild, boolean overwriteExisting) {
		return getEJBExportDataModel(projectName, destination, exportSource, runBuild, overwriteExisting);
	}
	
    /**
     * @param projectName name of the project to export
     * @param destination destination to export to
     * @param exportSource if TRUE export source files, else don't
     * @param runBuild if TRUE run a build before exporting, else don't
     * @param overwriteExisting if TRUE overwrite existing files, else don't
     * @return an EJBComponentExport data model with all of the given settings.
     */
    public static IDataModel getEJBExportDataModel(String projectName, String destination, boolean exportSource, boolean runBuild, boolean overwriteExisting){
    	IDataModel exportModel = DataModelFactory.createDataModel(new EJBComponentExportDataModelProvider());
    	
		exportModel.setProperty(IJ2EEComponentExportDataModelProperties.PROJECT_NAME, projectName);
		exportModel.setProperty(IJ2EEComponentExportDataModelProperties.ARCHIVE_DESTINATION, destination);
		exportModel.setProperty(IJ2EEComponentExportDataModelProperties.EXPORT_SOURCE_FILES, exportSource);
		exportModel.setProperty(IJ2EEComponentExportDataModelProperties.OVERWRITE_EXISTING, overwriteExisting);
		exportModel.setProperty(IJ2EEComponentExportDataModelProperties.RUN_BUILD, runBuild);
		
		return exportModel;
    }
    
    @Override
    protected void addJavaFilesToProject(String projectName, String[] classNames, String prackageName) throws Exception {
    	JavaFileTestingUtilities.addJavaFilesToEJB(projectName, classNames, prackageName);
    }
    
    @Override
    protected void verifyJavaFilesExported(String archiveName, String[] classNames, String packageName, boolean withClassFiles, boolean withSource) throws Exception {
    	JavaFileTestingUtilities.verifyJavaFilesInJAR(archiveName, classNames, packageName, withClassFiles, withSource);
    }
}
