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
 * A representation of the model object '<em><b>WSDL Return Value Mapping</b></em>'.
 * <!-- end-user-doc -->
 *
 * <!-- begin-model-doc -->
 * The wsdl-return-value-mapping  element defines the mapping for the method's return value. It defines the mapping to a specific message and its part.  Together they define uniquely the mapping for a specific parameter. Parts within a message context are uniquely 
 * identified with their names. The wsdl-message-part-name is not
 * specified if there is no return value or OUT parameters.
 * 
 * Used in: service-endpoint-method-mapping
 * <!-- end-model-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.eclipse.jst.j2ee.webservice.jaxrpcmap.WSDLReturnValueMapping#getId <em>Id</em>}</li>
 *   <li>{@link org.eclipse.jst.j2ee.webservice.jaxrpcmap.WSDLReturnValueMapping#getMethodReturnValue <em>Method Return Value</em>}</li>
 *   <li>{@link org.eclipse.jst.j2ee.webservice.jaxrpcmap.WSDLReturnValueMapping#getWsdlMessagePartName <em>Wsdl Message Part Name</em>}</li>
 *   <li>{@link org.eclipse.jst.j2ee.webservice.jaxrpcmap.WSDLReturnValueMapping#getWsdlMessage <em>Wsdl Message</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.eclipse.jst.j2ee.webservice.jaxrpcmap.JaxrpcmapPackage#getWSDLReturnValueMapping()
 * @model
 * @generated
 * @since 1.0 */
public interface WSDLReturnValueMapping extends EObject{
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
	 * @see org.eclipse.jst.j2ee.webservice.jaxrpcmap.JaxrpcmapPackage#getWSDLReturnValueMapping_Id()
	 * @model
	 * @generated
	 */
  String getId();

	/**
	 * Sets the value of the '{@link org.eclipse.jst.j2ee.webservice.jaxrpcmap.WSDLReturnValueMapping#getId <em>Id</em>}' attribute.
	 * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Id</em>' attribute.
	 * @see #getId()
	 * @generated
	 */
  void setId(String value);

	/**
	 * Returns the value of the '<em><b>Method Return Value</b></em>' attribute.
	 * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Method Return Value</em>' attribute isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * The method-return-value  element defines a fully qualified class name or void type for the method's return value type.
	 * 
	 * Used in: wsdl-return-value-mapping
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Method Return Value</em>' attribute.
	 * @see #setMethodReturnValue(String)
	 * @see org.eclipse.jst.j2ee.webservice.jaxrpcmap.JaxrpcmapPackage#getWSDLReturnValueMapping_MethodReturnValue()
	 * @model
	 * @generated
	 */
  String getMethodReturnValue();

	/**
	 * Sets the value of the '{@link org.eclipse.jst.j2ee.webservice.jaxrpcmap.WSDLReturnValueMapping#getMethodReturnValue <em>Method Return Value</em>}' attribute.
	 * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Method Return Value</em>' attribute.
	 * @see #getMethodReturnValue()
	 * @generated
	 */
  void setMethodReturnValue(String value);

	/**
	 * Returns the value of the '<em><b>Wsdl Message Part Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Wsdl Message Part Name</em>' attribute isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Interpretation of the wsdl-message-part-name element depends on whether or not wrapped-element has been defined in the service-endpoint-method-mapping.  If wrapped-element is not specified, wsdl-message-part-name defines a WSDL message part. It should always be interpreter with respect to a wsdl-message element. If wrapped-element is specified, wsdl-message-part-name refers to an element
	 * name of the element type.
	 * 
	 * Used in: wsdl-message-mapping, wsdl-return-value-mapping
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Wsdl Message Part Name</em>' attribute.
	 * @see #setWsdlMessagePartName(String)
	 * @see org.eclipse.jst.j2ee.webservice.jaxrpcmap.JaxrpcmapPackage#getWSDLReturnValueMapping_WsdlMessagePartName()
	 * @model
	 * @generated
	 */
  String getWsdlMessagePartName();

	/**
	 * Sets the value of the '{@link org.eclipse.jst.j2ee.webservice.jaxrpcmap.WSDLReturnValueMapping#getWsdlMessagePartName <em>Wsdl Message Part Name</em>}' attribute.
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
	 * @see org.eclipse.jst.j2ee.webservice.jaxrpcmap.JaxrpcmapPackage#getWSDLReturnValueMapping_WsdlMessage()
	 * @model containment="true" required="true"
	 * @generated
	 */
  WSDLMessage getWsdlMessage();

	/**
	 * Sets the value of the '{@link org.eclipse.jst.j2ee.webservice.jaxrpcmap.WSDLReturnValueMapping#getWsdlMessage <em>Wsdl Message</em>}' containment reference.
	 * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Wsdl Message</em>' containment reference.
	 * @see #getWsdlMessage()
	 * @generated
	 */
  void setWsdlMessage(WSDLMessage value);

} // WSDLReturnValueMapping
