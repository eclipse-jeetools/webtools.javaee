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
 * A representation of the model object '<em><b>Component Scoped Refs</b></em>'.
 * <!-- end-user-doc -->
 *
 * <!-- begin-model-doc -->
 * @since J2EE1.4 deprecated
 * <!-- end-model-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.eclipse.jst.j2ee.internal.webservice.wsclient.ComponentScopedRefs#getComponentName <em>Component Name</em>}</li>
 *   <li>{@link org.eclipse.jst.j2ee.internal.webservice.wsclient.ComponentScopedRefs#getServiceRefs <em>Service Refs</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.eclipse.jst.j2ee.internal.webservice.wsclient.Webservice_clientPackage#getComponentScopedRefs()
 * @model 
 * @generated
 */
public interface ComponentScopedRefs extends EObject{
	/**
	 * Returns the value of the '<em><b>Component Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Component Name</em>' attribute isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
	 * @return the value of the '<em>Component Name</em>' attribute.
	 * @see #setComponentName(String)
	 * @see org.eclipse.jst.j2ee.internal.webservice.wsclient.Webservice_clientPackage#getComponentScopedRefs_ComponentName()
	 * @model 
	 * @generated
	 */
    String getComponentName();

	/**
	 * Sets the value of the '{@link org.eclipse.jst.j2ee.internal.webservice.wsclient.ComponentScopedRefs#getComponentName <em>Component Name</em>}' attribute.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Component Name</em>' attribute.
	 * @see #getComponentName()
	 * @generated
	 */
    void setComponentName(String value);

	/**
	 * Returns the value of the '<em><b>Service Refs</b></em>' containment reference list.
	 * The list contents are of type {@link org.eclipse.jst.j2ee.internal.webservice.wsclient.ServiceRef}.
	 * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Service Refs</em>' containment reference list isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
	 * @return the value of the '<em>Service Refs</em>' containment reference list.
	 * @see org.eclipse.jst.j2ee.internal.webservice.wsclient.Webservice_clientPackage#getComponentScopedRefs_ServiceRefs()
	 * @model type="org.eclipse.jst.j2ee.internal.webservice.wsclient.ServiceRef" containment="true" required="true"
	 * @generated
	 */
    EList getServiceRefs();

} // ComponentScopedRefs
