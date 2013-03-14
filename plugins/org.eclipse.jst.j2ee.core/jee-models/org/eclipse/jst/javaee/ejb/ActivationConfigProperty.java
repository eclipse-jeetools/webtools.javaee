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
package org.eclipse.jst.javaee.ejb;

import org.eclipse.jst.javaee.core.JavaEEObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Activation Config Property</b></em>'.
 * <!-- end-user-doc -->
 *
 * <!-- begin-model-doc -->
 * 
 * 
 *         The activation-config-propertyType contains a name/value
 *         configuration property pair for a message-driven bean.
 *         
 *         The properties that are recognized for a particular
 *         message-driven bean are determined by the messaging type.
 *         
 *         @since Java EE 5, EJB 3.0
 *       
 * <!-- end-model-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.eclipse.jst.javaee.ejb.ActivationConfigProperty#getActivationConfigPropertyName <em>Activation Config Property Name</em>}</li>
 *   <li>{@link org.eclipse.jst.javaee.ejb.ActivationConfigProperty#getActivationConfigPropertyValue <em>Activation Config Property Value</em>}</li>
 *   <li>{@link org.eclipse.jst.javaee.ejb.ActivationConfigProperty#getId <em>Id</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.eclipse.jst.javaee.ejb.internal.metadata.EjbPackage#getActivationConfigProperty()
 * @extends JavaEEObject
 * @generated
 */
public interface ActivationConfigProperty extends JavaEEObject {
	/**
	 * Returns the value of the '<em><b>Activation Config Property Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * 
	 * 
	 *             The activation-config-property-name element contains
	 *             the name for an activation configuration property of
	 *             a message-driven bean.
	 *             
	 *             For JMS message-driven beans, the following property
	 *             names are recognized: acknowledgeMode,
	 *             messageSelector, destinationType, subscriptionDurability,
	 *             destinationLookup, connectionFactoryLookup, subscriptionName,
	 *             clientId, and shareSubscriptions.
	 *             
	 *             @since Java EE 5, EJB 3.0
	 *           
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Activation Config Property Name</em>' attribute.
	 * @see #setActivationConfigPropertyName(String)
	 * @see org.eclipse.jst.javaee.ejb.internal.metadata.EjbPackage#getActivationConfigProperty_ActivationConfigPropertyName()
	 * @generated
	 */
	String getActivationConfigPropertyName();

	/**
	 * Sets the value of the '{@link org.eclipse.jst.javaee.ejb.ActivationConfigProperty#getActivationConfigPropertyName <em>Activation Config Property Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Activation Config Property Name</em>' attribute.
	 * @see #getActivationConfigPropertyName()
	 * @generated
	 */
	void setActivationConfigPropertyName(String value);

	/**
	 * Returns the value of the '<em><b>Activation Config Property Value</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * 
	 * 
	 *             The activation-config-property-value element
	 *             contains the value for an activation configuration
	 *             property of a message-driven bean.
	 *             
	 *             @since Java EE 5, EJB 3.0
	 *           
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Activation Config Property Value</em>' attribute.
	 * @see #setActivationConfigPropertyValue(String)
	 * @see org.eclipse.jst.javaee.ejb.internal.metadata.EjbPackage#getActivationConfigProperty_ActivationConfigPropertyValue()
	 * @generated
	 */
	String getActivationConfigPropertyValue();

	/**
	 * Sets the value of the '{@link org.eclipse.jst.javaee.ejb.ActivationConfigProperty#getActivationConfigPropertyValue <em>Activation Config Property Value</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Activation Config Property Value</em>' attribute.
	 * @see #getActivationConfigPropertyValue()
	 * @generated
	 */
	void setActivationConfigPropertyValue(String value);

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
	 * @see org.eclipse.jst.javaee.ejb.internal.metadata.EjbPackage#getActivationConfigProperty_Id()
	 * @generated
	 */
	String getId();

	/**
	 * Sets the value of the '{@link org.eclipse.jst.javaee.ejb.ActivationConfigProperty#getId <em>Id</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Id</em>' attribute.
	 * @see #getId()
	 * @generated
	 */
	void setId(String value);

} // ActivationConfigProperty