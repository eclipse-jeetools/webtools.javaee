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

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.eclipse.emf.common.util.Enumerator;

/**
 * <!-- begin-user-doc -->
 * A representation of the literals of the enumeration '<em><b>Credential Interface Type</b></em>',
 * and utility methods for working with them.
 * <!-- end-user-doc -->
 * <!-- begin-model-doc -->
 * 
 * 
 *         The credential-interfaceType specifies the
 *         interface that the resource adapter implementation
 *         supports for the representation of the
 *         credentials. This element(s) that use this type,
 *         i.e. credential-interface,  should be used by
 *         application server to find out the Credential
 *         interface it should use as part of the security
 *         contract.
 *         
 *         The possible values are:
 *         
 *         javax.resource.spi.security.PasswordCredential
 *         org.ietf.jgss.GSSCredential
 *         javax.resource.spi.security.GenericCredential
 *         
 *         @since Java EE 6, Connector 1.6
 *       
 * <!-- end-model-doc -->
 * @see org.eclipse.jst.javaee.jca.internal.metadata.JcaPackage#getCredentialInterfaceType()
 * @generated
 */
public enum CredentialInterfaceType implements Enumerator {
	/**
	 * The '<em><b>Javax Resource Spi Security Password Credential</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #JAVAX_RESOURCE_SPI_SECURITY_PASSWORD_CREDENTIAL_VALUE
	 * @generated
	 * @ordered
	 */
	JAVAX_RESOURCE_SPI_SECURITY_PASSWORD_CREDENTIAL(0, "javaxResourceSpiSecurityPasswordCredential", "javax.resource.spi.security.PasswordCredential"), //$NON-NLS-1$ //$NON-NLS-2$

	/**
	 * The '<em><b>Org Ietf Jgss GSS Credential</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #ORG_IETF_JGSS_GSS_CREDENTIAL_VALUE
	 * @generated
	 * @ordered
	 */
	ORG_IETF_JGSS_GSS_CREDENTIAL(1, "orgIetfJgssGSSCredential", "org.ietf.jgss.GSSCredential"), //$NON-NLS-1$ //$NON-NLS-2$

	/**
	 * The '<em><b>Javax Resource Spi Security Generic Credential</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #JAVAX_RESOURCE_SPI_SECURITY_GENERIC_CREDENTIAL_VALUE
	 * @generated
	 * @ordered
	 */
	JAVAX_RESOURCE_SPI_SECURITY_GENERIC_CREDENTIAL(2, "javaxResourceSpiSecurityGenericCredential", "javax.resource.spi.security.GenericCredential"); //$NON-NLS-1$ //$NON-NLS-2$

	/**
	 * The '<em><b>Javax Resource Spi Security Password Credential</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>Javax Resource Spi Security Password Credential</b></em>' literal object isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @see #JAVAX_RESOURCE_SPI_SECURITY_PASSWORD_CREDENTIAL
	 * @generated
	 * @ordered
	 */
	public static final int JAVAX_RESOURCE_SPI_SECURITY_PASSWORD_CREDENTIAL_VALUE = 0;

	/**
	 * The '<em><b>Org Ietf Jgss GSS Credential</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>Org Ietf Jgss GSS Credential</b></em>' literal object isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @see #ORG_IETF_JGSS_GSS_CREDENTIAL
	 * @generated
	 * @ordered
	 */
	public static final int ORG_IETF_JGSS_GSS_CREDENTIAL_VALUE = 1;

	/**
	 * The '<em><b>Javax Resource Spi Security Generic Credential</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>Javax Resource Spi Security Generic Credential</b></em>' literal object isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @see #JAVAX_RESOURCE_SPI_SECURITY_GENERIC_CREDENTIAL
	 * @generated
	 * @ordered
	 */
	public static final int JAVAX_RESOURCE_SPI_SECURITY_GENERIC_CREDENTIAL_VALUE = 2;

	/**
	 * An array of all the '<em><b>Credential Interface Type</b></em>' enumerators.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private static final CredentialInterfaceType[] VALUES_ARRAY =
		new CredentialInterfaceType[] {
			JAVAX_RESOURCE_SPI_SECURITY_PASSWORD_CREDENTIAL,
			ORG_IETF_JGSS_GSS_CREDENTIAL,
			JAVAX_RESOURCE_SPI_SECURITY_GENERIC_CREDENTIAL,
		};

	/**
	 * A public read-only list of all the '<em><b>Credential Interface Type</b></em>' enumerators.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static final List<CredentialInterfaceType> VALUES = Collections.unmodifiableList(Arrays.asList(VALUES_ARRAY));

	/**
	 * Returns the '<em><b>Credential Interface Type</b></em>' literal with the specified literal value.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static CredentialInterfaceType get(String literal) {
		for (int i = 0; i < VALUES_ARRAY.length; ++i) {
			CredentialInterfaceType result = VALUES_ARRAY[i];
			if (result.toString().equals(literal)) {
				return result;
			}
		}
		return null;
	}

	/**
	 * Returns the '<em><b>Credential Interface Type</b></em>' literal with the specified name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static CredentialInterfaceType getByName(String name) {
		for (int i = 0; i < VALUES_ARRAY.length; ++i) {
			CredentialInterfaceType result = VALUES_ARRAY[i];
			if (result.getName().equals(name)) {
				return result;
			}
		}
		return null;
	}

	/**
	 * Returns the '<em><b>Credential Interface Type</b></em>' literal with the specified integer value.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static CredentialInterfaceType get(int value) {
		switch (value) {
			case JAVAX_RESOURCE_SPI_SECURITY_PASSWORD_CREDENTIAL_VALUE: return JAVAX_RESOURCE_SPI_SECURITY_PASSWORD_CREDENTIAL;
			case ORG_IETF_JGSS_GSS_CREDENTIAL_VALUE: return ORG_IETF_JGSS_GSS_CREDENTIAL;
			case JAVAX_RESOURCE_SPI_SECURITY_GENERIC_CREDENTIAL_VALUE: return JAVAX_RESOURCE_SPI_SECURITY_GENERIC_CREDENTIAL;
		}
		return null;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private final int value;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private final String name;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private final String literal;

	/**
	 * Only this class can construct instances.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private CredentialInterfaceType(int value, String name, String literal) {
		this.value = value;
		this.name = name;
		this.literal = literal;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public int getValue() {
	  return value;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getName() {
	  return name;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getLiteral() {
	  return literal;
	}

	/**
	 * Returns the literal value of the enumerator, which is its string representation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String toString() {
		return literal;
	}
	
} //CredentialInterfaceType
