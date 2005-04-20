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
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.widgets.Text;
import org.eclipse.wst.common.componentcore.ArtifactEdit;


public class AdapterText extends AdapterImpl implements ModifyListener {
	protected EditModel editModel_;
	protected EObject eObject_;
	protected EStructuralFeature feature_;
	protected Text text_;
	protected boolean nillable_;
	protected ArtifactEdit artifactEdit;

	public AdapterText(EditModel editModel, EStructuralFeature feature, Text text, boolean nillable) {
		super();
		editModel_ = editModel;
		eObject_ = null;
		feature_ = feature;
		text_ = text;
		nillable_ = nillable;
		text_.addModifyListener(this);
	}

	public AdapterText(EditModel editModel, EObject eObject, EStructuralFeature feature, Text text, boolean nillable) {
		this(editModel, feature, text, nillable);
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
			if (editModel_ != null) {
				editModel_.getRootModelResource().setModified(true);
				editModel_.getCommandStack().execute(command);
			} else {
				artifactEdit.getCommandStack().execute(command);
			}
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

	protected boolean syncTextAndModel() {
		if (eObject_ != null) {
			String modelValue = (String) eObject_.eGet(feature_);
			String textValue = text_.getText();
			if (modelValue == null || modelValue.length() <= 0)
				return !(textValue == null || textValue.length() <= 0);
			return !(modelValue.equals(textValue));
		}
		return false;
	}

	protected void setText(String text) {
		if (!text_.isDisposed()) {
			String currText = text_.getText();
			if (text == null || text.length() <= 0) {
				if (currText != null && currText.length() > 0)
					text_.setText(""); //$NON-NLS-1$
			} else if (!text.equals(currText))
				text_.setText(text);
		}
	}

	/**
	 * @return Returns the j2eeEditModel.
	 */
	public ArtifactEdit getArtifactEdit() {
		return artifactEdit;
	}

	/**
	 * @param editModel
	 *            The j2eeEditModel to set.
	 */
	public void setArtifactEdit(ArtifactEdit anArtifactEdit) {
		artifactEdit = anArtifactEdit;
	}

}