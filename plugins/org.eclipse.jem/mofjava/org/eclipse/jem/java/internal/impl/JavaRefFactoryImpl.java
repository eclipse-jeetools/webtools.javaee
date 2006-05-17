/*******************************************************************************
 * Copyright (c) 2001, 2006 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *******************************************************************************/
package org.eclipse.jem.java.internal.impl;
import java.util.List;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.*;
import org.eclipse.emf.ecore.impl.EFactoryImpl;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;

import org.eclipse.jem.internal.java.adapters.ReflectionAdaptor;
import org.eclipse.jem.java.*;
import org.eclipse.jem.java.util.JavaContext;



/**
 * @generated
 */
public class JavaRefFactoryImpl extends EFactoryImpl implements JavaRefFactory {
	
	/**
	 * Notes about Type URI's.
	 * <p>
	 * The format of a type uri is: "java:/package#classname". "package" is either the package name,
	 * with '.' for package separator (e.g. "java:/a.b#XYZ"). Or for primitive (e.g. "java:/#int") or
	 * for default package (e.g. "java:/#XYZ").
	 * <p>
	 * The classname has some special changes. First it must use reflection format which means
	 * that any inner class must use the '$' format, i.e. "A.B" must be "A$B".
	 * <p>
	 * Next if there is
	 * a generic involved then it must be changed in the following way: "A<? extends a.B>" will
	 * be changed to "A{? extends a!B}". The reason for this is first that '<' and '>' are
	 * invalid in a fragment (id) of a URI. So we replace them with '{}'. Next because we use
	 * the fragment "A.field" or "A.method(" to mean a field or method id, we can't have the
	 * '.' in the generic portion. If it was there we couldn't quickly find the appropriate nesting
	 * of the generic's '{}' because there could be another generic within the generic, and then
	 * say to find the '.' after that nested set.
	 */

	
	/**
	 * Comment for <code>GENERIC_ID_SEPARATOR</code>
	 * 
	 * @since 1.2.0
	 */
	private static final char GENERIC_ID_SEPARATOR = '!';
	/**
	 * Comment for <code>GENERIC_SEPARATOR</code>
	 * 
	 * @since 1.2.0
	 */
	private static final char GENERIC_SEPARATOR = '.';
	/**
	 * Comment for <code>GENERIC_ID_END</code>
	 * 
	 * @since 1.2.0
	 */
	private static final char GENERIC_ID_END = '}';
	/**
	 * Comment for <code>GENERIC_END</code>
	 * 
	 * @since 1.2.0
	 */
	private static final char GENERIC_END = '>';
	/**
	 * Comment for <code>GENERIC_ID_START</code>
	 * 
	 * @since 1.2.0
	 */
	private static final char GENERIC_ID_START = '{';
	/**
	 * Comment for <code>GENERIC_START</code>
	 * 
	 * @since 1.2.0
	 */
	private static final char GENERIC_START = '<';
	

