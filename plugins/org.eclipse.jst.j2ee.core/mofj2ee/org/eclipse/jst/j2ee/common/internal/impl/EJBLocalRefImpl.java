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
package org.eclipse.jst.j2ee.common.internal.impl;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.jst.j2ee.common.CommonPackage;
import org.eclipse.jst.j2ee.common.EJBLocalRef;

/**
 * The ejb-local-ref element is used for the declaration of a reference to another enterprise bean's local home. The declaration consists of an optional description; the EJB reference name used in the code of the referencing enterprise bean; the expected type of the referenced enterprise bean; the expected local home and local interfaces of the referenced enterprise bean; and an optional ejb-link information. The optional ejb-link element is used to specify the referenced enterprise bean.
 * Used in: entity, session, message-driven
 * @generated
 */
public class EJBLocalRefImpl extends EjbRefImpl implements EJBLocalRef {

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
	@Override
	protected EClass eStaticClass() {
		return CommonPackage.Literals.EJB_LOCAL_REF;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String getLocalHome() {
		return localHome;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
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
	@Override
	public String getLocal() {
		return local;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
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
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case CommonPackage.EJB_LOCAL_REF__LOCAL_HOME:
				return getLocalHome();
			case CommonPackage.EJB_LOCAL_REF__LOCAL:
				return getLocal();
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
			case CommonPackage.EJB_LOCAL_REF__LOCAL_HOME:
				setLocalHome((String)newValue);
				return;
			case CommonPackage.EJB_LOCAL_REF__LOCAL:
				setLocal((String)newValue);
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
			case CommonPackage.EJB_LOCAL_REF__LOCAL_HOME:
				setLocalHome(LOCAL_HOME_EDEFAULT);
				return;
			case CommonPackage.EJB_LOCAL_REF__LOCAL:
				setLocal(LOCAL_EDEFAULT);
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
			case CommonPackage.EJB_LOCAL_REF__LOCAL_HOME:
				return LOCAL_HOME_EDEFAULT == null ? localHome != null : !LOCAL_HOME_EDEFAULT.equals(localHome);
			case CommonPackage.EJB_LOCAL_REF__LOCAL:
				return LOCAL_EDEFAULT == null ? local != null : !LOCAL_EDEFAULT.equals(local);
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
@Override
public boolean isLocal(){
	return true;
}
	/* (non-Javadoc)
	 * @see org.eclipse.jst.j2ee.internal.common.EjbRef#getRemote()
	 */
	@Override
	public String getRemote() {
		return getLocal(); //forward to the local
	}
	
	/* (non-Javadoc)
	 * @see org.eclipse.jst.j2ee.internal.common.EjbRef#setRemote(java.lang.String)
	 */
	@Override
	public void setRemote(String value) {
		setLocal(value); //forward to the local
	}
	
	/* (non-Javadoc)
	 * @see org.eclipse.jst.j2ee.internal.common.EjbRef#getHome()
	 */
	@Override
	public String getHome() {
		return getLocalHome(); //forward to the local home
	}
	/* (non-Javadoc)
	 * @see org.eclipse.jst.j2ee.internal.common.EjbRef#setHome(java.lang.String)
	 */
	@Override
	public void setHome(String value) {
		setLocalHome(value); //forward to the local home
	}
}





