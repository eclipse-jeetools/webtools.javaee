/*******************************************************************************
 * Copyright (c) 2007, 2019 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 *
 * Contributors:
 * IBM Corporation - initial API and implementation
 *******************************************************************************/
package org.eclipse.jst.j2ee.webservice.wsdd;

import org.eclipse.jst.j2ee.common.J2EEEObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Addressing Type</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.eclipse.jst.j2ee.webservice.wsdd.AddressingType#isEnabled <em>Enabled</em>}</li>
 *   <li>{@link org.eclipse.jst.j2ee.webservice.wsdd.AddressingType#isRequired <em>Required</em>}</li>
 *   <li>{@link org.eclipse.jst.j2ee.webservice.wsdd.AddressingType#getResponses <em>Responses</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.eclipse.jst.j2ee.webservice.wsdd.WsddPackage#getAddressingType()
 * @model
 * @generated
 */
public interface AddressingType extends J2EEEObject {
	/**
	 * Returns the value of the '<em><b>Enabled</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Enabled</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Enabled</em>' attribute.
	 * @see #setEnabled(boolean)
	 * @see org.eclipse.jst.j2ee.webservice.wsdd.WsddPackage#getAddressingType_Enabled()
	 * @model
	 * @generated
	 */
	boolean isEnabled();

	/**
	 * Sets the value of the '{@link org.eclipse.jst.j2ee.webservice.wsdd.AddressingType#isEnabled <em>Enabled</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Enabled</em>' attribute.
	 * @see #isEnabled()
	 * @generated
	 */
	void setEnabled(boolean value);

	/**
	 * Returns the value of the '<em><b>Required</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Required</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Required</em>' attribute.
	 * @see #setRequired(boolean)
	 * @see org.eclipse.jst.j2ee.webservice.wsdd.WsddPackage#getAddressingType_Required()
	 * @model
	 * @generated
	 */
	boolean isRequired();

	/**
	 * Sets the value of the '{@link org.eclipse.jst.j2ee.webservice.wsdd.AddressingType#isRequired <em>Required</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Required</em>' attribute.
	 * @see #isRequired()
	 * @generated
	 */
	void setRequired(boolean value);

	/**
	 * Returns the value of the '<em><b>Responses</b></em>' attribute.
	 * The literals are from the enumeration {@link org.eclipse.jst.j2ee.webservice.wsdd.AddressingResponsesType}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Responses</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Responses</em>' attribute.
	 * @see org.eclipse.jst.j2ee.webservice.wsdd.AddressingResponsesType
	 * @see #setResponses(AddressingResponsesType)
	 * @see org.eclipse.jst.j2ee.webservice.wsdd.WsddPackage#getAddressingType_Responses()
	 * @model
	 * @generated
	 */
	AddressingResponsesType getResponses();

	/**
	 * Sets the value of the '{@link org.eclipse.jst.j2ee.webservice.wsdd.AddressingType#getResponses <em>Responses</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Responses</em>' attribute.
	 * @see org.eclipse.jst.j2ee.webservice.wsdd.AddressingResponsesType
	 * @see #getResponses()
	 * @generated
	 */
	void setResponses(AddressingResponsesType value);

} // AddressingType
