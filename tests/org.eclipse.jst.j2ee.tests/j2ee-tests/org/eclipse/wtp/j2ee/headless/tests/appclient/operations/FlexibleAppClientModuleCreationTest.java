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

import org.eclipse.jst.j2ee.application.internal.operations.FlexibleJavaProjectCreationDataModel;
import org.eclipse.jst.j2ee.applicationclient.internal.creation.AppClientComponentCreationDataModel;
import org.eclipse.wst.common.frameworks.internal.operations.WTPOperation;
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
       createProject(DEFAULT_PROJECT_NAME);
        createAppClientModule(DEFAULT_MODULE_NAME);
    }

    public void createProject(String projectName) throws Exception {
        FlexibleJavaProjectCreationDataModel dataModel = getProjectCreationDataModel();
        dataModel.setProperty(FlexibleJavaProjectCreationDataModel.PROJECT_NAME, projectName);
        WTPOperation op = dataModel.getDefaultOperation();
        op.run(null);
    }
    public void createAppClientModule(String moduleName) throws Exception {
        AppClientComponentCreationDataModel dataModel = getFlexibleAppClientModuleCreation();
        dataModel.setProperty(AppClientComponentCreationDataModel.PROJECT_NAME, DEFAULT_PROJECT_NAME);
        dataModel.setProperty(AppClientComponentCreationDataModel.COMPONENT_NAME, DEFAULT_MODULE_NAME);
        runAndVerify(dataModel);
    }
    
    public FlexibleJavaProjectCreationDataModel getProjectCreationDataModel(){
		return new FlexibleJavaProjectCreationDataModel();
    }
    public AppClientComponentCreationDataModel getFlexibleAppClientModuleCreation(){
		return new AppClientComponentCreationDataModel();
    }
}
