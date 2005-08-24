/*******************************************************************************
 * Copyright (c) 2001, 2005 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *******************************************************************************/
/*
 *  $RCSfile: REMMethodProxyFactory.java,v $
 *  $Revision: 1.10 $  $Date: 2005/08/24 20:39:06 $ 
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
		// This will redirect to the beantype, which will go through the method cache.
		return clsProxy.getMethodProxy(methodName, parameterTypes);
	}
	
	/**
	 * A helper method to get the method proxy for the expression. This is used by registries and beantype proxies. It will go through
	 * the method cache to make sure we get only one.
	 * 
	 * @param expression
	 * @param aBeanTypeProxy
	 * @param methodName
	 * @param parmTypes
	 * @return
	 * 
	 * @since 1.1.0
	 */
	public IProxyMethod getMethodProxy(IExpression expression, IProxyBeanType aBeanTypeProxy, String methodName, IProxyBeanType[] parmTypes){
		return ((REMStandardBeanTypeProxyFactory) fFactoryRegistry.getBeanTypeProxyFactory()).proxyConstants.getMethodProxy(expression, aBeanTypeProxy, methodName, parmTypes);
	}
	
	/**
	 * A helper method to get the field proxy for the expression. This is used by registries and beantype proxies. It will go through
	 * the method cache to make sure we get only one.
	 * @param expression
	 * @param aBeanTypeProxy
	 * @param fieldName
	 * 
	 * @return
	 * 
	 * @since 1.1.0
	 */
	public IProxyField getFieldProxy(IExpression expression, IProxyBeanType aBeanTypeProxy, String fieldName){
		return ((REMStandardBeanTypeProxyFactory) fFactoryRegistry.getBeanTypeProxyFactory()).proxyConstants.getFieldProxy(expression, aBeanTypeProxy, fieldName);
	}
	

	/* (non-Javadoc)
	 * @see org.eclipse.jem.internal.proxy.core.IMethodProxyFactory#getFieldProxy(org.eclipse.jem.internal.proxy.core.IExpression, java.lang.String, java.lang.String)
	 */
	public IProxyField getFieldProxy(IExpression expression, String className, String fieldName) {
		// We are getting the class resolved through the expression. Might as well because it probably will
		// be needed again and this way when the expression is finished it will be resolved for later usage.
		IStandardBeanTypeProxyFactory beanTypeProxyFactory = fFactoryRegistry.getBeanTypeProxyFactory();
		IProxyBeanType beanType = beanTypeProxyFactory.getBeanTypeProxy(expression, className);
		return beanType.getFieldProxy(expression, fieldName);
	}
	
	/* (non-Javadoc)
	 * @see org.eclipse.jem.internal.proxy.core.IMethodProxyFactory#getMethodProxy(org.eclipse.jem.internal.proxy.core.IExpression, java.lang.String, java.lang.String, java.lang.String[])
	 */
	public IProxyMethod getMethodProxy(IExpression expression, String className, String methodName, String[] parameterTypes) {
		// We are getting the class and parmtypes resolved through the expression. Might as well because they probably will
		// be needed again and this way when the expression is finished they will be resolved for later usage.
		IStandardBeanTypeProxyFactory beanTypeProxyFactory = fFactoryRegistry.getBeanTypeProxyFactory();
		IProxyBeanType beanType = beanTypeProxyFactory.getBeanTypeProxy(expression, className);
		IProxyBeanType[] parmTypes = getParameterTypes(expression, parameterTypes, beanTypeProxyFactory);
		return beanType.getMethodProxy(expression, methodName, parmTypes);
	}
	
	/**
	 * Helper method for BeanTypes and proxy Beantypes. So they don't need to have common code to convert string to proxy bean type.
	 * @param expression
	 * @param classType
	 * @param methodName
	 * @param parameterTypes
	 * @return
	 * 
	 * @since 1.1.0
	 */
	public IProxyMethod getMethodProxy(IExpression expression, IProxyBeanType classType, String methodName, String[] parameterTypes) {
		// We are getting the class and parmtypes resolved through the expression. Might as well because they probably will
		// be needed again and this way when the expression is finished they will be resolved for later usage.
		IStandardBeanTypeProxyFactory beanTypeProxyFactory = fFactoryRegistry.getBeanTypeProxyFactory();
		IProxyBeanType[] parmTypes = getParameterTypes(expression, parameterTypes, beanTypeProxyFactory);
		return classType.getMethodProxy(expression, methodName, parmTypes);
	}

	/**
	 * @param expression
	 * @param parameterTypes
	 * @param beanTypeProxyFactory
	 * @return
	 * 
	 * @since 1.1.0
	 */
	protected IProxyBeanType[] getParameterTypes(IExpression expression, String[] parameterTypes, IStandardBeanTypeProxyFactory beanTypeProxyFactory) {
		IProxyBeanType[] parmTypes;
		if (parameterTypes == null || parameterTypes.length == 0)
			parmTypes = null;
		else {
			parmTypes = new IProxyBeanType[parameterTypes.length];
			for (int i = 0; i < parameterTypes.length; i++) {
				parmTypes[i] = beanTypeProxyFactory.getBeanTypeProxy(expression, parameterTypes[i]);
			}
		}
		return parmTypes;
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
	 * This does not go through the method cache. It goes direct to the remote vm.
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
	 * <p>
	 * This does not go through the method cache. It goes direct to the remote vm.
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

	/*
	 * Terminate the factory. Don't need to do anything because of the proxies being held onto are registered any will be cleaned up themselves.
	 */
	public void terminateFactory(boolean wait) {
	}

}
