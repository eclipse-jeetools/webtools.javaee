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
package org.eclipse.jst.j2ee.project.creation.operations;

import junit.framework.Test;

import org.eclipse.jst.j2ee.application.internal.operations.FlexibleJavaProjectCreationDataModelProvider;
import org.eclipse.jst.j2ee.project.datamodel.properties.IFlexibleJavaProjectCreationDataModelProperties;
import org.eclipse.wst.common.componentcore.datamodel.FlexibleProjectCreationDataModelProvider;
import org.eclipse.wst.common.componentcore.datamodel.properties.IFlexibleProjectCreationDataModelProperties;
import org.eclipse.wst.common.frameworks.datamodel.DataModelFactory;
import org.eclipse.wst.common.frameworks.datamodel.IDataModel;
import org.eclipse.wst.common.tests.OperationTestCase;
import org.eclipse.wst.common.tests.SimpleTestSuite;

public class J2EEProjectCreationOperationTest extends OperationTestCase {
    public static Test suite() {
        return new SimpleTestSuite(J2EEProjectCreationOperationTest.class);
    }
    public void testDefaults() throws Exception {
        createSimpleProject("SimpleJavaWTPProjectCreation2");
        createSimpleJavaProject("SimpleJavaProjects");
    }
    /**
     * @param string
     */
    private void createSimpleJavaProject(String projectName) throws Exception{
    	IDataModel dataModel = DataModelFactory.createDataModel(new FlexibleJavaProjectCreationDataModelProvider());
        dataModel.setProperty(IFlexibleJavaProjectCreationDataModelProperties.PROJECT_NAME, projectName);
        runAndVerify(dataModel);
    }
    public static void createSimpleProject(String projectName) throws Exception {
        IDataModel dataModel = DataModelFactory.createDataModel(new FlexibleProjectCreationDataModelProvider());
        dataModel.setProperty(IFlexibleProjectCreationDataModelProperties.PROJECT_NAME, projectName);
        runAndVerify(dataModel);
    }
}
