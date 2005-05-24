/*
 * Created on Jan 6, 2004
 *
 * To change the template for this generated file go to
 * Window - Preferences - Java - Code Generation - Code and Comments
 */
package org.eclipse.wtp.j2ee.headless.tests.web.operations;

import java.io.File;
import java.net.URL;
import java.util.List;

import junit.framework.Test;
import junit.framework.TestSuite;

import org.eclipse.core.runtime.Path;
import org.eclipse.jst.j2ee.application.internal.operations.J2EEModuleImportDataModel;
import org.eclipse.wst.common.tests.ProjectUtility;
import org.eclipse.wtp.j2ee.headless.tests.j2ee.operations.ModuleImportOperationTestCase;
import org.eclipse.wtp.j2ee.headless.tests.plugin.HeadlessTestsPlugin;

//TODO DELETE
/**
 * @deprecated
 *
 */
public class WebImportOperationTest extends ModuleImportOperationTestCase {
	
	public final String WEB_TESTS_PATH;
	
	public WebImportOperationTest(String name) {
		super(name);
		String relativeImportTestsPath = "TestData" + File.separator + getDirectory() + File.separator;
		URL fullImportTestsPath = HeadlessTestsPlugin.getDefault().find(new Path(relativeImportTestsPath));
		WEB_TESTS_PATH = fullImportTestsPath.getPath();
	}	
	
	public static Test suite() {
		return new TestSuite(WebImportOperationTest.class);
	}

	/* (non-Javadoc)
	 * @see org.eclipse.wtp.j2ee.headless.tests.j2ee.operations.ModuleImportOperationTestCase#getDirectory()
	 */
	protected String getDirectory() {
		return "WARImportTests";
	}
 
	/* (non-Javadoc)
	 * @see org.eclipse.wtp.j2ee.headless.tests.j2ee.operations.ModuleImportOperationTestCase#getModelInstance()
	 */
	protected J2EEModuleImportDataModel getModelInstance() {
		return null;//new WebModuleImportDataModel();
	}
	
	/* (non-Javadoc)
	 * @see org.eclipse.wtp.j2ee.headless.tests.j2ee.operations.ModuleImportOperationTestCase#getImportableArchiveFileNames()
	 */
	protected List getImportableArchiveFileNames() {
		return ProjectUtility.getWarsInDirectory(HeadlessTestsPlugin.getDefault(),WEB_TESTS_PATH);
	}

}
