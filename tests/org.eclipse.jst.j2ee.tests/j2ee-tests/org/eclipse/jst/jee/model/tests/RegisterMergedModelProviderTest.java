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
package org.eclipse.jst.jee.model.tests;

import junit.framework.TestCase;
import junit.framework.TestSuite;

import org.eclipse.core.resources.IProject;
import org.eclipse.jst.j2ee.model.IModelProvider;
import org.eclipse.jst.j2ee.model.IModelProviderFactory;
import org.eclipse.jst.j2ee.model.ModelProviderManager;
import org.eclipse.jst.javaee.ejb.EJBJar;
import org.eclipse.jst.jee.model.internal.EJB3MergedModelProvider;
import org.eclipse.jst.jee.model.internal.Ejb3MergedModelProviderFactory;
import org.eclipse.wst.common.componentcore.resources.IVirtualComponent;
import org.eclipse.wst.common.project.facet.core.IProjectFacetVersion;
import org.eclipse.wst.common.project.facet.core.ProjectFacetsManager;

/**
 * @author Kiril Mitov k.mitov@sap.com
 * 
 */
public class RegisterMergedModelProviderTest extends TestCase {

	private IProject fixture;

	public static junit.framework.Test suite() {
		TestSuite suite = new TestSuite(RegisterMergedModelProviderTest.class);
		return suite;
	}

	public void setUp() throws Exception {
		fixture = AbstractTest.createEjbProject(RegisterMergedModelProviderTest.class.getName()).getProject();
	}

	public void tearDown() throws Exception {
		AbstractTest.deleteProject(RegisterMergedModelProviderTest.class.getName());
	}

	public void testRegister() {
		IProjectFacetVersion version = ProjectFacetsManager.getProjectFacet("jst.ejb").getVersion("3.0");
		IModelProvider oldProvider = ModelProviderManager.getModelProvider(getFixture());
		assertNotNull(oldProvider);
		ModelProviderManager.registerProvider(new Ejb3MergedModelProviderFactory(), version, "3");
		IModelProvider provider = ModelProviderManager.getModelProvider(getFixture());
		assertNotNull(provider);
		assertTrue(EJB3MergedModelProvider.class.isInstance(provider));
		assertNotNull(provider.getModelObject());
		assertTrue(EJBJar.class.isInstance(provider.getModelObject()));
	}

	/**
	 * Call register method first without calling getModelProvider method before
	 * that. Test case for bug [214136]
	 */
	public void testCallRegisterMethodFirst() {
		IProjectFacetVersion version = ProjectFacetsManager.getProjectFacet("jst.ejb").getVersion("3.0");
		ModelProviderManager.registerProvider(new MyModelProviderFactory(), version, "3");
		IModelProvider provider = ModelProviderManager.getModelProvider(getFixture());
		assertNull(provider);
		
		// register the real model provider so that this test does not affect
		// the ather tests.
		ModelProviderManager.registerProvider(new Ejb3MergedModelProviderFactory(), version, "3");
	}

	private static class MyModelProviderFactory implements IModelProviderFactory {

		public IModelProvider create(IProject project) {
			return null;
		}

		public IModelProvider create(IVirtualComponent component) {
			return null;
		}
	}

	private IProject getFixture() {
		return fixture;
	}
}
