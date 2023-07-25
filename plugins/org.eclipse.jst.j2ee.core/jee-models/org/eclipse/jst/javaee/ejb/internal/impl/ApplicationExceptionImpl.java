/*******************************************************************************
 * Copyright (c) 2007, 2023 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 *
 * Contributors:
 * IBM Corporation - initial API and implementation
 *******************************************************************************/
package org.eclipse.jst.javaee.ejb.internal.impl;

import org.eclipse.emf.common.notify.Notification;

import org.eclipse.emf.ecore.EClass;

import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.EObjectImpl;

import org.eclipse.jst.javaee.ejb.ApplicationException;

import org.eclipse.jst.javaee.ejb.internal.metadata.EjbPackage;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Application Exception</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.eclipse.jst.javaee.ejb.internal.impl.ApplicationExceptionImpl#getExceptionClass <em>Exception Class</em>}</li>
 *   <li>{@link org.eclipse.jst.javaee.ejb.internal.impl.ApplicationExceptionImpl#isRollback <em>Rollback</em>}</li>
 *   <li>{@link org.eclipse.jst.javaee.ejb.internal.impl.ApplicationExceptionImpl#isInherited <em>Inherited</em>}</li>
 *   <li>{@link org.eclipse.jst.javaee.ejb.internal.impl.ApplicationExceptionImpl#getId <em>Id</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class ApplicationExceptionImpl extends EObjectImpl implements ApplicationException {
	/**
	 * The default value of the '{@link #getExceptionClass() <em>Exception Class</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getExceptionClass()
	 * @generated
	 * @ordered
	 */
	protected static final String EXCEPTION_CLASS_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getExceptionClass() <em>Exception Class</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getExceptionClass()
	 * @generated
	 * @ordered
	 */
	protected String exceptionClass = EXCEPTION_CLASS_EDEFAULT;

	/**
	 * The default value of the '{@link #isRollback() <em>Rollback</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isRollback()
	 * @generated
	 * @ordered
	 */
	protected static final boolean ROLLBACK_EDEFAULT = false;

	/**
	 * The cached value of the '{@link #isRollback() <em>Rollback</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isRollback()
	 * @generated
	 * @ordered
	 */
	protected boolean rollback = ROLLBACK_EDEFAULT;

	/**
	 * This is true if the Rollback attribute has been set.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	protected boolean rollbackESet;

	/**
	 * The default value of the '{@link #isInherited() <em>Inherited</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isInherited()
	 * @generated
	 * @ordered
	 */
	protected static final boolean INHERITED_EDEFAULT = false;

	/**
	 * The cached value of the '{@link #isInherited() <em>Inherited</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isInherited()
	 * @generated
	 * @ordered
	 */
	protected boolean inherited = INHERITED_EDEFAULT;

	/**
	 * This is true if the Inherited attribute has been set.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	protected boolean inheritedESet;

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
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected ApplicationExceptionImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return EjbPackage.Literals.APPLICATION_EXCEPTION;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String getExceptionClass() {
		return exceptionClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setExceptionClass(String newExceptionClass) {
		String oldExceptionClass = exceptionClass;
		exceptionClass = newExceptionClass;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, EjbPackage.APPLICATION_EXCEPTION__EXCEPTION_CLASS, oldExceptionClass, exceptionClass));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public boolean isRollback() {
		return rollback;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setRollback(boolean newRollback) {
		boolean oldRollback = rollback;
		rollback = newRollback;
		boolean oldRollbackESet = rollbackESet;
		rollbackESet = true;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, EjbPackage.APPLICATION_EXCEPTION__ROLLBACK, oldRollback, rollback, !oldRollbackESet));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void unsetRollback() {
		boolean oldRollback = rollback;
		boolean oldRollbackESet = rollbackESet;
		rollback = ROLLBACK_EDEFAULT;
		rollbackESet = false;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.UNSET, EjbPackage.APPLICATION_EXCEPTION__ROLLBACK, oldRollback, ROLLBACK_EDEFAULT, oldRollbackESet));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public boolean isSetRollback() {
		return rollbackESet;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public boolean isInherited() {
		return inherited;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setInherited(boolean newInherited) {
		boolean oldInherited = inherited;
		inherited = newInherited;
		boolean oldInheritedESet = inheritedESet;
		inheritedESet = true;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, EjbPackage.APPLICATION_EXCEPTION__INHERITED, oldInherited, inherited, !oldInheritedESet));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void unsetInherited() {
		boolean oldInherited = inherited;
		boolean oldInheritedESet = inheritedESet;
		inherited = INHERITED_EDEFAULT;
		inheritedESet = false;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.UNSET, EjbPackage.APPLICATION_EXCEPTION__INHERITED, oldInherited, INHERITED_EDEFAULT, oldInheritedESet));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public boolean isSetInherited() {
		return inheritedESet;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String getId() {
		return id;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setId(String newId) {
		String oldId = id;
		id = newId;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, EjbPackage.APPLICATION_EXCEPTION__ID, oldId, id));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case EjbPackage.APPLICATION_EXCEPTION__EXCEPTION_CLASS:
				return getExceptionClass();
			case EjbPackage.APPLICATION_EXCEPTION__ROLLBACK:
				return isRollback();
			case EjbPackage.APPLICATION_EXCEPTION__INHERITED:
				return isInherited();
			case EjbPackage.APPLICATION_EXCEPTION__ID:
				return getId();
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
			case EjbPackage.APPLICATION_EXCEPTION__EXCEPTION_CLASS:
				setExceptionClass((String)newValue);
				return;
			case EjbPackage.APPLICATION_EXCEPTION__ROLLBACK:
				setRollback((Boolean)newValue);
				return;
			case EjbPackage.APPLICATION_EXCEPTION__INHERITED:
				setInherited((Boolean)newValue);
				return;
			case EjbPackage.APPLICATION_EXCEPTION__ID:
				setId((String)newValue);
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
			case EjbPackage.APPLICATION_EXCEPTION__EXCEPTION_CLASS:
				setExceptionClass(EXCEPTION_CLASS_EDEFAULT);
				return;
			case EjbPackage.APPLICATION_EXCEPTION__ROLLBACK:
				unsetRollback();
				return;
			case EjbPackage.APPLICATION_EXCEPTION__INHERITED:
				unsetInherited();
				return;
			case EjbPackage.APPLICATION_EXCEPTION__ID:
				setId(ID_EDEFAULT);
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
			case EjbPackage.APPLICATION_EXCEPTION__EXCEPTION_CLASS:
				return EXCEPTION_CLASS_EDEFAULT == null ? exceptionClass != null : !EXCEPTION_CLASS_EDEFAULT.equals(exceptionClass);
			case EjbPackage.APPLICATION_EXCEPTION__ROLLBACK:
				return isSetRollback();
			case EjbPackage.APPLICATION_EXCEPTION__INHERITED:
				return isSetInherited();
			case EjbPackage.APPLICATION_EXCEPTION__ID:
				return ID_EDEFAULT == null ? id != null : !ID_EDEFAULT.equals(id);
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
		result.append(" (exceptionClass: "); //$NON-NLS-1$
		result.append(exceptionClass);
		result.append(", rollback: "); //$NON-NLS-1$
		if (rollbackESet) result.append(rollback); else result.append("<unset>"); //$NON-NLS-1$
		result.append(", inherited: "); //$NON-NLS-1$
		if (inheritedESet) result.append(inherited); else result.append("<unset>"); //$NON-NLS-1$
		result.append(", id: "); //$NON-NLS-1$
		result.append(id);
		result.append(')');
		return result.toString();
	}

} //ApplicationExceptionImpl