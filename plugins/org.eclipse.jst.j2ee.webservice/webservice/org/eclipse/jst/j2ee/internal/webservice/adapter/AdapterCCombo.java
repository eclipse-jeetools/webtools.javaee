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
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.swt.custom.CCombo;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;

public class AdapterCCombo extends AdapterImpl implements SelectionListener {
	private EObject eObject_;
	private EStructuralFeature feature_;
	private EStructuralFeature displayFeature_;
	private CCombo combo_;
	private String defaultDisplayString_;

	public AdapterCCombo(EStructuralFeature feature, EStructuralFeature displayFeature, CCombo combo, String defaultDisplayString) {
		super();
		eObject_ = null;
		feature_ = feature;
		displayFeature_ = displayFeature;
		combo_ = combo;
		combo_.addSelectionListener(this);
		defaultDisplayString_ = (defaultDisplayString != null) ? defaultDisplayString : ""; //$NON-NLS-1$
	}

	public AdapterCCombo(EStructuralFeature feature, EStructuralFeature displayFeature, CCombo combo) {
		this(feature, displayFeature, combo, null);
	}

	public AdapterCCombo(EObject eObject, EStructuralFeature feature, EStructuralFeature displayFeature, CCombo combo) {
		this(eObject, feature, displayFeature, combo, null);
	}

	public AdapterCCombo(EObject eObject, EStructuralFeature feature, EStructuralFeature displayFeature, CCombo combo, String defaultDisplayString) {
		this(feature, displayFeature, combo, defaultDisplayString);
		adapt(eObject);
	}

	public void notifyChanged(Notification msg) {
		Object notifier = msg.getNotifier();
		if (notifier == eObject_) {
			int type = msg.getEventType();
			if (type == Notification.SET || type == Notification.UNSET || type == Notification.ADD || type == Notification.REMOVE || type == Notification.ADD_MANY || type == Notification.REMOVE_MANY)
				populateCombo();
		} else if (msg.getFeature() == displayFeature_)
			populateCombo();
	}

	private void populateCombo() {
		if (eObject_ != null) {
			Object object = eObject_.eGet(feature_);
			if (object != null) {
				if (combo_.isDisposed())
					return;
				int selectionIndex = combo_.getSelectionIndex();
				String text = combo_.getText();
				combo_.removeAll();
				if (object instanceof EList) {
					EList eList = (EList) object;
					for (Iterator it = eList.iterator(); it.hasNext();)
						combo_.add(toDisplayString(((EObject) it.next()).eGet(displayFeature_)));
				} else
					combo_.add(toDisplayString(((EObject) object).eGet(displayFeature_)));
				int newIndex = combo_.indexOf(text);
				if (selectionIndex != -1 && newIndex != -1)
					combo_.select(newIndex);
				else
					combo_.select(0);
			}
		}
	}

	private String toDisplayString(Object object) {
		if (object != null) {
			String s = object.toString();
			if (s != null && s.length() > 0)
				return s;
		}
		return defaultDisplayString_;
	}

	public void widgetDefaultSelected(SelectionEvent e) {
		//Do nothing
	}

	public void widgetSelected(SelectionEvent e) {
		//Do nothing
	}

	public void adapt(EObject eObject) {
		if (eObject_ != null) {
			eObject_.eAdapters().remove(this);
			combo_.removeAll();
		}
		eObject_ = eObject;
		if (eObject_ != null) {
			eObject_.eAdapters().add(this);
			Object object = eObject_.eGet(feature_);
			if (object instanceof EList) {
				for (Iterator it = ((EList) object).iterator(); it.hasNext();) {
					Object item = it.next();
					if (item instanceof EObject)
						((EObject) item).eAdapters().add(this);
				}
			} else if (object instanceof EObject)
				((EObject) object).eAdapters().add(this);
			populateCombo();
		}
	}

	public void dispose() {
		if (eObject_ != null)
			eObject_.eAdapters().remove(this);
		if (combo_ != null && !combo_.isDisposed())
			combo_.removeSelectionListener(this);
	}
}