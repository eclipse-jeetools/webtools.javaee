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
package org.eclipse.jem.internal.beaninfo.adapters;
/*
 *  $RCSfile: BeaninfoAdapterFactory.java,v $
 *  $Revision: 1.4.2.1 $  $Date: 2004/06/24 18:19:38 $ 
 */
import java.lang.ref.ReferenceQueue;
import java.lang.ref.WeakReference;
import java.util.*;

import org.eclipse.core.resources.IProject;
import org.eclipse.emf.common.notify.Adapter;
import org.eclipse.emf.common.notify.Notifier;
import org.eclipse.emf.common.notify.impl.AdapterFactoryImpl;

import org.eclipse.jem.internal.beaninfo.core.*;
import org.eclipse.jem.internal.java.beaninfo.IIntrospectionAdapter;
import org.eclipse.jem.internal.proxy.core.ProxyFactoryRegistry;
import org.eclipse.jem.java.ArrayType;
/**
 * BeaninfoAdapterFactory - the factory for 
 * beaninfo introspection to populate the Java Model.
 * Creation date: (11/1/2000 11:52:55 AM)
 * @author: Administrator
 */
public class BeaninfoAdapterFactory extends AdapterFactoryImpl {
	protected IBeaninfoSupplier fInfoSupplier;

	// Maintain a mapping of the source objects to the adaptors which have
	// introspected from them.  This allows a close operation to force those
	// adapters to clear out the data. It also allows for marking an adapter as stale
	// so that next time it introspects it will re-get the data.
	// 
	// This is a WeakReference so that we don't hold onto adapters that were
	// explicitly removed in other ways.
	private Map fIntrospected = new HashMap();	// NOTE: This is to be accessed only under sync(this)!
	private ReferenceQueue fRefQ = new ReferenceQueue();
	private static class WeakValue extends WeakReference {
		private Object key;
		public WeakValue(Object aKey, Object value, ReferenceQueue que) {
			super(value, que);
			key = aKey;
		}

		public Object getKey() {
			return key;
		}
	};

	public BeaninfoAdapterFactory(IBeaninfoSupplier supplier) {
		fInfoSupplier = supplier;
	}
	
	public Adapter createAdapter(Notifier target, Object type) {
		if (type == IIntrospectionAdapter.ADAPTER_KEY) {
			return !(target instanceof ArrayType) ? new BeaninfoClassAdapter(this) : null;	// Array types don't have beaninfo adapters.
		} else
			return new BeaninfoSuperAdapter();
	}
	
	/**
	 * @see org.eclipse.emf.common.notify.AdapterFactory#isFactoryForType(Object)
	 */
	public boolean isFactoryForType(Object type) {
		return IIntrospectionAdapter.ADAPTER_KEY == type || BeaninfoSuperAdapter.ADAPTER_KEY == type;
	}	

	public ProxyFactoryRegistry getRegistry() {
		return fInfoSupplier.getRegistry();
	}
	
	public boolean isRegistryCreated() {
		return fInfoSupplier.isRegistryCreated();
	}

	public ProxyFactoryRegistry recycleRegistry() {
		markAllStale(); // At this point in time we need to mark them all stale because we are recycling. MarkAllStale also closes the registry.
		return getRegistry();
	}
	
	public IProject getProject() {
		return fInfoSupplier.getProject();
	}

	/**
	 * Close ALL adapters. Also remove the adapters so that they
	 * are not being held onto. This means we are closing the project or removing the nature.
	 */
	public void closeAll(boolean clearResults) {
		processQueue();
		synchronized (this) {
			// We are going to be removing all of them, so just set introspected to an empty one
			// and use the real one. This way we won't get concurrent modifications as we remove
			// it from the notifier removeAdapter.
			Map intr = fIntrospected;
			fIntrospected = Collections.EMPTY_MAP;	// Since we are closing we can keep the unmodifiable one here.
			Iterator i = intr.values().iterator();
			while (i.hasNext()) {
				BeaninfoClassAdapter a = (BeaninfoClassAdapter) ((WeakValue) i.next()).get();
				if (a != null) {
					if (clearResults)
						a.clearIntrospection();
					Notifier notifier = a.getTarget();
					if (notifier != null)
						notifier.eAdapters().remove(a);
				}
			}
		}
	}

