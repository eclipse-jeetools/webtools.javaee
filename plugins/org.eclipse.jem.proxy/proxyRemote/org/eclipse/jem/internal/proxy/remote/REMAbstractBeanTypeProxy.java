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
/*
 *  $RCSfile: REMAbstractBeanTypeProxy.java,v $
 *  $Revision: 1.8 $  $Date: 2005/02/08 11:29:09 $ 
 */
package org.eclipse.jem.internal.proxy.remote;

import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;

import org.eclipse.jem.internal.proxy.core.*;
import org.eclipse.jem.internal.proxy.common.CommandException;
import org.eclipse.jem.internal.proxy.common.remote.Commands;
import org.eclipse.jem.internal.proxy.common.remote.TransmitableArray;

/**
 * This implementation of IBeanTypeProxy is for where the Bean is on a different VM then the IDE
 * 
 * Creation date: (12/3/99 12:29:26 PM)
 * 
 * @author: Joe Winchester
 */
public abstract class REMAbstractBeanTypeProxy implements IREMBeanTypeProxy {

	private Integer fID;

	protected final REMProxyFactoryRegistry fRegistry;

	protected final String fClassname; // This is cached here so that we don't need to go over the line to get it.

	private IBeanTypeProxy fSuperType; // This is cached so as not to have to go over the line for it.

	/**
	 * Create with a registry and a class. It is protected so that only subclasses and the factory with this REM package can call it.
	 */
	protected REMAbstractBeanTypeProxy(REMProxyFactoryRegistry aRegistry, Integer anID, String aClassname, IBeanTypeProxy aSuperType) {
		fRegistry = aRegistry;
		fID = anID;
		fClassname = aClassname;
		fSuperType = aSuperType;
	}

