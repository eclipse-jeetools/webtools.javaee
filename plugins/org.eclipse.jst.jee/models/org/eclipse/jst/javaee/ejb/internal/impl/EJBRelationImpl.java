/**
 * <copyright>
 * </copyright>
 *
 * $Id: EJBRelationImpl.java,v 1.1 2007/03/20 18:04:37 jsholl Exp $
 */
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

import org.eclipse.jst.javaee.core.Description;

import org.eclipse.jst.javaee.ejb.EJBRelation;
import org.eclipse.jst.javaee.ejb.EJBRelationshipRole;

import org.eclipse.jst.javaee.ejb.internal.metadata.EjbPackage;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>EJB Relation</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.eclipse.jst.javaee.ejb.internal.impl.EJBRelationImpl#getDescriptions <em>Descriptions</em>}</li>
 *   <li>{@link org.eclipse.jst.javaee.ejb.internal.impl.EJBRelationImpl#getEjbRelationName <em>Ejb Relation Name</em>}</li>
 *   <li>{@link org.eclipse.jst.javaee.ejb.internal.impl.EJBRelationImpl#getEjbRelationshipRole <em>Ejb Relationship Role</em>}</li>
 *   <li>{@link org.eclipse.jst.javaee.ejb.internal.impl.EJBRelationImpl#getEjbRelationshipRole1 <em>Ejb Relationship Role1</em>}</li>
 *   <li>{@link org.eclipse.jst.javaee.ejb.internal.impl.EJBRelationImpl#getId <em>Id</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class EJBRelationImpl extends EObjectImpl implements EJBRelation {
	/**
	 * The cached value of the '{@link #getDescriptions() <em>Descriptions</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getDescriptions()
	 * @generated
	 * @ordered
	 */
	protected EList descriptions = null;

	/**
	 * The default value of the '{@link #getEjbRelationName() <em>Ejb Relation Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getEjbRelationName()
	 * @generated
	 * @ordered
	 */
	protected static final String EJB_RELATION_NAME_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getEjbRelationName() <em>Ejb Relation Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getEjbRelationName()
	 * @generated
	 * @ordered
	 */
	protected String ejbRelationName = EJB_RELATION_NAME_EDEFAULT;

	/**
	 * The cached value of the '{@link #getEjbRelationshipRole() <em>Ejb Relationship Role</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getEjbRelationshipRole()
	 * @generated
	 * @ordered
	 */
	protected EJBRelationshipRole ejbRelationshipRole = null;

	/**
	 * The cached value of the '{@link #getEjbRelationshipRole1() <em>Ejb Relationship Role1</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getEjbRelationshipRole1()
	 * @generated
	 * @ordered
	 */
	protected EJBRelationshipRole ejbRelationshipRole1 = null;

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
	protected EJBRelationImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected EClass eStaticClass() {
		return EjbPackage.Literals.EJB_RELATION;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public List getDescriptions() {
		if (descriptions == null) {
			descriptions = new EObjectContainmentEList(Description.class, this, EjbPackage.EJB_RELATION__DESCRIPTIONS);
		}
		return descriptions;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getEjbRelationName() {
		return ejbRelationName;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setEjbRelationName(String newEjbRelationName) {
		String oldEjbRelationName = ejbRelationName;
		ejbRelationName = newEjbRelationName;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, EjbPackage.EJB_RELATION__EJB_RELATION_NAME, oldEjbRelationName, ejbRelationName));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EJBRelationshipRole getEjbRelationshipRole() {
		return ejbRelationshipRole;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetEjbRelationshipRole(EJBRelationshipRole newEjbRelationshipRole, NotificationChain msgs) {
		EJBRelationshipRole oldEjbRelationshipRole = ejbRelationshipRole;
		ejbRelationshipRole = newEjbRelationshipRole;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, EjbPackage.EJB_RELATION__EJB_RELATIONSHIP_ROLE, oldEjbRelationshipRole, newEjbRelationshipRole);
			if (msgs == null) msgs = notification; else msgs.add(notification);
		}
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setEjbRelationshipRole(EJBRelationshipRole newEjbRelationshipRole) {
		if (newEjbRelationshipRole != ejbRelationshipRole) {
			NotificationChain msgs = null;
			if (ejbRelationshipRole != null)
				msgs = ((InternalEObject)ejbRelationshipRole).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - EjbPackage.EJB_RELATION__EJB_RELATIONSHIP_ROLE, null, msgs);
			if (newEjbRelationshipRole != null)
				msgs = ((InternalEObject)newEjbRelationshipRole).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - EjbPackage.EJB_RELATION__EJB_RELATIONSHIP_ROLE, null, msgs);
			msgs = basicSetEjbRelationshipRole(newEjbRelationshipRole, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, EjbPackage.EJB_RELATION__EJB_RELATIONSHIP_ROLE, newEjbRelationshipRole, newEjbRelationshipRole));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EJBRelationshipRole getEjbRelationshipRole1() {
		return ejbRelationshipRole1;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetEjbRelationshipRole1(EJBRelationshipRole newEjbRelationshipRole1, NotificationChain msgs) {
		EJBRelationshipRole oldEjbRelationshipRole1 = ejbRelationshipRole1;
		ejbRelationshipRole1 = newEjbRelationshipRole1;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, EjbPackage.EJB_RELATION__EJB_RELATIONSHIP_ROLE1, oldEjbRelationshipRole1, newEjbRelationshipRole1);
			if (msgs == null) msgs = notification; else msgs.add(notification);
		}
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setEjbRelationshipRole1(EJBRelationshipRole newEjbRelationshipRole1) {
		if (newEjbRelationshipRole1 != ejbRelationshipRole1) {
			NotificationChain msgs = null;
			if (ejbRelationshipRole1 != null)
				msgs = ((InternalEObject)ejbRelationshipRole1).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - EjbPackage.EJB_RELATION__EJB_RELATIONSHIP_ROLE1, null, msgs);
			if (newEjbRelationshipRole1 != null)
				msgs = ((InternalEObject)newEjbRelationshipRole1).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - EjbPackage.EJB_RELATION__EJB_RELATIONSHIP_ROLE1, null, msgs);
			msgs = basicSetEjbRelationshipRole1(newEjbRelationshipRole1, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, EjbPackage.EJB_RELATION__EJB_RELATIONSHIP_ROLE1, newEjbRelationshipRole1, newEjbRelationshipRole1));
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
			eNotify(new ENotificationImpl(this, Notification.SET, EjbPackage.EJB_RELATION__ID, oldId, id));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case EjbPackage.EJB_RELATION__DESCRIPTIONS:
				return ((InternalEList)getDescriptions()).basicRemove(otherEnd, msgs);
			case EjbPackage.EJB_RELATION__EJB_RELATIONSHIP_ROLE:
				return basicSetEjbRelationshipRole(null, msgs);
			case EjbPackage.EJB_RELATION__EJB_RELATIONSHIP_ROLE1:
				return basicSetEjbRelationshipRole1(null, msgs);
		}
		return super.eInverseRemove(otherEnd, featureID, msgs);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case EjbPackage.EJB_RELATION__DESCRIPTIONS:
				return getDescriptions();
			case EjbPackage.EJB_RELATION__EJB_RELATION_NAME:
				return getEjbRelationName();
			case EjbPackage.EJB_RELATION__EJB_RELATIONSHIP_ROLE:
				return getEjbRelationshipRole();
			case EjbPackage.EJB_RELATION__EJB_RELATIONSHIP_ROLE1:
				return getEjbRelationshipRole1();
			case EjbPackage.EJB_RELATION__ID:
				return getId();
		}
		return super.eGet(featureID, resolve, coreType);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void eSet(int featureID, Object newValue) {
		switch (featureID) {
			case EjbPackage.EJB_RELATION__DESCRIPTIONS:
				getDescriptions().clear();
				getDescriptions().addAll((Collection)newValue);
				return;
			case EjbPackage.EJB_RELATION__EJB_RELATION_NAME:
				setEjbRelationName((String)newValue);
				return;
			case EjbPackage.EJB_RELATION__EJB_RELATIONSHIP_ROLE:
				setEjbRelationshipRole((EJBRelationshipRole)newValue);
				return;
			case EjbPackage.EJB_RELATION__EJB_RELATIONSHIP_ROLE1:
				setEjbRelationshipRole1((EJBRelationshipRole)newValue);
				return;
			case EjbPackage.EJB_RELATION__ID:
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
	public void eUnset(int featureID) {
		switch (featureID) {
			case EjbPackage.EJB_RELATION__DESCRIPTIONS:
				getDescriptions().clear();
				return;
			case EjbPackage.EJB_RELATION__EJB_RELATION_NAME:
				setEjbRelationName(EJB_RELATION_NAME_EDEFAULT);
				return;
			case EjbPackage.EJB_RELATION__EJB_RELATIONSHIP_ROLE:
				setEjbRelationshipRole((EJBRelationshipRole)null);
				return;
			case EjbPackage.EJB_RELATION__EJB_RELATIONSHIP_ROLE1:
				setEjbRelationshipRole1((EJBRelationshipRole)null);
				return;
			case EjbPackage.EJB_RELATION__ID:
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
	public boolean eIsSet(int featureID) {
		switch (featureID) {
			case EjbPackage.EJB_RELATION__DESCRIPTIONS:
				return descriptions != null && !descriptions.isEmpty();
			case EjbPackage.EJB_RELATION__EJB_RELATION_NAME:
				return EJB_RELATION_NAME_EDEFAULT == null ? ejbRelationName != null : !EJB_RELATION_NAME_EDEFAULT.equals(ejbRelationName);
			case EjbPackage.EJB_RELATION__EJB_RELATIONSHIP_ROLE:
				return ejbRelationshipRole != null;
			case EjbPackage.EJB_RELATION__EJB_RELATIONSHIP_ROLE1:
				return ejbRelationshipRole1 != null;
			case EjbPackage.EJB_RELATION__ID:
				return ID_EDEFAULT == null ? id != null : !ID_EDEFAULT.equals(id);
		}
		return super.eIsSet(featureID);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String toString() {
		if (eIsProxy()) return super.toString();

		StringBuffer result = new StringBuffer(super.toString());
		result.append(" (ejbRelationName: "); //$NON-NLS-1$
		result.append(ejbRelationName);
		result.append(", id: "); //$NON-NLS-1$
		result.append(id);
		result.append(')');
		return result.toString();
	}

} //EJBRelationImpl