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

import org.eclipse.jst.j2ee.application.operations.FlexibleJavaProjectCreationDataModel;
import org.eclipse.jst.j2ee.application.operations.FlexibleProjectCreationDataModel;
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
    	FlexibleJavaProjectCreationDataModel dataModel = new FlexibleJavaProjectCreationDataModel();
        dataModel.setProperty(FlexibleJavaProjectCreationDataModel.PROJECT_NAME, projectName);
        runAndVerify(dataModel);
    }
    public static void createSimpleProject(String projectName) throws Exception {
    	FlexibleProjectCreationDataModel dataModel = new FlexibleProjectCreationDataModel();
        dataModel.setProperty(FlexibleProjectCreationDataModel.PROJECT_NAME, projectName);
        runAndVerify(dataModel);
    }
}
