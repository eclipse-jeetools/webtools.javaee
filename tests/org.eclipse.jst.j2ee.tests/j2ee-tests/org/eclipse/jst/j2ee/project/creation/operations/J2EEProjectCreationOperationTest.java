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

import org.eclipse.jst.j2ee.application.operations.J2EEJavaProjectCreationDataModel;
import org.eclipse.jst.j2ee.application.operations.J2EEProjectCreationDataModel;
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
    	J2EEJavaProjectCreationDataModel dataModel = new J2EEJavaProjectCreationDataModel();
        dataModel.setProperty(J2EEJavaProjectCreationDataModel.PROJECT_NAME, projectName);
        runAndVerify(dataModel);
    }
    public static void createSimpleProject(String projectName) throws Exception {
    	J2EEProjectCreationDataModel dataModel = new J2EEProjectCreationDataModel();
        dataModel.setProperty(J2EEProjectCreationDataModel.PROJECT_NAME, projectName);
        runAndVerify(dataModel);
    }
}
