/*******************************************************************************
 * Copyright (c) 2001, 2004 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *******************************************************************************/
package org.eclipse.jem.internal.proxy.ide;
/*
 *  $RCSfile: IDEConstructorProxy.java,v $
 *  $Revision: 1.9 $  $Date: 2005/05/18 23:11:26 $ 
 */

import java.lang.reflect.Constructor;

import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;

import org.eclipse.jem.internal.proxy.core.*;

/**
 * This is an implementation of IConstructorProxy where the target VM is the same as
 * the IDE.  The java.lang.reflect.Constructor instance is held in a field
 */
class IDEConstructorProxy extends IDEAccessibleObjectProxy implements IConstructorProxy {

	/**
	 * package protected constructor where the instance of java.lang.reflect.Constructor
	 * can be passed in directly
	 */
	IDEConstructorProxy(IDEProxyFactoryRegistry aRegistry, Constructor aConstructor) {
		super(aRegistry, aConstructor);
	}
	/**
	 * The type proxy is constant proxy out of the method factory.
	 */
	public IBeanTypeProxy getTypeProxy() {
		return ((IDEMethodProxyFactory) fProxyFactoryRegistry.getMethodProxyFactory()).constructorType;
	}

	/**
	 * This is factored into a special method so that subclasses can override
	 * if they wish.
	 */
	protected IBeanProxy getNewIDEBeanProxy(Object aBean) {

		return IDEStandardBeanProxyFactory.createBeanProxy(fProxyFactoryRegistry, aBean); // Use local helper method to do this.
	}
	/**
	 * Invoke the constructor and return an IDEBeanProxy with the result
	 */
	public IBeanProxy newInstance() {

		// Invoke the constructor
		try {
			Object result = ((Constructor) getBean()).newInstance(null);
			return getNewIDEBeanProxy(result);
		} catch (Exception e) {
			ProxyPlugin.getPlugin().getLogger().log(
				new Status(IStatus.WARNING, ProxyPlugin.getPlugin().getBundle().getSymbolicName(), 0, "", e)); //$NON-NLS-1$
			return null;
		}

	}
	public IBeanProxy newInstanceCatchThrowableExceptions() {
		return newInstance();
	}
	public IBeanProxy newInstanceCatchThrowableExceptions(IBeanProxy[] args) {
		return newInstance(args);
	}
	/**
	 * Invoke the constructor and return an IDEBeanProxy with the result
	 */
	public IBeanProxy newInstance(IBeanProxy[] creationArguments) {

		// Get an array of bean objects from the bean proxy arguments
		Object result = null;
		Object[] creationObjects = new Object[creationArguments.length];
		for (int i = 0; i < creationArguments.length; i++) {
			// It is possible the arguments are null which is perfectly valid
			if (creationArguments[i] != null)
				creationObjects[i] = ((IIDEBeanProxy) creationArguments[i]).getBean();
		}

		// Invoke the constructor
		try {
			result = ((Constructor) getBean()).newInstance(creationObjects);
		} catch (Exception e) {
			ProxyPlugin.getPlugin().getLogger().log(
				new Status(IStatus.WARNING, ProxyPlugin.getPlugin().getBundle().getSymbolicName(), 0, "", e)); //$NON-NLS-1$
		}

		// If we have a non-null result create an IDEBeanProxy and return it
		if (result != null) {
			return getNewIDEBeanProxy(result);
		} else {
			return null;
		}

	}
	
	/*
	 *  (non-Javadoc)
	 * @see org.eclipse.jem.internal.proxy.core.IConstructorProxy#getParameterTypes()
	 */
	public IBeanTypeProxy[] getParameterTypes() {
		Class[] parmClasses = ((Constructor) getBean()).getParameterTypes();
		IBeanTypeProxy[] parmTypes = new IBeanTypeProxy[parmClasses.length];
		IDEStandardBeanTypeProxyFactory factory = (IDEStandardBeanTypeProxyFactory) fProxyFactoryRegistry.getBeanTypeProxyFactory();
		for (int i = 0; i < parmClasses.length; i++) {
			parmTypes[i] = factory.getBeanTypeProxy(parmClasses[i]);
		}
		return parmTypes;
	}

}
