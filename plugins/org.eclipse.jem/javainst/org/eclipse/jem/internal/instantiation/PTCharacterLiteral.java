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
 *  $RCSfile: PTCharacterLiteral.java,v $
 *  $Revision: 1.2 $  $Date: 2004/02/03 23:18:28 $ 
 */
package org.eclipse.jem.internal.instantiation;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Character Literal</b></em>'.
 * <!-- end-user-doc -->
 *
 * <!-- begin-model-doc -->
 * This represents a primitive character expression, e.g. 'a' or '\r'.
 * <!-- end-model-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.eclipse.jem.internal.instantiation.CharacterLiteral#getEscapedValue <em>Escaped Value</em>}</li>
 *   <li>{@link org.eclipse.jem.internal.instantiation.CharacterLiteral#getCharValue <em>Char Value</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.eclipse.jem.internal.instantiation.InstantiationPackage#getCharacterLiteral()
 * @model 
 * @generated
 */
public interface PTCharacterLiteral extends PTExpression{
	/**
	 * Returns the value of the '<em><b>Escaped Value</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * This is the escaped character. In other words the literal exactly as it is in the java file. E.g. 'a' or '\r'. Including the surrounding single quotes themselves.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Escaped Value</em>' attribute.
	 * @see #setEscapedValue(String)
	 * @see org.eclipse.jem.internal.instantiation.InstantiationPackage#getPTCharacterLiteral_EscapedValue()
	 * @model 
	 * @generated
	 */
	String getEscapedValue();

	/**
	 * Sets the value of the '{@link org.eclipse.jem.internal.instantiation.PTCharacterLiteral#getEscapedValue <em>Escaped Value</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Escaped Value</em>' attribute.
	 * @see #getEscapedValue()
	 * @generated
	 */
	void setEscapedValue(String value);

	/**
	 * Returns the value of the '<em><b>Char Value</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * The actual primitive character without the quotes. Escape are evaluated and turned into their character value. This is a transient field. If not set when created when queried it will create it. Typically it will be set when created from an AST node since the node already has evaluated it.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Char Value</em>' attribute.
	 * @see #setCharValue(char)
	 * @see org.eclipse.jem.internal.instantiation.InstantiationPackage#getPTCharacterLiteral_CharValue()
	 * @model transient="true"
	 * @generated
	 */
	char getCharValue();

	/**
	 * Sets the value of the '{@link org.eclipse.jem.internal.instantiation.PTCharacterLiteral#getCharValue <em>Char Value</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Char Value</em>' attribute.
	 * @see #getCharValue()
	 * @generated
	 */
	void setCharValue(char value);

} // CharacterLiteral
