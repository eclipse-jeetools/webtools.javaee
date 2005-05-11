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
/*
 *  $RCSfile: REMInitErrorBeanTypeProxy.java,v $
 *  $Revision: 1.8 $  $Date: 2005/05/11 19:01:12 $ 
 */package org.eclipse.jem.internal.proxy.remote;

import org.eclipse.jem.internal.proxy.core.*;
import org.eclipse.jem.internal.proxy.common.remote.Commands;

/**
 * A special bean type proxy. It is for those bean types that had an instantiation error while being initialized. It is so we have the message. All
 * other actions against it will fail.
 * 
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

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.jem.internal.proxy.remote.IREMBeanTypeProxy#newBeanProxy(java.lang.Integer)
	 */
	public IREMBeanProxy newBeanProxy(Integer anID) {
		return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.jem.internal.proxy.remote.IREMBeanTypeProxy#newBeanTypeForClass(java.lang.Integer, java.lang.String, boolean)
	 */
	public IREMBeanTypeProxy newBeanTypeForClass(Integer anID, String aClassname, boolean anAbstract) {
		return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.jem.internal.proxy.core.IBeanTypeProxy#getConstructorProxy(java.lang.String[])
	 */
	public IConstructorProxy getConstructorProxy(String[] argumentClassNames) {
		return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.jem.internal.proxy.core.IBeanTypeProxy#getConstructorProxy(org.eclipse.jem.internal.proxy.core.IBeanTypeProxy[])
	 */
	public IConstructorProxy getConstructorProxy(IBeanTypeProxy[] argumentTypes) {
		return null;
	}
	
	
	/* (non-Javadoc)
	 * @see org.eclipse.jem.internal.proxy.core.IBeanTypeProxy#getConstructors()
	 */
	public IConstructorProxy[] getConstructors() {
		return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.jem.internal.proxy.core.IBeanTypeProxy#getConstructorProxy(java.lang.String[])
	 */
	public IConstructorProxy getDeclaredConstructorProxy(String[] argumentClassNames) {
		return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.jem.internal.proxy.core.IBeanTypeProxy#getConstructorProxy(org.eclipse.jem.internal.proxy.core.IBeanTypeProxy[])
	 */
	public IConstructorProxy getDeclaredConstructorProxy(IBeanTypeProxy[] argumentTypes) {
		return null;
	}
	
	
	/* (non-Javadoc)
	 * @see org.eclipse.jem.internal.proxy.core.IBeanTypeProxy#getConstructors()
	 */
	public IConstructorProxy[] getDeclaredConstructors() {
		return null;
	}

	/* (non-Javadoc)
	 * @see org.eclipse.jem.internal.proxy.core.IBeanTypeProxy#getConstructors()
	 */
	public IFieldProxy[] getFields() {
		return null;
	}

	/* (non-Javadoc)
	 * @see org.eclipse.jem.internal.proxy.core.IBeanTypeProxy#getConstructors()
	 */
	public IFieldProxy[] getDeclaredFields() {
		return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.jem.internal.proxy.core.IBeanTypeProxy#getFieldProxy(java.lang.String)
	 */
	public IFieldProxy getFieldProxy(String fieldName) {
		return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.jem.internal.proxy.core.IBeanTypeProxy#getDeclaredFieldProxy(java.lang.String)
	 */
	public IFieldProxy getDeclaredFieldProxy(String fieldName) {
		return null;
	}

	/* (non-Javadoc)
	 * @see org.eclipse.jem.internal.proxy.core.IBeanTypeProxy#getConstructors()
	 */
	public IMethodProxy[] getMethods() {
		return null;
	}

	/* (non-Javadoc)
	 * @see org.eclipse.jem.internal.proxy.core.IBeanTypeProxy#getConstructors()
	 */
	public IMethodProxy[] getDeclaredMethods() {
		return null;
	}

	
	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.jem.internal.proxy.core.IBeanTypeProxy#getMethodProxy(java.lang.String)
	 */
	public IMethodProxy getMethodProxy(String methodName) {
		return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.jem.internal.proxy.core.IBeanTypeProxy#getMethodProxy(java.lang.String, java.lang.String[])
	 */
	public IMethodProxy getMethodProxy(String methodName, String[] argumentClassNames) {
		return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.jem.internal.proxy.core.IBeanTypeProxy#getMethodProxy(java.lang.String, java.lang.String)
	 */
	public IMethodProxy getMethodProxy(String methodName, String argumentClassName) {
		return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.jem.internal.proxy.core.IBeanTypeProxy#getMethodProxy(java.lang.String, org.eclipse.jem.internal.proxy.core.IBeanTypeProxy[])
	 */
	public IMethodProxy getMethodProxy(String methodName, IBeanTypeProxy[] argumentTypes) {
		return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.jem.internal.proxy.core.IBeanTypeProxy#getMethodProxy(java.lang.String, org.eclipse.jem.internal.proxy.core.IBeanTypeProxy[])
	 */
	public IMethodProxy getDeclaredMethodProxy(String methodName, IBeanTypeProxy[] argumentTypes) {
		return null;
	}
	
	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.jem.internal.proxy.core.IBeanTypeProxy#getMethodProxy(java.lang.String, java.lang.String[])
	 */
	public IMethodProxy getDeclaredMethodProxy(String methodName, String[] argumentClassNames) {
		return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.jem.internal.proxy.core.IBeanTypeProxy#getNullConstructorProxy()
	 */
	public IConstructorProxy getNullConstructorProxy() {
		return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.jem.internal.proxy.core.IBeanProxy#getProxyFactoryRegistry()
	 */
	public ProxyFactoryRegistry getProxyFactoryRegistry() {
		return registry;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.jem.internal.proxy.core.IBeanTypeProxy#getSuperBeanTypeProxy()
	 */
	public IBeanTypeProxy getSuperBeanTypeProxy() {
		return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.jem.internal.proxy.core.IBeanTypeProxy#getTypeName()
	 */
	public String getTypeName() {
		return classname;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.jem.internal.proxy.core.IBeanTypeProxy#getFormalTypeName()
	 */
	public String getFormalTypeName() {
		return getTypeName();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.jem.internal.proxy.core.IBeanTypeProxy#isArray()
	 */
	public boolean isArray() {
		return false;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.jem.internal.proxy.core.IBeanTypeProxy#isInterface()
	 */
	public boolean isInterface() {
		return false;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.jem.internal.proxy.core.IBeanTypeProxy#isPrimitive()
	 */
	public boolean isPrimitive() {
		return false;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.jem.internal.proxy.core.IBeanTypeProxy#isKindOf(org.eclipse.jem.internal.proxy.core.IBeanTypeProxy)
	 */
	public boolean isKindOf(IBeanTypeProxy aBeanProxyType) {
		return false;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.jem.internal.proxy.core.IBeanTypeProxy#newInstance()
	 */
	public IBeanProxy newInstance() throws ThrowableProxy {
		return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.jem.internal.proxy.core.IBeanTypeProxy#newInstance(java.lang.String)
	 */
	public IBeanProxy newInstance(String initializationString) throws ThrowableProxy, ClassCastException, InstantiationException {
		return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.jem.internal.proxy.core.IBeanTypeProxy#getInitializationError()
	 */
	public String getInitializationError() {
		return initializationError;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.jem.internal.proxy.remote.IREMBeanProxy#getID()
	 */
	public Integer getID() {
		return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.jem.internal.proxy.remote.IREMBeanProxy#release()
	 */
	public void release() {
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.jem.internal.proxy.remote.IREMBeanProxy#renderBean(org.eclipse.jem.internal.proxy.common.remote.Commands.ValueObject)
	 */
	public void renderBean(Commands.ValueObject renderInto) {
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.jem.internal.proxy.core.IBeanProxy#getTypeProxy()
	 */
	public IBeanTypeProxy getTypeProxy() {
		return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.jem.internal.proxy.core.IBeanProxy#toBeanString()
	 */
	public String toBeanString() {
		return ""; //$NON-NLS-1$
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.jem.internal.proxy.core.IBeanProxy#isValid()
	 */
	public boolean isValid() {
		return false;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.jem.internal.proxy.core.IBeanProxy#sameAs(org.eclipse.jem.internal.proxy.core.IBeanProxy)
	 */
	public boolean sameAs(IBeanProxy aBeanProxy) {
		return this == aBeanProxy; // This is place holder anyway. So only identical if same proxy.
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.jem.internal.proxy.core.IBeanTypeProxy#getInvokable(java.lang.String, org.eclipse.jem.internal.proxy.core.IBeanTypeProxy[])
	 */
	public IInvokable getInvokable(String methodName, IBeanTypeProxy[] argumentTypes) {
		return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.jem.internal.proxy.core.IBeanTypeProxy#getInvokable(java.lang.String, java.lang.String)
	 */
	public IInvokable getInvokable(String methodName, String argumentClassName) {
		return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.jem.internal.proxy.core.IBeanTypeProxy#getInvokable(java.lang.String, java.lang.String[])
	 */
	public IInvokable getInvokable(String methodName, String[] argumentClassNames) {
		return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.jem.internal.proxy.core.IBeanTypeProxy#getInvokable(java.lang.String)
	 */
	public IInvokable getInvokable(String methodName) {
		return null;
	}
	/* (non-Javadoc)
	 * @see org.eclipse.jem.internal.proxy.core.IBeanTypeProxy#getCompatibleConstructor(org.eclipse.jem.internal.proxy.core.IBeanTypeProxy[])
	 */
	public IConstructorProxy getCompatibleConstructor(IBeanTypeProxy[] argumentTypes) {
		return null;
	}
	/* (non-Javadoc)
	 * @see org.eclipse.jem.internal.proxy.core.IBeanTypeProxy#getCompatibleMethod(java.lang.String, org.eclipse.jem.internal.proxy.core.IBeanTypeProxy[])
	 */
	public IMethodProxy getCompatibleMethod(String methodName, IBeanTypeProxy[] argumentTypes) {
		return null;
	}
	/* (non-Javadoc)
	 * @see org.eclipse.jem.internal.proxy.core.IProxy#isBeanProxy()
	 */
	public final boolean isBeanProxy() {
		return true;
	}
	/* (non-Javadoc)
	 * @see org.eclipse.jem.internal.proxy.core.IProxy#isExpressionProxy()
	 */
	public final boolean isExpressionProxy() {
		return false;
	}
	
	/* (non-Javadoc)
	 * @see org.eclipse.jem.internal.proxy.core.IProxyBeanType#getMethodProxy(org.eclipse.jem.internal.proxy.core.IExpression, java.lang.String, org.eclipse.jem.internal.proxy.core.IProxyBeanType[])
	 */
	public IProxyMethod getMethodProxy(IExpression expression, String methodName, IProxyBeanType[] parameters) {
		return null;	// Since this guy is invalid, we can't return a method.
	}

	/* (non-Javadoc)
	 * @see org.eclipse.jem.internal.proxy.core.IProxyBeanType#getMethodProxy(org.eclipse.jem.internal.proxy.core.IExpression, java.lang.String, java.lang.String[])
	 */
	public IProxyMethod getMethodProxy(IExpression expression, String methodName, String[] parameterTypes) {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see org.eclipse.jem.internal.proxy.core.IProxyBeanType#getFieldProxy(org.eclipse.jem.internal.proxy.core.IExpression, java.lang.String)
	 */
	public IProxyField getFieldProxy(IExpression expression, String fieldName) {
		// TODO Auto-generated method stub
		return null;
	}
	
	
}
