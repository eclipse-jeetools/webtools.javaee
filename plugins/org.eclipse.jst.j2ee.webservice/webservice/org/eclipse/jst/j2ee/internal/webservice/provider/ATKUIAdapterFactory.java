/***************************************************************************************************
 * Copyright (c) 2003, 2004 IBM Corporation and others. All rights reserved. This program and the
 * accompanying materials are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors: IBM Corporation - initial API and implementation
 **************************************************************************************************/
package org.eclipse.jst.j2ee.internal.webservice.provider;

import org.eclipse.emf.common.notify.Adapter;
import org.eclipse.emf.common.notify.AdapterFactory;
import org.eclipse.emf.common.notify.Notifier;
import org.eclipse.emf.common.notify.impl.AdapterFactoryImpl;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.edit.provider.INotifyChangedListener;
import org.eclipse.jst.j2ee.internal.webservice.adapter.AdapterViewerItem;


public class ATKUIAdapterFactory extends AdapterFactoryImpl {
	private AdapterFactory[] adapterFactories_;

	public ATKUIAdapterFactory() {
		adapterFactories_ = new AdapterFactory[4];
		adapterFactories_[0] = new WsddItemProviderAdapterFactory();
		adapterFactories_[1] = new ATKUIWscddAdapterFactory();
		adapterFactories_[2] = new ATKUIWscommonAdapterFactory();
		adapterFactories_[3] = new ATKUICommonAdapterFactory();

	}

	public Object adapt(Object target, Object type) {

		if (target instanceof Notifier) {
			for (int i = 0; i < adapterFactories_.length; i++) {
				Object object = adapterFactories_[i].adapt(target, type);
				if (object != null) {
					return object;
				}
			}
			return null;
		}
		return resolve(target, type);
	}

	public Adapter adapt(Notifier target, Object type) {
		Object object = adapt((Object) target, type);
		if (object instanceof Adapter) {
			return (Adapter) object;
		}
		return null;
	}

	/**
	 * This is called by {@link #adapt(Object,Object) adapt(Object, Object)}for objects that aren't
	 * notifiers.
	 * 
	 * @param an
	 *            arbitrary object to adapt.
	 * @param type
	 *            the key indicating the type of adapter required.
	 * @return the object itself.
	 * @see #adapt(Object,Object)
	 */
	protected Object resolve(Object object, Object type) {
		if (object instanceof EList)
			return resolveEList((EList) object, type);
		else if (object instanceof AdapterViewerItem)
			return resolveAdapterViewerItem((AdapterViewerItem) object, type);
		else
			return null;
	}

	private Object resolveEList(EList eList, Object type) {
		if (eList.size() > 0)
			return adapt(eList.get(0), type);
		return null;
	}

	private Object resolveAdapterViewerItem(AdapterViewerItem adapterViewerItem, Object type) {
		return resolveEList(adapterViewerItem.getEObjectList(), type);
	}

	public void addListener(INotifyChangedListener listener) {
		((WsddItemProviderAdapterFactory) adapterFactories_[0]).addListener(listener);
		((ATKUIWscddAdapterFactory) adapterFactories_[1]).addListener(listener);
		((ATKUIWscommonAdapterFactory) adapterFactories_[2]).addListener(listener);
		((ATKUICommonAdapterFactory) adapterFactories_[3]).addListener(listener);
	}
}