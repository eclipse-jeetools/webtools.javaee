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
 *  $RCSfile: IDEMethodProxy.java,v $
 *  $Revision: 1.5.2.1 $  $Date: 2004/06/24 18:19:03 $ 
 */

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;

import org.eclipse.jem.internal.proxy.core.*;

public class IDEMethodProxy extends IDEAccessibleObjectProxy implements IMethodProxy {

	protected IDEMethodProxy(IDEProxyFactoryRegistry aRegistry, Method aMethod) {
		super(aRegistry, aMethod);
	}

	public IBeanTypeProxy getClassType() {
		return ((IDEStandardBeanTypeProxyFactory) fProxyFactoryRegistry.getBeanTypeProxyFactory()).getBeanTypeProxy(
			((Method) getBean()).getDeclaringClass());
	}
	public IBeanTypeProxy getReturnType() {
		return ((IDEStandardBeanTypeProxyFactory) fProxyFactoryRegistry.getBeanTypeProxyFactory()).getBeanTypeProxy(
			((Method) getBean()).getReturnType());
	}
	public IBeanTypeProxy[] getParameterTypes() {
		Class[] parmClasses = ((Method) getBean()).getParameterTypes();
		IBeanTypeProxy[] parmTypes = new IBeanTypeProxy[parmClasses.length];
		IDEStandardBeanTypeProxyFactory factory = (IDEStandardBeanTypeProxyFactory) fProxyFactoryRegistry.getBeanTypeProxyFactory();
		for (int i = 0; i < parmClasses.length; i++) {
			parmTypes[i] = factory.getBeanTypeProxy(parmClasses[i]);
		}
		return parmTypes;
	}
	public String getName() {
		return ((Method) getBean()).getName();
	}

	/**
	 * The type proxy is constant proxy out of the method factory.
	 */
	public IBeanTypeProxy getTypeProxy() {
		return ((IDEMethodProxyFactory) fProxyFactoryRegistry.getMethodProxyFactory()).methodType;
	}

