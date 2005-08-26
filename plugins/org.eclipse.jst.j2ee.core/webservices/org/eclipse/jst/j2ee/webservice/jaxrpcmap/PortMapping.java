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
package org.eclipse.jst.j2ee.webservice.jaxrpcmap;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Port Mapping</b></em>'.
 * <!-- end-user-doc -->
 *
 * <!-- begin-model-doc -->
 * The port-mapping defines the mapping of the WSDL port name attribute to the Java name used to generate the Generated Service Interface method get<java-name>.
 * 
 * Used in: service-interface-mapping
 * <!-- end-model-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.eclipse.jst.j2ee.webservice.jaxrpcmap.PortMapping#getId <em>Id</em>}</li>
 *   <li>{@link org.eclipse.jst.j2ee.webservice.jaxrpcmap.PortMapping#getPortName <em>Port Name</em>}</li>
 *   <li>{@link org.eclipse.jst.j2ee.webservice.jaxrpcmap.PortMapping#getJavaPortName <em>Java Port Name</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.eclipse.jst.j2ee.webservice.jaxrpcmap.JaxrpcmapPackage#getPortMapping()
 * @model
 * @generated
 */
public interface PortMapping extends EObject{
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
	 * @see org.eclipse.jst.j2ee.webservice.jaxrpcmap.JaxrpcmapPackage#getPortMapping_Id()
	 * @model
	 * @generated
	 */
  String getId();

	/**
	 * Sets the value of the '{@link org.eclipse.jst.j2ee.webservice.jaxrpcmap.PortMapping#getId <em>Id</em>}' attribute.
	 * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Id</em>' attribute.
	 * @see #getId()
	 * @generated
	 */
  void setId(String value);

	/**
	 * Returns the value of the '<em><b>Port Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Port Name</em>' attribute isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * The port-name is the attribute value of a name attribute of a WSDL port element.
	 * 
	 * Used in: port-mapping
	 * 
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Port Name</em>' attribute.
	 * @see #setPortName(String)
	 * @see org.eclipse.jst.j2ee.webservice.jaxrpcmap.JaxrpcmapPackage#getPortMapping_PortName()
	 * @model
	 * @generated
	 */
  String getPortName();

	/**
	 * Sets the value of the '{@link org.eclipse.jst.j2ee.webservice.jaxrpcmap.PortMapping#getPortName <em>Port Name</em>}' attribute.
	 * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Port Name</em>' attribute.
	 * @see #getPortName()
	 * @generated
	 */
  void setPortName(String value);

	/**
	 * Returns the value of the '<em><b>Java Port Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Java Port Name</em>' attribute isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * The java-port-name element is the string to use as the port name in Java.  It is used in generating the Generated Service Interface method
	 * get<java-port-name>.
	 * 
	 * Used in: port-mapping
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Java Port Name</em>' attribute.
	 * @see #setJavaPortName(String)
	 * @see org.eclipse.jst.j2ee.webservice.jaxrpcmap.JaxrpcmapPackage#getPortMapping_JavaPortName()
	 * @model
	 * @generated
	 */
  String getJavaPortName();

	/**
	 * Sets the value of the '{@link org.eclipse.jst.j2ee.webservice.jaxrpcmap.PortMapping#getJavaPortName <em>Java Port Name</em>}' attribute.
	 * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Java Port Name</em>' attribute.
	 * @see #getJavaPortName()
	 * @generated
	 */
  void setJavaPortName(String value);

} // PortMapping
