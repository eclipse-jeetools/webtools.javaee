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

import java.util.Iterator;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.impl.AdapterImpl;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EFactory;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.jst.j2ee.common.CommonFactory;
import org.eclipse.jst.j2ee.common.QName;
import org.eclipse.jst.j2ee.internal.common.CommonPackage;
import org.eclipse.jst.j2ee.internal.webservice.command.CommandAddElement;
import org.eclipse.jst.j2ee.internal.webservice.command.CommandRemoveElement;
import org.eclipse.jst.j2ee.internal.webservice.util.QNameHelper;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.widgets.Text;
import org.eclipse.wst.common.componentcore.ArtifactEdit;


/**
 * Generic eSet's will not work when setting namespaceURIs on QName's so AdapterElement cannot be
 * used when the child is a QName. This class is very similar AdapterElement but has special
 * behaviour for handling QName's.
 */
public class AdapterQNameElement extends AdapterImpl implements ModifyListener {

	private ArtifactEdit artifactEdit;
	private EObject parent_;
	private EClass childEClass_;
	private EStructuralFeature childFeature_;
	private boolean childNillable_;
	private EStructuralFeature[] features_;
	private boolean[] featuresNillable_;
	private Text[] featuresTexts_;
	private AdapterText[] featuresAdapters_;

	/**
	 *  
	 */
	public AdapterQNameElement(ArtifactEdit anArtifactEdit, EClass childEClass, EStructuralFeature childFeature, boolean childNillable, Text nsURIText, Text localPartText) {
		super();
		artifactEdit = anArtifactEdit;
		parent_ = null;
		childEClass_ = childEClass;
		childFeature_ = childFeature;
		childNillable_ = childNillable;
		CommonPackage commonPKG = CommonFactory.eINSTANCE.getCommonPackage();
		// Set up QName features
		features_ = new EStructuralFeature[2];
		features_[0] = commonPKG.getQName_NamespaceURI();
		features_[1] = commonPKG.getQName_LocalPart();
		featuresNillable_ = new boolean[2];
		featuresNillable_[0] = false;
		featuresNillable_[1] = false;
		featuresTexts_ = new Text[2];
		featuresTexts_[0] = nsURIText;
		featuresTexts_[1] = localPartText;
		addModifyListener();
		featuresAdapters_ = new AdapterText[features_.length];
		newAdapters(null);

	}

	/**
	 *  
	 */
	public AdapterQNameElement(ArtifactEdit anArtifactEdit, EObject parent, EClass childEClass, EStructuralFeature childFeature, boolean childNillable, Text nsURIText, Text localPartText) {
		this(anArtifactEdit, childEClass, childFeature, childNillable, nsURIText, localPartText);
		adapt(parent);
	}

	private void newAdapters(EObject eObject) {
		if (eObject == null) {
			featuresAdapters_[0] = new AdapterQNameText(artifactEdit, features_[0], featuresTexts_[0], featuresNillable_[0]);
			featuresAdapters_[1] = new AdapterText(artifactEdit, features_[1], featuresTexts_[1], featuresNillable_[1]);
		} else {
			featuresAdapters_[0] = new AdapterQNameText(artifactEdit, eObject, features_[0], featuresTexts_[0], featuresNillable_[0]);
			featuresAdapters_[1] = new AdapterText(artifactEdit, eObject, features_[1], featuresTexts_[1], featuresNillable_[1]);
		}
	}

	private void addModifyListener() {
		for (int i = 0; i < featuresTexts_.length; i++)
			featuresTexts_[i].addModifyListener(this);
	}

	public void notifyChanged(Notification msg) {
		int type = msg.getEventType();
		if ((type == Notification.SET || type == Notification.UNSET) && msg.getFeature() == childFeature_) {
			EObject child = (EObject) parent_.eGet(childFeature_);
			if (child == null && !childNillable_)
				child = setChild();
			for (int i = 0; i < featuresAdapters_.length; i++)
				featuresAdapters_[i].adapt(child);
		}
	}

