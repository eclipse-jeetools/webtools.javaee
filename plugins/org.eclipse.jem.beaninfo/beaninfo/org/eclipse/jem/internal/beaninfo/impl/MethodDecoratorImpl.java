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
package org.eclipse.jem.internal.beaninfo.impl;
/*
 *  $RCSfile: MethodDecoratorImpl.java,v $
 *  $Revision: 1.11 $  $Date: 2005/09/19 15:43:02 $ 
 */


import java.util.Collection;

import java.util.*;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EModelElement;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.EcorePackage;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.util.EObjectContainmentEList;
import org.eclipse.emf.ecore.util.InternalEList;

import org.eclipse.jem.internal.beaninfo.BeaninfoFactory;
import org.eclipse.jem.internal.beaninfo.BeaninfoPackage;
import org.eclipse.jem.internal.beaninfo.ImplicitItem;
import org.eclipse.jem.internal.beaninfo.MethodDecorator;
import org.eclipse.jem.internal.beaninfo.MethodProxy;
import org.eclipse.jem.internal.beaninfo.ParameterDecorator;
import org.eclipse.jem.java.JavaParameter;
import org.eclipse.jem.java.Method;
/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Method Decorator</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.eclipse.jem.internal.beaninfo.impl.MethodDecoratorImpl#isParmsExplicitEmpty <em>Parms Explicit Empty</em>}</li>
 *   <li>{@link org.eclipse.jem.internal.beaninfo.impl.MethodDecoratorImpl#getParameterDescriptors <em>Parameter Descriptors</em>}</li>
 *   <li>{@link org.eclipse.jem.internal.beaninfo.impl.MethodDecoratorImpl#getSerParmDesc <em>Ser Parm Desc</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */


public class MethodDecoratorImpl extends FeatureDecoratorImpl implements MethodDecorator{
	
	/**
	 * Bits for implicitly set features. This is internal, not meant for clients.
	 */
	public static final long METHOD_PARAMETERS_IMPLICIT = 0x1L;
	public static final long METHOD_PARAMETERS_DEFAULT = 02L;	// Special, means were created by default and not by implicit (from beaninfo).

	/**
	 * The default value of the '{@link #isParmsExplicitEmpty() <em>Parms Explicit Empty</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isParmsExplicitEmpty()
	 * @generated
	 * @ordered
	 */
	protected static final boolean PARMS_EXPLICIT_EMPTY_EDEFAULT = false;

	/**
	 * The flag representing the value of the '{@link #isParmsExplicitEmpty() <em>Parms Explicit Empty</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isParmsExplicitEmpty()
	 * @generated
	 * @ordered
	 */
	protected static final int PARMS_EXPLICIT_EMPTY_EFLAG = 1 << 18;

	/**
	 * The cached value of the '{@link #getSerParmDesc() <em>Ser Parm Desc</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getSerParmDesc()
	 * @generated
	 * @ordered
	 */
	protected EList serParmDesc = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */	
	protected MethodDecoratorImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected EClass eStaticClass() {
		return BeaninfoPackage.eINSTANCE.getMethodDecorator();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean isParmsExplicitEmpty() {
		return (eFlags & PARMS_EXPLICIT_EMPTY_EFLAG) != 0;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setParmsExplicitEmpty(boolean newParmsExplicitEmpty) {
		boolean oldParmsExplicitEmpty = (eFlags & PARMS_EXPLICIT_EMPTY_EFLAG) != 0;
		if (newParmsExplicitEmpty) eFlags |= PARMS_EXPLICIT_EMPTY_EFLAG; else eFlags &= ~PARMS_EXPLICIT_EMPTY_EFLAG;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, BeaninfoPackage.METHOD_DECORATOR__PARMS_EXPLICIT_EMPTY, oldParmsExplicitEmpty, newParmsExplicitEmpty));
	}

	/*
	 * This is called if parms list not explicitly set and it was empty from BeanInfo or
	 * this is from reflection. This becomes the default list based upon the method
	 * we are attached to.
	 */
	private EList createDefaultParmsList() {
		EList parmsList = this.getSerParmDesc();
		parmsList.clear();
		
		setImplicitlySetBits(getImplicitlySetBits()|METHOD_PARAMETERS_DEFAULT);	// Mark as we implicitly filled it in.
		List p = getMethodParameters();
		if (p == null)
			return parmsList;	// Couldn't get the list for some reason, so leave as is.
		int psize = p.size();
		for (int i=0; i<psize; i++) {
			ParameterDecorator pd = BeaninfoFactory.eINSTANCE.createParameterDecorator();
			JavaParameter jp = (JavaParameter) p.get(i);
			pd.setName(jp.getName());
			pd.setImplicitlySetBits(ParameterDecoratorImpl.PARAMETER_NAME_IMPLICIT);
			pd.setParameter(jp);
			parmsList.add(pd);
		}
		return parmsList;
	}
	
	/*
	 * Initialize the ParameterDecorators to hook up the JavaParameter they are describing.
	 * This is called from ParameterDecorator when it finds that its JavaParameter has not been set.
	 * This means that it was explicitly added and we need to setup the parms.
	 * <p>
	 * Note this an internal method for BeanInfo. It is not meant to be called by clients.
	 */
	void initializeParameters() {
		if (this.serParmDesc == null)
			return;
		List mp = getMethodParameters();
		if (mp.isEmpty())
			return;	// Nothing that can be described.
		int psize = Math.min(this.serParmDesc.size(), mp.size());
		for (int i=0; i < psize; i++)
			((ParameterDecorator) this.serParmDesc.get(i)).setParameter((JavaParameter) mp.get(i));
	}


	/*
	 * Get the jem parameters from the method (jem method)
	 */
	private List getMethodParameters() {
		// Get the method
		Method m = null;
		Object d = getEModelElement();
		if (d instanceof Method)
			m = (Method) d;
		else 
			if (d instanceof MethodProxy)
				m = ((MethodProxy) d).getMethod();
			else
				return Collections.EMPTY_LIST;	// Not decorating correct object.
		if (m == null)
			return Collections.EMPTY_LIST;	// Couldn't find the method.
		List p = m.getParameters();
		return p;
	}
			
			
	
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String toString() {
		if (eIsProxy()) return super.toString();

		StringBuffer result = new StringBuffer(super.toString());
		result.append(" (parmsExplicitEmpty: ");
		result.append((eFlags & PARMS_EXPLICIT_EMPTY_EFLAG) != 0);
		result.append(')');
		return result.toString();
	}

	/*
	 *  (non-Javadoc)
	 * @see org.eclipse.jem.internal.beaninfo.MethodDecorator#getParameterDescriptors()
	 */
	public EList getParameterDescriptors() {
		if (!isParmsExplicitEmpty() && getSerParmDesc().isEmpty() && (getImplicitlySetBits()&(METHOD_PARAMETERS_IMPLICIT | METHOD_PARAMETERS_DEFAULT)) == 0) {
			// Not explicitly empty, it is empty, and we have not implicitly or by defaults made it empty, then create the defaults.
			createDefaultParmsList();
		}
		return getSerParmDesc();
	}
	
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList getParameterDescriptorsGen() {
		// TODO: implement this method to return the 'Parameter Descriptors' reference list
		// Ensure that you remove @generated or mark it @generated NOT
		throw new UnsupportedOperationException();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList getSerParmDesc() {
		if (serParmDesc == null) {
			serParmDesc = new EObjectContainmentEList(ParameterDecorator.class, this, BeaninfoPackage.METHOD_DECORATOR__SER_PARM_DESC);
		}
		return serParmDesc;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain eInverseAdd(InternalEObject otherEnd, int featureID, Class baseClass, NotificationChain msgs) {
		if (featureID >= 0) {
			switch (eDerivedStructuralFeatureID(featureID, baseClass)) {
				case BeaninfoPackage.METHOD_DECORATOR__EANNOTATIONS:
					return ((InternalEList)getEAnnotations()).basicAdd(otherEnd, msgs);
				case BeaninfoPackage.METHOD_DECORATOR__EMODEL_ELEMENT:
					if (eContainer != null)
						msgs = eBasicRemoveFromContainer(msgs);
					return eBasicSetContainer(otherEnd, BeaninfoPackage.METHOD_DECORATOR__EMODEL_ELEMENT, msgs);
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
				case BeaninfoPackage.METHOD_DECORATOR__EANNOTATIONS:
					return ((InternalEList)getEAnnotations()).basicRemove(otherEnd, msgs);
				case BeaninfoPackage.METHOD_DECORATOR__DETAILS:
					return ((InternalEList)getDetails()).basicRemove(otherEnd, msgs);
				case BeaninfoPackage.METHOD_DECORATOR__EMODEL_ELEMENT:
					return eBasicSetContainer(null, BeaninfoPackage.METHOD_DECORATOR__EMODEL_ELEMENT, msgs);
				case BeaninfoPackage.METHOD_DECORATOR__CONTENTS:
					return ((InternalEList)getContents()).basicRemove(otherEnd, msgs);
				case BeaninfoPackage.METHOD_DECORATOR__ATTRIBUTES:
					return ((InternalEList)getAttributes()).basicRemove(otherEnd, msgs);
				case BeaninfoPackage.METHOD_DECORATOR__SER_PARM_DESC:
					return ((InternalEList)getSerParmDesc()).basicRemove(otherEnd, msgs);
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
				case BeaninfoPackage.METHOD_DECORATOR__EMODEL_ELEMENT:
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
			case BeaninfoPackage.METHOD_DECORATOR__EANNOTATIONS:
				return getEAnnotations();
			case BeaninfoPackage.METHOD_DECORATOR__SOURCE:
				return getSource();
			case BeaninfoPackage.METHOD_DECORATOR__DETAILS:
				return getDetails();
			case BeaninfoPackage.METHOD_DECORATOR__EMODEL_ELEMENT:
				return getEModelElement();
			case BeaninfoPackage.METHOD_DECORATOR__CONTENTS:
				return getContents();
			case BeaninfoPackage.METHOD_DECORATOR__REFERENCES:
				return getReferences();
			case BeaninfoPackage.METHOD_DECORATOR__DISPLAY_NAME:
				return getDisplayName();
			case BeaninfoPackage.METHOD_DECORATOR__SHORT_DESCRIPTION:
				return getShortDescription();
			case BeaninfoPackage.METHOD_DECORATOR__CATEGORY:
				return getCategory();
			case BeaninfoPackage.METHOD_DECORATOR__EXPERT:
				return isExpert() ? Boolean.TRUE : Boolean.FALSE;
			case BeaninfoPackage.METHOD_DECORATOR__HIDDEN:
				return isHidden() ? Boolean.TRUE : Boolean.FALSE;
			case BeaninfoPackage.METHOD_DECORATOR__PREFERRED:
				return isPreferred() ? Boolean.TRUE : Boolean.FALSE;
			case BeaninfoPackage.METHOD_DECORATOR__MERGE_INTROSPECTION:
				return isMergeIntrospection() ? Boolean.TRUE : Boolean.FALSE;
			case BeaninfoPackage.METHOD_DECORATOR__ATTRIBUTES_EXPLICIT_EMPTY:
				return isAttributesExplicitEmpty() ? Boolean.TRUE : Boolean.FALSE;
			case BeaninfoPackage.METHOD_DECORATOR__IMPLICITLY_SET_BITS:
				return new Long(getImplicitlySetBits());
			case BeaninfoPackage.METHOD_DECORATOR__IMPLICIT_DECORATOR_FLAG:
				return getImplicitDecoratorFlag();
			case BeaninfoPackage.METHOD_DECORATOR__ATTRIBUTES:
				return getAttributes();
			case BeaninfoPackage.METHOD_DECORATOR__PARMS_EXPLICIT_EMPTY:
				return isParmsExplicitEmpty() ? Boolean.TRUE : Boolean.FALSE;
			case BeaninfoPackage.METHOD_DECORATOR__PARAMETER_DESCRIPTORS:
				return getParameterDescriptors();
			case BeaninfoPackage.METHOD_DECORATOR__SER_PARM_DESC:
				return getSerParmDesc();
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
			case BeaninfoPackage.METHOD_DECORATOR__EANNOTATIONS:
				getEAnnotations().clear();
				getEAnnotations().addAll((Collection)newValue);
				return;
			case BeaninfoPackage.METHOD_DECORATOR__SOURCE:
				setSource((String)newValue);
				return;
			case BeaninfoPackage.METHOD_DECORATOR__DETAILS:
				getDetails().clear();
				getDetails().addAll((Collection)newValue);
				return;
			case BeaninfoPackage.METHOD_DECORATOR__EMODEL_ELEMENT:
				setEModelElement((EModelElement)newValue);
				return;
			case BeaninfoPackage.METHOD_DECORATOR__CONTENTS:
				getContents().clear();
				getContents().addAll((Collection)newValue);
				return;
			case BeaninfoPackage.METHOD_DECORATOR__REFERENCES:
				getReferences().clear();
				getReferences().addAll((Collection)newValue);
				return;
			case BeaninfoPackage.METHOD_DECORATOR__DISPLAY_NAME:
				setDisplayName((String)newValue);
				return;
			case BeaninfoPackage.METHOD_DECORATOR__SHORT_DESCRIPTION:
				setShortDescription((String)newValue);
				return;
			case BeaninfoPackage.METHOD_DECORATOR__CATEGORY:
				setCategory((String)newValue);
				return;
			case BeaninfoPackage.METHOD_DECORATOR__EXPERT:
				setExpert(((Boolean)newValue).booleanValue());
				return;
			case BeaninfoPackage.METHOD_DECORATOR__HIDDEN:
				setHidden(((Boolean)newValue).booleanValue());
				return;
			case BeaninfoPackage.METHOD_DECORATOR__PREFERRED:
				setPreferred(((Boolean)newValue).booleanValue());
				return;
			case BeaninfoPackage.METHOD_DECORATOR__MERGE_INTROSPECTION:
				setMergeIntrospection(((Boolean)newValue).booleanValue());
				return;
			case BeaninfoPackage.METHOD_DECORATOR__ATTRIBUTES_EXPLICIT_EMPTY:
				setAttributesExplicitEmpty(((Boolean)newValue).booleanValue());
				return;
			case BeaninfoPackage.METHOD_DECORATOR__IMPLICITLY_SET_BITS:
				setImplicitlySetBits(((Long)newValue).longValue());
				return;
			case BeaninfoPackage.METHOD_DECORATOR__IMPLICIT_DECORATOR_FLAG:
				setImplicitDecoratorFlag((ImplicitItem)newValue);
				return;
			case BeaninfoPackage.METHOD_DECORATOR__ATTRIBUTES:
				getAttributes().clear();
				getAttributes().addAll((Collection)newValue);
				return;
			case BeaninfoPackage.METHOD_DECORATOR__PARMS_EXPLICIT_EMPTY:
				setParmsExplicitEmpty(((Boolean)newValue).booleanValue());
				return;
			case BeaninfoPackage.METHOD_DECORATOR__PARAMETER_DESCRIPTORS:
				getParameterDescriptors().clear();
				getParameterDescriptors().addAll((Collection)newValue);
				return;
			case BeaninfoPackage.METHOD_DECORATOR__SER_PARM_DESC:
				getSerParmDesc().clear();
				getSerParmDesc().addAll((Collection)newValue);
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
			case BeaninfoPackage.METHOD_DECORATOR__EANNOTATIONS:
				getEAnnotations().clear();
				return;
			case BeaninfoPackage.METHOD_DECORATOR__SOURCE:
				setSource(SOURCE_EDEFAULT);
				return;
			case BeaninfoPackage.METHOD_DECORATOR__DETAILS:
				getDetails().clear();
				return;
			case BeaninfoPackage.METHOD_DECORATOR__EMODEL_ELEMENT:
				setEModelElement((EModelElement)null);
				return;
			case BeaninfoPackage.METHOD_DECORATOR__CONTENTS:
				getContents().clear();
				return;
			case BeaninfoPackage.METHOD_DECORATOR__REFERENCES:
				getReferences().clear();
				return;
			case BeaninfoPackage.METHOD_DECORATOR__DISPLAY_NAME:
				unsetDisplayName();
				return;
			case BeaninfoPackage.METHOD_DECORATOR__SHORT_DESCRIPTION:
				unsetShortDescription();
				return;
			case BeaninfoPackage.METHOD_DECORATOR__CATEGORY:
				setCategory(CATEGORY_EDEFAULT);
				return;
			case BeaninfoPackage.METHOD_DECORATOR__EXPERT:
				unsetExpert();
				return;
			case BeaninfoPackage.METHOD_DECORATOR__HIDDEN:
				unsetHidden();
				return;
			case BeaninfoPackage.METHOD_DECORATOR__PREFERRED:
				unsetPreferred();
				return;
			case BeaninfoPackage.METHOD_DECORATOR__MERGE_INTROSPECTION:
				setMergeIntrospection(MERGE_INTROSPECTION_EDEFAULT);
				return;
			case BeaninfoPackage.METHOD_DECORATOR__ATTRIBUTES_EXPLICIT_EMPTY:
				setAttributesExplicitEmpty(ATTRIBUTES_EXPLICIT_EMPTY_EDEFAULT);
				return;
			case BeaninfoPackage.METHOD_DECORATOR__IMPLICITLY_SET_BITS:
				setImplicitlySetBits(IMPLICITLY_SET_BITS_EDEFAULT);
				return;
			case BeaninfoPackage.METHOD_DECORATOR__IMPLICIT_DECORATOR_FLAG:
				setImplicitDecoratorFlag(IMPLICIT_DECORATOR_FLAG_EDEFAULT);
				return;
			case BeaninfoPackage.METHOD_DECORATOR__ATTRIBUTES:
				getAttributes().clear();
				return;
			case BeaninfoPackage.METHOD_DECORATOR__PARMS_EXPLICIT_EMPTY:
				setParmsExplicitEmpty(PARMS_EXPLICIT_EMPTY_EDEFAULT);
				return;
			case BeaninfoPackage.METHOD_DECORATOR__PARAMETER_DESCRIPTORS:
				getParameterDescriptors().clear();
				return;
			case BeaninfoPackage.METHOD_DECORATOR__SER_PARM_DESC:
				getSerParmDesc().clear();
				return;
		}
		eDynamicUnset(eFeature);
	}

	/*
	 * This is overridden so that we can do special is set tests:
	 * 1) parameter descriptors: check if serParmDesc exists and not empty, since parameter descriptors is derived.
	 * 2) serParmDesc: if flag set to default parm desc, then answer not set, else do normal isSet test. That way if set by default it won't serialize
	 *    out the unneeded default parms. They can be reconstructed quickly when needed.
	 */
	public boolean eIsSet(EStructuralFeature eFeature) {
		switch (eDerivedStructuralFeatureID(eFeature)) {
			case BeaninfoPackage.METHOD_DECORATOR__SOURCE:
					return isSourceSet();	// Override so that if set to the same as classname, then it is considered not set.
			case BeaninfoPackage.METHOD_DECORATOR__PARAMETER_DESCRIPTORS:
				return eIsSetGen(BeaninfoPackage.eINSTANCE.getMethodDecorator_SerParmDesc());	// Let default serParmDesc is set work.
			case BeaninfoPackage.METHOD_DECORATOR__SER_PARM_DESC:
				if ((getImplicitlySetBits() & METHOD_PARAMETERS_DEFAULT) != 0)
					return false;	// Not considered set if initialized by default.
				else
					return eIsSetGen(eFeature);	// Not set by default, so check true setting.
			default:
				return eIsSetGen(eFeature);	// Everything else use the gen method.
		}
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean eIsSetGen(EStructuralFeature eFeature) {
		switch (eDerivedStructuralFeatureID(eFeature)) {
			case BeaninfoPackage.METHOD_DECORATOR__EANNOTATIONS:
				return eAnnotations != null && !eAnnotations.isEmpty();
			case BeaninfoPackage.METHOD_DECORATOR__SOURCE:
				return SOURCE_EDEFAULT == null ? source != null : !SOURCE_EDEFAULT.equals(source);
			case BeaninfoPackage.METHOD_DECORATOR__DETAILS:
				return details != null && !details.isEmpty();
			case BeaninfoPackage.METHOD_DECORATOR__EMODEL_ELEMENT:
				return getEModelElement() != null;
			case BeaninfoPackage.METHOD_DECORATOR__CONTENTS:
				return contents != null && !contents.isEmpty();
			case BeaninfoPackage.METHOD_DECORATOR__REFERENCES:
				return references != null && !references.isEmpty();
			case BeaninfoPackage.METHOD_DECORATOR__DISPLAY_NAME:
				return isSetDisplayName();
			case BeaninfoPackage.METHOD_DECORATOR__SHORT_DESCRIPTION:
				return isSetShortDescription();
			case BeaninfoPackage.METHOD_DECORATOR__CATEGORY:
				return CATEGORY_EDEFAULT == null ? category != null : !CATEGORY_EDEFAULT.equals(category);
			case BeaninfoPackage.METHOD_DECORATOR__EXPERT:
				return isSetExpert();
			case BeaninfoPackage.METHOD_DECORATOR__HIDDEN:
				return isSetHidden();
			case BeaninfoPackage.METHOD_DECORATOR__PREFERRED:
				return isSetPreferred();
			case BeaninfoPackage.METHOD_DECORATOR__MERGE_INTROSPECTION:
				return ((eFlags & MERGE_INTROSPECTION_EFLAG) != 0) != MERGE_INTROSPECTION_EDEFAULT;
			case BeaninfoPackage.METHOD_DECORATOR__ATTRIBUTES_EXPLICIT_EMPTY:
				return ((eFlags & ATTRIBUTES_EXPLICIT_EMPTY_EFLAG) != 0) != ATTRIBUTES_EXPLICIT_EMPTY_EDEFAULT;
			case BeaninfoPackage.METHOD_DECORATOR__IMPLICITLY_SET_BITS:
				return implicitlySetBits != IMPLICITLY_SET_BITS_EDEFAULT;
			case BeaninfoPackage.METHOD_DECORATOR__IMPLICIT_DECORATOR_FLAG:
				return implicitDecoratorFlag != IMPLICIT_DECORATOR_FLAG_EDEFAULT;
			case BeaninfoPackage.METHOD_DECORATOR__ATTRIBUTES:
				return attributes != null && !attributes.isEmpty();
			case BeaninfoPackage.METHOD_DECORATOR__PARMS_EXPLICIT_EMPTY:
				return ((eFlags & PARMS_EXPLICIT_EMPTY_EFLAG) != 0) != PARMS_EXPLICIT_EMPTY_EDEFAULT;
			case BeaninfoPackage.METHOD_DECORATOR__PARAMETER_DESCRIPTORS:
				return !getParameterDescriptors().isEmpty();
			case BeaninfoPackage.METHOD_DECORATOR__SER_PARM_DESC:
				return serParmDesc != null && !serParmDesc.isEmpty();
		}
		return eDynamicIsSet(eFeature);
	}

}
