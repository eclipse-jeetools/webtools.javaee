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
package org.eclipse.jst.javaee.applicationclient;

import java.util.Map;

import org.eclipse.emf.ecore.util.FeatureMap;

import org.eclipse.jst.javaee.core.JavaEEObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Application Client Deployment Descriptor</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.eclipse.jst.javaee.applicationclient.ApplicationClientDeploymentDescriptor#getMixed <em>Mixed</em>}</li>
 *   <li>{@link org.eclipse.jst.javaee.applicationclient.ApplicationClientDeploymentDescriptor#getXMLNSPrefixMap <em>XMLNS Prefix Map</em>}</li>
 *   <li>{@link org.eclipse.jst.javaee.applicationclient.ApplicationClientDeploymentDescriptor#getXSISchemaLocation <em>XSI Schema Location</em>}</li>
 *   <li>{@link org.eclipse.jst.javaee.applicationclient.ApplicationClientDeploymentDescriptor#getApplicationClient <em>Application Client</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.eclipse.jst.javaee.applicationclient.internal.metadata.ApplicationclientPackage#getApplicationClientDeploymentDescriptor()
 * @extends JavaEEObject
 * @generated
 */
public interface ApplicationClientDeploymentDescriptor extends JavaEEObject {
	/**
	 * Returns the value of the '<em><b>Mixed</b></em>' attribute list.
	 * The list contents are of type {@link org.eclipse.emf.ecore.util.FeatureMap.Entry}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Mixed</em>' attribute list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Mixed</em>' attribute list.
	 * @see org.eclipse.jst.javaee.applicationclient.internal.metadata.ApplicationclientPackage#getApplicationClientDeploymentDescriptor_Mixed()
	 * @generated
	 */
	FeatureMap getMixed();

	/**
	 * Returns the value of the '<em><b>XMLNS Prefix Map</b></em>' map.
	 * The key is of type {@link java.lang.String},
	 * and the value is of type {@link java.lang.String},
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>XMLNS Prefix Map</em>' map isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>XMLNS Prefix Map</em>' map.
	 * @see org.eclipse.jst.javaee.applicationclient.internal.metadata.ApplicationclientPackage#getApplicationClientDeploymentDescriptor_XMLNSPrefixMap()
	 * @generated
	 */
	Map<String, String> getXMLNSPrefixMap();

	/**
	 * Returns the value of the '<em><b>XSI Schema Location</b></em>' map.
	 * The key is of type {@link java.lang.String},
	 * and the value is of type {@link java.lang.String},
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>XSI Schema Location</em>' map isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>XSI Schema Location</em>' map.
	 * @see org.eclipse.jst.javaee.applicationclient.internal.metadata.ApplicationclientPackage#getApplicationClientDeploymentDescriptor_XSISchemaLocation()
	 * @generated
	 */
	Map<String, String> getXSISchemaLocation();

	/**
	 * Returns the value of the '<em><b>Application Client</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * 
	 * 
	 *         The application-client element is the root element of an
	 *         application client deployment descriptor.  The application
	 *         client deployment descriptor describes the EJB components
	 *         and external resources referenced by the application
	 *         client.
	 *         
	 *         @since Java EE 5, Application Client 5
	 *       
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Application Client</em>' containment reference.
	 * @see #setApplicationClient(ApplicationClient)
	 * @see org.eclipse.jst.javaee.applicationclient.internal.metadata.ApplicationclientPackage#getApplicationClientDeploymentDescriptor_ApplicationClient()
	 * @generated
	 */
	ApplicationClient getApplicationClient();

	/**
	 * Sets the value of the '{@link org.eclipse.jst.javaee.applicationclient.ApplicationClientDeploymentDescriptor#getApplicationClient <em>Application Client</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Application Client</em>' containment reference.
	 * @see #getApplicationClient()
	 * @generated
	 */
	void setApplicationClient(ApplicationClient value);

} // ApplicationClientDeploymentDescriptor