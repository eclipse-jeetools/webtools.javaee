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
package org.eclipse.jst.j2ee.ejb.impl;

import java.util.Collection;
import java.util.Collections;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EClassifier;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.EcorePackage;
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
import org.eclipse.jst.j2ee.J2EEVersionConstants;
import org.eclipse.jst.j2ee.common.Description;
import org.eclipse.jst.j2ee.ejb.CMPAttribute;
import org.eclipse.jst.j2ee.ejb.ContainerManagedEntity;
import org.eclipse.jst.j2ee.ejb.EjbPackage;

/**
 * Describes a container-managed field. The field element includes an optional description of the field, and the name of the field.

 */
public class CMPAttributeImpl extends EAttributeImpl implements CMPAttribute, EAttribute{

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
	protected EClass eStaticClass() {
		return EjbPackage.eINSTANCE.getCMPAttribute();
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
public String getGetterName() {
	if (name != null && name.length() > 0) {
		StringBuffer b = new StringBuffer("get"); //$NON-NLS-1$
		b.append(name.substring(0, 1).toUpperCase()).append(name.length() > 1 ? name.substring(1) : ""); //$NON-NLS-1$
		return b.toString();
	}
	return name;
}
/**
 * Return the type of this attribute in its original form (i.e., unwrapped if
 * the type has been wrapped).
 */
public JavaHelpers getOriginatingType() {
	return originatingType;
}
public String getSetterName() {
	return "set" + getName().substring(0, 1).toUpperCase() + (getName().length() > 1 ? getName().substring(1) : ""); //$NON-NLS-1$ //$NON-NLS-2$
}
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
public boolean isCMRField() {
	return false;
}
public boolean isKey() {
	return getCMPEntity() == null ? false : getCMPEntity().isKeyAttribute(this);
}

public boolean isPrimKeyField(){
	return getCMPEntity() == null ? false : getCMPEntity().getPrimKeyField() == this;
}

public void setOriginatingType(JavaHelpers newOriginatingType) {
	originatingType = newOriginatingType;
}
	/**
	 * @generated This field/method will be replaced during code generation 
	 * The description is used by the ejb-jar file producer to provide text describing the cmr field.
	 * 
	 * The description should include any information that the ejb-jar file producer wants to provide to the consumer of the ejb-jar file (i.e. to the Deployer). Typically, the tools used by the ejb-jar file consumer will display the description when processing the list of dependents.
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
			eNotify(new ENotificationImpl(this, Notification.SET, EjbPackage.CMP_ATTRIBUTE__DESCRIPTION, oldDescription, description));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
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
	public NotificationChain eInverseAdd(InternalEObject otherEnd, int aFeatureID, Class baseClass, NotificationChain msgs) {
		if (aFeatureID >= 0) {
			switch (eDerivedStructuralFeatureID(aFeatureID, baseClass)) {
				case EjbPackage.CMP_ATTRIBUTE__EANNOTATIONS:
					return ((InternalEList)getEAnnotations()).basicAdd(otherEnd, msgs);
				case EjbPackage.CMP_ATTRIBUTE__ECONTAINING_CLASS:
					if (eContainer != null)
						msgs = eBasicRemoveFromContainer(msgs);
					return eBasicSetContainer(otherEnd, EjbPackage.CMP_ATTRIBUTE__ECONTAINING_CLASS, msgs);
				default:
					return eDynamicInverseAdd(otherEnd, aFeatureID, baseClass, msgs);
			}
		}
		if (eContainer != null)
			msgs = eBasicRemoveFromContainer(msgs);
		return eBasicSetContainer(otherEnd, aFeatureID, msgs);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int aFeatureID, Class baseClass, NotificationChain msgs) {
		if (aFeatureID >= 0) {
			switch (eDerivedStructuralFeatureID(aFeatureID, baseClass)) {
				case EjbPackage.CMP_ATTRIBUTE__EANNOTATIONS:
					return ((InternalEList)getEAnnotations()).basicRemove(otherEnd, msgs);
				case EjbPackage.CMP_ATTRIBUTE__ECONTAINING_CLASS:
					return eBasicSetContainer(null, EjbPackage.CMP_ATTRIBUTE__ECONTAINING_CLASS, msgs);
				case EjbPackage.CMP_ATTRIBUTE__DESCRIPTIONS:
					return ((InternalEList)getDescriptions()).basicRemove(otherEnd, msgs);
				default:
					return eDynamicInverseRemove(otherEnd, aFeatureID, baseClass, msgs);
			}
		}
		return eBasicSetContainer(null, aFeatureID, msgs);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain eBasicRemoveFromContainer(NotificationChain msgs) {
		if (eContainerFeatureID >= 0) {
			switch (eContainerFeatureID) {
				case EjbPackage.CMP_ATTRIBUTE__ECONTAINING_CLASS:
					return eContainer.eInverseRemove(this, EcorePackage.ECLASS__ESTRUCTURAL_FEATURES, EClass.class, msgs);
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
			case EjbPackage.CMP_ATTRIBUTE__EANNOTATIONS:
				return getEAnnotations();
			case EjbPackage.CMP_ATTRIBUTE__NAME:
				return getName();
			case EjbPackage.CMP_ATTRIBUTE__ORDERED:
				return isOrdered() ? Boolean.TRUE : Boolean.FALSE;
			case EjbPackage.CMP_ATTRIBUTE__UNIQUE:
				return isUnique() ? Boolean.TRUE : Boolean.FALSE;
			case EjbPackage.CMP_ATTRIBUTE__LOWER_BOUND:
				return new Integer(getLowerBound());
			case EjbPackage.CMP_ATTRIBUTE__UPPER_BOUND:
				return new Integer(getUpperBound());
			case EjbPackage.CMP_ATTRIBUTE__MANY:
				return isMany() ? Boolean.TRUE : Boolean.FALSE;
			case EjbPackage.CMP_ATTRIBUTE__REQUIRED:
				return isRequired() ? Boolean.TRUE : Boolean.FALSE;
			case EjbPackage.CMP_ATTRIBUTE__ETYPE:
				if (resolve) return getEType();
				return basicGetEType();
			case EjbPackage.CMP_ATTRIBUTE__CHANGEABLE:
				return isChangeable() ? Boolean.TRUE : Boolean.FALSE;
			case EjbPackage.CMP_ATTRIBUTE__VOLATILE:
				return isVolatile() ? Boolean.TRUE : Boolean.FALSE;
			case EjbPackage.CMP_ATTRIBUTE__TRANSIENT:
				return isTransient() ? Boolean.TRUE : Boolean.FALSE;
			case EjbPackage.CMP_ATTRIBUTE__DEFAULT_VALUE_LITERAL:
				return getDefaultValueLiteral();
			case EjbPackage.CMP_ATTRIBUTE__DEFAULT_VALUE:
				return getDefaultValue();
			case EjbPackage.CMP_ATTRIBUTE__UNSETTABLE:
				return isUnsettable() ? Boolean.TRUE : Boolean.FALSE;
			case EjbPackage.CMP_ATTRIBUTE__DERIVED:
				return isDerived() ? Boolean.TRUE : Boolean.FALSE;
			case EjbPackage.CMP_ATTRIBUTE__ECONTAINING_CLASS:
				return getEContainingClass();
			case EjbPackage.CMP_ATTRIBUTE__ID:
				return isID() ? Boolean.TRUE : Boolean.FALSE;
			case EjbPackage.CMP_ATTRIBUTE__EATTRIBUTE_TYPE:
				if (resolve) return getEAttributeType();
				return basicGetEAttributeType();
			case EjbPackage.CMP_ATTRIBUTE__DESCRIPTION:
				return getDescription();
			case EjbPackage.CMP_ATTRIBUTE__DESCRIPTIONS:
				return getDescriptions();
		}
		return eDynamicGet(eFeature, resolve);
	}

	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	public boolean eIsSet(EStructuralFeature eFeature) {
		switch (eDerivedStructuralFeatureID(eFeature)) {
			case EjbPackage.CMP_ATTRIBUTE__EANNOTATIONS:
				return eAnnotations != null && !eAnnotations.isEmpty();
			case EjbPackage.CMP_ATTRIBUTE__NAME:
				return NAME_EDEFAULT == null ? name != null : !NAME_EDEFAULT.equals(name);
			case EjbPackage.CMP_ATTRIBUTE__ORDERED:
				return ordered != ORDERED_EDEFAULT;
			case EjbPackage.CMP_ATTRIBUTE__UNIQUE:
				return unique != UNIQUE_EDEFAULT;
			case EjbPackage.CMP_ATTRIBUTE__LOWER_BOUND:
				return lowerBound != LOWER_BOUND_EDEFAULT;
			case EjbPackage.CMP_ATTRIBUTE__UPPER_BOUND:
				return upperBound != UPPER_BOUND_EDEFAULT;
			case EjbPackage.CMP_ATTRIBUTE__MANY:
				return isMany() != false;
			case EjbPackage.CMP_ATTRIBUTE__REQUIRED:
				return isRequired() != false;
			case EjbPackage.CMP_ATTRIBUTE__ETYPE:
				return eType != null;
			case EjbPackage.CMP_ATTRIBUTE__CHANGEABLE:
				return changeable != CHANGEABLE_EDEFAULT;
			case EjbPackage.CMP_ATTRIBUTE__VOLATILE:
				return volatile_ != VOLATILE_EDEFAULT;
			case EjbPackage.CMP_ATTRIBUTE__TRANSIENT:
				return transient_ != TRANSIENT_EDEFAULT;
			case EjbPackage.CMP_ATTRIBUTE__DEFAULT_VALUE_LITERAL:
				return DEFAULT_VALUE_LITERAL_EDEFAULT == null ? defaultValueLiteral != null : !DEFAULT_VALUE_LITERAL_EDEFAULT.equals(defaultValueLiteral);
			case EjbPackage.CMP_ATTRIBUTE__DEFAULT_VALUE:
				return getDefaultValue() != null;
			case EjbPackage.CMP_ATTRIBUTE__UNSETTABLE:
				return unsettable != UNSETTABLE_EDEFAULT;
			case EjbPackage.CMP_ATTRIBUTE__DERIVED:
				return derived != DERIVED_EDEFAULT;
			case EjbPackage.CMP_ATTRIBUTE__ECONTAINING_CLASS:
				return getEContainingClass() != null;
			case EjbPackage.CMP_ATTRIBUTE__ID:
				return iD != ID_EDEFAULT;
			case EjbPackage.CMP_ATTRIBUTE__EATTRIBUTE_TYPE:
				return basicGetEAttributeType() != null;
			case EjbPackage.CMP_ATTRIBUTE__DESCRIPTION:
				return DESCRIPTION_EDEFAULT == null ? description != null : !DESCRIPTION_EDEFAULT.equals(description);
			case EjbPackage.CMP_ATTRIBUTE__DESCRIPTIONS:
				return descriptions != null && !descriptions.isEmpty();
		}
		return eDynamicIsSet(eFeature);
	}

	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	public void eSet(EStructuralFeature eFeature, Object newValue) {
		switch (eDerivedStructuralFeatureID(eFeature)) {
			case EjbPackage.CMP_ATTRIBUTE__EANNOTATIONS:
				getEAnnotations().clear();
				getEAnnotations().addAll((Collection)newValue);
				return;
			case EjbPackage.CMP_ATTRIBUTE__NAME:
				setName((String)newValue);
				return;
			case EjbPackage.CMP_ATTRIBUTE__ORDERED:
				setOrdered(((Boolean)newValue).booleanValue());
				return;
			case EjbPackage.CMP_ATTRIBUTE__UNIQUE:
				setUnique(((Boolean)newValue).booleanValue());
				return;
			case EjbPackage.CMP_ATTRIBUTE__LOWER_BOUND:
				setLowerBound(((Integer)newValue).intValue());
				return;
			case EjbPackage.CMP_ATTRIBUTE__UPPER_BOUND:
				setUpperBound(((Integer)newValue).intValue());
				return;
			case EjbPackage.CMP_ATTRIBUTE__ETYPE:
				setEType((EClassifier)newValue);
				return;
			case EjbPackage.CMP_ATTRIBUTE__CHANGEABLE:
				setChangeable(((Boolean)newValue).booleanValue());
				return;
			case EjbPackage.CMP_ATTRIBUTE__VOLATILE:
				setVolatile(((Boolean)newValue).booleanValue());
				return;
			case EjbPackage.CMP_ATTRIBUTE__TRANSIENT:
				setTransient(((Boolean)newValue).booleanValue());
				return;
			case EjbPackage.CMP_ATTRIBUTE__DEFAULT_VALUE_LITERAL:
				setDefaultValueLiteral((String)newValue);
				return;
			case EjbPackage.CMP_ATTRIBUTE__UNSETTABLE:
				setUnsettable(((Boolean)newValue).booleanValue());
				return;
			case EjbPackage.CMP_ATTRIBUTE__DERIVED:
				setDerived(((Boolean)newValue).booleanValue());
				return;
			case EjbPackage.CMP_ATTRIBUTE__ID:
				setID(((Boolean)newValue).booleanValue());
				return;
			case EjbPackage.CMP_ATTRIBUTE__DESCRIPTION:
				setDescription((String)newValue);
				return;
			case EjbPackage.CMP_ATTRIBUTE__DESCRIPTIONS:
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
			case EjbPackage.CMP_ATTRIBUTE__EANNOTATIONS:
				getEAnnotations().clear();
				return;
			case EjbPackage.CMP_ATTRIBUTE__NAME:
				setName(NAME_EDEFAULT);
				return;
			case EjbPackage.CMP_ATTRIBUTE__ORDERED:
				setOrdered(ORDERED_EDEFAULT);
				return;
			case EjbPackage.CMP_ATTRIBUTE__UNIQUE:
				setUnique(UNIQUE_EDEFAULT);
				return;
			case EjbPackage.CMP_ATTRIBUTE__LOWER_BOUND:
				setLowerBound(LOWER_BOUND_EDEFAULT);
				return;
			case EjbPackage.CMP_ATTRIBUTE__UPPER_BOUND:
				setUpperBound(UPPER_BOUND_EDEFAULT);
				return;
			case EjbPackage.CMP_ATTRIBUTE__ETYPE:
				setEType((EClassifier)null);
				return;
			case EjbPackage.CMP_ATTRIBUTE__CHANGEABLE:
				setChangeable(CHANGEABLE_EDEFAULT);
				return;
			case EjbPackage.CMP_ATTRIBUTE__VOLATILE:
				setVolatile(VOLATILE_EDEFAULT);
				return;
			case EjbPackage.CMP_ATTRIBUTE__TRANSIENT:
				setTransient(TRANSIENT_EDEFAULT);
				return;
			case EjbPackage.CMP_ATTRIBUTE__DEFAULT_VALUE_LITERAL:
				setDefaultValueLiteral(DEFAULT_VALUE_LITERAL_EDEFAULT);
				return;
			case EjbPackage.CMP_ATTRIBUTE__UNSETTABLE:
				setUnsettable(UNSETTABLE_EDEFAULT);
				return;
			case EjbPackage.CMP_ATTRIBUTE__DERIVED:
				setDerived(DERIVED_EDEFAULT);
				return;
			case EjbPackage.CMP_ATTRIBUTE__ID:
				setID(ID_EDEFAULT);
				return;
			case EjbPackage.CMP_ATTRIBUTE__DESCRIPTION:
				setDescription(DESCRIPTION_EDEFAULT);
				return;
			case EjbPackage.CMP_ATTRIBUTE__DESCRIPTIONS:
				getDescriptions().clear();
				return;
		}
		eDynamicUnset(eFeature);
	}

	/**
	 * This field/method will be replaced during code generation.
	 */
	public String toString() {
		return super.toString() + " :: " + getName(); //$NON-NLS-1$
	}
	/**
	 * Returns true if a targetAttribute is being used.
	 * @return Returns a boolean
	 */
	public boolean isDerived() {
		return derived;
	}
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






