package org.eclipse.jem.internal.beaninfo.impl;
/*******************************************************************************
 * Copyright (c)  2001, 2003 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials 
 * are made available under the terms of the Common Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/cpl-v10.html
 * 
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *******************************************************************************/
/*
 *  $RCSfile: BeanDecoratorImpl.java,v $
 *  $Revision: 1.5 $  $Date: 2004/03/07 14:33:09 $ 
 */


import java.net.MalformedURLException;
import java.net.URL;
import java.util.*;
import java.util.Collection;
import java.util.Iterator;
import java.util.logging.Level;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EModelElement;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.EcorePackage;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.util.InternalEList;

import org.eclipse.jem.internal.beaninfo.BeanDecorator;
import org.eclipse.jem.internal.beaninfo.BeaninfoPackage;
import org.eclipse.jem.internal.beaninfo.FeatureAttributeValue;
import org.eclipse.jem.internal.beaninfo.adapters.*;
import org.eclipse.jem.internal.beaninfo.adapters.BeaninfoProxyConstants;
import org.eclipse.jem.internal.beaninfo.adapters.Utilities;
import org.eclipse.jem.java.JavaClass;
import org.eclipse.jem.internal.proxy.core.*;
import org.eclipse.jem.internal.proxy.core.IBeanTypeProxy;
import org.eclipse.jem.internal.proxy.core.IStringBeanProxy;
import org.eclipse.jem.internal.proxy.core.ThrowableProxy;

