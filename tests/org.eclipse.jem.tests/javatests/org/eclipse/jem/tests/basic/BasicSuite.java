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
/*
 *  $RCSfile: BasicSuite.java,v $
 *  $Revision: 1.2 $  $Date: 2006/02/21 17:16:36 $ 
 */
package org.eclipse.jem.tests.basic;

import java.net.URL;

import junit.extensions.TestSetup;
import junit.framework.Test;
import junit.framework.TestSuite;

import org.eclipse.core.resources.*;
import org.eclipse.core.runtime.*;

import org.eclipse.jem.tests.JavaProjectUtil;
import org.eclipse.jem.tests.JavaTestsPlugin;

/**
 * This is basic JEM testing not covered by specific components tests.
 * 
 * @author richkulp
 *
 */
public class BasicSuite extends TestSetup {

	// Test cases to be include in the suite
	private static final Class testsList[] = { TestWorkbenchUtils112678.class};

	/**
	 * 
	 */
	public BasicSuite() {
		this("Test Basic JEM Suite");
	}

	/**
	 * @param name
	 */
	public BasicSuite(String name) {
		super(new TestSuite(name) {
			{
				for (int i = 0; i < testsList.length; i++) {
					addTestSuite(testsList[i]);
				}

			}
		});
	}

	public static Test suite() {
		return new BasicSuite();
	}

	public static final String TEST_BASIC_PROJECT = "Test Basic";	//$NON-NLS-1$
	
	private boolean oldAutoBuildingState; // autoBuilding state before we started.	
	protected void setUp() throws Exception {
		System.out.println("-- Initializing the Basic test data --"); //$NON-NLS-1$
		oldAutoBuildingState = JavaProjectUtil.setAutoBuild(true);
		URL installURL = JavaTestsPlugin.getPlugin().getBundle().getEntry("/");
		String[] zipPaths = new String[1];
		zipPaths[0] = FileLocator.toFileURL(new URL(installURL, "testdata/testbasic.zip")).getFile();
		IProject[] projects =
			JavaProjectUtil.importProjects(
				new String[] { TEST_BASIC_PROJECT },
				zipPaths);
		assertNotNull(projects[0]);
		JavaProjectUtil.waitForAutoBuild();
		System.out.println("-- Data initialized --"); //$NON-NLS-1$

	}

	protected void tearDown() throws Exception {
		ResourcesPlugin.getWorkspace().run(new IWorkspaceRunnable() {
			public void run(IProgressMonitor monitor) throws CoreException {
				JavaProjectUtil.deleteProject(JavaProjectUtil.getProject(TEST_BASIC_PROJECT));
			}
		}, ResourcesPlugin.getWorkspace().getRoot(), 0, null);

		JavaProjectUtil.setAutoBuild(oldAutoBuildingState);
	}

}
