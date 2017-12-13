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

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.jst.j2ee.dependency.tests.util.ProjectUtil;
import org.eclipse.jst.j2ee.internal.J2EEVersionConstants;
import org.eclipse.jst.j2ee.model.IModelProvider;
import org.eclipse.jst.jee.model.internal.Ejb3MergedModelProviderFactory;

/**
 * @author Kiril Mitov k.mitov@sap.com
 * 
 */
public class EJB3MergedModelProviderFactoryTest extends TestCase {

	private Ejb3MergedModelProviderFactory fixture;

	public static Test suite() {
		TestSuite suite = new TestSuite(EJB3MergedModelProviderFactoryTest.class);
		return suite;
	}

	protected void setUp() throws Exception {
		super.setUp();
		fixture = new Ejb3MergedModelProviderFactory();
	}

	protected void tearDown() throws Exception {
		super.tearDown();
	}

	public void testCreateForProject() {
		IProject project = ResourcesPlugin.getWorkspace().getRoot().getProject(
				this.getClass().getSimpleName() + this.getName());
		IModelProvider provider = fixture.create(project);
		assertNotNull(provider);
	}

	public void testSameProjectDisposedProvider() {
		IProject project = ResourcesPlugin.getWorkspace().getRoot().getProject(
				this.getClass().getSimpleName() + this.getName());
		IModelProvider provider = fixture.create(project);
		assertFalse(provider.equals(fixture.create(project)));
		assertNotSame(provider, fixture.create(project));
	}

	/**
	 * Same provider must be returned for the same project.
	 * 
	 * @throws Exception
	 */
	public void testSameProject() throws Exception {
		IProject project = ProjectUtil.createEJBProject(this.getClass().getSimpleName() + this.getName(), null,
				J2EEVersionConstants.EJB_3_0_ID, true);
		IModelProvider provider = fixture.create(project);
		provider.getModelObject();
		IModelProvider secondProvider = fixture.create(project);
		assertEquals(provider.getModelObject(), secondProvider.getModelObject());
		assertEquals(provider, secondProvider);
	}
}
