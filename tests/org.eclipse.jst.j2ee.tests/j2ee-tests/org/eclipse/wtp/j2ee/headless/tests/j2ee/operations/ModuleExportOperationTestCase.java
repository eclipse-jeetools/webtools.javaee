/*
 * Created on Jan 6, 2004
 * 
 * To change the template for this generated file go to Window - Preferences - Java - Code
 * Generation - Code and Comments
 */
package org.eclipse.wtp.j2ee.headless.tests.j2ee.operations;

import java.io.File;
import java.io.IOException;

import junit.framework.Test;
import junit.framework.TestSuite;

import org.eclipse.core.resources.IProject;
import org.eclipse.jst.j2ee.application.internal.operations.J2EEModuleExportDataModel;
import org.eclipse.wst.common.tests.ProjectUtility;
import org.eclipse.wtp.j2ee.headless.tests.appclient.operations.AppClientExportOperationTest;
import org.eclipse.wtp.j2ee.headless.tests.ejb.operations.EJBExportOperationTest;
import org.eclipse.wtp.j2ee.headless.tests.jca.operations.RARExportOperationTest;
import org.eclipse.wtp.j2ee.headless.tests.plugin.HeadlessTestsPlugin;
import org.eclipse.wtp.j2ee.headless.tests.web.operations.WebExportOperationTest;

/**
 * @author Administrator
 * 
 * To change the template for this generated type comment go to Window - Preferences - Java - Code
 * Generation - Code and Comments
 */
public abstract class ModuleExportOperationTestCase extends OperationTestCase {

	protected boolean exportSourceFiles = false;
	protected boolean overwriteExisting = false;
	protected boolean dataModelShouldBeValid = true;

	public final String TESTS_OUTPUT_PATH;

	public static Test suite() {
		TestSuite suite = new TestSuite();
		suite.addTestSuite(RARExportOperationTest.class);
		suite.addTestSuite(EJBExportOperationTest.class);
		suite.addTestSuite(WebExportOperationTest.class);
		suite.addTestSuite(AppClientExportOperationTest.class);
		return suite;
	}

	public ModuleExportOperationTestCase(String name) {
		super(name);

		String relativeOutputTestsPath = "TestData" + File.separator + getOutputDirectory() + File.separator;
		TESTS_OUTPUT_PATH = getOutputPath(relativeOutputTestsPath);
	}
	
	public String getOutputPath(String relPath) {
		try{
			return ProjectUtility.getFullFileName(HeadlessTestsPlugin.getDefault(),relPath);
			} catch(IOException e) {
				e.printStackTrace();
			}
		return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.wtp.j2ee.headless.tests.j2ee.operations.OperationTestCase#setUp()
	 */
	protected void setUp() throws Exception {
		super.setUp();
		exportSourceFiles = false;
		overwriteExisting = false;
	}

	private String getOutputDirectory(){
	    return "testOutput";
	}
	protected abstract ModuleImportOperationTestCase getImportTestCase();
	protected abstract J2EEModuleExportDataModel getModelInstance();

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.wtp.j2ee.headless.tests.j2ee.operations.ModuleExportOperationTestCase#getExportableProjects()
	 */
	protected IProject[] getExportableProjects() throws Exception {
		ProjectUtility.deleteAllProjects();
		ModuleImportOperationTestCase importTestCase = getImportTestCase(); 
		importTestCase.testAllImportTestCases();

		// if the projects aren't created successfully, the previous
		// line will fail so there's no need to verify
		return ProjectUtility.getAllProjects();
	}

	public void testExport(String projectName, String filename) throws Exception {

		J2EEModuleExportDataModel dataModel = getModelInstance();
		dataModel.setProperty(J2EEModuleExportDataModel.ARCHIVE_DESTINATION, TESTS_OUTPUT_PATH + filename);
		dataModel.setProperty(J2EEModuleExportDataModel.PROJECT_NAME, projectName);
		dataModel.setBooleanProperty(J2EEModuleExportDataModel.EXPORT_SOURCE_FILES, exportSourceFiles);
		dataModel.setBooleanProperty(J2EEModuleExportDataModel.OVERWRITE_EXISTING, overwriteExisting);

		if (dataModelShouldBeValid)
			runAndVerify(dataModel);
		else
			verifyInvalidDataModel(dataModel);
	}

	public void testAllWithExportSourceFilesWithOverwriteExisting() throws Exception {
		exportSourceFiles = true;
		overwriteExisting = true;

		testAllExportTestCases();
	}

	public void testAllWithExportSourceFilesWithoutOverwriteExisting() throws Exception {
		exportSourceFiles = true;
		overwriteExisting = false;

		testAllExportTestCases();
	}

	public void testAllWithoutExportSourceFilesWithoutOverwriteExisting() throws Exception {
		exportSourceFiles = false;
		overwriteExisting = false;

		testAllExportTestCases();
	}

	public void testAllWithoutExportSourceFilesWithOverwriteExisting() throws Exception {
		exportSourceFiles = false;
		overwriteExisting = true;

		testAllExportTestCases();
	}

//	public void testOverwriteProjectExportFail() throws Exception {
//
//		testAllWithoutExportSourceFilesWithoutOverwriteExisting();
//		dataModelShouldBeValid = false;
//		testAllWithoutExportSourceFilesWithoutOverwriteExisting();
//	}

	public void testOverwriteProjectExportSucceed() throws Exception {

		testAllWithoutExportSourceFilesWithOverwriteExisting();
		testAllWithoutExportSourceFilesWithOverwriteExisting();
	}

	public void testAllExportTestCases() throws Exception {

		File exportDirectory = new File(TESTS_OUTPUT_PATH);
		if (exportDirectory.isDirectory()) {
			File[] contents = exportDirectory.listFiles();
			for (int i = 0; i < contents.length; i++) {
				if (!contents[i].isDirectory())
					contents[i].delete();
			}
		}
		IProject[] projects = getExportableProjects();
		for (int i = 0; i < projects.length; i++) {
			testExport(projects[i].getName(), getFileName(projects[i].getName()));
		}
	}

	public String getFileName(String baseName) {
		StringBuffer result = new StringBuffer(baseName);
		result.append((exportSourceFiles) ? "_withSource" : "_withoutSource").append(getModuleExportFileExt());
		return result.toString();
	}

	/**
	 * @return
	 */
	public String getModuleExportFileExt() {
		return ".jar";
	}
}
