/*******************************************************************************
 * Copyright (c) 2001, 2004 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Common Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/cpl-v10.html
 * 
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *******************************************************************************/
package org.eclipse.jem.internal.beaninfo.impl;
/*
 *  $RCSfile: ParameterDecoratorImpl.java,v $
 *  $Revision: 1.3 $  $Date: 2004/08/27 15:33:31 $ 
 */


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
import org.eclipse.jem.internal.beaninfo.ParameterDecorator;
import org.eclipse.jem.internal.beaninfo.adapters.BeaninfoProxyConstants;
import org.eclipse.jem.java.JavaParameter;
import org.eclipse.jem.internal.proxy.core.IBeanProxy;
import org.eclipse.jem.internal.proxy.core.IStringBeanProxy;
import org.eclipse.jem.internal.proxy.core.ThrowableProxy;
/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Parameter Decorator</b></em>'.
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

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected EClass eStaticClass() {
		return BeaninfoPackage.eINSTANCE.getParameterDecorator();
	}

	protected String fProxyName;
	protected boolean triedOnce;	// Set when tried to get the java parameter and couldn't.
	
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
	public void setDescriptorProxy(IBeanProxy descriptor) {
		super.setDescriptorProxy(descriptor);
		
		fProxyName = null;
		if (descriptor != null) {
			// Cache name and because these are used over and over.
			if (!eIsSet(BeaninfoPackage.eINSTANCE.getParameterDecorator_Name()))
				try {
					fProxyName = ((IStringBeanProxy) BeaninfoProxyConstants.getConstants(descriptor.getProxyFactoryRegistry()).getNameProxy().invoke(descriptor)).stringValue();
				} catch (NullPointerException e) {
				} catch (ThrowableProxy e) {
				}				
		}
	}
	
	public String getName() {
		if (!eIsSet(BeaninfoPackage.eINSTANCE.getParameterDecorator_Name()))
			if (fProxyName != null) 
				return fProxyName;
			else
				return "?";	// It must be set or come from proxy. //$NON-NLS-1$
		return this.getNameGen();
	}
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

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getNameGen() {
		return name;
	}

	/**
	 * The JavaParameter that this ParameterDecorator is decorating. Can't use eDecorates in this.
	 */
	public JavaParameter getParameter() {
		if (!!eIsSet(BeaninfoPackage.eINSTANCE.getParameterDecorator_Parameter()) && !triedOnce) {
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

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setParameter(JavaParameter newParameter) {
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
					return ((InternalEObject)eContainer).eInverseRemove(this, EcorePackage.EMODEL_ELEMENT__EANNOTATIONS, EModelElement.class, msgs);
				default:
					return eDynamicBasicRemoveFromContainer(msgs);
			}
		}
		return ((InternalEObject)eContainer).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - eContainerFeatureID, null, msgs);
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
			case BeaninfoPackage.PARAMETER_DECORATOR__ATTRIBUTES_EXPLICIT:
				return isAttributesExplicit() ? Boolean.TRUE : Boolean.FALSE;
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
			case BeaninfoPackage.PARAMETER_DECORATOR__ATTRIBUTES_EXPLICIT:
				setAttributesExplicit(((Boolean)newValue).booleanValue());
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
			case BeaninfoPackage.PARAMETER_DECORATOR__ATTRIBUTES_EXPLICIT:
				setAttributesExplicit(ATTRIBUTES_EXPLICIT_EDEFAULT);
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

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean eIsSet(EStructuralFeature eFeature) {
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
			case BeaninfoPackage.PARAMETER_DECORATOR__ATTRIBUTES_EXPLICIT:
				return attributesExplicit != ATTRIBUTES_EXPLICIT_EDEFAULT;
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
