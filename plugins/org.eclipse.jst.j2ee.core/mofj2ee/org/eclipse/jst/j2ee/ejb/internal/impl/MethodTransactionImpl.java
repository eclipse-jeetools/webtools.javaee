/*******************************************************************************
 * Copyright (c) 2001, 2005 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 * IBM Corporation - initial API and implementation
 *******************************************************************************/
package org.eclipse.jst.j2ee.ejb.internal.impl;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EEnumLiteral;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.EObjectImpl;
import org.eclipse.emf.ecore.util.EObjectContainmentEList;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.emf.ecore.util.InternalEList;
import org.eclipse.jem.java.Method;
import org.eclipse.jst.j2ee.common.Description;
import org.eclipse.jst.j2ee.ejb.AssemblyDescriptor;
import org.eclipse.jst.j2ee.ejb.EjbPackage;
import org.eclipse.jst.j2ee.ejb.EnterpriseBean;
import org.eclipse.jst.j2ee.ejb.MethodElement;
import org.eclipse.jst.j2ee.ejb.MethodTransaction;
import org.eclipse.jst.j2ee.ejb.TransactionAttributeType;

/**
 * Specifies how the container must manage transaction scopes for the enterprise bean's method invocations.  It consists of an optional description, a list of method elements, and a transaction attribute.The transaction attribute is to be applied to all the specified methods.
 */
public class MethodTransactionImpl extends EObjectImpl implements MethodTransaction, EObject {

