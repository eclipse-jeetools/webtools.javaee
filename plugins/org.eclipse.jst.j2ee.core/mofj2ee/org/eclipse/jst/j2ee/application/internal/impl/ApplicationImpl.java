/*******************************************************************************
 * Copyright (c) 2001, 2023 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 *
 * Contributors:
 * IBM Corporation - initial API and implementation
 *******************************************************************************/
package org.eclipse.jst.j2ee.application.internal.impl;

import java.util.Collection;
import java.util.List;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.util.EObjectContainmentEList;
import org.eclipse.emf.ecore.util.EObjectContainmentWithInverseEList;
import org.eclipse.emf.ecore.util.InternalEList;
import org.eclipse.jst.j2ee.application.Application;
import org.eclipse.jst.j2ee.application.ApplicationPackage;
import org.eclipse.jst.j2ee.application.Module;
import org.eclipse.jst.j2ee.common.SecurityRole;
import org.eclipse.jst.j2ee.common.internal.impl.CompatibilityDescriptionGroupImpl;
import org.eclipse.jst.j2ee.common.internal.util.CommonUtil;
import org.eclipse.jst.j2ee.internal.J2EEConstants;
import org.eclipse.jst.j2ee.internal.common.J2EEVersionResource;
import org.eclipse.jst.j2ee.internal.common.XMLResource;
import org.eclipse.jst.jee.application.ICommonApplication;
import org.eclipse.jst.jee.application.ICommonModule;
import org.eclipse.wst.common.internal.emf.utilities.StringUtil;


/**
 * The application element is the root element of a J2EE application deployment descriptor.

 */
public class ApplicationImpl extends CompatibilityDescriptionGroupImpl implements Application, ICommonApplication {

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
	@Override
	protected EClass eStaticClass() {
		return ApplicationPackage.Literals.APPLICATION;
	}

/**
 * @see org.eclipse.jst.j2ee.internal.application.Application
 */
@Override
public boolean containsSecurityRole(java.lang.String name) {
	return getSecurityRoleNamed(name) != null;
}
@Override
public Module getModule(String uri) {
	return getFirstModule(uri);
}

@Override
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
	@Override
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
@Override
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
@Override
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
@Override
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
@Override
public boolean isVersion1_3Descriptor() {
	CommonUtil.checkDDObjectForVersion(this);
	String systemId = ((XMLResource)eResource()).getSystemId();
	return systemId == null ? false : systemId.equals(J2EEConstants.APPLICATION_SYSTEMID_1_3);
}
	/**
	 *
	 */
	@Override
	public void setDisplayName(String newDisplayName) {
		super.setDisplayName(newDisplayName);
	}
	
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String getVersion() {
		return version;
	}

	/**
	This returns the module version id. Compare with J2EEVersionConstants to determine module level
	 */
	@Override
	public int getVersionID() throws IllegalStateException {
		J2EEVersionResource res = (J2EEVersionResource) eResource();
		if (res == null) throw new IllegalStateException();
		return res.getModuleVersionID();
	}
	/**
	 *This returns the j2ee version id. Compare with J2EEVersionConstants to determine j2ee level
	 */
	@Override
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
	@Override
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
	@Override
	public EList getSecurityRoles() {
		if (securityRoles == null) {
			securityRoles = new EObjectContainmentEList(SecurityRole.class, this, ApplicationPackage.APPLICATION__SECURITY_ROLES);
		}
		return securityRoles;
	}

	/**
	 * @generated This field/method will be replaced during code generation 
	 */
	@Override
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
	@Override
	public NotificationChain eInverseAdd(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case ApplicationPackage.APPLICATION__MODULES:
				return ((InternalEList)getModules()).basicAdd(otherEnd, msgs);
		}
		return super.eInverseAdd(otherEnd, featureID, msgs);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case ApplicationPackage.APPLICATION__SECURITY_ROLES:
				return ((InternalEList)getSecurityRoles()).basicRemove(otherEnd, msgs);
			case ApplicationPackage.APPLICATION__MODULES:
				return ((InternalEList)getModules()).basicRemove(otherEnd, msgs);
		}
		return super.eInverseRemove(otherEnd, featureID, msgs);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case ApplicationPackage.APPLICATION__VERSION:
				return getVersion();
			case ApplicationPackage.APPLICATION__SECURITY_ROLES:
				return getSecurityRoles();
			case ApplicationPackage.APPLICATION__MODULES:
				return getModules();
		}
		return super.eGet(featureID, resolve, coreType);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void eSet(int featureID, Object newValue) {
		switch (featureID) {
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
		super.eSet(featureID, newValue);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void eUnset(int featureID) {
		switch (featureID) {
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
		super.eUnset(featureID);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public boolean eIsSet(int featureID) {
		switch (featureID) {
			case ApplicationPackage.APPLICATION__VERSION:
				return VERSION_EDEFAULT == null ? version != null : !VERSION_EDEFAULT.equals(version);
			case ApplicationPackage.APPLICATION__SECURITY_ROLES:
				return securityRoles != null && !securityRoles.isEmpty();
			case ApplicationPackage.APPLICATION__MODULES:
				return modules != null && !modules.isEmpty();
		}
		return super.eIsSet(featureID);
	}

	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	@Override
	public String toString() {
		if (eIsProxy()) return super.toString();

		StringBuffer result = new StringBuffer(super.toString());
		result.append(" (version: "); //$NON-NLS-1$
		result.append(version);
		result.append(')');
		return result.toString();
	}
	@Override
	public List getEARModules() {
		return getModules();
	}
	@Override
	public ICommonModule getFirstEARModule(String uri) {
		return (ICommonModule)getFirstModule(uri);
	}

}





