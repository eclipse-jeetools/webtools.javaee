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
package org.eclipse.jst.j2ee.common;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Description Group</b></em>'.
 * <!-- end-user-doc -->
 *
 * <!-- begin-model-doc -->
 * @version J2EE1.4
 * This group keeps the usage of the contained description related
 * elements consistent across J2EE deployment descriptors.
 * <!-- end-model-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.eclipse.jst.j2ee.common.DescriptionGroup#getIcons <em>Icons</em>}</li>
 *   <li>{@link org.eclipse.jst.j2ee.common.DescriptionGroup#getDisplayNames <em>Display Names</em>}</li>
 *   <li>{@link org.eclipse.jst.j2ee.common.DescriptionGroup#getDescriptions <em>Descriptions</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.eclipse.jst.j2ee.common.CommonPackage#getDescriptionGroup()
 * @model
 * @generated
 * @since 1.0 */
public interface DescriptionGroup extends EObject{
	/**
	 * Returns the value of the '<em><b>Icons</b></em>' containment reference list.
	 * The list contents are of type {@link org.eclipse.jst.j2ee.common.IconType}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Icons</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Icons</em>' containment reference list.
	 * @see org.eclipse.jst.j2ee.common.CommonPackage#getDescriptionGroup_Icons()
	 * @model type="org.eclipse.jst.j2ee.common.IconType" containment="true"
	 * @generated
	 */
	EList getIcons();

	/**
	 * Returns the value of the '<em><b>Display Names</b></em>' containment reference list.
	 * The list contents are of type {@link org.eclipse.jst.j2ee.common.DisplayName}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Display Names</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Display Names</em>' containment reference list.
	 * @see org.eclipse.jst.j2ee.common.CommonPackage#getDescriptionGroup_DisplayNames()
	 * @model type="org.eclipse.jst.j2ee.common.DisplayName" containment="true"
	 * @generated
	 */
	EList getDisplayNames();

	/**
	 * Returns the value of the '<em><b>Descriptions</b></em>' containment reference list.
	 * The list contents are of type {@link org.eclipse.jst.j2ee.common.Description}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Descriptions</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Descriptions</em>' containment reference list.
	 * @see org.eclipse.jst.j2ee.common.CommonPackage#getDescriptionGroup_Descriptions()
	 * @model type="org.eclipse.jst.j2ee.common.Description" containment="true"
	 * @generated
	 */
	EList getDescriptions();

} // DescriptionGroup
