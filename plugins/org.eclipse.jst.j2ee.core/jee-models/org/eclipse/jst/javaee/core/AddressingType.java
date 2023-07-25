/*******************************************************************************
 * Copyright (c) 2009, 2019 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 *
 * Contributors:
 * IBM Corporation - initial API and implementation
 *******************************************************************************/
package org.eclipse.jst.javaee.core;


/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Addressing Type</b></em>'.
 * <!-- end-user-doc -->
 *
 * <!-- begin-model-doc -->
 * 
 * 
 *         This specifies the WS-Addressing requirements for a JAX-WS web service.
 *         It corresponds to javax.xml.ws.soap.Addressing annotation or its
 *         feature javax.xml.ws.soap.AddressingFeature.
 *         
 *         If the "enabled" element is "true", WS-Addressing is enabled.
 *         It means that the endpoint supports WS-Addressing but does not require
 *         its use. The default value for "enabled" is "true".
 *         
 *         If the WS-Addressing is enabled and the "required" element is "true",
 *         it means that the endpoint requires WS-Addressing. The default value
 *         for "required" is "false".
 *         
 *         If WS-Addressing is enabled, the "responses" element determines
 *         if an endpoint requires the use of only anonymous responses,
 *         or only non-anonymous responses, or all. The value of the "responses"
 *         element must be one of the following:
 *         
 *         ANONYMOUS
 *         NON_ANONYMOUS
 *         ALL
 *         
 *         The default value for the "responses" is ALL.
 *         
 *         @since Java EE 6, Web Services Client 1.3
 *       
 * <!-- end-model-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.eclipse.jst.javaee.core.AddressingType#isEnabled <em>Enabled</em>}</li>
 *   <li>{@link org.eclipse.jst.javaee.core.AddressingType#isRequired <em>Required</em>}</li>
 *   <li>{@link org.eclipse.jst.javaee.core.AddressingType#getResponses <em>Responses</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.eclipse.jst.javaee.core.internal.metadata.JavaeePackage#getAddressingType()
 * @extends JavaEEObject
 * @generated
 */
public interface AddressingType extends JavaEEObject {
	/**
	 * Returns the value of the '<em><b>Enabled</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Enabled</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Enabled</em>' attribute.
	 * @see #isSetEnabled()
	 * @see #unsetEnabled()
	 * @see #setEnabled(boolean)
	 * @see org.eclipse.jst.javaee.core.internal.metadata.JavaeePackage#getAddressingType_Enabled()
	 * @generated
	 */
	boolean isEnabled();

	/**
	 * Sets the value of the '{@link org.eclipse.jst.javaee.core.AddressingType#isEnabled <em>Enabled</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Enabled</em>' attribute.
	 * @see #isSetEnabled()
	 * @see #unsetEnabled()
	 * @see #isEnabled()
	 * @generated
	 */
	void setEnabled(boolean value);

	/**
	 * Unsets the value of the '{@link org.eclipse.jst.javaee.core.AddressingType#isEnabled <em>Enabled</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isSetEnabled()
	 * @see #isEnabled()
	 * @see #setEnabled(boolean)
	 * @generated
	 */
	void unsetEnabled();

	/**
	 * Returns whether the value of the '{@link org.eclipse.jst.javaee.core.AddressingType#isEnabled <em>Enabled</em>}' attribute is set.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return whether the value of the '<em>Enabled</em>' attribute is set.
	 * @see #unsetEnabled()
	 * @see #isEnabled()
	 * @see #setEnabled(boolean)
	 * @generated
	 */
	boolean isSetEnabled();

	/**
	 * Returns the value of the '<em><b>Required</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Required</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Required</em>' attribute.
	 * @see #isSetRequired()
	 * @see #unsetRequired()
	 * @see #setRequired(boolean)
	 * @see org.eclipse.jst.javaee.core.internal.metadata.JavaeePackage#getAddressingType_Required()
	 * @generated
	 */
	boolean isRequired();

	/**
	 * Sets the value of the '{@link org.eclipse.jst.javaee.core.AddressingType#isRequired <em>Required</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Required</em>' attribute.
	 * @see #isSetRequired()
	 * @see #unsetRequired()
	 * @see #isRequired()
	 * @generated
	 */
	void setRequired(boolean value);

	/**
	 * Unsets the value of the '{@link org.eclipse.jst.javaee.core.AddressingType#isRequired <em>Required</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isSetRequired()
	 * @see #isRequired()
	 * @see #setRequired(boolean)
	 * @generated
	 */
	void unsetRequired();

	/**
	 * Returns whether the value of the '{@link org.eclipse.jst.javaee.core.AddressingType#isRequired <em>Required</em>}' attribute is set.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return whether the value of the '<em>Required</em>' attribute is set.
	 * @see #unsetRequired()
	 * @see #isRequired()
	 * @see #setRequired(boolean)
	 * @generated
	 */
	boolean isSetRequired();

	/**
	 * Returns the value of the '<em><b>Responses</b></em>' attribute.
	 * The literals are from the enumeration {@link org.eclipse.jst.javaee.core.AddressingResponsesType}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Responses</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Responses</em>' attribute.
	 * @see org.eclipse.jst.javaee.core.AddressingResponsesType
	 * @see #isSetResponses()
	 * @see #unsetResponses()
	 * @see #setResponses(AddressingResponsesType)
	 * @see org.eclipse.jst.javaee.core.internal.metadata.JavaeePackage#getAddressingType_Responses()
	 * @generated
	 */
	AddressingResponsesType getResponses();

	/**
	 * Sets the value of the '{@link org.eclipse.jst.javaee.core.AddressingType#getResponses <em>Responses</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Responses</em>' attribute.
	 * @see org.eclipse.jst.javaee.core.AddressingResponsesType
	 * @see #isSetResponses()
	 * @see #unsetResponses()
	 * @see #getResponses()
	 * @generated
	 */
	void setResponses(AddressingResponsesType value);

	/**
	 * Unsets the value of the '{@link org.eclipse.jst.javaee.core.AddressingType#getResponses <em>Responses</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isSetResponses()
	 * @see #getResponses()
	 * @see #setResponses(AddressingResponsesType)
	 * @generated
	 */
	void unsetResponses();

	/**
	 * Returns whether the value of the '{@link org.eclipse.jst.javaee.core.AddressingType#getResponses <em>Responses</em>}' attribute is set.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return whether the value of the '<em>Responses</em>' attribute is set.
	 * @see #unsetResponses()
	 * @see #getResponses()
	 * @see #setResponses(AddressingResponsesType)
	 * @generated
	 */
	boolean isSetResponses();

} // AddressingType
