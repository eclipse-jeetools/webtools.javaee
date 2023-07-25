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
 * $Id: AbsoluteOrderingType.java,v 1.1 2009/10/15 18:52:03 canderson Exp $
 */
package org.eclipse.jst.javaee.web;

import java.util.List;

import org.eclipse.emf.ecore.util.FeatureMap;

import org.eclipse.jst.javaee.core.JavaEEObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Absolute Ordering Type</b></em>'.
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
 *   <li>{@link org.eclipse.jst.javaee.web.AbsoluteOrderingType#getGroup <em>Group</em>}</li>
 *   <li>{@link org.eclipse.jst.javaee.web.AbsoluteOrderingType#getName <em>Name</em>}</li>
 *   <li>{@link org.eclipse.jst.javaee.web.AbsoluteOrderingType#getOthers <em>Others</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.eclipse.jst.javaee.web.internal.metadata.WebPackage#getAbsoluteOrderingType()
 * @extends JavaEEObject
 * @generated
 */
public interface AbsoluteOrderingType extends JavaEEObject {
	/**
	 * Returns the value of the '<em><b>Group</b></em>' attribute list.
	 * The list contents are of type {@link org.eclipse.emf.ecore.util.FeatureMap.Entry}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Group</em>' attribute list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Group</em>' attribute list.
	 * @see org.eclipse.jst.javaee.web.internal.metadata.WebPackage#getAbsoluteOrderingType_Group()
	 * @generated
	 */
	FeatureMap getGroup();

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
	 * @see org.eclipse.jst.javaee.web.internal.metadata.WebPackage#getAbsoluteOrderingType_Name()
	 * @generated
	 */
	List<String> getName();

	/**
	 * Returns the value of the '<em><b>Others</b></em>' containment reference list.
	 * The list contents are of type {@link org.eclipse.jst.javaee.web.OrderingOthersType}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Others</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Others</em>' containment reference list.
	 * @see org.eclipse.jst.javaee.web.internal.metadata.WebPackage#getAbsoluteOrderingType_Others()
	 * @generated
	 */
	List<OrderingOthersType> getOthers();

} // AbsoluteOrderingType
