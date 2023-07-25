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
 * $Id: OrderingType.java,v 1.1 2009/10/15 18:52:03 canderson Exp $
 */
package org.eclipse.jst.javaee.web;

import org.eclipse.jst.javaee.core.JavaEEObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Ordering Type</b></em>'.
 * <!-- end-user-doc -->
 *
 * <!-- begin-model-doc -->
 * 
 * 
 *         Please see section 8.2.2 of the specification for details.
 *         
 *         @since Java EE 6, Web 3.0
 *       
 * <!-- end-model-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.eclipse.jst.javaee.web.OrderingType#getAfter <em>After</em>}</li>
 *   <li>{@link org.eclipse.jst.javaee.web.OrderingType#getBefore <em>Before</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.eclipse.jst.javaee.web.internal.metadata.WebPackage#getOrderingType()
 * @extends JavaEEObject
 * @generated
 */
public interface OrderingType extends JavaEEObject {
	/**
	 * Returns the value of the '<em><b>After</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>After</em>' containment reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>After</em>' containment reference.
	 * @see #setAfter(OrderingOrderingType)
	 * @see org.eclipse.jst.javaee.web.internal.metadata.WebPackage#getOrderingType_After()
	 * @generated
	 */
	OrderingOrderingType getAfter();

	/**
	 * Sets the value of the '{@link org.eclipse.jst.javaee.web.OrderingType#getAfter <em>After</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>After</em>' containment reference.
	 * @see #getAfter()
	 * @generated
	 */
	void setAfter(OrderingOrderingType value);

	/**
	 * Returns the value of the '<em><b>Before</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Before</em>' containment reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Before</em>' containment reference.
	 * @see #setBefore(OrderingOrderingType)
	 * @see org.eclipse.jst.javaee.web.internal.metadata.WebPackage#getOrderingType_Before()
	 * @generated
	 */
	OrderingOrderingType getBefore();

	/**
	 * Sets the value of the '{@link org.eclipse.jst.javaee.web.OrderingType#getBefore <em>Before</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Before</em>' containment reference.
	 * @see #getBefore()
	 * @generated
	 */
	void setBefore(OrderingOrderingType value);

} // OrderingType
