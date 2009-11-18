/*******************************************************************************
 * Copyright (c) 2005, 2006 IBM Corporation and others.
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
 * To change the template for this generated file go to Window - Preferences - Java - Code
 * Generation - Code and Comments
 */
package org.eclipse.wtp.j2ee.headless.tests.appclient.operations;

import junit.framework.Test;

import org.eclipse.jst.j2ee.application.internal.operations.AppClientComponentExportDataModelProvider;
import org.eclipse.jst.j2ee.datamodel.properties.IJ2EEComponentExportDataModelProperties;
import org.eclipse.wst.common.frameworks.datamodel.DataModelFactory;
import org.eclipse.wst.common.frameworks.datamodel.IDataModel;
import org.eclipse.wst.common.tests.OperationTestCase;
import org.eclipse.wst.common.tests.SimpleTestSuite;
import org.eclipse.wtp.j2ee.headless.tests.j2ee.operations.JavaEEFacetConstants;
import org.eclipse.wtp.j2ee.headless.tests.j2ee.operations.JavaFileTestingUtilities;
import org.eclipse.wtp.j2ee.headless.tests.j2ee.operations.ModuleExportOperationTest;

/**
 * @author Administrator
 * @author Ian Tewksbury (ictewksb@us.ibm.com)
 * 
 * To change the template for this generated type comment go to Window - Preferences - Java - Code
 * Generation - Code and Comments
 */
public class AppClientExportOperationTest extends ModuleExportOperationTest {

	
	public AppClientExportOperationTest() {
		super("AppClientExportOperationTests");
	}
	
	public AppClientExportOperationTest(String name) {
		super(name);
	}
	
	public static Test suite() {
		return new SimpleTestSuite(AppClientExportOperationTest.class);
	}
	
    public void testACExport12_Defaults() throws Exception{
    	IDataModel dm = AppClientProjectCreationOperationTest.getAppClientCreationDataModel("randomApp", null, JavaEEFacetConstants.APP_CLIENT_12, true, true);
    	OperationTestCase.runDataModel(dm);
    	
    	runExportTests_All(dm);
    }
    
    public void testACExport13_Defaults() throws Exception {
    	IDataModel dm = AppClientProjectCreationOperationTest.getAppClientCreationDataModel("coolApp", null, JavaEEFacetConstants.APP_CLIENT_13, true, true);
    	OperationTestCase.runDataModel(dm);
    	
    	runExportTests_All(dm);
    }
    
    public void testACExport14_Defaults() throws Exception {
    	IDataModel dm = AppClientProjectCreationOperationTest.getAppClientCreationDataModel("awesomeApp", null, JavaEEFacetConstants.APP_CLIENT_14, true, true);
    	OperationTestCase.runDataModel(dm);
    	
    	runExportTests_All(dm);
    }
    
    public void testACExport50_Defaults() throws Exception {
    	IDataModel dm = AppClientProjectCreationOperationTest.getAppClientCreationDataModel("amazingApp", null, JavaEEFacetConstants.APP_CLIENT_5, true, false);
    	OperationTestCase.runDataModel(dm);
    	
    	runExportTests_All(dm);
    }
    
    public void testACExport60_Defaults() throws Exception {
    	IDataModel dm = AppClientProjectCreationOperationTest.getAppClientCreationDataModel("jazzyApp", null, JavaEEFacetConstants.APP_CLIENT_6, true, false);
    	OperationTestCase.runDataModel(dm);
    	
    	runExportTests_All(dm);
    }

    public void testACExport12_NoDefaultClass() throws Exception{
    	IDataModel dm = AppClientProjectCreationOperationTest.getAppClientCreationDataModel("randomApp", null, JavaEEFacetConstants.APP_CLIENT_12, false, true);
    	OperationTestCase.runDataModel(dm);
    	
    	runExportTests_All(dm);
    }
    
    public void testACExport13_NoDefaultClass() throws Exception {
    	IDataModel dm = AppClientProjectCreationOperationTest.getAppClientCreationDataModel("coolApp", null, JavaEEFacetConstants.APP_CLIENT_13, false, true);
    	OperationTestCase.runDataModel(dm);
    	
    	runExportTests_All(dm);
    }
    
