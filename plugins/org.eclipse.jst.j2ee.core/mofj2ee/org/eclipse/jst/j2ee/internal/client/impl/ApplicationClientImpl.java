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
package org.eclipse.jst.j2ee.internal.client.impl;

import java.util.Collection;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.util.EObjectContainmentEList;
import org.eclipse.emf.ecore.util.InternalEList;
import org.eclipse.jem.java.JavaClass;
import org.eclipse.jem.java.JavaRefFactory;
import org.eclipse.jst.j2ee.client.ApplicationClient;
import org.eclipse.jst.j2ee.client.ClientPackage;
import org.eclipse.jst.j2ee.common.EjbRef;
import org.eclipse.jst.j2ee.common.EnvEntry;
import org.eclipse.jst.j2ee.common.J2EEVersionResource;
import org.eclipse.jst.j2ee.common.MessageDestination;
import org.eclipse.jst.j2ee.common.MessageDestinationRef;
import org.eclipse.jst.j2ee.common.ResourceEnvRef;
import org.eclipse.jst.j2ee.common.ResourceRef;
import org.eclipse.jst.j2ee.common.XMLResource;
import org.eclipse.jst.j2ee.internal.J2EEConstants;
import org.eclipse.jst.j2ee.internal.common.impl.CompatibilityDescriptionGroupImpl;
import org.eclipse.jst.j2ee.internal.common.util.CommonUtil;
import org.eclipse.jst.j2ee.webservice.wsclient.ServiceRef;


/**
 * The application-client element is the root element of an application client deployment descriptor.  The application client deployment descriptor describes the EJB components and external resources referenced by the application client.

 */
public class ApplicationClientImpl extends CompatibilityDescriptionGroupImpl implements ApplicationClient {

	/**
	 * The default value of the '{@link #getVersion() <em>Version</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getVersion()
	 * @generated
	 * @ordered
	 */
	protected static final String VERSION_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getVersion() <em>Version</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getVersion()
	 * @generated
	 * @ordered
	 */
	protected String version = VERSION_EDEFAULT;

	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	protected EList resourceRefs = null;
	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	protected EList environmentProps = null;
	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	protected EList ejbReferences = null;
	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	protected EList resourceEnvRefs = null;
	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	protected JavaClass callbackHandler = null;
	/**
	 * The cached value of the '{@link #getServiceRefs() <em>Service Refs</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getServiceRefs()
	 * @generated
	 * @ordered
	 */
	protected EList serviceRefs = null;

	/**
	 * The cached value of the '{@link #getMessageDestinationRefs() <em>Message Destination Refs</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getMessageDestinationRefs()
	 * @generated
	 * @ordered
	 */
	protected EList messageDestinationRefs = null;

	/**
	 * The cached value of the '{@link #getMessageDestinations() <em>Message Destinations</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getMessageDestinations()
	 * @generated
	 * @ordered
	 */
	protected EList messageDestinations = null;

