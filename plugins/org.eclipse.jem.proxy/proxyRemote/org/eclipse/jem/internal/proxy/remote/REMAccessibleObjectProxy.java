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
 *  $RCSfile: REMAccessibleObjectProxy.java,v $
 *  $Revision: 1.1 $  $Date: 2004/01/12 21:44:26 $ 
 */
package org.eclipse.jem.internal.proxy.remote;

import org.eclipse.jem.internal.proxy.core.*;
import org.eclipse.jem.internal.proxy.core.IAccessibleObjectProxy;
import org.eclipse.jem.internal.proxy.core.ThrowableProxy;
 
/**
 * AccessibleObject Proxy.
 * 
 * @since 1.0.0
 */
public class REMAccessibleObjectProxy extends REMAbstractBeanProxy implements IAccessibleObjectProxy {

	protected REMAccessibleObjectProxy(REMProxyFactoryRegistry aRegistry, Integer anID) {
		super(aRegistry, anID);
	}

	/* (non-Javadoc)
	 * @see org.eclipse.jem.internal.proxy.core.IAccessibleObjectProxy#isAccessible()
	 */
	public boolean isAccessible() throws ThrowableProxy {
		return ((IBooleanBeanProxy) REMStandardBeanProxyConstants.getConstants(fFactory).getAccessibleIsAccessible().invoke(this)).booleanValue();
	}

	/* (non-Javadoc)
	 * @see org.eclipse.jem.internal.proxy.core.IAccessibleObjectProxy#setAccessible(boolean)
	 */
	public void setAccessible(boolean flag) throws ThrowableProxy {
		REMStandardBeanProxyConstants.getConstants(fFactory).getAccessibleSetAccessible().invoke(this, fFactory.getBeanProxyFactory().createBeanProxyWith(flag));
	}

	/* (non-Javadoc)
	 * @see org.eclipse.jem.internal.proxy.core.IBeanProxy#getTypeProxy()
	 */
	public IBeanTypeProxy getTypeProxy() {
		return ((REMMethodProxyFactory) fFactory.getMethodProxyFactory()).accessibleType;
	}

}
