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
package org.eclipse.jst.j2ee.webapplication.impl;

import java.util.Collection;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.EObjectImpl;
import org.eclipse.emf.ecore.util.EDataTypeEList;
import org.eclipse.emf.ecore.util.EDataTypeUniqueEList;
import org.eclipse.emf.ecore.util.EObjectContainmentEList;
import org.eclipse.emf.ecore.util.EObjectContainmentWithInverseEList;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.emf.ecore.util.InternalEList;
import org.eclipse.jst.j2ee.common.Description;
import org.eclipse.jst.j2ee.webapplication.HTTPMethodType;
import org.eclipse.jst.j2ee.webapplication.HTTPMethodTypeEnum;
import org.eclipse.jst.j2ee.webapplication.SecurityConstraint;
import org.eclipse.jst.j2ee.webapplication.URLPatternType;
import org.eclipse.jst.j2ee.webapplication.WebResourceCollection;
import org.eclipse.jst.j2ee.webapplication.WebapplicationFactory;
import org.eclipse.jst.j2ee.webapplication.WebapplicationPackage;


/**
 * The web-resource-collection element is used to identify a subset of the resources and HTTP methods on those resources within a web application to which a security constraint applies. If no HTTP methods are specified, then the security constraint applies to all HTTP methods.

 */
public class WebResourceCollectionImpl extends EObjectImpl implements WebResourceCollection, EObject {

	/**
	 * The default value of the '{@link #getWebResourceName() <em>Web Resource Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getWebResourceName()
	 * @generated
	 * @ordered
	 */
	protected static final String WEB_RESOURCE_NAME_EDEFAULT = null;

	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	protected String webResourceName = WEB_RESOURCE_NAME_EDEFAULT;
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
	protected String description = DESCRIPTION_EDEFAULT;
	/**
	 * The cached value of the '{@link #getUrlPattern() <em>Url Pattern</em>}' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getUrlPattern()
	 * @generated
	 * @ordered
	 */
	protected EList urlPattern = null;

	/**
	 * The cached value of the '{@link #getHttpMethod() <em>Http Method</em>}' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getHttpMethod()
	 * @generated
	 * @ordered
	 */
	protected EList httpMethod = null;

	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	protected EList urLs = null;
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
	 * The cached value of the '{@link #getHTTPs() <em>HTT Ps</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getHTTPs()
	 * @generated
	 * @ordered
	 */
	protected EList httPs = null;

