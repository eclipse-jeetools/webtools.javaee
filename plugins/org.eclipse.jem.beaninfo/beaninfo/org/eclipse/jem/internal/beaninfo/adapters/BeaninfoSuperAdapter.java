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
 *  $RCSfile: BeaninfoSuperAdapter.java,v $
 *  $Revision: 1.2.4.1 $  $Date: 2004/06/24 18:19:38 $ 
 */

import java.util.Collection;
import java.util.Iterator;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.Notifier;
import org.eclipse.emf.common.notify.impl.AdapterImpl;
import org.eclipse.emf.ecore.impl.ESuperAdapter;
import org.eclipse.emf.ecore.util.EcoreUtil;

import org.eclipse.jem.java.JavaClass;
import org.eclipse.jem.java.JavaRefPackage;
import org.eclipse.jem.java.impl.JavaClassImpl;

/**
 * Used to know when events or any of the super classes change so
 * that we can re-construct our all events list next time we need it.
 * @version 	1.0
 * @author
 */
public class BeaninfoSuperAdapter extends AdapterImpl {

	public static final Class ADAPTER_KEY = BeaninfoSuperAdapter.class;

	private boolean allEventsCollectionModified = true;
	private boolean allPropertiesCollectionModified = true;

	public BeaninfoSuperAdapter() {
		super();
	}

	public void notifyChanged(Notification msg) {
		if (msg.getEventType() != Notification.REMOVING_ADAPTER)
			setFlags(msg.getFeatureID(JavaClass.class));
	}

	public boolean isAllEventsCollectionModified() {
		return allEventsCollectionModified;
	}

	public void setAllEventsCollectionModified(boolean newEventsModified) {
		allEventsCollectionModified = newEventsModified;
	}

	public void setAllPropertiesCollectionModified(boolean allPropertiesCollectionModified) {
		this.allPropertiesCollectionModified = allPropertiesCollectionModified;
	}

	public boolean isAllPropertiesCollectionModified() {
		return allPropertiesCollectionModified;
	}
	
	public Collection getSubclasses() {
		// Get them from the ESuperAdapter. Easiest to do.
		ESuperAdapter ea = ((JavaClassImpl) getTarget()).getESuperAdapter();
		return ea.getSubclasses();
	}

	public boolean isAdapterForType(Object type) {
		return ADAPTER_KEY.equals(type);
	}

	void setFlags(int featureId) {
		switch (featureId) {
			case JavaRefPackage.JAVA_CLASS__EVENTS :
				setAllEventsCollectionModified(true);
				break;

			case JavaRefPackage.JAVA_CLASS__ESUPER_TYPES:
				setAllEventsCollectionModified(true);
				setAllPropertiesCollectionModified(true);
				break;				
				
			case JavaRefPackage.JAVA_CLASS__ESTRUCTURAL_FEATURES:
				setAllPropertiesCollectionModified(true);
				break;
				
			default :
				break;
		}
		Iterator i = getSubclasses().iterator();
		while (i.hasNext()) {
			Notifier n = (Notifier) i.next();
			BeaninfoSuperAdapter a = (BeaninfoSuperAdapter) EcoreUtil.getExistingAdapter(n, ADAPTER_KEY);
			if (a != null)
				a.setFlags(featureId);
		}
	}

}
