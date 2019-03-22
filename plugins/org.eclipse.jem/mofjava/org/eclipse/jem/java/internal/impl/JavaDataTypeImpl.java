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

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.*;
import org.eclipse.emf.ecore.impl.EClassImpl;
import org.eclipse.emf.ecore.util.EContentsEList;
import org.eclipse.emf.ecore.util.ECrossReferenceEList;

import org.eclipse.jem.internal.java.instantiation.IInstantiationInstance;
import org.eclipse.jem.java.*;
/**
 * @generated
 */
public class JavaDataTypeImpl extends EClassImpl implements JavaDataType {

	
	static final String FALSE = "false";
	static final String DOUBLE_ZERO = "0.0";
	static final String FLOAT_ZERO = "0.0f";
	static final String CHAR_ZERO = "'0'";
	static final String ZERO = "0";
	
	private int primitive_type = PRIM_NOT_ID;
	
	protected JavaDataTypeImpl() {
		super();
	}
	
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected EClass eStaticClass() {
		return JavaRefPackage.Literals.JAVA_DATA_TYPE;
	}

	/**
	 * Return the default string representing the default value of the primitive.
	 */
	public String getDefaultValueString() {
		String typeName = getJavaName();
		if (typeName.equals(PRIM_BOOLEAN_NAME))
			return FALSE;
		if (typeName.equals(PRIM_DOUBLE_NAME))
			return DOUBLE_ZERO;
		if (typeName.equals(PRIM_FLOAT_NAME))
			return FLOAT_ZERO;			
		if (typeName.equals(PRIM_CHARACTER_NAME))
			return CHAR_ZERO;
		return ZERO;
	}
	public String getJavaName() {
		return getName();
	}
	public JavaDataType getPrimitive() {
		return this;
	}
	
	
	/* (non-Javadoc)
	 * @see org.eclipse.jem.java.JavaHelpers#getPrimitiveID()
	 */
	public int getPrimitiveID() {
		if (primitive_type == PRIM_NOT_ID) {
			String name = getName();
			if (name.equals(PRIM_BOOLEAN_NAME))
				primitive_type = PRIM_BOOLEAN_ID;
			if (name.equals(PRIM_CHARACTER_NAME))
				primitive_type = PRIM_CHARACTER_ID;
			if (name.equals(PRIM_BYTE_NAME))
				primitive_type = PRIM_BYTE_ID;
			if (name.equals(PRIM_SHORT_NAME))
				primitive_type = PRIM_SHORT_ID;
			if (name.equals(PRIM_INTEGER_NAME))
				primitive_type = PRIM_INTEGER_ID;
			if (name.equals(PRIM_LONG_NAME))
				primitive_type = PRIM_LONG_ID;
			if (name.equals(PRIM_FLOAT_NAME))
				primitive_type = PRIM_FLOAT_ID;
			if (name.equals(PRIM_DOUBLE_NAME))
				primitive_type = PRIM_DOUBLE_ID;
		}
		return primitive_type;
	}
	
	public String getSimpleName() {
		return getName();
	}
	public String getQualifiedName() {
		return getJavaName();
	}
	public JavaClass getWrapper() {
		String wrapperName = getWrapperQualifiedName();
		if (wrapperName != null) {
			return (JavaClass) JavaRefFactory.eINSTANCE.reflectType(wrapperName, this);
		}
		return null;
	}
	/**
	 * getWrapper method comment.
	 */
	protected String getWrapperQualifiedName() {
		switch (getPrimitiveID()) {
			case PRIM_INTEGER_ID:
				return INTEGER_NAME;
			case PRIM_CHARACTER_ID:
				return CHARACTER_NAME;
			case PRIM_BOOLEAN_ID:
				return BOOLEAN_NAME;
			case PRIM_BYTE_ID:
				return BYTE_NAME;
			case PRIM_SHORT_ID:
				return SHORT_NAME;
			case PRIM_LONG_ID:
				return LONG_NAME;
			case PRIM_FLOAT_ID:
				return FLOAT_NAME;
			case PRIM_DOUBLE_ID:
				return DOUBLE_NAME;
			default:
				return null;
		}
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
	/* (non-Javadoc)
	 * @see org.eclipse.jem.java.JavaHelpers#getQualifiedNameForReflection()
	 */
	public String getQualifiedNameForReflection() {
		return getJavaName();
	}
	@Override
	  public EList<EObject> eContents()
	  {
	    return EContentsEList.createEContentsEList(this);
	  }

	  @Override
	  public EList<EObject> eCrossReferences()
	  {
	    return ECrossReferenceEList.createECrossReferenceEList(this);
	  }

}





