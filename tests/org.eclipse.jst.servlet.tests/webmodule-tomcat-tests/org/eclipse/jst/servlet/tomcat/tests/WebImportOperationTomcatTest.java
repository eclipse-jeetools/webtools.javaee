/*
 * Created on Jan 6, 2004
 *
 * To change the template for this generated file go to
 * Window - Preferences - Java - Code Generation - Code and Comments
 */
package org.eclipse.jst.servlet.tomcat.tests;

import java.io.File;
import java.util.List;

import junit.framework.Test;

import org.eclipse.jst.j2ee.datamodel.properties.IJ2EEComponentImportDataModelProperties;
import org.eclipse.wst.common.frameworks.datamodel.IDataModel;
import org.eclipse.wst.common.tests.ProjectUtility;
import org.eclipse.wst.common.tests.SimpleTestSuite;
import org.eclipse.wtp.j2ee.headless.tests.j2ee.operations.ModuleImportOperationTest;
import org.eclipse.wtp.j2ee.headless.tests.plugin.HeadlessTestsPlugin;

/**
 * @author Administrator
 * 
 * To change the template for this generated type comment go to Window - Preferences - Java - Code
 * Generation - Code and Comments
 */
public class WebImportOperationTomcatTest extends ModuleImportOperationTest {

	private static final String TESTS_PATH = System.getProperty("user.dir") + java.io.File.separatorChar + "TestData" + java.io.File.separatorChar + "WARImportTests";
	protected boolean dataModelShouldBeValid = true;
	
	public WebImportOperationTomcatTest() {
		super("WebImportOperationTomcatTests");
	}
	
	public WebImportOperationTomcatTest(String name) {
		super(name);
	}

	public static Test suite() {
		return new SimpleTestSuite(WebImportOperationTomcatTest.class);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.wtp.j2ee.headless.tests.j2ee.operations.ModuleImportOperationTestCase#getModelInstance()
	 */
	protected IDataModel getModelInstance() {
		return null; // new WebModuleImportDataModel();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.wtp.j2ee.headless.tests.j2ee.operations.ModuleImportOperationTestCase#getImportableArchiveFileNames()
	 */
	protected List getImportableArchiveFileNames() {
		return ProjectUtility.getWarsInDirectory(HeadlessTestsPlugin.getDefault(), TESTS_PATH);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.wtp.j2ee.headless.tests.j2ee.operations.ModuleImportOperationTestCase#setServerTargetProperty(org.eclipse.jst.j2ee.application.operations.J2EEModuleImportDataModel)
	 */
	public void setServerTargetProperty(IDataModel dataModel) {
		// dataModel.setProperty(ServerTargetDataModel.RUNTIME_TARGET_ID,AllTomcatTests.TOMCAT_RUNTIME.getId());
	}
	
	public void testAllImportTestCases() throws Exception {

		List projects = getImportableArchiveFileNames();
		for (int i = 0; i < projects.size(); i++) {
			String jarName = projects.get(i).toString();
			String projectName = jarName.substring(jarName.lastIndexOf(File.separator) + 1, jarName.length() - 4);
			testImport(projectName, jarName);
		}
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
	
	public void testImport(String projectName, String filename) throws Exception {
		IDataModel dataModel = getModelInstance();
		dataModel.setProperty(IJ2EEComponentImportDataModelProperties.FILE_NAME, filename);
		dataModel.setProperty(IJ2EEComponentImportDataModelProperties.PROJECT_NAME, projectName);
		if (dataModelShouldBeValid)
			runAndVerify(dataModel);
		else
			verifyInvalidDataModel(dataModel);
	}

	public void testBadFileName() throws Exception {

		dataModelShouldBeValid = false;
		testImport("BobTheProject", "BobTheFile");
	}
	
	protected IDataModel getExportDataModel(String projectName,
			String destination, boolean exportSource, boolean runBuild,
			boolean overwriteExisting) {
		return null;
	}

	protected IDataModel getImportDataModel(String filePath,
			String projectName, 
			IDataModel creationModel, boolean closeArchiveOnDispose) {
		return null;
	}
	
	protected String getModuleExtension() {
		return null;
	}

}
