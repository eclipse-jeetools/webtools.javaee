/*******************************************************************************
 * Copyright (c) 2001, 2004 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Common Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/cpl-v10.html
 * 
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *******************************************************************************/
package org.eclipse.jem.internal.beaninfo.impl;
/*
 *  $RCSfile: IndexedPropertyDecoratorImpl.java,v $
 *  $Revision: 1.6 $  $Date: 2005/02/08 21:54:02 $ 
 */


import java.util.Collection;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EModelElement;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.EcorePackage;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.util.InternalEList;

import org.eclipse.jem.internal.beaninfo.BeaninfoPackage;
import org.eclipse.jem.internal.beaninfo.ImplicitItem;
import org.eclipse.jem.internal.beaninfo.IndexedPropertyDecorator;
import org.eclipse.jem.java.Field;
import org.eclipse.jem.java.JavaClass;
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
	 * This is true if the Indexed Read Method reference has been set.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	protected boolean indexedReadMethodESet = false;

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
	 * This is true if the Indexed Write Method reference has been set.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	protected boolean indexedWriteMethodESet = false;

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
		return BeaninfoPackage.eINSTANCE.getIndexedPropertyDecorator();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Method getIndexedReadMethod() {
		if (indexedReadMethod != null && indexedReadMethod.eIsProxy()) {
			Method oldIndexedReadMethod = indexedReadMethod;
			indexedReadMethod = (Method)eResolveProxy((InternalEObject)indexedReadMethod);
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
		boolean oldIndexedReadMethodESet = indexedReadMethodESet;
		indexedReadMethodESet = true;
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
		boolean oldIndexedReadMethodESet = indexedReadMethodESet;
		indexedReadMethod = null;
		indexedReadMethodESet = false;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.UNSET, BeaninfoPackage.INDEXED_PROPERTY_DECORATOR__INDEXED_READ_METHOD, oldIndexedReadMethod, null, oldIndexedReadMethodESet));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean isSetIndexedReadMethod() {
		return indexedReadMethodESet;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Method getIndexedWriteMethod() {
		if (indexedWriteMethod != null && indexedWriteMethod.eIsProxy()) {
			Method oldIndexedWriteMethod = indexedWriteMethod;
			indexedWriteMethod = (Method)eResolveProxy((InternalEObject)indexedWriteMethod);
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
		boolean oldIndexedWriteMethodESet = indexedWriteMethodESet;
		indexedWriteMethodESet = true;
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
		boolean oldIndexedWriteMethodESet = indexedWriteMethodESet;
		indexedWriteMethod = null;
		indexedWriteMethodESet = false;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.UNSET, BeaninfoPackage.INDEXED_PROPERTY_DECORATOR__INDEXED_WRITE_METHOD, oldIndexedWriteMethod, null, oldIndexedWriteMethodESet));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean isSetIndexedWriteMethod() {
		return indexedWriteMethodESet;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain eInverseAdd(InternalEObject otherEnd, int featureID, Class baseClass, NotificationChain msgs) {
		if (featureID >= 0) {
			switch (eDerivedStructuralFeatureID(featureID, baseClass)) {
				case BeaninfoPackage.INDEXED_PROPERTY_DECORATOR__EANNOTATIONS:
					return ((InternalEList)getEAnnotations()).basicAdd(otherEnd, msgs);
				case BeaninfoPackage.INDEXED_PROPERTY_DECORATOR__EMODEL_ELEMENT:
					if (eContainer != null)
						msgs = eBasicRemoveFromContainer(msgs);
					return eBasicSetContainer(otherEnd, BeaninfoPackage.INDEXED_PROPERTY_DECORATOR__EMODEL_ELEMENT, msgs);
				default:
					return eDynamicInverseAdd(otherEnd, featureID, baseClass, msgs);
			}
		}
		if (eContainer != null)
			msgs = eBasicRemoveFromContainer(msgs);
		return eBasicSetContainer(otherEnd, featureID, msgs);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, Class baseClass, NotificationChain msgs) {
		if (featureID >= 0) {
			switch (eDerivedStructuralFeatureID(featureID, baseClass)) {
				case BeaninfoPackage.INDEXED_PROPERTY_DECORATOR__EANNOTATIONS:
					return ((InternalEList)getEAnnotations()).basicRemove(otherEnd, msgs);
				case BeaninfoPackage.INDEXED_PROPERTY_DECORATOR__DETAILS:
					return ((InternalEList)getDetails()).basicRemove(otherEnd, msgs);
				case BeaninfoPackage.INDEXED_PROPERTY_DECORATOR__EMODEL_ELEMENT:
					return eBasicSetContainer(null, BeaninfoPackage.INDEXED_PROPERTY_DECORATOR__EMODEL_ELEMENT, msgs);
				case BeaninfoPackage.INDEXED_PROPERTY_DECORATOR__CONTENTS:
					return ((InternalEList)getContents()).basicRemove(otherEnd, msgs);
				case BeaninfoPackage.INDEXED_PROPERTY_DECORATOR__ATTRIBUTES:
					return ((InternalEList)getAttributes()).basicRemove(otherEnd, msgs);
				default:
					return eDynamicInverseRemove(otherEnd, featureID, baseClass, msgs);
			}
		}
		return eBasicSetContainer(null, featureID, msgs);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain eBasicRemoveFromContainer(NotificationChain msgs) {
		if (eContainerFeatureID >= 0) {
			switch (eContainerFeatureID) {
				case BeaninfoPackage.INDEXED_PROPERTY_DECORATOR__EMODEL_ELEMENT:
					return ((InternalEObject)eContainer).eInverseRemove(this, EcorePackage.EMODEL_ELEMENT__EANNOTATIONS, EModelElement.class, msgs);
				default:
					return eDynamicBasicRemoveFromContainer(msgs);
			}
		}
		return ((InternalEObject)eContainer).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - eContainerFeatureID, null, msgs);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Object eGet(EStructuralFeature eFeature, boolean resolve) {
		switch (eDerivedStructuralFeatureID(eFeature)) {
			case BeaninfoPackage.INDEXED_PROPERTY_DECORATOR__EANNOTATIONS:
				return getEAnnotations();
			case BeaninfoPackage.INDEXED_PROPERTY_DECORATOR__SOURCE:
				return getSource();
			case BeaninfoPackage.INDEXED_PROPERTY_DECORATOR__DETAILS:
				return getDetails();
			case BeaninfoPackage.INDEXED_PROPERTY_DECORATOR__EMODEL_ELEMENT:
				return getEModelElement();
			case BeaninfoPackage.INDEXED_PROPERTY_DECORATOR__CONTENTS:
				return getContents();
			case BeaninfoPackage.INDEXED_PROPERTY_DECORATOR__REFERENCES:
				return getReferences();
			case BeaninfoPackage.INDEXED_PROPERTY_DECORATOR__DISPLAY_NAME:
				return getDisplayName();
			case BeaninfoPackage.INDEXED_PROPERTY_DECORATOR__SHORT_DESCRIPTION:
				return getShortDescription();
			case BeaninfoPackage.INDEXED_PROPERTY_DECORATOR__CATEGORY:
				return getCategory();
			case BeaninfoPackage.INDEXED_PROPERTY_DECORATOR__EXPERT:
				return isExpert() ? Boolean.TRUE : Boolean.FALSE;
			case BeaninfoPackage.INDEXED_PROPERTY_DECORATOR__HIDDEN:
				return isHidden() ? Boolean.TRUE : Boolean.FALSE;
			case BeaninfoPackage.INDEXED_PROPERTY_DECORATOR__PREFERRED:
				return isPreferred() ? Boolean.TRUE : Boolean.FALSE;
			case BeaninfoPackage.INDEXED_PROPERTY_DECORATOR__MERGE_INTROSPECTION:
				return isMergeIntrospection() ? Boolean.TRUE : Boolean.FALSE;
			case BeaninfoPackage.INDEXED_PROPERTY_DECORATOR__ATTRIBUTES_EXPLICIT_EMPTY:
				return isAttributesExplicitEmpty() ? Boolean.TRUE : Boolean.FALSE;
			case BeaninfoPackage.INDEXED_PROPERTY_DECORATOR__IMPLICITLY_SET_BITS:
				return new Long(getImplicitlySetBits());
			case BeaninfoPackage.INDEXED_PROPERTY_DECORATOR__IMPLICIT_DECORATOR_FLAG:
				return getImplicitDecoratorFlag();
			case BeaninfoPackage.INDEXED_PROPERTY_DECORATOR__ATTRIBUTES:
				return getAttributes();
			case BeaninfoPackage.INDEXED_PROPERTY_DECORATOR__BOUND:
				return isBound() ? Boolean.TRUE : Boolean.FALSE;
			case BeaninfoPackage.INDEXED_PROPERTY_DECORATOR__CONSTRAINED:
				return isConstrained() ? Boolean.TRUE : Boolean.FALSE;
			case BeaninfoPackage.INDEXED_PROPERTY_DECORATOR__DESIGN_TIME:
				return isDesignTime() ? Boolean.TRUE : Boolean.FALSE;
			case BeaninfoPackage.INDEXED_PROPERTY_DECORATOR__ALWAYS_INCOMPATIBLE:
				return isAlwaysIncompatible() ? Boolean.TRUE : Boolean.FALSE;
			case BeaninfoPackage.INDEXED_PROPERTY_DECORATOR__FILTER_FLAGS:
				return getFilterFlags();
			case BeaninfoPackage.INDEXED_PROPERTY_DECORATOR__FIELD_READ_ONLY:
				return isFieldReadOnly() ? Boolean.TRUE : Boolean.FALSE;
			case BeaninfoPackage.INDEXED_PROPERTY_DECORATOR__PROPERTY_EDITOR_CLASS:
				if (resolve) return getPropertyEditorClass();
				return basicGetPropertyEditorClass();
			case BeaninfoPackage.INDEXED_PROPERTY_DECORATOR__READ_METHOD:
				if (resolve) return getReadMethod();
				return basicGetReadMethod();
			case BeaninfoPackage.INDEXED_PROPERTY_DECORATOR__WRITE_METHOD:
				if (resolve) return getWriteMethod();
				return basicGetWriteMethod();
			case BeaninfoPackage.INDEXED_PROPERTY_DECORATOR__FIELD:
				if (resolve) return getField();
				return basicGetField();
			case BeaninfoPackage.INDEXED_PROPERTY_DECORATOR__INDEXED_READ_METHOD:
				if (resolve) return getIndexedReadMethod();
				return basicGetIndexedReadMethod();
			case BeaninfoPackage.INDEXED_PROPERTY_DECORATOR__INDEXED_WRITE_METHOD:
				if (resolve) return getIndexedWriteMethod();
				return basicGetIndexedWriteMethod();
		}
		return eDynamicGet(eFeature, resolve);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void eSet(EStructuralFeature eFeature, Object newValue) {
		switch (eDerivedStructuralFeatureID(eFeature)) {
			case BeaninfoPackage.INDEXED_PROPERTY_DECORATOR__EANNOTATIONS:
				getEAnnotations().clear();
				getEAnnotations().addAll((Collection)newValue);
				return;
			case BeaninfoPackage.INDEXED_PROPERTY_DECORATOR__SOURCE:
				setSource((String)newValue);
				return;
			case BeaninfoPackage.INDEXED_PROPERTY_DECORATOR__DETAILS:
				getDetails().clear();
				getDetails().addAll((Collection)newValue);
				return;
			case BeaninfoPackage.INDEXED_PROPERTY_DECORATOR__EMODEL_ELEMENT:
				setEModelElement((EModelElement)newValue);
				return;
			case BeaninfoPackage.INDEXED_PROPERTY_DECORATOR__CONTENTS:
				getContents().clear();
				getContents().addAll((Collection)newValue);
				return;
			case BeaninfoPackage.INDEXED_PROPERTY_DECORATOR__REFERENCES:
				getReferences().clear();
				getReferences().addAll((Collection)newValue);
				return;
			case BeaninfoPackage.INDEXED_PROPERTY_DECORATOR__DISPLAY_NAME:
				setDisplayName((String)newValue);
				return;
			case BeaninfoPackage.INDEXED_PROPERTY_DECORATOR__SHORT_DESCRIPTION:
				setShortDescription((String)newValue);
				return;
			case BeaninfoPackage.INDEXED_PROPERTY_DECORATOR__CATEGORY:
				setCategory((String)newValue);
				return;
			case BeaninfoPackage.INDEXED_PROPERTY_DECORATOR__EXPERT:
				setExpert(((Boolean)newValue).booleanValue());
				return;
			case BeaninfoPackage.INDEXED_PROPERTY_DECORATOR__HIDDEN:
				setHidden(((Boolean)newValue).booleanValue());
				return;
			case BeaninfoPackage.INDEXED_PROPERTY_DECORATOR__PREFERRED:
				setPreferred(((Boolean)newValue).booleanValue());
				return;
			case BeaninfoPackage.INDEXED_PROPERTY_DECORATOR__MERGE_INTROSPECTION:
				setMergeIntrospection(((Boolean)newValue).booleanValue());
				return;
			case BeaninfoPackage.INDEXED_PROPERTY_DECORATOR__ATTRIBUTES_EXPLICIT_EMPTY:
				setAttributesExplicitEmpty(((Boolean)newValue).booleanValue());
				return;
			case BeaninfoPackage.INDEXED_PROPERTY_DECORATOR__IMPLICITLY_SET_BITS:
				setImplicitlySetBits(((Long)newValue).longValue());
				return;
			case BeaninfoPackage.INDEXED_PROPERTY_DECORATOR__IMPLICIT_DECORATOR_FLAG:
				setImplicitDecoratorFlag((ImplicitItem)newValue);
				return;
			case BeaninfoPackage.INDEXED_PROPERTY_DECORATOR__ATTRIBUTES:
				getAttributes().clear();
				getAttributes().addAll((Collection)newValue);
				return;
			case BeaninfoPackage.INDEXED_PROPERTY_DECORATOR__BOUND:
				setBound(((Boolean)newValue).booleanValue());
				return;
			case BeaninfoPackage.INDEXED_PROPERTY_DECORATOR__CONSTRAINED:
				setConstrained(((Boolean)newValue).booleanValue());
				return;
			case BeaninfoPackage.INDEXED_PROPERTY_DECORATOR__DESIGN_TIME:
				setDesignTime(((Boolean)newValue).booleanValue());
				return;
			case BeaninfoPackage.INDEXED_PROPERTY_DECORATOR__ALWAYS_INCOMPATIBLE:
				setAlwaysIncompatible(((Boolean)newValue).booleanValue());
				return;
			case BeaninfoPackage.INDEXED_PROPERTY_DECORATOR__FILTER_FLAGS:
				getFilterFlags().clear();
				getFilterFlags().addAll((Collection)newValue);
				return;
			case BeaninfoPackage.INDEXED_PROPERTY_DECORATOR__FIELD_READ_ONLY:
				setFieldReadOnly(((Boolean)newValue).booleanValue());
				return;
			case BeaninfoPackage.INDEXED_PROPERTY_DECORATOR__PROPERTY_EDITOR_CLASS:
				setPropertyEditorClass((JavaClass)newValue);
				return;
			case BeaninfoPackage.INDEXED_PROPERTY_DECORATOR__READ_METHOD:
				setReadMethod((Method)newValue);
				return;
			case BeaninfoPackage.INDEXED_PROPERTY_DECORATOR__WRITE_METHOD:
				setWriteMethod((Method)newValue);
				return;
			case BeaninfoPackage.INDEXED_PROPERTY_DECORATOR__FIELD:
				setField((Field)newValue);
				return;
			case BeaninfoPackage.INDEXED_PROPERTY_DECORATOR__INDEXED_READ_METHOD:
				setIndexedReadMethod((Method)newValue);
				return;
			case BeaninfoPackage.INDEXED_PROPERTY_DECORATOR__INDEXED_WRITE_METHOD:
				setIndexedWriteMethod((Method)newValue);
				return;
		}
		eDynamicSet(eFeature, newValue);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void eUnset(EStructuralFeature eFeature) {
		switch (eDerivedStructuralFeatureID(eFeature)) {
			case BeaninfoPackage.INDEXED_PROPERTY_DECORATOR__EANNOTATIONS:
				getEAnnotations().clear();
				return;
			case BeaninfoPackage.INDEXED_PROPERTY_DECORATOR__SOURCE:
				setSource(SOURCE_EDEFAULT);
				return;
			case BeaninfoPackage.INDEXED_PROPERTY_DECORATOR__DETAILS:
				getDetails().clear();
				return;
			case BeaninfoPackage.INDEXED_PROPERTY_DECORATOR__EMODEL_ELEMENT:
				setEModelElement((EModelElement)null);
				return;
			case BeaninfoPackage.INDEXED_PROPERTY_DECORATOR__CONTENTS:
				getContents().clear();
				return;
			case BeaninfoPackage.INDEXED_PROPERTY_DECORATOR__REFERENCES:
				getReferences().clear();
				return;
			case BeaninfoPackage.INDEXED_PROPERTY_DECORATOR__DISPLAY_NAME:
				unsetDisplayName();
				return;
			case BeaninfoPackage.INDEXED_PROPERTY_DECORATOR__SHORT_DESCRIPTION:
				unsetShortDescription();
				return;
			case BeaninfoPackage.INDEXED_PROPERTY_DECORATOR__CATEGORY:
				setCategory(CATEGORY_EDEFAULT);
				return;
			case BeaninfoPackage.INDEXED_PROPERTY_DECORATOR__EXPERT:
				unsetExpert();
				return;
			case BeaninfoPackage.INDEXED_PROPERTY_DECORATOR__HIDDEN:
				unsetHidden();
				return;
			case BeaninfoPackage.INDEXED_PROPERTY_DECORATOR__PREFERRED:
				unsetPreferred();
				return;
			case BeaninfoPackage.INDEXED_PROPERTY_DECORATOR__MERGE_INTROSPECTION:
				setMergeIntrospection(MERGE_INTROSPECTION_EDEFAULT);
				return;
			case BeaninfoPackage.INDEXED_PROPERTY_DECORATOR__ATTRIBUTES_EXPLICIT_EMPTY:
				setAttributesExplicitEmpty(ATTRIBUTES_EXPLICIT_EMPTY_EDEFAULT);
				return;
			case BeaninfoPackage.INDEXED_PROPERTY_DECORATOR__IMPLICITLY_SET_BITS:
				setImplicitlySetBits(IMPLICITLY_SET_BITS_EDEFAULT);
				return;
			case BeaninfoPackage.INDEXED_PROPERTY_DECORATOR__IMPLICIT_DECORATOR_FLAG:
				setImplicitDecoratorFlag(IMPLICIT_DECORATOR_FLAG_EDEFAULT);
				return;
			case BeaninfoPackage.INDEXED_PROPERTY_DECORATOR__ATTRIBUTES:
				getAttributes().clear();
				return;
			case BeaninfoPackage.INDEXED_PROPERTY_DECORATOR__BOUND:
				unsetBound();
				return;
			case BeaninfoPackage.INDEXED_PROPERTY_DECORATOR__CONSTRAINED:
				unsetConstrained();
				return;
			case BeaninfoPackage.INDEXED_PROPERTY_DECORATOR__DESIGN_TIME:
				unsetDesignTime();
				return;
			case BeaninfoPackage.INDEXED_PROPERTY_DECORATOR__ALWAYS_INCOMPATIBLE:
				setAlwaysIncompatible(ALWAYS_INCOMPATIBLE_EDEFAULT);
				return;
			case BeaninfoPackage.INDEXED_PROPERTY_DECORATOR__FILTER_FLAGS:
				getFilterFlags().clear();
				return;
			case BeaninfoPackage.INDEXED_PROPERTY_DECORATOR__FIELD_READ_ONLY:
				setFieldReadOnly(FIELD_READ_ONLY_EDEFAULT);
				return;
			case BeaninfoPackage.INDEXED_PROPERTY_DECORATOR__PROPERTY_EDITOR_CLASS:
				setPropertyEditorClass((JavaClass)null);
				return;
			case BeaninfoPackage.INDEXED_PROPERTY_DECORATOR__READ_METHOD:
				unsetReadMethod();
				return;
			case BeaninfoPackage.INDEXED_PROPERTY_DECORATOR__WRITE_METHOD:
				unsetWriteMethod();
				return;
			case BeaninfoPackage.INDEXED_PROPERTY_DECORATOR__FIELD:
				unsetField();
				return;
			case BeaninfoPackage.INDEXED_PROPERTY_DECORATOR__INDEXED_READ_METHOD:
				unsetIndexedReadMethod();
				return;
			case BeaninfoPackage.INDEXED_PROPERTY_DECORATOR__INDEXED_WRITE_METHOD:
				unsetIndexedWriteMethod();
				return;
		}
		eDynamicUnset(eFeature);
	}

	/* (non-Javadoc)
	 * @see org.eclipse.emf.ecore.EObject#eIsSet(org.eclipse.emf.ecore.EStructuralFeature)
	 */
	public boolean eIsSet(EStructuralFeature eFeature) {
		switch (eDerivedStructuralFeatureID(eFeature)) {
			case BeaninfoPackage.INDEXED_PROPERTY_DECORATOR__SOURCE:
				return isSourceSet();	// Override so that if set to the same as classname, then it is considered not set.
			default:
				return eIsSetGen(eFeature);
		}
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean eIsSetGen(EStructuralFeature eFeature) {
		switch (eDerivedStructuralFeatureID(eFeature)) {
			case BeaninfoPackage.INDEXED_PROPERTY_DECORATOR__EANNOTATIONS:
				return eAnnotations != null && !eAnnotations.isEmpty();
			case BeaninfoPackage.INDEXED_PROPERTY_DECORATOR__SOURCE:
				return SOURCE_EDEFAULT == null ? source != null : !SOURCE_EDEFAULT.equals(source);
			case BeaninfoPackage.INDEXED_PROPERTY_DECORATOR__DETAILS:
				return details != null && !details.isEmpty();
			case BeaninfoPackage.INDEXED_PROPERTY_DECORATOR__EMODEL_ELEMENT:
				return getEModelElement() != null;
			case BeaninfoPackage.INDEXED_PROPERTY_DECORATOR__CONTENTS:
				return contents != null && !contents.isEmpty();
			case BeaninfoPackage.INDEXED_PROPERTY_DECORATOR__REFERENCES:
				return references != null && !references.isEmpty();
			case BeaninfoPackage.INDEXED_PROPERTY_DECORATOR__DISPLAY_NAME:
				return isSetDisplayName();
			case BeaninfoPackage.INDEXED_PROPERTY_DECORATOR__SHORT_DESCRIPTION:
				return isSetShortDescription();
			case BeaninfoPackage.INDEXED_PROPERTY_DECORATOR__CATEGORY:
				return CATEGORY_EDEFAULT == null ? category != null : !CATEGORY_EDEFAULT.equals(category);
			case BeaninfoPackage.INDEXED_PROPERTY_DECORATOR__EXPERT:
				return isSetExpert();
			case BeaninfoPackage.INDEXED_PROPERTY_DECORATOR__HIDDEN:
				return isSetHidden();
			case BeaninfoPackage.INDEXED_PROPERTY_DECORATOR__PREFERRED:
				return isSetPreferred();
			case BeaninfoPackage.INDEXED_PROPERTY_DECORATOR__MERGE_INTROSPECTION:
				return mergeIntrospection != MERGE_INTROSPECTION_EDEFAULT;
			case BeaninfoPackage.INDEXED_PROPERTY_DECORATOR__ATTRIBUTES_EXPLICIT_EMPTY:
				return attributesExplicitEmpty != ATTRIBUTES_EXPLICIT_EMPTY_EDEFAULT;
			case BeaninfoPackage.INDEXED_PROPERTY_DECORATOR__IMPLICITLY_SET_BITS:
				return implicitlySetBits != IMPLICITLY_SET_BITS_EDEFAULT;
			case BeaninfoPackage.INDEXED_PROPERTY_DECORATOR__IMPLICIT_DECORATOR_FLAG:
				return implicitDecoratorFlag != IMPLICIT_DECORATOR_FLAG_EDEFAULT;
			case BeaninfoPackage.INDEXED_PROPERTY_DECORATOR__ATTRIBUTES:
				return attributes != null && !attributes.isEmpty();
			case BeaninfoPackage.INDEXED_PROPERTY_DECORATOR__BOUND:
				return isSetBound();
			case BeaninfoPackage.INDEXED_PROPERTY_DECORATOR__CONSTRAINED:
				return isSetConstrained();
			case BeaninfoPackage.INDEXED_PROPERTY_DECORATOR__DESIGN_TIME:
				return isSetDesignTime();
			case BeaninfoPackage.INDEXED_PROPERTY_DECORATOR__ALWAYS_INCOMPATIBLE:
				return alwaysIncompatible != ALWAYS_INCOMPATIBLE_EDEFAULT;
			case BeaninfoPackage.INDEXED_PROPERTY_DECORATOR__FILTER_FLAGS:
				return filterFlags != null && !filterFlags.isEmpty();
			case BeaninfoPackage.INDEXED_PROPERTY_DECORATOR__FIELD_READ_ONLY:
				return fieldReadOnly != FIELD_READ_ONLY_EDEFAULT;
			case BeaninfoPackage.INDEXED_PROPERTY_DECORATOR__PROPERTY_EDITOR_CLASS:
				return propertyEditorClass != null;
			case BeaninfoPackage.INDEXED_PROPERTY_DECORATOR__READ_METHOD:
				return isSetReadMethod();
			case BeaninfoPackage.INDEXED_PROPERTY_DECORATOR__WRITE_METHOD:
				return isSetWriteMethod();
			case BeaninfoPackage.INDEXED_PROPERTY_DECORATOR__FIELD:
				return isSetField();
			case BeaninfoPackage.INDEXED_PROPERTY_DECORATOR__INDEXED_READ_METHOD:
				return isSetIndexedReadMethod();
			case BeaninfoPackage.INDEXED_PROPERTY_DECORATOR__INDEXED_WRITE_METHOD:
				return isSetIndexedWriteMethod();
		}
		return eDynamicIsSet(eFeature);
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
