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
package org.eclipse.jst.j2ee.webservice.internal.wsdd.impl;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.EObjectImpl;
import org.eclipse.jst.j2ee.webservice.wsdd.BeanLink;
import org.eclipse.jst.j2ee.webservice.wsdd.EJBLink;
import org.eclipse.jst.j2ee.webservice.wsdd.ServiceImplBean;
import org.eclipse.jst.j2ee.webservice.wsdd.ServletLink;
import org.eclipse.jst.j2ee.webservice.wsdd.WsddPackage;


/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Service Impl Bean</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.eclipse.jst.j2ee.internal.webservice.wsdd.impl.ServiceImplBeanImpl#getEEJBLink <em>EEJB Link</em>}</li>
 *   <li>{@link org.eclipse.jst.j2ee.internal.webservice.wsdd.impl.ServiceImplBeanImpl#getEServletLink <em>EServlet Link</em>}</li>
 *   <li>{@link org.eclipse.jst.j2ee.internal.webservice.wsdd.impl.ServiceImplBeanImpl#getBeanLink <em>Bean Link</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class ServiceImplBeanImpl extends EObjectImpl implements ServiceImplBean
{
	/**
	 * The cached value of the '{@link #getEEJBLink() <em>EEJB Link</em>}' containment reference.
	 * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
	 * @see #getEEJBLink()
	 * @generated
	 * @ordered
	 */
  protected EJBLink eEJBLink = null;

	/**
	 * The cached value of the '{@link #getEServletLink() <em>EServlet Link</em>}' containment reference.
	 * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
	 * @see #getEServletLink()
	 * @generated
	 * @ordered
	 */
  protected ServletLink eServletLink = null;

	/**
	 * The cached value of the '{@link #getBeanLink() <em>Bean Link</em>}' containment reference.
	 * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
	 * @see #getBeanLink()
	 * @generated
	 * @ordered
	 */
  protected BeanLink beanLink = null;

	/**
	 * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
	 * @generated
	 */
  protected ServiceImplBeanImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
	 * @generated
	 */
  protected EClass eStaticClass() {
		return WsddPackage.eINSTANCE.getServiceImplBean();
	}

	/**
	 * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
	 * @generated
	 */
  public EJBLink getEEJBLink() {
		return eEJBLink;
	}

	/**
	 * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
	 * @generated
	 */
  public NotificationChain basicSetEEJBLink(EJBLink newEEJBLink, NotificationChain msgs) {
		EJBLink oldEEJBLink = eEJBLink;
		eEJBLink = newEEJBLink;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, WsddPackage.SERVICE_IMPL_BEAN__EEJB_LINK, oldEEJBLink, newEEJBLink);
			if (msgs == null) msgs = notification; else msgs.add(notification);
		}
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
	 * @generated
	 */
  public void setEEJBLink(EJBLink newEEJBLink) {
		if (newEEJBLink != eEJBLink) {
			NotificationChain msgs = null;
			if (eEJBLink != null)
				msgs = ((InternalEObject)eEJBLink).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - WsddPackage.SERVICE_IMPL_BEAN__EEJB_LINK, null, msgs);
			if (newEEJBLink != null)
				msgs = ((InternalEObject)newEEJBLink).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - WsddPackage.SERVICE_IMPL_BEAN__EEJB_LINK, null, msgs);
			msgs = basicSetEEJBLink(newEEJBLink, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, WsddPackage.SERVICE_IMPL_BEAN__EEJB_LINK, newEEJBLink, newEEJBLink));
	}

	/**
	 * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
	 * @generated
	 */
  public ServletLink getEServletLink() {
		return eServletLink;
	}

	/**
	 * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
	 * @generated
	 */
  public NotificationChain basicSetEServletLink(ServletLink newEServletLink, NotificationChain msgs) {
		ServletLink oldEServletLink = eServletLink;
		eServletLink = newEServletLink;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, WsddPackage.SERVICE_IMPL_BEAN__ESERVLET_LINK, oldEServletLink, newEServletLink);
			if (msgs == null) msgs = notification; else msgs.add(notification);
		}
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
	 * @generated
	 */
  public void setEServletLink(ServletLink newEServletLink) {
		if (newEServletLink != eServletLink) {
			NotificationChain msgs = null;
			if (eServletLink != null)
				msgs = ((InternalEObject)eServletLink).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - WsddPackage.SERVICE_IMPL_BEAN__ESERVLET_LINK, null, msgs);
			if (newEServletLink != null)
				msgs = ((InternalEObject)newEServletLink).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - WsddPackage.SERVICE_IMPL_BEAN__ESERVLET_LINK, null, msgs);
			msgs = basicSetEServletLink(newEServletLink, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, WsddPackage.SERVICE_IMPL_BEAN__ESERVLET_LINK, newEServletLink, newEServletLink));
	}

	/**
	 * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
	 * @generated
	 */
  public BeanLink getBeanLink() {
		return beanLink;
	}

	/**
	 * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
	 * @generated
	 */
  public NotificationChain basicSetBeanLink(BeanLink newBeanLink, NotificationChain msgs) {
		BeanLink oldBeanLink = beanLink;
		beanLink = newBeanLink;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, WsddPackage.SERVICE_IMPL_BEAN__BEAN_LINK, oldBeanLink, newBeanLink);
			if (msgs == null) msgs = notification; else msgs.add(notification);
		}
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
	 * @generated
	 */
  public void setBeanLink(BeanLink newBeanLink) {
		if (newBeanLink != beanLink) {
			NotificationChain msgs = null;
			if (beanLink != null)
				msgs = ((InternalEObject)beanLink).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - WsddPackage.SERVICE_IMPL_BEAN__BEAN_LINK, null, msgs);
			if (newBeanLink != null)
				msgs = ((InternalEObject)newBeanLink).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - WsddPackage.SERVICE_IMPL_BEAN__BEAN_LINK, null, msgs);
			msgs = basicSetBeanLink(newBeanLink, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, WsddPackage.SERVICE_IMPL_BEAN__BEAN_LINK, newBeanLink, newBeanLink));
	}

	/**
	 * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
	 * @generated
	 */
  public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, Class baseClass, NotificationChain msgs) {
		if (featureID >= 0) {
			switch (eDerivedStructuralFeatureID(featureID, baseClass)) {
				case WsddPackage.SERVICE_IMPL_BEAN__EEJB_LINK:
					return basicSetEEJBLink(null, msgs);
				case WsddPackage.SERVICE_IMPL_BEAN__ESERVLET_LINK:
					return basicSetEServletLink(null, msgs);
				case WsddPackage.SERVICE_IMPL_BEAN__BEAN_LINK:
					return basicSetBeanLink(null, msgs);
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
  public Object eGet(EStructuralFeature eFeature, boolean resolve) {
		switch (eDerivedStructuralFeatureID(eFeature)) {
			case WsddPackage.SERVICE_IMPL_BEAN__EEJB_LINK:
				return getEEJBLink();
			case WsddPackage.SERVICE_IMPL_BEAN__ESERVLET_LINK:
				return getEServletLink();
			case WsddPackage.SERVICE_IMPL_BEAN__BEAN_LINK:
				return getBeanLink();
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
			case WsddPackage.SERVICE_IMPL_BEAN__EEJB_LINK:
				setEEJBLink((EJBLink)newValue);
				return;
			case WsddPackage.SERVICE_IMPL_BEAN__ESERVLET_LINK:
				setEServletLink((ServletLink)newValue);
				return;
			case WsddPackage.SERVICE_IMPL_BEAN__BEAN_LINK:
				setBeanLink((BeanLink)newValue);
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
			case WsddPackage.SERVICE_IMPL_BEAN__EEJB_LINK:
				setEEJBLink((EJBLink)null);
				return;
			case WsddPackage.SERVICE_IMPL_BEAN__ESERVLET_LINK:
				setEServletLink((ServletLink)null);
				return;
			case WsddPackage.SERVICE_IMPL_BEAN__BEAN_LINK:
				setBeanLink((BeanLink)null);
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
			case WsddPackage.SERVICE_IMPL_BEAN__EEJB_LINK:
				return eEJBLink != null;
			case WsddPackage.SERVICE_IMPL_BEAN__ESERVLET_LINK:
				return eServletLink != null;
			case WsddPackage.SERVICE_IMPL_BEAN__BEAN_LINK:
				return beanLink != null;
		}
		return eDynamicIsSet(eFeature);
	}

} //ServiceImplBeanImpl
