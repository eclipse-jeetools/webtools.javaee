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
package org.eclipse.jst.javaee.application.internal.impl;

import java.util.Collection;
import java.util.List;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.EObjectImpl;
import org.eclipse.emf.ecore.util.EObjectContainmentEList;
import org.eclipse.emf.ecore.util.InternalEList;
import org.eclipse.jst.javaee.application.Application;
import org.eclipse.jst.javaee.application.Module;
import org.eclipse.jst.javaee.application.internal.metadata.ApplicationPackage;
import org.eclipse.jst.javaee.core.Description;
import org.eclipse.jst.javaee.core.DisplayName;
import org.eclipse.jst.javaee.core.Icon;
import org.eclipse.jst.javaee.core.SecurityRole;
import org.eclipse.jst.jee.application.ICommonApplication;
import org.eclipse.jst.jee.application.ICommonModule;
import org.eclipse.wst.common.internal.emf.utilities.StringUtil;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Application</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.eclipse.jst.javaee.application.internal.impl.ApplicationImpl#getDescriptions <em>Descriptions</em>}</li>
 *   <li>{@link org.eclipse.jst.javaee.application.internal.impl.ApplicationImpl#getDisplayNames <em>Display Names</em>}</li>
 *   <li>{@link org.eclipse.jst.javaee.application.internal.impl.ApplicationImpl#getIcons <em>Icons</em>}</li>
 *   <li>{@link org.eclipse.jst.javaee.application.internal.impl.ApplicationImpl#getModules <em>Modules</em>}</li>
 *   <li>{@link org.eclipse.jst.javaee.application.internal.impl.ApplicationImpl#getSecurityRoles <em>Security Roles</em>}</li>
 *   <li>{@link org.eclipse.jst.javaee.application.internal.impl.ApplicationImpl#getLibraryDirectory <em>Library Directory</em>}</li>
 *   <li>{@link org.eclipse.jst.javaee.application.internal.impl.ApplicationImpl#getId <em>Id</em>}</li>
 *   <li>{@link org.eclipse.jst.javaee.application.internal.impl.ApplicationImpl#getVersion <em>Version</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class ApplicationImpl extends EObjectImpl implements Application, ICommonApplication {
	/**
	 * The cached value of the '{@link #getDescriptions() <em>Descriptions</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getDescriptions()
	 * @generated
	 * @ordered
	 */
	protected EList descriptions = null;

	/**
	 * The cached value of the '{@link #getDisplayNames() <em>Display Names</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getDisplayNames()
	 * @generated
	 * @ordered
	 */
	protected EList displayNames = null;

	/**
	 * The cached value of the '{@link #getIcons() <em>Icons</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getIcons()
	 * @generated
	 * @ordered
	 */
	protected EList icons = null;

	/**
	 * The cached value of the '{@link #getModules() <em>Modules</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getModules()
	 * @generated
	 * @ordered
	 */
	protected EList modules = null;

	/**
	 * The cached value of the '{@link #getSecurityRoles() <em>Security Roles</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getSecurityRoles()
	 * @generated
	 * @ordered
	 */
	protected EList securityRoles = null;

	/**
	 * The default value of the '{@link #getLibraryDirectory() <em>Library Directory</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getLibraryDirectory()
	 * @generated
	 * @ordered
	 */
	protected static final String LIBRARY_DIRECTORY_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getLibraryDirectory() <em>Library Directory</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getLibraryDirectory()
	 * @generated
	 * @ordered
	 */
	protected String libraryDirectory = LIBRARY_DIRECTORY_EDEFAULT;

	/**
	 * The default value of the '{@link #getId() <em>Id</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getId()
	 * @generated
	 * @ordered
	 */
	protected static final String ID_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getId() <em>Id</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getId()
	 * @generated
	 * @ordered
	 */
	protected String id = ID_EDEFAULT;

	/**
	 * The default value of the '{@link #getVersion() <em>Version</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getVersion()
	 * @generated
	 * @ordered
	 */
	protected static final String VERSION_EDEFAULT = "5"; //$NON-NLS-1$

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
	 * This is true if the Version attribute has been set.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	protected boolean versionESet = false;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected ApplicationImpl() {
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
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public List getDescriptions() {
		if (descriptions == null) {
			descriptions = new EObjectContainmentEList(Description.class, this, ApplicationPackage.APPLICATION__DESCRIPTIONS);
		}
		return descriptions;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public List getDisplayNames() {
		if (displayNames == null) {
			displayNames = new EObjectContainmentEList(DisplayName.class, this, ApplicationPackage.APPLICATION__DISPLAY_NAMES);
		}
		return displayNames;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public List getIcons() {
		if (icons == null) {
			icons = new EObjectContainmentEList(Icon.class, this, ApplicationPackage.APPLICATION__ICONS);
		}
		return icons;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public List getModules() {
		if (modules == null) {
			modules = new EObjectContainmentEList(Module.class, this, ApplicationPackage.APPLICATION__MODULES);
		}
		return modules;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public List getSecurityRoles() {
		if (securityRoles == null) {
			securityRoles = new EObjectContainmentEList(SecurityRole.class, this, ApplicationPackage.APPLICATION__SECURITY_ROLES);
		}
		return securityRoles;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getLibraryDirectory() {
		return libraryDirectory;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setLibraryDirectory(String newLibraryDirectory) {
		String oldLibraryDirectory = libraryDirectory;
		libraryDirectory = newLibraryDirectory;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ApplicationPackage.APPLICATION__LIBRARY_DIRECTORY, oldLibraryDirectory, libraryDirectory));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getId() {
		return id;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setId(String newId) {
		String oldId = id;
		id = newId;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ApplicationPackage.APPLICATION__ID, oldId, id));
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
		boolean oldVersionESet = versionESet;
		versionESet = true;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ApplicationPackage.APPLICATION__VERSION, oldVersion, version, !oldVersionESet));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void unsetVersion() {
		String oldVersion = version;
		boolean oldVersionESet = versionESet;
		version = VERSION_EDEFAULT;
		versionESet = false;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.UNSET, ApplicationPackage.APPLICATION__VERSION, oldVersion, VERSION_EDEFAULT, oldVersionESet));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean isSetVersion() {
		return versionESet;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case ApplicationPackage.APPLICATION__DESCRIPTIONS:
				return ((InternalEList)getDescriptions()).basicRemove(otherEnd, msgs);
			case ApplicationPackage.APPLICATION__DISPLAY_NAMES:
				return ((InternalEList)getDisplayNames()).basicRemove(otherEnd, msgs);
			case ApplicationPackage.APPLICATION__ICONS:
				return ((InternalEList)getIcons()).basicRemove(otherEnd, msgs);
			case ApplicationPackage.APPLICATION__MODULES:
				return ((InternalEList)getModules()).basicRemove(otherEnd, msgs);
			case ApplicationPackage.APPLICATION__SECURITY_ROLES:
				return ((InternalEList)getSecurityRoles()).basicRemove(otherEnd, msgs);
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
			case ApplicationPackage.APPLICATION__DESCRIPTIONS:
				return getDescriptions();
			case ApplicationPackage.APPLICATION__DISPLAY_NAMES:
				return getDisplayNames();
			case ApplicationPackage.APPLICATION__ICONS:
				return getIcons();
			case ApplicationPackage.APPLICATION__MODULES:
				return getModules();
			case ApplicationPackage.APPLICATION__SECURITY_ROLES:
				return getSecurityRoles();
			case ApplicationPackage.APPLICATION__LIBRARY_DIRECTORY:
				return getLibraryDirectory();
			case ApplicationPackage.APPLICATION__ID:
				return getId();
			case ApplicationPackage.APPLICATION__VERSION:
				return getVersion();
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
			case ApplicationPackage.APPLICATION__DESCRIPTIONS:
				getDescriptions().clear();
				getDescriptions().addAll((Collection)newValue);
				return;
			case ApplicationPackage.APPLICATION__DISPLAY_NAMES:
				getDisplayNames().clear();
				getDisplayNames().addAll((Collection)newValue);
				return;
			case ApplicationPackage.APPLICATION__ICONS:
				getIcons().clear();
				getIcons().addAll((Collection)newValue);
				return;
			case ApplicationPackage.APPLICATION__MODULES:
				getModules().clear();
				getModules().addAll((Collection)newValue);
				return;
			case ApplicationPackage.APPLICATION__SECURITY_ROLES:
				getSecurityRoles().clear();
				getSecurityRoles().addAll((Collection)newValue);
				return;
			case ApplicationPackage.APPLICATION__LIBRARY_DIRECTORY:
				setLibraryDirectory((String)newValue);
				return;
			case ApplicationPackage.APPLICATION__ID:
				setId((String)newValue);
				return;
			case ApplicationPackage.APPLICATION__VERSION:
				setVersion((String)newValue);
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
			case ApplicationPackage.APPLICATION__DESCRIPTIONS:
				getDescriptions().clear();
				return;
			case ApplicationPackage.APPLICATION__DISPLAY_NAMES:
				getDisplayNames().clear();
				return;
			case ApplicationPackage.APPLICATION__ICONS:
				getIcons().clear();
				return;
			case ApplicationPackage.APPLICATION__MODULES:
				getModules().clear();
				return;
			case ApplicationPackage.APPLICATION__SECURITY_ROLES:
				getSecurityRoles().clear();
				return;
			case ApplicationPackage.APPLICATION__LIBRARY_DIRECTORY:
				setLibraryDirectory(LIBRARY_DIRECTORY_EDEFAULT);
				return;
			case ApplicationPackage.APPLICATION__ID:
				setId(ID_EDEFAULT);
				return;
			case ApplicationPackage.APPLICATION__VERSION:
				unsetVersion();
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
			case ApplicationPackage.APPLICATION__DESCRIPTIONS:
				return descriptions != null && !descriptions.isEmpty();
			case ApplicationPackage.APPLICATION__DISPLAY_NAMES:
				return displayNames != null && !displayNames.isEmpty();
			case ApplicationPackage.APPLICATION__ICONS:
				return icons != null && !icons.isEmpty();
			case ApplicationPackage.APPLICATION__MODULES:
				return modules != null && !modules.isEmpty();
			case ApplicationPackage.APPLICATION__SECURITY_ROLES:
				return securityRoles != null && !securityRoles.isEmpty();
			case ApplicationPackage.APPLICATION__LIBRARY_DIRECTORY:
				return LIBRARY_DIRECTORY_EDEFAULT == null ? libraryDirectory != null : !LIBRARY_DIRECTORY_EDEFAULT.equals(libraryDirectory);
			case ApplicationPackage.APPLICATION__ID:
				return ID_EDEFAULT == null ? id != null : !ID_EDEFAULT.equals(id);
			case ApplicationPackage.APPLICATION__VERSION:
				return isSetVersion();
		}
		return super.eIsSet(featureID);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String toString() {
		if (eIsProxy()) return super.toString();

		StringBuffer result = new StringBuffer(super.toString());
		result.append(" (libraryDirectory: "); //$NON-NLS-1$
		result.append(libraryDirectory);
		result.append(", id: "); //$NON-NLS-1$
		result.append(id);
		result.append(", version: "); //$NON-NLS-1$
		if (versionESet) result.append(version); else result.append("<unset>"); //$NON-NLS-1$
		result.append(')');
		return result.toString();
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

	public Module getModule(String uri, String altDD) {
		List allModules = getModules();
		for (int i = 0; i < allModules.size(); i++) {
			Module aModule = (Module) allModules.get(i);
			if (StringUtil.stringsEqual(uri, aModule.getUri()) && StringUtil.stringsEqual(altDD, aModule.getAltDd()))
				return aModule;
		}
		return null;
	}

	public List getEARModules() {
		return getModules();
	}

	public ICommonModule getFirstEARModule(String uri) {
		return (ICommonModule) getFirstModule(uri);
	}

} //ApplicationImpl