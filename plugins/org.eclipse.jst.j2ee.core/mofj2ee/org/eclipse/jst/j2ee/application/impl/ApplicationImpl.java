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
package org.eclipse.jst.j2ee.application.impl;

import java.util.Collection;
import java.util.List;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.util.EObjectContainmentEList;
import org.eclipse.emf.ecore.util.EObjectContainmentWithInverseEList;
import org.eclipse.emf.ecore.util.InternalEList;
import org.eclipse.jst.j2ee.application.Application;
import org.eclipse.jst.j2ee.application.ApplicationPackage;
import org.eclipse.jst.j2ee.application.Module;
import org.eclipse.jst.j2ee.common.J2EEVersionResource;
import org.eclipse.jst.j2ee.common.SecurityRole;
import org.eclipse.jst.j2ee.common.XMLResource;
import org.eclipse.jst.j2ee.common.impl.CompatibilityDescriptionGroupImpl;
import org.eclipse.jst.j2ee.common.util.CommonUtil;
import org.eclipse.jst.j2ee.internal.J2EEConstants;
import org.eclipse.wst.common.internal.emf.utilities.StringUtil;


/**
 * The application element is the root element of a J2EE application deployment descriptor.

 */
public class ApplicationImpl extends CompatibilityDescriptionGroupImpl implements Application{

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
	protected EList securityRoles = null;
	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	protected EList modules = null;
	public ApplicationImpl() {
		super();
	}
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected EClass eStaticClass() {
		return ApplicationPackage.eINSTANCE.getApplication();
	}

/**
 * @see org.eclipse.jst.j2ee.internal.application.Application
 */
public boolean containsSecurityRole(java.lang.String name) {
	return getSecurityRoleNamed(name) != null;
}
public Module getModule(String uri) {
	return getFirstModule(uri);
}

public Module getModule(String uri, String altDD) {
	List allModules = getModules();
	for (int i = 0; i < allModules.size(); i++) {
		Module aModule = (Module) allModules.get(i);
		if (StringUtil.stringsEqual(uri, aModule.getUri()) && StringUtil.stringsEqual(altDD, aModule.getAltDD()))
			return aModule;
	}
	return null;
}
	/**
	 * Gets the first module matching the specified uri
	 * @param uri The uri of a module to find.
	 * @return The first matching module or null if no module is found.
	 */
	public Module getFirstModule(String uri){
		if (uri == null) return null;
		java.util.Iterator allModules = getModules().iterator();
		while (allModules.hasNext()){
			Module aModule = (Module)allModules.next();
			if(uri.equals(aModule.getUri())) return aModule;
		}
		return null;
	}
/**
 * @see org.eclipse.jst.j2ee.internal.application.Application
 */
public Module getModuleHavingAltDD(java.lang.String uri) {
	if (uri == null)
		return null;
	List aModules = getModules();
	for (int i = 0; i < aModules.size(); i++) {
		Module aModule = (Module) aModules.get(i);
		if (uri.equals(aModule.getAltDD()))
			return aModule;
	}
	return null;
}
public SecurityRole getSecurityRoleNamed(String roleName) {
	java.util.List tempRoles = getSecurityRoles();
	SecurityRole role;
	for (int i = 0; i < tempRoles.size(); i++) {
		role = (SecurityRole) tempRoles.get(i);
		if (role.getRoleName().equals(roleName))
			return role;
	}
	return null;
}
	
/**
 * Return boolean indicating if this Application was populated from an Applcation1.2 compliant descriptor
 * @return boolean
 * @deprecated Use getVersionID() to determine module level
 */
public boolean isVersion1_2Descriptor() {
	CommonUtil.checkDDObjectForVersion(this);
	String systemId = ((XMLResource)eResource()).getSystemId();
	return systemId == null ? false : systemId.equals(J2EEConstants.APPLICATION_SYSTEMID_1_2);
}
/**
 * Return boolean indicating if this Application was populated from an Application1.3 compliant descriptor
 * @return boolean
 * @deprecated Use getVersionID() to determine module level
 */
public boolean isVersion1_3Descriptor() {
	CommonUtil.checkDDObjectForVersion(this);
	String systemId = ((XMLResource)eResource()).getSystemId();
	return systemId == null ? false : systemId.equals(J2EEConstants.APPLICATION_SYSTEMID_1_3);
}
	/**
	 *
	 */
	public void setDisplayName(String newDisplayName) {
		super.setDisplayName(newDisplayName);
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
	This returns the module version id. Compare with J2EEVersionConstants to determine module level
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
	public void setVersion(String newVersion) {
		String oldVersion = version;
		version = newVersion;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ApplicationPackage.APPLICATION__VERSION, oldVersion, version));
	}

	/**
	 * @generated This field/method will be replaced during code generation 
	 * Contains the definitions of security roles which are
	 * global to the application. 
	 */
	public EList getSecurityRoles() {
		if (securityRoles == null) {
			securityRoles = new EObjectContainmentEList(SecurityRole.class, this, ApplicationPackage.APPLICATION__SECURITY_ROLES);
		}
		return securityRoles;
	}

	/**
	 * @generated This field/method will be replaced during code generation 
	 */
	public EList getModules() {
		if (modules == null) {
			modules = new EObjectContainmentWithInverseEList(Module.class, this, ApplicationPackage.APPLICATION__MODULES, ApplicationPackage.MODULE__APPLICATION);
		}
		return modules;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain eInverseAdd(InternalEObject otherEnd, int featureID, Class baseClass, NotificationChain msgs) {
		if (featureID >= 0) {
			switch (eDerivedStructuralFeatureID(featureID, baseClass)) {
				case ApplicationPackage.APPLICATION__MODULES:
					return ((InternalEList)getModules()).basicAdd(otherEnd, msgs);
				default:
					return eDynamicInverseAdd(otherEnd, featureID, baseClass, msgs);
			}
		}
		if (eContainer != null)
			msgs = eBasicRemoveFromContainer(msgs);
		return eBasicSetContainer(otherEnd, featureID, msgs);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, Class baseClass, NotificationChain msgs) {
		if (featureID >= 0) {
			switch (eDerivedStructuralFeatureID(featureID, baseClass)) {
				case ApplicationPackage.APPLICATION__ICONS:
					return ((InternalEList)getIcons()).basicRemove(otherEnd, msgs);
				case ApplicationPackage.APPLICATION__DISPLAY_NAMES:
					return ((InternalEList)getDisplayNames()).basicRemove(otherEnd, msgs);
				case ApplicationPackage.APPLICATION__DESCRIPTIONS:
					return ((InternalEList)getDescriptions()).basicRemove(otherEnd, msgs);
				case ApplicationPackage.APPLICATION__SECURITY_ROLES:
					return ((InternalEList)getSecurityRoles()).basicRemove(otherEnd, msgs);
				case ApplicationPackage.APPLICATION__MODULES:
					return ((InternalEList)getModules()).basicRemove(otherEnd, msgs);
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
			case ApplicationPackage.APPLICATION__ICONS:
				return getIcons();
			case ApplicationPackage.APPLICATION__DISPLAY_NAMES:
				return getDisplayNames();
			case ApplicationPackage.APPLICATION__DESCRIPTIONS:
				return getDescriptions();
			case ApplicationPackage.APPLICATION__SMALL_ICON:
				return getSmallIcon();
			case ApplicationPackage.APPLICATION__LARGE_ICON:
				return getLargeIcon();
			case ApplicationPackage.APPLICATION__DESCRIPTION:
				return getDescription();
			case ApplicationPackage.APPLICATION__DISPLAY_NAME:
				return getDisplayName();
			case ApplicationPackage.APPLICATION__VERSION:
				return getVersion();
			case ApplicationPackage.APPLICATION__SECURITY_ROLES:
				return getSecurityRoles();
			case ApplicationPackage.APPLICATION__MODULES:
				return getModules();
		}
		return eDynamicGet(eFeature, resolve);
	}

	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	public boolean eIsSet(EStructuralFeature eFeature) {
		switch (eDerivedStructuralFeatureID(eFeature)) {
			case ApplicationPackage.APPLICATION__ICONS:
				return icons != null && !icons.isEmpty();
			case ApplicationPackage.APPLICATION__DISPLAY_NAMES:
				return displayNames != null && !displayNames.isEmpty();
			case ApplicationPackage.APPLICATION__DESCRIPTIONS:
				return descriptions != null && !descriptions.isEmpty();
			case ApplicationPackage.APPLICATION__SMALL_ICON:
				return SMALL_ICON_EDEFAULT == null ? smallIcon != null : !SMALL_ICON_EDEFAULT.equals(smallIcon);
			case ApplicationPackage.APPLICATION__LARGE_ICON:
				return LARGE_ICON_EDEFAULT == null ? largeIcon != null : !LARGE_ICON_EDEFAULT.equals(largeIcon);
			case ApplicationPackage.APPLICATION__DESCRIPTION:
				return DESCRIPTION_EDEFAULT == null ? description != null : !DESCRIPTION_EDEFAULT.equals(description);
			case ApplicationPackage.APPLICATION__DISPLAY_NAME:
				return DISPLAY_NAME_EDEFAULT == null ? displayName != null : !DISPLAY_NAME_EDEFAULT.equals(displayName);
			case ApplicationPackage.APPLICATION__VERSION:
				return VERSION_EDEFAULT == null ? version != null : !VERSION_EDEFAULT.equals(version);
			case ApplicationPackage.APPLICATION__SECURITY_ROLES:
				return securityRoles != null && !securityRoles.isEmpty();
			case ApplicationPackage.APPLICATION__MODULES:
				return modules != null && !modules.isEmpty();
		}
		return eDynamicIsSet(eFeature);
	}

	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	public void eSet(EStructuralFeature eFeature, Object newValue) {
		switch (eDerivedStructuralFeatureID(eFeature)) {
			case ApplicationPackage.APPLICATION__ICONS:
				getIcons().clear();
				getIcons().addAll((Collection)newValue);
				return;
			case ApplicationPackage.APPLICATION__DISPLAY_NAMES:
				getDisplayNames().clear();
				getDisplayNames().addAll((Collection)newValue);
				return;
			case ApplicationPackage.APPLICATION__DESCRIPTIONS:
				getDescriptions().clear();
				getDescriptions().addAll((Collection)newValue);
				return;
			case ApplicationPackage.APPLICATION__SMALL_ICON:
				setSmallIcon((String)newValue);
				return;
			case ApplicationPackage.APPLICATION__LARGE_ICON:
				setLargeIcon((String)newValue);
				return;
			case ApplicationPackage.APPLICATION__DESCRIPTION:
				setDescription((String)newValue);
				return;
			case ApplicationPackage.APPLICATION__DISPLAY_NAME:
				setDisplayName((String)newValue);
				return;
			case ApplicationPackage.APPLICATION__VERSION:
				setVersion((String)newValue);
				return;
			case ApplicationPackage.APPLICATION__SECURITY_ROLES:
				getSecurityRoles().clear();
				getSecurityRoles().addAll((Collection)newValue);
				return;
			case ApplicationPackage.APPLICATION__MODULES:
				getModules().clear();
				getModules().addAll((Collection)newValue);
				return;
		}
		eDynamicSet(eFeature, newValue);
	}

	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	public void eUnset(EStructuralFeature eFeature) {
		switch (eDerivedStructuralFeatureID(eFeature)) {
			case ApplicationPackage.APPLICATION__ICONS:
				getIcons().clear();
				return;
			case ApplicationPackage.APPLICATION__DISPLAY_NAMES:
				getDisplayNames().clear();
				return;
			case ApplicationPackage.APPLICATION__DESCRIPTIONS:
				getDescriptions().clear();
				return;
			case ApplicationPackage.APPLICATION__SMALL_ICON:
				setSmallIcon(SMALL_ICON_EDEFAULT);
				return;
			case ApplicationPackage.APPLICATION__LARGE_ICON:
				setLargeIcon(LARGE_ICON_EDEFAULT);
				return;
			case ApplicationPackage.APPLICATION__DESCRIPTION:
				setDescription(DESCRIPTION_EDEFAULT);
				return;
			case ApplicationPackage.APPLICATION__DISPLAY_NAME:
				setDisplayName(DISPLAY_NAME_EDEFAULT);
				return;
			case ApplicationPackage.APPLICATION__VERSION:
				setVersion(VERSION_EDEFAULT);
				return;
			case ApplicationPackage.APPLICATION__SECURITY_ROLES:
				getSecurityRoles().clear();
				return;
			case ApplicationPackage.APPLICATION__MODULES:
				getModules().clear();
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





