/***************************************************************************************************
 * Copyright (c) 2003, 2004 IBM Corporation and others. All rights reserved. This program and the
 * accompanying materials are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors: IBM Corporation - initial API and implementation
 **************************************************************************************************/

package org.eclipse.jst.j2ee.internal.webservice.adapter;

import org.eclipse.emf.common.util.BasicEList;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;

public class AdapterViewerItem {
	private EList eObjects_;
	private EStructuralFeature feature_;

	public AdapterViewerItem(EList eObjects, EStructuralFeature feature) {
		eObjects_ = eObjects;
		feature_ = feature;
	}

	public AdapterViewerItem(EObject eObject, EStructuralFeature feature) {
		eObjects_ = new BasicEList();
		eObjects_.add(eObject);
		feature_ = feature;
	}

	public EList getEObjectList() {
		return eObjects_;
	}

	public EStructuralFeature getEStructuralFeature() {
		return feature_;
	}
}