	public ApplicationClientImpl() {
		super();
	}
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected EClass eStaticClass() {
		return ClientPackage.eINSTANCE.getApplicationClient();
	}

/**
 * createClassRef - return a JavaURL reference to the named Java class
 */
public JavaClass createClassRef(String targetName) {
	return JavaRefFactory.eINSTANCE.createClassRef(targetName);
}
public String getCallbackHandlerClassName() {
	getCallbackHandler();
	return (callbackHandler == null) ? null : callbackHandler.getQualifiedName();
}
/**
 * Return boolean indicating if this Application Client was populated from an Application Client 1.2 compliant descriptor
 * @return boolean
 * @deprecated Use getVersionID() to determine module level
 */
public boolean isVersion1_2Descriptor() {
	CommonUtil.checkDDObjectForVersion(this);
	String systemId = ((XMLResource)eResource()).getSystemId();
	return systemId == null ? false : systemId.equals(J2EEConstants.APP_CLIENT_SYSTEMID_1_2);
}
/**
 * Return boolean indicating if this Application client was populated from an Application Client 1.3 compliant descriptor
 * @return boolean
 * @deprecated Use getVersionID() to determine module level
 */
public boolean isVersion1_3Descriptor() {
	CommonUtil.checkDDObjectForVersion(this);
	String systemId = ((XMLResource)eResource()).getSystemId();
	return systemId == null ? false : systemId.equals(J2EEConstants.APP_CLIENT_SYSTEMID_1_3);
}
/**
 * @deprecated Use getVersionID() to determine module level
 */
public boolean isVersion1_4Descriptor() {
	return version != null && J2EEConstants.J2EE_1_4_TEXT.equals(version);
}
public void setCallbackHandlerClassName(String callbackHandlerClassName) {
	eSet(ClientPackage.eINSTANCE.getApplicationClient_CallbackHandler(), createClassRef(callbackHandlerClassName));
}
/**
 *This returns the module version id. Compare with J2EEVersionConstants to determine module level
 */
public int getVersionID() throws IllegalStateException {
	J2EEVersionResource res = (J2EEVersionResource) eResource();
	if (res == null) throw new IllegalStateException();
	return res.getModuleVersionID();
}
/**
 *This returns the j2ee version id. Compare with J2EEVersionConstants to determine j2ee level
 */
public int getJ2EEVersionID() throws IllegalStateException {
	J2EEVersionResource res = (J2EEVersionResource) eResource();
	if (res == null) throw new IllegalStateException();
	return res.getJ2EEVersionID();
}
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getVersion() {
		return version;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setVersion(String newVersion) {
		String oldVersion = version;
		version = newVersion;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ClientPackage.APPLICATION_CLIENT__VERSION, oldVersion, version));
	}

	/**
	 * @generated This field/method will be replaced during code generation 
	 * Contains declarations of the application clients's references to an external
	 * resources.
	 */
	public EList getResourceRefs() {
		if (resourceRefs == null) {
			resourceRefs = new EObjectContainmentEList(ResourceRef.class, this, ClientPackage.APPLICATION_CLIENT__RESOURCE_REFS);
		}
		return resourceRefs;
	}

	/**
	 * @generated This field/method will be replaced during code generation 
	 * The env-entry element contains the declaration of an application client's
	 * environment entries. Each declaration consists of an optional description, the
	 * name of the environment entry, and an optional value.
	 */
	public EList getEnvironmentProps() {
		if (environmentProps == null) {
			environmentProps = new EObjectContainmentEList(EnvEntry.class, this, ClientPackage.APPLICATION_CLIENT__ENVIRONMENT_PROPS);
		}
		return environmentProps;
	}

	/**
	 * @generated This field/method will be replaced during code generation 
	 * Contains a list of ejb-ref elements used for the declaration of a reference to
	 * an enterprise
	 * bean's home. 
	 */
	public EList getEjbReferences() {
		if (ejbReferences == null) {
			ejbReferences = new EObjectContainmentEList(EjbRef.class, this, ClientPackage.APPLICATION_CLIENT__EJB_REFERENCES);
		}
		return ejbReferences;
	}

	/**
	 * @generated This field/method will be replaced during code generation 
	 * The resource-env-ref element contains a declaration of an application client's
	 * reference to an administered object associated with a resource in the
	 * application client's environment.  It consists of an optional
	 * description, the resource environment reference name, and an indication of the
	 * resource environment reference type expected by the application client code.
	 * 
	 * Used in: application-client
	 * 
	 * Example:
	 * 
	 * <resource-env-ref>
	 *     <resource-env-ref-name>jms//StockQueue<//resource-env-ref-name>
	 *     <resource-env-ref-type>javax.jms.Queue<//resource-env-ref-type>
	 * <//resource-env-ref>

	 */
	public EList getResourceEnvRefs() {
		if (resourceEnvRefs == null) {
			resourceEnvRefs = new EObjectContainmentEList(ResourceEnvRef.class, this, ClientPackage.APPLICATION_CLIENT__RESOURCE_ENV_REFS);
		}
		return resourceEnvRefs;
	}

	/**
	 * @generated This field/method will be replaced during code generation 
	 * A class provided by the application.  The class must have a no args constructor and must implement the javax.security.auth.callback.CallbackHandler interface.  The class will be instantiated by the application client container and used by the container to collect authentication information from the user.
	 */
	public JavaClass getCallbackHandler() {
		if (callbackHandler != null && callbackHandler.eIsProxy()) {
			JavaClass oldCallbackHandler = callbackHandler;
			callbackHandler = (JavaClass)eResolveProxy((InternalEObject)callbackHandler);
			if (callbackHandler != oldCallbackHandler) {
				if (eNotificationRequired())
					eNotify(new ENotificationImpl(this, Notification.RESOLVE, ClientPackage.APPLICATION_CLIENT__CALLBACK_HANDLER, oldCallbackHandler, callbackHandler));
			}
		}
		return callbackHandler;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public JavaClass basicGetCallbackHandler() {
		return callbackHandler;
	}

	/**
	 */
	public void setCallbackHandler(JavaClass newCallbackHandler) {
		if ((callbackHandler == null || callbackHandler.getQualifiedName() == null)
				&& (newCallbackHandler == null || newCallbackHandler.getQualifiedName() == null)) {
			return;
		}
		JavaClass oldCallbackHandler = callbackHandler;
		callbackHandler = newCallbackHandler;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ClientPackage.APPLICATION_CLIENT__CALLBACK_HANDLER, oldCallbackHandler, callbackHandler));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList getServiceRefs() {
		if (serviceRefs == null) {
			serviceRefs = new EObjectContainmentEList(ServiceRef.class, this, ClientPackage.APPLICATION_CLIENT__SERVICE_REFS);
		}
		return serviceRefs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList getMessageDestinationRefs() {
		if (messageDestinationRefs == null) {
			messageDestinationRefs = new EObjectContainmentEList(MessageDestinationRef.class, this, ClientPackage.APPLICATION_CLIENT__MESSAGE_DESTINATION_REFS);
		}
		return messageDestinationRefs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList getMessageDestinations() {
		if (messageDestinations == null) {
			messageDestinations = new EObjectContainmentEList(MessageDestination.class, this, ClientPackage.APPLICATION_CLIENT__MESSAGE_DESTINATIONS);
		}
		return messageDestinations;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, Class baseClass, NotificationChain msgs) {
		if (featureID >= 0) {
			switch (eDerivedStructuralFeatureID(featureID, baseClass)) {
				case ClientPackage.APPLICATION_CLIENT__ICONS:
					return ((InternalEList)getIcons()).basicRemove(otherEnd, msgs);
				case ClientPackage.APPLICATION_CLIENT__DISPLAY_NAMES:
					return ((InternalEList)getDisplayNames()).basicRemove(otherEnd, msgs);
				case ClientPackage.APPLICATION_CLIENT__DESCRIPTIONS:
					return ((InternalEList)getDescriptions()).basicRemove(otherEnd, msgs);
				case ClientPackage.APPLICATION_CLIENT__RESOURCE_REFS:
					return ((InternalEList)getResourceRefs()).basicRemove(otherEnd, msgs);
				case ClientPackage.APPLICATION_CLIENT__ENVIRONMENT_PROPS:
					return ((InternalEList)getEnvironmentProps()).basicRemove(otherEnd, msgs);
				case ClientPackage.APPLICATION_CLIENT__EJB_REFERENCES:
					return ((InternalEList)getEjbReferences()).basicRemove(otherEnd, msgs);
				case ClientPackage.APPLICATION_CLIENT__RESOURCE_ENV_REFS:
					return ((InternalEList)getResourceEnvRefs()).basicRemove(otherEnd, msgs);
				case ClientPackage.APPLICATION_CLIENT__SERVICE_REFS:
					return ((InternalEList)getServiceRefs()).basicRemove(otherEnd, msgs);
				case ClientPackage.APPLICATION_CLIENT__MESSAGE_DESTINATION_REFS:
					return ((InternalEList)getMessageDestinationRefs()).basicRemove(otherEnd, msgs);
				case ClientPackage.APPLICATION_CLIENT__MESSAGE_DESTINATIONS:
					return ((InternalEList)getMessageDestinations()).basicRemove(otherEnd, msgs);
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
			case ClientPackage.APPLICATION_CLIENT__ICONS:
				return getIcons();
			case ClientPackage.APPLICATION_CLIENT__DISPLAY_NAMES:
				return getDisplayNames();
			case ClientPackage.APPLICATION_CLIENT__DESCRIPTIONS:
				return getDescriptions();
			case ClientPackage.APPLICATION_CLIENT__SMALL_ICON:
				return getSmallIcon();
			case ClientPackage.APPLICATION_CLIENT__LARGE_ICON:
				return getLargeIcon();
			case ClientPackage.APPLICATION_CLIENT__DESCRIPTION:
				return getDescription();
			case ClientPackage.APPLICATION_CLIENT__DISPLAY_NAME:
				return getDisplayName();
			case ClientPackage.APPLICATION_CLIENT__VERSION:
				return getVersion();
			case ClientPackage.APPLICATION_CLIENT__RESOURCE_REFS:
				return getResourceRefs();
			case ClientPackage.APPLICATION_CLIENT__ENVIRONMENT_PROPS:
				return getEnvironmentProps();
			case ClientPackage.APPLICATION_CLIENT__EJB_REFERENCES:
				return getEjbReferences();
			case ClientPackage.APPLICATION_CLIENT__RESOURCE_ENV_REFS:
				return getResourceEnvRefs();
			case ClientPackage.APPLICATION_CLIENT__CALLBACK_HANDLER:
				if (resolve) return getCallbackHandler();
				return basicGetCallbackHandler();
			case ClientPackage.APPLICATION_CLIENT__SERVICE_REFS:
				return getServiceRefs();
			case ClientPackage.APPLICATION_CLIENT__MESSAGE_DESTINATION_REFS:
				return getMessageDestinationRefs();
			case ClientPackage.APPLICATION_CLIENT__MESSAGE_DESTINATIONS:
				return getMessageDestinations();
		}
		return eDynamicGet(eFeature, resolve);
	}

	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	public boolean eIsSet(EStructuralFeature eFeature) {
		switch (eDerivedStructuralFeatureID(eFeature)) {
			case ClientPackage.APPLICATION_CLIENT__ICONS:
				return icons != null && !icons.isEmpty();
			case ClientPackage.APPLICATION_CLIENT__DISPLAY_NAMES:
				return displayNames != null && !displayNames.isEmpty();
			case ClientPackage.APPLICATION_CLIENT__DESCRIPTIONS:
				return descriptions != null && !descriptions.isEmpty();
			case ClientPackage.APPLICATION_CLIENT__SMALL_ICON:
				return SMALL_ICON_EDEFAULT == null ? smallIcon != null : !SMALL_ICON_EDEFAULT.equals(smallIcon);
			case ClientPackage.APPLICATION_CLIENT__LARGE_ICON:
				return LARGE_ICON_EDEFAULT == null ? largeIcon != null : !LARGE_ICON_EDEFAULT.equals(largeIcon);
			case ClientPackage.APPLICATION_CLIENT__DESCRIPTION:
				return DESCRIPTION_EDEFAULT == null ? description != null : !DESCRIPTION_EDEFAULT.equals(description);
			case ClientPackage.APPLICATION_CLIENT__DISPLAY_NAME:
				return DISPLAY_NAME_EDEFAULT == null ? displayName != null : !DISPLAY_NAME_EDEFAULT.equals(displayName);
			case ClientPackage.APPLICATION_CLIENT__VERSION:
				return VERSION_EDEFAULT == null ? version != null : !VERSION_EDEFAULT.equals(version);
			case ClientPackage.APPLICATION_CLIENT__RESOURCE_REFS:
				return resourceRefs != null && !resourceRefs.isEmpty();
			case ClientPackage.APPLICATION_CLIENT__ENVIRONMENT_PROPS:
				return environmentProps != null && !environmentProps.isEmpty();
			case ClientPackage.APPLICATION_CLIENT__EJB_REFERENCES:
				return ejbReferences != null && !ejbReferences.isEmpty();
			case ClientPackage.APPLICATION_CLIENT__RESOURCE_ENV_REFS:
				return resourceEnvRefs != null && !resourceEnvRefs.isEmpty();
			case ClientPackage.APPLICATION_CLIENT__CALLBACK_HANDLER:
				return callbackHandler != null;
			case ClientPackage.APPLICATION_CLIENT__SERVICE_REFS:
				return serviceRefs != null && !serviceRefs.isEmpty();
			case ClientPackage.APPLICATION_CLIENT__MESSAGE_DESTINATION_REFS:
				return messageDestinationRefs != null && !messageDestinationRefs.isEmpty();
			case ClientPackage.APPLICATION_CLIENT__MESSAGE_DESTINATIONS:
				return messageDestinations != null && !messageDestinations.isEmpty();
		}
		return eDynamicIsSet(eFeature);
	}

	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	public void eSet(EStructuralFeature eFeature, Object newValue) {
		switch (eDerivedStructuralFeatureID(eFeature)) {
			case ClientPackage.APPLICATION_CLIENT__ICONS:
				getIcons().clear();
				getIcons().addAll((Collection)newValue);
				return;
			case ClientPackage.APPLICATION_CLIENT__DISPLAY_NAMES:
				getDisplayNames().clear();
				getDisplayNames().addAll((Collection)newValue);
				return;
			case ClientPackage.APPLICATION_CLIENT__DESCRIPTIONS:
				getDescriptions().clear();
				getDescriptions().addAll((Collection)newValue);
				return;
			case ClientPackage.APPLICATION_CLIENT__SMALL_ICON:
				setSmallIcon((String)newValue);
				return;
			case ClientPackage.APPLICATION_CLIENT__LARGE_ICON:
				setLargeIcon((String)newValue);
				return;
			case ClientPackage.APPLICATION_CLIENT__DESCRIPTION:
				setDescription((String)newValue);
				return;
			case ClientPackage.APPLICATION_CLIENT__DISPLAY_NAME:
				setDisplayName((String)newValue);
				return;
			case ClientPackage.APPLICATION_CLIENT__VERSION:
				setVersion((String)newValue);
				return;
			case ClientPackage.APPLICATION_CLIENT__RESOURCE_REFS:
				getResourceRefs().clear();
				getResourceRefs().addAll((Collection)newValue);
				return;
			case ClientPackage.APPLICATION_CLIENT__ENVIRONMENT_PROPS:
				getEnvironmentProps().clear();
				getEnvironmentProps().addAll((Collection)newValue);
				return;
			case ClientPackage.APPLICATION_CLIENT__EJB_REFERENCES:
				getEjbReferences().clear();
				getEjbReferences().addAll((Collection)newValue);
				return;
			case ClientPackage.APPLICATION_CLIENT__RESOURCE_ENV_REFS:
				getResourceEnvRefs().clear();
				getResourceEnvRefs().addAll((Collection)newValue);
				return;
			case ClientPackage.APPLICATION_CLIENT__CALLBACK_HANDLER:
				setCallbackHandler((JavaClass)newValue);
				return;
			case ClientPackage.APPLICATION_CLIENT__SERVICE_REFS:
				getServiceRefs().clear();
				getServiceRefs().addAll((Collection)newValue);
				return;
			case ClientPackage.APPLICATION_CLIENT__MESSAGE_DESTINATION_REFS:
				getMessageDestinationRefs().clear();
				getMessageDestinationRefs().addAll((Collection)newValue);
				return;
			case ClientPackage.APPLICATION_CLIENT__MESSAGE_DESTINATIONS:
				getMessageDestinations().clear();
				getMessageDestinations().addAll((Collection)newValue);
				return;
		}
		eDynamicSet(eFeature, newValue);
	}

	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	public void eUnset(EStructuralFeature eFeature) {
		switch (eDerivedStructuralFeatureID(eFeature)) {
			case ClientPackage.APPLICATION_CLIENT__ICONS:
				getIcons().clear();
				return;
			case ClientPackage.APPLICATION_CLIENT__DISPLAY_NAMES:
				getDisplayNames().clear();
				return;
			case ClientPackage.APPLICATION_CLIENT__DESCRIPTIONS:
				getDescriptions().clear();
				return;
			case ClientPackage.APPLICATION_CLIENT__SMALL_ICON:
				setSmallIcon(SMALL_ICON_EDEFAULT);
				return;
			case ClientPackage.APPLICATION_CLIENT__LARGE_ICON:
				setLargeIcon(LARGE_ICON_EDEFAULT);
				return;
			case ClientPackage.APPLICATION_CLIENT__DESCRIPTION:
				setDescription(DESCRIPTION_EDEFAULT);
				return;
			case ClientPackage.APPLICATION_CLIENT__DISPLAY_NAME:
				setDisplayName(DISPLAY_NAME_EDEFAULT);
				return;
			case ClientPackage.APPLICATION_CLIENT__VERSION:
				setVersion(VERSION_EDEFAULT);
				return;
			case ClientPackage.APPLICATION_CLIENT__RESOURCE_REFS:
				getResourceRefs().clear();
				return;
			case ClientPackage.APPLICATION_CLIENT__ENVIRONMENT_PROPS:
				getEnvironmentProps().clear();
				return;
			case ClientPackage.APPLICATION_CLIENT__EJB_REFERENCES:
				getEjbReferences().clear();
				return;
			case ClientPackage.APPLICATION_CLIENT__RESOURCE_ENV_REFS:
				getResourceEnvRefs().clear();
				return;
			case ClientPackage.APPLICATION_CLIENT__CALLBACK_HANDLER:
				setCallbackHandler((JavaClass)null);
				return;
			case ClientPackage.APPLICATION_CLIENT__SERVICE_REFS:
				getServiceRefs().clear();
				return;
			case ClientPackage.APPLICATION_CLIENT__MESSAGE_DESTINATION_REFS:
				getMessageDestinationRefs().clear();
				return;
			case ClientPackage.APPLICATION_CLIENT__MESSAGE_DESTINATIONS:
				getMessageDestinations().clear();
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
		result.append(" (version: "); //$NON-NLS-1$
		result.append(version);
		result.append(')');
		return result.toString();
	}

}






