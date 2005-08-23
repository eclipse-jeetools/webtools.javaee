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
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.emf.ecore.util.InternalEList;
import org.eclipse.jst.j2ee.common.Description;
import org.eclipse.jst.j2ee.ejb.ContainerManagedEntity;
import org.eclipse.jst.j2ee.ejb.EjbPackage;
import org.eclipse.jst.j2ee.ejb.Query;
import org.eclipse.jst.j2ee.ejb.QueryMethod;
import org.eclipse.jst.j2ee.ejb.ReturnTypeMapping;

/**
 * The query element is used to specify a finder or select query. It contains an optional description of the query; the specification of the finder or select method it is used by; a specification of the return type mapping, if any, if the query is for a select method; and the EJB QL query string that defines the query. Queries that are expressible in EJB QL must use the ejb-ql element to specify the query. If a query
 * is not expressible in EJB QL, the description element should be used to describe the semantics of the query and the ejb-ql element should be empty.
 */
public class QueryImpl extends EObjectImpl implements Query, EObject{

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
	 * The default value of the '{@link #getEjbQL() <em>Ejb QL</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getEjbQL()
	 * @generated
	 * @ordered
	 */
	protected static final String EJB_QL_EDEFAULT = null;

	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	protected String ejbQL = EJB_QL_EDEFAULT;
	/**
	 * The default value of the '{@link #getReturnTypeMapping() <em>Return Type Mapping</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getReturnTypeMapping()
	 * @generated
	 * @ordered
	 */
	protected static final ReturnTypeMapping RETURN_TYPE_MAPPING_EDEFAULT = ReturnTypeMapping.LOCAL_LITERAL;

	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	protected ReturnTypeMapping returnTypeMapping = RETURN_TYPE_MAPPING_EDEFAULT;
	/**
	 * This is true if the Return Type Mapping attribute has been set.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	protected boolean returnTypeMappingESet = false;

	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	protected QueryMethod queryMethod = null;
	/**
	 * The cached value of the '{@link #getDescriptions() <em>Descriptions</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getDescriptions()
	 * @generated
	 * @ordered
	 */
	protected EList descriptions = null;

