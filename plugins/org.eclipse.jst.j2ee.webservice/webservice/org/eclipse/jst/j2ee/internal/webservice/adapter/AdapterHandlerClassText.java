/***************************************************************************************************
 * Copyright (c) 2003, 2004 IBM Corporation and others. All rights reserved. This program and the
 * accompanying materials are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors: IBM Corporation - initial API and implementation
 **************************************************************************************************/

package org.eclipse.jst.j2ee.internal.webservice.adapter;

/*
 * import org.eclipse.emf.ecore.EObject;
 */
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.impl.AdapterImpl;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.jst.j2ee.internal.webservice.command.CommandModifyHandlerClassText;
import org.eclipse.jst.j2ee.webservice.internal.wsclient.Webservice_clientPackage;
import org.eclipse.jst.j2ee.webservice.wsclient.Handler;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.widgets.Text;
import org.eclipse.wst.common.componentcore.ArtifactEdit;


public class AdapterHandlerClassText extends AdapterImpl implements ModifyListener {
	protected ArtifactEdit artifactEdit;
	protected Handler eObject_;
	protected EStructuralFeature feature_;
	protected Text text_;
	protected boolean nillable_;

	public AdapterHandlerClassText(ArtifactEdit anArtifactEdit, Text text) {
		super();
		artifactEdit = anArtifactEdit;
		eObject_ = null;
		feature_ = Webservice_clientPackage.eINSTANCE.getHandler_HandlerClass();
		text_ = text;
		nillable_ = false;
		text_.addModifyListener(this);
	}

	public AdapterHandlerClassText(ArtifactEdit anArtifactEdit, Handler eObject, Text text) {
		this(anArtifactEdit, text);
		adapt(eObject);
	}

	public void notifyChanged(Notification msg) {
		int type = msg.getEventType();
		if ((type == Notification.SET || type == Notification.UNSET) && syncTextAndModel() && msg.getFeature() == feature_) {
			String newClassName = eObject_.getHandlerClass().getQualifiedNameForReflection();
			setText(newClassName);
		}
	}

	public void modifyText(ModifyEvent e) {
		if (syncTextAndModel()) {
			CommandModifyHandlerClassText command = new CommandModifyHandlerClassText(null, null, eObject_, text_.getText());
			if (artifactEdit != null) {
				artifactEdit.getContentModelRoot().eResource().setModified(true);
				artifactEdit.getCommandStack().execute(command);
			}
		}
	}

	public void adapt(Handler eObject) {
		if (eObject_ != null)
			eObject_.eAdapters().remove(this);
		eObject_ = eObject;
		if (eObject_ != null) {
			eObject_.eAdapters().add(this);
			String newClassName = eObject_.getHandlerClass().getQualifiedNameForReflection();
			setText(newClassName);
		} else
			setText(null);
	}

	/*
	 * public void adapt(EObject eObject, EStructuralFeature feature) { feature_ = feature;
	 * adapt(eObject); }
	 */
	public void dispose() {
		if (eObject_ != null)
			eObject_.eAdapters().remove(this);
		if (text_ != null && !text_.isDisposed())
			text_.removeModifyListener(this);
	}

	protected boolean syncTextAndModel() {
		if (eObject_ != null) {
			String modelValue = eObject_.getHandlerClass().getQualifiedNameForReflection();
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