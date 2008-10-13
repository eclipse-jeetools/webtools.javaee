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

import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.resources.IFolder;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.ResourcesPlugin;
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
		return suite;
	}

	// @Before
	@Override
	protected void setUp() throws Exception {
		setUpProject();
		fixture = ModelProviderManager.getModelProvider(facetedProject.getProject());
	}

	protected void tearDown() throws Exception {
		deleteProjectAndWait(facetedProject.getProject().getName());
	}

	private void deleteProjectAndWait(String projectName) throws InterruptedException {
		SynchronousModelChangedListener listener = new SynchronousModelChangedListener(1);
		fixture.addListener(listener);
		AbstractTest.deleteProject(projectName);
		assertTrue(listener.waitForEvents());
		fixture.removeListener(listener);
	}

	// @BeforeClass
	public void setUpProject() throws Exception {
		IProject project = ResourcesPlugin.getWorkspace().getRoot().getProject(
				Web25MergedModelProviderTest.class.getSimpleName() + this.getName());
		facetedProject = AbstractTest.createWebProject(project.getName());
		createProjectContent();
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

		executeAndWait(operation);

		WebApp app = (WebApp) fixture.getModelObject();
		assertEquals(new Integer(1), new Integer(app.getServlets().size()));
		assertNotNull(TestUtils.findServletByName(app, "testAddServletWithOperation"));
	}

	public void testAddServletFromJSP() throws Exception {
		AbstractTest.saveFile(facetedProject.getProject().getFile("WebContent/index.jsp"), "");
		IDataModel dataModel = DataModelFactory.createDataModel(new NewServletClassDataModelProvider());
		dataModel.setStringProperty(INewServletClassDataModelProperties.CLASS_NAME, "index.jsp");
		dataModel.setStringProperty(INewServletClassDataModelProperties.DISPLAY_NAME, "index");
		dataModel.setStringProperty(INewServletClassDataModelProperties.PROJECT_NAME, facetedProject.getProject()
				.getName());
		dataModel.setBooleanProperty(INewServletClassDataModelProperties.USE_EXISTING_CLASS, Boolean.TRUE);
		dataModel.setBooleanProperty(INewServletClassDataModelProperties.IS_SERVLET_TYPE, Boolean.FALSE);
		AddServletOperation operation = new AddServletOperation(dataModel);

		executeAndWait(operation);

		WebApp app = (WebApp) fixture.getModelObject();
		assertEquals(new Integer(1), new Integer(app.getServlets().size()));
		assertNotNull(TestUtils.findServletByName(app, "index"));
	}

	private void executeAndWait(AddServletOperation operation) throws InterruptedException, ExecutionException {
		SynchronousModelChangedListener listener = new SynchronousModelChangedListener(1);
		fixture.addListener(listener);
		operation.execute(null, null);
		listener.waitForEvents();
		fixture.removeListener(listener);
	}

}
