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
package org.eclipse.jem.java.impl;
/*
 *  $RCSfile: ArrayTypeImpl.java,v $
 *  $Revision: 1.6 $  $Date: 2005/02/15 22:37:02 $ 
 */

import java.util.Collection;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EClassifier;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.EcorePackage;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.util.InternalEList;

import org.eclipse.jem.java.*;
import org.eclipse.jem.java.ArrayType;
import org.eclipse.jem.java.JavaClass;
import org.eclipse.jem.java.JavaHelpers;
import org.eclipse.jem.java.JavaRefPackage;
import org.eclipse.jem.java.TypeKind;

/**
 * Describes a Java Array type
 *    For multi-dimensional arrays, it is unlikely that the component type will be
 *    specified directly.  This would require instantiating a chain of component types
 *    such as String[][][][]->String[][][]->String[][]->String[]->String.
 * 
 *   The component type relationship will be computed if the finalComponentType
 *   and array dimensions is specified.
 *  
 *   For this reason, the preferred way to create is through the JavaRefFactory factory method:
 *        createArrayType(JavaClass finalComponentType, int dimensions)
 */
public class ArrayTypeImpl extends JavaClassImpl implements ArrayType, JavaClass{

	/**
	 * The default value of the '{@link #getArrayDimensions() <em>Array Dimensions</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getArrayDimensions()
	 * @generated
	 * @ordered
	 */
	protected static final int ARRAY_DIMENSIONS_EDEFAULT = 0;

	
	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	protected int arrayDimensions = ARRAY_DIMENSIONS_EDEFAULT;
	/**
	 * The cached value of the '{@link #getComponentType() <em>Component Type</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getComponentType()
	 * @generated
	 * @ordered
	 */
	protected EClassifier componentType = null;
	
	protected JavaHelpers finalComponentType = null;

	protected ArrayTypeImpl() {
		super();
	}
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected EClass eStaticClass() {
		return JavaRefPackage.eINSTANCE.getArrayType();
	}