    public void testACExport14_NoDefaultClass() throws Exception {
    	IDataModel dm = AppClientProjectCreationOperationTest.getAppClientCreationDataModel("awesomeApp", null, JavaEEFacetConstants.APP_CLIENT_14, false, true);
    	OperationTestCase.runDataModel(dm);
    	
    	runExportTests_All(dm);
    }
    
    public void testACExport50_NoDefaultClass() throws Exception {
    	IDataModel dm = AppClientProjectCreationOperationTest.getAppClientCreationDataModel("amazingApp", null, JavaEEFacetConstants.APP_CLIENT_5, false, false);
    	OperationTestCase.runDataModel(dm);
    	
    	runExportTests_All(dm);
    }

    public void testACExport60_NoDefaultClass() throws Exception {
    	IDataModel dm = AppClientProjectCreationOperationTest.getAppClientCreationDataModel("jazzyApp", null, JavaEEFacetConstants.APP_CLIENT_6, false, false);
    	OperationTestCase.runDataModel(dm);
    	
    	runExportTests_All(dm);
    }

    
    public void testACExport12_AddToEAR() throws Exception {
    	IDataModel dm = AppClientProjectCreationOperationTest.getAppClientCreationDataModel("fooAppToEar", "someEar", JavaEEFacetConstants.APP_CLIENT_12, true, true);
    	OperationTestCase.runDataModel(dm);
    	
    	runExportTests_All(dm);
    }
    
    public void testACExport13_AddToEAR() throws Exception {
    	IDataModel dm = AppClientProjectCreationOperationTest.getAppClientCreationDataModel("fooAppToEar", "coolEar", JavaEEFacetConstants.APP_CLIENT_13, true, true);
    	OperationTestCase.runDataModel(dm);
    	
    	runExportTests_All(dm);
    }
    
    public void testACExport14_AddToEAR() throws Exception {
    	IDataModel dm = AppClientProjectCreationOperationTest.getAppClientCreationDataModel("fooAppToEar", "sweetEar", JavaEEFacetConstants.APP_CLIENT_14, true, true);
    	OperationTestCase.runDataModel(dm);
    	
    	runExportTests_All(dm);
    }
    
    public void testACExport50_AddToEAR() throws Exception {
    	IDataModel dm = AppClientProjectCreationOperationTest.getAppClientCreationDataModel("fooAppToEar", "netoEar", JavaEEFacetConstants.APP_CLIENT_5, true, false);
    	OperationTestCase.runDataModel(dm);
    	
    	runExportTests_All(dm);
    }
    
    public void testACExport60_AddToEAR() throws Exception {
    	IDataModel dm = AppClientProjectCreationOperationTest.getAppClientCreationDataModel("fooAppToEar", "jelloEar", JavaEEFacetConstants.APP_CLIENT_6, true, false);
    	OperationTestCase.runDataModel(dm);
    	
    	runExportTests_All(dm);
    }

    public void testACExport12_InterestingName() throws Exception{
    	IDataModel dm = AppClientProjectCreationOperationTest.getAppClientCreationDataModel("kd3(2k_djfD3", null, JavaEEFacetConstants.APP_CLIENT_12, true, true);
    	OperationTestCase.runDataModel(dm);
    	
    	runExportTests_All(dm);
    }
    
    public void testACExport13_InterestingName() throws Exception{
    	IDataModel dm = AppClientProjectCreationOperationTest.getAppClientCreationDataModel("a_dD3dj8)f7", null, JavaEEFacetConstants.APP_CLIENT_13, true, true);
    	OperationTestCase.runDataModel(dm);
    	
    	runExportTests_All(dm);
    }
    
    public void testACExport14_InterestingName() throws Exception{
    	IDataModel dm = AppClientProjectCreationOperationTest.getAppClientCreationDataModel("_Jid7dh)3a", null, JavaEEFacetConstants.APP_CLIENT_14, true, true);
    	OperationTestCase.runDataModel(dm);
    	
    	runExportTests_All(dm);
    }
    
