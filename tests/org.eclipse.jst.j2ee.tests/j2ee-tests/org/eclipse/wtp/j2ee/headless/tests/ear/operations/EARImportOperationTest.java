package org.eclipse.wtp.j2ee.headless.tests.ear.operations;

import java.util.ArrayList;
import java.util.List;

import junit.framework.Test;

import org.eclipse.jst.j2ee.application.internal.operations.EnterpriseApplicationImportDataModelProvider;
import org.eclipse.jst.j2ee.commonarchivecore.internal.Archive;
import org.eclipse.jst.j2ee.commonarchivecore.internal.CommonarchiveFactory;
import org.eclipse.jst.j2ee.commonarchivecore.internal.EARFile;
import org.eclipse.jst.j2ee.commonarchivecore.internal.File;
import org.eclipse.jst.j2ee.commonarchivecore.internal.exception.OpenFailureException;
import org.eclipse.jst.j2ee.commonarchivecore.internal.impl.FileImpl;
import org.eclipse.jst.j2ee.commonarchivecore.internal.impl.WARFileImpl;
import org.eclipse.wst.common.frameworks.datamodel.DataModelFactory;
import org.eclipse.wst.common.frameworks.datamodel.IDataModel;
import org.eclipse.wst.common.tests.LogUtility;
import org.eclipse.wst.common.tests.ProjectUtility;
import org.eclipse.wst.common.tests.SimpleTestSuite;
import org.eclipse.wtp.headless.tests.savestrategy.ModuleImportOperationTestCase;
import org.eclipse.wtp.j2ee.headless.tests.plugin.HeadlessTestsPlugin;

/**
 * @author vijayb
 * 
 * To change the template for this generated type comment go to Window - Preferences - Java - Code Generation - Code
 * and Comments
 */
public class EARImportOperationTest extends ModuleImportOperationTestCase {

    public static String fileSep = System.getProperty("file.separator");

   

    public EARImportOperationTest(String name) {
		super(name);
	}

