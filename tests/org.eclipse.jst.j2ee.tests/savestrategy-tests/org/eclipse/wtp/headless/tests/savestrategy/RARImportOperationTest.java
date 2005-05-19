/*
 * Created on Jan 6, 2004
 * 
 * To change the template for this generated file go to Window - Preferences - Java - Code
 * Generation - Code and Comments
 */
package org.eclipse.wtp.headless.tests.savestrategy;

import java.util.List;

import junit.framework.Test;
import junit.framework.TestSuite;

import org.eclipse.jst.j2ee.internal.jca.operations.ConnectorModuleImportDataModelProvider;
import org.eclipse.wst.common.frameworks.datamodel.DataModelFactory;
import org.eclipse.wst.common.frameworks.datamodel.IDataModel;
import org.eclipse.wst.common.tests.ProjectUtility;
import org.eclipse.wtp.j2ee.headless.tests.plugin.HeadlessTestsPlugin;

public class RARImportOperationTest extends ModuleImportOperationTestCase {

	public static boolean isBinary = false;
	public static boolean overwriteProject = false;

	public static Test suite() {
		return new TestSuite(RARImportOperationTest.class);
	}

	public RARImportOperationTest(String name) {
		super(name);
	}

	protected String getDirectory() {
		return "RARImportTests";
	}


	protected List getImportableArchiveFileNames() {
		return ProjectUtility.getRarsInDirectory(HeadlessTestsPlugin.getDefault(), TESTS_PATH);
	}

	protected IDataModel getModelInstance() {
		return DataModelFactory.createDataModel(new ConnectorModuleImportDataModelProvider());
	}

}
