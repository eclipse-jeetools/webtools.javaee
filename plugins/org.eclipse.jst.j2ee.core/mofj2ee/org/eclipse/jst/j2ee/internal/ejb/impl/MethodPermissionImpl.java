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

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

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
import org.eclipse.emf.ecore.util.EObjectResolvingEList;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.emf.ecore.util.InternalEList;
import org.eclipse.jst.j2ee.common.Description;
import org.eclipse.jst.j2ee.common.SecurityRole;
import org.eclipse.jst.j2ee.ejb.AssemblyDescriptor;
import org.eclipse.jst.j2ee.ejb.EjbPackage;
import org.eclipse.jst.j2ee.ejb.EnterpriseBean;
import org.eclipse.jst.j2ee.ejb.MethodElement;
import org.eclipse.jst.j2ee.ejb.MethodPermission;


/**
 * The method-permission element specifies that one or more security roles are allowed to invoke one or more enterprise bean methods. The method-permission element consists of an optional description, a list of security role names, and a list of method elements.  The security roles used in the method-permission element must be defined in the security-role element of the deployment descriptor, and the methods must be methods defined in the enterprise bean's remote and//or home interfaces.
 */
public class MethodPermissionImpl extends EObjectImpl implements MethodPermission, EObject {


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
	 * The default value of the '{@link #isUnchecked() <em>Unchecked</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isUnchecked()
	 * @generated
	 * @ordered
	 */
	protected static final boolean UNCHECKED_EDEFAULT = false;

	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	protected boolean unchecked = UNCHECKED_EDEFAULT;
	/**
	 * This is true if the Unchecked attribute has been set.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	protected boolean uncheckedESet = false;

	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	protected EList roles = null;
	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	protected EList methodElements = null;
	/**
	 * The cached value of the '{@link #getDescriptions() <em>Descriptions</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getDescriptions()
	 * @generated
	 * @ordered
	 */
	protected EList descriptions = null;

