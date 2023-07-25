/*******************************************************************************
 * Copyright (c) 2007, 2023 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 *
 * Contributors:
 * IBM Corporation - initial API and implementation
 *******************************************************************************/
package org.eclipse.jst.javaee.ejb.internal.impl;

import java.util.Collection;
import java.util.List;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.EObjectImpl;

import org.eclipse.emf.ecore.util.EObjectContainmentEList;
import org.eclipse.emf.ecore.util.InternalEList;

import org.eclipse.jst.javaee.core.MessageDestination;
import org.eclipse.jst.javaee.core.SecurityRole;

import org.eclipse.jst.javaee.ejb.ApplicationException;
import org.eclipse.jst.javaee.ejb.AssemblyDescriptor;
import org.eclipse.jst.javaee.ejb.ContainerTransactionType;
import org.eclipse.jst.javaee.ejb.ExcludeList;
import org.eclipse.jst.javaee.ejb.InterceptorBindingType;
import org.eclipse.jst.javaee.ejb.MethodPermission;

import org.eclipse.jst.javaee.ejb.internal.metadata.EjbPackage;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Assembly Descriptor</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.eclipse.jst.javaee.ejb.internal.impl.AssemblyDescriptorImpl#getSecurityRoles <em>Security Roles</em>}</li>
 *   <li>{@link org.eclipse.jst.javaee.ejb.internal.impl.AssemblyDescriptorImpl#getMethodPermissions <em>Method Permissions</em>}</li>
 *   <li>{@link org.eclipse.jst.javaee.ejb.internal.impl.AssemblyDescriptorImpl#getContainerTransactions <em>Container Transactions</em>}</li>
 *   <li>{@link org.eclipse.jst.javaee.ejb.internal.impl.AssemblyDescriptorImpl#getInterceptorBindings <em>Interceptor Bindings</em>}</li>
 *   <li>{@link org.eclipse.jst.javaee.ejb.internal.impl.AssemblyDescriptorImpl#getMessageDestinations <em>Message Destinations</em>}</li>
 *   <li>{@link org.eclipse.jst.javaee.ejb.internal.impl.AssemblyDescriptorImpl#getExcludeList <em>Exclude List</em>}</li>
 *   <li>{@link org.eclipse.jst.javaee.ejb.internal.impl.AssemblyDescriptorImpl#getApplicationExceptions <em>Application Exceptions</em>}</li>
 *   <li>{@link org.eclipse.jst.javaee.ejb.internal.impl.AssemblyDescriptorImpl#getId <em>Id</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class AssemblyDescriptorImpl extends EObjectImpl implements AssemblyDescriptor {
	/**
	 * The cached value of the '{@link #getSecurityRoles() <em>Security Roles</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getSecurityRoles()
	 * @generated
	 * @ordered
	 */
	protected EList<SecurityRole> securityRoles;

	/**
	 * The cached value of the '{@link #getMethodPermissions() <em>Method Permissions</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getMethodPermissions()
	 * @generated
	 * @ordered
	 */
	protected EList<MethodPermission> methodPermissions;

	/**
	 * The cached value of the '{@link #getContainerTransactions() <em>Container Transactions</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getContainerTransactions()
	 * @generated
	 * @ordered
	 */
	protected EList<ContainerTransactionType> containerTransactions;

	/**
	 * The cached value of the '{@link #getInterceptorBindings() <em>Interceptor Bindings</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getInterceptorBindings()
	 * @generated
	 * @ordered
	 */
	protected EList<InterceptorBindingType> interceptorBindings;

	/**
	 * The cached value of the '{@link #getMessageDestinations() <em>Message Destinations</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getMessageDestinations()
	 * @generated
	 * @ordered
	 */
	protected EList<MessageDestination> messageDestinations;

	/**
	 * The cached value of the '{@link #getExcludeList() <em>Exclude List</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getExcludeList()
	 * @generated
	 * @ordered
	 */
	protected ExcludeList excludeList;

	/**
	 * The cached value of the '{@link #getApplicationExceptions() <em>Application Exceptions</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getApplicationExceptions()
	 * @generated
	 * @ordered
	 */
	protected EList<ApplicationException> applicationExceptions;

	/**
	 * The default value of the '{@link #getId() <em>Id</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getId()
	 * @generated
	 * @ordered
	 */
	protected static final String ID_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getId() <em>Id</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getId()
	 * @generated
	 * @ordered
	 */
	protected String id = ID_EDEFAULT;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected AssemblyDescriptorImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return EjbPackage.Literals.ASSEMBLY_DESCRIPTOR;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public List<SecurityRole> getSecurityRoles() {
		if (securityRoles == null) {
			securityRoles = new EObjectContainmentEList<SecurityRole>(SecurityRole.class, this, EjbPackage.ASSEMBLY_DESCRIPTOR__SECURITY_ROLES);
		}
		return securityRoles;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public List<MethodPermission> getMethodPermissions() {
		if (methodPermissions == null) {
			methodPermissions = new EObjectContainmentEList<MethodPermission>(MethodPermission.class, this, EjbPackage.ASSEMBLY_DESCRIPTOR__METHOD_PERMISSIONS);
		}
		return methodPermissions;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public List<ContainerTransactionType> getContainerTransactions() {
		if (containerTransactions == null) {
			containerTransactions = new EObjectContainmentEList<ContainerTransactionType>(ContainerTransactionType.class, this, EjbPackage.ASSEMBLY_DESCRIPTOR__CONTAINER_TRANSACTIONS);
		}
		return containerTransactions;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public List<InterceptorBindingType> getInterceptorBindings() {
		if (interceptorBindings == null) {
			interceptorBindings = new EObjectContainmentEList<InterceptorBindingType>(InterceptorBindingType.class, this, EjbPackage.ASSEMBLY_DESCRIPTOR__INTERCEPTOR_BINDINGS);
		}
		return interceptorBindings;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public List<MessageDestination> getMessageDestinations() {
		if (messageDestinations == null) {
			messageDestinations = new EObjectContainmentEList<MessageDestination>(MessageDestination.class, this, EjbPackage.ASSEMBLY_DESCRIPTOR__MESSAGE_DESTINATIONS);
		}
		return messageDestinations;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
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
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
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
	@Override
	public List<ApplicationException> getApplicationExceptions() {
		if (applicationExceptions == null) {
			applicationExceptions = new EObjectContainmentEList<ApplicationException>(ApplicationException.class, this, EjbPackage.ASSEMBLY_DESCRIPTOR__APPLICATION_EXCEPTIONS);
		}
		return applicationExceptions;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String getId() {
		return id;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setId(String newId) {
		String oldId = id;
		id = newId;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, EjbPackage.ASSEMBLY_DESCRIPTOR__ID, oldId, id));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case EjbPackage.ASSEMBLY_DESCRIPTOR__SECURITY_ROLES:
				return ((InternalEList<?>)getSecurityRoles()).basicRemove(otherEnd, msgs);
			case EjbPackage.ASSEMBLY_DESCRIPTOR__METHOD_PERMISSIONS:
				return ((InternalEList<?>)getMethodPermissions()).basicRemove(otherEnd, msgs);
			case EjbPackage.ASSEMBLY_DESCRIPTOR__CONTAINER_TRANSACTIONS:
				return ((InternalEList<?>)getContainerTransactions()).basicRemove(otherEnd, msgs);
			case EjbPackage.ASSEMBLY_DESCRIPTOR__INTERCEPTOR_BINDINGS:
				return ((InternalEList<?>)getInterceptorBindings()).basicRemove(otherEnd, msgs);
			case EjbPackage.ASSEMBLY_DESCRIPTOR__MESSAGE_DESTINATIONS:
				return ((InternalEList<?>)getMessageDestinations()).basicRemove(otherEnd, msgs);
			case EjbPackage.ASSEMBLY_DESCRIPTOR__EXCLUDE_LIST:
				return basicSetExcludeList(null, msgs);
			case EjbPackage.ASSEMBLY_DESCRIPTOR__APPLICATION_EXCEPTIONS:
				return ((InternalEList<?>)getApplicationExceptions()).basicRemove(otherEnd, msgs);
		}
		return super.eInverseRemove(otherEnd, featureID, msgs);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case EjbPackage.ASSEMBLY_DESCRIPTOR__SECURITY_ROLES:
				return getSecurityRoles();
			case EjbPackage.ASSEMBLY_DESCRIPTOR__METHOD_PERMISSIONS:
				return getMethodPermissions();
			case EjbPackage.ASSEMBLY_DESCRIPTOR__CONTAINER_TRANSACTIONS:
				return getContainerTransactions();
			case EjbPackage.ASSEMBLY_DESCRIPTOR__INTERCEPTOR_BINDINGS:
				return getInterceptorBindings();
			case EjbPackage.ASSEMBLY_DESCRIPTOR__MESSAGE_DESTINATIONS:
				return getMessageDestinations();
			case EjbPackage.ASSEMBLY_DESCRIPTOR__EXCLUDE_LIST:
				return getExcludeList();
			case EjbPackage.ASSEMBLY_DESCRIPTOR__APPLICATION_EXCEPTIONS:
				return getApplicationExceptions();
			case EjbPackage.ASSEMBLY_DESCRIPTOR__ID:
				return getId();
		}
		return super.eGet(featureID, resolve, coreType);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@SuppressWarnings("unchecked")
	@Override
	public void eSet(int featureID, Object newValue) {
		switch (featureID) {
			case EjbPackage.ASSEMBLY_DESCRIPTOR__SECURITY_ROLES:
				getSecurityRoles().clear();
				getSecurityRoles().addAll((Collection<? extends SecurityRole>)newValue);
				return;
			case EjbPackage.ASSEMBLY_DESCRIPTOR__METHOD_PERMISSIONS:
				getMethodPermissions().clear();
				getMethodPermissions().addAll((Collection<? extends MethodPermission>)newValue);
				return;
			case EjbPackage.ASSEMBLY_DESCRIPTOR__CONTAINER_TRANSACTIONS:
				getContainerTransactions().clear();
				getContainerTransactions().addAll((Collection<? extends ContainerTransactionType>)newValue);
				return;
			case EjbPackage.ASSEMBLY_DESCRIPTOR__INTERCEPTOR_BINDINGS:
				getInterceptorBindings().clear();
				getInterceptorBindings().addAll((Collection<? extends InterceptorBindingType>)newValue);
				return;
			case EjbPackage.ASSEMBLY_DESCRIPTOR__MESSAGE_DESTINATIONS:
				getMessageDestinations().clear();
				getMessageDestinations().addAll((Collection<? extends MessageDestination>)newValue);
				return;
			case EjbPackage.ASSEMBLY_DESCRIPTOR__EXCLUDE_LIST:
				setExcludeList((ExcludeList)newValue);
				return;
			case EjbPackage.ASSEMBLY_DESCRIPTOR__APPLICATION_EXCEPTIONS:
				getApplicationExceptions().clear();
				getApplicationExceptions().addAll((Collection<? extends ApplicationException>)newValue);
				return;
			case EjbPackage.ASSEMBLY_DESCRIPTOR__ID:
				setId((String)newValue);
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
			case EjbPackage.ASSEMBLY_DESCRIPTOR__SECURITY_ROLES:
				getSecurityRoles().clear();
				return;
			case EjbPackage.ASSEMBLY_DESCRIPTOR__METHOD_PERMISSIONS:
				getMethodPermissions().clear();
				return;
			case EjbPackage.ASSEMBLY_DESCRIPTOR__CONTAINER_TRANSACTIONS:
				getContainerTransactions().clear();
				return;
			case EjbPackage.ASSEMBLY_DESCRIPTOR__INTERCEPTOR_BINDINGS:
				getInterceptorBindings().clear();
				return;
			case EjbPackage.ASSEMBLY_DESCRIPTOR__MESSAGE_DESTINATIONS:
				getMessageDestinations().clear();
				return;
			case EjbPackage.ASSEMBLY_DESCRIPTOR__EXCLUDE_LIST:
				setExcludeList((ExcludeList)null);
				return;
			case EjbPackage.ASSEMBLY_DESCRIPTOR__APPLICATION_EXCEPTIONS:
				getApplicationExceptions().clear();
				return;
			case EjbPackage.ASSEMBLY_DESCRIPTOR__ID:
				setId(ID_EDEFAULT);
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
			case EjbPackage.ASSEMBLY_DESCRIPTOR__SECURITY_ROLES:
				return securityRoles != null && !securityRoles.isEmpty();
			case EjbPackage.ASSEMBLY_DESCRIPTOR__METHOD_PERMISSIONS:
				return methodPermissions != null && !methodPermissions.isEmpty();
			case EjbPackage.ASSEMBLY_DESCRIPTOR__CONTAINER_TRANSACTIONS:
				return containerTransactions != null && !containerTransactions.isEmpty();
			case EjbPackage.ASSEMBLY_DESCRIPTOR__INTERCEPTOR_BINDINGS:
				return interceptorBindings != null && !interceptorBindings.isEmpty();
			case EjbPackage.ASSEMBLY_DESCRIPTOR__MESSAGE_DESTINATIONS:
				return messageDestinations != null && !messageDestinations.isEmpty();
			case EjbPackage.ASSEMBLY_DESCRIPTOR__EXCLUDE_LIST:
				return excludeList != null;
			case EjbPackage.ASSEMBLY_DESCRIPTOR__APPLICATION_EXCEPTIONS:
				return applicationExceptions != null && !applicationExceptions.isEmpty();
			case EjbPackage.ASSEMBLY_DESCRIPTOR__ID:
				return ID_EDEFAULT == null ? id != null : !ID_EDEFAULT.equals(id);
		}
		return super.eIsSet(featureID);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String toString() {
		if (eIsProxy()) return super.toString();

		StringBuffer result = new StringBuffer(super.toString());
		result.append(" (id: "); //$NON-NLS-1$
		result.append(id);
		result.append(')');
		return result.toString();
	}

} //AssemblyDescriptorImpl