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
 * A representation of the model object '<em><b>Method Param Parts Mapping</b></em>'.
 * <!-- end-user-doc -->
 *
 * <!-- begin-model-doc -->
 * The method-param-parts-mapping element defines the mapping between a Java method parameters and a wsdl-message.
 * 
 * Used in: service-endpoint-method-mapping
 * <!-- end-model-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.eclipse.jst.j2ee.webservice.jaxrpcmap.MethodParamPartsMapping#getId <em>Id</em>}</li>
 *   <li>{@link org.eclipse.jst.j2ee.webservice.jaxrpcmap.MethodParamPartsMapping#getParamPosition <em>Param Position</em>}</li>
 *   <li>{@link org.eclipse.jst.j2ee.webservice.jaxrpcmap.MethodParamPartsMapping#getParamType <em>Param Type</em>}</li>
 *   <li>{@link org.eclipse.jst.j2ee.webservice.jaxrpcmap.MethodParamPartsMapping#getWsdlMessageMapping <em>Wsdl Message Mapping</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.eclipse.jst.j2ee.webservice.jaxrpcmap.JaxrpcmapPackage#getMethodParamPartsMapping()
 * @model
 * @generated
 * @since 1.0 */
public interface MethodParamPartsMapping extends EObject{
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
	 * @see org.eclipse.jst.j2ee.webservice.jaxrpcmap.JaxrpcmapPackage#getMethodParamPartsMapping_Id()
	 * @model
	 * @generated
	 */
  String getId();

	/**
	 * Sets the value of the '{@link org.eclipse.jst.j2ee.webservice.jaxrpcmap.MethodParamPartsMapping#getId <em>Id</em>}' attribute.
	 * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Id</em>' attribute.
	 * @see #getId()
	 * @generated
	 */
  void setId(String value);

	/**
	 * Returns the value of the '<em><b>Param Position</b></em>' attribute.
	 * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Param Position</em>' attribute isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * The param-position element defines the position of a parameter within a Java method.  It must be an integer starting from 0.
	 * 
	 * Used in: method-param-parts-mapping
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Param Position</em>' attribute.
	 * @see #setParamPosition(String)
	 * @see org.eclipse.jst.j2ee.webservice.jaxrpcmap.JaxrpcmapPackage#getMethodParamPartsMapping_ParamPosition()
	 * @model
	 * @generated
	 */
  String getParamPosition();

	/**
	 * Sets the value of the '{@link org.eclipse.jst.j2ee.webservice.jaxrpcmap.MethodParamPartsMapping#getParamPosition <em>Param Position</em>}' attribute.
	 * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Param Position</em>' attribute.
	 * @see #getParamPosition()
	 * @generated
	 */
  void setParamPosition(String value);

	/**
	 * Returns the value of the '<em><b>Param Type</b></em>' attribute.
	 * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Param Type</em>' attribute isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * The param-type element defines the Java type of a parameter within a Java method. It must be defined by a fully qualified name of a class.
	 * 
	 * Used in: method-param-parts-mapping
	 * 
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Param Type</em>' attribute.
	 * @see #setParamType(String)
	 * @see org.eclipse.jst.j2ee.webservice.jaxrpcmap.JaxrpcmapPackage#getMethodParamPartsMapping_ParamType()
	 * @model
	 * @generated
	 */
  String getParamType();

	/**
	 * Sets the value of the '{@link org.eclipse.jst.j2ee.webservice.jaxrpcmap.MethodParamPartsMapping#getParamType <em>Param Type</em>}' attribute.
	 * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Param Type</em>' attribute.
	 * @see #getParamType()
	 * @generated
	 */
  void setParamType(String value);

	/**
	 * Returns the value of the '<em><b>Wsdl Message Mapping</b></em>' containment reference.
	 * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Wsdl Message Mapping</em>' containment reference isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
	 * @return the value of the '<em>Wsdl Message Mapping</em>' containment reference.
	 * @see #setWsdlMessageMapping(WSDLMessageMapping)
	 * @see org.eclipse.jst.j2ee.webservice.jaxrpcmap.JaxrpcmapPackage#getMethodParamPartsMapping_WsdlMessageMapping()
	 * @model containment="true" required="true"
	 * @generated
	 */
  WSDLMessageMapping getWsdlMessageMapping();

	/**
	 * Sets the value of the '{@link org.eclipse.jst.j2ee.webservice.jaxrpcmap.MethodParamPartsMapping#getWsdlMessageMapping <em>Wsdl Message Mapping</em>}' containment reference.
	 * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Wsdl Message Mapping</em>' containment reference.
	 * @see #getWsdlMessageMapping()
	 * @generated
	 */
  void setWsdlMessageMapping(WSDLMessageMapping value);

} // MethodParamPartsMapping
