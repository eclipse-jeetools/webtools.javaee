/*******************************************************************************
 * Copyright (c) 2004 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials 
 * are made available under the terms of the Common Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/cpl-v10.html
 * 
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *******************************************************************************/
/*
 *  $RCSfile: IDEClassBeanTypeProxy.java,v $
 *  $Revision: 1.1 $  $Date: 2004/02/03 23:18:36 $ 
 */
package org.eclipse.jem.internal.proxy.ide;

import org.eclipse.jem.internal.proxy.core.IBeanProxy;
 
/**
 * For java.lang.Class type.
 * @since 1.0.0
 */
public class IDEClassBeanTypeProxy extends IDEBeanTypeProxy {

	/**
	 * @param aRegistry
	 * @param aClass
	 * 
	 * @since 1.0.0
	 */
	public IDEClassBeanTypeProxy(IDEProxyFactoryRegistry aRegistry, Class aClass) {
		super(aRegistry, aClass);
	}

	/* (non-Javadoc)
	 * @see org.eclipse.jem.internal.proxy.ide.IDEBeanTypeProxy#newBeanProxy(java.lang.Object)
	 */
	protected IIDEBeanProxy newBeanProxy(Object anObject) {
		return (IIDEBeanProxy) ((IDEStandardBeanTypeProxyFactory) fProxyFactoryRegistry.getBeanTypeProxyFactory()).getBeanTypeProxy((Class) anObject);
	}

	/* (non-Javadoc)
	 * @see org.eclipse.jem.internal.proxy.ide.IDEBeanTypeProxy#newBeanTypeForClass(java.lang.Class)
	 */
	public IDEBeanTypeProxy newBeanTypeForClass(Class type) {
		throw new UnsupportedOperationException();
	}

	/* (non-Javadoc)
	 * @see org.eclipse.jem.internal.proxy.core.IBeanTypeProxy#newInstance()
	 */
	public IBeanProxy newInstance() {
		throw new UnsupportedOperationException();
	}

	/* (non-Javadoc)
	 * @see org.eclipse.jem.internal.proxy.core.IBeanTypeProxy#newInstance(java.lang.String)
	 */
	public IBeanProxy newInstance(String initializationString) {
		throw new UnsupportedOperationException();
	}

}
