/*******************************************************************************
 * Copyright (c) 2001, 2004 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Common Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/cpl-v10.html
 * 
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *******************************************************************************/
package org.eclipse.jem.internal.beaninfo.impl;
/*
 *  $RCSfile: EventSetDecoratorImpl.java,v $
 *  $Revision: 1.8 $  $Date: 2005/02/04 23:11:53 $ 
 */


import java.util.Collection;
import java.util.List;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EModelElement;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.EcorePackage;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.util.EObjectContainmentEList;
import org.eclipse.emf.ecore.util.InternalEList;

import org.eclipse.jem.internal.beaninfo.BeaninfoPackage;
import org.eclipse.jem.internal.beaninfo.EventSetDecorator;
import org.eclipse.jem.internal.beaninfo.ImplicitItem;
import org.eclipse.jem.internal.beaninfo.MethodProxy;

import org.eclipse.jem.java.JavaClass;
import org.eclipse.jem.java.Method;

import org.eclipse.jem.internal.beaninfo.*;
import org.eclipse.jem.java.*;
/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Event Set Decorator</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.eclipse.jem.internal.beaninfo.impl.EventSetDecoratorImpl#isInDefaultEventSet <em>In Default Event Set</em>}</li>
 *   <li>{@link org.eclipse.jem.internal.beaninfo.impl.EventSetDecoratorImpl#isUnicast <em>Unicast</em>}</li>
 *   <li>{@link org.eclipse.jem.internal.beaninfo.impl.EventSetDecoratorImpl#isListenerMethodsExplicitEmpty <em>Listener Methods Explicit Empty</em>}</li>
 *   <li>{@link org.eclipse.jem.internal.beaninfo.impl.EventSetDecoratorImpl#getAddListenerMethod <em>Add Listener Method</em>}</li>
 *   <li>{@link org.eclipse.jem.internal.beaninfo.impl.EventSetDecoratorImpl#getListenerMethods <em>Listener Methods</em>}</li>
 *   <li>{@link org.eclipse.jem.internal.beaninfo.impl.EventSetDecoratorImpl#getListenerType <em>Listener Type</em>}</li>
 *   <li>{@link org.eclipse.jem.internal.beaninfo.impl.EventSetDecoratorImpl#getRemoveListenerMethod <em>Remove Listener Method</em>}</li>
 *   <li>{@link org.eclipse.jem.internal.beaninfo.impl.EventSetDecoratorImpl#getEventAdapterClass <em>Event Adapter Class</em>}</li>
 *   <li>{@link org.eclipse.jem.internal.beaninfo.impl.EventSetDecoratorImpl#getSerListMthd <em>Ser List Mthd</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */


public class EventSetDecoratorImpl extends FeatureDecoratorImpl implements EventSetDecorator{
	
	/**
	 * Bits for implicitly set features. This is internal, not meant for clients.
	 */
	public static final long EVENT_ADDLISTENERMETHOD_IMPLICIT = 0x1L;
	public static final long EVENT_ADAPTERCLASS_IMPLICIT = 0x2L;
	public static final long EVENT_LISTENERMETHODS_IMPLICIT = 0x4L;
	public static final long EVENT_LISTENERTYPE_IMPLICIT = 0x8L;
	public static final long EVENT_REMOVELISTENERMETHOD_IMPLICIT = 0x10L;
	public static final long EVENT_DEFAULTEVENTSET_IMPLICIT = 0x20L;
	public static final long EVENT_UNICAST_IMPLICIT = 0x40L;
	public static final long EVENT_LISTENERMETHODS_DEFAULT = 0x80L;


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
	 * The default value of the '{@link #isListenerMethodsExplicitEmpty() <em>Listener Methods Explicit Empty</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isListenerMethodsExplicitEmpty()
	 * @generated
	 * @ordered
	 */
	protected static final boolean LISTENER_METHODS_EXPLICIT_EMPTY_EDEFAULT = false;

	/**
	 * The cached value of the '{@link #isListenerMethodsExplicitEmpty() <em>Listener Methods Explicit Empty</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isListenerMethodsExplicitEmpty()
	 * @generated
	 * @ordered
	 */
	protected boolean listenerMethodsExplicitEmpty = LISTENER_METHODS_EXPLICIT_EMPTY_EDEFAULT;

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
	 * The cached value of the '{@link #getEventAdapterClass() <em>Event Adapter Class</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getEventAdapterClass()
	 * @generated
	 * @ordered
	 */
	protected JavaClass eventAdapterClass = null;

	/**
	 * The cached value of the '{@link #getSerListMthd() <em>Ser List Mthd</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getSerListMthd()
	 * @generated
	 * @ordered
	 */
	protected EList serListMthd = null;

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

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean isInDefaultEventSet() {
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

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean isUnicast() {
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

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean isListenerMethodsExplicitEmpty() {
		return listenerMethodsExplicitEmpty;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setListenerMethodsExplicitEmpty(boolean newListenerMethodsExplicitEmpty) {
		boolean oldListenerMethodsExplicitEmpty = listenerMethodsExplicitEmpty;
		listenerMethodsExplicitEmpty = newListenerMethodsExplicitEmpty;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, BeaninfoPackage.EVENT_SET_DECORATOR__LISTENER_METHODS_EXPLICIT_EMPTY, oldListenerMethodsExplicitEmpty, listenerMethodsExplicitEmpty));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Method getAddListenerMethod() {
		if (addListenerMethod != null && addListenerMethod.eIsProxy()) {
			Method oldAddListenerMethod = addListenerMethod;
			addListenerMethod = (Method)eResolveProxy((InternalEObject)addListenerMethod);
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

	public EList getListenerMethods() {
		if (!isListenerMethodsExplicitEmpty() && getSerListMthd().isEmpty() && (getImplicitlySetBits()&(EVENT_LISTENERMETHODS_IMPLICIT | EVENT_LISTENERMETHODS_DEFAULT)) == 0) {
			// Not explicitly empty, it is empty, and we have not implicitly or by defaults made it empty, then create the defaults.
			createDefaultListenerMethodsList();
		}
		return getSerListMthd();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList getListenerMethodsGen() {
		// TODO: implement this method to return the 'Listener Methods' reference list
		// Ensure that you remove @generated or mark it @generated NOT
		throw new UnsupportedOperationException();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public JavaClass getListenerType() {
		if (listenerType != null && listenerType.eIsProxy()) {
			JavaClass oldListenerType = listenerType;
			listenerType = (JavaClass)eResolveProxy((InternalEObject)listenerType);
			if (listenerType != oldListenerType) {
				if (eNotificationRequired())
					eNotify(new ENotificationImpl(this, Notification.RESOLVE, BeaninfoPackage.EVENT_SET_DECORATOR__LISTENER_TYPE, oldListenerType, listenerType));
			}
		}
		return listenerType;
	}

	/**
	 * This is called if method listeners list not explicitly set and there is no feature proxy or
	 * there is a feature proxy and the proxy has nothing defined.
	 * <p>
	 * This is an internal method used by BeanInfo classes. It is not to be used by clients.
	 */
	public EList createDefaultListenerMethodsList() {
		if (!eIsSet(EcorePackage.eINSTANCE.getEAnnotation_EModelElement()))
			return this.getSerListMthd();	// We are not attached, can't determine the list yet.
		EList mthdsList = this.getSerListMthd();		
			
		JavaClass eventObjectClass = (JavaClass) JavaRefFactory.eINSTANCE.reflectType("java.util.EventObject", getEModelElement().eResource().getResourceSet()); //$NON-NLS-1$
		
		mthdsList.clear();
		
		// This is a little tricky. Need to get the methods for the listener type, and
		// then go through the methods and filter out the non-event ones.
		JavaClass lt = getListenerType();
		if (lt == null)
			return mthdsList;	// Couldn't get the listener type for some reason, so leave as is.
		setImplicitlySetBits(getImplicitlySetBits()|EVENT_LISTENERMETHODS_DEFAULT);	// Mark as we implicitly filled it in.
		
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
			md.setImplicitDecoratorFlag(ImplicitItem.IMPLICIT_DECORATOR_AND_FEATURE_LITERAL);
			md.setEModelElement(mproxy);			
			mthdsList.add(mproxy);
		}
		return mthdsList;
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

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Method getRemoveListenerMethod() {
		if (removeListenerMethod != null && removeListenerMethod.eIsProxy()) {
			Method oldRemoveListenerMethod = removeListenerMethod;
			removeListenerMethod = (Method)eResolveProxy((InternalEObject)removeListenerMethod);
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
	public JavaClass getEventAdapterClass() {
		if (eventAdapterClass != null && eventAdapterClass.eIsProxy()) {
			JavaClass oldEventAdapterClass = eventAdapterClass;
			eventAdapterClass = (JavaClass)eResolveProxy((InternalEObject)eventAdapterClass);
			if (eventAdapterClass != oldEventAdapterClass) {
				if (eNotificationRequired())
					eNotify(new ENotificationImpl(this, Notification.RESOLVE, BeaninfoPackage.EVENT_SET_DECORATOR__EVENT_ADAPTER_CLASS, oldEventAdapterClass, eventAdapterClass));
			}
		}
		return eventAdapterClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public JavaClass basicGetEventAdapterClass() {
		return eventAdapterClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setEventAdapterClass(JavaClass newEventAdapterClass) {
		JavaClass oldEventAdapterClass = eventAdapterClass;
		eventAdapterClass = newEventAdapterClass;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, BeaninfoPackage.EVENT_SET_DECORATOR__EVENT_ADAPTER_CLASS, oldEventAdapterClass, eventAdapterClass));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList getSerListMthd() {
		if (serListMthd == null) {
			serListMthd = new EObjectContainmentEList(MethodProxy.class, this, BeaninfoPackage.EVENT_SET_DECORATOR__SER_LIST_MTHD);
		}
		return serListMthd;
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
				case BeaninfoPackage.EVENT_SET_DECORATOR__SER_LIST_MTHD:
					return ((InternalEList)getSerListMthd()).basicRemove(otherEnd, msgs);
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
			case BeaninfoPackage.EVENT_SET_DECORATOR__ATTRIBUTES_EXPLICIT_EMPTY:
				return isAttributesExplicitEmpty() ? Boolean.TRUE : Boolean.FALSE;
			case BeaninfoPackage.EVENT_SET_DECORATOR__IMPLICITLY_SET_BITS:
				return new Long(getImplicitlySetBits());
			case BeaninfoPackage.EVENT_SET_DECORATOR__IMPLICIT_DECORATOR_FLAG:
				return getImplicitDecoratorFlag();
			case BeaninfoPackage.EVENT_SET_DECORATOR__ATTRIBUTES:
				return getAttributes();
			case BeaninfoPackage.EVENT_SET_DECORATOR__IN_DEFAULT_EVENT_SET:
				return isInDefaultEventSet() ? Boolean.TRUE : Boolean.FALSE;
			case BeaninfoPackage.EVENT_SET_DECORATOR__UNICAST:
				return isUnicast() ? Boolean.TRUE : Boolean.FALSE;
			case BeaninfoPackage.EVENT_SET_DECORATOR__LISTENER_METHODS_EXPLICIT_EMPTY:
				return isListenerMethodsExplicitEmpty() ? Boolean.TRUE : Boolean.FALSE;
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
			case BeaninfoPackage.EVENT_SET_DECORATOR__EVENT_ADAPTER_CLASS:
				if (resolve) return getEventAdapterClass();
				return basicGetEventAdapterClass();
			case BeaninfoPackage.EVENT_SET_DECORATOR__SER_LIST_MTHD:
				return getSerListMthd();
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
			case BeaninfoPackage.EVENT_SET_DECORATOR__ATTRIBUTES_EXPLICIT_EMPTY:
				setAttributesExplicitEmpty(((Boolean)newValue).booleanValue());
				return;
			case BeaninfoPackage.EVENT_SET_DECORATOR__IMPLICITLY_SET_BITS:
				setImplicitlySetBits(((Long)newValue).longValue());
				return;
			case BeaninfoPackage.EVENT_SET_DECORATOR__IMPLICIT_DECORATOR_FLAG:
				setImplicitDecoratorFlag((ImplicitItem)newValue);
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
			case BeaninfoPackage.EVENT_SET_DECORATOR__LISTENER_METHODS_EXPLICIT_EMPTY:
				setListenerMethodsExplicitEmpty(((Boolean)newValue).booleanValue());
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
			case BeaninfoPackage.EVENT_SET_DECORATOR__EVENT_ADAPTER_CLASS:
				setEventAdapterClass((JavaClass)newValue);
				return;
			case BeaninfoPackage.EVENT_SET_DECORATOR__SER_LIST_MTHD:
				getSerListMthd().clear();
				getSerListMthd().addAll((Collection)newValue);
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
			case BeaninfoPackage.EVENT_SET_DECORATOR__ATTRIBUTES_EXPLICIT_EMPTY:
				setAttributesExplicitEmpty(ATTRIBUTES_EXPLICIT_EMPTY_EDEFAULT);
				return;
			case BeaninfoPackage.EVENT_SET_DECORATOR__IMPLICITLY_SET_BITS:
				setImplicitlySetBits(IMPLICITLY_SET_BITS_EDEFAULT);
				return;
			case BeaninfoPackage.EVENT_SET_DECORATOR__IMPLICIT_DECORATOR_FLAG:
				setImplicitDecoratorFlag(IMPLICIT_DECORATOR_FLAG_EDEFAULT);
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
			case BeaninfoPackage.EVENT_SET_DECORATOR__LISTENER_METHODS_EXPLICIT_EMPTY:
				setListenerMethodsExplicitEmpty(LISTENER_METHODS_EXPLICIT_EMPTY_EDEFAULT);
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
			case BeaninfoPackage.EVENT_SET_DECORATOR__EVENT_ADAPTER_CLASS:
				setEventAdapterClass((JavaClass)null);
				return;
			case BeaninfoPackage.EVENT_SET_DECORATOR__SER_LIST_MTHD:
				getSerListMthd().clear();
				return;
		}
		eDynamicUnset(eFeature);
	}

	/*
	 * This is overridden so that we can do special is set tests:
	 * 1) parameter descriptors: check if serParmDesc exists and not empty, since parameter descriptors is derived.
	 * 2) serParmDesc: if flag set to default parm desc, then answer not set, else do normal isSet test. That way if set by default it won't serialize
	 *    out the unneeded default parms. They can be reconstructed quickly when needed.
	 */
	public boolean eIsSet(EStructuralFeature eFeature) {
		switch (eDerivedStructuralFeatureID(eFeature)) {
			case BeaninfoPackage.EVENT_SET_DECORATOR__SOURCE:
				return isSourceSet();	// Override so that if set to the same as classname, then it is considered not set.
			case BeaninfoPackage.EVENT_SET_DECORATOR__LISTENER_METHODS:
				return eIsSetGen(BeaninfoPackage.eINSTANCE.getEventSetDecorator_SerListMthd());	// Let default serListMthd is set work.
			case BeaninfoPackage.EVENT_SET_DECORATOR__SER_LIST_MTHD:
				if ((getImplicitlySetBits() & EVENT_LISTENERMETHODS_DEFAULT) != 0)
					return false;	// Not considered set if initialized by default.
				else
					return eIsSetGen(eFeature);	// Not set by default, so check true setting.
			default:
				return eIsSetGen(eFeature);	// Everything else use the gen method.
		}
	}
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean eIsSetGen(EStructuralFeature eFeature) {
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
			case BeaninfoPackage.EVENT_SET_DECORATOR__ATTRIBUTES_EXPLICIT_EMPTY:
				return attributesExplicitEmpty != ATTRIBUTES_EXPLICIT_EMPTY_EDEFAULT;
			case BeaninfoPackage.EVENT_SET_DECORATOR__IMPLICITLY_SET_BITS:
				return implicitlySetBits != IMPLICITLY_SET_BITS_EDEFAULT;
			case BeaninfoPackage.EVENT_SET_DECORATOR__IMPLICIT_DECORATOR_FLAG:
				return implicitDecoratorFlag != IMPLICIT_DECORATOR_FLAG_EDEFAULT;
			case BeaninfoPackage.EVENT_SET_DECORATOR__ATTRIBUTES:
				return attributes != null && !attributes.isEmpty();
			case BeaninfoPackage.EVENT_SET_DECORATOR__IN_DEFAULT_EVENT_SET:
				return isSetInDefaultEventSet();
			case BeaninfoPackage.EVENT_SET_DECORATOR__UNICAST:
				return isSetUnicast();
			case BeaninfoPackage.EVENT_SET_DECORATOR__LISTENER_METHODS_EXPLICIT_EMPTY:
				return listenerMethodsExplicitEmpty != LISTENER_METHODS_EXPLICIT_EMPTY_EDEFAULT;
			case BeaninfoPackage.EVENT_SET_DECORATOR__ADD_LISTENER_METHOD:
				return addListenerMethod != null;
			case BeaninfoPackage.EVENT_SET_DECORATOR__LISTENER_METHODS:
				return !getListenerMethods().isEmpty();
			case BeaninfoPackage.EVENT_SET_DECORATOR__LISTENER_TYPE:
				return listenerType != null;
			case BeaninfoPackage.EVENT_SET_DECORATOR__REMOVE_LISTENER_METHOD:
				return removeListenerMethod != null;
			case BeaninfoPackage.EVENT_SET_DECORATOR__EVENT_ADAPTER_CLASS:
				return eventAdapterClass != null;
			case BeaninfoPackage.EVENT_SET_DECORATOR__SER_LIST_MTHD:
				return serListMthd != null && !serListMthd.isEmpty();
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
		result.append(", listenerMethodsExplicitEmpty: ");
		result.append(listenerMethodsExplicitEmpty);
		result.append(')');
		return result.toString();
	}

}
