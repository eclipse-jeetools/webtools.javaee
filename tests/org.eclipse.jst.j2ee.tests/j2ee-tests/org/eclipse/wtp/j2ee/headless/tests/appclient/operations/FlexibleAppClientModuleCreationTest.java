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

import org.eclipse.jst.j2ee.application.operations.J2EEJavaProjectCreationDataModel;
import org.eclipse.jst.j2ee.application.operations.J2EEProjectCreationDataModel;
import org.eclipse.jst.j2ee.applicationclient.creation.FlexibleAppClientCreationDataModel;
import org.eclipse.wst.common.frameworks.operations.WTPOperation;
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
        J2EEJavaProjectCreationDataModel dataModel = getProjectCreationDataModel();
        dataModel.setProperty(J2EEProjectCreationDataModel.PROJECT_NAME, projectName);
        WTPOperation op = dataModel.getDefaultOperation();
        op.run(null);
    }
    public void createAppClientModule(String moduleName) throws Exception {
        FlexibleAppClientCreationDataModel dataModel = getFlexibleAppClientModuleCreation();
        dataModel.setProperty(FlexibleAppClientCreationDataModel.PROJECT_NAME, DEFAULT_PROJECT_NAME);
        dataModel.setProperty(FlexibleAppClientCreationDataModel.MODULE_NAME, DEFAULT_MODULE_NAME);
        runAndVerify(dataModel);
    }
    
    public J2EEJavaProjectCreationDataModel getProjectCreationDataModel(){
		return new J2EEJavaProjectCreationDataModel();
    }
    public FlexibleAppClientCreationDataModel getFlexibleAppClientModuleCreation(){
		return new FlexibleAppClientCreationDataModel();
    }
}
