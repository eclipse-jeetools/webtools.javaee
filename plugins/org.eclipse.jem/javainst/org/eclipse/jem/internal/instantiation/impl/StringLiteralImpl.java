package org.eclipse.jem.internal.instantiation.impl;
/*******************************************************************************
 * Copyright (c)  2004 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials 
 * are made available under the terms of the Common Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/cpl-v10.html
 * 
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *******************************************************************************/
/*
 *  $RCSfile: StringLiteralImpl.java,v $
 *  $Revision: 1.1 $  $Date: 2004/01/19 22:50:15 $ 
 */
import org.eclipse.emf.common.notify.Notification;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EStructuralFeature;

import org.eclipse.emf.ecore.impl.ENotificationImpl;

import org.eclipse.jem.internal.instantiation.*;
import org.eclipse.jem.internal.instantiation.InstantiationPackage;
import org.eclipse.jem.internal.instantiation.StringLiteral;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>String Literal</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.eclipse.jem.internal.instantiation.impl.StringLiteralImpl#getEscapedValue <em>Escaped Value</em>}</li>
 *   <li>{@link org.eclipse.jem.internal.instantiation.impl.StringLiteralImpl#getLiteralValue <em>Literal Value</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class StringLiteralImpl extends ExpressionImpl implements StringLiteral {
	/**
	 * The default value of the '{@link #getEscapedValue() <em>Escaped Value</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getEscapedValue()
	 * @generated
	 * @ordered
	 */
	protected static final String ESCAPED_VALUE_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getEscapedValue() <em>Escaped Value</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getEscapedValue()
	 * @generated
	 * @ordered
	 */
	protected String escapedValue = ESCAPED_VALUE_EDEFAULT;

	/**
	 * The default value of the '{@link #getLiteralValue() <em>Literal Value</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getLiteralValue()
	 * @generated
	 * @ordered
	 */
	protected static final String LITERAL_VALUE_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getLiteralValue() <em>Literal Value</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getLiteralValue()
	 * @generated
	 * @ordered
	 */
	protected String literalValue = LITERAL_VALUE_EDEFAULT;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected StringLiteralImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected EClass eStaticClass() {
		return InstantiationPackage.eINSTANCE.getStringLiteral();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getEscapedValue() {
		return escapedValue;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setEscapedValue(String newEscapedValue) {
		String oldEscapedValue = escapedValue;
		escapedValue = newEscapedValue;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, InstantiationPackage.STRING_LITERAL__ESCAPED_VALUE, oldEscapedValue, escapedValue));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getLiteralValue() {
		return literalValue;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setLiteralValue(String newLiteralValue) {
		String oldLiteralValue = literalValue;
		literalValue = newLiteralValue;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, InstantiationPackage.STRING_LITERAL__LITERAL_VALUE, oldLiteralValue, literalValue));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Object eGet(EStructuralFeature eFeature, boolean resolve) {
		switch (eDerivedStructuralFeatureID(eFeature)) {
			case InstantiationPackage.STRING_LITERAL__ESCAPED_VALUE:
				return getEscapedValue();
			case InstantiationPackage.STRING_LITERAL__LITERAL_VALUE:
				return getLiteralValue();
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
			case InstantiationPackage.STRING_LITERAL__ESCAPED_VALUE:
				setEscapedValue((String)newValue);
				return;
			case InstantiationPackage.STRING_LITERAL__LITERAL_VALUE:
				setLiteralValue((String)newValue);
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
			case InstantiationPackage.STRING_LITERAL__ESCAPED_VALUE:
				setEscapedValue(ESCAPED_VALUE_EDEFAULT);
				return;
			case InstantiationPackage.STRING_LITERAL__LITERAL_VALUE:
				setLiteralValue(LITERAL_VALUE_EDEFAULT);
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
			case InstantiationPackage.STRING_LITERAL__ESCAPED_VALUE:
				return ESCAPED_VALUE_EDEFAULT == null ? escapedValue != null : !ESCAPED_VALUE_EDEFAULT.equals(escapedValue);
			case InstantiationPackage.STRING_LITERAL__LITERAL_VALUE:
				return LITERAL_VALUE_EDEFAULT == null ? literalValue != null : !LITERAL_VALUE_EDEFAULT.equals(literalValue);
		}
		return eDynamicIsSet(eFeature);
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	public String toString() {
		if (eIsProxy() || getEscapedValue() == null) return super.toString();
		return getEscapedValue();
	}
	
	/*
	 *  (non-Javadoc)
	 * @see org.eclipse.jem.internal.instantiation.impl.ExpressionImpl#accept0(org.eclipse.jem.internal.instantiation.ParseVisitor)
	 */
	protected void accept0(ParseVisitor visitor) {
		visitor.visit(this);
		visitor.endVisit(this);
	}

} //StringLiteralImpl
