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
package org.eclipse.jem.internal.proxy.core;
/*
 *  $RCSfile: EnumerationBeanProxyWrapper.java,v $
 *  $Revision: 1.6 $  $Date: 2005/02/08 22:10:11 $ 
 */


import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
/**
 * This is a wrapper for an java.util.Enumeration proxy.
 * It provides the Enumeration methods to interface to
 * the proxy.
 */

public class EnumerationBeanProxyWrapper {
	protected final IBeanProxy fEnumeration;
	protected final JavaStandardBeanProxyConstants fConstants;
	
	/**
	 * Construct with the collection.
	 */
	public EnumerationBeanProxyWrapper(IBeanProxy anEnumerationProxy) {
		
		fEnumeration = anEnumerationProxy;
			
		fConstants = JavaStandardBeanProxyConstants.getConstants(anEnumerationProxy.getProxyFactoryRegistry());
	}
	
	/**
	 * Answer the iterator proxy that this is wrappering.
	 */
	public IBeanProxy getBeanProxy() {
		return fEnumeration;
	}

	/**
	 * equals - Pass it on to the proxy to handle this.
	 */
	public boolean equals(Object object) {
		return fEnumeration.equals(object);
	}
	
	/**
	 * hashCode - Pass it on to the proxy to handle this.
	 */
	public int hashCode() {
		return fEnumeration.hashCode();
	}
	
	/**
	 * Enumeration accessors
	 */
	public boolean hasMoreElements() {
		try {
			return ((IBooleanBeanProxy) fConstants.getEnumerationHasMoreElements().invoke(fEnumeration)).booleanValue();
		} catch (ThrowableProxy e) {
			// This shouldn't occur, so just log it.
			ProxyPlugin.getPlugin().getLogger().log(new Status(IStatus.ERROR, ProxyPlugin.getPlugin().getBundle().getSymbolicName(), 0, ProxyMessages.getString(ProxyMessages.UNEXPECTED_EXCEPTION), e));
			return false;
		}			
	}
	public IBeanProxy nextElement() throws ThrowableProxy {
		return fConstants.getEnumerationNextElement().invoke(fEnumeration);
	}	
	
}
