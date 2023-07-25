/*******************************************************************************
 * Copyright (c) 2001, 2023 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 *
 * Contributors:
 * IBM Corporation - initial API and implementation
 *******************************************************************************/
package org.eclipse.jst.j2ee.ejb.internal.impl;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.jem.java.JavaClass;
import org.eclipse.jem.java.Method;
import org.eclipse.jst.j2ee.ejb.EjbPackage;
import org.eclipse.jst.j2ee.ejb.EnterpriseBean;
import org.eclipse.jst.j2ee.ejb.Query;
import org.eclipse.jst.j2ee.ejb.QueryMethod;

/**
 * @generated
 */
public class QueryMethodImpl extends MethodElementImpl implements QueryMethod {

	public QueryMethodImpl() {
		super();
	}
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return EjbPackage.Literals.QUERY_METHOD;
	}

/**
 * For Query methods, their enterprise bean must be derived.
 * It is the Entity which contains the Query.
 */
@Override
public EnterpriseBean getEnterpriseBean() {
	if (enterpriseBean == null) {
		if (getQuery() != null)
			enterpriseBean = getQuery().getEntity();
	}
	return enterpriseBean;
}
/**
 * Answer the method for which this method element applies in the HOME INTERFACE.
 */
protected Method getHomeMethod() {
	Method result = null;
	JavaClass javaClass = getEnterpriseBean().getHomeInterface();
	if (javaClass != null) {
		String methodName = getName().trim();
		if (hasMethodParams())
			result = javaClass.getPublicMethodExtended(methodName, getMethodParams());
		else {
			List methods = javaClass.getPublicMethodsExtendedNamed(methodName);
			if (!methods.isEmpty())
				result = (Method) methods.get(0);
		}
	}
	return result;
}
/**
 * Answer the method for which this method element applies in the LOCAL HOME INTERFACE.
 */
protected Method getLocalHomeMethod() {
	Method result = null;
	JavaClass javaClass = getEnterpriseBean().getLocalHomeInterface();
	if (javaClass != null) {
		String methodName = getName().trim();
		if (hasMethodParams())
			result = javaClass.getPublicMethodExtended(methodName, getMethodParams());
		else {
			List methods = javaClass.getPublicMethodsExtendedNamed(methodName);
			if (!methods.isEmpty())
				result = (Method) methods.get(0);
		}
	}
	return result;
}



protected void addMethodIfNotNull(List aList, Method aMethod) {
	if (aMethod != null)
		aList.add(aMethod);
}
/**
 * This method will return a zero-length Method[] if there is no matching method
 * on the home, local home, or bean class.  It will return exactly one method
 * for ejbSelect, and one or two methods for finders.  The two method case occurs
 * when the same method with the same signature exists on both the home and
 * local home.
 */
@Override
public Method[] getMethods() {
	List result = new ArrayList(2);
	if (getName().startsWith("ejbSelect")) { //$NON-NLS-1$
		addMethodIfNotNull(result, getSelectMethod());
	} else {
		addMethodIfNotNull(result, getHomeMethod());
		addMethodIfNotNull(result, getLocalHomeMethod());
	}
	return (Method[])result.toArray(new Method[result.size()]);
}
/**
 * Answer the method for which this method element applies in the HOME INTERFACE.
 */
