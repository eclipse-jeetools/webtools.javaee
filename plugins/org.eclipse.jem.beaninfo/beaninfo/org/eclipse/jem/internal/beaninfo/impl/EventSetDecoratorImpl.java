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
 *  $RCSfile: EventSetDecoratorImpl.java,v $
 *  $Revision: 1.1 $  $Date: 2003/10/27 17:17:59 $ 
 */


import java.util.Collection;
import java.util.Iterator;
import java.util.List;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EModelElement;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.EcorePackage;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.util.EObjectContainmentEList;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.emf.ecore.util.InternalEList;

import org.eclipse.jem.internal.beaninfo.BeaninfoFactory;
import org.eclipse.jem.internal.beaninfo.BeaninfoPackage;
import org.eclipse.jem.internal.beaninfo.EventSetDecorator;
import org.eclipse.jem.internal.beaninfo.FeatureAttributeValue;
import org.eclipse.jem.internal.beaninfo.MethodDecorator;
import org.eclipse.jem.internal.beaninfo.MethodProxy;
import org.eclipse.jem.internal.beaninfo.adapters.BeaninfoProxyConstants;
import org.eclipse.jem.internal.beaninfo.adapters.Utilities;
import org.eclipse.jem.internal.java.JavaClass;
import org.eclipse.jem.internal.java.JavaParameter;
import org.eclipse.jem.internal.java.Method;
import org.eclipse.jem.internal.java.impl.JavaClassImpl;
import org.eclipse.jem.internal.proxy.core.IArrayBeanProxy;
import org.eclipse.jem.internal.proxy.core.IBeanProxy;
import org.eclipse.jem.internal.proxy.core.IBeanTypeProxy;
import org.eclipse.jem.internal.proxy.core.IBooleanBeanProxy;
import org.eclipse.jem.internal.proxy.core.IMethodProxy;
import org.eclipse.jem.internal.proxy.core.IStringBeanProxy;
import org.eclipse.jem.internal.proxy.core.ThrowableProxy;
/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Event Set Decorator</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.eclipse.jem.internal.beaninfo.impl.EventSetDecoratorImpl#isInDefaultEventSet <em>In Default Event Set</em>}</li>
 *   <li>{@link org.eclipse.jem.internal.beaninfo.impl.EventSetDecoratorImpl#isUnicast <em>Unicast</em>}</li>
 *   <li>{@link org.eclipse.jem.internal.beaninfo.impl.EventSetDecoratorImpl#isListenerMethodsExplicit <em>Listener Methods Explicit</em>}</li>
 *   <li>{@link org.eclipse.jem.internal.beaninfo.impl.EventSetDecoratorImpl#getAddListenerMethod <em>Add Listener Method</em>}</li>
 *   <li>{@link org.eclipse.jem.internal.beaninfo.impl.EventSetDecoratorImpl#getListenerMethods <em>Listener Methods</em>}</li>
 *   <li>{@link org.eclipse.jem.internal.beaninfo.impl.EventSetDecoratorImpl#getListenerType <em>Listener Type</em>}</li>
 *   <li>{@link org.eclipse.jem.internal.beaninfo.impl.EventSetDecoratorImpl#getRemoveListenerMethod <em>Remove Listener Method</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */


public class EventSetDecoratorImpl extends FeatureDecoratorImpl implements EventSetDecorator{

	/**
	 * The default value of the '{@link #isInDefaultEventSet() <em>In Default Event Set</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isInDefaultEventSet()
	 * @generated
	 * @ordered
	 */
	protected static final boolean IN_DEFAULT_EVENT_SET_EDEFAULT = false;

	/**
	 * The cached value of the '{@link #isInDefaultEventSet() <em>In Default Event Set</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isInDefaultEventSet()
	 * @generated
	 * @ordered
	 */
	protected boolean inDefaultEventSet = IN_DEFAULT_EVENT_SET_EDEFAULT;

	/**
	 * This is true if the In Default Event Set attribute has been set.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	protected boolean inDefaultEventSetESet = false;

	/**
	 * The default value of the '{@link #isUnicast() <em>Unicast</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isUnicast()
	 * @generated
	 * @ordered
	 */
	protected static final boolean UNICAST_EDEFAULT = false;

	/**
	 * The cached value of the '{@link #isUnicast() <em>Unicast</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isUnicast()
	 * @generated
	 * @ordered
	 */
	protected boolean unicast = UNICAST_EDEFAULT;

	/**
	 * This is true if the Unicast attribute has been set.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	protected boolean unicastESet = false;

	/**
	 * The default value of the '{@link #isListenerMethodsExplicit() <em>Listener Methods Explicit</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isListenerMethodsExplicit()
	 * @generated
	 * @ordered
	 */
	protected static final boolean LISTENER_METHODS_EXPLICIT_EDEFAULT = false;

	/**
	 * The cached value of the '{@link #isListenerMethodsExplicit() <em>Listener Methods Explicit</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isListenerMethodsExplicit()
	 * @generated
	 * @ordered
	 */
	protected boolean listenerMethodsExplicit = LISTENER_METHODS_EXPLICIT_EDEFAULT;
	/**
	 * The cached value of the '{@link #getAddListenerMethod() <em>Add Listener Method</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getAddListenerMethod()
	 * @generated
	 * @ordered
	 */
	protected Method addListenerMethod = null;
	/**
	 * The cached value of the '{@link #getListenerMethods() <em>Listener Methods</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getListenerMethods()
	 * @generated
	 * @ordered
	 */
	protected EList listenerMethods = null;
	/**
	 * The cached value of the '{@link #getListenerType() <em>Listener Type</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getListenerType()
	 * @generated
	 * @ordered
	 */
	protected JavaClass listenerType = null;
	/**
	 * The cached value of the '{@link #getRemoveListenerMethod() <em>Remove Listener Method</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getRemoveListenerMethod()
	 * @generated
	 * @ordered
	 */
	protected Method removeListenerMethod = null;
	
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */	
	protected EventSetDecoratorImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected EClass eStaticClass() {
		return BeaninfoPackage.eINSTANCE.getEventSetDecorator();
	}

	public boolean isInDefaultEventSet() {	
		if (!isSetInDefaultEventSet())
			if (validProxy(fFeatureProxy)) {
				try {
					return ((IBooleanBeanProxy) BeaninfoProxyConstants.getConstants(fFeatureProxy.getProxyFactoryRegistry()).getIsInDefaultEventSetProxy().invoke(fFeatureProxy)).booleanValue();
				} catch (ThrowableProxy e) {
				}
			}
				
		return this.isInDefaultEventSetGen();
	}
	
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean isInDefaultEventSetGen() {
		return inDefaultEventSet;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setInDefaultEventSet(boolean newInDefaultEventSet) {
		boolean oldInDefaultEventSet = inDefaultEventSet;
		inDefaultEventSet = newInDefaultEventSet;
		boolean oldInDefaultEventSetESet = inDefaultEventSetESet;
		inDefaultEventSetESet = true;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, BeaninfoPackage.EVENT_SET_DECORATOR__IN_DEFAULT_EVENT_SET, oldInDefaultEventSet, inDefaultEventSet, !oldInDefaultEventSetESet));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void unsetInDefaultEventSet() {
		boolean oldInDefaultEventSet = inDefaultEventSet;
		boolean oldInDefaultEventSetESet = inDefaultEventSetESet;
		inDefaultEventSet = IN_DEFAULT_EVENT_SET_EDEFAULT;
		inDefaultEventSetESet = false;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.UNSET, BeaninfoPackage.EVENT_SET_DECORATOR__IN_DEFAULT_EVENT_SET, oldInDefaultEventSet, IN_DEFAULT_EVENT_SET_EDEFAULT, oldInDefaultEventSetESet));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean isSetInDefaultEventSet() {
		return inDefaultEventSetESet;
	}

	public boolean isUnicast() {	
		if (!isSetUnicast())
			if (validProxy(fFeatureProxy)) {
				try {
					return ((IBooleanBeanProxy) BeaninfoProxyConstants.getConstants(fFeatureProxy.getProxyFactoryRegistry()).getIsUnicastProxy().invoke(fFeatureProxy)).booleanValue();
				} catch (ThrowableProxy e) {
				}
			}
				
		return this.isUnicastGen();
	}
		
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean isUnicastGen() {
		return unicast;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setUnicast(boolean newUnicast) {
		boolean oldUnicast = unicast;
		unicast = newUnicast;
		boolean oldUnicastESet = unicastESet;
		unicastESet = true;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, BeaninfoPackage.EVENT_SET_DECORATOR__UNICAST, oldUnicast, unicast, !oldUnicastESet));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void unsetUnicast() {
		boolean oldUnicast = unicast;
		boolean oldUnicastESet = unicastESet;
		unicast = UNICAST_EDEFAULT;
		unicastESet = false;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.UNSET, BeaninfoPackage.EVENT_SET_DECORATOR__UNICAST, oldUnicast, UNICAST_EDEFAULT, oldUnicastESet));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean isSetUnicast() {
		return unicastESet;
	}

	public Method getAddListenerMethod() {	
		if (!eIsSet(BeaninfoPackage.eINSTANCE.getEventSetDecorator_AddListenerMethod()))
			if (validProxy(fFeatureProxy)) {
				try {
					return Utilities.getMethod((IMethodProxy) BeaninfoProxyConstants.getConstants(fFeatureProxy.getProxyFactoryRegistry()).getAddListenerMethodProxy().invoke(fFeatureProxy),  getEModelElement().eResource().getResourceSet());
				} catch (ThrowableProxy e) {
				}
			}
				
		return this.getAddListenerMethodGen();
	}
		
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Method getAddListenerMethodGen() {
		if (addListenerMethod != null && addListenerMethod.eIsProxy()) {
			Method oldAddListenerMethod = addListenerMethod;
			addListenerMethod = (Method)EcoreUtil.resolve(addListenerMethod, this);
			if (addListenerMethod != oldAddListenerMethod) {
				if (eNotificationRequired())
					eNotify(new ENotificationImpl(this, Notification.RESOLVE, BeaninfoPackage.EVENT_SET_DECORATOR__ADD_LISTENER_METHOD, oldAddListenerMethod, addListenerMethod));
			}
		}
		return addListenerMethod;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Method basicGetAddListenerMethod() {
		return addListenerMethod;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setAddListenerMethod(Method newAddListenerMethod) {
		Method oldAddListenerMethod = addListenerMethod;
		addListenerMethod = newAddListenerMethod;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, BeaninfoPackage.EVENT_SET_DECORATOR__ADD_LISTENER_METHOD, oldAddListenerMethod, addListenerMethod));
	}

	protected boolean retrievedListenerMethods;
	protected boolean retrievedListenerMethodsSuccessful; 
	
	public EList getListenerMethods() {	
		if (!isListenerMethodsExplicit()) {
			if (validProxy(fFeatureProxy) && !retrievedListenerMethods) {
				retrievedListenerMethods = true;
				EList methodsList = this.getListenerMethodsGen();
				try {
					BeaninfoProxyConstants constants = BeaninfoProxyConstants.getConstants(fFeatureProxy.getProxyFactoryRegistry());
					IArrayBeanProxy methodDescs = (IArrayBeanProxy) constants.getListenerMethodDescriptorsProxy().invoke(fFeatureProxy);
					if (methodDescs != null) {
						ResourceSet rset = getEModelElement().eResource().getResourceSet();
						int methodDescsLength = methodDescs.getLength();
						BeaninfoFactory bfact = BeaninfoFactory.eINSTANCE;
						for (int i=0; i<methodDescsLength; i++) {
							IBeanProxy mthdDesc = methodDescs.get(i);
							// First find the Method from the descriptor
							URI uri = Utilities.getMethodURI((IMethodProxy) constants.getMethodProxy().invokeCatchThrowableExceptions(mthdDesc));
							Method method = (Method) rset.getEObject(uri, true);	// In the V5 release, this poofs one up, even if it didn't exist, however, since we are getting one from the remote vm, if should exist here too.
							// We need a method proxy, and a method decorator.
							MethodProxy mproxy = bfact.createMethodProxy();
							mproxy.setMethod(method);
							mproxy.setName(method.getName());
							MethodDecorator md = bfact.createMethodDecorator();
							md.setImplicitlyCreated(IMPLICIT_DECORATOR_AND_FEATURE);
							md.setDescriptorProxy(mthdDesc);
							md.setEModelElement(mproxy);
							methodsList.add(mproxy);
						}
						retrievedListenerMethodsSuccessful = true;
						return methodsList;
					}		
				} catch (ThrowableProxy e) {
				};
			}
			
			if (retrievedListenerMethodsSuccessful)
				return this.getListenerMethodsGen();	// Built once from proxy, use it always.
			else
				return createDefaultListenerMethodsList();	// Not explicit and not sucessful retrieval, use default.
		}
		return this.getListenerMethodsGen();
	}
	
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList getListenerMethodsGen() {
		if (listenerMethods == null) {
			listenerMethods = new EObjectContainmentEList(MethodProxy.class, this, BeaninfoPackage.EVENT_SET_DECORATOR__LISTENER_METHODS);
		}
		return listenerMethods;
	}

	/**
	 * This is called if method listeners list not explicitly set and there is no feature proxy or
	 * there is a feature proxy and the proxy has nothing defined.
	 */
	protected EList createDefaultListenerMethodsList() {
		EList mthdsList = this.getListenerMethodsGen();		
		if (!eIsSet(EcorePackage.eINSTANCE.getEAnnotation_EModelElement()))
			return mthdsList;	// We are not attached, can't determine the list yet.
			
		retrievedListenerMethods = retrievedListenerMethodsSuccessful = true;
		JavaClass eventObjectClass = (JavaClass) JavaClassImpl.reflect("java.util.EventObject", getEModelElement().eResource().getResourceSet()); //$NON-NLS-1$
		
		mthdsList.clear();
		
		// This is a little tricky. Need to get the methods for the listener type, and
		// then go through the methods and filter out the non-event ones.
		JavaClass lt = getListenerType();
		if (lt == null)
			return mthdsList;	// Couldn't get the listener type for some reason, so leave as is.
		
		BeaninfoFactory bfact = BeaninfoFactory.eINSTANCE;
		List ms = lt.getPublicMethodsExtended();
		int msize = ms.size();
		for (int i=0; i<msize; i++) {
			Method method = (Method) ms.get(i);
			List parms = method.getParameters();
			if (parms.size() != 1)
				continue;	// Must have only one parm.
			if (!eventObjectClass.isAssignableFrom(((JavaParameter) parms.get(0)).getEType()))
				continue;	// Parm does not inherit from java.util.EventObject
				
			// We need a method proxy, and a method decorator.
			MethodProxy mproxy = bfact.createMethodProxy();
			mproxy.setMethod(method);
			mproxy.setName(method.getName());			
			MethodDecorator md = bfact.createMethodDecorator();
			md.setImplicitlyCreated(IMPLICIT_DECORATOR_AND_FEATURE);
			md.setEModelElement(mproxy);			
			mthdsList.add(mproxy);
		}
		return mthdsList;
	}	
	
	public JavaClass getListenerType() {	
		if (!eIsSet(BeaninfoPackage.eINSTANCE.getEventSetDecorator_ListenerType()))
			if (validProxy(fFeatureProxy)) {
				try {
					return (JavaClass) Utilities.getJavaClass((IBeanTypeProxy) BeaninfoProxyConstants.getConstants(fFeatureProxy.getProxyFactoryRegistry()).getListenerTypeProxy().invoke(fFeatureProxy),  getEModelElement().eResource().getResourceSet());
				} catch (ThrowableProxy e) {
				}
			}
				
		return this.getListenerTypeGen();
	}
	
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public JavaClass getListenerTypeGen() {
		if (listenerType != null && listenerType.eIsProxy()) {
			JavaClass oldListenerType = listenerType;
			listenerType = (JavaClass)EcoreUtil.resolve(listenerType, this);
			if (listenerType != oldListenerType) {
				if (eNotificationRequired())
					eNotify(new ENotificationImpl(this, Notification.RESOLVE, BeaninfoPackage.EVENT_SET_DECORATOR__LISTENER_TYPE, oldListenerType, listenerType));
			}
		}
		return listenerType;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public JavaClass basicGetListenerType() {
		return listenerType;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setListenerType(JavaClass newListenerType) {
		JavaClass oldListenerType = listenerType;
		listenerType = newListenerType;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, BeaninfoPackage.EVENT_SET_DECORATOR__LISTENER_TYPE, oldListenerType, listenerType));
	}

	public Method getRemoveListenerMethod() {	
		if (!eIsSet(BeaninfoPackage.eINSTANCE.getEventSetDecorator_RemoveListenerMethod()))
			if (validProxy(fFeatureProxy)) {
				try {
					return Utilities.getMethod((IMethodProxy) BeaninfoProxyConstants.getConstants(fFeatureProxy.getProxyFactoryRegistry()).getRemoveListenerMethodProxy().invoke(fFeatureProxy),  getEModelElement().eResource().getResourceSet());
				} catch (ThrowableProxy e) {
				}
			}
				
		return this.getRemoveListenerMethodGen();
	}
		
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Method getRemoveListenerMethodGen() {
		if (removeListenerMethod != null && removeListenerMethod.eIsProxy()) {
			Method oldRemoveListenerMethod = removeListenerMethod;
			removeListenerMethod = (Method)EcoreUtil.resolve(removeListenerMethod, this);
			if (removeListenerMethod != oldRemoveListenerMethod) {
				if (eNotificationRequired())
					eNotify(new ENotificationImpl(this, Notification.RESOLVE, BeaninfoPackage.EVENT_SET_DECORATOR__REMOVE_LISTENER_METHOD, oldRemoveListenerMethod, removeListenerMethod));
			}
		}
		return removeListenerMethod;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Method basicGetRemoveListenerMethod() {
		return removeListenerMethod;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setRemoveListenerMethod(Method newRemoveListenerMethod) {
		Method oldRemoveListenerMethod = removeListenerMethod;
		removeListenerMethod = newRemoveListenerMethod;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, BeaninfoPackage.EVENT_SET_DECORATOR__REMOVE_LISTENER_METHOD, oldRemoveListenerMethod, removeListenerMethod));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain eInverseAdd(InternalEObject otherEnd, int featureID, Class baseClass, NotificationChain msgs) {
		if (featureID >= 0) {
			switch (eDerivedStructuralFeatureID(featureID, baseClass)) {
				case BeaninfoPackage.EVENT_SET_DECORATOR__EANNOTATIONS:
					return ((InternalEList)getEAnnotations()).basicAdd(otherEnd, msgs);
				case BeaninfoPackage.EVENT_SET_DECORATOR__EMODEL_ELEMENT:
					if (eContainer != null)
						msgs = eBasicRemoveFromContainer(msgs);
					return eBasicSetContainer(otherEnd, BeaninfoPackage.EVENT_SET_DECORATOR__EMODEL_ELEMENT, msgs);
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
				case BeaninfoPackage.EVENT_SET_DECORATOR__EANNOTATIONS:
					return ((InternalEList)getEAnnotations()).basicRemove(otherEnd, msgs);
				case BeaninfoPackage.EVENT_SET_DECORATOR__DETAILS:
					return ((InternalEList)getDetails()).basicRemove(otherEnd, msgs);
				case BeaninfoPackage.EVENT_SET_DECORATOR__EMODEL_ELEMENT:
					return eBasicSetContainer(null, BeaninfoPackage.EVENT_SET_DECORATOR__EMODEL_ELEMENT, msgs);
				case BeaninfoPackage.EVENT_SET_DECORATOR__CONTENTS:
					return ((InternalEList)getContents()).basicRemove(otherEnd, msgs);
				case BeaninfoPackage.EVENT_SET_DECORATOR__ATTRIBUTES:
					return ((InternalEList)getAttributes()).basicRemove(otherEnd, msgs);
				case BeaninfoPackage.EVENT_SET_DECORATOR__LISTENER_METHODS:
					return ((InternalEList)getListenerMethods()).basicRemove(otherEnd, msgs);
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
				case BeaninfoPackage.EVENT_SET_DECORATOR__EMODEL_ELEMENT:
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
			case BeaninfoPackage.EVENT_SET_DECORATOR__EANNOTATIONS:
				return getEAnnotations();
			case BeaninfoPackage.EVENT_SET_DECORATOR__SOURCE:
				return getSource();
			case BeaninfoPackage.EVENT_SET_DECORATOR__DETAILS:
				return getDetails();
			case BeaninfoPackage.EVENT_SET_DECORATOR__EMODEL_ELEMENT:
				return getEModelElement();
			case BeaninfoPackage.EVENT_SET_DECORATOR__CONTENTS:
				return getContents();
			case BeaninfoPackage.EVENT_SET_DECORATOR__REFERENCES:
				return getReferences();
			case BeaninfoPackage.EVENT_SET_DECORATOR__DISPLAY_NAME:
				return getDisplayName();
			case BeaninfoPackage.EVENT_SET_DECORATOR__SHORT_DESCRIPTION:
				return getShortDescription();
			case BeaninfoPackage.EVENT_SET_DECORATOR__CATEGORY:
				return getCategory();
			case BeaninfoPackage.EVENT_SET_DECORATOR__EXPERT:
				return isExpert() ? Boolean.TRUE : Boolean.FALSE;
			case BeaninfoPackage.EVENT_SET_DECORATOR__HIDDEN:
				return isHidden() ? Boolean.TRUE : Boolean.FALSE;
			case BeaninfoPackage.EVENT_SET_DECORATOR__PREFERRED:
				return isPreferred() ? Boolean.TRUE : Boolean.FALSE;
			case BeaninfoPackage.EVENT_SET_DECORATOR__MERGE_INTROSPECTION:
				return isMergeIntrospection() ? Boolean.TRUE : Boolean.FALSE;
			case BeaninfoPackage.EVENT_SET_DECORATOR__ATTRIBUTES_EXPLICIT:
				return isAttributesExplicit() ? Boolean.TRUE : Boolean.FALSE;
			case BeaninfoPackage.EVENT_SET_DECORATOR__ATTRIBUTES:
				return getAttributes();
			case BeaninfoPackage.EVENT_SET_DECORATOR__IN_DEFAULT_EVENT_SET:
				return isInDefaultEventSet() ? Boolean.TRUE : Boolean.FALSE;
			case BeaninfoPackage.EVENT_SET_DECORATOR__UNICAST:
				return isUnicast() ? Boolean.TRUE : Boolean.FALSE;
			case BeaninfoPackage.EVENT_SET_DECORATOR__LISTENER_METHODS_EXPLICIT:
				return isListenerMethodsExplicit() ? Boolean.TRUE : Boolean.FALSE;
			case BeaninfoPackage.EVENT_SET_DECORATOR__ADD_LISTENER_METHOD:
				if (resolve) return getAddListenerMethod();
				return basicGetAddListenerMethod();
			case BeaninfoPackage.EVENT_SET_DECORATOR__LISTENER_METHODS:
				return getListenerMethods();
			case BeaninfoPackage.EVENT_SET_DECORATOR__LISTENER_TYPE:
				if (resolve) return getListenerType();
				return basicGetListenerType();
			case BeaninfoPackage.EVENT_SET_DECORATOR__REMOVE_LISTENER_METHOD:
				if (resolve) return getRemoveListenerMethod();
				return basicGetRemoveListenerMethod();
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
			case BeaninfoPackage.EVENT_SET_DECORATOR__EANNOTATIONS:
				getEAnnotations().clear();
				getEAnnotations().addAll((Collection)newValue);
				return;
			case BeaninfoPackage.EVENT_SET_DECORATOR__SOURCE:
				setSource((String)newValue);
				return;
			case BeaninfoPackage.EVENT_SET_DECORATOR__DETAILS:
				getDetails().clear();
				getDetails().addAll((Collection)newValue);
				return;
			case BeaninfoPackage.EVENT_SET_DECORATOR__EMODEL_ELEMENT:
				setEModelElement((EModelElement)newValue);
				return;
			case BeaninfoPackage.EVENT_SET_DECORATOR__CONTENTS:
				getContents().clear();
				getContents().addAll((Collection)newValue);
				return;
			case BeaninfoPackage.EVENT_SET_DECORATOR__REFERENCES:
				getReferences().clear();
				getReferences().addAll((Collection)newValue);
				return;
			case BeaninfoPackage.EVENT_SET_DECORATOR__DISPLAY_NAME:
				setDisplayName((String)newValue);
				return;
			case BeaninfoPackage.EVENT_SET_DECORATOR__SHORT_DESCRIPTION:
				setShortDescription((String)newValue);
				return;
			case BeaninfoPackage.EVENT_SET_DECORATOR__CATEGORY:
				setCategory((String)newValue);
				return;
			case BeaninfoPackage.EVENT_SET_DECORATOR__EXPERT:
				setExpert(((Boolean)newValue).booleanValue());
				return;
			case BeaninfoPackage.EVENT_SET_DECORATOR__HIDDEN:
				setHidden(((Boolean)newValue).booleanValue());
				return;
			case BeaninfoPackage.EVENT_SET_DECORATOR__PREFERRED:
				setPreferred(((Boolean)newValue).booleanValue());
				return;
			case BeaninfoPackage.EVENT_SET_DECORATOR__MERGE_INTROSPECTION:
				setMergeIntrospection(((Boolean)newValue).booleanValue());
				return;
			case BeaninfoPackage.EVENT_SET_DECORATOR__ATTRIBUTES_EXPLICIT:
				setAttributesExplicit(((Boolean)newValue).booleanValue());
				return;
			case BeaninfoPackage.EVENT_SET_DECORATOR__ATTRIBUTES:
				getAttributes().clear();
				getAttributes().addAll((Collection)newValue);
				return;
			case BeaninfoPackage.EVENT_SET_DECORATOR__IN_DEFAULT_EVENT_SET:
				setInDefaultEventSet(((Boolean)newValue).booleanValue());
				return;
			case BeaninfoPackage.EVENT_SET_DECORATOR__UNICAST:
				setUnicast(((Boolean)newValue).booleanValue());
				return;
			case BeaninfoPackage.EVENT_SET_DECORATOR__LISTENER_METHODS_EXPLICIT:
				setListenerMethodsExplicit(((Boolean)newValue).booleanValue());
				return;
			case BeaninfoPackage.EVENT_SET_DECORATOR__ADD_LISTENER_METHOD:
				setAddListenerMethod((Method)newValue);
				return;
			case BeaninfoPackage.EVENT_SET_DECORATOR__LISTENER_METHODS:
				getListenerMethods().clear();
				getListenerMethods().addAll((Collection)newValue);
				return;
			case BeaninfoPackage.EVENT_SET_DECORATOR__LISTENER_TYPE:
				setListenerType((JavaClass)newValue);
				return;
			case BeaninfoPackage.EVENT_SET_DECORATOR__REMOVE_LISTENER_METHOD:
				setRemoveListenerMethod((Method)newValue);
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
			case BeaninfoPackage.EVENT_SET_DECORATOR__EANNOTATIONS:
				getEAnnotations().clear();
				return;
			case BeaninfoPackage.EVENT_SET_DECORATOR__SOURCE:
				setSource(SOURCE_EDEFAULT);
				return;
			case BeaninfoPackage.EVENT_SET_DECORATOR__DETAILS:
				getDetails().clear();
				return;
			case BeaninfoPackage.EVENT_SET_DECORATOR__EMODEL_ELEMENT:
				setEModelElement((EModelElement)null);
				return;
			case BeaninfoPackage.EVENT_SET_DECORATOR__CONTENTS:
				getContents().clear();
				return;
			case BeaninfoPackage.EVENT_SET_DECORATOR__REFERENCES:
				getReferences().clear();
				return;
			case BeaninfoPackage.EVENT_SET_DECORATOR__DISPLAY_NAME:
				unsetDisplayName();
				return;
			case BeaninfoPackage.EVENT_SET_DECORATOR__SHORT_DESCRIPTION:
				unsetShortDescription();
				return;
			case BeaninfoPackage.EVENT_SET_DECORATOR__CATEGORY:
				setCategory(CATEGORY_EDEFAULT);
				return;
			case BeaninfoPackage.EVENT_SET_DECORATOR__EXPERT:
				unsetExpert();
				return;
			case BeaninfoPackage.EVENT_SET_DECORATOR__HIDDEN:
				unsetHidden();
				return;
			case BeaninfoPackage.EVENT_SET_DECORATOR__PREFERRED:
				unsetPreferred();
				return;
			case BeaninfoPackage.EVENT_SET_DECORATOR__MERGE_INTROSPECTION:
				setMergeIntrospection(MERGE_INTROSPECTION_EDEFAULT);
				return;
			case BeaninfoPackage.EVENT_SET_DECORATOR__ATTRIBUTES_EXPLICIT:
				setAttributesExplicit(ATTRIBUTES_EXPLICIT_EDEFAULT);
				return;
			case BeaninfoPackage.EVENT_SET_DECORATOR__ATTRIBUTES:
				getAttributes().clear();
				return;
			case BeaninfoPackage.EVENT_SET_DECORATOR__IN_DEFAULT_EVENT_SET:
				unsetInDefaultEventSet();
				return;
			case BeaninfoPackage.EVENT_SET_DECORATOR__UNICAST:
				unsetUnicast();
				return;
			case BeaninfoPackage.EVENT_SET_DECORATOR__LISTENER_METHODS_EXPLICIT:
				setListenerMethodsExplicit(LISTENER_METHODS_EXPLICIT_EDEFAULT);
				return;
			case BeaninfoPackage.EVENT_SET_DECORATOR__ADD_LISTENER_METHOD:
				setAddListenerMethod((Method)null);
				return;
			case BeaninfoPackage.EVENT_SET_DECORATOR__LISTENER_METHODS:
				getListenerMethods().clear();
				return;
			case BeaninfoPackage.EVENT_SET_DECORATOR__LISTENER_TYPE:
				setListenerType((JavaClass)null);
				return;
			case BeaninfoPackage.EVENT_SET_DECORATOR__REMOVE_LISTENER_METHOD:
				setRemoveListenerMethod((Method)null);
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
			case BeaninfoPackage.EVENT_SET_DECORATOR__EANNOTATIONS:
				return eAnnotations != null && !eAnnotations.isEmpty();
			case BeaninfoPackage.EVENT_SET_DECORATOR__SOURCE:
				return SOURCE_EDEFAULT == null ? source != null : !SOURCE_EDEFAULT.equals(source);
			case BeaninfoPackage.EVENT_SET_DECORATOR__DETAILS:
				return details != null && !details.isEmpty();
			case BeaninfoPackage.EVENT_SET_DECORATOR__EMODEL_ELEMENT:
				return getEModelElement() != null;
			case BeaninfoPackage.EVENT_SET_DECORATOR__CONTENTS:
				return contents != null && !contents.isEmpty();
			case BeaninfoPackage.EVENT_SET_DECORATOR__REFERENCES:
				return references != null && !references.isEmpty();
			case BeaninfoPackage.EVENT_SET_DECORATOR__DISPLAY_NAME:
				return isSetDisplayName();
			case BeaninfoPackage.EVENT_SET_DECORATOR__SHORT_DESCRIPTION:
				return isSetShortDescription();
			case BeaninfoPackage.EVENT_SET_DECORATOR__CATEGORY:
				return CATEGORY_EDEFAULT == null ? category != null : !CATEGORY_EDEFAULT.equals(category);
			case BeaninfoPackage.EVENT_SET_DECORATOR__EXPERT:
				return isSetExpert();
			case BeaninfoPackage.EVENT_SET_DECORATOR__HIDDEN:
				return isSetHidden();
			case BeaninfoPackage.EVENT_SET_DECORATOR__PREFERRED:
				return isSetPreferred();
			case BeaninfoPackage.EVENT_SET_DECORATOR__MERGE_INTROSPECTION:
				return mergeIntrospection != MERGE_INTROSPECTION_EDEFAULT;
			case BeaninfoPackage.EVENT_SET_DECORATOR__ATTRIBUTES_EXPLICIT:
				return attributesExplicit != ATTRIBUTES_EXPLICIT_EDEFAULT;
			case BeaninfoPackage.EVENT_SET_DECORATOR__ATTRIBUTES:
				return attributes != null && !attributes.isEmpty();
			case BeaninfoPackage.EVENT_SET_DECORATOR__IN_DEFAULT_EVENT_SET:
				return isSetInDefaultEventSet();
			case BeaninfoPackage.EVENT_SET_DECORATOR__UNICAST:
				return isSetUnicast();
			case BeaninfoPackage.EVENT_SET_DECORATOR__LISTENER_METHODS_EXPLICIT:
				return listenerMethodsExplicit != LISTENER_METHODS_EXPLICIT_EDEFAULT;
			case BeaninfoPackage.EVENT_SET_DECORATOR__ADD_LISTENER_METHOD:
				return addListenerMethod != null;
			case BeaninfoPackage.EVENT_SET_DECORATOR__LISTENER_METHODS:
				return listenerMethods != null && !listenerMethods.isEmpty();
			case BeaninfoPackage.EVENT_SET_DECORATOR__LISTENER_TYPE:
				return listenerType != null;
			case BeaninfoPackage.EVENT_SET_DECORATOR__REMOVE_LISTENER_METHOD:
				return removeListenerMethod != null;
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
		result.append(" (inDefaultEventSet: ");
		if (inDefaultEventSetESet) result.append(inDefaultEventSet); else result.append("<unset>");
		result.append(", unicast: ");
		if (unicastESet) result.append(unicast); else result.append("<unset>");
		result.append(", listenerMethodsExplicit: ");
		result.append(listenerMethodsExplicit);
		result.append(')');
		return result.toString();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean isListenerMethodsExplicit() {
		return listenerMethodsExplicit;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setListenerMethodsExplicit(boolean newListenerMethodsExplicit) {
		boolean oldListenerMethodsExplicit = listenerMethodsExplicit;
		listenerMethodsExplicit = newListenerMethodsExplicit;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, BeaninfoPackage.EVENT_SET_DECORATOR__LISTENER_METHODS_EXPLICIT, oldListenerMethodsExplicit, listenerMethodsExplicit));
	}

	public void setDescriptorProxy(IBeanProxy descriptorProxy) {
		if (retrievedListenerMethods) {
			this.getListenerMethodsGen().clear();
			retrievedListenerMethods = retrievedListenerMethodsSuccessful = false;
		}
		super.setDescriptorProxy(descriptorProxy);
	}	

	/**
	 * For some listener interfaces an adapter class is provided that implements default no-op methods, e.g.
	 * java.awt.event.FocusEvent which has java.awt.event.FocusAdapter.
	 * The Adapter class is provided in a key/value pair on the java.beans.EventSetDescriptor with a key 
	 * defined in a static final constants EVENTADAPTERCLASS = "eventAdapterClass";
	 */	
	public JavaClass getEventAdapterClass(){
		
		Iterator iter = getAttributes().iterator();
		while(iter.hasNext()){
			FeatureAttributeValue featureAttribute = (FeatureAttributeValue)iter.next();
			if ( EventSetDecorator.EVENTADAPTERCLASS.equals(featureAttribute.getName()) ){
				String adapterClassName = ((IStringBeanProxy)featureAttribute.getValueProxy()).stringValue();
				return (JavaClass) Utilities.getJavaClass(adapterClassName,eResource().getResourceSet());
			} 
		}
		return null;	
	}
}
