package org.eclipse.jem.internal.proxy.ide;
/*******************************************************************************
 * Copyright (c)  2001, 2003 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials 
 * are made available under the terms of the Common Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/cpl-v10.html
 * 
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *******************************************************************************/
/*
 *  $RCSfile: IDEInitErrorBeanTypeProxy.java,v $
 *  $Revision: 1.2 $  $Date: 2004/02/03 23:18:36 $ 
 */

import org.eclipse.jem.internal.proxy.core.*;

/**
 * @author richkulp
 *
 * To change this generated comment edit the template variable "typecomment":
 * Window>Preferences>Java>Templates.
 * To enable and disable the creation of type comments go to
 * Window>Preferences>Java>Code Generation.
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

	/**
	 * @see org.eclipse.jem.internal.proxy.core.IBeanTypeProxy#getFieldProxy(String)
	 */
	public IFieldProxy getFieldProxy(String fieldName) {
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
	public String toBeanString(){
		return classname;
	}

	/**
	 * @see org.eclipse.jem.internal.proxy.core.IBeanProxy#isValid()
	 */
	public boolean isValid() {
		return false;
	}

}
