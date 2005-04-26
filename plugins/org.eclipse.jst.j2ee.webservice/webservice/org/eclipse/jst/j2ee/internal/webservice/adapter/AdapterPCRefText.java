/*******************************************************************************
 * Copyright (c) 2003, 2004 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 * IBM Corporation - initial API and implementation
 *******************************************************************************/
/*
 * Created on Jan 21, 2004
 *
 * To change the template for this generated file go to
 * Window - Preferences - Java - Code Generation - Code and Comments
 */
package org.eclipse.jst.j2ee.internal.webservice.adapter;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.jem.java.JavaClass;
import org.eclipse.jst.j2ee.internal.webservice.command.CommandModifySEI;
import org.eclipse.jst.j2ee.internal.webservice.command.CommandModifyText;
import org.eclipse.jst.j2ee.webservice.internal.wsclient.Webservice_clientPackage;
import org.eclipse.jst.j2ee.webservice.wsclient.PortComponentRef;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.widgets.Text;
import org.eclipse.wst.common.componentcore.ArtifactEdit;


/**
 * @author rsinha
 * 
 * To change the template for this generated type comment go to Window - Preferences - Java - Code
 * Generation - Code and Comments
 */
public class AdapterPCRefText extends AdapterText {
	public AdapterPCRefText(ArtifactEdit anArtifactEdit, EStructuralFeature feature, Text text, boolean nillable) {
		super(anArtifactEdit, feature, text, nillable);
	}


	public AdapterPCRefText(ArtifactEdit anArtifactEdit, EObject eObject, EStructuralFeature feature, Text text, boolean nillable) {
		super(anArtifactEdit, eObject, feature, text, nillable);
	}

	public void notifyChanged(Notification msg) {
		int type = msg.getEventType();
		if ((type == Notification.SET || type == Notification.UNSET) && syncTextAndModel() && msg.getFeature() == feature_) {
			Object obj = eObject_.eGet(feature_);
			String objStringValue = ""; //$NON-NLS-1$
			if (obj instanceof String) {
				objStringValue = (String) obj;
			} else if (obj instanceof JavaClass) {
				JavaClass javaClass = (JavaClass) obj;
				objStringValue = javaClass.getQualifiedNameForReflection();
			}
			setText(objStringValue);
		}
	}

	public void modifyText(ModifyEvent e) {
		if (syncTextAndModel()) {
			//Handle PortComponentRef_ServiceEndpointInterface feature in a special way.
			String SEIFeatureName = Webservice_clientPackage.eINSTANCE.getPortComponentRef_ServiceEndpointInterface().getName();
			if ((eObject_ instanceof PortComponentRef) && (feature_.getName().equals(SEIFeatureName))) {
				CommandModifySEI command = new CommandModifySEI(null, null, (PortComponentRef) eObject_, text_.getText(), nillable_);
				artifactEdit.getContentModelRoot().eResource().setModified(true);
				artifactEdit.getCommandStack().execute(command);
			} else {
				CommandModifyText command = new CommandModifyText(null, null, eObject_, feature_, text_.getText(), nillable_);
				artifactEdit.getContentModelRoot().eResource().setModified(true);
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
			Object obj = eObject_.eGet(feature_);
			String objStringValue = ""; //$NON-NLS-1$
			if (obj instanceof String) {
				objStringValue = (String) obj;
			} else if (obj instanceof JavaClass) {
				JavaClass javaClass = (JavaClass) obj;
				objStringValue = javaClass.getQualifiedNameForReflection();
			}
			setText(objStringValue);
		} else
			setText(null);
	}

	protected boolean syncTextAndModel() {
		if (eObject_ != null) {
			String modelValue;
			//Handle PortComponentRef_ServiceEndpointInterface feature in a special way.
			String SEIFeatureName = Webservice_clientPackage.eINSTANCE.getPortComponentRef_ServiceEndpointInterface().getName();
			if ((eObject_ instanceof PortComponentRef) && (feature_.getName().equals(SEIFeatureName))) {
				modelValue = ((PortComponentRef) eObject_).getServiceEndpointInterface().getQualifiedNameForReflection();
			} else {
				modelValue = (String) eObject_.eGet(feature_);
			}
			String textValue = text_.getText();
			if (modelValue == null || modelValue.length() <= 0)
				return !(textValue == null || textValue.length() <= 0);
			return !(modelValue.equals(textValue));
		}
		return false;
	}

}