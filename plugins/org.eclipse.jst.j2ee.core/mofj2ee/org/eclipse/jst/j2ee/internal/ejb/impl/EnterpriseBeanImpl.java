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

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;

import org.eclipse.emf.common.notify.Adapter;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.common.util.Enumerator;
import org.eclipse.emf.ecore.EAnnotation;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.util.EObjectContainmentEList;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.emf.ecore.util.InternalEList;
import org.eclipse.jem.java.JavaClass;
import org.eclipse.jem.java.JavaHelpers;
import org.eclipse.jem.java.JavaParameter;
import org.eclipse.jem.java.JavaRefFactory;
import org.eclipse.jem.java.JavaRefPackage;
import org.eclipse.jem.java.Method;
import org.eclipse.jst.j2ee.common.EJBLocalRef;
import org.eclipse.jst.j2ee.common.EjbRef;
import org.eclipse.jst.j2ee.common.J2EEVersionResource;
import org.eclipse.jst.j2ee.common.SecurityIdentity;
import org.eclipse.jst.j2ee.common.SecurityRoleRef;
import org.eclipse.jst.j2ee.ejb.EJBJar;
import org.eclipse.jst.j2ee.ejb.EjbFactory;
import org.eclipse.jst.j2ee.ejb.EjbMethodElementComparator;
import org.eclipse.jst.j2ee.ejb.EnterpriseBean;
import org.eclipse.jst.j2ee.ejb.MethodElement;
import org.eclipse.jst.j2ee.ejb.MethodElementKind;
import org.eclipse.jst.j2ee.ejb.MethodPermission;
import org.eclipse.jst.j2ee.ejb.MethodTransaction;
import org.eclipse.jst.j2ee.internal.J2EEVersionConstants;
import org.eclipse.jst.j2ee.internal.common.impl.JNDIEnvRefsGroupImpl;
import org.eclipse.jst.j2ee.internal.ejb.EjbPackage;
import org.eclipse.wst.common.internal.emf.utilities.Revisit;

/**
 * @generated
 * EnterpriseJavaBean is a class.  It can have instances, someone could write an instance document containing Departments and Employees.  It also has attributes, operations, and associations.  These are actually derived//filtered from its implementation classes and interfaces.  For mapping and browsing purposes, though, you would like the EJB to appear as a class.  
 * 
 * In this light, even Session Beans can have associations and properties implemented by their bean.  For example, it would be meaningful to describe associations from a Session to the Entities which it uses to perform its work.
 */
public abstract class EnterpriseBeanImpl extends JNDIEnvRefsGroupImpl implements EnterpriseBean {

	protected final static Method[] EMPTY_METHOD_ARRAY = new Method[0];
	protected static final String METHOD_ELEMENT_NAME = "MethodElement"; //$NON-NLS-1$
	/**
	 * The default value of the '{@link #getName() <em>Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getName()
	 * @generated
	 * @ordered
	 */
	protected static final String NAME_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getName() <em>Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getName()
	 * @generated
	 * @ordered
	 */
	protected String name = NAME_EDEFAULT;

	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	protected EList securityRoleRefs = null;
	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	protected JavaClass ejbClass = null;
	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	protected JavaClass homeInterface = null;
	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	protected JavaClass remoteInterface = null;
	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	protected SecurityIdentity securityIdentity = null;
	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	protected JavaClass localHomeInterface = null;
	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	protected JavaClass localInterface = null;
	protected EjbMethodElementComparator defaultMethodElementComparator;
	public EnterpriseBeanImpl() {
		super();
	}
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected EClass eStaticClass() {
		return EjbPackage.eINSTANCE.getEnterpriseBean();
	}

	/**
	 * createClassRef - return a JavaURL reference to the named Java class
	 */
	public JavaClass createClassRef(String targetName) {
		return getJavaRefFactory().createClassRef(targetName);
	}

	protected List createMethodElements(List signatures, Enumerator aType) {
		return createMethodElements(METHOD_ELEMENT_NAME, signatures, aType);
	}
	
	protected List createMethodElements(String meTypeName, List signatures, Enumerator aType) {
		int size = signatures.size();
		List methodElements = new ArrayList(size);
		String signature;
		MethodElement me;
		for (int i = 0; i < size; i++) {
			signature = (String) signatures.get(i);
			me = (MethodElement) getEjbFactory().create((EClass)getEjbPackage().getEClassifier(meTypeName));
			me.initializeFromSignature(signature);
			me.setEnterpriseBean(this);
			me.setType((MethodElementKind)aType);
			methodElements.add(me);
		}
		return methodElements;
	}

	public List createMethodElements(List someMethods, JavaClass anInterface) {
		return createMethodElements(METHOD_ELEMENT_NAME, someMethods, anInterface);
	}
	
	public List createMethodElements(String meTypeName, List someMethods, JavaClass anInterface) {
		if (anInterface == null)
			return Collections.EMPTY_LIST;
		List signatures = getMethodElementSignatures(someMethods);
		Collections.sort(signatures);
		Enumerator enumerator = getMethodElementKindLiteral(anInterface);
		return createMethodElements(meTypeName, signatures, enumerator);
	}
	
	protected Enumerator getMethodElementKindLiteral(JavaClass anInterface) {
		Enumerator enumerator = MethodElementKind.UNSPECIFIED_LITERAL;
		if (anInterface == getHomeInterface())
			enumerator = MethodElementKind.HOME_LITERAL;
		else if (anInterface == getRemoteInterface())
			enumerator = MethodElementKind.REMOTE_LITERAL;
		else if (anInterface == getLocalHomeInterface())
			enumerator = MethodElementKind.LOCAL_HOME_LITERAL;
		else if (anInterface == getLocalInterface())
			enumerator = MethodElementKind.LOCAL_LITERAL;
		return enumerator;
	}
	
