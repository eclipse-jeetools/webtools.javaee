/*******************************************************************************
 * Copyright (c) 2001, 2005 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *******************************************************************************/
/*
 *  $RCSfile: JavaClassImpl.java,v $
 *  $Revision: 1.1 $  $Date: 2005/09/14 23:30:32 $ 
 */
package org.eclipse.jem.java.internal.impl;

import java.util.Collection;
import java.util.List;

import java.util.*;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.common.util.ECollections;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.EcorePackage;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.EClassImpl;
import org.eclipse.emf.ecore.impl.ENotificationImpl;

import org.eclipse.emf.ecore.util.EObjectContainmentEList;
import org.eclipse.emf.ecore.util.EObjectContainmentWithInverseEList;
import org.eclipse.emf.ecore.util.EObjectResolvingEList;
import org.eclipse.emf.ecore.util.EObjectWithInverseResolvingEList;
import org.eclipse.emf.ecore.util.InternalEList;

import org.eclipse.jem.java.Field;
import org.eclipse.jem.java.Initializer;
import org.eclipse.jem.java.JavaClass;
import org.eclipse.jem.java.JavaEvent;
import org.eclipse.jem.java.JavaPackage;
import org.eclipse.jem.java.JavaRefPackage;
import org.eclipse.jem.java.Method;
import org.eclipse.jem.java.TypeKind;

import org.eclipse.emf.ecore.*;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.util.*;

import org.eclipse.jem.internal.java.adapters.*;
import org.eclipse.jem.internal.java.beaninfo.IIntrospectionAdapter;
import org.eclipse.jem.internal.java.instantiation.IInstantiationInstance;
import org.eclipse.jem.java.*;

