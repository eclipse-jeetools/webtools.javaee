package org.eclipse.jem.internal.java.impl;
/*******************************************************************************
 * Copyright (c)  2001, 2003 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials 
 * are made available under the terms of the Common Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/cpl-v10.html
 * 
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *******************************************************************************/
/*
 *  $RCSfile: JavaDataTypeImpl.java,v $
 *  $Revision: 1.1.4.1 $  $Date: 2003/12/16 19:29:35 $ 
 */

import java.util.Collection;

import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EClassifier;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.EcorePackage;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.EClassImpl;
import org.eclipse.emf.ecore.util.InternalEList;

import org.eclipse.jem.internal.java.JavaClass;
import org.eclipse.jem.internal.java.JavaDataType;
import org.eclipse.jem.internal.java.JavaRefPackage;
import org.eclipse.jem.internal.java.instantiation.IInstantiationInstance;
/**
 * @generated
 */
public class JavaDataTypeImpl extends EClassImpl implements JavaDataType{

	
	static final String FALSE = "false";
	static final String DOUBLE_ZERO = "0.0";
	static final String FLOAT_ZERO = "0.0f";
	static final String CHAR_ZERO = "'0'";
	static final String ZERO = "0";
	protected JavaDataTypeImpl() {
		super();
	}
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected EClass eStaticClass()
	{
		return JavaRefPackage.eINSTANCE.getJavaDataType();
	}

