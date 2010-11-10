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
package org.eclipse.jst.jee.model.ejb.tests;

import junit.framework.TestCase;
import junit.framework.TestSuite;

import org.eclipse.core.resources.IFolder;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.jdt.core.IJavaProject;
import org.eclipse.jdt.core.IPackageFragment;
import org.eclipse.jdt.core.IPackageFragmentRoot;
import org.eclipse.jdt.core.JavaCore;
import org.eclipse.jdt.core.JavaModelException;
import org.eclipse.jst.j2ee.dependency.tests.util.ProjectUtil;
import org.eclipse.jst.j2ee.internal.J2EEVersionConstants;
import org.eclipse.jst.j2ee.model.IModelProvider;
import org.eclipse.jst.j2ee.model.ModelProviderManager;
import org.eclipse.jst.javaee.ejb.EJBJar;
import org.eclipse.jst.jee.model.tests.AbstractTest;
import org.eclipse.jst.jee.model.tests.SynchronousModelChangedListener;
import org.eclipse.wst.common.project.facet.core.IFacetedProject;
import org.eclipse.wst.common.project.facet.core.ProjectFacetsManager;

/**
 * This is a test for deleting a project and updating the model providers.
 * 
 * @author Kiril Mitov k.mitov@sap.com
 * 
 */
public class DeleteProjectTest extends TestCase {

	public static TestSuite suite() {
		TestSuite suite = new TestSuite(DeleteProjectTest.class);
		return suite;
	}

	private static void createProjectContent(IFacetedProject facetedProject) throws CoreException {
		IJavaProject javaProject = JavaCore.create(facetedProject.getProject());
		IFolder comFolder = javaProject.getProject().getFolder("ejbModule/com");
		comFolder.create(true, true, new NullProgressMonitor());
		IPackageFragmentRoot root = javaProject.getPackageFragmentRoot(comFolder);
		IPackageFragment fragment = root.createPackageFragment("sap", true, new NullProgressMonitor());
		createSessionBean(fragment);
		createSessionBeanLocal(fragment);
		createBeanWithName(fragment);
	}

	private static void createBeanWithName(IPackageFragment fragment) throws JavaModelException {
		String content = String.format("package com.sap;" + "import javax.ejb.Stateless;"
				+ "@Stateless(name=\"%s\") public class BeanWithName implements SessionBeanLocal {}", "beanWithName");
		fragment.createCompilationUnit("BeanWithName.java", content, true, null);
	}

	private static void createSessionBeanLocal(IPackageFragment fragment) throws JavaModelException {
		fragment.createCompilationUnit("SessionBeanLocal.java", "package com.sap;" + "import javax.ejb.Local;"
				+ "@Local public interface SessionBeanLocal {}", true, null);
	}

	private static void createSessionBean(IPackageFragment fragment) throws JavaModelException {
		fragment.createCompilationUnit("SessionBean.java", "package com.sap;" + "import javax.ejb.Stateless;"
				+ "@Stateless public class SessionBean implements SessionBeanLocal {}", true, null);
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
		IProject project = ProjectUtil.createEJBProject(DeleteProjectTest.class.getSimpleName()
				+ "testDeleteProjectSameProvider", null, J2EEVersionConstants.EJB_3_0_ID, true);
		IFacetedProject facetedProject = ProjectFacetsManager.create(project);
		createProjectContent(facetedProject);
		IModelProvider provider = ModelProviderManager.getModelProvider(facetedProject.getProject());
		EJBJar firstModel = (EJBJar) provider.getModelObject();
		assertNotNull(firstModel);
		assertEquals(firstModel, provider.getModelObject());

		SynchronousModelChangedListener listener = new SynchronousModelChangedListener(1);
		provider.addListener(listener);
		AbstractTest.deleteProject(facetedProject.getProject().getName());
		assertTrue(listener.waitForEvents());
		provider.removeListener(listener);

		try {
			EJBJar secondModel = (EJBJar) provider.getModelObject();
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
		IProject project = ProjectUtil.createEJBProject(DeleteProjectTest.class.getSimpleName() + "testClose", null,
				J2EEVersionConstants.EJB_3_0_ID, true);
		IFacetedProject facetedProject = ProjectFacetsManager.create(project);
		createProjectContent(facetedProject);
		IModelProvider provider = ModelProviderManager.getModelProvider(facetedProject.getProject());
		EJBJar firstModel = (EJBJar) provider.getModelObject();
		assertNotNull(firstModel);
		assertEquals(firstModel, provider.getModelObject());

		SynchronousModelChangedListener listener = new SynchronousModelChangedListener(1);
		provider.addListener(listener);
		AbstractTest.closeProject(facetedProject.getProject().getName());
		assertTrue(listener.waitForEvents());
		provider.removeListener(listener);
		try {
			EJBJar secondModel = (EJBJar) provider.getModelObject();
			fail("IllegalStateExceptino expected because the project is deleted and can not be accessed.");
		} catch (IllegalStateException e) {
		}
	}

}