/**
 * <!-- begin-user-doc -->
 * 
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.eclipse.jem.java.internal.impl.JavaClassImpl#getKind <em>Kind</em>}</li>
 *   <li>{@link org.eclipse.jem.java.internal.impl.JavaClassImpl#isPublic <em>Public</em>}</li>
 *   <li>{@link org.eclipse.jem.java.internal.impl.JavaClassImpl#isFinal <em>Final</em>}</li>
 *   <li>{@link org.eclipse.jem.java.internal.impl.JavaClassImpl#getImplementsInterfaces <em>Implements Interfaces</em>}</li>
 *   <li>{@link org.eclipse.jem.java.internal.impl.JavaClassImpl#getClassImport <em>Class Import</em>}</li>
 *   <li>{@link org.eclipse.jem.java.internal.impl.JavaClassImpl#getPackageImports <em>Package Imports</em>}</li>
 *   <li>{@link org.eclipse.jem.java.internal.impl.JavaClassImpl#getFields <em>Fields</em>}</li>
 *   <li>{@link org.eclipse.jem.java.internal.impl.JavaClassImpl#getMethods <em>Methods</em>}</li>
 *   <li>{@link org.eclipse.jem.java.internal.impl.JavaClassImpl#getInitializers <em>Initializers</em>}</li>
 *   <li>{@link org.eclipse.jem.java.internal.impl.JavaClassImpl#getDeclaredClasses <em>Declared Classes</em>}</li>
 *   <li>{@link org.eclipse.jem.java.internal.impl.JavaClassImpl#getDeclaringClass <em>Declaring Class</em>}</li>
 *   <li>{@link org.eclipse.jem.java.internal.impl.JavaClassImpl#getJavaPackage <em>Java Package</em>}</li>
 *   <li>{@link org.eclipse.jem.java.internal.impl.JavaClassImpl#getEvents <em>Events</em>}</li>
 *   <li>{@link org.eclipse.jem.java.internal.impl.JavaClassImpl#getAllEvents <em>All Events</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class JavaClassImpl extends EClassImpl implements JavaClass {

	/**
	 * The default value of the '{@link #getKind() <em>Kind</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getKind()
	 * @generated
	 * @ordered
	 */
	protected static final TypeKind KIND_EDEFAULT = TypeKind.UNDEFINED_LITERAL;

	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	protected TypeKind kind = KIND_EDEFAULT;

	/**
	 * The default value of the '{@link #isPublic() <em>Public</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isPublic()
	 * @generated
	 * @ordered
	 */
	protected static final boolean PUBLIC_EDEFAULT = false;

	/**
	 * The cached value of the '{@link #isPublic() <em>Public</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isPublic()
	 * @generated
	 * @ordered
	 */
	protected boolean public_ = PUBLIC_EDEFAULT;

	/**
	 * The default value of the '{@link #isFinal() <em>Final</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isFinal()
	 * @generated
	 * @ordered
	 */
	protected static final boolean FINAL_EDEFAULT = false;

	/**
	 * The cached value of the '{@link #isFinal() <em>Final</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isFinal()
	 * @generated
	 * @ordered
	 */
	protected boolean final_ = FINAL_EDEFAULT;

	/**
	 * The cached value of the '{@link #getImplementsInterfaces() <em>Implements Interfaces</em>}' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getImplementsInterfaces()
	 * @generated
	 * @ordered
	 */
	protected EList implementsInterfaces = null;

	/**
	 * The cached value of the '{@link #getClassImport() <em>Class Import</em>}' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getClassImport()
	 * @generated
	 * @ordered
	 */
	protected EList classImport = null;

	/**
	 * The cached value of the '{@link #getPackageImports() <em>Package Imports</em>}' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getPackageImports()
	 * @generated
	 * @ordered
	 */
	protected EList packageImports = null;

	/**
	 * The cached value of the '{@link #getFields() <em>Fields</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getFields()
	 * @generated
	 * @ordered
	 */
	protected EList fields = null;

	/**
	 * The cached value of the '{@link #getMethods() <em>Methods</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getMethods()
	 * @generated
	 * @ordered
	 */
	protected EList methods = null;

	/**
	 * The cached value of the '{@link #getInitializers() <em>Initializers</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getInitializers()
	 * @generated
	 * @ordered
	 */
	protected EList initializers = null;

	/**
	 * The cached value of the '{@link #getDeclaredClasses() <em>Declared Classes</em>}' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getDeclaredClasses()
	 * @generated
	 * @ordered
	 */
	protected EList declaredClasses = null;

	/**
	 * The cached value of the '{@link #getDeclaringClass() <em>Declaring Class</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getDeclaringClass()
	 * @generated
	 * @ordered
	 */
	protected JavaClass declaringClass = null;

	/**
	 * The cached value of the '{@link #getEvents() <em>Events</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getEvents()
	 * @generated
	 * @ordered
	 */
	protected EList events = null;

	protected JavaClassImpl() {
		super();
	}
	
	public EList getEAnnotationsInternal() {
		return super.getEAnnotations();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected EClass eStaticClass() {
		return JavaRefPackage.eINSTANCE.getJavaClass();
	}

	/**
	 * Protected helper methods.
	 */
	protected void collectFieldsExtended(List fields) {
		fields.addAll(getFields());
		Iterator it;
		it = getExtendedLookupIterator();
		while (it.hasNext())
			((JavaClassImpl) it.next()).collectFieldsExtended(fields);
	}

	protected void collectMethodsExtended(Map methods, boolean onlyPublic, List excludedClasses, List excludedMethods) {
		Iterator it1, it2;
		it2 = getExtendedLookupIterator();
		JavaClassImpl javaClass;
		while (it2.hasNext()) {
			javaClass = (JavaClassImpl) it2.next();
			if (!excludedClasses.contains(javaClass))
				javaClass.collectMethodsExtended(methods, onlyPublic, excludedClasses, excludedMethods);
		}
		it1 = onlyPublic ? getPublicMethods().iterator() : getMethods().iterator();
		Method nextMethod;
		while (it1.hasNext()) {
			nextMethod = (Method) it1.next();
			if (!excludedMethods.contains(nextMethod))
				methods.put(nextMethod.getMethodElementSignature(), nextMethod);
		}
	}

	/**
	 * createClassRef - return a JavaURL reference to the named Java class
	 * 
	 * @deprecated @see org.eclipse.jem.java.JavaRefFactory#createClassRef(java.lang.String)
	 */
	public static JavaClass createClassRef(String targetName) {
		return JavaRefFactory.eINSTANCE.createClassRef(targetName);
	}

	/**
	 * Get the method of this name and these parameters. It will look up the supertype hierarchy.
	 */
	protected Method findClassMethodExtended(String methodName, List parameterTypes) {
		Method method = getMethod(methodName, parameterTypes);
		if (method != null)
			return method;
		else {
			JavaClassImpl mySuper;
			mySuper = (JavaClassImpl) getSupertype();
			if (mySuper != null)
				return mySuper.findClassMethodExtended(methodName, parameterTypes);
		}
		return null;
	}

	/**
	 * Get the method of this name and these parameters. It will look up the supertype hierarchy.
	 */
	protected Method findInterfaceMethodExtended(String methodName, List parameterTypes) {
		Method method = getMethod(methodName, parameterTypes);
		if (method != null)
			return method;
		else {
			JavaClassImpl superInterface;
			List list = getImplementsInterfaces();
			for (int i = 0; i < list.size(); i++) {
				superInterface = (JavaClassImpl) list.get(i);
				method = superInterface.findInterfaceMethodExtended(methodName, parameterTypes);
				if (method != null)
					return method;
			}
		}
		return null;
	}

	/**
	 * 
	 * @return
	 * @deprecated If anyone is referencing this, please use getEAllSuperTypes() instead. This was placed here and misspelled by mistake.
	 */
	public EList getAllSupertypes() {
		getESuperTypes(); //Force reflection, if needed, before getting all supertypes.
		return super.getEAllSuperTypes();
	}

	/**
	 * Overrides to perform reflection if necessary
	 */
	public EList getClassImport() {
		reflectBase();
		return getClassImportGen();
	}

	public EList getEAllOperations() {
		IIntrospectionAdapter ia = getIntrospectionAdapter();
		if (ia == null)
			return super.getEAllOperations(); // No introspection, do normal.
		if (eAllOperations == null)
			eAllOperations = ia.getEAllOperations();
		return eAllOperations;
	}
	
	/**
	 * This is not meant to be used by others. It is here so that BeanInfo adapter can access
	 * un-introspected all operations.
	 * @return
	 * 
	 * @since 1.1.0
	 */
	public EList primGetEAllOperations() {
		return super.getEAllOperations();
	}

	public EList getEOperations() {
		IIntrospectionAdapter adapter = getIntrospectionAdapter();
		if (adapter != null)
			return adapter.getEOperations();
		return super.getEOperations();
	}

	/**
	 * This method is not meant to be used by others. It is here so that BeanInfo adapters can go to
	 * the non-introspected operations.
	 * @return
	 * 
	 * @since 1.1.0
	 */
	public EList getEOperationsInternal() {
		// An internal method for returning actual wo fluffing up.
		return super.getEOperations();
	}

	public EList getEAnnotations() {
		IIntrospectionAdapter adapter = getIntrospectionAdapter();
		if (adapter != null)
			adapter.introspectIfNecessary(); // To cause introspection so the appropriate decorator is added.
		return super.getEAnnotations();
	}

	public EList getEStructuralFeatures() {
		IIntrospectionAdapter adapter = getIntrospectionAdapter();
		if (adapter != null)
			return adapter.getEStructuralFeatures();
		return super.getEStructuralFeatures();
	}

	public EList getEStructuralFeaturesInternal() {
		// An internal method for returning actual wo fluffing up.
		return super.getEStructuralFeatures();
	}

	/**
	 * Return an Iterator on the implemntsInferface List if this is an interface class or on the super List if it is a class.
	 */
	protected Iterator getExtendedLookupIterator() {
		if (isInterface())
			return getImplementsInterfaces().iterator();
		else
			return getESuperTypes().iterator();
	}

	/**
	 * Return an Field with the passed name, or null.
	 */
	public Field getField(String fieldName) {
		java.util.List fields = getFields();
		Field field;
		for (int i = 0; i < fields.size(); i++) {
			field = (Field) fields.get(i);
			if (field.getName().equals(fieldName))
				return field;
		}
		return null;
	}

	/**
	 * Return an Field with the passed name from this JavaClass or any supertypes.
	 * 
	 * Return null if a Field named fieldName is not found.
	 */
	public Field getFieldExtended(String fieldName) {
		Field field = getFieldNamed(fieldName);
		if (field != null)
			return field;
		Iterator it = getExtendedLookupIterator();
		while (it.hasNext()) {
			Field result = ((JavaClass) it.next()).getFieldExtended(fieldName);
			if (result != null)
				return result;
		}
		return null;
	}

	/**
	 * Return an Field with the passed name, or null.
	 */
	public Field getFieldNamed(String fieldName) {
		return getField(fieldName);
	}

	public EList getFields() {
		reflectFields();
		return getFieldsGen();
	}

	/**
	 * Return all fields, including those from supertypes.
	 */
	public List getFieldsExtended() {
		List fields = new ArrayList();
		collectFieldsExtended(fields);
		return fields;
	}

	public EList getImplementsInterfaces() {
		reflectBase();
		return getImplementsInterfacesGen();
	}

	/**
	 * Return an IntrospectionAdaptor which can introspect our Java properties
	 */
	protected synchronized IIntrospectionAdapter getIntrospectionAdapter() {
		// Need to sync this because we are now in multi-thread env.
		return (IIntrospectionAdapter) EcoreUtil.getRegisteredAdapter(this, IIntrospectionAdapter.ADAPTER_KEY);
	}

	public String getJavaName() {
		return getQualifiedName();
	}

	/*
	 *  (non-Javadoc)
	 * @see org.eclipse.jem.java.JavaHelpers#getSimpleName()
	 */
	public String getSimpleName() {
		return primGetName().replace('$', '.');
	}
	/**
	 * getJavaPackage. This is a derived relationship, so we must implement it here to get the EPackage that this object is contained in.
	 */
	public JavaPackage getJavaPackage() {
		return (JavaPackage) getEPackage();
	}

	/**
	 * Get the method of this name and these parameters. It will not look up the supertype hierarchy.
	 */
	public Method getMethod(String methodName, List parameterTypes) {
		return getMethod(methodName, parameterTypes, getMethods());
	}

	protected Method getMethod(String name, List parameterTypes, List methodList) {
		boolean found = false;
		Method method;
		for (int i = 0; i < methodList.size(); i++) {
			method = (Method) methodList.get(i);
			JavaParameter[] params;
			if (method.getName().equals(name)) {
				params = method.listParametersWithoutReturn();
				if (params.length == parameterTypes.size()) {
					found = true; //Maybe; we need more info
					for (int j = 0; j < params.length; j++) {
						//if any one of the parameters doesn't match then flip back to false
						JavaHelpers jh = (JavaHelpers) params[j].getEType();
						if (!jh.getQualifiedName().equals(parameterTypes.get(j))) {
							found = false;
							break;
						} // end if params equal
					} // end compare all params
					if (found) //short circuit out of this loop and return the winner
						return method;
				} // end compare lengths
			} // end compare names
		} // end loop through all methodList
		return null;
	}

	/**
	 * Return a List of Strings that represent MethodElement signatures from most general to most specific.
	 */
	public List getMethodElementSignatures() {
		List methods, signatures, sameNames;
		methods = getMethodsExtended();
		sameNames = new ArrayList();
		signatures = new ArrayList(methods.size() + 1);
		signatures.add(DEFAULT_METHOD_NAME);
		Iterator it = methods.iterator();
		Method aMethod;
		String methodName;
		while (it.hasNext()) {
			aMethod = (Method) it.next();
			methodName = aMethod.getName();
			if (sameNames.contains(methodName)) {
				if (!signatures.contains(methodName))
					signatures.add(methodName);
			} else
				sameNames.add(methodName);
			signatures.add(aMethod.getMethodElementSignature());
		}
		Collections.sort(signatures);
		return signatures;
	}

	/**
	 * Get the method of this name and these parameters. It will look up the supertype hierarchy.
	 */
	public Method getMethodExtended(String methodName, List parameterTypes) {
		if (isInterface())
			return findInterfaceMethodExtended(methodName, parameterTypes);
		else
			return findClassMethodExtended(methodName, parameterTypes);
	}

	public EList getMethods() {
		reflectMethods();
		return getMethodsGen();
	}

	/**
	 * Return all methods, including those from supertypes.
	 */
	public List getMethodsExtended() {
		Map methods = new HashMap();
		collectMethodsExtended(methods, false, Collections.EMPTY_LIST, Collections.EMPTY_LIST);
		return new ArrayList(methods.values());
	}

	/*
	 * @see getMethodsExtendedWithFilters(List, List) on JavaClass.
	 */
	public List getMethodsExtendedWithFilters(List excludedClasses, List excludedMethods) {
		Map methods = new HashMap();
		collectMethodsExtended(methods, false, excludedClasses, excludedMethods);
		return new ArrayList(methods.values());
	}

	public String getName() {
		String result = this.primGetName();
		if (result == null && eIsProxy()) {
			JavaURL url = new JavaURL(eProxyURI().toString());
			result = url.getClassName();
		}
		return result;
	}

	/**
	 * Return a List of Methods that begins with @aMethodNamePrefix and is not included in the @excludedNames list. If @aMethodNamePrefix is null, all methods will be returned.
	 
	 */
	public List getOnlySpecificMethods(String aMethodNamePrefix, List excludedNames) {
		List methods, specific;
		methods = getMethodsExtended();
		specific = new ArrayList(methods.size());
		Iterator it = methods.iterator();
		Method aMethod;
		String methodName;
		while (it.hasNext()) {
			aMethod = (Method) it.next();
			methodName = aMethod.getName();
			if (aMethodNamePrefix != null && methodName.startsWith(aMethodNamePrefix) && excludedNames != null && !excludedNames.contains(methodName))
				specific.add(aMethod);
		}
		return specific;
	}

	public EList getPackageImports() {
		reflectBase();
		return getPackageImportsGen();
	}

	/**
	 * getPrimitive method comment.
	 */
	public JavaDataType getPrimitive() {
		String primitiveName = getPrimitiveName();
		if (primitiveName != null) {
			Resource res = eResource();
			return (JavaDataType) JavaClassImpl.reflect(primitiveName, res.getResourceSet());
		}
		return null;
	}
	
	
	/* (non-Javadoc)
	 * @see org.eclipse.jem.java.JavaHelpers#getPrimitiveID()
	 */
	public int getPrimitiveID() {
		String myName = getQualifiedName();
		if (myName.equals(INTEGER_NAME))
			return PRIM_INTEGER_ID;
		if (myName.equals(BOOLEAN_NAME))
			return PRIM_BOOLEAN_ID;
		if (myName.equals(BYTE_NAME))
			return PRIM_BYTE_ID;
		if (myName.equals(SHORT_NAME))
			return PRIM_SHORT_ID;
		if (myName.equals(LONG_NAME))
			return PRIM_LONG_ID;
		if (myName.equals(FLOAT_NAME))
			return PRIM_FLOAT_ID;
		if (myName.equals(DOUBLE_NAME))
			return PRIM_DOUBLE_ID;
		if (myName.equals(CHARACTER_NAME))
			return PRIM_CHARACTER_ID;
		return PRIM_NOT_ID;
	}

	/**
	 * Return the primitive name for this type if one exists.
	 */
	protected String getPrimitiveName() {
		String myName = getQualifiedName();
		if (myName.equals(INTEGER_NAME))
			return PRIM_INTEGER_NAME;
		if (myName.equals(BOOLEAN_NAME))
			return PRIM_BOOLEAN_NAME;
		if (myName.equals(BYTE_NAME))
			return PRIM_BYTE_NAME;
		if (myName.equals(SHORT_NAME))
			return PRIM_SHORT_NAME;
		if (myName.equals(LONG_NAME))
			return PRIM_LONG_NAME;
		if (myName.equals(FLOAT_NAME))
			return PRIM_FLOAT_NAME;
		if (myName.equals(DOUBLE_NAME))
			return PRIM_DOUBLE_NAME;
		if (myName.equals(CHARACTER_NAME))
			return PRIM_CHARACTER_NAME;
		return null;
	}

	/**
	 * Return a method matching the name, and non-return parameters with fully qualified types matching all the types in the list, if it exists. It
	 * will not look up the supertype hierarchy.
	 */
	public Method getPublicMethod(String methodName, List parameterTypes) {
		return getMethod(methodName, parameterTypes, getPublicMethods());
	}

	/**
	 * Return a method matching the name, and non-return parameters with fully qualified types matching all the types in the list, if it exists. It
	 * will not look up the supertype hierarchy.
	 */
	public Method getPublicMethodExtended(String methodName, List parameterTypes) {
		return getMethod(methodName, parameterTypes, getPublicMethodsExtended());
	}

	/**
	 * Return all methods, it will not go up the supertype hierarchy.
	 */
	public List getPublicMethods() {
		List publicMethods = new ArrayList();
		List theMethods = getMethods();
		for (int i = 0; i < theMethods.size(); i++) {
			Method m = (Method) theMethods.get(i);
			if (JavaVisibilityKind.PUBLIC_LITERAL == m.getJavaVisibility())
				publicMethods.add(m);
		}
		return publicMethods;
	}

	/**
	 * Return all public methods, including those from supertypes.
	 */
	public List getPublicMethodsExtended() {
		Map methods = new HashMap();
		collectMethodsExtended(methods, true, Collections.EMPTY_LIST, Collections.EMPTY_LIST);
		return new ArrayList(methods.values());
	}

	/**
	 * Returns a filtered list on the methods of this class, having a name equal to that of the parameter.
	 */
	public List getPublicMethodsExtendedNamed(String name) {
		List publicMethods = new ArrayList();
		List theMethods = getPublicMethodsExtended();
		for (int i = 0; i < theMethods.size(); i++) {
			Method m = (Method) theMethods.get(i);
			if (m.getName().equals(name))
				publicMethods.add(m);
		}
		return publicMethods;
	}

	/**
	 * Returns a filtered list on the methods of this class, having a name equal to that of the parameter.
	 */
	public List getPublicMethodsNamed(String name) {
		List publicMethods = new ArrayList();
		List theMethods = getPublicMethods();
		for (int i = 0; i < theMethods.size(); i++) {
			Method m = (Method) theMethods.get(i);
			if (m.getName().equals(name))
				publicMethods.add(m);
		}
		return publicMethods;
	}

	public String getQualifiedName() {
		String result = null;
		if (eIsProxy()) {
			JavaURL url = new JavaURL(eProxyURI().toString());
			String internalName = url.getPackageName();
			if (internalName != null && internalName.length() > 0)
				result = internalName + "." + url.getClassName();
			else
				result = url.getClassName();
		} else {
			result = primGetQualifiedName();
		}
		if (result != null)
			result = result.replace('$', '.');
		return result;
	}

	/**
	 * To be used by people that need to get the qualified name used for reflection. Typically bean info would need to use something like this.
	 */
	public String getQualifiedNameForReflection() {
		return primGetQualifiedName();
	}

	/**
	 * Return a ReadAdaptor which can reflect our Java properties
	 */
	protected synchronized ReadAdaptor getReadAdapter() {
		// Need to sync because now in a multi-thread env.
		return (ReadAdaptor) EcoreUtil.getRegisteredAdapter(this, ReadAdaptor.TYPE_KEY);
	}

	private static final int NOT_REFLECTED = 0x0, REFLECTED_BASE = 0x1, REFLECTED_METHODS = 0x2, REFLECTED_FIELDS = 0x4;

	protected int reflectionStatus = NOT_REFLECTED;

	protected void reflectBase() {
		// We only want the testing of the hasReflected and get readadapter to be sync(this) so that
		// it is short and no deadlock possibility (this is because the the method reflection adapter may go
		// back to the containing java class to get its reflection adapter, which would lock on itself. So
		// we need to keep the sections that are sync(this) to not be deadlockable by not doing significant work
		// during the sync.
		ReadAdaptor readAdaptor = null;
		synchronized (this) {
			if ((reflectionStatus & REFLECTED_BASE) == 0) {
				readAdaptor = getReadAdapter();
			}
		}
		if (readAdaptor != null) {
			boolean setReflected = readAdaptor.reflectValuesIfNecessary();
			synchronized (this) {
				// Don't want to set it false. That is job of reflection adapter. Otherwise we could have a race.
				if (setReflected)
					reflectionStatus |= REFLECTED_BASE;
			}
		}
	}

	protected void reflectFields() {
		// We only want the testing of the hasReflected and get readadapter to be sync(this) so that
		// it is short and no deadlock possibility (this is because the the method reflection adapter may go
		// back to the containing java class to get its reflection adapter, which would lock on itself. So
		// we need to keep the sections that are sync(this) to not be deadlockable by not doing significant work
		// during the sync.
		ReadAdaptor readAdaptor = null;
		synchronized (this) {
			if ((reflectionStatus & REFLECTED_FIELDS) == 0) {
				readAdaptor = getReadAdapter();
			}
		}
		if (readAdaptor != null) {
			boolean setReflected = ((IJavaClassAdaptor) readAdaptor).reflectFieldsIfNecessary();
			synchronized (this) {
				// Don't want to set it false. That is job of reflection adapter. Otherwise we could have a race.
				if (setReflected)
					reflectionStatus |= (REFLECTED_FIELDS | REFLECTED_BASE); // We can be certain base will be done by reflect fields if not already
																			 // done.
			}
		}
	}

	protected void reflectMethods() {
		// We only want the testing of the hasReflected and get readadapter to be sync(this) so that
		// it is short and no deadlock possibility (this is because the the method reflection adapter may go
		// back to the containing java class to get its reflection adapter, which would lock on itself. So
		// we need to keep the sections that are sync(this) to not be deadlockable by not doing significant work
		// during the sync.
		ReadAdaptor readAdaptor = null;
		synchronized (this) {
			if ((reflectionStatus & REFLECTED_METHODS) == 0) {
				readAdaptor = getReadAdapter();
			}
		}
		if (readAdaptor != null) {
			boolean setReflected = ((IJavaClassAdaptor) readAdaptor).reflectMethodsIfNecessary();
			synchronized (this) {
				// Don't want to set it false. That is job of reflection adapter. Otherwise we could have a race.
				if (setReflected)
					reflectionStatus |= (REFLECTED_METHODS | REFLECTED_BASE); // We can be certain base will be done by reflect fields if not already
																			  // done.
			}
		}
	}

	public JavaClass getSupertype() {
		// If this is an interface, there is no supertype. However, the eSuperTypes IS filled in with any interface that this extends. 
		if (isInterface())
			return null;
		List list = getESuperTypes();
		return (!list.isEmpty()) ? (JavaClass) list.get(0) : null;
	}

	public JavaClass getWrapper() {
		return this;
	}

	/**
	 * Test whether the receiver implements the passed interface (or one of its supertypes).
	 */
	public boolean implementsInterface(JavaClass interfaceType) {
		if (this == interfaceType)
			return true;
		EList implemented = getImplementsInterfaces();
		JavaClass anInterface;
		for (int i = 0; i < implemented.size(); i++) {
			anInterface = (JavaClass) implemented.get(i);
			if (anInterface.implementsInterface(interfaceType))
				return true;
		}
		if (getSupertype() != null)
			return getSupertype().implementsInterface(interfaceType);
		else
			return false;
	}

	/**
	 * Return a string showing our details.
	 */
	public String infoString() {
		StringBuffer out = new StringBuffer();
		// trip class reflection
		//FB this.eGet(JavaRefPackage.eINSTANCE.getJavaClass_Public());
		reflectBase(); //FB
		out.append("Java class: " + getQualifiedName() + "\n");
		out.append("  superclass: " + this.getSupertype() + "\n");
		EList fields = getFields();
		Field field;
		if (fields.size() > 0) {
			out.append("  Fields:\n");
			for (int i = 0; i < fields.size(); i++) {
				field = (Field) fields.get(i);
				out.append("    " + ((JavaHelpers) field.getEType()).getJavaName() + " " + field.getName() + "\n");
			}
		}
		EList methods = getMethods();
		Method method;
		if (methods.size() > 0) {
			out.append("  Methods:\n");
			for (int i = 0; i < methods.size(); i++) {
				method = (Method) methods.get(i);
				// trip method reflection
				method.isStatic();
				if (method.getReturnType() != null)
					out.append("    " + method.getReturnType().getJavaName() + " ");
				else
					out.append("    void ");
				out.append(method.getName() + "(");
				EList parms = method.getParameters();
				JavaParameter parm;
				if (parms.size() > 0) {
					for (int ii = 0; ii < parms.size(); ii++) {
						parm = (JavaParameter) parms.get(ii);
						//FB if (!parm.isReturn()) {
						out.append(((JavaHelpers) parm.getEType()).getJavaName() + " " + parm.getName());
						if (ii < parms.size() - 1)
							out.append(", ");
						//FB }
					}
				}
				out.append(")\n");
			}
		}
		return out.toString();
	}

	/**
	 * Tests whether this class inherits from the passed in class.
	 */
	public boolean inheritsFrom(JavaClass javaClass) {
		if (this == javaClass)
			return true;
		else if (getSupertype() != null)
			return getSupertype().inheritsFrom(javaClass);
		else
			return false;
	}

	public boolean isArray() {
		return false;
	}

	/**
	 * Can an object of the passed in class be assigned to an object of this class. In other words is this class a supertype of the passed in class,
	 * or is it superinterface of it.
	 */
	public boolean isAssignableFrom(EClassifier aClass) {
		if (aClass instanceof JavaClass) {
			JavaClass theClass = (JavaClass) aClass;
			// If either this class or aClass kind is unknown then it isn't assignableFrom.
			if (getKind() == TypeKind.UNDEFINED_LITERAL || theClass.getKind() == TypeKind.UNDEFINED_LITERAL)
				return false;
			// If the "aClass" is not a JavaClass (i.e. it is either a JavaDatatype or
			// some other kind of MOF classifier), then it cannot be
			// assigned to something of this class.
			if (getKind() != TypeKind.INTERFACE_LITERAL)
				if (theClass.getKind() != TypeKind.INTERFACE_LITERAL)
					return theClass.inheritsFrom(this);
				else {
					// aClass is an interface, so it is assignable only if
					// "this" is "java.lang.Object".
					return getQualifiedName().equals("java.lang.Object");
				}
			else
				return theClass.implementsInterface(this);
		}
		return false;
	}

	/**
	 * Does this type exist.
	 */
	public boolean isExistingType() {
		// TODO: Temporary, inefficient implementation
		return ((JavaReflectionAdaptor) getReadAdapter()).hasReflectionSource();
	}

	/**
	 * See if this is valid object of this type.
	 */
	public boolean isInstance(Object o) {
		return o instanceof IInstantiationInstance ? isAssignableFrom(((IInstantiationInstance) o).getJavaType()) : false;
	}

	/**
	 * Is this an interface.
	 */
	public boolean isInterface() {
		return getKind() == TypeKind.INTERFACE_LITERAL;
	}

	public boolean isNested() {
		return getDeclaringClass() != null;
	}

	public boolean isPrimitive() {
		return false;
	}

	/**
	 * Return an array listing our fields, including inherited fields. The field relationship is derived from contents. This implementation depends on
	 * the assumption that supertypes above JavaClass will hold Attributes rather than Fields.
	 */
	public Field[] listFieldExtended() {
		List fields = getFieldsExtended();
		Field[] result = new Field[fields.size()];
		fields.toArray(result);
		return result;
	}

	/**
	 * Return an array listing our Methods, including inherited methods. The method relationship is derived from contents. This implementation depends
	 * on the assumption that supertypes above JavaClass will hold Operations rather than Methods.
	 */
	public Method[] listMethodExtended() {
		java.util.List methods = getMethodsExtended();
		Method[] result = new Method[methods.size()];
		methods.toArray(result);
		return result;
	}

	/**
	 * This is required for internal reflection do not use.
	 */
	public String primGetName() {
		return super.getName();
	}

	/**
	 * This is required for internal reflection do not use.
	 */
	public String primGetQualifiedName() {
		String result = "";
		JavaPackage pack = getJavaPackage();
		if (pack != null && pack.getPackageName().length() != 0)
			result = pack.getPackageName() + "." + this.primGetName();
		else
			result = this.getName();
		return result;
	}

	/**
	 * reflect - reflect a JavaClass for a given qualified name. If the package or class does not exist, one will be created through the reflection
	 * mechanism. Lookup the JavaClass in the context of the passed object, handling some error cases.
	 * 
	 * @deprecated @see org.eclipse.jem.java.JavaRefFactory#reflectType(java.lang.String, org.eclipse.emf.ecore.EObject)
	 *  
	 */
	public static JavaHelpers reflect(String aQualifiedName, EObject relatedObject) {
		return JavaRefFactory.eINSTANCE.reflectType(aQualifiedName, relatedObject);
	}

	/**
	 * reflect - reflect a JavaClass for a given qualified name. If the package or class does not exist, one will be created through the reflection
	 * mechanism.
	 * 
	 * @deprecated @see org.eclipse.jem.java.JavaRefFactory#reflectType(java.lang.String, org.eclipse.emf.ecore.resource.ResourceSet)
	 */
	public static JavaHelpers reflect(String aQualifiedName, ResourceSet set) {
		return JavaRefFactory.eINSTANCE.reflectType(aQualifiedName, set);
	}

	/**
	 * reflect - reflect a JavaClass for a given package name or class name. If the package or class does not exist, one will be created through the
	 * reflection mechanism.
	 * 
	 * @deprecated @see org.eclipse.jem.java.JavaRefFactory#reflectType(java.lang.String, java.lang.String,
	 *             org.eclipse.emf.ecore.resource.ResourceSet)
	 */
	public static JavaHelpers reflect(String aPackageName, String aClassName, ResourceSet set) {
		return JavaRefFactory.eINSTANCE.reflectType(aPackageName, aClassName, set);
	}

	public void setSupertype(JavaClass aJavaClass) throws InheritanceCycleException {
		validateSupertype(aJavaClass);
		List s = super.getESuperTypes();
		s.clear();
		if (aJavaClass != null)
			s.add(aJavaClass);
	}

	/**
	 * Check to make sure that the passed JavaClass is a valid super class (i.e., it does not create any cycles in the inheritance.
	 * 
	 * @param aJavaClass
	 */
	protected void validateSupertype(JavaClass aJavaClass) throws InheritanceCycleException {
		if (!isValidSupertype(aJavaClass))
			throw new InheritanceCycleException(this, aJavaClass);
	}

	public boolean isValidSupertype(JavaClass aJavaClass) {
		if (aJavaClass != null) {
			if (this.equals(aJavaClass))
				return false;
			return extendedIsValidSupertype(getSubtypes(), aJavaClass);
		}
		return true;
	}

	/**
	 * @param subtypes
	 * @param aJavaClass
	 */
	private boolean extendedIsValidSupertype(List subtypes, JavaClass aJavaClass) {
		if (!basicIsValidSupertype(subtypes, aJavaClass))
			return false;
		JavaClass subtype;
		for (int i = 0; i < subtypes.size(); i++) {
			subtype = (JavaClass) subtypes.get(i);
			if (!subtype.isValidSupertype(aJavaClass))
				return false;
		}
		return true;
	}

	private boolean basicIsValidSupertype(List subtypes, JavaClass aJavaClass) {
		JavaClass subtype;
		for (int i = 0; i < subtypes.size(); i++) {
			subtype = (JavaClass) subtypes.get(i);
			if (subtype.equals(aJavaClass))
				return false;
		}
		return true;
	}

	protected List getSubtypes() {
		return getESuperAdapter().getSubclasses();
	}

	public String toString() {
		return getClass().getName() + "(" + getQualifiedName() + ")";
	}

	public TypeKind getKind() {
		reflectBase();
		return getKindGen();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setKind(TypeKind newKind) {
		TypeKind oldKind = kind;
		kind = newKind == null ? KIND_EDEFAULT : newKind;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, JavaRefPackage.JAVA_CLASS__KIND, oldKind, kind));
	}

	public boolean isPublic() {
		reflectBase();
		return isPublicGen();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setPublic(boolean newPublic) {
		boolean oldPublic = public_;
		public_ = newPublic;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, JavaRefPackage.JAVA_CLASS__PUBLIC, oldPublic, public_));
	}

	public boolean isFinal() {
		reflectBase();
		return isFinalGen();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setFinal(boolean newFinal) {
		boolean oldFinal = final_;
		final_ = newFinal;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, JavaRefPackage.JAVA_CLASS__FINAL, oldFinal, final_));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public TypeKind getKindGen() {
		return kind;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean isPublicGen() {
		return public_;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean isFinalGen() {
		return final_;
	}

	public EList getInitializers() {
		if (initializers == null) {
			initializers = new EObjectContainmentWithInverseEList(Initializer.class, this, JavaRefPackage.JAVA_CLASS__INITIALIZERS,
					JavaRefPackage.INITIALIZER__JAVA_CLASS);
		}
		return initializers;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setDeclaringClass(JavaClass newDeclaringClass) {
		if (newDeclaringClass != declaringClass) {
			NotificationChain msgs = null;
			if (declaringClass != null)
				msgs = ((InternalEObject)declaringClass).eInverseRemove(this, JavaRefPackage.JAVA_CLASS__DECLARED_CLASSES, JavaClass.class, msgs);
			if (newDeclaringClass != null)
				msgs = ((InternalEObject)newDeclaringClass).eInverseAdd(this, JavaRefPackage.JAVA_CLASS__DECLARED_CLASSES, JavaClass.class, msgs);
			msgs = basicSetDeclaringClass(newDeclaringClass, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, JavaRefPackage.JAVA_CLASS__DECLARING_CLASS, newDeclaringClass, newDeclaringClass));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public JavaClass getDeclaringClass() {
		if (declaringClass != null && declaringClass.eIsProxy()) {
			JavaClass oldDeclaringClass = declaringClass;
			declaringClass = (JavaClass)eResolveProxy((InternalEObject)declaringClass);
			if (declaringClass != oldDeclaringClass) {
				if (eNotificationRequired())
					eNotify(new ENotificationImpl(this, Notification.RESOLVE, JavaRefPackage.JAVA_CLASS__DECLARING_CLASS, oldDeclaringClass, declaringClass));
			}
		}
		return declaringClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public JavaClass basicGetDeclaringClass() {
		return declaringClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetDeclaringClass(JavaClass newDeclaringClass, NotificationChain msgs) {
		JavaClass oldDeclaringClass = declaringClass;
		declaringClass = newDeclaringClass;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, JavaRefPackage.JAVA_CLASS__DECLARING_CLASS, oldDeclaringClass, newDeclaringClass);
			if (msgs == null) msgs = notification; else msgs.add(notification);
		}
		return msgs;
	}

	public EList getDeclaredClasses() {
		reflectBase();
		return getDeclaredClassesGen();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList getDeclaredClassesGen() {
		if (declaredClasses == null) {
			declaredClasses = new EObjectWithInverseResolvingEList(JavaClass.class, this, JavaRefPackage.JAVA_CLASS__DECLARED_CLASSES, JavaRefPackage.JAVA_CLASS__DECLARING_CLASS);
		}
		return declaredClasses;
	}

	public EList getProperties() {
		return getEStructuralFeatures(); // As of EMF 2.0, local properties are the local features. Used to be a merge of eattributes and ereferences.
	}

	public EList getEvents() {
		IIntrospectionAdapter adapter = getIntrospectionAdapter();
		if (adapter != null)
			return adapter.getEvents();
		return getEventsGen();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList getEventsGen() {
		if (events == null) {
			events = new EObjectContainmentEList(JavaEvent.class, this, JavaRefPackage.JAVA_CLASS__EVENTS);
		}
		return events;
	}

	private EList allEvents;

	public EList getAllEvents() {
		IIntrospectionAdapter ia = getIntrospectionAdapter();
		if (ia == null)
			return ECollections.EMPTY_ELIST; // No introspection, do normal.
		return allEvents = ia.getAllEvents();
	}

	public EList getAllEventsGen() {
		return allEvents;
	}

	private EList allProperties;

	public EList getAllProperties() {
		IIntrospectionAdapter ia = getIntrospectionAdapter();
		if (ia == null)
			return ECollections.EMPTY_ELIST; // No introspection, do normal.
		return allProperties = ia.getAllProperties();
	}

	public EList getAllPropertiesGen() {
		return allProperties;
	}

	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	public boolean eIsSet(EStructuralFeature eFeature) {
		switch (eDerivedStructuralFeatureID(eFeature)) {
			case JavaRefPackage.JAVA_CLASS__EANNOTATIONS:
				return eAnnotations != null && !eAnnotations.isEmpty();
			case JavaRefPackage.JAVA_CLASS__NAME:
				return NAME_EDEFAULT == null ? name != null : !NAME_EDEFAULT.equals(name);
			case JavaRefPackage.JAVA_CLASS__INSTANCE_CLASS_NAME:
				return INSTANCE_CLASS_NAME_EDEFAULT == null ? instanceClassName != null : !INSTANCE_CLASS_NAME_EDEFAULT.equals(instanceClassName);
			case JavaRefPackage.JAVA_CLASS__INSTANCE_CLASS:
				return INSTANCE_CLASS_EDEFAULT == null ? getInstanceClass() != null : !INSTANCE_CLASS_EDEFAULT.equals(getInstanceClass());
			case JavaRefPackage.JAVA_CLASS__DEFAULT_VALUE:
				return DEFAULT_VALUE_EDEFAULT == null ? getDefaultValue() != null : !DEFAULT_VALUE_EDEFAULT.equals(getDefaultValue());
			case JavaRefPackage.JAVA_CLASS__EPACKAGE:
				return getEPackage() != null;
			case JavaRefPackage.JAVA_CLASS__ABSTRACT:
				return ((eFlags & ABSTRACT_EFLAG) != 0) != ABSTRACT_EDEFAULT;
			case JavaRefPackage.JAVA_CLASS__INTERFACE:
				return ((eFlags & INTERFACE_EFLAG) != 0) != INTERFACE_EDEFAULT;
			case JavaRefPackage.JAVA_CLASS__ESUPER_TYPES:
				return eSuperTypes != null && !eSuperTypes.isEmpty();
			case JavaRefPackage.JAVA_CLASS__EOPERATIONS:
				return eOperations != null && !eOperations.isEmpty();
			case JavaRefPackage.JAVA_CLASS__EALL_ATTRIBUTES:
				return !getEAllAttributes().isEmpty();
			case JavaRefPackage.JAVA_CLASS__EALL_REFERENCES:
				return !getEAllReferences().isEmpty();
			case JavaRefPackage.JAVA_CLASS__EREFERENCES:
				return !getEReferences().isEmpty();
			case JavaRefPackage.JAVA_CLASS__EATTRIBUTES:
				return !getEAttributes().isEmpty();
			case JavaRefPackage.JAVA_CLASS__EALL_CONTAINMENTS:
				return !getEAllContainments().isEmpty();
			case JavaRefPackage.JAVA_CLASS__EALL_OPERATIONS:
				return !getEAllOperations().isEmpty();
			case JavaRefPackage.JAVA_CLASS__EALL_STRUCTURAL_FEATURES:
				return !getEAllStructuralFeatures().isEmpty();
			case JavaRefPackage.JAVA_CLASS__EALL_SUPER_TYPES:
				return !getEAllSuperTypes().isEmpty();
			case JavaRefPackage.JAVA_CLASS__EID_ATTRIBUTE:
				return getEIDAttribute() != null;
			case JavaRefPackage.JAVA_CLASS__ESTRUCTURAL_FEATURES:
				return eStructuralFeatures != null && !eStructuralFeatures.isEmpty();
			case JavaRefPackage.JAVA_CLASS__KIND:
				return kind != KIND_EDEFAULT;
			case JavaRefPackage.JAVA_CLASS__PUBLIC:
				return public_ != PUBLIC_EDEFAULT;
			case JavaRefPackage.JAVA_CLASS__FINAL:
				return final_ != FINAL_EDEFAULT;
			case JavaRefPackage.JAVA_CLASS__IMPLEMENTS_INTERFACES:
				return implementsInterfaces != null && !implementsInterfaces.isEmpty();
			case JavaRefPackage.JAVA_CLASS__CLASS_IMPORT:
				return classImport != null && !classImport.isEmpty();
			case JavaRefPackage.JAVA_CLASS__PACKAGE_IMPORTS:
				return packageImports != null && !packageImports.isEmpty();
			case JavaRefPackage.JAVA_CLASS__FIELDS:
				return fields != null && !fields.isEmpty();
			case JavaRefPackage.JAVA_CLASS__METHODS:
				return methods != null && !methods.isEmpty();
			case JavaRefPackage.JAVA_CLASS__INITIALIZERS:
				return initializers != null && !initializers.isEmpty();
			case JavaRefPackage.JAVA_CLASS__DECLARED_CLASSES:
				return declaredClasses != null && !declaredClasses.isEmpty();
			case JavaRefPackage.JAVA_CLASS__DECLARING_CLASS:
				return declaringClass != null;
			case JavaRefPackage.JAVA_CLASS__JAVA_PACKAGE:
				return basicGetJavaPackage() != null;
			case JavaRefPackage.JAVA_CLASS__EVENTS:
				return events != null && !events.isEmpty();
			case JavaRefPackage.JAVA_CLASS__ALL_EVENTS:
				return !getAllEvents().isEmpty();
		}
		return eDynamicIsSet(eFeature);
	}

	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	public void eSet(EStructuralFeature eFeature, Object newValue) {
		switch (eDerivedStructuralFeatureID(eFeature)) {
			case JavaRefPackage.JAVA_CLASS__EANNOTATIONS:
				getEAnnotations().clear();
				getEAnnotations().addAll((Collection)newValue);
				return;
			case JavaRefPackage.JAVA_CLASS__NAME:
				setName((String)newValue);
				return;
			case JavaRefPackage.JAVA_CLASS__INSTANCE_CLASS_NAME:
				setInstanceClassName((String)newValue);
				return;
			case JavaRefPackage.JAVA_CLASS__ABSTRACT:
				setAbstract(((Boolean)newValue).booleanValue());
				return;
			case JavaRefPackage.JAVA_CLASS__INTERFACE:
				setInterface(((Boolean)newValue).booleanValue());
				return;
			case JavaRefPackage.JAVA_CLASS__ESUPER_TYPES:
				getESuperTypes().clear();
				getESuperTypes().addAll((Collection)newValue);
				return;
			case JavaRefPackage.JAVA_CLASS__EOPERATIONS:
				getEOperations().clear();
				getEOperations().addAll((Collection)newValue);
				return;
			case JavaRefPackage.JAVA_CLASS__ESTRUCTURAL_FEATURES:
				getEStructuralFeatures().clear();
				getEStructuralFeatures().addAll((Collection)newValue);
				return;
			case JavaRefPackage.JAVA_CLASS__KIND:
				setKind((TypeKind)newValue);
				return;
			case JavaRefPackage.JAVA_CLASS__PUBLIC:
				setPublic(((Boolean)newValue).booleanValue());
				return;
			case JavaRefPackage.JAVA_CLASS__FINAL:
				setFinal(((Boolean)newValue).booleanValue());
				return;
			case JavaRefPackage.JAVA_CLASS__IMPLEMENTS_INTERFACES:
				getImplementsInterfaces().clear();
				getImplementsInterfaces().addAll((Collection)newValue);
				return;
			case JavaRefPackage.JAVA_CLASS__CLASS_IMPORT:
				getClassImport().clear();
				getClassImport().addAll((Collection)newValue);
				return;
			case JavaRefPackage.JAVA_CLASS__PACKAGE_IMPORTS:
				getPackageImports().clear();
				getPackageImports().addAll((Collection)newValue);
				return;
			case JavaRefPackage.JAVA_CLASS__FIELDS:
				getFields().clear();
				getFields().addAll((Collection)newValue);
				return;
			case JavaRefPackage.JAVA_CLASS__METHODS:
				getMethods().clear();
				getMethods().addAll((Collection)newValue);
				return;
			case JavaRefPackage.JAVA_CLASS__INITIALIZERS:
				getInitializers().clear();
				getInitializers().addAll((Collection)newValue);
				return;
			case JavaRefPackage.JAVA_CLASS__DECLARED_CLASSES:
				getDeclaredClasses().clear();
				getDeclaredClasses().addAll((Collection)newValue);
				return;
			case JavaRefPackage.JAVA_CLASS__DECLARING_CLASS:
				setDeclaringClass((JavaClass)newValue);
				return;
			case JavaRefPackage.JAVA_CLASS__EVENTS:
				getEvents().clear();
				getEvents().addAll((Collection)newValue);
				return;
			case JavaRefPackage.JAVA_CLASS__ALL_EVENTS:
				getAllEvents().clear();
				getAllEvents().addAll((Collection)newValue);
				return;
		}
		eDynamicSet(eFeature, newValue);
	}

	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	public void eUnset(EStructuralFeature eFeature) {
		switch (eDerivedStructuralFeatureID(eFeature)) {
			case JavaRefPackage.JAVA_CLASS__EANNOTATIONS:
				getEAnnotations().clear();
				return;
			case JavaRefPackage.JAVA_CLASS__NAME:
				setName(NAME_EDEFAULT);
				return;
			case JavaRefPackage.JAVA_CLASS__INSTANCE_CLASS_NAME:
				setInstanceClassName(INSTANCE_CLASS_NAME_EDEFAULT);
				return;
			case JavaRefPackage.JAVA_CLASS__ABSTRACT:
				setAbstract(ABSTRACT_EDEFAULT);
				return;
			case JavaRefPackage.JAVA_CLASS__INTERFACE:
				setInterface(INTERFACE_EDEFAULT);
				return;
			case JavaRefPackage.JAVA_CLASS__ESUPER_TYPES:
				getESuperTypes().clear();
				return;
			case JavaRefPackage.JAVA_CLASS__EOPERATIONS:
				getEOperations().clear();
				return;
			case JavaRefPackage.JAVA_CLASS__ESTRUCTURAL_FEATURES:
				getEStructuralFeatures().clear();
				return;
			case JavaRefPackage.JAVA_CLASS__KIND:
				setKind(KIND_EDEFAULT);
				return;
			case JavaRefPackage.JAVA_CLASS__PUBLIC:
				setPublic(PUBLIC_EDEFAULT);
				return;
			case JavaRefPackage.JAVA_CLASS__FINAL:
				setFinal(FINAL_EDEFAULT);
				return;
			case JavaRefPackage.JAVA_CLASS__IMPLEMENTS_INTERFACES:
				getImplementsInterfaces().clear();
				return;
			case JavaRefPackage.JAVA_CLASS__CLASS_IMPORT:
				getClassImport().clear();
				return;
			case JavaRefPackage.JAVA_CLASS__PACKAGE_IMPORTS:
				getPackageImports().clear();
				return;
			case JavaRefPackage.JAVA_CLASS__FIELDS:
				getFields().clear();
				return;
			case JavaRefPackage.JAVA_CLASS__METHODS:
				getMethods().clear();
				return;
			case JavaRefPackage.JAVA_CLASS__INITIALIZERS:
				getInitializers().clear();
				return;
			case JavaRefPackage.JAVA_CLASS__DECLARED_CLASSES:
				getDeclaredClasses().clear();
				return;
			case JavaRefPackage.JAVA_CLASS__DECLARING_CLASS:
				setDeclaringClass((JavaClass)null);
				return;
			case JavaRefPackage.JAVA_CLASS__EVENTS:
				getEvents().clear();
				return;
			case JavaRefPackage.JAVA_CLASS__ALL_EVENTS:
				getAllEvents().clear();
				return;
		}
		eDynamicUnset(eFeature);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 *  
	 */
	public EList getImplementsInterfacesGen() {
		if (implementsInterfaces == null) {
			implementsInterfaces = new EObjectResolvingEList(JavaClass.class, this, JavaRefPackage.JAVA_CLASS__IMPLEMENTS_INTERFACES) {

				/**
				 * Comment for <code>serialVersionUID</code>
				 * 
				 * @since 1.1.0
				 */
				private static final long serialVersionUID = 1L;

				public Object get(int index) {
					if (isInterface())
						getInterfaceSuperTypes().get(index); //force resolution so the ESuperAdapter will be updated correctly
					return super.get(index);
				}

				public void clear() {
					super.clear();
					if (isInterface())
						getInterfaceSuperTypes().clear();
				}

				public Object remove(int index) {
					Object result = super.remove(index);
					if (isInterface())
						getInterfaceSuperTypes().remove(index);
					return result;

				}

				public boolean removeAll(Collection collection) {
					boolean result = super.removeAll(collection);
					if (isInterface())
						getInterfaceSuperTypes().removeAll(collection);
					return result;
				}

				public void add(int index, Object object) {
					super.add(index, object);
					if (isInterface())
						getInterfaceSuperTypes().add(index, object);
				}

				public boolean add(Object object) {
					boolean result = super.add(object);
					if (isInterface())
						getInterfaceSuperTypes().add(object);
					return result;
				}

				public boolean addAll(Collection collection) {
					boolean result = super.addAll(collection);
					if (isInterface())
						getInterfaceSuperTypes().addAll(collection);
					return result;
				}

				public boolean addAll(int index, Collection collection) {
					boolean result = super.addAll(index, collection);
					if (isInterface())
						getInterfaceSuperTypes().addAll(index, collection);
					return result;
				}
			};
		}
		return implementsInterfaces;
	}

	private EList getInterfaceSuperTypes() {
		return super.getESuperTypes();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList getClassImportGen() {
		if (classImport == null) {
			classImport = new EObjectResolvingEList(JavaClass.class, this, JavaRefPackage.JAVA_CLASS__CLASS_IMPORT);
		}
		return classImport;
	}

	public EList getEAllSuperTypes() {
		reflectBase();//Force reflection, if needed, before getting all supertypes.
		return super.getEAllSuperTypes();
	}

	public EList getESuperTypes() {
		reflectBase();
		return super.getESuperTypes();
	}
	
	/**
	 * This method is not meant to be used by others. It is here so that the reflection
	 * adapters can reference the unreflected supertypes.
	 *  
	 * @return
	 * 
	 * @since 1.1.0
	 */
	public EList primGetESuperTypes() {
		return super.getESuperTypes();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList getPackageImportsGen() {
		if (packageImports == null) {
			packageImports = new EObjectResolvingEList(JavaPackage.class, this, JavaRefPackage.JAVA_CLASS__PACKAGE_IMPORTS);
		}
		return packageImports;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList getFieldsGen() {
		if (fields == null) {
			fields = new EObjectContainmentWithInverseEList(Field.class, this, JavaRefPackage.JAVA_CLASS__FIELDS, JavaRefPackage.FIELD__JAVA_CLASS);
		}
		return fields;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList getMethodsGen() {
		if (methods == null) {
			methods = new EObjectContainmentWithInverseEList(Method.class, this, JavaRefPackage.JAVA_CLASS__METHODS, JavaRefPackage.METHOD__JAVA_CLASS);
		}
		return methods;
	}

	/**
	 * @generated This field/method will be replaced during code generation 
	 */
	public JavaPackage getJavaPackageGen() {
		JavaPackage javaPackage = basicGetJavaPackage();
		return javaPackage == null ? null : (JavaPackage)eResolveProxy((InternalEObject)javaPackage);
	}

	/*
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 */
	public JavaPackage basicGetJavaPackage() {
		return getJavaPackage();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain eInverseAdd(InternalEObject otherEnd, int featureID, Class baseClass, NotificationChain msgs) {
		if (featureID >= 0) {
			switch (eDerivedStructuralFeatureID(featureID, baseClass)) {
				case JavaRefPackage.JAVA_CLASS__EANNOTATIONS:
					return ((InternalEList)getEAnnotations()).basicAdd(otherEnd, msgs);
				case JavaRefPackage.JAVA_CLASS__EPACKAGE:
					if (eContainer != null)
						msgs = eBasicRemoveFromContainer(msgs);
					return eBasicSetContainer(otherEnd, JavaRefPackage.JAVA_CLASS__EPACKAGE, msgs);
				case JavaRefPackage.JAVA_CLASS__EOPERATIONS:
					return ((InternalEList)getEOperations()).basicAdd(otherEnd, msgs);
				case JavaRefPackage.JAVA_CLASS__ESTRUCTURAL_FEATURES:
					return ((InternalEList)getEStructuralFeatures()).basicAdd(otherEnd, msgs);
				case JavaRefPackage.JAVA_CLASS__FIELDS:
					return ((InternalEList)getFields()).basicAdd(otherEnd, msgs);
				case JavaRefPackage.JAVA_CLASS__METHODS:
					return ((InternalEList)getMethods()).basicAdd(otherEnd, msgs);
				case JavaRefPackage.JAVA_CLASS__INITIALIZERS:
					return ((InternalEList)getInitializers()).basicAdd(otherEnd, msgs);
				case JavaRefPackage.JAVA_CLASS__DECLARED_CLASSES:
					return ((InternalEList)getDeclaredClasses()).basicAdd(otherEnd, msgs);
				case JavaRefPackage.JAVA_CLASS__DECLARING_CLASS:
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
				case JavaRefPackage.JAVA_CLASS__EANNOTATIONS:
					return ((InternalEList)getEAnnotations()).basicRemove(otherEnd, msgs);
				case JavaRefPackage.JAVA_CLASS__EPACKAGE:
					return eBasicSetContainer(null, JavaRefPackage.JAVA_CLASS__EPACKAGE, msgs);
				case JavaRefPackage.JAVA_CLASS__EOPERATIONS:
					return ((InternalEList)getEOperations()).basicRemove(otherEnd, msgs);
				case JavaRefPackage.JAVA_CLASS__ESTRUCTURAL_FEATURES:
					return ((InternalEList)getEStructuralFeatures()).basicRemove(otherEnd, msgs);
				case JavaRefPackage.JAVA_CLASS__FIELDS:
					return ((InternalEList)getFields()).basicRemove(otherEnd, msgs);
				case JavaRefPackage.JAVA_CLASS__METHODS:
					return ((InternalEList)getMethods()).basicRemove(otherEnd, msgs);
				case JavaRefPackage.JAVA_CLASS__INITIALIZERS:
					return ((InternalEList)getInitializers()).basicRemove(otherEnd, msgs);
				case JavaRefPackage.JAVA_CLASS__DECLARED_CLASSES:
					return ((InternalEList)getDeclaredClasses()).basicRemove(otherEnd, msgs);
				case JavaRefPackage.JAVA_CLASS__DECLARING_CLASS:
					return basicSetDeclaringClass(null, msgs);
				case JavaRefPackage.JAVA_CLASS__EVENTS:
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
				case JavaRefPackage.JAVA_CLASS__EPACKAGE:
					return eContainer.eInverseRemove(this, EcorePackage.EPACKAGE__ECLASSIFIERS, EPackage.class, msgs);
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
			case JavaRefPackage.JAVA_CLASS__EANNOTATIONS:
				return getEAnnotations();
			case JavaRefPackage.JAVA_CLASS__NAME:
				return getName();
			case JavaRefPackage.JAVA_CLASS__INSTANCE_CLASS_NAME:
				return getInstanceClassName();
			case JavaRefPackage.JAVA_CLASS__INSTANCE_CLASS:
				return getInstanceClass();
			case JavaRefPackage.JAVA_CLASS__DEFAULT_VALUE:
				return getDefaultValue();
			case JavaRefPackage.JAVA_CLASS__EPACKAGE:
				return getEPackage();
			case JavaRefPackage.JAVA_CLASS__ABSTRACT:
				return isAbstract() ? Boolean.TRUE : Boolean.FALSE;
			case JavaRefPackage.JAVA_CLASS__INTERFACE:
				return isInterface() ? Boolean.TRUE : Boolean.FALSE;
			case JavaRefPackage.JAVA_CLASS__ESUPER_TYPES:
				return getESuperTypes();
			case JavaRefPackage.JAVA_CLASS__EOPERATIONS:
				return getEOperations();
			case JavaRefPackage.JAVA_CLASS__EALL_ATTRIBUTES:
				return getEAllAttributes();
			case JavaRefPackage.JAVA_CLASS__EALL_REFERENCES:
				return getEAllReferences();
			case JavaRefPackage.JAVA_CLASS__EREFERENCES:
				return getEReferences();
			case JavaRefPackage.JAVA_CLASS__EATTRIBUTES:
				return getEAttributes();
			case JavaRefPackage.JAVA_CLASS__EALL_CONTAINMENTS:
				return getEAllContainments();
			case JavaRefPackage.JAVA_CLASS__EALL_OPERATIONS:
				return getEAllOperations();
			case JavaRefPackage.JAVA_CLASS__EALL_STRUCTURAL_FEATURES:
				return getEAllStructuralFeatures();
			case JavaRefPackage.JAVA_CLASS__EALL_SUPER_TYPES:
				return getEAllSuperTypes();
			case JavaRefPackage.JAVA_CLASS__EID_ATTRIBUTE:
				return getEIDAttribute();
			case JavaRefPackage.JAVA_CLASS__ESTRUCTURAL_FEATURES:
				return getEStructuralFeatures();
			case JavaRefPackage.JAVA_CLASS__KIND:
				return getKind();
			case JavaRefPackage.JAVA_CLASS__PUBLIC:
				return isPublic() ? Boolean.TRUE : Boolean.FALSE;
			case JavaRefPackage.JAVA_CLASS__FINAL:
				return isFinal() ? Boolean.TRUE : Boolean.FALSE;
			case JavaRefPackage.JAVA_CLASS__IMPLEMENTS_INTERFACES:
				return getImplementsInterfaces();
			case JavaRefPackage.JAVA_CLASS__CLASS_IMPORT:
				return getClassImport();
			case JavaRefPackage.JAVA_CLASS__PACKAGE_IMPORTS:
				return getPackageImports();
			case JavaRefPackage.JAVA_CLASS__FIELDS:
				return getFields();
			case JavaRefPackage.JAVA_CLASS__METHODS:
				return getMethods();
			case JavaRefPackage.JAVA_CLASS__INITIALIZERS:
				return getInitializers();
			case JavaRefPackage.JAVA_CLASS__DECLARED_CLASSES:
				return getDeclaredClasses();
			case JavaRefPackage.JAVA_CLASS__DECLARING_CLASS:
				if (resolve) return getDeclaringClass();
				return basicGetDeclaringClass();
			case JavaRefPackage.JAVA_CLASS__JAVA_PACKAGE:
				if (resolve) return getJavaPackage();
				return basicGetJavaPackage();
			case JavaRefPackage.JAVA_CLASS__EVENTS:
				return getEvents();
			case JavaRefPackage.JAVA_CLASS__ALL_EVENTS:
				return getAllEvents();
		}
		return eDynamicGet(eFeature, resolve);
	}

	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	public String toStringGen() {
		if (eIsProxy()) return super.toString();

		StringBuffer result = new StringBuffer(super.toString());
		result.append(" (kind: ");
		result.append(kind);
		result.append(", public: ");
		result.append(public_);
		result.append(", final: ");
		result.append(final_);
		result.append(')');
		return result.toString();
	}

	/*
	 * This should never be called with true. It is basically only for reset of reflection, not to set a particular state. But InternalReadAdaptable
	 * may be used by someone that shouldn't so to be be safe we keep it. TODO Remove InternalReadAdaptable in next version. Need to wait because we
	 * need time to notify everyone.
	 * 
	 * @see org.eclipse.jem.java.adapters.InternalReadAdaptable#setReflected(boolean)
	 */
	public synchronized void setReflected(boolean aBoolean) {
		if (!aBoolean)
			reflectionStatus = NOT_REFLECTED;
	}
	/* (non-Javadoc)
	 * @see org.eclipse.jem.java.JavaClass#getReflectionType()
	 */
	public Object getReflectionType() {
		JavaReflectionAdaptor ja = ((JavaReflectionAdaptor) getReadAdapter());
		return ja != null ? ja.getReflectionSource() : null;
	}
}
