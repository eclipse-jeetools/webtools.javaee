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

package org.eclipse.jst.j2ee.webservice.wsdd;

import org.eclipse.emf.common.util.EList;

import org.eclipse.jst.j2ee.common.J2EEEObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Handlers Chains</b></em>'.
 * <!-- end-user-doc -->
 *
 * <!-- begin-model-doc -->
 * The ejb-link element is used in the service-impl-bean element to specify that a Service Implementation Bean is defined as a Web Service Endpoint.
 * 
 * The value of the ejb-link element must be the ejb-name of an enterprise bean in the same ejb-jar file.
 * 
 * Used in: service-impl-bean
 * 
 * Examples:
 * <ejb-link>EmployeeRecord</ejb-link>
 * 	<ejb-link>../products/product.jar#ProductEJB</ejb-link>
 * 
 * <!-- end-model-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.eclipse.jst.j2ee.webservice.wsdd.HandlersChains#getHandlerChain <em>Handler Chain</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.eclipse.jst.j2ee.webservice.wsdd.WsddPackage#getHandlersChains()
 * @model
 * @generated
 */
public interface HandlersChains extends J2EEEObject {
	/**
	 * Returns the value of the '<em><b>Handler Chain</b></em>' containment reference list.
	 * The list contents are of type {@link org.eclipse.jst.j2ee.webservice.wsdd.HandlerChain}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Handler Chain</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Handler Chain</em>' containment reference list.
	 * @see org.eclipse.jst.j2ee.webservice.wsdd.WsddPackage#getHandlersChains_HandlerChain()
	 * @model type="org.eclipse.jst.j2ee.webservice.wsdd.HandlerChain" containment="true"
	 * @generated
	 */
	EList getHandlerChain();

} // HandlersChains
