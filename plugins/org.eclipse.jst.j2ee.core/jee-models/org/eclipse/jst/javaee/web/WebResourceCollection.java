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

import org.eclipse.jst.javaee.core.Description;
import org.eclipse.jst.javaee.core.JavaEEObject;
import org.eclipse.jst.javaee.core.UrlPatternType;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Resource Collection</b></em>'.
 * <!-- end-user-doc -->
 *
 * <!-- begin-model-doc -->
 * 
 * 
 *         The web-resource-collectionType is used to identify the
 *         resources and HTTP methods on those resources to which a
 *         security constraint applies. If no HTTP methods are specified,
 *         then the security constraint applies to all HTTP methods.
 *         If HTTP methods are specified by http-method-omission
 *         elements, the security constraint applies to all methods
 *         except those identified in the collection.
 *         http-method-omission and http-method elements are never
 *         mixed in the same collection. 
 *         
 *         Used in: security-constraint
 *         
 *         @since Java EE 5, Web 2.5
 *       
 * <!-- end-model-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.eclipse.jst.javaee.web.WebResourceCollection#getWebResourceName <em>Web Resource Name</em>}</li>
 *   <li>{@link org.eclipse.jst.javaee.web.WebResourceCollection#getDescriptions <em>Descriptions</em>}</li>
 *   <li>{@link org.eclipse.jst.javaee.web.WebResourceCollection#getUrlPatterns <em>Url Patterns</em>}</li>
 *   <li>{@link org.eclipse.jst.javaee.web.WebResourceCollection#getHttpMethods <em>Http Methods</em>}</li>
 *   <li>{@link org.eclipse.jst.javaee.web.WebResourceCollection#getHttpMethodOmission <em>Http Method Omission</em>}</li>
 *   <li>{@link org.eclipse.jst.javaee.web.WebResourceCollection#getId <em>Id</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.eclipse.jst.javaee.web.internal.metadata.WebPackage#getWebResourceCollection()
 * @extends JavaEEObject
 * @generated
 */
public interface WebResourceCollection extends JavaEEObject {
	/**
	 * Returns the value of the '<em><b>Web Resource Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * 
	 * 
	 *             The web-resource-name contains the name of this web
	 *             resource collection.
	 *             
	 *             @since Java EE 5, Web 2.5
	 *           
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Web Resource Name</em>' attribute.
	 * @see #setWebResourceName(String)
	 * @see org.eclipse.jst.javaee.web.internal.metadata.WebPackage#getWebResourceCollection_WebResourceName()
	 * @generated
	 */
	String getWebResourceName();

	/**
	 * Sets the value of the '{@link org.eclipse.jst.javaee.web.WebResourceCollection#getWebResourceName <em>Web Resource Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Web Resource Name</em>' attribute.
	 * @see #getWebResourceName()
	 * @generated
	 */
	void setWebResourceName(String value);

	/**
	 * Returns the value of the '<em><b>Descriptions</b></em>' containment reference list.
	 * The list contents are of type {@link org.eclipse.jst.javaee.core.Description}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Descriptions</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Descriptions</em>' containment reference list.
	 * @see org.eclipse.jst.javaee.web.internal.metadata.WebPackage#getWebResourceCollection_Descriptions()
	 * @generated
	 */
	List<Description> getDescriptions();

	/**
	 * Returns the value of the '<em><b>Url Patterns</b></em>' containment reference list.
	 * The list contents are of type {@link org.eclipse.jst.javaee.core.UrlPatternType}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Url Patterns</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Url Patterns</em>' containment reference list.
	 * @see org.eclipse.jst.javaee.web.internal.metadata.WebPackage#getWebResourceCollection_UrlPatterns()
	 * @generated
	 */
	List<UrlPatternType> getUrlPatterns();

	/**
	 * Returns the value of the '<em><b>Http Methods</b></em>' attribute list.
	 * The list contents are of type {@link java.lang.String}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Http Methods</em>' attribute list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * 
	 * 
	 *               Each http-method names an HTTP method to which the
	 *               constraint applies.
	 *               
	 *               @since Java EE 5, Web 2.5
	 *             
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Http Methods</em>' attribute list.
	 * @see org.eclipse.jst.javaee.web.internal.metadata.WebPackage#getWebResourceCollection_HttpMethods()
	 * @generated
	 */
	List<String> getHttpMethods();

	/**
	 * Returns the value of the '<em><b>Http Method Omission</b></em>' attribute list.
	 * The list contents are of type {@link java.lang.String}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * 
	 * 
	 *               Each http-method-omission names an HTTP method to
	 *               which the constraint does not apply.
	 *               
	 *               @since Java EE 6, Web 3.0
	 *             
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Http Method Omission</em>' attribute list.
	 * @see org.eclipse.jst.javaee.web.internal.metadata.WebPackage#getWebResourceCollection_HttpMethodOmission()
	 * @generated
	 */
	List<String> getHttpMethodOmission();

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
	 * @see org.eclipse.jst.javaee.web.internal.metadata.WebPackage#getWebResourceCollection_Id()
	 * @generated
	 */
	String getId();

	/**
	 * Sets the value of the '{@link org.eclipse.jst.javaee.web.WebResourceCollection#getId <em>Id</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Id</em>' attribute.
	 * @see #getId()
	 * @generated
	 */
	void setId(String value);

} // WebResourceCollection