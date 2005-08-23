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
package org.eclipse.jst.j2ee.ejb.internal.impl;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.EObjectImpl;
import org.eclipse.emf.ecore.util.EObjectContainmentEList;
import org.eclipse.emf.ecore.util.EObjectContainmentWithInverseEList;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.emf.ecore.util.InternalEList;
import org.eclipse.jst.j2ee.common.MessageDestination;
import org.eclipse.jst.j2ee.common.SecurityRole;
import org.eclipse.jst.j2ee.ejb.AssemblyDescriptor;
import org.eclipse.jst.j2ee.ejb.EJBJar;
import org.eclipse.jst.j2ee.ejb.EjbPackage;
import org.eclipse.jst.j2ee.ejb.EnterpriseBean;
import org.eclipse.jst.j2ee.ejb.ExcludeList;
import org.eclipse.jst.j2ee.ejb.MethodElement;
import org.eclipse.jst.j2ee.ejb.MethodPermission;
import org.eclipse.jst.j2ee.ejb.MethodTransaction;
import org.eclipse.wst.common.internal.emf.utilities.ExtendedEcoreUtil;


/**
 * The assembly-descriptor element contains application-assembly information.  The application-assembly information consists of the following parts: the definition of security roles, the definition of method permissions, and the definition of transaction attributes for enterprise beans with container-managed transaction demarcation. All the parts are optional in the sense that they are omitted if the lists represented by them are empty. Providing an assembly-descriptor in the deployment descriptor is optional for the ejb-jar file producer.
 */
public class AssemblyDescriptorImpl extends EObjectImpl implements AssemblyDescriptor, EObject {

	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	protected EList methodPermissions = null;
	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	protected EList methodTransactions = null;
	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	protected EList securityRoles = null;
	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	protected ExcludeList excludeList = null;
	/**
	 * The cached value of the '{@link #getMessageDestinations() <em>Message Destinations</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getMessageDestinations()
	 * @generated
	 * @ordered
	 */
	protected EList messageDestinations = null;

	public AssemblyDescriptorImpl() {
		super();
	}
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected EClass eStaticClass() {
		return EjbPackage.eINSTANCE.getAssemblyDescriptor();
	}

/**
 * Return the first method permission that contains all the roles in securityRoles and
 * is the same size
 */

public MethodPermission getMethodPermission(List securityRolesList) {
	List permissions = getMethodPermissions();
	MethodPermission temp = null;
	for (int i = 0; i < permissions.size(); i++) {
		temp = (MethodPermission) permissions.get(i);
		if (temp.getRoles().containsAll(securityRolesList) && temp.getRoles().size() == securityRolesList.size())
			return temp;
	}
	return null;
}
public List getMethodPermissionMethodElements(EnterpriseBean anEJB) {
	List allMethodElements = new ArrayList();
	List permissions = getMethodPermissions();
	MethodPermission permission;
	for (int i = 0; i < permissions.size(); i++){
		permission = (MethodPermission) permissions.get(i);
		allMethodElements.addAll(permission.getMethodElements(anEJB)); 
	}
	return allMethodElements;
}
	

public List getMethodTransactionMethodElements(EnterpriseBean anEJB) {
	List allMethodElements = new ArrayList();
	List transactions = getMethodTransactions();
	MethodTransaction transaction;
	for (int i = 0; i < transactions.size(); i++){
		transaction = (MethodTransaction) transactions.get(i);
		allMethodElements.addAll(transaction.getMethodElements(anEJB)); 
	}
	return allMethodElements;
}
	
public SecurityRole getSecurityRoleNamed(String roleName) {
	java.util.List tempRoles = getSecurityRoles();
	SecurityRole role;
	for (int i = 0; i < tempRoles.size(); i++) {
		role = (SecurityRole) tempRoles.get(i);
		if (role.getRoleName().equals(roleName))
			return role;
	}
	return null;
}

/**
 * @see org.eclipse.jst.j2ee.internal.ejb.AssemblyDescriptor
 */
public void renameSecurityRole(java.lang.String existingRoleName, java.lang.String newRoleName) {
	SecurityRole role = getSecurityRoleNamed(existingRoleName);
	role.setRoleName(newRoleName);
}
	/**
	 * @generated This field/method will be replaced during code generation 
	 */
	public EList getMethodPermissions() {
		if (methodPermissions == null) {
			methodPermissions = new EObjectContainmentWithInverseEList(MethodPermission.class, this, EjbPackage.ASSEMBLY_DESCRIPTOR__METHOD_PERMISSIONS, EjbPackage.METHOD_PERMISSION__ASSEMBLY_DESCRIPTOR);
		}
		return methodPermissions;
	}