	public void modifyText(ModifyEvent e) {
		boolean isTextsNull = isTextsNull();
		EObject child = (EObject) parent_.eGet(childFeature_);
		boolean childCanBeRemoved = true;
		if (child != null) {
			Iterator allfeatures = childEClass_.getEAllStructuralFeatures().iterator();
			while (allfeatures.hasNext()) {
				EStructuralFeature thisFeature = (EStructuralFeature) allfeatures.next();
				if (!adaptingFeature(thisFeature) && child.eIsSet(thisFeature)) {
					childCanBeRemoved = false;
					break;
				}
			}
		}
		if (isTextsNull && child != null && childNillable_ && childCanBeRemoved) {
			disposeAdapters();
			parent_.eAdapters().remove(this);
			CommandRemoveElement command = new CommandRemoveElement(null, null, parent_, childFeature_);
			artifactEdit.getContentModelRoot().eResource().setModified(true);
			artifactEdit.getCommandStack().execute(command);
			parent_.eAdapters().add(this);
			newAdapters(null);
		} else if (!isTextsNull && child == null) {
			disposeAdapters();
			parent_.eAdapters().remove(this);
			child = createChild();
			setFeaturesOnChild(child);
			/*
			 * replaced by setFeaturesOnChild for (int i = 0; i < features_.length; i++) { String
			 * text = featuresTexts_[i].getText(); if (text != null && text.length() > 0)
			 * child.eSet(features_[i], text); }
			 */
			CommandAddElement command = new CommandAddElement(null, null, parent_, childFeature_, child);
			artifactEdit.getContentModelRoot().eResource().setModified(true);
			artifactEdit.getCommandStack().execute(command);
			parent_.eAdapters().add(this);
			newAdapters(child);
		}
	}

	private boolean isTextsNull() {
		for (int i = 0; i < featuresTexts_.length; i++) {
			String text = featuresTexts_[i].getText();
			if (text != null && text.length() > 0)
				return false;
		}
		return true;
	}

	public void adapt(EObject eObject) {
		if (parent_ != null)
			parent_.eAdapters().remove(this);
		parent_ = eObject;
		EObject child = null;
		if (parent_ != null) {
			parent_.eAdapters().add(this);
			child = (EObject) parent_.eGet(childFeature_);
			if (child == null && !childNillable_)
				child = setChild();
		}
		removeModifyListener();
		for (int i = 0; i < featuresAdapters_.length; i++)
			featuresAdapters_[i].adapt(child);
		addModifyListener();
	}

	private EObject setChild() {
		EObject child = createChild();
		parent_.eAdapters().remove(this);
		parent_.eSet(childFeature_, child);
		parent_.eAdapters().add(this);
		return child;
	}

	private EObject createChild() {
		EFactory childFactory = childEClass_.getEPackage().getEFactoryInstance();
		EObject child = childFactory.create(childEClass_);
		for (int i = 0; i < features_.length; i++) {
			if (!featuresNillable_[i])
				child.eSet(features_[i], ""); //$NON-NLS-1$
		}
		return child;
	}

	public void dispose() {
		if (parent_ != null)
			parent_.eAdapters().remove(this);
		disposeAdapters();
		removeModifyListener();
	}

	private void disposeAdapters() {
		for (int i = 0; i < featuresAdapters_.length; i++)
			featuresAdapters_[i].dispose();
	}

	private void removeModifyListener() {
		for (int i = 0; i < featuresTexts_.length; i++)
			if (featuresTexts_[i] != null && !featuresTexts_[i].isDisposed())
				featuresTexts_[i].removeModifyListener(this);
	}

	private boolean adaptingFeature(EStructuralFeature feature) {
		if (features_ == null)
			return false;

		for (int i = 0; i < features_.length; i++) {
			if (feature == features_[i]) {
				return true;
			}
		}

		return false;
	}

	protected void setFeaturesOnChild(EObject child) {
		if (child instanceof QName) //This will always be true.
		{
			QName qname = (QName) child;
			String namespaceURIValue = featuresTexts_[0].getText();
			String localPartValue = featuresTexts_[1].getText();
			if (namespaceURIValue == null || namespaceURIValue.length() == 0) {
				//Just set the local part
				if (localPartValue != null && localPartValue.length() > 0)
					qname.setLocalPart(localPartValue);

			} else {
				if (localPartValue == null)
					localPartValue = ""; //$NON-NLS-1$

				//Set namespace and local part using QNameHelper
				QNameHelper.setQNameValue(qname, namespaceURIValue, localPartValue);
			}
		}
	}

}