	protected List getMethodElementSignatures(List someMethods) {
		int size = someMethods.size();
		List signatures = new ArrayList(size);
		Method method;
		for (int i = 0; i < size; i++) {
			method = (Method) someMethods.get(i);
			signatures.add(method.getMethodElementSignature());
		}
		return signatures;
	}
	/**
	 * This method will return a List of newly created MethodElements
	 * that represent all the possible MethodElements that can be created
	 * and are common to both the Home and Remote interfaces.  These MethodElements are not contained and will
	 * not be serialized and should not be added to the model.  Their intention is
	 * solely for comparing with actual MethodElements contained in the model.
	 * @deprecated use getAvailableUnspecifiedMethodElements();
	 */
	public List getAvailableCommonMethodElements() {
		return getAvailableUnspecifiedMethodElements();
	}
	/**
	 * This method will return a List of newly created MethodElements
	 * that represent all the possible MethodElements that can be created
	 * and are common to both the Home and Remote interfaces.  These MethodElements are not contained and will
	 * not be serialized and should not be added to the model.  Their intention is
	 * solely for comparing with actual MethodElements contained in the model.
	 */
	public List getAvailableUnspecifiedMethodElements() {
		List commonSigs = getAvailableUnspecifiedMethodElementSignatures();
		return createMethodElements(commonSigs, MethodElementKind.UNSPECIFIED_LITERAL);
	}
	protected List gatherCommonSignatures(List sigCollections) {
		if (sigCollections.isEmpty()) return sigCollections;
		List first = (List)sigCollections.get(0);
		if (sigCollections.size() == 1) return first;
		List result = new ArrayList(first.size());
		sigCollections.remove(0);
		String sig = null;
		boolean contains = false;
		for (int i = 0; i < first.size(); i++) {
			contains = false;
			sig = (String) first.get(i);
			List list = null;
			for (int j = 0; j < sigCollections.size(); j++) {
				list = (List) sigCollections.get(j);
				if (list.contains(sig))
					contains = true;
			}
			if (contains)
				result.add(sig);
		}
		return result;
	}
	/**
	 * Return a List of MethodElements that are set for a particular EObject
	 * which can be a MethodTransaction or a MethodPermission.
	 */
	protected List getExistingMethodElements(EObject refObject) {
		if (refObject != null) {
			EjbPackage pack = getEjbPackage();
			if (refObject.eClass() == pack.getMethodTransaction())
				return ((MethodTransaction)refObject).getMethodElements(this);
			if (refObject.eClass() == pack.getMethodPermission())
				return ((MethodPermission)refObject).getMethodElements(this);
		}
		return Collections.EMPTY_LIST;
	}
	/**
	 * This method will return a List of MethodElements signatures
	 * that represent all the possible MethodElements that can be created
	 * and are common to both the Home and Remote interfaces.
	 * @deprecated use getAvailableUnspecifiedMethodElementSignatures();
	 */
	public List getAvailableCommonMethodElementSignatures() {
		return getAvailableUnspecifiedMethodElementSignatures();
	}
	/**
	 * This method will return a List of MethodElements signatures
	 * that represent all the possible MethodElements that can be created
	 * and are common to both the Home and Remote interfaces.
	 */
	public List getAvailableUnspecifiedMethodElementSignatures() {
		List sigCollections = new ArrayList();
		if (getHomeInterface() != null)
			sigCollections.add(getHomeInterface().getMethodElementSignatures());
		if (getRemoteInterface() != null)
			sigCollections.add(getRemoteInterface().getMethodElementSignatures());
		if (getLocalHomeInterface() != null)
			sigCollections.add(getLocalHomeInterface().getMethodElementSignatures());
		if (getLocalInterface() != null)
			sigCollections.add(getLocalInterface().getMethodElementSignatures());	
		return gatherCommonSignatures(sigCollections);
	}
	/**
	 * This method will return a List of newly created MethodElements
	 * that represent all the possible MethodElements that can be created
	 * for the Home interface.  These MethodElements are not contained and will
	 * not be serialized and should not be added to the model.  Their intention is
	 * solely for comparing with actual MethodElements contained in the model.
	 */
	public List getAvailableHomeMethodElements() {
		return getAvailableMethodElements(getHomeInterface(), MethodElementKind.HOME_LITERAL);
	}
	/**
	 * This method will return a List of newly created MethodElements
	 * that represent all the possible MethodElements that can be created
	 * for the Local Home interface.  These MethodElements are not contained and will
	 * not be serialized and should not be added to the model.  Their intention is
	 * solely for comparing with actual MethodElements contained in the model.
	 */
	public List getAvailableLocalHomeMethodElements() {
		return getAvailableMethodElements(getLocalHomeInterface(), MethodElementKind.LOCAL_HOME_LITERAL);
	}
	/**
	 * This method will return a List of newly created MethodElements
	 * that represent all the possible MethodElements that can be created
	 * for the Local interface.  These MethodElements are not contained and will
	 * not be serialized and should not be added to the model.  Their intention is
	 * solely for comparing with actual MethodElements contained in the model.
	 */
	public List getAvailableLocalMethodElements() {
		return getAvailableMethodElements(getLocalInterface(), MethodElementKind.LOCAL_LITERAL);
	}

	public List getAvailableServiceEndpointMethodElements(){
		return getAvailableMethodElements(getServiceEndpoint(), MethodElementKind.SERVICE_ENDPOINT_LITERAL);
	}
	
	protected List getAvailableMethodElements(JavaClass anInterface, Enumerator aType) {
		if (anInterface == null)
			return Collections.EMPTY_LIST;
		List signatures = anInterface.getMethodElementSignatures();
		return createMethodElements(signatures, aType);
	}
	/**
	 * This method will return a List of newly created MethodElements
	 * that represent all the possible MethodElements that can be created
	 * for the Remote interface.  These MethodElements are not contained and will
	 * not be serialized and should not be added to the model.  Their intention is
	 * solely for comparing with actual MethodElements contained in the model.
	 */
	public List getAvailableRemoteMethodElements() {
		return getAvailableMethodElements(getRemoteInterface(), MethodElementKind.REMOTE_LITERAL);
	}
	
	/*
	 * See EnterpriseBean#getExistingOrAvailableMethodElements(EObject, Comparator);
	 */
	public List getExistingOrAvailableMethodElements(EObject refObject, Comparator comparator) {
		List result = new ArrayList();
		result.addAll(getAvailableHomeMethodElements());
		result.addAll(getAvailableRemoteMethodElements());
		result.addAll(getAvailableLocalHomeMethodElements());
		result.addAll(getAvailableLocalMethodElements());
		result.addAll(getAvailableServiceEndpointMethodElements());
		result.addAll(getAvailableUnspecifiedMethodElements());
		unionMethodElements(result, getExistingMethodElements(refObject));
		Collections.sort(result, comparator);
		return result;
	}
	/*
	 * See EnterpriseBean#getExistingOrAvailableMethodElements(EObject);
	 */
	public List getExistingOrAvailableMethodElements(EObject refObject) {
		return getExistingOrAvailableMethodElements(refObject, getDefaultMethodElementComparator());	
	}
	