	/**
	 * @generated This field/method will be replaced during code generation 
	 * Specifies how the container must manage transaction scopes for the enterprise
	 * bean's method invocations.  The element consists of an optional description, a
	 * list of method elements, and a transaction attribute.The transaction attribute
	 * is to be applied to all the specified methods.
	 */
	public EList getMethodTransactions() {
		if (methodTransactions == null) {
			methodTransactions = new EObjectContainmentWithInverseEList(MethodTransaction.class, this, EjbPackage.ASSEMBLY_DESCRIPTOR__METHOD_TRANSACTIONS, EjbPackage.METHOD_TRANSACTION__ASSEMBLY_DESCRIPTOR);
		}
		return methodTransactions;
	}

	/**
	 * @generated This field/method will be replaced during code generation 
	 */
	public EJBJar getEjbJar() {
		if (eContainerFeatureID != EjbPackage.ASSEMBLY_DESCRIPTOR__EJB_JAR) return null;
		return (EJBJar)eContainer;
	}

	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	public void setEjbJar(EJBJar newEjbJar) {
		if (newEjbJar != eContainer || (eContainerFeatureID != EjbPackage.ASSEMBLY_DESCRIPTOR__EJB_JAR && newEjbJar != null)) {
			if (EcoreUtil.isAncestor(this, newEjbJar))
				throw new IllegalArgumentException("Recursive containment not allowed for " + toString());
			NotificationChain msgs = null;
			if (eContainer != null)
				msgs = eBasicRemoveFromContainer(msgs);
			if (newEjbJar != null)
				msgs = ((InternalEObject)newEjbJar).eInverseAdd(this, EjbPackage.EJB_JAR__ASSEMBLY_DESCRIPTOR, EJBJar.class, msgs);
			msgs = eBasicSetContainer((InternalEObject)newEjbJar, EjbPackage.ASSEMBLY_DESCRIPTOR__EJB_JAR, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, EjbPackage.ASSEMBLY_DESCRIPTOR__EJB_JAR, newEjbJar, newEjbJar));
	}

	/**
	 * @generated This field/method will be replaced during code generation 
	 */
	public EList getSecurityRoles() {
		if (securityRoles == null) {
			securityRoles = new EObjectContainmentEList(SecurityRole.class, this, EjbPackage.ASSEMBLY_DESCRIPTOR__SECURITY_ROLES);
		}
		return securityRoles;
	}

