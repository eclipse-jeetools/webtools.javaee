/*******************************************************************************
 * Copyright (c) 2009 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 * IBM Corporation - initial API and implementation
 *******************************************************************************/
package org.eclipse.jst.javaee.jca;

import java.util.List;

import org.eclipse.jst.javaee.core.Description;
import org.eclipse.jst.javaee.core.JavaEEObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Required Config Property</b></em>'.
 * <!-- end-user-doc -->
 *
 * <!-- begin-model-doc -->
 * 
 * [
 *         The required-config-propertyType contains a declaration
 *         of a single configuration property used for specifying a
 *         required configuration property name. It is used
 *         by required-config-property elements.
 *         
 *         Usage of this type is deprecated from Connectors 1.6 specification. 
 *         Refer to required-config-property element for more information.
 *         
 *         Example:
 *         
 *         <required-config-property>
 *         <config-property-name>Destination</config-property-name>
 *         </required-config-property>
 *         
 * 
 * 		@since Java EE 6, Connector 1.6
 *       
 * <!-- end-model-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.eclipse.jst.javaee.jca.RequiredConfigProperty#getDescription <em>Description</em>}</li>
 *   <li>{@link org.eclipse.jst.javaee.jca.RequiredConfigProperty#getConfigPropertyName <em>Config Property Name</em>}</li>
 *   <li>{@link org.eclipse.jst.javaee.jca.RequiredConfigProperty#getId <em>Id</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.eclipse.jst.javaee.jca.internal.metadata.JcaPackage#getRequiredConfigProperty()
 * @extends JavaEEObject
 * @generated
 */
public interface RequiredConfigProperty extends JavaEEObject {
	/**
	 * Returns the value of the '<em><b>Description</b></em>' containment reference list.
	 * The list contents are of type {@link org.eclipse.jst.javaee.core.Description}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Description</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Description</em>' containment reference list.
	 * @see org.eclipse.jst.javaee.jca.internal.metadata.JcaPackage#getRequiredConfigProperty_Description()
	 * @generated
	 */
	List<Description> getDescription();

	/**
	 * Returns the value of the '<em><b>Config Property Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Config Property Name</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Config Property Name</em>' attribute.
	 * @see #setConfigPropertyName(String)
	 * @see org.eclipse.jst.javaee.jca.internal.metadata.JcaPackage#getRequiredConfigProperty_ConfigPropertyName()
	 * @generated
	 */
	String getConfigPropertyName();

	/**
	 * Sets the value of the '{@link org.eclipse.jst.javaee.jca.RequiredConfigProperty#getConfigPropertyName <em>Config Property Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Config Property Name</em>' attribute.
	 * @see #getConfigPropertyName()
	 * @generated
	 */
	void setConfigPropertyName(String value);

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
	 * @see org.eclipse.jst.javaee.jca.internal.metadata.JcaPackage#getRequiredConfigProperty_Id()
	 * @generated
	 */
	String getId();

	/**
	 * Sets the value of the '{@link org.eclipse.jst.javaee.jca.RequiredConfigProperty#getId <em>Id</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Id</em>' attribute.
	 * @see #getId()
	 * @generated
	 */
	void setId(String value);

} // RequiredConfigProperty