	protected EjbMethodElementComparator getDefaultMethodElementComparator() {
		if (defaultMethodElementComparator == null)
			defaultMethodElementComparator = new EjbMethodElementComparator();
		return defaultMethodElementComparator;
	}
	protected void unionMethodElements(List sourceMethodElements, List additionalMethodElements) {
		MethodElement sourceME, additionalME;
		int sourceSize, addSize;
		sourceSize = sourceMethodElements.size();
		addSize = additionalMethodElements.size();
		List foundElements = new ArrayList(addSize);
		boolean found = false;
		for (int i = 0; i < addSize; i++){
			found = false;
			additionalME = (MethodElement) additionalMethodElements.get(i);
			sourceME = null;
			for (int ii = 0; ii < sourceSize; ii++){
				sourceME = (MethodElement) sourceMethodElements.get(ii);
				if (sourceME.isEquivalent(additionalME)) {
					found = true;
					break;
				}
			}
			if (found)
				foundElements.add(sourceME);
		}
		sourceMethodElements.removeAll(foundElements);
		sourceMethodElements.addAll(additionalMethodElements);
	}
	public String getEjbClassName() {
		getEjbClass();
		return (ejbClass == null) ? null : ejbClass.getQualifiedName();
	}
	protected EjbPackage getEjbPackage() {
		return EjbFactoryImpl.getPackage();
	}
	protected EjbFactory getEjbFactory() {
		return ((EjbPackage)EPackage.Registry.INSTANCE.getEPackage(EjbPackage.eNS_URI)).getEjbFactory();
	}
	public EjbRef getEquivalentEjbRef(EjbRef anEjbRef) {
		return getEquivalentEjbRef(anEjbRef, getEjbRefs());
	}
	public EJBLocalRef getEquivalentEJBLocalRef(EJBLocalRef anEjbLocalRef) {
		return (EJBLocalRef) getEquivalentEjbRef(anEjbLocalRef, getEjbLocalRefs());
	}
	private EjbRef getEquivalentEjbRef(EjbRef anEjbRef, List someRefs) {
		if (anEjbRef == null)
			return null;
		int size = someRefs.size();
		EjbRef ref;
		for (int i = 0; i < size; i++) {
			ref = (EjbRef) someRefs.get(i);
			if (ref.isEquivalent(anEjbRef))
				return ref;
		}
		return null;
	}
	public String getHomeInterfaceName() {
		getHomeInterface();
		return homeInterface == null ? null : homeInterface.getQualifiedName();
	}
	/**
	 * See <code>getMethods<code> for comment
	 */
	public Method[] getHomeMethodsForDeployment() {
		return (getHomeInterface() != null ? getMethods(getHomeInterface()) : EMPTY_METHOD_ARRAY);
	}
	private JavaRefFactory getJavaRefFactory() {
		return ((JavaRefPackage)EPackage.Registry.INSTANCE.getEPackage(JavaRefPackage.eNS_URI)).getJavaRefFactory();
	}
	public EjbRef getLinkedEJBReference(EnterpriseBean anEJB) {
		return getLinkedEJBReference(anEJB, getEjbRefs());
	}
	public EJBLocalRef getLinkedEJBLocalReference(EnterpriseBean anEJB) {
		return (EJBLocalRef) getLinkedEJBReference(anEJB, getEjbLocalRefs());
	}
	private EjbRef getLinkedEJBReference(EnterpriseBean anEJB, List someRefs) {
		if (anEJB == null)
			return null;
		int size = someRefs.size();
		EjbRef ref;
		for (int i = 0; i < size; i++) {
			ref = (EjbRef) someRefs.get(i);
			if (ref.isLinked(anEJB))
				return ref;
		}
		return null;
	}
	public String getLocalHomeInterfaceName() {
		getLocalHomeInterface();
		return localHomeInterface == null ? null : localHomeInterface.getQualifiedName();
	}
	/**
	 * See <code>getMethods<code> for comment
	 */
	public Method[] getLocalHomeMethodsForDeployment() {
		return (getLocalHomeInterface() != null ? getMethods(getLocalHomeInterface()) : EMPTY_METHOD_ARRAY);
	}
	public String getLocalInterfaceName() {
		getLocalInterface();
		return localInterface == null ? null : localInterface.getQualifiedName();
	}
	/**
	 * Retrieve an ordered list of methods for the given JavaClass.
	 * The list should include all non-static, public methods that are
	 * not inherited from javax.ejb.EJBObject
	 * This uses the algorithm that web sphere currently uses, and could
	 * be subject to change
	 * Creation date: (8/21/00 1:41:03 PM)
	 * @param intf JavaClass
	 */
	public static Method[] getMethods(JavaClass intf) {
		Method[] allMethods = intf.listMethodExtended();
		java.util.Vector resultVector = new java.util.Vector(allMethods.length);
		java.util.Hashtable methodNameTable = new java.util.Hashtable();

		//----------------------------------------------------------------
		// Iterate over the list returned by "listMethodExtended" and
		// filter out methods belonging to the EJBObject interface
		// and method synonyms. 
		//
		// Method synonyms correspond to overrides. For all synonyms
		// keep the method instance declared on the most specific class. 
		// How do you determine the most specific class? Don't know.
		// For now, if the method is declared on the interface class
		// it always overrides others.
		//----------------------------------------------------------------

		Method[] result = null;

		for (int i = 0; i < allMethods.length; i++) {
			Method m = allMethods[i];

			//---------------------------
			// Filter out static methods
			//---------------------------

			if (m.isStatic()) {
				continue;
			}

			String mKey = methodKey(m);

			String interfaceName = m.getContainingJavaClass().getJavaName();
			if (!interfaceName.equals("javax.ejb.EJBObject")) { //$NON-NLS-1$
				Method synonym = (Method) methodNameTable.get(mKey);
				if (synonym == null) {
					methodNameTable.put(mKey, m);
					resultVector.addElement(m);
				} else {
					//---------------------------------------------
					// Method declared on most specific class wins
					//---------------------------------------------
					JavaClass mClass = m.getContainingJavaClass();
					JavaClass sClass = synonym.getContainingJavaClass();
					if (sClass.implementsInterface(mClass)) {
						resultVector.removeElement(synonym);
						methodNameTable.put(mKey, m);
						resultVector.addElement(m);
					}
				}
			}
		}

		result = new Method[resultVector.size()];
		resultVector.copyInto(result);
		return sortMethods(result);
	}
	public String getRemoteInterfaceName() {
		getRemoteInterface();
		return remoteInterface == null ? null : remoteInterface.getQualifiedName();
	}
	/**
	 * See <code>getMethods<code> for comment
	 */
	public Method[] getRemoteMethodsForDeployment() {
		return getMethods(getRemoteInterface());
	}
	/**
	 * Return true if @aJavaClass as equal to the
	 * home, remote, bean class, or key class.
	 */
	public boolean hasJavaReference(JavaClass aJavaClass) {
		if (aJavaClass != null) {
			return aJavaClass.equals(getEjbClass())||
					aJavaClass.equals(getRemoteInterface()) || 
					aJavaClass.equals(getHomeInterface()) || 
					aJavaClass.equals(getLocalInterface()) ||
					aJavaClass.equals(getLocalHomeInterface());
		}
		return false;
	}
	/**
	 * Return true if this ejb has BeanManagedPersistance.
	 */
	public boolean isBeanManagedEntity() {
		return isEntity() && !isContainerManagedEntity();
	}
	/**
	 * isContainerManagedEntity method comment.
	 */
	public boolean isContainerManagedEntity() {
		return false;
	}
	//Return false by default
	public boolean isEntity() {
		return false;
	}
	//Return false by default
	public boolean isMessageDriven() {
		return false;
	}
	//Return false by default
	public boolean isSession() {
		return false;
	}
	/**
	 * @deprecated - Use getEjbJar().getVersionID()
	 * isVersion1_X method comment.
	 */
	public boolean isVersion1_X() {
		if (getEjbJar() != null)
			return getEjbJar().getVersionID() <= J2EEVersionConstants.EJB_1_1_ID;
		Revisit.revisit(); //jsholl this should throw an exception. if there is not JAR then the version is undefined
		return false;
	}
	/**
	 * @deprecated - Use getEjbJar().getVersionID()
	 * isVersion2_X method comment.
	 */
	public boolean isVersion2_X() {
		if (getEjbJar() != null) {
			return getEjbJar().getVersionID() >= J2EEVersionConstants.EJB_1_1_ID;
		}
		Revisit.revisit(); //jsholl this should throw an exception. if there is not JAR then the version is undefined
		return false;
	}
	
