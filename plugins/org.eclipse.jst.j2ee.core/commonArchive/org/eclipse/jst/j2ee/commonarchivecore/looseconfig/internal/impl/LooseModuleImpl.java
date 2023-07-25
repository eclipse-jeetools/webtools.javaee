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
package org.eclipse.jst.j2ee.commonarchivecore.looseconfig.internal.impl;


import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.jst.j2ee.commonarchivecore.looseconfig.internal.LooseModule;
import org.eclipse.jst.j2ee.commonarchivecore.looseconfig.internal.LooseconfigPackage;

/**
 * @generated
 */
public class LooseModuleImpl extends LooseArchiveImpl implements LooseModule {
	/**
	 * The default value of the '{@link #getAltDD() <em>Alt DD</em>}' attribute. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @see #getAltDD()
	 * @generated
	 * @ordered
	 */
	protected static final String ALT_DD_EDEFAULT = null;

	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	protected String altDD = ALT_DD_EDEFAULT;

	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	protected LooseModuleImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return LooseconfigPackage.Literals.LOOSE_MODULE;
	}

	/**
	 * @generated This field/method will be replaced during code generation
	 */
	@Override
	public String getAltDD() {
		return altDD;
	}

	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	@Override
	public void setAltDD(String newAltDD) {
		String oldAltDD = altDD;
		altDD = newAltDD;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, LooseconfigPackage.LOOSE_MODULE__ALT_DD, oldAltDD, altDD));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case LooseconfigPackage.LOOSE_MODULE__ALT_DD:
				return getAltDD();
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
			case LooseconfigPackage.LOOSE_MODULE__ALT_DD:
				setAltDD((String)newValue);
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
			case LooseconfigPackage.LOOSE_MODULE__ALT_DD:
				setAltDD(ALT_DD_EDEFAULT);
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
			case LooseconfigPackage.LOOSE_MODULE__ALT_DD:
				return ALT_DD_EDEFAULT == null ? altDD != null : !ALT_DD_EDEFAULT.equals(altDD);
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
		result.append(" (altDD: "); //$NON-NLS-1$
		result.append(altDD);
		result.append(')');
		return result.toString();
	}

} //LooseModuleImpl



