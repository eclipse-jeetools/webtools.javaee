/***********************************************************************
 * Copyright (c) 2008 by SAP AG, Walldorf. 
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     SAP AG - initial API and implementation
 ***********************************************************************/
package org.eclipse.jst.jee.model.web.tests;

import junit.framework.TestCase;
import junit.framework.TestSuite;

import org.eclipse.core.resources.IFolder;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.jdt.core.IJavaProject;
import org.eclipse.jdt.core.IPackageFragment;
import org.eclipse.jdt.core.IPackageFragmentRoot;
import org.eclipse.jdt.core.JavaCore;
import org.eclipse.jst.j2ee.internal.web.operations.AddServletOperation;
import org.eclipse.jst.j2ee.internal.web.operations.INewServletClassDataModelProperties;
import org.eclipse.jst.j2ee.internal.web.operations.NewServletClassDataModelProvider;
import org.eclipse.jst.j2ee.model.IModelProvider;
import org.eclipse.jst.j2ee.model.ModelProviderManager;
import org.eclipse.jst.javaee.web.WebApp;
import org.eclipse.jst.jee.model.tests.AbstractTest;
import org.eclipse.jst.jee.model.tests.SynchronousModelChangedListener;
import org.eclipse.jst.jee.model.tests.TestUtils;
import org.eclipse.wst.common.frameworks.datamodel.DataModelFactory;
import org.eclipse.wst.common.frameworks.datamodel.IDataModel;
import org.eclipse.wst.common.project.facet.core.IFacetedProject;

/**
 * @author Kiril Mitov k.mitov@sap.com
 * 
 */
public class Web25MergedModelProviderTest extends TestCase {

	private static IFacetedProject facetedProject;

	private IModelProvider fixture;

	public static TestSuite suite() throws Exception {
		TestSuite suite = new TestSuite(Web25MergedModelProviderTest.class);
		setUpProject();
		return suite;
	}

	// @Before
	public void setUp() {
		fixture = ModelProviderManager.getModelProvider(facetedProject.getProject());
	}

	// @BeforeClass
	public static void setUpProject() throws Exception {
		facetedProject = AbstractTest.createWebProject(Web25MergedModelProviderTest.class.getSimpleName());
		createProjectContent();
	}

	// @AfterClass
	public static void tearDownAfterClass() throws InterruptedException {
		AbstractTest.deleteProject(Web25MergedModelProviderTest.class.getSimpleName());
	}

	private static void createProjectContent() throws Exception {
		IJavaProject javaProject = JavaCore.create(facetedProject.getProject());
		IFolder comFolder = javaProject.getProject().getFolder("src/com");
		comFolder.create(true, true, new NullProgressMonitor());
		IPackageFragmentRoot root = javaProject.getPackageFragmentRoot(comFolder);
		IPackageFragment fragment = root.createPackageFragment("sap", true, new NullProgressMonitor());
	}

	// @Test
	public void testAddServletWithOperation() throws Exception {
		IDataModel dataModel = DataModelFactory.createDataModel(new NewServletClassDataModelProvider());
		dataModel.setStringProperty(INewServletClassDataModelProperties.CLASS_NAME, "testAddServletWithOperation");
		dataModel.setStringProperty(INewServletClassDataModelProperties.PROJECT_NAME, facetedProject.getProject()
				.getName());
		dataModel.setBooleanProperty(INewServletClassDataModelProperties.IS_SERVLET_TYPE, Boolean.TRUE);
		dataModel.setStringProperty(INewServletClassDataModelProperties.JAVA_PACKAGE, "org.eclipse");
		AddServletOperation operation = new AddServletOperation(dataModel);

		SynchronousModelChangedListener listener = new SynchronousModelChangedListener(1);
		fixture.addListener(listener);
		operation.execute(null, null);
		listener.waitForEvents();
		fixture.removeListener(listener);

		WebApp app = (WebApp) fixture.getModelObject();
		assertEquals(new Integer(1), new Integer(app.getServlets().size()));
		assertNotNull(TestUtils.findServletByName(app, "testAddServletWithOperation"));
	}

}
