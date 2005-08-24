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
package org.eclipse.jem.tests.proxy.remote;
/*
 *  $RCSfile: RemoteProxySuite.java,v $
 *  $Revision: 1.8 $  $Date: 2005/08/24 20:58:55 $ 
 */
import java.net.URL;

import junit.extensions.TestSetup;
import junit.framework.Test;
import junit.framework.TestSuite;

import org.eclipse.core.resources.IProject;
import org.eclipse.core.runtime.*;

import org.eclipse.jem.internal.proxy.core.*;
import org.eclipse.jem.tests.JavaProjectUtil;
import org.eclipse.jem.tests.JavaTestsPlugin;
import org.eclipse.jem.tests.proxy.AbstractTestProxy;
import org.eclipse.jem.tests.proxy.ProxySuite;

/**
 * @author richkulp
 *
 * This is the true test suite for Remote Proxy Testing. The RemoteProxyTest will use this.
 */
public class RemoteProxySuite extends TestSetup {

	// Test cases to be include in the suite
	private static final Class testsList[] = { ProxySuite.class, TestProjectAccess.class, };

	private IProject project; // The project to start the proxy factory on.		                               
	private AbstractTestProxy.RegistryHandler registryHandler = new AbstractTestProxy.RegistryHandler() {
		private ProxyFactoryRegistry registry;

		public boolean isValid() {
			return project != null;
		}
		public ProxyFactoryRegistry getRegistry() throws CoreException {
			if (registry == null) {
				registry =
					ProxyLaunchSupport.startImplementation(
						project,
						"JUnit Remote Proxy Test",
						new IConfigurationContributor[] { ProxySuite.getProxySuiteContributor()},
						new NullProgressMonitor());
			}
			return registry;
		}

		public void destroyRegistry() {
			if (registry != null) {
				registry.terminateRegistry();
				registry = null;
			}
		}
	};

	public RemoteProxySuite() {
		this("Test Remote Proxy Suite");
	}

	public RemoteProxySuite(String name) {
		super(new TestSuite(name) {
			{
				for (int i = 0; i < testsList.length; i++) {
					// We may get some tests and suites.
					if (TestSuite.class.isAssignableFrom(testsList[i]) || TestSetup.class.isAssignableFrom(testsList[i])) {
						try {
							Test ts = (Test) testsList[i].newInstance();
							addTest(ts);
						} catch (Exception e) {
							e.printStackTrace();
						}
					} else
						addTestSuite(testsList[i]);
				}
			}
		});

		AbstractTestProxy.initRegistryHandler((TestSuite) this.getTest(), registryHandler);
	}

	public static Test suite() {
		return new RemoteProxySuite();
	}

	private final static String TEST_PROJECT_NAME = "Test Remote Proxy";
	private boolean oldAutoBuildingState; // autoBuilding state before we started.
	protected void setUp() throws Exception {
		System.out.println("-- Initializing the Proxy Remote test data --"); //$NON-NLS-1$
		oldAutoBuildingState = JavaProjectUtil.setAutoBuild(true);
		String zipPath =
			Platform
				.asLocalURL(new URL(JavaTestsPlugin.getPlugin().getBundle().getEntry("/"), "testdata/testremoteproject.zip"))
				.getFile();
		IProject[] projects = JavaProjectUtil.importProjects(new String[] { TEST_PROJECT_NAME }, new String[] { zipPath });
		assertNotNull(projects[0]);
		JavaProjectUtil.waitForAutoBuild();		
		project = projects[0];
		System.out.println("-- Data initialized --"); //$NON-NLS-1$

	}

	protected void tearDown() throws Exception {
		registryHandler.destroyRegistry();
		if (project != null) {
			project.delete(true, false, null); // Get rid of the project and the files themselves.
			project = null;
		}
		JavaProjectUtil.setAutoBuild(oldAutoBuildingState);
	}

}
