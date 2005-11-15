/*******************************************************************************
 * Copyright (c) 2003, 2004, 2005 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 * IBM Corporation - initial API and implementation
 *******************************************************************************/
package org.eclipse.wtp.j2ee.headless.tests.appclient.operations;

import junit.framework.Test;
import junit.framework.TestSuite;

import org.eclipse.jst.j2ee.applicationclient.internal.creation.AppClientFacetProjectCreationDataModelProvider;
import org.eclipse.wst.common.componentcore.datamodel.properties.IFacetProjectCreationDataModelProperties;
import org.eclipse.wst.common.frameworks.datamodel.DataModelFactory;
import org.eclipse.wst.common.frameworks.datamodel.IDataModel;
import org.eclipse.wst.common.tests.OperationTestCase;


public class FlexibleAppClientModuleCreationTest extends OperationTestCase {

    public static String DEFAULT_PROJECT_NAME = "SimpleAppClientProject";
    public static String DEFAULT_MODULE_NAME= "SimpleAppClientModule";
    
    public static Test suite() {
        TestSuite suite = new TestSuite();
        suite.addTestSuite(FlexibleAppClientModuleCreationTest.class);
        return suite;
    }
    
    public void testDefaults() throws Exception {
       createAppClientModule(DEFAULT_MODULE_NAME);
    }

    
    public void createAppClientModule(String moduleName) throws Exception {
        IDataModel dataModel = getFlexibleAppClientModuleCreation();
        dataModel.setProperty(IFacetProjectCreationDataModelProperties.FACET_PROJECT_NAME, DEFAULT_PROJECT_NAME);
        runAndVerify(dataModel);
    }
    
    public IDataModel getFlexibleAppClientModuleCreation(){
		return DataModelFactory.createDataModel(new AppClientFacetProjectCreationDataModelProvider());
    }
}
