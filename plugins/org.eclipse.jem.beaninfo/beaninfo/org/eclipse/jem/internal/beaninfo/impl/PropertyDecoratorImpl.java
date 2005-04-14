/*******************************************************************************
 * Copyright (c) 2001, 2004 IBM Corporation and others.
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
 *  $RCSfile: PropertyDecoratorImpl.java,v $
 *  $Revision: 1.11 $  $Date: 2005/04/14 19:05:36 $ 
 */


import java.util.Collection;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EClassifier;
import org.eclipse.emf.ecore.EModelElement;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.EcorePackage;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.util.EDataTypeUniqueEList;
import org.eclipse.emf.ecore.util.InternalEList;

import org.eclipse.jem.internal.beaninfo.BeaninfoPackage;
import org.eclipse.jem.internal.beaninfo.ImplicitItem;
import org.eclipse.jem.internal.beaninfo.PropertyDecorator;
import org.eclipse.jem.java.Field;
import org.eclipse.jem.java.JavaClass;
import org.eclipse.jem.java.Method;
/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Property Decorator</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.eclipse.jem.internal.beaninfo.impl.PropertyDecoratorImpl#isBound <em>Bound</em>}</li>
 *   <li>{@link org.eclipse.jem.internal.beaninfo.impl.PropertyDecoratorImpl#isConstrained <em>Constrained</em>}</li>
 *   <li>{@link org.eclipse.jem.internal.beaninfo.impl.PropertyDecoratorImpl#isDesignTime <em>Design Time</em>}</li>
 *   <li>{@link org.eclipse.jem.internal.beaninfo.impl.PropertyDecoratorImpl#isAlwaysIncompatible <em>Always Incompatible</em>}</li>
 *   <li>{@link org.eclipse.jem.internal.beaninfo.impl.PropertyDecoratorImpl#getFilterFlags <em>Filter Flags</em>}</li>
 *   <li>{@link org.eclipse.jem.internal.beaninfo.impl.PropertyDecoratorImpl#isFieldReadOnly <em>Field Read Only</em>}</li>
 *   <li>{@link org.eclipse.jem.internal.beaninfo.impl.PropertyDecoratorImpl#getPropertyEditorClass <em>Property Editor Class</em>}</li>
 *   <li>{@link org.eclipse.jem.internal.beaninfo.impl.PropertyDecoratorImpl#getReadMethod <em>Read Method</em>}</li>
 *   <li>{@link org.eclipse.jem.internal.beaninfo.impl.PropertyDecoratorImpl#getWriteMethod <em>Write Method</em>}</li>
 *   <li>{@link org.eclipse.jem.internal.beaninfo.impl.PropertyDecoratorImpl#getField <em>Field</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */


public class PropertyDecoratorImpl extends FeatureDecoratorImpl implements PropertyDecorator{

	/**
	 * Bits for implicitly set features. This is internal, not meant for clients.
	 */
	public static final long PROPERTY_EDITOR_CLASS_IMPLICIT = 0x1L;
	public static final long PROPERTY_TYPE_IMPLICIT = 0x2L;
	public static final long PROPERTY_READMETHOD_IMPLICIT = 0x4L;
	public static final long PROPERTY_WRITEMETHOD_IMPLICIT = 0x8L;
	public static final long PROPERTY_BOUND_IMPLICIT = 0x10L;
	public static final long PROPERTY_CONSTRAINED_IMPLICIT = 0x20L;
	public static final long PROPERTY_DESIGNTIME_IMPLICIT = 0x40L;
	public static final long PROPERTY_FIELD_IMPLICIT = 0x80L;
	
	
	/**
	 * The default value of the '{@link #isBound() <em>Bound</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isBound()
	 * @generated
	 * @ordered
	 */
	protected static final boolean BOUND_EDEFAULT = false;

	/**
	 * The cached value of the '{@link #isBound() <em>Bound</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isBound()
	 * @generated
	 * @ordered
	 */
	protected boolean bound = BOUND_EDEFAULT;

	/**
	 * This is true if the Bound attribute has been set.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	protected boolean boundESet = false;

	/**
	 * The default value of the '{@link #isConstrained() <em>Constrained</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isConstrained()
	 * @generated
	 * @ordered
	 */
	protected static final boolean CONSTRAINED_EDEFAULT = false;

	/**
	 * The cached value of the '{@link #isConstrained() <em>Constrained</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isConstrained()
	 * @generated
	 * @ordered
	 */
	protected boolean constrained = CONSTRAINED_EDEFAULT;

	/**
	 * This is true if the Constrained attribute has been set.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	protected boolean constrainedESet = false;

	/**
	 * The default value of the '{@link #isDesignTime() <em>Design Time</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isDesignTime()
	 * @generated
	 * @ordered
	 */
	protected static final boolean DESIGN_TIME_EDEFAULT = false;

	/**
	 * The cached value of the '{@link #isDesignTime() <em>Design Time</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isDesignTime()
	 * @generated
	 * @ordered
	 */
	protected boolean designTime = DESIGN_TIME_EDEFAULT;

	/**
	 * This is true if the Design Time attribute has been set.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	protected boolean designTimeESet = false;

	/**
	 * The default value of the '{@link #isAlwaysIncompatible() <em>Always Incompatible</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isAlwaysIncompatible()
	 * @generated
	 * @ordered
	 */
	protected static final boolean ALWAYS_INCOMPATIBLE_EDEFAULT = false;

	/**
	 * The cached value of the '{@link #isAlwaysIncompatible() <em>Always Incompatible</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isAlwaysIncompatible()
	 * @generated
	 * @ordered
	 */
	protected boolean alwaysIncompatible = ALWAYS_INCOMPATIBLE_EDEFAULT;
		
	/**
	 * The cached value of the '{@link #getFilterFlags() <em>Filter Flags</em>}' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getFilterFlags()
	 * @generated
	 * @ordered
	 */
	protected EList filterFlags = null;
	/**
	 * The default value of the '{@link #isFieldReadOnly() <em>Field Read Only</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isFieldReadOnly()
	 * @generated
	 * @ordered
	 */
	protected static final boolean FIELD_READ_ONLY_EDEFAULT = false;

	/**
	 * The cached value of the '{@link #isFieldReadOnly() <em>Field Read Only</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isFieldReadOnly()
	 * @generated
	 * @ordered
	 */
	protected boolean fieldReadOnly = FIELD_READ_ONLY_EDEFAULT;

	/**
	 * The cached value of the '{@link #getPropertyEditorClass() <em>Property Editor Class</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getPropertyEditorClass()
	 * @generated
	 * @ordered
	 */
	protected JavaClass propertyEditorClass = null;
	/**
	 * The cached value of the '{@link #getReadMethod() <em>Read Method</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getReadMethod()
	 * @generated
	 * @ordered
	 */
	protected Method readMethod = null;
	/**
	 * This is true if the Read Method reference has been set.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	protected boolean readMethodESet = false;

	/**
	 * The cached value of the '{@link #getWriteMethod() <em>Write Method</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getWriteMethod()
	 * @generated
	 * @ordered
	 */
	protected Method writeMethod = null;
	
	/**
	 * This is true if the Write Method reference has been set.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	protected boolean writeMethodESet = false;

	/**
	 * The cached value of the '{@link #getField() <em>Field</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getField()
	 * @generated
	 * @ordered
	 */
	protected Field field = null;

	/**
	 * This is true if the Field reference has been set.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	protected boolean fieldESet = false;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */	
	protected PropertyDecoratorImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected EClass eStaticClass() {
		return BeaninfoPackage.eINSTANCE.getPropertyDecorator();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean isBound() {
		return bound;
	}

	public EClassifier getPropertyType() {
		EStructuralFeature feature = (EStructuralFeature) getEModelElement();
		return (feature != null) ? feature.getEType() : null;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setBound(boolean newBound) {
		boolean oldBound = bound;
		bound = newBound;
		boolean oldBoundESet = boundESet;
		boundESet = true;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, BeaninfoPackage.PROPERTY_DECORATOR__BOUND, oldBound, bound, !oldBoundESet));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void unsetBound() {
		boolean oldBound = bound;
		boolean oldBoundESet = boundESet;
		bound = BOUND_EDEFAULT;
		boundESet = false;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.UNSET, BeaninfoPackage.PROPERTY_DECORATOR__BOUND, oldBound, BOUND_EDEFAULT, oldBoundESet));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean isSetBound() {
		return boundESet;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean isConstrained() {
		return constrained;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setConstrained(boolean newConstrained) {
		boolean oldConstrained = constrained;
		constrained = newConstrained;
		boolean oldConstrainedESet = constrainedESet;
		constrainedESet = true;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, BeaninfoPackage.PROPERTY_DECORATOR__CONSTRAINED, oldConstrained, constrained, !oldConstrainedESet));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void unsetConstrained() {
		boolean oldConstrained = constrained;
		boolean oldConstrainedESet = constrainedESet;
		constrained = CONSTRAINED_EDEFAULT;
		constrainedESet = false;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.UNSET, BeaninfoPackage.PROPERTY_DECORATOR__CONSTRAINED, oldConstrained, CONSTRAINED_EDEFAULT, oldConstrainedESet));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean isSetConstrained() {
		return constrainedESet;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean isDesignTime() {
		return designTime;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setDesignTime(boolean newDesignTime) {
		boolean oldDesignTime = designTime;
		designTime = newDesignTime;
		boolean oldDesignTimeESet = designTimeESet;
		designTimeESet = true;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, BeaninfoPackage.PROPERTY_DECORATOR__DESIGN_TIME, oldDesignTime, designTime, !oldDesignTimeESet));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void unsetDesignTime() {
		boolean oldDesignTime = designTime;
		boolean oldDesignTimeESet = designTimeESet;
		designTime = DESIGN_TIME_EDEFAULT;
		designTimeESet = false;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.UNSET, BeaninfoPackage.PROPERTY_DECORATOR__DESIGN_TIME, oldDesignTime, DESIGN_TIME_EDEFAULT, oldDesignTimeESet));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean isSetDesignTime() {
		return designTimeESet;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean isAlwaysIncompatible() {
		return alwaysIncompatible;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setAlwaysIncompatible(boolean newAlwaysIncompatible) {
		boolean oldAlwaysIncompatible = alwaysIncompatible;
		alwaysIncompatible = newAlwaysIncompatible;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, BeaninfoPackage.PROPERTY_DECORATOR__ALWAYS_INCOMPATIBLE, oldAlwaysIncompatible, alwaysIncompatible));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList getFilterFlags() {
		if (filterFlags == null) {
			filterFlags = new EDataTypeUniqueEList(String.class, this, BeaninfoPackage.PROPERTY_DECORATOR__FILTER_FLAGS);
		}
		return filterFlags;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean isFieldReadOnly() {
		return fieldReadOnly;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setFieldReadOnly(boolean newFieldReadOnly) {
		boolean oldFieldReadOnly = fieldReadOnly;
		fieldReadOnly = newFieldReadOnly;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, BeaninfoPackage.PROPERTY_DECORATOR__FIELD_READ_ONLY, oldFieldReadOnly, fieldReadOnly));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public JavaClass getPropertyEditorClass() {
		if (propertyEditorClass != null && propertyEditorClass.eIsProxy()) {
			JavaClass oldPropertyEditorClass = propertyEditorClass;
			propertyEditorClass = (JavaClass)eResolveProxy((InternalEObject)propertyEditorClass);
			if (propertyEditorClass != oldPropertyEditorClass) {
				if (eNotificationRequired())
					eNotify(new ENotificationImpl(this, Notification.RESOLVE, BeaninfoPackage.PROPERTY_DECORATOR__PROPERTY_EDITOR_CLASS, oldPropertyEditorClass, propertyEditorClass));
			}
		}
		return propertyEditorClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setPropertyEditorClass(JavaClass newPropertyEditorClass) {
		JavaClass oldPropertyEditorClass = propertyEditorClass;
		propertyEditorClass = newPropertyEditorClass;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, BeaninfoPackage.PROPERTY_DECORATOR__PROPERTY_EDITOR_CLASS, oldPropertyEditorClass, propertyEditorClass));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Method getReadMethod() {
		if (readMethod != null && readMethod.eIsProxy()) {
			Method oldReadMethod = readMethod;
			readMethod = (Method)eResolveProxy((InternalEObject)readMethod);
			if (readMethod != oldReadMethod) {
				if (eNotificationRequired())
					eNotify(new ENotificationImpl(this, Notification.RESOLVE, BeaninfoPackage.PROPERTY_DECORATOR__READ_METHOD, oldReadMethod, readMethod));
			}
		}
		return readMethod;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setReadMethod(Method newReadMethod) {
		Method oldReadMethod = readMethod;
		readMethod = newReadMethod;
		boolean oldReadMethodESet = readMethodESet;
		readMethodESet = true;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, BeaninfoPackage.PROPERTY_DECORATOR__READ_METHOD, oldReadMethod, readMethod, !oldReadMethodESet));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void unsetReadMethod() {
		Method oldReadMethod = readMethod;
		boolean oldReadMethodESet = readMethodESet;
		readMethod = null;
		readMethodESet = false;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.UNSET, BeaninfoPackage.PROPERTY_DECORATOR__READ_METHOD, oldReadMethod, null, oldReadMethodESet));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean isSetReadMethod() {
		return readMethodESet;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Method getWriteMethod() {
		if (writeMethod != null && writeMethod.eIsProxy()) {
			Method oldWriteMethod = writeMethod;
			writeMethod = (Method)eResolveProxy((InternalEObject)writeMethod);
			if (writeMethod != oldWriteMethod) {
				if (eNotificationRequired())
					eNotify(new ENotificationImpl(this, Notification.RESOLVE, BeaninfoPackage.PROPERTY_DECORATOR__WRITE_METHOD, oldWriteMethod, writeMethod));
			}
		}
		return writeMethod;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setWriteMethod(Method newWriteMethod) {
		Method oldWriteMethod = writeMethod;
		writeMethod = newWriteMethod;
		boolean oldWriteMethodESet = writeMethodESet;
		writeMethodESet = true;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, BeaninfoPackage.PROPERTY_DECORATOR__WRITE_METHOD, oldWriteMethod, writeMethod, !oldWriteMethodESet));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void unsetWriteMethod() {
		Method oldWriteMethod = writeMethod;
		boolean oldWriteMethodESet = writeMethodESet;
		writeMethod = null;
		writeMethodESet = false;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.UNSET, BeaninfoPackage.PROPERTY_DECORATOR__WRITE_METHOD, oldWriteMethod, null, oldWriteMethodESet));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean isSetWriteMethod() {
		return writeMethodESet;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Field getField() {
		if (field != null && field.eIsProxy()) {
			Field oldField = field;
			field = (Field)eResolveProxy((InternalEObject)field);
			if (field != oldField) {
				if (eNotificationRequired())
					eNotify(new ENotificationImpl(this, Notification.RESOLVE, BeaninfoPackage.PROPERTY_DECORATOR__FIELD, oldField, field));
			}
		}
		return field;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Field basicGetField() {
		return field;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setField(Field newField) {
		Field oldField = field;
		field = newField;
		boolean oldFieldESet = fieldESet;
		fieldESet = true;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, BeaninfoPackage.PROPERTY_DECORATOR__FIELD, oldField, field, !oldFieldESet));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void unsetField() {
		Field oldField = field;
		boolean oldFieldESet = fieldESet;
		field = null;
		fieldESet = false;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.UNSET, BeaninfoPackage.PROPERTY_DECORATOR__FIELD, oldField, null, oldFieldESet));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean isSetField() {
		return fieldESet;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String toString() {
		if (eIsProxy()) return super.toString();

		StringBuffer result = new StringBuffer(super.toString());
		result.append(" (bound: ");
		if (boundESet) result.append(bound); else result.append("<unset>");
		result.append(", constrained: ");
		if (constrainedESet) result.append(constrained); else result.append("<unset>");
		result.append(", designTime: ");
		if (designTimeESet) result.append(designTime); else result.append("<unset>");
		result.append(", alwaysIncompatible: ");
		result.append(alwaysIncompatible);
		result.append(", filterFlags: ");
		result.append(filterFlags);
		result.append(", fieldReadOnly: ");
		result.append(fieldReadOnly);
		result.append(')');
		return result.toString();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public JavaClass basicGetPropertyEditorClass() {
		return propertyEditorClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Method basicGetReadMethod() {
		return readMethod;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Method basicGetWriteMethod() {
		return writeMethod;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain eInverseAdd(InternalEObject otherEnd, int featureID, Class baseClass, NotificationChain msgs) {
		if (featureID >= 0) {
			switch (eDerivedStructuralFeatureID(featureID, baseClass)) {
				case BeaninfoPackage.PROPERTY_DECORATOR__EANNOTATIONS:
					return ((InternalEList)getEAnnotations()).basicAdd(otherEnd, msgs);
				case BeaninfoPackage.PROPERTY_DECORATOR__EMODEL_ELEMENT:
					if (eContainer != null)
						msgs = eBasicRemoveFromContainer(msgs);
					return eBasicSetContainer(otherEnd, BeaninfoPackage.PROPERTY_DECORATOR__EMODEL_ELEMENT, msgs);
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
				case BeaninfoPackage.PROPERTY_DECORATOR__EANNOTATIONS:
					return ((InternalEList)getEAnnotations()).basicRemove(otherEnd, msgs);
				case BeaninfoPackage.PROPERTY_DECORATOR__DETAILS:
					return ((InternalEList)getDetails()).basicRemove(otherEnd, msgs);
				case BeaninfoPackage.PROPERTY_DECORATOR__EMODEL_ELEMENT:
					return eBasicSetContainer(null, BeaninfoPackage.PROPERTY_DECORATOR__EMODEL_ELEMENT, msgs);
				case BeaninfoPackage.PROPERTY_DECORATOR__CONTENTS:
					return ((InternalEList)getContents()).basicRemove(otherEnd, msgs);
				case BeaninfoPackage.PROPERTY_DECORATOR__ATTRIBUTES:
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
				case BeaninfoPackage.PROPERTY_DECORATOR__EMODEL_ELEMENT:
					return eContainer.eInverseRemove(this, EcorePackage.EMODEL_ELEMENT__EANNOTATIONS, EModelElement.class, msgs);
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
			case BeaninfoPackage.PROPERTY_DECORATOR__EANNOTATIONS:
				return getEAnnotations();
			case BeaninfoPackage.PROPERTY_DECORATOR__SOURCE:
				return getSource();
			case BeaninfoPackage.PROPERTY_DECORATOR__DETAILS:
				return getDetails();
			case BeaninfoPackage.PROPERTY_DECORATOR__EMODEL_ELEMENT:
				return getEModelElement();
			case BeaninfoPackage.PROPERTY_DECORATOR__CONTENTS:
				return getContents();
			case BeaninfoPackage.PROPERTY_DECORATOR__REFERENCES:
				return getReferences();
			case BeaninfoPackage.PROPERTY_DECORATOR__DISPLAY_NAME:
				return getDisplayName();
			case BeaninfoPackage.PROPERTY_DECORATOR__SHORT_DESCRIPTION:
				return getShortDescription();
			case BeaninfoPackage.PROPERTY_DECORATOR__CATEGORY:
				return getCategory();
			case BeaninfoPackage.PROPERTY_DECORATOR__EXPERT:
				return isExpert() ? Boolean.TRUE : Boolean.FALSE;
			case BeaninfoPackage.PROPERTY_DECORATOR__HIDDEN:
				return isHidden() ? Boolean.TRUE : Boolean.FALSE;
			case BeaninfoPackage.PROPERTY_DECORATOR__PREFERRED:
				return isPreferred() ? Boolean.TRUE : Boolean.FALSE;
			case BeaninfoPackage.PROPERTY_DECORATOR__MERGE_INTROSPECTION:
				return isMergeIntrospection() ? Boolean.TRUE : Boolean.FALSE;
			case BeaninfoPackage.PROPERTY_DECORATOR__ATTRIBUTES_EXPLICIT_EMPTY:
				return isAttributesExplicitEmpty() ? Boolean.TRUE : Boolean.FALSE;
			case BeaninfoPackage.PROPERTY_DECORATOR__IMPLICITLY_SET_BITS:
				return new Long(getImplicitlySetBits());
			case BeaninfoPackage.PROPERTY_DECORATOR__IMPLICIT_DECORATOR_FLAG:
				return getImplicitDecoratorFlag();
			case BeaninfoPackage.PROPERTY_DECORATOR__ATTRIBUTES:
				return getAttributes();
			case BeaninfoPackage.PROPERTY_DECORATOR__BOUND:
				return isBound() ? Boolean.TRUE : Boolean.FALSE;
			case BeaninfoPackage.PROPERTY_DECORATOR__CONSTRAINED:
				return isConstrained() ? Boolean.TRUE : Boolean.FALSE;
			case BeaninfoPackage.PROPERTY_DECORATOR__DESIGN_TIME:
				return isDesignTime() ? Boolean.TRUE : Boolean.FALSE;
			case BeaninfoPackage.PROPERTY_DECORATOR__ALWAYS_INCOMPATIBLE:
				return isAlwaysIncompatible() ? Boolean.TRUE : Boolean.FALSE;
			case BeaninfoPackage.PROPERTY_DECORATOR__FILTER_FLAGS:
				return getFilterFlags();
			case BeaninfoPackage.PROPERTY_DECORATOR__FIELD_READ_ONLY:
				return isFieldReadOnly() ? Boolean.TRUE : Boolean.FALSE;
			case BeaninfoPackage.PROPERTY_DECORATOR__PROPERTY_EDITOR_CLASS:
				if (resolve) return getPropertyEditorClass();
				return basicGetPropertyEditorClass();
			case BeaninfoPackage.PROPERTY_DECORATOR__READ_METHOD:
				if (resolve) return getReadMethod();
				return basicGetReadMethod();
			case BeaninfoPackage.PROPERTY_DECORATOR__WRITE_METHOD:
				if (resolve) return getWriteMethod();
				return basicGetWriteMethod();
			case BeaninfoPackage.PROPERTY_DECORATOR__FIELD:
				if (resolve) return getField();
				return basicGetField();
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
			case BeaninfoPackage.PROPERTY_DECORATOR__EANNOTATIONS:
				getEAnnotations().clear();
				getEAnnotations().addAll((Collection)newValue);
				return;
			case BeaninfoPackage.PROPERTY_DECORATOR__SOURCE:
				setSource((String)newValue);
				return;
			case BeaninfoPackage.PROPERTY_DECORATOR__DETAILS:
				getDetails().clear();
				getDetails().addAll((Collection)newValue);
				return;
			case BeaninfoPackage.PROPERTY_DECORATOR__EMODEL_ELEMENT:
				setEModelElement((EModelElement)newValue);
				return;
			case BeaninfoPackage.PROPERTY_DECORATOR__CONTENTS:
				getContents().clear();
				getContents().addAll((Collection)newValue);
				return;
			case BeaninfoPackage.PROPERTY_DECORATOR__REFERENCES:
				getReferences().clear();
				getReferences().addAll((Collection)newValue);
				return;
			case BeaninfoPackage.PROPERTY_DECORATOR__DISPLAY_NAME:
				setDisplayName((String)newValue);
				return;
			case BeaninfoPackage.PROPERTY_DECORATOR__SHORT_DESCRIPTION:
				setShortDescription((String)newValue);
				return;
			case BeaninfoPackage.PROPERTY_DECORATOR__CATEGORY:
				setCategory((String)newValue);
				return;
			case BeaninfoPackage.PROPERTY_DECORATOR__EXPERT:
				setExpert(((Boolean)newValue).booleanValue());
				return;
			case BeaninfoPackage.PROPERTY_DECORATOR__HIDDEN:
				setHidden(((Boolean)newValue).booleanValue());
				return;
			case BeaninfoPackage.PROPERTY_DECORATOR__PREFERRED:
				setPreferred(((Boolean)newValue).booleanValue());
				return;
			case BeaninfoPackage.PROPERTY_DECORATOR__MERGE_INTROSPECTION:
				setMergeIntrospection(((Boolean)newValue).booleanValue());
				return;
			case BeaninfoPackage.PROPERTY_DECORATOR__ATTRIBUTES_EXPLICIT_EMPTY:
				setAttributesExplicitEmpty(((Boolean)newValue).booleanValue());
				return;
			case BeaninfoPackage.PROPERTY_DECORATOR__IMPLICITLY_SET_BITS:
				setImplicitlySetBits(((Long)newValue).longValue());
				return;
			case BeaninfoPackage.PROPERTY_DECORATOR__IMPLICIT_DECORATOR_FLAG:
				setImplicitDecoratorFlag((ImplicitItem)newValue);
				return;
			case BeaninfoPackage.PROPERTY_DECORATOR__ATTRIBUTES:
				getAttributes().clear();
				getAttributes().addAll((Collection)newValue);
				return;
			case BeaninfoPackage.PROPERTY_DECORATOR__BOUND:
				setBound(((Boolean)newValue).booleanValue());
				return;
			case BeaninfoPackage.PROPERTY_DECORATOR__CONSTRAINED:
				setConstrained(((Boolean)newValue).booleanValue());
				return;
			case BeaninfoPackage.PROPERTY_DECORATOR__DESIGN_TIME:
				setDesignTime(((Boolean)newValue).booleanValue());
				return;
			case BeaninfoPackage.PROPERTY_DECORATOR__ALWAYS_INCOMPATIBLE:
				setAlwaysIncompatible(((Boolean)newValue).booleanValue());
				return;
			case BeaninfoPackage.PROPERTY_DECORATOR__FILTER_FLAGS:
				getFilterFlags().clear();
				getFilterFlags().addAll((Collection)newValue);
				return;
			case BeaninfoPackage.PROPERTY_DECORATOR__FIELD_READ_ONLY:
				setFieldReadOnly(((Boolean)newValue).booleanValue());
				return;
			case BeaninfoPackage.PROPERTY_DECORATOR__PROPERTY_EDITOR_CLASS:
				setPropertyEditorClass((JavaClass)newValue);
				return;
			case BeaninfoPackage.PROPERTY_DECORATOR__READ_METHOD:
				setReadMethod((Method)newValue);
				return;
			case BeaninfoPackage.PROPERTY_DECORATOR__WRITE_METHOD:
				setWriteMethod((Method)newValue);
				return;
			case BeaninfoPackage.PROPERTY_DECORATOR__FIELD:
				setField((Field)newValue);
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
			case BeaninfoPackage.PROPERTY_DECORATOR__EANNOTATIONS:
				getEAnnotations().clear();
				return;
			case BeaninfoPackage.PROPERTY_DECORATOR__SOURCE:
				setSource(SOURCE_EDEFAULT);
				return;
			case BeaninfoPackage.PROPERTY_DECORATOR__DETAILS:
				getDetails().clear();
				return;
			case BeaninfoPackage.PROPERTY_DECORATOR__EMODEL_ELEMENT:
				setEModelElement((EModelElement)null);
				return;
			case BeaninfoPackage.PROPERTY_DECORATOR__CONTENTS:
				getContents().clear();
				return;
			case BeaninfoPackage.PROPERTY_DECORATOR__REFERENCES:
				getReferences().clear();
				return;
			case BeaninfoPackage.PROPERTY_DECORATOR__DISPLAY_NAME:
				unsetDisplayName();
				return;
			case BeaninfoPackage.PROPERTY_DECORATOR__SHORT_DESCRIPTION:
				unsetShortDescription();
				return;
			case BeaninfoPackage.PROPERTY_DECORATOR__CATEGORY:
				setCategory(CATEGORY_EDEFAULT);
				return;
			case BeaninfoPackage.PROPERTY_DECORATOR__EXPERT:
				unsetExpert();
				return;
			case BeaninfoPackage.PROPERTY_DECORATOR__HIDDEN:
				unsetHidden();
				return;
			case BeaninfoPackage.PROPERTY_DECORATOR__PREFERRED:
				unsetPreferred();
				return;
			case BeaninfoPackage.PROPERTY_DECORATOR__MERGE_INTROSPECTION:
				setMergeIntrospection(MERGE_INTROSPECTION_EDEFAULT);
				return;
			case BeaninfoPackage.PROPERTY_DECORATOR__ATTRIBUTES_EXPLICIT_EMPTY:
				setAttributesExplicitEmpty(ATTRIBUTES_EXPLICIT_EMPTY_EDEFAULT);
				return;
			case BeaninfoPackage.PROPERTY_DECORATOR__IMPLICITLY_SET_BITS:
				setImplicitlySetBits(IMPLICITLY_SET_BITS_EDEFAULT);
				return;
			case BeaninfoPackage.PROPERTY_DECORATOR__IMPLICIT_DECORATOR_FLAG:
				setImplicitDecoratorFlag(IMPLICIT_DECORATOR_FLAG_EDEFAULT);
				return;
			case BeaninfoPackage.PROPERTY_DECORATOR__ATTRIBUTES:
				getAttributes().clear();
				return;
			case BeaninfoPackage.PROPERTY_DECORATOR__BOUND:
				unsetBound();
				return;
			case BeaninfoPackage.PROPERTY_DECORATOR__CONSTRAINED:
				unsetConstrained();
				return;
			case BeaninfoPackage.PROPERTY_DECORATOR__DESIGN_TIME:
				unsetDesignTime();
				return;
			case BeaninfoPackage.PROPERTY_DECORATOR__ALWAYS_INCOMPATIBLE:
				setAlwaysIncompatible(ALWAYS_INCOMPATIBLE_EDEFAULT);
				return;
			case BeaninfoPackage.PROPERTY_DECORATOR__FILTER_FLAGS:
				getFilterFlags().clear();
				return;
			case BeaninfoPackage.PROPERTY_DECORATOR__FIELD_READ_ONLY:
				setFieldReadOnly(FIELD_READ_ONLY_EDEFAULT);
				return;
			case BeaninfoPackage.PROPERTY_DECORATOR__PROPERTY_EDITOR_CLASS:
				setPropertyEditorClass((JavaClass)null);
				return;
			case BeaninfoPackage.PROPERTY_DECORATOR__READ_METHOD:
				unsetReadMethod();
				return;
			case BeaninfoPackage.PROPERTY_DECORATOR__WRITE_METHOD:
				unsetWriteMethod();
				return;
			case BeaninfoPackage.PROPERTY_DECORATOR__FIELD:
				unsetField();
				return;
		}
		eDynamicUnset(eFeature);
	}

	/* (non-Javadoc)
	 * @see org.eclipse.emf.ecore.EObject#eIsSet(org.eclipse.emf.ecore.EStructuralFeature)
	 */
	public boolean eIsSet(EStructuralFeature eFeature) {
		switch (eDerivedStructuralFeatureID(eFeature)) {
			case BeaninfoPackage.PROPERTY_DECORATOR__SOURCE:
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
			case BeaninfoPackage.PROPERTY_DECORATOR__EANNOTATIONS:
				return eAnnotations != null && !eAnnotations.isEmpty();
			case BeaninfoPackage.PROPERTY_DECORATOR__SOURCE:
				return SOURCE_EDEFAULT == null ? source != null : !SOURCE_EDEFAULT.equals(source);
			case BeaninfoPackage.PROPERTY_DECORATOR__DETAILS:
				return details != null && !details.isEmpty();
			case BeaninfoPackage.PROPERTY_DECORATOR__EMODEL_ELEMENT:
				return getEModelElement() != null;
			case BeaninfoPackage.PROPERTY_DECORATOR__CONTENTS:
				return contents != null && !contents.isEmpty();
			case BeaninfoPackage.PROPERTY_DECORATOR__REFERENCES:
				return references != null && !references.isEmpty();
			case BeaninfoPackage.PROPERTY_DECORATOR__DISPLAY_NAME:
				return isSetDisplayName();
			case BeaninfoPackage.PROPERTY_DECORATOR__SHORT_DESCRIPTION:
				return isSetShortDescription();
			case BeaninfoPackage.PROPERTY_DECORATOR__CATEGORY:
				return CATEGORY_EDEFAULT == null ? category != null : !CATEGORY_EDEFAULT.equals(category);
			case BeaninfoPackage.PROPERTY_DECORATOR__EXPERT:
				return isSetExpert();
			case BeaninfoPackage.PROPERTY_DECORATOR__HIDDEN:
				return isSetHidden();
			case BeaninfoPackage.PROPERTY_DECORATOR__PREFERRED:
				return isSetPreferred();
			case BeaninfoPackage.PROPERTY_DECORATOR__MERGE_INTROSPECTION:
				return mergeIntrospection != MERGE_INTROSPECTION_EDEFAULT;
			case BeaninfoPackage.PROPERTY_DECORATOR__ATTRIBUTES_EXPLICIT_EMPTY:
				return attributesExplicitEmpty != ATTRIBUTES_EXPLICIT_EMPTY_EDEFAULT;
			case BeaninfoPackage.PROPERTY_DECORATOR__IMPLICITLY_SET_BITS:
				return implicitlySetBits != IMPLICITLY_SET_BITS_EDEFAULT;
			case BeaninfoPackage.PROPERTY_DECORATOR__IMPLICIT_DECORATOR_FLAG:
				return implicitDecoratorFlag != IMPLICIT_DECORATOR_FLAG_EDEFAULT;
			case BeaninfoPackage.PROPERTY_DECORATOR__ATTRIBUTES:
				return attributes != null && !attributes.isEmpty();
			case BeaninfoPackage.PROPERTY_DECORATOR__BOUND:
				return isSetBound();
			case BeaninfoPackage.PROPERTY_DECORATOR__CONSTRAINED:
				return isSetConstrained();
			case BeaninfoPackage.PROPERTY_DECORATOR__DESIGN_TIME:
				return isSetDesignTime();
			case BeaninfoPackage.PROPERTY_DECORATOR__ALWAYS_INCOMPATIBLE:
				return alwaysIncompatible != ALWAYS_INCOMPATIBLE_EDEFAULT;
			case BeaninfoPackage.PROPERTY_DECORATOR__FILTER_FLAGS:
				return filterFlags != null && !filterFlags.isEmpty();
			case BeaninfoPackage.PROPERTY_DECORATOR__FIELD_READ_ONLY:
				return fieldReadOnly != FIELD_READ_ONLY_EDEFAULT;
			case BeaninfoPackage.PROPERTY_DECORATOR__PROPERTY_EDITOR_CLASS:
				return propertyEditorClass != null;
			case BeaninfoPackage.PROPERTY_DECORATOR__READ_METHOD:
				return isSetReadMethod();
			case BeaninfoPackage.PROPERTY_DECORATOR__WRITE_METHOD:
				return isSetWriteMethod();
			case BeaninfoPackage.PROPERTY_DECORATOR__FIELD:
				return isSetField();
		}
		return eDynamicIsSet(eFeature);
	}

	/* (non-Javadoc)
	 * @see org.eclipse.jem.internal.beaninfo.PropertyDecorator#isWriteable()
	 */
	public boolean isWriteable() {
		return !(getWriteMethod() == null && getField() == null);
	}
	
	/* (non-Javadoc)
	 * @see org.eclipse.jem.internal.beaninfo.PropertyDecorator#isReadable()
	 */
	public boolean isReadable() {
		return !(getReadMethod() == null && getField() == null);
	}
	

}
