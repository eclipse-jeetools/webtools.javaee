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
package org.eclipse.jst.javaee.core;

import java.util.List;
import javax.xml.namespace.QName;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Service Ref</b></em>'.
 * <!-- end-user-doc -->
 *
 * <!-- begin-model-doc -->
 * 
 * 
 *         The service-ref element declares a reference to a Web
 *         service. It contains optional description, display name and
 *         icons, a declaration of the required Service interface,
 *         an optional WSDL document location, an optional set
 *         of JAX-RPC mappings, an optional QName for the service element,
 *         an optional set of Service Endpoint Interfaces to be resolved 
 *         by the container to a WSDL port, and an optional set of handlers.
 *         
 *         @since Java EE 5, Web Services Client 1.2
 *       
 * <!-- end-model-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.eclipse.jst.javaee.core.ServiceRef#getDescriptions <em>Descriptions</em>}</li>
 *   <li>{@link org.eclipse.jst.javaee.core.ServiceRef#getDisplayNames <em>Display Names</em>}</li>
 *   <li>{@link org.eclipse.jst.javaee.core.ServiceRef#getIcons <em>Icons</em>}</li>
 *   <li>{@link org.eclipse.jst.javaee.core.ServiceRef#getServiceRefName <em>Service Ref Name</em>}</li>
 *   <li>{@link org.eclipse.jst.javaee.core.ServiceRef#getServiceInterface <em>Service Interface</em>}</li>
 *   <li>{@link org.eclipse.jst.javaee.core.ServiceRef#getServiceRefType <em>Service Ref Type</em>}</li>
 *   <li>{@link org.eclipse.jst.javaee.core.ServiceRef#getWsdlFile <em>Wsdl File</em>}</li>
 *   <li>{@link org.eclipse.jst.javaee.core.ServiceRef#getJaxrpcMappingFile <em>Jaxrpc Mapping File</em>}</li>
 *   <li>{@link org.eclipse.jst.javaee.core.ServiceRef#getServiceQname <em>Service Qname</em>}</li>
 *   <li>{@link org.eclipse.jst.javaee.core.ServiceRef#getPortComponentRefs <em>Port Component Refs</em>}</li>
 *   <li>{@link org.eclipse.jst.javaee.core.ServiceRef#getHandlers <em>Handlers</em>}</li>
 *   <li>{@link org.eclipse.jst.javaee.core.ServiceRef#getHandlerChains <em>Handler Chains</em>}</li>
 *   <li>{@link org.eclipse.jst.javaee.core.ServiceRef#getMappedName <em>Mapped Name</em>}</li>
 *   <li>{@link org.eclipse.jst.javaee.core.ServiceRef#getInjectionTargets <em>Injection Targets</em>}</li>
 *   <li>{@link org.eclipse.jst.javaee.core.ServiceRef#getLookupName <em>Lookup Name</em>}</li>
 *   <li>{@link org.eclipse.jst.javaee.core.ServiceRef#getId <em>Id</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.eclipse.jst.javaee.core.internal.metadata.JavaeePackage#getServiceRef()
 * @extends JavaEEObject
 * @generated
 */
public interface ServiceRef extends JavaEEObject {
	/**
	 * Returns the value of the '<em><b>Descriptions</b></em>' containment reference list.
	 * The list contents are of type {@link org.eclipse.jst.javaee.core.Description}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Descriptions</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Descriptions</em>' containment reference list.
	 * @see org.eclipse.jst.javaee.core.internal.metadata.JavaeePackage#getServiceRef_Descriptions()
	 * @generated
	 */
	List<Description> getDescriptions();

	/**
	 * Returns the value of the '<em><b>Display Names</b></em>' containment reference list.
	 * The list contents are of type {@link org.eclipse.jst.javaee.core.DisplayName}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Display Names</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Display Names</em>' containment reference list.
	 * @see org.eclipse.jst.javaee.core.internal.metadata.JavaeePackage#getServiceRef_DisplayNames()
	 * @generated
	 */
	List<DisplayName> getDisplayNames();

	/**
	 * Returns the value of the '<em><b>Icons</b></em>' containment reference list.
	 * The list contents are of type {@link org.eclipse.jst.javaee.core.Icon}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Icons</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Icons</em>' containment reference list.
	 * @see org.eclipse.jst.javaee.core.internal.metadata.JavaeePackage#getServiceRef_Icons()
	 * @generated
	 */
	List<Icon> getIcons();

	/**
	 * Returns the value of the '<em><b>Service Ref Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * 
	 * 
	 *             The service-ref-name element declares logical name that the
	 *             components in the module use to look up the Web service. It 
	 *             is recommended that all service reference names start with 
	 *             "service/".
	 *             
	 *             @since Java EE 5, Web Services Client 1.2
	 *           
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Service Ref Name</em>' attribute.
	 * @see #setServiceRefName(String)
	 * @see org.eclipse.jst.javaee.core.internal.metadata.JavaeePackage#getServiceRef_ServiceRefName()
	 * @generated
	 */
	String getServiceRefName();

	/**
	 * Sets the value of the '{@link org.eclipse.jst.javaee.core.ServiceRef#getServiceRefName <em>Service Ref Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Service Ref Name</em>' attribute.
	 * @see #getServiceRefName()
	 * @generated
	 */
	void setServiceRefName(String value);

	/**
	 * Returns the value of the '<em><b>Service Interface</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * 
	 * 
	 *             The service-interface element declares the fully qualified class
	 *             name of the JAX-RPC Service interface the client depends on. 
	 *             In most cases the value will be javax.xml.rpc.Service.  A JAX-RPC
	 *             generated Service Interface class may also be specified.
	 *             
	 *             @since Java EE 5, Web Services Client 1.2
	 *           
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Service Interface</em>' attribute.
	 * @see #setServiceInterface(String)
	 * @see org.eclipse.jst.javaee.core.internal.metadata.JavaeePackage#getServiceRef_ServiceInterface()
	 * @generated
	 */
	String getServiceInterface();

	/**
	 * Sets the value of the '{@link org.eclipse.jst.javaee.core.ServiceRef#getServiceInterface <em>Service Interface</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Service Interface</em>' attribute.
	 * @see #getServiceInterface()
	 * @generated
	 */
	void setServiceInterface(String value);

	/**
	 * Returns the value of the '<em><b>Service Ref Type</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * 
	 * 
	 *             The service-ref-type element declares the type of the service-ref 
	 *             element that is injected or returned when a JNDI lookup is done.
	 *             This must be either a fully qualified name of Service class or 
	 *             the fully qualified name of service endpoint interface class. 
	 *             This is only used with JAX-WS runtime where the corresponding 
	 *             @WebServiceRef annotation can be used to denote both a Service
	 *             or a Port.
	 *             
	 *             If this is not specified, then the type of service-ref element 
	 *             that is injected or returned when a JNDI lookup is done is 
	 *             always a Service interface/class.
	 *             
	 *             @since Java EE 5, Web Services Client 1.2
	 *           
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Service Ref Type</em>' attribute.
	 * @see #setServiceRefType(String)
	 * @see org.eclipse.jst.javaee.core.internal.metadata.JavaeePackage#getServiceRef_ServiceRefType()
	 * @generated
	 */
	String getServiceRefType();

	/**
	 * Sets the value of the '{@link org.eclipse.jst.javaee.core.ServiceRef#getServiceRefType <em>Service Ref Type</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Service Ref Type</em>' attribute.
	 * @see #getServiceRefType()
	 * @generated
	 */
	void setServiceRefType(String value);

	/**
	 * Returns the value of the '<em><b>Wsdl File</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * 
	 * 
	 *             The wsdl-file element contains the URI location of a WSDL
	 *             file. The location is relative to the root of the module.
	 *             
	 *             @since Java EE 5, Web Services Client 1.2
	 *           
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Wsdl File</em>' attribute.
	 * @see #setWsdlFile(String)
	 * @see org.eclipse.jst.javaee.core.internal.metadata.JavaeePackage#getServiceRef_WsdlFile()
	 * @generated
	 */
	String getWsdlFile();

	/**
	 * Sets the value of the '{@link org.eclipse.jst.javaee.core.ServiceRef#getWsdlFile <em>Wsdl File</em>}' attribute.
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
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * 
	 * 
	 *             The jaxrpc-mapping-file element contains the name of a file that
	 *             describes the JAX-RPC mapping between the Java interaces used by
	 *             the application and the WSDL description in the wsdl-file.  The 
	 *             file name is a relative path within the module file.
	 *             
	 *             This is not required when JAX-WS based runtime is used.
	 *             
	 *             @since Java EE 5, Web Services Client 1.2
	 *           
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Jaxrpc Mapping File</em>' attribute.
	 * @see #setJaxrpcMappingFile(String)
	 * @see org.eclipse.jst.javaee.core.internal.metadata.JavaeePackage#getServiceRef_JaxrpcMappingFile()
	 * @generated
	 */
	String getJaxrpcMappingFile();

	/**
	 * Sets the value of the '{@link org.eclipse.jst.javaee.core.ServiceRef#getJaxrpcMappingFile <em>Jaxrpc Mapping File</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Jaxrpc Mapping File</em>' attribute.
	 * @see #getJaxrpcMappingFile()
	 * @generated
	 */
	void setJaxrpcMappingFile(String value);

	/**
	 * Returns the value of the '<em><b>Service Qname</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * 
	 * 
	 *             The service-qname element declares the specific WSDL service
	 *             element that is being refered to.  It is not specified if no
	 *             wsdl-file is declared.
	 *             
	 *             @since Java EE 5, Web Services Client 1.2
	 *           
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Service Qname</em>' attribute.
	 * @see #setServiceQname(QName)
	 * @see org.eclipse.jst.javaee.core.internal.metadata.JavaeePackage#getServiceRef_ServiceQname()
	 * @generated
	 */
	QName getServiceQname();

	/**
	 * Sets the value of the '{@link org.eclipse.jst.javaee.core.ServiceRef#getServiceQname <em>Service Qname</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Service Qname</em>' attribute.
	 * @see #getServiceQname()
	 * @generated
	 */
	void setServiceQname(QName value);

	/**
	 * Returns the value of the '<em><b>Port Component Refs</b></em>' containment reference list.
	 * The list contents are of type {@link org.eclipse.jst.javaee.core.PortComponentRef}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * 
	 * 
	 *             The port-component-ref element declares a client dependency
	 *             on the container for resolving a Service Endpoint Interface
	 *             to a WSDL port. It optionally associates the Service Endpoint
	 *             Interface with a particular port-component. This is only used
	 *             by the container for a Service.getPort(Class) method call.
	 *             
	 *             @since Java EE 5, Web Services Client 1.2
	 *           
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Port Component Refs</em>' containment reference list.
	 * @see org.eclipse.jst.javaee.core.internal.metadata.JavaeePackage#getServiceRef_PortComponentRefs()
	 * @generated
	 */
	List<PortComponentRef> getPortComponentRefs();

	/**
	 * Returns the value of the '<em><b>Handlers</b></em>' containment reference list.
	 * The list contents are of type {@link org.eclipse.jst.javaee.core.ServiceRefHandler}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * 
	 * 
	 *               	Declares the handler for a port-component. Handlers can
	 *               	access the init-param name/value pairs using the
	 *               	HandlerInfo interface. If port-name is not specified, the
	 *               	handler is assumed to be associated with all ports of the
	 *               	service.
	 *               
	 *               	To be used with JAX-RPC based runtime only.
	 *               
	 *               	@since Java EE 5, Web Services Client 1.2
	 *             
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Handlers</em>' containment reference list.
	 * @see org.eclipse.jst.javaee.core.internal.metadata.JavaeePackage#getServiceRef_Handlers()
	 * @generated
	 */
	List<ServiceRefHandler> getHandlers();

	/**
	 * Returns the value of the '<em><b>Handler Chains</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * 
	 * 
	 *               	 To be used with JAX-WS based runtime only.
	 *               
	 *               	 @since Java EE 5, Web Services Client 1.2
	 *             
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Handler Chains</em>' containment reference.
	 * @see #setHandlerChains(ServiceRefHandlerChains)
	 * @see org.eclipse.jst.javaee.core.internal.metadata.JavaeePackage#getServiceRef_HandlerChains()
	 * @generated
	 */
	ServiceRefHandlerChains getHandlerChains();

	/**
	 * Sets the value of the '{@link org.eclipse.jst.javaee.core.ServiceRef#getHandlerChains <em>Handler Chains</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Handler Chains</em>' containment reference.
	 * @see #getHandlerChains()
	 * @generated
	 */
	void setHandlerChains(ServiceRefHandlerChains value);

	/**
	 * Returns the value of the '<em><b>Mapped Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * 
	 * 
	 *             A product specific name that this resource should be
	 *             mapped to.  The name of this resource, as defined by the
	 *             resource's name element or defaulted, is a name that is
	 *             local to the application component using the resource.
	 *             (It's a name in the JNDI java:comp/env namespace.)  Many
	 *             application servers provide a way to map these local
	 *             names to names of resources known to the application
	 *             server.  This mapped name is often a global JNDI name,
	 *             but may be a name of any form.
	 *             
	 *             Application servers are not required to support any
	 *             particular form or type of mapped name, nor the ability
	 *             to use mapped names.  The mapped name is
	 *             product-dependent and often installation-dependent.  No
	 *             use of a mapped name is portable.
	 *             
	 *             @since Java EE 5
	 *           
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Mapped Name</em>' attribute.
	 * @see #setMappedName(String)
	 * @see org.eclipse.jst.javaee.core.internal.metadata.JavaeePackage#getServiceRef_MappedName()
	 * @generated
	 */
	String getMappedName();

	/**
	 * Sets the value of the '{@link org.eclipse.jst.javaee.core.ServiceRef#getMappedName <em>Mapped Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Mapped Name</em>' attribute.
	 * @see #getMappedName()
	 * @generated
	 */
	void setMappedName(String value);

	/**
	 * Returns the value of the '<em><b>Injection Targets</b></em>' containment reference list.
	 * The list contents are of type {@link org.eclipse.jst.javaee.core.InjectionTarget}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Injection Targets</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Injection Targets</em>' containment reference list.
	 * @see org.eclipse.jst.javaee.core.internal.metadata.JavaeePackage#getServiceRef_InjectionTargets()
	 * @generated
	 */
	List<InjectionTarget> getInjectionTargets();

	/**
	 * Returns the value of the '<em><b>Lookup Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * 
	 * 
	 *             The JNDI name to be looked up to resolve a resource reference.
	 *             
	 *             @since Java EE 6
	 *           
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Lookup Name</em>' attribute.
	 * @see #setLookupName(String)
	 * @see org.eclipse.jst.javaee.core.internal.metadata.JavaeePackage#getServiceRef_LookupName()
	 * @generated
	 */
	String getLookupName();

	/**
	 * Sets the value of the '{@link org.eclipse.jst.javaee.core.ServiceRef#getLookupName <em>Lookup Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Lookup Name</em>' attribute.
	 * @see #getLookupName()
	 * @generated
	 */
	void setLookupName(String value);

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
	 * @see org.eclipse.jst.javaee.core.internal.metadata.JavaeePackage#getServiceRef_Id()
	 * @generated
	 */
	String getId();

	/**
	 * Sets the value of the '{@link org.eclipse.jst.javaee.core.ServiceRef#getId <em>Id</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Id</em>' attribute.
	 * @see #getId()
	 * @generated
	 */
	void setId(String value);

	/**
	 * This method is provided for API compliance with the older Java EE 5 models
	 * @param newServiceQname
	 */
	void setServiceQname(Object newServiceQname);
} // ServiceRef