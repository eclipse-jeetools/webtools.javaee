/*******************************************************************************
 * Copyright (c) 2001, 2006 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 *
 * Contributors:
 * IBM Corporation - initial API and implementation
 *******************************************************************************/
package org.eclipse.jst.j2ee.ejb.internal.impl;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.jem.java.JavaClass;
import org.eclipse.jem.java.JavaRefFactory;
import org.eclipse.jst.j2ee.ejb.CMRField;
import org.eclipse.jst.j2ee.ejb.ContainerManagedEntity;
import org.eclipse.jst.j2ee.ejb.EJBRelationshipRole;
import org.eclipse.jst.j2ee.ejb.EjbPackage;

/**
 * Describes the bean provider's view of a relationship. It consists of an optional description, and the name and the class type of a field in the source of a role of a relationship. The CMRField::name element corresponds to the name used for the get and set accessor methods for the relationship. The CMRField::type element is used only for collection-valued CMRFields. It specifies the type of the collection that is used (a java class name).
 * 
 * @generated
 */
public class CMRFieldImpl extends CMPAttributeImpl implements CMRField {

	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	protected JavaClass collectionType = null;
	public CMRFieldImpl() {
		super();
	}
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return EjbPackage.Literals.CMR_FIELD;
	}

/**
 * createClassRef - return a JavaURL reference to the named Java class
 */
public JavaClass createClassRef(String targetName) {
	return JavaRefFactory.eINSTANCE.createClassRef(targetName);
}
public String getCollectionTypeName() {
	getCollectionType();
	return collectionType == null ? null : collectionType.getQualifiedName();
}          

@Override
public boolean isPrimKeyField(){
	return false;
}

@Override
public boolean isCMRField() {
	return true;
}
public void setCollectionTypeName(String typeName) {
	eSet(EjbPackage.eINSTANCE.getCMRField_CollectionType(), createClassRef(typeName));
}
	/**
	 * @generated This field/method will be replaced during code generation 
	 */
	public EJBRelationshipRole getRole() {
		if (eContainerFeatureID() != EjbPackage.CMR_FIELD__ROLE) return null;
		return (EJBRelationshipRole)eContainer();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetRole(EJBRelationshipRole newRole, NotificationChain msgs) {
		msgs = eBasicSetContainer((InternalEObject)newRole, EjbPackage.CMR_FIELD__ROLE, msgs);
		return msgs;
	}

	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	public void setRole(EJBRelationshipRole newRole) {
		if (newRole != eInternalContainer() || (eContainerFeatureID() != EjbPackage.CMR_FIELD__ROLE && newRole != null)) {
			if (EcoreUtil.isAncestor(this, newRole))
				throw new IllegalArgumentException("Recursive containment not allowed for " + toString()); //$NON-NLS-1$
			NotificationChain msgs = null;
			if (eInternalContainer() != null)
				msgs = eBasicRemoveFromContainer(msgs);
			if (newRole != null)
				msgs = ((InternalEObject)newRole).eInverseAdd(this, EjbPackage.EJB_RELATIONSHIP_ROLE__CMR_FIELD, EJBRelationshipRole.class, msgs);
			msgs = basicSetRole(newRole, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, EjbPackage.CMR_FIELD__ROLE, newRole, newRole));
	}

	/**
	 * @generated This field/method will be replaced during code generation 
	 */
	public JavaClass getCollectionType() {
		if (collectionType != null && collectionType.eIsProxy()) {
			InternalEObject oldCollectionType = (InternalEObject)collectionType;
			collectionType = (JavaClass)eResolveProxy(oldCollectionType);
			if (collectionType != oldCollectionType) {
				if (eNotificationRequired())
					eNotify(new ENotificationImpl(this, Notification.RESOLVE, EjbPackage.CMR_FIELD__COLLECTION_TYPE, oldCollectionType, collectionType));
			}
		}
		return collectionType;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public JavaClass basicGetCollectionType() {
		return collectionType;
	}

	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	public void setCollectionType(JavaClass newCollectionType) {
		JavaClass oldCollectionType = collectionType;
		collectionType = newCollectionType;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, EjbPackage.CMR_FIELD__COLLECTION_TYPE, oldCollectionType, collectionType));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseAdd(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case EjbPackage.CMR_FIELD__ROLE:
				if (eInternalContainer() != null)
					msgs = eBasicRemoveFromContainer(msgs);
				return basicSetRole((EJBRelationshipRole)otherEnd, msgs);
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
			case EjbPackage.CMR_FIELD__ROLE:
				return basicSetRole(null, msgs);
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
		switch (eContainerFeatureID()) {
			case EjbPackage.CMR_FIELD__ROLE:
				return eInternalContainer().eInverseRemove(this, EjbPackage.EJB_RELATIONSHIP_ROLE__CMR_FIELD, EJBRelationshipRole.class, msgs);
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
			case EjbPackage.CMR_FIELD__ROLE:
				return getRole();
			case EjbPackage.CMR_FIELD__COLLECTION_TYPE:
				if (resolve) return getCollectionType();
				return basicGetCollectionType();
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
			case EjbPackage.CMR_FIELD__ROLE:
				setRole((EJBRelationshipRole)newValue);
				return;
			case EjbPackage.CMR_FIELD__COLLECTION_TYPE:
				setCollectionType((JavaClass)newValue);
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
			case EjbPackage.CMR_FIELD__ROLE:
				setRole((EJBRelationshipRole)null);
				return;
			case EjbPackage.CMR_FIELD__COLLECTION_TYPE:
				setCollectionType((JavaClass)null);
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
			case EjbPackage.CMR_FIELD__ROLE:
				return getRole() != null;
			case EjbPackage.CMR_FIELD__COLLECTION_TYPE:
				return collectionType != null;
		}
		return super.eIsSet(featureID);
	}

	/**
	 * @see org.eclipse.jst.j2ee.ejb.internal.impl.CMPAttributeImpl#getCMPEntity()
	 */
	@Override
	public ContainerManagedEntity getCMPEntity() {
		EJBRelationshipRole role = getRole();
		if (role != null)
			return role.getSourceEntity();
		return null;
	}


}






