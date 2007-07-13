package org.eclipse.wtp.j2ee.headless.tests.ear.operations;

import java.util.Collections;
import java.util.List;

import junit.framework.Assert;
import junit.framework.Test;
import junit.framework.TestSuite;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.runtime.IPath;
import org.eclipse.jst.j2ee.application.internal.operations.EARComponentImportDataModelProvider;
import org.eclipse.jst.j2ee.datamodel.properties.IEARComponentImportDataModelProperties;
import org.eclipse.jst.j2ee.datamodel.properties.IJ2EEComponentImportDataModelProperties;
import org.eclipse.jst.j2ee.internal.archive.ArchiveWrapper;
import org.eclipse.jst.j2ee.internal.archive.operations.IOverwriteHandler;
import org.eclipse.wst.common.componentcore.internal.util.ComponentUtilities;
import org.eclipse.wst.common.componentcore.resources.IVirtualComponent;
import org.eclipse.wst.common.componentcore.resources.IVirtualFile;
import org.eclipse.wst.common.componentcore.resources.IVirtualFolder;
import org.eclipse.wst.common.frameworks.datamodel.DataModelFactory;
import org.eclipse.wst.common.frameworks.datamodel.IDataModel;
import org.eclipse.wst.common.tests.OperationTestCase;
import org.eclipse.wtp.j2ee.headless.tests.j2ee.operations.JEEImportOperationTest;

/**
 * @author itewk
 * 
 * To change the template for this generated type comment go to Window - Preferences - Java - Code Generation - Code
 * and Comments
 */
public class EARImportOperationTest extends JEEImportOperationTest {
	public EARImportOperationTest() {
		super("EARImportOperationTests");
	}
	
	public EARImportOperationTest(String name) {
		super(name);
	}
	
	public static Test suite() {
		TestSuite suite = new TestSuite();
		suite.addTestSuite(EARImportOperationTest.class);
		return suite;
	}
	
    public void testEARImport12_Defaults() throws Exception{
    	runImportTests_All("testEARImport12_Defaults");
    }
    
    public void testEARImport13_Defaults() throws Exception{
    	runImportTests_All("testEARImport13_Defaults");
    }
    
    public void testEARImport14_Defaults() throws Exception{
    	runImportTests_All("testEARImport14_Defaults");
    }
    
    public void testEARImport50_Defaults() throws Exception{
    	runImportTests_All("testEARImport50_Defaults");
    }
    
    
    public void testEARImport12_ChangedContentDir() throws Exception{
    	runImportTests_All("testEARImport12_ChangedContentDir");
    }
    
    public void testEARImport13_ChangedContentDir() throws Exception{
    	runImportTests_All("testEARImport13_ChangedContentDir");
    }
    
    public void testEARImport14_ChangedContentDir() throws Exception{
    	runImportTests_All("testEARImport14_ChangedContentDir");
    }
    
    public void testEARImport50_ChangedContentDir() throws Exception{
    	runImportTests_All("testEARImport50_ChangedContentDir");
    }
    
    
    public void testEARImport12_WithDependencies() throws Exception{
    	runImportTests_All("testEARImport12_WithDependencies");
    }
    
    public void testEARImport13_WithDependencies() throws Exception{
    	runImportTests_All("testEARImport13_WithDependencies");
    }
    
    public void testEARImport14_WithDependencies() throws Exception{
    	runImportTests_All("testEARImport14_WithDependencies");
    }
    
    public void testEARImport50_WithDependencies() throws Exception{
    	runImportTests_All("testEARImport50_WithDependencies");
    }
    
    
    public void testEARImport12_ChangedContentDir_WithDependencies() throws Exception{
    	runImportTests_All("testEARImport12_ChangedContentDir_WithDependencies");
    }
    
    public void testEARImport13_ChangedContentDir_WithDependencies() throws Exception{
    	runImportTests_All("testEARImport13_ChangedContentDir_WithDependencies");
    }
    
    public void testEARImport14_ChangedContentDir_WithDependencies() throws Exception{
    	runImportTests_All("testEARImport14_ChangedContentDir_WithDependencies");
    }
    
    public void testEARImport50_ChangedContentDir_WithDependencies() throws Exception{
    	runImportTests_All("testEARImport50_ChangedContentDir_WithDependencies");
    }
    
    
    public void testEARImport50_Defaults_WithDD() throws Exception{
    	runImportTests_All("testEARImport50_Defaults_WithDD");
    }
    
    public void testEARImport50_ChangedContentDir_WithDD() throws Exception{
    	runImportTests_All("testEARImport50_ChangedContentDir_WithDD");
    }
    
    public void testEARImport50_WithDependencies_WithDD() throws Exception{
    	runImportTests_All("testEARImport50_WithDependencies_WithDD");
    }
    
    public void testEARImport50_ChangedContentDir_WithDependencies_WithDD() throws Exception{
    	runImportTests_All("testEARImport50_ChangedContentDir_WithDependencies_WithDD");
    }
	
	
	@Override
	protected String getModuleExtension() {
		return ".ear";
	}

	@Override
	protected IDataModel getImportDataModel(String filePath, String projectName, IOverwriteHandler overwriteHandler, IDataModel creationModel, boolean closeArchiveOnDispose) {
		return getEARImportDataModel(filePath, projectName, overwriteHandler, creationModel, closeArchiveOnDispose);
	}
	
