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
 * @author vijayb
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
    	runImportTests_All();
    }
    
    public void testEARImport13_Defaults() throws Exception{
    	runImportTests_All();
    }
    
    public void testEARImport14_Defaults() throws Exception{
    	runImportTests_All();
    }
    
    public void testEARImport50_Defaults() throws Exception{
    	runImportTests_All();
    }
    
    
    public void testEARImport12_ChangedContentDir() throws Exception{
    	runImportTests_All();
    }
    
    public void testEARImport13_ChangedContentDir() throws Exception{
    	runImportTests_All();
    }
    
    public void testEARImport14_ChangedContentDir() throws Exception{
    	runImportTests_All();
    }
    
    public void testEARImport50_ChangedContentDir() throws Exception{
    	runImportTests_All();
    }
    
    
    public void testEARImport12_WithDependencies() throws Exception{
    	runImportTests_All();
    }
    
    public void testEARImport13_WithDependencies() throws Exception{
    	runImportTests_All();
    }
    
    public void testEARImport14_WithDependencies() throws Exception{
    	runImportTests_All();
    }
    
    public void testEARImport50_WithDependencies() throws Exception{
    	runImportTests_All();
    }
    
    
    public void testEARImport12_ChangedContentDir_WithDependencies() throws Exception{
    	runImportTests_All();
    }
    
    public void testEARImport13_ChangedContentDir_WithDependencies() throws Exception{
    	runImportTests_All();
    }
    
    public void testEARImport14_ChangedContentDir_WithDependencies() throws Exception{
    	runImportTests_All();
    }
    
    public void testEARImport50_ChangedContentDir_WithDependencies() throws Exception{
    	runImportTests_All();
    }
    
    
    public void testEARImport50_Defaults_WithDD() throws Exception{
    	runImportTests_All();
    }
    
    public void testEARImport50_ChangedContentDir_WithDD() throws Exception{
    	runImportTests_All();
    }
    
    public void testEARImport50_WithDependencies_WithDD() throws Exception{
    	runImportTests_All();
    }
    
    public void testEARImport50_ChangedContentDir_WithDependencies_WithDD() throws Exception{
    	runImportTests_All();
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
	protected void runImportTests_All() throws Exception {
		super.runImportTests_All();
		
		runAndVerifyImport_DontExplodeAllModules();
		OperationTestCase.deleteAllProjects();
		
		runAndVerifyImport_DontExplodeAnyModules();
		OperationTestCase.deleteAllProjects();
	}
	
	private void runAndVerifyImport_DontExplodeAllModules() throws Exception {
		String archiveName = this.getClass().getSimpleName() + "_" + this.getName() + "_WithSource" + getModuleExtension();
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
	
	private void runAndVerifyImport_DontExplodeAnyModules() throws Exception {
		String archiveName = this.getClass().getSimpleName() + "_" + this.getName() + "_WithSource" + getModuleExtension();
		String archivePath = getArchivePath(archiveName);
		String projectName = "exportedWithSource";
		
		verifyImportArchiveExists(archivePath);
		
		//remove some of the selected models
		IDataModel importModel = getImportDataModel(archivePath, projectName, null, null, true);
    	List selectedModelsList = Collections.EMPTY_LIST;
    	for(int i = 0; i<2 & i<selectedModelsList.size(); i++) {
    		selectedModelsList.remove(i);
    	}
    	
		importModel = setExtendedEARImportDataModelProperties(importModel, null, null, selectedModelsList, null, null, null);
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
	
//
//	public List getUtilityJarsInEAR(EARFile earFile) {
//        List utilJars = new ArrayList();
//        try {
//            List files = earFile.getArchiveFiles();
//            for (int i = 0; i < files.size(); i++) {
//                FileImpl file = (FileImpl) files.get(i);
//                if (file.isArchive() && !file.isModuleFile() && file.getURI().endsWith(".jar")) //$NON-NLS-1$
//                    utilJars.add(file);
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        return utilJars;
//    }
//
//    public void testEARImportRootLocation() throws Exception {
////    	EnterpriseApplicationImportDataModel importModel = null;
////        String projectName = "Test13WEBEJBEAR";
////        String earName = getFullTestDataPath("TestData" + fileSep + "EARImportTests" + fileSep + "Test13WEBEJBEAR.ear");
////        try {
////            importModel = new EnterpriseApplicationImportDataModel();
////            importModel.setProperty(EARComponentCreationDataModel.PROJECT_NAME, projectName);
////            importModel.setProperty(EnterpriseApplicationImportDataModel.FILE_NAME, earName);
////            importModel.setIntProperty(EARComponentCreationDataModel.COMPONENT_VERSION, J2EEVersionConstants.J2EE_1_3_ID);
////            //importModel.setProperty(EARComponentCreationDataModel.PROJECT_LOCATION, "c:" + fileSep + "EARTestTemp");
////            importModel.setProperty(EnterpriseApplicationImportDataModel.NESTED_MODULE_ROOT, "c:" + fileSep + "EARTestModulesTemp");
////            runAndVerify(importModel);
////        } finally {
////            importModel.dispose();
////        }
//    }
//    
//    //TODO M4 Action Item - enable the util jar tests after the fixes for util jar import are available in M4
//
//    /*public void testImportSomeUtilityJarsExploded() throws Exception {
//        String projectName = "UtilityJarTestEAR";
//        String earName = getFullTestDataPath("TestData" + fileSep + "EARImportTests" + fileSep + "UtilityJarTestEAR.ear");
//        EnterpriseApplicationImportDataModel importModel = null;
//        try {
//            importModel = new EnterpriseApplicationImportDataModel();
//            importModel.setProperty(EnterpriseApplicationCreationDataModel.PROJECT_NAME, projectName);
//            importModel.setProperty(EnterpriseApplicationImportDataModel.FILE_NAME, earName);
//            importModel.setIntProperty(EnterpriseApplicationCreationDataModel.APPLICATION_VERSION, J2EEVersionConstants.J2EE_1_3_ID);
//            EARFile file = importModel.getEARFile();
//            List utilJars = getUtilityJarsInEAR(file);
//            if (!utilJars.isEmpty()) {
//                utilJars.remove(0);
//                utilJars.remove(2);
//                importModel.setProperty(EnterpriseApplicationImportDataModel.UTILITY_LIST, utilJars);
//            }
//            //TODO to be changed when the new property is available, EARImportDataModel.CREATE_BINARY_PROJECT is
//            // removed
//            //importModel.setBooleanProperty(EARImportDataModel.CREATE_BINARY_PROJECT, false);
//            runAndVerify(importModel);
//        } finally {
//            importModel.dispose();
//        }
//    }
//
//    public void testImportUtilityJarsExploded() throws Exception {
//        String projectName = "UtilityJarTestEAR";
//        String earName = getFullTestDataPath("TestData" + fileSep + "EARImportTests" + fileSep + "UtilityJarTestEAR.ear");
//        EnterpriseApplicationImportDataModel importModel = null;
//        try {
//            importModel = new EnterpriseApplicationImportDataModel();
//            importModel.setProperty(EnterpriseApplicationCreationDataModel.PROJECT_NAME, projectName);
//            importModel.setProperty(EnterpriseApplicationImportDataModel.FILE_NAME, earName);
//            importModel.setIntProperty(EnterpriseApplicationCreationDataModel.APPLICATION_VERSION, J2EEVersionConstants.J2EE_1_3_ID);
//            EARFile file = importModel.getEARFile();
//            List utilJars = getUtilityJarsInEAR(file);
//            if (!utilJars.isEmpty())
//                importModel.setProperty(EnterpriseApplicationImportDataModel.UTILITY_LIST, utilJars);
//            //TODO to be changed when the new property is available, EARImportDataModel.CREATE_BINARY_PROJECT is
//            // removed
//            //importModel.setBooleanProperty(EARImportDataModel.CREATE_BINARY_PROJECT, false);
//            runAndVerify(importModel);
//        } finally {
//            importModel.dispose();
//        }
//    }
//    
//    public void testImportWebLibWithMetaData() throws Exception {
//        String projectName = "YourCompanyExampleEAR";
//        String earName = getFullTestDataPath("TestData" + fileSep + "EARImportTests" + fileSep + "YourCompanyExampleEAR.ear");
//        EnterpriseApplicationImportDataModel importModel = null;
//        try {
//            importModel = new EnterpriseApplicationImportDataModel();
//            importModel.setProperty(EnterpriseApplicationCreationDataModel.PROJECT_NAME, projectName);
//            importModel.setProperty(EnterpriseApplicationImportDataModel.FILE_NAME, earName);
//            importModel.setIntProperty(EnterpriseApplicationCreationDataModel.APPLICATION_VERSION, J2EEVersionConstants.J2EE_1_3_ID);
//            EARFile file = importModel.getEARFile();
//            List webLibs = getWebLibs(file);
//            //TODO to be changed when the new property is available, EARImportDataModel.CREATE_BINARY_PROJECT is
//            // removed
//            //importModel.setBooleanProperty(EARImportDataModel.CREATE_BINARY_PROJECT, false);
//            List projectModels = importModel.getProjectModels();
//            for (int i = 0; i < projectModels.size(); i++) {
//                if (projectModels.get(i) instanceof J2EEModuleCreationDataModel) {
//                    J2EEModuleCreationDataModel projectDataModel = (J2EEModuleCreationDataModel) projectModels.get(i);
//                    if (projectDataModel.getJ2EENatureID().equals(J2EEWebNatureRuntime.J2EE_NATURE_ID)) {
//                        projectDataModel.setProperty(WebModuleCreationDataModel.JAR_LIST_TEXT_UI, webLibs);
//                    }
//                }
//            }
//            importModel.setBooleanProperty(EnterpriseApplicationImportDataModel.PRESERVE_PROJECT_METADATA, true);
//            runAndVerify(importModel);
//        } finally {
//            importModel.dispose();
//        }
//    }*/
//    //TODO M4 Action Item - enable the util jar tests after the fixes for util jar import are available in M4
//    /*public void testImportUtilityJarsAndWebLibWithMetaData() throws Exception {
//        String projectName = "UtilityJarWebLibWithMetaDataFilesEAR";
//        String earName = getFullTestDataPath("TestData" + fileSep + "EARImportTests" + fileSep + "UtilityJarWebLibWithMetaDataFilesEAR.ear");
//        EnterpriseApplicationImportDataModel importModel = null;
//        try {
//            importModel = new EnterpriseApplicationImportDataModel();
//            importModel.setProperty(EnterpriseApplicationCreationDataModel.PROJECT_NAME, projectName);
//            importModel.setProperty(EnterpriseApplicationImportDataModel.FILE_NAME, earName);
//            importModel.setIntProperty(EnterpriseApplicationCreationDataModel.APPLICATION_VERSION, J2EEVersionConstants.J2EE_1_3_ID);
//            EARFile file = importModel.getEARFile();
//            List utilJars = getUtilityJarsInEAR(file);
//            if (!utilJars.isEmpty())
//                importModel.setProperty(EnterpriseApplicationImportDataModel.UTILITY_LIST, utilJars);
//            List webLibs = getWebLibs(file);
//            //TODO to be changed when the new property is available, EARImportDataModel.CREATE_BINARY_PROJECT is
//            // removed
//            //importModel.setBooleanProperty(EARImportDataModel.CREATE_BINARY_PROJECT, false);
//            List projectModels = importModel.getProjectModels();
//            for (int i = 0; i < projectModels.size(); i++) {
//                if (projectModels.get(i) instanceof J2EEModuleCreationDataModel) {
//                    J2EEModuleCreationDataModel projectDataModel = (J2EEModuleCreationDataModel) projectModels.get(i);
//                    if (projectDataModel.getJ2EENatureID().equals(J2EEWebNatureRuntime.J2EE_NATURE_ID)) {
//                        projectDataModel.setProperty(WebModuleCreationDataModel.JAR_LIST_TEXT_UI, webLibs);
//                    }
//                }
//            }
//            importModel.setBooleanProperty(EnterpriseApplicationImportDataModel.PRESERVE_PROJECT_METADATA, false);
//            runAndVerify(importModel);
//        } finally {
//            importModel.dispose();
//        }
//    }*/
//
//    /**
//     * @param file
//     * @return
//     */
//    private List getWebLibs(EARFile earFile) {
//        List files = earFile.getArchiveFiles();
//        List webLibs = new ArrayList();
//        for (int i = 0; i < files.size(); i++) {
//            FileImpl file = (FileImpl) files.get(i);
//            if (file.isWARFile()) {
//                List webLib = ((WARFileImpl) file).getLibArchives();
//                if (!webLib.isEmpty())
//                    webLibs.addAll(webLib);
//            }
//        }
//        return webLibs;
//    }
//
//    public static String getFullTestDataPath(String dataPath) {
//    	try {
//    	  return ProjectUtility.getFullFileName(HeadlessTestsPlugin.getDefault(),dataPath);
//    	} catch(Exception e) {
//    		e.printStackTrace();
//    	}
//    	return "";
//    }
//
//    public void testImportEAR14() throws Exception {
////        String projectName = "Test14WEBEJBEAR";
////        String earName = getFullTestDataPath("TestData" + fileSep + "EARImportTests" + fileSep + "Test14WEBEJBEAR.ear");
////        EnterpriseApplicationImportDataModel importModel = null;
////        try {
////            importModel = new EnterpriseApplicationImportDataModel();
////            importModel.setProperty(EnterpriseApplicationImportDataModel.PROJECT_NAME, projectName);
////            importModel.setProperty(EnterpriseApplicationImportDataModel.FILE_NAME, earName);
////            runAndVerify(importModel);
////        } finally {
////            importModel.dispose();
////        }
//    }
//
//    public static Test suite() {
//        return new SimpleTestSuite(EARImportOperationTest.class);
//    }
//
//    public void testImportProjectNameCollisionForEARImport() {
//        try {
//            String earName = "TestData" + fileSep + "EARImportTests" + fileSep + "Test13WEBEJBEAR.ear";
//            load(null, earName);
//            load(null, earName);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }
//
//    public void testImportEAR12() throws Exception {
//        String projectName = "Test12WEBEJBEAR";
//        String earName = "TestData" + fileSep + "EARImportTests" + fileSep + "Test12WEBEJBEAR.ear";
//        load(projectName, earName);
//    }
//    public void testImportEAR13() throws Exception {
//        String projectName = "Test13WEBEJBEAR";
//        String earName = "TestData" + fileSep + "EARImportTests" + fileSep + "Test13WEBEJBEAR.ear";
//        load(projectName, earName);
//    }
//    public static void testAllEARImportProjects() throws Exception {
//        String projectName = "EARTest";
//        String earName = "TestData" + fileSep + "EARImportTests" + fileSep;
//        List projects = ProjectUtility.getEarsInDirectory(HeadlessTestsPlugin.getDefault(),getFullTestDataPath(earName));
//        for (int i = 0; i < projects.size(); i++) {
//            deleteAllProjects();
//            LogUtility.getInstance().resetLogging();
//            load(projectName, earName + projects.get(i));
//        }
//    }
//
//    public static void load(String projectName, String earName) throws Exception {
//		// EnterpriseApplicationImportDataModel importModel = null;
//		// try {
//		// importModel = new EnterpriseApplicationImportDataModel();
//		// importModel.setProperty(EnterpriseApplicationImportDataModel.PROJECT_NAME, projectName);
//		// importModel.setProperty(EnterpriseApplicationImportDataModel.FILE_NAME,
//		// getFullTestDataPath(earName));
//		// runAndVerify(importModel);
//		// } finally {
//		// importModel.dispose();
//		//        }
//    }
//
//    public List getArchiveInEAR(String earFileURI) {
//        CommonarchiveFactory factory = CommonarchiveFactory.eINSTANCE;
//        List moduleArchives = new ArrayList();
//        try {
//            Archive archive = factory.openArchive(earFileURI);
//            List files = archive.getFiles();
//            if (!files.isEmpty()) {
//                for (int i = 0; i < files.size(); i++) {
//                    moduleArchives = new ArrayList();
//                    File file = (File) files.get(i);
//                    if (file.isArchive()) {
//                        moduleArchives.add(file);
//                    }
//                }
//            }
//        } catch (OpenFailureException oe) {
//            oe.printStackTrace();
//        }
//        return moduleArchives;
//    }
//
//    protected String getDirectory() {
//		return "EARImportTests";
//	}
//
//    protected List getImportableArchiveFileNames() {
//		return ProjectUtility.getEarsInDirectory(HeadlessTestsPlugin.getDefault(), TESTS_PATH);
//	}
//
//	protected IDataModel getModelInstance() {
//		return DataModelFactory.createDataModel(new EARComponentImportDataModelProvider());
//	}

}
