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
package org.eclipse.jem.tests.proxy;
/*
 *  $RCSfile: AbstractTestProxy.java,v $
 *  $Revision: 1.5 $  $Date: 2005/08/24 20:58:54 $ 
 */
import java.util.Enumeration;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.jem.internal.proxy.core.*;
import org.eclipse.jem.internal.proxy.core.IStandardBeanTypeProxyFactory;
import org.eclipse.jem.internal.proxy.core.ProxyFactoryRegistry;

import junit.framework.*;
import junit.framework.TestCase;
import junit.framework.TestSuite;

/**
 * @author richkulp
 *
 * Standard type of Proxy Tests.
 */
public abstract class AbstractTestProxy extends TestCase {
	
	/**
	 * @author richkulp
	 *
	 * Interface for registry handler. Actual implementations will place their
	 * handler in the field REGISTRY_HANDLER to that the proxy tests can access it. 
	 */
	public interface RegistryHandler {
		
		/**
		 * @return is the handler valid and setup correctly.
		 */
		public boolean isValid();
		
		/**
		 * Return the current registry, creating it if necessary.
		 * @return The registry. 
		 */
		public ProxyFactoryRegistry getRegistry() throws CoreException;
		
		/**
		 * Destroy the current registry.
		 */
		public void destroyRegistry();
	}

	/**
	 * Initialize the registry handler for all AbstractTestProxy tests in the 
	 * given suite.
	 * @param suite
	 */
	public static void initRegistryHandler(TestSuite suite, RegistryHandler registryHandler) {
		Enumeration tests = suite.tests();
		while (tests.hasMoreElements()) {
			Test test = (Test) tests.nextElement();
			if (test instanceof AbstractTestProxy)
				((AbstractTestProxy) test).setRegistryHandler(registryHandler);
			else if (test instanceof TestSuite)
				initRegistryHandler((TestSuite) test, registryHandler);
		}
	}
	
	public AbstractTestProxy() {
		super();
	}

	public AbstractTestProxy(String name) {
		super(name);
	}
	
	private RegistryHandler registryHandler;
	
	protected ProxyFactoryRegistry registry;
	protected IStandardBeanTypeProxyFactory proxyTypeFactory;
	protected IStandardBeanProxyFactory proxyFactory;

	/* (non-Javadoc)
	 * @see junit.framework.TestCase#setUp()
	 */
	protected void setUp() throws Exception {
		super.setUp();
		assertNotNull(getRegistryHandler());
		assertTrue("Suite not setup correctly.", getRegistryHandler().isValid());
		createRegistry();
	}

	/*
	 * @throws CoreException
	 * 
	 * @since 1.1.0
	 */
	private void createRegistry() throws CoreException {
		registry = getRegistryHandler().getRegistry();
		assertNotNull(registry);
		proxyFactory = registry.getBeanProxyFactory();
		proxyTypeFactory = registry.getBeanTypeProxyFactory();
	}

	public void setRegistryHandler(RegistryHandler registryHandler) {
		this.registryHandler = registryHandler;
	}

	public RegistryHandler getRegistryHandler() {
		return registryHandler;
	}
	
	/**
	 * Recreate the registry. This is to make sure we have a clean one. Should be used with caution because expensive.
	 * @throws CoreException
	 * 
	 * 
	 * @since 1.1.0
	 */
	public void recreateRegistry() throws CoreException {
		getRegistryHandler().destroyRegistry();
		createRegistry();
	}

}
