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
package org.eclipse.jst.j2ee.internal.common.impl;

import java.util.Collection;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.util.InternalEList;
import org.eclipse.jst.j2ee.common.EJBLocalRef;
import org.eclipse.jst.j2ee.common.EjbRef;
import org.eclipse.jst.j2ee.common.EjbRefType;
import org.eclipse.jst.j2ee.internal.common.CommonPackage;

/**
 * The ejb-local-ref element is used for the declaration of a reference to another enterprise bean's local home. The declaration consists of an optional description; the EJB reference name used in the code of the referencing enterprise bean; the expected type of the referenced enterprise bean; the expected local home and local interfaces of the referenced enterprise bean; and an optional ejb-link information. The optional ejb-link element is used to specify the referenced enterprise bean.
 * Used in: entity, session, message-driven
 */
public class EJBLocalRefImpl extends EjbRefImpl implements EJBLocalRef, EjbRef{

	/**
	 * The default value of the '{@link #getLocalHome() <em>Local Home</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getLocalHome()
	 * @generated
	 * @ordered
	 */
	protected static final String LOCAL_HOME_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getLocalHome() <em>Local Home</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getLocalHome()
	 * @generated
	 * @ordered
	 */
	protected String localHome = LOCAL_HOME_EDEFAULT;

	/**
	 * The default value of the '{@link #getLocal() <em>Local</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getLocal()
	 * @generated
	 * @ordered
	 */
	protected static final String LOCAL_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getLocal() <em>Local</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getLocal()
	 * @generated
	 * @ordered
	 */
	protected String local = LOCAL_EDEFAULT;

