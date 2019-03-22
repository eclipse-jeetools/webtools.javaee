/*******************************************************************************
 * Copyright (c) 2007 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 *
 * Contributors:
 * IBM Corporation - initial API and implementation
 *******************************************************************************/
package org.eclipse.jst.javaee.core.internal.impl;

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

import org.eclipse.jst.javaee.core.Description;
import org.eclipse.jst.javaee.core.EjbLocalRef;
import org.eclipse.jst.javaee.core.EjbRefType;
import org.eclipse.jst.javaee.core.InjectionTarget;

import org.eclipse.jst.javaee.core.internal.metadata.JavaeePackage;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Ejb Local Ref</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.eclipse.jst.javaee.core.internal.impl.EjbLocalRefImpl#getDescriptions <em>Descriptions</em>}</li>
 *   <li>{@link org.eclipse.jst.javaee.core.internal.impl.EjbLocalRefImpl#getEjbRefName <em>Ejb Ref Name</em>}</li>
 *   <li>{@link org.eclipse.jst.javaee.core.internal.impl.EjbLocalRefImpl#getEjbRefType <em>Ejb Ref Type</em>}</li>
 *   <li>{@link org.eclipse.jst.javaee.core.internal.impl.EjbLocalRefImpl#getLocalHome <em>Local Home</em>}</li>
 *   <li>{@link org.eclipse.jst.javaee.core.internal.impl.EjbLocalRefImpl#getLocal <em>Local</em>}</li>
 *   <li>{@link org.eclipse.jst.javaee.core.internal.impl.EjbLocalRefImpl#getEjbLink <em>Ejb Link</em>}</li>
 *   <li>{@link org.eclipse.jst.javaee.core.internal.impl.EjbLocalRefImpl#getMappedName <em>Mapped Name</em>}</li>
 *   <li>{@link org.eclipse.jst.javaee.core.internal.impl.EjbLocalRefImpl#getInjectionTargets <em>Injection Targets</em>}</li>
 *   <li>{@link org.eclipse.jst.javaee.core.internal.impl.EjbLocalRefImpl#getLookupName <em>Lookup Name</em>}</li>
 *   <li>{@link org.eclipse.jst.javaee.core.internal.impl.EjbLocalRefImpl#getId <em>Id</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class EjbLocalRefImpl extends EObjectImpl implements EjbLocalRef {
	/**
	 * The cached value of the '{@link #getDescriptions() <em>Descriptions</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getDescriptions()
	 * @generated
	 * @ordered
	 */
	protected EList<Description> descriptions;

	/**
	 * The default value of the '{@link #getEjbRefName() <em>Ejb Ref Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getEjbRefName()
	 * @generated
	 * @ordered
	 */
	protected static final String EJB_REF_NAME_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getEjbRefName() <em>Ejb Ref Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getEjbRefName()
	 * @generated
	 * @ordered
	 */
	protected String ejbRefName = EJB_REF_NAME_EDEFAULT;

	/**
	 * The default value of the '{@link #getEjbRefType() <em>Ejb Ref Type</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getEjbRefType()
	 * @generated
	 * @ordered
	 */
	protected static final EjbRefType EJB_REF_TYPE_EDEFAULT = EjbRefType.ENTITY_LITERAL;

	/**
	 * The cached value of the '{@link #getEjbRefType() <em>Ejb Ref Type</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getEjbRefType()
	 * @generated
	 * @ordered
	 */
	protected EjbRefType ejbRefType = EJB_REF_TYPE_EDEFAULT;

	/**
	 * This is true if the Ejb Ref Type attribute has been set.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	protected boolean ejbRefTypeESet;

	/**
	 * The default value of the '{@link #getLocalHome() <em>Local Home</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getLocalHome()
	 * @generated
	 * @ordered
	 */
	protected static final String LOCAL_HOME_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getLocalHome() <em>Local Home</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getLocalHome()
	 * @generated
	 * @ordered
	 */
	protected String localHome = LOCAL_HOME_EDEFAULT;

	/**
	 * The default value of the '{@link #getLocal() <em>Local</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getLocal()
	 * @generated
	 * @ordered
	 */
	protected static final String LOCAL_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getLocal() <em>Local</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getLocal()
	 * @generated
	 * @ordered
	 */
	protected String local = LOCAL_EDEFAULT;

	/**
	 * The default value of the '{@link #getEjbLink() <em>Ejb Link</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getEjbLink()
	 * @generated
	 * @ordered
	 */
	protected static final String EJB_LINK_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getEjbLink() <em>Ejb Link</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getEjbLink()
	 * @generated
	 * @ordered
	 */
	protected String ejbLink = EJB_LINK_EDEFAULT;

	/**
	 * The default value of the '{@link #getMappedName() <em>Mapped Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getMappedName()
	 * @generated
	 * @ordered
	 */
	protected static final String MAPPED_NAME_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getMappedName() <em>Mapped Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getMappedName()
	 * @generated
	 * @ordered
	 */
	protected String mappedName = MAPPED_NAME_EDEFAULT;

	/**
	 * The cached value of the '{@link #getInjectionTargets() <em>Injection Targets</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getInjectionTargets()
	 * @generated
	 * @ordered
	 */
	protected EList<InjectionTarget> injectionTargets;

	/**
	 * The default value of the '{@link #getLookupName() <em>Lookup Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getLookupName()
	 * @generated
	 * @ordered
	 */
	protected static final String LOOKUP_NAME_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getLookupName() <em>Lookup Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getLookupName()
	 * @generated
	 * @ordered
	 */
	protected String lookupName = LOOKUP_NAME_EDEFAULT;

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
	protected EjbLocalRefImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return JavaeePackage.Literals.EJB_LOCAL_REF;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public List<Description> getDescriptions() {
		if (descriptions == null) {
			descriptions = new EObjectContainmentEList<Description>(Description.class, this, JavaeePackage.EJB_LOCAL_REF__DESCRIPTIONS);
		}
		return descriptions;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getEjbRefName() {
		return ejbRefName;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setEjbRefName(String newEjbRefName) {
		String oldEjbRefName = ejbRefName;
		ejbRefName = newEjbRefName;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, JavaeePackage.EJB_LOCAL_REF__EJB_REF_NAME, oldEjbRefName, ejbRefName));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EjbRefType getEjbRefType() {
		return ejbRefType;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setEjbRefType(EjbRefType newEjbRefType) {
		EjbRefType oldEjbRefType = ejbRefType;
		ejbRefType = newEjbRefType == null ? EJB_REF_TYPE_EDEFAULT : newEjbRefType;
		boolean oldEjbRefTypeESet = ejbRefTypeESet;
		ejbRefTypeESet = true;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, JavaeePackage.EJB_LOCAL_REF__EJB_REF_TYPE, oldEjbRefType, ejbRefType, !oldEjbRefTypeESet));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void unsetEjbRefType() {
		EjbRefType oldEjbRefType = ejbRefType;
		boolean oldEjbRefTypeESet = ejbRefTypeESet;
		ejbRefType = EJB_REF_TYPE_EDEFAULT;
		ejbRefTypeESet = false;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.UNSET, JavaeePackage.EJB_LOCAL_REF__EJB_REF_TYPE, oldEjbRefType, EJB_REF_TYPE_EDEFAULT, oldEjbRefTypeESet));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean isSetEjbRefType() {
		return ejbRefTypeESet;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getLocalHome() {
		return localHome;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setLocalHome(String newLocalHome) {
		String oldLocalHome = localHome;
		localHome = newLocalHome;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, JavaeePackage.EJB_LOCAL_REF__LOCAL_HOME, oldLocalHome, localHome));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getLocal() {
		return local;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setLocal(String newLocal) {
		String oldLocal = local;
		local = newLocal;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, JavaeePackage.EJB_LOCAL_REF__LOCAL, oldLocal, local));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getEjbLink() {
		return ejbLink;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setEjbLink(String newEjbLink) {
		String oldEjbLink = ejbLink;
		ejbLink = newEjbLink;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, JavaeePackage.EJB_LOCAL_REF__EJB_LINK, oldEjbLink, ejbLink));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getMappedName() {
		return mappedName;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setMappedName(String newMappedName) {
		String oldMappedName = mappedName;
		mappedName = newMappedName;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, JavaeePackage.EJB_LOCAL_REF__MAPPED_NAME, oldMappedName, mappedName));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public List<InjectionTarget> getInjectionTargets() {
		if (injectionTargets == null) {
			injectionTargets = new EObjectContainmentEList<InjectionTarget>(InjectionTarget.class, this, JavaeePackage.EJB_LOCAL_REF__INJECTION_TARGETS);
		}
		return injectionTargets;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getLookupName() {
		return lookupName;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setLookupName(String newLookupName) {
		String oldLookupName = lookupName;
		lookupName = newLookupName;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, JavaeePackage.EJB_LOCAL_REF__LOOKUP_NAME, oldLookupName, lookupName));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getId() {
		return id;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setId(String newId) {
		String oldId = id;
		id = newId;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, JavaeePackage.EJB_LOCAL_REF__ID, oldId, id));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case JavaeePackage.EJB_LOCAL_REF__DESCRIPTIONS:
				return ((InternalEList<?>)getDescriptions()).basicRemove(otherEnd, msgs);
			case JavaeePackage.EJB_LOCAL_REF__INJECTION_TARGETS:
				return ((InternalEList<?>)getInjectionTargets()).basicRemove(otherEnd, msgs);
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
			case JavaeePackage.EJB_LOCAL_REF__DESCRIPTIONS:
				return getDescriptions();
			case JavaeePackage.EJB_LOCAL_REF__EJB_REF_NAME:
				return getEjbRefName();
			case JavaeePackage.EJB_LOCAL_REF__EJB_REF_TYPE:
				return getEjbRefType();
			case JavaeePackage.EJB_LOCAL_REF__LOCAL_HOME:
				return getLocalHome();
			case JavaeePackage.EJB_LOCAL_REF__LOCAL:
				return getLocal();
			case JavaeePackage.EJB_LOCAL_REF__EJB_LINK:
				return getEjbLink();
			case JavaeePackage.EJB_LOCAL_REF__MAPPED_NAME:
				return getMappedName();
			case JavaeePackage.EJB_LOCAL_REF__INJECTION_TARGETS:
				return getInjectionTargets();
			case JavaeePackage.EJB_LOCAL_REF__LOOKUP_NAME:
				return getLookupName();
			case JavaeePackage.EJB_LOCAL_REF__ID:
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
			case JavaeePackage.EJB_LOCAL_REF__DESCRIPTIONS:
				getDescriptions().clear();
				getDescriptions().addAll((Collection<? extends Description>)newValue);
				return;
			case JavaeePackage.EJB_LOCAL_REF__EJB_REF_NAME:
				setEjbRefName((String)newValue);
				return;
			case JavaeePackage.EJB_LOCAL_REF__EJB_REF_TYPE:
				setEjbRefType((EjbRefType)newValue);
				return;
			case JavaeePackage.EJB_LOCAL_REF__LOCAL_HOME:
				setLocalHome((String)newValue);
				return;
			case JavaeePackage.EJB_LOCAL_REF__LOCAL:
				setLocal((String)newValue);
				return;
			case JavaeePackage.EJB_LOCAL_REF__EJB_LINK:
				setEjbLink((String)newValue);
				return;
			case JavaeePackage.EJB_LOCAL_REF__MAPPED_NAME:
				setMappedName((String)newValue);
				return;
			case JavaeePackage.EJB_LOCAL_REF__INJECTION_TARGETS:
				getInjectionTargets().clear();
				getInjectionTargets().addAll((Collection<? extends InjectionTarget>)newValue);
				return;
			case JavaeePackage.EJB_LOCAL_REF__LOOKUP_NAME:
				setLookupName((String)newValue);
				return;
			case JavaeePackage.EJB_LOCAL_REF__ID:
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
			case JavaeePackage.EJB_LOCAL_REF__DESCRIPTIONS:
				getDescriptions().clear();
				return;
			case JavaeePackage.EJB_LOCAL_REF__EJB_REF_NAME:
				setEjbRefName(EJB_REF_NAME_EDEFAULT);
				return;
			case JavaeePackage.EJB_LOCAL_REF__EJB_REF_TYPE:
				unsetEjbRefType();
				return;
			case JavaeePackage.EJB_LOCAL_REF__LOCAL_HOME:
				setLocalHome(LOCAL_HOME_EDEFAULT);
				return;
			case JavaeePackage.EJB_LOCAL_REF__LOCAL:
				setLocal(LOCAL_EDEFAULT);
				return;
			case JavaeePackage.EJB_LOCAL_REF__EJB_LINK:
				setEjbLink(EJB_LINK_EDEFAULT);
				return;
			case JavaeePackage.EJB_LOCAL_REF__MAPPED_NAME:
				setMappedName(MAPPED_NAME_EDEFAULT);
				return;
			case JavaeePackage.EJB_LOCAL_REF__INJECTION_TARGETS:
				getInjectionTargets().clear();
				return;
			case JavaeePackage.EJB_LOCAL_REF__LOOKUP_NAME:
				setLookupName(LOOKUP_NAME_EDEFAULT);
				return;
			case JavaeePackage.EJB_LOCAL_REF__ID:
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
			case JavaeePackage.EJB_LOCAL_REF__DESCRIPTIONS:
				return descriptions != null && !descriptions.isEmpty();
			case JavaeePackage.EJB_LOCAL_REF__EJB_REF_NAME:
				return EJB_REF_NAME_EDEFAULT == null ? ejbRefName != null : !EJB_REF_NAME_EDEFAULT.equals(ejbRefName);
			case JavaeePackage.EJB_LOCAL_REF__EJB_REF_TYPE:
				return isSetEjbRefType();
			case JavaeePackage.EJB_LOCAL_REF__LOCAL_HOME:
				return LOCAL_HOME_EDEFAULT == null ? localHome != null : !LOCAL_HOME_EDEFAULT.equals(localHome);
			case JavaeePackage.EJB_LOCAL_REF__LOCAL:
				return LOCAL_EDEFAULT == null ? local != null : !LOCAL_EDEFAULT.equals(local);
			case JavaeePackage.EJB_LOCAL_REF__EJB_LINK:
				return EJB_LINK_EDEFAULT == null ? ejbLink != null : !EJB_LINK_EDEFAULT.equals(ejbLink);
			case JavaeePackage.EJB_LOCAL_REF__MAPPED_NAME:
				return MAPPED_NAME_EDEFAULT == null ? mappedName != null : !MAPPED_NAME_EDEFAULT.equals(mappedName);
			case JavaeePackage.EJB_LOCAL_REF__INJECTION_TARGETS:
				return injectionTargets != null && !injectionTargets.isEmpty();
			case JavaeePackage.EJB_LOCAL_REF__LOOKUP_NAME:
				return LOOKUP_NAME_EDEFAULT == null ? lookupName != null : !LOOKUP_NAME_EDEFAULT.equals(lookupName);
			case JavaeePackage.EJB_LOCAL_REF__ID:
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
		result.append(" (ejbRefName: "); //$NON-NLS-1$
		result.append(ejbRefName);
		result.append(", ejbRefType: "); //$NON-NLS-1$
		if (ejbRefTypeESet) result.append(ejbRefType); else result.append("<unset>"); //$NON-NLS-1$
		result.append(", localHome: "); //$NON-NLS-1$
		result.append(localHome);
		result.append(", local: "); //$NON-NLS-1$
		result.append(local);
		result.append(", ejbLink: "); //$NON-NLS-1$
		result.append(ejbLink);
		result.append(", mappedName: "); //$NON-NLS-1$
		result.append(mappedName);
		result.append(", lookupName: "); //$NON-NLS-1$
		result.append(lookupName);
		result.append(", id: "); //$NON-NLS-1$
		result.append(id);
		result.append(')');
		return result.toString();
	}

} //EjbLocalRefImpl