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
 *  $RCSfile: REMAbstractBeanTypeProxy.java,v $
 *  $Revision: 1.4 $  $Date: 2004/04/20 09:01:20 $ 
 */

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
 * @author: Joe Winchester
 */
public abstract class REMAbstractBeanTypeProxy implements IREMBeanTypeProxy {
	private Integer fID;
	protected final REMProxyFactoryRegistry fRegistry;
	protected final String fClassname; // This is cached here so that we don't need to go over the line to get it.
	private IBeanTypeProxy fSuperType; // This is cached so as not to have to go over the line for it.
	/**
	 * Create with a registry and a class. It is protected so that only subclasses
	 * and the factory with this REM package can call it.
	 */
	protected REMAbstractBeanTypeProxy(REMProxyFactoryRegistry aRegistry, Integer anID, String aClassname, IBeanTypeProxy aSuperType) {
		fRegistry = aRegistry;
		fID = anID;
		fClassname = aClassname;
		fSuperType = aSuperType;
	}

	/**
	 * equals: Equal if:
	 *         1) This proxy == (identity) to the other object
	 *	This is all that is needed for BeanTypes because we know these are classes, there is only one per class,
	 *  and Class equals is true only for identity.
	 */
	public boolean equals(Object anObject) {
		return super.equals(anObject);
	}
	
	/* (non-Javadoc)
	 * @see org.eclipse.jem.internal.proxy.core.IBeanProxy#sameAs(org.eclipse.jem.internal.proxy.core.IBeanProxy)
	 */
	public boolean sameAs(IBeanProxy aBeanProxy) {
		return this == aBeanProxy;	// We can be assured in Remote Proxy that identity of proxy and identity of object are the same.
	}	

	/**
	 * Return the ID of this proxy
	 */
	public Integer getID() {
		return fID;
	}

	/**
	 * isValid - has beantype been released
	 */
	public boolean isValid() {
		return fID != null;
	}

	/**
	 * release - BeanType is about to be released.
	 * Clear the id and supertype (supertype is cleared so that
	 * it won't hold onto it and will let the supertype be GC'd
	 * if necessary.
	 */
	public void release() {
		fID = null;
		fSuperType = null;
	}

	/**
	 * Return a proxy to the constructor for the target VM 
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

	/**
	 * Return a proxy to the constructor for the target VM with specified argument types
	 */
	public IConstructorProxy getConstructorProxy(IBeanTypeProxy[] argumentTypes) {
		if (isInterface())
			return null; // Interfaces don't have ctor's.

		IREMMethodProxy getCtorMethod = (IREMMethodProxy) REMStandardBeanProxyConstants.getConstants(fRegistry).getClassConstructor();

		// Create the argument array
		Object[] getParms = (argumentTypes != null) ? new Object[] { new TransmitableArray(Commands.CLASS_CLASS, argumentTypes)}
		: // Get Ctor has only one parm, the array of parm types.
		null;

		try {
			return (IConstructorProxy) getCtorMethod.invokeWithParms(this, getParms);
		} catch (ThrowableProxy e) {
			fRegistry.releaseProxy(e);
			return null;
		}
	}
	/**
	 * Construct an REMFieldProxy and return it
	 */
	public IFieldProxy getDeclaredFieldProxy(String fieldName) {
		return (IFieldProxy) REMStandardBeanProxyConstants.getConstants(fRegistry).getClassGetDeclaredField().invokeCatchThrowableExceptions(
			this,
			fRegistry.getBeanProxyFactory().createBeanProxyWith(fieldName));
	}	
	/**
	 * Construct an REMFieldProxy and return it
	 */
	public IFieldProxy getFieldProxy(String fieldName) {
		return (IFieldProxy) REMStandardBeanProxyConstants.getConstants(fRegistry).getClassGetField().invokeCatchThrowableExceptions(
			this,
			fRegistry.getBeanProxyFactory().createBeanProxyWith(fieldName));
	}
	/**
	 * Return the method proxy for this method with no parms.
	 */
	public IMethodProxy getMethodProxy(String methodName) {
		return ((REMMethodProxyFactory) fRegistry.getMethodProxyFactory()).getMethodProxy(this, methodName, (IBeanTypeProxy[]) null);
	}
	/**
	 * Return the method proxy for this method with these arguments.
	 */
	public IMethodProxy getMethodProxy(String methodName, String[] argumentClassNames) {
		return ((REMMethodProxyFactory) fRegistry.getMethodProxyFactory()).getMethodProxy(this, methodName, argumentClassNames);
	}
	/**
	 * Defer to the method that accepts an array of arguments.
	 */
	public IMethodProxy getMethodProxy(String methodName, String argumentQualifiedTypeName) {
		return getMethodProxy(methodName, new String[] { argumentQualifiedTypeName });
	}

