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
 *  $RCSfile: IDEAccessibleObjectProxy.java,v $
 *  $Revision: 1.1 $  $Date: 2004/01/12 21:44:26 $ 
 */
package org.eclipse.jem.internal.proxy.ide;

import java.lang.reflect.AccessibleObject;

import org.eclipse.jem.internal.proxy.core.*;
import org.eclipse.jem.internal.proxy.core.IAccessibleObjectProxy;
import org.eclipse.jem.internal.proxy.core.ProxyFactoryRegistry;
 
/**
 * 
 * @since 1.0.0
 */
public class IDEAccessibleObjectProxy extends IDEBeanProxy implements IAccessibleObjectProxy {

	protected IDEAccessibleObjectProxy(ProxyFactoryRegistry aProxyFactoryRegistry) {
		super(aProxyFactoryRegistry);
	}

	protected IDEAccessibleObjectProxy(ProxyFactoryRegistry aProxyFactoryRegistry, Object anObject) {
		super(aProxyFactoryRegistry, anObject);
	}

	/* (non-Javadoc)
	 * @see org.eclipse.jem.internal.proxy.core.IAccessibleObjectProxy#isAccessible()
	 */
	public boolean isAccessible() throws ThrowableProxy {
		return ((AccessibleObject) getBean()).isAccessible();
	}

	/* (non-Javadoc)
	 * @see org.eclipse.jem.internal.proxy.core.IAccessibleObjectProxy#setAccessible(boolean)
	 */
	public void setAccessible(boolean flag) throws ThrowableProxy {
		((AccessibleObject) getBean()).setAccessible(flag);
	}

	/* (non-Javadoc)
	 * @see org.eclipse.jem.internal.proxy.core.IBeanProxy#getTypeProxy()
	 */
	public IBeanTypeProxy getTypeProxy() {
		return ((IDEMethodProxyFactory) fProxyFactoryRegistry.getMethodProxyFactory()).accessibleType;
	}

}
