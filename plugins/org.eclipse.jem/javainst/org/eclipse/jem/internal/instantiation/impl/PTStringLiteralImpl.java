/*******************************************************************************
 * Copyright (c) 2004, 2005 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *******************************************************************************/
package org.eclipse.jem.internal.instantiation.impl;
/*
 *  $RCSfile: PTStringLiteralImpl.java,v $
 *  $Revision: 1.3 $  $Date: 2005/08/24 20:20:24 $ 
 */
import org.eclipse.emf.common.notify.Notification;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EStructuralFeature;

import org.eclipse.emf.ecore.impl.ENotificationImpl;

import org.eclipse.jem.internal.instantiation.*;
import org.eclipse.jem.internal.instantiation.InstantiationPackage;
import org.eclipse.jem.internal.instantiation.PTStringLiteral;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>String Literal</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.eclipse.jem.internal.instantiation.impl.PTStringLiteralImpl#getEscapedValue <em>Escaped Value</em>}</li>
 *   <li>{@link org.eclipse.jem.internal.instantiation.impl.PTStringLiteralImpl#getLiteralValue <em>Literal Value</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class PTStringLiteralImpl extends PTExpressionImpl implements PTStringLiteral {
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
	protected PTStringLiteralImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected EClass eStaticClass() {
		return InstantiationPackage.eINSTANCE.getPTStringLiteral();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getEscapedValue() {
		return escapedValue;
	}

	public void setEscapedValue(String newEscapedValue) {
		stringLiteralSet = false;
		setLiteralValueGen(LITERAL_VALUE_EDEFAULT);
		setEscapedValueGen(newEscapedValue);		
	}
	
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setEscapedValueGen(String newEscapedValue) {
		String oldEscapedValue = escapedValue;
		escapedValue = newEscapedValue;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, InstantiationPackage.PT_STRING_LITERAL__ESCAPED_VALUE, oldEscapedValue, escapedValue));
	}

	private boolean stringLiteralSet;
	
	public String getLiteralValue() {
		if (!stringLiteralSet && escapedValue != ESCAPED_VALUE_EDEFAULT) {
			String s = getEscapedValue();
			int len = s.length();
			if (len < 2 || s.charAt(0) != '\"' || s.charAt(len-1) != '\"' ) {
				throw new IllegalArgumentException();
			}
			
			StringBuffer buffer = new StringBuffer(len);
			int stop = len-1;
			for (int i = 1; i < stop; i++) {
				char ati = s.charAt(i);
				if (ati == '\\') {
					if (++i < stop) {
						ati = s.charAt(i);
						if (ati == 'u') {
							int c1 = 0, c2 = 0, c3 = 0, c4 = 0;
							if (i+4 >= stop
									|| (c1 = Character.getNumericValue(s.charAt(++i))) > 15
									|| c1 < 0
									|| (c2 = Character.getNumericValue(s.charAt(++i))) > 15
									|| c2 < 0
									|| (c3 = Character.getNumericValue(s.charAt(++i))) > 15
									|| c3 < 0
									|| (c4 = Character.getNumericValue(s.charAt(++i))) > 15
									|| c4 < 0){
								throw new IllegalArgumentException("illegal string literal");//$NON-NLS-1$
							} else
								buffer.append((char) (((c1 * 16 + c2) * 16 + c3) * 16 + c4));
						} else
							buffer.append(PTCharacterLiteralImpl.getCharEscaped(ati));
					} else
						throw new IllegalArgumentException("illegal string literal");//$NON-NLS-1$
				} else
					buffer.append(ati);	
			}
			
			stringLiteralSet = true;
			setLiteralValueGen(buffer.toString());
		}
		
		return getLiteralValueGen();
	}
	
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getLiteralValueGen() {
		return literalValue;
	}

	public void setLiteralValue(String newLiteralValue) {
		stringLiteralSet = true;
		setLiteralValueGen(newLiteralValue);
		
		StringBuffer b = new StringBuffer(newLiteralValue.length()+2);
		b.append('\"'); // opening delimiter
		int stop = newLiteralValue.length();
		for (int i = 0; i < stop; i++) {
			PTCharacterLiteralImpl.appendCharacter(newLiteralValue.charAt(i), b);	
		}
		b.append('\"'); // closing delimiter
		setEscapedValueGen(b.toString());
		
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setLiteralValueGen(String newLiteralValue) {
		String oldLiteralValue = literalValue;
		literalValue = newLiteralValue;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, InstantiationPackage.PT_STRING_LITERAL__LITERAL_VALUE, oldLiteralValue, literalValue));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Object eGet(EStructuralFeature eFeature, boolean resolve) {
		switch (eDerivedStructuralFeatureID(eFeature)) {
			case InstantiationPackage.PT_STRING_LITERAL__ESCAPED_VALUE:
				return getEscapedValue();
			case InstantiationPackage.PT_STRING_LITERAL__LITERAL_VALUE:
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
			case InstantiationPackage.PT_STRING_LITERAL__ESCAPED_VALUE:
				setEscapedValue((String)newValue);
				return;
			case InstantiationPackage.PT_STRING_LITERAL__LITERAL_VALUE:
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
			case InstantiationPackage.PT_STRING_LITERAL__ESCAPED_VALUE:
				setEscapedValue(ESCAPED_VALUE_EDEFAULT);
				return;
			case InstantiationPackage.PT_STRING_LITERAL__LITERAL_VALUE:
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
			case InstantiationPackage.PT_STRING_LITERAL__ESCAPED_VALUE:
				return ESCAPED_VALUE_EDEFAULT == null ? escapedValue != null : !ESCAPED_VALUE_EDEFAULT.equals(escapedValue);
			case InstantiationPackage.PT_STRING_LITERAL__LITERAL_VALUE:
				return LITERAL_VALUE_EDEFAULT == null ? literalValue != null : !LITERAL_VALUE_EDEFAULT.equals(literalValue);
		}
		return eDynamicIsSet(eFeature);
	}

	public String toString() {
		return super.toString();	// Really want toString to be final at PTExpression, but EMF doesn't allow it.
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