	/**
	 * equals: Equal if: 1) This proxy == (identity) to the other object This is all that is needed for BeanTypes because we know these are classes,
	 * there is only one per class, and Class equals is true only for identity.
	 */
	public boolean equals(Object anObject) {
		return super.equals(anObject);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.jem.internal.proxy.core.IBeanProxy#sameAs(org.eclipse.jem.internal.proxy.core.IBeanProxy)
	 */
	public boolean sameAs(IBeanProxy aBeanProxy) {
		return this == aBeanProxy; // We can be assured in Remote Proxy that identity of proxy and identity of object are the same.
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.jem.internal.proxy.remote.IREMBeanProxy#getID()
	 */
	public Integer getID() {
		return fID;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.jem.internal.proxy.core.IBeanProxy#isValid()
	 */
	public boolean isValid() {
		return fID != null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.jem.internal.proxy.remote.IREMBeanProxy#release()
	 */
	public void release() {
		// Clear the id and supertype (supertype is cleared so that
		// it won't hold onto it and will let the supertype be GC'd
		// if necessary.

		fID = null;
		fSuperType = null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.jem.internal.proxy.core.IBeanTypeProxy#getConstructorProxy(java.lang.String[])
	 */
	public IConstructorProxy getConstructorProxy(String[] argumentClassNames) {
		if (isInterface())
			return null; // Interfaces don't have ctor's.

		// Turn class names into array of BeanTypes.
		IBeanTypeProxy[] argTypes = null;
		if (argumentClassNames != null) {
			IStandardBeanTypeProxyFactory typeFactory = fRegistry.getBeanTypeProxyFactory();
			argTypes = new IBeanTypeProxy[argumentClassNames.length];
			for (int i = 0; i < argumentClassNames.length; i++) {
				IBeanTypeProxy type = argTypes[i] = typeFactory.getBeanTypeProxy(argumentClassNames[i]);
				if (type == null)
					return null; // Couldn't find the type.
			}
		}

		return getConstructorProxy(argTypes);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.jem.internal.proxy.core.IBeanTypeProxy#getConstructorProxy(org.eclipse.jem.internal.proxy.core.IBeanTypeProxy[])
	 */
	public IConstructorProxy getConstructorProxy(IBeanTypeProxy[] argumentTypes) {
		if (isInterface())
			return null; // Interfaces don't have ctor's.

		IREMMethodProxy getCtorMethod = (IREMMethodProxy) REMStandardBeanProxyConstants.getConstants(fRegistry).getClassConstructor();

		// Create the argument array
		Object[] getParms = (argumentTypes != null) ? new Object[] { new TransmitableArray(Commands.CLASS_CLASS, argumentTypes)} : // Get Ctor has
																																   // only one parm,
																																   // the array of
																																   // parm types.
				null;

		try {
			return (IConstructorProxy) getCtorMethod.invokeWithParms(this, getParms);
		} catch (ThrowableProxy e) {
			fRegistry.releaseProxy(e);
			return null;
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.jem.internal.proxy.core.IBeanTypeProxy#getDeclaredFieldProxy(java.lang.String)
	 */
	public IFieldProxy getDeclaredFieldProxy(String fieldName) {
		return (IFieldProxy) REMStandardBeanProxyConstants.getConstants(fRegistry).getClassGetDeclaredField().invokeCatchThrowableExceptions(this,
				fRegistry.getBeanProxyFactory().createBeanProxyWith(fieldName));
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.jem.internal.proxy.core.IBeanTypeProxy#getFieldProxy(java.lang.String)
	 */
	public IFieldProxy getFieldProxy(String fieldName) {
		return REMProxyConstants.getConstants(getProxyFactoryRegistry()).getFieldProxy(this,fieldName);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.jem.internal.proxy.core.IBeanTypeProxy#getMethodProxy(java.lang.String)
	 */
	public IMethodProxy getMethodProxy(String methodName) {
		return REMProxyConstants.getConstants(getProxyFactoryRegistry()).getMethodProxy(this,methodName,(String[])null);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.jem.internal.proxy.core.IBeanTypeProxy#getMethodProxy(java.lang.String, java.lang.String[])
	 */
	public IMethodProxy getMethodProxy(String methodName, String[] argumentClassNames) {
		return REMProxyConstants.getConstants(getProxyFactoryRegistry()).getMethodProxy(this,methodName,argumentClassNames);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.jem.internal.proxy.core.IBeanTypeProxy#getMethodProxy(java.lang.String, java.lang.String)
	 */
	public IMethodProxy getMethodProxy(String methodName, String argumentQualifiedTypeName) {
		return REMProxyConstants.getConstants(getProxyFactoryRegistry()).getMethodProxy(this,methodName,new String[] {argumentQualifiedTypeName});
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.jem.internal.proxy.core.IBeanTypeProxy#getMethodProxy(java.lang.String, org.eclipse.jem.internal.proxy.core.IBeanTypeProxy[])
	 */
	public IMethodProxy getMethodProxy(String methodName, IBeanTypeProxy[] argumentTypes) {
		return REMProxyConstants.getConstants(getProxyFactoryRegistry()).getMethodProxy(this,methodName,argumentTypes);
	}
	
	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.jem.internal.proxy.core.IBeanTypeProxy#getInvokable(java.lang.String)
	 */
	public IInvokable getInvokable(String methodName) {
		return REMProxyConstants.getConstants(getProxyFactoryRegistry()).getInvokable(this,methodName,(String[])null);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.jem.internal.proxy.core.IBeanTypeProxy#getInvokable(java.lang.String, java.lang.String[])
	 */
	public IInvokable getInvokable(String methodName, String[] argumentClassNames) {
		return REMProxyConstants.getConstants(getProxyFactoryRegistry()).getInvokable(this,methodName,argumentClassNames);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.jem.internal.proxy.core.IBeanTypeProxy#getInvokable(java.lang.String, java.lang.String)
	 */
	public IInvokable getInvokable(String methodName, String argumentQualifiedTypeName) {
		return getInvokable(methodName, new String[] { argumentQualifiedTypeName});
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.jem.internal.proxy.core.IBeanTypeProxy#getInvokable(java.lang.String, org.eclipse.jem.internal.proxy.core.IBeanTypeProxy[])
	 */
	public IInvokable getInvokable(String methodName, IBeanTypeProxy[] argumentTypes) {
		return REMProxyConstants.getConstants(getProxyFactoryRegistry()).getInvokable(this,methodName,argumentTypes);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.jem.internal.proxy.core.IBeanTypeProxy#getNullConstructorProxy()
	 */
	public IConstructorProxy getNullConstructorProxy() {
		return getConstructorProxy((IBeanTypeProxy[]) null);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.jem.internal.proxy.core.IBeanProxy#getProxyFactoryRegistry()
	 */
	public ProxyFactoryRegistry getProxyFactoryRegistry() {
		return fRegistry;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.jem.internal.proxy.core.IBeanTypeProxy#getSuperBeanTypeProxy()
	 */
	public IBeanTypeProxy getSuperBeanTypeProxy() {
		return fSuperType;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.jem.internal.proxy.core.IBeanTypeProxy#getTypeName()
	 */
	public String getTypeName() {
		return fClassname;
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
		return ((IBooleanBeanProxy) REMStandardBeanProxyConstants.getConstants(fRegistry).getClassIsAssignableFrom().invokeCatchThrowableExceptions(
				aBeanProxyType, this)).booleanValue();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.jem.internal.proxy.core.IBeanTypeProxy#newInstance(java.lang.String)
	 */
	public IBeanProxy newInstance(String initializationString) throws ThrowableProxy, ClassCastException, InstantiationException {
		try {
			return ((REMStandardBeanProxyFactory) fRegistry.getBeanProxyFactory()).createBeanProxy(this, initializationString);
		} catch (CommandException e) {
			ProxyPlugin.getPlugin().getLogger().log(new Status(IStatus.WARNING, ProxyPlugin.getPlugin().getBundle().getSymbolicName(), 0, "", e)); //$NON-NLS-1$
			return null;
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	public String toString() {
		return super.toString() + "(" + getTypeName() + ")"; //$NON-NLS-2$//$NON-NLS-1$
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.jem.internal.proxy.core.IBeanProxy#getTypeProxy()
	 */
	public IBeanTypeProxy getTypeProxy() {
		return ((REMStandardBeanTypeProxyFactory) fRegistry.getBeanTypeProxyFactory()).classClass;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.jem.internal.proxy.core.IBeanProxy#toBeanString()
	 */
	public String toBeanString() {
		return getTypeName();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.jem.internal.proxy.core.IBeanTypeProxy#newInstance()
	 */
	public IBeanProxy newInstance() throws ThrowableProxy {
		return ((REMStandardBeanProxyFactory) fRegistry.getBeanProxyFactory()).createBeanProxy(this);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.jem.internal.proxy.remote.IREMBeanProxy#renderBean(org.eclipse.jem.internal.proxy.common.remote.Commands.ValueObject)
	 */
	public void renderBean(Commands.ValueObject value) {
		value.setObjectID(getID().intValue());
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
	 * @see org.eclipse.jem.internal.proxy.core.IBeanTypeProxy#getInitializationError()
	 */
	public String getInitializationError() {
		return null; // By default none have an initialization error. There is a special instance for init errors.
	}

}
