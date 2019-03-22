/*******************************************************************************
 * Copyright (c) 2009 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 *
 * Contributors:
 * IBM Corporation - initial API and implementation
 *******************************************************************************/
package org.eclipse.jst.javaee.jca;

import java.util.List;

import org.eclipse.jst.javaee.core.Description;
import org.eclipse.jst.javaee.core.JavaEEObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>License</b></em>'.
 * <!-- end-user-doc -->
 *
 * <!-- begin-model-doc -->
 * 
 * 
 *         The licenseType specifies licensing requirements for the
 *         resource adapter module. This type specifies whether a
 *         license is required to deploy and use this resource adapter,
 *         and an optional description of the licensing terms
 *         (examples: duration of license, number of connection
 *         restrictions). It is used by the license element.
 *         
 *         @since Java EE 6, Connector 1.6
 *       
 * <!-- end-model-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.eclipse.jst.javaee.jca.License#getDescription <em>Description</em>}</li>
 *   <li>{@link org.eclipse.jst.javaee.jca.License#isLicenseRequired <em>License Required</em>}</li>
 *   <li>{@link org.eclipse.jst.javaee.jca.License#getId <em>Id</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.eclipse.jst.javaee.jca.internal.metadata.JcaPackage#getLicense()
 * @extends JavaEEObject
 * @generated
 */
public interface License extends JavaEEObject {
	/**
	 * Returns the value of the '<em><b>Description</b></em>' containment reference list.
	 * The list contents are of type {@link org.eclipse.jst.javaee.core.Description}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Description</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Description</em>' containment reference list.
	 * @see org.eclipse.jst.javaee.jca.internal.metadata.JcaPackage#getLicense_Description()
	 * @generated
	 */
	List<Description> getDescription();

	/**
	 * Returns the value of the '<em><b>License Required</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * 
	 * 
	 *             The element license-required specifies whether a
	 *             license is required to deploy and use the
	 *             resource adapter. This element must be one of
	 *             the following, "true" or "false".
	 *             
	 *             @since Java EE 6, Connector 1.6
	 *           
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>License Required</em>' attribute.
	 * @see #isSetLicenseRequired()
	 * @see #unsetLicenseRequired()
	 * @see #setLicenseRequired(boolean)
	 * @see org.eclipse.jst.javaee.jca.internal.metadata.JcaPackage#getLicense_LicenseRequired()
	 * @generated
	 */
	boolean isLicenseRequired();

	/**
	 * Sets the value of the '{@link org.eclipse.jst.javaee.jca.License#isLicenseRequired <em>License Required</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>License Required</em>' attribute.
	 * @see #isSetLicenseRequired()
	 * @see #unsetLicenseRequired()
	 * @see #isLicenseRequired()
	 * @generated
	 */
	void setLicenseRequired(boolean value);

	/**
	 * Unsets the value of the '{@link org.eclipse.jst.javaee.jca.License#isLicenseRequired <em>License Required</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isSetLicenseRequired()
	 * @see #isLicenseRequired()
	 * @see #setLicenseRequired(boolean)
	 * @generated
	 */
	void unsetLicenseRequired();

	/**
	 * Returns whether the value of the '{@link org.eclipse.jst.javaee.jca.License#isLicenseRequired <em>License Required</em>}' attribute is set.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return whether the value of the '<em>License Required</em>' attribute is set.
	 * @see #unsetLicenseRequired()
	 * @see #isLicenseRequired()
	 * @see #setLicenseRequired(boolean)
	 * @generated
	 */
	boolean isSetLicenseRequired();

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
	 * @see org.eclipse.jst.javaee.jca.internal.metadata.JcaPackage#getLicense_Id()
	 * @generated
	 */
	String getId();

	/**
	 * Sets the value of the '{@link org.eclipse.jst.javaee.jca.License#getId <em>Id</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Id</em>' attribute.
	 * @see #getId()
	 * @generated
	 */
	void setId(String value);

} // License
