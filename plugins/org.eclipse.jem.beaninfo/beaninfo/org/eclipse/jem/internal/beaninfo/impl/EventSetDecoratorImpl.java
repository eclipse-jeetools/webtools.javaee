/*******************************************************************************
 * Copyright (c) 2001, 2005 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *******************************************************************************/
package org.eclipse.jem.internal.beaninfo.impl;
/*
 *  $RCSfile: EventSetDecoratorImpl.java,v $
 *  $Revision: 1.16 $  $Date: 2007/03/14 01:22:51 $ 
 */


import java.util.Collection;
import java.util.List;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.*;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.util.EObjectContainmentEList;
import org.eclipse.emf.ecore.util.InternalEList;

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
	 * The flag representing the value of the '{@link #isInDefaultEventSet() <em>In Default Event Set</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isInDefaultEventSet()
	 * @generated
	 * @ordered
	 */
	protected static final int IN_DEFAULT_EVENT_SET_EFLAG = 1 << 18;

	/**
	 * The flag representing whether the In Default Event Set attribute has been set.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	protected static final int IN_DEFAULT_EVENT_SET_ESETFLAG = 1 << 19;

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
	 * The flag representing the value of the '{@link #isUnicast() <em>Unicast</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isUnicast()
	 * @generated
	 * @ordered
	 */
	protected static final int UNICAST_EFLAG = 1 << 20;

	/**
	 * The flag representing whether the Unicast attribute has been set.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	protected static final int UNICAST_ESETFLAG = 1 << 21;

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
	 * The flag representing the value of the '{@link #isListenerMethodsExplicitEmpty() <em>Listener Methods Explicit Empty</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isListenerMethodsExplicitEmpty()
	 * @generated
	 * @ordered
	 */
	protected static final int LISTENER_METHODS_EXPLICIT_EMPTY_EFLAG = 1 << 22;

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
		return BeaninfoPackage.Literals.EVENT_SET_DECORATOR;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean isInDefaultEventSet() {
		return (eFlags & IN_DEFAULT_EVENT_SET_EFLAG) != 0;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setInDefaultEventSet(boolean newInDefaultEventSet) {
		boolean oldInDefaultEventSet = (eFlags & IN_DEFAULT_EVENT_SET_EFLAG) != 0;
		if (newInDefaultEventSet) eFlags |= IN_DEFAULT_EVENT_SET_EFLAG; else eFlags &= ~IN_DEFAULT_EVENT_SET_EFLAG;
		boolean oldInDefaultEventSetESet = (eFlags & IN_DEFAULT_EVENT_SET_ESETFLAG) != 0;
		eFlags |= IN_DEFAULT_EVENT_SET_ESETFLAG;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, BeaninfoPackage.EVENT_SET_DECORATOR__IN_DEFAULT_EVENT_SET, oldInDefaultEventSet, newInDefaultEventSet, !oldInDefaultEventSetESet));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void unsetInDefaultEventSet() {
		boolean oldInDefaultEventSet = (eFlags & IN_DEFAULT_EVENT_SET_EFLAG) != 0;
		boolean oldInDefaultEventSetESet = (eFlags & IN_DEFAULT_EVENT_SET_ESETFLAG) != 0;
		if (IN_DEFAULT_EVENT_SET_EDEFAULT) eFlags |= IN_DEFAULT_EVENT_SET_EFLAG; else eFlags &= ~IN_DEFAULT_EVENT_SET_EFLAG;
		eFlags &= ~IN_DEFAULT_EVENT_SET_ESETFLAG;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.UNSET, BeaninfoPackage.EVENT_SET_DECORATOR__IN_DEFAULT_EVENT_SET, oldInDefaultEventSet, IN_DEFAULT_EVENT_SET_EDEFAULT, oldInDefaultEventSetESet));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean isSetInDefaultEventSet() {
		return (eFlags & IN_DEFAULT_EVENT_SET_ESETFLAG) != 0;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean isUnicast() {
		return (eFlags & UNICAST_EFLAG) != 0;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setUnicast(boolean newUnicast) {
		boolean oldUnicast = (eFlags & UNICAST_EFLAG) != 0;
		if (newUnicast) eFlags |= UNICAST_EFLAG; else eFlags &= ~UNICAST_EFLAG;
		boolean oldUnicastESet = (eFlags & UNICAST_ESETFLAG) != 0;
		eFlags |= UNICAST_ESETFLAG;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, BeaninfoPackage.EVENT_SET_DECORATOR__UNICAST, oldUnicast, newUnicast, !oldUnicastESet));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void unsetUnicast() {
		boolean oldUnicast = (eFlags & UNICAST_EFLAG) != 0;
		boolean oldUnicastESet = (eFlags & UNICAST_ESETFLAG) != 0;
		if (UNICAST_EDEFAULT) eFlags |= UNICAST_EFLAG; else eFlags &= ~UNICAST_EFLAG;
		eFlags &= ~UNICAST_ESETFLAG;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.UNSET, BeaninfoPackage.EVENT_SET_DECORATOR__UNICAST, oldUnicast, UNICAST_EDEFAULT, oldUnicastESet));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean isSetUnicast() {
		return (eFlags & UNICAST_ESETFLAG) != 0;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean isListenerMethodsExplicitEmpty() {
		return (eFlags & LISTENER_METHODS_EXPLICIT_EMPTY_EFLAG) != 0;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setListenerMethodsExplicitEmpty(boolean newListenerMethodsExplicitEmpty) {
		boolean oldListenerMethodsExplicitEmpty = (eFlags & LISTENER_METHODS_EXPLICIT_EMPTY_EFLAG) != 0;
		if (newListenerMethodsExplicitEmpty) eFlags |= LISTENER_METHODS_EXPLICIT_EMPTY_EFLAG; else eFlags &= ~LISTENER_METHODS_EXPLICIT_EMPTY_EFLAG;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, BeaninfoPackage.EVENT_SET_DECORATOR__LISTENER_METHODS_EXPLICIT_EMPTY, oldListenerMethodsExplicitEmpty, newListenerMethodsExplicitEmpty));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Method getAddListenerMethod() {
		if (addListenerMethod != null && addListenerMethod.eIsProxy()) {
			InternalEObject oldAddListenerMethod = (InternalEObject)addListenerMethod;
			addListenerMethod = (Method)eResolveProxy(oldAddListenerMethod);
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
		// The list is expected to implement org.eclipse.emf.ecore.util.InternalEList and org.eclipse.emf.ecore.EStructuralFeature.Setting
		// so it's likely that an appropriate subclass of org.eclipse.emf.ecore.util.EcoreEList should be used.
		throw new UnsupportedOperationException();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public JavaClass getListenerType() {
		if (listenerType != null && listenerType.eIsProxy()) {
			InternalEObject oldListenerType = (InternalEObject)listenerType;
			listenerType = (JavaClass)eResolveProxy(oldListenerType);
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
			InternalEObject oldRemoveListenerMethod = (InternalEObject)removeListenerMethod;
			removeListenerMethod = (Method)eResolveProxy(oldRemoveListenerMethod);
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
			InternalEObject oldEventAdapterClass = (InternalEObject)eventAdapterClass;
			eventAdapterClass = (JavaClass)eResolveProxy(oldEventAdapterClass);
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
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case BeaninfoPackage.EVENT_SET_DECORATOR__SER_LIST_MTHD:
				return ((InternalEList)getSerListMthd()).basicRemove(otherEnd, msgs);
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
		return super.eGet(featureID, resolve, coreType);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void eSet(int featureID, Object newValue) {
		switch (featureID) {
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
		super.eSet(featureID, newValue);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void eUnset(int featureID) {
		switch (featureID) {
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
		super.eUnset(featureID);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * Overriden
	 */
	public boolean eIsSet(int featureID) {
		switch (featureID) {
			case BeaninfoPackage.EVENT_SET_DECORATOR__SOURCE:
				return isSourceSet();	// Override so that if set to the same as classname, then it is considered not set.
			case BeaninfoPackage.EVENT_SET_DECORATOR__LISTENER_METHODS:
				return eIsSet(BeaninfoPackage.eINSTANCE.getEventSetDecorator_SerListMthd());	// Let default serListMthd is set work.
			case BeaninfoPackage.EVENT_SET_DECORATOR__SER_LIST_MTHD:
				if ((getImplicitlySetBits() & EVENT_LISTENERMETHODS_DEFAULT) != 0)
					return false;	// Not considered set if initialized by default.
			case BeaninfoPackage.EVENT_SET_DECORATOR__IN_DEFAULT_EVENT_SET:
				return isSetInDefaultEventSet();
			case BeaninfoPackage.EVENT_SET_DECORATOR__UNICAST:
				return isSetUnicast();
			case BeaninfoPackage.EVENT_SET_DECORATOR__LISTENER_METHODS_EXPLICIT_EMPTY:
				return ((eFlags & LISTENER_METHODS_EXPLICIT_EMPTY_EFLAG) != 0) != LISTENER_METHODS_EXPLICIT_EMPTY_EDEFAULT;
			case BeaninfoPackage.EVENT_SET_DECORATOR__ADD_LISTENER_METHOD:
				return addListenerMethod != null;
			case BeaninfoPackage.EVENT_SET_DECORATOR__LISTENER_TYPE:
				return listenerType != null;
			case BeaninfoPackage.EVENT_SET_DECORATOR__REMOVE_LISTENER_METHOD:
				return removeListenerMethod != null;
			case BeaninfoPackage.EVENT_SET_DECORATOR__EVENT_ADAPTER_CLASS:
				return eventAdapterClass != null;
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
		result.append(" (inDefaultEventSet: ");
		if ((eFlags & IN_DEFAULT_EVENT_SET_ESETFLAG) != 0) result.append((eFlags & IN_DEFAULT_EVENT_SET_EFLAG) != 0); else result.append("<unset>");
		result.append(", unicast: ");
		if ((eFlags & UNICAST_ESETFLAG) != 0) result.append((eFlags & UNICAST_EFLAG) != 0); else result.append("<unset>");
		result.append(", listenerMethodsExplicitEmpty: ");
		result.append((eFlags & LISTENER_METHODS_EXPLICIT_EMPTY_EFLAG) != 0);
		result.append(')');
		return result.toString();
	}

}
