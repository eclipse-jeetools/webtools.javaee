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
 *  $RCSfile: ListBeanProxyWrapper.java,v $
 *  $Revision: 1.3 $  $Date: 2004/05/24 23:23:36 $ 
 */


import java.text.MessageFormat;

import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
/**
 * This is a wrapper for an java.util.Collection proxy.
 * It provides the collection methods to interface to
 * the proxy.
 */

public class ListBeanProxyWrapper extends CollectionBeanProxyWrapper {
	/**
	 * Construct with the List.
	 */
	public ListBeanProxyWrapper(IBeanProxy aListProxy) {
		super(aListProxy);
		
		if (!aListProxy.getTypeProxy().isKindOf(aListProxy.getProxyFactoryRegistry().getBeanTypeProxyFactory().getBeanTypeProxy("java.util.List"))) //$NON-NLS-1$
			throw new ClassCastException(MessageFormat.format(ProxyMessages.getString(ProxyMessages.CLASSCAST_INCORRECTTYPE), new Object[] {aListProxy.getTypeProxy().getTypeName(), "java.util.List"})); //$NON-NLS-1$
	}
	
	
	/**
	 * List accessors
	 */
	public void add(int index, IBeanProxy object) throws ThrowableProxy {
		fConstants.getListAddWithInt().invoke(fCollection, new IBeanProxy[] {fCollection.getProxyFactoryRegistry().getBeanProxyFactory().createBeanProxyWith(index), object});
	}
	public boolean addAll(int index, IBeanProxy aCollection) throws ThrowableProxy {
		return ((IBooleanBeanProxy) fConstants.getListAddAllWithInt().invoke(fCollection, new IBeanProxy[] {fCollection.getProxyFactoryRegistry().getBeanProxyFactory().createBeanProxyWith(index), aCollection})).booleanValue();
	}
	public IBeanProxy get(int index) throws ThrowableProxy {
		return fConstants.getListGet().invoke(fCollection, fCollection.getProxyFactoryRegistry().getBeanProxyFactory().createBeanProxyWith(index));
	}
	public int indexOf(IBeanProxy object) {
		try {
			return ((IIntegerBeanProxy) fConstants.getListIndexOf().invoke(fCollection, object)).intValue();
		} catch (ThrowableProxy e) {
			// This shouldn't occur, so just log it.
			ProxyPlugin.getPlugin().getLogger().log(new Status(IStatus.ERROR, ProxyPlugin.getPlugin().getBundle().getSymbolicName(), 0, ProxyMessages.getString(ProxyMessages.UNEXPECTED_EXCEPTION), e));
			return -1;
		}			
	}
	public int lastIndexOf(IBeanProxy object) {
		try {
			return ((IIntegerBeanProxy) fConstants.getListLastIndexOf().invoke(fCollection, object)).intValue();
		} catch (ThrowableProxy e) {
			// This shouldn't occur, so just log it.
			ProxyPlugin.getPlugin().getLogger().log(new Status(IStatus.ERROR, ProxyPlugin.getPlugin().getBundle().getSymbolicName(), 0, ProxyMessages.getString(ProxyMessages.UNEXPECTED_EXCEPTION), e));
			return -1;
		}			
	}
	public ListIteratorBeanProxyWrapper listIterator() {
		try {
			IBeanProxy itr = fConstants.getListListIterator().invoke(fCollection);
			if (itr != null)
				return new ListIteratorBeanProxyWrapper(itr);
			else
				return null;
		} catch (ThrowableProxy e) {
			// This shouldn't occur, so just log it.
			ProxyPlugin.getPlugin().getLogger().log(new Status(IStatus.ERROR, ProxyPlugin.getPlugin().getBundle().getSymbolicName(), 0, ProxyMessages.getString(ProxyMessages.UNEXPECTED_EXCEPTION), e));
			return null;
		}			
	}
	public ListIteratorBeanProxyWrapper listIterator(int index) {
		try {
			IBeanProxy itr = fConstants.getListListIteratorWithInt().invoke(fCollection, fCollection.getProxyFactoryRegistry().getBeanProxyFactory().createBeanProxyWith(index));
			if (itr != null)
				return new ListIteratorBeanProxyWrapper(itr);
			else
				return null;
		} catch (ThrowableProxy e) {
			// This shouldn't occur, so just log it.
			ProxyPlugin.getPlugin().getLogger().log(new Status(IStatus.ERROR, ProxyPlugin.getPlugin().getBundle().getSymbolicName(), 0, ProxyMessages.getString(ProxyMessages.UNEXPECTED_EXCEPTION), e));
			return null;
		}			
	}
	public IBeanProxy remove(int index, IBeanProxy object) throws ThrowableProxy {
		return fConstants.getListSet().invoke(fCollection, fCollection.getProxyFactoryRegistry().getBeanProxyFactory().createBeanProxyWith(index));
	}
	public IBeanProxy set(int index, IBeanProxy object) throws ThrowableProxy {
		return fConstants.getListRemoveInt().invoke(fCollection, new IBeanProxy[] {fCollection.getProxyFactoryRegistry().getBeanProxyFactory().createBeanProxyWith(index), object});
	}											
	public ListBeanProxyWrapper subList(int fromIndex, int toIndex) throws ThrowableProxy {
		IBeanProxy list = fConstants.getListSubList().invoke(fCollection, new IBeanProxy[] {fCollection.getProxyFactoryRegistry().getBeanProxyFactory().createBeanProxyWith(fromIndex), fCollection.getProxyFactoryRegistry().getBeanProxyFactory().createBeanProxyWith(toIndex)});
		if (list != null)
			return new ListBeanProxyWrapper(list);
		else
			return null;		
	}
												
		
}