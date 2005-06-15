/*******************************************************************************
 * Copyright (c) 2005 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials 
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *******************************************************************************/
/*
 *  $RCSfile: BaseProxyFactoryRegistry.java,v $
 *  $Revision: 1.1 $  $Date: 2005/06/15 20:19:11 $ 
 */
package org.eclipse.jem.internal.proxy.core;
 
/**
 * This is the Base Registry that all implementations should subclass. 
 * <p>
 * It is not meant to be used by customers. It provides public functions only for access of individual implementations.
 * Customers should only access {@link org.eclipse.jem.internal.proxy.core.ProxyFactoryRegistry}.
 * 
 * @since 1.1.0
 */
public abstract class BaseProxyFactoryRegistry extends ProxyFactoryRegistry {

	private final String registryTypeID;

	/**
	 * Construct with registry type ID. The type id should be provided by
	 * each implementation. This will be used to provide specific type
	 * registrations through contributions.
	 *  
	 * @param registryTypeID
	 * 
	 * @since 1.1.0
	 */
	protected BaseProxyFactoryRegistry(String registryTypeID) {
		super();
		this.registryTypeID = registryTypeID;
	}
	
	/**
	 * Return the reqistry type id.
	 * @return
	 * 
	 * @since 1.1.0
	 */
	public String getRegistryTypeID() {
		return registryTypeID;
	}

	/**
	 Register the current registered bean proxy factory to be used for creating IBeanProxy instances
	 */
	public void registerBeanProxyFactory(IStandardBeanProxyFactory aStandardFactory) {
		fCurrentStandardBeanProxyFactory = aStandardFactory;
	}

	/**
	 Register a factory to be used for creating IBeanProxy instances
	 */
	public void registerBeanProxyFactory(String packageName, IBeanProxyFactory aFactory) {
		fRegisteredExtensionBeanProxyFactories.put(packageName, aFactory);
	}

	/**
	 Register the current registered bean type proxy factory to be used for creating IBeanTypeProxy instances
	 */
	public void registerBeanTypeProxyFactory(IStandardBeanTypeProxyFactory aStandardFactory) {
		fCurrentStandardBeanTypeProxyFactory = aStandardFactory;
	}

	/**
	 Register a factory to be used for creating IBeanTypeProxy instances
	 */
	public void registerBeanTypeProxyFactory(String packageName, IBeanTypeProxyFactory aFactory) {
		fRegisteredExtensionBeanTypeProxyFactories.put(packageName, aFactory);
	}

	/**
	 * Set the Method Proxy Factory for this VM.
	 * Creation date: (3/13/00 10:58:19 AM)
	 */
	public void registerMethodProxyFactory(IMethodProxyFactory newMethodProxyFactory) {
		fMethodProxyFactory = newMethodProxyFactory;
	}

}
