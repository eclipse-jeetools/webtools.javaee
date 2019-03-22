/*******************************************************************************
 * Copyright (c) 2001, 2005 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v2.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 * 
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *******************************************************************************/
package org.eclipse.jem.java.internal.impl;
/*


 */

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.ecore.*;
import org.eclipse.emf.ecore.impl.ENotificationImpl;

import org.eclipse.jem.java.*;

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
public class ArrayTypeImpl extends JavaClassImpl implements ArrayType, JavaClass {

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
		return JavaRefPackage.Literals.ARRAY_TYPE;
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
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case JavaRefPackage.ARRAY_TYPE__ARRAY_DIMENSIONS:
				return new Integer(getArrayDimensions());
			case JavaRefPackage.ARRAY_TYPE__COMPONENT_TYPE:
				if (resolve) return getComponentType();
				return basicGetComponentType();
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
			case JavaRefPackage.ARRAY_TYPE__ARRAY_DIMENSIONS:
				setArrayDimensions(((Integer)newValue).intValue());
				return;
			case JavaRefPackage.ARRAY_TYPE__COMPONENT_TYPE:
				setComponentType((EClassifier)newValue);
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
			case JavaRefPackage.ARRAY_TYPE__ARRAY_DIMENSIONS:
				setArrayDimensions(ARRAY_DIMENSIONS_EDEFAULT);
				return;
			case JavaRefPackage.ARRAY_TYPE__COMPONENT_TYPE:
				setComponentType((EClassifier)null);
				return;
		}
		super.eUnset(featureID);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean eIsSet(int featureID) {
		switch (featureID) {
			case JavaRefPackage.ARRAY_TYPE__ARRAY_DIMENSIONS:
				return arrayDimensions != ARRAY_DIMENSIONS_EDEFAULT;
			case JavaRefPackage.ARRAY_TYPE__COMPONENT_TYPE:
				return componentType != null;
		}
		return super.eIsSet(featureID);
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
			InternalEObject oldComponentType = (InternalEObject)componentType;
			componentType = (EClassifier)eResolveProxy(oldComponentType);
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





