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

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Service Endpoint Method Mapping</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.eclipse.jst.j2ee.webservice.jaxrpcmap.ServiceEndpointMethodMapping#getId <em>Id</em>}</li>
 *   <li>{@link org.eclipse.jst.j2ee.webservice.jaxrpcmap.ServiceEndpointMethodMapping#getJavaMethodName <em>Java Method Name</em>}</li>
 *   <li>{@link org.eclipse.jst.j2ee.webservice.jaxrpcmap.ServiceEndpointMethodMapping#getWsdlOperation <em>Wsdl Operation</em>}</li>
 *   <li>{@link org.eclipse.jst.j2ee.webservice.jaxrpcmap.ServiceEndpointMethodMapping#isWrappedElement <em>Wrapped Element</em>}</li>
 *   <li>{@link org.eclipse.jst.j2ee.webservice.jaxrpcmap.ServiceEndpointMethodMapping#getMethodParamPartsMappings <em>Method Param Parts Mappings</em>}</li>
 *   <li>{@link org.eclipse.jst.j2ee.webservice.jaxrpcmap.ServiceEndpointMethodMapping#getWsdlReturnValueMapping <em>Wsdl Return Value Mapping</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.eclipse.jst.j2ee.webservice.jaxrpcmap.JaxrpcmapPackage#getServiceEndpointMethodMapping()
 * @model 
 * @generated
 */
public interface ServiceEndpointMethodMapping extends EObject{
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
   * @see org.eclipse.jst.j2ee.webservice.jaxrpcmap.JaxrpcmapPackage#getServiceEndpointMethodMapping_Id()
   * @model 
   * @generated
   */
  String getId();

  /**
   * Sets the value of the '{@link org.eclipse.jst.j2ee.webservice.jaxrpcmap.ServiceEndpointMethodMapping#getId <em>Id</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Id</em>' attribute.
   * @see #getId()
   * @generated
   */
  void setId(String value);

  /**
   * Returns the value of the '<em><b>Java Method Name</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Java Method Name</em>' attribute isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Java Method Name</em>' attribute.
   * @see #setJavaMethodName(String)
   * @see org.eclipse.jst.j2ee.webservice.jaxrpcmap.JaxrpcmapPackage#getServiceEndpointMethodMapping_JavaMethodName()
   * @model 
   * @generated
   */
  String getJavaMethodName();

  /**
   * Sets the value of the '{@link org.eclipse.jst.j2ee.webservice.jaxrpcmap.ServiceEndpointMethodMapping#getJavaMethodName <em>Java Method Name</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Java Method Name</em>' attribute.
   * @see #getJavaMethodName()
   * @generated
   */
  void setJavaMethodName(String value);

  /**
   * Returns the value of the '<em><b>Wsdl Operation</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Wsdl Operation</em>' attribute isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Wsdl Operation</em>' attribute.
   * @see #setWsdlOperation(String)
   * @see org.eclipse.jst.j2ee.webservice.jaxrpcmap.JaxrpcmapPackage#getServiceEndpointMethodMapping_WsdlOperation()
   * @model 
   * @generated
   */
  String getWsdlOperation();

  /**
   * Sets the value of the '{@link org.eclipse.jst.j2ee.webservice.jaxrpcmap.ServiceEndpointMethodMapping#getWsdlOperation <em>Wsdl Operation</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Wsdl Operation</em>' attribute.
   * @see #getWsdlOperation()
   * @generated
   */
  void setWsdlOperation(String value);

  /**
   * Returns the value of the '<em><b>Wrapped Element</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Wrapped Element</em>' attribute isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Wrapped Element</em>' attribute.
   * @see #isSetWrappedElement()
   * @see #unsetWrappedElement()
   * @see #setWrappedElement(boolean)
   * @see org.eclipse.jst.j2ee.webservice.jaxrpcmap.JaxrpcmapPackage#getServiceEndpointMethodMapping_WrappedElement()
   * @model unsettable="true"
   * @generated
   */
  boolean isWrappedElement();

  /**
   * Sets the value of the '{@link org.eclipse.jst.j2ee.webservice.jaxrpcmap.ServiceEndpointMethodMapping#isWrappedElement <em>Wrapped Element</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Wrapped Element</em>' attribute.
   * @see #isSetWrappedElement()
   * @see #unsetWrappedElement()
   * @see #isWrappedElement()
   * @generated
   */
  void setWrappedElement(boolean value);

  /**
   * Unsets the value of the '{@link org.eclipse.jst.j2ee.webservice.jaxrpcmap.ServiceEndpointMethodMapping#isWrappedElement <em>Wrapped Element</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #isSetWrappedElement()
   * @see #isWrappedElement()
   * @see #setWrappedElement(boolean)
   * @generated
   */
  void unsetWrappedElement();

  /**
   * Returns whether the value of the '{@link org.eclipse.jst.j2ee.webservice.jaxrpcmap.ServiceEndpointMethodMapping#isWrappedElement <em>Wrapped Element</em>}' attribute is set.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return whether the value of the '<em>Wrapped Element</em>' attribute is set.
   * @see #unsetWrappedElement()
   * @see #isWrappedElement()
   * @see #setWrappedElement(boolean)
   * @generated
   */
  boolean isSetWrappedElement();

  /**
   * Returns the value of the '<em><b>Method Param Parts Mappings</b></em>' containment reference list.
   * The list contents are of type {@link org.eclipse.jst.j2ee.webservice.jaxrpcmap.MethodParamPartsMapping}.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Method Param Parts Mappings</em>' containment reference list isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Method Param Parts Mappings</em>' containment reference list.
   * @see org.eclipse.jst.j2ee.webservice.jaxrpcmap.JaxrpcmapPackage#getServiceEndpointMethodMapping_MethodParamPartsMappings()
   * @model type="org.eclipse.jst.j2ee.webservice.jaxrpcmap.MethodParamPartsMapping" containment="true"
   * @generated
   */
  EList getMethodParamPartsMappings();

  /**
   * Returns the value of the '<em><b>Wsdl Return Value Mapping</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Wsdl Return Value Mapping</em>' containment reference isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Wsdl Return Value Mapping</em>' containment reference.
   * @see #setWsdlReturnValueMapping(WSDLReturnValueMapping)
   * @see org.eclipse.jst.j2ee.webservice.jaxrpcmap.JaxrpcmapPackage#getServiceEndpointMethodMapping_WsdlReturnValueMapping()
   * @model containment="true"
   * @generated
   */
  WSDLReturnValueMapping getWsdlReturnValueMapping();

  /**
   * Sets the value of the '{@link org.eclipse.jst.j2ee.webservice.jaxrpcmap.ServiceEndpointMethodMapping#getWsdlReturnValueMapping <em>Wsdl Return Value Mapping</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Wsdl Return Value Mapping</em>' containment reference.
   * @see #getWsdlReturnValueMapping()
   * @generated
   */
  void setWsdlReturnValueMapping(WSDLReturnValueMapping value);

} // ServiceEndpointMethodMapping
