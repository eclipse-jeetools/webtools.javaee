/*
 * Created on Jan 6, 2004
 * 
 * To change the template for this generated file go to Window - Preferences - Java - Code
 * Generation - Code and Comments
 */
package org.eclipse.wtp.j2ee.headless.tests.j2ee.operations;

import java.io.File;
import java.net.URL;
import java.util.List;

import junit.framework.Test;
import junit.framework.TestSuite;

import org.eclipse.core.runtime.Path;
import org.eclipse.jst.j2ee.application.operations.J2EEModuleImportDataModel;
import org.eclipse.wst.common.tests.ProjectUtility;
import org.eclipse.wtp.j2ee.headless.tests.appclient.operations.AppClientImportOperationTest;
import org.eclipse.wtp.j2ee.headless.tests.ejb.operations.EJBImportOperationTest;
import org.eclipse.wtp.j2ee.headless.tests.jca.operations.RARImportOperationTest;
import org.eclipse.wtp.j2ee.headless.tests.plugin.HeadlessTestsPlugin;
import org.eclipse.wtp.j2ee.headless.tests.web.operations.WebImportOperationTest;

/**
 * @author Administrator
 * 
 * To change the template for this generated type comment go to Window - Preferences - Java - Code
 * Generation - Code and Comments
 */
public abstract class ModuleImportOperationTestCase extends OperationTestCase {

	protected boolean isBinary = false;
	protected boolean overwriteProject = false;
	protected boolean dataModelShouldBeValid = true;
	

	public final String TESTS_PATH;
	public static final String VALID_SERVER_TARGET_ID_VALUE = "org.acme.target.test";

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
		String relativeImportTestsPath = "TestData" + File.separator + getDirectory() + File.separator;
		URL fullImportTestsPath = HeadlessTestsPlugin.getDefault().find(new Path(relativeImportTestsPath));
		TESTS_PATH = fullImportTestsPath.getPath();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.wtp.j2ee.headless.tests.j2ee.operations.OperationTestCase#setUp()
	 */
	protected void setUp() throws Exception {
		super.setUp();
		isBinary = false;
		overwriteProject = false;
		dataModelShouldBeValid = true;
	}

	protected abstract String getDirectory(); 
	protected abstract J2EEModuleImportDataModel getModelInstance();

	public void testImport(String projectName, String filename) throws Exception {

		J2EEModuleImportDataModel dataModel = getModelInstance();
		dataModel.setProperty(J2EEModuleImportDataModel.FILE_NAME, TESTS_PATH + filename);
		dataModel.setProperty(J2EEModuleImportDataModel.PROJECT_NAME, projectName);
		dataModel.setProperty(J2EEModuleImportDataModel.SERVER_TARGET_ID, VALID_SERVER_TARGET_ID_VALUE);
		// TODO revisit once refactorings are completed
		//dataModel.setBooleanProperty(J2EEImportDataModel.CREATE_BINARY_PROJECT, isBinary);
		dataModel.setBooleanProperty(J2EEModuleImportDataModel.OVERWRITE_PROJECT, overwriteProject);

		if (dataModelShouldBeValid)
			runAndVerify(dataModel);
		else 
			verifyInvalidDataModel(dataModel);
	}

	public void testAllBinaryImportTestCases() throws Exception {
		isBinary = true;
		testAllImportTestCases();
	}

	public void testAllImportTestCases() throws Exception {
 
		List projects = getImportableArchiveFileNames();
		for (int i = 0; i < projects.size(); i++) {
			String jarName =  projects.get(i).toString(); 
			String projectName = jarName.substring(0, jarName.length()-4);
			testImport(projectName, jarName);
		}
	} 
 
	/**
	 * @return
	 */
	protected List getImportableArchiveFileNames() {
		return ProjectUtility.getJarsInDirectory(HeadlessTestsPlugin.getDefault(),TESTS_PATH);
	}

	public void testBadFileName() throws Exception {
 
		dataModelShouldBeValid = false;
		testImport("BobTheProject", "BobTheFile");
	}

	public void testOverwriteProjectImportFail() throws Exception {

		overwriteProject = false;
		testAllImportTestCases();
		dataModelShouldBeValid = false;
		testAllImportTestCases();
	}
	
	public void testOverwriteProjectImportSucceed() throws Exception {

		overwriteProject = true; 
		testAllImportTestCases();
		testAllImportTestCases();
	} 
 
}
