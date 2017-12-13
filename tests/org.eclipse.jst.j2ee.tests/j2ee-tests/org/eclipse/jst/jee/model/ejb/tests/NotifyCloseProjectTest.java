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

import org.eclipse.jst.j2ee.model.IModelProvider;
import org.eclipse.jst.j2ee.model.IModelProviderEvent;
import org.eclipse.jst.j2ee.model.ModelProviderManager;
import org.eclipse.jst.jee.model.tests.AbstractTest;
import org.eclipse.jst.jee.model.tests.SynchronousModelChangedListener;
import org.eclipse.wst.common.project.facet.core.IFacetedProject;

/**
 * @author Kiril Mitov k.mitov@sap.com
 * 
 */
public class NotifyCloseProjectTest extends TestCase {

	public static Test suite() {
		TestSuite suite = new TestSuite(NotifyCloseProjectTest.class);
		return suite;
	}

	/**
	 * Get the model for a project. Close the project. Get the model from the
	 * same model provider. The model should be null because the project is
	 * closed.
	 * 
	 * @throws Exception
	 */
	// @Test
	public void testCloseEjbProject() throws Exception {
		IFacetedProject facetedProject = AbstractTest.createEjbProject(NotifyCloseProjectTest.class.getSimpleName()
				+ "testCloseEjbProject");
		IModelProvider provider = ModelProviderManager.getModelProvider(facetedProject.getProject());
		provider.getModelObject();

		SynchronousModelChangedListener listener = new SynchronousModelChangedListener(1);
		provider.addListener(listener);
		AbstractTest.closeProject(facetedProject.getProject().getName());
		assertTrue(listener.waitForEvents());
		provider.removeListener(listener);
		assertEquals(new Integer(1), new Integer(listener.getReceivedEvents().size()));
		IModelProviderEvent event = listener.getReceivedEvents().iterator().next();
		assertTrue(event.getEventCode() == IModelProviderEvent.UNLOADED_RESOURCE);
	}

	/**
	 * Get the model for a project. Close the project. Get the model from the
	 * same model provider. The model should be null because the project is
	 * closed.
	 * 
	 * @throws Exception
	 */
	// @Test
	public void testCloseWebProject() throws Exception {
		IFacetedProject facetedProject = AbstractTest.createWebProject(NotifyCloseProjectTest.class.getSimpleName()
				+ "testCloseWebProject");
		IModelProvider provider = ModelProviderManager.getModelProvider(facetedProject.getProject());
		provider.getModelObject();

		SynchronousModelChangedListener listener = new SynchronousModelChangedListener(1);
		provider.addListener(listener);
		AbstractTest.closeProject(facetedProject.getProject().getName());
		assertTrue(listener.waitForEvents());
		provider.removeListener(listener);
		assertEquals(new Integer(1), new Integer(listener.getReceivedEvents().size()));
		IModelProviderEvent event = listener.getReceivedEvents().iterator().next();
		assertTrue(event.getEventCode() == IModelProviderEvent.UNLOADED_RESOURCE);
	}

}
