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
 *  $RCSfile: REMMethodProxyFactory.java,v $
 *  $Revision: 1.6 $  $Date: 2004/08/27 15:35:20 $ 
 */
package org.eclipse.jem.internal.proxy.remote;

import java.lang.reflect.AccessibleObject;

import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.jem.internal.proxy.core.*;
import org.eclipse.jem.internal.proxy.common.remote.Commands;
import org.eclipse.jem.internal.proxy.common.remote.TransmitableArray;

/**
 * Factory for creating method proxies. Creation date: (12/3/99 6:29:40 PM)
 * 
 * @author: Joe Winchester
 */
public class REMMethodProxyFactory implements IMethodProxyFactory {

	final protected REMProxyFactoryRegistry fFactoryRegistry;

	// Cached copy of a few typical method type proxies.
	IREMBeanTypeProxy accessibleType;

	REMMethodTypeProxy methodType;

	REMFieldTypeProxy fieldType;

	REMConstructorTypeProxy ctorType;

	REMMethodProxy getMethodProxy;

	REMMethodProxyFactory(REMProxyFactoryRegistry aRegistry) {
		fFactoryRegistry = aRegistry;
		aRegistry.registerMethodProxyFactory(this);
		REMStandardBeanTypeProxyFactory typeFactory = (REMStandardBeanTypeProxyFactory) aRegistry.getBeanTypeProxyFactory();

		accessibleType = typeFactory.objectClass.newBeanTypeForClass(new Integer(Commands.ACCESSIBLEOBJECT_CLASS), AccessibleObject.class.getName(),
				false);
		methodType = new REMMethodTypeProxy(aRegistry, accessibleType);
		fieldType = new REMFieldTypeProxy(aRegistry, accessibleType);
		ctorType = new REMConstructorTypeProxy(aRegistry, accessibleType);
		aRegistry.getBeanTypeProxyFactory().registerBeanTypeProxy(accessibleType, true);
		aRegistry.getBeanTypeProxyFactory().registerBeanTypeProxy(methodType, true);
		aRegistry.getBeanTypeProxyFactory().registerBeanTypeProxy(fieldType, true);
		aRegistry.getBeanTypeProxyFactory().registerBeanTypeProxy(ctorType, true);

		getMethodProxy = (REMMethodProxy) methodType.newBeanProxy(new Integer(Commands.GET_METHOD_ID));
		((REMStandardBeanProxyFactory) aRegistry.getBeanProxyFactory()).registerProxy(getMethodProxy);

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.jem.internal.proxy.core.IMethodProxyFactory#getMethodProxy(java.lang.String, java.lang.String, java.lang.String[])
	 */
	public IMethodProxy getMethodProxy(String className, String methodName, String[] parameterTypes) {
		IStandardBeanTypeProxyFactory typeFactory = fFactoryRegistry.getBeanTypeProxyFactory();
		IBeanTypeProxy clsProxy = typeFactory.getBeanTypeProxy(className);
		if (clsProxy == null)
			return null;
		return clsProxy.getMethodProxy(methodName, parameterTypes);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.jem.internal.proxy.core.IMethodProxyFactory#getInvokable(java.lang.String, java.lang.String, java.lang.String[])
	 */
	public IInvokable getInvokable(String className, String methodName, String[] parameterTypes) {
		IStandardBeanTypeProxyFactory typeFactory = fFactoryRegistry.getBeanTypeProxyFactory();
		IBeanTypeProxy clsProxy = typeFactory.getBeanTypeProxy(className);
		if (clsProxy == null)
			return null;
		return clsProxy.getInvokable(methodName, parameterTypes);
	}

	/**
	 * Get the method id from the remote system and create the method proxy.
	 * 
	 * NOTE: It is public ONLY so that IBeanTypeProxy implementations can call it. It must not be used by anyone else.
	 * 
	 * @param beanType
	 * @param methodName
	 * @param parameterTypes
	 * @return
	 * 
	 * @since 1.0.0
	 */
	public IInvokable getInvokable(IREMBeanTypeProxy beanType, String methodName, String[] parameterTypes) {
		// First convert the parameter types into IBeanTypeProxy[].
		IREMBeanTypeProxy[] types = null;
		if (parameterTypes != null) {
			IStandardBeanTypeProxyFactory typeFactory = fFactoryRegistry.getBeanTypeProxyFactory();
			types = new IREMBeanTypeProxy[parameterTypes.length];
			for (int i = 0; i < parameterTypes.length; i++) {
				IBeanTypeProxy type = types[i] = (IREMBeanTypeProxy) typeFactory.getBeanTypeProxy(parameterTypes[i]);
				if (type == null)
					return null; // One of the parm types doesn't exist.
			}
		}

		return getInvokable(beanType, methodName, types);		
	}
	
	/**
	 * Get the method id from the remote system and create the method proxy.
	 * 
	 * NOTE: It is public ONLY so that IBeanTypeProxy implementations can call it. It must not be used by anyone else.
	 */
	public IMethodProxy getMethodProxy(IREMBeanTypeProxy beanType, String methodName, String[] parameterTypes) {
		// First convert the parameter types into IBeanTypeProxy[].
		IBeanTypeProxy[] types = null;
		if (parameterTypes != null) {
			IStandardBeanTypeProxyFactory typeFactory = fFactoryRegistry.getBeanTypeProxyFactory();
			types = new IBeanTypeProxy[parameterTypes.length];
			for (int i = 0; i < parameterTypes.length; i++) {
				IBeanTypeProxy type = types[i] = typeFactory.getBeanTypeProxy(parameterTypes[i]);
				if (type == null)
					return null; // One of the parm types doesn't exist.
			}
		}

		return getMethodProxy(beanType, methodName, types);
	}

	
	/**
	 * Get the invokable. 
	 * 
	 * NOTE: It is public ONLY so that IBeanTypeProxy implementations can call it. It must not be used by anyone else.
	 * @param beanType
	 * @param methodName
	 * @param parameterTypes
	 * @return
	 * 
	 * @since 1.0.0
	 */
	public IInvokable getInvokable(IREMBeanTypeProxy beanType, String methodName, IBeanTypeProxy[] parameterTypes) {
		return new REMInvokable(beanType, methodName, parameterTypes);
	}
	
	/**
	 * Get the method id from the remote system and create the method proxy.
	 * 
	 * NOTE: It is public ONLY so that IBeanTypeProxy implementations can call it. It must not be used by anyone else.
	 */
	public IMethodProxy getMethodProxy(IREMBeanTypeProxy beanType, String methodName, IBeanTypeProxy[] parameterTypes) {
		// The parms to getMethod are:
		// parm[0] = String - method name
		// parm[1] = Class[] - Parameter Classes.
		Object[] parms = new Object[] { methodName, parameterTypes != null ? (new TransmitableArray(Commands.CLASS_CLASS, parameterTypes)) : null};
		IBeanProxy result = null;
		try {
			result = getMethodProxy.invokeWithParms(beanType, parms);
			return (IMethodProxy) result;
		} catch (ThrowableProxy e) {
			fFactoryRegistry.releaseProxy(e); // Since it's not needed, get rid of now instead of GC time.
			return null;
		} catch (ClassCastException e) {
			// Some trace msgs because we keep getting into a mode after awhile (though not reproducible) that returns non-method proxies and we need
			// more info.
			StringBuffer buf = new StringBuffer("Method requested is \"" + methodName + "("); //$NON-NLS-1$	//$NON-NLS-2$
			for (int i = 0; i < parameterTypes.length; i++) {
				if (i > 0)
					buf.append(','); //$NON-NLS-1$
				buf.append(parameterTypes[i].getTypeName());
			}
			buf.append(")\""); //$NON-NLS-1$
			ProxyPlugin.getPlugin().getLogger().log(
					new Status(IStatus.ERROR, ProxyPlugin.getPlugin().getBundle().getSymbolicName(), 0, buf.toString(), null));
			try {
				if (result != null) {
					ProxyPlugin.getPlugin().getLogger().log(
							new Status(IStatus.ERROR, ProxyPlugin.getPlugin().getBundle().getSymbolicName(), 0,
									"ClassCastException of result. Return type is \"" + result.getTypeProxy().getTypeName() + "\"\n" + //$NON-NLS-1$	//$NON-NLS-2$
											"Return type bean type proxy class=\"" + result.getTypeProxy().getClass().getName() + "\"\n" + //$NON-NLS-1$	//$NON-NLS-2$
											"GetMethodProxy valid=" + getMethodProxy.isValid() + "\n" + //$NON-NLS-1$	//$NON-NLS-2$
											"GetMethodProxy real name is \"" + getMethodProxy.getName() + "\"\n", null)); //$NON-NLS-1$	//$NON-NLS-2$			
				}
			} catch (Exception e2) {
				ProxyPlugin.getPlugin().getLogger().log(new Status(IStatus.ERROR, ProxyPlugin.getPlugin().getBundle().getSymbolicName(), 0, "", e2)); //$NON-NLS-1$
			}
			throw e; // Rethrow it.
		}
	}

	/**
	 * Terminate the factory. Don't need to do anything because of the proxies being held onto are registered any will be cleaned up themselves.
	 */
	public void terminateFactory() {
	}

}