	public static IDataModel getEARImportDataModel(String filePath, String projectName, IOverwriteHandler overwriteHandler, IDataModel creationModel, boolean closeArchiveOnDispose) {
		IDataModel importModel = DataModelFactory.createDataModel(new EARComponentImportDataModelProvider());
    	
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
	
	public static IDataModel setExtendedEARImportDataModelProperties(IDataModel importModel, IPath nestedModuleRoot, List utilityList, List selectedModelsList, List ejbClientList, List moduleModelsList, List utilityModelsList) {
    	if(nestedModuleRoot != null) {
    		importModel.setProperty(IEARComponentImportDataModelProperties.NESTED_MODULE_ROOT, nestedModuleRoot);
    	}
    	
    	if(utilityList != null) {
    		importModel.setProperty(IEARComponentImportDataModelProperties.UTILITY_LIST, utilityList);
    	}
    	
    	if(selectedModelsList != null) {
    		importModel.setProperty(IEARComponentImportDataModelProperties.SELECTED_MODELS_LIST, selectedModelsList);
    	}
    	
    	if(ejbClientList != null) {
    		importModel.setProperty(IEARComponentImportDataModelProperties.EJB_CLIENT_LIST, ejbClientList);
    	}
    	
    	if(moduleModelsList != null) {
    		importModel.setProperty(IEARComponentImportDataModelProperties.MODULE_MODELS_LIST, moduleModelsList);
    	}
    	
    	if(utilityModelsList != null) {
    		importModel.setProperty(IEARComponentImportDataModelProperties.UTILITY_MODELS_LIST, utilityModelsList);
    	}
    	
    	return importModel;
    }
	
	@Override
	protected IDataModel getExportDataModel(String projectName, String destination, boolean exportSource, boolean runBuild, boolean overwriteExisting) {
		return EARExportOperationTest.getEARExportDataModel(projectName, destination, exportSource, runBuild, overwriteExisting);
	}

	@Override
	protected void runImportTests_All(String testName) throws Exception {
		super.runImportTests_All(testName);
		String archiveNamePrefix = getArchiveFileNamePrefix(testName);
		
		runAndVerifyImport_DontExplodeAllModules(archiveNamePrefix + "_WithSource" + getModuleExtension());
		OperationTestCase.deleteAllProjects();
		
		runAndVerifyImport_DontExplodeAnyModules(archiveNamePrefix + "_WithSource" + getModuleExtension());
		OperationTestCase.deleteAllProjects();
	}
	
	private void runAndVerifyImport_DontExplodeAllModules(String archiveName) throws Exception {
//		String archiveName = this.getClass().getSimpleName() + "_" + this.getName() + "_WithSource" + getModuleExtension();
		String archivePath = getArchivePath(archiveName);
		String projectName = "exportedWithSource";
		
		verifyImportArchiveExists(archivePath);
		
		//remove some of the selected models
		IDataModel importModel = getImportDataModel(archivePath, projectName, null, null, true);
    	List selectedModelsList = (List)importModel.getProperty(IEARComponentImportDataModelProperties.SELECTED_MODELS_LIST);
    	for(int i = 0; i<2 & i<selectedModelsList.size(); i++) {
    		selectedModelsList.remove(i);
    	}
    	
		importModel = setExtendedEARImportDataModelProperties(importModel, null, null, selectedModelsList, null, null, null);
		runAndVerify(importModel);
		
		runAndVerifyReExportation(importModel, true, true);
	}
	
	private void runAndVerifyImport_DontExplodeAnyModules(String archiveName) throws Exception {
//		String archiveName = this.getClass().getSimpleName() + "_" + this.getName() + "_WithSource" + getModuleExtension();
		String archivePath = getArchivePath(archiveName);
		String projectName = "exportedWithSource";
		
		verifyImportArchiveExists(archivePath);
		
		//remove all of the selected models
		IDataModel importModel = getImportDataModel(archivePath, projectName, null, null, true);
		importModel = setExtendedEARImportDataModelProperties(importModel, null, null, Collections.EMPTY_LIST, null, null, null);
		runAndVerify(importModel);
		
		runAndVerifyReExportation(importModel, true, true);
	}
	
	@Override
	protected void runAndVerifyReExportation(IDataModel importModel, boolean exportSource, boolean runBuild) throws Exception {
		removeNestedArchivesForExplodedProjects(importModel);
		super.runAndVerifyReExportation(importModel, exportSource, runBuild);
	}
	
	/**
	 * this is used to be sure when re-exporting the exploded projects are used and not the nested archives.
	 * 
	 * @param importModel
	 * @throws Exception
	 */
	private void removeNestedArchivesForExplodedProjects(IDataModel importModel) throws Exception {
		List<IDataModel> selectedModelsList = (List<IDataModel>)importModel.getProperty(IEARComponentImportDataModelProperties.SELECTED_MODELS_LIST);
		String earProjectName = importModel.getStringProperty(IEARComponentImportDataModelProperties.PROJECT_NAME);
		IVirtualComponent projectVirtComponent = ComponentUtilities.getComponent(earProjectName);
		IVirtualFolder virtRootFolder = projectVirtComponent.getRootFolder();
		
		ArchiveWrapper nestedArchiveWrapper = null;
		IPath nestedArchivePath = null;
		IVirtualFile nestedArchiveVirtFile = null;
		IFile nestedArchiveFile = null;
		for(IDataModel selectedModel : selectedModelsList) {
			nestedArchiveWrapper = (ArchiveWrapper)selectedModel.getProperty(IEARComponentImportDataModelProperties.ARCHIVE_WRAPPER);
			nestedArchivePath = nestedArchiveWrapper.getPath();
			nestedArchiveVirtFile = virtRootFolder.getFile(nestedArchivePath);
			Assert.assertTrue("The EAR project should contain the selected module as an archive", nestedArchiveVirtFile.exists());
			
			nestedArchiveFile = nestedArchiveVirtFile.getUnderlyingFile();
			nestedArchiveFile.delete(true, null);
		}
	}
}
