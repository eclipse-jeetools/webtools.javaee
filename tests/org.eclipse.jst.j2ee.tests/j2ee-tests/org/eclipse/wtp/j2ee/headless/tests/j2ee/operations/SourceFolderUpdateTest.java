/*******************************************************************************
 * Copyright (c) 2008 SAP AG and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     SAP AG - initial API and implementation
 *******************************************************************************/
package org.eclipse.wtp.j2ee.headless.tests.j2ee.operations;

import static org.eclipse.jst.j2ee.internal.common.operations.INewJavaClassDataModelProperties.SOURCE_FOLDER;
import static org.eclipse.wst.common.componentcore.internal.operation.IArtifactEditOperationDataModelProperties.PROJECT_NAME;
import junit.framework.Test;

import org.eclipse.core.runtime.Path;
import org.eclipse.jst.j2ee.internal.common.operations.NewJavaClassDataModelProvider;
import org.eclipse.wst.common.frameworks.datamodel.DataModelFactory;
import org.eclipse.wst.common.frameworks.datamodel.IDataModel;
import org.eclipse.wst.common.tests.OperationTestCase;
import org.eclipse.wtp.j2ee.headless.tests.utility.operations.UtilityProjectCreationOperationTest;

public class SourceFolderUpdateTest extends OperationTestCase {
	
	public SourceFolderUpdateTest(String name) {
		super(name);
	}
	
	public static Test suite() {
		return new SourceFolderUpdateTest("testSourceFolderUpdate");
	}

	@Override
	protected void setUp() throws Exception {
		super.setUp();
		
		// create 2 utility projects
		runAndVerify(UtilityProjectCreationOperationTest.getUtilityDataModel("project1", null));
		runAndVerify(UtilityProjectCreationOperationTest.getUtilityDataModel("project2", null));
	}
	
	public void testSourceFolderUpdate() {
		// create the new Java class data model
		IDataModel model = DataModelFactory.createDataModel(new NewJavaClassDataModelProvider());
		
		// set the first project
		model.setStringProperty(PROJECT_NAME, "project1");
		
		// the data model provider should already set the source folder, but we
		// reset it in order to avoid further calls to the default value
		model.setStringProperty(SOURCE_FOLDER, "/project1/src");
		
		// set the second project
		model.setStringProperty(PROJECT_NAME, "project2");
		
		// check if the source folder has switched to the one of the second project
		assertEquals(new Path("/project2/src"), new Path(model.getStringProperty(SOURCE_FOLDER)));
	}

	@Override
	protected void tearDown() throws Exception {
		super.tearDown();
		deleteAllProjects();
	}

}
