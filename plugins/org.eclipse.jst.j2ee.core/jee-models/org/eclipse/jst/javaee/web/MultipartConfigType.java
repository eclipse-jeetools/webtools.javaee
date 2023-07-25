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
 * $Id: MultipartConfigType.java,v 1.2 2010/01/15 14:32:06 canderson Exp $
 */
package org.eclipse.jst.javaee.web;

import java.math.BigInteger;

import org.eclipse.jst.javaee.core.JavaEEObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Multipart Config Type</b></em>'.
 * <!-- end-user-doc -->
 *
 * <!-- begin-model-doc -->
 * 
 * 
 *         This element specifies configuration information related to the
 *         handling of multipart/form-data requests.
 *         
 *         @since Java EE 6, Web 3.0
 *       
 * <!-- end-model-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.eclipse.jst.javaee.web.MultipartConfigType#getLocation <em>Location</em>}</li>
 *   <li>{@link org.eclipse.jst.javaee.web.MultipartConfigType#getMaxFileSize <em>Max File Size</em>}</li>
 *   <li>{@link org.eclipse.jst.javaee.web.MultipartConfigType#getMaxRequestSize <em>Max Request Size</em>}</li>
 *   <li>{@link org.eclipse.jst.javaee.web.MultipartConfigType#getFileSizeThreshold <em>File Size Threshold</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.eclipse.jst.javaee.web.internal.metadata.WebPackage#getMultipartConfigType()
 * @extends JavaEEObject
 * @generated
 */
public interface MultipartConfigType extends JavaEEObject {
	/**
	 * Returns the value of the '<em><b>Location</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Location</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * 
	 * 
	 *             The directory location where uploaded files will be stored
	 *             
	 *           
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Location</em>' attribute.
	 * @see #setLocation(String)
	 * @see org.eclipse.jst.javaee.web.internal.metadata.WebPackage#getMultipartConfigType_Location()
	 * @generated
	 */
	String getLocation();

	/**
	 * Sets the value of the '{@link org.eclipse.jst.javaee.web.MultipartConfigType#getLocation <em>Location</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Location</em>' attribute.
	 * @see #getLocation()
	 * @generated
	 */
	void setLocation(String value);

	/**
	 * Returns the value of the '<em><b>Max File Size</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Max File Size</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * 
	 * 
	 *             The maximum size limit of uploaded files
	 *             
	 *           
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Max File Size</em>' attribute.
	 * @see #isSetMaxFileSize()
	 * @see #unsetMaxFileSize()
	 * @see #setMaxFileSize(long)
	 * @see org.eclipse.jst.javaee.web.internal.metadata.WebPackage#getMultipartConfigType_MaxFileSize()
	 * @generated
	 */
	long getMaxFileSize();

	/**
	 * Sets the value of the '{@link org.eclipse.jst.javaee.web.MultipartConfigType#getMaxFileSize <em>Max File Size</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Max File Size</em>' attribute.
	 * @see #isSetMaxFileSize()
	 * @see #unsetMaxFileSize()
	 * @see #getMaxFileSize()
	 * @generated
	 */
	void setMaxFileSize(long value);

	/**
	 * Unsets the value of the '{@link org.eclipse.jst.javaee.web.MultipartConfigType#getMaxFileSize <em>Max File Size</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isSetMaxFileSize()
	 * @see #getMaxFileSize()
	 * @see #setMaxFileSize(long)
	 * @generated
	 */
	void unsetMaxFileSize();

	/**
	 * Returns whether the value of the '{@link org.eclipse.jst.javaee.web.MultipartConfigType#getMaxFileSize <em>Max File Size</em>}' attribute is set.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return whether the value of the '<em>Max File Size</em>' attribute is set.
	 * @see #unsetMaxFileSize()
	 * @see #getMaxFileSize()
	 * @see #setMaxFileSize(long)
	 * @generated
	 */
	boolean isSetMaxFileSize();

	/**
	 * Returns the value of the '<em><b>Max Request Size</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Max Request Size</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * 
	 * 
	 *             The maximum size limit of multipart/form-data requests
	 *             
	 *           
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Max Request Size</em>' attribute.
	 * @see #isSetMaxRequestSize()
	 * @see #unsetMaxRequestSize()
	 * @see #setMaxRequestSize(long)
	 * @see org.eclipse.jst.javaee.web.internal.metadata.WebPackage#getMultipartConfigType_MaxRequestSize()
	 * @generated
	 */
	long getMaxRequestSize();

	/**
	 * Sets the value of the '{@link org.eclipse.jst.javaee.web.MultipartConfigType#getMaxRequestSize <em>Max Request Size</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Max Request Size</em>' attribute.
	 * @see #isSetMaxRequestSize()
	 * @see #unsetMaxRequestSize()
	 * @see #getMaxRequestSize()
	 * @generated
	 */
	void setMaxRequestSize(long value);

	/**
	 * Unsets the value of the '{@link org.eclipse.jst.javaee.web.MultipartConfigType#getMaxRequestSize <em>Max Request Size</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isSetMaxRequestSize()
	 * @see #getMaxRequestSize()
	 * @see #setMaxRequestSize(long)
	 * @generated
	 */
	void unsetMaxRequestSize();

	/**
	 * Returns whether the value of the '{@link org.eclipse.jst.javaee.web.MultipartConfigType#getMaxRequestSize <em>Max Request Size</em>}' attribute is set.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return whether the value of the '<em>Max Request Size</em>' attribute is set.
	 * @see #unsetMaxRequestSize()
	 * @see #getMaxRequestSize()
	 * @see #setMaxRequestSize(long)
	 * @generated
	 */
	boolean isSetMaxRequestSize();

	/**
	 * Returns the value of the '<em><b>File Size Threshold</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>File Size Threshold</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * 
	 * 
	 *             The size threshold after which an uploaded file will be
	 *             written to disk
	 *             
	 *           
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>File Size Threshold</em>' attribute.
	 * @see #setFileSizeThreshold(BigInteger)
	 * @see org.eclipse.jst.javaee.web.internal.metadata.WebPackage#getMultipartConfigType_FileSizeThreshold()
	 * @generated
	 */
	BigInteger getFileSizeThreshold();

	/**
	 * Sets the value of the '{@link org.eclipse.jst.javaee.web.MultipartConfigType#getFileSizeThreshold <em>File Size Threshold</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>File Size Threshold</em>' attribute.
	 * @see #getFileSizeThreshold()
	 * @generated
	 */
	void setFileSizeThreshold(BigInteger value);

} // MultipartConfigType
