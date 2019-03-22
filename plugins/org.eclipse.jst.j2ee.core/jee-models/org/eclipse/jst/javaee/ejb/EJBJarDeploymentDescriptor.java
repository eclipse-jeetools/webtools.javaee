/*******************************************************************************
 * Copyright (c) 2007 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 *
 * Contributors:
 * IBM Corporation - initial API and implementation
 *******************************************************************************/
package org.eclipse.jst.javaee.ejb;

import java.util.Map;

import org.eclipse.emf.ecore.util.FeatureMap;

import org.eclipse.jst.javaee.core.JavaEEObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>EJB Jar Deployment Descriptor</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.eclipse.jst.javaee.ejb.EJBJarDeploymentDescriptor#getMixed <em>Mixed</em>}</li>
 *   <li>{@link org.eclipse.jst.javaee.ejb.EJBJarDeploymentDescriptor#getXMLNSPrefixMap <em>XMLNS Prefix Map</em>}</li>
 *   <li>{@link org.eclipse.jst.javaee.ejb.EJBJarDeploymentDescriptor#getXSISchemaLocation <em>XSI Schema Location</em>}</li>
 *   <li>{@link org.eclipse.jst.javaee.ejb.EJBJarDeploymentDescriptor#getEjbJar <em>Ejb Jar</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.eclipse.jst.javaee.ejb.internal.metadata.EjbPackage#getEJBJarDeploymentDescriptor()
 * @extends JavaEEObject
 * @generated
 */
public interface EJBJarDeploymentDescriptor extends JavaEEObject {
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
	 * @see org.eclipse.jst.javaee.ejb.internal.metadata.EjbPackage#getEJBJarDeploymentDescriptor_Mixed()
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
	 * @see org.eclipse.jst.javaee.ejb.internal.metadata.EjbPackage#getEJBJarDeploymentDescriptor_XMLNSPrefixMap()
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
	 * @see org.eclipse.jst.javaee.ejb.internal.metadata.EjbPackage#getEJBJarDeploymentDescriptor_XSISchemaLocation()
	 * @generated
	 */
	Map<String, String> getXSISchemaLocation();

	/**
	 * Returns the value of the '<em><b>Ejb Jar</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * 
	 * 
	 *         This is the root of the ejb-jar deployment descriptor.
	 *         
	 *         @since Java EE 5, EJB 3.0
	 *       
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Ejb Jar</em>' containment reference.
	 * @see #setEjbJar(EJBJar)
	 * @see org.eclipse.jst.javaee.ejb.internal.metadata.EjbPackage#getEJBJarDeploymentDescriptor_EjbJar()
	 * @generated
	 */
	EJBJar getEjbJar();

	/**
	 * Sets the value of the '{@link org.eclipse.jst.javaee.ejb.EJBJarDeploymentDescriptor#getEjbJar <em>Ejb Jar</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Ejb Jar</em>' containment reference.
	 * @see #getEjbJar()
	 * @generated
	 */
	void setEjbJar(EJBJar value);

} // EJBJarDeploymentDescriptor