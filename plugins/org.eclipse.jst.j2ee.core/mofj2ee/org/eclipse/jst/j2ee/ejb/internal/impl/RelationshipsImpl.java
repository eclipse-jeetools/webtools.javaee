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
import org.eclipse.jst.j2ee.ejb.EJBJar;
import org.eclipse.jst.j2ee.ejb.EJBRelation;
import org.eclipse.jst.j2ee.ejb.EjbPackage;
import org.eclipse.jst.j2ee.ejb.Relationships;

/**
 * The relationships describes the relationships in which entity beans with container managed persistence participate. The relationships element contains an optional description; and a list of ejb-relation elements, which specify the container managed relationships.
 * @generated
 */
public class RelationshipsImpl extends J2EEEObjectImpl implements Relationships {

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
	protected EList ejbRelations = null;
	/**
	 * The cached value of the '{@link #getDescriptions() <em>Descriptions</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getDescriptions()
	 * @generated
	 * @ordered
	 */
	protected EList descriptions = null;

	public RelationshipsImpl() {
		super();
	}
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return EjbPackage.Literals.RELATIONSHIPS;
	}

	/**
	 * @generated This field/method will be replaced during code generation 
	 * The description is used by the ejb-jar file producer to provide text describing the collection of relationships.
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
			eNotify(new ENotificationImpl(this, Notification.SET, EjbPackage.RELATIONSHIPS__DESCRIPTION, oldDescription, description));
	}

	/**
	 * @generated This field/method will be replaced during code generation 
	 */
	public EJBJar getEjbJar() {
		if (eContainerFeatureID != EjbPackage.RELATIONSHIPS__EJB_JAR) return null;
		return (EJBJar)eContainer();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetEjbJar(EJBJar newEjbJar, NotificationChain msgs) {
		msgs = eBasicSetContainer((InternalEObject)newEjbJar, EjbPackage.RELATIONSHIPS__EJB_JAR, msgs);
		return msgs;
	}

	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	public void setEjbJar(EJBJar newEjbJar) {
		if (newEjbJar != eInternalContainer() || (eContainerFeatureID != EjbPackage.RELATIONSHIPS__EJB_JAR && newEjbJar != null)) {
			if (EcoreUtil.isAncestor(this, newEjbJar))
				throw new IllegalArgumentException("Recursive containment not allowed for " + toString()); //$NON-NLS-1$
			NotificationChain msgs = null;
			if (eInternalContainer() != null)
				msgs = eBasicRemoveFromContainer(msgs);
			if (newEjbJar != null)
				msgs = ((InternalEObject)newEjbJar).eInverseAdd(this, EjbPackage.EJB_JAR__RELATIONSHIP_LIST, EJBJar.class, msgs);
			msgs = basicSetEjbJar(newEjbJar, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, EjbPackage.RELATIONSHIPS__EJB_JAR, newEjbJar, newEjbJar));
	}

	/**
	 * @generated This field/method will be replaced during code generation 
	 * A list of ejb-relation elements, which specify the container managed
	 * relationships.
	 */
	public EList getEjbRelations() {
		if (ejbRelations == null) {
			ejbRelations = new EObjectContainmentWithInverseEList(EJBRelation.class, this, EjbPackage.RELATIONSHIPS__EJB_RELATIONS, EjbPackage.EJB_RELATION__RELATIONSHIP_LIST);
		}
		return ejbRelations;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList getDescriptions() {
		if (descriptions == null) {
			descriptions = new EObjectContainmentEList(Description.class, this, EjbPackage.RELATIONSHIPS__DESCRIPTIONS);
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
			case EjbPackage.RELATIONSHIPS__EJB_JAR:
				if (eInternalContainer() != null)
					msgs = eBasicRemoveFromContainer(msgs);
				return basicSetEjbJar((EJBJar)otherEnd, msgs);
			case EjbPackage.RELATIONSHIPS__EJB_RELATIONS:
				return ((InternalEList)getEjbRelations()).basicAdd(otherEnd, msgs);
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
			case EjbPackage.RELATIONSHIPS__EJB_JAR:
				return basicSetEjbJar(null, msgs);
			case EjbPackage.RELATIONSHIPS__EJB_RELATIONS:
				return ((InternalEList)getEjbRelations()).basicRemove(otherEnd, msgs);
			case EjbPackage.RELATIONSHIPS__DESCRIPTIONS:
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
			case EjbPackage.RELATIONSHIPS__EJB_JAR:
				return eInternalContainer().eInverseRemove(this, EjbPackage.EJB_JAR__RELATIONSHIP_LIST, EJBJar.class, msgs);
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
			case EjbPackage.RELATIONSHIPS__DESCRIPTION:
				return getDescription();
			case EjbPackage.RELATIONSHIPS__EJB_JAR:
				return getEjbJar();
			case EjbPackage.RELATIONSHIPS__EJB_RELATIONS:
				return getEjbRelations();
			case EjbPackage.RELATIONSHIPS__DESCRIPTIONS:
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
			case EjbPackage.RELATIONSHIPS__DESCRIPTION:
				setDescription((String)newValue);
				return;
			case EjbPackage.RELATIONSHIPS__EJB_JAR:
				setEjbJar((EJBJar)newValue);
				return;
			case EjbPackage.RELATIONSHIPS__EJB_RELATIONS:
				getEjbRelations().clear();
				getEjbRelations().addAll((Collection)newValue);
				return;
			case EjbPackage.RELATIONSHIPS__DESCRIPTIONS:
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
			case EjbPackage.RELATIONSHIPS__DESCRIPTION:
				setDescription(DESCRIPTION_EDEFAULT);
				return;
			case EjbPackage.RELATIONSHIPS__EJB_JAR:
				setEjbJar((EJBJar)null);
				return;
			case EjbPackage.RELATIONSHIPS__EJB_RELATIONS:
				getEjbRelations().clear();
				return;
			case EjbPackage.RELATIONSHIPS__DESCRIPTIONS:
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
			case EjbPackage.RELATIONSHIPS__DESCRIPTION:
				return DESCRIPTION_EDEFAULT == null ? description != null : !DESCRIPTION_EDEFAULT.equals(description);
			case EjbPackage.RELATIONSHIPS__EJB_JAR:
				return getEjbJar() != null;
			case EjbPackage.RELATIONSHIPS__EJB_RELATIONS:
				return ejbRelations != null && !ejbRelations.isEmpty();
			case EjbPackage.RELATIONSHIPS__DESCRIPTIONS:
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





