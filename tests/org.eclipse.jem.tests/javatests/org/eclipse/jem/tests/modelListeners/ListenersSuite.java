/*******************************************************************************
 * Copyright (c) 2003, 2005 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *******************************************************************************/
package org.eclipse.jem.tests.modelListeners;
/*
 *  $RCSfile: ListenersSuite.java,v $
 *  $Revision: 1.6 $  $Date: 2006/02/21 17:16:36 $ 
 */
import java.net.URL;

import junit.extensions.TestSetup;
import junit.framework.Test;
import junit.framework.TestSuite;

import org.eclipse.core.resources.*;
import org.eclipse.core.runtime.*;

import org.eclipse.jem.tests.JavaProjectUtil;
import org.eclipse.jem.tests.JavaTestsPlugin;
import org.eclipse.jem.tests.beaninfo.AbstractBeanInfoTestCase;

/**
 * @author richkulp
 *
 */
public class ListenersSuite extends TestSetup {

	// Test cases to be include in the suite
	private static final Class testsList[] = { TestJEM.class, TestBeanInfo.class};

	/**
	 * 
	 */
	public ListenersSuite() {
		this("Test Listeners Suite");
	}

	/**
	 * @param name
	 */
	public ListenersSuite(String name) {
		super(new TestSuite(name) {
			{
				for (int i = 0; i < testsList.length; i++) {
					addTestSuite(testsList[i]);
				}

			}
		});
	}

	public static Test suite() {
		return new ListenersSuite();
	}

	private boolean oldAutoBuildingState; // autoBuilding state before we started.	
	protected void setUp() throws Exception {
		System.out.println("-- Initializing the Listeners test data --"); //$NON-NLS-1$
		oldAutoBuildingState = JavaProjectUtil.setAutoBuild(true);
		URL installURL = JavaTestsPlugin.getPlugin().getBundle().getEntry("/");
		String[] zipPaths = new String[2];
		zipPaths[0] = FileLocator.toFileURL(new URL(installURL, "testdata/testbeaninfo.zip")).getFile();
		zipPaths[1] = FileLocator.toFileURL(new URL(installURL, "testdata/testbeaninfopreq.zip")).getFile();
		IProject[] projects =
			JavaProjectUtil.importProjects(
				new String[] { AbstractBeanInfoTestCase.TEST_BEANINFO_PROJECT, AbstractBeanInfoTestCase.TEST_BEANINFO_PREREQ_PROJECT },
				zipPaths);
		assertNotNull(projects[0]);
		assertNotNull(projects[1]);
		JavaProjectUtil.waitForAutoBuild();
		System.out.println("-- Data initialized --"); //$NON-NLS-1$

	}

	protected void tearDown() throws Exception {
		ResourcesPlugin.getWorkspace().run(new IWorkspaceRunnable() {
			public void run(IProgressMonitor monitor) throws CoreException {
				JavaProjectUtil.deleteProject(JavaProjectUtil.getProject(AbstractBeanInfoTestCase.TEST_BEANINFO_PROJECT));
				JavaProjectUtil.deleteProject(JavaProjectUtil.getProject(AbstractBeanInfoTestCase.TEST_BEANINFO_PREREQ_PROJECT));
			}
		}, ResourcesPlugin.getWorkspace().getRoot(), 0, null);

		JavaProjectUtil.setAutoBuild(oldAutoBuildingState);
	}

}
