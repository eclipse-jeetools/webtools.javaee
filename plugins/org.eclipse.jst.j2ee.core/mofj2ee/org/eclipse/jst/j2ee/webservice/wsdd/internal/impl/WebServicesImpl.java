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
package org.eclipse.jst.j2ee.webservice.wsdd.internal.impl;

import java.util.Collection;

import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.util.EObjectContainmentEList;
import org.eclipse.emf.ecore.util.InternalEList;
import org.eclipse.jst.j2ee.common.internal.impl.CompatibilityDescriptionGroupImpl;
import org.eclipse.jst.j2ee.webservice.wsdd.WebServiceDescription;
import org.eclipse.jst.j2ee.webservice.wsdd.WebServices;
import org.eclipse.jst.j2ee.webservice.wsdd.WsddPackage;


/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Web Services</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.eclipse.jst.j2ee.webservice.wsdd.internal.impl.WebServicesImpl#getWebServiceDescriptions <em>Web Service Descriptions</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class WebServicesImpl extends CompatibilityDescriptionGroupImpl implements WebServices
{
	/**
	 * The cached value of the '{@link #getWebServiceDescriptions() <em>Web Service Descriptions</em>}' containment reference list.
	 * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
	 * @see #getWebServiceDescriptions()
	 * @generated
	 * @ordered
	 */
  protected EList webServiceDescriptions = null;

	/**
	 * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
	 * @generated
	 */
  protected WebServicesImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
	 * @generated
	 */
  protected EClass eStaticClass() {
		return WsddPackage.eINSTANCE.getWebServices();
	}

  /**
   *
   */
  public void setDisplayName(String newDisplayName) {
  	super.setDisplayName(newDisplayName);
  }
  
	/**
	 * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
	 * @generated
	 */
  public EList getWebServiceDescriptions() {
		if (webServiceDescriptions == null) {
			webServiceDescriptions = new EObjectContainmentEList(WebServiceDescription.class, this, WsddPackage.WEB_SERVICES__WEB_SERVICE_DESCRIPTIONS);
		}
		return webServiceDescriptions;
	}

	/**
	 * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
	 * @generated
	 */
  public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, Class baseClass, NotificationChain msgs) {
		if (featureID >= 0) {
			switch (eDerivedStructuralFeatureID(featureID, baseClass)) {
				case WsddPackage.WEB_SERVICES__ICONS:
					return ((InternalEList)getIcons()).basicRemove(otherEnd, msgs);
				case WsddPackage.WEB_SERVICES__DISPLAY_NAMES:
					return ((InternalEList)getDisplayNames()).basicRemove(otherEnd, msgs);
				case WsddPackage.WEB_SERVICES__DESCRIPTIONS:
					return ((InternalEList)getDescriptions()).basicRemove(otherEnd, msgs);
				case WsddPackage.WEB_SERVICES__WEB_SERVICE_DESCRIPTIONS:
					return ((InternalEList)getWebServiceDescriptions()).basicRemove(otherEnd, msgs);
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
			case WsddPackage.WEB_SERVICES__ICONS:
				return getIcons();
			case WsddPackage.WEB_SERVICES__DISPLAY_NAMES:
				return getDisplayNames();
			case WsddPackage.WEB_SERVICES__DESCRIPTIONS:
				return getDescriptions();
			case WsddPackage.WEB_SERVICES__SMALL_ICON:
				return getSmallIcon();
			case WsddPackage.WEB_SERVICES__LARGE_ICON:
				return getLargeIcon();
			case WsddPackage.WEB_SERVICES__DESCRIPTION:
				return getDescription();
			case WsddPackage.WEB_SERVICES__DISPLAY_NAME:
				return getDisplayName();
			case WsddPackage.WEB_SERVICES__WEB_SERVICE_DESCRIPTIONS:
				return getWebServiceDescriptions();
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
			case WsddPackage.WEB_SERVICES__ICONS:
				getIcons().clear();
				getIcons().addAll((Collection)newValue);
				return;
			case WsddPackage.WEB_SERVICES__DISPLAY_NAMES:
				getDisplayNames().clear();
				getDisplayNames().addAll((Collection)newValue);
				return;
			case WsddPackage.WEB_SERVICES__DESCRIPTIONS:
				getDescriptions().clear();
				getDescriptions().addAll((Collection)newValue);
				return;
			case WsddPackage.WEB_SERVICES__SMALL_ICON:
				setSmallIcon((String)newValue);
				return;
			case WsddPackage.WEB_SERVICES__LARGE_ICON:
				setLargeIcon((String)newValue);
				return;
			case WsddPackage.WEB_SERVICES__DESCRIPTION:
				setDescription((String)newValue);
				return;
			case WsddPackage.WEB_SERVICES__DISPLAY_NAME:
				setDisplayName((String)newValue);
				return;
			case WsddPackage.WEB_SERVICES__WEB_SERVICE_DESCRIPTIONS:
				getWebServiceDescriptions().clear();
				getWebServiceDescriptions().addAll((Collection)newValue);
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
			case WsddPackage.WEB_SERVICES__ICONS:
				getIcons().clear();
				return;
			case WsddPackage.WEB_SERVICES__DISPLAY_NAMES:
				getDisplayNames().clear();
				return;
			case WsddPackage.WEB_SERVICES__DESCRIPTIONS:
				getDescriptions().clear();
				return;
			case WsddPackage.WEB_SERVICES__SMALL_ICON:
				setSmallIcon(SMALL_ICON_EDEFAULT);
				return;
			case WsddPackage.WEB_SERVICES__LARGE_ICON:
				setLargeIcon(LARGE_ICON_EDEFAULT);
				return;
			case WsddPackage.WEB_SERVICES__DESCRIPTION:
				setDescription(DESCRIPTION_EDEFAULT);
				return;
			case WsddPackage.WEB_SERVICES__DISPLAY_NAME:
				setDisplayName(DISPLAY_NAME_EDEFAULT);
				return;
			case WsddPackage.WEB_SERVICES__WEB_SERVICE_DESCRIPTIONS:
				getWebServiceDescriptions().clear();
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
			case WsddPackage.WEB_SERVICES__ICONS:
				return icons != null && !icons.isEmpty();
			case WsddPackage.WEB_SERVICES__DISPLAY_NAMES:
				return displayNames != null && !displayNames.isEmpty();
			case WsddPackage.WEB_SERVICES__DESCRIPTIONS:
				return descriptions != null && !descriptions.isEmpty();
			case WsddPackage.WEB_SERVICES__SMALL_ICON:
				return SMALL_ICON_EDEFAULT == null ? smallIcon != null : !SMALL_ICON_EDEFAULT.equals(smallIcon);
			case WsddPackage.WEB_SERVICES__LARGE_ICON:
				return LARGE_ICON_EDEFAULT == null ? largeIcon != null : !LARGE_ICON_EDEFAULT.equals(largeIcon);
			case WsddPackage.WEB_SERVICES__DESCRIPTION:
				return DESCRIPTION_EDEFAULT == null ? description != null : !DESCRIPTION_EDEFAULT.equals(description);
			case WsddPackage.WEB_SERVICES__DISPLAY_NAME:
				return DISPLAY_NAME_EDEFAULT == null ? displayName != null : !DISPLAY_NAME_EDEFAULT.equals(displayName);
			case WsddPackage.WEB_SERVICES__WEB_SERVICE_DESCRIPTIONS:
				return webServiceDescriptions != null && !webServiceDescriptions.isEmpty();
		}
		return eDynamicIsSet(eFeature);
	}

} //WebServicesImpl
