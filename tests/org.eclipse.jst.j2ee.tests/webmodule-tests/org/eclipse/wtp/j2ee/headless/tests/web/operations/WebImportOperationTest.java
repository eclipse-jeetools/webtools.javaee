/*
 * Created on Jan 6, 2004
 *
 * To change the template for this generated file go to
 * Window - Preferences - Java - Code Generation - Code and Comments
 */
package org.eclipse.wtp.j2ee.headless.tests.web.operations;

import java.util.List;

import junit.framework.Test;
import junit.framework.TestSuite;

import org.eclipse.jst.j2ee.application.operations.J2EEModuleImportDataModel;
import org.eclipse.jst.j2ee.internal.web.archive.operations.WebModuleImportDataModel;
import org.eclipse.wst.common.tests.ProjectUtility;
import org.eclipse.wtp.j2ee.headless.tests.j2ee.operations.ModuleImportOperationTestCase;
import org.eclipse.wtp.j2ee.headless.tests.plugin.HeadlessTestsPlugin;

/**
 * @author Administrator
 *
 * To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Generation - Code and Comments
 */
public class WebImportOperationTest extends ModuleImportOperationTestCase {
	
	public WebImportOperationTest(String name) {
		super(name);
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
		return new WebModuleImportDataModel();
	}
	
	/* (non-Javadoc)
	 * @see org.eclipse.wtp.j2ee.headless.tests.j2ee.operations.ModuleImportOperationTestCase#getImportableArchiveFileNames()
	 */
	protected List getImportableArchiveFileNames() {
		return ProjectUtility.getWarsInDirectory(HeadlessTestsPlugin.getDefault(),TESTS_PATH);
	}

}
