/*******************************************************************************
 * Copyright (c) 2001, 2005 IBM Corporation and others.
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
 * The element authentication-mechanism specifies an authentication mechanism supported by the resource adapter. Note that this support is for the resource adapter and not for the underlying EIS instance. The optional description specifies any resource adapter specific requirement for the support of security contract and authentication mechanism.
 * 
 * Note that BasicPassword mechanism type should support the
 * javax.resource.spi.security.PasswordCredential interface.
 * The Kerbv5 mechanism type should support the
 * javax.resource.spi.security.GenericCredential interface.

 * @since 1.0 */
public interface AuthenticationMechanism extends EObject{

	/**
	 * @generated This field/method will be replaced during code generation 
	 * @return The value of the Description attribute
	 */
	String getDescription();

	/**
	 * @generated This field/method will be replaced during code generation 
	 * @param value The new value of the Description attribute
	 */
	void setDescription(String value);

	/**
	 * Returns the value of the '<em><b>Authentication Mechanism</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Authentication Mechanism</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Authentication Mechanism</em>' attribute.
	 * @see #setAuthenticationMechanism(String)
	 * @see org.eclipse.jst.j2ee.jca.JcaPackage#getAuthenticationMechanism_AuthenticationMechanism()
	 * @model
	 * @generated
	 */
	String getAuthenticationMechanism();

	/**
	 * Sets the value of the '{@link org.eclipse.jst.j2ee.jca.AuthenticationMechanism#getAuthenticationMechanism <em>Authentication Mechanism</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Authentication Mechanism</em>' attribute.
	 * @see #getAuthenticationMechanism()
	 * @generated
	 */
	void setAuthenticationMechanism(String value);

	/**
	 * @generated This field/method will be replaced during code generation 
	 * @return The value of the AuthenticationMechanismType attribute
	 * The authentication-mechanism-type specifies type of an authentication mechanism.
	 * 
	 * The example values are:
	 *   - BasicPassword
	 *   - Kerbv5
	 * 
	 * Any additional security mechanisms are outside the scope of the
	 * Connector architecture specification.

	 */
	AuthenticationMechanismType getAuthenticationMechanismType();

	/**
	 * Sets the value of the '{@link org.eclipse.jst.j2ee.jca.AuthenticationMechanism#getAuthenticationMechanismType <em>Authentication Mechanism Type</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Authentication Mechanism Type</em>' attribute.
	 * @see org.eclipse.jst.j2ee.jca.AuthenticationMechanismType
	 * @see #isSetAuthenticationMechanismType()
	 * @see #unsetAuthenticationMechanismType()
	 * @see #getAuthenticationMechanismType()
	 * @generated
	 */
	void setAuthenticationMechanismType(AuthenticationMechanismType value);

	/**
	 * Unsets the value of the '{@link org.eclipse.jst.j2ee.jca.AuthenticationMechanism#getAuthenticationMechanismType <em>Authentication Mechanism Type</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isSetAuthenticationMechanismType()
	 * @see #getAuthenticationMechanismType()
	 * @see #setAuthenticationMechanismType(AuthenticationMechanismType)
	 * @generated
	 */
	void unsetAuthenticationMechanismType();

	/**
	 * Returns whether the value of the '{@link org.eclipse.jst.j2ee.jca.AuthenticationMechanism#getAuthenticationMechanismType <em>Authentication Mechanism Type</em>}' attribute is set.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return whether the value of the '<em>Authentication Mechanism Type</em>' attribute is set.
	 * @see #unsetAuthenticationMechanismType()
	 * @see #getAuthenticationMechanismType()
	 * @see #setAuthenticationMechanismType(AuthenticationMechanismType)
	 * @generated
	 */
	boolean isSetAuthenticationMechanismType();

	/**
	 * @generated This field/method will be replaced during code generation 
	 * @return The value of the CredentialInterface attribute
	 * Specifies the interface that the resource adapter implementation supports for
	 * the representation of the credentials. This element should be used by
	 * application server to find out the Credential interface it should use as part
	 * of the security contract.
	 * 
	 * The possible values are:
	 *    javax.resource.spi.security.PasswordCredential
	 *    javax.resource.spi.security.GenericCredential

	 */
	String getCredentialInterface();

	/**
	 * @generated This field/method will be replaced during code generation 
	 * @param value The new value of the CredentialInterface attribute
	 */
	void setCredentialInterface(String value);

	/**
	 * @generated This field/method will be replaced during code generation 
	 * @return The value of the CustomAuthMechType attribute
	 * This is an internal variable used to store the AthenticationMechanismType when
	 * it is something other than BasicPassword or Kerbv5.  AuthenticationMecanismType 
	 * is set to 'Custom' in those cases.  This attribute should not be used by
	 * runtime.  It is only used by the writer to write back out the custom
	 * AuthenticationMechanismType string in the case of custom
	 * AuthenticationMechanismType's.  
	 * This variable is internal, used only by the reader//writer classes, and can
	 * change or disappear without notice.  
	 */
	String getCustomAuthMechType();

	/**
	 * @generated This field/method will be replaced during code generation 
	 * @param value The new value of the CustomAuthMechType attribute
	 */
	void setCustomAuthMechType(String value);

	/**
	 * Returns the value of the '<em><b>Descriptions</b></em>' containment reference list.
	 * The list contents are of type {@link org.eclipse.jst.j2ee.common.Description}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Descriptions</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * @version J2EE1.4
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Descriptions</em>' containment reference list.
	 * @see org.eclipse.jst.j2ee.jca.JcaPackage#getAuthenticationMechanism_Descriptions()
	 * @model type="org.eclipse.jst.j2ee.common.Description" containment="true"
	 * @generated
	 */
	EList getDescriptions();

}































































































































































































































































































