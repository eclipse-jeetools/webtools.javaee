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
import java.util.List;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.util.EObjectContainmentEList;
import org.eclipse.emf.ecore.util.EObjectContainmentWithInverseEList;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.emf.ecore.util.InternalEList;
import org.eclipse.jst.j2ee.common.Description;
import org.eclipse.jst.j2ee.common.internal.impl.J2EEEObjectImpl;
import org.eclipse.jst.j2ee.ejb.CommonRelationshipRole;
import org.eclipse.jst.j2ee.ejb.EJBRelation;
import org.eclipse.jst.j2ee.ejb.EJBRelationshipRole;
import org.eclipse.jst.j2ee.ejb.EjbPackage;
import org.eclipse.jst.j2ee.ejb.Relationships;

/**
 * The ejb-relation element describes a relationship between two entity beans with container managed persistence. An ejb-relation element contains a description; an optional ejb-relation-name element; and exactly two relationship role declarations, defined by the ejb-relationship-roles. The name of the relationship, if specified, is unique within the ejb-jar file.
 * 
 * @invariant The name of the relationship, if specified, is unique within the ejb-jar file.
 * @invariant self.relationshipRoles.size == 2
 * @invariant self.relationshipRoles[0].name != self.relationshipRoles[1].name
 * 
 * @migration EJB1.1 Moved from ejbext::EjbRelationshipRole contained under ejbext::EJBJarExtension
 * @migration EJB1.1 added optional attribute, description:String
 * @migration EJB1.1 added optional attribute, name:String (May have been inherited from EObject previously)
 * @generated
 */
public class EJBRelationImpl extends J2EEEObjectImpl implements EJBRelation {

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
	 * The default value of the '{@link #getName() <em>Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getName()
	 * @generated
	 * @ordered
	 */
	protected static final String NAME_EDEFAULT = null;

	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	protected String name = NAME_EDEFAULT;
	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	protected EList relationshipRoles = null;
	/**
	 * The cached value of the '{@link #getDescriptions() <em>Descriptions</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getDescriptions()
	 * @generated
	 * @ordered
	 */
	protected EList descriptions = null;

	public EJBRelationImpl() {
		super();
	}
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return EjbPackage.Literals.EJB_RELATION;
	}

public boolean containsRelationshipRole(EJBRelationshipRole aRole) {
	return getRelationshipRoles().contains(aRole);
}
/**
 * Return the roles.
 */
public EList getCommonRoles() {
	return getRelationshipRoles();
}
/**
 * Return the first role.
 */
public CommonRelationshipRole getFirstCommonRole() {
	return getFirstRole();
}
/**
 * Return the first role.
 */
public EJBRelationshipRole getFirstRole() {
	List list = getRelationshipRoles();
	if (list.size() == 0)
		return null;
	return (EJBRelationshipRole) list.get(0);
}
/**
 * Return the other role.
 */
public EJBRelationshipRole getOppositeRole(EJBRelationshipRole aRole) {
	if (aRole != null) {
		if (aRole == getFirstRole())
			return getSecondRole();
		if (aRole == getSecondRole())
			return getFirstRole();
	}
	return null;
}
/**
 * Return the first role.
 */
public CommonRelationshipRole getSecondCommonRole() {
	return getSecondRole();
}
/**
 * Return the second role.
 */
public EJBRelationshipRole getSecondRole() {
	List list = getRelationshipRoles();
	if (list.size() < 2)
		return null;
	return (EJBRelationshipRole) list.get(1);
}
/**
 * Set the forward role.  Implemented by ensuring that the passed role is the first role 
 * which is the "tiebreaker" in case of unclear relationships, such as 1:1.
 */
