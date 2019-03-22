/*******************************************************************************
 * Copyright (c) 2009 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 *
 * Contributors:
 * IBM Corporation - initial API and implementation
 *******************************************************************************/
package org.eclipse.jst.javaee.jca;

import java.util.List;

import org.eclipse.jst.javaee.core.JavaEEObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Activation Spec</b></em>'.
 * <!-- end-user-doc -->
 *
 * <!-- begin-model-doc -->
 * 
 * 
 *         The activationspecType specifies an activation
 *         specification.  The information includes fully qualified
 *         Java class name of an activation specification and a set of
 *         required configuration property names.
 *         
 *         @since Java EE 6, Connector 1.6
 *       
 * <!-- end-model-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.eclipse.jst.javaee.jca.ActivationSpec#getActivationspecClass <em>Activationspec Class</em>}</li>
 *   <li>{@link org.eclipse.jst.javaee.jca.ActivationSpec#getRequiredConfigProperty <em>Required Config Property</em>}</li>
 *   <li>{@link org.eclipse.jst.javaee.jca.ActivationSpec#getConfigProperty <em>Config Property</em>}</li>
 *   <li>{@link org.eclipse.jst.javaee.jca.ActivationSpec#getId <em>Id</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.eclipse.jst.javaee.jca.internal.metadata.JcaPackage#getActivationSpec()
 * @extends JavaEEObject
 * @generated
 */
public interface ActivationSpec extends JavaEEObject {
	/**
	 * Returns the value of the '<em><b>Activationspec Class</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * 
	 * [
	 *             The element activationspec-class specifies the fully
	 *             qualified Java class name of the activation
	 *             specification class. This class must implement the
	 *             javax.resource.spi.ActivationSpec interface. The
	 *             implementation of this class is required to be a
	 *             JavaBean.
	 *             
	 *             Example:
	 *             	  <activationspec-class>com.wombat.ActivationSpecImpl
	 *             	  </activationspec-class>
	 *             
	 * 
	 * 			@since Java EE 6, Connector 1.6
	 *           
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Activationspec Class</em>' attribute.
	 * @see #setActivationspecClass(String)
	 * @see org.eclipse.jst.javaee.jca.internal.metadata.JcaPackage#getActivationSpec_ActivationspecClass()
	 * @generated
	 */
	String getActivationspecClass();

	/**
	 * Sets the value of the '{@link org.eclipse.jst.javaee.jca.ActivationSpec#getActivationspecClass <em>Activationspec Class</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Activationspec Class</em>' attribute.
	 * @see #getActivationspecClass()
	 * @generated
	 */
	void setActivationspecClass(String value);

	/**
	 * Returns the value of the '<em><b>Required Config Property</b></em>' containment reference list.
	 * The list contents are of type {@link org.eclipse.jst.javaee.jca.RequiredConfigProperty}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * 
	 * 
	 *             The required-config-property element is deprecated since
	 *             Connectors 1.6 specification. The resource adapter 
	 *             implementation is recommended to use the @NotNull
	 *             Bean Validation annotation or its XML validation
	 *             descriptor equivalent to indicate that a configuration
	 *             property is required to be specified by the deployer.
	 *             See the Connectors specification for more information. 
	 *             
	 *             @since Java EE 6, Connector 1.6
	 *           
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Required Config Property</em>' containment reference list.
	 * @see org.eclipse.jst.javaee.jca.internal.metadata.JcaPackage#getActivationSpec_RequiredConfigProperty()
	 * @generated
	 */
	List<RequiredConfigProperty> getRequiredConfigProperty();

	/**
	 * Returns the value of the '<em><b>Config Property</b></em>' containment reference list.
	 * The list contents are of type {@link org.eclipse.jst.javaee.jca.ConfigProperty}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Config Property</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Config Property</em>' containment reference list.
	 * @see org.eclipse.jst.javaee.jca.internal.metadata.JcaPackage#getActivationSpec_ConfigProperty()
	 * @generated
	 */
	List<ConfigProperty> getConfigProperty();

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
	 * @see org.eclipse.jst.javaee.jca.internal.metadata.JcaPackage#getActivationSpec_Id()
	 * @generated
	 */
	String getId();

	/**
	 * Sets the value of the '{@link org.eclipse.jst.javaee.jca.ActivationSpec#getId <em>Id</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Id</em>' attribute.
	 * @see #getId()
	 * @generated
	 */
	void setId(String value);

} // ActivationSpec
