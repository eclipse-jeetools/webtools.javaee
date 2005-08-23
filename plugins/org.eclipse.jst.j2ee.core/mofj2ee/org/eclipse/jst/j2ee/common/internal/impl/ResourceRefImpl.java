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
package org.eclipse.jst.j2ee.common.internal.impl;

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
import org.eclipse.emf.ecore.util.InternalEList;
import org.eclipse.jst.j2ee.common.CommonPackage;
import org.eclipse.jst.j2ee.common.Description;
import org.eclipse.jst.j2ee.common.ResAuthTypeBase;
import org.eclipse.jst.j2ee.common.ResSharingScopeType;
import org.eclipse.jst.j2ee.common.ResourceRef;

/**
 * The resource-ref element contains a declaration of application clients's reference to an external resource. It consists of an optional description, the resource factory reference name, the indication of the resource factory type expected by the application client's code, and the type of authentication (bean or container).
 * Example:
 * <resource-ref>
 * <res-ref-name>EmployeeAppDB<//res-ref-name>
 * <res-type>javax.sql.DataSource<//res-type>
 * <res-auth>Container<//res-auth>
 * <//resource-ref>
 */
public class ResourceRefImpl extends EObjectImpl implements ResourceRef, EObject{

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
	 * The default value of the '{@link #getName() <em>Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getName()
	 * @generated
	 * @ordered
	 */
	protected static final String NAME_EDEFAULT = null;

	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	protected String name = NAME_EDEFAULT;
	/**
	 * The default value of the '{@link #getType() <em>Type</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getType()
	 * @generated
	 * @ordered
	 */
	protected static final String TYPE_EDEFAULT = null;

	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	protected String type = TYPE_EDEFAULT;
	/**
	 * The default value of the '{@link #getAuth() <em>Auth</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getAuth()
	 * @generated
	 * @ordered
	 */
	protected static final ResAuthTypeBase AUTH_EDEFAULT = ResAuthTypeBase.CONTAINER_LITERAL;

	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	protected ResAuthTypeBase auth = AUTH_EDEFAULT;
	/**
	 * This is true if the Auth attribute has been set.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	protected boolean authESet = false;

	/**
	 * The default value of the '{@link #getLink() <em>Link</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getLink()
	 * @generated
	 * @ordered
	 */
	protected static final String LINK_EDEFAULT = null;

	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	protected String link = LINK_EDEFAULT;
	/**
	 * The default value of the '{@link #getResSharingScope() <em>Res Sharing Scope</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getResSharingScope()
	 * @generated
	 * @ordered
	 */
	protected static final ResSharingScopeType RES_SHARING_SCOPE_EDEFAULT = ResSharingScopeType.SHAREABLE_LITERAL;

	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	protected ResSharingScopeType resSharingScope = RES_SHARING_SCOPE_EDEFAULT;
	/**
	 * This is true if the Res Sharing Scope attribute has been set.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	protected boolean resSharingScopeESet = false;

	/**
	 * The cached value of the '{@link #getDescriptions() <em>Descriptions</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getDescriptions()
	 * @generated
	 * @ordered
	 */
	protected EList descriptions = null;

	public ResourceRefImpl() {
		super();
	}
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected EClass eStaticClass() {
		return CommonPackage.eINSTANCE.getResourceRef();
	}

