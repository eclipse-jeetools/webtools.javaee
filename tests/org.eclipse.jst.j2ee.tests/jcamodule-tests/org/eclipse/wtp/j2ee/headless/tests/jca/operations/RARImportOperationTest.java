/*
 * Created on Jan 6, 2004
 * 
 * To change the template for this generated file go to Window - Preferences - Java - Code
 * Generation - Code and Comments
 */
package org.eclipse.wtp.j2ee.headless.tests.jca.operations;

import java.util.List;

import junit.framework.Test;
import junit.framework.TestSuite;

import org.eclipse.wst.common.frameworks.datamodel.IDataModel;
import org.eclipse.wst.common.tests.ProjectUtility;
import org.eclipse.wtp.j2ee.headless.tests.j2ee.operations.ModuleImportOperationTestCase;
import org.eclipse.wtp.j2ee.headless.tests.plugin.HeadlessTestsPlugin;

//TODO DELETE
/**
 * @deprecated
 *
 */
public class RARImportOperationTest extends ModuleImportOperationTestCase {

	public static boolean isBinary = false;
	public static boolean overwriteProject = false;

	public static Test suite() {
		return new TestSuite(RARImportOperationTest.class);
	}
 
	public RARImportOperationTest(String name) {
		super(name);
	}

	/* (non-Javadoc)
	 * @see org.eclipse.wtp.j2ee.headless.tests.j2ee.operations.ModuleImportOperationTestCase#getDirectory()
	 */
	protected String getDirectory() {
		return "RARImportTests";
	}
	
	/* (non-Javadoc)
	 * @see org.eclipse.wtp.j2ee.headless.tests.j2ee.operations.ModuleImportOperationTestCase#getModelInstance()
	 */
	protected IDataModel getModelInstance() {
		return null;//new ConnectorModuleImportDataModel();
	}
	
	/* (non-Javadoc)
	 * @see org.eclipse.wtp.j2ee.headless.tests.j2ee.operations.ModuleImportOperationTestCase#getImportableArchiveFileNames()
	 */
	protected List getImportableArchiveFileNames() {
		return ProjectUtility.getRarsInDirectory(HeadlessTestsPlugin.getDefault(),TESTS_PATH);
	}
 
}
