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
package org.eclipse.jst.j2ee.ejb.impl;

import java.util.Collection;

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
import org.eclipse.jst.j2ee.common.Description;
import org.eclipse.jst.j2ee.ejb.EJBJar;
import org.eclipse.jst.j2ee.ejb.EJBRelation;
import org.eclipse.jst.j2ee.ejb.EjbPackage;
import org.eclipse.jst.j2ee.ejb.Relationships;


/**
 * The relationships describes the relationships in which entity beans with container managed persistence participate. The relationships element contains an optional description; and a list of ejb-relation elements, which specify the container managed relationships.
 */
public class RelationshipsImpl extends EObjectImpl implements Relationships, EObject{

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
	protected EClass eStaticClass() {
		return EjbPackage.eINSTANCE.getRelationships();
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
		return (EJBJar)eContainer;
	}

	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	public void setEjbJar(EJBJar newEjbJar) {
		if (newEjbJar != eContainer || (eContainerFeatureID != EjbPackage.RELATIONSHIPS__EJB_JAR && newEjbJar != null)) {
			if (EcoreUtil.isAncestor(this, newEjbJar))
				throw new IllegalArgumentException("Recursive containment not allowed for " + toString());
			NotificationChain msgs = null;
			if (eContainer != null)
				msgs = eBasicRemoveFromContainer(msgs);
			if (newEjbJar != null)
				msgs = ((InternalEObject)newEjbJar).eInverseAdd(this, EjbPackage.EJB_JAR__RELATIONSHIP_LIST, EJBJar.class, msgs);
			msgs = eBasicSetContainer((InternalEObject)newEjbJar, EjbPackage.RELATIONSHIPS__EJB_JAR, msgs);
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
	public NotificationChain eInverseAdd(InternalEObject otherEnd, int featureID, Class baseClass, NotificationChain msgs) {
		if (featureID >= 0) {
			switch (eDerivedStructuralFeatureID(featureID, baseClass)) {
				case EjbPackage.RELATIONSHIPS__EJB_JAR:
					if (eContainer != null)
						msgs = eBasicRemoveFromContainer(msgs);
					return eBasicSetContainer(otherEnd, EjbPackage.RELATIONSHIPS__EJB_JAR, msgs);
				case EjbPackage.RELATIONSHIPS__EJB_RELATIONS:
					return ((InternalEList)getEjbRelations()).basicAdd(otherEnd, msgs);
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
				case EjbPackage.RELATIONSHIPS__EJB_JAR:
					return eBasicSetContainer(null, EjbPackage.RELATIONSHIPS__EJB_JAR, msgs);
				case EjbPackage.RELATIONSHIPS__EJB_RELATIONS:
					return ((InternalEList)getEjbRelations()).basicRemove(otherEnd, msgs);
				case EjbPackage.RELATIONSHIPS__DESCRIPTIONS:
					return ((InternalEList)getDescriptions()).basicRemove(otherEnd, msgs);
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
				case EjbPackage.RELATIONSHIPS__EJB_JAR:
					return eContainer.eInverseRemove(this, EjbPackage.EJB_JAR__RELATIONSHIP_LIST, EJBJar.class, msgs);
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
			case EjbPackage.RELATIONSHIPS__DESCRIPTION:
				return getDescription();
			case EjbPackage.RELATIONSHIPS__EJB_JAR:
				return getEjbJar();
			case EjbPackage.RELATIONSHIPS__EJB_RELATIONS:
				return getEjbRelations();
			case EjbPackage.RELATIONSHIPS__DESCRIPTIONS:
				return getDescriptions();
		}
		return eDynamicGet(eFeature, resolve);
	}

	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	public boolean eIsSet(EStructuralFeature eFeature) {
		switch (eDerivedStructuralFeatureID(eFeature)) {
			case EjbPackage.RELATIONSHIPS__DESCRIPTION:
				return DESCRIPTION_EDEFAULT == null ? description != null : !DESCRIPTION_EDEFAULT.equals(description);
			case EjbPackage.RELATIONSHIPS__EJB_JAR:
				return getEjbJar() != null;
			case EjbPackage.RELATIONSHIPS__EJB_RELATIONS:
				return ejbRelations != null && !ejbRelations.isEmpty();
			case EjbPackage.RELATIONSHIPS__DESCRIPTIONS:
				return descriptions != null && !descriptions.isEmpty();
		}
		return eDynamicIsSet(eFeature);
	}

	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	public void eSet(EStructuralFeature eFeature, Object newValue) {
		switch (eDerivedStructuralFeatureID(eFeature)) {
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
		eDynamicSet(eFeature, newValue);
	}

	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	public void eUnset(EStructuralFeature eFeature) {
		switch (eDerivedStructuralFeatureID(eFeature)) {
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
		eDynamicUnset(eFeature);
	}

	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	public String toString() {
		if (eIsProxy()) return super.toString();

		StringBuffer result = new StringBuffer(super.toString());
		result.append(" (description: "); //$NON-NLS-1$
		result.append(description);
		result.append(')');
		return result.toString();
	}

}





