/*
 * Created on Jan 6, 2004
 * 
 * To change the template for this generated file go to Window - Preferences - Java - Code
 * Generation - Code and Comments
 */
package org.eclipse.wtp.headless.tests.savestrategy;

import java.io.File;
import java.util.List;

import junit.framework.Test;
import junit.framework.TestSuite;

import org.eclipse.jst.j2ee.datamodel.properties.IJ2EEComponentImportDataModelProperties;
import org.eclipse.wst.common.frameworks.datamodel.IDataModel;
import org.eclipse.wst.common.tests.OperationTestCase;
import org.eclipse.wst.common.tests.ProjectUtility;
import org.eclipse.wtp.j2ee.headless.tests.plugin.HeadlessTestsPlugin;

/**
 * @author Administrator
 * 
 * To change the template for this generated type comment go to Window - Preferences - Java - Code
 * Generation - Code and Comments
 */
public abstract class ModuleImportOperationTestCase extends OperationTestCase {

	protected boolean dataModelShouldBeValid = true;
	public String TESTS_PATH;


	public static Test suite() {
		TestSuite suite = new TestSuite();
		suite.addTestSuite(RARImportOperationTest.class);
		suite.addTestSuite(EJBImportOperationTest.class);
		suite.addTestSuite(WebImportOperationTest.class);
		suite.addTestSuite(AppClientImportOperationTest.class);
		return suite;
	}

	public ModuleImportOperationTestCase(String name) {
		super(name);
		TESTS_PATH = "TestData" + File.separator + getDirectory() + File.separator;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.wtp.j2ee.headless.tests.j2ee.operations.OperationTestCase#setUp()
	 */
	protected void setUp() throws Exception {
		super.setUp();
		dataModelShouldBeValid = true;
	}

	protected abstract String getDirectory();

	protected abstract IDataModel getModelInstance();

	public void testImport(String projectName, String filename) throws Exception {
		IDataModel dataModel = getModelInstance();
		dataModel.setProperty(IJ2EEComponentImportDataModelProperties.FILE_NAME, filename);
		dataModel.setProperty(IJ2EEComponentImportDataModelProperties.PROJECT_NAME, projectName);
		if (dataModelShouldBeValid)
			runDataModel(dataModel);
		else
			verifyInvalidDataModel(dataModel);
	}

	/**
	 * @param dataModel
	 */
	public void setServerTargetProperty(IDataModel dataModel) {
		// dataModel.setProperty(IJavaComponentCreationDataModelProperties.SERVER_TARGET_ID,
		// AllPluginTests.JONAS_SERVER.getId());
	}

	public void testImportTestCase_0() throws Exception {
		importIndex(0);
	}

	public void testImportTestCase_1() throws Exception {
		importIndex(1);
	}

	public void testImportTestCase_2() throws Exception {
		importIndex(2);
	}

	public void testImportTestCase_3() throws Exception {
		importIndex(3);
	}

	public void testImportTestCase_4() throws Exception {
		importIndex(4);
	}

	public void testImportTestCase_5() throws Exception {
		importIndex(5);
	}

	public void testImportTestCase_6() throws Exception {
		importIndex(6);
	}

	public void testImportTestCase_7() throws Exception {
		importIndex(7);
	}

	public void testImportTestCase_8() throws Exception {
		importIndex(8);
	}

	public void testImportTestCase_9() throws Exception {
		importIndex(9);
	}

	private void importIndex(int index) throws Exception {
		List projects = getImportableArchiveFileNames();
		if (index < projects.size()) {
			String jarName = projects.get(index).toString();
			String projectName = jarName.substring(jarName.lastIndexOf(File.separator) + 1, jarName.length() - 4);
			testImport(projectName, jarName);
		}
	}


	public void testImportTestCase_rest() throws Exception {
		List projects = getImportableArchiveFileNames();
		for (int i = 10; i < projects.size(); i++) {
			String jarName = projects.get(i).toString();
			String projectName = jarName.substring(jarName.lastIndexOf(File.separator) + 1, jarName.length() - 4);
			testImport(projectName, jarName);
		}
	}

	public void testAllImportTestCases() throws Exception {

		List projects = getImportableArchiveFileNames();
		for (int i = 0; i < projects.size(); i++) {
			String jarName = projects.get(i).toString();
			String projectName = jarName.substring(jarName.lastIndexOf(File.separator) + 1, jarName.length() - 4);
			testImport(projectName, jarName);
		}
	}

	/**
	 * @return
	 */
	protected List getImportableArchiveFileNames() {
		return ProjectUtility.getJarsInDirectory(HeadlessTestsPlugin.getDefault(), TESTS_PATH);
	}

	public void testBadFileName() throws Exception {

		dataModelShouldBeValid = false;
		testImport("BobTheProject", "BobTheFile");
	}

}
