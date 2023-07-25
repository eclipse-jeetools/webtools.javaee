/*******************************************************************************
 * Copyright (c) 2001, 2023 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 *
 * Contributors:
 * IBM Corporation - initial API and implementation
 *******************************************************************************/
package org.eclipse.jst.j2ee.ejb.internal.impl;

import java.util.Collection;
import java.util.Collections;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EClassifier;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.EAttributeImpl;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.util.EObjectContainmentEList;
import org.eclipse.emf.ecore.util.InternalEList;
import org.eclipse.jem.java.Field;
import org.eclipse.jem.java.JavaClass;
import org.eclipse.jem.java.JavaHelpers;
import org.eclipse.jem.java.JavaRefPackage;
import org.eclipse.jem.java.Method;
import org.eclipse.jst.j2ee.common.Description;
import org.eclipse.jst.j2ee.ejb.CMPAttribute;
import org.eclipse.jst.j2ee.ejb.ContainerManagedEntity;
import org.eclipse.jst.j2ee.ejb.EjbPackage;
import org.eclipse.jst.j2ee.internal.J2EEVersionConstants;

/**
 * Describes a container-managed field. The field element includes an optional description of the field, and the name of the field.

 */
public class CMPAttributeImpl extends EAttributeImpl implements CMPAttribute {

	/**
	 * The default value of the '{@link #getDescription() <em>Description</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getDescription()
	 * @generated
	 * @ordered
	 */
	protected static final String DESCRIPTION_EDEFAULT = null;

	protected transient JavaHelpers originatingType;
	protected transient CMPAttribute targetAttribute;
	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	protected String description = DESCRIPTION_EDEFAULT;
	/**
	 * The cached value of the '{@link #getDescriptions() <em>Descriptions</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getDescriptions()
	 * @generated
	 * @ordered
	 */
	protected EList descriptions = null;

	protected boolean derived;
	
	public CMPAttributeImpl() {
		super();
	}
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return EjbPackage.Literals.CMP_ATTRIBUTE;
	}

protected Field findExistingField() {
	JavaClass javaClass = getEjbClass();
	if (javaClass != null)
		return javaClass.getFieldExtended(getName());
	return null;
}
public org.eclipse.emf.ecore.EClassifier getAccessorType() {
	if (getEjbClass() != null) {
		Method getter = getEjbClass().getMethodExtended(getGetterName(), Collections.EMPTY_LIST);
		if (getter != null)
			return getter.getReturnType();
	}
	return null;
}
public ContainerManagedEntity getCMPEntity() {
	return (ContainerManagedEntity) eContainer();
}
/**
 * Return the EjbClass from the CMP Entity.
 */

protected JavaClass getEjbClass() {
	ContainerManagedEntity ejb = getCMPEntity();
	return ejb == null ? null : ejb.getEjbClass();
}
/**
 * Return the KeyClass from the CMP Entity.
 */

protected JavaClass getKeyClass() {
	ContainerManagedEntity ejb = getCMPEntity();
	return ejb == null ? null : ejb.getPrimaryKey();
}
/**
 * @deprecated - With EJB 2.0, assuming a Java field is no longer safe.
 * 		Use getType() and getName() to get the most common field info.
 *		Or use getEjbClass() to get additional Java info.
 */

 @Override
public Field getField() {
	ContainerManagedEntity ejb = getCMPEntity();
	
	if(ejb == null)
		return null;

	Field field = null;
	switch(ejb.getVersionID()) {
		case J2EEVersionConstants.EJB_1_0_ID:
		case J2EEVersionConstants.EJB_1_1_ID:
			field = findExistingField();
			//Set the field type based on the attribute type if necessary
			if (field != null && getEType() != null && field.getEType() == null)
				field.setEType(getEType()); 
			break;
		case J2EEVersionConstants.EJB_2_0_ID:
		case J2EEVersionConstants.EJB_2_1_ID: default:
			field = ((JavaRefPackage)EPackage.Registry.INSTANCE.getEPackage(JavaRefPackage.eNS_URI)).getJavaRefFactory().createField();
			field.setName(getName());
			field.setEType(getAccessorType());
			break; 
	}
	return field;
}
@Override
public String getGetterName() {
	if (name != null && name.length() > 0) {
		StringBuffer b = new StringBuffer("get"); //$NON-NLS-1$

		b.append(Character.toUpperCase(name.charAt(0))).append(name.length() > 1 ? name.substring(1) : ""); //$NON-NLS-1$
		return b.toString();
	}
	return name;
}
/**
 * Return the type of this attribute in its original form (i.e., unwrapped if
 * the type has been wrapped).
 */
