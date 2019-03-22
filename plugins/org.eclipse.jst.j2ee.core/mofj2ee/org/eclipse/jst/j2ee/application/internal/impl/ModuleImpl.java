/*******************************************************************************
 * Copyright (c) 2001, 2007 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 *
 * Contributors:
 * IBM Corporation - initial API and implementation
 *******************************************************************************/
package org.eclipse.jst.j2ee.application.internal.impl;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.jst.j2ee.application.Application;
import org.eclipse.jst.j2ee.application.ApplicationPackage;
import org.eclipse.jst.j2ee.application.Module;
import org.eclipse.jst.j2ee.common.internal.impl.J2EEEObjectImpl;
import org.eclipse.jst.j2ee.internal.ICommonEMFModule;
import org.eclipse.jst.jee.application.ICommonModule;

/**
 * The module element represents a single J2EE module and contains an ejb, java, or web element, which indicates the module type and contains a path to the module file, and an optional alt-dd element, which specifies an optional URI to the post-assembly version of the deployment descriptor. The application deployment descriptor must have one module element for each J2EE module in the application package.
 * @generated
 */
public class ModuleImpl extends J2EEEObjectImpl implements Module, ICommonModule, ICommonEMFModule {

	/**
	 * The default value of the '{@link #getUri() <em>Uri</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getUri()
	 * @generated
	 * @ordered
	 */
	protected static final String URI_EDEFAULT = null;

	protected static final char FORWARD_SLASH = '/';
	protected static final char BACKWARD_SLASH = '\\';
	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	protected String uri = URI_EDEFAULT;
	/**
	 * The default value of the '{@link #getAltDD() <em>Alt DD</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getAltDD()
	 * @generated
	 * @ordered
	 */
	protected static final String ALT_DD_EDEFAULT = null;

	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	protected String altDD = ALT_DD_EDEFAULT;
	public ModuleImpl() {
		super();
	}
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return ApplicationPackage.Literals.MODULE;
	}

public boolean isConnectorModule() {
	return false;
}
/* Returns false by default
 *
 */
public boolean isEjbModule() {
	return false;
}
/* 
 * Returns false by default
 */
public boolean isJavaModule() {
	return false;
}
/* 
 * Returns false by default
 */
public boolean isWebModule() {
	return false;
}
	/**
	 * @generated This field/method will be replaced during code generation 
	 * Specifies the URI of a module, relative to the top level of the application package.

	 */
	public String getUri() {
		return uri;
	}

	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	public void setUri(String newUri) {
		String oldUri = uri;
		uri = newUri;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ApplicationPackage.MODULE__URI, oldUri, uri));
	}

	/**
	 * @generated This field/method will be replaced during code generation 
	 * The alt-dd element specifies an optional URI to the post-assembly version of the deployment descriptor file for a particular J2EE module. The URI must specify the full pathname of the deployment descriptor file relative to the application's root directory. If alt-dd is not specified, the deployer must read the deployment descriptor from the default location and file name required by the respective component specification.

	 */
	public String getAltDD() {
		return altDD;
	}

	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	public void setAltDD(String newAltDD) {
		String oldAltDD = altDD;
		altDD = newAltDD;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ApplicationPackage.MODULE__ALT_DD, oldAltDD, altDD));
	}

	/**
	 * @generated This field/method will be replaced during code generation 
	 */
	public Application getApplication() {
		if (eContainerFeatureID != ApplicationPackage.MODULE__APPLICATION) return null;
		return (Application)eContainer();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetApplication(Application newApplication, NotificationChain msgs) {
		NotificationChain innerMsgs = eBasicSetContainer((InternalEObject)newApplication, ApplicationPackage.MODULE__APPLICATION, msgs);
		return innerMsgs;
	}

	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	public void setApplication(Application newApplication) {
		if (newApplication != eInternalContainer() || (eContainerFeatureID != ApplicationPackage.MODULE__APPLICATION && newApplication != null)) {
			if (EcoreUtil.isAncestor(this, newApplication))
				throw new IllegalArgumentException("Recursive containment not allowed for " + toString()); //$NON-NLS-1$
			NotificationChain msgs = null;
			if (eInternalContainer() != null)
				msgs = eBasicRemoveFromContainer(msgs);
			if (newApplication != null)
				msgs = ((InternalEObject)newApplication).eInverseAdd(this, ApplicationPackage.APPLICATION__MODULES, Application.class, msgs);
			msgs = basicSetApplication(newApplication, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ApplicationPackage.MODULE__APPLICATION, newApplication, newApplication));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseAdd(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case ApplicationPackage.MODULE__APPLICATION:
				NotificationChain innerMsgs = msgs;
				if (eInternalContainer() != null){
					innerMsgs = eBasicRemoveFromContainer(msgs);
				}
				return basicSetApplication((Application)otherEnd, innerMsgs);
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
			case ApplicationPackage.MODULE__APPLICATION:
				return basicSetApplication(null, msgs);
		}
		return super.eInverseRemove(otherEnd, featureID, msgs);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eBasicRemoveFromContainerFeature(NotificationChain msgs) {
		switch (eContainerFeatureID) {
			case ApplicationPackage.MODULE__APPLICATION:
				return eInternalContainer().eInverseRemove(this, ApplicationPackage.APPLICATION__MODULES, Application.class, msgs);
		}
		return super.eBasicRemoveFromContainerFeature(msgs);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case ApplicationPackage.MODULE__URI:
				return getUri();
			case ApplicationPackage.MODULE__ALT_DD:
				return getAltDD();
			case ApplicationPackage.MODULE__APPLICATION:
				return getApplication();
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
			case ApplicationPackage.MODULE__URI:
				setUri((String)newValue);
				return;
			case ApplicationPackage.MODULE__ALT_DD:
				setAltDD((String)newValue);
				return;
			case ApplicationPackage.MODULE__APPLICATION:
				setApplication((Application)newValue);
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
			case ApplicationPackage.MODULE__URI:
				setUri(URI_EDEFAULT);
				return;
			case ApplicationPackage.MODULE__ALT_DD:
				setAltDD(ALT_DD_EDEFAULT);
				return;
			case ApplicationPackage.MODULE__APPLICATION:
				setApplication((Application)null);
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
			case ApplicationPackage.MODULE__URI:
				return URI_EDEFAULT == null ? uri != null : !URI_EDEFAULT.equals(uri);
			case ApplicationPackage.MODULE__ALT_DD:
				return ALT_DD_EDEFAULT == null ? altDD != null : !ALT_DD_EDEFAULT.equals(altDD);
			case ApplicationPackage.MODULE__APPLICATION:
				return getApplication() != null;
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
		result.append(" (uri: "); //$NON-NLS-1$
		result.append(uri);
		result.append(", altDD: "); //$NON-NLS-1$
		result.append(altDD);
		result.append(')');
		return result.toString();
	}
	public void setId(String frag) {
		// This is a No-op because Id's are automatically set during proxy resolution
		
	}
	/* 
	 * @see org.eclipse.jst.j2ee.internal.ICommonEMFModule#getId()
	 * This returns the id based on the containing resource
	 */
	public String getId() {
		if (eResource() != null)
			return eResource().getURIFragment(this);
		else
			return null;
	}

}