    public void testACExport50_InterestingName() throws Exception{
    	IDataModel dm = AppClientProjectCreationOperationTest.getAppClientCreationDataModel("a_1B2c()3D4", null, JavaEEFacetConstants.APP_CLIENT_5, true, false);
    	OperationTestCase.runDataModel(dm);
    	
    	runExportTests_All(dm);
    }
    
    public void testACExport60_InterestingName() throws Exception{
    	IDataModel dm = AppClientProjectCreationOperationTest.getAppClientCreationDataModel("EE6_a_1B2c()3D4", null, JavaEEFacetConstants.APP_CLIENT_6, true, false);
    	OperationTestCase.runDataModel(dm);
    	
    	runExportTests_All(dm);
    }

    public void testACExport12_AddToEAR_InterestingName() throws Exception{
    	IDataModel dm = AppClientProjectCreationOperationTest.getAppClientCreationDataModel("kd(32k_djfD)3", "hFdf(8G_Fij))3", JavaEEFacetConstants.APP_CLIENT_12, true, true);
    	OperationTestCase.runDataModel(dm);
    	
    	runExportTests_All(dm);
    }
    
    public void testACExport13_AddToEAR_InterestingName() throws Exception{
    	IDataModel dm = AppClientProjectCreationOperationTest.getAppClientCreationDataModel("adD__3dj8)df7", "(53_hdj(f8HD", JavaEEFacetConstants.APP_CLIENT_13, true, true);
    	OperationTestCase.runDataModel(dm);
    	
    	runExportTests_All(dm);
    }
    
    public void testACExport14_AddToEAR_InterestingName() throws Exception{
    	IDataModel dm = AppClientProjectCreationOperationTest.getAppClientCreationDataModel("J_id7((dh3a_", "d_3Dk)j(f8", JavaEEFacetConstants.APP_CLIENT_14, true, true);
    	OperationTestCase.runDataModel(dm);
    	
    	runExportTests_All(dm);
    }
    
    public void testACExport50_AddToEAR_InterestingName() throws Exception{
    	IDataModel dm = AppClientProjectCreationOperationTest.getAppClientCreationDataModel("a1B_2c)3D4", "4D_3c2)B1a", JavaEEFacetConstants.APP_CLIENT_5, true, false);
    	OperationTestCase.runDataModel(dm);
    	
    	runExportTests_All(dm);
    }

    public void testACExport60_AddToEAR_InterestingName() throws Exception{
    	IDataModel dm = AppClientProjectCreationOperationTest.getAppClientCreationDataModel("EE6_a1B_2c)3D4", "EE6_4D_3c2)B1a", JavaEEFacetConstants.APP_CLIENT_6, true, false);
    	OperationTestCase.runDataModel(dm);
    	
    	runExportTests_All(dm);
    }
    
    public void testACExport50_WithDD() throws Exception {
    	IDataModel dm = AppClientProjectCreationOperationTest.getAppClientCreationDataModel("insaneApp", null, JavaEEFacetConstants.APP_CLIENT_5, true, true);
    	OperationTestCase.runDataModel(dm);
    	
    	runExportTests_All(dm);
    }
    
    public void testACExport60_WithDD() throws Exception {
    	IDataModel dm = AppClientProjectCreationOperationTest.getAppClientCreationDataModel("insaneApp6", null, JavaEEFacetConstants.APP_CLIENT_6, true, true);
    	OperationTestCase.runDataModel(dm);
    	
    	runExportTests_All(dm);
    }

    public void testACExport50_NoDefaultClass_WithDD() throws Exception {
    	IDataModel dm = AppClientProjectCreationOperationTest.getAppClientCreationDataModel("insaneApp", null, JavaEEFacetConstants.APP_CLIENT_5, false, true);
    	OperationTestCase.runDataModel(dm);
    	
    	runExportTests_All(dm);
    }
    
