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

import java.util.Collection;

import org.eclipse.core.resources.IProject;
import org.eclipse.jst.j2ee.dependency.tests.util.ProjectUtil;
import org.eclipse.jst.j2ee.internal.J2EEVersionConstants;
import org.eclipse.jst.j2ee.model.IModelProviderEvent;
import org.eclipse.jst.javaee.core.Description;
import org.eclipse.jst.javaee.core.JavaeeFactory;
import org.eclipse.jst.javaee.ejb.EJBJar;
import org.eclipse.jst.jee.model.internal.Ejb3ModelProvider;
import org.eclipse.jst.jee.model.tests.SynchronousModelChangedListener;
import org.eclipse.wst.common.project.facet.core.IFacetedProject;
import org.eclipse.wst.common.project.facet.core.ProjectFacetsManager;

import junit.framework.TestCase;
import junit.framework.TestSuite;

/**
 * @author  Kiril Mitov k.mitov@sap.com
 *
 */
public class Ejb3ModelProviderTest extends TestCase {

	private static final String EAR_PROJECT_NAME = "earProject";
	private static final String EJB_PROJECT_NAME = "ejbProject";

	public static TestSuite suite() throws Exception {
		TestSuite suite = new TestSuite(Ejb3ModelProviderTest.class);
		return suite;
	}

	// @BeforeClass
	public static void setUpProject() throws Exception {
	}

	/**
	 * A notification should be send after a modification on the model.
	 * @throws Exception 
	 */
	public void testModifyOnlyModelWithOperation() throws Exception {
		ProjectUtil.createEARProject(EAR_PROJECT_NAME);
		IProject project = ProjectUtil.createEJBProject(EJB_PROJECT_NAME, EAR_PROJECT_NAME,
				J2EEVersionConstants.EJB_3_0_ID, true);
		IFacetedProject facetedProject = ProjectFacetsManager.create(project);
		SynchronousModelChangedListener listener = new SynchronousModelChangedListener(1);
		final Ejb3ModelProvider modelProvider = new Ejb3ModelProvider(facetedProject.getProject());
		assertNotNull(modelProvider.getModelObject());
		modelProvider.addListener(listener);
		modelProvider.modify(new Runnable() {
			public void run() {
				EJBJar modelObject = (EJBJar) modelProvider.getModelObject();
				changeModelObject(modelObject);
			}

			private void changeModelObject(EJBJar modelObject) {
				Description description = JavaeeFactory.eINSTANCE.createDescription();
				modelObject.getDescriptions().add(description);
			}
		}, null);
		assertTrue(listener.waitForEvents());
		Collection<IModelProviderEvent> events = listener.getReceivedEvents();
		assertEquals(1, events.size());
		modelProvider.removeListener(listener);
	}

	@Override
	protected void setUp() throws Exception {
		super.setUp();
		setUpProject();
	}
}