	public WebResourceCollectionImpl() {
		super();
	}
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected EClass eStaticClass() {
		return WebapplicationPackage.eINSTANCE.getWebResourceCollection();
	}

/**
 * addHTTP method comment.
 */
public void addHTTP(java.lang.String ahttpMethod) {
	HTTPMethodType http = getFactory().createHTTPMethodType();
	http.setHttpMethod(ahttpMethod);
	getHTTPs().add(http);
}
/**
 * addURL method comment.
 */
public void addURL(java.lang.String aurlPattern) {
	URLPatternType url = getFactory().createURLPatternType();
	url.setUrlPattern(aurlPattern);
	getURLs().add(url);
}
public WebapplicationFactory getFactory() {
	return ((WebapplicationPackage)EPackage.Registry.INSTANCE.getEPackage(WebapplicationPackage.eNS_URI)).getWebapplicationFactory();
}
	/**
	 * @generated This field/method will be replaced during code generation 
	 * The web-resource-name contains the name of this web resource collection
	 */
	public String getWebResourceName() {
		return webResourceName;
	}

	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	public void setWebResourceName(String newWebResourceName) {
		String oldWebResourceName = webResourceName;
		webResourceName = newWebResourceName;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, WebapplicationPackage.WEB_RESOURCE_COLLECTION__WEB_RESOURCE_NAME, oldWebResourceName, webResourceName));
	}

	/**
	 * @generated This field/method will be replaced during code generation 
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
			eNotify(new ENotificationImpl(this, Notification.SET, WebapplicationPackage.WEB_RESOURCE_COLLECTION__DESCRIPTION, oldDescription, description));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * Added for 1.4
	 * @generated
	 */
	public EList getUrlPattern() {
		if (urlPattern == null) {
			urlPattern = new EDataTypeEList(String.class, this, WebapplicationPackage.WEB_RESOURCE_COLLECTION__URL_PATTERN);
		}
		return urlPattern;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * Added for 1.4
	 * @generated
	 */
	public EList getHttpMethod() {
		if (httpMethod == null) {
			httpMethod = new EDataTypeUniqueEList(HTTPMethodTypeEnum.class, this, WebapplicationPackage.WEB_RESOURCE_COLLECTION__HTTP_METHOD);
		}
		return httpMethod;
	}

	/**
	 * @generated This field/method will be replaced during code generation 
	 */
	public SecurityConstraint getSecConstraint() {
		if (eContainerFeatureID != WebapplicationPackage.WEB_RESOURCE_COLLECTION__SEC_CONSTRAINT) return null;
		return (SecurityConstraint)eContainer;
	}

	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	public void setSecConstraint(SecurityConstraint newSecConstraint) {
		if (newSecConstraint != eContainer || (eContainerFeatureID != WebapplicationPackage.WEB_RESOURCE_COLLECTION__SEC_CONSTRAINT && newSecConstraint != null)) {
			if (EcoreUtil.isAncestor(this, newSecConstraint))
				throw new IllegalArgumentException("Recursive containment not allowed for " + toString());//$NON-NLS-1$
			NotificationChain msgs = null;
			if (eContainer != null)
				msgs = eBasicRemoveFromContainer(msgs);
			if (newSecConstraint != null)
				msgs = ((InternalEObject)newSecConstraint).eInverseAdd(this, WebapplicationPackage.SECURITY_CONSTRAINT__WEB_RESOURCE_COLLECTIONS, SecurityConstraint.class, msgs);
			msgs = eBasicSetContainer((InternalEObject)newSecConstraint, WebapplicationPackage.WEB_RESOURCE_COLLECTION__SEC_CONSTRAINT, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, WebapplicationPackage.WEB_RESOURCE_COLLECTION__SEC_CONSTRAINT, newSecConstraint, newSecConstraint));
	}

	/**
	 * @generated This field/method will be replaced during code generation 
	 *  <!-- begin-user-doc -->
	 * @since J2EE1.4 deprecated, use getUrlPattern()
	 * <!-- end-user-doc -->
	 * 
	 */
	public EList getURLs() {
		if (urLs == null) {
			urLs = new EObjectContainmentWithInverseEList(URLPatternType.class, this, WebapplicationPackage.WEB_RESOURCE_COLLECTION__UR_LS, WebapplicationPackage.URL_PATTERN_TYPE__RES_COLLECTION);
		}
		return urLs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList getDescriptions() {
		if (descriptions == null) {
			descriptions = new EObjectContainmentEList(Description.class, this, WebapplicationPackage.WEB_RESOURCE_COLLECTION__DESCRIPTIONS);
		}
		return descriptions;
	}

	/**
	 * <!-- begin-user-doc -->
	 * @since J2EE1.4 deprecated, use getHttpMethods()
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList getHTTPs() {
		if (httPs == null) {
			httPs = new EObjectContainmentEList(HTTPMethodType.class, this, WebapplicationPackage.WEB_RESOURCE_COLLECTION__HTT_PS);
		}
		return httPs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain eInverseAdd(InternalEObject otherEnd, int featureID, Class baseClass, NotificationChain msgs) {
		if (featureID >= 0) {
			switch (eDerivedStructuralFeatureID(featureID, baseClass)) {
				case WebapplicationPackage.WEB_RESOURCE_COLLECTION__SEC_CONSTRAINT:
					if (eContainer != null)
						msgs = eBasicRemoveFromContainer(msgs);
					return eBasicSetContainer(otherEnd, WebapplicationPackage.WEB_RESOURCE_COLLECTION__SEC_CONSTRAINT, msgs);
				case WebapplicationPackage.WEB_RESOURCE_COLLECTION__UR_LS:
					return ((InternalEList)getURLs()).basicAdd(otherEnd, msgs);
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
				case WebapplicationPackage.WEB_RESOURCE_COLLECTION__SEC_CONSTRAINT:
					return eBasicSetContainer(null, WebapplicationPackage.WEB_RESOURCE_COLLECTION__SEC_CONSTRAINT, msgs);
				case WebapplicationPackage.WEB_RESOURCE_COLLECTION__UR_LS:
					return ((InternalEList)getURLs()).basicRemove(otherEnd, msgs);
				case WebapplicationPackage.WEB_RESOURCE_COLLECTION__DESCRIPTIONS:
					return ((InternalEList)getDescriptions()).basicRemove(otherEnd, msgs);
				case WebapplicationPackage.WEB_RESOURCE_COLLECTION__HTT_PS:
					return ((InternalEList)getHTTPs()).basicRemove(otherEnd, msgs);
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
				case WebapplicationPackage.WEB_RESOURCE_COLLECTION__SEC_CONSTRAINT:
					return eContainer.eInverseRemove(this, WebapplicationPackage.SECURITY_CONSTRAINT__WEB_RESOURCE_COLLECTIONS, SecurityConstraint.class, msgs);
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
			case WebapplicationPackage.WEB_RESOURCE_COLLECTION__WEB_RESOURCE_NAME:
				return getWebResourceName();
			case WebapplicationPackage.WEB_RESOURCE_COLLECTION__DESCRIPTION:
				return getDescription();
			case WebapplicationPackage.WEB_RESOURCE_COLLECTION__URL_PATTERN:
				return getUrlPattern();
			case WebapplicationPackage.WEB_RESOURCE_COLLECTION__HTTP_METHOD:
				return getHttpMethod();
			case WebapplicationPackage.WEB_RESOURCE_COLLECTION__SEC_CONSTRAINT:
				return getSecConstraint();
			case WebapplicationPackage.WEB_RESOURCE_COLLECTION__UR_LS:
				return getURLs();
			case WebapplicationPackage.WEB_RESOURCE_COLLECTION__DESCRIPTIONS:
				return getDescriptions();
			case WebapplicationPackage.WEB_RESOURCE_COLLECTION__HTT_PS:
				return getHTTPs();
		}
		return eDynamicGet(eFeature, resolve);
	}

	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	public boolean eIsSet(EStructuralFeature eFeature) {
		switch (eDerivedStructuralFeatureID(eFeature)) {
			case WebapplicationPackage.WEB_RESOURCE_COLLECTION__WEB_RESOURCE_NAME:
				return WEB_RESOURCE_NAME_EDEFAULT == null ? webResourceName != null : !WEB_RESOURCE_NAME_EDEFAULT.equals(webResourceName);
			case WebapplicationPackage.WEB_RESOURCE_COLLECTION__DESCRIPTION:
				return DESCRIPTION_EDEFAULT == null ? description != null : !DESCRIPTION_EDEFAULT.equals(description);
			case WebapplicationPackage.WEB_RESOURCE_COLLECTION__URL_PATTERN:
				return urlPattern != null && !urlPattern.isEmpty();
			case WebapplicationPackage.WEB_RESOURCE_COLLECTION__HTTP_METHOD:
				return httpMethod != null && !httpMethod.isEmpty();
			case WebapplicationPackage.WEB_RESOURCE_COLLECTION__SEC_CONSTRAINT:
				return getSecConstraint() != null;
			case WebapplicationPackage.WEB_RESOURCE_COLLECTION__UR_LS:
				return urLs != null && !urLs.isEmpty();
			case WebapplicationPackage.WEB_RESOURCE_COLLECTION__DESCRIPTIONS:
				return descriptions != null && !descriptions.isEmpty();
			case WebapplicationPackage.WEB_RESOURCE_COLLECTION__HTT_PS:
				return httPs != null && !httPs.isEmpty();
		}
		return eDynamicIsSet(eFeature);
	}

	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	public void eSet(EStructuralFeature eFeature, Object newValue) {
		switch (eDerivedStructuralFeatureID(eFeature)) {
			case WebapplicationPackage.WEB_RESOURCE_COLLECTION__WEB_RESOURCE_NAME:
				setWebResourceName((String)newValue);
				return;
			case WebapplicationPackage.WEB_RESOURCE_COLLECTION__DESCRIPTION:
				setDescription((String)newValue);
				return;
			case WebapplicationPackage.WEB_RESOURCE_COLLECTION__URL_PATTERN:
				getUrlPattern().clear();
				getUrlPattern().addAll((Collection)newValue);
				return;
			case WebapplicationPackage.WEB_RESOURCE_COLLECTION__HTTP_METHOD:
				getHttpMethod().clear();
				getHttpMethod().addAll((Collection)newValue);
				return;
			case WebapplicationPackage.WEB_RESOURCE_COLLECTION__SEC_CONSTRAINT:
				setSecConstraint((SecurityConstraint)newValue);
				return;
			case WebapplicationPackage.WEB_RESOURCE_COLLECTION__UR_LS:
				getURLs().clear();
				getURLs().addAll((Collection)newValue);
				return;
			case WebapplicationPackage.WEB_RESOURCE_COLLECTION__DESCRIPTIONS:
				getDescriptions().clear();
				getDescriptions().addAll((Collection)newValue);
				return;
			case WebapplicationPackage.WEB_RESOURCE_COLLECTION__HTT_PS:
				getHTTPs().clear();
				getHTTPs().addAll((Collection)newValue);
				return;
		}
		eDynamicSet(eFeature, newValue);
	}

	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	public void eUnset(EStructuralFeature eFeature) {
		switch (eDerivedStructuralFeatureID(eFeature)) {
			case WebapplicationPackage.WEB_RESOURCE_COLLECTION__WEB_RESOURCE_NAME:
				setWebResourceName(WEB_RESOURCE_NAME_EDEFAULT);
				return;
			case WebapplicationPackage.WEB_RESOURCE_COLLECTION__DESCRIPTION:
				setDescription(DESCRIPTION_EDEFAULT);
				return;
			case WebapplicationPackage.WEB_RESOURCE_COLLECTION__URL_PATTERN:
				getUrlPattern().clear();
				return;
			case WebapplicationPackage.WEB_RESOURCE_COLLECTION__HTTP_METHOD:
				getHttpMethod().clear();
				return;
			case WebapplicationPackage.WEB_RESOURCE_COLLECTION__SEC_CONSTRAINT:
				setSecConstraint((SecurityConstraint)null);
				return;
			case WebapplicationPackage.WEB_RESOURCE_COLLECTION__UR_LS:
				getURLs().clear();
				return;
			case WebapplicationPackage.WEB_RESOURCE_COLLECTION__DESCRIPTIONS:
				getDescriptions().clear();
				return;
			case WebapplicationPackage.WEB_RESOURCE_COLLECTION__HTT_PS:
				getHTTPs().clear();
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
		result.append(" (webResourceName: ");//$NON-NLS-1$
		result.append(webResourceName);
		result.append(", description: ");//$NON-NLS-1$
		result.append(description);
		result.append(", urlPattern: ");//$NON-NLS-1$
		result.append(urlPattern);
		result.append(", httpMethod: ");//$NON-NLS-1$
		result.append(httpMethod);
		result.append(')');
		return result.toString();
	}

}














