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
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Local Encoding Mapping List</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.eclipse.jst.j2ee.webapplication.LocalEncodingMappingList#getLocalEncodingMappings <em>Local Encoding Mappings</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.eclipse.jst.j2ee.webapplication.WebapplicationPackage#getLocalEncodingMappingList()
 * @model
 * @generated
 * @since 1.0
 */
public interface LocalEncodingMappingList extends EObject{
	/**
	 * Returns the value of the '<em><b>Local Encoding Mappings</b></em>' containment reference list.
	 * The list contents are of type {@link org.eclipse.jst.j2ee.webapplication.LocalEncodingMapping}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Local Encoding Mappings</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Local Encoding Mappings</em>' containment reference list.
	 * @see org.eclipse.jst.j2ee.webapplication.WebapplicationPackage#getLocalEncodingMappingList_LocalEncodingMappings()
	 * @model type="org.eclipse.jst.j2ee.webapplication.LocalEncodingMapping" containment="true" required="true"
	 * @generated
	 */
	EList getLocalEncodingMappings();

} // LocalEncodingMappingList
