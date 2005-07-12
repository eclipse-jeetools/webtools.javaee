/*
 * Created on Jan 6, 2004
 *
 * To change the template for this generated file go to
 * Window - Preferences - Java - Code Generation - Code and Comments
 */
package org.eclipse.jst.servlet.tomcat.tests;

import java.util.List;

import junit.framework.Test;

import org.eclipse.wst.common.frameworks.datamodel.IDataModel;
import org.eclipse.wst.common.tests.ProjectUtility;
import org.eclipse.wst.common.tests.SimpleTestSuite;
import org.eclipse.wtp.headless.tests.savestrategy.ModuleImportOperationTestCase;
import org.eclipse.wtp.j2ee.headless.tests.plugin.HeadlessTestsPlugin;

/**
 * @author Administrator
 * 
 * To change the template for this generated type comment go to Window - Preferences - Java - Code
 * Generation - Code and Comments
 */
public class WebImportOperationTomcatTest extends ModuleImportOperationTestCase {

	public WebImportOperationTomcatTest(String name) {
		super(name);
	}

	public static Test suite() {
		return new SimpleTestSuite(WebImportOperationTomcatTest.class);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.wtp.j2ee.headless.tests.j2ee.operations.ModuleImportOperationTestCase#getDirectory()
	 */
	protected String getDirectory() {
		return "WARImportTests";
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

}
