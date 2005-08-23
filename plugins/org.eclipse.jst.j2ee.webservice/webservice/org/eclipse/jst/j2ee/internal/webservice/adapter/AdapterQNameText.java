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
package org.eclipse.jst.j2ee.internal.webservice.adapter;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.jst.j2ee.common.CommonPackage;
import org.eclipse.jst.j2ee.common.QName;
import org.eclipse.jst.j2ee.internal.webservice.command.CommandModifyNSURI;
import org.eclipse.jst.j2ee.internal.webservice.command.CommandModifyText;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.widgets.Text;
import org.eclipse.wst.common.componentcore.ArtifactEdit;




/**
 * Since generic eSet's will not work for setting QName namespaceURI's, this subclass's purpose is
 * to override the behaviour when necessary.
 */
public class AdapterQNameText extends AdapterText {

	public AdapterQNameText(ArtifactEdit anArtifactEdit, EStructuralFeature feature, Text text, boolean nillable) {
		super(anArtifactEdit, feature, text, nillable);
	}


	public AdapterQNameText(ArtifactEdit anArtifactEdit, EObject eObject, EStructuralFeature feature, Text text, boolean nillable) {
		super(anArtifactEdit, eObject, feature, text, nillable);
	}

	public void modifyText(ModifyEvent e) {
		if (syncTextAndModel()) {
			//Handle QName_NamespaceURI feature in a special way.
			String namespaceFeatureName = CommonPackage.eINSTANCE.getQName_NamespaceURI().getName();
			if ((eObject_ instanceof QName) && (feature_.getName().equals(namespaceFeatureName))) {
				CommandModifyNSURI command = new CommandModifyNSURI(null, null, (QName) eObject_, text_.getText(), nillable_);
				if (artifactEdit != null) {
					artifactEdit.getContentModelRoot().eResource().setModified(true);
					artifactEdit.getCommandStack().execute(command);
				} 
			} else {
				CommandModifyText command = new CommandModifyText(null, null, eObject_, feature_, text_.getText(), nillable_);
				if (artifactEdit != null) {
					artifactEdit.getContentModelRoot().eResource().setModified(true);
					artifactEdit.getCommandStack().execute(command);
				} 
			}


		}
	}
}