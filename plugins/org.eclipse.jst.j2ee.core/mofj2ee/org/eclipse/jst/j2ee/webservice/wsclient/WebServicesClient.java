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
import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Web Services Client</b></em>'.
 * <!-- end-user-doc -->
 *
 * <!-- begin-model-doc -->
 * @version J2EE1.4 deprecated
 * <!-- end-model-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.eclipse.jst.j2ee.webservice.wsclient.WebServicesClient#getComponentScopedRefs <em>Component Scoped Refs</em>}</li>
 *   <li>{@link org.eclipse.jst.j2ee.webservice.wsclient.WebServicesClient#getServiceRefs <em>Service Refs</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.eclipse.jst.j2ee.webservice.wsclient.Webservice_clientPackage#getWebServicesClient()
 * @model
 * @generated
 */
public interface WebServicesClient extends EObject{
	/**
	 * Returns the value of the '<em><b>Component Scoped Refs</b></em>' containment reference list.
	 * The list contents are of type {@link org.eclipse.jst.j2ee.webservice.wsclient.ComponentScopedRefs}.
	 * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Component Scoped Refs</em>' containment reference list isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
	 * @return the value of the '<em>Component Scoped Refs</em>' containment reference list.
	 * @see org.eclipse.jst.j2ee.webservice.wsclient.Webservice_clientPackage#getWebServicesClient_ComponentScopedRefs()
	 * @model type="org.eclipse.jst.j2ee.webservice.wsclient.ComponentScopedRefs" containment="true"
	 * @generated
	 */
    EList getComponentScopedRefs();

	/**
	 * Returns the value of the '<em><b>Service Refs</b></em>' containment reference list.
	 * The list contents are of type {@link org.eclipse.jst.j2ee.webservice.wsclient.ServiceRef}.
	 * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Service Refs</em>' containment reference list isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
	 * @return the value of the '<em>Service Refs</em>' containment reference list.
	 * @see org.eclipse.jst.j2ee.webservice.wsclient.Webservice_clientPackage#getWebServicesClient_ServiceRefs()
	 * @model type="org.eclipse.jst.j2ee.webservice.wsclient.ServiceRef" containment="true"
	 * @generated
	 */
    EList getServiceRefs();

} // WebServicesClient
