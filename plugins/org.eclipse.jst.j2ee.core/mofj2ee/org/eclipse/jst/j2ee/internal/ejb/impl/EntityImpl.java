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
package org.eclipse.jst.j2ee.internal.ejb.impl;

import java.util.Collection;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.emf.ecore.util.InternalEList;
import org.eclipse.jem.java.JavaClass;
import org.eclipse.jst.j2ee.common.SecurityIdentity;
import org.eclipse.jst.j2ee.ejb.EJBJar;
import org.eclipse.jst.j2ee.ejb.EjbPackage;
import org.eclipse.jst.j2ee.ejb.EnterpriseBean;
import org.eclipse.jst.j2ee.ejb.Entity;


/**
 * The entity element declares an entity bean. The declaration consists of: an optional description; optional display name; optional small icon file name; optional large icon file name; a unique name assigned to the enterprise bean in the deployment descriptor; the names of the entity bean's home and remote interfaces, if any; the names of the entity bean's local home and local interface, if any; the entity bean's implementation class; the entity bean's persistence management type; the entity bean's primary key class name; an indication of the entity bean's reentrancy; an optional specification of the entity bean's cmp-version; an optional specification of the entity bean's abstract schema name; an optional list of container-managed fields; an optional specification of the primary key field; an optional declaration of the bean's environment entries; an optional declaration of the bean's EJB references; an optional declaration of the bean's local EJB references; an optional declaration of the security role references; an optional declaration of the security identity to be used for the execution of the bean's methods; an optional declaration of the bean's resource manager connection factory references; an optional declaration of the bean's resource environment references; an optional set of query declarations for finder and select methods for an entity bean with cmp-version 2.x.
 * The optional abstract-schema-name element must be specified for an entity bean with container managed persistence and cmp-version 2.x. The optional primkey-field may be present in the descriptor if the entity's persistence-type is Container. The optional cmp-version element may be present in the descriptor if the entity's persistence-type is Container. If the persistence-type is Container and the cmp-version element is not specified, its value defaults to 2.x.
 * The optional home and remote elements must be specified if the entity bean cmp-version is 1.x.
 * The optional local-home and local elements must be specified if the entity bean has a local home and local interface.
 * The optional query elements must be present if the persistence-type is Container and the cmp-version is 2.x and query methods other than findByPrimaryKey have been defined for the entity bean.
 * The other elements that are optional are "optional" in the sense that they are omitted if the lists represented by them are empty. At least one cmp-field element must be present in the descriptor if
 * the entity's persistence-type is Container and the cmp-version is 1.x, and none must not be present if the entity's persistence-type is Bean.
 */
public class EntityImpl extends EnterpriseBeanImpl implements Entity, EnterpriseBean {

	/**
	 * The default value of the '{@link #isReentrant() <em>Reentrant</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isReentrant()
	 * @generated
	 * @ordered
	 */
	protected static final boolean REENTRANT_EDEFAULT = false;

	/**
	 * The cached value of the '{@link #isReentrant() <em>Reentrant</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isReentrant()
	 * @generated
	 * @ordered
	 */
	protected boolean reentrant = REENTRANT_EDEFAULT;

