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
 *  $RCSfile: CollectionBeanProxyWrapper.java,v $
 *  $Revision: 1.2 $  $Date: 2004/02/20 00:44:05 $ 
 */

import org.eclipse.core.runtime.*;
/**
 * This is a wrapper for an java.util.Collection proxy.
 * It provides the collection methods to interface to
 * the proxy.
 */

public class CollectionBeanProxyWrapper {
	protected final IBeanProxy fCollection;
	protected final JavaStandardBeanProxyConstants fConstants;
	
	/**
	 * Construct with the collection.
	 */
	public CollectionBeanProxyWrapper(IBeanProxy aCollectionProxy) {
		if (!aCollectionProxy.getTypeProxy().isKindOf(aCollectionProxy.getProxyFactoryRegistry().getBeanTypeProxyFactory().getBeanTypeProxy("java.util.Collection"))) //$NON-NLS-1$
			throw new ClassCastException(java.text.MessageFormat.format(ProxyMessages.getString(ProxyMessages.CLASSCAST_INCORRECTTYPE), new Object[] {aCollectionProxy.getTypeProxy().getTypeName(), "java.util.Collection"})); //$NON-NLS-1$
		else
			fCollection = aCollectionProxy;
			
		fConstants = JavaStandardBeanProxyConstants.getConstants(aCollectionProxy.getProxyFactoryRegistry());
	}
	
	/**
	 * Answer the collection proxy that this is wrappering.
	 */
	public IBeanProxy getBeanProxy() {
		return fCollection;
	}

	/**
	 * equals - Pass it on to the proxy to handle this.
	 */
	public boolean equals(Object object) {
		return fCollection.equals(object);
	}
	
	/**
	 * hashCode - Pass it on to the proxy to handle this.
	 */
	public int hashCode() {
		return fCollection.hashCode();
	}
	
	/**
	 * Collection accessors
	 */
	public boolean add(IBeanProxy object) throws ThrowableProxy {
		return ((IBooleanBeanProxy) fConstants.getCollectionAdd().invoke(fCollection, object)).booleanValue();
	}
	public boolean addAll(IBeanProxy collection) throws ThrowableProxy {
		return ((IBooleanBeanProxy) fConstants.getCollectionAddAll().invoke(fCollection, collection)).booleanValue();
	}
	public void clear() throws ThrowableProxy {
		fConstants.getCollectionClear().invoke(fCollection);
	}
	public boolean contains(IBeanProxy object) {
		try {
			return ((IBooleanBeanProxy) fConstants.getCollectionContains().invoke(fCollection, object)).booleanValue();
		} catch (ThrowableProxy e) {
			// This shouldn't occur, so just log it.
			ProxyPlugin.getPlugin().getLogger().log(new Status(IStatus.ERROR, ProxyPlugin.getPlugin().getDescriptor().getUniqueIdentifier(), 0, ProxyMessages.getString(ProxyMessages.UNEXPECTED_EXCEPTION), e));
			return false;
		}
	}
	public boolean containsAll(IBeanProxy collection) {
		try {
			return ((IBooleanBeanProxy) fConstants.getCollectionContainsAll().invoke(fCollection, collection)).booleanValue();
		} catch (ThrowableProxy e) {
			// This shouldn't occur, so just log it.
			ProxyPlugin.getPlugin().getLogger().log(new Status(IStatus.ERROR, ProxyPlugin.getPlugin().getDescriptor().getUniqueIdentifier(), 0, ProxyMessages.getString(ProxyMessages.UNEXPECTED_EXCEPTION), e));
			return false;
		}			
	}		
	public boolean isEmpty() {
		try {
			return ((IBooleanBeanProxy) fConstants.getCollectionIsEmpty().invoke(fCollection)).booleanValue();
		} catch (ThrowableProxy e) {
			// This shouldn't occur, so just log it.
			ProxyPlugin.getPlugin().getLogger().log(new Status(IStatus.ERROR, ProxyPlugin.getPlugin().getDescriptor().getUniqueIdentifier(), 0, ProxyMessages.getString(ProxyMessages.UNEXPECTED_EXCEPTION), e));
			return true;
		}			
	}		
	public IteratorBeanProxyWrapper iterator() {
		try {
			IBeanProxy itr = fConstants.getCollectionIterator().invoke(fCollection);
			if (itr != null)
				return new IteratorBeanProxyWrapper(itr);
			else
				return null;
		} catch (ThrowableProxy e) {
			// This shouldn't occur, so just log it.
			ProxyPlugin.getPlugin().getLogger().log(new Status(IStatus.ERROR, ProxyPlugin.getPlugin().getDescriptor().getUniqueIdentifier(), 0, ProxyMessages.getString(ProxyMessages.UNEXPECTED_EXCEPTION), e));
			return null;
		}			
	}
	public boolean remove(IBeanProxy object) throws ThrowableProxy {
		return ((IBooleanBeanProxy) fConstants.getCollectionRemove().invoke(fCollection, object)).booleanValue();
	}
	public boolean removeAll(IBeanProxy collection) throws ThrowableProxy {
		return ((IBooleanBeanProxy) fConstants.getCollectionRemoveAll().invoke(fCollection, collection)).booleanValue();
	}		
	public boolean retainAll(IBeanProxy collection) throws ThrowableProxy {
		return ((IBooleanBeanProxy) fConstants.getCollectionRetainAll().invoke(fCollection, collection)).booleanValue();
	}
	public int size() {
		try {
			return ((IIntegerBeanProxy) fConstants.getCollectionSize().invoke(fCollection)).intValue();
		} catch (ThrowableProxy e) {
			// This shouldn't occur, so just log it.
			ProxyPlugin.getPlugin().getLogger().log(new Status(IStatus.ERROR, ProxyPlugin.getPlugin().getDescriptor().getUniqueIdentifier(), 0, ProxyMessages.getString(ProxyMessages.UNEXPECTED_EXCEPTION), e));
			return 0;
		}			
	}	
	public IArrayBeanProxy toArray() throws ThrowableProxy {
		return (IArrayBeanProxy) fConstants.getCollectionToArray().invoke(fCollection);
	}
	public IArrayBeanProxy toArray(IArrayBeanProxy array) throws ThrowableProxy {
		return (IArrayBeanProxy) fConstants.getCollectionToArrayWithArray().invoke(fCollection, array);
	}	
}