	public MethodPermissionImpl() {
		super();
	}
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected EClass eStaticClass() {
		return EjbPackage.eINSTANCE.getMethodPermission();
	}

public List getMethodElements(EnterpriseBean anEJB) {
	if (anEJB == null) return Collections.EMPTY_LIST;
	List result = new ArrayList();
	List elements = getMethodElements();
	MethodElement me;
	for (int i = 0; i < elements.size(); i++){
		me = (MethodElement) elements.get(i);
		if (anEJB.equals(me.getEnterpriseBean()))
			result.add(me);			
	}
	return result;
}
public SecurityRole getSecurityRole(String aRoleName) {
	if (aRoleName == null) return null;
	List myRoles = getRoles();
	int size = myRoles.size();
	SecurityRole role;
	for (int i = 0; i < size; i++){
		role = (SecurityRole) myRoles.get(i);
		if (aRoleName.equals(role.getRoleName()))
			return role;
	}
	return null;
}
/**
 * Return true if this permission contains @aSecurityRole
 */
public boolean hasSecurityRole(SecurityRole aSecurityRole) {
	if (aSecurityRole == null)
		return false;
	List myRoles = getRoles();
	int size = myRoles.size();
	for (int i = 0; i < size; i++) {
		if (aSecurityRole == myRoles.get(i))
			return true;
	}
	return false;
}
public boolean hasSecurityRole(String aRoleName) {
	return getSecurityRole(aRoleName) != null;
}
/**
 * Return true if this MethodPermission and @anotherMethodPermission
 * have the same roles.
 */
public boolean isEquivalent(MethodPermission anotherMethodPermission) {
	if (anotherMethodPermission == null) return false;
	return getRoles().size() == anotherMethodPermission.getRoles().size() &&
			getRoles().containsAll(anotherMethodPermission.getRoles());
}
	/**
	 * @generated This field/method will be replaced during code generation 
	 * The description element is used by the ejb-jar file producer to provide text describing the parent element.  The description element should include any information that the ejb-jar file producer wants to provide to the consumer of the ejb-jar file (i.e. to the Deployer). Typically, the tools used by the ejb-jar file consumer will display the description when processing the parent element.
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
			eNotify(new ENotificationImpl(this, Notification.SET, EjbPackage.METHOD_PERMISSION__DESCRIPTION, oldDescription, description));
	}

	/**
	 * @generated This field/method will be replaced during code generation 
	 */
	public boolean isUnchecked() {
		return unchecked;
	}

	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	public void setUnchecked(boolean newUnchecked) {
		boolean oldUnchecked = unchecked;
		unchecked = newUnchecked;
		boolean oldUncheckedESet = uncheckedESet;
		uncheckedESet = true;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, EjbPackage.METHOD_PERMISSION__UNCHECKED, oldUnchecked, unchecked, !oldUncheckedESet));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void unsetUnchecked() {
		boolean oldUnchecked = unchecked;
		boolean oldUncheckedESet = uncheckedESet;
		unchecked = UNCHECKED_EDEFAULT;
		uncheckedESet = false;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.UNSET, EjbPackage.METHOD_PERMISSION__UNCHECKED, oldUnchecked, UNCHECKED_EDEFAULT, oldUncheckedESet));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean isSetUnchecked() {
		return uncheckedESet;
	}

	/**
	 * @generated This field/method will be replaced during code generation 
	 */
	public AssemblyDescriptor getAssemblyDescriptor() {
		if (eContainerFeatureID != EjbPackage.METHOD_PERMISSION__ASSEMBLY_DESCRIPTOR) return null;
		return (AssemblyDescriptor)eContainer;
	}

	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	public void setAssemblyDescriptor(AssemblyDescriptor newAssemblyDescriptor) {
		if (newAssemblyDescriptor != eContainer || (eContainerFeatureID != EjbPackage.METHOD_PERMISSION__ASSEMBLY_DESCRIPTOR && newAssemblyDescriptor != null)) {
			if (EcoreUtil.isAncestor(this, newAssemblyDescriptor))
				throw new IllegalArgumentException("Recursive containment not allowed for " + toString());
			NotificationChain msgs = null;
			if (eContainer != null)
				msgs = eBasicRemoveFromContainer(msgs);
			if (newAssemblyDescriptor != null)
				msgs = ((InternalEObject)newAssemblyDescriptor).eInverseAdd(this, EjbPackage.ASSEMBLY_DESCRIPTOR__METHOD_PERMISSIONS, AssemblyDescriptor.class, msgs);
			msgs = eBasicSetContainer((InternalEObject)newAssemblyDescriptor, EjbPackage.METHOD_PERMISSION__ASSEMBLY_DESCRIPTOR, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, EjbPackage.METHOD_PERMISSION__ASSEMBLY_DESCRIPTOR, newAssemblyDescriptor, newAssemblyDescriptor));
	}

	/**
	 * @generated This field/method will be replaced during code generation 
	 */
	public EList getRoles() {
		if (roles == null) {
			roles = new EObjectResolvingEList(SecurityRole.class, this, EjbPackage.METHOD_PERMISSION__ROLES);
		}
		return roles;
	}

	/**
	 * @generated This field/method will be replaced during code generation 
	 */
	public EList getMethodElements() {
		if (methodElements == null) {
			methodElements = new EObjectContainmentEList(MethodElement.class, this, EjbPackage.METHOD_PERMISSION__METHOD_ELEMENTS);
		}
		return methodElements;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList getDescriptions() {
		if (descriptions == null) {
			descriptions = new EObjectContainmentEList(Description.class, this, EjbPackage.METHOD_PERMISSION__DESCRIPTIONS);
		}
		return descriptions;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain eInverseAdd(InternalEObject otherEnd, int featureID, Class baseClass, NotificationChain msgs) {
		if (featureID >= 0) {
			switch (eDerivedStructuralFeatureID(featureID, baseClass)) {
				case EjbPackage.METHOD_PERMISSION__ASSEMBLY_DESCRIPTOR:
					if (eContainer != null)
						msgs = eBasicRemoveFromContainer(msgs);
					return eBasicSetContainer(otherEnd, EjbPackage.METHOD_PERMISSION__ASSEMBLY_DESCRIPTOR, msgs);
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
				case EjbPackage.METHOD_PERMISSION__ASSEMBLY_DESCRIPTOR:
					return eBasicSetContainer(null, EjbPackage.METHOD_PERMISSION__ASSEMBLY_DESCRIPTOR, msgs);
				case EjbPackage.METHOD_PERMISSION__METHOD_ELEMENTS:
					return ((InternalEList)getMethodElements()).basicRemove(otherEnd, msgs);
				case EjbPackage.METHOD_PERMISSION__DESCRIPTIONS:
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
	public NotificationChain eBasicRemoveFromContainer(NotificationChain msgs) {
		if (eContainerFeatureID >= 0) {
			switch (eContainerFeatureID) {
				case EjbPackage.METHOD_PERMISSION__ASSEMBLY_DESCRIPTOR:
					return eContainer.eInverseRemove(this, EjbPackage.ASSEMBLY_DESCRIPTOR__METHOD_PERMISSIONS, AssemblyDescriptor.class, msgs);
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
			case EjbPackage.METHOD_PERMISSION__DESCRIPTION:
				return getDescription();
			case EjbPackage.METHOD_PERMISSION__UNCHECKED:
				return isUnchecked() ? Boolean.TRUE : Boolean.FALSE;
			case EjbPackage.METHOD_PERMISSION__ASSEMBLY_DESCRIPTOR:
				return getAssemblyDescriptor();
			case EjbPackage.METHOD_PERMISSION__ROLES:
				return getRoles();
			case EjbPackage.METHOD_PERMISSION__METHOD_ELEMENTS:
				return getMethodElements();
			case EjbPackage.METHOD_PERMISSION__DESCRIPTIONS:
				return getDescriptions();
		}
		return eDynamicGet(eFeature, resolve);
	}

	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	public boolean eIsSet(EStructuralFeature eFeature) {
		switch (eDerivedStructuralFeatureID(eFeature)) {
			case EjbPackage.METHOD_PERMISSION__DESCRIPTION:
				return DESCRIPTION_EDEFAULT == null ? description != null : !DESCRIPTION_EDEFAULT.equals(description);
			case EjbPackage.METHOD_PERMISSION__UNCHECKED:
				return isSetUnchecked();
			case EjbPackage.METHOD_PERMISSION__ASSEMBLY_DESCRIPTOR:
				return getAssemblyDescriptor() != null;
			case EjbPackage.METHOD_PERMISSION__ROLES:
				return roles != null && !roles.isEmpty();
			case EjbPackage.METHOD_PERMISSION__METHOD_ELEMENTS:
				return methodElements != null && !methodElements.isEmpty();
			case EjbPackage.METHOD_PERMISSION__DESCRIPTIONS:
				return descriptions != null && !descriptions.isEmpty();
		}
		return eDynamicIsSet(eFeature);
	}

	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	public void eSet(EStructuralFeature eFeature, Object newValue) {
		switch (eDerivedStructuralFeatureID(eFeature)) {
			case EjbPackage.METHOD_PERMISSION__DESCRIPTION:
				setDescription((String)newValue);
				return;
			case EjbPackage.METHOD_PERMISSION__UNCHECKED:
				setUnchecked(((Boolean)newValue).booleanValue());
				return;
			case EjbPackage.METHOD_PERMISSION__ASSEMBLY_DESCRIPTOR:
				setAssemblyDescriptor((AssemblyDescriptor)newValue);
				return;
			case EjbPackage.METHOD_PERMISSION__ROLES:
				getRoles().clear();
				getRoles().addAll((Collection)newValue);
				return;
			case EjbPackage.METHOD_PERMISSION__METHOD_ELEMENTS:
				getMethodElements().clear();
				getMethodElements().addAll((Collection)newValue);
				return;
			case EjbPackage.METHOD_PERMISSION__DESCRIPTIONS:
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
			case EjbPackage.METHOD_PERMISSION__DESCRIPTION:
				setDescription(DESCRIPTION_EDEFAULT);
				return;
			case EjbPackage.METHOD_PERMISSION__UNCHECKED:
				unsetUnchecked();
				return;
			case EjbPackage.METHOD_PERMISSION__ASSEMBLY_DESCRIPTOR:
				setAssemblyDescriptor((AssemblyDescriptor)null);
				return;
			case EjbPackage.METHOD_PERMISSION__ROLES:
				getRoles().clear();
				return;
			case EjbPackage.METHOD_PERMISSION__METHOD_ELEMENTS:
				getMethodElements().clear();
				return;
			case EjbPackage.METHOD_PERMISSION__DESCRIPTIONS:
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
		result.append(" (description: "); //$NON-NLS-1$
		result.append(description);
		result.append(", unchecked: "); //$NON-NLS-1$
		if (uncheckedESet) result.append(unchecked); else result.append("<unset>"); //$NON-NLS-1$
		result.append(')');
		return result.toString();
	}

}





