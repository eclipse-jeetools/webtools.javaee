package org.eclipse.jem.internal.instantiation.impl;
/*******************************************************************************
 * Copyright (c)  2004 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials 
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *******************************************************************************/
/*
 *  $RCSfile: PTCharacterLiteralImpl.java,v $
 *  $Revision: 1.2 $  $Date: 2005/02/15 22:36:09 $ 
 */
import org.eclipse.emf.common.notify.Notification;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EStructuralFeature;

import org.eclipse.emf.ecore.impl.ENotificationImpl;

import org.eclipse.jem.internal.instantiation.*;
import org.eclipse.jem.internal.instantiation.PTCharacterLiteral;
import org.eclipse.jem.internal.instantiation.InstantiationPackage;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Character Literal</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.eclipse.jem.internal.instantiation.impl.PTCharacterLiteralImpl#getEscapedValue <em>Escaped Value</em>}</li>
 *   <li>{@link org.eclipse.jem.internal.instantiation.impl.PTCharacterLiteralImpl#getCharValue <em>Char Value</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class PTCharacterLiteralImpl extends PTExpressionImpl implements PTCharacterLiteral {
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
	protected PTCharacterLiteralImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected EClass eStaticClass() {
		return InstantiationPackage.eINSTANCE.getPTCharacterLiteral();
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
		charValueSet = false;
		setCharValueGen(CHAR_VALUE_EDEFAULT);
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
			eNotify(new ENotificationImpl(this, Notification.SET, InstantiationPackage.PT_CHARACTER_LITERAL__ESCAPED_VALUE, oldEscapedValue, escapedValue));
	}

	private boolean charValueSet;
	
	public char getCharValue() {
		if (!charValueSet && escapedValue != ESCAPED_VALUE_EDEFAULT) {
			String s = getEscapedValue();
			int len = s.length();
			if (len < 2 || s.charAt(0) != '\'' || s.charAt(len-1) != '\'' ) {
				throw new IllegalArgumentException();
			}
			char c = s.charAt(1);
			if (c == '\'') {
				throw new IllegalArgumentException();
			}
			if (c == '\\') {
				if (len == 4) {
					setCharValueGen(getCharEscaped(s.charAt(2)));
				} else if (len == 8) {
					//handle the case of unicode.
					int currentPosition = 2;
					int c1 = 0, c2 = 0, c3 = 0, c4 = 0;
					if (s.charAt(currentPosition++) == 'u') {
						if ((c1 = Character.getNumericValue(s.charAt(currentPosition++))) > 15
								|| c1 < 0
								|| (c2 = Character.getNumericValue(s.charAt(currentPosition++))) > 15
								|| c2 < 0
								|| (c3 = Character.getNumericValue(s.charAt(currentPosition++))) > 15
								|| c3 < 0
								|| (c4 = Character.getNumericValue(s.charAt(currentPosition++))) > 15
								|| c4 < 0){
							throw new IllegalArgumentException("illegal character literal");//$NON-NLS-1$
						} else {
							setCharValueGen((char) (((c1 * 16 + c2) * 16 + c3) * 16 + c4));
						}
					} else {
						throw new IllegalArgumentException("illegal character literal");//$NON-NLS-1$
					}
				} else {
					throw new IllegalArgumentException("illegal character literal");//$NON-NLS-1$
				}
			} else
				setCharValueGen(c);
			
			charValueSet = true;
		}
		return getCharValueGen();
	}
	
	public static char getCharEscaped(char nextChar) {
		switch(nextChar) {
			case 'b' :
				return '\b';
			case 't' :
				return '\t';
			case 'n' :
				return '\n';
			case 'f' :
				return '\f';
			case 'r' :
				return '\r';
			case '\"':
				return '\"';
			case '\'':
				return '\'';
			case '\\':
				return '\\';
			case '0' :
				return '\0';
			case '1' :
				return '\1';
			case '2' :
				return '\2';
			case '3' :
				return '\3';
			case '4' :
				return '\4';
			case '5' :
				return '\5';
			case '6' :
				return '\6';
			case '7' :
				return '\7';
			default:
				throw new IllegalArgumentException("illegal character");//$NON-NLS-1$
		}
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public char getCharValueGen() {
		return charValue;
	}

	public void setCharValue(char newCharValue) {		
		charValueSet = true;
		setCharValueGen(newCharValue);
		
		StringBuffer b = new StringBuffer(8);
		b.append('\''); // opening delimiter
		appendCharacter(newCharValue, b);
		b.append('\''); // closing delimiter
		setEscapedValueGen(b.toString());
	}
	
	public static void appendCharacter(char character, StringBuffer b) {
		switch(character) {
			case '\b' :
				b.append("\\b"); //$NON-NLS-1$
				break;
			case '\t' :
				b.append("\\t"); //$NON-NLS-1$
				break;
			case '\n' :
				b.append("\\n"); //$NON-NLS-1$
				break;
			case '\f' :
				b.append("\\f"); //$NON-NLS-1$
				break;
			case '\r' :
				b.append("\\r"); //$NON-NLS-1$
				break;
			case '\"':
				b.append("\\\""); //$NON-NLS-1$
				break;
			case '\'':
				b.append("\\\'"); //$NON-NLS-1$
				break;
			case '\\':
				b.append("\\\\"); //$NON-NLS-1$
				break;
			case '\0' :
				b.append("\\0"); //$NON-NLS-1$
				break;
			case '\1' :
				b.append("\\1"); //$NON-NLS-1$
				break;
			case '\2' :
				b.append("\\2"); //$NON-NLS-1$
				break;
			case '\3' :
				b.append("\\3"); //$NON-NLS-1$
				break;
			case '\4' :
				b.append("\\4"); //$NON-NLS-1$
				break;
			case '\5' :
				b.append("\\5"); //$NON-NLS-1$
				break;
			case '\6' :
				b.append("\\6"); //$NON-NLS-1$
				break;
			case '\7' :
				b.append("\\7"); //$NON-NLS-1$
				break;			
			default:
				// use a simple algorithm, if char is < 255 then just use it, else use it as unicode.
				if (character < 255)
					b.append(character);
				else {
					b.append("\\u");
					String s = Integer.toString((int) character, 16);
					int zeros = 4-s.length();
					while (zeros-- > 0) {
						b.append('0');
					}
					b.append(s);
				}	
		}
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setCharValueGen(char newCharValue) {
		char oldCharValue = charValue;
		charValue = newCharValue;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, InstantiationPackage.PT_CHARACTER_LITERAL__CHAR_VALUE, oldCharValue, charValue));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Object eGet(EStructuralFeature eFeature, boolean resolve) {
		switch (eDerivedStructuralFeatureID(eFeature)) {
			case InstantiationPackage.PT_CHARACTER_LITERAL__ESCAPED_VALUE:
				return getEscapedValue();
			case InstantiationPackage.PT_CHARACTER_LITERAL__CHAR_VALUE:
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
			case InstantiationPackage.PT_CHARACTER_LITERAL__ESCAPED_VALUE:
				setEscapedValue((String)newValue);
				return;
			case InstantiationPackage.PT_CHARACTER_LITERAL__CHAR_VALUE:
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
			case InstantiationPackage.PT_CHARACTER_LITERAL__ESCAPED_VALUE:
				setEscapedValue(ESCAPED_VALUE_EDEFAULT);
				return;
			case InstantiationPackage.PT_CHARACTER_LITERAL__CHAR_VALUE:
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
			case InstantiationPackage.PT_CHARACTER_LITERAL__ESCAPED_VALUE:
				return ESCAPED_VALUE_EDEFAULT == null ? escapedValue != null : !ESCAPED_VALUE_EDEFAULT.equals(escapedValue);
			case InstantiationPackage.PT_CHARACTER_LITERAL__CHAR_VALUE:
				return charValue != CHAR_VALUE_EDEFAULT;
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
} //CharacterLiteralImpl
