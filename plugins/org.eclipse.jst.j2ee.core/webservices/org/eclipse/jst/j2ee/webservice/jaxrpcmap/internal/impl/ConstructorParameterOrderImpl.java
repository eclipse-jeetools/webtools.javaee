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
package org.eclipse.jst.j2ee.webservice.jaxrpcmap.internal.impl;

import java.util.Collection;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.util.EObjectContainmentEList;
import org.eclipse.emf.ecore.util.InternalEList;
import org.eclipse.jst.j2ee.common.internal.impl.J2EEEObjectImpl;
import org.eclipse.jst.j2ee.webservice.jaxrpcmap.ConstructorParameterOrder;
import org.eclipse.jst.j2ee.webservice.jaxrpcmap.ElementName;
import org.eclipse.jst.j2ee.webservice.jaxrpcmap.JaxrpcmapPackage;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Constructor Parameter Order</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.eclipse.jst.j2ee.webservice.jaxrpcmap.internal.impl.ConstructorParameterOrderImpl#getId <em>Id</em>}</li>
 *   <li>{@link org.eclipse.jst.j2ee.webservice.jaxrpcmap.internal.impl.ConstructorParameterOrderImpl#getElementNames <em>Element Names</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class ConstructorParameterOrderImpl extends J2EEEObjectImpl implements ConstructorParameterOrder
{
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
	 * The cached value of the '{@link #getElementNames() <em>Element Names</em>}' containment reference list.
	 * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
	 * @see #getElementNames()
	 * @generated
	 * @ordered
	 */
  protected EList elementNames = null;

	/**
	 * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
	 * @generated
	 */
  protected ConstructorParameterOrderImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
	 * @generated
	 */
  @Override
protected EClass eStaticClass() {
		return JaxrpcmapPackage.Literals.CONSTRUCTOR_PARAMETER_ORDER;
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
			eNotify(new ENotificationImpl(this, Notification.SET, JaxrpcmapPackage.CONSTRUCTOR_PARAMETER_ORDER__ID, oldId, id));
	}

	/**
	 * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
	 * @generated
	 */
  @Override
public EList getElementNames() {
		if (elementNames == null) {
			elementNames = new EObjectContainmentEList(ElementName.class, this, JaxrpcmapPackage.CONSTRUCTOR_PARAMETER_ORDER__ELEMENT_NAMES);
		}
		return elementNames;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case JaxrpcmapPackage.CONSTRUCTOR_PARAMETER_ORDER__ELEMENT_NAMES:
				return ((InternalEList)getElementNames()).basicRemove(otherEnd, msgs);
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
			case JaxrpcmapPackage.CONSTRUCTOR_PARAMETER_ORDER__ID:
				return getId();
			case JaxrpcmapPackage.CONSTRUCTOR_PARAMETER_ORDER__ELEMENT_NAMES:
				return getElementNames();
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
			case JaxrpcmapPackage.CONSTRUCTOR_PARAMETER_ORDER__ID:
				setId((String)newValue);
				return;
			case JaxrpcmapPackage.CONSTRUCTOR_PARAMETER_ORDER__ELEMENT_NAMES:
				getElementNames().clear();
				getElementNames().addAll((Collection)newValue);
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
			case JaxrpcmapPackage.CONSTRUCTOR_PARAMETER_ORDER__ID:
				setId(ID_EDEFAULT);
				return;
			case JaxrpcmapPackage.CONSTRUCTOR_PARAMETER_ORDER__ELEMENT_NAMES:
				getElementNames().clear();
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
			case JaxrpcmapPackage.CONSTRUCTOR_PARAMETER_ORDER__ID:
				return ID_EDEFAULT == null ? id != null : !ID_EDEFAULT.equals(id);
			case JaxrpcmapPackage.CONSTRUCTOR_PARAMETER_ORDER__ELEMENT_NAMES:
				return elementNames != null && !elementNames.isEmpty();
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
		result.append(" (id: "); //$NON-NLS-1$
		result.append(id);
		result.append(')');
		return result.toString();
	}

} //ConstructorParameterOrderImpl
