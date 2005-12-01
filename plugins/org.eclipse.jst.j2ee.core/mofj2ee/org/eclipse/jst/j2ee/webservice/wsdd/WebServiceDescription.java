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
package org.eclipse.jst.j2ee.webservice.wsdd;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.jst.j2ee.common.IconType;
import org.eclipse.jst.j2ee.webservice.wscommon.DescriptionType;
import org.eclipse.jst.j2ee.webservice.wscommon.DisplayNameType;


/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Web Service Description</b></em>'.
 * <!-- end-user-doc -->
 *
 * <!-- begin-model-doc -->
 * The webservice-description element defines a WSDL document file and the set of Port components associated with the WSDL ports defined in the WSDL document.  There may be multiple webservice-descriptions defined within a module.
 * 
 * All WSDL file ports must have a corresponding port-component element defined.
 * 
 * Used in: webservices
 * 
 * <!-- end-model-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.eclipse.jst.j2ee.webservice.wsdd.WebServiceDescription#getJaxrpcMappingFile <em>Jaxrpc Mapping File</em>}</li>
 *   <li>{@link org.eclipse.jst.j2ee.webservice.wsdd.WebServiceDescription#getWebServiceDescriptionName <em>Web Service Description Name</em>}</li>
 *   <li>{@link org.eclipse.jst.j2ee.webservice.wsdd.WebServiceDescription#getWsdlFile <em>Wsdl File</em>}</li>
 *   <li>{@link org.eclipse.jst.j2ee.webservice.wsdd.WebServiceDescription#getSmallIcon <em>Small Icon</em>}</li>
 *   <li>{@link org.eclipse.jst.j2ee.webservice.wsdd.WebServiceDescription#getLargeIcon <em>Large Icon</em>}</li>
 *   <li>{@link org.eclipse.jst.j2ee.webservice.wsdd.WebServiceDescription#getDescription <em>Description</em>}</li>
 *   <li>{@link org.eclipse.jst.j2ee.webservice.wsdd.WebServiceDescription#getDisplayName <em>Display Name</em>}</li>
 *   <li>{@link org.eclipse.jst.j2ee.webservice.wsdd.WebServiceDescription#getPortComponents <em>Port Components</em>}</li>
 *   <li>{@link org.eclipse.jst.j2ee.webservice.wsdd.WebServiceDescription#getDescriptionType <em>Description Type</em>}</li>
 *   <li>{@link org.eclipse.jst.j2ee.webservice.wsdd.WebServiceDescription#getDisplayNameType <em>Display Name Type</em>}</li>
 *   <li>{@link org.eclipse.jst.j2ee.webservice.wsdd.WebServiceDescription#getIconType <em>Icon Type</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.eclipse.jst.j2ee.webservice.wsdd.WsddPackage#getWebServiceDescription()
 * @model
 * @generated
 * @since 1.0 */
public interface WebServiceDescription extends EObject{
	/**
	 * Returns the value of the '<em><b>Jaxrpc Mapping File</b></em>' attribute.
	 * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Jaxrpc Mapping File</em>' attribute isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * The jaxrpc-mapping-file element contains the name of a file that describes the JAX-RPC mapping between the Java interaces used by the application and the WSDL description in the wsdl-file. The file name is a relative path within the module.
	 * 
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Jaxrpc Mapping File</em>' attribute.
	 * @see #setJaxrpcMappingFile(String)
	 * @see org.eclipse.jst.j2ee.webservice.wsdd.WsddPackage#getWebServiceDescription_JaxrpcMappingFile()
	 * @model
	 * @generated
	 */
  String getJaxrpcMappingFile();

	/**
	 * Sets the value of the '{@link org.eclipse.jst.j2ee.webservice.wsdd.WebServiceDescription#getJaxrpcMappingFile <em>Jaxrpc Mapping File</em>}' attribute.
	 * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Jaxrpc Mapping File</em>' attribute.
	 * @see #getJaxrpcMappingFile()
	 * @generated
	 */
  void setJaxrpcMappingFile(String value);

	/**
	 * Returns the value of the '<em><b>Web Service Description Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Web Service Description Name</em>' attribute isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * The webservice-description-name identifies the collection of port-components associated with a WSDL file and JAX-RPC mapping. The name must be unique within the deployment descriptor.
	 * 
	 * 
	 * 
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Web Service Description Name</em>' attribute.
	 * @see #setWebServiceDescriptionName(String)
	 * @see org.eclipse.jst.j2ee.webservice.wsdd.WsddPackage#getWebServiceDescription_WebServiceDescriptionName()
	 * @model
	 * @generated
	 */
  String getWebServiceDescriptionName();

	/**
	 * Sets the value of the '{@link org.eclipse.jst.j2ee.webservice.wsdd.WebServiceDescription#getWebServiceDescriptionName <em>Web Service Description Name</em>}' attribute.
	 * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Web Service Description Name</em>' attribute.
	 * @see #getWebServiceDescriptionName()
	 * @generated
	 */
  void setWebServiceDescriptionName(String value);

	/**
	 * Returns the value of the '<em><b>Wsdl File</b></em>' attribute.
	 * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Wsdl File</em>' attribute isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * The wsdl-file element contains the name of a WSDL file in the module. The file name is a relative path within the module.
	 * 
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Wsdl File</em>' attribute.
	 * @see #setWsdlFile(String)
	 * @see org.eclipse.jst.j2ee.webservice.wsdd.WsddPackage#getWebServiceDescription_WsdlFile()
	 * @model
	 * @generated
	 */
  String getWsdlFile();

	/**
	 * Sets the value of the '{@link org.eclipse.jst.j2ee.webservice.wsdd.WebServiceDescription#getWsdlFile <em>Wsdl File</em>}' attribute.
	 * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Wsdl File</em>' attribute.
	 * @see #getWsdlFile()
	 * @generated
	 */
  void setWsdlFile(String value);

	/**
	 * Returns the value of the '<em><b>Small Icon</b></em>' attribute.
	 * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Small Icon</em>' attribute isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
	 * @return the value of the '<em>Small Icon</em>' attribute.
	 * @see #setSmallIcon(String)
	 * @see org.eclipse.jst.j2ee.webservice.wsdd.WsddPackage#getWebServiceDescription_SmallIcon()
	 * @model
	 * @generated
	 */
  String getSmallIcon();

	/**
	 * Sets the value of the '{@link org.eclipse.jst.j2ee.webservice.wsdd.WebServiceDescription#getSmallIcon <em>Small Icon</em>}' attribute.
	 * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Small Icon</em>' attribute.
	 * @see #getSmallIcon()
	 * @generated
	 */
  void setSmallIcon(String value);

	/**
	 * Returns the value of the '<em><b>Large Icon</b></em>' attribute.
	 * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Large Icon</em>' attribute isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
	 * @return the value of the '<em>Large Icon</em>' attribute.
	 * @see #setLargeIcon(String)
	 * @see org.eclipse.jst.j2ee.webservice.wsdd.WsddPackage#getWebServiceDescription_LargeIcon()
	 * @model
	 * @generated
	 */
  String getLargeIcon();

	/**
	 * Sets the value of the '{@link org.eclipse.jst.j2ee.webservice.wsdd.WebServiceDescription#getLargeIcon <em>Large Icon</em>}' attribute.
	 * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Large Icon</em>' attribute.
	 * @see #getLargeIcon()
	 * @generated
	 */
  void setLargeIcon(String value);

	/**
	 * Returns the value of the '<em><b>Description</b></em>' attribute.
	 * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Description</em>' attribute isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
	 * @return the value of the '<em>Description</em>' attribute.
	 * @see #setDescription(String)
	 * @see org.eclipse.jst.j2ee.webservice.wsdd.WsddPackage#getWebServiceDescription_Description()
	 * @model
	 * @generated
	 */
  String getDescription();

	/**
	 * Sets the value of the '{@link org.eclipse.jst.j2ee.webservice.wsdd.WebServiceDescription#getDescription <em>Description</em>}' attribute.
	 * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Description</em>' attribute.
	 * @see #getDescription()
	 * @generated
	 */
  void setDescription(String value);

	/**
	 * Returns the value of the '<em><b>Display Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Display Name</em>' attribute isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
	 * @return the value of the '<em>Display Name</em>' attribute.
	 * @see #setDisplayName(String)
	 * @see org.eclipse.jst.j2ee.webservice.wsdd.WsddPackage#getWebServiceDescription_DisplayName()
	 * @model
	 * @generated
	 */
  String getDisplayName();

	/**
	 * Sets the value of the '{@link org.eclipse.jst.j2ee.webservice.wsdd.WebServiceDescription#getDisplayName <em>Display Name</em>}' attribute.
	 * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Display Name</em>' attribute.
	 * @see #getDisplayName()
	 * @generated
	 */
  void setDisplayName(String value);

	/**
	 * Returns the value of the '<em><b>Port Components</b></em>' containment reference list.
	 * The list contents are of type {@link org.eclipse.jst.j2ee.webservice.wsdd.PortComponent}.
	 * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Port Components</em>' containment reference list isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
	 * @return the value of the '<em>Port Components</em>' containment reference list.
	 * @see org.eclipse.jst.j2ee.webservice.wsdd.WsddPackage#getWebServiceDescription_PortComponents()
	 * @model type="org.eclipse.jst.j2ee.webservice.wsdd.PortComponent" containment="true" required="true"
	 * @generated
	 */
  EList getPortComponents();

	/**
	 * Returns the value of the '<em><b>Description Type</b></em>' containment reference.
	 * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Description Type</em>' containment reference list isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
	 * @return the value of the '<em>Description Type</em>' containment reference.
	 * @see #setDescriptionType(DescriptionType)
	 * @see org.eclipse.jst.j2ee.webservice.wsdd.WsddPackage#getWebServiceDescription_DescriptionType()
	 * @model containment="true"
	 * @generated
	 */
  DescriptionType getDescriptionType();

	/**
	 * Sets the value of the '{@link org.eclipse.jst.j2ee.webservice.wsdd.WebServiceDescription#getDescriptionType <em>Description Type</em>}' containment reference.
	 * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Description Type</em>' containment reference.
	 * @see #getDescriptionType()
	 * @generated
	 */
  void setDescriptionType(DescriptionType value);

	/**
	 * Returns the value of the '<em><b>Display Name Type</b></em>' containment reference.
	 * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Display Name Type</em>' containment reference list isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
	 * @return the value of the '<em>Display Name Type</em>' containment reference.
	 * @see #setDisplayNameType(DisplayNameType)
	 * @see org.eclipse.jst.j2ee.webservice.wsdd.WsddPackage#getWebServiceDescription_DisplayNameType()
	 * @model containment="true"
	 * @generated
	 */
  DisplayNameType getDisplayNameType();

	/**
	 * Sets the value of the '{@link org.eclipse.jst.j2ee.webservice.wsdd.WebServiceDescription#getDisplayNameType <em>Display Name Type</em>}' containment reference.
	 * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Display Name Type</em>' containment reference.
	 * @see #getDisplayNameType()
	 * @generated
	 */
  void setDisplayNameType(DisplayNameType value);

	/**
	 * Returns the value of the '<em><b>Icon Type</b></em>' containment reference.
	 * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Icon Type</em>' containment reference list isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
	 * @return the value of the '<em>Icon Type</em>' containment reference.
	 * @see #setIconType(IconType)
	 * @see org.eclipse.jst.j2ee.webservice.wsdd.WsddPackage#getWebServiceDescription_IconType()
	 * @model containment="true"
	 * @generated
	 */
  IconType getIconType();

	/**
	 * Sets the value of the '{@link org.eclipse.jst.j2ee.webservice.wsdd.WebServiceDescription#getIconType <em>Icon Type</em>}' containment reference.
	 * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Icon Type</em>' containment reference.
	 * @see #getIconType()
	 * @generated
	 */
  void setIconType(IconType value);

} // WebServiceDescription
