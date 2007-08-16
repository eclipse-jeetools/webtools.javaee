/*******************************************************************************
 * Copyright (c) 2001, 2005 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 * IBM Corporation - initial API and implementation
 *******************************************************************************/
package org.eclipse.jst.j2ee.ejb.internal.util;
import org.eclipse.emf.common.notify.Adapter;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.impl.AdapterImpl;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.jst.j2ee.ejb.EJBRelation;
import org.eclipse.jst.j2ee.ejb.internal.impl.EjbFactoryImpl;

public class RelationshipsAttributeMaintenanceAdapter extends AdapterImpl {
	/**
	 * @see org.eclipse.emf.common.notify.impl.AdapterImpl#notifyChanged(Notification)
	 */
	public void notifyChanged(Notification msg) {
		if ((EStructuralFeature)msg.getFeature() == EjbFactoryImpl.getPackage().getRelationships_EjbRelations()) {
			switch (msg.getEventType()) {
				case Notification.ADD :
					addedEJBRelation((EJBRelation) msg.getNewValue(), (EStructuralFeature)msg.getFeature());
					break;
				case Notification.REMOVE :
					removedEJBRelation((EJBRelation) msg.getOldValue(), (EStructuralFeature)msg.getFeature());
					break;
			}
		}
	}
	private void removedEJBRelation(EJBRelation aRelation, EObject sf) {
		Adapter a = getAdapter(aRelation);
		if (a != null) {
			Notification not = new ENotificationImpl((InternalEObject)aRelation, Notification.REMOVE,(EStructuralFeature) sf, aRelation, null, Notification.NO_INDEX);
			a.notifyChanged(not);
		}
	}
	private void addedEJBRelation(EJBRelation aRelation, EObject sf) {
		Adapter a = getAdapter(aRelation);
		if (a != null) {
			Notification not = new ENotificationImpl((InternalEObject)aRelation, Notification.ADD,(EStructuralFeature) sf, null, aRelation, Notification.NO_INDEX);
			a.notifyChanged(not);
		}
	}
	private Adapter getAdapter(EObject anObject) {
		return EcoreUtil.getAdapter(anObject.eAdapters(),EJBAttributeMaintenanceFactoryImpl.ADAPTER_KEY);
	}
}

