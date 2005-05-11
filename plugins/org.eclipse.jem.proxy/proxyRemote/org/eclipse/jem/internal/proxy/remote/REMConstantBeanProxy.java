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
package org.eclipse.jem.internal.proxy.remote;
/*
 *  $RCSfile: REMConstantBeanProxy.java,v $
 *  $Revision: 1.5 $  $Date: 2005/05/11 19:01:12 $ 
 */


import org.eclipse.jem.internal.proxy.common.remote.Commands;
import org.eclipse.jem.internal.proxy.core.IBeanProxy;
import org.eclipse.jem.internal.proxy.core.ProxyFactoryRegistry;
/**
 * This is a base for bean proxies that are constants and
 * do not have an object on the remote VM side. They are
 * shipped back and forth by value.
 */

public abstract class REMConstantBeanProxy implements IREMConstantBeanProxy {
	protected final REMProxyFactoryRegistry fFactory;
	private final static Integer ID = new Integer(Commands.NOT_AN_ID);
	
	protected REMConstantBeanProxy(REMProxyFactoryRegistry aFactory) {
		fFactory = aFactory;
	}
	/**
	 * Return the registry this proxy was created with.
	 */
	public ProxyFactoryRegistry getProxyFactoryRegistry() {
		return fFactory;
	}
	
	/**
	 * equals: Equal if:
	 *         1) This proxy == (identity) to the other object
	 *         2) Else if other is an IBeanProxy and not a constant one, then if
	 *            equals on the server.
	 *         3) If this is a constant proxy and the other is too or is a constant
	 *            value (e.g. IStringBeanProxy.equals(String), then true if values are equals.
	 * This is abstract because each type needs to do a unique test for equality.
	 */
	public abstract boolean equals(Object anObject);
	
	/* (non-Javadoc)
	 * @see org.eclipse.jem.internal.proxy.core.IBeanProxy#sameAs(org.eclipse.jem.internal.proxy.core.IBeanProxy)
	 */
	public boolean sameAs(IBeanProxy aBeanProxy) {
		// We can't be assured that there are not non-identical constants that are actually identical.
		// This is because for constants we do not know the id of the original. That is not sent across from the vm.		
		return this == aBeanProxy;	
	}	
	
	/**
	 * Return the ID of this proxy.
	 * Constants have no id's, so we return -1.
	 */
	public Integer getID() {
		return ID;
	}
	
	/**
	 * isValid. Constant ones are always valid unless the registry is terminated, except if an
	 * override releases resources. In that case the overriding
	 * subclass should also override isValid() to return false
	 * if release() was called.
	 */
	public boolean isValid() {
		return fFactory.isValid();
	}
	
	/**
	 * release. Constant ones don't need to typically be released, however
	 * if an override does do something to release, then it MUST also
	 * override isValid() to return false.
	 */
	public void release() {
	}


	/* (non-Javadoc)
	 * @see org.eclipse.jem.internal.proxy.core.IProxy#isBeanProxy()
	 */
	public final boolean isBeanProxy() {
		return true;
	}
	/* (non-Javadoc)
	 * @see org.eclipse.jem.internal.proxy.core.IProxy#isExpressionProxy()
	 */
	public final boolean isExpressionProxy() {
		return false;
	}
}
