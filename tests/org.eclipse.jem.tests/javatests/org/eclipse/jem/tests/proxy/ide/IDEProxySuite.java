/*
 * Created on Aug 22, 2003
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package org.eclipse.jem.tests.proxy.ide;
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

import junit.extensions.TestSetup;
import junit.framework.Test;
import junit.framework.TestSuite;

import org.eclipse.core.runtime.CoreException;

import org.eclipse.jem.internal.proxy.core.IConfigurationContributor;
import org.eclipse.jem.internal.proxy.core.ProxyFactoryRegistry;
import org.eclipse.jem.internal.proxy.ide.IDERegistration;
import org.eclipse.jem.tests.proxy.AbstractTestProxy;
import org.eclipse.jem.tests.proxy.ProxySuite;

/**
 * @author richkulp
 *
 * This is the true test suite for Remote Proxy Testing. The RemoteProxyTest will use this.
 */
public class IDEProxySuite extends TestSetup {

	// Test cases to be include in the suite
	private static Class testsList[] = { ProxySuite.class, };

	private AbstractTestProxy.RegistryHandler registryHandler = new AbstractTestProxy.RegistryHandler() {
		private ProxyFactoryRegistry registry;

		public boolean isValid() {
			return true;
		}
		public ProxyFactoryRegistry getRegistry() throws CoreException {
			if (registry == null) {
				registry = IDERegistration.startAnImplementation(new IConfigurationContributor[] { ProxySuite.getProxySuiteContributor()}, true, null, "JUnit Tests for IDE Proxy", "org.eclipse.jem.tests", null); //$NON-NLS-1$ //$NON-NLS-2$
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

	public IDEProxySuite() {
		this("Test IDE Proxy Suite");
	}

	public IDEProxySuite(String name) {
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

		AbstractTestProxy.initRegistryHandler((TestSuite) getTest(), registryHandler);
	}

	public static Test suite() {
		return new IDEProxySuite();
	}

	protected void tearDown() throws Exception {
		registryHandler.destroyRegistry();
	}

}