	/**
	 * @generated This field/method will be replaced during code generation 
	 */
	public ExcludeList getExcludeList() {
		return excludeList;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetExcludeList(ExcludeList newExcludeList, NotificationChain msgs) {
		ExcludeList oldExcludeList = excludeList;
		excludeList = newExcludeList;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, EjbPackage.ASSEMBLY_DESCRIPTOR__EXCLUDE_LIST, oldExcludeList, newExcludeList);
			if (msgs == null) msgs = notification; else msgs.add(notification);
		}
		return msgs;
	}

	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	public void setExcludeList(ExcludeList newExcludeList) {
		if (newExcludeList != excludeList) {
			NotificationChain msgs = null;
			if (excludeList != null)
				msgs = ((InternalEObject)excludeList).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - EjbPackage.ASSEMBLY_DESCRIPTOR__EXCLUDE_LIST, null, msgs);
			if (newExcludeList != null)
				msgs = ((InternalEObject)newExcludeList).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - EjbPackage.ASSEMBLY_DESCRIPTOR__EXCLUDE_LIST, null, msgs);
			msgs = basicSetExcludeList(newExcludeList, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, EjbPackage.ASSEMBLY_DESCRIPTOR__EXCLUDE_LIST, newExcludeList, newExcludeList));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList getMessageDestinations() {
		if (messageDestinations == null) {
			messageDestinations = new EObjectContainmentEList(MessageDestination.class, this, EjbPackage.ASSEMBLY_DESCRIPTOR__MESSAGE_DESTINATIONS);
		}
		return messageDestinations;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain eInverseAdd(InternalEObject otherEnd, int featureID, Class baseClass, NotificationChain msgs) {
		if (featureID >= 0) {
			switch (eDerivedStructuralFeatureID(featureID, baseClass)) {
				case EjbPackage.ASSEMBLY_DESCRIPTOR__METHOD_PERMISSIONS:
					return ((InternalEList)getMethodPermissions()).basicAdd(otherEnd, msgs);
				case EjbPackage.ASSEMBLY_DESCRIPTOR__METHOD_TRANSACTIONS:
					return ((InternalEList)getMethodTransactions()).basicAdd(otherEnd, msgs);
				case EjbPackage.ASSEMBLY_DESCRIPTOR__EJB_JAR:
					if (eContainer != null)
						msgs = eBasicRemoveFromContainer(msgs);
					return eBasicSetContainer(otherEnd, EjbPackage.ASSEMBLY_DESCRIPTOR__EJB_JAR, msgs);
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
				case EjbPackage.ASSEMBLY_DESCRIPTOR__METHOD_PERMISSIONS:
					return ((InternalEList)getMethodPermissions()).basicRemove(otherEnd, msgs);
				case EjbPackage.ASSEMBLY_DESCRIPTOR__METHOD_TRANSACTIONS:
					return ((InternalEList)getMethodTransactions()).basicRemove(otherEnd, msgs);
				case EjbPackage.ASSEMBLY_DESCRIPTOR__EJB_JAR:
					return eBasicSetContainer(null, EjbPackage.ASSEMBLY_DESCRIPTOR__EJB_JAR, msgs);
				case EjbPackage.ASSEMBLY_DESCRIPTOR__SECURITY_ROLES:
					return ((InternalEList)getSecurityRoles()).basicRemove(otherEnd, msgs);
				case EjbPackage.ASSEMBLY_DESCRIPTOR__EXCLUDE_LIST:
					return basicSetExcludeList(null, msgs);
				case EjbPackage.ASSEMBLY_DESCRIPTOR__MESSAGE_DESTINATIONS:
					return ((InternalEList)getMessageDestinations()).basicRemove(otherEnd, msgs);
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
				case EjbPackage.ASSEMBLY_DESCRIPTOR__EJB_JAR:
					return eContainer.eInverseRemove(this, EjbPackage.EJB_JAR__ASSEMBLY_DESCRIPTOR, EJBJar.class, msgs);
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
			case EjbPackage.ASSEMBLY_DESCRIPTOR__METHOD_PERMISSIONS:
				return getMethodPermissions();
			case EjbPackage.ASSEMBLY_DESCRIPTOR__METHOD_TRANSACTIONS:
				return getMethodTransactions();
			case EjbPackage.ASSEMBLY_DESCRIPTOR__EJB_JAR:
				return getEjbJar();
			case EjbPackage.ASSEMBLY_DESCRIPTOR__SECURITY_ROLES:
				return getSecurityRoles();
			case EjbPackage.ASSEMBLY_DESCRIPTOR__EXCLUDE_LIST:
				return getExcludeList();
			case EjbPackage.ASSEMBLY_DESCRIPTOR__MESSAGE_DESTINATIONS:
				return getMessageDestinations();
		}
		return eDynamicGet(eFeature, resolve);
	}

	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	public boolean eIsSet(EStructuralFeature eFeature) {
		switch (eDerivedStructuralFeatureID(eFeature)) {
			case EjbPackage.ASSEMBLY_DESCRIPTOR__METHOD_PERMISSIONS:
				return methodPermissions != null && !methodPermissions.isEmpty();
			case EjbPackage.ASSEMBLY_DESCRIPTOR__METHOD_TRANSACTIONS:
				return methodTransactions != null && !methodTransactions.isEmpty();
			case EjbPackage.ASSEMBLY_DESCRIPTOR__EJB_JAR:
				return getEjbJar() != null;
			case EjbPackage.ASSEMBLY_DESCRIPTOR__SECURITY_ROLES:
				return securityRoles != null && !securityRoles.isEmpty();
			case EjbPackage.ASSEMBLY_DESCRIPTOR__EXCLUDE_LIST:
				return excludeList != null;
			case EjbPackage.ASSEMBLY_DESCRIPTOR__MESSAGE_DESTINATIONS:
				return messageDestinations != null && !messageDestinations.isEmpty();
		}
		return eDynamicIsSet(eFeature);
	}

	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	public void eSet(EStructuralFeature eFeature, Object newValue) {
		switch (eDerivedStructuralFeatureID(eFeature)) {
			case EjbPackage.ASSEMBLY_DESCRIPTOR__METHOD_PERMISSIONS:
				getMethodPermissions().clear();
				getMethodPermissions().addAll((Collection)newValue);
				return;
			case EjbPackage.ASSEMBLY_DESCRIPTOR__METHOD_TRANSACTIONS:
				getMethodTransactions().clear();
				getMethodTransactions().addAll((Collection)newValue);
				return;
			case EjbPackage.ASSEMBLY_DESCRIPTOR__EJB_JAR:
				setEjbJar((EJBJar)newValue);
				return;
			case EjbPackage.ASSEMBLY_DESCRIPTOR__SECURITY_ROLES:
				getSecurityRoles().clear();
				getSecurityRoles().addAll((Collection)newValue);
				return;
			case EjbPackage.ASSEMBLY_DESCRIPTOR__EXCLUDE_LIST:
				setExcludeList((ExcludeList)newValue);
				return;
			case EjbPackage.ASSEMBLY_DESCRIPTOR__MESSAGE_DESTINATIONS:
				getMessageDestinations().clear();
				getMessageDestinations().addAll((Collection)newValue);
				return;
		}
		eDynamicSet(eFeature, newValue);
	}

	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	public void eUnset(EStructuralFeature eFeature) {
		switch (eDerivedStructuralFeatureID(eFeature)) {
			case EjbPackage.ASSEMBLY_DESCRIPTOR__METHOD_PERMISSIONS:
				getMethodPermissions().clear();
				return;
			case EjbPackage.ASSEMBLY_DESCRIPTOR__METHOD_TRANSACTIONS:
				getMethodTransactions().clear();
				return;
			case EjbPackage.ASSEMBLY_DESCRIPTOR__EJB_JAR:
				setEjbJar((EJBJar)null);
				return;
			case EjbPackage.ASSEMBLY_DESCRIPTOR__SECURITY_ROLES:
				getSecurityRoles().clear();
				return;
			case EjbPackage.ASSEMBLY_DESCRIPTOR__EXCLUDE_LIST:
				setExcludeList((ExcludeList)null);
				return;
			case EjbPackage.ASSEMBLY_DESCRIPTOR__MESSAGE_DESTINATIONS:
				getMessageDestinations().clear();
				return;
		}
		eDynamicUnset(eFeature);
	}

	/**
	 * Remove the MethodElements that are referencing @anEJB.
	 */
	public void removeData(EnterpriseBean anEJB) {
		if (anEJB != null) {
			removeMethodPermissionData(anEJB);
			removeMethodTransactionData(anEJB);
		}
	}
	/**
	 * Remove the MethodElements that are referencing @anEJB.
	 */
	protected void removeMethodPermissionData(EnterpriseBean anEJB) {
		List mes = getMethodPermissionMethodElements(anEJB);
		MethodElement me;
		MethodPermission mp;
		for (int i = 0; i < mes.size(); i++){
			me = (MethodElement) mes.get(i);
			mp = (MethodPermission)me.eContainer();
			if (mp.getMethodElements().size() == 1){
				ExtendedEcoreUtil.becomeProxy(mp, mp.eResource());
				getMethodPermissions().remove(mp);
			}
			else{
				ExtendedEcoreUtil.becomeProxy(me, me.eResource());
				mp.getMethodElements().remove(me);
			}
		}
	}
	/**
	 * Remove the MethodElements that are referencing @anEJB.
	 */
	protected void removeMethodTransactionData(EnterpriseBean anEJB) {
		List mes = getMethodTransactionMethodElements(anEJB);
		MethodElement me;
		MethodTransaction mt;
		for (int i = 0; i < mes.size(); i++){
			me = (MethodElement) mes.get(i);
			mt = (MethodTransaction)me.eContainer();
			if (mt.getMethodElements().size() == 1){
				ExtendedEcoreUtil.becomeProxy(mt, mt.eResource());
				getMethodTransactions().remove(mt);
			}
			else{
				ExtendedEcoreUtil.becomeProxy(me, me.eResource());	
				mt.getMethodElements().remove(me);
			}
		}
	}

}