	public List getUtilityJarsInEAR(EARFile earFile) {
        List utilJars = new ArrayList();
        try {
            List files = earFile.getArchiveFiles();
            for (int i = 0; i < files.size(); i++) {
                FileImpl file = (FileImpl) files.get(i);
                if (file.isArchive() && !file.isModuleFile() && file.getURI().endsWith(".jar")) //$NON-NLS-1$
                    utilJars.add(file);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return utilJars;
    }

    public void testEARImportRootLocation() throws Exception {
//    	EnterpriseApplicationImportDataModel importModel = null;
//        String projectName = "Test13WEBEJBEAR";
//        String earName = getFullTestDataPath("TestData" + fileSep + "EARImportTests" + fileSep + "Test13WEBEJBEAR.ear");
//        try {
//            importModel = new EnterpriseApplicationImportDataModel();
//            importModel.setProperty(EARComponentCreationDataModel.PROJECT_NAME, projectName);
//            importModel.setProperty(EnterpriseApplicationImportDataModel.FILE_NAME, earName);
//            importModel.setIntProperty(EARComponentCreationDataModel.COMPONENT_VERSION, J2EEVersionConstants.J2EE_1_3_ID);
//            //importModel.setProperty(EARComponentCreationDataModel.PROJECT_LOCATION, "c:" + fileSep + "EARTestTemp");
//            importModel.setProperty(EnterpriseApplicationImportDataModel.NESTED_MODULE_ROOT, "c:" + fileSep + "EARTestModulesTemp");
//            runAndVerify(importModel);
//        } finally {
//            importModel.dispose();
//        }
    }
    
    //TODO M4 Action Item - enable the util jar tests after the fixes for util jar import are available in M4

    /*public void testImportSomeUtilityJarsExploded() throws Exception {
        String projectName = "UtilityJarTestEAR";
        String earName = getFullTestDataPath("TestData" + fileSep + "EARImportTests" + fileSep + "UtilityJarTestEAR.ear");
        EnterpriseApplicationImportDataModel importModel = null;
        try {
            importModel = new EnterpriseApplicationImportDataModel();
            importModel.setProperty(EnterpriseApplicationCreationDataModel.PROJECT_NAME, projectName);
            importModel.setProperty(EnterpriseApplicationImportDataModel.FILE_NAME, earName);
            importModel.setIntProperty(EnterpriseApplicationCreationDataModel.APPLICATION_VERSION, J2EEVersionConstants.J2EE_1_3_ID);
            EARFile file = importModel.getEARFile();
            List utilJars = getUtilityJarsInEAR(file);
            if (!utilJars.isEmpty()) {
                utilJars.remove(0);
                utilJars.remove(2);
                importModel.setProperty(EnterpriseApplicationImportDataModel.UTILITY_LIST, utilJars);
            }
            //TODO to be changed when the new property is available, EARImportDataModel.CREATE_BINARY_PROJECT is
            // removed
            //importModel.setBooleanProperty(EARImportDataModel.CREATE_BINARY_PROJECT, false);
            runAndVerify(importModel);
        } finally {
            importModel.dispose();
        }
    }

    public void testImportUtilityJarsExploded() throws Exception {
        String projectName = "UtilityJarTestEAR";
        String earName = getFullTestDataPath("TestData" + fileSep + "EARImportTests" + fileSep + "UtilityJarTestEAR.ear");
        EnterpriseApplicationImportDataModel importModel = null;
        try {
            importModel = new EnterpriseApplicationImportDataModel();
            importModel.setProperty(EnterpriseApplicationCreationDataModel.PROJECT_NAME, projectName);
            importModel.setProperty(EnterpriseApplicationImportDataModel.FILE_NAME, earName);
            importModel.setIntProperty(EnterpriseApplicationCreationDataModel.APPLICATION_VERSION, J2EEVersionConstants.J2EE_1_3_ID);
            EARFile file = importModel.getEARFile();
            List utilJars = getUtilityJarsInEAR(file);
            if (!utilJars.isEmpty())
                importModel.setProperty(EnterpriseApplicationImportDataModel.UTILITY_LIST, utilJars);
            //TODO to be changed when the new property is available, EARImportDataModel.CREATE_BINARY_PROJECT is
            // removed
            //importModel.setBooleanProperty(EARImportDataModel.CREATE_BINARY_PROJECT, false);
            runAndVerify(importModel);
        } finally {
            importModel.dispose();
        }
    }
    
    public void testImportWebLibWithMetaData() throws Exception {
        String projectName = "YourCompanyExampleEAR";
        String earName = getFullTestDataPath("TestData" + fileSep + "EARImportTests" + fileSep + "YourCompanyExampleEAR.ear");
        EnterpriseApplicationImportDataModel importModel = null;
        try {
            importModel = new EnterpriseApplicationImportDataModel();
            importModel.setProperty(EnterpriseApplicationCreationDataModel.PROJECT_NAME, projectName);
            importModel.setProperty(EnterpriseApplicationImportDataModel.FILE_NAME, earName);
            importModel.setIntProperty(EnterpriseApplicationCreationDataModel.APPLICATION_VERSION, J2EEVersionConstants.J2EE_1_3_ID);
            EARFile file = importModel.getEARFile();
            List webLibs = getWebLibs(file);
            //TODO to be changed when the new property is available, EARImportDataModel.CREATE_BINARY_PROJECT is
            // removed
            //importModel.setBooleanProperty(EARImportDataModel.CREATE_BINARY_PROJECT, false);
            List projectModels = importModel.getProjectModels();
            for (int i = 0; i < projectModels.size(); i++) {
                if (projectModels.get(i) instanceof J2EEModuleCreationDataModel) {
                    J2EEModuleCreationDataModel projectDataModel = (J2EEModuleCreationDataModel) projectModels.get(i);
                    if (projectDataModel.getJ2EENatureID().equals(J2EEWebNatureRuntime.J2EE_NATURE_ID)) {
                        projectDataModel.setProperty(WebModuleCreationDataModel.JAR_LIST_TEXT_UI, webLibs);
                    }
                }
            }
            importModel.setBooleanProperty(EnterpriseApplicationImportDataModel.PRESERVE_PROJECT_METADATA, true);
            runAndVerify(importModel);
        } finally {
            importModel.dispose();
        }
    }*/
    //TODO M4 Action Item - enable the util jar tests after the fixes for util jar import are available in M4
    /*public void testImportUtilityJarsAndWebLibWithMetaData() throws Exception {
        String projectName = "UtilityJarWebLibWithMetaDataFilesEAR";
        String earName = getFullTestDataPath("TestData" + fileSep + "EARImportTests" + fileSep + "UtilityJarWebLibWithMetaDataFilesEAR.ear");
        EnterpriseApplicationImportDataModel importModel = null;
        try {
            importModel = new EnterpriseApplicationImportDataModel();
            importModel.setProperty(EnterpriseApplicationCreationDataModel.PROJECT_NAME, projectName);
            importModel.setProperty(EnterpriseApplicationImportDataModel.FILE_NAME, earName);
            importModel.setIntProperty(EnterpriseApplicationCreationDataModel.APPLICATION_VERSION, J2EEVersionConstants.J2EE_1_3_ID);
            EARFile file = importModel.getEARFile();
            List utilJars = getUtilityJarsInEAR(file);
            if (!utilJars.isEmpty())
                importModel.setProperty(EnterpriseApplicationImportDataModel.UTILITY_LIST, utilJars);
            List webLibs = getWebLibs(file);
            //TODO to be changed when the new property is available, EARImportDataModel.CREATE_BINARY_PROJECT is
            // removed
            //importModel.setBooleanProperty(EARImportDataModel.CREATE_BINARY_PROJECT, false);
            List projectModels = importModel.getProjectModels();
            for (int i = 0; i < projectModels.size(); i++) {
                if (projectModels.get(i) instanceof J2EEModuleCreationDataModel) {
                    J2EEModuleCreationDataModel projectDataModel = (J2EEModuleCreationDataModel) projectModels.get(i);
                    if (projectDataModel.getJ2EENatureID().equals(J2EEWebNatureRuntime.J2EE_NATURE_ID)) {
                        projectDataModel.setProperty(WebModuleCreationDataModel.JAR_LIST_TEXT_UI, webLibs);
                    }
                }
            }
            importModel.setBooleanProperty(EnterpriseApplicationImportDataModel.PRESERVE_PROJECT_METADATA, false);
            runAndVerify(importModel);
        } finally {
            importModel.dispose();
        }
    }*/

    /**
     * @param file
     * @return
     */
    private List getWebLibs(EARFile earFile) {
        List files = earFile.getArchiveFiles();
        List webLibs = new ArrayList();
        for (int i = 0; i < files.size(); i++) {
            FileImpl file = (FileImpl) files.get(i);
            if (file.isWARFile()) {
                List webLib = ((WARFileImpl) file).getLibArchives();
                if (!webLib.isEmpty())
                    webLibs.addAll(webLib);
            }
        }
        return webLibs;
    }

    public static String getFullTestDataPath(String dataPath) {
    	try {
    	  return ProjectUtility.getFullFileName(HeadlessTestsPlugin.getDefault(),dataPath);
    	} catch(Exception e) {
    		e.printStackTrace();
    	}
    	return "";
    }

    public void testImportEAR14() throws Exception {
//        String projectName = "Test14WEBEJBEAR";
//        String earName = getFullTestDataPath("TestData" + fileSep + "EARImportTests" + fileSep + "Test14WEBEJBEAR.ear");
//        EnterpriseApplicationImportDataModel importModel = null;
//        try {
//            importModel = new EnterpriseApplicationImportDataModel();
//            importModel.setProperty(EnterpriseApplicationImportDataModel.PROJECT_NAME, projectName);
//            importModel.setProperty(EnterpriseApplicationImportDataModel.FILE_NAME, earName);
//            runAndVerify(importModel);
//        } finally {
//            importModel.dispose();
//        }
    }

    public static Test suite() {
        return new SimpleTestSuite(EARImportOperationTest.class);
    }

    public void testImportProjectNameCollisionForEARImport() {
        try {
            String earName = "TestData" + fileSep + "EARImportTests" + fileSep + "Test13WEBEJBEAR.ear";
            load(null, earName);
            load(null, earName);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void testImportEAR12() throws Exception {
        String projectName = "Test12WEBEJBEAR";
        String earName = "TestData" + fileSep + "EARImportTests" + fileSep + "Test12WEBEJBEAR.ear";
        load(projectName, earName);
    }
    public void testImportEAR13() throws Exception {
        String projectName = "Test13WEBEJBEAR";
        String earName = "TestData" + fileSep + "EARImportTests" + fileSep + "Test13WEBEJBEAR.ear";
        load(projectName, earName);
    }
    public static void testAllEARImportProjects() throws Exception {
        String projectName = "EARTest";
        String earName = "TestData" + fileSep + "EARImportTests" + fileSep;
        List projects = ProjectUtility.getEarsInDirectory(HeadlessTestsPlugin.getDefault(),getFullTestDataPath(earName));
        for (int i = 0; i < projects.size(); i++) {
            deleteAllProjects();
            LogUtility.getInstance().resetLogging();
            load(projectName, earName + projects.get(i));
        }
    }

    public static void load(String projectName, String earName) throws Exception {
		// EnterpriseApplicationImportDataModel importModel = null;
		// try {
		// importModel = new EnterpriseApplicationImportDataModel();
		// importModel.setProperty(EnterpriseApplicationImportDataModel.PROJECT_NAME, projectName);
		// importModel.setProperty(EnterpriseApplicationImportDataModel.FILE_NAME,
		// getFullTestDataPath(earName));
		// runAndVerify(importModel);
		// } finally {
		// importModel.dispose();
		//        }
    }

    public List getArchiveInEAR(String earFileURI) {
        CommonarchiveFactory factory = CommonarchiveFactory.eINSTANCE;
        List moduleArchives = new ArrayList();
        try {
            Archive archive = factory.openArchive(earFileURI);
            List files = archive.getFiles();
            if (!files.isEmpty()) {
                for (int i = 0; i < files.size(); i++) {
                    moduleArchives = new ArrayList();
                    File file = (File) files.get(i);
                    if (file.isArchive()) {
                        moduleArchives.add(file);
                    }
                }
            }
        } catch (OpenFailureException oe) {
            oe.printStackTrace();
        }
        return moduleArchives;
    }

    protected String getDirectory() {
		return "EARImportTests";
	}

    protected List getImportableArchiveFileNames() {
		return ProjectUtility.getEarsInDirectory(HeadlessTestsPlugin.getDefault(), TESTS_PATH);
	}

	protected IDataModel getModelInstance() {
		return DataModelFactory.createDataModel(new EnterpriseApplicationImportDataModelProvider());
	}

}
