/*******************************************************************************
 * Copyright (c) 2001, 2006 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 *
 * Contributors:
 * IBM Corporation - initial API and implementation
 *******************************************************************************/
package org.eclipse.jst.j2ee.ejb.internal.impl;

import java.util.Collection;

import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Platform;
import org.eclipse.core.runtime.Status;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.util.EObjectContainmentEList;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.emf.ecore.util.InternalEList;
import org.eclipse.jst.j2ee.common.Description;
import org.eclipse.jst.j2ee.common.internal.impl.J2EEEObjectImpl;
import org.eclipse.jst.j2ee.core.internal.plugin.J2EECorePlugin;
import org.eclipse.jst.j2ee.ejb.ContainerManagedEntity;
import org.eclipse.jst.j2ee.ejb.EJBRelationshipRole;
import org.eclipse.jst.j2ee.ejb.EjbPackage;
import org.eclipse.jst.j2ee.ejb.EnterpriseBean;
import org.eclipse.jst.j2ee.ejb.RoleSource;
import org.eclipse.jst.j2ee.internal.MOFJ2EEResourceHandler;
import org.eclipse.jst.javaee.ejb.MessageDrivenBean;
import org.eclipse.jst.javaee.ejb.SessionBean;

/**
 * Designates the source of a role that participates in a relationship. A relationship-role-source element uniquely identifies an entity bean.
 * @invariant The content of each role-source element shall refer to an existing entity bean, entity bean reference.
 * @generated
 */
public class RoleSourceImpl extends J2EEEObjectImpl implements RoleSource {

	/**
	 * The default value of the '{@link #getDescription() <em>Description</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getDescription()
	 * @generated
	 * @ordered
	 */
	protected static final String DESCRIPTION_EDEFAULT = null;

	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	protected String description = DESCRIPTION_EDEFAULT;
	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	protected ContainerManagedEntity entityBean = null;
	/**
	 * The cached value of the '{@link #getDescriptions() <em>Descriptions</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getDescriptions()
	 * @generated
	 * @ordered
	 */
	protected EList descriptions = null;