	public void setDescription(String newDescription) {
		String oldDescription = description;
		description = newDescription;
		if (getDescriptions() != null && !getDescriptions().isEmpty()) {
			Description d = (Description) getDescriptions().get(0);
			if (d != null)
				d.setValue(newDescription);
		}
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, CommonPackage.SECURITY_ROLE__DESCRIPTION, oldDescription, description));
	}

	public String getDescription() {
		if (getDescriptions() != null && !getDescriptions().isEmpty()) {
			Description d = (Description) getDescriptions().get(0);
			if (d != null)
				description = d.getValue();
		}
		return description;
	}

	/**
	 * @generated This field/method will be replaced during code generation 
	 * The res-ref-name element specifies the name of the resource factory reference name. The resource factory reference name is the name of the application client's environment entry whose value contains the JNDI name of the data source.
	 */
	public String getName() {
		return name;
	}

	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	public void setName(String newName) {
		String oldName = name;
		name = newName;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, CommonPackage.RESOURCE_REF__NAME, oldName, name));
	}

	/**
	 * @generated This field/method will be replaced during code generation 
	 * The res-type element specifies the type of the data source. The type is specified by the Java interface (or class) expected to be implemented by the data source.
	 */
	public String getType() {
		return type;
	}

	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	public void setType(String newType) {
		String oldType = type;
		type = newType;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, CommonPackage.RESOURCE_REF__TYPE, oldType, type));
	}

	/**
	 * @generated This field/method will be replaced during code generation 
	 * The res-auth element specifies whether the enterprise bean code signs on programmatically to the resource manager, or whether the Container will sign on to the resource manager on behalf of the bean. In the latter case, the Container uses information that is supplied by the Deployer.  The value of this element must be one of the two following:
	 * <res-auth>Application<//res-auth>
	 * <res-auth>Container<//res-auth>
	 */
	public ResAuthTypeBase getAuth() {
		return auth;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setAuth(ResAuthTypeBase newAuth) {
		ResAuthTypeBase oldAuth = auth;
		auth = newAuth == null ? AUTH_EDEFAULT : newAuth;
		boolean oldAuthESet = authESet;
		authESet = true;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, CommonPackage.RESOURCE_REF__AUTH, oldAuth, auth, !oldAuthESet));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void unsetAuth() {
		ResAuthTypeBase oldAuth = auth;
		boolean oldAuthESet = authESet;
		auth = AUTH_EDEFAULT;
		authESet = false;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.UNSET, CommonPackage.RESOURCE_REF__AUTH, oldAuth, AUTH_EDEFAULT, oldAuthESet));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean isSetAuth() {
		return authESet;
	}

	/**
	 * @generated This field/method will be replaced during code generation 
	 */
	public String getLink() {
		return link;
	}

	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	public void setLink(String newLink) {
		String oldLink = link;
		link = newLink;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, CommonPackage.RESOURCE_REF__LINK, oldLink, link));
	}

	/**
	 * @generated This field/method will be replaced during code generation 
	 * The res-sharing-scope element specifies whether connections obtained
	 * through the given resource manager connection factory reference can be
	 * shared. The value of this element, if specified, must be one of the
	 * two following:  Shareable, Unshareable
	 * 
	 * The default value is Shareable.

	 */
	public ResSharingScopeType getResSharingScope() {
		return resSharingScope;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setResSharingScope(ResSharingScopeType newResSharingScope) {
		ResSharingScopeType oldResSharingScope = resSharingScope;
		resSharingScope = newResSharingScope == null ? RES_SHARING_SCOPE_EDEFAULT : newResSharingScope;
		boolean oldResSharingScopeESet = resSharingScopeESet;
		resSharingScopeESet = true;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, CommonPackage.RESOURCE_REF__RES_SHARING_SCOPE, oldResSharingScope, resSharingScope, !oldResSharingScopeESet));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void unsetResSharingScope() {
		ResSharingScopeType oldResSharingScope = resSharingScope;
		boolean oldResSharingScopeESet = resSharingScopeESet;
		resSharingScope = RES_SHARING_SCOPE_EDEFAULT;
		resSharingScopeESet = false;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.UNSET, CommonPackage.RESOURCE_REF__RES_SHARING_SCOPE, oldResSharingScope, RES_SHARING_SCOPE_EDEFAULT, oldResSharingScopeESet));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean isSetResSharingScope() {
		return resSharingScopeESet;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList getDescriptions() {
		if (descriptions == null) {
			descriptions = new EObjectContainmentEList(Description.class, this, CommonPackage.RESOURCE_REF__DESCRIPTIONS);
		}
		return descriptions;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, Class baseClass, NotificationChain msgs) {
		if (featureID >= 0) {
			switch (eDerivedStructuralFeatureID(featureID, baseClass)) {
				case CommonPackage.RESOURCE_REF__DESCRIPTIONS:
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
	public Object eGet(EStructuralFeature eFeature, boolean resolve) {
		switch (eDerivedStructuralFeatureID(eFeature)) {
			case CommonPackage.RESOURCE_REF__DESCRIPTION:
				return getDescription();
			case CommonPackage.RESOURCE_REF__NAME:
				return getName();
			case CommonPackage.RESOURCE_REF__TYPE:
				return getType();
			case CommonPackage.RESOURCE_REF__AUTH:
				return getAuth();
			case CommonPackage.RESOURCE_REF__LINK:
				return getLink();
			case CommonPackage.RESOURCE_REF__RES_SHARING_SCOPE:
				return getResSharingScope();
			case CommonPackage.RESOURCE_REF__DESCRIPTIONS:
				return getDescriptions();
		}
		return eDynamicGet(eFeature, resolve);
	}

	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	public boolean eIsSet(EStructuralFeature eFeature) {
		switch (eDerivedStructuralFeatureID(eFeature)) {
			case CommonPackage.RESOURCE_REF__DESCRIPTION:
				return DESCRIPTION_EDEFAULT == null ? description != null : !DESCRIPTION_EDEFAULT.equals(description);
			case CommonPackage.RESOURCE_REF__NAME:
				return NAME_EDEFAULT == null ? name != null : !NAME_EDEFAULT.equals(name);
			case CommonPackage.RESOURCE_REF__TYPE:
				return TYPE_EDEFAULT == null ? type != null : !TYPE_EDEFAULT.equals(type);
			case CommonPackage.RESOURCE_REF__AUTH:
				return isSetAuth();
			case CommonPackage.RESOURCE_REF__LINK:
				return LINK_EDEFAULT == null ? link != null : !LINK_EDEFAULT.equals(link);
			case CommonPackage.RESOURCE_REF__RES_SHARING_SCOPE:
				return isSetResSharingScope();
			case CommonPackage.RESOURCE_REF__DESCRIPTIONS:
				return descriptions != null && !descriptions.isEmpty();
		}
		return eDynamicIsSet(eFeature);
	}

	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	public void eSet(EStructuralFeature eFeature, Object newValue) {
		switch (eDerivedStructuralFeatureID(eFeature)) {
			case CommonPackage.RESOURCE_REF__DESCRIPTION:
				setDescription((String)newValue);
				return;
			case CommonPackage.RESOURCE_REF__NAME:
				setName((String)newValue);
				return;
			case CommonPackage.RESOURCE_REF__TYPE:
				setType((String)newValue);
				return;
			case CommonPackage.RESOURCE_REF__AUTH:
				setAuth((ResAuthTypeBase)newValue);
				return;
			case CommonPackage.RESOURCE_REF__LINK:
				setLink((String)newValue);
				return;
			case CommonPackage.RESOURCE_REF__RES_SHARING_SCOPE:
				setResSharingScope((ResSharingScopeType)newValue);
				return;
			case CommonPackage.RESOURCE_REF__DESCRIPTIONS:
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
			case CommonPackage.RESOURCE_REF__DESCRIPTION:
				setDescription(DESCRIPTION_EDEFAULT);
				return;
			case CommonPackage.RESOURCE_REF__NAME:
				setName(NAME_EDEFAULT);
				return;
			case CommonPackage.RESOURCE_REF__TYPE:
				setType(TYPE_EDEFAULT);
				return;
			case CommonPackage.RESOURCE_REF__AUTH:
				unsetAuth();
				return;
			case CommonPackage.RESOURCE_REF__LINK:
				setLink(LINK_EDEFAULT);
				return;
			case CommonPackage.RESOURCE_REF__RES_SHARING_SCOPE:
				unsetResSharingScope();
				return;
			case CommonPackage.RESOURCE_REF__DESCRIPTIONS:
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
		result.append(", name: ");
		result.append(name);
		result.append(", type: ");
		result.append(type);
		result.append(", auth: ");
		if (authESet) result.append(auth); else result.append("<unset>");
		result.append(", link: ");
		result.append(link);
		result.append(", resSharingScope: ");
		if (resSharingScopeESet) result.append(resSharingScope); else result.append("<unset>");
		result.append(')');
		return result.toString();
	}

}





