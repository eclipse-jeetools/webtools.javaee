/*******************************************************************************
 * Copyright (c) 2007 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 *
 * Contributors:
 * IBM Corporation - initial API and implementation
 *******************************************************************************/
package org.eclipse.jst.javaee.web;

import java.util.List;

import org.eclipse.jst.javaee.core.Description;
import org.eclipse.jst.javaee.core.JavaEEObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>User Data Constraint</b></em>'.
 * <!-- end-user-doc -->
 *
 * <!-- begin-model-doc -->
 * 
 * 
 *         The user-data-constraintType is used to indicate how
 *         data communicated between the client and container should be
 *         protected.
 *         
 *         Used in: security-constraint
 *         
 *         @since Java EE 5, Web 2.5
 *       
 * <!-- end-model-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.eclipse.jst.javaee.web.UserDataConstraint#getDescriptions <em>Descriptions</em>}</li>
 *   <li>{@link org.eclipse.jst.javaee.web.UserDataConstraint#getTransportGuarantee <em>Transport Guarantee</em>}</li>
 *   <li>{@link org.eclipse.jst.javaee.web.UserDataConstraint#getId <em>Id</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.eclipse.jst.javaee.web.internal.metadata.WebPackage#getUserDataConstraint()
 * @extends JavaEEObject
 * @generated
 */
public interface UserDataConstraint extends JavaEEObject {
	/**
	 * Returns the value of the '<em><b>Descriptions</b></em>' containment reference list.
	 * The list contents are of type {@link org.eclipse.jst.javaee.core.Description}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Descriptions</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Descriptions</em>' containment reference list.
	 * @see org.eclipse.jst.javaee.web.internal.metadata.WebPackage#getUserDataConstraint_Descriptions()
	 * @generated
	 */
	List<Description> getDescriptions();

	/**
	 * Returns the value of the '<em><b>Transport Guarantee</b></em>' attribute.
	 * The literals are from the enumeration {@link org.eclipse.jst.javaee.web.TransportGuaranteeType}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Transport Guarantee</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Transport Guarantee</em>' attribute.
	 * @see org.eclipse.jst.javaee.web.TransportGuaranteeType
	 * @see #isSetTransportGuarantee()
	 * @see #unsetTransportGuarantee()
	 * @see #setTransportGuarantee(TransportGuaranteeType)
	 * @see org.eclipse.jst.javaee.web.internal.metadata.WebPackage#getUserDataConstraint_TransportGuarantee()
	 * @generated
	 */
	TransportGuaranteeType getTransportGuarantee();

	/**
	 * Sets the value of the '{@link org.eclipse.jst.javaee.web.UserDataConstraint#getTransportGuarantee <em>Transport Guarantee</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Transport Guarantee</em>' attribute.
	 * @see org.eclipse.jst.javaee.web.TransportGuaranteeType
	 * @see #isSetTransportGuarantee()
	 * @see #unsetTransportGuarantee()
	 * @see #getTransportGuarantee()
	 * @generated
	 */
	void setTransportGuarantee(TransportGuaranteeType value);

	/**
	 * Unsets the value of the '{@link org.eclipse.jst.javaee.web.UserDataConstraint#getTransportGuarantee <em>Transport Guarantee</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isSetTransportGuarantee()
	 * @see #getTransportGuarantee()
	 * @see #setTransportGuarantee(TransportGuaranteeType)
	 * @generated
	 */
	void unsetTransportGuarantee();

	/**
	 * Returns whether the value of the '{@link org.eclipse.jst.javaee.web.UserDataConstraint#getTransportGuarantee <em>Transport Guarantee</em>}' attribute is set.
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
	 * Returns the value of the '<em><b>Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Id</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Id</em>' attribute.
	 * @see #setId(String)
	 * @see org.eclipse.jst.javaee.web.internal.metadata.WebPackage#getUserDataConstraint_Id()
	 * @generated
	 */
	String getId();

	/**
	 * Sets the value of the '{@link org.eclipse.jst.javaee.web.UserDataConstraint#getId <em>Id</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Id</em>' attribute.
	 * @see #getId()
	 * @generated
	 */
	void setId(String value);

} // UserDataConstraint