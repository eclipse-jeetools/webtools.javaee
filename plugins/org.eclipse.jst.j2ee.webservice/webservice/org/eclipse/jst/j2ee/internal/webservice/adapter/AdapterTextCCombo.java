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
import org.eclipse.swt.custom.CCombo;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.wst.common.componentcore.ArtifactEdit;


public class AdapterTextCCombo extends AdapterImpl implements ModifyListener {
	private ArtifactEdit artifactEdit_;
	private EObject eObject_;
	private EStructuralFeature feature_;
	private CCombo text_;
	private boolean nillable_;

	public AdapterTextCCombo(ArtifactEdit artifactEdit, EStructuralFeature feature, CCombo text, boolean nillable) {
		super();
		artifactEdit_ = artifactEdit;
		eObject_ = null;
		feature_ = feature;
		text_ = text;
		nillable_ = nillable;
		text_.addModifyListener(this);
	}

	public AdapterTextCCombo(ArtifactEdit artifactEdit, EObject eObject, EStructuralFeature feature, CCombo text, boolean nillable) {
		this(artifactEdit, feature, text, nillable);
		adapt(eObject);
	}

	public void notifyChanged(Notification msg) {
		int type = msg.getEventType();
		if ((type == Notification.SET || type == Notification.UNSET) && syncTextAndModel() && msg.getFeature() == feature_)
			setText(msg.getNewStringValue());
	}

	public void modifyText(ModifyEvent e) {
		if (syncTextAndModel()) {
			CommandModifyText command = new CommandModifyText(null, null, eObject_, feature_, text_.getText(), nillable_);
			artifactEdit_.getContentModelRoot().eResource().setModified(true);
			artifactEdit_.getCommandStack().execute(command);
		}
	}

	public void adapt(EObject eObject) {
		if (eObject_ != null)
			eObject_.eAdapters().remove(this);
		eObject_ = eObject;
		if (eObject_ != null) {
			eObject_.eAdapters().add(this);
			setText((String) eObject_.eGet(feature_));
		} else
			setText(null);
	}

	public void adapt(EObject eObject, EStructuralFeature feature) {
		feature_ = feature;
		adapt(eObject);
	}

	public void dispose() {
		if (eObject_ != null)
			eObject_.eAdapters().remove(this);
		if (text_ != null && !text_.isDisposed())
			text_.removeModifyListener(this);
	}

	private boolean syncTextAndModel() {
		if (eObject_ != null) {
			String modelValue = (String) eObject_.eGet(feature_);
			String textValue = text_.getText();
			if (modelValue == null || modelValue.length() <= 0)
				return !(textValue == null || textValue.length() <= 0);
			return !(modelValue.equals(textValue));
		}
		return false;
	}

	private void setText(String text) {
		if (!text_.isDisposed()) {
			String currText = text_.getText();
			if (text == null || text.length() <= 0) {
				if (currText != null && currText.length() > 0)
					text_.setText(""); //$NON-NLS-1$
			} else if (!text.equals(currText))
				text_.setText(text);
		}
	}
}