    public void testACExport60_NoDefaultClass_WithDD() throws Exception {
    	IDataModel dm = AppClientProjectCreationOperationTest.getAppClientCreationDataModel("insaneApp6", null, JavaEEFacetConstants.APP_CLIENT_6, false, true);
    	OperationTestCase.runDataModel(dm);
    	
    	runExportTests_All(dm);
    }

    public void testACExport50_AddToEAR_WithDD() throws Exception {
    	IDataModel dm = AppClientProjectCreationOperationTest.getAppClientCreationDataModel("appToEARwithDD", "bigEAR", JavaEEFacetConstants.APP_CLIENT_5, true, true);
    	OperationTestCase.runDataModel(dm);
    	
    	runExportTests_All(dm);
    }
    
    public void testACExport60_AddToEAR_WithDD() throws Exception {
    	IDataModel dm = AppClientProjectCreationOperationTest.getAppClientCreationDataModel("appToEARwithDD6", "bigEAR6", JavaEEFacetConstants.APP_CLIENT_6, true, true);
    	OperationTestCase.runDataModel(dm);
    	
    	runExportTests_All(dm);
    }

    public void testACExport50_AddToEAR_InterestingName_WithDD() throws Exception {
    	IDataModel dm = AppClientProjectCreationOperationTest.getAppClientCreationDataModel("D875)_DFj", "7D_3cF2)BaQ", JavaEEFacetConstants.APP_CLIENT_5, true, true);
    	OperationTestCase.runDataModel(dm);
    	
    	runExportTests_All(dm);
    }
    
    public void testACExport60_AddToEAR_InterestingName_WithDD() throws Exception {
    	IDataModel dm = AppClientProjectCreationOperationTest.getAppClientCreationDataModel("EE6_D875)_DFj", "EE6_7D_3cF2)BaQ", JavaEEFacetConstants.APP_CLIENT_6, true, true);
    	OperationTestCase.runDataModel(dm);
    	
    	runExportTests_All(dm);
    }

    @Override
    protected String getModuleExtension() {
    	return ".jar";
    }
    
	@Override
	protected IDataModel getExportDataModel(String projectName, String destination, boolean exportSource, boolean runBuild, boolean overwriteExisting) {
		return getAppClientExportDataModel(projectName, destination, exportSource, runBuild, overwriteExisting);
	}
    
    /**
     * @param projectName name of the project to export
     * @param destination destination to export to
     * @param exportSource if TRUE export source files, else don't
     * @param runBuild if TRUE run a build before exporting, else don't
     * @param overwriteExisting if TRUE overwrite existing files, else don't
     * @return an AppClientComponentExport data model with all of the given settings.
     */
    public static IDataModel getAppClientExportDataModel(String projectName, String destination, boolean exportSource, boolean runBuild, boolean overwriteExisting){
    	IDataModel exportModel = DataModelFactory.createDataModel(new AppClientComponentExportDataModelProvider());
    	
		exportModel.setProperty(IJ2EEComponentExportDataModelProperties.PROJECT_NAME, projectName);
		exportModel.setProperty(IJ2EEComponentExportDataModelProperties.ARCHIVE_DESTINATION, destination);
		exportModel.setProperty(IJ2EEComponentExportDataModelProperties.EXPORT_SOURCE_FILES, exportSource);
		exportModel.setProperty(IJ2EEComponentExportDataModelProperties.OVERWRITE_EXISTING, overwriteExisting);
		exportModel.setProperty(IJ2EEComponentExportDataModelProperties.RUN_BUILD, runBuild);
		
		return exportModel;
    }
    
    @Override
    protected void addJavaFilesToProject(String projectName, String[] classNames, String prackageName) throws Exception {
    	JavaFileTestingUtilities.addJavaFilesToAppClient(projectName, classNames, prackageName);
    }
    
    @Override
    protected void verifyJavaFilesExported(String archiveName, String[] classNames, String packageName, boolean withClassFiles, boolean withSource) throws Exception {
    	JavaFileTestingUtilities.verifyJavaFilesInJAR(archiveName, classNames, packageName, withClassFiles, withSource);
    }
}
