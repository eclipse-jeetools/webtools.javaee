/*******************************************************************************
 * Copyright (c) 2007 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
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

import org.eclipse.jst.javaee.core.Description;
import org.eclipse.jst.javaee.core.EjbLocalRef;
import org.eclipse.jst.javaee.core.EjbRef;
import org.eclipse.jst.javaee.core.EnvEntry;
import org.eclipse.jst.javaee.core.LifecycleCallback;
import org.eclipse.jst.javaee.core.MessageDestinationRef;
import org.eclipse.jst.javaee.core.PersistenceContextRef;
import org.eclipse.jst.javaee.core.PersistenceUnitRef;
import org.eclipse.jst.javaee.core.ResourceEnvRef;
import org.eclipse.jst.javaee.core.ResourceRef;
import org.eclipse.jst.javaee.core.ServiceRef;

import org.eclipse.jst.javaee.ejb.AroundInvokeType;
import org.eclipse.jst.javaee.ejb.InterceptorType;

import org.eclipse.jst.javaee.ejb.internal.metadata.EjbPackage;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Interceptor Type</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.eclipse.jst.javaee.ejb.internal.impl.InterceptorTypeImpl#getDescriptions <em>Descriptions</em>}</li>
 *   <li>{@link org.eclipse.jst.javaee.ejb.internal.impl.InterceptorTypeImpl#getInterceptorClass <em>Interceptor Class</em>}</li>
 *   <li>{@link org.eclipse.jst.javaee.ejb.internal.impl.InterceptorTypeImpl#getAroundInvokes <em>Around Invokes</em>}</li>
 *   <li>{@link org.eclipse.jst.javaee.ejb.internal.impl.InterceptorTypeImpl#getEnvEntries <em>Env Entries</em>}</li>
 *   <li>{@link org.eclipse.jst.javaee.ejb.internal.impl.InterceptorTypeImpl#getEjbRefs <em>Ejb Refs</em>}</li>
 *   <li>{@link org.eclipse.jst.javaee.ejb.internal.impl.InterceptorTypeImpl#getEjbLocalRefs <em>Ejb Local Refs</em>}</li>
 *   <li>{@link org.eclipse.jst.javaee.ejb.internal.impl.InterceptorTypeImpl#getServiceRefs <em>Service Refs</em>}</li>
 *   <li>{@link org.eclipse.jst.javaee.ejb.internal.impl.InterceptorTypeImpl#getResourceRefs <em>Resource Refs</em>}</li>
 *   <li>{@link org.eclipse.jst.javaee.ejb.internal.impl.InterceptorTypeImpl#getResourceEnvRefs <em>Resource Env Refs</em>}</li>
 *   <li>{@link org.eclipse.jst.javaee.ejb.internal.impl.InterceptorTypeImpl#getMessageDestinationRefs <em>Message Destination Refs</em>}</li>
 *   <li>{@link org.eclipse.jst.javaee.ejb.internal.impl.InterceptorTypeImpl#getPersistenceContextRefs <em>Persistence Context Refs</em>}</li>
 *   <li>{@link org.eclipse.jst.javaee.ejb.internal.impl.InterceptorTypeImpl#getPersistenceUnitRefs <em>Persistence Unit Refs</em>}</li>
 *   <li>{@link org.eclipse.jst.javaee.ejb.internal.impl.InterceptorTypeImpl#getPostConstructs <em>Post Constructs</em>}</li>
 *   <li>{@link org.eclipse.jst.javaee.ejb.internal.impl.InterceptorTypeImpl#getPreDestroys <em>Pre Destroys</em>}</li>
 *   <li>{@link org.eclipse.jst.javaee.ejb.internal.impl.InterceptorTypeImpl#getPostActivates <em>Post Activates</em>}</li>
 *   <li>{@link org.eclipse.jst.javaee.ejb.internal.impl.InterceptorTypeImpl#getPrePassivates <em>Pre Passivates</em>}</li>
 *   <li>{@link org.eclipse.jst.javaee.ejb.internal.impl.InterceptorTypeImpl#getId <em>Id</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class InterceptorTypeImpl extends EObjectImpl implements InterceptorType {
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
	 * The default value of the '{@link #getInterceptorClass() <em>Interceptor Class</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getInterceptorClass()
	 * @generated
	 * @ordered
	 */
	protected static final String INTERCEPTOR_CLASS_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getInterceptorClass() <em>Interceptor Class</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getInterceptorClass()
	 * @generated
	 * @ordered
	 */
	protected String interceptorClass = INTERCEPTOR_CLASS_EDEFAULT;

	/**
	 * The cached value of the '{@link #getAroundInvokes() <em>Around Invokes</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getAroundInvokes()
	 * @generated
	 * @ordered
	 */
	protected EList aroundInvokes = null;

	/**
	 * The cached value of the '{@link #getEnvEntries() <em>Env Entries</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getEnvEntries()
	 * @generated
	 * @ordered
	 */
	protected EList envEntries = null;

	/**
	 * The cached value of the '{@link #getEjbRefs() <em>Ejb Refs</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getEjbRefs()
	 * @generated
	 * @ordered
	 */
	protected EList ejbRefs = null;

	/**
	 * The cached value of the '{@link #getEjbLocalRefs() <em>Ejb Local Refs</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getEjbLocalRefs()
	 * @generated
	 * @ordered
	 */
	protected EList ejbLocalRefs = null;

	/**
	 * The cached value of the '{@link #getServiceRefs() <em>Service Refs</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getServiceRefs()
	 * @generated
	 * @ordered
	 */
	protected EList serviceRefs = null;

	/**
	 * The cached value of the '{@link #getResourceRefs() <em>Resource Refs</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getResourceRefs()
	 * @generated
	 * @ordered
	 */
	protected EList resourceRefs = null;

	/**
	 * The cached value of the '{@link #getResourceEnvRefs() <em>Resource Env Refs</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getResourceEnvRefs()
	 * @generated
	 * @ordered
	 */
	protected EList resourceEnvRefs = null;

	/**
	 * The cached value of the '{@link #getMessageDestinationRefs() <em>Message Destination Refs</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getMessageDestinationRefs()
	 * @generated
	 * @ordered
	 */
	protected EList messageDestinationRefs = null;

	/**
	 * The cached value of the '{@link #getPersistenceContextRefs() <em>Persistence Context Refs</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getPersistenceContextRefs()
	 * @generated
	 * @ordered
	 */
	protected EList persistenceContextRefs = null;

	/**
	 * The cached value of the '{@link #getPersistenceUnitRefs() <em>Persistence Unit Refs</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getPersistenceUnitRefs()
	 * @generated
	 * @ordered
	 */
	protected EList persistenceUnitRefs = null;

	/**
	 * The cached value of the '{@link #getPostConstructs() <em>Post Constructs</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getPostConstructs()
	 * @generated
	 * @ordered
	 */
	protected EList postConstructs = null;

	/**
	 * The cached value of the '{@link #getPreDestroys() <em>Pre Destroys</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getPreDestroys()
	 * @generated
	 * @ordered
	 */
	protected EList preDestroys = null;

	/**
	 * The cached value of the '{@link #getPostActivates() <em>Post Activates</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getPostActivates()
	 * @generated
	 * @ordered
	 */
	protected EList postActivates = null;

	/**
	 * The cached value of the '{@link #getPrePassivates() <em>Pre Passivates</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getPrePassivates()
	 * @generated
	 * @ordered
	 */
	protected EList prePassivates = null;

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
	protected InterceptorTypeImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return EjbPackage.Literals.INTERCEPTOR_TYPE;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public List getDescriptions() {
		if (descriptions == null) {
			descriptions = new EObjectContainmentEList(Description.class, this, EjbPackage.INTERCEPTOR_TYPE__DESCRIPTIONS);
		}
		return descriptions;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getInterceptorClass() {
		return interceptorClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setInterceptorClass(String newInterceptorClass) {
		String oldInterceptorClass = interceptorClass;
		interceptorClass = newInterceptorClass;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, EjbPackage.INTERCEPTOR_TYPE__INTERCEPTOR_CLASS, oldInterceptorClass, interceptorClass));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public List getAroundInvokes() {
		if (aroundInvokes == null) {
			aroundInvokes = new EObjectContainmentEList(AroundInvokeType.class, this, EjbPackage.INTERCEPTOR_TYPE__AROUND_INVOKES);
		}
		return aroundInvokes;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public List getEnvEntries() {
		if (envEntries == null) {
			envEntries = new EObjectContainmentEList(EnvEntry.class, this, EjbPackage.INTERCEPTOR_TYPE__ENV_ENTRIES);
		}
		return envEntries;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public List getEjbRefs() {
		if (ejbRefs == null) {
			ejbRefs = new EObjectContainmentEList(EjbRef.class, this, EjbPackage.INTERCEPTOR_TYPE__EJB_REFS);
		}
		return ejbRefs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public List getEjbLocalRefs() {
		if (ejbLocalRefs == null) {
			ejbLocalRefs = new EObjectContainmentEList(EjbLocalRef.class, this, EjbPackage.INTERCEPTOR_TYPE__EJB_LOCAL_REFS);
		}
		return ejbLocalRefs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public List getServiceRefs() {
		if (serviceRefs == null) {
			serviceRefs = new EObjectContainmentEList(ServiceRef.class, this, EjbPackage.INTERCEPTOR_TYPE__SERVICE_REFS);
		}
		return serviceRefs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public List getResourceRefs() {
		if (resourceRefs == null) {
			resourceRefs = new EObjectContainmentEList(ResourceRef.class, this, EjbPackage.INTERCEPTOR_TYPE__RESOURCE_REFS);
		}
		return resourceRefs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public List getResourceEnvRefs() {
		if (resourceEnvRefs == null) {
			resourceEnvRefs = new EObjectContainmentEList(ResourceEnvRef.class, this, EjbPackage.INTERCEPTOR_TYPE__RESOURCE_ENV_REFS);
		}
		return resourceEnvRefs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public List getMessageDestinationRefs() {
		if (messageDestinationRefs == null) {
			messageDestinationRefs = new EObjectContainmentEList(MessageDestinationRef.class, this, EjbPackage.INTERCEPTOR_TYPE__MESSAGE_DESTINATION_REFS);
		}
		return messageDestinationRefs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public List getPersistenceContextRefs() {
		if (persistenceContextRefs == null) {
			persistenceContextRefs = new EObjectContainmentEList(PersistenceContextRef.class, this, EjbPackage.INTERCEPTOR_TYPE__PERSISTENCE_CONTEXT_REFS);
		}
		return persistenceContextRefs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public List getPersistenceUnitRefs() {
		if (persistenceUnitRefs == null) {
			persistenceUnitRefs = new EObjectContainmentEList(PersistenceUnitRef.class, this, EjbPackage.INTERCEPTOR_TYPE__PERSISTENCE_UNIT_REFS);
		}
		return persistenceUnitRefs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public List getPostConstructs() {
		if (postConstructs == null) {
			postConstructs = new EObjectContainmentEList(LifecycleCallback.class, this, EjbPackage.INTERCEPTOR_TYPE__POST_CONSTRUCTS);
		}
		return postConstructs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public List getPreDestroys() {
		if (preDestroys == null) {
			preDestroys = new EObjectContainmentEList(LifecycleCallback.class, this, EjbPackage.INTERCEPTOR_TYPE__PRE_DESTROYS);
		}
		return preDestroys;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public List getPostActivates() {
		if (postActivates == null) {
			postActivates = new EObjectContainmentEList(LifecycleCallback.class, this, EjbPackage.INTERCEPTOR_TYPE__POST_ACTIVATES);
		}
		return postActivates;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public List getPrePassivates() {
		if (prePassivates == null) {
			prePassivates = new EObjectContainmentEList(LifecycleCallback.class, this, EjbPackage.INTERCEPTOR_TYPE__PRE_PASSIVATES);
		}
		return prePassivates;
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
			eNotify(new ENotificationImpl(this, Notification.SET, EjbPackage.INTERCEPTOR_TYPE__ID, oldId, id));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case EjbPackage.INTERCEPTOR_TYPE__DESCRIPTIONS:
				return ((InternalEList)getDescriptions()).basicRemove(otherEnd, msgs);
			case EjbPackage.INTERCEPTOR_TYPE__AROUND_INVOKES:
				return ((InternalEList)getAroundInvokes()).basicRemove(otherEnd, msgs);
			case EjbPackage.INTERCEPTOR_TYPE__ENV_ENTRIES:
				return ((InternalEList)getEnvEntries()).basicRemove(otherEnd, msgs);
			case EjbPackage.INTERCEPTOR_TYPE__EJB_REFS:
				return ((InternalEList)getEjbRefs()).basicRemove(otherEnd, msgs);
			case EjbPackage.INTERCEPTOR_TYPE__EJB_LOCAL_REFS:
				return ((InternalEList)getEjbLocalRefs()).basicRemove(otherEnd, msgs);
			case EjbPackage.INTERCEPTOR_TYPE__SERVICE_REFS:
				return ((InternalEList)getServiceRefs()).basicRemove(otherEnd, msgs);
			case EjbPackage.INTERCEPTOR_TYPE__RESOURCE_REFS:
				return ((InternalEList)getResourceRefs()).basicRemove(otherEnd, msgs);
			case EjbPackage.INTERCEPTOR_TYPE__RESOURCE_ENV_REFS:
				return ((InternalEList)getResourceEnvRefs()).basicRemove(otherEnd, msgs);
			case EjbPackage.INTERCEPTOR_TYPE__MESSAGE_DESTINATION_REFS:
				return ((InternalEList)getMessageDestinationRefs()).basicRemove(otherEnd, msgs);
			case EjbPackage.INTERCEPTOR_TYPE__PERSISTENCE_CONTEXT_REFS:
				return ((InternalEList)getPersistenceContextRefs()).basicRemove(otherEnd, msgs);
			case EjbPackage.INTERCEPTOR_TYPE__PERSISTENCE_UNIT_REFS:
				return ((InternalEList)getPersistenceUnitRefs()).basicRemove(otherEnd, msgs);
			case EjbPackage.INTERCEPTOR_TYPE__POST_CONSTRUCTS:
				return ((InternalEList)getPostConstructs()).basicRemove(otherEnd, msgs);
			case EjbPackage.INTERCEPTOR_TYPE__PRE_DESTROYS:
				return ((InternalEList)getPreDestroys()).basicRemove(otherEnd, msgs);
			case EjbPackage.INTERCEPTOR_TYPE__POST_ACTIVATES:
				return ((InternalEList)getPostActivates()).basicRemove(otherEnd, msgs);
			case EjbPackage.INTERCEPTOR_TYPE__PRE_PASSIVATES:
				return ((InternalEList)getPrePassivates()).basicRemove(otherEnd, msgs);
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
			case EjbPackage.INTERCEPTOR_TYPE__DESCRIPTIONS:
				return getDescriptions();
			case EjbPackage.INTERCEPTOR_TYPE__INTERCEPTOR_CLASS:
				return getInterceptorClass();
			case EjbPackage.INTERCEPTOR_TYPE__AROUND_INVOKES:
				return getAroundInvokes();
			case EjbPackage.INTERCEPTOR_TYPE__ENV_ENTRIES:
				return getEnvEntries();
			case EjbPackage.INTERCEPTOR_TYPE__EJB_REFS:
				return getEjbRefs();
			case EjbPackage.INTERCEPTOR_TYPE__EJB_LOCAL_REFS:
				return getEjbLocalRefs();
			case EjbPackage.INTERCEPTOR_TYPE__SERVICE_REFS:
				return getServiceRefs();
			case EjbPackage.INTERCEPTOR_TYPE__RESOURCE_REFS:
				return getResourceRefs();
			case EjbPackage.INTERCEPTOR_TYPE__RESOURCE_ENV_REFS:
				return getResourceEnvRefs();
			case EjbPackage.INTERCEPTOR_TYPE__MESSAGE_DESTINATION_REFS:
				return getMessageDestinationRefs();
			case EjbPackage.INTERCEPTOR_TYPE__PERSISTENCE_CONTEXT_REFS:
				return getPersistenceContextRefs();
			case EjbPackage.INTERCEPTOR_TYPE__PERSISTENCE_UNIT_REFS:
				return getPersistenceUnitRefs();
			case EjbPackage.INTERCEPTOR_TYPE__POST_CONSTRUCTS:
				return getPostConstructs();
			case EjbPackage.INTERCEPTOR_TYPE__PRE_DESTROYS:
				return getPreDestroys();
			case EjbPackage.INTERCEPTOR_TYPE__POST_ACTIVATES:
				return getPostActivates();
			case EjbPackage.INTERCEPTOR_TYPE__PRE_PASSIVATES:
				return getPrePassivates();
			case EjbPackage.INTERCEPTOR_TYPE__ID:
				return getId();
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
			case EjbPackage.INTERCEPTOR_TYPE__DESCRIPTIONS:
				getDescriptions().clear();
				getDescriptions().addAll((Collection)newValue);
				return;
			case EjbPackage.INTERCEPTOR_TYPE__INTERCEPTOR_CLASS:
				setInterceptorClass((String)newValue);
				return;
			case EjbPackage.INTERCEPTOR_TYPE__AROUND_INVOKES:
				getAroundInvokes().clear();
				getAroundInvokes().addAll((Collection)newValue);
				return;
			case EjbPackage.INTERCEPTOR_TYPE__ENV_ENTRIES:
				getEnvEntries().clear();
				getEnvEntries().addAll((Collection)newValue);
				return;
			case EjbPackage.INTERCEPTOR_TYPE__EJB_REFS:
				getEjbRefs().clear();
				getEjbRefs().addAll((Collection)newValue);
				return;
			case EjbPackage.INTERCEPTOR_TYPE__EJB_LOCAL_REFS:
				getEjbLocalRefs().clear();
				getEjbLocalRefs().addAll((Collection)newValue);
				return;
			case EjbPackage.INTERCEPTOR_TYPE__SERVICE_REFS:
				getServiceRefs().clear();
				getServiceRefs().addAll((Collection)newValue);
				return;
			case EjbPackage.INTERCEPTOR_TYPE__RESOURCE_REFS:
				getResourceRefs().clear();
				getResourceRefs().addAll((Collection)newValue);
				return;
			case EjbPackage.INTERCEPTOR_TYPE__RESOURCE_ENV_REFS:
				getResourceEnvRefs().clear();
				getResourceEnvRefs().addAll((Collection)newValue);
				return;
			case EjbPackage.INTERCEPTOR_TYPE__MESSAGE_DESTINATION_REFS:
				getMessageDestinationRefs().clear();
				getMessageDestinationRefs().addAll((Collection)newValue);
				return;
			case EjbPackage.INTERCEPTOR_TYPE__PERSISTENCE_CONTEXT_REFS:
				getPersistenceContextRefs().clear();
				getPersistenceContextRefs().addAll((Collection)newValue);
				return;
			case EjbPackage.INTERCEPTOR_TYPE__PERSISTENCE_UNIT_REFS:
				getPersistenceUnitRefs().clear();
				getPersistenceUnitRefs().addAll((Collection)newValue);
				return;
			case EjbPackage.INTERCEPTOR_TYPE__POST_CONSTRUCTS:
				getPostConstructs().clear();
				getPostConstructs().addAll((Collection)newValue);
				return;
			case EjbPackage.INTERCEPTOR_TYPE__PRE_DESTROYS:
				getPreDestroys().clear();
				getPreDestroys().addAll((Collection)newValue);
				return;
			case EjbPackage.INTERCEPTOR_TYPE__POST_ACTIVATES:
				getPostActivates().clear();
				getPostActivates().addAll((Collection)newValue);
				return;
			case EjbPackage.INTERCEPTOR_TYPE__PRE_PASSIVATES:
				getPrePassivates().clear();
				getPrePassivates().addAll((Collection)newValue);
				return;
			case EjbPackage.INTERCEPTOR_TYPE__ID:
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
			case EjbPackage.INTERCEPTOR_TYPE__DESCRIPTIONS:
				getDescriptions().clear();
				return;
			case EjbPackage.INTERCEPTOR_TYPE__INTERCEPTOR_CLASS:
				setInterceptorClass(INTERCEPTOR_CLASS_EDEFAULT);
				return;
			case EjbPackage.INTERCEPTOR_TYPE__AROUND_INVOKES:
				getAroundInvokes().clear();
				return;
			case EjbPackage.INTERCEPTOR_TYPE__ENV_ENTRIES:
				getEnvEntries().clear();
				return;
			case EjbPackage.INTERCEPTOR_TYPE__EJB_REFS:
				getEjbRefs().clear();
				return;
			case EjbPackage.INTERCEPTOR_TYPE__EJB_LOCAL_REFS:
				getEjbLocalRefs().clear();
				return;
			case EjbPackage.INTERCEPTOR_TYPE__SERVICE_REFS:
				getServiceRefs().clear();
				return;
			case EjbPackage.INTERCEPTOR_TYPE__RESOURCE_REFS:
				getResourceRefs().clear();
				return;
			case EjbPackage.INTERCEPTOR_TYPE__RESOURCE_ENV_REFS:
				getResourceEnvRefs().clear();
				return;
			case EjbPackage.INTERCEPTOR_TYPE__MESSAGE_DESTINATION_REFS:
				getMessageDestinationRefs().clear();
				return;
			case EjbPackage.INTERCEPTOR_TYPE__PERSISTENCE_CONTEXT_REFS:
				getPersistenceContextRefs().clear();
				return;
			case EjbPackage.INTERCEPTOR_TYPE__PERSISTENCE_UNIT_REFS:
				getPersistenceUnitRefs().clear();
				return;
			case EjbPackage.INTERCEPTOR_TYPE__POST_CONSTRUCTS:
				getPostConstructs().clear();
				return;
			case EjbPackage.INTERCEPTOR_TYPE__PRE_DESTROYS:
				getPreDestroys().clear();
				return;
			case EjbPackage.INTERCEPTOR_TYPE__POST_ACTIVATES:
				getPostActivates().clear();
				return;
			case EjbPackage.INTERCEPTOR_TYPE__PRE_PASSIVATES:
				getPrePassivates().clear();
				return;
			case EjbPackage.INTERCEPTOR_TYPE__ID:
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
			case EjbPackage.INTERCEPTOR_TYPE__DESCRIPTIONS:
				return descriptions != null && !descriptions.isEmpty();
			case EjbPackage.INTERCEPTOR_TYPE__INTERCEPTOR_CLASS:
				return INTERCEPTOR_CLASS_EDEFAULT == null ? interceptorClass != null : !INTERCEPTOR_CLASS_EDEFAULT.equals(interceptorClass);
			case EjbPackage.INTERCEPTOR_TYPE__AROUND_INVOKES:
				return aroundInvokes != null && !aroundInvokes.isEmpty();
			case EjbPackage.INTERCEPTOR_TYPE__ENV_ENTRIES:
				return envEntries != null && !envEntries.isEmpty();
			case EjbPackage.INTERCEPTOR_TYPE__EJB_REFS:
				return ejbRefs != null && !ejbRefs.isEmpty();
			case EjbPackage.INTERCEPTOR_TYPE__EJB_LOCAL_REFS:
				return ejbLocalRefs != null && !ejbLocalRefs.isEmpty();
			case EjbPackage.INTERCEPTOR_TYPE__SERVICE_REFS:
				return serviceRefs != null && !serviceRefs.isEmpty();
			case EjbPackage.INTERCEPTOR_TYPE__RESOURCE_REFS:
				return resourceRefs != null && !resourceRefs.isEmpty();
			case EjbPackage.INTERCEPTOR_TYPE__RESOURCE_ENV_REFS:
				return resourceEnvRefs != null && !resourceEnvRefs.isEmpty();
			case EjbPackage.INTERCEPTOR_TYPE__MESSAGE_DESTINATION_REFS:
				return messageDestinationRefs != null && !messageDestinationRefs.isEmpty();
			case EjbPackage.INTERCEPTOR_TYPE__PERSISTENCE_CONTEXT_REFS:
				return persistenceContextRefs != null && !persistenceContextRefs.isEmpty();
			case EjbPackage.INTERCEPTOR_TYPE__PERSISTENCE_UNIT_REFS:
				return persistenceUnitRefs != null && !persistenceUnitRefs.isEmpty();
			case EjbPackage.INTERCEPTOR_TYPE__POST_CONSTRUCTS:
				return postConstructs != null && !postConstructs.isEmpty();
			case EjbPackage.INTERCEPTOR_TYPE__PRE_DESTROYS:
				return preDestroys != null && !preDestroys.isEmpty();
			case EjbPackage.INTERCEPTOR_TYPE__POST_ACTIVATES:
				return postActivates != null && !postActivates.isEmpty();
			case EjbPackage.INTERCEPTOR_TYPE__PRE_PASSIVATES:
				return prePassivates != null && !prePassivates.isEmpty();
			case EjbPackage.INTERCEPTOR_TYPE__ID:
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
		result.append(" (interceptorClass: "); //$NON-NLS-1$
		result.append(interceptorClass);
		result.append(", id: "); //$NON-NLS-1$
		result.append(id);
		result.append(')');
		return result.toString();
	}

} //InterceptorTypeImpl