	public JavaRefFactoryImpl() {
		super();
	}
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EObject create(EClass eClass) {
		switch (eClass.getClassifierID()) {
			case JavaRefPackage.JAVA_CLASS: return createJavaClass();
			case JavaRefPackage.INITIALIZER: return createInitializer();
			case JavaRefPackage.JAVA_PARAMETER: return createJavaParameter();
			case JavaRefPackage.METHOD: return createMethod();
			case JavaRefPackage.FIELD: return createField();
			case JavaRefPackage.BLOCK: return createBlock();
			case JavaRefPackage.COMMENT: return createComment();
			case JavaRefPackage.STATEMENT: return createStatement();
			case JavaRefPackage.JAVA_PACKAGE: return createJavaPackage();
			case JavaRefPackage.JAVA_DATA_TYPE: return createJavaDataType();
			case JavaRefPackage.ARRAY_TYPE: return createArrayType();
			default:
				throw new IllegalArgumentException("The class '" + eClass.getName() + "' is not a valid classifier");
		}
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Object createFromString(EDataType eDataType, String initialValue) {
		switch (eDataType.getClassifierID()) {
			case JavaRefPackage.TYPE_KIND: {
				TypeKind result = TypeKind.get(initialValue);
				if (result == null) throw new IllegalArgumentException("The value '" + initialValue + "' is not a valid enumerator of '" + eDataType.getName() + "'");
				return result;
			}
			case JavaRefPackage.JAVA_VISIBILITY_KIND: {
				JavaVisibilityKind result = JavaVisibilityKind.get(initialValue);
				if (result == null) throw new IllegalArgumentException("The value '" + initialValue + "' is not a valid enumerator of '" + eDataType.getName() + "'");
				return result;
			}
			case JavaRefPackage.JAVA_PARAMETER_KIND: {
				JavaParameterKind result = JavaParameterKind.get(initialValue);
				if (result == null) throw new IllegalArgumentException("The value '" + initialValue + "' is not a valid enumerator of '" + eDataType.getName() + "'");
				return result;
			}
			case JavaRefPackage.JTYPE_JAVA_HELPERS:
				return createJTypeJavaHelpersFromString(eDataType, initialValue);
			case JavaRefPackage.JTYPE_LIST:
				return createJTypeListFromString(eDataType, initialValue);
			default:
				throw new IllegalArgumentException("The datatype '" + eDataType.getName() + "' is not a valid classifier");
		}
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String convertToString(EDataType eDataType, Object instanceValue) {
		switch (eDataType.getClassifierID()) {
			case JavaRefPackage.TYPE_KIND:
				return instanceValue == null ? null : instanceValue.toString();
			case JavaRefPackage.JAVA_VISIBILITY_KIND:
				return instanceValue == null ? null : instanceValue.toString();
			case JavaRefPackage.JAVA_PARAMETER_KIND:
				return instanceValue == null ? null : instanceValue.toString();
			case JavaRefPackage.JTYPE_JAVA_HELPERS:
				return convertJTypeJavaHelpersToString(eDataType, instanceValue);
			case JavaRefPackage.JTYPE_LIST:
				return convertJTypeListToString(eDataType, instanceValue);
			default:
				throw new IllegalArgumentException("The datatype '" + eDataType.getName() + "' is not a valid classifier");
		}
	}

	public ArrayType createArrayType(JavaHelpers componentType) {
		ArrayType at = createArrayType();
		
		String computedName = componentType.getQualifiedName() + "[]";
		at.setName(computedName);
		return at;
	}
	public ArrayType createArrayType(JavaHelpers finalComponentType, int dimensions) {
		ArrayType at = createArrayType();
		
		String computedName = finalComponentType.getQualifiedName();
		for (int i = 0; i < dimensions; i++){
			computedName = computedName + "[]";
		}
		at.setName(computedName);
		return at;
	}

  /**
   * Get the java context
   * @return
   * 
   * @deprecated Use the API {@link JavaContext#createJavaContext()} instead.
   * @since 1.2.0
   */
  public static ResourceSet createJavaContext() {
    return JavaContext.createJavaContext();
  }
	/**
	 * Return the Class for the Java refection adapter factory.
	 * @deprecated Use the API {@link JavaContext#getReflectionAdapterFactoryClass()} instead.
	 */
	public static Class getReflectionAdapterFactoryClass() {
		return JavaContext.getReflectionAdapterFactoryClass();
	}
	
	/**
	 * Set the Class for the Java refection adapter factory.
	 * 
	 * @deprecated Use the API {@link JavaContext#setReflectionAdapterFactoryClass(Class)} instead.
	 */
	public static void setReflectionAdapterFactoryClass(Class javaReflectionFactoryClass) {
		JavaContext.setReflectionAdapterFactoryClass(javaReflectionFactoryClass);
	}
	
	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	public Method createMethod() {
		MethodImpl method = new MethodImpl();
		return method;
	}

	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	public JavaClass createJavaClass() {
		JavaClassImpl javaClass = new JavaClassImpl();
		return javaClass;
	}

	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	public Field createField() {
		FieldImpl field = new FieldImpl();
		return field;
	}

	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	public Block createBlock() {
		BlockImpl block = new BlockImpl();
		return block;
	}

	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	public Comment createComment() {
		CommentImpl comment = new CommentImpl();
		return comment;
	}

	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	public Statement createStatement() {
		StatementImpl statement = new StatementImpl();
		return statement;
	}

	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	public Initializer createInitializer() {
		InitializerImpl initializer = new InitializerImpl();
		return initializer;
	}

	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	public JavaParameter createJavaParameter() {
		JavaParameterImpl javaParameter = new JavaParameterImpl();
		return javaParameter;
	}

	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	public ArrayType createArrayType() {
		ArrayTypeImpl arrayType = new ArrayTypeImpl();
		return arrayType;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public JavaHelpers createJTypeJavaHelpersFromString(EDataType eDataType, String initialValue) {
		return (JavaHelpers)super.createFromString(eDataType, initialValue);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String convertJTypeJavaHelpersToString(EDataType eDataType, Object instanceValue) {
		return super.convertToString(eDataType, instanceValue);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public List createJTypeListFromString(EDataType eDataType, String initialValue) {
		return (List)super.createFromString(eDataType, initialValue);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String convertJTypeListToString(EDataType eDataType, Object instanceValue) {
		return super.convertToString(eDataType, instanceValue);
	}

	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	public JavaDataType createJavaDataType() {
		JavaDataTypeImpl javaDataType = new JavaDataTypeImpl();
		return javaDataType;
	}

	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	public JavaPackage createJavaPackage() {
		JavaPackageImpl javaPackage = new JavaPackageImpl();
		return javaPackage;
	}

	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	public JavaRefPackage getJavaRefPackage() {
		return (JavaRefPackage)getEPackage();
	}

	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	public static JavaRefPackage getPackage() {
		return JavaRefPackage.eINSTANCE;
	}

	public static JavaRefFactory getActiveFactory() {
		return JavaRefFactory.eINSTANCE;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.jem.java.JavaRefFactory#createClassRef(java.lang.String)
	 */
	public JavaClass createClassRef(String targetName) {
		JavaClass ref = createJavaClass();
		((InternalEObject) ref).eSetProxyURI(createTypeURI(targetName));
		return ref;
	}

	/* (non-Javadoc)
	 * @see org.eclipse.jem.java.JavaRefFactory#reflectType(java.lang.String, org.eclipse.emf.ecore.EObject)
	 */
	public JavaHelpers reflectType(String aQualifiedName, EObject relatedObject) {
		Resource r = relatedObject.eResource();
		if (r != null) {
			ResourceSet rs = r.getResourceSet();
			if (rs != null) {
				return reflectType(aQualifiedName, rs);
			}
		}
		return null;
	}

	/* (non-Javadoc)
	 * @see org.eclipse.jem.java.JavaRefFactory#reflectType(java.lang.String, org.eclipse.emf.ecore.resource.ResourceSet)
	 */
	public JavaHelpers reflectType(String aQualifiedName, ResourceSet set) {
		if (aQualifiedName != null) {
			int index = aQualifiedName.lastIndexOf(".");
			if (index > 0)
				return reflectType(aQualifiedName.substring(0, index), aQualifiedName.substring(index + 1, aQualifiedName.length()), set);
			else
				return reflectType("", aQualifiedName, set);
		}
		return null;
	}

	/* (non-Javadoc)
	 * @see org.eclipse.jem.java.JavaRefFactory#reflectType(java.lang.String, java.lang.String, org.eclipse.emf.ecore.resource.ResourceSet)
	 */
	public JavaHelpers reflectType(String aPackageName, String aTypeName, ResourceSet set) {
		if (aTypeName != null && aPackageName != null) {
			org.eclipse.jem.internal.java.init.JavaInit.init();
			return (JavaHelpers) set.getEObject(createTypeURI(aPackageName, aTypeName), true);
		}
		return null;
	}

	/* (non-Javadoc)
	 * @see org.eclipse.jem.java.JavaRefFactory#reflectPackage(java.lang.String, org.eclipse.emf.ecore.resource.ResourceSet)
	 */
	public JavaPackage reflectPackage(String packageName, ResourceSet set) {
		if (packageName != null) {
			org.eclipse.jem.internal.java.init.JavaInit.init();
			return (JavaPackage) set.getEObject(createPackageURI(packageName), true);
		}
		return null;
	}
	
	/* (non-Javadoc)
	 * @see org.eclipse.jem.java.JavaRefFactory#createTypeURI(java.lang.String)
	 */
	public URI createTypeURI(String aQualifiedName) {
		// for need to just tolerate generics (i.e. "<...>". Fully support later.
		int genNdx = aQualifiedName.indexOf(GENERIC_START);
		int pkgNdx = genNdx == -1 ? aQualifiedName.lastIndexOf('.') : aQualifiedName.lastIndexOf('.', genNdx);
		if (pkgNdx > -1)
			return createTypeURI(aQualifiedName.substring(0, pkgNdx), aQualifiedName.substring(pkgNdx+1));
		else
			return createTypeURI(null, aQualifiedName);
	}
	
	/* (non-Javadoc)
	 * @see org.eclipse.jem.java.JavaRefFactory#createTypeURI(java.lang.String, java.lang.String)
	 */
	public URI createTypeURI(String packageName, String typeName) {
		String[] pkgname = null;
		if (packageName != null && packageName.length() > 0)
			pkgname = new String[] {packageName};
		return URI.createHierarchicalURI(JAVA_PROTOCOL_URI_SCHEME, null, null, pkgname, null, createTypeName(typeName));
	}
	
	/**
	 * Create a URI'd type name from a regular typename.
	 * <p>
	 * This should only be used by friends of the Impl package.
	 * @param typeName type name, no package allowed, and must be in form for reflection (i.e. '$' not '.' to separate outer and inner classes).
	 * @return
	 * 
	 * @since 1.2.0
	 */
	public String createTypeName(String typeName) {

		int genStart = typeName.indexOf(GENERIC_START);
		if (genStart > -1) {
			char[] chName = new char[typeName.length()];
			typeName.getChars(0, chName.length, chName, 0);
			for (int i = 0; i < chName.length; i++) {
				switch (chName[i]) {
					case GENERIC_START:
						chName[i] = GENERIC_ID_START;
						break;
					case GENERIC_END:
						chName[i] = GENERIC_ID_END;
						break;
					case GENERIC_SEPARATOR:
						chName[i] = GENERIC_ID_SEPARATOR;
						break;
					default:
						break;
				}
			}
			typeName = new String(chName);
		}
		return typeName;
	}
	
	/* (non-Javadoc)
	 * @see org.eclipse.jem.java.JavaRefFactory#getTypeName(org.eclipse.emf.common.util.URI)
	 */
	public String getTypeName(URI typeURI) {
		if (isTypeURI(typeURI)) {
			return primGetTypeName(typeURI.fragment());
		} else
			throw new IllegalArgumentException(typeURI.toString());
	}
	
	/**
	 * Get type name from URI'd typename.
	 * <p>
	 * This should only be used by friends of the Impl package.
	 * @param type uri fragment value for type name.
	 * @return
	 * 
	 * @since 1.2.0
	 */
	public String primGetTypeName(String typeName) {
		int genStart = typeName.indexOf(GENERIC_ID_START);
		if (genStart > -1) {
			char[] chName = new char[typeName.length()];
			typeName.getChars(0, chName.length, chName, 0);
			for (int i = 0; i < chName.length; i++) {
				switch (chName[i]) {
					case GENERIC_ID_START:
						chName[i] = GENERIC_START;
						break;
					case GENERIC_ID_END:
						chName[i] = GENERIC_END;
						break;
					case GENERIC_ID_SEPARATOR:
						chName[i] = GENERIC_SEPARATOR;
						break;
					default:
						break;
				}
			}
			typeName = new String(chName);
		}
		return typeName;
	}
	
	/* (non-Javadoc)
	 * @see org.eclipse.jem.java.JavaRefFactory#getPackageName(org.eclipse.emf.common.util.URI)
	 */
	public String getPackageName(URI javaURI) {
		if (isTypeURI(javaURI) || isPackageURI(javaURI)) {
			if (javaURI.segmentCount() == 1) {
				return javaURI.segment(0);
			} else if (javaURI.segmentCount() == 0)
				return "";	//$NON-NLS-1$	
			else
				throw new IllegalArgumentException(javaURI.toString());
		} else
			throw new IllegalArgumentException(javaURI.toString());
	}
	
	/* (non-Javadoc)
	 * @see org.eclipse.jem.java.JavaRefFactory#getFullTypeName(org.eclipse.emf.common.util.URI)
	 */
	public String getFullTypeName(URI typeURI) {
		String pkgName = getPackageName(typeURI);
		if (!isTypeURI(typeURI))
			throw new IllegalArgumentException(typeURI.toString());
		
		if (pkgName.length() > 0)
			return pkgName+'.'+getTypeName(typeURI);
		else
			return typeURI.fragment();
	}
	
	/* (non-Javadoc)
	 * @see org.eclipse.jem.java.JavaRefFactory#createPackageURI(java.lang.String)
	 */
	public URI createPackageURI(String packageName) {
		String[] pkgname = null;
		if (packageName != null && packageName.length() > 0)
			pkgname = new String[] {packageName};
		return URI.createHierarchicalURI(JAVA_PROTOCOL_URI_SCHEME, null, null, pkgname, null, JavaPackage.PACKAGE_ID);
	}
	/* (non-Javadoc)
	 * @see org.eclipse.jem.java.JavaRefFactory#isJavaURI(org.eclipse.emf.common.util.URI)
	 */
	public boolean isJavaURI(URI uri) {
		return JAVA_PROTOCOL_URI_SCHEME.equals(uri.scheme());
	}
	
	/* (non-Javadoc)
	 * @see org.eclipse.jem.java.JavaRefFactory#isTypeURI(org.eclipse.emf.common.util.URI)
	 */
	public boolean isTypeURI(URI uri) {
		if (isJavaURI(uri)) {
			String frag = uri.fragment();
			if (frag != null && !JavaPackage.PACKAGE_ID.equals(frag)) {
				int delimNdx = frag.indexOf(ReflectionAdaptor.C_CLASS_MEMBER_DELIMITER);
				if (delimNdx != -1)
					return false;
				delimNdx = frag.indexOf('/');	// From beaninfo for property.
				if (delimNdx != -1)
					return false;
				else
					return true;
			} else
				return false;
		} else
			return false;
	}
	
	/* (non-Javadoc)
	 * @see org.eclipse.jem.java.JavaRefFactory#isPackageURI(org.eclipse.emf.common.util.URI)
	 */
	public boolean isPackageURI(URI uri) {
		return isJavaURI(uri) && JavaPackage.PACKAGE_ID.equals(uri.fragment());
	}

}






