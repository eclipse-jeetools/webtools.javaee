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
 *  $Revision: 1.3.4.1 $  $Date: 2004/06/24 18:19:38 $ 
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
import org.eclipse.jem.internal.beaninfo.IndexedPropertyDecorator;
import org.eclipse.jem.internal.beaninfo.adapters.BeaninfoProxyConstants;
import org.eclipse.jem.internal.beaninfo.core.Utilities;
import org.eclipse.jem.java.JavaClass;
import org.eclipse.jem.java.Method;
import org.eclipse.jem.internal.proxy.core.IMethodProxy;
import org.eclipse.jem.internal.proxy.core.ThrowableProxy;
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
	 * The cached value of the '{@link #getIndexedReadMethod() <em>Indexed Read Method</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getIndexedReadMethod()
	 * @generated
	 * @ordered
	 */
	protected Method indexedReadMethod = null;
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

	public Method getIndexedReadMethod() {
		if (!eIsSet(BeaninfoPackage.eINSTANCE.getIndexedPropertyDecorator_IndexedReadMethod())) {
			if (validProxy(fFeatureProxy)) {			
				try {
					return (Method) Utilities.getMethod((IMethodProxy) BeaninfoProxyConstants.getConstants(fFeatureProxy.getProxyFactoryRegistry()).getIndexedReadMethodProxy().invoke(fFeatureProxy),  getEModelElement().eResource().getResourceSet());
				} catch (ThrowableProxy e) {
				}
			} else
				if (fFeatureDecoratorProxy != null)
					return ((IndexedPropertyDecorator) fFeatureDecoratorProxy).getIndexedReadMethod();
		}			
					
		return this.getIndexedReadMethodGen();
	}
	public Method getIndexedWriteMethod() {
		if (!eIsSet(BeaninfoPackage.eINSTANCE.getIndexedPropertyDecorator_IndexedWriteMethod())) {
			if (validProxy(fFeatureProxy)) {			
				try {
					return (Method) Utilities.getMethod((IMethodProxy) BeaninfoProxyConstants.getConstants(fFeatureProxy.getProxyFactoryRegistry()).getIndexedWriteMethodProxy().invoke(fFeatureProxy),  getEModelElement().eResource().getResourceSet());
				} catch (ThrowableProxy e) {
				}
			} else
				if (fFeatureDecoratorProxy != null)
					return ((IndexedPropertyDecorator) fFeatureDecoratorProxy).getIndexedWriteMethod();
		}			
									
		return this.getIndexedWriteMethodGen();
	}
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setIndexedReadMethod(Method newIndexedReadMethod) {
		Method oldIndexedReadMethod = indexedReadMethod;
		indexedReadMethod = newIndexedReadMethod;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, BeaninfoPackage.INDEXED_PROPERTY_DECORATOR__INDEXED_READ_METHOD, oldIndexedReadMethod, indexedReadMethod));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setIndexedWriteMethod(Method newIndexedWriteMethod) {
		Method oldIndexedWriteMethod = indexedWriteMethod;
		indexedWriteMethod = newIndexedWriteMethod;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, BeaninfoPackage.INDEXED_PROPERTY_DECORATOR__INDEXED_WRITE_METHOD, oldIndexedWriteMethod, indexedWriteMethod));
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
			case BeaninfoPackage.INDEXED_PROPERTY_DECORATOR__ATTRIBUTES_EXPLICIT:
				return isAttributesExplicit() ? Boolean.TRUE : Boolean.FALSE;
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
			case BeaninfoPackage.INDEXED_PROPERTY_DECORATOR__PROPERTY_EDITOR_CLASS:
				if (resolve) return getPropertyEditorClass();
				return basicGetPropertyEditorClass();
			case BeaninfoPackage.INDEXED_PROPERTY_DECORATOR__READ_METHOD:
				if (resolve) return getReadMethod();
				return basicGetReadMethod();
			case BeaninfoPackage.INDEXED_PROPERTY_DECORATOR__WRITE_METHOD:
				if (resolve) return getWriteMethod();
				return basicGetWriteMethod();
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
			case BeaninfoPackage.INDEXED_PROPERTY_DECORATOR__ATTRIBUTES_EXPLICIT:
				setAttributesExplicit(((Boolean)newValue).booleanValue());
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
			case BeaninfoPackage.INDEXED_PROPERTY_DECORATOR__PROPERTY_EDITOR_CLASS:
				setPropertyEditorClass((JavaClass)newValue);
				return;
			case BeaninfoPackage.INDEXED_PROPERTY_DECORATOR__READ_METHOD:
				setReadMethod((Method)newValue);
				return;
			case BeaninfoPackage.INDEXED_PROPERTY_DECORATOR__WRITE_METHOD:
				setWriteMethod((Method)newValue);
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
			case BeaninfoPackage.INDEXED_PROPERTY_DECORATOR__ATTRIBUTES_EXPLICIT:
				setAttributesExplicit(ATTRIBUTES_EXPLICIT_EDEFAULT);
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
			case BeaninfoPackage.INDEXED_PROPERTY_DECORATOR__PROPERTY_EDITOR_CLASS:
				setPropertyEditorClass((JavaClass)null);
				return;
			case BeaninfoPackage.INDEXED_PROPERTY_DECORATOR__READ_METHOD:
				setReadMethod((Method)null);
				return;
			case BeaninfoPackage.INDEXED_PROPERTY_DECORATOR__WRITE_METHOD:
				setWriteMethod((Method)null);
				return;
			case BeaninfoPackage.INDEXED_PROPERTY_DECORATOR__INDEXED_READ_METHOD:
				setIndexedReadMethod((Method)null);
				return;
			case BeaninfoPackage.INDEXED_PROPERTY_DECORATOR__INDEXED_WRITE_METHOD:
				setIndexedWriteMethod((Method)null);
				return;
		}
		eDynamicUnset(eFeature);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean eIsSet(EStructuralFeature eFeature) {
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
			case BeaninfoPackage.INDEXED_PROPERTY_DECORATOR__ATTRIBUTES_EXPLICIT:
				return attributesExplicit != ATTRIBUTES_EXPLICIT_EDEFAULT;
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
			case BeaninfoPackage.INDEXED_PROPERTY_DECORATOR__PROPERTY_EDITOR_CLASS:
				return propertyEditorClass != null;
			case BeaninfoPackage.INDEXED_PROPERTY_DECORATOR__READ_METHOD:
				return readMethod != null;
			case BeaninfoPackage.INDEXED_PROPERTY_DECORATOR__WRITE_METHOD:
				return writeMethod != null;
			case BeaninfoPackage.INDEXED_PROPERTY_DECORATOR__INDEXED_READ_METHOD:
				return indexedReadMethod != null;
			case BeaninfoPackage.INDEXED_PROPERTY_DECORATOR__INDEXED_WRITE_METHOD:
				return indexedWriteMethod != null;
		}
		return eDynamicIsSet(eFeature);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Method getIndexedReadMethodGen() {
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
	public Method basicGetIndexedReadMethod() {
		return indexedReadMethod;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Method getIndexedWriteMethodGen() {
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
	public Method basicGetIndexedWriteMethod() {
		return indexedWriteMethod;
	}

}