protected Method getSelectMethod() {
	Method result = null;
	JavaClass javaClass = getEnterpriseBean().getEjbClass();
	String methodName = getName().trim();
	if (hasMethodParams())
		result = javaClass.getPublicMethodExtended(name, getMethodParams());
	else {
		List methods = javaClass.getPublicMethodsExtendedNamed(methodName);
		if (!methods.isEmpty())
			result = (Method) methods.iterator().next();
	}
	return result;
}
	/**
	 * @generated This field/method will be replaced during code generation 
	 */
	@Override
	public Query getQuery() {
		if (eContainerFeatureID != EjbPackage.QUERY_METHOD__QUERY) return null;
		return (Query)eContainer();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetQuery(Query newQuery, NotificationChain msgs) {
		msgs = eBasicSetContainer((InternalEObject)newQuery, EjbPackage.QUERY_METHOD__QUERY, msgs);
		return msgs;
	}

	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	@Override
	public void setQuery(Query newQuery) {
		if (newQuery != eInternalContainer() || (eContainerFeatureID != EjbPackage.QUERY_METHOD__QUERY && newQuery != null)) {
			if (EcoreUtil.isAncestor(this, newQuery))
				throw new IllegalArgumentException("Recursive containment not allowed for " + toString()); //$NON-NLS-1$
			NotificationChain msgs = null;
			if (eInternalContainer() != null)
				msgs = eBasicRemoveFromContainer(msgs);
			if (newQuery != null)
				msgs = ((InternalEObject)newQuery).eInverseAdd(this, EjbPackage.QUERY__QUERY_METHOD, Query.class, msgs);
			msgs = basicSetQuery(newQuery, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, EjbPackage.QUERY_METHOD__QUERY, newQuery, newQuery));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseAdd(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case EjbPackage.QUERY_METHOD__QUERY:
				if (eInternalContainer() != null)
					msgs = eBasicRemoveFromContainer(msgs);
				return basicSetQuery((Query)otherEnd, msgs);
		}
		return super.eInverseAdd(otherEnd, featureID, msgs);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case EjbPackage.QUERY_METHOD__QUERY:
				return basicSetQuery(null, msgs);
		}
		return super.eInverseRemove(otherEnd, featureID, msgs);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eBasicRemoveFromContainerFeature(NotificationChain msgs) {
		switch (eContainerFeatureID) {
			case EjbPackage.QUERY_METHOD__QUERY:
				return eInternalContainer().eInverseRemove(this, EjbPackage.QUERY__QUERY_METHOD, Query.class, msgs);
		}
		return super.eBasicRemoveFromContainerFeature(msgs);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case EjbPackage.QUERY_METHOD__QUERY:
				return getQuery();
		}
		return super.eGet(featureID, resolve, coreType);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void eSet(int featureID, Object newValue) {
		switch (featureID) {
			case EjbPackage.QUERY_METHOD__QUERY:
				setQuery((Query)newValue);
				return;
		}
		super.eSet(featureID, newValue);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void eUnset(int featureID) {
		switch (featureID) {
			case EjbPackage.QUERY_METHOD__QUERY:
				setQuery((Query)null);
				return;
		}
		super.eUnset(featureID);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public boolean eIsSet(int featureID) {
		switch (featureID) {
			case EjbPackage.QUERY_METHOD__QUERY:
				return getQuery() != null;
		}
		return super.eIsSet(featureID);
	}

	/**
	 * You should use getClientTypeJavaClasses to ensure you pick up both home interfaces
	 * if they exist.
	 */
	@Override
	public JavaClass getTypeJavaClass() {
		JavaClass[] result = getClientTypeJavaClasses();
		if (result != null && result.length > 0)
			return result[0];
		return null;
	}
	
	/**
	 * Return an array of JavaClasses of all the interfaces or bean class that this method is presented
	 * to a client.  If it is an ejbSelect... on the bean class will be returned and if
	 * it if a find both home interfaces will be returned if they exist.
	 * 
	 * May return null.
	 */
	@Override
	public JavaClass[] getClientTypeJavaClasses() {
		if (getName() == null || getEnterpriseBean() == null) return null;
		EnterpriseBean ejb = getEnterpriseBean();
		if (getName().startsWith(SELECT_PREFIX))
			return new JavaClass[]{ejb.getEjbClass()};
		//Next case is tougher since you could have both a remove and local client
		//We want to return the home interface in this case.
		if (getName().startsWith(FIND_PREFIX)) {
			if (ejb.hasLocalClient() && !ejb.hasRemoteClient())
				return new JavaClass[]{getEnterpriseBean().getLocalHomeInterface()};
			if (ejb.hasRemoteClient() && !ejb.hasLocalClient())
				return new JavaClass[]{ejb.getHomeInterface()};
			if (ejb.hasRemoteClient() && ejb.hasLocalClient())
				return new JavaClass[]{ejb.getLocalHomeInterface(), ejb.getHomeInterface()};
		}
		return null;
	}
}







