/*****************************************************************************************************************************************************
 * Copyright (c) 2001, 2004 IBM Corporation and others. All rights reserved. This program and the accompanying materials are made available under the
 * terms of the Common Public License v1.0 which accompanies this distribution, and is available at http://www.eclipse.org/legal/cpl-v10.html
 * 
 * Contributors: IBM Corporation - initial API and implementation
 ****************************************************************************************************************************************************/
/*
 * $RCSfile: IDEBeanTypeProxy.java,v $ $Revision: 1.8 $ $Date: 2005/02/10 22:38:30 $
 */
package org.eclipse.jem.internal.proxy.ide;

import org.eclipse.jem.internal.proxy.common.AmbiguousMethodException;
import org.eclipse.jem.internal.proxy.core.*;

import java.lang.reflect.*;

import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;

public class IDEBeanTypeProxy extends IDEBeanProxy implements IBeanTypeProxy {

	protected IDEProxyFactoryRegistry fProxyFactoryRegistry;

	Class fClass;

	protected IDEBeanTypeProxy(IDEProxyFactoryRegistry aRegistry, Class aClass) {
		super(aRegistry, aClass);
		fProxyFactoryRegistry = aRegistry;
		fClass = aClass;
	}

	/**
	 * Get Type Proxy. The type proxy of a BeanType is Class.
	 */
	public IBeanTypeProxy getTypeProxy() {
		return ((IDEStandardBeanTypeProxyFactory) fProxyFactoryRegistry.getBeanTypeProxyFactory()).classClass;
	}

	public Class getTypeClass() {
		return fClass;
	}

	public String toBeanString() {
		return fClass.toString();
	}

	public ProxyFactoryRegistry getProxyFactoryRegistry() {
		return fProxyFactoryRegistry;
	}

	public String getTypeName() {
		return fClass.getName();
	}

	public boolean isKindOf(IBeanTypeProxy aBeanTypeProxy) {
		return ((IDEBeanTypeProxy) aBeanTypeProxy).fClass.isAssignableFrom(fClass);
	}

	public boolean isInterface() {
		return fClass.isInterface();
	}

	public boolean isPrimitive() {
		return fClass.isPrimitive();
	}

