/*******************************************************************************
 * Copyright (c) 2001, 2004 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Common Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/cpl-v10.html
 * 
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *******************************************************************************/
package org.eclipse.jem.internal.proxy.ide;

/*
 *  $RCSfile: IDEInitErrorBeanTypeProxy.java,v $
 *  $Revision: 1.4 $  $Date: 2005/02/10 22:38:30 $ 
 */

import org.eclipse.jem.internal.proxy.core.*;

/**
 * @author richkulp
 * 
 * To change this generated comment edit the template variable "typecomment": Window>Preferences>Java>Templates. To enable and disable the creation of
 * type comments go to Window>Preferences>Java>Code Generation.
 */
public class IDEInitErrorBeanTypeProxy extends IDEBeanTypeProxy {

	protected String classname;

	protected String initializationError;

	protected IDEInitErrorBeanTypeProxy(IDEProxyFactoryRegistry registry, String classname, String initializationError) {
		super(registry, null);
		this.classname = classname;
		this.initializationError = initializationError;
	}

	/**
	 * @see org.eclipse.jem.internal.proxy.core.IBeanTypeProxy#getConstructorProxy(String[])
	 */
	public IConstructorProxy getConstructorProxy(String[] argumentClassNames) {
		return null;
	}

	/**
	 * @see org.eclipse.jem.internal.proxy.core.IBeanTypeProxy#getConstructorProxy(IBeanTypeProxy[])
	 */
	public IConstructorProxy getConstructorProxy(IBeanTypeProxy[] argumentTypes) {
		return null;
	}

	public IConstructorProxy[] getConstructors() {
		return null;
	}

	/**
	 * @see org.eclipse.jem.internal.proxy.core.IBeanTypeProxy#getConstructorProxy(String[])
	 */
	public IConstructorProxy getDeclaredConstructorProxy(String[] argumentClassNames) {
		return null;
	}

	/**
	 * @see org.eclipse.jem.internal.proxy.core.IBeanTypeProxy#getConstructorProxy(IBeanTypeProxy[])
	 */
	public IConstructorProxy getDeclaredConstructorProxy(IBeanTypeProxy[] argumentTypes) {
		return null;
	}

	public IConstructorProxy[] getDeclaredConstructors() {
		return null;
	}

	public IFieldProxy[] getFields() {
		return null;
	}

	public IFieldProxy[] getDeclaredFields() {
		return null;
	}

	/**
	 * @see org.eclipse.jem.internal.proxy.core.IBeanTypeProxy#getFieldProxy(String)
	 */
	public IFieldProxy getFieldProxy(String fieldName) {
		return null;
	}

	public IMethodProxy[] getMethods() {
		return null;
	}

	public IMethodProxy[] getDeclaredMethods() {
		return null;
	}

	/**
	 * @see org.eclipse.jem.internal.proxy.core.IBeanTypeProxy#getMethodProxy(String)
	 */
	public IMethodProxy getMethodProxy(String methodName) {
		return null;
	}

	/**
	 * @see org.eclipse.jem.internal.proxy.core.IBeanTypeProxy#getMethodProxy(String, String[])
	 */
	public IMethodProxy getMethodProxy(String methodName, String[] argumentClassNames) {
		return null;
	}

	/**
	 * @see org.eclipse.jem.internal.proxy.core.IBeanTypeProxy#getMethodProxy(String, String)
	 */
	public IMethodProxy getMethodProxy(String methodName, String argumentClassName) {
		return null;
	}

	/**
	 * @see org.eclipse.jem.internal.proxy.core.IBeanTypeProxy#getMethodProxy(String, IBeanTypeProxy[])
	 */
	public IMethodProxy getMethodProxy(String methodName, IBeanTypeProxy[] argumentTypes) {
		return null;
	}

	/**
	 * @see org.eclipse.jem.internal.proxy.core.IBeanTypeProxy#getMethodProxy(String, String[])
	 */
	public IMethodProxy getDeclaredMethodProxy(String methodName, String[] argumentClassNames) {
		return null;
	}

	/**
	 * @see org.eclipse.jem.internal.proxy.core.IBeanTypeProxy#getMethodProxy(String, IBeanTypeProxy[])
	 */
	public IMethodProxy getDeclaredMethodProxy(String methodName, IBeanTypeProxy[] argumentTypes) {
		return null;
	}

	/**
	 * @see org.eclipse.jem.internal.proxy.core.IBeanTypeProxy#getNullConstructorProxy()
	 */
	public IConstructorProxy getNullConstructorProxy() {
		return null;
	}

	/**
	 * @see org.eclipse.jem.internal.proxy.core.IBeanTypeProxy#getSuperBeanTypeProxy()
	 */
	public IBeanTypeProxy getSuperBeanTypeProxy() {
		return null;
	}

	/**
	 * @see org.eclipse.jem.internal.proxy.core.IBeanTypeProxy#getTypeName()
	 */
	public String getTypeName() {
		return classname;
	}

	/**
	 * @see org.eclipse.jem.internal.proxy.core.IBeanTypeProxy#getFormalTypeName()
	 */
	public String getFormalTypeName() {
		return getTypeName();
	}

	/**
	 * @see org.eclipse.jem.internal.proxy.core.IBeanTypeProxy#isArray()
	 */
	public boolean isArray() {
		return false;
	}

	/**
	 * @see org.eclipse.jem.internal.proxy.core.IBeanTypeProxy#isInterface()
	 */
	public boolean isInterface() {
		return false;
	}

	/**
	 * @see org.eclipse.jem.internal.proxy.core.IBeanTypeProxy#isPrimitive()
	 */
	public boolean isPrimitive() {
		return false;
	}

	/**
	 * @see org.eclipse.jem.internal.proxy.core.IBeanTypeProxy#isKindOf(IBeanTypeProxy)
	 */
	public boolean isKindOf(IBeanTypeProxy aBeanProxyType) {
		return false;
	}

	/**
	 * @see org.eclipse.jem.internal.proxy.core.IBeanTypeProxy#newInstance()
	 */
	public IBeanProxy newInstance() {
		return null;
	}

	/**
	 * @see org.eclipse.jem.internal.proxy.core.IBeanTypeProxy#newInstance(String)
	 */
	public IBeanProxy newInstance(String initializationString) {
		return null;
	}

	/**
	 * @see org.eclipse.jem.internal.proxy.core.IBeanTypeProxy#getInitializationError()
	 */
	public String getInitializationError() {
		return initializationError;
	}

	/**
	 * @see org.eclipse.jem.internal.proxy.core.IBeanProxy#toBeanString()
	 */
	public String toBeanString() {
		return classname;
	}

	/**
	 * @see org.eclipse.jem.internal.proxy.core.IBeanProxy#isValid()
	 */
	public boolean isValid() {
		return false;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.jem.internal.proxy.core.IBeanTypeProxy#getCompatibleConstructor(org.eclipse.jem.internal.proxy.core.IBeanTypeProxy[])
	 */
	public IConstructorProxy getCompatibleConstructor(IBeanTypeProxy[] argumentTypes) {
		return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.jem.internal.proxy.core.IBeanTypeProxy#getCompatibleMethod(java.lang.String,
	 *      org.eclipse.jem.internal.proxy.core.IBeanTypeProxy[])
	 */
	public IMethodProxy getCompatibleMethod(String methodName, IBeanTypeProxy[] argumentTypes) {
		return null;
	}
}