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
package org.eclipse.jst.j2ee.internal.jca.impl;

import java.util.Collection;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.EObjectImpl;
import org.eclipse.emf.ecore.util.EObjectContainmentEList;
import org.eclipse.emf.ecore.util.InternalEList;
import org.eclipse.jst.j2ee.common.Description;
import org.eclipse.jst.j2ee.internal.jca.JcaPackage;
import org.eclipse.jst.j2ee.jca.AuthenticationMechanism;
import org.eclipse.jst.j2ee.jca.AuthenticationMechanismType;


/**
 * The element authentication-mechanism specifies an authentication mechanism supported by the resource adapter. Note that this support is for the resource adapter and not for the underlying EIS instance. The optional description specifies any resource adapter specific requirement for the support of security contract and authentication mechanism.
 * 
 * Note that BasicPassword mechanism type should support the
 * javax.resource.spi.security.PasswordCredential interface.
 * The Kerbv5 mechanism type should support the
 * javax.resource.spi.security.GenericCredential interface.

 */
public class AuthenticationMechanismImpl extends EObjectImpl implements AuthenticationMechanism, EObject{

	/**
	 * The default value of the '{@link #getDescription() <em>Description</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getDescription()
	 * @generated
	 * @ordered
	 */
	protected static final String DESCRIPTION_EDEFAULT = null;


	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	protected String description = DESCRIPTION_EDEFAULT;
	/**
	 * The default value of the '{@link #getAuthenticationMechanism() <em>Authentication Mechanism</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getAuthenticationMechanism()
	 * @generated
	 * @ordered
	 */
	protected static final String AUTHENTICATION_MECHANISM_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getAuthenticationMechanism() <em>Authentication Mechanism</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getAuthenticationMechanism()
	 * @generated
	 * @ordered
	 */
	protected String authenticationMechanism = AUTHENTICATION_MECHANISM_EDEFAULT;

	/**
	 * The default value of the '{@link #getAuthenticationMechanismType() <em>Authentication Mechanism Type</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getAuthenticationMechanismType()
	 * @generated
	 * @ordered
	 */
	protected static final AuthenticationMechanismType AUTHENTICATION_MECHANISM_TYPE_EDEFAULT = AuthenticationMechanismType.BASIC_PASSWORD_LITERAL;

	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	protected AuthenticationMechanismType authenticationMechanismType = AUTHENTICATION_MECHANISM_TYPE_EDEFAULT;
	/**
	 * This is true if the Authentication Mechanism Type attribute has been set.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	protected boolean authenticationMechanismTypeESet = false;

	/**
	 * The default value of the '{@link #getCredentialInterface() <em>Credential Interface</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getCredentialInterface()
	 * @generated
	 * @ordered
	 */
	protected static final String CREDENTIAL_INTERFACE_EDEFAULT = null;

	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	protected String credentialInterface = CREDENTIAL_INTERFACE_EDEFAULT;
	/**
	 * The default value of the '{@link #getCustomAuthMechType() <em>Custom Auth Mech Type</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getCustomAuthMechType()
	 * @generated
	 * @ordered
	 */
	protected static final String CUSTOM_AUTH_MECH_TYPE_EDEFAULT = null;

	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	protected String customAuthMechType = CUSTOM_AUTH_MECH_TYPE_EDEFAULT;
	/**
	 * The cached value of the '{@link #getDescriptions() <em>Descriptions</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getDescriptions()
	 * @generated
	 * @ordered
	 */
	protected EList descriptions = null;

	protected AuthenticationMechanismImpl() {
		super();
	}
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected EClass eStaticClass() {
		return JcaPackage.eINSTANCE.getAuthenticationMechanism();
	}