	/**
	 * Compute the component type for this array type from our type name.
	 * The component type of this array type is essentially: new ArrayTypeImpl(finalComponentType, arrayDimensions-1)
	 * unless our array dimension is 1, in which case it is only our final component type.
	 *
	 * In order to ensure a unique instance, we will resolve this type using reflection.
	 * "java.lang.String[][]" component type is "java.lang.String[]"
	 *
	 */
	public JavaHelpers computeComponentType() {
		String componentName = getQualifiedNameForReflection();
		// Strip the last [] form my name to get my component type's name
		componentName = componentName.substring(0, componentName.length() - 2);
		return JavaRefFactory.eINSTANCE.reflectType(componentName, this);
	}
	/**
	 * Override to perform some lazy initialization
	 */
	public EClassifier getComponentType() {
		// If we do not have a component type set, but we have a name (which contains our component type name)
		// we can compute the component type.
		if ((this.getComponentTypeGen() == null) && (this.getName() != null)) {
			componentType = computeComponentType();
		}
		return getComponentTypeGen();
	}
	/**
	 * Get the component type of this array. 
	 * 
	 * If this is a multi-dimensional array, the component type will be the nested array type.
	 */
	public JavaHelpers getComponentTypeAsHelper() {
		return (JavaHelpers) getComponentType();
	}
	/**
	 * Get the final component type for this Array Type.
	 * 
	 * In order to ensure a unique instance, we will resolve this type using reflection. It turns out to be most efficient to just do this by trimming the name.
	 */
	public JavaHelpers getFinalComponentType() {
		if (finalComponentType == null) {
			String componentName = getQualifiedNameForReflection();
			// Strip all the [] from my name to get my FINAL component type's name
			componentName = componentName.substring(0, componentName.indexOf("["));
			finalComponentType = JavaRefFactory.eINSTANCE.reflectType(componentName, this);
		}
		return finalComponentType;
	}
	/**
	 * (JavaHelpers)isArray - ArrayTypes are arrays
	 * Override from JavaClass.
	 */    
	public boolean isArray() {
		return true;
	}
	/**
	 * Is this an array of java primitives
	 */
	public boolean isPrimitiveArray() {
		return getFinalComponentType().isPrimitive();
	}
	/**
	 * Set the component type.
	 */
	public void setComponentType(JavaHelpers helperComponentType) {
		setComponentType((EClassifier) helperComponentType);		
	}
	/**
	 * @generated This field/method will be replaced during code generation 
	 */
	public int getArrayDimensions() {
		return arrayDimensions;
	}

	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	public void setArrayDimensions(int newArrayDimensions) {
		int oldArrayDimensions = arrayDimensions;
		arrayDimensions = newArrayDimensions;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, JavaRefPackage.ARRAY_TYPE__ARRAY_DIMENSIONS, oldArrayDimensions, arrayDimensions));
	}

	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	public boolean eIsSet(EStructuralFeature eFeature) {
		switch (eDerivedStructuralFeatureID(eFeature)) {
			case JavaRefPackage.ARRAY_TYPE__EANNOTATIONS:
				return eAnnotations != null && !eAnnotations.isEmpty();
			case JavaRefPackage.ARRAY_TYPE__NAME:
				return NAME_EDEFAULT == null ? name != null : !NAME_EDEFAULT.equals(name);
			case JavaRefPackage.ARRAY_TYPE__INSTANCE_CLASS_NAME:
				return INSTANCE_CLASS_NAME_EDEFAULT == null ? instanceClassName != null : !INSTANCE_CLASS_NAME_EDEFAULT.equals(instanceClassName);
			case JavaRefPackage.ARRAY_TYPE__INSTANCE_CLASS:
				return getInstanceClass() != null;
			case JavaRefPackage.ARRAY_TYPE__DEFAULT_VALUE:
				return getDefaultValue() != null;
			case JavaRefPackage.ARRAY_TYPE__EPACKAGE:
				return getEPackage() != null;
			case JavaRefPackage.ARRAY_TYPE__ABSTRACT:
				return abstract_ != ABSTRACT_EDEFAULT;
			case JavaRefPackage.ARRAY_TYPE__INTERFACE:
				return interface_ != INTERFACE_EDEFAULT;
			case JavaRefPackage.ARRAY_TYPE__ESUPER_TYPES:
				return eSuperTypes != null && !eSuperTypes.isEmpty();
			case JavaRefPackage.ARRAY_TYPE__EOPERATIONS:
				return eOperations != null && !eOperations.isEmpty();
			case JavaRefPackage.ARRAY_TYPE__EALL_ATTRIBUTES:
				return !getEAllAttributes().isEmpty();
			case JavaRefPackage.ARRAY_TYPE__EALL_REFERENCES:
				return !getEAllReferences().isEmpty();
			case JavaRefPackage.ARRAY_TYPE__EREFERENCES:
				return !getEReferences().isEmpty();
			case JavaRefPackage.ARRAY_TYPE__EATTRIBUTES:
				return !getEAttributes().isEmpty();
			case JavaRefPackage.ARRAY_TYPE__EALL_CONTAINMENTS:
				return !getEAllContainments().isEmpty();
			case JavaRefPackage.ARRAY_TYPE__EALL_OPERATIONS:
				return !getEAllOperations().isEmpty();
			case JavaRefPackage.ARRAY_TYPE__EALL_STRUCTURAL_FEATURES:
				return !getEAllStructuralFeatures().isEmpty();
			case JavaRefPackage.ARRAY_TYPE__EALL_SUPER_TYPES:
				return !getEAllSuperTypes().isEmpty();
			case JavaRefPackage.ARRAY_TYPE__EID_ATTRIBUTE:
				return getEIDAttribute() != null;
			case JavaRefPackage.ARRAY_TYPE__ESTRUCTURAL_FEATURES:
				return eStructuralFeatures != null && !eStructuralFeatures.isEmpty();
			case JavaRefPackage.ARRAY_TYPE__KIND:
				return kind != KIND_EDEFAULT;
			case JavaRefPackage.ARRAY_TYPE__PUBLIC:
				return public_ != PUBLIC_EDEFAULT;
			case JavaRefPackage.ARRAY_TYPE__FINAL:
				return final_ != FINAL_EDEFAULT;
			case JavaRefPackage.ARRAY_TYPE__IMPLEMENTS_INTERFACES:
				return implementsInterfaces != null && !implementsInterfaces.isEmpty();
			case JavaRefPackage.ARRAY_TYPE__CLASS_IMPORT:
				return classImport != null && !classImport.isEmpty();
			case JavaRefPackage.ARRAY_TYPE__PACKAGE_IMPORTS:
				return packageImports != null && !packageImports.isEmpty();
			case JavaRefPackage.ARRAY_TYPE__FIELDS:
				return fields != null && !fields.isEmpty();
			case JavaRefPackage.ARRAY_TYPE__METHODS:
				return methods != null && !methods.isEmpty();
			case JavaRefPackage.ARRAY_TYPE__INITIALIZERS:
				return initializers != null && !initializers.isEmpty();
			case JavaRefPackage.ARRAY_TYPE__DECLARED_CLASSES:
				return declaredClasses != null && !declaredClasses.isEmpty();
			case JavaRefPackage.ARRAY_TYPE__DECLARING_CLASS:
				return declaringClass != null;
			case JavaRefPackage.ARRAY_TYPE__JAVA_PACKAGE:
				return basicGetJavaPackage() != null;
			case JavaRefPackage.ARRAY_TYPE__EVENTS:
				return events != null && !events.isEmpty();
			case JavaRefPackage.ARRAY_TYPE__ALL_EVENTS:
				return !getAllEvents().isEmpty();
			case JavaRefPackage.ARRAY_TYPE__ARRAY_DIMENSIONS:
				return arrayDimensions != ARRAY_DIMENSIONS_EDEFAULT;
			case JavaRefPackage.ARRAY_TYPE__COMPONENT_TYPE:
				return componentType != null;
		}
		return eDynamicIsSet(eFeature);
	}

	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	public void eSet(EStructuralFeature eFeature, Object newValue) {
		switch (eDerivedStructuralFeatureID(eFeature)) {
			case JavaRefPackage.ARRAY_TYPE__EANNOTATIONS:
				getEAnnotations().clear();
				getEAnnotations().addAll((Collection)newValue);
				return;
			case JavaRefPackage.ARRAY_TYPE__NAME:
				setName((String)newValue);
				return;
			case JavaRefPackage.ARRAY_TYPE__INSTANCE_CLASS_NAME:
				setInstanceClassName((String)newValue);
				return;
			case JavaRefPackage.ARRAY_TYPE__ABSTRACT:
				setAbstract(((Boolean)newValue).booleanValue());
				return;
			case JavaRefPackage.ARRAY_TYPE__INTERFACE:
				setInterface(((Boolean)newValue).booleanValue());
				return;
			case JavaRefPackage.ARRAY_TYPE__ESUPER_TYPES:
				getESuperTypes().clear();
				getESuperTypes().addAll((Collection)newValue);
				return;
			case JavaRefPackage.ARRAY_TYPE__EOPERATIONS:
				getEOperations().clear();
				getEOperations().addAll((Collection)newValue);
				return;
			case JavaRefPackage.ARRAY_TYPE__ESTRUCTURAL_FEATURES:
				getEStructuralFeatures().clear();
				getEStructuralFeatures().addAll((Collection)newValue);
				return;
			case JavaRefPackage.ARRAY_TYPE__KIND:
				setKind((TypeKind)newValue);
				return;
			case JavaRefPackage.ARRAY_TYPE__PUBLIC:
				setPublic(((Boolean)newValue).booleanValue());
				return;
			case JavaRefPackage.ARRAY_TYPE__FINAL:
				setFinal(((Boolean)newValue).booleanValue());
				return;
			case JavaRefPackage.ARRAY_TYPE__IMPLEMENTS_INTERFACES:
				getImplementsInterfaces().clear();
				getImplementsInterfaces().addAll((Collection)newValue);
				return;
			case JavaRefPackage.ARRAY_TYPE__CLASS_IMPORT:
				getClassImport().clear();
				getClassImport().addAll((Collection)newValue);
				return;
			case JavaRefPackage.ARRAY_TYPE__PACKAGE_IMPORTS:
				getPackageImports().clear();
				getPackageImports().addAll((Collection)newValue);
				return;
			case JavaRefPackage.ARRAY_TYPE__FIELDS:
				getFields().clear();
				getFields().addAll((Collection)newValue);
				return;
			case JavaRefPackage.ARRAY_TYPE__METHODS:
				getMethods().clear();
				getMethods().addAll((Collection)newValue);
				return;
			case JavaRefPackage.ARRAY_TYPE__INITIALIZERS:
				getInitializers().clear();
				getInitializers().addAll((Collection)newValue);
				return;
			case JavaRefPackage.ARRAY_TYPE__DECLARED_CLASSES:
				getDeclaredClasses().clear();
				getDeclaredClasses().addAll((Collection)newValue);
				return;
			case JavaRefPackage.ARRAY_TYPE__DECLARING_CLASS:
				setDeclaringClass((JavaClass)newValue);
				return;
			case JavaRefPackage.ARRAY_TYPE__EVENTS:
				getEvents().clear();
				getEvents().addAll((Collection)newValue);
				return;
			case JavaRefPackage.ARRAY_TYPE__ALL_EVENTS:
				getAllEvents().clear();
				getAllEvents().addAll((Collection)newValue);
				return;
			case JavaRefPackage.ARRAY_TYPE__ARRAY_DIMENSIONS:
				setArrayDimensions(((Integer)newValue).intValue());
				return;
			case JavaRefPackage.ARRAY_TYPE__COMPONENT_TYPE:
				setComponentType((EClassifier)newValue);
				return;
		}
		eDynamicSet(eFeature, newValue);
	}

	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	public void eUnset(EStructuralFeature eFeature) {
		switch (eDerivedStructuralFeatureID(eFeature)) {
			case JavaRefPackage.ARRAY_TYPE__EANNOTATIONS:
				getEAnnotations().clear();
				return;
			case JavaRefPackage.ARRAY_TYPE__NAME:
				setName(NAME_EDEFAULT);
				return;
			case JavaRefPackage.ARRAY_TYPE__INSTANCE_CLASS_NAME:
				setInstanceClassName(INSTANCE_CLASS_NAME_EDEFAULT);
				return;
			case JavaRefPackage.ARRAY_TYPE__ABSTRACT:
				setAbstract(ABSTRACT_EDEFAULT);
				return;
			case JavaRefPackage.ARRAY_TYPE__INTERFACE:
				setInterface(INTERFACE_EDEFAULT);
				return;
			case JavaRefPackage.ARRAY_TYPE__ESUPER_TYPES:
				getESuperTypes().clear();
				return;
			case JavaRefPackage.ARRAY_TYPE__EOPERATIONS:
				getEOperations().clear();
				return;
			case JavaRefPackage.ARRAY_TYPE__ESTRUCTURAL_FEATURES:
				getEStructuralFeatures().clear();
				return;
			case JavaRefPackage.ARRAY_TYPE__KIND:
				setKind(KIND_EDEFAULT);
				return;
			case JavaRefPackage.ARRAY_TYPE__PUBLIC:
				setPublic(PUBLIC_EDEFAULT);
				return;
			case JavaRefPackage.ARRAY_TYPE__FINAL:
				setFinal(FINAL_EDEFAULT);
				return;
			case JavaRefPackage.ARRAY_TYPE__IMPLEMENTS_INTERFACES:
				getImplementsInterfaces().clear();
				return;
			case JavaRefPackage.ARRAY_TYPE__CLASS_IMPORT:
				getClassImport().clear();
				return;
			case JavaRefPackage.ARRAY_TYPE__PACKAGE_IMPORTS:
				getPackageImports().clear();
				return;
			case JavaRefPackage.ARRAY_TYPE__FIELDS:
				getFields().clear();
				return;
			case JavaRefPackage.ARRAY_TYPE__METHODS:
				getMethods().clear();
				return;
			case JavaRefPackage.ARRAY_TYPE__INITIALIZERS:
				getInitializers().clear();
				return;
			case JavaRefPackage.ARRAY_TYPE__DECLARED_CLASSES:
				getDeclaredClasses().clear();
				return;
			case JavaRefPackage.ARRAY_TYPE__DECLARING_CLASS:
				setDeclaringClass((JavaClass)null);
				return;
			case JavaRefPackage.ARRAY_TYPE__EVENTS:
				getEvents().clear();
				return;
			case JavaRefPackage.ARRAY_TYPE__ALL_EVENTS:
				getAllEvents().clear();
				return;
			case JavaRefPackage.ARRAY_TYPE__ARRAY_DIMENSIONS:
				setArrayDimensions(ARRAY_DIMENSIONS_EDEFAULT);
				return;
			case JavaRefPackage.ARRAY_TYPE__COMPONENT_TYPE:
				setComponentType((EClassifier)null);
				return;
		}
		eDynamicUnset(eFeature);
	}

	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	public String toString() {
		if (eIsProxy()) return super.toString();

		StringBuffer result = new StringBuffer(super.toString());
		result.append(" (arrayDimensions: ");
		result.append(arrayDimensions);
		result.append(')');
		return result.toString();
	}

	/**
	 * @generated This field/method will be replaced during code generation 
	 */
	public EClassifier getComponentTypeGen() {
		if (componentType != null && componentType.eIsProxy()) {
			EClassifier oldComponentType = componentType;
			componentType = (EClassifier)eResolveProxy((InternalEObject)componentType);
			if (componentType != oldComponentType) {
				if (eNotificationRequired())
					eNotify(new ENotificationImpl(this, Notification.RESOLVE, JavaRefPackage.ARRAY_TYPE__COMPONENT_TYPE, oldComponentType, componentType));
			}
		}
		return componentType;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClassifier basicGetComponentType() {
		return componentType;
	}

	public void setComponentType(EClassifier newComponentType) {
		finalComponentType = null;
		setComponentTypeGen(newComponentType);
	}
	
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setComponentTypeGen(EClassifier newComponentType) {
		EClassifier oldComponentType = componentType;
		componentType = newComponentType;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, JavaRefPackage.ARRAY_TYPE__COMPONENT_TYPE, oldComponentType, componentType));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain eInverseAdd(InternalEObject otherEnd, int featureID, Class baseClass, NotificationChain msgs) {
		if (featureID >= 0) {
			switch (eDerivedStructuralFeatureID(featureID, baseClass)) {
				case JavaRefPackage.ARRAY_TYPE__EANNOTATIONS:
					return ((InternalEList)getEAnnotations()).basicAdd(otherEnd, msgs);
				case JavaRefPackage.ARRAY_TYPE__EPACKAGE:
					if (eContainer != null)
						msgs = eBasicRemoveFromContainer(msgs);
					return eBasicSetContainer(otherEnd, JavaRefPackage.ARRAY_TYPE__EPACKAGE, msgs);
				case JavaRefPackage.ARRAY_TYPE__EOPERATIONS:
					return ((InternalEList)getEOperations()).basicAdd(otherEnd, msgs);
				case JavaRefPackage.ARRAY_TYPE__ESTRUCTURAL_FEATURES:
					return ((InternalEList)getEStructuralFeatures()).basicAdd(otherEnd, msgs);
				case JavaRefPackage.ARRAY_TYPE__FIELDS:
					return ((InternalEList)getFields()).basicAdd(otherEnd, msgs);
				case JavaRefPackage.ARRAY_TYPE__METHODS:
					return ((InternalEList)getMethods()).basicAdd(otherEnd, msgs);
				case JavaRefPackage.ARRAY_TYPE__INITIALIZERS:
					return ((InternalEList)getInitializers()).basicAdd(otherEnd, msgs);
				case JavaRefPackage.ARRAY_TYPE__DECLARED_CLASSES:
					return ((InternalEList)getDeclaredClasses()).basicAdd(otherEnd, msgs);
				case JavaRefPackage.ARRAY_TYPE__DECLARING_CLASS:
					if (declaringClass != null)
						msgs = ((InternalEObject)declaringClass).eInverseRemove(this, JavaRefPackage.JAVA_CLASS__DECLARED_CLASSES, JavaClass.class, msgs);
					return basicSetDeclaringClass((JavaClass)otherEnd, msgs);
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
				case JavaRefPackage.ARRAY_TYPE__EANNOTATIONS:
					return ((InternalEList)getEAnnotations()).basicRemove(otherEnd, msgs);
				case JavaRefPackage.ARRAY_TYPE__EPACKAGE:
					return eBasicSetContainer(null, JavaRefPackage.ARRAY_TYPE__EPACKAGE, msgs);
				case JavaRefPackage.ARRAY_TYPE__EOPERATIONS:
					return ((InternalEList)getEOperations()).basicRemove(otherEnd, msgs);
				case JavaRefPackage.ARRAY_TYPE__ESTRUCTURAL_FEATURES:
					return ((InternalEList)getEStructuralFeatures()).basicRemove(otherEnd, msgs);
				case JavaRefPackage.ARRAY_TYPE__FIELDS:
					return ((InternalEList)getFields()).basicRemove(otherEnd, msgs);
				case JavaRefPackage.ARRAY_TYPE__METHODS:
					return ((InternalEList)getMethods()).basicRemove(otherEnd, msgs);
				case JavaRefPackage.ARRAY_TYPE__INITIALIZERS:
					return ((InternalEList)getInitializers()).basicRemove(otherEnd, msgs);
				case JavaRefPackage.ARRAY_TYPE__DECLARED_CLASSES:
					return ((InternalEList)getDeclaredClasses()).basicRemove(otherEnd, msgs);
				case JavaRefPackage.ARRAY_TYPE__DECLARING_CLASS:
					return basicSetDeclaringClass(null, msgs);
				case JavaRefPackage.ARRAY_TYPE__EVENTS:
					return ((InternalEList)getEvents()).basicRemove(otherEnd, msgs);
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
				case JavaRefPackage.ARRAY_TYPE__EPACKAGE:
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
	public Object eGet(EStructuralFeature eFeature, boolean resolve) {
		switch (eDerivedStructuralFeatureID(eFeature)) {
			case JavaRefPackage.ARRAY_TYPE__EANNOTATIONS:
				return getEAnnotations();
			case JavaRefPackage.ARRAY_TYPE__NAME:
				return getName();
			case JavaRefPackage.ARRAY_TYPE__INSTANCE_CLASS_NAME:
				return getInstanceClassName();
			case JavaRefPackage.ARRAY_TYPE__INSTANCE_CLASS:
				return getInstanceClass();
			case JavaRefPackage.ARRAY_TYPE__DEFAULT_VALUE:
				return getDefaultValue();
			case JavaRefPackage.ARRAY_TYPE__EPACKAGE:
				return getEPackage();
			case JavaRefPackage.ARRAY_TYPE__ABSTRACT:
				return isAbstract() ? Boolean.TRUE : Boolean.FALSE;
			case JavaRefPackage.ARRAY_TYPE__INTERFACE:
				return isInterface() ? Boolean.TRUE : Boolean.FALSE;
			case JavaRefPackage.ARRAY_TYPE__ESUPER_TYPES:
				return getESuperTypes();
			case JavaRefPackage.ARRAY_TYPE__EOPERATIONS:
				return getEOperations();
			case JavaRefPackage.ARRAY_TYPE__EALL_ATTRIBUTES:
				return getEAllAttributes();
			case JavaRefPackage.ARRAY_TYPE__EALL_REFERENCES:
				return getEAllReferences();
			case JavaRefPackage.ARRAY_TYPE__EREFERENCES:
				return getEReferences();
			case JavaRefPackage.ARRAY_TYPE__EATTRIBUTES:
				return getEAttributes();
			case JavaRefPackage.ARRAY_TYPE__EALL_CONTAINMENTS:
				return getEAllContainments();
			case JavaRefPackage.ARRAY_TYPE__EALL_OPERATIONS:
				return getEAllOperations();
			case JavaRefPackage.ARRAY_TYPE__EALL_STRUCTURAL_FEATURES:
				return getEAllStructuralFeatures();
			case JavaRefPackage.ARRAY_TYPE__EALL_SUPER_TYPES:
				return getEAllSuperTypes();
			case JavaRefPackage.ARRAY_TYPE__EID_ATTRIBUTE:
				return getEIDAttribute();
			case JavaRefPackage.ARRAY_TYPE__ESTRUCTURAL_FEATURES:
				return getEStructuralFeatures();
			case JavaRefPackage.ARRAY_TYPE__KIND:
				return getKind();
			case JavaRefPackage.ARRAY_TYPE__PUBLIC:
				return isPublic() ? Boolean.TRUE : Boolean.FALSE;
			case JavaRefPackage.ARRAY_TYPE__FINAL:
				return isFinal() ? Boolean.TRUE : Boolean.FALSE;
			case JavaRefPackage.ARRAY_TYPE__IMPLEMENTS_INTERFACES:
				return getImplementsInterfaces();
			case JavaRefPackage.ARRAY_TYPE__CLASS_IMPORT:
				return getClassImport();
			case JavaRefPackage.ARRAY_TYPE__PACKAGE_IMPORTS:
				return getPackageImports();
			case JavaRefPackage.ARRAY_TYPE__FIELDS:
				return getFields();
			case JavaRefPackage.ARRAY_TYPE__METHODS:
				return getMethods();
			case JavaRefPackage.ARRAY_TYPE__INITIALIZERS:
				return getInitializers();
			case JavaRefPackage.ARRAY_TYPE__DECLARED_CLASSES:
				return getDeclaredClasses();
			case JavaRefPackage.ARRAY_TYPE__DECLARING_CLASS:
				if (resolve) return getDeclaringClass();
				return basicGetDeclaringClass();
			case JavaRefPackage.ARRAY_TYPE__JAVA_PACKAGE:
				if (resolve) return getJavaPackage();
				return basicGetJavaPackage();
			case JavaRefPackage.ARRAY_TYPE__EVENTS:
				return getEvents();
			case JavaRefPackage.ARRAY_TYPE__ALL_EVENTS:
				return getAllEvents();
			case JavaRefPackage.ARRAY_TYPE__ARRAY_DIMENSIONS:
				return new Integer(getArrayDimensions());
			case JavaRefPackage.ARRAY_TYPE__COMPONENT_TYPE:
				if (resolve) return getComponentType();
				return basicGetComponentType();
		}
		return eDynamicGet(eFeature, resolve);
	}

	/* (non-Javadoc)
	 * @see org.eclipse.jem.java.JavaClass#getKind()
	 */
	public TypeKind getKind() {
		// Override to always return the class if final type is valid.
		JavaHelpers ft = getFinalComponentType();
		if (!ft.isPrimitive()) {
			TypeKind ftKind = ((JavaClass) ft).getKind(); 
			return  ftKind != TypeKind.UNDEFINED_LITERAL ? TypeKind.CLASS_LITERAL : TypeKind.UNDEFINED_LITERAL;
		} else
			return TypeKind.CLASS_LITERAL;
	}
	
	
	/* (non-Javadoc)
	 * @see org.eclipse.jem.java.JavaClass#isPublic()
	 */
	public boolean isPublic() {
		// Override to return the kind of the final component, because that determines it.
		JavaHelpers ft = getFinalComponentType();
		if (!ft.isPrimitive()) {
			return ((JavaClass) ft).isPublic();
		} else
			return true;
	}
}