	public boolean isArray() {
		return fClass.isArray();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.jem.internal.proxy.core.IBeanTypeProxy#getConstructors()
	 */
	public IMethodProxy[] getMethods() {
		return ((IDEMethodProxyFactory) fProxyFactoryRegistry.getMethodProxyFactory()).getMethods(fClass);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.jem.internal.proxy.core.IBeanTypeProxy#getConstructors()
	 */
	public IMethodProxy[] getDeclaredMethods() {
		return ((IDEMethodProxyFactory) fProxyFactoryRegistry.getMethodProxyFactory()).getDeclaredMethods(fClass);
	}

	/**
	 * We can use reflection on our class to find the java.reflect.Method instance and create the IDEMethodProxy directly
	 */
	public IMethodProxy getMethodProxy(String methodName) {

		try {
			Method aMethod = fClass.getMethod(methodName, new Class[0]);
			return ((IDEMethodProxyFactory) fProxyFactoryRegistry.getMethodProxyFactory()).getMethodProxy(aMethod);
		} catch (Exception exc) {
			ProxyPlugin.getPlugin().getLogger().log(new Status(IStatus.WARNING, ProxyPlugin.getPlugin().getBundle().getSymbolicName(), 0, "", exc));
		}
		return null;

	}

	public IMethodProxy getMethodProxy(String methodName, String[] argumentClassNames) {
		return ((IDEMethodProxyFactory) fProxyFactoryRegistry.getMethodProxyFactory()).getMethodProxy(this, methodName, argumentClassNames);
	}

	public IMethodProxy getMethodProxy(String methodName, String firstArgClass) {
		return getMethodProxy(methodName, new String[] { firstArgClass});
	}

	public IMethodProxy getMethodProxy(String methodName, IBeanTypeProxy[] args) {
		Class[] argClasses = new Class[args.length];
		for (int i = 0; i < args.length; i++) {
			argClasses[i] = ((IDEBeanTypeProxy) args[i]).fClass;
		}
		return ((IDEMethodProxyFactory) fProxyFactoryRegistry.getMethodProxyFactory()).getMethodProxy(fClass, methodName, argClasses);
	}

	public IMethodProxy getDeclaredMethodProxy(String methodName, String[] argumentClassNames) {
		return ((IDEMethodProxyFactory) fProxyFactoryRegistry.getMethodProxyFactory()).getDeclaredMethodProxy(fClass, methodName, argumentClassNames);
	}

	public IMethodProxy getDeclaredMethodProxy(String methodName, IBeanTypeProxy[] args) {
		Class[] argClasses = null;
		if (args != null) {
			argClasses = new Class[args.length];
			for (int i = 0; i < args.length; i++) {
				argClasses[i] = ((IDEBeanTypeProxy) args[i]).fClass;
			}
		}
		return ((IDEMethodProxyFactory) fProxyFactoryRegistry.getMethodProxyFactory()).getDeclaredMethodProxy(fClass, methodName, argClasses);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.jem.internal.proxy.core.IBeanTypeProxy#getInvokable(java.lang.String, org.eclipse.jem.internal.proxy.core.IBeanTypeProxy[])
	 */
	public IInvokable getInvokable(String methodName, IBeanTypeProxy[] argumentTypes) {
		return getMethodProxy(methodName, argumentTypes);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.jem.internal.proxy.core.IBeanTypeProxy#getInvokable(java.lang.String, java.lang.String)
	 */
	public IInvokable getInvokable(String methodName, String argumentClassName) {
		return getMethodProxy(methodName, argumentClassName);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.jem.internal.proxy.core.IBeanTypeProxy#getInvokable(java.lang.String, java.lang.String[])
	 */
	public IInvokable getInvokable(String methodName, String[] argumentClassNames) {
		return getMethodProxy(methodName, argumentClassNames);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.jem.internal.proxy.core.IBeanTypeProxy#getInvokable(java.lang.String)
	 */
	public IInvokable getInvokable(String methodName) {
		return getMethodProxy(methodName);
	}

	public IConstructorProxy getConstructorProxy(String[] argTypeNames) {
		IBeanTypeProxy[] argClasses = new IBeanTypeProxy[argTypeNames.length];
		IStandardBeanTypeProxyFactory btFactory = fProxyFactoryRegistry.getBeanTypeProxyFactory();
		for (int i = 0; i < argTypeNames.length; i++) {
			argClasses[i] = btFactory.getBeanTypeProxy(argTypeNames[i]);
			if (argClasses[i] == null) {
				ProxyPlugin.getPlugin().getLogger().log(
						new Status(IStatus.WARNING, ProxyPlugin.getPlugin().getBundle().getSymbolicName(), 0, "Constructor not found - "
								+ fClass.getName() + " args=" + argTypeNames, null));
				return null;
			}
		}
		return getConstructorProxy(argClasses);
	}

	public IConstructorProxy getConstructorProxy(IBeanTypeProxy[] args) {
		Class[] argClasses = new Class[args.length];
		for (int i = 0; i < args.length; i++) {
			argClasses[i] = ((IDEBeanTypeProxy) args[i]).fClass;
		}
		return ((IDEMethodProxyFactory) fProxyFactoryRegistry.getMethodProxyFactory()).getConstructorProxy(fClass, argClasses);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.jem.internal.proxy.core.IBeanTypeProxy#getConstructors()
	 */
	public IConstructorProxy[] getConstructors() {
		return ((IDEMethodProxyFactory) fProxyFactoryRegistry.getMethodProxyFactory()).getConstructors(fClass);
	}

	public IConstructorProxy getDeclaredConstructorProxy(String[] argTypeNames) {
		IBeanTypeProxy[] argClasses = new IBeanTypeProxy[argTypeNames.length];
		IStandardBeanTypeProxyFactory btFactory = fProxyFactoryRegistry.getBeanTypeProxyFactory();
		for (int i = 0; i < argTypeNames.length; i++) {
			argClasses[i] = btFactory.getBeanTypeProxy(argTypeNames[i]);
			if (argClasses[i] == null) {
				ProxyPlugin.getPlugin().getLogger().log(
						new Status(IStatus.WARNING, ProxyPlugin.getPlugin().getBundle().getSymbolicName(), 0, "Constructor not found - "
								+ fClass.getName() + " args=" + argTypeNames, null));
				return null;
			}
		}
		return getDeclaredConstructorProxy(argClasses);
	}

	public IConstructorProxy getDeclaredConstructorProxy(IBeanTypeProxy[] args) {
		Class[] argClasses = new Class[args.length];
		for (int i = 0; i < args.length; i++) {
			argClasses[i] = ((IDEBeanTypeProxy) args[i]).fClass;
		}
		return ((IDEMethodProxyFactory) fProxyFactoryRegistry.getMethodProxyFactory()).getDeclaredConstructorProxy(fClass, argClasses);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.jem.internal.proxy.core.IBeanTypeProxy#getConstructors()
	 */
	public IConstructorProxy[] getDeclaredConstructors() {
		return ((IDEMethodProxyFactory) fProxyFactoryRegistry.getMethodProxyFactory()).getDeclaredConstructors(fClass);
	}

	/**
	 * Return the supertype for us Use the factory to get it rather than create it directly so that the factory has the chance to cache classes if
	 * required This is required as part of the interface
	 */
	public IBeanTypeProxy getSuperBeanTypeProxy() {

		if (fClass.isInterface())
			return null;
		else
			return fProxyFactoryRegistry.getBeanTypeProxyFactory().getBeanTypeProxy(fClass.getSuperclass().getName());

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.jem.internal.proxy.core.IBeanTypeProxy#getConstructors()
	 */
	public IFieldProxy[] getFields() {
		return ((IDEMethodProxyFactory) fProxyFactoryRegistry.getMethodProxyFactory()).getFields(fClass);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.jem.internal.proxy.core.IBeanTypeProxy#getConstructors()
	 */
	public IFieldProxy[] getDeclaredFields() {
		return ((IDEMethodProxyFactory) fProxyFactoryRegistry.getMethodProxyFactory()).getDeclaredFields(fClass);
	}

	public IFieldProxy getFieldProxy(String fieldName) {
		try {
			Field field = fClass.getField(fieldName);
			return ((IDEMethodProxyFactory) fProxyFactoryRegistry.getMethodProxyFactory()).getFieldProxy(field);
		} catch (NoSuchFieldException exc) {
			ProxyPlugin.getPlugin().getLogger().log(
					new Status(IStatus.WARNING, ProxyPlugin.getPlugin().getBundle().getSymbolicName(), 0, "Field not found " + fClass.getName()
							+ " - " + fieldName, exc));
			return null;
		}
	}

	public IFieldProxy getDeclaredFieldProxy(String fieldName) {
		try {
			Field field = fClass.getDeclaredField(fieldName);
			return ((IDEMethodProxyFactory) fProxyFactoryRegistry.getMethodProxyFactory()).getFieldProxy(field);
		} catch (NoSuchFieldException exc) {
			ProxyPlugin.getPlugin().getLogger().log(
					new Status(IStatus.WARNING, ProxyPlugin.getPlugin().getBundle().getSymbolicName(), 0, "Field not found " + fClass.getName()
							+ " - " + fieldName, exc));
			return null;
		}
	}

	/**
	 * Return a proxy to the constructor for the target VM being the same as the IDE We can use the package protected contstructor on
	 * IDEConstructorProxy
	 */
	IConstructorProxy getConstructorProxy(Class[] parameterTypes) {

		Constructor aConstructor = null;

		// Use reflection to try and find a constructor. The argumentClassNames must be converted to classes
		try {
			aConstructor = fClass.getConstructor(parameterTypes);
		} catch (Exception e) {
			ProxyPlugin.getPlugin().getLogger().log(new Status(IStatus.WARNING, ProxyPlugin.getPlugin().getBundle().getSymbolicName(), 0, "", e));
		}
		// If we have a constructor return it
		if (aConstructor != null) {
			return getNewConstructorProxy(aConstructor);
		} else {
			return null;
		}

	}

	/**
	 * Factored into its own method so subclass can override
	 */
	protected IConstructorProxy getNewConstructorProxy(Constructor aConstructor) {

		return ((IDEMethodProxyFactory) fProxyFactoryRegistry.getMethodProxyFactory()).getConstructorProxy(aConstructor);

	}

	/**
	 * Return a proxy to the null constructor for the target VM being the same as the IDE We can use the package protected contstructor on
	 * IDEConstructorProxy
	 */
	public IConstructorProxy getNullConstructorProxy() {

		Constructor aConstructor = null;

		// Use reflection to try and find a constructor. The argumentClassNames must be converted to classes
		try {
			aConstructor = fClass.getConstructor(null);
			return ((IDEMethodProxyFactory) fProxyFactoryRegistry.getMethodProxyFactory()).getConstructorProxy(aConstructor);
		} catch (Exception e) {
			ProxyPlugin.getPlugin().getLogger().log(new Status(IStatus.WARNING, ProxyPlugin.getPlugin().getBundle().getSymbolicName(), 0, "", e));
			return null;
		}

	}

	public IBeanProxy newInstance(String initializationString) {
		return ((IDEStandardBeanProxyFactory) fProxyFactoryRegistry.getBeanProxyFactory()).createBeanProxy(this, initializationString);
	}

	public IBeanProxy newInstance() {
		return ((IDEStandardBeanProxyFactory) fProxyFactoryRegistry.getBeanProxyFactory()).createBeanProxy(this);
	}

	/**
	 * Create a new bean proxy with the specified object Use the constructor that allows the type proxy to be set as well as the bean because we are
	 * the type and by setting it now we might avoid it being lookup for in the factory the first time it is asked for
	 * 
	 * NOTE: All subclasses need to override this to return the appropriate IDEBeanProxy.
	 */
	protected IIDEBeanProxy newBeanProxy(Object anObject) {

		return new IDEObjectBeanProxy(fProxyFactoryRegistry, anObject, this);

	}

	/**
	 * Create a subclass beantype proxy.
	 */
	public IDEBeanTypeProxy newBeanTypeForClass(Class type) {
		// Default is just another instance of this same class.
		return new IDEBeanTypeProxy(fProxyFactoryRegistry, type);
	}

	/**
	 * @see IBeanTypeProxy#getFormalTypeName()
	 */
	public String getFormalTypeName() {
		return getTypeName();
	}

	/**
	 * @see org.eclipse.jem.internal.proxy.core.IBeanTypeProxy#getInitializationError()
	 */
	public String getInitializationError() {
		return null; // By default none have an initialization error. There is a special instance for init errors.
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.jem.internal.proxy.core.IBeanTypeProxy#getCompatibleConstructor(org.eclipse.jem.internal.proxy.core.IBeanTypeProxy[])
	 */
	public IConstructorProxy getCompatibleConstructor(IBeanTypeProxy[] argumentTypes) throws AmbiguousMethodException, NoSuchMethodException {

		Class[] argClasses = new Class[argumentTypes.length];
		for (int i = 0; i < argumentTypes.length; i++) {
			argClasses[i] = ((IDEBeanTypeProxy) argumentTypes[i]).fClass;
		}
		return ((IDEMethodProxyFactory) fProxyFactoryRegistry.getMethodProxyFactory()).getCompatibleConstructor(fClass, argClasses);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.jem.internal.proxy.core.IBeanTypeProxy#getCompatibleMethod(java.lang.String,
	 *      org.eclipse.jem.internal.proxy.core.IBeanTypeProxy[])
	 */
	public IMethodProxy getCompatibleMethod(String methodName, IBeanTypeProxy[] argumentTypes) throws NoSuchMethodException, AmbiguousMethodException {
		Class[] argClasses = new Class[argumentTypes.length];
		for (int i = 0; i < argumentTypes.length; i++) {
			argClasses[i] = ((IDEBeanTypeProxy) argumentTypes[i]).fClass;
		}
		return ((IDEMethodProxyFactory) fProxyFactoryRegistry.getMethodProxyFactory()).getCompatibleMethod(fClass, methodName, argClasses);
	}
}