	/**
	 * @generated This field/method will be replaced during code generation 
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	public void setDescription(String newDescription) {
		String oldDescription = description;
		description = newDescription;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, JcaPackage.AUTHENTICATION_MECHANISM__DESCRIPTION, oldDescription, description));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getAuthenticationMechanism() {
		return authenticationMechanism;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setAuthenticationMechanism(String newAuthenticationMechanism) {
		String oldAuthenticationMechanism = authenticationMechanism;
		authenticationMechanism = newAuthenticationMechanism;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, JcaPackage.AUTHENTICATION_MECHANISM__AUTHENTICATION_MECHANISM, oldAuthenticationMechanism, authenticationMechanism));
	}

	/**
	 * @generated This field/method will be replaced during code generation 
	 * The authentication-mechanism-type specifies type of an authentication mechanism.
	 * 
	 * The example values are:
	 *   - BasicPassword
	 *   - Kerbv5
	 * 
	 * Any additional security mechanisms are outside the scope of the
	 * Connector architecture specification.

	 */
	public AuthenticationMechanismType getAuthenticationMechanismType() {
		return authenticationMechanismType;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setAuthenticationMechanismType(AuthenticationMechanismType newAuthenticationMechanismType) {
		AuthenticationMechanismType oldAuthenticationMechanismType = authenticationMechanismType;
		authenticationMechanismType = newAuthenticationMechanismType == null ? AUTHENTICATION_MECHANISM_TYPE_EDEFAULT : newAuthenticationMechanismType;
		boolean oldAuthenticationMechanismTypeESet = authenticationMechanismTypeESet;
		authenticationMechanismTypeESet = true;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, JcaPackage.AUTHENTICATION_MECHANISM__AUTHENTICATION_MECHANISM_TYPE, oldAuthenticationMechanismType, authenticationMechanismType, !oldAuthenticationMechanismTypeESet));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void unsetAuthenticationMechanismType() {
		AuthenticationMechanismType oldAuthenticationMechanismType = authenticationMechanismType;
		boolean oldAuthenticationMechanismTypeESet = authenticationMechanismTypeESet;
		authenticationMechanismType = AUTHENTICATION_MECHANISM_TYPE_EDEFAULT;
		authenticationMechanismTypeESet = false;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.UNSET, JcaPackage.AUTHENTICATION_MECHANISM__AUTHENTICATION_MECHANISM_TYPE, oldAuthenticationMechanismType, AUTHENTICATION_MECHANISM_TYPE_EDEFAULT, oldAuthenticationMechanismTypeESet));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean isSetAuthenticationMechanismType() {
		return authenticationMechanismTypeESet;
	}

	/**
	 * @generated This field/method will be replaced during code generation 
	 * Specifies the interface that the resource adapter implementation supports for
	 * the representation of the credentials. This element should be used by
	 * application server to find out the Credential interface it should use as part
	 * of the security contract.
	 * 
	 * The possible values are:
	 *    javax.resource.spi.security.PasswordCredential
	 *    javax.resource.spi.security.GenericCredential

	 */
	public String getCredentialInterface() {
		return credentialInterface;
	}

	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	public void setCredentialInterface(String newCredentialInterface) {
		String oldCredentialInterface = credentialInterface;
		credentialInterface = newCredentialInterface;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, JcaPackage.AUTHENTICATION_MECHANISM__CREDENTIAL_INTERFACE, oldCredentialInterface, credentialInterface));
	}

	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	public boolean eIsSet(EStructuralFeature eFeature) {
		switch (eDerivedStructuralFeatureID(eFeature)) {
			case JcaPackage.AUTHENTICATION_MECHANISM__DESCRIPTION:
				return DESCRIPTION_EDEFAULT == null ? description != null : !DESCRIPTION_EDEFAULT.equals(description);
			case JcaPackage.AUTHENTICATION_MECHANISM__AUTHENTICATION_MECHANISM:
				return AUTHENTICATION_MECHANISM_EDEFAULT == null ? authenticationMechanism != null : !AUTHENTICATION_MECHANISM_EDEFAULT.equals(authenticationMechanism);
			case JcaPackage.AUTHENTICATION_MECHANISM__AUTHENTICATION_MECHANISM_TYPE:
				return isSetAuthenticationMechanismType();
			case JcaPackage.AUTHENTICATION_MECHANISM__CREDENTIAL_INTERFACE:
				return CREDENTIAL_INTERFACE_EDEFAULT == null ? credentialInterface != null : !CREDENTIAL_INTERFACE_EDEFAULT.equals(credentialInterface);
			case JcaPackage.AUTHENTICATION_MECHANISM__CUSTOM_AUTH_MECH_TYPE:
				return CUSTOM_AUTH_MECH_TYPE_EDEFAULT == null ? customAuthMechType != null : !CUSTOM_AUTH_MECH_TYPE_EDEFAULT.equals(customAuthMechType);
			case JcaPackage.AUTHENTICATION_MECHANISM__DESCRIPTIONS:
				return descriptions != null && !descriptions.isEmpty();
		}
		return eDynamicIsSet(eFeature);
	}

	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	public void eSet(EStructuralFeature eFeature, Object newValue) {
		switch (eDerivedStructuralFeatureID(eFeature)) {
			case JcaPackage.AUTHENTICATION_MECHANISM__DESCRIPTION:
				setDescription((String)newValue);
				return;
			case JcaPackage.AUTHENTICATION_MECHANISM__AUTHENTICATION_MECHANISM:
				setAuthenticationMechanism((String)newValue);
				return;
			case JcaPackage.AUTHENTICATION_MECHANISM__AUTHENTICATION_MECHANISM_TYPE:
				setAuthenticationMechanismType((AuthenticationMechanismType)newValue);
				return;
			case JcaPackage.AUTHENTICATION_MECHANISM__CREDENTIAL_INTERFACE:
				setCredentialInterface((String)newValue);
				return;
			case JcaPackage.AUTHENTICATION_MECHANISM__CUSTOM_AUTH_MECH_TYPE:
				setCustomAuthMechType((String)newValue);
				return;
			case JcaPackage.AUTHENTICATION_MECHANISM__DESCRIPTIONS:
				getDescriptions().clear();
				getDescriptions().addAll((Collection)newValue);
				return;
		}
		eDynamicSet(eFeature, newValue);
	}

	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	public void eUnset(EStructuralFeature eFeature) {
		switch (eDerivedStructuralFeatureID(eFeature)) {
			case JcaPackage.AUTHENTICATION_MECHANISM__DESCRIPTION:
				setDescription(DESCRIPTION_EDEFAULT);
				return;
			case JcaPackage.AUTHENTICATION_MECHANISM__AUTHENTICATION_MECHANISM:
				setAuthenticationMechanism(AUTHENTICATION_MECHANISM_EDEFAULT);
				return;
			case JcaPackage.AUTHENTICATION_MECHANISM__AUTHENTICATION_MECHANISM_TYPE:
				unsetAuthenticationMechanismType();
				return;
			case JcaPackage.AUTHENTICATION_MECHANISM__CREDENTIAL_INTERFACE:
				setCredentialInterface(CREDENTIAL_INTERFACE_EDEFAULT);
				return;
			case JcaPackage.AUTHENTICATION_MECHANISM__CUSTOM_AUTH_MECH_TYPE:
				setCustomAuthMechType(CUSTOM_AUTH_MECH_TYPE_EDEFAULT);
				return;
			case JcaPackage.AUTHENTICATION_MECHANISM__DESCRIPTIONS:
				getDescriptions().clear();
				return;
		}
		eDynamicUnset(eFeature);
	}

	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	public String toString() {
		if (eIsProxy()) return super.toString();

		StringBuffer result = new StringBuffer(super.toString());
		result.append(" (description: ");//$NON-NLS-1$
		result.append(description);
		result.append(", authenticationMechanism: ");//$NON-NLS-1$
		result.append(authenticationMechanism);
		result.append(", authenticationMechanismType: ");//$NON-NLS-1$
		if (authenticationMechanismTypeESet) result.append(authenticationMechanismType); else result.append("<unset>");//$NON-NLS-1$
		result.append(", credentialInterface: ");//$NON-NLS-1$
		result.append(credentialInterface);
		result.append(", customAuthMechType: ");//$NON-NLS-1$
		result.append(customAuthMechType);
		result.append(')');
		return result.toString();
	}

	/**
	 * @generated This field/method will be replaced during code generation 
	 * This is an internal variable used to store the AthenticationMechanismType when
	 * it is something other than BasicPassword or Kerbv5.  AuthenticationMecanismType 
	 * is set to 'Custom' in those cases.  This attribute should not be used by
	 * runtime.  It is only used by the writer to write back out the custom
	 * AuthenticationMechanismType string in the case of custom
	 * AuthenticationMechanismType's.  
	 * This variable is internal, used only by the reader//writer classes, and can
	 * change or disappear without notice.  
	 */
	public String getCustomAuthMechType() {
		return customAuthMechType;
	}

	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	public void setCustomAuthMechType(String newCustomAuthMechType) {
		String oldCustomAuthMechType = customAuthMechType;
		customAuthMechType = newCustomAuthMechType;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, JcaPackage.AUTHENTICATION_MECHANISM__CUSTOM_AUTH_MECH_TYPE, oldCustomAuthMechType, customAuthMechType));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList getDescriptions() {
		if (descriptions == null) {
			descriptions = new EObjectContainmentEList(Description.class, this, JcaPackage.AUTHENTICATION_MECHANISM__DESCRIPTIONS);
		}
		return descriptions;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, Class baseClass, NotificationChain msgs) {
		if (featureID >= 0) {
			switch (eDerivedStructuralFeatureID(featureID, baseClass)) {
				case JcaPackage.AUTHENTICATION_MECHANISM__DESCRIPTIONS:
					return ((InternalEList)getDescriptions()).basicRemove(otherEnd, msgs);
				default:
					return eDynamicInverseRemove(otherEnd, featureID, baseClass, msgs);
			}
		}
		return eBasicSetContainer(null, featureID, msgs);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Object eGet(EStructuralFeature eFeature, boolean resolve) {
		switch (eDerivedStructuralFeatureID(eFeature)) {
			case JcaPackage.AUTHENTICATION_MECHANISM__DESCRIPTION:
				return getDescription();
			case JcaPackage.AUTHENTICATION_MECHANISM__AUTHENTICATION_MECHANISM:
				return getAuthenticationMechanism();
			case JcaPackage.AUTHENTICATION_MECHANISM__AUTHENTICATION_MECHANISM_TYPE:
				return getAuthenticationMechanismType();
			case JcaPackage.AUTHENTICATION_MECHANISM__CREDENTIAL_INTERFACE:
				return getCredentialInterface();
			case JcaPackage.AUTHENTICATION_MECHANISM__CUSTOM_AUTH_MECH_TYPE:
				return getCustomAuthMechType();
			case JcaPackage.AUTHENTICATION_MECHANISM__DESCRIPTIONS:
				return getDescriptions();
		}
		return eDynamicGet(eFeature, resolve);
	}

}





































































































































































































































































































































































































































