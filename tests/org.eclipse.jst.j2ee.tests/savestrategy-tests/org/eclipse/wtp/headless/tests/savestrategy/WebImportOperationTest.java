/*
 * Created on Jan 6, 2004
 *
 * To change the template for this generated file go to
 * Window - Preferences - Java - Code Generation - Code and Comments
 */
package org.eclipse.wtp.headless.tests.savestrategy;

import java.io.File;
import java.net.URL;
import java.util.List;

import junit.framework.Test;
import junit.framework.TestSuite;

import org.eclipse.core.runtime.Path;
import org.eclipse.jst.j2ee.internal.web.archive.operations.WebComponentImportDataModelProvider;
import org.eclipse.wst.common.frameworks.datamodel.DataModelFactory;
import org.eclipse.wst.common.frameworks.datamodel.IDataModel;
import org.eclipse.wst.common.tests.ProjectUtility;
import org.eclipse.wtp.j2ee.headless.tests.plugin.HeadlessTestsPlugin;

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

	protected String getDirectory() {
		return "WARImportTests";
	}

	protected List getImportableArchiveFileNames() {
		return ProjectUtility.getWarsInDirectory(HeadlessTestsPlugin.getDefault(), WEB_TESTS_PATH);
	}

	protected IDataModel getModelInstance() {
		return DataModelFactory.createDataModel(new WebComponentImportDataModelProvider());
	}

}
