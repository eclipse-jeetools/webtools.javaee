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
package org.eclipse.jst.j2ee.webservice.wsclient;

import org.eclipse.emf.common.util.EList;
import org.eclipse.jem.java.JavaClass;
import org.eclipse.jst.j2ee.common.CompatibilityDescriptionGroup;
import org.eclipse.jst.j2ee.common.QName;


/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Service Ref</b></em>'.
 * <!-- end-user-doc -->
 *
 * <!-- begin-model-doc -->
 * The service-ref element declares a reference to a Web
 *     service. It contains optional description, display name and
 *     icons, a declaration of the required Service interface,
 *     an optional WSDL document location, an optional set
 *     of JAX-RPC mappings, an optional QName for the service element,
 *     an optional set of Service Endpoint Interfaces to be resolved
 *     by the container to a WSDL port, and an optional set of handlers.
 * <!-- end-model-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.eclipse.jst.j2ee.internal.webservice.wsclient.ServiceRef#getServiceInterface <em>Service Interface</em>}</li>
 *   <li>{@link org.eclipse.jst.j2ee.internal.webservice.wsclient.ServiceRef#getServiceRefName <em>Service Ref Name</em>}</li>
 *   <li>{@link org.eclipse.jst.j2ee.internal.webservice.wsclient.ServiceRef#getWsdlFile <em>Wsdl File</em>}</li>
 *   <li>{@link org.eclipse.jst.j2ee.internal.webservice.wsclient.ServiceRef#getJaxrpcMappingFile <em>Jaxrpc Mapping File</em>}</li>
 *   <li>{@link org.eclipse.jst.j2ee.internal.webservice.wsclient.ServiceRef#getPortComponentRefs <em>Port Component Refs</em>}</li>
 *   <li>{@link org.eclipse.jst.j2ee.internal.webservice.wsclient.ServiceRef#getHandlers <em>Handlers</em>}</li>
 *   <li>{@link org.eclipse.jst.j2ee.internal.webservice.wsclient.ServiceRef#getServiceQname <em>Service Qname</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.eclipse.jst.j2ee.internal.webservice.wsclient.Webservice_clientPackage#getServiceRef()
 * @model 
 * @generated
 */
public interface ServiceRef extends CompatibilityDescriptionGroup {
	/**
	 * Returns the value of the '<em><b>Service Ref Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Service Ref Name</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * The service-ref-name element declares logical name that the
	 *         components in the module use to look up the Web service. It
	 *         is recommended that all service reference names start with
	 *         "service/".
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Service Ref Name</em>' attribute.
	 * @see #setServiceRefName(String)
	 * @see org.eclipse.jst.j2ee.internal.webservice.wsclient.Webservice_clientPackage#getServiceRef_ServiceRefName()
	 * @model 
	 * @generated
	 */
	String getServiceRefName();

	/**
	 * Sets the value of the '{@link org.eclipse.jst.j2ee.internal.webservice.wsclient.ServiceRef#getServiceRefName <em>Service Ref Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Service Ref Name</em>' attribute.
	 * @see #getServiceRefName()
	 * @generated
	 */
	void setServiceRefName(String value);

	/**
	 * Returns the value of the '<em><b>Wsdl File</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Wsdl File</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 *  The wsdl-file element contains the URI location of a WSDL file. The
	 *         location is relative to the root of the module.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Wsdl File</em>' attribute.
	 * @see #setWsdlFile(String)
	 * @see org.eclipse.jst.j2ee.internal.webservice.wsclient.Webservice_clientPackage#getServiceRef_WsdlFile()
	 * @model 
	 * @generated
	 */
	String getWsdlFile();

	/**
	 * Sets the value of the '{@link org.eclipse.jst.j2ee.internal.webservice.wsclient.ServiceRef#getWsdlFile <em>Wsdl File</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Wsdl File</em>' attribute.
	 * @see #getWsdlFile()
	 * @generated
	 */
	void setWsdlFile(String value);

	/**
	 * Returns the value of the '<em><b>Jaxrpc Mapping File</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Jaxrpc Mapping File</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * The jaxrpc-mapping-file element contains the name of a file that
	 *         describes the JAX-RPC mapping between the Java interaces used by
	 *         the application and the WSDL description in the wsdl-file.  The
	 *         file name is a relative path within the module file.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Jaxrpc Mapping File</em>' attribute.
	 * @see #setJaxrpcMappingFile(String)
	 * @see org.eclipse.jst.j2ee.internal.webservice.wsclient.Webservice_clientPackage#getServiceRef_JaxrpcMappingFile()
	 * @model 
	 * @generated
	 */
	String getJaxrpcMappingFile();

	/**
	 * Sets the value of the '{@link org.eclipse.jst.j2ee.internal.webservice.wsclient.ServiceRef#getJaxrpcMappingFile <em>Jaxrpc Mapping File</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Jaxrpc Mapping File</em>' attribute.
	 * @see #getJaxrpcMappingFile()
	 * @generated
	 */
	void setJaxrpcMappingFile(String value);

	/**
	 * Returns the value of the '<em><b>Service Interface</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Service Interface</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * The service-interface element declares the fully qualified class
	 *         name of the JAX-RPC Service interface the client depends on.
	 *         In most cases the value will be javax.xml.rpc.Service.  A JAX-RPC
	 *         generated Service Interface class may also be specified.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Service Interface</em>' reference.
	 * @see #setServiceInterface(JavaClass)
	 * @see org.eclipse.jst.j2ee.internal.webservice.wsclient.Webservice_clientPackage#getServiceRef_ServiceInterface()
	 * @model required="true"
	 * @generated
	 */
	JavaClass getServiceInterface();

	/**
	 * Sets the value of the '{@link org.eclipse.jst.j2ee.internal.webservice.wsclient.ServiceRef#getServiceInterface <em>Service Interface</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Service Interface</em>' reference.
	 * @see #getServiceInterface()
	 * @generated
	 */
	void setServiceInterface(JavaClass value);

	/**
	 * Returns the value of the '<em><b>Port Component Refs</b></em>' containment reference list.
	 * The list contents are of type {@link org.eclipse.jst.j2ee.internal.webservice.wsclient.PortComponentRef}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Port Component Refs</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * The port-component-ref element declares a client dependency
	 *         on the container for resolving a Service Endpoint Interface
	 *         to a WSDL port. It optionally associates the Service Endpoint
	 *         Interface with a particular port-component. This is only used
	 *         by the container for a Service.getPort(Class) method call.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Port Component Refs</em>' containment reference list.
	 * @see org.eclipse.jst.j2ee.internal.webservice.wsclient.Webservice_clientPackage#getServiceRef_PortComponentRefs()
	 * @model type="org.eclipse.jst.j2ee.internal.webservice.wsclient.PortComponentRef" containment="true"
	 * @generated
	 */
	EList getPortComponentRefs();

	/**
	 * Returns the value of the '<em><b>Handlers</b></em>' containment reference list.
	 * The list contents are of type {@link org.eclipse.jst.j2ee.internal.webservice.wsclient.Handler}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Handlers</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 *  Declares the handler for a port-component. Handlers can access the
	 *         init-param name/value pairs using the HandlerInfo interface. If
	 *         port-name is not specified, the handler is assumed to be associated
	 *         with all ports of the service.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Handlers</em>' containment reference list.
	 * @see org.eclipse.jst.j2ee.internal.webservice.wsclient.Webservice_clientPackage#getServiceRef_Handlers()
	 * @model type="org.eclipse.jst.j2ee.internal.webservice.wsclient.Handler" containment="true"
	 * @generated
	 */
	EList getHandlers();

	/**
	 * Returns the value of the '<em><b>Service Qname</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Service Qname</em>' containment reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Service Qname</em>' containment reference.
	 * @see #setServiceQname(QName)
	 * @see org.eclipse.jst.j2ee.internal.webservice.wsclient.Webservice_clientPackage#getServiceRef_ServiceQname()
	 * @model containment="true" required="true"
	 * @generated
	 */
	QName getServiceQname();

	/**
	 * Sets the value of the '{@link org.eclipse.jst.j2ee.internal.webservice.wsclient.ServiceRef#getServiceQname <em>Service Qname</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Service Qname</em>' containment reference.
	 * @see #getServiceQname()
	 * @generated
	 */
	void setServiceQname(QName value);

} // ServiceRef
