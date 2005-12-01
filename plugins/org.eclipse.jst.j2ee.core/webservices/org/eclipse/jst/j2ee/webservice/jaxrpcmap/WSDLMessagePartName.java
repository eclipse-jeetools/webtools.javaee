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
package org.eclipse.jst.j2ee.webservice.jaxrpcmap;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>WSDL Message Part Name</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.eclipse.jst.j2ee.webservice.jaxrpcmap.WSDLMessagePartName#getId <em>Id</em>}</li>
 *   <li>{@link org.eclipse.jst.j2ee.webservice.jaxrpcmap.WSDLMessagePartName#getWsdlMessagePartName <em>Wsdl Message Part Name</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.eclipse.jst.j2ee.webservice.jaxrpcmap.JaxrpcmapPackage#getWSDLMessagePartName()
 * @model
 * @generated
 * @since 1.0 */
public interface WSDLMessagePartName extends EObject{
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
	 * @see org.eclipse.jst.j2ee.webservice.jaxrpcmap.JaxrpcmapPackage#getWSDLMessagePartName_Id()
	 * @model
	 * @generated
	 */
  String getId();

	/**
	 * Sets the value of the '{@link org.eclipse.jst.j2ee.webservice.jaxrpcmap.WSDLMessagePartName#getId <em>Id</em>}' attribute.
	 * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Id</em>' attribute.
	 * @see #getId()
	 * @generated
	 */
  void setId(String value);

	/**
	 * Returns the value of the '<em><b>Wsdl Message Part Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Wsdl Message Part Name</em>' attribute isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
	 * @return the value of the '<em>Wsdl Message Part Name</em>' attribute.
	 * @see #setWsdlMessagePartName(String)
	 * @see org.eclipse.jst.j2ee.webservice.jaxrpcmap.JaxrpcmapPackage#getWSDLMessagePartName_WsdlMessagePartName()
	 * @model
	 * @generated
	 */
  String getWsdlMessagePartName();

	/**
	 * Sets the value of the '{@link org.eclipse.jst.j2ee.webservice.jaxrpcmap.WSDLMessagePartName#getWsdlMessagePartName <em>Wsdl Message Part Name</em>}' attribute.
	 * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Wsdl Message Part Name</em>' attribute.
	 * @see #getWsdlMessagePartName()
	 * @generated
	 */
  void setWsdlMessagePartName(String value);

} // WSDLMessagePartName
