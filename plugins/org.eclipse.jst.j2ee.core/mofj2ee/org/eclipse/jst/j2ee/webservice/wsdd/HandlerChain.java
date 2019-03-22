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

package org.eclipse.jst.j2ee.webservice.wsdd;

import org.eclipse.emf.common.util.EList;

import org.eclipse.jst.j2ee.common.J2EEEObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Handler Chain</b></em>'.
 * <!-- end-user-doc -->
 *
 * <!-- begin-model-doc -->
 * Declares the handler for a port-component. Handlers can access the init-param name/value pairs using the HandlerInfo interface.
 * 
 * Used in: port-component
 * 
 * <!-- end-model-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.eclipse.jst.j2ee.webservice.wsdd.HandlerChain#getServiceNamePattern <em>Service Name Pattern</em>}</li>
 *   <li>{@link org.eclipse.jst.j2ee.webservice.wsdd.HandlerChain#getPortNamePattern <em>Port Name Pattern</em>}</li>
 *   <li>{@link org.eclipse.jst.j2ee.webservice.wsdd.HandlerChain#getProtocolBindings <em>Protocol Bindings</em>}</li>
 *   <li>{@link org.eclipse.jst.j2ee.webservice.wsdd.HandlerChain#getHandlers <em>Handlers</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.eclipse.jst.j2ee.webservice.wsdd.WsddPackage#getHandlerChain()
 * @model
 * @generated
 */
public interface HandlerChain extends J2EEEObject {
	/**
	 * Returns the value of the '<em><b>Service Name Pattern</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Service Name Pattern</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Service Name Pattern</em>' attribute.
	 * @see #setServiceNamePattern(String)
	 * @see org.eclipse.jst.j2ee.webservice.wsdd.WsddPackage#getHandlerChain_ServiceNamePattern()
	 * @model
	 * @generated
	 */
	String getServiceNamePattern();

	/**
	 * Sets the value of the '{@link org.eclipse.jst.j2ee.webservice.wsdd.HandlerChain#getServiceNamePattern <em>Service Name Pattern</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Service Name Pattern</em>' attribute.
	 * @see #getServiceNamePattern()
	 * @generated
	 */
	void setServiceNamePattern(String value);

	/**
	 * Returns the value of the '<em><b>Port Name Pattern</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Port Name Pattern</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Port Name Pattern</em>' attribute.
	 * @see #setPortNamePattern(String)
	 * @see org.eclipse.jst.j2ee.webservice.wsdd.WsddPackage#getHandlerChain_PortNamePattern()
	 * @model
	 * @generated
	 */
	String getPortNamePattern();

	/**
	 * Sets the value of the '{@link org.eclipse.jst.j2ee.webservice.wsdd.HandlerChain#getPortNamePattern <em>Port Name Pattern</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Port Name Pattern</em>' attribute.
	 * @see #getPortNamePattern()
	 * @generated
	 */
	void setPortNamePattern(String value);

	/**
	 * Returns the value of the '<em><b>Protocol Bindings</b></em>' attribute list.
	 * The list contents are of type {@link java.lang.String}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Protocol Bindings</em>' attribute list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Protocol Bindings</em>' attribute list.
	 * @see org.eclipse.jst.j2ee.webservice.wsdd.WsddPackage#getHandlerChain_ProtocolBindings()
	 * @model
	 * @generated
	 */
	EList getProtocolBindings();

	/**
	 * Returns the value of the '<em><b>Handlers</b></em>' containment reference list.
	 * The list contents are of type {@link org.eclipse.jst.j2ee.webservice.wsdd.Handler}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Handlers</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Handlers</em>' containment reference list.
	 * @see org.eclipse.jst.j2ee.webservice.wsdd.WsddPackage#getHandlerChain_Handlers()
	 * @model type="org.eclipse.jst.j2ee.webservice.wsdd.Handler" containment="true" required="true"
	 * @generated
	 */
	EList getHandlers();

} // HandlerChain
