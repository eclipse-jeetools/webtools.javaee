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
 *  $RCSfile: CharacterLiteralImpl.java,v $
 *  $Revision: 1.1 $  $Date: 2004/01/19 22:50:15 $ 
 */
import org.eclipse.emf.common.notify.Notification;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EStructuralFeature;

import org.eclipse.emf.ecore.impl.ENotificationImpl;

import org.eclipse.jem.internal.instantiation.*;
import org.eclipse.jem.internal.instantiation.CharacterLiteral;
import org.eclipse.jem.internal.instantiation.InstantiationPackage;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Character Literal</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.eclipse.jem.internal.instantiation.impl.CharacterLiteralImpl#getEscapedValue <em>Escaped Value</em>}</li>
 *   <li>{@link org.eclipse.jem.internal.instantiation.impl.CharacterLiteralImpl#getCharValue <em>Char Value</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class CharacterLiteralImpl extends ExpressionImpl implements CharacterLiteral {
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
	 * The default value of the '{@link #getCharValue() <em>Char Value</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getCharValue()
	 * @generated
	 * @ordered
	 */
	protected static final char CHAR_VALUE_EDEFAULT = '\u0000';

	/**
	 * The cached value of the '{@link #getCharValue() <em>Char Value</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getCharValue()
	 * @generated
	 * @ordered
	 */
	protected char charValue = CHAR_VALUE_EDEFAULT;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected CharacterLiteralImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected EClass eStaticClass() {
		return InstantiationPackage.eINSTANCE.getCharacterLiteral();
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
			eNotify(new ENotificationImpl(this, Notification.SET, InstantiationPackage.CHARACTER_LITERAL__ESCAPED_VALUE, oldEscapedValue, escapedValue));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public char getCharValue() {
		return charValue;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setCharValue(char newCharValue) {
		char oldCharValue = charValue;
		charValue = newCharValue;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, InstantiationPackage.CHARACTER_LITERAL__CHAR_VALUE, oldCharValue, charValue));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Object eGet(EStructuralFeature eFeature, boolean resolve) {
		switch (eDerivedStructuralFeatureID(eFeature)) {
			case InstantiationPackage.CHARACTER_LITERAL__ESCAPED_VALUE:
				return getEscapedValue();
			case InstantiationPackage.CHARACTER_LITERAL__CHAR_VALUE:
				return new Character(getCharValue());
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
			case InstantiationPackage.CHARACTER_LITERAL__ESCAPED_VALUE:
				setEscapedValue((String)newValue);
				return;
			case InstantiationPackage.CHARACTER_LITERAL__CHAR_VALUE:
				setCharValue(((Character)newValue).charValue());
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
			case InstantiationPackage.CHARACTER_LITERAL__ESCAPED_VALUE:
				setEscapedValue(ESCAPED_VALUE_EDEFAULT);
				return;
			case InstantiationPackage.CHARACTER_LITERAL__CHAR_VALUE:
				setCharValue(CHAR_VALUE_EDEFAULT);
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
			case InstantiationPackage.CHARACTER_LITERAL__ESCAPED_VALUE:
				return ESCAPED_VALUE_EDEFAULT == null ? escapedValue != null : !ESCAPED_VALUE_EDEFAULT.equals(escapedValue);
			case InstantiationPackage.CHARACTER_LITERAL__CHAR_VALUE:
				return charValue != CHAR_VALUE_EDEFAULT;
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
} //CharacterLiteralImpl
