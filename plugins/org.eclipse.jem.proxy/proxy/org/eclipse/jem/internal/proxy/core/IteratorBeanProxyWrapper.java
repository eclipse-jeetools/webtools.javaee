package org.eclipse.jem.internal.proxy.core;
/*******************************************************************************
 * Copyright (c)  2001, 2003 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials 
 * are made available under the terms of the Common Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/cpl-v10.html
 * 
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *******************************************************************************/
/*
 *  $RCSfile: IteratorBeanProxyWrapper.java,v $
 *  $Revision: 1.3 $  $Date: 2004/05/24 23:23:36 $ 
 */


import java.text.MessageFormat;

import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
/**
 * This is a wrapper for an java.util.Iterator proxy.
 * It provides the iterator methods to interface to
 * the proxy.
 */

public class IteratorBeanProxyWrapper {
	protected final IBeanProxy fIterator;
	protected final JavaStandardBeanProxyConstants fConstants;
	
	/**
	 * Construct with the collection.
	 */
	public IteratorBeanProxyWrapper(IBeanProxy anIteratorProxy) {
		if (!anIteratorProxy.getTypeProxy().isKindOf(anIteratorProxy.getProxyFactoryRegistry().getBeanTypeProxyFactory().getBeanTypeProxy("java.util.Iterator"))) //$NON-NLS-1$
			throw new ClassCastException(MessageFormat.format(ProxyMessages.getString(ProxyMessages.CLASSCAST_INCORRECTTYPE), new Object[] {anIteratorProxy.getTypeProxy().getTypeName(), "java.util.Iterator"})); //$NON-NLS-1$
		else
			fIterator = anIteratorProxy;
			
		fConstants = JavaStandardBeanProxyConstants.getConstants(anIteratorProxy.getProxyFactoryRegistry());
	}
	
	/**
	 * Answer the iterator proxy that this is wrappering.
	 */
	public IBeanProxy getBeanProxy() {
		return fIterator;
	}

	/**
	 * equals - Pass it on to the proxy to handle this.
	 */
	public boolean equals(Object object) {
		return fIterator.equals(object);
	}
	
	/**
	 * hashCode - Pass it on to the proxy to handle this.
	 */
	public int hashCode() {
		return fIterator.hashCode();
	}
	
	/**
	 * Iterator accessors
	 */
	public boolean hasNext() {
		try {
			return ((IBooleanBeanProxy) fConstants.getIteratorHasNext().invoke(fIterator)).booleanValue();
		} catch (ThrowableProxy e) {
			// This shouldn't occur, so just log it.
			ProxyPlugin.getPlugin().getLogger().log(new Status(IStatus.ERROR, ProxyPlugin.getPlugin().getBundle().getSymbolicName(), 0, ProxyMessages.getString(ProxyMessages.UNEXPECTED_EXCEPTION), e));
			return false;
		}			
	}
	public IBeanProxy next() throws ThrowableProxy {
		return fConstants.getIteratorNext().invoke(fIterator);
	}	
	public void remove() throws ThrowableProxy {
		fConstants.getIteratorRemove().invoke(fIterator);
	}	
	
}