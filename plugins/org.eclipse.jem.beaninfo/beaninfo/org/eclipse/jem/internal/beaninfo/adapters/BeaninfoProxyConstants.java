/*******************************************************************************
 * Copyright (c)  2001, 2003 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials 
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *******************************************************************************/
/*
 *  $RCSfile: BeaninfoProxyConstants.java,v $
 *  $Revision: 1.5 $  $Date: 2005/05/11 19:01:28 $ 
 */
package org.eclipse.jem.internal.beaninfo.adapters;

import org.eclipse.jem.internal.proxy.core.*;

/**
 * This holds the proxy constants for access in beaninfo. It is created on a per-registry basis and stored in the constants section of the registry so
 * that they can be accessed as needed.
 * <p>
 * To gain access to these constants, use the static accessor method "getConstants()" so that the instance is not created until actually needed.
 * 
 * @since 1.0.0
 */
public final class BeaninfoProxyConstants {

	private static final Object REGISTRY_KEY = new Object();

	private final IMethodProxy introspectProxy;

	private final IMethodProxy getBeanInfoSearchPathProxy;

	private final IMethodProxy setBeanInfoSearchPathProxy;

	private final IMethodProxy sendBeanInfoProxy;

	/**
	 * Get the constants instance for the specified registry.
	 */
	public static BeaninfoProxyConstants getConstants(ProxyFactoryRegistry registry) {
		BeaninfoProxyConstants constants = (BeaninfoProxyConstants) registry.getConstants(REGISTRY_KEY);
		if (constants == null)
			registry.registerConstants(REGISTRY_KEY, constants = new BeaninfoProxyConstants(registry));
		return constants;
	}

	private BeaninfoProxyConstants(ProxyFactoryRegistry registry) {

		IStandardBeanTypeProxyFactory typeFactory = registry.getBeanTypeProxyFactory();

		IBeanTypeProxy introspector = typeFactory.getBeanTypeProxy("java.beans.Introspector"); //$NON-NLS-1$
		getBeanInfoSearchPathProxy = introspector.getMethodProxy("getBeanInfoSearchPath"); //$NON-NLS-1$
		setBeanInfoSearchPathProxy = introspector.getMethodProxy("setBeanInfoSearchPath", "[Ljava.lang.String;"); //$NON-NLS-1$ //$NON-NLS-2$

		IBeanTypeProxy modelingBeaninfo = typeFactory.getBeanTypeProxy("org.eclipse.jem.internal.beaninfo.vm.ModelingBeanInfo");//$NON-NLS-1$
		introspectProxy = modelingBeaninfo.getMethodProxy("introspect", new String[] { "java.lang.Class", "boolean", "int"}); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$ //$NON-NLS-4$

		sendBeanInfoProxy = modelingBeaninfo.getMethodProxy("send"); //$NON-NLS-1$

	}

	/**
	 * Get the sendBeanDecorator method proxy from ModelingBeanInfo.
	 * @return
	 * 
	 * @since 1.1.0
	 */
	public IMethodProxy getSendBeanInfoProxy() {
		return sendBeanInfoProxy;
	}
	
	/**
	 * Get the getBeanInfoSearchPath method proxy from Introspector.
	 * @return
	 * 
	 * @since 1.0.0
	 */
	public IMethodProxy getGetBeanInfoSearchPathProxy() {
		return getBeanInfoSearchPathProxy;
	}

	/**
	 * Get the setBeanInfoSearchPath method proxy from Introspector.
	 * @return
	 * 
	 * @since 1.0.0
	 */
	public IMethodProxy getSetBeanInfoSearchPathProxy() {
		return setBeanInfoSearchPathProxy;
	}

	/**
	 * Get the introspect method proxy from ModelingBeanInfo.
	 * @return
	 * 
	 * @since 1.1.0
	 */
	public IMethodProxy getIntrospectProxy() {
		return introspectProxy;
	}
}