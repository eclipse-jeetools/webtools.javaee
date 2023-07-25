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


import java.util.Collection;

import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.util.EObjectContainmentWithInverseEList;
import org.eclipse.emf.ecore.util.InternalEList;
import org.eclipse.jst.j2ee.commonarchivecore.looseconfig.internal.LooseLibrary;
import org.eclipse.jst.j2ee.commonarchivecore.looseconfig.internal.LooseWARFile;
import org.eclipse.jst.j2ee.commonarchivecore.looseconfig.internal.LooseconfigPackage;

/**
 * @generated
 */
public class LooseWARFileImpl extends LooseModuleImpl implements LooseWARFile {
	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	protected EList looseLibs = null;

	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	protected LooseWARFileImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return LooseconfigPackage.Literals.LOOSE_WAR_FILE;
	}

	@Override
	public boolean isWAR() {
		return true;
	}

	/**
	 * @generated This field/method will be replaced during code generation
	 */
	@Override
	public EList getLooseLibs() {
		if (looseLibs == null) {
			looseLibs = new EObjectContainmentWithInverseEList(LooseLibrary.class, this, LooseconfigPackage.LOOSE_WAR_FILE__LOOSE_LIBS, LooseconfigPackage.LOOSE_LIBRARY__LOOSE_WAR);
		}
		return looseLibs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseAdd(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case LooseconfigPackage.LOOSE_WAR_FILE__LOOSE_LIBS:
				return ((InternalEList)getLooseLibs()).basicAdd(otherEnd, msgs);
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
			case LooseconfigPackage.LOOSE_WAR_FILE__LOOSE_LIBS:
				return ((InternalEList)getLooseLibs()).basicRemove(otherEnd, msgs);
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
			case LooseconfigPackage.LOOSE_WAR_FILE__LOOSE_LIBS:
				return getLooseLibs();
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
			case LooseconfigPackage.LOOSE_WAR_FILE__LOOSE_LIBS:
				getLooseLibs().clear();
				getLooseLibs().addAll((Collection)newValue);
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
			case LooseconfigPackage.LOOSE_WAR_FILE__LOOSE_LIBS:
				getLooseLibs().clear();
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
			case LooseconfigPackage.LOOSE_WAR_FILE__LOOSE_LIBS:
				return looseLibs != null && !looseLibs.isEmpty();
		}
		return super.eIsSet(featureID);
	}

} //LooseWARFileImpl



