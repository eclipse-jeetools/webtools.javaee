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
package org.eclipse.jst.j2ee.internal.ejb.impl;

import java.util.Collection;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EClassifier;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.EcorePackage;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.emf.ecore.util.InternalEList;
import org.eclipse.jem.java.JavaClass;
import org.eclipse.jem.java.JavaRefFactory;
import org.eclipse.jst.j2ee.ejb.CMPAttribute;
import org.eclipse.jst.j2ee.ejb.CMRField;
import org.eclipse.jst.j2ee.ejb.ContainerManagedEntity;
import org.eclipse.jst.j2ee.ejb.EJBRelationshipRole;
import org.eclipse.jst.j2ee.ejb.EjbPackage;

/**
 * Describes the bean provider's view of a relationship. It consists of an optional description, and the name and the class type of a field in the source of a role of a relationship. The CMRField::name element corresponds to the name used for the get and set accessor methods for the relationship. The CMRField::type element is used only for collection-valued CMRFields. It specifies the type of the collection that is used (a java class name).
 * 

 */
public class CMRFieldImpl extends CMPAttributeImpl implements CMRField, CMPAttribute{

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
	protected EClass eStaticClass() {
		return EjbPackage.eINSTANCE.getCMRField();
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

public boolean isPrimKeyField(){
	return false;
}

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
		if (eContainerFeatureID != EjbPackage.CMR_FIELD__ROLE) return null;
		return (EJBRelationshipRole)eContainer;
	}

	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	public void setRole(EJBRelationshipRole newRole) {
		if (newRole != eContainer || (eContainerFeatureID != EjbPackage.CMR_FIELD__ROLE && newRole != null)) {
			if (EcoreUtil.isAncestor(this, newRole))
				throw new IllegalArgumentException("Recursive containment not allowed for " + toString());
			NotificationChain msgs = null;
			if (eContainer != null)
				msgs = eBasicRemoveFromContainer(msgs);
			if (newRole != null)
				msgs = ((InternalEObject)newRole).eInverseAdd(this, EjbPackage.EJB_RELATIONSHIP_ROLE__CMR_FIELD, EJBRelationshipRole.class, msgs);
			msgs = eBasicSetContainer((InternalEObject)newRole, EjbPackage.CMR_FIELD__ROLE, msgs);
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
			JavaClass oldCollectionType = collectionType;
			collectionType = (JavaClass)eResolveProxy((InternalEObject)collectionType);
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
	public NotificationChain eInverseAdd(InternalEObject otherEnd, int aFeatureID, Class baseClass, NotificationChain msgs) {
		if (aFeatureID >= 0) {
			switch (eDerivedStructuralFeatureID(aFeatureID, baseClass)) {
				case EjbPackage.CMR_FIELD__EANNOTATIONS:
					return ((InternalEList)getEAnnotations()).basicAdd(otherEnd, msgs);
				case EjbPackage.CMR_FIELD__ECONTAINING_CLASS:
					if (eContainer != null)
						msgs = eBasicRemoveFromContainer(msgs);
					return eBasicSetContainer(otherEnd, EjbPackage.CMR_FIELD__ECONTAINING_CLASS, msgs);
				case EjbPackage.CMR_FIELD__ROLE:
					if (eContainer != null)
						msgs = eBasicRemoveFromContainer(msgs);
					return eBasicSetContainer(otherEnd, EjbPackage.CMR_FIELD__ROLE, msgs);
				default:
					return eDynamicInverseAdd(otherEnd, aFeatureID, baseClass, msgs);
			}
		}
		if (eContainer != null)
			msgs = eBasicRemoveFromContainer(msgs);
		return eBasicSetContainer(otherEnd, aFeatureID, msgs);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int aFeatureID, Class baseClass, NotificationChain msgs) {
		if (aFeatureID >= 0) {
			switch (eDerivedStructuralFeatureID(aFeatureID, baseClass)) {
				case EjbPackage.CMR_FIELD__EANNOTATIONS:
					return ((InternalEList)getEAnnotations()).basicRemove(otherEnd, msgs);
				case EjbPackage.CMR_FIELD__ECONTAINING_CLASS:
					return eBasicSetContainer(null, EjbPackage.CMR_FIELD__ECONTAINING_CLASS, msgs);
				case EjbPackage.CMR_FIELD__DESCRIPTIONS:
					return ((InternalEList)getDescriptions()).basicRemove(otherEnd, msgs);
				case EjbPackage.CMR_FIELD__ROLE:
					return eBasicSetContainer(null, EjbPackage.CMR_FIELD__ROLE, msgs);
				default:
					return eDynamicInverseRemove(otherEnd, aFeatureID, baseClass, msgs);
			}
		}
		return eBasicSetContainer(null, aFeatureID, msgs);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain eBasicRemoveFromContainer(NotificationChain msgs) {
		if (eContainerFeatureID >= 0) {
			switch (eContainerFeatureID) {
				case EjbPackage.CMR_FIELD__ECONTAINING_CLASS:
					return eContainer.eInverseRemove(this, EcorePackage.ECLASS__ESTRUCTURAL_FEATURES, EClass.class, msgs);
				case EjbPackage.CMR_FIELD__ROLE:
					return eContainer.eInverseRemove(this, EjbPackage.EJB_RELATIONSHIP_ROLE__CMR_FIELD, EJBRelationshipRole.class, msgs);
				default:
					return eDynamicBasicRemoveFromContainer(msgs);
			}
		}
		return eContainer.eInverseRemove(this, EOPPOSITE_FEATURE_BASE - eContainerFeatureID, null, msgs);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Object eGet(EStructuralFeature eFeature, boolean resolve) {
		switch (eDerivedStructuralFeatureID(eFeature)) {
			case EjbPackage.CMR_FIELD__EANNOTATIONS:
				return getEAnnotations();
			case EjbPackage.CMR_FIELD__NAME:
				return getName();
			case EjbPackage.CMR_FIELD__ORDERED:
				return isOrdered() ? Boolean.TRUE : Boolean.FALSE;
			case EjbPackage.CMR_FIELD__UNIQUE:
				return isUnique() ? Boolean.TRUE : Boolean.FALSE;
			case EjbPackage.CMR_FIELD__LOWER_BOUND:
				return new Integer(getLowerBound());
			case EjbPackage.CMR_FIELD__UPPER_BOUND:
				return new Integer(getUpperBound());
			case EjbPackage.CMR_FIELD__MANY:
				return isMany() ? Boolean.TRUE : Boolean.FALSE;
			case EjbPackage.CMR_FIELD__REQUIRED:
				return isRequired() ? Boolean.TRUE : Boolean.FALSE;
			case EjbPackage.CMR_FIELD__ETYPE:
				if (resolve) return getEType();
				return basicGetEType();
			case EjbPackage.CMR_FIELD__CHANGEABLE:
				return isChangeable() ? Boolean.TRUE : Boolean.FALSE;
			case EjbPackage.CMR_FIELD__VOLATILE:
				return isVolatile() ? Boolean.TRUE : Boolean.FALSE;
			case EjbPackage.CMR_FIELD__TRANSIENT:
				return isTransient() ? Boolean.TRUE : Boolean.FALSE;
			case EjbPackage.CMR_FIELD__DEFAULT_VALUE_LITERAL:
				return getDefaultValueLiteral();
			case EjbPackage.CMR_FIELD__DEFAULT_VALUE:
				return getDefaultValue();
			case EjbPackage.CMR_FIELD__UNSETTABLE:
				return isUnsettable() ? Boolean.TRUE : Boolean.FALSE;
			case EjbPackage.CMR_FIELD__DERIVED:
				return isDerived() ? Boolean.TRUE : Boolean.FALSE;
			case EjbPackage.CMR_FIELD__ECONTAINING_CLASS:
				return getEContainingClass();
			case EjbPackage.CMR_FIELD__ID:
				return isID() ? Boolean.TRUE : Boolean.FALSE;
			case EjbPackage.CMR_FIELD__EATTRIBUTE_TYPE:
				if (resolve) return getEAttributeType();
				return basicGetEAttributeType();
			case EjbPackage.CMR_FIELD__DESCRIPTION:
				return getDescription();
			case EjbPackage.CMR_FIELD__DESCRIPTIONS:
				return getDescriptions();
			case EjbPackage.CMR_FIELD__ROLE:
				return getRole();
			case EjbPackage.CMR_FIELD__COLLECTION_TYPE:
				if (resolve) return getCollectionType();
				return basicGetCollectionType();
		}
		return eDynamicGet(eFeature, resolve);
	}

	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	public boolean eIsSet(EStructuralFeature eFeature) {
		switch (eDerivedStructuralFeatureID(eFeature)) {
			case EjbPackage.CMR_FIELD__EANNOTATIONS:
				return eAnnotations != null && !eAnnotations.isEmpty();
			case EjbPackage.CMR_FIELD__NAME:
				return NAME_EDEFAULT == null ? name != null : !NAME_EDEFAULT.equals(name);
			case EjbPackage.CMR_FIELD__ORDERED:
				return ordered != ORDERED_EDEFAULT;
			case EjbPackage.CMR_FIELD__UNIQUE:
				return unique != UNIQUE_EDEFAULT;
			case EjbPackage.CMR_FIELD__LOWER_BOUND:
				return lowerBound != LOWER_BOUND_EDEFAULT;
			case EjbPackage.CMR_FIELD__UPPER_BOUND:
				return upperBound != UPPER_BOUND_EDEFAULT;
			case EjbPackage.CMR_FIELD__MANY:
				return isMany() != false;
			case EjbPackage.CMR_FIELD__REQUIRED:
				return isRequired() != false;
			case EjbPackage.CMR_FIELD__ETYPE:
				return eType != null;
			case EjbPackage.CMR_FIELD__CHANGEABLE:
				return changeable != CHANGEABLE_EDEFAULT;
			case EjbPackage.CMR_FIELD__VOLATILE:
				return volatile_ != VOLATILE_EDEFAULT;
			case EjbPackage.CMR_FIELD__TRANSIENT:
				return transient_ != TRANSIENT_EDEFAULT;
			case EjbPackage.CMR_FIELD__DEFAULT_VALUE_LITERAL:
				return DEFAULT_VALUE_LITERAL_EDEFAULT == null ? defaultValueLiteral != null : !DEFAULT_VALUE_LITERAL_EDEFAULT.equals(defaultValueLiteral);
			case EjbPackage.CMR_FIELD__DEFAULT_VALUE:
				return getDefaultValue() != null;
			case EjbPackage.CMR_FIELD__UNSETTABLE:
				return unsettable != UNSETTABLE_EDEFAULT;
			case EjbPackage.CMR_FIELD__DERIVED:
				return derived != DERIVED_EDEFAULT;
			case EjbPackage.CMR_FIELD__ECONTAINING_CLASS:
				return getEContainingClass() != null;
			case EjbPackage.CMR_FIELD__ID:
				return iD != ID_EDEFAULT;
			case EjbPackage.CMR_FIELD__EATTRIBUTE_TYPE:
				return basicGetEAttributeType() != null;
			case EjbPackage.CMR_FIELD__DESCRIPTION:
				return DESCRIPTION_EDEFAULT == null ? description != null : !DESCRIPTION_EDEFAULT.equals(description);
			case EjbPackage.CMR_FIELD__DESCRIPTIONS:
				return descriptions != null && !descriptions.isEmpty();
			case EjbPackage.CMR_FIELD__ROLE:
				return getRole() != null;
			case EjbPackage.CMR_FIELD__COLLECTION_TYPE:
				return collectionType != null;
		}
		return eDynamicIsSet(eFeature);
	}

	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	public void eSet(EStructuralFeature eFeature, Object newValue) {
		switch (eDerivedStructuralFeatureID(eFeature)) {
			case EjbPackage.CMR_FIELD__EANNOTATIONS:
				getEAnnotations().clear();
				getEAnnotations().addAll((Collection)newValue);
				return;
			case EjbPackage.CMR_FIELD__NAME:
				setName((String)newValue);
				return;
			case EjbPackage.CMR_FIELD__ORDERED:
				setOrdered(((Boolean)newValue).booleanValue());
				return;
			case EjbPackage.CMR_FIELD__UNIQUE:
				setUnique(((Boolean)newValue).booleanValue());
				return;
			case EjbPackage.CMR_FIELD__LOWER_BOUND:
				setLowerBound(((Integer)newValue).intValue());
				return;
			case EjbPackage.CMR_FIELD__UPPER_BOUND:
				setUpperBound(((Integer)newValue).intValue());
				return;
			case EjbPackage.CMR_FIELD__ETYPE:
				setEType((EClassifier)newValue);
				return;
			case EjbPackage.CMR_FIELD__CHANGEABLE:
				setChangeable(((Boolean)newValue).booleanValue());
				return;
			case EjbPackage.CMR_FIELD__VOLATILE:
				setVolatile(((Boolean)newValue).booleanValue());
				return;
			case EjbPackage.CMR_FIELD__TRANSIENT:
				setTransient(((Boolean)newValue).booleanValue());
				return;
			case EjbPackage.CMR_FIELD__DEFAULT_VALUE_LITERAL:
				setDefaultValueLiteral((String)newValue);
				return;
			case EjbPackage.CMR_FIELD__UNSETTABLE:
				setUnsettable(((Boolean)newValue).booleanValue());
				return;
			case EjbPackage.CMR_FIELD__DERIVED:
				setDerived(((Boolean)newValue).booleanValue());
				return;
			case EjbPackage.CMR_FIELD__ID:
				setID(((Boolean)newValue).booleanValue());
				return;
			case EjbPackage.CMR_FIELD__DESCRIPTION:
				setDescription((String)newValue);
				return;
			case EjbPackage.CMR_FIELD__DESCRIPTIONS:
				getDescriptions().clear();
				getDescriptions().addAll((Collection)newValue);
				return;
			case EjbPackage.CMR_FIELD__ROLE:
				setRole((EJBRelationshipRole)newValue);
				return;
			case EjbPackage.CMR_FIELD__COLLECTION_TYPE:
				setCollectionType((JavaClass)newValue);
				return;
		}
		eDynamicSet(eFeature, newValue);
	}

	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	public void eUnset(EStructuralFeature eFeature) {
		switch (eDerivedStructuralFeatureID(eFeature)) {
			case EjbPackage.CMR_FIELD__EANNOTATIONS:
				getEAnnotations().clear();
				return;
			case EjbPackage.CMR_FIELD__NAME:
				setName(NAME_EDEFAULT);
				return;
			case EjbPackage.CMR_FIELD__ORDERED:
				setOrdered(ORDERED_EDEFAULT);
				return;
			case EjbPackage.CMR_FIELD__UNIQUE:
				setUnique(UNIQUE_EDEFAULT);
				return;
			case EjbPackage.CMR_FIELD__LOWER_BOUND:
				setLowerBound(LOWER_BOUND_EDEFAULT);
				return;
			case EjbPackage.CMR_FIELD__UPPER_BOUND:
				setUpperBound(UPPER_BOUND_EDEFAULT);
				return;
			case EjbPackage.CMR_FIELD__ETYPE:
				setEType((EClassifier)null);
				return;
			case EjbPackage.CMR_FIELD__CHANGEABLE:
				setChangeable(CHANGEABLE_EDEFAULT);
				return;
			case EjbPackage.CMR_FIELD__VOLATILE:
				setVolatile(VOLATILE_EDEFAULT);
				return;
			case EjbPackage.CMR_FIELD__TRANSIENT:
				setTransient(TRANSIENT_EDEFAULT);
				return;
			case EjbPackage.CMR_FIELD__DEFAULT_VALUE_LITERAL:
				setDefaultValueLiteral(DEFAULT_VALUE_LITERAL_EDEFAULT);
				return;
			case EjbPackage.CMR_FIELD__UNSETTABLE:
				setUnsettable(UNSETTABLE_EDEFAULT);
				return;
			case EjbPackage.CMR_FIELD__DERIVED:
				setDerived(DERIVED_EDEFAULT);
				return;
			case EjbPackage.CMR_FIELD__ID:
				setID(ID_EDEFAULT);
				return;
			case EjbPackage.CMR_FIELD__DESCRIPTION:
				setDescription(DESCRIPTION_EDEFAULT);
				return;
			case EjbPackage.CMR_FIELD__DESCRIPTIONS:
				getDescriptions().clear();
				return;
			case EjbPackage.CMR_FIELD__ROLE:
				setRole((EJBRelationshipRole)null);
				return;
			case EjbPackage.CMR_FIELD__COLLECTION_TYPE:
				setCollectionType((JavaClass)null);
				return;
		}
		eDynamicUnset(eFeature);
	}

	/**
	 * @see org.eclipse.jst.j2ee.internal.ejb.impl.CMPAttributeImpl#getCMPEntity()
	 */
	public ContainerManagedEntity getCMPEntity() {
		EJBRelationshipRole role = getRole();
		if (role != null)
			return role.getSourceEntity();
		return null;
	}


}






