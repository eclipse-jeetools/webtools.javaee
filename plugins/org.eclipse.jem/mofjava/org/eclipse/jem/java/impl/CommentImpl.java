/*******************************************************************************
 * Copyright (c) 2001, 2005 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *******************************************************************************/
package org.eclipse.jem.java.impl;

/*
 *  $RCSfile: CommentImpl.java,v $
 *  $Revision: 1.5 $  $Date: 2005/08/24 20:20:25 $ 
 */

import java.util.Collection;

import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.util.InternalEList;

import org.eclipse.jem.java.Comment;
import org.eclipse.jem.java.JavaRefPackage;
/**
 * @generated
 */
public class CommentImpl extends BlockImpl implements Comment{

	
	protected CommentImpl() {
		super();
	}
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected EClass eStaticClass() {
		return JavaRefPackage.eINSTANCE.getComment();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, Class baseClass, NotificationChain msgs) {
		if (featureID >= 0) {
			switch (eDerivedStructuralFeatureID(featureID, baseClass)) {
				case JavaRefPackage.COMMENT__CONTENTS:
					return ((InternalEList)getContents()).basicRemove(otherEnd, msgs);
				default:
					return eDynamicInverseRemove(otherEnd, featureID, baseClass, msgs);
			}
		}
		return eBasicSetContainer(null, featureID, msgs);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Object eGet(EStructuralFeature eFeature, boolean resolve) {
		switch (eDerivedStructuralFeatureID(eFeature)) {
			case JavaRefPackage.COMMENT__SOURCE:
				return getSource();
			case JavaRefPackage.COMMENT__NAME:
				return getName();
			case JavaRefPackage.COMMENT__CONTENTS:
				return getContents();
		}
		return eDynamicGet(eFeature, resolve);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void eSet(EStructuralFeature eFeature, Object newValue) {
		switch (eDerivedStructuralFeatureID(eFeature)) {
			case JavaRefPackage.COMMENT__SOURCE:
				setSource((String)newValue);
				return;
			case JavaRefPackage.COMMENT__NAME:
				setName((String)newValue);
				return;
			case JavaRefPackage.COMMENT__CONTENTS:
				getContents().clear();
				getContents().addAll((Collection)newValue);
				return;
		}
		eDynamicSet(eFeature, newValue);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void eUnset(EStructuralFeature eFeature) {
		switch (eDerivedStructuralFeatureID(eFeature)) {
			case JavaRefPackage.COMMENT__SOURCE:
				setSource(SOURCE_EDEFAULT);
				return;
			case JavaRefPackage.COMMENT__NAME:
				setName(NAME_EDEFAULT);
				return;
			case JavaRefPackage.COMMENT__CONTENTS:
				getContents().clear();
				return;
		}
		eDynamicUnset(eFeature);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean eIsSet(EStructuralFeature eFeature) {
		switch (eDerivedStructuralFeatureID(eFeature)) {
			case JavaRefPackage.COMMENT__SOURCE:
				return SOURCE_EDEFAULT == null ? source != null : !SOURCE_EDEFAULT.equals(source);
			case JavaRefPackage.COMMENT__NAME:
				return NAME_EDEFAULT == null ? name != null : !NAME_EDEFAULT.equals(name);
			case JavaRefPackage.COMMENT__CONTENTS:
				return contents != null && !contents.isEmpty();
		}
		return eDynamicIsSet(eFeature);
	}

}





