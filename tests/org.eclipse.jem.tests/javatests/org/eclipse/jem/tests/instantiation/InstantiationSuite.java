package org.eclipse.jem.tests.instantiation;
/*******************************************************************************
 * Copyright (c)  2003 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials 
 * are made available under the terms of the Common Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/cpl-v10.html
 * 
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *******************************************************************************/
/*
 *  $RCSfile: InstantiationSuite.java,v $
 *  $Revision: 1.4 $  $Date: 2004/03/24 15:07:48 $ 
 */
import java.net.URL;

import junit.extensions.TestSetup;
import junit.framework.Test;
import junit.framework.TestSuite;

import org.eclipse.core.resources.*;
import org.eclipse.core.runtime.*;

import org.eclipse.jem.internal.beaninfo.adapters.BeaninfoNature;
import org.eclipse.jem.internal.instantiation.base.JavaInstantiation;
import org.eclipse.jem.tests.JavaProjectUtil;
import org.eclipse.jem.tests.JavaTestsPlugin;
import org.eclipse.jem.tests.beaninfo.AbstractBeanInfoTestCase;

/**
 * @author richkulp
 *
 */
public class InstantiationSuite extends TestSetup {

	// Test cases to be include in the suite
	private static final Class testsList[] = { TestStandard.class, };

	/**
	 * 
	 */
	public InstantiationSuite() {
		this("Test Instantiation Suite");
	}

	/**
	 * @param name
	 */
	public InstantiationSuite(String name) {
		super(new TestSuite(name) {
			{
				for (int i = 0; i < testsList.length; i++) {
					addTestSuite(testsList[i]);
				}

			}
		});
	}

	public static Test suite() {
		return new InstantiationSuite();
	}

	private boolean oldAutoBuildingState; // autoBuilding state before we started.	
	protected void setUp() throws Exception {
		System.out.println("-- Initializing the Instantiation test data --"); //$NON-NLS-1$
		oldAutoBuildingState = JavaProjectUtil.setAutoBuild(true);
		URL installURL = JavaTestsPlugin.getPlugin().getDescriptor().getInstallURL();
		String[] zipPaths = new String[2];
		zipPaths[0] = Platform.asLocalURL(new URL(installURL, "testdata/testbeaninfo.zip")).getFile();
		zipPaths[1] = Platform.asLocalURL(new URL(installURL, "testdata/testbeaninfopreq.zip")).getFile();
		IProject[] projects =
			JavaProjectUtil.importProjects(
				new String[] { AbstractBeanInfoTestCase.TEST_BEANINFO_PROJECT, AbstractBeanInfoTestCase.TEST_BEANINFO_PREREQ_PROJECT },
				zipPaths);
		assertNotNull(projects[0]);
		assertNotNull(projects[1]);
		System.out.println("-- Data initialized --"); //$NON-NLS-1$

		BeaninfoNature nature = BeaninfoNature.getRuntime(projects[0]);
		assertNotNull(nature);
		JavaInstantiation.initialize(nature.getResourceSet());
	}

	protected void tearDown() throws Exception {
		ResourcesPlugin.getWorkspace().run(new IWorkspaceRunnable() {
			public void run(IProgressMonitor monitor) throws CoreException {
				JavaProjectUtil.deleteProject(JavaProjectUtil.getProject(AbstractBeanInfoTestCase.TEST_BEANINFO_PROJECT));
				JavaProjectUtil.deleteProject(JavaProjectUtil.getProject(AbstractBeanInfoTestCase.TEST_BEANINFO_PREREQ_PROJECT));
			}
		}, null);

		JavaProjectUtil.setAutoBuild(oldAutoBuildingState);
	}

}
