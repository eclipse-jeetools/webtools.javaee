/*******************************************************************************
 * Copyright (c) 2001, 2005 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *******************************************************************************/
/*
 *  $RCSfile: ParameterDecoratorImpl.java,v $
 *  $Revision: 1.8 $  $Date: 2005/09/13 20:30:47 $ 
 */
package org.eclipse.jem.internal.beaninfo.impl;



import java.util.Collection;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EModelElement;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.EcorePackage;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.util.InternalEList;

import org.eclipse.jem.internal.beaninfo.BeaninfoPackage;
import org.eclipse.jem.internal.beaninfo.ImplicitItem;
import org.eclipse.jem.internal.beaninfo.ParameterDecorator;
import org.eclipse.jem.java.JavaParameter;
/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Method Decorator</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.eclipse.jem.internal.beaninfo.impl.ParameterDecoratorImpl#getName <em>Name</em>}</li>
 *   <li>{@link org.eclipse.jem.internal.beaninfo.impl.ParameterDecoratorImpl#getParameter <em>Parameter</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */

public class ParameterDecoratorImpl extends FeatureDecoratorImpl implements ParameterDecorator{
	
	/**
	 * Bits for implicitly set features. This is internal, not meant for clients.
	 */
	public static final long PARAMETER_NAME_IMPLICIT = 0x1L;

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
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected ParameterDecoratorImpl() {
		super();
	}
	
	protected String getSourceDefault() {
		return ParameterDecorator.class.getName();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected EClass eStaticClass() {
		return BeaninfoPackage.eINSTANCE.getParameterDecorator();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getName() {
		return name;
	}

	/**
	 * The cached value of the '{@link #getName() <em>Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getName()
	 * @generated
	 * @ordered
	 */
	protected String name = NAME_EDEFAULT;
	/**
	 * The cached value of the '{@link #getParameter() <em>Parameter</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getParameter()
	 * @generated
	 * @ordered
	 */
	protected JavaParameter parameter = null;
	
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setName(String newName) {
		String oldName = name;
		name = newName;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, BeaninfoPackage.PARAMETER_DECORATOR__NAME, oldName, name));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String toString() {
		if (eIsProxy()) return super.toString();

		StringBuffer result = new StringBuffer(super.toString());
		result.append(" (name: ");
		result.append(name);
		result.append(')');
		return result.toString();
	}

	/*
	 * Flag for if we tried to link up this parameter to java parm.
	 */
	private boolean triedOnce;
	
	/**
	 * The JavaParameter that this ParameterDecorator is decorating.
	 */
	public JavaParameter getParameter() {
		if (!eIsSet(BeaninfoPackage.eINSTANCE.getParameterDecorator_Parameter()) && !triedOnce) {
			// Need to try to fill in the parameter setting.
			triedOnce = true;
			EObject container = eContainer();	// See if we are in a MethodDecorator.
			if (container instanceof MethodDecoratorImpl)
				((MethodDecoratorImpl) container).initializeParameters();
		}
		
		return getParameterGen();
	}
	
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public JavaParameter getParameterGen() {
		if (parameter != null && parameter.eIsProxy()) {
			JavaParameter oldParameter = parameter;
			parameter = (JavaParameter)eResolveProxy((InternalEObject)parameter);
			if (parameter != oldParameter) {
				if (eNotificationRequired())
					eNotify(new ENotificationImpl(this, Notification.RESOLVE, BeaninfoPackage.PARAMETER_DECORATOR__PARAMETER, oldParameter, parameter));
			}
		}
		return parameter;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public JavaParameter basicGetParameter() {
		return parameter;
	}

	public void setParameter(JavaParameter newParameter) {
		if (newParameter == null)
			triedOnce = false;
		setParameterGen(newParameter);
	}
	
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setParameterGen(JavaParameter newParameter) {
		JavaParameter oldParameter = parameter;
		parameter = newParameter;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, BeaninfoPackage.PARAMETER_DECORATOR__PARAMETER, oldParameter, parameter));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain eInverseAdd(InternalEObject otherEnd, int featureID, Class baseClass, NotificationChain msgs) {
		if (featureID >= 0) {
			switch (eDerivedStructuralFeatureID(featureID, baseClass)) {
				case BeaninfoPackage.PARAMETER_DECORATOR__EANNOTATIONS:
					return ((InternalEList)getEAnnotations()).basicAdd(otherEnd, msgs);
				case BeaninfoPackage.PARAMETER_DECORATOR__EMODEL_ELEMENT:
					if (eContainer != null)
						msgs = eBasicRemoveFromContainer(msgs);
					return eBasicSetContainer(otherEnd, BeaninfoPackage.PARAMETER_DECORATOR__EMODEL_ELEMENT, msgs);
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
				case BeaninfoPackage.PARAMETER_DECORATOR__EANNOTATIONS:
					return ((InternalEList)getEAnnotations()).basicRemove(otherEnd, msgs);
				case BeaninfoPackage.PARAMETER_DECORATOR__DETAILS:
					return ((InternalEList)getDetails()).basicRemove(otherEnd, msgs);
				case BeaninfoPackage.PARAMETER_DECORATOR__EMODEL_ELEMENT:
					return eBasicSetContainer(null, BeaninfoPackage.PARAMETER_DECORATOR__EMODEL_ELEMENT, msgs);
				case BeaninfoPackage.PARAMETER_DECORATOR__CONTENTS:
					return ((InternalEList)getContents()).basicRemove(otherEnd, msgs);
				case BeaninfoPackage.PARAMETER_DECORATOR__ATTRIBUTES:
					return ((InternalEList)getAttributes()).basicRemove(otherEnd, msgs);
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
				case BeaninfoPackage.PARAMETER_DECORATOR__EMODEL_ELEMENT:
					return eContainer.eInverseRemove(this, EcorePackage.EMODEL_ELEMENT__EANNOTATIONS, EModelElement.class, msgs);
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
			case BeaninfoPackage.PARAMETER_DECORATOR__EANNOTATIONS:
				return getEAnnotations();
			case BeaninfoPackage.PARAMETER_DECORATOR__SOURCE:
				return getSource();
			case BeaninfoPackage.PARAMETER_DECORATOR__DETAILS:
				return getDetails();
			case BeaninfoPackage.PARAMETER_DECORATOR__EMODEL_ELEMENT:
				return getEModelElement();
			case BeaninfoPackage.PARAMETER_DECORATOR__CONTENTS:
				return getContents();
			case BeaninfoPackage.PARAMETER_DECORATOR__REFERENCES:
				return getReferences();
			case BeaninfoPackage.PARAMETER_DECORATOR__DISPLAY_NAME:
				return getDisplayName();
			case BeaninfoPackage.PARAMETER_DECORATOR__SHORT_DESCRIPTION:
				return getShortDescription();
			case BeaninfoPackage.PARAMETER_DECORATOR__CATEGORY:
				return getCategory();
			case BeaninfoPackage.PARAMETER_DECORATOR__EXPERT:
				return isExpert() ? Boolean.TRUE : Boolean.FALSE;
			case BeaninfoPackage.PARAMETER_DECORATOR__HIDDEN:
				return isHidden() ? Boolean.TRUE : Boolean.FALSE;
			case BeaninfoPackage.PARAMETER_DECORATOR__PREFERRED:
				return isPreferred() ? Boolean.TRUE : Boolean.FALSE;
			case BeaninfoPackage.PARAMETER_DECORATOR__MERGE_INTROSPECTION:
				return isMergeIntrospection() ? Boolean.TRUE : Boolean.FALSE;
			case BeaninfoPackage.PARAMETER_DECORATOR__ATTRIBUTES_EXPLICIT_EMPTY:
				return isAttributesExplicitEmpty() ? Boolean.TRUE : Boolean.FALSE;
			case BeaninfoPackage.PARAMETER_DECORATOR__IMPLICITLY_SET_BITS:
				return new Long(getImplicitlySetBits());
			case BeaninfoPackage.PARAMETER_DECORATOR__IMPLICIT_DECORATOR_FLAG:
				return getImplicitDecoratorFlag();
			case BeaninfoPackage.PARAMETER_DECORATOR__ATTRIBUTES:
				return getAttributes();
			case BeaninfoPackage.PARAMETER_DECORATOR__NAME:
				return getName();
			case BeaninfoPackage.PARAMETER_DECORATOR__PARAMETER:
				if (resolve) return getParameter();
				return basicGetParameter();
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
			case BeaninfoPackage.PARAMETER_DECORATOR__EANNOTATIONS:
				getEAnnotations().clear();
				getEAnnotations().addAll((Collection)newValue);
				return;
			case BeaninfoPackage.PARAMETER_DECORATOR__SOURCE:
				setSource((String)newValue);
				return;
			case BeaninfoPackage.PARAMETER_DECORATOR__DETAILS:
				getDetails().clear();
				getDetails().addAll((Collection)newValue);
				return;
			case BeaninfoPackage.PARAMETER_DECORATOR__EMODEL_ELEMENT:
				setEModelElement((EModelElement)newValue);
				return;
			case BeaninfoPackage.PARAMETER_DECORATOR__CONTENTS:
				getContents().clear();
				getContents().addAll((Collection)newValue);
				return;
			case BeaninfoPackage.PARAMETER_DECORATOR__REFERENCES:
				getReferences().clear();
				getReferences().addAll((Collection)newValue);
				return;
			case BeaninfoPackage.PARAMETER_DECORATOR__DISPLAY_NAME:
				setDisplayName((String)newValue);
				return;
			case BeaninfoPackage.PARAMETER_DECORATOR__SHORT_DESCRIPTION:
				setShortDescription((String)newValue);
				return;
			case BeaninfoPackage.PARAMETER_DECORATOR__CATEGORY:
				setCategory((String)newValue);
				return;
			case BeaninfoPackage.PARAMETER_DECORATOR__EXPERT:
				setExpert(((Boolean)newValue).booleanValue());
				return;
			case BeaninfoPackage.PARAMETER_DECORATOR__HIDDEN:
				setHidden(((Boolean)newValue).booleanValue());
				return;
			case BeaninfoPackage.PARAMETER_DECORATOR__PREFERRED:
				setPreferred(((Boolean)newValue).booleanValue());
				return;
			case BeaninfoPackage.PARAMETER_DECORATOR__MERGE_INTROSPECTION:
				setMergeIntrospection(((Boolean)newValue).booleanValue());
				return;
			case BeaninfoPackage.PARAMETER_DECORATOR__ATTRIBUTES_EXPLICIT_EMPTY:
				setAttributesExplicitEmpty(((Boolean)newValue).booleanValue());
				return;
			case BeaninfoPackage.PARAMETER_DECORATOR__IMPLICITLY_SET_BITS:
				setImplicitlySetBits(((Long)newValue).longValue());
				return;
			case BeaninfoPackage.PARAMETER_DECORATOR__IMPLICIT_DECORATOR_FLAG:
				setImplicitDecoratorFlag((ImplicitItem)newValue);
				return;
			case BeaninfoPackage.PARAMETER_DECORATOR__ATTRIBUTES:
				getAttributes().clear();
				getAttributes().addAll((Collection)newValue);
				return;
			case BeaninfoPackage.PARAMETER_DECORATOR__NAME:
				setName((String)newValue);
				return;
			case BeaninfoPackage.PARAMETER_DECORATOR__PARAMETER:
				setParameter((JavaParameter)newValue);
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
			case BeaninfoPackage.PARAMETER_DECORATOR__EANNOTATIONS:
				getEAnnotations().clear();
				return;
			case BeaninfoPackage.PARAMETER_DECORATOR__SOURCE:
				setSource(SOURCE_EDEFAULT);
				return;
			case BeaninfoPackage.PARAMETER_DECORATOR__DETAILS:
				getDetails().clear();
				return;
			case BeaninfoPackage.PARAMETER_DECORATOR__EMODEL_ELEMENT:
				setEModelElement((EModelElement)null);
				return;
			case BeaninfoPackage.PARAMETER_DECORATOR__CONTENTS:
				getContents().clear();
				return;
			case BeaninfoPackage.PARAMETER_DECORATOR__REFERENCES:
				getReferences().clear();
				return;
			case BeaninfoPackage.PARAMETER_DECORATOR__DISPLAY_NAME:
				unsetDisplayName();
				return;
			case BeaninfoPackage.PARAMETER_DECORATOR__SHORT_DESCRIPTION:
				unsetShortDescription();
				return;
			case BeaninfoPackage.PARAMETER_DECORATOR__CATEGORY:
				setCategory(CATEGORY_EDEFAULT);
				return;
			case BeaninfoPackage.PARAMETER_DECORATOR__EXPERT:
				unsetExpert();
				return;
			case BeaninfoPackage.PARAMETER_DECORATOR__HIDDEN:
				unsetHidden();
				return;
			case BeaninfoPackage.PARAMETER_DECORATOR__PREFERRED:
				unsetPreferred();
				return;
			case BeaninfoPackage.PARAMETER_DECORATOR__MERGE_INTROSPECTION:
				setMergeIntrospection(MERGE_INTROSPECTION_EDEFAULT);
				return;
			case BeaninfoPackage.PARAMETER_DECORATOR__ATTRIBUTES_EXPLICIT_EMPTY:
				setAttributesExplicitEmpty(ATTRIBUTES_EXPLICIT_EMPTY_EDEFAULT);
				return;
			case BeaninfoPackage.PARAMETER_DECORATOR__IMPLICITLY_SET_BITS:
				setImplicitlySetBits(IMPLICITLY_SET_BITS_EDEFAULT);
				return;
			case BeaninfoPackage.PARAMETER_DECORATOR__IMPLICIT_DECORATOR_FLAG:
				setImplicitDecoratorFlag(IMPLICIT_DECORATOR_FLAG_EDEFAULT);
				return;
			case BeaninfoPackage.PARAMETER_DECORATOR__ATTRIBUTES:
				getAttributes().clear();
				return;
			case BeaninfoPackage.PARAMETER_DECORATOR__NAME:
				setName(NAME_EDEFAULT);
				return;
			case BeaninfoPackage.PARAMETER_DECORATOR__PARAMETER:
				setParameter((JavaParameter)null);
				return;
		}
		eDynamicUnset(eFeature);
	}

	/* (non-Javadoc)
	 * @see org.eclipse.emf.ecore.EObject#eIsSet(org.eclipse.emf.ecore.EStructuralFeature)
	 */
	public boolean eIsSet(EStructuralFeature eFeature) {
		switch (eDerivedStructuralFeatureID(eFeature)) {
			case BeaninfoPackage.PARAMETER_DECORATOR__SOURCE:
				return isSourceSet();	// Override so that if set to the same as classname, then it is considered not set.
			default:
				return eIsSetGen(eFeature);
		}
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean eIsSetGen(EStructuralFeature eFeature) {
		switch (eDerivedStructuralFeatureID(eFeature)) {
			case BeaninfoPackage.PARAMETER_DECORATOR__EANNOTATIONS:
				return eAnnotations != null && !eAnnotations.isEmpty();
			case BeaninfoPackage.PARAMETER_DECORATOR__SOURCE:
				return SOURCE_EDEFAULT == null ? source != null : !SOURCE_EDEFAULT.equals(source);
			case BeaninfoPackage.PARAMETER_DECORATOR__DETAILS:
				return details != null && !details.isEmpty();
			case BeaninfoPackage.PARAMETER_DECORATOR__EMODEL_ELEMENT:
				return getEModelElement() != null;
			case BeaninfoPackage.PARAMETER_DECORATOR__CONTENTS:
				return contents != null && !contents.isEmpty();
			case BeaninfoPackage.PARAMETER_DECORATOR__REFERENCES:
				return references != null && !references.isEmpty();
			case BeaninfoPackage.PARAMETER_DECORATOR__DISPLAY_NAME:
				return isSetDisplayName();
			case BeaninfoPackage.PARAMETER_DECORATOR__SHORT_DESCRIPTION:
				return isSetShortDescription();
			case BeaninfoPackage.PARAMETER_DECORATOR__CATEGORY:
				return CATEGORY_EDEFAULT == null ? category != null : !CATEGORY_EDEFAULT.equals(category);
			case BeaninfoPackage.PARAMETER_DECORATOR__EXPERT:
				return isSetExpert();
			case BeaninfoPackage.PARAMETER_DECORATOR__HIDDEN:
				return isSetHidden();
			case BeaninfoPackage.PARAMETER_DECORATOR__PREFERRED:
				return isSetPreferred();
			case BeaninfoPackage.PARAMETER_DECORATOR__MERGE_INTROSPECTION:
				return mergeIntrospection != MERGE_INTROSPECTION_EDEFAULT;
			case BeaninfoPackage.PARAMETER_DECORATOR__ATTRIBUTES_EXPLICIT_EMPTY:
				return attributesExplicitEmpty != ATTRIBUTES_EXPLICIT_EMPTY_EDEFAULT;
			case BeaninfoPackage.PARAMETER_DECORATOR__IMPLICITLY_SET_BITS:
				return implicitlySetBits != IMPLICITLY_SET_BITS_EDEFAULT;
			case BeaninfoPackage.PARAMETER_DECORATOR__IMPLICIT_DECORATOR_FLAG:
				return implicitDecoratorFlag != IMPLICIT_DECORATOR_FLAG_EDEFAULT;
			case BeaninfoPackage.PARAMETER_DECORATOR__ATTRIBUTES:
				return attributes != null && !attributes.isEmpty();
			case BeaninfoPackage.PARAMETER_DECORATOR__NAME:
				return NAME_EDEFAULT == null ? name != null : !NAME_EDEFAULT.equals(name);
			case BeaninfoPackage.PARAMETER_DECORATOR__PARAMETER:
				return parameter != null;
		}
		return eDynamicIsSet(eFeature);
	}

}