	/**
	 * The default value of the '{@link #getTransactionAttribute() <em>Transaction Attribute</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getTransactionAttribute()
	 * @generated
	 * @ordered
	 */
	protected static final TransactionAttributeType TRANSACTION_ATTRIBUTE_EDEFAULT = TransactionAttributeType.NOT_SUPPORTED_LITERAL;

	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	protected TransactionAttributeType transactionAttribute = TRANSACTION_ATTRIBUTE_EDEFAULT;
	/**
	 * This is true if the Transaction Attribute attribute has been set.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	protected boolean transactionAttributeESet = false;

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

	public MethodTransactionImpl() {
		super();
	}
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected EClass eStaticClass() {
		return EjbPackage.eINSTANCE.getMethodTransaction();
	}

/**
 * Return an array of Strings for all the EEnumLiteral values
 * for the transactionAttribute enumeration.
 */
public static String[] getAvailableTransactionAttributeNames() {
	List l = getAvailableTransactionAttributes();
	String[] names = new String[l.size()];
	for (int i = 0; i < l.size(); i++)
		names[i] = ((EEnumLiteral)l.get(i)).toString();
	return names;
}
/**
 * Return a List of the all the EEnumLiteral values
 * for the transactionAttribute enumeration.
 * The toString() method for a EEnumLiteral will display
 * the String value for the enumeration value.
 */
public static List getAvailableTransactionAttributes() {
	EjbPackage pack = (EjbPackage)EPackage.Registry.INSTANCE.getEPackage(EjbPackage.eNS_URI);
	return pack.getTransactionAttributeType().getELiterals();
}
public List getMethodElements(EnterpriseBean anEJB) {
	List result = new ArrayList();
	List elements = getMethodElements();
	MethodElement me;
	for (int i = 0; i < elements.size(); i++){
		me = (MethodElement) elements.get(i);
		if (me.getEnterpriseBean() != null && me.getEnterpriseBean().equals(anEJB))
			result.add(me);			
	}
	return result;
}
public MethodElement getMostSpecificMethodElement(Method aMethod) {
   return MethodElementImpl.getMostSpecificMethodElement(getMethodElements(), aMethod);
}
public boolean isEquivalent(MethodTransaction anotherMethodTransaction) {
	return getTransactionAttribute() == anotherMethodTransaction.getTransactionAttribute();
}
	/**
	 * @generated This field/method will be replaced during code generation 
	 * The trans-attribute element specifies how the container must manage the transaction boundaries when delegating a method invocation to an enterprise bean's business method.

	 */
	public TransactionAttributeType getTransactionAttribute() {
		return transactionAttribute;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setTransactionAttribute(TransactionAttributeType newTransactionAttribute) {
		TransactionAttributeType oldTransactionAttribute = transactionAttribute;
		transactionAttribute = newTransactionAttribute == null ? TRANSACTION_ATTRIBUTE_EDEFAULT : newTransactionAttribute;
		boolean oldTransactionAttributeESet = transactionAttributeESet;
		transactionAttributeESet = true;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, EjbPackage.METHOD_TRANSACTION__TRANSACTION_ATTRIBUTE, oldTransactionAttribute, transactionAttribute, !oldTransactionAttributeESet));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void unsetTransactionAttribute() {
		TransactionAttributeType oldTransactionAttribute = transactionAttribute;
		boolean oldTransactionAttributeESet = transactionAttributeESet;
		transactionAttribute = TRANSACTION_ATTRIBUTE_EDEFAULT;
		transactionAttributeESet = false;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.UNSET, EjbPackage.METHOD_TRANSACTION__TRANSACTION_ATTRIBUTE, oldTransactionAttribute, TRANSACTION_ATTRIBUTE_EDEFAULT, oldTransactionAttributeESet));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean isSetTransactionAttribute() {
		return transactionAttributeESet;
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
			eNotify(new ENotificationImpl(this, Notification.SET, EjbPackage.METHOD_TRANSACTION__DESCRIPTION, oldDescription, description));
	}

	/**
	 * @generated This field/method will be replaced during code generation 
	 */
	public AssemblyDescriptor getAssemblyDescriptor() {
		if (eContainerFeatureID != EjbPackage.METHOD_TRANSACTION__ASSEMBLY_DESCRIPTOR) return null;
		return (AssemblyDescriptor)eContainer;
	}

	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	public void setAssemblyDescriptor(AssemblyDescriptor newAssemblyDescriptor) {
		if (newAssemblyDescriptor != eContainer || (eContainerFeatureID != EjbPackage.METHOD_TRANSACTION__ASSEMBLY_DESCRIPTOR && newAssemblyDescriptor != null)) {
			if (EcoreUtil.isAncestor(this, newAssemblyDescriptor))
				throw new IllegalArgumentException("Recursive containment not allowed for " + toString());
			NotificationChain msgs = null;
			if (eContainer != null)
				msgs = eBasicRemoveFromContainer(msgs);
			if (newAssemblyDescriptor != null)
				msgs = ((InternalEObject)newAssemblyDescriptor).eInverseAdd(this, EjbPackage.ASSEMBLY_DESCRIPTOR__METHOD_TRANSACTIONS, AssemblyDescriptor.class, msgs);
			msgs = eBasicSetContainer((InternalEObject)newAssemblyDescriptor, EjbPackage.METHOD_TRANSACTION__ASSEMBLY_DESCRIPTOR, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, EjbPackage.METHOD_TRANSACTION__ASSEMBLY_DESCRIPTOR, newAssemblyDescriptor, newAssemblyDescriptor));
	}

	/**
	 * @generated This field/method will be replaced during code generation 
	 */
	public EList getMethodElements() {
		if (methodElements == null) {
			methodElements = new EObjectContainmentEList(MethodElement.class, this, EjbPackage.METHOD_TRANSACTION__METHOD_ELEMENTS);
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
			descriptions = new EObjectContainmentEList(Description.class, this, EjbPackage.METHOD_TRANSACTION__DESCRIPTIONS);
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
				case EjbPackage.METHOD_TRANSACTION__ASSEMBLY_DESCRIPTOR:
					if (eContainer != null)
						msgs = eBasicRemoveFromContainer(msgs);
					return eBasicSetContainer(otherEnd, EjbPackage.METHOD_TRANSACTION__ASSEMBLY_DESCRIPTOR, msgs);
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
				case EjbPackage.METHOD_TRANSACTION__ASSEMBLY_DESCRIPTOR:
					return eBasicSetContainer(null, EjbPackage.METHOD_TRANSACTION__ASSEMBLY_DESCRIPTOR, msgs);
				case EjbPackage.METHOD_TRANSACTION__METHOD_ELEMENTS:
					return ((InternalEList)getMethodElements()).basicRemove(otherEnd, msgs);
				case EjbPackage.METHOD_TRANSACTION__DESCRIPTIONS:
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
				case EjbPackage.METHOD_TRANSACTION__ASSEMBLY_DESCRIPTOR:
					return eContainer.eInverseRemove(this, EjbPackage.ASSEMBLY_DESCRIPTOR__METHOD_TRANSACTIONS, AssemblyDescriptor.class, msgs);
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
			case EjbPackage.METHOD_TRANSACTION__TRANSACTION_ATTRIBUTE:
				return getTransactionAttribute();
			case EjbPackage.METHOD_TRANSACTION__DESCRIPTION:
				return getDescription();
			case EjbPackage.METHOD_TRANSACTION__ASSEMBLY_DESCRIPTOR:
				return getAssemblyDescriptor();
			case EjbPackage.METHOD_TRANSACTION__METHOD_ELEMENTS:
				return getMethodElements();
			case EjbPackage.METHOD_TRANSACTION__DESCRIPTIONS:
				return getDescriptions();
		}
		return eDynamicGet(eFeature, resolve);
	}

	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	public boolean eIsSet(EStructuralFeature eFeature) {
		switch (eDerivedStructuralFeatureID(eFeature)) {
			case EjbPackage.METHOD_TRANSACTION__TRANSACTION_ATTRIBUTE:
				return isSetTransactionAttribute();
			case EjbPackage.METHOD_TRANSACTION__DESCRIPTION:
				return DESCRIPTION_EDEFAULT == null ? description != null : !DESCRIPTION_EDEFAULT.equals(description);
			case EjbPackage.METHOD_TRANSACTION__ASSEMBLY_DESCRIPTOR:
				return getAssemblyDescriptor() != null;
			case EjbPackage.METHOD_TRANSACTION__METHOD_ELEMENTS:
				return methodElements != null && !methodElements.isEmpty();
			case EjbPackage.METHOD_TRANSACTION__DESCRIPTIONS:
				return descriptions != null && !descriptions.isEmpty();
		}
		return eDynamicIsSet(eFeature);
	}

	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	public void eSet(EStructuralFeature eFeature, Object newValue) {
		switch (eDerivedStructuralFeatureID(eFeature)) {
			case EjbPackage.METHOD_TRANSACTION__TRANSACTION_ATTRIBUTE:
				setTransactionAttribute((TransactionAttributeType)newValue);
				return;
			case EjbPackage.METHOD_TRANSACTION__DESCRIPTION:
				setDescription((String)newValue);
				return;
			case EjbPackage.METHOD_TRANSACTION__ASSEMBLY_DESCRIPTOR:
				setAssemblyDescriptor((AssemblyDescriptor)newValue);
				return;
			case EjbPackage.METHOD_TRANSACTION__METHOD_ELEMENTS:
				getMethodElements().clear();
				getMethodElements().addAll((Collection)newValue);
				return;
			case EjbPackage.METHOD_TRANSACTION__DESCRIPTIONS:
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
			case EjbPackage.METHOD_TRANSACTION__TRANSACTION_ATTRIBUTE:
				unsetTransactionAttribute();
				return;
			case EjbPackage.METHOD_TRANSACTION__DESCRIPTION:
				setDescription(DESCRIPTION_EDEFAULT);
				return;
			case EjbPackage.METHOD_TRANSACTION__ASSEMBLY_DESCRIPTOR:
				setAssemblyDescriptor((AssemblyDescriptor)null);
				return;
			case EjbPackage.METHOD_TRANSACTION__METHOD_ELEMENTS:
				getMethodElements().clear();
				return;
			case EjbPackage.METHOD_TRANSACTION__DESCRIPTIONS:
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
		result.append(" (transactionAttribute: ");
		if (transactionAttributeESet) result.append(transactionAttribute); else result.append("<unset>");
		result.append(", description: ");
		result.append(description);
		result.append(')');
		return result.toString();
	}

}





