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
 *  $RCSfile: REMAbstractBeanTypeProxy.java,v $
 *  $Revision: 1.11 $  $Date: 2005/02/15 22:56:10 $ 
 */
package org.eclipse.jem.internal.proxy.remote;

import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;

import org.eclipse.jem.internal.proxy.core.*;
import org.eclipse.jem.internal.proxy.common.AmbiguousMethodException;
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
	
	
	/* (non-Javadoc)
	 * @see org.eclipse.jem.internal.proxy.core.IBeanTypeProxy#getConstructors()
	 */
	public IConstructorProxy[] getConstructors() {

		if (isInterface())
			return null; // Interfaces don't have ctor's.

		IREMMethodProxy getCtorMethod = (IREMMethodProxy) REMStandardBeanProxyConstants.getConstants(fRegistry).getClassConstructors();

		try {
			IArrayBeanProxy ctors = (IArrayBeanProxy) getCtorMethod.invokeWithParms(this, null);
			IBeanProxy[] proxies = ctors.getSnapshot();
			IConstructorProxy[] ctorproxies = new IConstructorProxy[proxies.length];
			System.arraycopy(proxies, 0, ctorproxies, 0, proxies.length);
			return ctorproxies;
		} catch (ThrowableProxy e) {
			fRegistry.releaseProxy(e);
			return null;
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.jem.internal.proxy.core.IBeanTypeProxy#getConstructorProxy(java.lang.String[])
	 */
	public IConstructorProxy getDeclaredConstructorProxy(String[] argumentClassNames) {
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

		return getDeclaredConstructorProxy(argTypes);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.jem.internal.proxy.core.IBeanTypeProxy#getConstructorProxy(org.eclipse.jem.internal.proxy.core.IBeanTypeProxy[])
	 */
	public IConstructorProxy getDeclaredConstructorProxy(IBeanTypeProxy[] argumentTypes) {
		if (isInterface())
			return null; // Interfaces don't have ctor's.

		IREMMethodProxy getCtorMethod = (IREMMethodProxy) REMStandardBeanProxyConstants.getConstants(fRegistry).getDeclaredClassConstructor();

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
	
	
	/* (non-Javadoc)
	 * @see org.eclipse.jem.internal.proxy.core.IBeanTypeProxy#getConstructors()
	 */
	public IConstructorProxy[] getDeclaredConstructors() {

		if (isInterface())
			return null; // Interfaces don't have ctor's.

		IREMMethodProxy getCtorMethod = (IREMMethodProxy) REMStandardBeanProxyConstants.getConstants(fRegistry).getDeclaredClassConstructors();

		try {
			IArrayBeanProxy ctors = (IArrayBeanProxy) getCtorMethod.invokeWithParms(this, null);
			IBeanProxy[] proxies = ctors.getSnapshot();
			IConstructorProxy[] ctorproxies = new IConstructorProxy[proxies.length];
			System.arraycopy(proxies, 0, ctorproxies, 0, proxies.length);
			return ctorproxies;
		} catch (ThrowableProxy e) {
			fRegistry.releaseProxy(e);
			return null;
		}
	}

	/* (non-Javadoc)
	 * @see org.eclipse.jem.internal.proxy.core.IBeanTypeProxy#getConstructors()
	 */
	public IFieldProxy[] getFields() {

		IREMMethodProxy getFieldsMethod = (IREMMethodProxy) REMStandardBeanProxyConstants.getConstants(fRegistry).getClassGetFields();

		try {
			IArrayBeanProxy fields = (IArrayBeanProxy) getFieldsMethod.invokeWithParms(this, null);
			IBeanProxy[] proxies = fields.getSnapshot();
			IFieldProxy[] fieldProxies = new IFieldProxy[proxies.length];
			System.arraycopy(proxies, 0, fieldProxies, 0, proxies.length);
			return fieldProxies;
		} catch (ThrowableProxy e) {
			fRegistry.releaseProxy(e);
			return null;
		}
	}

	/* (non-Javadoc)
	 * @see org.eclipse.jem.internal.proxy.core.IBeanTypeProxy#getConstructors()
	 */
	public IFieldProxy[] getDeclaredFields() {

		IREMMethodProxy getDeclaredFieldsMethod = (IREMMethodProxy) REMStandardBeanProxyConstants.getConstants(fRegistry).getClassGetDeclaredFields();

		try {
			IArrayBeanProxy fields = (IArrayBeanProxy) getDeclaredFieldsMethod.invokeWithParms(this, null);
			IBeanProxy[] proxies = fields.getSnapshot();
			IFieldProxy[] fieldProxies = new IFieldProxy[proxies.length];
			System.arraycopy(proxies, 0, fieldProxies, 0, proxies.length);
			return fieldProxies;
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
		return ((REMStandardBeanTypeProxyFactory) fRegistry.getBeanTypeProxyFactory()).proxyConstants.getFieldProxy(this,fieldName);
	}

	/* (non-Javadoc)
	 * @see org.eclipse.jem.internal.proxy.core.IBeanTypeProxy#getConstructors()
	 */
	public IMethodProxy[] getMethods() {

		IREMMethodProxy getMethodsMethod = (IREMMethodProxy) REMStandardBeanProxyConstants.getConstants(fRegistry).getClassMethods();

		try {
			IArrayBeanProxy methods = (IArrayBeanProxy) getMethodsMethod.invokeWithParms(this, null);
			IBeanProxy[] proxies = methods.getSnapshot();
			IMethodProxy[] methodProxies = new IMethodProxy[proxies.length];
			System.arraycopy(proxies, 0, methodProxies, 0, proxies.length);
			return methodProxies;
		} catch (ThrowableProxy e) {
			fRegistry.releaseProxy(e);
			return null;
		}
	}

	/* (non-Javadoc)
	 * @see org.eclipse.jem.internal.proxy.core.IBeanTypeProxy#getConstructors()
	 */
	public IMethodProxy[] getDeclaredMethods() {

		IREMMethodProxy getDeclaredMethodsMethod = (IREMMethodProxy) REMStandardBeanProxyConstants.getConstants(fRegistry).getClassDeclaredMethods();

		try {
			IArrayBeanProxy methods = (IArrayBeanProxy) getDeclaredMethodsMethod.invokeWithParms(this, null);
			IBeanProxy[] proxies = methods.getSnapshot();
			IMethodProxy[] methodProxies = new IMethodProxy[proxies.length];
			System.arraycopy(proxies, 0, methodProxies, 0, proxies.length);
			return methodProxies;
		} catch (ThrowableProxy e) {
			fRegistry.releaseProxy(e);
			return null;
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.jem.internal.proxy.core.IBeanTypeProxy#getMethodProxy(java.lang.String)
	 */
	public IMethodProxy getMethodProxy(String methodName) {
		return ((REMStandardBeanTypeProxyFactory) fRegistry.getBeanTypeProxyFactory()).proxyConstants.getMethodProxy(this,methodName,(String[])null);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.jem.internal.proxy.core.IBeanTypeProxy#getMethodProxy(java.lang.String, java.lang.String[])
	 */
	public IMethodProxy getMethodProxy(String methodName, String[] argumentClassNames) {
		return ((REMStandardBeanTypeProxyFactory) fRegistry.getBeanTypeProxyFactory()).proxyConstants.getMethodProxy(this,methodName,argumentClassNames);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.jem.internal.proxy.core.IBeanTypeProxy#getMethodProxy(java.lang.String, java.lang.String)
	 */
	public IMethodProxy getMethodProxy(String methodName, String argumentQualifiedTypeName) {
		return ((REMStandardBeanTypeProxyFactory) fRegistry.getBeanTypeProxyFactory()).proxyConstants.getMethodProxy(this,methodName,new String[] {argumentQualifiedTypeName});
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.jem.internal.proxy.core.IBeanTypeProxy#getMethodProxy(java.lang.String, org.eclipse.jem.internal.proxy.core.IBeanTypeProxy[])
	 */
	public IMethodProxy getMethodProxy(String methodName, IBeanTypeProxy[] argumentTypes) {
		return ((REMStandardBeanTypeProxyFactory) fRegistry.getBeanTypeProxyFactory()).proxyConstants.getMethodProxy(this,methodName,argumentTypes);
	}
	
	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.jem.internal.proxy.core.IBeanTypeProxy#getConstructorProxy(java.lang.String[])
	 */
	public IMethodProxy getDeclaredMethodProxy(String methodName, String[] argumentClassNames) {

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

		return getDeclaredMethodProxy(methodName, argTypes);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.jem.internal.proxy.core.IBeanTypeProxy#getConstructorProxy(org.eclipse.jem.internal.proxy.core.IBeanTypeProxy[])
	 */
	public IMethodProxy getDeclaredMethodProxy(String methodName, IBeanTypeProxy[] argumentTypes) {
		IREMMethodProxy getDeclaredMethodMethod = (IREMMethodProxy) REMStandardBeanProxyConstants.getConstants(fRegistry).getClassDeclaredMethod();
		IBeanProxy nameProxy = fRegistry.getBeanProxyFactory().createBeanProxyWith(methodName);
		// Create the argument array
		Object[] getParms = (argumentTypes != null) ? new Object[] { nameProxy, new TransmitableArray(Commands.CLASS_CLASS, argumentTypes)} : new Object[] {nameProxy, null};

		try {
			return (IMethodProxy) getDeclaredMethodMethod.invokeWithParms(this, getParms);
		} catch (ThrowableProxy e) {
			fRegistry.releaseProxy(e);
			return null;
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.jem.internal.proxy.core.IBeanTypeProxy#getInvokable(java.lang.String)
	 */
	public IInvokable getInvokable(String methodName) {
		return ((REMStandardBeanTypeProxyFactory) fRegistry.getBeanTypeProxyFactory()).proxyConstants.getInvokable(this,methodName,(String[])null);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.jem.internal.proxy.core.IBeanTypeProxy#getInvokable(java.lang.String, java.lang.String[])
	 */
	public IInvokable getInvokable(String methodName, String[] argumentClassNames) {
		return ((REMStandardBeanTypeProxyFactory) fRegistry.getBeanTypeProxyFactory()).proxyConstants.getInvokable(this,methodName,argumentClassNames);
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
		return ((REMStandardBeanTypeProxyFactory) fRegistry.getBeanTypeProxyFactory()).proxyConstants.getInvokable(this,methodName,argumentTypes);
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

	/* (non-Javadoc)
	 * @see org.eclipse.jem.internal.proxy.core.IBeanTypeProxy#getCompatibleConstructor(org.eclipse.jem.internal.proxy.core.IBeanTypeProxy[])
	 */
	public IConstructorProxy getCompatibleConstructor(IBeanTypeProxy[] argumentTypes) throws NoSuchMethodException, AmbiguousMethodException {
		if (isInterface())
			return null; // Interfaces don't have ctor's.

		IREMMethodProxy findCompatibleMethod = (IREMMethodProxy) REMStandardBeanProxyConstants.getConstants(fRegistry).getFindCompatibleConstructorMethod();

		// Create the argument array
		Object[] getParms = (argumentTypes != null) ? new Object[] { this, new TransmitableArray(Commands.CLASS_CLASS, argumentTypes)} : new Object[] {this, null};

		try {
			return (IConstructorProxy) findCompatibleMethod.invokeWithParms(this, getParms);
		} catch (ThrowableProxy e) {
			try {
				if (e.getTypeProxy().equals(fRegistry.getBeanTypeProxyFactory().getBeanTypeProxy("java.lang.NoSuchMethodException")))
					throw new NoSuchMethodException(e.getProxyLocalizedMessage());
				else if (e.getTypeProxy().equals(fRegistry.getBeanTypeProxyFactory().getBeanTypeProxy("org.eclipse.jem.internal.proxy.common.AmbiguousMethodException")))
					throw new AmbiguousMethodException(e.getProxyLocalizedMessage());
				ProxyPlugin.getPlugin().getLogger().log(e);
				return null;
			} finally {
				fRegistry.releaseProxy(e);
			}
		}
	}
	/* (non-Javadoc)
	 * @see org.eclipse.jem.internal.proxy.core.IBeanTypeProxy#getCompatibleMethod(java.lang.String, org.eclipse.jem.internal.proxy.core.IBeanTypeProxy[])
	 */
	public IMethodProxy getCompatibleMethod(String methodName, IBeanTypeProxy[] argumentTypes) throws NoSuchMethodException, AmbiguousMethodException {

		IREMMethodProxy findCompatibleMethod = (IREMMethodProxy) REMStandardBeanProxyConstants.getConstants(fRegistry).getFindCompatibleMethodMethod();
		IBeanProxy methodNameProxy = fRegistry.getBeanProxyFactory().createBeanProxyWith(methodName);

		// Create the argument array
		Object[] getParms = (argumentTypes != null) ? new Object[] { this, methodNameProxy, new TransmitableArray(Commands.CLASS_CLASS, argumentTypes)} : new Object[] {this, methodNameProxy, null};

		try {
			return (IMethodProxy) findCompatibleMethod.invokeWithParms(this, getParms);
		} catch (ThrowableProxy e) {
			try {
				if (e.getTypeProxy().equals(fRegistry.getBeanTypeProxyFactory().getBeanTypeProxy("java.lang.NoSuchMethodException")))
					throw new NoSuchMethodException(e.getProxyLocalizedMessage());
				else if (e.getTypeProxy().equals(fRegistry.getBeanTypeProxyFactory().getBeanTypeProxy("org.eclipse.jem.internal.proxy.common.AmbiguousMethodException")))
					throw new AmbiguousMethodException(e.getProxyLocalizedMessage());
				ProxyPlugin.getPlugin().getLogger().log(e);
				return null;
			} finally {
				fRegistry.releaseProxy(e);
			}
		}
	}
}
