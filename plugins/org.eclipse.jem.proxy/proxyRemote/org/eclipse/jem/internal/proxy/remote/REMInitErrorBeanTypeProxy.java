package org.eclipse.jem.internal.proxy.remote;
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
 *  $RCSfile: REMInitErrorBeanTypeProxy.java,v $
 *  $Revision: 1.1 $  $Date: 2003/10/27 17:22:23 $ 
 */
 
import org.eclipse.jem.internal.proxy.core.*;
import org.eclipse.jem.internal.proxy.common.remote.Commands;

/**
 * A special bean type proxy. It is for those bean types that had an
 * instantiation error while being initialized. It is so we have
 * the message. All other actions against it will fail.
 * @author richkulp
 */
public class REMInitErrorBeanTypeProxy implements IREMBeanTypeProxy {

	protected final String initializationError;
	protected final REMProxyFactoryRegistry registry;
	protected final String classname;
	
	/**
	 * Constructor for REMInitErrorBeanTypeProxy.
	 */
	public REMInitErrorBeanTypeProxy(REMProxyFactoryRegistry registry, String initializationError, String classname) {
		super();
		this.registry = registry;
		this.initializationError = initializationError;
		this.classname = classname;
	}

	/**
	 * @see org.eclipse.jem.internal.proxy.remote.IREMBeanTypeProxy#newBeanProxy(Integer)
	 */
	public IREMBeanProxy newBeanProxy(Integer anID) {
		return null;
	}

	/**
	 * @see org.eclipse.jem.internal.proxy.remote.IREMBeanTypeProxy#newBeanTypeForClass(Integer, String, boolean)
	 */
	public IREMBeanTypeProxy newBeanTypeForClass(Integer anID, String aClassname, boolean anAbstract) {
		return null;
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
	 * @see org.eclipse.jem.internal.proxy.core.IBeanProxy#getProxyFactoryRegistry()
	 */
	public ProxyFactoryRegistry getProxyFactoryRegistry() {
		return registry;
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
	public IBeanProxy newInstance() throws ThrowableProxy {
		return null;
	}

	/**
	 * @see org.eclipse.jem.internal.proxy.core.IBeanTypeProxy#newInstance(String)
	 */
	public IBeanProxy newInstance(String initializationString) throws ThrowableProxy, ClassCastException, InstantiationException {
		return null;
	}

	/**
	 * @see org.eclipse.jem.internal.proxy.core.IBeanTypeProxy#getInitializationError()
	 */
	public String getInitializationError() {
		return initializationError;
	}

	/**
	 * @see org.eclipse.jem.internal.proxy.remote.IREMBeanProxy#getID()
	 */
	public Integer getID() {
		return null;
	}

	/**
	 * @see org.eclipse.jem.internal.proxy.remote.IREMBeanProxy#release()
	 */
	public void release() {
	}

	/**
	 * @see org.eclipse.jem.internal.proxy.remote.IREMBeanProxy#renderBean(ValueObject)
	 */
	public void renderBean(Commands.ValueObject renderInto) {
	}

	/**
	 * @see org.eclipse.jem.internal.proxy.core.IBeanProxy#getTypeProxy()
	 */
	public IBeanTypeProxy getTypeProxy() {
		return null;
	}

	/**
	 * @see org.eclipse.jem.internal.proxy.core.IBeanProxy#isNullProxy()
	 */
	public boolean isNullProxy() {
		return false;
	}

	/**
	 * @see org.eclipse.jem.internal.proxy.core.IBeanProxy#toBeanString()
	 */
	public String toBeanString() {
		return ""; //$NON-NLS-1$
	}

	/**
	 * @see org.eclipse.jem.internal.proxy.core.IBeanProxy#isValid()
	 */
	public boolean isValid() {
		return false;
	}

	/* (non-Javadoc)
	 * @see org.eclipse.jem.internal.proxy.core.IBeanProxy#sameAs(org.eclipse.jem.internal.proxy.core.IBeanProxy)
	 */
	public boolean sameAs(IBeanProxy aBeanProxy) {
		return this == aBeanProxy;	// This is place holder anyway. So only identical if same proxy.
	}

}
