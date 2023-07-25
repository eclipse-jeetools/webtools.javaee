/*******************************************************************************
 * Copyright (c) 2007, 2019 IBM Corporation and others.
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

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Service Ref Handler Chains</b></em>'.
 * <!-- end-user-doc -->
 *
 * <!-- begin-model-doc -->
 * 
 * 
 *         The handler-chains element defines the handlerchains associated with this
 *         service or service endpoint.
 *         
 *         @since Java EE 5, Web Services Client 1.2
 *       
 * <!-- end-model-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.eclipse.jst.javaee.core.ServiceRefHandlerChains#getHandlerChains <em>Handler Chains</em>}</li>
 *   <li>{@link org.eclipse.jst.javaee.core.ServiceRefHandlerChains#getId <em>Id</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.eclipse.jst.javaee.core.internal.metadata.JavaeePackage#getServiceRefHandlerChains()
 * @extends JavaEEObject
 * @generated
 */
public interface ServiceRefHandlerChains extends JavaEEObject {
	/**
	 * Returns the value of the '<em><b>Handler Chains</b></em>' containment reference list.
	 * The list contents are of type {@link org.eclipse.jst.javaee.core.ServiceRefHandlerChain}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Handler Chains</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Handler Chains</em>' containment reference list.
	 * @see org.eclipse.jst.javaee.core.internal.metadata.JavaeePackage#getServiceRefHandlerChains_HandlerChains()
	 * @generated
	 */
	List<ServiceRefHandlerChain> getHandlerChains();

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
	 * @see org.eclipse.jst.javaee.core.internal.metadata.JavaeePackage#getServiceRefHandlerChains_Id()
	 * @generated
	 */
	String getId();

	/**
	 * Sets the value of the '{@link org.eclipse.jst.javaee.core.ServiceRefHandlerChains#getId <em>Id</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Id</em>' attribute.
	 * @see #getId()
	 * @generated
	 */
	void setId(String value);

} // ServiceRefHandlerChains