	/* (non-Javadoc)
	 * @see org.eclipse.jst.j2ee.internal.ejb.EnterpriseBean#getVersionID()
	 */
	public int getVersionID() throws IllegalStateException {
		J2EEVersionResource res = (J2EEVersionResource) eResource();
		if (res == null) throw new IllegalStateException();
		return res.getModuleVersionID();
	}

	
	/*
	 * @see EnterpriseBean#hasLocalClient()
	 */
	public boolean hasLocalClient() {
		return getLocalInterface() != null && getLocalHomeInterface() != null;
	}

	/*
	 * @see EnterpriseBean#hasRemoteClient()
	 */
	public boolean hasRemoteClient() {
		return getRemoteInterface() != null && getHomeInterface() != null;
	}
	
	/**
	 *  Get method name string of form: 
	 *  methodName(class name of arg1,class name of arg2,...)
	 * Creation date: (8/24/00 1:21:10 PM)
	 * @return String
	 * @param m Method
	 */
	private static String methodKey(Method m) {
		StringBuffer result = new StringBuffer(m.getName());

		result.append("("); //$NON-NLS-1$

		java.util.List args = m.getParameters();
		JavaParameter arg;
		for (int i = 0; i < args.size(); i++) {
			arg = (JavaParameter) args.get(i);
			result.append(((JavaHelpers) arg.getEType()).getJavaName());
			result.append(","); //$NON-NLS-1$
		}
		result.append(")"); //$NON-NLS-1$
		return result.toString();
	}
	public Object primRefValue(EObject feature) {
		switch (eDerivedStructuralFeatureID(((EStructuralFeature)feature).getFeatureID(), ((EStructuralFeature)feature).getContainerClass())) {
			case EjbPackage.ENTERPRISE_BEAN__DESCRIPTION :
				return getDescription();
			case EjbPackage.ENTERPRISE_BEAN__DISPLAY_NAME :
				return getDisplayName();
			case EjbPackage.ENTERPRISE_BEAN__SMALL_ICON :
				return getSmallIcon();
			case EjbPackage.ENTERPRISE_BEAN__LARGE_ICON :
				return getLargeIcon();
			case EjbPackage.ENTERPRISE_BEAN__RESOURCE_REFS :
				return getResourceRefs();
			case EjbPackage.ENTERPRISE_BEAN__EJB_CLASS :
				return getEjbClass();
			case EjbPackage.ENTERPRISE_BEAN__HOME_INTERFACE :
				return getHomeInterface();
			case EjbPackage.ENTERPRISE_BEAN__REMOTE_INTERFACE :
				return getRemoteInterface();
			case EjbPackage.ENTERPRISE_BEAN__ENVIRONMENT_PROPERTIES :
				return getEnvironmentProperties();
			case EjbPackage.ENTERPRISE_BEAN__SECURITY_ROLE_REFS :
				return getSecurityRoleRefs();
			case EjbPackage.ENTERPRISE_BEAN__EJB_REFS :
				return getEjbRefs();
			case EjbPackage.ENTERPRISE_BEAN__EJB_LOCAL_REFS :
				return getEjbLocalRefs();
			case EjbPackage.ENTERPRISE_BEAN__RESOURCE_ENV_REFS :
				return getResourceEnvRefs();
			case EjbPackage.ENTERPRISE_BEAN__SECURITY_IDENTITY :
				return getSecurityIdentity();
			case EjbPackage.ENTERPRISE_BEAN__NAME :
				return getName();
		}
		return null;
	}
	/**
	 * @see org.eclipse.jst.j2ee.internal.ejb.EnterpriseBean
	 */
	public void reSyncSecurityRoleRef(java.lang.String existingRoleName, java.lang.String newRoleName) {
		List roleRefs = getSecurityRoleRefs();
		for (int i = 0; i < roleRefs.size(); i++) {
			SecurityRoleRef roleRef = (SecurityRoleRef) roleRefs.get(i);
			if (roleRef.getLink() != null && roleRef.getLink().equals(existingRoleName))
				roleRef.setLink(newRoleName);
		}
	}
	public void setEjbClassName(String ejbClassName) {
		eSet(EjbPackage.eINSTANCE.getEnterpriseBean_EjbClass(), createClassRef(ejbClassName));
	}
	public void setHomeInterfaceName(String homeInterfaceName) {
		eSet(EjbPackage.eINSTANCE.getEnterpriseBean_HomeInterface(), createClassRef(homeInterfaceName));
	}
	public void setLocalHomeInterfaceName(String localHomeInterfaceName) {
		eSet(EjbPackage.eINSTANCE.getEnterpriseBean_LocalHomeInterface(), createClassRef(localHomeInterfaceName));
	}
	public void setLocalInterfaceName(String localInterfaceName) {
		eSet(EjbPackage.eINSTANCE.getEnterpriseBean_LocalInterface(), createClassRef(localInterfaceName));
	}
	public void setRemoteInterfaceName(String remoteInterfaceName) {
		eSet(EjbPackage.eINSTANCE.getEnterpriseBean_RemoteInterface(), createClassRef(remoteInterfaceName));
	}
	/**
	 * Insert the method's description here.
	 * Creation date: (8/24/00 1:32:13 PM)
	 * @return Method[]
	 * @param methods Method[]
	 */
	private static Method[] sortMethods(Method[] methods) {
		Method result[] = new Method[methods.length];

		/**
		 *  Insert each element of given list of methods into result
		 *  arary in sorted order
		 */

		for (int i = 0; i < methods.length; i++) {
			Method currMethod = methods[i];
			String currMethodName = currMethod.getSignature();
			int insertIndex = 0;
			while (insertIndex < i) {
				if (currMethodName.compareTo(result[insertIndex].getSignature()) <= 0) {
					break;
				}
				insertIndex++;
			}

			for (int j = insertIndex; j <= i; j++) {
				Method tmpMethod = result[j];
				result[j] = currMethod;
				currMethod = tmpMethod;
			}
		}
		return result;
	}
	public String toString() {
		return getClass().getName() + "(" + getName() + ")"; //$NON-NLS-1$ //$NON-NLS-2$
	}
	/**
	 *
	 */
	public void setDisplayName(String newDisplayName) {
		super.setDisplayName(newDisplayName);
	}
	
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getName() {
		return name;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setName(String newName) {
		String oldName = name;
		name = newName;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, EjbPackage.ENTERPRISE_BEAN__NAME, oldName, name));
	}

	/**
	 * @generated This field/method will be replaced during code generation 
	 */
	public EList getSecurityRoleRefs() {
		if (securityRoleRefs == null) {
			securityRoleRefs = new EObjectContainmentEList(SecurityRoleRef.class, this, EjbPackage.ENTERPRISE_BEAN__SECURITY_ROLE_REFS);
		}
		return securityRoleRefs;
	}

    /**
     * Handwritten version of getEjbClass():
     */
 
 
    public JavaClass getEjbClass()  { 
       if (ejbClass != null && ejbClass.eIsProxy()) { 
          JavaClass oldEjbClass = ejbClass; 
          JavaClass resolved = (JavaClass)EcoreUtil.resolve(ejbClass, this); 
          resolved(this.ejbClass, resolved);
          this.ejbClass = resolved;
          if (ejbClass != oldEjbClass) { 
             if (eNotificationRequired()) 
                eNotify(new ENotificationImpl(this, Notification.RESOLVE, EjbPackage.ENTERPRISE_BEAN__EJB_CLASS, oldEjbClass, ejbClass)); 
          } 
       } 
       return ejbClass; 
    } 
 
         

	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	public void setEjbClass(JavaClass newEjbClass) {
		JavaClass oldEjbClass = ejbClass;
		ejbClass = newEjbClass;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, EjbPackage.ENTERPRISE_BEAN__EJB_CLASS, oldEjbClass, ejbClass));
	}

	/**
	 * @generated This field/method will be replaced during code generation 
	 */
	public JavaClass getHomeInterface() {
		if (homeInterface != null && homeInterface.eIsProxy()) {
			JavaClass oldHomeInterface = homeInterface;
			homeInterface = (JavaClass)eResolveProxy((InternalEObject)homeInterface);
			if (homeInterface != oldHomeInterface) {
				if (eNotificationRequired())
					eNotify(new ENotificationImpl(this, Notification.RESOLVE, EjbPackage.ENTERPRISE_BEAN__HOME_INTERFACE, oldHomeInterface, homeInterface));
			}
		}
		return homeInterface;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public JavaClass basicGetHomeInterface() {
		return homeInterface;
	}

	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	public void setHomeInterface(JavaClass newHomeInterface) {
		JavaClass oldHomeInterface = homeInterface;
		homeInterface = newHomeInterface;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, EjbPackage.ENTERPRISE_BEAN__HOME_INTERFACE, oldHomeInterface, homeInterface));
	}

	/**
	 * @generated This field/method will be replaced during code generation 
	 */
	public JavaClass getRemoteInterface() {
		if (remoteInterface != null && remoteInterface.eIsProxy()) {
			JavaClass oldRemoteInterface = remoteInterface;
			remoteInterface = (JavaClass)eResolveProxy((InternalEObject)remoteInterface);
			if (remoteInterface != oldRemoteInterface) {
				if (eNotificationRequired())
					eNotify(new ENotificationImpl(this, Notification.RESOLVE, EjbPackage.ENTERPRISE_BEAN__REMOTE_INTERFACE, oldRemoteInterface, remoteInterface));
			}
		}
		return remoteInterface;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public JavaClass basicGetRemoteInterface() {
		return remoteInterface;
	}

	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	public void setRemoteInterface(JavaClass newRemoteInterface) {
		JavaClass oldRemoteInterface = remoteInterface;
		remoteInterface = newRemoteInterface;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, EjbPackage.ENTERPRISE_BEAN__REMOTE_INTERFACE, oldRemoteInterface, remoteInterface));
	}

	/**
	 * @generated This field/method will be replaced during code generation 
	 */
	public EJBJar getEjbJar() {
		if (eContainerFeatureID != EjbPackage.ENTERPRISE_BEAN__EJB_JAR) return null;
		return (EJBJar)eContainer;
	}

	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	public void setEjbJar(EJBJar newEjbJar) {
		if (newEjbJar != eContainer || (eContainerFeatureID != EjbPackage.ENTERPRISE_BEAN__EJB_JAR && newEjbJar != null)) {
			if (EcoreUtil.isAncestor(this, newEjbJar))
				throw new IllegalArgumentException("Recursive containment not allowed for " + toString());
			NotificationChain msgs = null;
			if (eContainer != null)
				msgs = eBasicRemoveFromContainer(msgs);
			if (newEjbJar != null)
				msgs = ((InternalEObject)newEjbJar).eInverseAdd(this, EjbPackage.EJB_JAR__ENTERPRISE_BEANS, EJBJar.class, msgs);
			msgs = eBasicSetContainer((InternalEObject)newEjbJar, EjbPackage.ENTERPRISE_BEAN__EJB_JAR, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, EjbPackage.ENTERPRISE_BEAN__EJB_JAR, newEjbJar, newEjbJar));
	}

	/**
	 * @generated This field/method will be replaced during code generation 
	 * The security-identity element specifies whether the caller's security identity is to be used for the execution of the methods of the enterprise bean or whether a specific run-as identity is to be used. It
	 * contains an optional description and a specification of the security identity to be used.
	 */
	public SecurityIdentity getSecurityIdentity() {
		return securityIdentity;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetSecurityIdentity(SecurityIdentity newSecurityIdentity, NotificationChain msgs) {
		SecurityIdentity oldSecurityIdentity = securityIdentity;
		securityIdentity = newSecurityIdentity;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, EjbPackage.ENTERPRISE_BEAN__SECURITY_IDENTITY, oldSecurityIdentity, newSecurityIdentity);
			if (msgs == null) msgs = notification; else msgs.add(notification);
		}
		return msgs;
	}

	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	public void setSecurityIdentity(SecurityIdentity newSecurityIdentity) {
		if (newSecurityIdentity != securityIdentity) {
			NotificationChain msgs = null;
			if (securityIdentity != null)
				msgs = ((InternalEObject)securityIdentity).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - EjbPackage.ENTERPRISE_BEAN__SECURITY_IDENTITY, null, msgs);
			if (newSecurityIdentity != null)
				msgs = ((InternalEObject)newSecurityIdentity).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - EjbPackage.ENTERPRISE_BEAN__SECURITY_IDENTITY, null, msgs);
			msgs = basicSetSecurityIdentity(newSecurityIdentity, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, EjbPackage.ENTERPRISE_BEAN__SECURITY_IDENTITY, newSecurityIdentity, newSecurityIdentity));
	}

	/**
	 * @generated This field/method will be replaced during code generation 
	 */
	public JavaClass getLocalHomeInterface() {
		if (localHomeInterface != null && localHomeInterface.eIsProxy()) {
			JavaClass oldLocalHomeInterface = localHomeInterface;
			localHomeInterface = (JavaClass)eResolveProxy((InternalEObject)localHomeInterface);
			if (localHomeInterface != oldLocalHomeInterface) {
				if (eNotificationRequired())
					eNotify(new ENotificationImpl(this, Notification.RESOLVE, EjbPackage.ENTERPRISE_BEAN__LOCAL_HOME_INTERFACE, oldLocalHomeInterface, localHomeInterface));
			}
		}
		return localHomeInterface;
	}
	
	public JavaClass getServiceEndpoint() {
		return null;
	}
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public JavaClass basicGetLocalHomeInterface() {
		return localHomeInterface;
	}

	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	public void setLocalHomeInterface(JavaClass newLocalHomeInterface) {
		JavaClass oldLocalHomeInterface = localHomeInterface;
		localHomeInterface = newLocalHomeInterface;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, EjbPackage.ENTERPRISE_BEAN__LOCAL_HOME_INTERFACE, oldLocalHomeInterface, localHomeInterface));
	}

	/**
	 * @generated This field/method will be replaced during code generation 
	 */
	public JavaClass getLocalInterface() {
		if (localInterface != null && localInterface.eIsProxy()) {
			JavaClass oldLocalInterface = localInterface;
			localInterface = (JavaClass)eResolveProxy((InternalEObject)localInterface);
			if (localInterface != oldLocalInterface) {
				if (eNotificationRequired())
					eNotify(new ENotificationImpl(this, Notification.RESOLVE, EjbPackage.ENTERPRISE_BEAN__LOCAL_INTERFACE, oldLocalInterface, localInterface));
			}
		}
		return localInterface;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public JavaClass basicGetLocalInterface() {
		return localInterface;
	}

	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	public void setLocalInterface(JavaClass newLocalInterface) {
		JavaClass oldLocalInterface = localInterface;
		localInterface = newLocalInterface;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, EjbPackage.ENTERPRISE_BEAN__LOCAL_INTERFACE, oldLocalInterface, localInterface));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain eInverseAdd(InternalEObject otherEnd, int featureID, Class baseClass, NotificationChain msgs) {
		if (featureID >= 0) {
			switch (eDerivedStructuralFeatureID(featureID, baseClass)) {
				case EjbPackage.ENTERPRISE_BEAN__EJB_JAR:
					if (eContainer != null)
						msgs = eBasicRemoveFromContainer(msgs);
					return eBasicSetContainer(otherEnd, EjbPackage.ENTERPRISE_BEAN__EJB_JAR, msgs);
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
				case EjbPackage.ENTERPRISE_BEAN__ICONS:
					return ((InternalEList)getIcons()).basicRemove(otherEnd, msgs);
				case EjbPackage.ENTERPRISE_BEAN__DISPLAY_NAMES:
					return ((InternalEList)getDisplayNames()).basicRemove(otherEnd, msgs);
				case EjbPackage.ENTERPRISE_BEAN__DESCRIPTIONS:
					return ((InternalEList)getDescriptions()).basicRemove(otherEnd, msgs);
				case EjbPackage.ENTERPRISE_BEAN__ENVIRONMENT_PROPERTIES:
					return ((InternalEList)getEnvironmentProperties()).basicRemove(otherEnd, msgs);
				case EjbPackage.ENTERPRISE_BEAN__RESOURCE_REFS:
					return ((InternalEList)getResourceRefs()).basicRemove(otherEnd, msgs);
				case EjbPackage.ENTERPRISE_BEAN__EJB_REFS:
					return ((InternalEList)getEjbRefs()).basicRemove(otherEnd, msgs);
				case EjbPackage.ENTERPRISE_BEAN__RESOURCE_ENV_REFS:
					return ((InternalEList)getResourceEnvRefs()).basicRemove(otherEnd, msgs);
				case EjbPackage.ENTERPRISE_BEAN__EJB_LOCAL_REFS:
					return ((InternalEList)getEjbLocalRefs()).basicRemove(otherEnd, msgs);
				case EjbPackage.ENTERPRISE_BEAN__MESSAGE_DESTINATION_REFS:
					return ((InternalEList)getMessageDestinationRefs()).basicRemove(otherEnd, msgs);
				case EjbPackage.ENTERPRISE_BEAN__SERVICE_REFS:
					return ((InternalEList)getServiceRefs()).basicRemove(otherEnd, msgs);
				case EjbPackage.ENTERPRISE_BEAN__SECURITY_ROLE_REFS:
					return ((InternalEList)getSecurityRoleRefs()).basicRemove(otherEnd, msgs);
				case EjbPackage.ENTERPRISE_BEAN__EJB_JAR:
					return eBasicSetContainer(null, EjbPackage.ENTERPRISE_BEAN__EJB_JAR, msgs);
				case EjbPackage.ENTERPRISE_BEAN__SECURITY_IDENTITY:
					return basicSetSecurityIdentity(null, msgs);
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
				case EjbPackage.ENTERPRISE_BEAN__EJB_JAR:
					return eContainer.eInverseRemove(this, EjbPackage.EJB_JAR__ENTERPRISE_BEANS, EJBJar.class, msgs);
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
			case EjbPackage.ENTERPRISE_BEAN__ICONS:
				return getIcons();
			case EjbPackage.ENTERPRISE_BEAN__DISPLAY_NAMES:
				return getDisplayNames();
			case EjbPackage.ENTERPRISE_BEAN__DESCRIPTIONS:
				return getDescriptions();
			case EjbPackage.ENTERPRISE_BEAN__SMALL_ICON:
				return getSmallIcon();
			case EjbPackage.ENTERPRISE_BEAN__LARGE_ICON:
				return getLargeIcon();
			case EjbPackage.ENTERPRISE_BEAN__DESCRIPTION:
				return getDescription();
			case EjbPackage.ENTERPRISE_BEAN__DISPLAY_NAME:
				return getDisplayName();
			case EjbPackage.ENTERPRISE_BEAN__ENVIRONMENT_PROPERTIES:
				return getEnvironmentProperties();
			case EjbPackage.ENTERPRISE_BEAN__RESOURCE_REFS:
				return getResourceRefs();
			case EjbPackage.ENTERPRISE_BEAN__EJB_REFS:
				return getEjbRefs();
			case EjbPackage.ENTERPRISE_BEAN__RESOURCE_ENV_REFS:
				return getResourceEnvRefs();
			case EjbPackage.ENTERPRISE_BEAN__EJB_LOCAL_REFS:
				return getEjbLocalRefs();
			case EjbPackage.ENTERPRISE_BEAN__MESSAGE_DESTINATION_REFS:
				return getMessageDestinationRefs();
			case EjbPackage.ENTERPRISE_BEAN__SERVICE_REFS:
				return getServiceRefs();
			case EjbPackage.ENTERPRISE_BEAN__NAME:
				return getName();
			case EjbPackage.ENTERPRISE_BEAN__SECURITY_ROLE_REFS:
				return getSecurityRoleRefs();
			case EjbPackage.ENTERPRISE_BEAN__EJB_CLASS:
				if (resolve) return getEjbClass();
				return basicGetEjbClass();
			case EjbPackage.ENTERPRISE_BEAN__HOME_INTERFACE:
				if (resolve) return getHomeInterface();
				return basicGetHomeInterface();
			case EjbPackage.ENTERPRISE_BEAN__REMOTE_INTERFACE:
				if (resolve) return getRemoteInterface();
				return basicGetRemoteInterface();
			case EjbPackage.ENTERPRISE_BEAN__EJB_JAR:
				return getEjbJar();
			case EjbPackage.ENTERPRISE_BEAN__SECURITY_IDENTITY:
				return getSecurityIdentity();
			case EjbPackage.ENTERPRISE_BEAN__LOCAL_HOME_INTERFACE:
				if (resolve) return getLocalHomeInterface();
				return basicGetLocalHomeInterface();
			case EjbPackage.ENTERPRISE_BEAN__LOCAL_INTERFACE:
				if (resolve) return getLocalInterface();
				return basicGetLocalInterface();
		}
		return eDynamicGet(eFeature, resolve);
	}

	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	public boolean eIsSet(EStructuralFeature eFeature) {
		switch (eDerivedStructuralFeatureID(eFeature)) {
			case EjbPackage.ENTERPRISE_BEAN__ICONS:
				return icons != null && !icons.isEmpty();
			case EjbPackage.ENTERPRISE_BEAN__DISPLAY_NAMES:
				return displayNames != null && !displayNames.isEmpty();
			case EjbPackage.ENTERPRISE_BEAN__DESCRIPTIONS:
				return descriptions != null && !descriptions.isEmpty();
			case EjbPackage.ENTERPRISE_BEAN__SMALL_ICON:
				return SMALL_ICON_EDEFAULT == null ? smallIcon != null : !SMALL_ICON_EDEFAULT.equals(smallIcon);
			case EjbPackage.ENTERPRISE_BEAN__LARGE_ICON:
				return LARGE_ICON_EDEFAULT == null ? largeIcon != null : !LARGE_ICON_EDEFAULT.equals(largeIcon);
			case EjbPackage.ENTERPRISE_BEAN__DESCRIPTION:
				return DESCRIPTION_EDEFAULT == null ? description != null : !DESCRIPTION_EDEFAULT.equals(description);
			case EjbPackage.ENTERPRISE_BEAN__DISPLAY_NAME:
				return DISPLAY_NAME_EDEFAULT == null ? displayName != null : !DISPLAY_NAME_EDEFAULT.equals(displayName);
			case EjbPackage.ENTERPRISE_BEAN__ENVIRONMENT_PROPERTIES:
				return environmentProperties != null && !environmentProperties.isEmpty();
			case EjbPackage.ENTERPRISE_BEAN__RESOURCE_REFS:
				return resourceRefs != null && !resourceRefs.isEmpty();
			case EjbPackage.ENTERPRISE_BEAN__EJB_REFS:
				return ejbRefs != null && !ejbRefs.isEmpty();
			case EjbPackage.ENTERPRISE_BEAN__RESOURCE_ENV_REFS:
				return resourceEnvRefs != null && !resourceEnvRefs.isEmpty();
			case EjbPackage.ENTERPRISE_BEAN__EJB_LOCAL_REFS:
				return ejbLocalRefs != null && !ejbLocalRefs.isEmpty();
			case EjbPackage.ENTERPRISE_BEAN__MESSAGE_DESTINATION_REFS:
				return messageDestinationRefs != null && !messageDestinationRefs.isEmpty();
			case EjbPackage.ENTERPRISE_BEAN__SERVICE_REFS:
				return serviceRefs != null && !serviceRefs.isEmpty();
			case EjbPackage.ENTERPRISE_BEAN__NAME:
				return NAME_EDEFAULT == null ? name != null : !NAME_EDEFAULT.equals(name);
			case EjbPackage.ENTERPRISE_BEAN__SECURITY_ROLE_REFS:
				return securityRoleRefs != null && !securityRoleRefs.isEmpty();
			case EjbPackage.ENTERPRISE_BEAN__EJB_CLASS:
				return ejbClass != null;
			case EjbPackage.ENTERPRISE_BEAN__HOME_INTERFACE:
				return homeInterface != null;
			case EjbPackage.ENTERPRISE_BEAN__REMOTE_INTERFACE:
				return remoteInterface != null;
			case EjbPackage.ENTERPRISE_BEAN__EJB_JAR:
				return getEjbJar() != null;
			case EjbPackage.ENTERPRISE_BEAN__SECURITY_IDENTITY:
				return securityIdentity != null;
			case EjbPackage.ENTERPRISE_BEAN__LOCAL_HOME_INTERFACE:
				return localHomeInterface != null;
			case EjbPackage.ENTERPRISE_BEAN__LOCAL_INTERFACE:
				return localInterface != null;
		}
		return eDynamicIsSet(eFeature);
	}

	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	public void eSet(EStructuralFeature eFeature, Object newValue) {
		switch (eDerivedStructuralFeatureID(eFeature)) {
			case EjbPackage.ENTERPRISE_BEAN__ICONS:
				getIcons().clear();
				getIcons().addAll((Collection)newValue);
				return;
			case EjbPackage.ENTERPRISE_BEAN__DISPLAY_NAMES:
				getDisplayNames().clear();
				getDisplayNames().addAll((Collection)newValue);
				return;
			case EjbPackage.ENTERPRISE_BEAN__DESCRIPTIONS:
				getDescriptions().clear();
				getDescriptions().addAll((Collection)newValue);
				return;
			case EjbPackage.ENTERPRISE_BEAN__SMALL_ICON:
				setSmallIcon((String)newValue);
				return;
			case EjbPackage.ENTERPRISE_BEAN__LARGE_ICON:
				setLargeIcon((String)newValue);
				return;
			case EjbPackage.ENTERPRISE_BEAN__DESCRIPTION:
				setDescription((String)newValue);
				return;
			case EjbPackage.ENTERPRISE_BEAN__DISPLAY_NAME:
				setDisplayName((String)newValue);
				return;
			case EjbPackage.ENTERPRISE_BEAN__ENVIRONMENT_PROPERTIES:
				getEnvironmentProperties().clear();
				getEnvironmentProperties().addAll((Collection)newValue);
				return;
			case EjbPackage.ENTERPRISE_BEAN__RESOURCE_REFS:
				getResourceRefs().clear();
				getResourceRefs().addAll((Collection)newValue);
				return;
			case EjbPackage.ENTERPRISE_BEAN__EJB_REFS:
				getEjbRefs().clear();
				getEjbRefs().addAll((Collection)newValue);
				return;
			case EjbPackage.ENTERPRISE_BEAN__RESOURCE_ENV_REFS:
				getResourceEnvRefs().clear();
				getResourceEnvRefs().addAll((Collection)newValue);
				return;
			case EjbPackage.ENTERPRISE_BEAN__EJB_LOCAL_REFS:
				getEjbLocalRefs().clear();
				getEjbLocalRefs().addAll((Collection)newValue);
				return;
			case EjbPackage.ENTERPRISE_BEAN__MESSAGE_DESTINATION_REFS:
				getMessageDestinationRefs().clear();
				getMessageDestinationRefs().addAll((Collection)newValue);
				return;
			case EjbPackage.ENTERPRISE_BEAN__SERVICE_REFS:
				getServiceRefs().clear();
				getServiceRefs().addAll((Collection)newValue);
				return;
			case EjbPackage.ENTERPRISE_BEAN__NAME:
				setName((String)newValue);
				return;
			case EjbPackage.ENTERPRISE_BEAN__SECURITY_ROLE_REFS:
				getSecurityRoleRefs().clear();
				getSecurityRoleRefs().addAll((Collection)newValue);
				return;
			case EjbPackage.ENTERPRISE_BEAN__EJB_CLASS:
				setEjbClass((JavaClass)newValue);
				return;
			case EjbPackage.ENTERPRISE_BEAN__HOME_INTERFACE:
				setHomeInterface((JavaClass)newValue);
				return;
			case EjbPackage.ENTERPRISE_BEAN__REMOTE_INTERFACE:
				setRemoteInterface((JavaClass)newValue);
				return;
			case EjbPackage.ENTERPRISE_BEAN__EJB_JAR:
				setEjbJar((EJBJar)newValue);
				return;
			case EjbPackage.ENTERPRISE_BEAN__SECURITY_IDENTITY:
				setSecurityIdentity((SecurityIdentity)newValue);
				return;
			case EjbPackage.ENTERPRISE_BEAN__LOCAL_HOME_INTERFACE:
				setLocalHomeInterface((JavaClass)newValue);
				return;
			case EjbPackage.ENTERPRISE_BEAN__LOCAL_INTERFACE:
				setLocalInterface((JavaClass)newValue);
				return;
		}
		eDynamicSet(eFeature, newValue);
	}

	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	public void eUnset(EStructuralFeature eFeature) {
		switch (eDerivedStructuralFeatureID(eFeature)) {
			case EjbPackage.ENTERPRISE_BEAN__ICONS:
				getIcons().clear();
				return;
			case EjbPackage.ENTERPRISE_BEAN__DISPLAY_NAMES:
				getDisplayNames().clear();
				return;
			case EjbPackage.ENTERPRISE_BEAN__DESCRIPTIONS:
				getDescriptions().clear();
				return;
			case EjbPackage.ENTERPRISE_BEAN__SMALL_ICON:
				setSmallIcon(SMALL_ICON_EDEFAULT);
				return;
			case EjbPackage.ENTERPRISE_BEAN__LARGE_ICON:
				setLargeIcon(LARGE_ICON_EDEFAULT);
				return;
			case EjbPackage.ENTERPRISE_BEAN__DESCRIPTION:
				setDescription(DESCRIPTION_EDEFAULT);
				return;
			case EjbPackage.ENTERPRISE_BEAN__DISPLAY_NAME:
				setDisplayName(DISPLAY_NAME_EDEFAULT);
				return;
			case EjbPackage.ENTERPRISE_BEAN__ENVIRONMENT_PROPERTIES:
				getEnvironmentProperties().clear();
				return;
			case EjbPackage.ENTERPRISE_BEAN__RESOURCE_REFS:
				getResourceRefs().clear();
				return;
			case EjbPackage.ENTERPRISE_BEAN__EJB_REFS:
				getEjbRefs().clear();
				return;
			case EjbPackage.ENTERPRISE_BEAN__RESOURCE_ENV_REFS:
				getResourceEnvRefs().clear();
				return;
			case EjbPackage.ENTERPRISE_BEAN__EJB_LOCAL_REFS:
				getEjbLocalRefs().clear();
				return;
			case EjbPackage.ENTERPRISE_BEAN__MESSAGE_DESTINATION_REFS:
				getMessageDestinationRefs().clear();
				return;
			case EjbPackage.ENTERPRISE_BEAN__SERVICE_REFS:
				getServiceRefs().clear();
				return;
			case EjbPackage.ENTERPRISE_BEAN__NAME:
				setName(NAME_EDEFAULT);
				return;
			case EjbPackage.ENTERPRISE_BEAN__SECURITY_ROLE_REFS:
				getSecurityRoleRefs().clear();
				return;
			case EjbPackage.ENTERPRISE_BEAN__EJB_CLASS:
				setEjbClass((JavaClass)null);
				return;
			case EjbPackage.ENTERPRISE_BEAN__HOME_INTERFACE:
				setHomeInterface((JavaClass)null);
				return;
			case EjbPackage.ENTERPRISE_BEAN__REMOTE_INTERFACE:
				setRemoteInterface((JavaClass)null);
				return;
			case EjbPackage.ENTERPRISE_BEAN__EJB_JAR:
				setEjbJar((EJBJar)null);
				return;
			case EjbPackage.ENTERPRISE_BEAN__SECURITY_IDENTITY:
				setSecurityIdentity((SecurityIdentity)null);
				return;
			case EjbPackage.ENTERPRISE_BEAN__LOCAL_HOME_INTERFACE:
				setLocalHomeInterface((JavaClass)null);
				return;
			case EjbPackage.ENTERPRISE_BEAN__LOCAL_INTERFACE:
				setLocalInterface((JavaClass)null);
				return;
		}
		eDynamicUnset(eFeature);
	}

	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	public String toStringGen() {
		if (eIsProxy()) return super.toString();

		StringBuffer result = new StringBuffer(super.toString());
		result.append(" (name: "); //$NON-NLS-1$
		result.append(name);
		result.append(')');
		return result.toString();
	}

	/**
	 * Preserve adapters when object is resolved.
	 */
	protected void resolved(EObject originalObject, EObject resolvedObject) {
		if (originalObject != null && resolvedObject != null && originalObject != resolvedObject) {
			Collection col = originalObject.eAdapters();
			if (!col.isEmpty()) {
				Iterator it = col.iterator();
				Adapter a;
				while (it.hasNext()) {
					a = (Adapter) it.next();
					if (a == null) continue;
					resolvedObject.eAdapters().add(a);
				}
			}
		}
	}
	/**
	 * @generated This field/method will be replaced during code generation 
	 */
	public JavaClass getEjbClassGen() {
		if (ejbClass != null && ejbClass.eIsProxy()) {
			JavaClass oldEjbClass = ejbClass;
			ejbClass = (JavaClass)eResolveProxy((InternalEObject)ejbClass);
			if (ejbClass != oldEjbClass) {
				if (eNotificationRequired())
					eNotify(new ENotificationImpl(this, Notification.RESOLVE, EjbPackage.ENTERPRISE_BEAN__EJB_CLASS, oldEjbClass, ejbClass));
			}
		}
		return ejbClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public JavaClass basicGetEjbClass() {
		return ejbClass;
	}

	/* (non-Javadoc)
	 * @see org.eclipse.emf.ecore.EModelElement#getEAnnotation(java.lang.String)
	 */
	public EAnnotation getEAnnotation(String source) {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see org.eclipse.emf.ecore.EModelElement#getEAnnotations()
	 */
	public EList getEAnnotations() {
		// TODO Auto-generated method stub
		return null;
	}

}






