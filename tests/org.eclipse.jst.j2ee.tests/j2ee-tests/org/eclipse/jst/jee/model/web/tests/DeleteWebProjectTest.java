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

import org.eclipse.core.resources.IContainer;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IFolder;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.core.runtime.Path;
import org.eclipse.jdt.core.IJavaProject;
import org.eclipse.jdt.core.IPackageFragment;
import org.eclipse.jdt.core.IPackageFragmentRoot;
import org.eclipse.jdt.core.JavaCore;
import org.eclipse.jst.j2ee.dependency.tests.util.ProjectUtil;
import org.eclipse.jst.j2ee.internal.J2EEVersionConstants;
import org.eclipse.jst.j2ee.model.IModelProvider;
import org.eclipse.jst.j2ee.model.ModelProviderManager;
import org.eclipse.jst.javaee.web.WebApp;
import org.eclipse.jst.jee.model.tests.AbstractTest;
import org.eclipse.jst.jee.model.tests.SynchronousModelChangedListener;
import org.eclipse.wst.common.project.facet.core.IFacetedProject;
import org.eclipse.wst.common.project.facet.core.ProjectFacetsManager;

/**
 * @author Kiril Mitov k.mitov@sap.com
 * 
 */
public class DeleteWebProjectTest extends TestCase {

	public static junit.framework.Test suite() {
		TestSuite suite = new TestSuite(DeleteWebProjectTest.class);
		return suite;
	}

	private static void createProjectContent(IFacetedProject facetedProject) throws Exception {
		IJavaProject javaProject = JavaCore.create(facetedProject.getProject());
		IFolder srcFolder = javaProject.getProject().getFolder("src");
		if (!srcFolder.exists())
			srcFolder.create(true, true, new NullProgressMonitor());
		IFolder comFolder = javaProject.getProject().getFolder("src/com");
		comFolder.create(true, true, new NullProgressMonitor());
		IPackageFragmentRoot root = javaProject.getPackageFragmentRoot(comFolder);
		IPackageFragment fragment = root.createPackageFragment("sap", true, new NullProgressMonitor());
		createServlet(fragment);
		createServletWithSecurity(fragment);
	}

	private static void createServlet(IPackageFragment fragment) throws Exception {
		final String content = "package com.sap;" + "import javax.annotation.Resource;import javax.ejb.EJB;"
				+ "import javax.servlet.http.HttpServlet;" + "public class Servlet1 extends HttpServlet {"
				+ "private static final long serialVersionUID = 1L;"
				+ "@EJB private Comparable comp; @EJB public void setComparable(Comparable comp){};"
				+ "@Resource private Comparable comp2; @Resource public void setComparable2(Comparable comp){} }";
		IFile file = ((IContainer) fragment.getResource()).getFile(new Path("Servlet1.java"));
		AbstractTest.saveFile(file, content);
	}

	private static void createServletWithSecurity(IPackageFragment fragment) throws Exception {
		final String servletContent = "package com.sap;"
				+ "import javax.servlet.http.HttpServlet;"
				+ "@DeclareRoles(value = {\"role1\", \"role2\"})  public class ServletWithSecurity extends HttpServlet {}";
		IFile file = ((IContainer) fragment.getResource()).getFile(new Path("ServletWithSecurity.java"));
		AbstractTest.saveFile(file, servletContent);
	}

	/**
	 * The same instance of the model provider is preserved during deleting the
	 * project. After the project is deleted the instance of the IModelProvider
	 * must return <code>null</code> for model object
	 * 
	 * @throws Exception
	 */
	// @Test
	public void testDeleteProjectSameProvider() throws Exception {
		IProject project = ProjectUtil.createWebProject(DeleteWebProjectTest.class.getSimpleName()
				+ "testDeleteProjectSameProvider", null, J2EEVersionConstants.WEB_2_5_ID, true);
		IFacetedProject facetedProject = ProjectFacetsManager.create(project);
		createProjectContent(facetedProject);
		IModelProvider provider = ModelProviderManager.getModelProvider(facetedProject.getProject());
		WebApp firstModel = (WebApp) provider.getModelObject();
		assertNotNull(firstModel);
		assertEquals(firstModel, provider.getModelObject());

		SynchronousModelChangedListener listener = new SynchronousModelChangedListener(1);
		provider.addListener(listener);
		AbstractTest.deleteProject(facetedProject.getProject().getName());
		assertTrue(listener.waitForEvents());
		provider.removeListener(listener);

		try {
			provider.getModelObject();
			fail("IllegalStateException expected because the project is deleted and can not be accessed.");
		} catch (IllegalStateException e) {
		}
	}

	// @Test(expected = IllegalArgumentException.class)
	public void testProviderForNonExistingProject() {
		IProject nonExistingProject = ResourcesPlugin.getWorkspace().getRoot().getProject(
				"testProviderForNonExistingProject");
		assertFalse(nonExistingProject.exists());
		IModelProvider modelProvider = ModelProviderManager.getModelProvider(nonExistingProject);
		assertNull(modelProvider);
	}

	/**
	 * Get the model for a project. Close the project. Get the model from the
	 * same model provider. The model should be null because the project is
	 * closed.
	 * 
	 * @throws Exception
	 */
	// @Test
	public void testCloseProjectSameProvider() throws Exception {
		IFacetedProject facetedProject = AbstractTest.createWebProject(DeleteWebProjectTest.class.getSimpleName()
				+ "testClose");
		createProjectContent(facetedProject);
		IModelProvider provider = ModelProviderManager.getModelProvider(facetedProject.getProject());
		WebApp firstModel = (WebApp) provider.getModelObject();
		assertNotNull(firstModel);
		assertEquals(firstModel, provider.getModelObject());

		SynchronousModelChangedListener listener = new SynchronousModelChangedListener(1);
		provider.addListener(listener);
		AbstractTest.closeProject(facetedProject.getProject().getName());
		assertTrue(listener.waitForEvents());
		provider.removeListener(listener);
		try {
			provider.getModelObject();
			fail("IllegalStateException expected because the project is closed and can not be accessed.");
		} catch (IllegalStateException e) {
		}
	}

}
