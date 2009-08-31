package org.eclipse.wtp.j2ee.headless.tests.ear.operations;

import java.util.Collections;
import java.util.List;

import junit.framework.Assert;
import junit.framework.Test;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.jst.j2ee.application.internal.operations.EARComponentImportDataModelProvider;
import org.eclipse.jst.j2ee.datamodel.properties.IEARComponentImportDataModelProperties;
import org.eclipse.jst.j2ee.datamodel.properties.IJ2EEComponentImportDataModelProperties;
import org.eclipse.jst.j2ee.internal.archive.ArchiveWrapper;
import org.eclipse.wst.common.componentcore.internal.util.ComponentUtilities;
import org.eclipse.wst.common.componentcore.resources.IVirtualComponent;
import org.eclipse.wst.common.componentcore.resources.IVirtualFile;
import org.eclipse.wst.common.componentcore.resources.IVirtualFolder;
import org.eclipse.wst.common.frameworks.datamodel.DataModelFactory;
import org.eclipse.wst.common.frameworks.datamodel.IDataModel;
import org.eclipse.wst.common.tests.OperationTestCase;
import org.eclipse.wst.common.tests.SimpleTestSuite;
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
		return new SimpleTestSuite(EARImportOperationTest.class);
	}
	
    public void testEARImport12_WithDependencies() throws Exception{
    	runImportTests_All("EAR12");
    }
    
    public void testEARImport13_WithDependencies() throws Exception{
    	runImportTests_All("EAR13");
    }
    
    public void testEARImport14_WithDependencies() throws Exception{
    	runImportTests_All("EAR14");
    }
    
    public void testEARImport50_WithDependencies() throws Exception{
    	runImportTests_All("EAR50");
    }
    
    
    public void testEARImport12_ChangedContentDir_WithDependencies() throws Exception{
    	runImportTests_All("EAR12_ChangedContentDir");
    }
    
    public void testEARImport13_ChangedContentDir_WithDependencies() throws Exception{
    	runImportTests_All("EAR13_ChangedContentDir");
    }
    
    public void testEARImport14_ChangedContentDir_WithDependencies() throws Exception{
    	runImportTests_All("EAR14_ChangedContentDir");
    }
    
    public void testEARImport50_ChangedContentDir_WithDependencies() throws Exception{
    	runImportTests_All("EAR50_ChangedContentDir");
    }
    
    
    public void testEARImport50_EAR5NoDD_DependincesNoDDs() throws Exception {
    	String archiveName = "EAR5NoDD_DependincesNoDDs" + getModuleExtension();
    	
    	runAndVerifyImport_ExportedWithSource(archiveName);
    	OperationTestCase.deleteAllProjects();
    	
    	runAndVerifyImport_DontExplodeAllModules(archiveName);
    	OperationTestCase.deleteAllProjects();
    	
		runAndVerifyImport_DontExplodeAnyModules(archiveName);
		OperationTestCase.deleteAllProjects();
    }
    
    public void testEARImport50_EAR5NoDD_DependincesNoDDs_WithUtil() throws Exception {
    	String archiveName = "EAR5NoDD_DependincesNoDDs_WithUtil" + getModuleExtension();
    	
    	runAndVerifyImport_ExportedWithSource(archiveName);
    	OperationTestCase.deleteAllProjects();
    	
    	runAndVerifyImport_DontExplodeAllModules(archiveName);
    	OperationTestCase.deleteAllProjects();
    	
		runAndVerifyImport_DontExplodeAnyModules(archiveName);
		OperationTestCase.deleteAllProjects();
    }
    
    public void testEARImport50_EAR5NoDD_DependincesWithDDs() throws Exception {
    	String archiveName = "EAR5NoDD_DependincesWithDDs" + getModuleExtension();
    	
    	runAndVerifyImport_ExportedWithSource(archiveName);
    	OperationTestCase.deleteAllProjects();
    	
    	runAndVerifyImport_DontExplodeAllModules(archiveName);
    	OperationTestCase.deleteAllProjects();
    	
		runAndVerifyImport_DontExplodeAnyModules(archiveName);
		OperationTestCase.deleteAllProjects();
    }
    
    public void testEARImport50_EAR5NoDD_DependincesWithDDs_WithUtil() throws Exception {
    	String archiveName = "EAR5NoDD_DependincesWithDDs_WithUtil" + getModuleExtension();
    	
    	runAndVerifyImport_ExportedWithSource(archiveName);
    	OperationTestCase.deleteAllProjects();
    	
    	runAndVerifyImport_DontExplodeAllModules(archiveName);
    	OperationTestCase.deleteAllProjects();
    	
		runAndVerifyImport_DontExplodeAnyModules(archiveName);
		OperationTestCase.deleteAllProjects();
    }
    
    public void testEARImport50_EAR5NoDD_DependincesNoDDsAndWithDDs() throws Exception {
    	String archiveName = "EAR5NoDD_DependincesNoDDsAndWithDDs" + getModuleExtension();
    	
    	runAndVerifyImport_ExportedWithSource(archiveName);
    	OperationTestCase.deleteAllProjects();
    	
    	runAndVerifyImport_DontExplodeAllModules(archiveName);
    	OperationTestCase.deleteAllProjects();
    	
		runAndVerifyImport_DontExplodeAnyModules(archiveName);
		OperationTestCase.deleteAllProjects();
    }
    
    public void testEARImport50_EAR5NoDD_DependincesNoDDsAndWithDDs_WithUtil() throws Exception {
    	String archiveName = "EAR5NoDD_DependincesNoDDsAndWithDDs_WithUtil" + getModuleExtension();
    	
    	runAndVerifyImport_ExportedWithSource(archiveName);
    	OperationTestCase.deleteAllProjects();
    	
    	runAndVerifyImport_DontExplodeAllModules(archiveName);
    	OperationTestCase.deleteAllProjects();
    	
		runAndVerifyImport_DontExplodeAnyModules(archiveName);
		OperationTestCase.deleteAllProjects();
    }
    
    public void testEARImport50_EAR5WithDD_DependincesNoDDs() throws Exception {
    	String archiveName = "EAR5WithDD_DependincesNoDDs" + getModuleExtension();
    	
    	runAndVerifyImport_ExportedWithSource(archiveName);
    	OperationTestCase.deleteAllProjects();
    	
    	runAndVerifyImport_DontExplodeAllModules(archiveName);
    	OperationTestCase.deleteAllProjects();
    	
		runAndVerifyImport_DontExplodeAnyModules(archiveName);
		OperationTestCase.deleteAllProjects();
    }
    
    public void testEARImport50_EAR5WithDD_DependincesNoDDs_WithUtil() throws Exception {
    	String archiveName = "EAR5WithDD_DependincesNoDDs_WithUtil" + getModuleExtension();
    	
    	runAndVerifyImport_ExportedWithSource(archiveName);
    	OperationTestCase.deleteAllProjects();
    	
    	runAndVerifyImport_DontExplodeAllModules(archiveName);
    	OperationTestCase.deleteAllProjects();
    	
		runAndVerifyImport_DontExplodeAnyModules(archiveName);
		OperationTestCase.deleteAllProjects();
    }
    
    public void testEARImport50_EAR5WithDD_DependincesWithDDs() throws Exception {
    	String archiveName = "EAR5WithDD_DependincesWithDDs" + getModuleExtension();
    	
    	runAndVerifyImport_ExportedWithSource(archiveName);
    	OperationTestCase.deleteAllProjects();
    	
    	runAndVerifyImport_DontExplodeAllModules(archiveName);
    	OperationTestCase.deleteAllProjects();
    	
		runAndVerifyImport_DontExplodeAnyModules(archiveName);
		OperationTestCase.deleteAllProjects();
    }
    
    public void testEARImport50_EAR5WithDD_DependincesWithDDs_WithUtil() throws Exception {
    	String archiveName = "EAR5WithDD_DependincesWithDDs_WithUtil" + getModuleExtension();
    	
    	runAndVerifyImport_ExportedWithSource(archiveName);
    	OperationTestCase.deleteAllProjects();
    	
    	runAndVerifyImport_DontExplodeAllModules(archiveName);
    	OperationTestCase.deleteAllProjects();
    	
		runAndVerifyImport_DontExplodeAnyModules(archiveName);
		OperationTestCase.deleteAllProjects();
    }
    
    public void testEARImport50_EAR5WithDD_DependincesNoDDsAndWithDDs() throws Exception {
    	String archiveName = "EAR5WithDD_DependincesNoDDsAndWithDDs" + getModuleExtension();
    	
    	runAndVerifyImport_ExportedWithSource(archiveName);
    	OperationTestCase.deleteAllProjects();
    	
    	runAndVerifyImport_DontExplodeAllModules(archiveName);
    	OperationTestCase.deleteAllProjects();
    	
		runAndVerifyImport_DontExplodeAnyModules(archiveName);
		OperationTestCase.deleteAllProjects();
    }
    
    public void testEARImport50_EAR5WithDD_DependincesNoDDsAndWithDDs_WithUtil() throws Exception {
    	String archiveName = "EAR5WithDD_DependincesNoDDsAndWithDDs_WithUtil" + getModuleExtension();
    	
    	runAndVerifyImport_ExportedWithSource(archiveName);
    	OperationTestCase.deleteAllProjects();
    	
    	runAndVerifyImport_DontExplodeAllModules(archiveName);
    	OperationTestCase.deleteAllProjects();
    	
		runAndVerifyImport_DontExplodeAnyModules(archiveName);
		OperationTestCase.deleteAllProjects();
    }    
	
    public void test288180() throws Exception {
    	String archiveName = "288180.ear";
    	String archivePath = getArchivePath(archiveName);
    	verifyImportArchiveExists(archivePath);
		IDataModel importModel = getImportDataModel(archivePath, "288180", null, true);
		IStatus status = importModel.validate();
		assertTrue(status.isOK());
		importModel.dispose();
		OperationTestCase.deleteAllProjects();
    }
    
	@Override
	protected String getModuleExtension() {
		return ".ear";
	}

	@Override
	protected IDataModel getImportDataModel(String filePath, String projectName, IDataModel creationModel, boolean closeArchiveOnDispose) {
		return getEARImportDataModel(filePath, projectName, creationModel, closeArchiveOnDispose);
	}
	
	public static IDataModel getEARImportDataModel(String filePath, String projectName, IDataModel creationModel, boolean closeArchiveOnDispose) {
		IDataModel importModel = DataModelFactory.createDataModel(new EARComponentImportDataModelProvider());
    	
    	importModel.setProperty(IJ2EEComponentImportDataModelProperties.FILE_NAME, filePath);
    	importModel.setProperty(IJ2EEComponentImportDataModelProperties.PROJECT_NAME, projectName);
    	importModel.setProperty(IJ2EEComponentImportDataModelProperties.CLOSE_ARCHIVE_ON_DISPOSE, closeArchiveOnDispose);
    	
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
		
		runAndVerifyImport_DontExplodeAllModules(testName + "_Source" + getModuleExtension());
		OperationTestCase.deleteAllProjects();
		
		runAndVerifyImport_DontExplodeAnyModules(testName + "_Source" + getModuleExtension());
		OperationTestCase.deleteAllProjects();
	}
	
	private void runAndVerifyImport_DontExplodeAllModules(String archiveName) throws Exception {
		String archivePath = getArchivePath(archiveName);
		String projectName = "exportedWithSource";
		
		verifyImportArchiveExists(archivePath);
		
		//remove some of the selected models
		IDataModel importModel = getImportDataModel(archivePath, projectName, null, true);
    	List selectedModelsList = (List)importModel.getProperty(IEARComponentImportDataModelProperties.SELECTED_MODELS_LIST);
    	for(int i = 0; i<2 & i<selectedModelsList.size(); i++) {
    		selectedModelsList.remove(i);
    	}
    	
		importModel = setExtendedEARImportDataModelProperties(importModel, null, null, selectedModelsList, null, null, null);
		runAndVerify(importModel);
		
		runAndVerifyReExportation(importModel, true, true);
	}
	
	private void runAndVerifyImport_DontExplodeAnyModules(String archiveName) throws Exception {
		String archivePath = getArchivePath(archiveName);
		String projectName = "exportedWithSource";
		
		verifyImportArchiveExists(archivePath);
		
		//remove all of the selected models
		IDataModel importModel = getImportDataModel(archivePath, projectName, null, true);
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