	/**
	 * Invoke the method directly.  Because we are an IDEMethodProxy we can assume that IBeanProxy
	 * is an IDEBeanProxy and we can get its bean directly
	 */
	public IBeanProxy invoke(IBeanProxy subject) throws ThrowableProxy {
		try {
			Object result = ((Method) getBean()).invoke(subject != null ? ((IIDEBeanProxy) subject).getBean() : null, new Object[0]);
			if (result == null) {
				return null;
			} else {
				// The result may be a java.lang.Integer when we really want to create an int
				// Reflection always give you the big object
				Class returnType = ((Method) getBean()).getReturnType();
				return getBeanProxy(returnType, result);
			}
		} catch (InvocationTargetException e) {
			// This is a wrappered exception. Return the wrappered one so it looks like
			// it was the real one.
			IBeanTypeProxy exceptionTypeProxy =
				((IDEStandardBeanTypeProxyFactory) fProxyFactoryRegistry.getBeanTypeProxyFactory()).getBeanTypeProxy(
					e.getTargetException().getClass());
			throw new IDEThrowableProxy(e.getTargetException(), exceptionTypeProxy);
		} catch (Exception exc) {
			IBeanTypeProxy exceptionTypeProxy =
				((IDEStandardBeanTypeProxyFactory) fProxyFactoryRegistry.getBeanTypeProxyFactory()).getBeanTypeProxy(exc.getClass());
			throw new IDEThrowableProxy(exc, exceptionTypeProxy);
		}
	}
	/**
	 * Do not throw an exception
	 */
	public IBeanProxy invokeCatchThrowableExceptions(IBeanProxy subject) {
		try {
			return invoke(subject);
		} catch (ThrowableProxy exc) {
			ProxyPlugin.getPlugin().getLogger().log(
				new Status(IStatus.WARNING, ProxyPlugin.getPlugin().getBundle().getSymbolicName(), 0, "", exc));
		}
		return null;
	}
	/**
	 * Invoke the method with argument.  The argument will be an IDEBeanProxy 
	 * ( because we are an IDEMethodProxy ) so we can cast to it and get the actual bean
	 * itself and use this to invoke against method which is the actual
	 * java.lang.Reflect instance
	 */
	public IBeanProxy invoke(IBeanProxy subject, IBeanProxy argument) throws ThrowableProxy {
		try {
			Object result =
				((Method) getBean()).invoke(
					subject != null ? ((IIDEBeanProxy) subject).getBean() : null,
					new Object[] { argument != null ? ((IIDEBeanProxy) argument).getBean() : null });
			if (result == null) {
				return null;
			} else {
				Class returnType = ((Method) getBean()).getReturnType();
				return getBeanProxy(returnType, result);
			}
		} catch (InvocationTargetException e) {
			// This is a wrappered exception. Return the wrappered one so it looks like
			// it was the real one.
			IBeanTypeProxy exceptionTypeProxy =
				((IDEStandardBeanTypeProxyFactory) fProxyFactoryRegistry.getBeanTypeProxyFactory()).getBeanTypeProxy(
					e.getTargetException().getClass());
			throw new IDEThrowableProxy(e.getTargetException(), exceptionTypeProxy);
		} catch (Exception exc) {
			IBeanTypeProxy exceptionTypeProxy =
				((IDEStandardBeanTypeProxyFactory) fProxyFactoryRegistry.getBeanTypeProxyFactory()).getBeanTypeProxy(exc.getClass());
			throw new IDEThrowableProxy(exc, exceptionTypeProxy);
		}
	}
	/**
	 * Invoke without throwing an exception
	 */
	public IBeanProxy invokeCatchThrowableExceptions(IBeanProxy subject, IBeanProxy argument) {
		try {
			return invoke(subject, argument);
		} catch (ThrowableProxy exc) {
			ProxyPlugin.getPlugin().getLogger().log(
				new Status(IStatus.WARNING, ProxyPlugin.getPlugin().getBundle().getSymbolicName(), 0, "", exc));
		}
		return null;
	}
	/**
	 * Invoke the method with arguments.  The arguments will be IDEBeanProxy objects
	 * ( because we are an IDEMethodProxy ) so we can cast to them and get the actual bean
	 * objects themselves and use these to invoke against method which is the actual
	 * java.lang.Reflect instance
	 */
	public IBeanProxy invoke(IBeanProxy subject, IBeanProxy[] arguments) throws ThrowableProxy {
		Object[] beanArguments = new Object[arguments.length];
		for (int i = 0; i < arguments.length; i++) {
			if (arguments[i] != null) {
				beanArguments[i] = ((IIDEBeanProxy) arguments[i]).getBean();
			}
		}
		try {
			Object result = ((Method) getBean()).invoke(subject != null ? ((IIDEBeanProxy) subject).getBean() : null, beanArguments);
			if (result == null) {
				return null;
			} else {
				Class returnType = ((Method) getBean()).getReturnType();
				return getBeanProxy(returnType, result);
			}
		} catch (InvocationTargetException e) {
			// This is a wrappered exception. Return the wrappered one so it looks like
			// it was the real one.
			IBeanTypeProxy exceptionTypeProxy =
				((IDEStandardBeanTypeProxyFactory) fProxyFactoryRegistry.getBeanTypeProxyFactory()).getBeanTypeProxy(
					e.getTargetException().getClass());
			throw new IDEThrowableProxy(e.getTargetException(), exceptionTypeProxy);
		} catch (Exception exc) {
			IBeanTypeProxy exceptionTypeProxy =
				((IDEStandardBeanTypeProxyFactory) fProxyFactoryRegistry.getBeanTypeProxyFactory()).getBeanTypeProxy(exc.getClass());
			throw new IDEThrowableProxy(exc, exceptionTypeProxy);
		}
	}
	/**
	 * Invoke without throwing an exception
	 */
	public IBeanProxy invokeCatchThrowableExceptions(IBeanProxy subject, IBeanProxy[] arguments) {
		try {
			return invoke(subject, arguments);
		} catch (ThrowableProxy exc) {
			ProxyPlugin.getPlugin().getLogger().log(
				new Status(IStatus.WARNING, ProxyPlugin.getPlugin().getBundle().getSymbolicName(), 0, "", exc));
		}
		return null;
	}
	IBeanProxy getBeanProxy(Class returnType, Object bean) {
		return fProxyFactoryRegistry.getBeanProxy(returnType, bean);
	}
}
