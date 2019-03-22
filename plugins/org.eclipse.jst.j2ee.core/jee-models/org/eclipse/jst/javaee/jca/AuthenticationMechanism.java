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

import org.eclipse.jst.javaee.core.Description;
import org.eclipse.jst.javaee.core.JavaEEObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Authentication Mechanism</b></em>'.
 * <!-- end-user-doc -->
 *
 * <!-- begin-model-doc -->
 * 
 * 
 *         The authentication-mechanismType specifies an authentication
 *         mechanism supported by the resource adapter. Note that this
 *         support is for the resource adapter and not for the
 *         underlying EIS instance. The optional description specifies
 *         any resource adapter specific requirement for the support of
 *         security contract and authentication mechanism.
 *         
 *         Note that BasicPassword mechanism type should support the
 *         javax.resource.spi.security.PasswordCredential interface.
 *         The Kerbv5 mechanism type should support the
 *         org.ietf.jgss.GSSCredential interface or the deprecated
 *         javax.resource.spi.security.GenericCredential interface.
 *         
 *         @since Java EE 6, Connector 1.6
 *       
 * <!-- end-model-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.eclipse.jst.javaee.jca.AuthenticationMechanism#getDescription <em>Description</em>}</li>
 *   <li>{@link org.eclipse.jst.javaee.jca.AuthenticationMechanism#getAuthenticationMechanismType <em>Authentication Mechanism Type</em>}</li>
 *   <li>{@link org.eclipse.jst.javaee.jca.AuthenticationMechanism#getCredentialInterface <em>Credential Interface</em>}</li>
 *   <li>{@link org.eclipse.jst.javaee.jca.AuthenticationMechanism#getId <em>Id</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.eclipse.jst.javaee.jca.internal.metadata.JcaPackage#getAuthenticationMechanism()
 * @extends JavaEEObject
 * @generated
 */
public interface AuthenticationMechanism extends JavaEEObject {
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
	 * @see org.eclipse.jst.javaee.jca.internal.metadata.JcaPackage#getAuthenticationMechanism_Description()
	 * @generated
	 */
	List<Description> getDescription();

	/**
	 * Returns the value of the '<em><b>Authentication Mechanism Type</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * 
	 * [
	 *             The element authentication-mechanism-type specifies
	 *             type of an authentication mechanism.
	 *             
	 *             The example values are:
	 *             
	 *             <authentication-mechanism-type>BasicPassword
	 *             </authentication-mechanism-type>
	 *             
	 *             <authentication-mechanism-type>Kerbv5
	 *             </authentication-mechanism-type>
	 *             
	 *             Any additional security mechanisms are outside the
	 *             scope of the Connector architecture specification.
	 *             
	 * 
	 * 			@since Java EE 6, Connector 1.6
	 *           
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Authentication Mechanism Type</em>' attribute.
	 * @see #setAuthenticationMechanismType(String)
	 * @see org.eclipse.jst.javaee.jca.internal.metadata.JcaPackage#getAuthenticationMechanism_AuthenticationMechanismType()
	 * @generated
	 */
	String getAuthenticationMechanismType();

	/**
	 * Sets the value of the '{@link org.eclipse.jst.javaee.jca.AuthenticationMechanism#getAuthenticationMechanismType <em>Authentication Mechanism Type</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Authentication Mechanism Type</em>' attribute.
	 * @see #getAuthenticationMechanismType()
	 * @generated
	 */
	void setAuthenticationMechanismType(String value);

	/**
	 * Returns the value of the '<em><b>Credential Interface</b></em>' attribute.
	 * The literals are from the enumeration {@link org.eclipse.jst.javaee.jca.CredentialInterfaceType}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Credential Interface</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Credential Interface</em>' attribute.
	 * @see org.eclipse.jst.javaee.jca.CredentialInterfaceType
	 * @see #isSetCredentialInterface()
	 * @see #unsetCredentialInterface()
	 * @see #setCredentialInterface(CredentialInterfaceType)
	 * @see org.eclipse.jst.javaee.jca.internal.metadata.JcaPackage#getAuthenticationMechanism_CredentialInterface()
	 * @generated
	 */
	CredentialInterfaceType getCredentialInterface();

	/**
	 * Sets the value of the '{@link org.eclipse.jst.javaee.jca.AuthenticationMechanism#getCredentialInterface <em>Credential Interface</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Credential Interface</em>' attribute.
	 * @see org.eclipse.jst.javaee.jca.CredentialInterfaceType
	 * @see #isSetCredentialInterface()
	 * @see #unsetCredentialInterface()
	 * @see #getCredentialInterface()
	 * @generated
	 */
	void setCredentialInterface(CredentialInterfaceType value);

	/**
	 * Unsets the value of the '{@link org.eclipse.jst.javaee.jca.AuthenticationMechanism#getCredentialInterface <em>Credential Interface</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isSetCredentialInterface()
	 * @see #getCredentialInterface()
	 * @see #setCredentialInterface(CredentialInterfaceType)
	 * @generated
	 */
	void unsetCredentialInterface();

	/**
	 * Returns whether the value of the '{@link org.eclipse.jst.javaee.jca.AuthenticationMechanism#getCredentialInterface <em>Credential Interface</em>}' attribute is set.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return whether the value of the '<em>Credential Interface</em>' attribute is set.
	 * @see #unsetCredentialInterface()
	 * @see #getCredentialInterface()
	 * @see #setCredentialInterface(CredentialInterfaceType)
	 * @generated
	 */
	boolean isSetCredentialInterface();

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
	 * @see org.eclipse.jst.javaee.jca.internal.metadata.JcaPackage#getAuthenticationMechanism_Id()
	 * @generated
	 */
	String getId();

	/**
	 * Sets the value of the '{@link org.eclipse.jst.javaee.jca.AuthenticationMechanism#getId <em>Id</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Id</em>' attribute.
	 * @see #getId()
	 * @generated
	 */
	void setId(String value);

} // AuthenticationMechanism
