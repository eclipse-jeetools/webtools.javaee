/*******************************************************************************
 * Copyright (c) 2009, 2023 IBM Corporation and others.
 * This program and the accompanying materials are made
 * available under the terms of the Eclipse Public License 2.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 *
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *******************************************************************************/
/**
 * <copyright>
 * </copyright>
 *
 * $Id: OrderingOrderingType.java,v 1.1 2009/10/15 18:52:03 canderson Exp $
 */
package org.eclipse.jst.javaee.web;

import java.util.List;

import org.eclipse.jst.javaee.core.JavaEEObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Ordering Ordering Type</b></em>'.
 * <!-- end-user-doc -->
 *
 * <!-- begin-model-doc -->
 * 
 * 
 *         This element contains a sequence of "name" elements, each of
 *         which
 *         refers to an application configuration resource by the "name"
 *         declared on its web.xml fragment.  This element can also contain
 *         a single "others" element which specifies that this document
 *         comes
 *         before or after other documents within the application.
 *         See section 8.2.2 of the specification for details.
 *         
 *         @since Java EE 6, Web 3.0
 *       
 * <!-- end-model-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.eclipse.jst.javaee.web.OrderingOrderingType#getName <em>Name</em>}</li>
 *   <li>{@link org.eclipse.jst.javaee.web.OrderingOrderingType#getOthers <em>Others</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.eclipse.jst.javaee.web.internal.metadata.WebPackage#getOrderingOrderingType()
 * @extends JavaEEObject
 * @generated
 */
public interface OrderingOrderingType extends JavaEEObject {
	/**
	 * Returns the value of the '<em><b>Name</b></em>' attribute list.
	 * The list contents are of type {@link java.lang.String}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Name</em>' attribute list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Name</em>' attribute list.
	 * @see org.eclipse.jst.javaee.web.internal.metadata.WebPackage#getOrderingOrderingType_Name()
	 * @generated
	 */
	List<String> getName();

	/**
	 * Returns the value of the '<em><b>Others</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Others</em>' containment reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Others</em>' containment reference.
	 * @see #setOthers(OrderingOthersType)
	 * @see org.eclipse.jst.javaee.web.internal.metadata.WebPackage#getOrderingOrderingType_Others()
	 * @generated
	 */
	OrderingOthersType getOthers();

	/**
	 * Sets the value of the '{@link org.eclipse.jst.javaee.web.OrderingOrderingType#getOthers <em>Others</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Others</em>' containment reference.
	 * @see #getOthers()
	 * @generated
	 */
	void setOthers(OrderingOthersType value);

} // OrderingOrderingType