	/**
	 * Return the method proxy for this method with these argument types.
	 */
	public IMethodProxy getMethodProxy(String methodName, IBeanTypeProxy[] argumentTypes) {
		return ((REMMethodProxyFactory) fRegistry.getMethodProxyFactory()).getMethodProxy(this, methodName, argumentTypes);
	}
	/**
	 * Return a proxy to the null constructor for the target VM being the same as the REM
	 * We can use the package protected contstructor on REMConstructorProxy
	 */
	public IConstructorProxy getNullConstructorProxy() {
		return getConstructorProxy((IBeanTypeProxy[]) null);
	}
	/**
	 * getProxyFactoryRegistry method comment.
	 */
	public ProxyFactoryRegistry getProxyFactoryRegistry() {
		return fRegistry;
	}
	/**
	 * Return the supertype for us
	 */
	public IBeanTypeProxy getSuperBeanTypeProxy() {
		return fSuperType;
	}
	/**
	 * Return the name of the type
	 */
	public String getTypeName() {
		return fClassname;
	}
	/**
	 * We are not an array type.
	 */
	public boolean isArray() {
		return false;
	}

	/**
	 * Interfaces override this and return the correct value.
	 */
	public boolean isInterface() {
		return false;
	}

	/**
	 * Primitives override this and return the correct value.
	 */
	public boolean isPrimitive() {
		return false;
	}
	/**
	 * Answer as to whether we are a kind of the argument
	 * This is the same as asking whether the argument isAssignable from us
	 * See the comment on Class.isAssignableFrom() for more details on this method.
	 */
	public boolean isKindOf(IBeanTypeProxy aBeanProxyType) {
		return (
			(IBooleanBeanProxy) REMStandardBeanProxyConstants
				.getConstants(fRegistry)
				.getClassIsAssignableFrom()
				.invokeCatchThrowableExceptions(
				aBeanProxyType,
				this))
			.booleanValue();
	}

	/**
	 * newInstance method comment.
	 */
	public IBeanProxy newInstance(String initializationString) throws ThrowableProxy, ClassCastException, InstantiationException {
		try {
			return ((REMStandardBeanProxyFactory) fRegistry.getBeanProxyFactory()).createBeanProxy(this, initializationString);
		} catch (CommandException e) {
			ProxyPlugin.getPlugin().getLogger().log(new Status(IStatus.WARNING, ProxyPlugin.getPlugin().getDescriptor().getUniqueIdentifier(), 0, "", e)); //$NON-NLS-1$
			return null;
		}
	}

	/**
	 */
	public String toString() {
		return super.toString() + "(" + getTypeName() + ")"; //$NON-NLS-2$//$NON-NLS-1$
	}

	/**
	 * Get Type Proxy. The type proxy of a BeanType is Class.
	 */
	public IBeanTypeProxy getTypeProxy() {
		return ((REMStandardBeanTypeProxyFactory) fRegistry.getBeanTypeProxyFactory()).classClass;
	}

	/**
	 * toBeanString
	 */
	public String toBeanString() {
		return getTypeName();
	}
	/**
	 * newInstance method. Let the factory create a new instance on the remote using the default ctor.
	 */
	public IBeanProxy newInstance() throws ThrowableProxy {
		return ((REMStandardBeanProxyFactory) fRegistry.getBeanProxyFactory()).createBeanProxy(this);
	}

	/**
	 * Render the bean into value object.
	 */
	public void renderBean(Commands.ValueObject value) {
		value.setObjectID(getID().intValue());
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
		return null;	// By default none have an initialization error. There is a special instance for init errors.
	}

}