import com.ibm.wtp.logger.proxyrender.EclipseLogger;
/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Bean Decorator</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.eclipse.jem.internal.beaninfo.impl.BeanDecoratorImpl#isMergeSuperProperties <em>Merge Super Properties</em>}</li>
 *   <li>{@link org.eclipse.jem.internal.beaninfo.impl.BeanDecoratorImpl#isMergeSuperBehaviors <em>Merge Super Behaviors</em>}</li>
 *   <li>{@link org.eclipse.jem.internal.beaninfo.impl.BeanDecoratorImpl#isMergeSuperEvents <em>Merge Super Events</em>}</li>
 *   <li>{@link org.eclipse.jem.internal.beaninfo.impl.BeanDecoratorImpl#isIntrospectProperties <em>Introspect Properties</em>}</li>
 *   <li>{@link org.eclipse.jem.internal.beaninfo.impl.BeanDecoratorImpl#isIntrospectBehaviors <em>Introspect Behaviors</em>}</li>
 *   <li>{@link org.eclipse.jem.internal.beaninfo.impl.BeanDecoratorImpl#isIntrospectEvents <em>Introspect Events</em>}</li>
 *   <li>{@link org.eclipse.jem.internal.beaninfo.impl.BeanDecoratorImpl#isDoBeaninfo <em>Do Beaninfo</em>}</li>
 *   <li>{@link org.eclipse.jem.internal.beaninfo.impl.BeanDecoratorImpl#getCustomizerClass <em>Customizer Class</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */


public class BeanDecoratorImpl extends FeatureDecoratorImpl implements BeanDecorator{
	/**
	 * The default value of the '{@link #isMergeSuperProperties() <em>Merge Super Properties</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isMergeSuperProperties()
	 * @generated
	 * @ordered
	 */
	protected static final boolean MERGE_SUPER_PROPERTIES_EDEFAULT = true;

	private Boolean mergeSuperPropertiesProxy;
	private Boolean mergeSuperBehaviorsProxy;
	private Boolean mergeSuperEventsProxy;
	
	/**
	 * The cached value of the '{@link #isMergeSuperProperties() <em>Merge Super Properties</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isMergeSuperProperties()
	 * @generated
	 * @ordered
	 */
	protected boolean mergeSuperProperties = MERGE_SUPER_PROPERTIES_EDEFAULT;
	/**
	 * This is true if the Merge Super Properties attribute has been set.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	protected boolean mergeSuperPropertiesESet = false;

	/**
	 * The default value of the '{@link #isMergeSuperBehaviors() <em>Merge Super Behaviors</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isMergeSuperBehaviors()
	 * @generated
	 * @ordered
	 */
	protected static final boolean MERGE_SUPER_BEHAVIORS_EDEFAULT = true;

	/**
	 * The cached value of the '{@link #isMergeSuperBehaviors() <em>Merge Super Behaviors</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isMergeSuperBehaviors()
	 * @generated
	 * @ordered
	 */
	protected boolean mergeSuperBehaviors = MERGE_SUPER_BEHAVIORS_EDEFAULT;
	/**
	 * This is true if the Merge Super Behaviors attribute has been set.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	protected boolean mergeSuperBehaviorsESet = false;

	/**
	 * The default value of the '{@link #isMergeSuperEvents() <em>Merge Super Events</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isMergeSuperEvents()
	 * @generated
	 * @ordered
	 */
	protected static final boolean MERGE_SUPER_EVENTS_EDEFAULT = true;

	/**
	 * The cached value of the '{@link #isMergeSuperEvents() <em>Merge Super Events</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isMergeSuperEvents()
	 * @generated
	 * @ordered
	 */
	protected boolean mergeSuperEvents = MERGE_SUPER_EVENTS_EDEFAULT;
	/**
	 * This is true if the Merge Super Events attribute has been set.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	protected boolean mergeSuperEventsESet = false;

	/**
	 * The default value of the '{@link #isIntrospectProperties() <em>Introspect Properties</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isIntrospectProperties()
	 * @generated
	 * @ordered
	 */
	protected static final boolean INTROSPECT_PROPERTIES_EDEFAULT = true;

	/**
	 * The cached value of the '{@link #isIntrospectProperties() <em>Introspect Properties</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isIntrospectProperties()
	 * @generated
	 * @ordered
	 */
	protected boolean introspectProperties = INTROSPECT_PROPERTIES_EDEFAULT;
	/**
	 * The default value of the '{@link #isIntrospectBehaviors() <em>Introspect Behaviors</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isIntrospectBehaviors()
	 * @generated
	 * @ordered
	 */
	protected static final boolean INTROSPECT_BEHAVIORS_EDEFAULT = true;

	/**
	 * The cached value of the '{@link #isIntrospectBehaviors() <em>Introspect Behaviors</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isIntrospectBehaviors()
	 * @generated
	 * @ordered
	 */
	protected boolean introspectBehaviors = INTROSPECT_BEHAVIORS_EDEFAULT;
	/**
	 * The default value of the '{@link #isIntrospectEvents() <em>Introspect Events</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isIntrospectEvents()
	 * @generated
	 * @ordered
	 */
	protected static final boolean INTROSPECT_EVENTS_EDEFAULT = true;

	/**
	 * The cached value of the '{@link #isIntrospectEvents() <em>Introspect Events</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isIntrospectEvents()
	 * @generated
	 * @ordered
	 */
	protected boolean introspectEvents = INTROSPECT_EVENTS_EDEFAULT;
	/**
	 * The default value of the '{@link #isDoBeaninfo() <em>Do Beaninfo</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isDoBeaninfo()
	 * @generated
	 * @ordered
	 */
	protected static final boolean DO_BEANINFO_EDEFAULT = true;

	/**
	 * The cached value of the '{@link #isDoBeaninfo() <em>Do Beaninfo</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isDoBeaninfo()
	 * @generated
	 * @ordered
	 */
	protected boolean doBeaninfo = DO_BEANINFO_EDEFAULT;
	/**
	 * The cached value of the '{@link #getCustomizerClass() <em>Customizer Class</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getCustomizerClass()
	 * @generated
	 * @ordered
	 */
	protected JavaClass customizerClass = null;
	
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */	
	protected BeanDecoratorImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected EClass eStaticClass() {
		return BeaninfoPackage.eINSTANCE.getBeanDecorator();
	}

	/**
	 * Should the properties of super types be merged when asking for eAllAttributes//eAllReferences.
	 */
	public boolean isMergeSuperProperties() {
		if (mergeSuperPropertiesProxy != null && !this.isSetMergeSuperProperties())
			return mergeSuperPropertiesProxy.booleanValue();
		else
			return this.isMergeSuperPropertiesGen();
	}
	
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean isMergeSuperPropertiesGen() {
		return mergeSuperProperties;
	}

	public JavaClass getCustomizerClass() {
		if (validProxy(fFeatureProxy) && !this.eIsSet(BeaninfoPackage.eINSTANCE.getBeanDecorator_CustomizerClass()))
			try {
				return (JavaClass) Utilities.getJavaClass((IBeanTypeProxy) BeaninfoProxyConstants.getConstants(fFeatureProxy.getProxyFactoryRegistry()).getCustomizerClassProxy().invoke(fFeatureProxy), getEModelElement().eResource().getResourceSet());
			} catch (ThrowableProxy e) {
			};
					
		return this.getCustomizerClassGen();
	}
	
	/**
	 * Set merge super properties proxy. This can't be answered from the BeanDescriptor proxy,
	 * so it must be explicitly set from the beaninfo class adapter.
	 */
	public void setMergeSuperPropertiesProxy(Boolean bool) {
		mergeSuperPropertiesProxy = bool;
	}
	


	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setMergeSuperProperties(boolean newMergeSuperProperties) {
		boolean oldMergeSuperProperties = mergeSuperProperties;
		mergeSuperProperties = newMergeSuperProperties;
		boolean oldMergeSuperPropertiesESet = mergeSuperPropertiesESet;
		mergeSuperPropertiesESet = true;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, BeaninfoPackage.BEAN_DECORATOR__MERGE_SUPER_PROPERTIES, oldMergeSuperProperties, mergeSuperProperties, !oldMergeSuperPropertiesESet));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void unsetMergeSuperProperties() {
		boolean oldMergeSuperProperties = mergeSuperProperties;
		boolean oldMergeSuperPropertiesESet = mergeSuperPropertiesESet;
		mergeSuperProperties = MERGE_SUPER_PROPERTIES_EDEFAULT;
		mergeSuperPropertiesESet = false;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.UNSET, BeaninfoPackage.BEAN_DECORATOR__MERGE_SUPER_PROPERTIES, oldMergeSuperProperties, MERGE_SUPER_PROPERTIES_EDEFAULT, oldMergeSuperPropertiesESet));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean isSetMergeSuperProperties() {
		return mergeSuperPropertiesESet;
	}

	/**
	 * Should the behaviors of super types be merged when asking for eAllBehaviors.
	 */
	public boolean isMergeSuperBehaviors() {
		if (mergeSuperBehaviorsProxy != null && !this.isSetMergeSuperBehaviors())
			return mergeSuperBehaviorsProxy.booleanValue();
		else
			return this.isMergeSuperBehaviorsGen();
	
	}
	
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean isMergeSuperBehaviorsGen() {
		return mergeSuperBehaviors;
	}

	/**
	 * Set merge super behaviors proxy. This can't be answered from the BeanDescriptor proxy,
	 * so it must be explicitly set from the beaninfo class adapter.
	 */
	public void setMergeSuperBehaviorsProxy(Boolean bool) {
		mergeSuperBehaviorsProxy = bool;
	}	
	


	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setMergeSuperBehaviors(boolean newMergeSuperBehaviors) {
		boolean oldMergeSuperBehaviors = mergeSuperBehaviors;
		mergeSuperBehaviors = newMergeSuperBehaviors;
		boolean oldMergeSuperBehaviorsESet = mergeSuperBehaviorsESet;
		mergeSuperBehaviorsESet = true;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, BeaninfoPackage.BEAN_DECORATOR__MERGE_SUPER_BEHAVIORS, oldMergeSuperBehaviors, mergeSuperBehaviors, !oldMergeSuperBehaviorsESet));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void unsetMergeSuperBehaviors() {
		boolean oldMergeSuperBehaviors = mergeSuperBehaviors;
		boolean oldMergeSuperBehaviorsESet = mergeSuperBehaviorsESet;
		mergeSuperBehaviors = MERGE_SUPER_BEHAVIORS_EDEFAULT;
		mergeSuperBehaviorsESet = false;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.UNSET, BeaninfoPackage.BEAN_DECORATOR__MERGE_SUPER_BEHAVIORS, oldMergeSuperBehaviors, MERGE_SUPER_BEHAVIORS_EDEFAULT, oldMergeSuperBehaviorsESet));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean isSetMergeSuperBehaviors() {
		return mergeSuperBehaviorsESet;
	}

	/** 
	 * Should the events of super types be merged when asking for eAllEvents.
	 */
	public boolean isMergeSuperEvents() {
		if (mergeSuperEventsProxy != null && !this.isSetMergeSuperEvents())
			return mergeSuperEventsProxy.booleanValue();
		else
			return this.isMergeSuperEventsGen();
	
	}
	
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean isMergeSuperEventsGen() {
		return mergeSuperEvents;
	}

	/**
	 * Set merge super events proxy. This can't be answered from the BeanDescriptor proxy,
	 * so it must be explicitly set from the beaninfo class adapter.
	 */
	public void setMergeSuperEventsProxy(Boolean bool) {
		mergeSuperEventsProxy = bool;
	}	
		

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setMergeSuperEvents(boolean newMergeSuperEvents) {
		boolean oldMergeSuperEvents = mergeSuperEvents;
		mergeSuperEvents = newMergeSuperEvents;
		boolean oldMergeSuperEventsESet = mergeSuperEventsESet;
		mergeSuperEventsESet = true;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, BeaninfoPackage.BEAN_DECORATOR__MERGE_SUPER_EVENTS, oldMergeSuperEvents, mergeSuperEvents, !oldMergeSuperEventsESet));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void unsetMergeSuperEvents() {
		boolean oldMergeSuperEvents = mergeSuperEvents;
		boolean oldMergeSuperEventsESet = mergeSuperEventsESet;
		mergeSuperEvents = MERGE_SUPER_EVENTS_EDEFAULT;
		mergeSuperEventsESet = false;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.UNSET, BeaninfoPackage.BEAN_DECORATOR__MERGE_SUPER_EVENTS, oldMergeSuperEvents, MERGE_SUPER_EVENTS_EDEFAULT, oldMergeSuperEventsESet));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean isSetMergeSuperEvents() {
		return mergeSuperEventsESet;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean isIntrospectProperties() {
		return introspectProperties;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setIntrospectProperties(boolean newIntrospectProperties) {
		boolean oldIntrospectProperties = introspectProperties;
		introspectProperties = newIntrospectProperties;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, BeaninfoPackage.BEAN_DECORATOR__INTROSPECT_PROPERTIES, oldIntrospectProperties, introspectProperties));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean isIntrospectBehaviors() {
		return introspectBehaviors;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setIntrospectBehaviors(boolean newIntrospectBehaviors) {
		boolean oldIntrospectBehaviors = introspectBehaviors;
		introspectBehaviors = newIntrospectBehaviors;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, BeaninfoPackage.BEAN_DECORATOR__INTROSPECT_BEHAVIORS, oldIntrospectBehaviors, introspectBehaviors));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean isIntrospectEvents() {
		return introspectEvents;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setIntrospectEvents(boolean newIntrospectEvents) {
		boolean oldIntrospectEvents = introspectEvents;
		introspectEvents = newIntrospectEvents;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, BeaninfoPackage.BEAN_DECORATOR__INTROSPECT_EVENTS, oldIntrospectEvents, introspectEvents));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setCustomizerClass(JavaClass newCustomizerClass) {
		JavaClass oldCustomizerClass = customizerClass;
		customizerClass = newCustomizerClass;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, BeaninfoPackage.BEAN_DECORATOR__CUSTOMIZER_CLASS, oldCustomizerClass, customizerClass));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain eInverseAdd(InternalEObject otherEnd, int featureID, Class baseClass, NotificationChain msgs) {
		if (featureID >= 0) {
			switch (eDerivedStructuralFeatureID(featureID, baseClass)) {
				case BeaninfoPackage.BEAN_DECORATOR__EANNOTATIONS:
					return ((InternalEList)getEAnnotations()).basicAdd(otherEnd, msgs);
				case BeaninfoPackage.BEAN_DECORATOR__EMODEL_ELEMENT:
					if (eContainer != null)
						msgs = eBasicRemoveFromContainer(msgs);
					return eBasicSetContainer(otherEnd, BeaninfoPackage.BEAN_DECORATOR__EMODEL_ELEMENT, msgs);
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
				case BeaninfoPackage.BEAN_DECORATOR__EANNOTATIONS:
					return ((InternalEList)getEAnnotations()).basicRemove(otherEnd, msgs);
				case BeaninfoPackage.BEAN_DECORATOR__DETAILS:
					return ((InternalEList)getDetails()).basicRemove(otherEnd, msgs);
				case BeaninfoPackage.BEAN_DECORATOR__EMODEL_ELEMENT:
					return eBasicSetContainer(null, BeaninfoPackage.BEAN_DECORATOR__EMODEL_ELEMENT, msgs);
				case BeaninfoPackage.BEAN_DECORATOR__CONTENTS:
					return ((InternalEList)getContents()).basicRemove(otherEnd, msgs);
				case BeaninfoPackage.BEAN_DECORATOR__ATTRIBUTES:
					return ((InternalEList)getAttributes()).basicRemove(otherEnd, msgs);
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
				case BeaninfoPackage.BEAN_DECORATOR__EMODEL_ELEMENT:
					return ((InternalEObject)eContainer).eInverseRemove(this, EcorePackage.EMODEL_ELEMENT__EANNOTATIONS, EModelElement.class, msgs);
				default:
					return eDynamicBasicRemoveFromContainer(msgs);
			}
		}
		return ((InternalEObject)eContainer).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - eContainerFeatureID, null, msgs);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Object eGet(EStructuralFeature eFeature, boolean resolve) {
		switch (eDerivedStructuralFeatureID(eFeature)) {
			case BeaninfoPackage.BEAN_DECORATOR__EANNOTATIONS:
				return getEAnnotations();
			case BeaninfoPackage.BEAN_DECORATOR__SOURCE:
				return getSource();
			case BeaninfoPackage.BEAN_DECORATOR__DETAILS:
				return getDetails();
			case BeaninfoPackage.BEAN_DECORATOR__EMODEL_ELEMENT:
				return getEModelElement();
			case BeaninfoPackage.BEAN_DECORATOR__CONTENTS:
				return getContents();
			case BeaninfoPackage.BEAN_DECORATOR__REFERENCES:
				return getReferences();
			case BeaninfoPackage.BEAN_DECORATOR__DISPLAY_NAME:
				return getDisplayName();
			case BeaninfoPackage.BEAN_DECORATOR__SHORT_DESCRIPTION:
				return getShortDescription();
			case BeaninfoPackage.BEAN_DECORATOR__CATEGORY:
				return getCategory();
			case BeaninfoPackage.BEAN_DECORATOR__EXPERT:
				return isExpert() ? Boolean.TRUE : Boolean.FALSE;
			case BeaninfoPackage.BEAN_DECORATOR__HIDDEN:
				return isHidden() ? Boolean.TRUE : Boolean.FALSE;
			case BeaninfoPackage.BEAN_DECORATOR__PREFERRED:
				return isPreferred() ? Boolean.TRUE : Boolean.FALSE;
			case BeaninfoPackage.BEAN_DECORATOR__MERGE_INTROSPECTION:
				return isMergeIntrospection() ? Boolean.TRUE : Boolean.FALSE;
			case BeaninfoPackage.BEAN_DECORATOR__ATTRIBUTES_EXPLICIT:
				return isAttributesExplicit() ? Boolean.TRUE : Boolean.FALSE;
			case BeaninfoPackage.BEAN_DECORATOR__ATTRIBUTES:
				return getAttributes();
			case BeaninfoPackage.BEAN_DECORATOR__MERGE_SUPER_PROPERTIES:
				return isMergeSuperProperties() ? Boolean.TRUE : Boolean.FALSE;
			case BeaninfoPackage.BEAN_DECORATOR__MERGE_SUPER_BEHAVIORS:
				return isMergeSuperBehaviors() ? Boolean.TRUE : Boolean.FALSE;
			case BeaninfoPackage.BEAN_DECORATOR__MERGE_SUPER_EVENTS:
				return isMergeSuperEvents() ? Boolean.TRUE : Boolean.FALSE;
			case BeaninfoPackage.BEAN_DECORATOR__INTROSPECT_PROPERTIES:
				return isIntrospectProperties() ? Boolean.TRUE : Boolean.FALSE;
			case BeaninfoPackage.BEAN_DECORATOR__INTROSPECT_BEHAVIORS:
				return isIntrospectBehaviors() ? Boolean.TRUE : Boolean.FALSE;
			case BeaninfoPackage.BEAN_DECORATOR__INTROSPECT_EVENTS:
				return isIntrospectEvents() ? Boolean.TRUE : Boolean.FALSE;
			case BeaninfoPackage.BEAN_DECORATOR__DO_BEANINFO:
				return isDoBeaninfo() ? Boolean.TRUE : Boolean.FALSE;
			case BeaninfoPackage.BEAN_DECORATOR__CUSTOMIZER_CLASS:
				if (resolve) return getCustomizerClass();
				return basicGetCustomizerClass();
		}
		return eDynamicGet(eFeature, resolve);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void eSet(EStructuralFeature eFeature, Object newValue) {
		switch (eDerivedStructuralFeatureID(eFeature)) {
			case BeaninfoPackage.BEAN_DECORATOR__EANNOTATIONS:
				getEAnnotations().clear();
				getEAnnotations().addAll((Collection)newValue);
				return;
			case BeaninfoPackage.BEAN_DECORATOR__SOURCE:
				setSource((String)newValue);
				return;
			case BeaninfoPackage.BEAN_DECORATOR__DETAILS:
				getDetails().clear();
				getDetails().addAll((Collection)newValue);
				return;
			case BeaninfoPackage.BEAN_DECORATOR__EMODEL_ELEMENT:
				setEModelElement((EModelElement)newValue);
				return;
			case BeaninfoPackage.BEAN_DECORATOR__CONTENTS:
				getContents().clear();
				getContents().addAll((Collection)newValue);
				return;
			case BeaninfoPackage.BEAN_DECORATOR__REFERENCES:
				getReferences().clear();
				getReferences().addAll((Collection)newValue);
				return;
			case BeaninfoPackage.BEAN_DECORATOR__DISPLAY_NAME:
				setDisplayName((String)newValue);
				return;
			case BeaninfoPackage.BEAN_DECORATOR__SHORT_DESCRIPTION:
				setShortDescription((String)newValue);
				return;
			case BeaninfoPackage.BEAN_DECORATOR__CATEGORY:
				setCategory((String)newValue);
				return;
			case BeaninfoPackage.BEAN_DECORATOR__EXPERT:
				setExpert(((Boolean)newValue).booleanValue());
				return;
			case BeaninfoPackage.BEAN_DECORATOR__HIDDEN:
				setHidden(((Boolean)newValue).booleanValue());
				return;
			case BeaninfoPackage.BEAN_DECORATOR__PREFERRED:
				setPreferred(((Boolean)newValue).booleanValue());
				return;
			case BeaninfoPackage.BEAN_DECORATOR__MERGE_INTROSPECTION:
				setMergeIntrospection(((Boolean)newValue).booleanValue());
				return;
			case BeaninfoPackage.BEAN_DECORATOR__ATTRIBUTES_EXPLICIT:
				setAttributesExplicit(((Boolean)newValue).booleanValue());
				return;
			case BeaninfoPackage.BEAN_DECORATOR__ATTRIBUTES:
				getAttributes().clear();
				getAttributes().addAll((Collection)newValue);
				return;
			case BeaninfoPackage.BEAN_DECORATOR__MERGE_SUPER_PROPERTIES:
				setMergeSuperProperties(((Boolean)newValue).booleanValue());
				return;
			case BeaninfoPackage.BEAN_DECORATOR__MERGE_SUPER_BEHAVIORS:
				setMergeSuperBehaviors(((Boolean)newValue).booleanValue());
				return;
			case BeaninfoPackage.BEAN_DECORATOR__MERGE_SUPER_EVENTS:
				setMergeSuperEvents(((Boolean)newValue).booleanValue());
				return;
			case BeaninfoPackage.BEAN_DECORATOR__INTROSPECT_PROPERTIES:
				setIntrospectProperties(((Boolean)newValue).booleanValue());
				return;
			case BeaninfoPackage.BEAN_DECORATOR__INTROSPECT_BEHAVIORS:
				setIntrospectBehaviors(((Boolean)newValue).booleanValue());
				return;
			case BeaninfoPackage.BEAN_DECORATOR__INTROSPECT_EVENTS:
				setIntrospectEvents(((Boolean)newValue).booleanValue());
				return;
			case BeaninfoPackage.BEAN_DECORATOR__DO_BEANINFO:
				setDoBeaninfo(((Boolean)newValue).booleanValue());
				return;
			case BeaninfoPackage.BEAN_DECORATOR__CUSTOMIZER_CLASS:
				setCustomizerClass((JavaClass)newValue);
				return;
		}
		eDynamicSet(eFeature, newValue);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void eUnset(EStructuralFeature eFeature) {
		switch (eDerivedStructuralFeatureID(eFeature)) {
			case BeaninfoPackage.BEAN_DECORATOR__EANNOTATIONS:
				getEAnnotations().clear();
				return;
			case BeaninfoPackage.BEAN_DECORATOR__SOURCE:
				setSource(SOURCE_EDEFAULT);
				return;
			case BeaninfoPackage.BEAN_DECORATOR__DETAILS:
				getDetails().clear();
				return;
			case BeaninfoPackage.BEAN_DECORATOR__EMODEL_ELEMENT:
				setEModelElement((EModelElement)null);
				return;
			case BeaninfoPackage.BEAN_DECORATOR__CONTENTS:
				getContents().clear();
				return;
			case BeaninfoPackage.BEAN_DECORATOR__REFERENCES:
				getReferences().clear();
				return;
			case BeaninfoPackage.BEAN_DECORATOR__DISPLAY_NAME:
				unsetDisplayName();
				return;
			case BeaninfoPackage.BEAN_DECORATOR__SHORT_DESCRIPTION:
				unsetShortDescription();
				return;
			case BeaninfoPackage.BEAN_DECORATOR__CATEGORY:
				setCategory(CATEGORY_EDEFAULT);
				return;
			case BeaninfoPackage.BEAN_DECORATOR__EXPERT:
				unsetExpert();
				return;
			case BeaninfoPackage.BEAN_DECORATOR__HIDDEN:
				unsetHidden();
				return;
			case BeaninfoPackage.BEAN_DECORATOR__PREFERRED:
				unsetPreferred();
				return;
			case BeaninfoPackage.BEAN_DECORATOR__MERGE_INTROSPECTION:
				setMergeIntrospection(MERGE_INTROSPECTION_EDEFAULT);
				return;
			case BeaninfoPackage.BEAN_DECORATOR__ATTRIBUTES_EXPLICIT:
				setAttributesExplicit(ATTRIBUTES_EXPLICIT_EDEFAULT);
				return;
			case BeaninfoPackage.BEAN_DECORATOR__ATTRIBUTES:
				getAttributes().clear();
				return;
			case BeaninfoPackage.BEAN_DECORATOR__MERGE_SUPER_PROPERTIES:
				unsetMergeSuperProperties();
				return;
			case BeaninfoPackage.BEAN_DECORATOR__MERGE_SUPER_BEHAVIORS:
				unsetMergeSuperBehaviors();
				return;
			case BeaninfoPackage.BEAN_DECORATOR__MERGE_SUPER_EVENTS:
				unsetMergeSuperEvents();
				return;
			case BeaninfoPackage.BEAN_DECORATOR__INTROSPECT_PROPERTIES:
				setIntrospectProperties(INTROSPECT_PROPERTIES_EDEFAULT);
				return;
			case BeaninfoPackage.BEAN_DECORATOR__INTROSPECT_BEHAVIORS:
				setIntrospectBehaviors(INTROSPECT_BEHAVIORS_EDEFAULT);
				return;
			case BeaninfoPackage.BEAN_DECORATOR__INTROSPECT_EVENTS:
				setIntrospectEvents(INTROSPECT_EVENTS_EDEFAULT);
				return;
			case BeaninfoPackage.BEAN_DECORATOR__DO_BEANINFO:
				setDoBeaninfo(DO_BEANINFO_EDEFAULT);
				return;
			case BeaninfoPackage.BEAN_DECORATOR__CUSTOMIZER_CLASS:
				setCustomizerClass((JavaClass)null);
				return;
		}
		eDynamicUnset(eFeature);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean eIsSet(EStructuralFeature eFeature) {
		switch (eDerivedStructuralFeatureID(eFeature)) {
			case BeaninfoPackage.BEAN_DECORATOR__EANNOTATIONS:
				return eAnnotations != null && !eAnnotations.isEmpty();
			case BeaninfoPackage.BEAN_DECORATOR__SOURCE:
				return SOURCE_EDEFAULT == null ? source != null : !SOURCE_EDEFAULT.equals(source);
			case BeaninfoPackage.BEAN_DECORATOR__DETAILS:
				return details != null && !details.isEmpty();
			case BeaninfoPackage.BEAN_DECORATOR__EMODEL_ELEMENT:
				return getEModelElement() != null;
			case BeaninfoPackage.BEAN_DECORATOR__CONTENTS:
				return contents != null && !contents.isEmpty();
			case BeaninfoPackage.BEAN_DECORATOR__REFERENCES:
				return references != null && !references.isEmpty();
			case BeaninfoPackage.BEAN_DECORATOR__DISPLAY_NAME:
				return isSetDisplayName();
			case BeaninfoPackage.BEAN_DECORATOR__SHORT_DESCRIPTION:
				return isSetShortDescription();
			case BeaninfoPackage.BEAN_DECORATOR__CATEGORY:
				return CATEGORY_EDEFAULT == null ? category != null : !CATEGORY_EDEFAULT.equals(category);
			case BeaninfoPackage.BEAN_DECORATOR__EXPERT:
				return isSetExpert();
			case BeaninfoPackage.BEAN_DECORATOR__HIDDEN:
				return isSetHidden();
			case BeaninfoPackage.BEAN_DECORATOR__PREFERRED:
				return isSetPreferred();
			case BeaninfoPackage.BEAN_DECORATOR__MERGE_INTROSPECTION:
				return mergeIntrospection != MERGE_INTROSPECTION_EDEFAULT;
			case BeaninfoPackage.BEAN_DECORATOR__ATTRIBUTES_EXPLICIT:
				return attributesExplicit != ATTRIBUTES_EXPLICIT_EDEFAULT;
			case BeaninfoPackage.BEAN_DECORATOR__ATTRIBUTES:
				return attributes != null && !attributes.isEmpty();
			case BeaninfoPackage.BEAN_DECORATOR__MERGE_SUPER_PROPERTIES:
				return isSetMergeSuperProperties();
			case BeaninfoPackage.BEAN_DECORATOR__MERGE_SUPER_BEHAVIORS:
				return isSetMergeSuperBehaviors();
			case BeaninfoPackage.BEAN_DECORATOR__MERGE_SUPER_EVENTS:
				return isSetMergeSuperEvents();
			case BeaninfoPackage.BEAN_DECORATOR__INTROSPECT_PROPERTIES:
				return introspectProperties != INTROSPECT_PROPERTIES_EDEFAULT;
			case BeaninfoPackage.BEAN_DECORATOR__INTROSPECT_BEHAVIORS:
				return introspectBehaviors != INTROSPECT_BEHAVIORS_EDEFAULT;
			case BeaninfoPackage.BEAN_DECORATOR__INTROSPECT_EVENTS:
				return introspectEvents != INTROSPECT_EVENTS_EDEFAULT;
			case BeaninfoPackage.BEAN_DECORATOR__DO_BEANINFO:
				return doBeaninfo != DO_BEANINFO_EDEFAULT;
			case BeaninfoPackage.BEAN_DECORATOR__CUSTOMIZER_CLASS:
				return customizerClass != null;
		}
		return eDynamicIsSet(eFeature);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String toString() {
		if (eIsProxy()) return super.toString();

		StringBuffer result = new StringBuffer(super.toString());
		result.append(" (mergeSuperProperties: ");
		if (mergeSuperPropertiesESet) result.append(mergeSuperProperties); else result.append("<unset>");
		result.append(", mergeSuperBehaviors: ");
		if (mergeSuperBehaviorsESet) result.append(mergeSuperBehaviors); else result.append("<unset>");
		result.append(", mergeSuperEvents: ");
		if (mergeSuperEventsESet) result.append(mergeSuperEvents); else result.append("<unset>");
		result.append(", introspectProperties: ");
		result.append(introspectProperties);
		result.append(", introspectBehaviors: ");
		result.append(introspectBehaviors);
		result.append(", introspectEvents: ");
		result.append(introspectEvents);
		result.append(", doBeaninfo: ");
		result.append(doBeaninfo);
		result.append(')');
		return result.toString();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public JavaClass getCustomizerClassGen() {
		if (customizerClass != null && customizerClass.eIsProxy()) {
			JavaClass oldCustomizerClass = customizerClass;
			customizerClass = (JavaClass)eResolveProxy((InternalEObject)customizerClass);
			if (customizerClass != oldCustomizerClass) {
				if (eNotificationRequired())
					eNotify(new ENotificationImpl(this, Notification.RESOLVE, BeaninfoPackage.BEAN_DECORATOR__CUSTOMIZER_CLASS, oldCustomizerClass, customizerClass));
			}
		}
		return customizerClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public JavaClass basicGetCustomizerClass() {
		return customizerClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean isDoBeaninfo() {
		return doBeaninfo;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setDoBeaninfo(boolean newDoBeaninfo) {
		boolean oldDoBeaninfo = doBeaninfo;
		doBeaninfo = newDoBeaninfo;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, BeaninfoPackage.BEAN_DECORATOR__DO_BEANINFO, oldDoBeaninfo, doBeaninfo));
	}

	private URL iconURL;
	private boolean hasQueriedIconURL;
	public URL getIconURL(){
		if (!hasQueriedIconURL){
			Iterator featureAttributeValues = getAttributes().iterator();
			while(featureAttributeValues.hasNext()){
				FeatureAttributeValue value = (FeatureAttributeValue) featureAttributeValues.next();
				if (value.getName().equals("ICON_COLOR_16x16_URL")){ //$NON-NLS-1$
					// Get the value
					String urlString = ((IStringBeanProxy)value.getValueProxy()).stringValue();
					try {
						hasQueriedIconURL = true;
						iconURL = new URL(urlString);
					} catch (MalformedURLException exc ) {
						EclipseLogger.getLogger().log(exc, Level.WARNING);
					}
					break;					
				}
			}			
		}
		return iconURL;
	}
	private Map styleDetails;
	public Map getStyleDetails(){
		if(styleDetails == null){
			styleDetails = new HashMap();
			try{
				Iterator featureAttributeValues = getAttributes().iterator();
				while(featureAttributeValues.hasNext()){
					FeatureAttributeValue value = (FeatureAttributeValue) featureAttributeValues.next();
					if(value.getName().equals("SWEET_STYLEBITS")) { // $NON-NLS-1$
						IArrayBeanProxy outerArray  = (IArrayBeanProxy) value.getValueProxy();
						for (int i = 0; i < outerArray.getLength(); i++) {
							IArrayBeanProxy innerArray = (IArrayBeanProxy) outerArray.get(i);
							// The first element is a String
							String propertyName = ((IStringBeanProxy)innerArray.get(0)).stringValue();
							// The next is a three element array of name, initString, and actual value * n for the number of allowble values
							// Iterate over it to extract the names and strings and turn these into two separate String arrays
							IArrayBeanProxy triplicateArray = (IArrayBeanProxy)innerArray.get(1);
							int numberOfValues = triplicateArray.getLength()/3;
							String[] names = new String[numberOfValues];
							String[] initStrings = new String[numberOfValues];
							Integer[] values = new Integer[numberOfValues]; 
							for (int j = 0; j < triplicateArray.getLength(); j = j+3) {
								int index = j/3;
								names[index] = ((IStringBeanProxy)triplicateArray.get(j)).stringValue();
								initStrings[index] = ((IStringBeanProxy)triplicateArray.get(j+1)).stringValue();
								values[index] = new Integer(((IIntegerBeanProxy)triplicateArray.get(j+2)).intValue());
							}							
							styleDetails.put(propertyName, new Object[] { names, initStrings, values});
						}
					}
				}
			} catch (ThrowableProxy exc) {
				EclipseLogger.getLogger().log(exc, Level.WARNING);
			}
		}
		return styleDetails;
	}
}
