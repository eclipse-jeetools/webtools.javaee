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
package org.eclipse.jst.j2ee.taglib.internal.impl;

import java.util.Collection;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.util.EObjectContainmentEList;
import org.eclipse.emf.ecore.util.InternalEList;
import org.eclipse.jem.java.JavaClass;
import org.eclipse.jst.j2ee.common.Description;
import org.eclipse.jst.j2ee.common.internal.impl.J2EEEObjectImpl;
import org.eclipse.jst.j2ee.taglib.internal.TaglibPackage;
import org.eclipse.jst.j2ee.taglib.internal.Validator;
import org.eclipse.jst.j2ee.webapplication.InitParam;


/**
 * The validator element provides information on an optional validator that can be used to validate the conformance of a JSP page to using this tag library.
 * @generated
 */
public class ValidatorImpl extends J2EEEObjectImpl implements Validator {

	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	protected JavaClass validatorClass = null;
	public ValidatorImpl() {
		super();
	}
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return TaglibPackage.Literals.VALIDATOR;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public JavaClass getValidatorClass() {
		if (validatorClass != null && validatorClass.eIsProxy()) {
			InternalEObject oldValidatorClass = (InternalEObject)validatorClass;
			validatorClass = (JavaClass)eResolveProxy(oldValidatorClass);
			if (validatorClass != oldValidatorClass) {
				if (eNotificationRequired())
					eNotify(new ENotificationImpl(this, Notification.RESOLVE, TaglibPackage.VALIDATOR__VALIDATOR_CLASS, oldValidatorClass, validatorClass));
			}
		}
		return validatorClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public JavaClass basicGetValidatorClass() {
		return validatorClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setValidatorClass(JavaClass newValidatorClass) {
		JavaClass oldValidatorClass = validatorClass;
		validatorClass = newValidatorClass;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, TaglibPackage.VALIDATOR__VALIDATOR_CLASS, oldValidatorClass, validatorClass));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EList getInitParams() {
		if (initParams == null) {
			initParams = new EObjectContainmentEList(InitParam.class, this, TaglibPackage.VALIDATOR__INIT_PARAMS);
		}
		return initParams;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EList getDescriptions() {
		if (descriptions == null) {
			descriptions = new EObjectContainmentEList(Description.class, this, TaglibPackage.VALIDATOR__DESCRIPTIONS);
		}
		return descriptions;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case TaglibPackage.VALIDATOR__INIT_PARAMS:
				return ((InternalEList)getInitParams()).basicRemove(otherEnd, msgs);
			case TaglibPackage.VALIDATOR__DESCRIPTIONS:
				return ((InternalEList)getDescriptions()).basicRemove(otherEnd, msgs);
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
			case TaglibPackage.VALIDATOR__VALIDATOR_CLASS:
				if (resolve) return getValidatorClass();
				return basicGetValidatorClass();
			case TaglibPackage.VALIDATOR__INIT_PARAMS:
				return getInitParams();
			case TaglibPackage.VALIDATOR__DESCRIPTIONS:
				return getDescriptions();
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
			case TaglibPackage.VALIDATOR__VALIDATOR_CLASS:
				setValidatorClass((JavaClass)newValue);
				return;
			case TaglibPackage.VALIDATOR__INIT_PARAMS:
				getInitParams().clear();
				getInitParams().addAll((Collection)newValue);
				return;
			case TaglibPackage.VALIDATOR__DESCRIPTIONS:
				getDescriptions().clear();
				getDescriptions().addAll((Collection)newValue);
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
			case TaglibPackage.VALIDATOR__VALIDATOR_CLASS:
				setValidatorClass((JavaClass)null);
				return;
			case TaglibPackage.VALIDATOR__INIT_PARAMS:
				getInitParams().clear();
				return;
			case TaglibPackage.VALIDATOR__DESCRIPTIONS:
				getDescriptions().clear();
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
			case TaglibPackage.VALIDATOR__VALIDATOR_CLASS:
				return validatorClass != null;
			case TaglibPackage.VALIDATOR__INIT_PARAMS:
				return initParams != null && !initParams.isEmpty();
			case TaglibPackage.VALIDATOR__DESCRIPTIONS:
				return descriptions != null && !descriptions.isEmpty();
		}
		return super.eIsSet(featureID);
	}

	protected EList initParams=null;


	/**
	 * The cached value of the '{@link #getDescriptions() <em>Descriptions</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getDescriptions()
	 * @generated
	 * @ordered
	 */
	protected EList descriptions = null;

	protected boolean setValidatorClass=false;

}














