/*
 * Created on Jan 6, 2004
 *
 * To change the template for this generated file go to
 * Window - Preferences - Java - Code Generation - Code and Comments
 */
package org.eclipse.wtp.headless.tests.savestrategy;

import org.eclipse.jst.j2ee.applicationclient.internal.creation.AppClientModuleImportDataModelProvider;
import org.eclipse.wst.common.frameworks.datamodel.DataModelFactory;
import org.eclipse.wst.common.frameworks.datamodel.IDataModel;

/**
 * @author Administrator
 * 
 * To change the template for this generated type comment go to Window - Preferences - Java - Code
 * Generation - Code and Comments
 */
public class AppClientImportOperationTest extends ModuleImportOperationTestCase {

	public AppClientImportOperationTest(String name) {
		super(name);
	}

	protected String getDirectory() {
		return "AppClientTests";
	}

	protected IDataModel getModelInstance() {
		return DataModelFactory.createDataModel(new AppClientModuleImportDataModelProvider());
	}

}