public void setFoward(EJBRelationshipRole aRole) {
	
	EList roles = getRelationshipRoles();
	int currentIndex = roles.indexOf(aRole);
	if (currentIndex != 0) {
		if (currentIndex > -1)
			roles.move(0, aRole);
		else
			roles.set(0, aRole);
	}
}
	/**
	 * @generated This field/method will be replaced during code generation 
	 * The description is used by the ejb-jar file producer to provide text describing the ejb relation.
	 * 
	 * The description should include any information that the ejb-jar file producer wants to provide to the consumer of the ejb-jar file (i.e. to the Deployer). Typically, the tools used by the ejb-jar file consumer will display the description when processing the list of dependents.
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
			eNotify(new ENotificationImpl(this, Notification.SET, EjbPackage.EJB_RELATION__DESCRIPTION, oldDescription, description));
	}

	/**
	 * @generated This field/method will be replaced during code generation 
	 * The name of the relationship. If specified, must be unique within the ejb-jar file.
	 * @invariant self.name unique within Set<collect(ejbJar.ejbRelations.name)>
	 */
	public String getName() {
		return name;
	}

	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	public void setName(String newName) {
		String oldName = name;
		name = newName;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, EjbPackage.EJB_RELATION__NAME, oldName, name));
	}

	/**
	 * @generated This field/method will be replaced during code generation 
	 */
	public Relationships getRelationshipList() {
		if (eContainerFeatureID != EjbPackage.EJB_RELATION__RELATIONSHIP_LIST) return null;
		return (Relationships)eContainer();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetRelationshipList(Relationships newRelationshipList, NotificationChain msgs) {
		msgs = eBasicSetContainer((InternalEObject)newRelationshipList, EjbPackage.EJB_RELATION__RELATIONSHIP_LIST, msgs);
		return msgs;
	}

	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	public void setRelationshipList(Relationships newRelationshipList) {
		if (newRelationshipList != eInternalContainer() || (eContainerFeatureID != EjbPackage.EJB_RELATION__RELATIONSHIP_LIST && newRelationshipList != null)) {
			if (EcoreUtil.isAncestor(this, newRelationshipList))
				throw new IllegalArgumentException("Recursive containment not allowed for " + toString()); //$NON-NLS-1$
			NotificationChain msgs = null;
			if (eInternalContainer() != null)
				msgs = eBasicRemoveFromContainer(msgs);
			if (newRelationshipList != null)
				msgs = ((InternalEObject)newRelationshipList).eInverseAdd(this, EjbPackage.RELATIONSHIPS__EJB_RELATIONS, Relationships.class, msgs);
			msgs = basicSetRelationshipList(newRelationshipList, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, EjbPackage.EJB_RELATION__RELATIONSHIP_LIST, newRelationshipList, newRelationshipList));
	}

	/**
	 * @generated This field/method will be replaced during code generation 
	 * A collection containing exactly two relationship roles.
	 * 
	 * @migration EJB1.1 Containment used to be by reference on relation--now by value.

	 */
	public EList getRelationshipRoles() {
		if (relationshipRoles == null) {
			relationshipRoles = new EObjectContainmentWithInverseEList(EJBRelationshipRole.class, this, EjbPackage.EJB_RELATION__RELATIONSHIP_ROLES, EjbPackage.EJB_RELATIONSHIP_ROLE__RELATIONSHIP);
		}
		return relationshipRoles;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList getDescriptions() {
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
	@Override
	public NotificationChain eInverseAdd(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case EjbPackage.EJB_RELATION__RELATIONSHIP_LIST:
				if (eInternalContainer() != null)
					msgs = eBasicRemoveFromContainer(msgs);
				return basicSetRelationshipList((Relationships)otherEnd, msgs);
			case EjbPackage.EJB_RELATION__RELATIONSHIP_ROLES:
				return ((InternalEList)getRelationshipRoles()).basicAdd(otherEnd, msgs);
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
			case EjbPackage.EJB_RELATION__RELATIONSHIP_LIST:
				return basicSetRelationshipList(null, msgs);
			case EjbPackage.EJB_RELATION__RELATIONSHIP_ROLES:
				return ((InternalEList)getRelationshipRoles()).basicRemove(otherEnd, msgs);
			case EjbPackage.EJB_RELATION__DESCRIPTIONS:
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
			case EjbPackage.EJB_RELATION__RELATIONSHIP_LIST:
				return eInternalContainer().eInverseRemove(this, EjbPackage.RELATIONSHIPS__EJB_RELATIONS, Relationships.class, msgs);
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
			case EjbPackage.EJB_RELATION__DESCRIPTION:
				return getDescription();
			case EjbPackage.EJB_RELATION__NAME:
				return getName();
			case EjbPackage.EJB_RELATION__RELATIONSHIP_LIST:
				return getRelationshipList();
			case EjbPackage.EJB_RELATION__RELATIONSHIP_ROLES:
				return getRelationshipRoles();
			case EjbPackage.EJB_RELATION__DESCRIPTIONS:
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
			case EjbPackage.EJB_RELATION__DESCRIPTION:
				setDescription((String)newValue);
				return;
			case EjbPackage.EJB_RELATION__NAME:
				setName((String)newValue);
				return;
			case EjbPackage.EJB_RELATION__RELATIONSHIP_LIST:
				setRelationshipList((Relationships)newValue);
				return;
			case EjbPackage.EJB_RELATION__RELATIONSHIP_ROLES:
				getRelationshipRoles().clear();
				getRelationshipRoles().addAll((Collection)newValue);
				return;
			case EjbPackage.EJB_RELATION__DESCRIPTIONS:
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
			case EjbPackage.EJB_RELATION__DESCRIPTION:
				setDescription(DESCRIPTION_EDEFAULT);
				return;
			case EjbPackage.EJB_RELATION__NAME:
				setName(NAME_EDEFAULT);
				return;
			case EjbPackage.EJB_RELATION__RELATIONSHIP_LIST:
				setRelationshipList((Relationships)null);
				return;
			case EjbPackage.EJB_RELATION__RELATIONSHIP_ROLES:
				getRelationshipRoles().clear();
				return;
			case EjbPackage.EJB_RELATION__DESCRIPTIONS:
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
			case EjbPackage.EJB_RELATION__DESCRIPTION:
				return DESCRIPTION_EDEFAULT == null ? description != null : !DESCRIPTION_EDEFAULT.equals(description);
			case EjbPackage.EJB_RELATION__NAME:
				return NAME_EDEFAULT == null ? name != null : !NAME_EDEFAULT.equals(name);
			case EjbPackage.EJB_RELATION__RELATIONSHIP_LIST:
				return getRelationshipList() != null;
			case EjbPackage.EJB_RELATION__RELATIONSHIP_ROLES:
				return relationshipRoles != null && !relationshipRoles.isEmpty();
			case EjbPackage.EJB_RELATION__DESCRIPTIONS:
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
		result.append(", name: "); //$NON-NLS-1$
		result.append(name);
		result.append(')');
		return result.toString();
	}

	public EJBRelationshipRole getRelationshipRole(String aRoleName) {
		if (aRoleName == null) return null;
		List roles = getRelationshipRoles();
		EJBRelationshipRole role = null;
		for (int i = 0; i < roles.size(); i++) {
			role = (EJBRelationshipRole) roles.get(i);
			if (aRoleName.equals(role.getName()))
				return role;
		}
		return null;
	}		
}






