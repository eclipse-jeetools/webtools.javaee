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
package org.eclipse.jst.j2ee.ejb;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Activation Config</b></em>'.
 * <!-- end-user-doc -->
 *
 * <!-- begin-model-doc -->
 * @version J2EE1.4
 * The activation-configType defines information about the
 * expected configuration properties of the message-driven bean
 * in its operational environment. This may include information
 * about message acknowledgement, message selector, expected
 * destination type, etc.
 * 
 * The configuration information is expressed in terms of
 * name/value configuration properties.
 * 
 * The properties that are recognized for a particular
 * message-driven bean are determined by the messaging type.
 * <!-- end-model-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.eclipse.jst.j2ee.ejb.ActivationConfig#getConfigProperties <em>Config Properties</em>}</li>
 *   <li>{@link org.eclipse.jst.j2ee.ejb.ActivationConfig#getDescriptions <em>Descriptions</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.eclipse.jst.j2ee.ejb.EjbPackage#getActivationConfig()
 * @model
 * @generated
 */
public interface ActivationConfig extends EObject{
	/**
	 * Returns the value of the '<em><b>Config Properties</b></em>' containment reference list.
	 * The list contents are of type {@link org.eclipse.jst.j2ee.ejb.ActivationConfigProperty}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Config Properties</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Config Properties</em>' containment reference list.
	 * @see org.eclipse.jst.j2ee.ejb.EjbPackage#getActivationConfig_ConfigProperties()
	 * @model type="org.eclipse.jst.j2ee.ejb.ActivationConfigProperty" containment="true" required="true"
	 * @generated
	 */
	EList getConfigProperties();

	/**
	 * Returns the value of the '<em><b>Descriptions</b></em>' containment reference list.
	 * The list contents are of type {@link org.eclipse.jst.j2ee.common.Description}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Descriptions</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Descriptions</em>' containment reference list.
	 * @see org.eclipse.jst.j2ee.ejb.EjbPackage#getActivationConfig_Descriptions()
	 * @model type="org.eclipse.jst.j2ee.common.Description" containment="true"
	 * @generated
	 */
	EList getDescriptions();

} // ActivationConfig
