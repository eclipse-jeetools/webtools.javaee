/*******************************************************************************
 * Copyright (c) 2001, 2004 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 * IBM Corporation - initial API and implementation
 *******************************************************************************/
package org.eclipse.jst.j2ee.webapplication;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.jst.j2ee.internal.webapplication.WebapplicationPackage;

/**
 * <!-- begin-user-doc -->
 *  @deprecated since 4/25/2003 use list on {@link org.eclipse.jst.j2ee.internal.webapplication.WebResourceCollection}
 * <ul>
 *   <li>{@link org.eclipse.jst.j2ee.internal.webapplication.HTTPMethodType#getHttpMethod <em>Http Method</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.eclipse.jst.j2ee.internal.webapplication.WebapplicationPackage#getHTTPMethodType()
 * @model 
 * @generated
 */
public interface HTTPMethodType extends EObject{
	/**
	 * Returns the value of the '<em><b>Http Method</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Http Method</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Http Method</em>' attribute.
	 * @see #setHttpMethod(String)
	 * @see org.eclipse.jst.j2ee.internal.webapplication.WebapplicationPackage#getHTTPMethodType_HttpMethod()
	 * @model 
	 * @generated
	 */
	String getHttpMethod();

	/**
	 * Sets the value of the '{@link org.eclipse.jst.j2ee.internal.webapplication.HTTPMethodType#getHttpMethod <em>Http Method</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Http Method</em>' attribute.
	 * @see #getHttpMethod()
	 * @generated
	 */
	void setHttpMethod(String value);

} // HTTPMethodType