	public QueryImpl() {
		super();
	}
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected EClass eStaticClass() {
		return EjbPackage.eINSTANCE.getQuery();
	}

public boolean isLocalResultMapping() {
	// override bug in the model which made Remote the default
	return getReturnTypeMapping().getValue() == ReturnTypeMapping.LOCAL;
}
public boolean isRemoteResultMapping() {
	return getReturnTypeMapping().getValue() == ReturnTypeMapping.REMOTE;
}
	/**
	 * @generated This field/method will be replaced during code generation 
	 * The description is used by the ejb-jar file producer to provide text describing the query.
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
			eNotify(new ENotificationImpl(this, Notification.SET, EjbPackage.QUERY__DESCRIPTION, oldDescription, description));
	}

	/**
	 * @generated This field/method will be replaced during code generation 
	 * Contains the EJB QL query string that defines a finder or select query. This element is defined within the scope of a query element whose contents specify the finder or the select method that uses the query. The content must be a valid EJB QL query string for the entity bean for which the query is specified. The ejb-ql element must be specified for all queries that are expressible in EJB QL.
	 * 
	 * @invariant The value must be a valid EJB QL query string for the entity bean or dependent object class for which the query is specified.
	 * @invariant The ejb-ql element must be specified for all queries that are expressible in EJB QL.
	 * 
	 * Example:
	 * <query>
	 * <query-method>
	 * <method-name>ejbSelectPendingLineitems<//method-name>
	 * <method-params//>
	 * <//query-method>
	 * <ejb-ql>SELECT ENTITY(l)
	 * FROM LineItems l
	 * WHERE l.shipped is FALSE
	 * <//ejb-ql>
	 * <//query>
	 */
	public String getEjbQL() {
		return ejbQL;
	}

	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	public void setEjbQL(String newEjbQL) {
		String oldEjbQL = ejbQL;
		ejbQL = newEjbQL;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, EjbPackage.QUERY__EJB_QL, oldEjbQL, ejbQL));
	}

	/**
	 * @generated This field/method will be replaced during code generation 
	 * The return-type-mapping element is used in the query element to specify whether an abstract schema type that is returned by a query for a select method is to be mapped to an EJBLocalObject or EJBObject type.  The value of the return-type-mapping element, if specified must be one of the following.
	 */
	public ReturnTypeMapping getReturnTypeMapping() {
		return returnTypeMapping;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setReturnTypeMapping(ReturnTypeMapping newReturnTypeMapping) {
		ReturnTypeMapping oldReturnTypeMapping = returnTypeMapping;
		returnTypeMapping = newReturnTypeMapping == null ? RETURN_TYPE_MAPPING_EDEFAULT : newReturnTypeMapping;
		boolean oldReturnTypeMappingESet = returnTypeMappingESet;
		returnTypeMappingESet = true;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, EjbPackage.QUERY__RETURN_TYPE_MAPPING, oldReturnTypeMapping, returnTypeMapping, !oldReturnTypeMappingESet));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void unsetReturnTypeMapping() {
		ReturnTypeMapping oldReturnTypeMapping = returnTypeMapping;
		boolean oldReturnTypeMappingESet = returnTypeMappingESet;
		returnTypeMapping = RETURN_TYPE_MAPPING_EDEFAULT;
		returnTypeMappingESet = false;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.UNSET, EjbPackage.QUERY__RETURN_TYPE_MAPPING, oldReturnTypeMapping, RETURN_TYPE_MAPPING_EDEFAULT, oldReturnTypeMappingESet));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean isSetReturnTypeMapping() {
		return returnTypeMappingESet;
	}

	/**
	 * @generated This field/method will be replaced during code generation 
	 * The query-method element is used to specify the method (DB//SR//CM-"on the home") for a finder or
	 * select query.
	 * 
	 * The method-name element specifies the name of a finder or select
	 * method in the entity bean's implementation class or a select method in
	 * the dependent object class.
	 * 
	 * Each method-param must be defined for a query-method using the
	 * method-params element.
	 * 
	 * Used in: query
	 * 
	 * Example:
	 * 
	 * Example:
	 * <query>
	 *   <description>Method finds large orders<//description>
	 *   <query-method>
	 *     <method-name>findLargeOrders<//method-name>
	 *     <method-params><//method-params>
	 *   <//query-method>
	 *   <ejb-ql>SELECT ENTITY(o) FROM Order o WHERE o.amount &gt; 1000<//ejb-ql>
	 * <//query>
	 */
	public QueryMethod getQueryMethod() {
		return queryMethod;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetQueryMethod(QueryMethod newQueryMethod, NotificationChain msgs) {
		QueryMethod oldQueryMethod = queryMethod;
		queryMethod = newQueryMethod;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, EjbPackage.QUERY__QUERY_METHOD, oldQueryMethod, newQueryMethod);
			if (msgs == null) msgs = notification; else msgs.add(notification);
		}
		return msgs;
	}

	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	public void setQueryMethod(QueryMethod newQueryMethod) {
		if (newQueryMethod != queryMethod) {
			NotificationChain msgs = null;
			if (queryMethod != null)
				msgs = ((InternalEObject)queryMethod).eInverseRemove(this, EjbPackage.QUERY_METHOD__QUERY, QueryMethod.class, msgs);
			if (newQueryMethod != null)
				msgs = ((InternalEObject)newQueryMethod).eInverseAdd(this, EjbPackage.QUERY_METHOD__QUERY, QueryMethod.class, msgs);
			msgs = basicSetQueryMethod(newQueryMethod, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, EjbPackage.QUERY__QUERY_METHOD, newQueryMethod, newQueryMethod));
	}

	/**
	 * @generated This field/method will be replaced during code generation 
	 */
	public ContainerManagedEntity getEntity() {
		if (eContainerFeatureID != EjbPackage.QUERY__ENTITY) return null;
		return (ContainerManagedEntity)eContainer;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setEntity(ContainerManagedEntity newEntity) {
		if (newEntity != eContainer || (eContainerFeatureID != EjbPackage.QUERY__ENTITY && newEntity != null)) {
			if (EcoreUtil.isAncestor(this, newEntity))
				throw new IllegalArgumentException("Recursive containment not allowed for " + toString());
			NotificationChain msgs = null;
			if (eContainer != null)
				msgs = eBasicRemoveFromContainer(msgs);
			if (newEntity != null)
				msgs = ((InternalEObject)newEntity).eInverseAdd(this, EjbPackage.CONTAINER_MANAGED_ENTITY__QUERIES, ContainerManagedEntity.class, msgs);
			msgs = eBasicSetContainer((InternalEObject)newEntity, EjbPackage.QUERY__ENTITY, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, EjbPackage.QUERY__ENTITY, newEntity, newEntity));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList getDescriptions() {
		if (descriptions == null) {
			descriptions = new EObjectContainmentEList(Description.class, this, EjbPackage.QUERY__DESCRIPTIONS);
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
				case EjbPackage.QUERY__QUERY_METHOD:
					if (queryMethod != null)
						msgs = ((InternalEObject)queryMethod).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - EjbPackage.QUERY__QUERY_METHOD, null, msgs);
					return basicSetQueryMethod((QueryMethod)otherEnd, msgs);
				case EjbPackage.QUERY__ENTITY:
					if (eContainer != null)
						msgs = eBasicRemoveFromContainer(msgs);
					return eBasicSetContainer(otherEnd, EjbPackage.QUERY__ENTITY, msgs);
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
				case EjbPackage.QUERY__QUERY_METHOD:
					return basicSetQueryMethod(null, msgs);
				case EjbPackage.QUERY__ENTITY:
					return eBasicSetContainer(null, EjbPackage.QUERY__ENTITY, msgs);
				case EjbPackage.QUERY__DESCRIPTIONS:
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
				case EjbPackage.QUERY__ENTITY:
					return eContainer.eInverseRemove(this, EjbPackage.CONTAINER_MANAGED_ENTITY__QUERIES, ContainerManagedEntity.class, msgs);
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
			case EjbPackage.QUERY__DESCRIPTION:
				return getDescription();
			case EjbPackage.QUERY__EJB_QL:
				return getEjbQL();
			case EjbPackage.QUERY__RETURN_TYPE_MAPPING:
				return getReturnTypeMapping();
			case EjbPackage.QUERY__QUERY_METHOD:
				return getQueryMethod();
			case EjbPackage.QUERY__ENTITY:
				return getEntity();
			case EjbPackage.QUERY__DESCRIPTIONS:
				return getDescriptions();
		}
		return eDynamicGet(eFeature, resolve);
	}

	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	public boolean eIsSet(EStructuralFeature eFeature) {
		switch (eDerivedStructuralFeatureID(eFeature)) {
			case EjbPackage.QUERY__DESCRIPTION:
				return DESCRIPTION_EDEFAULT == null ? description != null : !DESCRIPTION_EDEFAULT.equals(description);
			case EjbPackage.QUERY__EJB_QL:
				return EJB_QL_EDEFAULT == null ? ejbQL != null : !EJB_QL_EDEFAULT.equals(ejbQL);
			case EjbPackage.QUERY__RETURN_TYPE_MAPPING:
				return isSetReturnTypeMapping();
			case EjbPackage.QUERY__QUERY_METHOD:
				return queryMethod != null;
			case EjbPackage.QUERY__ENTITY:
				return getEntity() != null;
			case EjbPackage.QUERY__DESCRIPTIONS:
				return descriptions != null && !descriptions.isEmpty();
		}
		return eDynamicIsSet(eFeature);
	}

	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	public void eSet(EStructuralFeature eFeature, Object newValue) {
		switch (eDerivedStructuralFeatureID(eFeature)) {
			case EjbPackage.QUERY__DESCRIPTION:
				setDescription((String)newValue);
				return;
			case EjbPackage.QUERY__EJB_QL:
				setEjbQL((String)newValue);
				return;
			case EjbPackage.QUERY__RETURN_TYPE_MAPPING:
				setReturnTypeMapping((ReturnTypeMapping)newValue);
				return;
			case EjbPackage.QUERY__QUERY_METHOD:
				setQueryMethod((QueryMethod)newValue);
				return;
			case EjbPackage.QUERY__ENTITY:
				setEntity((ContainerManagedEntity)newValue);
				return;
			case EjbPackage.QUERY__DESCRIPTIONS:
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
			case EjbPackage.QUERY__DESCRIPTION:
				setDescription(DESCRIPTION_EDEFAULT);
				return;
			case EjbPackage.QUERY__EJB_QL:
				setEjbQL(EJB_QL_EDEFAULT);
				return;
			case EjbPackage.QUERY__RETURN_TYPE_MAPPING:
				unsetReturnTypeMapping();
				return;
			case EjbPackage.QUERY__QUERY_METHOD:
				setQueryMethod((QueryMethod)null);
				return;
			case EjbPackage.QUERY__ENTITY:
				setEntity((ContainerManagedEntity)null);
				return;
			case EjbPackage.QUERY__DESCRIPTIONS:
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
		result.append(" (description: ");
		result.append(description);
		result.append(", ejbQL: ");
		result.append(ejbQL);
		result.append(", returnTypeMapping: ");
		if (returnTypeMappingESet) result.append(returnTypeMapping); else result.append("<unset>");
		result.append(')');
		return result.toString();
	}

}





