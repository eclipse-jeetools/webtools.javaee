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

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.jst.j2ee.internal.webapplication.WebapplicationPackage;
/**
 * @generated
 * The web-resource-collection element is used to identify a subset of the resources and HTTP methods on those resources within a web application to which a security constraint applies. If no HTTP methods are specified, then the security constraint applies to all HTTP methods.

 */
public interface WebResourceCollection extends EObject{

public void addHTTP(String httpMethod);
public void addURL(String urlPattern);
	/**
	 * @generated This field/method will be replaced during code generation 
	 * @return The value of the WebResourceName attribute
	 * The web-resource-name contains the name of this web resource collection
	 */
	String getWebResourceName();

	/**
	 * @generated This field/method will be replaced during code generation 
	 * @param value The new value of the WebResourceName attribute
	 */
	void setWebResourceName(String value);

	/**
	 * @generated This field/method will be replaced during code generation 
	 * @return The value of the Description attribute
	 */
	String getDescription();

	/**
	 * @generated This field/method will be replaced during code generation 
	 * @param value The new value of the Description attribute
	 */
	void setDescription(String value);

	/**
	 * Returns the value of the '<em><b>Url Pattern</b></em>' attribute list.
	 * The list contents are of type {@link java.lang.String}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Url Pattern</em>' attribute list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * @since J2EE1.4
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Url Pattern</em>' attribute list.
	 * @see org.eclipse.jst.j2ee.internal.webapplication.WebapplicationPackage#getWebResourceCollection_UrlPattern()
	 * @model type="java.lang.String" unique="false" required="true"
	 * @generated
	 */
	EList getUrlPattern();

	/**
	 * Returns the value of the '<em><b>Http Method</b></em>' attribute list.
	 * The list contents are of type {@link org.eclipse.jst.j2ee.internal.webapplication.HTTPMethodTypeEnum}.
	 * The literals are from the enumeration {@link org.eclipse.jst.j2ee.internal.webapplication.HTTPMethodTypeEnum}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Http Method</em>' attribute list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * @since J2EE1.4
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Http Method</em>' attribute list.
	 * @see org.eclipse.jst.j2ee.internal.webapplication.HTTPMethodTypeEnum
	 * @see org.eclipse.jst.j2ee.internal.webapplication.WebapplicationPackage#getWebResourceCollection_HttpMethod()
	 * @model type="org.eclipse.jst.j2ee.internal.webapplication.HTTPMethodTypeEnum"
	 * @generated
	 */
	EList getHttpMethod();

	/**
	 * @deprecated : Use getHttpMethod();
	 */
	EList getHTTPs();
	
	/**
	 * @generated This field/method will be replaced during code generation 
	 * @return The SecConstraint reference
	 */
	
	
	SecurityConstraint getSecConstraint();

	/**
	 * @generated This field/method will be replaced during code generation 
	 * @param l The new value of the SecConstraint reference
	 */
	void setSecConstraint(SecurityConstraint value);

	/**
	 * @generated This field/method will be replaced during code generation 
	 * @return The list of URLs references
	 */
	EList getURLs();

	/**
	 * Returns the value of the '<em><b>Descriptions</b></em>' containment reference list.
	 * The list contents are of type {@link org.eclipse.jst.j2ee.internal.common.Description}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Descriptions</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * @since J2EE1.4
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Descriptions</em>' containment reference list.
	 * @see org.eclipse.jst.j2ee.internal.webapplication.WebapplicationPackage#getWebResourceCollection_Descriptions()
	 * @model type="org.eclipse.jst.j2ee.internal.common.Description" containment="true"
	 * @generated
	 */
	EList getDescriptions();

}














