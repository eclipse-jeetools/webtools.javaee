/*******************************************************************************
 * Copyright (c) 2001, 2005 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 * IBM Corporation - initial API and implementation
 *******************************************************************************/
package org.eclipse.jst.j2ee.webapplication;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;

/**
 * The user-data-constraint element is used to indicate how data communicated between the client and container should be protected
 */
public interface UserDataConstraint extends EObject{
	/**
	 * @generated This field/method will be replaced during code generation 
	 * @return The value of the Description attribute
	 * A description for the user data constraint.
	 */
	String getDescription();

	/**
	 * @generated This field/method will be replaced during code generation 
	 * @param value The new value of the Description attribute
	 */
	void setDescription(String value);

	/**
	 * @generated This field/method will be replaced during code generation 
	 * @return The value of the TransportGuarantee attribute
	 * The transport-guarantee element specifies that the communication between client 
	 * and server should be NONE, INTEGRAL, or CONFIDENTIAL. NONE means that the
	 * application does not require any
	 * transport guarantees. A value of INTEGRAL means that the application requires
	 * that the data sent between the client and server be sent in such a way that it
	 * can't be changed in transit. CONFIDENTIAL means that the application requires
	 * that the data be transmitted in a fashion that prevents other entities from
	 * observing the contents of the transmission. In most cases, the presence of the
	 * INTEGRAL or CONFIDENTIAL flag will indicate that the use of SSL is required.
	 */
	TransportGuaranteeType getTransportGuarantee();

	/**
	 * Sets the value of the '{@link org.eclipse.jst.j2ee.webapplication.UserDataConstraint#getTransportGuarantee <em>Transport Guarantee</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Transport Guarantee</em>' attribute.
	 * @see org.eclipse.jst.j2ee.webapplication.TransportGuaranteeType
	 * @see #isSetTransportGuarantee()
	 * @see #unsetTransportGuarantee()
	 * @see #getTransportGuarantee()
	 * @generated
	 */
	void setTransportGuarantee(TransportGuaranteeType value);

	/**
	 * Unsets the value of the '{@link org.eclipse.jst.j2ee.webapplication.UserDataConstraint#getTransportGuarantee <em>Transport Guarantee</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isSetTransportGuarantee()
	 * @see #getTransportGuarantee()
	 * @see #setTransportGuarantee(TransportGuaranteeType)
	 * @generated
	 */
	void unsetTransportGuarantee();

	/**
	 * Returns whether the value of the '{@link org.eclipse.jst.j2ee.webapplication.UserDataConstraint#getTransportGuarantee <em>Transport Guarantee</em>}' attribute is set.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return whether the value of the '<em>Transport Guarantee</em>' attribute is set.
	 * @see #unsetTransportGuarantee()
	 * @see #getTransportGuarantee()
	 * @see #setTransportGuarantee(TransportGuaranteeType)
	 * @generated
	 */
	boolean isSetTransportGuarantee();

	/**
	 * @generated This field/method will be replaced during code generation 
	 * @return The SecConstraint reference
	 */
	SecurityConstraint getSecConstraint();

	/**
	 * @generated This field/method will be replaced during code generation 
	 * @param l The new value of the SecConstraint reference
	 */
	void setSecConstraint(SecurityConstraint value);

	/**
	 * Returns the value of the '<em><b>Descriptions</b></em>' containment reference list.
	 * The list contents are of type {@link org.eclipse.jst.j2ee.common.Description}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Descriptions</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * @version J2EE1.4
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Descriptions</em>' containment reference list.
	 * @see org.eclipse.jst.j2ee.webapplication.WebapplicationPackage#getUserDataConstraint_Descriptions()
	 * @model type="org.eclipse.jst.j2ee.common.Description" containment="true"
	 * @generated
	 */
	EList getDescriptions();

}