	public RoleSourceImpl() {
		super();
	}
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return EjbPackage.Literals.ROLE_SOURCE;
	}

	/**
	 * @generated This field/method will be replaced during code generation 
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	public void setDescription(String newDescription) {
		String oldDescription = description;
		description = newDescription;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, EjbPackage.ROLE_SOURCE__DESCRIPTION, oldDescription, description));
	}

	/**
	 * @generated This field/method will be replaced during code generation 
	 */
	public EJBRelationshipRole getRole() {
		if (eContainerFeatureID != EjbPackage.ROLE_SOURCE__ROLE) return null;
		return (EJBRelationshipRole)eContainer();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetRole(EJBRelationshipRole newRole, NotificationChain msgs) {
		msgs = eBasicSetContainer((InternalEObject)newRole, EjbPackage.ROLE_SOURCE__ROLE, msgs);
		return msgs;
	}

	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	public void setRole(EJBRelationshipRole newRole) {
		if (newRole != eInternalContainer() || (eContainerFeatureID != EjbPackage.ROLE_SOURCE__ROLE && newRole != null)) {
			if (EcoreUtil.isAncestor(this, newRole))
				throw new IllegalArgumentException("Recursive containment not allowed for " + toString()); //$NON-NLS-1$
			NotificationChain msgs = null;
			if (eInternalContainer() != null)
				msgs = eBasicRemoveFromContainer(msgs);
			if (newRole != null)
				msgs = ((InternalEObject)newRole).eInverseAdd(this, EjbPackage.EJB_RELATIONSHIP_ROLE__SOURCE, EJBRelationshipRole.class, msgs);
			msgs = basicSetRole(newRole, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, EjbPackage.ROLE_SOURCE__ROLE, newRole, newRole));
	}

	/**
	 * @generated This field/method will be replaced during code generation 
	 */
	public ContainerManagedEntity getEntityBean() {
		if (entityBean != null && entityBean.eIsProxy()) {
			InternalEObject oldEntityBean = (InternalEObject)entityBean;
			entityBean = (ContainerManagedEntity)eResolveProxy(oldEntityBean);
			if (entityBean != oldEntityBean) {
				if (eNotificationRequired())
					eNotify(new ENotificationImpl(this, Notification.RESOLVE, EjbPackage.ROLE_SOURCE__ENTITY_BEAN, oldEntityBean, entityBean));
			}
		}
		return entityBean;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ContainerManagedEntity basicGetEntityBean() {
		return entityBean;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setEntityBean(ContainerManagedEntity newEntityBean) {
		ContainerManagedEntity oldEntityBean = entityBean;
		entityBean = newEntityBean;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, EjbPackage.ROLE_SOURCE__ENTITY_BEAN, oldEntityBean, entityBean));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList getDescriptions() {
		if (descriptions == null) {
			descriptions = new EObjectContainmentEList(Description.class, this, EjbPackage.ROLE_SOURCE__DESCRIPTIONS);
		}
		return descriptions;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseAdd(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case EjbPackage.ROLE_SOURCE__ROLE:
				if (eInternalContainer() != null)
					msgs = eBasicRemoveFromContainer(msgs);
				return basicSetRole((EJBRelationshipRole)otherEnd, msgs);
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
			case EjbPackage.ROLE_SOURCE__ROLE:
				return basicSetRole(null, msgs);
			case EjbPackage.ROLE_SOURCE__DESCRIPTIONS:
				return ((InternalEList)getDescriptions()).basicRemove(otherEnd, msgs);
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
			case EjbPackage.ROLE_SOURCE__ROLE:
				return eInternalContainer().eInverseRemove(this, EjbPackage.EJB_RELATIONSHIP_ROLE__SOURCE, EJBRelationshipRole.class, msgs);
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
			case EjbPackage.ROLE_SOURCE__DESCRIPTION:
				return getDescription();
			case EjbPackage.ROLE_SOURCE__ROLE:
				return getRole();
			case EjbPackage.ROLE_SOURCE__ENTITY_BEAN:
				if (resolve) return getEntityBean();
				return basicGetEntityBean();
			case EjbPackage.ROLE_SOURCE__DESCRIPTIONS:
				return getDescriptions();
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
			case EjbPackage.ROLE_SOURCE__DESCRIPTION:
				setDescription((String)newValue);
				return;
			case EjbPackage.ROLE_SOURCE__ROLE:
				setRole((EJBRelationshipRole)newValue);
				return;
			case EjbPackage.ROLE_SOURCE__ENTITY_BEAN:
				if(newValue instanceof ContainerManagedEntity) {
					setEntityBean((ContainerManagedEntity)newValue);
				} else {
					String relationshipName = this.getRole().getRelationship().getName();
					String beanName = ""; //$NON-NLS-1$
					if(relationshipName == null)
						relationshipName = ""; //$NON-NLS-1$
					if(newValue instanceof EnterpriseBean) {
						beanName = ((EnterpriseBean)newValue).getName();
					} else if (newValue instanceof SessionBean) {
						beanName = ((SessionBean)newValue).getEjbName();
					}else if(newValue instanceof MessageDrivenBean) {
						beanName = ((MessageDrivenBean)newValue).getEjbName();
					}
					if(beanName == null) {
						beanName = ""; //$NON-NLS-1$
					}
					Platform.getLog(Platform.getBundle(J2EECorePlugin.getPluginID())).log( new Status(IStatus.ERROR,J2EECorePlugin.getPluginID(), MOFJ2EEResourceHandler.getString(MOFJ2EEResourceHandler.INVALID_ROLE_SOURCE__ENTITY_BEAN, new Object[]{relationshipName, beanName})));
				}
				return;
			case EjbPackage.ROLE_SOURCE__DESCRIPTIONS:
				getDescriptions().clear();
				getDescriptions().addAll((Collection)newValue);
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
			case EjbPackage.ROLE_SOURCE__DESCRIPTION:
				setDescription(DESCRIPTION_EDEFAULT);
				return;
			case EjbPackage.ROLE_SOURCE__ROLE:
				setRole((EJBRelationshipRole)null);
				return;
			case EjbPackage.ROLE_SOURCE__ENTITY_BEAN:
				setEntityBean((ContainerManagedEntity)null);
				return;
			case EjbPackage.ROLE_SOURCE__DESCRIPTIONS:
				getDescriptions().clear();
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
			case EjbPackage.ROLE_SOURCE__DESCRIPTION:
				return DESCRIPTION_EDEFAULT == null ? description != null : !DESCRIPTION_EDEFAULT.equals(description);
			case EjbPackage.ROLE_SOURCE__ROLE:
				return getRole() != null;
			case EjbPackage.ROLE_SOURCE__ENTITY_BEAN:
				return entityBean != null;
			case EjbPackage.ROLE_SOURCE__DESCRIPTIONS:
				return descriptions != null && !descriptions.isEmpty();
		}
		return super.eIsSet(featureID);
	}

	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	@Override
	public String toString() {
		if (eIsProxy()) return super.toString();

		StringBuffer result = new StringBuffer(super.toString());
		result.append(" (description: "); //$NON-NLS-1$
		result.append(description);
		result.append(')');
		return result.toString();
	}

}





