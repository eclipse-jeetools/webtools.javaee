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

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Description</b></em>'.
 * <!-- end-user-doc -->
 *
 * <!-- begin-model-doc -->
 * @version J2EE1.4
 * The description type is used by a description element to
 * provide text describing the parent element.  The elements
 * that use this type should include any information that the
 * Deployment Component's Deployment File file producer wants
 * to provide to the consumer of the Deployment Component's
 * Deployment File (i.e., to the Deployer). Typically, the
 * tools used by such a Deployment File consumer will display
 * the description when processing the parent element that
 * contains the description.
 * 
 * The lang attribute defines the language that the
 * description is provided in. The default value is "en" (English).
 * <!-- end-model-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.eclipse.jst.j2ee.common.Description#getLang <em>Lang</em>}</li>
 *   <li>{@link org.eclipse.jst.j2ee.common.Description#getValue <em>Value</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.eclipse.jst.j2ee.common.CommonPackage#getDescription()
 * @model
 * @generated
 * @since 1.0 */
public interface Description extends EObject{
	/**
	 * Returns the value of the '<em><b>Lang</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Lang</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Lang</em>' attribute.
	 * @see #setLang(String)
	 * @see org.eclipse.jst.j2ee.common.CommonPackage#getDescription_Lang()
	 * @model
	 * @generated
	 */
	String getLang();

	/**
	 * Sets the value of the '{@link org.eclipse.jst.j2ee.common.Description#getLang <em>Lang</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Lang</em>' attribute.
	 * @see #getLang()
	 * @generated
	 */
	void setLang(String value);

	/**
	 * Returns the value of the '<em><b>Value</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Value</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Value</em>' attribute.
	 * @see #setValue(String)
	 * @see org.eclipse.jst.j2ee.common.CommonPackage#getDescription_Value()
	 * @model
	 * @generated
	 */
	String getValue();

	/**
	 * Sets the value of the '{@link org.eclipse.jst.j2ee.common.Description#getValue <em>Value</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Value</em>' attribute.
	 * @see #getValue()
	 * @generated
	 */
	void setValue(String value);

} // Description
