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
package org.eclipse.jst.j2ee.internal.common.impl;

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
import org.eclipse.jem.java.JavaClass;
import org.eclipse.jem.java.JavaRefFactory;
import org.eclipse.jst.j2ee.common.CommonPackage;
import org.eclipse.jst.j2ee.common.Description;
import org.eclipse.jst.j2ee.common.ResourceEnvRef;

/**
 * The resource-env-ref element contains a declaration of an enterprise bean's reference to an administered object associated with a resource in the enterprise bean's environment. It consists of an optional description, the resource environment reference name, and an indication of the resource environment reference type expected by the enterprise bean code.
 * 
 * Examples:
 * 
 * <resource-env-ref>
 *     <resource-env-ref-name>jms//StockQueue<//resource-env-ref-name>
 *     <resource-env-ref-type>javax.jms.Queue<//resource-env-ref-type>
 * <//resource-env-ref>
 */
public class ResourceEnvRefImpl extends EObjectImpl implements ResourceEnvRef, EObject{

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
	 * @generated This field/method will be replaced during code generation.
	 */
	protected JavaClass type = null;
	/**
	 * The cached value of the '{@link #getDescriptions() <em>Descriptions</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getDescriptions()
	 * @generated
	 * @ordered
	 */
	protected EList descriptions = null;

	public ResourceEnvRefImpl() {
		super();
	}
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected EClass eStaticClass() {
		return CommonPackage.eINSTANCE.getResourceEnvRef();
	}

	/**
	 * createClassRef - return a JavaURL reference to the named Java class
	 */
	public JavaClass createClassRef(String targetName) {
		return JavaRefFactory.eINSTANCE.createClassRef(targetName);
	}

	public String getTypeName() {
		getType();
		return type == null ? null : type.getQualifiedName();
	}

	public void setTypeName(String className) {
		eSet(CommonPackage.eINSTANCE.getResourceEnvRef_Type(), createClassRef(className));
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
	 *            Specifies the name of a resource environment reference; its
	 *            value is the environment entry name used in the enterprise
	 *            bean code.
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
			eNotify(new ENotificationImpl(this, Notification.SET, CommonPackage.RESOURCE_ENV_REF__NAME, oldName, name));
	}

	/**
	 * @generated This field/method will be replaced during code generation 
	 * Specifies the type of a resource environment reference.  See the class description for an example.
	 */
	public JavaClass getType() {
		if (type != null && type.eIsProxy()) {
			JavaClass oldType = type;
			type = (JavaClass)eResolveProxy((InternalEObject)type);
			if (type != oldType) {
				if (eNotificationRequired())
					eNotify(new ENotificationImpl(this, Notification.RESOLVE, CommonPackage.RESOURCE_ENV_REF__TYPE, oldType, type));
			}
		}
		return type;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public JavaClass basicGetType() {
		return type;
	}

	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	public void setType(JavaClass newType) {
		JavaClass oldType = type;
		type = newType;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, CommonPackage.RESOURCE_ENV_REF__TYPE, oldType, type));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList getDescriptions() {
		if (descriptions == null) {
			descriptions = new EObjectContainmentEList(Description.class, this, CommonPackage.RESOURCE_ENV_REF__DESCRIPTIONS);
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
				case CommonPackage.RESOURCE_ENV_REF__DESCRIPTIONS:
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
			case CommonPackage.RESOURCE_ENV_REF__DESCRIPTION:
				return getDescription();
			case CommonPackage.RESOURCE_ENV_REF__NAME:
				return getName();
			case CommonPackage.RESOURCE_ENV_REF__TYPE:
				if (resolve) return getType();
				return basicGetType();
			case CommonPackage.RESOURCE_ENV_REF__DESCRIPTIONS:
				return getDescriptions();
		}
		return eDynamicGet(eFeature, resolve);
	}

	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	public boolean eIsSet(EStructuralFeature eFeature) {
		switch (eDerivedStructuralFeatureID(eFeature)) {
			case CommonPackage.RESOURCE_ENV_REF__DESCRIPTION:
				return DESCRIPTION_EDEFAULT == null ? description != null : !DESCRIPTION_EDEFAULT.equals(description);
			case CommonPackage.RESOURCE_ENV_REF__NAME:
				return NAME_EDEFAULT == null ? name != null : !NAME_EDEFAULT.equals(name);
			case CommonPackage.RESOURCE_ENV_REF__TYPE:
				return type != null;
			case CommonPackage.RESOURCE_ENV_REF__DESCRIPTIONS:
				return descriptions != null && !descriptions.isEmpty();
		}
		return eDynamicIsSet(eFeature);
	}

	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	public void eSet(EStructuralFeature eFeature, Object newValue) {
		switch (eDerivedStructuralFeatureID(eFeature)) {
			case CommonPackage.RESOURCE_ENV_REF__DESCRIPTION:
				setDescription((String)newValue);
				return;
			case CommonPackage.RESOURCE_ENV_REF__NAME:
				setName((String)newValue);
				return;
			case CommonPackage.RESOURCE_ENV_REF__TYPE:
				setType((JavaClass)newValue);
				return;
			case CommonPackage.RESOURCE_ENV_REF__DESCRIPTIONS:
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
			case CommonPackage.RESOURCE_ENV_REF__DESCRIPTION:
				setDescription(DESCRIPTION_EDEFAULT);
				return;
			case CommonPackage.RESOURCE_ENV_REF__NAME:
				setName(NAME_EDEFAULT);
				return;
			case CommonPackage.RESOURCE_ENV_REF__TYPE:
				setType((JavaClass)null);
				return;
			case CommonPackage.RESOURCE_ENV_REF__DESCRIPTIONS:
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
		result.append(", name: "); //$NON-NLS-1$
		result.append(name);
		result.append(')');
		return result.toString();
	}

}






