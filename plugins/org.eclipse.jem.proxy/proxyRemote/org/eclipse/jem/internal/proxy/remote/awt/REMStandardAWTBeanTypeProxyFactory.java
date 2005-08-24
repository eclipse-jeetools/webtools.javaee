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
 *  $RCSfile: REMStandardAWTBeanTypeProxyFactory.java,v $
 *  $Revision: 1.5 $  $Date: 2005/08/24 20:39:07 $ 
 */
package org.eclipse.jem.internal.proxy.remote.awt;


import org.eclipse.jem.internal.proxy.core.*;
import org.eclipse.jem.internal.proxy.core.IBeanTypeProxy;
import org.eclipse.jem.internal.proxy.remote.*;

/**
 * BeanType factory standard AWT bean types. This is package protected because it shouldn't be referenced outside the package. It should only be
 * accessed through the interface.
 */
class REMStandardAWTBeanTypeProxyFactory implements IREMBeanTypeProxyFactory {

	static final String BEAN_TYPE_FACTORY_KEY = "java.awt"; //$NON-NLS-1$

	protected final REMProxyFactoryRegistry fFactoryRegistry;

	REMStandardAWTBeanTypeProxyFactory(REMProxyFactoryRegistry aRegistry) {
		fFactoryRegistry = aRegistry;
		fFactoryRegistry.registerBeanTypeProxyFactory(BEAN_TYPE_FACTORY_KEY, this);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.jem.internal.proxy.remote.IREMBeanTypeProxyFactory#getExtensionBeanTypeProxy(java.lang.String)
	 */
	public IREMBeanTypeProxy getExtensionBeanTypeProxy(String className) {
		return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.jem.internal.proxy.remote.IREMBeanTypeProxyFactory#getExtensionBeanTypeProxy(java.lang.String, java.lang.Integer,
	 *      org.eclipse.jem.internal.proxy.core.IBeanTypeProxy)
	 */
	public IREMBeanTypeProxy getExtensionBeanTypeProxy(String className, Integer classID, IBeanTypeProxy superType) {

		if ("java.awt.Dimension".equals(className)) //$NON-NLS-1$
			return new REMDimensionBeanTypeProxy(fFactoryRegistry, classID, className, superType);
		else if ("java.awt.Point".equals(className)) //$NON-NLS-1$
			return new REMPointBeanTypeProxy(fFactoryRegistry, classID, className, superType);
		else if ("java.awt.Rectangle".equals(className)) //$NON-NLS-1$
			return new REMRectangleBeanTypeProxy(fFactoryRegistry, classID, className, superType);
		else
			return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.jem.internal.proxy.remote.IREMBeanTypeProxyFactory#getExtensionBeanTypeProxy(java.lang.String,
	 *      org.eclipse.jem.internal.proxy.core.IExpression)
	 */
	public IProxyBeanType getExtensionBeanTypeProxy(String typeName, IExpression expression) {
		return getExtensionBeanTypeProxy(typeName);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.jem.internal.proxy.core.IBeanProxyFactory#terminateFactory(boolean)
	 */
	public void terminateFactory(boolean wait) {
	}

}
