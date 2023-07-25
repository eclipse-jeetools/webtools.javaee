/*******************************************************************************
 * Copyright (c) 2007, 2019 IBM Corporation and others.
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

import org.eclipse.jst.javaee.core.JavaEEObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Locale Encoding Mapping List</b></em>'.
 * <!-- end-user-doc -->
 *
 * <!-- begin-model-doc -->
 * 
 * 
 *         The locale-encoding-mapping-list contains one or more
 *         locale-encoding-mapping(s).
 *         
 *         @since Java EE 5, Web 2.5
 *       
 * <!-- end-model-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.eclipse.jst.javaee.web.LocaleEncodingMappingList#getLocalEncodingMappings <em>Local Encoding Mappings</em>}</li>
 *   <li>{@link org.eclipse.jst.javaee.web.LocaleEncodingMappingList#getId <em>Id</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.eclipse.jst.javaee.web.internal.metadata.WebPackage#getLocaleEncodingMappingList()
 * @extends JavaEEObject
 * @generated
 */
public interface LocaleEncodingMappingList extends JavaEEObject {
	/**
	 * Returns the value of the '<em><b>Local Encoding Mappings</b></em>' containment reference list.
	 * The list contents are of type {@link org.eclipse.jst.javaee.web.LocaleEncodingMapping}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Local Encoding Mappings</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Local Encoding Mappings</em>' containment reference list.
	 * @see org.eclipse.jst.javaee.web.internal.metadata.WebPackage#getLocaleEncodingMappingList_LocalEncodingMappings()
	 * @generated
	 */
	List<LocaleEncodingMapping> getLocalEncodingMappings();

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
	 * @see org.eclipse.jst.javaee.web.internal.metadata.WebPackage#getLocaleEncodingMappingList_Id()
	 * @generated
	 */
	String getId();

	/**
	 * Sets the value of the '{@link org.eclipse.jst.javaee.web.LocaleEncodingMappingList#getId <em>Id</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Id</em>' attribute.
	 * @see #getId()
	 * @generated
	 */
	void setId(String value);

} // LocaleEncodingMappingList