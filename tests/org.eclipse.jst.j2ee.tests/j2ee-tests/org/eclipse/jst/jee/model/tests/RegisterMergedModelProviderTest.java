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

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import org.eclipse.core.resources.IProject;
import org.eclipse.jst.j2ee.model.IModelProvider;
import org.eclipse.jst.j2ee.model.ModelProviderManager;
import org.eclipse.jst.javaee.ejb.EJBJar;
import org.eclipse.jst.jee.model.internal.EJB3MergedModelProvider;
import org.eclipse.jst.jee.model.internal.Ejb3MergedModelProviderFactory;
import org.eclipse.wst.common.project.facet.core.IProjectFacetVersion;
import org.eclipse.wst.common.project.facet.core.ProjectFacetsManager;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * @author  Kiril Mitov k.mitov@sap.com
 *
 */
public class RegisterMergedModelProviderTest {

	private IProject fixture;

	@Before
	public void setUp() throws Exception {
		fixture = AbstractTest.createProject(RegisterMergedModelProviderTest.class.getName()).getProject();
	}

	@After
	public void tearDown() throws Exception {
		AbstractTest.deleteProject(RegisterMergedModelProviderTest.class.getName());
	}

	@Test
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

	private IProject getFixture() {
		return fixture;
	}
}
