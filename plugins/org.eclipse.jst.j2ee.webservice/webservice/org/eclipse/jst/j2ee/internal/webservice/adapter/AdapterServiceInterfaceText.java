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
import org.eclipse.jst.j2ee.internal.webservice.command.CommandModifyServiceInterfaceText;
import org.eclipse.jst.j2ee.webservice.internal.wsclient.Webservice_clientPackage;
import org.eclipse.jst.j2ee.webservice.wsclient.ServiceRef;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.widgets.Text;
import org.eclipse.wst.common.componentcore.ArtifactEdit;


public class AdapterServiceInterfaceText extends AdapterImpl implements ModifyListener {
	protected ArtifactEdit artifactEdit;
	protected ServiceRef eObject_;
	protected EStructuralFeature feature_;
	protected Text text_;
	protected boolean nillable_;

	public AdapterServiceInterfaceText(ArtifactEdit anArtifactEdit, Text text) {
		super();
		artifactEdit = anArtifactEdit;
		eObject_ = null;
		feature_ = Webservice_clientPackage.eINSTANCE.getServiceRef_ServiceInterface();
		text_ = text;
		nillable_ = false;
		text_.addModifyListener(this);
	}

	public AdapterServiceInterfaceText(ArtifactEdit anArtifactEdit, ServiceRef eObject, Text text) {
		this(anArtifactEdit, text);
		adapt(eObject);
	}

	public void notifyChanged(Notification msg) {
		int type = msg.getEventType();
		if ((type == Notification.SET || type == Notification.UNSET) && syncTextAndModel() && msg.getFeature() == feature_) {
			String newClassName = eObject_.getServiceInterface().getQualifiedNameForReflection();
			setText(newClassName);
		}
	}

	public void modifyText(ModifyEvent e) {
		if (syncTextAndModel()) {
			CommandModifyServiceInterfaceText command = new CommandModifyServiceInterfaceText(null, null, eObject_, text_.getText());
			artifactEdit.getContentModelRoot().eResource().setModified(true);
			artifactEdit.getCommandStack().execute(command);
		}
	}

	public void adapt(ServiceRef eObject) {
		if (eObject_ != null)
			eObject_.eAdapters().remove(this);
		eObject_ = eObject;
		if (eObject_ != null) {
			eObject_.eAdapters().add(this);
			if (eObject_.getServiceInterface() != null) {
				String newClassName = eObject_.getServiceInterface().getQualifiedNameForReflection();
				setText(newClassName);
			} else {
				setText(null);
			}
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
			String modelValue = null;
			if (eObject_.getServiceInterface() != null)
				modelValue = eObject_.getServiceInterface().getQualifiedNameForReflection();
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