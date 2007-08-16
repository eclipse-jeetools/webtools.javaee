/***************************************************************************************************
 * Copyright (c) 2003, 2004 IBM Corporation and others. All rights reserved. This program and the
 * accompanying materials are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors: IBM Corporation - initial API and implementation
 **************************************************************************************************/

package org.eclipse.jst.j2ee.internal.webservice.adapter;

import java.util.Iterator;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.impl.AdapterImpl;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;

public class AdapterLayer extends AdapterImpl {
	private EObject parent_;
	private EStructuralFeature feature_;
	private EClass childEClass_;
	private EObject child_;

	public AdapterLayer(EObject parent, EStructuralFeature feature, EClass childEClass) {
		super();
		parent_ = parent;
		feature_ = feature;
		childEClass_ = childEClass;
		adapt(parent_);
	}

	public void notifyChanged(Notification msg) {
		int type = msg.getEventType();
		if (type == Notification.SET || type == Notification.UNSET || type == Notification.ADD || type == Notification.REMOVE || type == Notification.ADD_MANY || type == Notification.REMOVE_MANY)
			adaptParent();
	}

	public void adapt(EObject eObject) {
		if (child_ != null)
			child_.eAdapters().remove(this);
		parent_ = eObject;
		adaptChild();
		if (child_ != null)
			child_.eAdapters().add(this);
	}

	public void dispose() {
		if (child_ != null)
			child_.eAdapters().remove(this);
	}

	public EObject getChild() {
		return child_;
	}

	private void adaptChild() {
		if (parent_ != null) {
			Object object = parent_.eGet(feature_);
			if (object == null)
				child_ = childEClass_.getEPackage().getEFactoryInstance().create(childEClass_);
			else
				child_ = (EObject) object;
		} else
			child_ = null;
	}

	private void adaptParent() {
		if (parent_ != null && child_ != null) {
			EList childFeatures = childEClass_.getEAllStructuralFeatures();
			for (Iterator it = childFeatures.iterator(); it.hasNext();) {
				EStructuralFeature childFeature = (EStructuralFeature) it.next();
				boolean isFeatureSet;
				Object grandchild = child_.eGet(childFeature);
				if (grandchild != null && grandchild instanceof EList)
					isFeatureSet = !((EList) grandchild).isEmpty();
				else
					isFeatureSet = child_.eIsSet(childFeature);
				if (isFeatureSet) {
					parent_.eSet(feature_, child_);
					return;
				}
			}
			parent_.eUnset(feature_);
		}
	}
}