	/**
	 * Mark ALL adapters as stale. This occurs because we've recycled the registry.
	 */
	public void markAllStale() {
		ProxyFactoryRegistry fact = isRegistryCreated() ? getRegistry() : null;
		processQueue();
		synchronized (this) {
			Iterator i = fIntrospected.values().iterator();
			while (i.hasNext()) {
				BeaninfoClassAdapter a = (BeaninfoClassAdapter) ((WeakValue) i.next()).get();
				if (a != null)
					a.markStaleFactory(fact);
			}
			fInfoSupplier.closeRegistry();	// Get rid of the registry now since it is not needed. This way we won't accidentily hold onto it when not needed.
		}
	}
	/**
	 * Mark the introspection as stale for a source object. Also clear results if told to.
	 * @param sourceName Fully qualified source name, use type for reflection, i.e. "a.b.c.Class1$InnerClass"
	 * @param clearResults clear out the results. If false, they will be reused if possible on recycle.
	 */
	public void markStaleIntrospection(String sourceName, boolean clearResults) {
		processQueue();
		synchronized (this) {
			WeakValue ref = (WeakValue) fIntrospected.get(sourceName);
			if (ref != null) {
				BeaninfoClassAdapter a = (BeaninfoClassAdapter) ref.get();
				if (a != null) {
					if (clearResults)
						a.clearIntrospection();
					a.markStaleFactory(isRegistryCreated() ? getRegistry() : null); // Mark it stale with the current registry.
				}
			}
		}
	}
	
	public void markStaleIntrospectionPlusInner(String sourceName, boolean clearResults) {
		processQueue();
		String sourceNameForInner = sourceName + '$';
		synchronized (this) {
			Iterator itr = fIntrospected.entrySet().iterator();
			while (itr.hasNext()) {
				Map.Entry entry = (Map.Entry) itr.next();
				String entryName = (String) entry.getKey();
				if (entryName.equals(sourceName) || entryName.startsWith(sourceNameForInner)) {
					// It is the item or one of its inner classes.
					WeakValue ref = (WeakValue) entry.getValue();
					BeaninfoClassAdapter a = (BeaninfoClassAdapter) ref.get();					
					if (a != null) {
						if (clearResults)
							a.clearIntrospection();
						a.markStaleFactory(isRegistryCreated() ? getRegistry() : null); // Mark it stale with the current registry.
					}
				}
			}
		}
	}	

	/**
	 * Register an adapter for introspection.
	 * @param sourceName Fully qualified source name, use type for reflection, i.e. "a.b.c.Class1$InnerClass"
	 * @param adapter The adapter to register
	 */
	public void registerIntrospection(String sourceName, BeaninfoClassAdapter adapter) {
		// Create it as a weak reference so that it doesn't hold onto the adapter if it is ever removed
		// and thrown away (or the MOF resource itself is thrown away).
		processQueue();
		synchronized (this) {
			fIntrospected.put(sourceName, new WeakValue(sourceName, adapter, fRefQ));
		}
	}
	
	/**
	 * Remove adapter. This happens in the case that adapter is being removed and
	 * we want to remove it from our list. This is an internal API only for use by
	 * the adapter itself.
	 */
	public synchronized void removeAdapter(BeaninfoClassAdapter a) {
		fIntrospected.remove(a.getJavaClass().getQualifiedNameForReflection());
	}

	private synchronized void processQueue() {
		WeakValue wv;
		while ((wv = (WeakValue) fRefQ.poll()) != null) {
			fIntrospected.remove(wv.getKey());
		}
	}
}
