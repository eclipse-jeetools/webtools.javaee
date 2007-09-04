/*
 * Created on Jan 6, 2004
 *
 * To change the template for this generated file go to
 * Window - Preferences - Java - Code Generation - Code and Comments
 */
package org.eclipse.wtp.j2ee.headless.tests.appclient.operations;

import junit.framework.Test;
import junit.framework.TestSuite;

import org.eclipse.jst.j2ee.applicationclient.internal.creation.AppClientComponentImportDataModelProvider;
import org.eclipse.jst.j2ee.datamodel.properties.IJ2EEComponentImportDataModelProperties;
import org.eclipse.jst.j2ee.internal.archive.operations.IOverwriteHandler;
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
public class AppClientImportOperationTest extends ModuleImportOperationTest {
	

	
	public AppClientImportOperationTest() {
		super("AppClientImportOperationTests");
	}
	
	public AppClientImportOperationTest(String name) {
		super(name);
	}
	
	public static Test suite() {
		TestSuite suite = new TestSuite();
		suite.addTestSuite(AppClientImportOperationTest.class);
		return suite;
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
    
    public void testACImport50_Defaults() throws Exception {
    	runImportTests_All("AC50_Defaults");
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
    
    public void testACImport50_NoDefaultClass() throws Exception {
    	runImportTests_All("AC50_NoDefaultClass");
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
    
    public void testACImport50_AddToEAR() throws Exception {
    	runImportTests_All("AC50_AddToEAR");
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
    
    public void testACImport50_InterestingName() throws Exception{
    	runImportTests_All("AC50_InterestingName");
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
    
    public void testACImport50_AddToEAR_InterestingName() throws Exception{
    	runImportTests_All("AC50_AddToEAR_InterestingName");
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
    
    @Override
    protected String getModuleExtension() {
    	return ".jar";
    }
	
    @Override
    protected IDataModel getImportDataModel(String filePath, String projectName, IOverwriteHandler overwriteHandler, IDataModel creationModel, boolean closeArchiveOnDispose) {
    	return getAppClientImportDataModel(filePath, projectName, overwriteHandler, creationModel, closeArchiveOnDispose);
    }
    
    public static IDataModel getAppClientImportDataModel(String filePath, String projectName, IOverwriteHandler overwriteHandler, IDataModel creationModel, boolean closeArchiveOnDispose) {
    	IDataModel importModel = DataModelFactory.createDataModel(new AppClientComponentImportDataModelProvider());
    	
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
    	return AppClientExportOperationTest.getAppClientExportDataModel(projectName, destination, exportSource, runBuild, overwriteExisting);
    }
}
