/*
 * Created on Jan 6, 2004
 * 
 * To change the template for this generated file go to Window - Preferences - Java - Code
 * Generation - Code and Comments
 */
package org.eclipse.wtp.j2ee.headless.tests.appclient.operations;

import junit.framework.Test;

import org.eclipse.jst.j2ee.application.internal.operations.AppClientModuleExportDataModel;
import org.eclipse.jst.j2ee.application.internal.operations.J2EEModuleExportDataModel;
import org.eclipse.wst.common.tests.SimpleTestSuite;
import org.eclipse.wtp.j2ee.headless.tests.j2ee.operations.ModuleExportOperationTestCase;
import org.eclipse.wtp.j2ee.headless.tests.j2ee.operations.ModuleImportOperationTestCase;

/**
 * @author Administrator
 * 
 * To change the template for this generated type comment go to Window - Preferences - Java - Code
 * Generation - Code and Comments
 */
public class AppClientExportOperationTest extends ModuleExportOperationTestCase {

	public AppClientExportOperationTest(String name) {
		super(name);
	}
	public static Test suite() {
		return new SimpleTestSuite(AppClientExportOperationTest.class);
	}
	
	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.wtp.j2ee.headless.tests.j2ee.operations.ModuleExportOperationTestCase#getModelInstance()
	 */
	protected J2EEModuleExportDataModel getModelInstance() {
		return new AppClientModuleExportDataModel();
	}
	
	
	/* (non-Javadoc)
	 * @see org.eclipse.wtp.j2ee.headless.tests.j2ee.operations.ModuleExportOperationTestCase#getImportTestCase()
	 */
	protected ModuleImportOperationTestCase getImportTestCase() {
		return new AppClientImportOperationTest("");
	}

}
