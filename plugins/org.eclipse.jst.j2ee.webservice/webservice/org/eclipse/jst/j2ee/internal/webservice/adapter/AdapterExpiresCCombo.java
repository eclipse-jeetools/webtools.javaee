/***************************************************************************************************
 * Copyright (c) 2003, 2004 IBM Corporation and others. All rights reserved. This program and the
 * accompanying materials are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors: IBM Corporation - initial API and implementation
 **************************************************************************************************/

package org.eclipse.jst.j2ee.internal.webservice.adapter;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.impl.AdapterImpl;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.jst.j2ee.internal.webservice.command.CommandModifyText;
import org.eclipse.jst.j2ee.internal.webservice.editmodel.EditModel;
import org.eclipse.swt.custom.CCombo;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;


public class AdapterExpiresCCombo extends AdapterImpl implements ModifyListener {
	private EditModel editModel_;
	private EObject eObject_;
	private EStructuralFeature feature_;
	private CCombo year_;
	private CCombo month_;
	private CCombo day_;
	private CCombo hour_;
	private CCombo minute_;
	private CCombo second_;
	private CCombo msecond_;
	private boolean nillable_;

	public AdapterExpiresCCombo(EditModel editModel, EStructuralFeature feature, CCombo year, CCombo month, CCombo day, CCombo hour, CCombo minute, CCombo second, CCombo msecond, boolean nillable) {
		super();
		editModel_ = editModel;
		eObject_ = null;
		feature_ = feature;
		year_ = year;
		month_ = month;
		day_ = day;
		hour_ = hour;
		minute_ = minute;
		second_ = second;
		msecond_ = msecond;
		nillable_ = nillable;
		year_.addModifyListener(this);
		month_.addModifyListener(this);
		day_.addModifyListener(this);
		hour_.addModifyListener(this);
		minute_.addModifyListener(this);
		second_.addModifyListener(this);
		msecond_.addModifyListener(this);
	}

	public AdapterExpiresCCombo(EditModel editModel, EObject eObject, EStructuralFeature feature, CCombo year, CCombo month, CCombo day, CCombo hour, CCombo minute, CCombo second, CCombo msecond, boolean nillable) {
		this(editModel, feature, year, month, day, hour, minute, second, msecond, nillable);
		adapt(eObject);
	}

	public void notifyChanged(Notification msg) {
		int type = msg.getEventType();
		if ((type == Notification.SET || type == Notification.UNSET) && syncTextAndModel() && msg.getFeature() == feature_)
			setText(msg.getNewStringValue());
	}

	public void modifyText(ModifyEvent e) {
		if (syncTextAndModel()) {
			CommandModifyText command = new CommandModifyText(null, null, eObject_, feature_, expires(), nillable_);
			editModel_.getRootModelResource().setModified(true);
			editModel_.getCommandStack().execute(command);
		}
	}

	public String expires() {
		return "P" + //$NON-NLS-1$
					year_.getText() + "Y" + //$NON-NLS-1$
					month_.getText() + "M" + //$NON-NLS-1$
					day_.getText() + "DT" + //$NON-NLS-1$
					hour_.getText() + "H" + //$NON-NLS-1$
					minute_.getText() + "M" + //$NON-NLS-1$
					second_.getText() + "." + //$NON-NLS-1$
					msecond_.getText() + "S"; //$NON-NLS-1$
	}

	public void adapt(EObject eObject) {
		if (eObject_ != null)
			eObject_.eAdapters().remove(this);
		eObject_ = eObject;
		if (eObject_ != null) {
			eObject_.eAdapters().add(this);
			setText((String) eObject_.eGet(feature_));
		}
	}

	public void adapt(EObject eObject, EStructuralFeature feature) {
		feature_ = feature;
		adapt(eObject);
	}

	public void dispose() {
		if (eObject_ != null)
			eObject_.eAdapters().remove(this);
		if (year_ != null && !year_.isDisposed())
			year_.removeModifyListener(this);
		if (month_ != null && !month_.isDisposed())
			month_.removeModifyListener(this);
		if (day_ != null && !day_.isDisposed())
			day_.removeModifyListener(this);
		if (hour_ != null && !hour_.isDisposed())
			hour_.removeModifyListener(this);
		if (minute_ != null && !minute_.isDisposed())
			minute_.removeModifyListener(this);
		if (second_ != null && !second_.isDisposed())
			second_.removeModifyListener(this);
		if (msecond_ != null && !msecond_.isDisposed())
			msecond_.removeModifyListener(this);

	}

	private boolean syncTextAndModel() {
		if (eObject_ != null) {
			String modelValue = (String) eObject_.eGet(feature_);
			String value = expires();
			if (modelValue == null || modelValue.length() <= 0)
				return !(value == null || value.length() <= 0);
			return !(modelValue.equals(value));
		}
		return false;
	}

	private void setText(String modelValue) {
		if (modelValue != null) {
			int y = modelValue.indexOf("Y"); //$NON-NLS-1$
			int mo = modelValue.indexOf("M"); //$NON-NLS-1$
			int d = modelValue.indexOf("D"); //$NON-NLS-1$
			int h = modelValue.indexOf("H"); //$NON-NLS-1$
			int mi = modelValue.lastIndexOf("M"); //$NON-NLS-1$
			int s = modelValue.indexOf("."); //$NON-NLS-1$
			int ms = modelValue.indexOf("S"); //$NON-NLS-1$

			if (!year_.isDisposed() && y > 0)
				year_.setText(modelValue.substring(1, y));
			if (!month_.isDisposed() && mo > 0)
				month_.setText(modelValue.substring(y + 1, mo));
			if (!day_.isDisposed() && d > 0)
				day_.setText(modelValue.substring(mo + 1, d));
			if (!hour_.isDisposed() && h > 0)
				hour_.setText(modelValue.substring(d + 2, h));
			if (!minute_.isDisposed() && mi > 0)
				minute_.setText(modelValue.substring(h + 1, mi));
			if (!second_.isDisposed() && s > 0)
				second_.setText(modelValue.substring(mi + 1, s));
			if (!msecond_.isDisposed() && ms > 0)
				msecond_.setText(modelValue.substring(s + 1, ms));
		}
	}
}