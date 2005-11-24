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
package org.eclipse.jst.j2ee.jca;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Activation Spec</b></em>'.
 * <!-- end-user-doc -->
 *
 * <!-- begin-model-doc -->
 * @version J2EE1.4
 * The activationspecType specifies an activation
 * specification.  The information includes fully qualified
 * Java class name of an activation specification and a set of
 * required configuration property names.
 * <!-- end-model-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.eclipse.jst.j2ee.jca.ActivationSpec#getActivationSpecClass <em>Activation Spec Class</em>}</li>
 *   <li>{@link org.eclipse.jst.j2ee.jca.ActivationSpec#getRequiredConfigProperties <em>Required Config Properties</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.eclipse.jst.j2ee.jca.JcaPackage#getActivationSpec()
 * @model
 * @generated
 * @since 1.0 */
public interface ActivationSpec extends EObject{
	/**
	 * Returns the value of the '<em><b>Activation Spec Class</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Activation Spec Class</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * The element activationspec-class specifies the fully
	 *         qualified Java class name of the activation
	 *         specification class. This class must implement the
	 *         javax.resource.spi.ActivationSpec interface. The
	 *         implementation of this class is required to be a
	 *         JavaBean.
	 * 
	 *         Example:
	 *             <activationspec-class>com.wombat.ActivationSpecImpl
	 *             </activationspec-class>
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Activation Spec Class</em>' attribute.
	 * @see #setActivationSpecClass(String)
	 * @see org.eclipse.jst.j2ee.jca.JcaPackage#getActivationSpec_ActivationSpecClass()
	 * @model
	 * @generated
	 */
	String getActivationSpecClass();

	/**
	 * Sets the value of the '{@link org.eclipse.jst.j2ee.jca.ActivationSpec#getActivationSpecClass <em>Activation Spec Class</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Activation Spec Class</em>' attribute.
	 * @see #getActivationSpecClass()
	 * @generated
	 */
	void setActivationSpecClass(String value);

	/**
	 * Returns the value of the '<em><b>Required Config Properties</b></em>' containment reference list.
	 * The list contents are of type {@link org.eclipse.jst.j2ee.jca.RequiredConfigPropertyType}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Required Config Properties</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Required Config Properties</em>' containment reference list.
	 * @see org.eclipse.jst.j2ee.jca.JcaPackage#getActivationSpec_RequiredConfigProperties()
	 * @model type="org.eclipse.jst.j2ee.jca.RequiredConfigPropertyType" containment="true"
	 * @generated
	 */
	EList getRequiredConfigProperties();

} // ActivationSpec
