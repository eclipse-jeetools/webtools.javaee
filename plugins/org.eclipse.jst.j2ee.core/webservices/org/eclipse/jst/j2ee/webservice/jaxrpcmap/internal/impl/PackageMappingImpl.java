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

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.jst.j2ee.common.internal.impl.J2EEEObjectImpl;
import org.eclipse.jst.j2ee.webservice.jaxrpcmap.JaxrpcmapPackage;
import org.eclipse.jst.j2ee.webservice.jaxrpcmap.PackageMapping;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Package Mapping</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.eclipse.jst.j2ee.webservice.jaxrpcmap.internal.impl.PackageMappingImpl#getId <em>Id</em>}</li>
 *   <li>{@link org.eclipse.jst.j2ee.webservice.jaxrpcmap.internal.impl.PackageMappingImpl#getPackageType <em>Package Type</em>}</li>
 *   <li>{@link org.eclipse.jst.j2ee.webservice.jaxrpcmap.internal.impl.PackageMappingImpl#getNamespaceURI <em>Namespace URI</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class PackageMappingImpl extends J2EEEObjectImpl implements PackageMapping
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
	 * The default value of the '{@link #getPackageType() <em>Package Type</em>}' attribute.
	 * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
	 * @see #getPackageType()
	 * @generated
	 * @ordered
	 */
  protected static final String PACKAGE_TYPE_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getPackageType() <em>Package Type</em>}' attribute.
	 * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
	 * @see #getPackageType()
	 * @generated
	 * @ordered
	 */
  protected String packageType = PACKAGE_TYPE_EDEFAULT;

	/**
	 * The default value of the '{@link #getNamespaceURI() <em>Namespace URI</em>}' attribute.
	 * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
	 * @see #getNamespaceURI()
	 * @generated
	 * @ordered
	 */
  protected static final String NAMESPACE_URI_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getNamespaceURI() <em>Namespace URI</em>}' attribute.
	 * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
	 * @see #getNamespaceURI()
	 * @generated
	 * @ordered
	 */
  protected String namespaceURI = NAMESPACE_URI_EDEFAULT;

	/**
	 * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
	 * @generated
	 */
  protected PackageMappingImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
	 * @generated
	 */
  @Override
protected EClass eStaticClass() {
		return JaxrpcmapPackage.Literals.PACKAGE_MAPPING;
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
			eNotify(new ENotificationImpl(this, Notification.SET, JaxrpcmapPackage.PACKAGE_MAPPING__ID, oldId, id));
	}

	/**
	 * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
	 * @generated
	 */
  @Override
public String getPackageType() {
		return packageType;
	}

	/**
	 * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
	 * @generated
	 */
  @Override
public void setPackageType(String newPackageType) {
		String oldPackageType = packageType;
		packageType = newPackageType;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, JaxrpcmapPackage.PACKAGE_MAPPING__PACKAGE_TYPE, oldPackageType, packageType));
	}

	/**
	 * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
	 * @generated
	 */
  @Override
public String getNamespaceURI() {
		return namespaceURI;
	}

	/**
	 * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
	 * @generated
	 */
  @Override
public void setNamespaceURI(String newNamespaceURI) {
		String oldNamespaceURI = namespaceURI;
		namespaceURI = newNamespaceURI;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, JaxrpcmapPackage.PACKAGE_MAPPING__NAMESPACE_URI, oldNamespaceURI, namespaceURI));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case JaxrpcmapPackage.PACKAGE_MAPPING__ID:
				return getId();
			case JaxrpcmapPackage.PACKAGE_MAPPING__PACKAGE_TYPE:
				return getPackageType();
			case JaxrpcmapPackage.PACKAGE_MAPPING__NAMESPACE_URI:
				return getNamespaceURI();
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
			case JaxrpcmapPackage.PACKAGE_MAPPING__ID:
				setId((String)newValue);
				return;
			case JaxrpcmapPackage.PACKAGE_MAPPING__PACKAGE_TYPE:
				setPackageType((String)newValue);
				return;
			case JaxrpcmapPackage.PACKAGE_MAPPING__NAMESPACE_URI:
				setNamespaceURI((String)newValue);
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
			case JaxrpcmapPackage.PACKAGE_MAPPING__ID:
				setId(ID_EDEFAULT);
				return;
			case JaxrpcmapPackage.PACKAGE_MAPPING__PACKAGE_TYPE:
				setPackageType(PACKAGE_TYPE_EDEFAULT);
				return;
			case JaxrpcmapPackage.PACKAGE_MAPPING__NAMESPACE_URI:
				setNamespaceURI(NAMESPACE_URI_EDEFAULT);
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
			case JaxrpcmapPackage.PACKAGE_MAPPING__ID:
				return ID_EDEFAULT == null ? id != null : !ID_EDEFAULT.equals(id);
			case JaxrpcmapPackage.PACKAGE_MAPPING__PACKAGE_TYPE:
				return PACKAGE_TYPE_EDEFAULT == null ? packageType != null : !PACKAGE_TYPE_EDEFAULT.equals(packageType);
			case JaxrpcmapPackage.PACKAGE_MAPPING__NAMESPACE_URI:
				return NAMESPACE_URI_EDEFAULT == null ? namespaceURI != null : !NAMESPACE_URI_EDEFAULT.equals(namespaceURI);
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
		result.append(", packageType: "); //$NON-NLS-1$
		result.append(packageType);
		result.append(", namespaceURI: "); //$NON-NLS-1$
		result.append(namespaceURI);
		result.append(')');
		return result.toString();
	}

} //PackageMappingImpl
