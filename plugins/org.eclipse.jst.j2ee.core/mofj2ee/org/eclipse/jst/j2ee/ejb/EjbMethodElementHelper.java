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
/*
 * Created on Apr 21, 2003
 *
 * To change the template for this generated file go to
 * Window>Preferences>Java>Code Generation>Code and Comments
 */
package org.eclipse.jst.j2ee.ejb;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;

import org.eclipse.emf.common.util.Enumerator;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.jem.java.JavaClass;
import org.eclipse.jem.java.JavaRefFactory;
import org.eclipse.jem.java.Method;
import org.eclipse.jst.j2ee.internal.ejb.EjbPackage;
import org.eclipse.jst.j2ee.internal.ejb.impl.EjbFactoryImpl;

/**
 * @author blancett
 * 
 * To change the template for this generated type comment go to
 * Window>Preferences>Java>Code Generation>Code and Comments
 */
public class EjbMethodElementHelper {
	public static final int ALL_METHODS = 0;
	public static final int HOME_METHODS = 1;
	public static final int CLIENT_METHODS = 2;
	public static final int REMOTE_METHODS = 3;
	public static final int LOCAL_METHODS = 4;
	public static final int LOCAL_HOME_METHODS = 5;
	public static final int LOCAL_REMOTE_METHODS = 6;
	protected EjbMethodElementComparator defaultMEComparator;
	protected EjbMethodElementComparator defaultMethodElementComparator;
	private static EjbMethodElementHelper singleton;
	public static final String DEFAULT_METHOD_NAME = "*"; //$NON-NLS-1$
	protected static final String METHOD_ELEMENT_NAME = "MethodElement"; //$NON-NLS-1$
	public EjbMethodElementHelper() {
		super();
	}
	public static EjbMethodElementHelper singleton() {
		if (singleton == null)
			singleton = new EjbMethodElementHelper();
		return singleton;
	}
	public boolean isMethodElementsEqual(MethodElement me1, MethodElement me2) {
		return (me1.getSignature().equals(me2.getSignature()) 
				&& me1.getEnterpriseBean() == me2.getEnterpriseBean() 
				&& me1.getMethodParams().equals(me2.getMethodParams()) 
				&& me1.getType() == me2.getType());
	}
	public boolean isMethodElementinList(List meList, Object me) {
		if (! (me instanceof MethodElement))
			return false;
		for (int i = 0; i < meList.size(); i++) {
			if (meList.get(i) instanceof MethodElement) {
				MethodElement element = (MethodElement) meList.get(i);
				if (isMethodElementsEqual(element, (MethodElement)me))
					return true;
			}
		}
		return false;
	}
	protected EjbMethodElementComparator getDefaultMEComparator() {
		if (defaultMEComparator == null)
			defaultMEComparator = new EjbMethodElementComparator();
		return defaultMEComparator;
	}
	protected void excludeQueryMethods(List result, List someQueries) {
		if (someQueries.isEmpty())
			return;
		int size = someQueries.size();
		List methods = new ArrayList(size);
		Query query = null;
		for (int i = 0; i < size; i++) {
			query = (Query) someQueries.get(i);
			methods.add(query.getQueryMethod());
		}
		excludeMethodElements(result, methods);
	}
	public List getAvailableSelectQueryMethodsExcludingExisting(ContainerManagedEntity cmp) {
		if (cmp == null)
			return Collections.EMPTY_LIST;
		List result = cmp.getAvailableSelectQueryMethods();
		List existing = cmp.getQueries();
		excludeQueryMethods(result, existing);
		return result;
	}
	public List getAvailableFindQueryMethodsExcludingExisting(ContainerManagedEntity cmp) {
		if (cmp == null)
			return Collections.EMPTY_LIST;
		List result = cmp.getAvailableFindQueryMethods();
		List existing = cmp.getQueries();
		excludeQueryMethods(result, existing);
		return result;
	}
	public List getExistingOrAvailableTransactionMethodElements(EObject object, EnterpriseBean bean) {
		List result = new ArrayList();
		result.addAll(getAvailableMethodTransactionMethodElementsExcludingExisting(bean));
		unionMethodElements(result, getExistingMethodElements(object, bean));
		Collections.sort(result, getDefaultMethodElementComparator());
		return result;
	}
	protected EjbMethodElementComparator getDefaultMethodElementComparator() {
		if (defaultMethodElementComparator == null)
			defaultMethodElementComparator = new EjbMethodElementComparator();
		return defaultMethodElementComparator;
	}
	protected List getExistingMethodElements(EObject refObject, EnterpriseBean bean) {
		if (refObject != null) {
			EjbPackage pack = getEjbPackage();
			if (refObject.eClass() == pack.getMethodTransaction())
				return ((MethodTransaction) refObject).getMethodElements(bean);
			if (refObject.eClass() == pack.getMethodPermission())
				return ((MethodPermission) refObject).getMethodElements(bean);
		}
		return Collections.EMPTY_LIST;
	}
	protected void unionMethodElements(List sourceMethodElements, List additionalMethodElements) {
		MethodElement sourceME, additionalME;
		int sourceSize, addSize;
		sourceSize = sourceMethodElements.size();
		addSize = additionalMethodElements.size();
		List foundElements = new ArrayList(addSize);
		boolean found = false;
		for (int i = 0; i < addSize; i++) {
			found = false;
			additionalME = (MethodElement) additionalMethodElements.get(i);
			sourceME = null;
			for (int ii = 0; ii < sourceSize; ii++) {
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
	public List getAvailableMethodTransactionMethodElementsExcludingExisting(EnterpriseBean bean) {
		List result = new ArrayList();
		List filteredMethods = new ArrayList();
		if (bean.isEntity()) {
			if (bean.getRemoteInterface() != null) {
				filteredMethods = bean.getRemoteInterface().getMethodsExtendedWithFilters(Collections.EMPTY_LIST, getEntityMethodTransactionExcludedMethods((Entity) bean, REMOTE_METHODS));
				result.addAll(createMethodElements(getMethodElementSignatures(filteredMethods), MethodElementKind.REMOTE_LITERAL, bean));
			}
			if (bean.getHomeInterface() != null) {
				filteredMethods = bean.getHomeInterface().getMethodsExtendedWithFilters(Collections.EMPTY_LIST, getEntityMethodTransactionExcludedMethods((Entity) bean, HOME_METHODS));
				result.addAll(createMethodElements(getMethodElementSignatures(filteredMethods), MethodElementKind.HOME_LITERAL, bean));
			}
			if (bean.getLocalInterface() != null) {
				filteredMethods = bean.getLocalInterface().getMethodsExtendedWithFilters(Collections.EMPTY_LIST, getEntityMethodTransactionExcludedMethods((Entity) bean, LOCAL_METHODS));
				result.addAll(createMethodElements(getMethodElementSignatures(filteredMethods), MethodElementKind.LOCAL_LITERAL, bean));
			}
			if (bean.getLocalHomeInterface() != null)
				result.addAll(bean.getAvailableLocalHomeMethodElements());
			result.addAll(bean.getAvailableUnspecifiedMethodElements());
		} else if (bean.isSession()) {
			if (bean.getRemoteInterface() != null) {
				filteredMethods = bean.getRemoteInterface().getMethodsExtendedWithFilters(getSessionMethodTransactionExcludedClasses((Session) bean, REMOTE_METHODS), Collections.EMPTY_LIST);
				result.addAll(createMethodElements(getMethodElementSignatures(filteredMethods), MethodElementKind.REMOTE_LITERAL, bean));
			}
			if (bean.getLocalInterface() != null) {
				filteredMethods = bean.getLocalInterface().getMethodsExtendedWithFilters(getSessionMethodTransactionExcludedClasses((Session) bean, LOCAL_METHODS), Collections.EMPTY_LIST);
				result.addAll(createMethodElements(getMethodElementSignatures(filteredMethods), MethodElementKind.LOCAL_LITERAL, bean));
			}
			result.add(bean.getAvailableUnspecifiedMethodElements().get(0));
		} else if (bean.isMessageDriven()) {
			filteredMethods = Arrays.asList(new Object[]{getMethod(bean.getEjbClass(), getMDBMethod_Sig())});
			result.addAll(bean.createMethodElements(filteredMethods, bean.getEjbClass()));
		}
		return result;
	}
	public List getMethodElementSignatures(List methods) {
		List signatures, sameNames;
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
	protected List createMethodElements(List signatures, Enumerator aType, EnterpriseBean bean) {
		return createMethodElements(METHOD_ELEMENT_NAME, signatures, aType, bean);
	}
	protected EjbPackage getEjbPackage() {
		return EjbFactoryImpl.getPackage();
	}
	protected EjbFactory getEjbFactory() {
		return ((EjbPackage) EPackage.Registry.INSTANCE.getEPackage(EjbPackage.eNS_URI)).getEjbFactory();
	}
	protected List createMethodElements(String meTypeName, List signatures, Enumerator aType, EnterpriseBean bean) {
		int size = signatures.size();
		List methodElements = new ArrayList(size);
		String signature;
		MethodElement me;
		for (int i = 0; i < size; i++) {
			signature = (String) signatures.get(i);
			me = (MethodElement) getEjbFactory().create((EClass) getEjbPackage().getEClassifier(meTypeName));
			me.initializeFromSignature(signature);
			me.setEnterpriseBean(bean);
			me.setType((MethodElementKind) aType);
			methodElements.add(me);
		}
		return methodElements;
	}
	protected List getEntityMethodTransactionExcludedMethods(Entity entity, int type) {
		List result = new ArrayList();
		switch (type) {
			case (REMOTE_METHODS) :
				{
					JavaClass aClass = getEJBObjectClass(entity);
					aClass.getClassImport();
					result.addAll(excludeMethods(aClass, Arrays.asList(new Object[]{getMethod(aClass, getRemoteRemoveMethod_Sig())})));
					break;
				}
			case (HOME_METHODS) :
				{
					JavaClass aClass = getEJBHomeClass(entity);
					aClass.getClassImport();
					result.addAll(Arrays.asList(new Object[]{getMethod(aClass, getEjbGetMetaDataMethod_Sig()), getMethod(aClass, getEjbGetHomeHandleMethod_Sig())}));
					break;
				}
			case (LOCAL_METHODS) :
				{
					JavaClass aClass = getEJBLocalObjectClass(entity);
					aClass.getClassImport();
					result.addAll(excludeMethods(aClass, Arrays.asList(new Object[]{getMethod(aClass, getLocalRemoveMethod_Sig())})));
					break;
				}
		}
		return result;
	}
	protected List getSessionMethodTransactionExcludedClasses(Session session, int type) {
		List result = new ArrayList();
		Object[] allInterfaces;
		String className;
		switch (type) {
			case (REMOTE_METHODS) :
				{
					className = getEJBObjectClassName();
					allInterfaces = session.getRemoteInterface().getImplementsInterfaces().toArray();
					result.addAll(getInterfaces(allInterfaces, className));
					break;
				}
			case (LOCAL_METHODS) :
				{
					className = getEJBLocalObjectClassName();
					allInterfaces = session.getLocalInterface().getImplementsInterfaces().toArray();
					result.addAll(getInterfaces(allInterfaces, className));
					break;
				}
		}
		return result;
	}
	protected List excludeMethods(JavaClass aClass, List methods) {
		List currentMethods = aClass.getMethods();
		List resultMethods = new ArrayList();
		for (int i = 0; i < currentMethods.size(); i++) {
			if (!methods.contains(currentMethods.get(i)))
				resultMethods.add(currentMethods.get(i));
		}
		return resultMethods;
	}
	protected Method getMethod(JavaClass aClass, String methodName) {
		List currentMethods = aClass.getMethods();
		for (int i = 0; i < currentMethods.size(); i++) {
			Method method = (Method) currentMethods.get(i);
			if (methodName.equals(method.getSignature()))
				return method;
		}
		return null;
	}
	protected Method getMethodUsingMethodSig(JavaClass aClass, String methodName) {
		List currentMethods = aClass.getMethods();
		for (int i = 0; i < currentMethods.size(); i++) {
			Method method = (Method) currentMethods.get(i);
			if (methodName.equals(method.getSignature()))
				return method;
		}
		return null;
	}
	public List getInterfaces(Object[] interfaces, String aEjbObjectClassName) {
		List extractedInterfaces = new ArrayList();
		for (int i = 0; i < interfaces.length; i++) {
			if (((JavaClass) interfaces[i]).getQualifiedName().equals(aEjbObjectClassName))
				extractedInterfaces.add(interfaces[i]);
		}
		return extractedInterfaces;
	}
	public List getAvailableMethodElementsExcludingExisting(EnterpriseBean ejb, List someMethodElementOwners, EReference methodElementReference, int usageFlag) {
		return getAvailableMethodElementsExcludingExisting(ejb, someMethodElementOwners, methodElementReference, getDefaultMEComparator(), usageFlag);
	}
	public List getAvailableMethodElementsExcludingExisting(EnterpriseBean ejb, List someMethodElementOwners, EReference methodElementReference) {
		return getAvailableMethodElementsExcludingExisting(ejb, someMethodElementOwners, methodElementReference, getDefaultMEComparator());
	}
	public List getAvailableMethodElementsExcludingExisting(EnterpriseBean ejb, List someMethodElementOwners, EReference methodElementReference, Comparator comparator) {
		return getAvailableMethodElementsExcludingExisting(ejb, someMethodElementOwners, methodElementReference, comparator, ALL_METHODS);
	}
	protected List getExistingMethodElements(List someMethodElementOwners, EReference methodElementReference) {
		if (someMethodElementOwners == null || someMethodElementOwners.isEmpty() || methodElementReference == null || !methodElementReference.isMany())
			return Collections.EMPTY_LIST;
		List result = new ArrayList();
		int size = someMethodElementOwners.size();
		EObject owner = null;
		for (int i = 0; i < size; i++) {
			owner = (EObject) someMethodElementOwners.get(i);
			result.addAll((List) owner.eGet(methodElementReference));
		}
		return result;
	}
	public List getAvailableMethodElementsExcludingExisting(EnterpriseBean ejb, List someMethodElementOwners, EReference methodElementReference, Comparator comparator, int usageFlag) {
		if (ejb == null)
			return Collections.EMPTY_LIST;
		List result = new ArrayList();
		boolean showHomes = usageFlag == ALL_METHODS || usageFlag == HOME_METHODS;
		boolean showClients = usageFlag == ALL_METHODS || usageFlag == CLIENT_METHODS;
		if (showHomes)
			result.addAll(ejb.getAvailableHomeMethodElements());
		if (showClients)
			result.addAll(ejb.getAvailableRemoteMethodElements());
		if (showHomes)
			result.addAll(ejb.getAvailableLocalHomeMethodElements());
		if (showClients)
			result.addAll(ejb.getAvailableLocalMethodElements());
		result.addAll(ejb.getAvailableUnspecifiedMethodElements());
		excludeMethodElements(result, getExistingMethodElements(someMethodElementOwners, methodElementReference));
		Collections.sort(result, comparator);
		return result;
	}
	protected void excludeMethodElements(List sourceMethodElements, List existingMethodElements) {
		MethodElement sourceME, existingME;
		int sourceSize, existingSize;
		sourceSize = sourceMethodElements.size();
		existingSize = existingMethodElements.size();
		List foundElements = new ArrayList(existingSize);
		boolean found = false;
		for (int i = 0; i < existingSize; i++) {
			found = false;
			existingME = (MethodElement) existingMethodElements.get(i);
			sourceME = null;
			for (int ii = 0; ii < sourceSize; ii++) {
				sourceME = (MethodElement) sourceMethodElements.get(ii);
				if (existingME != null && existingME.getName() != null && sourceME.getSignature().equals(existingME.getSignature()) && sourceME.getTypeJavaClass() == existingME.getTypeJavaClass()) {
					if (sourceME.getTypeJavaClass() != null) { //it returns
						// null if the
						// method is *
						// and it is not
						// a message
						// bean
						found = true;
						break;
					}
					else if (sourceME.getEnterpriseBean().getEjbClass() == existingME.getEnterpriseBean().getEjbClass()) {
						found = true;
						break;
					}
				}
			}
			if (found)
				foundElements.add(sourceME);
		}
		sourceMethodElements.removeAll(foundElements);
	}
	private String getEjbGetHomeHandleMethod_Sig() {
		return "Public abstract javax.ejb.HomeHandle javax.ejb.EJBHome.getHomeHandle() throws java.rmi.RemoteException"; //$NON-NLS-1$
	}
	private String getEjbGetMetaDataMethod_Sig() {
		return "Public abstract javax.ejb.EJBMetaData javax.ejb.EJBHome.getEJBMetaData() throws java.rmi.RemoteException"; //$NON-NLS-1$
	}
	private String getRemoteRemoveMethod_Sig() {
		return "Public abstract void javax.ejb.EJBObject.remove() throws java.rmi.RemoteException,javax.ejb.RemoveException"; //non-nls //$NON-NLS-1$
	}
	private String getLocalRemoveMethod_Sig() {
		return "Public abstract void javax.ejb.EJBLocalObject.remove() throws java.rmi.RemoteException,javax.ejb.RemoveException"; //non-nls //$NON-NLS-1$
	}
	private String getMDBMethod_Sig() {
		return "public void onMessage(javax.jms.Message msg)"; //$NON-NLS-1$
	}
	private String getEJBObjectClassName() {
		return "javax.ejb.EJBObject"; //$NON-NLS-1$
	}
	private String getEJBLocalObjectClassName() {
		return "javax.ejb.EJBLocalObject"; //$NON-NLS-1$
	}
	private JavaClass getEJBObjectClass(EnterpriseBean bean) {
		Resource res = bean.eResource();
		ResourceSet set = res != null ? res.getResourceSet() : null;
		return (JavaClass) JavaRefFactory.eINSTANCE.reflectType("javax.ejb", "EJBObject", set); //$NON-NLS-1$ //$NON-NLS-2$
	}
	private JavaClass getEJBLocalObjectClass(EnterpriseBean bean) {
		Resource res = bean.eResource();
		ResourceSet set = res != null ? res.getResourceSet() : null;
		return (JavaClass) JavaRefFactory.eINSTANCE.reflectType("javax.ejb", "EJBLocalObject", set); //$NON-NLS-1$ //$NON-NLS-2$
	}
	private JavaClass getEJBHomeClass(EnterpriseBean bean) {
		Resource res = bean.eResource();
		ResourceSet set = res != null ? res.getResourceSet() : null;
		return (JavaClass) JavaRefFactory.eINSTANCE.reflectType("javax.ejb", "EJBHome", set); //$NON-NLS-1$ //$NON-NLS-2$
	}
}