	/**
	 * This is true if the Reentrant attribute has been set.
	 * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
  protected boolean reentrantESet = false;

	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	protected JavaClass primaryKey = null;
	public EntityImpl() {
		super();
	}
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected EClass eStaticClass() {
		return EjbPackage.eINSTANCE.getEntity();
	}

public String getPrimaryKeyName() {
	getPrimaryKey();
	return primaryKey == null ? null : primaryKey.getQualifiedName();
}
/**
 * Return true if @aJavaClass as equal to the
 * home, remote, bean class, or key class.
 */
public boolean hasJavaReference(JavaClass aJavaClass) {
	boolean result = super.hasJavaReference(aJavaClass);
	if (!result && aJavaClass != null)
		result = aJavaClass.equals(getPrimaryKey());
	return result;
}
//Return false by default
public boolean isContainerManagedEntity() {
	return false;
}
public boolean isEntity() {
	return true;
}
protected boolean primGetIsReentrant() {
   return isReentrant();
}
protected JavaClass primGetPrimaryKey() {
	return getPrimaryKey();
}

public java.lang.Object primRefValue(EObject feature) {
	switch (eDerivedStructuralFeatureID(((EStructuralFeature)feature).getFeatureID(), ((EStructuralFeature)feature).getContainerClass())) {
		case EjbPackage.ENTITY__REENTRANT: return new Boolean(isReentrant());
		case EjbPackage.ENTITY__PRIMARY_KEY: return getPrimaryKey();
	}
	return super.primRefValue(feature);
}
public void setPrimaryKeyName(String primaryKeyName) {
	eSet(EjbPackage.eINSTANCE.getEntity_PrimaryKey(), createClassRef(primaryKeyName));
}
	/**
	 * @generated This field/method will be replaced during code generation 
	 * The reentrant element specifies whether an entity bean is reentrant or not.

	 */
	public boolean isReentrant() {
		return reentrant;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setReentrant(boolean newReentrant) {
		boolean oldReentrant = reentrant;
		reentrant = newReentrant;
		boolean oldReentrantESet = reentrantESet;
		reentrantESet = true;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, EjbPackage.ENTITY__REENTRANT, oldReentrant, reentrant, !oldReentrantESet));
	}

	/**
	 * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
	 * @generated
	 */
  public void unsetReentrant() {
		boolean oldReentrant = reentrant;
		boolean oldReentrantESet = reentrantESet;
		reentrant = REENTRANT_EDEFAULT;
		reentrantESet = false;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.UNSET, EjbPackage.ENTITY__REENTRANT, oldReentrant, REENTRANT_EDEFAULT, oldReentrantESet));
	}

	/**
	 * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
	 * @generated
	 */
  public boolean isSetReentrant() {
		return reentrantESet;
	}

    /**
     * Handwritten version of getPrimaryKey():
     */
 
 
    public JavaClass getPrimaryKey()  { 
       if (primaryKey != null && primaryKey.eIsProxy()) { 
          JavaClass oldPrimaryKey = primaryKey; 
          JavaClass resolved = (JavaClass)EcoreUtil.resolve(primaryKey, this); 
          resolved(this.primaryKey, resolved);
          this.primaryKey = resolved;
          if (primaryKey != oldPrimaryKey) { 
             if (eNotificationRequired()) 
                eNotify(new ENotificationImpl(this, Notification.RESOLVE, EjbPackage.ENTERPRISE_BEAN__EJB_CLASS, oldPrimaryKey, primaryKey)); 
          } 
       } 
       return primaryKey; 
    } 
 
         

	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	public void setPrimaryKey(JavaClass newPrimaryKey) {
		JavaClass oldPrimaryKey = primaryKey;
		primaryKey = newPrimaryKey;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, EjbPackage.ENTITY__PRIMARY_KEY, oldPrimaryKey, primaryKey));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain eInverseAdd(InternalEObject otherEnd, int featureID, Class baseClass, NotificationChain msgs) {
		if (featureID >= 0) {
			switch (eDerivedStructuralFeatureID(featureID, baseClass)) {
				case EjbPackage.ENTITY__EJB_JAR:
					if (eContainer != null)
						msgs = eBasicRemoveFromContainer(msgs);
					return eBasicSetContainer(otherEnd, EjbPackage.ENTITY__EJB_JAR, msgs);
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
				case EjbPackage.ENTITY__ICONS:
					return ((InternalEList)getIcons()).basicRemove(otherEnd, msgs);
				case EjbPackage.ENTITY__DISPLAY_NAMES:
					return ((InternalEList)getDisplayNames()).basicRemove(otherEnd, msgs);
				case EjbPackage.ENTITY__DESCRIPTIONS:
					return ((InternalEList)getDescriptions()).basicRemove(otherEnd, msgs);
				case EjbPackage.ENTITY__ENVIRONMENT_PROPERTIES:
					return ((InternalEList)getEnvironmentProperties()).basicRemove(otherEnd, msgs);
				case EjbPackage.ENTITY__RESOURCE_REFS:
					return ((InternalEList)getResourceRefs()).basicRemove(otherEnd, msgs);
				case EjbPackage.ENTITY__EJB_REFS:
					return ((InternalEList)getEjbRefs()).basicRemove(otherEnd, msgs);
				case EjbPackage.ENTITY__RESOURCE_ENV_REFS:
					return ((InternalEList)getResourceEnvRefs()).basicRemove(otherEnd, msgs);
				case EjbPackage.ENTITY__EJB_LOCAL_REFS:
					return ((InternalEList)getEjbLocalRefs()).basicRemove(otherEnd, msgs);
				case EjbPackage.ENTITY__MESSAGE_DESTINATION_REFS:
					return ((InternalEList)getMessageDestinationRefs()).basicRemove(otherEnd, msgs);
				case EjbPackage.ENTITY__SERVICE_REFS:
					return ((InternalEList)getServiceRefs()).basicRemove(otherEnd, msgs);
				case EjbPackage.ENTITY__SECURITY_ROLE_REFS:
					return ((InternalEList)getSecurityRoleRefs()).basicRemove(otherEnd, msgs);
				case EjbPackage.ENTITY__EJB_JAR:
					return eBasicSetContainer(null, EjbPackage.ENTITY__EJB_JAR, msgs);
				case EjbPackage.ENTITY__SECURITY_IDENTITY:
					return basicSetSecurityIdentity(null, msgs);
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
				case EjbPackage.ENTITY__EJB_JAR:
					return eContainer.eInverseRemove(this, EjbPackage.EJB_JAR__ENTERPRISE_BEANS, EJBJar.class, msgs);
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
			case EjbPackage.ENTITY__ICONS:
				return getIcons();
			case EjbPackage.ENTITY__DISPLAY_NAMES:
				return getDisplayNames();
			case EjbPackage.ENTITY__DESCRIPTIONS:
				return getDescriptions();
			case EjbPackage.ENTITY__SMALL_ICON:
				return getSmallIcon();
			case EjbPackage.ENTITY__LARGE_ICON:
				return getLargeIcon();
			case EjbPackage.ENTITY__DESCRIPTION:
				return getDescription();
			case EjbPackage.ENTITY__DISPLAY_NAME:
				return getDisplayName();
			case EjbPackage.ENTITY__ENVIRONMENT_PROPERTIES:
				return getEnvironmentProperties();
			case EjbPackage.ENTITY__RESOURCE_REFS:
				return getResourceRefs();
			case EjbPackage.ENTITY__EJB_REFS:
				return getEjbRefs();
			case EjbPackage.ENTITY__RESOURCE_ENV_REFS:
				return getResourceEnvRefs();
			case EjbPackage.ENTITY__EJB_LOCAL_REFS:
				return getEjbLocalRefs();
			case EjbPackage.ENTITY__MESSAGE_DESTINATION_REFS:
				return getMessageDestinationRefs();
			case EjbPackage.ENTITY__SERVICE_REFS:
				return getServiceRefs();
			case EjbPackage.ENTITY__NAME:
				return getName();
			case EjbPackage.ENTITY__SECURITY_ROLE_REFS:
				return getSecurityRoleRefs();
			case EjbPackage.ENTITY__EJB_CLASS:
				if (resolve) return getEjbClass();
				return basicGetEjbClass();
			case EjbPackage.ENTITY__HOME_INTERFACE:
				if (resolve) return getHomeInterface();
				return basicGetHomeInterface();
			case EjbPackage.ENTITY__REMOTE_INTERFACE:
				if (resolve) return getRemoteInterface();
				return basicGetRemoteInterface();
			case EjbPackage.ENTITY__EJB_JAR:
				return getEjbJar();
			case EjbPackage.ENTITY__SECURITY_IDENTITY:
				return getSecurityIdentity();
			case EjbPackage.ENTITY__LOCAL_HOME_INTERFACE:
				if (resolve) return getLocalHomeInterface();
				return basicGetLocalHomeInterface();
			case EjbPackage.ENTITY__LOCAL_INTERFACE:
				if (resolve) return getLocalInterface();
				return basicGetLocalInterface();
			case EjbPackage.ENTITY__REENTRANT:
				return isReentrant() ? Boolean.TRUE : Boolean.FALSE;
			case EjbPackage.ENTITY__PRIMARY_KEY:
				if (resolve) return getPrimaryKey();
				return basicGetPrimaryKey();
		}
		return eDynamicGet(eFeature, resolve);
	}

	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	public boolean eIsSet(EStructuralFeature eFeature) {
		switch (eDerivedStructuralFeatureID(eFeature)) {
			case EjbPackage.ENTITY__ICONS:
				return icons != null && !icons.isEmpty();
			case EjbPackage.ENTITY__DISPLAY_NAMES:
				return displayNames != null && !displayNames.isEmpty();
			case EjbPackage.ENTITY__DESCRIPTIONS:
				return descriptions != null && !descriptions.isEmpty();
			case EjbPackage.ENTITY__SMALL_ICON:
				return SMALL_ICON_EDEFAULT == null ? smallIcon != null : !SMALL_ICON_EDEFAULT.equals(smallIcon);
			case EjbPackage.ENTITY__LARGE_ICON:
				return LARGE_ICON_EDEFAULT == null ? largeIcon != null : !LARGE_ICON_EDEFAULT.equals(largeIcon);
			case EjbPackage.ENTITY__DESCRIPTION:
				return DESCRIPTION_EDEFAULT == null ? description != null : !DESCRIPTION_EDEFAULT.equals(description);
			case EjbPackage.ENTITY__DISPLAY_NAME:
				return DISPLAY_NAME_EDEFAULT == null ? displayName != null : !DISPLAY_NAME_EDEFAULT.equals(displayName);
			case EjbPackage.ENTITY__ENVIRONMENT_PROPERTIES:
				return environmentProperties != null && !environmentProperties.isEmpty();
			case EjbPackage.ENTITY__RESOURCE_REFS:
				return resourceRefs != null && !resourceRefs.isEmpty();
			case EjbPackage.ENTITY__EJB_REFS:
				return ejbRefs != null && !ejbRefs.isEmpty();
			case EjbPackage.ENTITY__RESOURCE_ENV_REFS:
				return resourceEnvRefs != null && !resourceEnvRefs.isEmpty();
			case EjbPackage.ENTITY__EJB_LOCAL_REFS:
				return ejbLocalRefs != null && !ejbLocalRefs.isEmpty();
			case EjbPackage.ENTITY__MESSAGE_DESTINATION_REFS:
				return messageDestinationRefs != null && !messageDestinationRefs.isEmpty();
			case EjbPackage.ENTITY__SERVICE_REFS:
				return serviceRefs != null && !serviceRefs.isEmpty();
			case EjbPackage.ENTITY__NAME:
				return NAME_EDEFAULT == null ? name != null : !NAME_EDEFAULT.equals(name);
			case EjbPackage.ENTITY__SECURITY_ROLE_REFS:
				return securityRoleRefs != null && !securityRoleRefs.isEmpty();
			case EjbPackage.ENTITY__EJB_CLASS:
				return ejbClass != null;
			case EjbPackage.ENTITY__HOME_INTERFACE:
				return homeInterface != null;
			case EjbPackage.ENTITY__REMOTE_INTERFACE:
				return remoteInterface != null;
			case EjbPackage.ENTITY__EJB_JAR:
				return getEjbJar() != null;
			case EjbPackage.ENTITY__SECURITY_IDENTITY:
				return securityIdentity != null;
			case EjbPackage.ENTITY__LOCAL_HOME_INTERFACE:
				return localHomeInterface != null;
			case EjbPackage.ENTITY__LOCAL_INTERFACE:
				return localInterface != null;
			case EjbPackage.ENTITY__REENTRANT:
				return isSetReentrant();
			case EjbPackage.ENTITY__PRIMARY_KEY:
				return primaryKey != null;
		}
		return eDynamicIsSet(eFeature);
	}

	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	public void eSet(EStructuralFeature eFeature, Object newValue) {
		switch (eDerivedStructuralFeatureID(eFeature)) {
			case EjbPackage.ENTITY__ICONS:
				getIcons().clear();
				getIcons().addAll((Collection)newValue);
				return;
			case EjbPackage.ENTITY__DISPLAY_NAMES:
				getDisplayNames().clear();
				getDisplayNames().addAll((Collection)newValue);
				return;
			case EjbPackage.ENTITY__DESCRIPTIONS:
				getDescriptions().clear();
				getDescriptions().addAll((Collection)newValue);
				return;
			case EjbPackage.ENTITY__SMALL_ICON:
				setSmallIcon((String)newValue);
				return;
			case EjbPackage.ENTITY__LARGE_ICON:
				setLargeIcon((String)newValue);
				return;
			case EjbPackage.ENTITY__DESCRIPTION:
				setDescription((String)newValue);
				return;
			case EjbPackage.ENTITY__DISPLAY_NAME:
				setDisplayName((String)newValue);
				return;
			case EjbPackage.ENTITY__ENVIRONMENT_PROPERTIES:
				getEnvironmentProperties().clear();
				getEnvironmentProperties().addAll((Collection)newValue);
				return;
			case EjbPackage.ENTITY__RESOURCE_REFS:
				getResourceRefs().clear();
				getResourceRefs().addAll((Collection)newValue);
				return;
			case EjbPackage.ENTITY__EJB_REFS:
				getEjbRefs().clear();
				getEjbRefs().addAll((Collection)newValue);
				return;
			case EjbPackage.ENTITY__RESOURCE_ENV_REFS:
				getResourceEnvRefs().clear();
				getResourceEnvRefs().addAll((Collection)newValue);
				return;
			case EjbPackage.ENTITY__EJB_LOCAL_REFS:
				getEjbLocalRefs().clear();
				getEjbLocalRefs().addAll((Collection)newValue);
				return;
			case EjbPackage.ENTITY__MESSAGE_DESTINATION_REFS:
				getMessageDestinationRefs().clear();
				getMessageDestinationRefs().addAll((Collection)newValue);
				return;
			case EjbPackage.ENTITY__SERVICE_REFS:
				getServiceRefs().clear();
				getServiceRefs().addAll((Collection)newValue);
				return;
			case EjbPackage.ENTITY__NAME:
				setName((String)newValue);
				return;
			case EjbPackage.ENTITY__SECURITY_ROLE_REFS:
				getSecurityRoleRefs().clear();
				getSecurityRoleRefs().addAll((Collection)newValue);
				return;
			case EjbPackage.ENTITY__EJB_CLASS:
				setEjbClass((JavaClass)newValue);
				return;
			case EjbPackage.ENTITY__HOME_INTERFACE:
				setHomeInterface((JavaClass)newValue);
				return;
			case EjbPackage.ENTITY__REMOTE_INTERFACE:
				setRemoteInterface((JavaClass)newValue);
				return;
			case EjbPackage.ENTITY__EJB_JAR:
				setEjbJar((EJBJar)newValue);
				return;
			case EjbPackage.ENTITY__SECURITY_IDENTITY:
				setSecurityIdentity((SecurityIdentity)newValue);
				return;
			case EjbPackage.ENTITY__LOCAL_HOME_INTERFACE:
				setLocalHomeInterface((JavaClass)newValue);
				return;
			case EjbPackage.ENTITY__LOCAL_INTERFACE:
				setLocalInterface((JavaClass)newValue);
				return;
			case EjbPackage.ENTITY__REENTRANT:
				setReentrant(((Boolean)newValue).booleanValue());
				return;
			case EjbPackage.ENTITY__PRIMARY_KEY:
				setPrimaryKey((JavaClass)newValue);
				return;
		}
		eDynamicSet(eFeature, newValue);
	}

	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	public void eUnset(EStructuralFeature eFeature) {
		switch (eDerivedStructuralFeatureID(eFeature)) {
			case EjbPackage.ENTITY__ICONS:
				getIcons().clear();
				return;
			case EjbPackage.ENTITY__DISPLAY_NAMES:
				getDisplayNames().clear();
				return;
			case EjbPackage.ENTITY__DESCRIPTIONS:
				getDescriptions().clear();
				return;
			case EjbPackage.ENTITY__SMALL_ICON:
				setSmallIcon(SMALL_ICON_EDEFAULT);
				return;
			case EjbPackage.ENTITY__LARGE_ICON:
				setLargeIcon(LARGE_ICON_EDEFAULT);
				return;
			case EjbPackage.ENTITY__DESCRIPTION:
				setDescription(DESCRIPTION_EDEFAULT);
				return;
			case EjbPackage.ENTITY__DISPLAY_NAME:
				setDisplayName(DISPLAY_NAME_EDEFAULT);
				return;
			case EjbPackage.ENTITY__ENVIRONMENT_PROPERTIES:
				getEnvironmentProperties().clear();
				return;
			case EjbPackage.ENTITY__RESOURCE_REFS:
				getResourceRefs().clear();
				return;
			case EjbPackage.ENTITY__EJB_REFS:
				getEjbRefs().clear();
				return;
			case EjbPackage.ENTITY__RESOURCE_ENV_REFS:
				getResourceEnvRefs().clear();
				return;
			case EjbPackage.ENTITY__EJB_LOCAL_REFS:
				getEjbLocalRefs().clear();
				return;
			case EjbPackage.ENTITY__MESSAGE_DESTINATION_REFS:
				getMessageDestinationRefs().clear();
				return;
			case EjbPackage.ENTITY__SERVICE_REFS:
				getServiceRefs().clear();
				return;
			case EjbPackage.ENTITY__NAME:
				setName(NAME_EDEFAULT);
				return;
			case EjbPackage.ENTITY__SECURITY_ROLE_REFS:
				getSecurityRoleRefs().clear();
				return;
			case EjbPackage.ENTITY__EJB_CLASS:
				setEjbClass((JavaClass)null);
				return;
			case EjbPackage.ENTITY__HOME_INTERFACE:
				setHomeInterface((JavaClass)null);
				return;
			case EjbPackage.ENTITY__REMOTE_INTERFACE:
				setRemoteInterface((JavaClass)null);
				return;
			case EjbPackage.ENTITY__EJB_JAR:
				setEjbJar((EJBJar)null);
				return;
			case EjbPackage.ENTITY__SECURITY_IDENTITY:
				setSecurityIdentity((SecurityIdentity)null);
				return;
			case EjbPackage.ENTITY__LOCAL_HOME_INTERFACE:
				setLocalHomeInterface((JavaClass)null);
				return;
			case EjbPackage.ENTITY__LOCAL_INTERFACE:
				setLocalInterface((JavaClass)null);
				return;
			case EjbPackage.ENTITY__REENTRANT:
				unsetReentrant();
				return;
			case EjbPackage.ENTITY__PRIMARY_KEY:
				setPrimaryKey((JavaClass)null);
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
		result.append(" (reentrant: "); //$NON-NLS-1$
		if (reentrantESet) result.append(reentrant); else result.append("<unset>"); //$NON-NLS-1$
		result.append(')');
		return result.toString();
	}

	/**
	 * @generated This field/method will be replaced during code generation 
	 * The primkey-field element is used to specify the name of the primary key field
	 * for an entity with container-managed persistence.  The primkey-field must be
	 * one of the fields declared in the cmp-field
	 * element, and the type of the field must be the same as the primary key type. 
	 * The primkey-field element is not used if the primary key maps to multiple
	 * container-managed fields (i.e. the key is a compound key). In this case, the
	 * fields of the primary key class must be public, and their names must correspond 
	 * to the field names of the entity bean class that comprise the key.
	 * Examples:<prim-key-class>java.lang.String<//prim-key-class>
	 * 	<prim-key-class>com.wombat.empl.EmployeeID<//prim-key-class>
	 * 	<prim-key-class>java.lang.Object<//prim-key-class>

	 */
	public JavaClass getPrimaryKeyGen() {
		if (primaryKey != null && primaryKey.eIsProxy()) {
			JavaClass oldPrimaryKey = primaryKey;
			primaryKey = (JavaClass)eResolveProxy((InternalEObject)primaryKey);
			if (primaryKey != oldPrimaryKey) {
				if (eNotificationRequired())
					eNotify(new ENotificationImpl(this, Notification.RESOLVE, EjbPackage.ENTITY__PRIMARY_KEY, oldPrimaryKey, primaryKey));
			}
		}
		return primaryKey;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public JavaClass basicGetPrimaryKey() {
		return primaryKey;
	}

}







