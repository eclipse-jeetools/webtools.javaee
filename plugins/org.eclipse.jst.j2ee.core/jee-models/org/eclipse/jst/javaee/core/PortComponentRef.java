/*******************************************************************************
 * Copyright (c) 2007 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 * IBM Corporation - initial API and implementation
 *******************************************************************************/
package org.eclipse.jst.javaee.core;


/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Port Component Ref</b></em>'.
 * <!-- end-user-doc -->
 *
 * <!-- begin-model-doc -->
 * 
 * 
 * 	The port-component-ref element declares a client dependency
 * 	on the container for resolving a Service Endpoint Interface
 * 	to a WSDL port. It optionally associates the Service Endpoint
 * 	Interface with a particular port-component. This is only used
 * 	by the container for a Service.getPort(Class) method call.
 * 
 *       
 * <!-- end-model-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.eclipse.jst.javaee.core.PortComponentRef#getServiceEndpointInterface <em>Service Endpoint Interface</em>}</li>
 *   <li>{@link org.eclipse.jst.javaee.core.PortComponentRef#isEnableMtom <em>Enable Mtom</em>}</li>
 *   <li>{@link org.eclipse.jst.javaee.core.PortComponentRef#getPortComponentLink <em>Port Component Link</em>}</li>
 *   <li>{@link org.eclipse.jst.javaee.core.PortComponentRef#getId <em>Id</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.eclipse.jst.javaee.core.internal.metadata.JavaeePackage#getPortComponentRef()
 * @extends JavaEEObject
 * @generated
 */
public interface PortComponentRef extends JavaEEObject {
	/**
	 * Returns the value of the '<em><b>Service Endpoint Interface</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * 
	 * 
	 * 	    The service-endpoint-interface element defines a fully qualified
	 * 	    Java class that represents the Service Endpoint Interface of a
	 * 	    WSDL port.
	 * 
	 * 	  
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Service Endpoint Interface</em>' attribute.
	 * @see #setServiceEndpointInterface(String)
	 * @see org.eclipse.jst.javaee.core.internal.metadata.JavaeePackage#getPortComponentRef_ServiceEndpointInterface()
	 * @generated
	 */
	String getServiceEndpointInterface();

	/**
	 * Sets the value of the '{@link org.eclipse.jst.javaee.core.PortComponentRef#getServiceEndpointInterface <em>Service Endpoint Interface</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Service Endpoint Interface</em>' attribute.
	 * @see #getServiceEndpointInterface()
	 * @generated
	 */
	void setServiceEndpointInterface(String value);

	/**
	 * Returns the value of the '<em><b>Enable Mtom</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * 
	 * 
	 *             Used to enable or disable SOAP MTOM/XOP mechanism on the client
	 * 	    side for a port-component.
	 * 
	 * 	    Not to be specified for JAX-RPC runtime
	 *           
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Enable Mtom</em>' attribute.
	 * @see #isSetEnableMtom()
	 * @see #unsetEnableMtom()
	 * @see #setEnableMtom(boolean)
	 * @see org.eclipse.jst.javaee.core.internal.metadata.JavaeePackage#getPortComponentRef_EnableMtom()
	 * @generated
	 */
	boolean isEnableMtom();

	/**
	 * Sets the value of the '{@link org.eclipse.jst.javaee.core.PortComponentRef#isEnableMtom <em>Enable Mtom</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Enable Mtom</em>' attribute.
	 * @see #isSetEnableMtom()
	 * @see #unsetEnableMtom()
	 * @see #isEnableMtom()
	 * @generated
	 */
	void setEnableMtom(boolean value);

	/**
	 * Unsets the value of the '{@link org.eclipse.jst.javaee.core.PortComponentRef#isEnableMtom <em>Enable Mtom</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isSetEnableMtom()
	 * @see #isEnableMtom()
	 * @see #setEnableMtom(boolean)
	 * @generated
	 */
	void unsetEnableMtom();

	/**
	 * Returns whether the value of the '{@link org.eclipse.jst.javaee.core.PortComponentRef#isEnableMtom <em>Enable Mtom</em>}' attribute is set.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return whether the value of the '<em>Enable Mtom</em>' attribute is set.
	 * @see #unsetEnableMtom()
	 * @see #isEnableMtom()
	 * @see #setEnableMtom(boolean)
	 * @generated
	 */
	boolean isSetEnableMtom();

	/**
	 * Returns the value of the '<em><b>Port Component Link</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * 
	 * 
	 * 	    The port-component-link element links a port-component-ref
	 * 	    to a specific port-component required to be made available
	 * 	    by a service reference.
	 * 
	 * 	    The value of a port-component-link must be the
	 * 	    port-component-name of a port-component in the same module
	 * 	    or another module in the same application unit. The syntax
	 * 	    for specification follows the syntax defined for ejb-link
	 * 	    in the EJB 2.0 specification.
	 * 
	 * 	  
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Port Component Link</em>' attribute.
	 * @see #setPortComponentLink(String)
	 * @see org.eclipse.jst.javaee.core.internal.metadata.JavaeePackage#getPortComponentRef_PortComponentLink()
	 * @generated
	 */
	String getPortComponentLink();

	/**
	 * Sets the value of the '{@link org.eclipse.jst.javaee.core.PortComponentRef#getPortComponentLink <em>Port Component Link</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Port Component Link</em>' attribute.
	 * @see #getPortComponentLink()
	 * @generated
	 */
	void setPortComponentLink(String value);

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
	 * @see org.eclipse.jst.javaee.core.internal.metadata.JavaeePackage#getPortComponentRef_Id()
	 * @generated
	 */
	String getId();

	/**
	 * Sets the value of the '{@link org.eclipse.jst.javaee.core.PortComponentRef#getId <em>Id</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Id</em>' attribute.
	 * @see #getId()
	 * @generated
	 */
	void setId(String value);

} // PortComponentRef