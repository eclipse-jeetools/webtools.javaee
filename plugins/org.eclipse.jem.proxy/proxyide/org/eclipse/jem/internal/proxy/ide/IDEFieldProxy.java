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
 *  $RCSfile: IDEFieldProxy.java,v $
 *  $Revision: 1.8 $  $Date: 2005/05/18 23:11:26 $ 
 */

import java.lang.reflect.Field;

import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;

import org.eclipse.jem.internal.proxy.core.*;

/**
 * Implementation of IFieldProxy where the bean vm is the same as the IDE
 * Therefore we hold the instance of a java.lang.reflect.Field in a field
 * directly and have a package protected constructor to set this
 * Creation date: (1/17/00 12:32:24 PM)
 * @author: Joe Winchester
 */
public class IDEFieldProxy extends IDEAccessibleObjectProxy implements IFieldProxy {
	protected IBeanTypeProxy fFieldType;
	/**
	 * Package protected constructor that takes the field directly
	 * This is package protected because only classes in the IDE package can construct
	 * this.  Everyone else must go via the typeProxy
	 */
	IDEFieldProxy(IDEProxyFactoryRegistry aRegistry, Field aField) {
		super(aRegistry, aField);
	}
	/**
	 * Get the value of the field and return it wrapped in a bean proxy
	 */
	public IBeanProxy get(IBeanProxy aSubject) {

		Object result = null;
		// Get the field value and catch any errors	
		try {
			result = ((Field) getBean()).get(aSubject != null ? ((IIDEBeanProxy) aSubject).getBean() : null);
		} catch (Exception e) {
			ProxyPlugin.getPlugin().getLogger().log(
				new Status(IStatus.WARNING, ProxyPlugin.getPlugin().getBundle().getSymbolicName(), 0, "", e)); //$NON-NLS-1$
		}

		// If we have a non null result wrap it in an IBeanProxy and return it
		if (result != null) {
			return fProxyFactoryRegistry.getBeanProxy(((Field) getBean()).getType(), result);
		} else {
			return null;
		}

	}
	/**
	 * Get the beans from both of the proxies and invoke the field set method
	 * Cast to IDEBeanProxy and use package protected method
	 */
	public void set(IBeanProxy aSubject, IBeanProxy argument) {

		// Set the field value and catch any errors	
		try {
			((Field) getBean()).set(
				aSubject != null ? ((IIDEBeanProxy) aSubject).getBean() : null,
				argument != null ? ((IIDEBeanProxy) argument).getBean() : null);
		} catch (Exception e) {
			ProxyPlugin.getPlugin().getLogger().log(
				new Status(IStatus.WARNING, ProxyPlugin.getPlugin().getBundle().getSymbolicName(), 0, "", e)); //$NON-NLS-1$
		}
	}
	/**
	 * The type proxy is constant proxy out of the method factory.
	 */
	public IBeanTypeProxy getTypeProxy() {
		return ((IDEMethodProxyFactory) fProxyFactoryRegistry.getMethodProxyFactory()).fieldType;
	}
	/**
	 * Return the type of the field.
	 */
	public IBeanTypeProxy getFieldType() {
		if (fFieldType == null) {
			fFieldType =
				((IDEStandardBeanTypeProxyFactory) fProxyFactoryRegistry.getBeanTypeProxyFactory()).getBeanTypeProxy(
					((Field) getBean()).getType());
		}
		return fFieldType;
	}
}
