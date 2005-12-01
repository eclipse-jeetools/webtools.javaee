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
 * A representation of the model object '<em><b>QName</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.eclipse.jst.j2ee.common.QName#getNamespaceURI <em>Namespace URI</em>}</li>
 *   <li>{@link org.eclipse.jst.j2ee.common.QName#getLocalPart <em>Local Part</em>}</li>
 *   <li>{@link org.eclipse.jst.j2ee.common.QName#getCombinedQName <em>Combined QName</em>}</li>
 *   <li>{@link org.eclipse.jst.j2ee.common.QName#getInternalPrefixOrNsURI <em>Internal Prefix Or Ns URI</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.eclipse.jst.j2ee.common.CommonPackage#getQName()
 * @model
 * @generated
 * @since 1.0 */
public interface QName extends EObject{
	/**
	 * Returns the value of the '<em><b>Namespace URI</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Namespace URI</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Namespace URI</em>' attribute.
	 * @see #setNamespaceURI(String)
	 * @see org.eclipse.jst.j2ee.common.CommonPackage#getQName_NamespaceURI()
	 * @model
	 * @generated
	 */
	String getNamespaceURI();

	/**
	 * Sets the value of the '{@link org.eclipse.jst.j2ee.common.QName#getNamespaceURI <em>Namespace URI</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Namespace URI</em>' attribute.
	 * @see #getNamespaceURI()
	 * @generated
	 */
	void setNamespaceURI(String value);

	/**
	 * Returns the value of the '<em><b>Local Part</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Local Part</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Local Part</em>' attribute.
	 * @see #setLocalPart(String)
	 * @see org.eclipse.jst.j2ee.common.CommonPackage#getQName_LocalPart()
	 * @model
	 * @generated
	 */
	String getLocalPart();

	/**
	 * Sets the value of the '{@link org.eclipse.jst.j2ee.common.QName#getLocalPart <em>Local Part</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Local Part</em>' attribute.
	 * @see #getLocalPart()
	 * @generated
	 */
	void setLocalPart(String value);

	/**
	 * Returns the value of the '<em><b>Combined QName</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Combined QName</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Combined QName</em>' attribute.
	 * @see #setCombinedQName(String)
	 * @see org.eclipse.jst.j2ee.common.CommonPackage#getQName_CombinedQName()
	 * @model
	 * @generated
	 */
	String getCombinedQName();

	/**
	 * Sets the value of the '{@link org.eclipse.jst.j2ee.common.QName#getCombinedQName <em>Combined QName</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Combined QName</em>' attribute.
	 * @see #getCombinedQName()
	 * @generated
	 */
	void setCombinedQName(String value);

	/**
	 * Returns the value of the '<em><b>Internal Prefix Or Ns URI</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * Internal field used by implementation classes.  Clients should use 
	 * {@link #getNamespaceURI()}.
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Internal Prefix Or Ns URI</em>' attribute.
	 * @see #setInternalPrefixOrNsURI(String)
	 * @see org.eclipse.jst.j2ee.common.CommonPackage#getQName_InternalPrefixOrNsURI()
	 * @model
	 * @generated
	 */
	String getInternalPrefixOrNsURI();

	/**
	 * Sets the value of the '{@link org.eclipse.jst.j2ee.common.QName#getInternalPrefixOrNsURI <em>Internal Prefix Or Ns URI</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * Internal field used by implementation classes.  Clients should use 
	 * {@link #setValues(String, String, String)}.
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Internal Prefix Or Ns URI</em>' attribute.
	 * @see #getInternalPrefixOrNsURI()
	 * @generated
	 */
	void setInternalPrefixOrNsURI(String value);

	/**
	 * Sets all the values for this qName
	 * @param prefix    - The namespace prefix
	 * @param nsURI     - The namespace URI
	 * @param localPart - The local part
	 */
	void setValues(String prefix, String nsURI, String localPart);
	

} // QName