	public EJBLocalRefImpl() {
		super();
	}
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected EClass eStaticClass() {
		return CommonPackage.eINSTANCE.getEJBLocalRef();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getLocalHome() {
		return localHome;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setLocalHome(String newLocalHome) {
		String oldLocalHome = localHome;
		localHome = newLocalHome;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, CommonPackage.EJB_LOCAL_REF__LOCAL_HOME, oldLocalHome, localHome));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getLocal() {
		return local;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setLocal(String newLocal) {
		String oldLocal = local;
		local = newLocal;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, CommonPackage.EJB_LOCAL_REF__LOCAL, oldLocal, local));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Object eGet(EStructuralFeature eFeature, boolean resolve) {
		switch (eDerivedStructuralFeatureID(eFeature)) {
			case CommonPackage.EJB_LOCAL_REF__NAME:
				return getName();
			case CommonPackage.EJB_LOCAL_REF__TYPE:
				return getType();
			case CommonPackage.EJB_LOCAL_REF__HOME:
				return getHome();
			case CommonPackage.EJB_LOCAL_REF__REMOTE:
				return getRemote();
			case CommonPackage.EJB_LOCAL_REF__LINK:
				return getLink();
			case CommonPackage.EJB_LOCAL_REF__DESCRIPTION:
				return getDescription();
			case CommonPackage.EJB_LOCAL_REF__DESCRIPTIONS:
				return getDescriptions();
			case CommonPackage.EJB_LOCAL_REF__LOCAL_HOME:
				return getLocalHome();
			case CommonPackage.EJB_LOCAL_REF__LOCAL:
				return getLocal();
		}
		return eDynamicGet(eFeature, resolve);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void eSet(EStructuralFeature eFeature, Object newValue) {
		switch (eDerivedStructuralFeatureID(eFeature)) {
			case CommonPackage.EJB_LOCAL_REF__NAME:
				setName((String)newValue);
				return;
			case CommonPackage.EJB_LOCAL_REF__TYPE:
				setType((EjbRefType)newValue);
				return;
			case CommonPackage.EJB_LOCAL_REF__HOME:
				setHome((String)newValue);
				return;
			case CommonPackage.EJB_LOCAL_REF__REMOTE:
				setRemote((String)newValue);
				return;
			case CommonPackage.EJB_LOCAL_REF__LINK:
				setLink((String)newValue);
				return;
			case CommonPackage.EJB_LOCAL_REF__DESCRIPTION:
				setDescription((String)newValue);
				return;
			case CommonPackage.EJB_LOCAL_REF__DESCRIPTIONS:
				getDescriptions().clear();
				getDescriptions().addAll((Collection)newValue);
				return;
			case CommonPackage.EJB_LOCAL_REF__LOCAL_HOME:
				setLocalHome((String)newValue);
				return;
			case CommonPackage.EJB_LOCAL_REF__LOCAL:
				setLocal((String)newValue);
				return;
		}
		eDynamicSet(eFeature, newValue);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void eUnset(EStructuralFeature eFeature) {
		switch (eDerivedStructuralFeatureID(eFeature)) {
			case CommonPackage.EJB_LOCAL_REF__NAME:
				setName(NAME_EDEFAULT);
				return;
			case CommonPackage.EJB_LOCAL_REF__TYPE:
				unsetType();
				return;
			case CommonPackage.EJB_LOCAL_REF__HOME:
				setHome(HOME_EDEFAULT);
				return;
			case CommonPackage.EJB_LOCAL_REF__REMOTE:
				setRemote(REMOTE_EDEFAULT);
				return;
			case CommonPackage.EJB_LOCAL_REF__LINK:
				setLink(LINK_EDEFAULT);
				return;
			case CommonPackage.EJB_LOCAL_REF__DESCRIPTION:
				setDescription(DESCRIPTION_EDEFAULT);
				return;
			case CommonPackage.EJB_LOCAL_REF__DESCRIPTIONS:
				getDescriptions().clear();
				return;
			case CommonPackage.EJB_LOCAL_REF__LOCAL_HOME:
				setLocalHome(LOCAL_HOME_EDEFAULT);
				return;
			case CommonPackage.EJB_LOCAL_REF__LOCAL:
				setLocal(LOCAL_EDEFAULT);
				return;
		}
		eDynamicUnset(eFeature);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean eIsSet(EStructuralFeature eFeature) {
		switch (eDerivedStructuralFeatureID(eFeature)) {
			case CommonPackage.EJB_LOCAL_REF__NAME:
				return NAME_EDEFAULT == null ? name != null : !NAME_EDEFAULT.equals(name);
			case CommonPackage.EJB_LOCAL_REF__TYPE:
				return isSetType();
			case CommonPackage.EJB_LOCAL_REF__HOME:
				return HOME_EDEFAULT == null ? home != null : !HOME_EDEFAULT.equals(home);
			case CommonPackage.EJB_LOCAL_REF__REMOTE:
				return REMOTE_EDEFAULT == null ? remote != null : !REMOTE_EDEFAULT.equals(remote);
			case CommonPackage.EJB_LOCAL_REF__LINK:
				return LINK_EDEFAULT == null ? link != null : !LINK_EDEFAULT.equals(link);
			case CommonPackage.EJB_LOCAL_REF__DESCRIPTION:
				return DESCRIPTION_EDEFAULT == null ? description != null : !DESCRIPTION_EDEFAULT.equals(description);
			case CommonPackage.EJB_LOCAL_REF__DESCRIPTIONS:
				return descriptions != null && !descriptions.isEmpty();
			case CommonPackage.EJB_LOCAL_REF__LOCAL_HOME:
				return LOCAL_HOME_EDEFAULT == null ? localHome != null : !LOCAL_HOME_EDEFAULT.equals(localHome);
			case CommonPackage.EJB_LOCAL_REF__LOCAL:
				return LOCAL_EDEFAULT == null ? local != null : !LOCAL_EDEFAULT.equals(local);
		}
		return eDynamicIsSet(eFeature);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String toString() {
		if (eIsProxy()) return super.toString();

		StringBuffer result = new StringBuffer(super.toString());
		result.append(" (localHome: "); //$NON-NLS-1$
		result.append(localHome);
		result.append(", local: "); //$NON-NLS-1$
		result.append(local);
		result.append(')');
		return result.toString();
	}

/**
 * Return true if the ejbref is for a local ejb interface.
 */
public boolean isLocal(){
	return true;
}
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, Class baseClass, NotificationChain msgs) {
		if (featureID >= 0) {
			switch (eDerivedStructuralFeatureID(featureID, baseClass)) {
				case CommonPackage.EJB_LOCAL_REF__DESCRIPTIONS:
					return ((InternalEList)getDescriptions()).basicRemove(otherEnd, msgs);
				default:
					return eDynamicInverseRemove(otherEnd, featureID, baseClass, msgs);
			}
		}
		return eBasicSetContainer(null, featureID, msgs);
	}

	/* (non-Javadoc)
	 * @see org.eclipse.jst.j2ee.internal.common.EjbRef#getRemote()
	 */
	public String getRemote() {
		return getLocal(); //forward to the local
	}
	
	/* (non-Javadoc)
	 * @see org.eclipse.jst.j2ee.internal.common.EjbRef#setRemote(java.lang.String)
	 */
	public void setRemote(String value) {
		setLocal(value); //forward to the local
	}
	
	/* (non-Javadoc)
	 * @see org.eclipse.jst.j2ee.internal.common.EjbRef#getHome()
	 */
	public String getHome() {
		return getLocalHome(); //forward to the local home
	}
	/* (non-Javadoc)
	 * @see org.eclipse.jst.j2ee.internal.common.EjbRef#setHome(java.lang.String)
	 */
	public void setHome(String value) {
		setLocalHome(value); //forward to the local home
	}
}





