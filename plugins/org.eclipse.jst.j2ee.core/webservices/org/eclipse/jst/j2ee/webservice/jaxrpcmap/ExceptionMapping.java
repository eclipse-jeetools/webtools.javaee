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
 * A representation of the model object '<em><b>Exception Mapping</b></em>'.
 * <!-- end-user-doc -->
 *
 * <!-- begin-model-doc -->
 * The exception-mapping element defines the mapping between the service specific exception types and the wsdl faults.
 * 
 * This element should be interpreted with respect to the
 * mapping between a method and an operation which provides the mapping context.
 * 
 * Used in: service-endpoint-method-mapping
 * <!-- end-model-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.eclipse.jst.j2ee.webservice.jaxrpcmap.ExceptionMapping#getId <em>Id</em>}</li>
 *   <li>{@link org.eclipse.jst.j2ee.webservice.jaxrpcmap.ExceptionMapping#getExceptionType <em>Exception Type</em>}</li>
 *   <li>{@link org.eclipse.jst.j2ee.webservice.jaxrpcmap.ExceptionMapping#getWsdlMessagePartName <em>Wsdl Message Part Name</em>}</li>
 *   <li>{@link org.eclipse.jst.j2ee.webservice.jaxrpcmap.ExceptionMapping#getWsdlMessage <em>Wsdl Message</em>}</li>
 *   <li>{@link org.eclipse.jst.j2ee.webservice.jaxrpcmap.ExceptionMapping#getConstructorParameterOrder <em>Constructor Parameter Order</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.eclipse.jst.j2ee.webservice.jaxrpcmap.JaxrpcmapPackage#getExceptionMapping()
 * @model
 * @generated
 * @since 1.0 */
public interface ExceptionMapping extends EObject{
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
	 * @see org.eclipse.jst.j2ee.webservice.jaxrpcmap.JaxrpcmapPackage#getExceptionMapping_Id()
	 * @model
	 * @generated
	 */
  String getId();

	/**
	 * Sets the value of the '{@link org.eclipse.jst.j2ee.webservice.jaxrpcmap.ExceptionMapping#getId <em>Id</em>}' attribute.
	 * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Id</em>' attribute.
	 * @see #getId()
	 * @generated
	 */
  void setId(String value);

	/**
	 * Returns the value of the '<em><b>Exception Type</b></em>' attribute.
	 * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Exception Type</em>' attribute isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * The exception-type element defines Java type of the exception. It may be a service specific exception.
	 * 
	 * It must be a fully qualified class name.
	 * 
	 * Used in: exception-mapping
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Exception Type</em>' attribute.
	 * @see #setExceptionType(String)
	 * @see org.eclipse.jst.j2ee.webservice.jaxrpcmap.JaxrpcmapPackage#getExceptionMapping_ExceptionType()
	 * @model
	 * @generated
	 */
  String getExceptionType();

	/**
	 * Sets the value of the '{@link org.eclipse.jst.j2ee.webservice.jaxrpcmap.ExceptionMapping#getExceptionType <em>Exception Type</em>}' attribute.
	 * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Exception Type</em>' attribute.
	 * @see #getExceptionType()
	 * @generated
	 */
  void setExceptionType(String value);

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
	 * @see org.eclipse.jst.j2ee.webservice.jaxrpcmap.JaxrpcmapPackage#getExceptionMapping_WsdlMessagePartName()
	 * @model
	 * @generated
	 */
  String getWsdlMessagePartName();

	/**
	 * Sets the value of the '{@link org.eclipse.jst.j2ee.webservice.jaxrpcmap.ExceptionMapping#getWsdlMessagePartName <em>Wsdl Message Part Name</em>}' attribute.
	 * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Wsdl Message Part Name</em>' attribute.
	 * @see #getWsdlMessagePartName()
	 * @generated
	 */
  void setWsdlMessagePartName(String value);

	/**
	 * Returns the value of the '<em><b>Wsdl Message</b></em>' containment reference.
	 * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Wsdl Message</em>' containment reference isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
	 * @return the value of the '<em>Wsdl Message</em>' containment reference.
	 * @see #setWsdlMessage(WSDLMessage)
	 * @see org.eclipse.jst.j2ee.webservice.jaxrpcmap.JaxrpcmapPackage#getExceptionMapping_WsdlMessage()
	 * @model containment="true" required="true"
	 * @generated
	 */
  WSDLMessage getWsdlMessage();

	/**
	 * Sets the value of the '{@link org.eclipse.jst.j2ee.webservice.jaxrpcmap.ExceptionMapping#getWsdlMessage <em>Wsdl Message</em>}' containment reference.
	 * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Wsdl Message</em>' containment reference.
	 * @see #getWsdlMessage()
	 * @generated
	 */
  void setWsdlMessage(WSDLMessage value);

	/**
	 * Returns the value of the '<em><b>Constructor Parameter Order</b></em>' containment reference.
	 * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Constructor Parameter Order</em>' containment reference isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
	 * @return the value of the '<em>Constructor Parameter Order</em>' containment reference.
	 * @see #setConstructorParameterOrder(ConstructorParameterOrder)
	 * @see org.eclipse.jst.j2ee.webservice.jaxrpcmap.JaxrpcmapPackage#getExceptionMapping_ConstructorParameterOrder()
	 * @model containment="true"
	 * @generated
	 */
  ConstructorParameterOrder getConstructorParameterOrder();

	/**
	 * Sets the value of the '{@link org.eclipse.jst.j2ee.webservice.jaxrpcmap.ExceptionMapping#getConstructorParameterOrder <em>Constructor Parameter Order</em>}' containment reference.
	 * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Constructor Parameter Order</em>' containment reference.
	 * @see #getConstructorParameterOrder()
	 * @generated
	 */
  void setConstructorParameterOrder(ConstructorParameterOrder value);

} // ExceptionMapping