	/**
	 * Return the default string representing the default value of the primitive.
	 */
	public String getDefaultValueString() {
		String typeName = getJavaName();
		if (typeName.equals(Boolean.TYPE.getName()))
			return FALSE;
		if (typeName.equals(Double.TYPE.getName()))
			return DOUBLE_ZERO;
		if (typeName.equals(Float.TYPE.getName()))
			return FLOAT_ZERO;			
		if (typeName.equals(Character.TYPE.getName()))
			return CHAR_ZERO;
		return ZERO;
	}
	/**
	 * (JavaHelpers)javaName - return the String which represents the Java name for this type
	 */
	public String getJavaName() {
		return getName();
	}
	public JavaDataType getPrimitive() {
		return this;
	}
	public String getQualifiedName() {
		return getJavaName();
	}
	public JavaClass getWrapper() {
		String wrapperName = getWrapperQualifiedName();
		if (wrapperName != null) {
			return (JavaClass) JavaClassImpl.reflect(wrapperName, this);
		}
		return null;
	}
	/**
	 * getWrapper method comment.
	 */
	protected String getWrapperQualifiedName() {
		String myName = getJavaName();
		if (myName.equals(PRIM_INTEGER_NAME))
			return INTEGER_NAME;
		if(myName.equals(PRIM_CHARACTER_NAME))
			return CHARACTER_NAME;
		if(myName.equals(PRIM_BOOLEAN_NAME))
			return BOOLEAN_NAME;
		if(myName.equals(PRIM_BYTE_NAME))
			return BYTE_NAME;
		if(myName.equals(PRIM_SHORT_NAME))
			return SHORT_NAME;
		if(myName.equals(PRIM_LONG_NAME))
			return LONG_NAME;
		if(myName.equals(PRIM_FLOAT_NAME))
			return FLOAT_NAME;
		if(myName.equals(PRIM_DOUBLE_NAME))
			return DOUBLE_NAME;
		return null;
	}
	/*
	 * JavaHelpers.isArray() - array types are always JavaClasses, even if their component type is
	 *		a primitive data type. Return false.
	 */
	public boolean isArray() {
		return false;
	}
	/**
	 * Can an object of the passed in class be assigned to an
	 * object of this class? In the case of primitives, are they the same.
	 */
	public boolean isAssignableFrom(EClassifier aClass) {
		return this == aClass;
	}
	/**
	 * See if this is valid object of this type.
	 */
	public boolean isInstance(Object o) {
		return o instanceof IInstantiationInstance ? isAssignableFrom(((IInstantiationInstance) o).getJavaType()) : false;
	}
	public boolean isPrimitive() {
		return true;
	}
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain eInverseAdd(InternalEObject otherEnd, int featureID, Class baseClass, NotificationChain msgs)
	{
		if (featureID >= 0) {
			switch (eDerivedStructuralFeatureID(featureID, baseClass)) {
				case JavaRefPackage.JAVA_DATA_TYPE__EANNOTATIONS:
					return ((InternalEList)getEAnnotations()).basicAdd(otherEnd, msgs);
				case JavaRefPackage.JAVA_DATA_TYPE__EPACKAGE:
					if (eContainer != null)
						msgs = eBasicRemoveFromContainer(msgs);
					return eBasicSetContainer(otherEnd, JavaRefPackage.JAVA_DATA_TYPE__EPACKAGE, msgs);
				case JavaRefPackage.JAVA_DATA_TYPE__EOPERATIONS:
					return ((InternalEList)getEOperations()).basicAdd(otherEnd, msgs);
				case JavaRefPackage.JAVA_DATA_TYPE__ESTRUCTURAL_FEATURES:
					return ((InternalEList)getEStructuralFeatures()).basicAdd(otherEnd, msgs);
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
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, Class baseClass, NotificationChain msgs)
	{
		if (featureID >= 0) {
			switch (eDerivedStructuralFeatureID(featureID, baseClass)) {
				case JavaRefPackage.JAVA_DATA_TYPE__EANNOTATIONS:
					return ((InternalEList)getEAnnotations()).basicRemove(otherEnd, msgs);
				case JavaRefPackage.JAVA_DATA_TYPE__EPACKAGE:
					return eBasicSetContainer(null, JavaRefPackage.JAVA_DATA_TYPE__EPACKAGE, msgs);
				case JavaRefPackage.JAVA_DATA_TYPE__EOPERATIONS:
					return ((InternalEList)getEOperations()).basicRemove(otherEnd, msgs);
				case JavaRefPackage.JAVA_DATA_TYPE__ESTRUCTURAL_FEATURES:
					return ((InternalEList)getEStructuralFeatures()).basicRemove(otherEnd, msgs);
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
	public NotificationChain eBasicRemoveFromContainer(NotificationChain msgs)
	{
		if (eContainerFeatureID >= 0) {
			switch (eContainerFeatureID) {
				case JavaRefPackage.JAVA_DATA_TYPE__EPACKAGE:
					return ((InternalEObject)eContainer).eInverseRemove(this, EcorePackage.EPACKAGE__ECLASSIFIERS, EPackage.class, msgs);
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
	public Object eGet(EStructuralFeature eFeature, boolean resolve)
	{
		switch (eDerivedStructuralFeatureID(eFeature)) {
			case JavaRefPackage.JAVA_DATA_TYPE__EANNOTATIONS:
				return getEAnnotations();
			case JavaRefPackage.JAVA_DATA_TYPE__NAME:
				return getName();
			case JavaRefPackage.JAVA_DATA_TYPE__INSTANCE_CLASS_NAME:
				return getInstanceClassName();
			case JavaRefPackage.JAVA_DATA_TYPE__INSTANCE_CLASS:
				return getInstanceClass();
			case JavaRefPackage.JAVA_DATA_TYPE__DEFAULT_VALUE:
				return getDefaultValue();
			case JavaRefPackage.JAVA_DATA_TYPE__EPACKAGE:
				return getEPackage();
			case JavaRefPackage.JAVA_DATA_TYPE__ABSTRACT:
				return isAbstract() ? Boolean.TRUE : Boolean.FALSE;
			case JavaRefPackage.JAVA_DATA_TYPE__INTERFACE:
				return isInterface() ? Boolean.TRUE : Boolean.FALSE;
			case JavaRefPackage.JAVA_DATA_TYPE__ESUPER_TYPES:
				return getESuperTypes();
			case JavaRefPackage.JAVA_DATA_TYPE__EOPERATIONS:
				return getEOperations();
			case JavaRefPackage.JAVA_DATA_TYPE__EALL_ATTRIBUTES:
				return getEAllAttributes();
			case JavaRefPackage.JAVA_DATA_TYPE__EALL_REFERENCES:
				return getEAllReferences();
			case JavaRefPackage.JAVA_DATA_TYPE__EREFERENCES:
				return getEReferences();
			case JavaRefPackage.JAVA_DATA_TYPE__EATTRIBUTES:
				return getEAttributes();
			case JavaRefPackage.JAVA_DATA_TYPE__EALL_CONTAINMENTS:
				return getEAllContainments();
			case JavaRefPackage.JAVA_DATA_TYPE__EALL_OPERATIONS:
				return getEAllOperations();
			case JavaRefPackage.JAVA_DATA_TYPE__EALL_STRUCTURAL_FEATURES:
				return getEAllStructuralFeatures();
			case JavaRefPackage.JAVA_DATA_TYPE__EALL_SUPER_TYPES:
				return getEAllSuperTypes();
			case JavaRefPackage.JAVA_DATA_TYPE__EID_ATTRIBUTE:
				return getEIDAttribute();
			case JavaRefPackage.JAVA_DATA_TYPE__ESTRUCTURAL_FEATURES:
				return getEStructuralFeatures();
		}
		return eDynamicGet(eFeature, resolve);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void eSet(EStructuralFeature eFeature, Object newValue)
	{
		switch (eDerivedStructuralFeatureID(eFeature)) {
			case JavaRefPackage.JAVA_DATA_TYPE__EANNOTATIONS:
				getEAnnotations().clear();
				getEAnnotations().addAll((Collection)newValue);
				return;
			case JavaRefPackage.JAVA_DATA_TYPE__NAME:
				setName((String)newValue);
				return;
			case JavaRefPackage.JAVA_DATA_TYPE__INSTANCE_CLASS_NAME:
				setInstanceClassName((String)newValue);
				return;
			case JavaRefPackage.JAVA_DATA_TYPE__ABSTRACT:
				setAbstract(((Boolean)newValue).booleanValue());
				return;
			case JavaRefPackage.JAVA_DATA_TYPE__INTERFACE:
				setInterface(((Boolean)newValue).booleanValue());
				return;
			case JavaRefPackage.JAVA_DATA_TYPE__ESUPER_TYPES:
				getESuperTypes().clear();
				getESuperTypes().addAll((Collection)newValue);
				return;
			case JavaRefPackage.JAVA_DATA_TYPE__EOPERATIONS:
				getEOperations().clear();
				getEOperations().addAll((Collection)newValue);
				return;
			case JavaRefPackage.JAVA_DATA_TYPE__ESTRUCTURAL_FEATURES:
				getEStructuralFeatures().clear();
				getEStructuralFeatures().addAll((Collection)newValue);
				return;
		}
		eDynamicSet(eFeature, newValue);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void eUnset(EStructuralFeature eFeature)
	{
		switch (eDerivedStructuralFeatureID(eFeature)) {
			case JavaRefPackage.JAVA_DATA_TYPE__EANNOTATIONS:
				getEAnnotations().clear();
				return;
			case JavaRefPackage.JAVA_DATA_TYPE__NAME:
				setName(NAME_EDEFAULT);
				return;
			case JavaRefPackage.JAVA_DATA_TYPE__INSTANCE_CLASS_NAME:
				setInstanceClassName(INSTANCE_CLASS_NAME_EDEFAULT);
				return;
			case JavaRefPackage.JAVA_DATA_TYPE__ABSTRACT:
				setAbstract(ABSTRACT_EDEFAULT);
				return;
			case JavaRefPackage.JAVA_DATA_TYPE__INTERFACE:
				setInterface(INTERFACE_EDEFAULT);
				return;
			case JavaRefPackage.JAVA_DATA_TYPE__ESUPER_TYPES:
				getESuperTypes().clear();
				return;
			case JavaRefPackage.JAVA_DATA_TYPE__EOPERATIONS:
				getEOperations().clear();
				return;
			case JavaRefPackage.JAVA_DATA_TYPE__ESTRUCTURAL_FEATURES:
				getEStructuralFeatures().clear();
				return;
		}
		eDynamicUnset(eFeature);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean eIsSet(EStructuralFeature eFeature)
	{
		switch (eDerivedStructuralFeatureID(eFeature)) {
			case JavaRefPackage.JAVA_DATA_TYPE__EANNOTATIONS:
				return eAnnotations != null && !eAnnotations.isEmpty();
			case JavaRefPackage.JAVA_DATA_TYPE__NAME:
				return NAME_EDEFAULT == null ? name != null : !NAME_EDEFAULT.equals(name);
			case JavaRefPackage.JAVA_DATA_TYPE__INSTANCE_CLASS_NAME:
				return INSTANCE_CLASS_NAME_EDEFAULT == null ? instanceClassName != null : !INSTANCE_CLASS_NAME_EDEFAULT.equals(instanceClassName);
			case JavaRefPackage.JAVA_DATA_TYPE__INSTANCE_CLASS:
				return getInstanceClass() != null;
			case JavaRefPackage.JAVA_DATA_TYPE__DEFAULT_VALUE:
				return getDefaultValue() != null;
			case JavaRefPackage.JAVA_DATA_TYPE__EPACKAGE:
				return getEPackage() != null;
			case JavaRefPackage.JAVA_DATA_TYPE__ABSTRACT:
				return abstract_ != ABSTRACT_EDEFAULT;
			case JavaRefPackage.JAVA_DATA_TYPE__INTERFACE:
				return interface_ != INTERFACE_EDEFAULT;
			case JavaRefPackage.JAVA_DATA_TYPE__ESUPER_TYPES:
				return eSuperTypes != null && !eSuperTypes.isEmpty();
			case JavaRefPackage.JAVA_DATA_TYPE__EOPERATIONS:
				return eOperations != null && !eOperations.isEmpty();
			case JavaRefPackage.JAVA_DATA_TYPE__EALL_ATTRIBUTES:
				return !getEAllAttributes().isEmpty();
			case JavaRefPackage.JAVA_DATA_TYPE__EALL_REFERENCES:
				return !getEAllReferences().isEmpty();
			case JavaRefPackage.JAVA_DATA_TYPE__EREFERENCES:
				return !getEReferences().isEmpty();
			case JavaRefPackage.JAVA_DATA_TYPE__EATTRIBUTES:
				return !getEAttributes().isEmpty();
			case JavaRefPackage.JAVA_DATA_TYPE__EALL_CONTAINMENTS:
				return !getEAllContainments().isEmpty();
			case JavaRefPackage.JAVA_DATA_TYPE__EALL_OPERATIONS:
				return !getEAllOperations().isEmpty();
			case JavaRefPackage.JAVA_DATA_TYPE__EALL_STRUCTURAL_FEATURES:
				return !getEAllStructuralFeatures().isEmpty();
			case JavaRefPackage.JAVA_DATA_TYPE__EALL_SUPER_TYPES:
				return !getEAllSuperTypes().isEmpty();
			case JavaRefPackage.JAVA_DATA_TYPE__EID_ATTRIBUTE:
				return getEIDAttribute() != null;
			case JavaRefPackage.JAVA_DATA_TYPE__ESTRUCTURAL_FEATURES:
				return eStructuralFeatures != null && !eStructuralFeatures.isEmpty();
		}
		return eDynamicIsSet(eFeature);
	}

}





