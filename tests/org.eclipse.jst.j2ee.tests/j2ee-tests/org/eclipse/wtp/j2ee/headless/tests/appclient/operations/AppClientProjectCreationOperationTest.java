/*
 * Created on Nov 6, 2003
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package org.eclipse.wtp.j2ee.headless.tests.appclient.operations;

import junit.framework.Test;

import org.eclipse.jst.j2ee.applicationclient.internal.creation.AppClientComponentCreationDataModelProvider;
import org.eclipse.wst.common.frameworks.datamodel.DataModelFactory;
import org.eclipse.wst.common.frameworks.datamodel.IDataModel;
import org.eclipse.wst.common.tests.SimpleTestSuite;
import org.eclipse.wtp.j2ee.headless.tests.j2ee.operations.ModuleProjectCreationOperationTest;

/**
 * @author jsholl
 *
 * To change the template for this generated type comment go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
public class AppClientProjectCreationOperationTest extends ModuleProjectCreationOperationTest {

    public static String DEFAULT_PROJECT_NAME = "SimpleAppClient";
    
    /**
	 * @param name
	 */
	public AppClientProjectCreationOperationTest(String name) {
		super(name);
		// TODO Auto-generated constructor stub
	}

	public static Test suite() {
        return new SimpleTestSuite(AppClientProjectCreationOperationTest.class);
    }

    public IDataModel getComponentCreationDataModel() {
        return DataModelFactory.createDataModel(new AppClientComponentCreationDataModelProvider());
    }

}
