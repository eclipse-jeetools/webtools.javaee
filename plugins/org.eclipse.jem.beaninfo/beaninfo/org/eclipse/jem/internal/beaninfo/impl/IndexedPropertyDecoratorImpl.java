/*******************************************************************************
 * Copyright (c) 2001, 2005 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *******************************************************************************/
package org.eclipse.jem.internal.beaninfo.impl;
/*
 *  $RCSfile: IndexedPropertyDecoratorImpl.java,v $
 *  $Revision: 1.13 $  $Date: 2007/03/14 01:22:51 $ 
 */


import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.ecore.*;
import org.eclipse.emf.ecore.impl.ENotificationImpl;

import org.eclipse.jem.internal.beaninfo.BeaninfoPackage;
import org.eclipse.jem.internal.beaninfo.IndexedPropertyDecorator;
import org.eclipse.jem.java.Method;
/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Indexed Property Decorator</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.eclipse.jem.internal.beaninfo.impl.IndexedPropertyDecoratorImpl#getIndexedReadMethod <em>Indexed Read Method</em>}</li>
 *   <li>{@link org.eclipse.jem.internal.beaninfo.impl.IndexedPropertyDecoratorImpl#getIndexedWriteMethod <em>Indexed Write Method</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */


public class IndexedPropertyDecoratorImpl extends PropertyDecoratorImpl implements IndexedPropertyDecorator{
	
	/**
	 * Bits for implicitly set features. This is internal, not meant for clients.
	 */
	public static final long INDEXED_READMETHOD_IMPLICIT = 0x100000L;	// Start kind of high so as to allow PropertyDecorator to increase without conflig.
	public static final long INDEXED_WRITEMETHOD_IMPLICIT = 0x200000L;
	
	/**
	 * The cached value of the '{@link #getIndexedReadMethod() <em>Indexed Read Method</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getIndexedReadMethod()
	 * @generated
	 * @ordered
	 */
	protected Method indexedReadMethod = null;
	/**
	 * The flag representing whether the Indexed Read Method reference has been set.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	protected static final int INDEXED_READ_METHOD_ESETFLAG = 1 << 29;

	/**
	 * The cached value of the '{@link #getIndexedWriteMethod() <em>Indexed Write Method</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getIndexedWriteMethod()
	 * @generated
	 * @ordered
	 */
	protected Method indexedWriteMethod = null;
	
	/**
	 * The flag representing whether the Indexed Write Method reference has been set.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	protected static final int INDEXED_WRITE_METHOD_ESETFLAG = 1 << 30;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */	
	protected IndexedPropertyDecoratorImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected EClass eStaticClass() {
		return BeaninfoPackage.Literals.INDEXED_PROPERTY_DECORATOR;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Method getIndexedReadMethod() {
		if (indexedReadMethod != null && indexedReadMethod.eIsProxy()) {
			InternalEObject oldIndexedReadMethod = (InternalEObject)indexedReadMethod;
			indexedReadMethod = (Method)eResolveProxy(oldIndexedReadMethod);
			if (indexedReadMethod != oldIndexedReadMethod) {
				if (eNotificationRequired())
					eNotify(new ENotificationImpl(this, Notification.RESOLVE, BeaninfoPackage.INDEXED_PROPERTY_DECORATOR__INDEXED_READ_METHOD, oldIndexedReadMethod, indexedReadMethod));
			}
		}
		return indexedReadMethod;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setIndexedReadMethod(Method newIndexedReadMethod) {
		Method oldIndexedReadMethod = indexedReadMethod;
		indexedReadMethod = newIndexedReadMethod;
		boolean oldIndexedReadMethodESet = (eFlags & INDEXED_READ_METHOD_ESETFLAG) != 0;
		eFlags |= INDEXED_READ_METHOD_ESETFLAG;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, BeaninfoPackage.INDEXED_PROPERTY_DECORATOR__INDEXED_READ_METHOD, oldIndexedReadMethod, indexedReadMethod, !oldIndexedReadMethodESet));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void unsetIndexedReadMethod() {
		Method oldIndexedReadMethod = indexedReadMethod;
		boolean oldIndexedReadMethodESet = (eFlags & INDEXED_READ_METHOD_ESETFLAG) != 0;
		indexedReadMethod = null;
		eFlags &= ~INDEXED_READ_METHOD_ESETFLAG;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.UNSET, BeaninfoPackage.INDEXED_PROPERTY_DECORATOR__INDEXED_READ_METHOD, oldIndexedReadMethod, null, oldIndexedReadMethodESet));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean isSetIndexedReadMethod() {
		return (eFlags & INDEXED_READ_METHOD_ESETFLAG) != 0;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Method getIndexedWriteMethod() {
		if (indexedWriteMethod != null && indexedWriteMethod.eIsProxy()) {
			InternalEObject oldIndexedWriteMethod = (InternalEObject)indexedWriteMethod;
			indexedWriteMethod = (Method)eResolveProxy(oldIndexedWriteMethod);
			if (indexedWriteMethod != oldIndexedWriteMethod) {
				if (eNotificationRequired())
					eNotify(new ENotificationImpl(this, Notification.RESOLVE, BeaninfoPackage.INDEXED_PROPERTY_DECORATOR__INDEXED_WRITE_METHOD, oldIndexedWriteMethod, indexedWriteMethod));
			}
		}
		return indexedWriteMethod;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setIndexedWriteMethod(Method newIndexedWriteMethod) {
		Method oldIndexedWriteMethod = indexedWriteMethod;
		indexedWriteMethod = newIndexedWriteMethod;
		boolean oldIndexedWriteMethodESet = (eFlags & INDEXED_WRITE_METHOD_ESETFLAG) != 0;
		eFlags |= INDEXED_WRITE_METHOD_ESETFLAG;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, BeaninfoPackage.INDEXED_PROPERTY_DECORATOR__INDEXED_WRITE_METHOD, oldIndexedWriteMethod, indexedWriteMethod, !oldIndexedWriteMethodESet));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void unsetIndexedWriteMethod() {
		Method oldIndexedWriteMethod = indexedWriteMethod;
		boolean oldIndexedWriteMethodESet = (eFlags & INDEXED_WRITE_METHOD_ESETFLAG) != 0;
		indexedWriteMethod = null;
		eFlags &= ~INDEXED_WRITE_METHOD_ESETFLAG;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.UNSET, BeaninfoPackage.INDEXED_PROPERTY_DECORATOR__INDEXED_WRITE_METHOD, oldIndexedWriteMethod, null, oldIndexedWriteMethodESet));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean isSetIndexedWriteMethod() {
		return (eFlags & INDEXED_WRITE_METHOD_ESETFLAG) != 0;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case BeaninfoPackage.INDEXED_PROPERTY_DECORATOR__INDEXED_READ_METHOD:
				if (resolve) return getIndexedReadMethod();
				return basicGetIndexedReadMethod();
			case BeaninfoPackage.INDEXED_PROPERTY_DECORATOR__INDEXED_WRITE_METHOD:
				if (resolve) return getIndexedWriteMethod();
				return basicGetIndexedWriteMethod();
		}
		return super.eGet(featureID, resolve, coreType);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void eSet(int featureID, Object newValue) {
		switch (featureID) {
			case BeaninfoPackage.INDEXED_PROPERTY_DECORATOR__INDEXED_READ_METHOD:
				setIndexedReadMethod((Method)newValue);
				return;
			case BeaninfoPackage.INDEXED_PROPERTY_DECORATOR__INDEXED_WRITE_METHOD:
				setIndexedWriteMethod((Method)newValue);
				return;
		}
		super.eSet(featureID, newValue);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void eUnset(int featureID) {
		switch (featureID) {
			case BeaninfoPackage.INDEXED_PROPERTY_DECORATOR__INDEXED_READ_METHOD:
				unsetIndexedReadMethod();
				return;
			case BeaninfoPackage.INDEXED_PROPERTY_DECORATOR__INDEXED_WRITE_METHOD:
				unsetIndexedWriteMethod();
				return;
		}
		super.eUnset(featureID);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * Overridden
	 */
	public boolean eIsSet(int featureID) {
		switch (featureID) {
			case BeaninfoPackage.INDEXED_PROPERTY_DECORATOR__SOURCE:
				return isSourceSet();	// Override so that if set to the same as classname, then it is considered not set.
			case BeaninfoPackage.INDEXED_PROPERTY_DECORATOR__INDEXED_READ_METHOD:
				return isSetIndexedReadMethod();
			case BeaninfoPackage.INDEXED_PROPERTY_DECORATOR__INDEXED_WRITE_METHOD:
				return isSetIndexedWriteMethod();
		}
		return super.eIsSet(featureID);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Method basicGetIndexedReadMethod() {
		return indexedReadMethod;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Method basicGetIndexedWriteMethod() {
		return indexedWriteMethod;
	}

}
