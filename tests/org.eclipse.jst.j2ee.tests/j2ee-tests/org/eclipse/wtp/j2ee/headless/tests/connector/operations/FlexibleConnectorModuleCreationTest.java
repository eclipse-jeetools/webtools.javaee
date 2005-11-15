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
package org.eclipse.wtp.j2ee.headless.tests.connector.operations;

import junit.framework.Test;
import junit.framework.TestSuite;

import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.jst.j2ee.application.internal.operations.FlexibleJavaProjectCreationDataModelProvider;
import org.eclipse.jst.j2ee.jca.project.facet.ConnectorFacetProjectCreationDataModelProvider;
import org.eclipse.jst.j2ee.project.datamodel.properties.IFlexibleJavaProjectCreationDataModelProperties;
import org.eclipse.wst.common.componentcore.datamodel.properties.IFacetProjectCreationDataModelProperties;
import org.eclipse.wst.common.frameworks.datamodel.DataModelFactory;
import org.eclipse.wst.common.frameworks.datamodel.IDataModel;
import org.eclipse.wst.common.frameworks.datamodel.IDataModelOperation;
import org.eclipse.wst.common.tests.OperationTestCase;


public class FlexibleConnectorModuleCreationTest extends OperationTestCase {

    public static String DEFAULT_PROJECT_NAME = "SimpleAppClientProject";
    public static String DEFAULT_MODULE_NAME= "SimpleAppClientModule";
    
    public static Test suite() {
        TestSuite suite = new TestSuite();
        suite.addTestSuite(FlexibleConnectorModuleCreationTest.class);
        return suite;
    }
    
    public void testDefaults() throws Exception {
       createProject(DEFAULT_PROJECT_NAME);
        createAppClientModule(DEFAULT_MODULE_NAME);
    }

    public void createProject(String projectName) throws Exception {
        IDataModel dataModel = getProjectCreationDataModel();
        dataModel.setProperty(IFlexibleJavaProjectCreationDataModelProperties.PROJECT_NAME, projectName);
        IDataModelOperation op = dataModel.getDefaultOperation();
        op.execute(new NullProgressMonitor(), null);
    }

    
    public IDataModel getProjectCreationDataModel(){
        return DataModelFactory.createDataModel(new FlexibleJavaProjectCreationDataModelProvider());
    }
    
    public void createAppClientModule(String moduleName) throws Exception {
        IDataModel dataModel = getFlexibleConnectorModuleCreation();
        dataModel.setProperty(IFacetProjectCreationDataModelProperties.FACET_PROJECT_NAME, DEFAULT_PROJECT_NAME);
        runAndVerify(dataModel);
    }
    public IDataModel getFlexibleConnectorModuleCreation(){
		return DataModelFactory.createDataModel(new ConnectorFacetProjectCreationDataModelProvider());
    }

}
