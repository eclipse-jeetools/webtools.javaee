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
import java.util.List;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.emf.ecore.util.InternalEList;
import org.eclipse.jem.java.JavaClass;
import org.eclipse.jem.java.Method;
import org.eclipse.jst.j2ee.ejb.EjbPackage;
import org.eclipse.jst.j2ee.ejb.EnterpriseBean;
import org.eclipse.jst.j2ee.ejb.MethodElementKind;
import org.eclipse.jst.j2ee.ejb.Query;
import org.eclipse.jst.j2ee.ejb.QueryMethod;

/**
 * @generated
 */
public class QueryMethodImpl extends MethodElementImpl implements QueryMethod{

	public QueryMethodImpl() {
		super();
	}
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected EClass eStaticClass() {
		return EjbPackage.eINSTANCE.getQueryMethod();
	}

/**
 * For Query methods, their enterprise bean must be derived.
 * It is the Entity which contains the Query.
 */
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
	public Query getQuery() {
		if (eContainerFeatureID != EjbPackage.QUERY_METHOD__QUERY) return null;
		return (Query)eContainer;
	}

	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	public void setQuery(Query newQuery) {
		if (newQuery != eContainer || (eContainerFeatureID != EjbPackage.QUERY_METHOD__QUERY && newQuery != null)) {
			if (EcoreUtil.isAncestor(this, newQuery))
				throw new IllegalArgumentException("Recursive containment not allowed for " + toString());
			NotificationChain msgs = null;
			if (eContainer != null)
				msgs = eBasicRemoveFromContainer(msgs);
			if (newQuery != null)
				msgs = ((InternalEObject)newQuery).eInverseAdd(this, EjbPackage.QUERY__QUERY_METHOD, Query.class, msgs);
			msgs = eBasicSetContainer((InternalEObject)newQuery, EjbPackage.QUERY_METHOD__QUERY, msgs);
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
	public NotificationChain eInverseAdd(InternalEObject otherEnd, int featureID, Class baseClass, NotificationChain msgs) {
		if (featureID >= 0) {
			switch (eDerivedStructuralFeatureID(featureID, baseClass)) {
				case EjbPackage.QUERY_METHOD__QUERY:
					if (eContainer != null)
						msgs = eBasicRemoveFromContainer(msgs);
					return eBasicSetContainer(otherEnd, EjbPackage.QUERY_METHOD__QUERY, msgs);
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
				case EjbPackage.QUERY_METHOD__DESCRIPTIONS:
					return ((InternalEList)getDescriptions()).basicRemove(otherEnd, msgs);
				case EjbPackage.QUERY_METHOD__QUERY:
					return eBasicSetContainer(null, EjbPackage.QUERY_METHOD__QUERY, msgs);
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
				case EjbPackage.QUERY_METHOD__QUERY:
					return eContainer.eInverseRemove(this, EjbPackage.QUERY__QUERY_METHOD, Query.class, msgs);
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
			case EjbPackage.QUERY_METHOD__NAME:
				return getName();
			case EjbPackage.QUERY_METHOD__PARMS:
				return getParms();
			case EjbPackage.QUERY_METHOD__TYPE:
				return getType();
			case EjbPackage.QUERY_METHOD__DESCRIPTION:
				return getDescription();
			case EjbPackage.QUERY_METHOD__ENTERPRISE_BEAN:
				if (resolve) return getEnterpriseBean();
				return basicGetEnterpriseBean();
			case EjbPackage.QUERY_METHOD__DESCRIPTIONS:
				return getDescriptions();
			case EjbPackage.QUERY_METHOD__QUERY:
				return getQuery();
		}
		return eDynamicGet(eFeature, resolve);
	}

	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	public boolean eIsSet(EStructuralFeature eFeature) {
		switch (eDerivedStructuralFeatureID(eFeature)) {
			case EjbPackage.QUERY_METHOD__NAME:
				return NAME_EDEFAULT == null ? name != null : !NAME_EDEFAULT.equals(name);
			case EjbPackage.QUERY_METHOD__PARMS:
				return PARMS_EDEFAULT == null ? parms != null : !PARMS_EDEFAULT.equals(parms);
			case EjbPackage.QUERY_METHOD__TYPE:
				return isSetType();
			case EjbPackage.QUERY_METHOD__DESCRIPTION:
				return DESCRIPTION_EDEFAULT == null ? description != null : !DESCRIPTION_EDEFAULT.equals(description);
			case EjbPackage.QUERY_METHOD__ENTERPRISE_BEAN:
				return enterpriseBean != null;
			case EjbPackage.QUERY_METHOD__DESCRIPTIONS:
				return descriptions != null && !descriptions.isEmpty();
			case EjbPackage.QUERY_METHOD__QUERY:
				return getQuery() != null;
		}
		return eDynamicIsSet(eFeature);
	}

	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	public void eSet(EStructuralFeature eFeature, Object newValue) {
		switch (eDerivedStructuralFeatureID(eFeature)) {
			case EjbPackage.QUERY_METHOD__NAME:
				setName((String)newValue);
				return;
			case EjbPackage.QUERY_METHOD__PARMS:
				setParms((String)newValue);
				return;
			case EjbPackage.QUERY_METHOD__TYPE:
				setType((MethodElementKind)newValue);
				return;
			case EjbPackage.QUERY_METHOD__DESCRIPTION:
				setDescription((String)newValue);
				return;
			case EjbPackage.QUERY_METHOD__ENTERPRISE_BEAN:
				setEnterpriseBean((EnterpriseBean)newValue);
				return;
			case EjbPackage.QUERY_METHOD__DESCRIPTIONS:
				getDescriptions().clear();
				getDescriptions().addAll((Collection)newValue);
				return;
			case EjbPackage.QUERY_METHOD__QUERY:
				setQuery((Query)newValue);
				return;
		}
		eDynamicSet(eFeature, newValue);
	}

	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	public void eUnset(EStructuralFeature eFeature) {
		switch (eDerivedStructuralFeatureID(eFeature)) {
			case EjbPackage.QUERY_METHOD__NAME:
				setName(NAME_EDEFAULT);
				return;
			case EjbPackage.QUERY_METHOD__PARMS:
				setParms(PARMS_EDEFAULT);
				return;
			case EjbPackage.QUERY_METHOD__TYPE:
				unsetType();
				return;
			case EjbPackage.QUERY_METHOD__DESCRIPTION:
				setDescription(DESCRIPTION_EDEFAULT);
				return;
			case EjbPackage.QUERY_METHOD__ENTERPRISE_BEAN:
				setEnterpriseBean((EnterpriseBean)null);
				return;
			case EjbPackage.QUERY_METHOD__DESCRIPTIONS:
				getDescriptions().clear();
				return;
			case EjbPackage.QUERY_METHOD__QUERY:
				setQuery((Query)null);
				return;
		}
		eDynamicUnset(eFeature);
	}

	/**
	 * You should use getClientTypeJavaClasses to ensure you pick up both home interfaces
	 * if they exist.
	 */
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