@Override
public JavaHelpers getOriginatingType() {
	return originatingType;
}
@Override
public String getSetterName() {
	return "set" + Character.toUpperCase(getName().charAt(0)) + (getName().length() > 1 ? getName().substring(1) : ""); //$NON-NLS-1$ //$NON-NLS-2$
}
@Override
public JavaHelpers getType() {
	if (getEType() != null)
		return (JavaHelpers) getEType();
	// Be sensitive to CMP 2.0 beans, get type from field or get accessor type
	ContainerManagedEntity ejb = getCMPEntity();
	Resource res = eResource();
	boolean modFlag = res == null ? false : res.isModified();
	boolean deliveryFlag = eDeliver();
	try {
		eSetDeliver(false);
		if(ejb != null) {
			switch(ejb.getVersionID()) {
				case J2EEVersionConstants.EJB_1_0_ID:
				case J2EEVersionConstants.EJB_1_1_ID:
					setEType(getTypeFromBeanField());
					break;
				case J2EEVersionConstants.EJB_2_0_ID:
				case J2EEVersionConstants.EJB_2_1_ID: default:
					setEType(get20Type());
					break; 
			} 
		}
	} finally {
		eSetDeliver(deliveryFlag);
		if (res != null && res.isTrackingModification())
			res.setModified(modFlag);
	}
	return (JavaHelpers) getEType();
}
protected EClassifier getTypeFromBeanField() {
	EClassifier type = null;
	Field field = getField();
	if (field != null)
		type = field.getEType();
	if (type == null)
		type = getKeyFieldTypeFromKeyClass();
	return type;
}
public EClassifier get20Type() {
	EClassifier type = null;
	if (getEjbClass() != null) {
		String getterName = getGetterName();
		if (getterName != null && getterName.length() > 0) {
			Method getter = getEjbClass().getMethodExtended(getterName, Collections.EMPTY_LIST);
			if (getter != null)
				type = getter.getReturnType();
		}
	}
	if (type == null)
		type = getKeyFieldTypeFromKeyClass();
	return type;	
}
/**
 * @return
 */
private EClassifier getKeyFieldTypeFromKeyClass() {
	if (isKey()) {
		JavaClass key = getCMPEntity().getPrimaryKey();
		if (key != null && key.getQualifiedName() != null && !key.getQualifiedName().startsWith("java.lang")) { //$NON-NLS-1$
			Field field = key.getFieldExtended(getName());
			if (field != null)
				return field.getEType();
		} else
			return key;
	}
	return null;
}
@Override
public boolean isCMRField() {
	return false;
}
@Override
public boolean isKey() {
	return getCMPEntity() == null ? false : getCMPEntity().isKeyAttribute(this);
}

@Override
public boolean isPrimKeyField(){
	return getCMPEntity() == null ? false : getCMPEntity().getPrimKeyField() == this;
}

@Override
public void setOriginatingType(JavaHelpers newOriginatingType) {
	originatingType = newOriginatingType;
}
	/**
	 * @generated This field/method will be replaced during code generation 
	 * The description is used by the ejb-jar file producer to provide text describing the cmr field.
	 * 
	 * The description should include any information that the ejb-jar file producer wants to provide to the consumer of the ejb-jar file (i.e. to the Deployer). Typically, the tools used by the ejb-jar file consumer will display the description when processing the list of dependents.
	 */
	@Override
	public String getDescription() {
		return description;
	}

	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	@Override
	public void setDescription(String newDescription) {
		String oldDescription = description;
		description = newDescription;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, EjbPackage.CMP_ATTRIBUTE__DESCRIPTION, oldDescription, description));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EList getDescriptions() {
		if (descriptions == null) {
			descriptions = new EObjectContainmentEList(Description.class, this, EjbPackage.CMP_ATTRIBUTE__DESCRIPTIONS);
		}
		return descriptions;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case EjbPackage.CMP_ATTRIBUTE__DESCRIPTIONS:
				return ((InternalEList)getDescriptions()).basicRemove(otherEnd, msgs);
		}
		return super.eInverseRemove(otherEnd, featureID, msgs);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case EjbPackage.CMP_ATTRIBUTE__DESCRIPTION:
				return getDescription();
			case EjbPackage.CMP_ATTRIBUTE__DESCRIPTIONS:
				return getDescriptions();
		}
		return super.eGet(featureID, resolve, coreType);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void eSet(int featureID, Object newValue) {
		switch (featureID) {
			case EjbPackage.CMP_ATTRIBUTE__DESCRIPTION:
				setDescription((String)newValue);
				return;
			case EjbPackage.CMP_ATTRIBUTE__DESCRIPTIONS:
				getDescriptions().clear();
				getDescriptions().addAll((Collection)newValue);
				return;
		}
		super.eSet(featureID, newValue);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void eUnset(int featureID) {
		switch (featureID) {
			case EjbPackage.CMP_ATTRIBUTE__DESCRIPTION:
				setDescription(DESCRIPTION_EDEFAULT);
				return;
			case EjbPackage.CMP_ATTRIBUTE__DESCRIPTIONS:
				getDescriptions().clear();
				return;
		}
		super.eUnset(featureID);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public boolean eIsSet(int featureID) {
		switch (featureID) {
			case EjbPackage.CMP_ATTRIBUTE__DESCRIPTION:
				return DESCRIPTION_EDEFAULT == null ? description != null : !DESCRIPTION_EDEFAULT.equals(description);
			case EjbPackage.CMP_ATTRIBUTE__DESCRIPTIONS:
				return descriptions != null && !descriptions.isEmpty();
		}
		return super.eIsSet(featureID);
	}

	/**
	 * This field/method will be replaced during code generation.
	 */
	@Override
	public String toString() {
		return super.toString() + " :: " + getName(); //$NON-NLS-1$
	}
	/**
	 * Returns true if a targetAttribute is being used.
	 * @return Returns a boolean
	 */
	@Override
	public boolean isDerived() {
		return derived;
	}
	@Override
	public void setDerived(boolean aBoolean) {
		if (aBoolean && getEType() == null)
			getType(); //This ensures that the type is set.
		derived = aBoolean;
	}
	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	public String toStringGen() {
		if (eIsProxy()) return super.toString();

		StringBuffer result = new StringBuffer(super.toString());
		result.append(" (description: "); //$NON-NLS-1$
		result.append(description);
		result.append(')');
		return result.toString();
	}

}






