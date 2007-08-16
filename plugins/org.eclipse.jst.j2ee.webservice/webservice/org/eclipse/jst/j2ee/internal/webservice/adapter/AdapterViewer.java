/***************************************************************************************************
 * Copyright (c) 2003, 2004 IBM Corporation and others. All rights reserved. This program and the
 * accompanying materials are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors: IBM Corporation - initial API and implementation
 **************************************************************************************************/

package org.eclipse.jst.j2ee.internal.webservice.adapter;

import java.util.ArrayList;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.impl.AdapterImpl;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.jface.viewers.Viewer;

public class AdapterViewer extends AdapterImpl {
	private EObject eObject_;
	private Viewer viewer_;
	private ArrayList features_;

	public AdapterViewer(Viewer viewer) {
		super();
		eObject_ = null;
		viewer_ = viewer;
		features_ = new ArrayList();
	}

	public AdapterViewer(EObject eObject, Viewer viewer) {
		this(viewer);
		adapt(eObject);
	}

	public void notifyChanged(Notification msg) {
		int type = msg.getEventType();
		if (type == Notification.SET || type == Notification.UNSET || type == Notification.ADD || type == Notification.REMOVE || type == Notification.ADD_MANY || type == Notification.REMOVE_MANY) {
			Object notifier = msg.getNotifier();
			if (notifier == eObject_) {
				if (type == Notification.SET || type == Notification.ADD || type == Notification.ADD_MANY)
					internalAdaptFeature((EStructuralFeature) msg.getFeature());
				else if (type == Notification.UNSET || type == Notification.REMOVE || type == Notification.REMOVE_MANY) {
					Object object = msg.getOldValue();
					if (object instanceof EObject)
						((EObject) object).eAdapters().remove(this);
					else if (object instanceof EList) {
						EList eList = (EList) object;
						for (int i = 0; i < eList.size(); i++) {
							Object item = eList.get(i);
							if (item instanceof EObject)
								((EObject) item).eAdapters().remove(this);
						}
					}
				}
			}
			viewer_.refresh();
		}
	}

	public void adapt(EObject eObject) {
		if (eObject_ != null) {
			eObject_.eAdapters().remove(this);
			unadaptAllCachedFeature();
		}
		eObject_ = eObject;
		if (eObject_ != null) {
			eObject_.eAdapters().add(this);
			readaptAllCachedFeature();
		}
		viewer_.refresh();
	}

	public void adaptFeature(EStructuralFeature feature) {
		if (!features_.contains(feature)) {
			features_.add(feature);
			internalAdaptFeature(feature);
		}
	}

	private void internalAdaptFeature(EStructuralFeature feature) {
		if (eObject_ != null) {
			Object featureObject = eObject_.eGet(feature);
			if (featureObject instanceof EObject) {
				EList eAdapters = ((EObject) featureObject).eAdapters();
				if (!eAdapters.contains(this))
					eAdapters.add(this);
			} else if (featureObject instanceof EList) {
				EList eList = (EList) featureObject;
				for (int i = 0; i < eList.size(); i++) {
					Object object = eList.get(i);
					if (object instanceof EObject) {
						EList eAdapters = ((EObject) object).eAdapters();
						if (!eAdapters.contains(this))
							eAdapters.add(this);
					}
				}
			}
		}
	}

	private void readaptAllCachedFeature() {
		EStructuralFeature[] features = new EStructuralFeature[features_.size()];
		for (int i = 0; i < features.length; i++)
			features[i] = (EStructuralFeature) features_.get(i);
		for (int i = 0; i < features.length; i++)
			internalAdaptFeature(features[i]);
	}

	public void unadaptFeature(EStructuralFeature feature) {
		features_.remove(feature);
		internalUnadaptFeature(feature);
	}

	private void internalUnadaptFeature(EStructuralFeature feature) {
		if (eObject_ != null) {
			Object featureObject = eObject_.eGet(feature);
			if (featureObject instanceof EObject)
				((EObject) featureObject).eAdapters().remove(this);
			else if (featureObject instanceof EList) {
				EList eList = (EList) featureObject;
				for (int i = 0; i < eList.size(); i++) {
					Object object = eList.get(i);
					if (object instanceof EObject)
						((EObject) object).eAdapters().remove(this);
				}
			}
		}
	}

	private void unadaptAllCachedFeature() {
		EStructuralFeature[] features = new EStructuralFeature[features_.size()];
		for (int i = 0; i < features.length; i++)
			features[i] = (EStructuralFeature) features_.get(i);
		for (int i = 0; i < features.length; i++)
			internalUnadaptFeature(features[i]);
	}

	public void dispose() {
		if (eObject_ != null)
			eObject_.eAdapters().remove(this);
		unadaptAllCachedFeature();
		features_ = null;
	}
}