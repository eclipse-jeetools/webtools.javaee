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
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EFactory;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.jst.j2ee.internal.webservice.command.CommandAddElement;
import org.eclipse.jst.j2ee.internal.webservice.command.CommandRemoveElement;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.widgets.Text;
import org.eclipse.wst.common.componentcore.ArtifactEdit;


public class AdapterElement extends AdapterImpl implements ModifyListener {
	private ArtifactEdit artifactEdit;
	private EObject parent_;
	private EClass childEClass_;
	private EStructuralFeature childFeature_;
	private boolean childNillable_;
	private EStructuralFeature[] features_;
	private boolean[] featuresNillable_;
	private Text[] featuresTexts_;
	private AdapterText[] featuresAdapters_;

	public AdapterElement(ArtifactEdit anArtifactEdit, EClass childEClass, EStructuralFeature childFeature, boolean childNillable, EStructuralFeature[] features, boolean[] featuresNillable, Text[] featuresTexts) {
		super();
		artifactEdit = anArtifactEdit;
		parent_ = null;
		childEClass_ = childEClass;
		childFeature_ = childFeature;
		childNillable_ = childNillable;
		features_ = features;
		featuresNillable_ = featuresNillable;
		featuresTexts_ = featuresTexts;
		addModifyListener();
		featuresAdapters_ = new AdapterText[features_.length];
		newAdapters(null);
	}

	public AdapterElement(ArtifactEdit anArtifactEdit, EObject parent, EClass childEClass, EStructuralFeature childFeature, boolean childNillable, EStructuralFeature[] features, boolean[] featuresNillable, Text[] featuresTexts) {
		this(anArtifactEdit, childEClass, childFeature, childNillable, features, featuresNillable, featuresTexts);
		adapt(parent);
	}

	private void newAdapters(EObject eObject) {
		if (eObject == null)
			for (int i = 0; i < featuresAdapters_.length; i++)
				featuresAdapters_[i] = new AdapterText(artifactEdit, features_[i], featuresTexts_[i], featuresNillable_[i]);
		else
			for (int i = 0; i < featuresAdapters_.length; i++)
				featuresAdapters_[i] = new AdapterText(artifactEdit, eObject, features_[i], featuresTexts_[i], featuresNillable_[i]);
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
			for (int i = 0; i < features_.length; i++) {
				String text = featuresTexts_[i].getText();
				if (text != null && text.length() > 0)
					child.eSet(features_[i], text);
			}
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
}