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
 *  $RCSfile: MethodDecoratorImpl.java,v $
 *  $Revision: 1.3 $  $Date: 2004/08/27 15:33:31 $ 
 */


import java.util.Collection;
import java.util.List;

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
import org.eclipse.jem.internal.beaninfo.MethodDecorator;
import org.eclipse.jem.internal.beaninfo.MethodProxy;
import org.eclipse.jem.internal.beaninfo.ParameterDecorator;
import org.eclipse.jem.internal.beaninfo.adapters.BeaninfoProxyConstants;
import org.eclipse.jem.java.JavaParameter;
import org.eclipse.jem.java.Method;
import org.eclipse.jem.internal.proxy.core.IArrayBeanProxy;
import org.eclipse.jem.internal.proxy.core.IBeanProxy;
import org.eclipse.jem.internal.proxy.core.ThrowableProxy;
/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Method Decorator</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.eclipse.jem.internal.beaninfo.impl.MethodDecoratorImpl#isParmsExplicit <em>Parms Explicit</em>}</li>
 *   <li>{@link org.eclipse.jem.internal.beaninfo.impl.MethodDecoratorImpl#getParameterDescriptors <em>Parameter Descriptors</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */


public class MethodDecoratorImpl extends FeatureDecoratorImpl implements MethodDecorator{
	/**
	 * The default value of the '{@link #isParmsExplicit() <em>Parms Explicit</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isParmsExplicit()
	 * @generated
	 * @ordered
	 */
	protected static final boolean PARMS_EXPLICIT_EDEFAULT = false;

	protected boolean fRetrievedParms = false;
	protected boolean fRetrievedParmsSuccessful = false;
	
	/**
	 * The cached value of the '{@link #isParmsExplicit() <em>Parms Explicit</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isParmsExplicit()
	 * @generated
	 * @ordered
	 */
	protected boolean parmsExplicit = PARMS_EXPLICIT_EDEFAULT;
	/**
	 * The cached value of the '{@link #getParameterDescriptors() <em>Parameter Descriptors</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getParameterDescriptors()
	 * @generated
	 * @ordered
	 */
	protected EList parameterDescriptors = null;
	
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

	public EList getParameterDescriptors() {
		if (!isParmsExplicit()) {
			if (validProxy(fFeatureProxy) && !fRetrievedParms) {
				fRetrievedParms = true;
				EList parmsList = this.getParameterDescriptorsGen();
									
				try {
					IArrayBeanProxy parms = (IArrayBeanProxy) BeaninfoProxyConstants.getConstants(fFeatureProxy.getProxyFactoryRegistry()).getParameterDescriptorsProxy().invoke(fFeatureProxy);
					if (parms != null) {
						List plist = getMethodParameters();
						int plistLength = plist != null ? plist.size() : 0;
						int parmsLength = Math.min(parms.getLength(), plistLength);
						// Don't want to add more than there really is. This just means that the beaninfo is out of sync with the code.
						for (int i=0; i<parmsLength; i++) {
							IBeanProxy parm = parms.get(i);
							ParameterDecorator pd = BeaninfoFactory.eINSTANCE.createParameterDecorator();
							pd.setImplicitlyCreated(IMPLICIT_DECORATOR);
							pd.setDescriptorProxy(parm);
							parmsList.add(pd);
							// Get the real parm and set as the decorates.
							pd.setParameter((JavaParameter) plist.get(i));
						}
						fRetrievedParmsSuccessful = true;
						return parmsList;
					}		
				} catch (ThrowableProxy e) {
				};
			}
			
			if (fRetrievedParmsSuccessful)
				return this.getParameterDescriptorsGen();	// Built once from proxy, use it always.
			else
				return createDefaultParmsList();	// Not explicit and not sucessful retrieval, use default.
		}
		return this.getParameterDescriptorsGen();
	}
	/**
	 * This is called if parms list not explicitly set and there is no feature proxy or
	 * there is a feature proxy and the proxy has nothing defined.
	 * We will generate a list based upon the parameters from the method itself.
	 * We won't cache it because it may change and we aren't listening for changes.
	 * It shouldn't be used that often to cause a performance problem.
	 */
	protected EList createDefaultParmsList() {
		EList parmsList = this.getParameterDescriptorsGen();
		parmsList.clear();
		
		List p = getMethodParameters();
		if (p == null)
			return parmsList;	// Couldn't get the list for some reason, so leave as is.
		int psize = p.size();
		for (int i=0; i<psize; i++) {
			ParameterDecorator pd = BeaninfoFactory.eINSTANCE.createParameterDecorator();
			JavaParameter jp = (JavaParameter) p.get(i);
			pd.setName(jp.getName());
			pd.setParameter(jp);
			parmsList.add(pd);
		}
		return parmsList;
	}
	
	/*
	 * Initialize the ParameterDecorators to point to the JavaParameter they are describing.
	 * This is called from ParameterDecorator when it finds that its JavaParameter has not been set.
	 * This means that it was explicitly added and we need to setup the parms.
	 */
	protected void initializeParameters() {
		if (this.parameterDescriptors == null)
			return;
		List mp = getMethodParameters();
		if (mp == null || mp.isEmpty())
			return;	// Nothing that can be described.
		int psize = Math.min(this.parameterDescriptors.size(), mp.size());
		for (int i=0; i < psize; i++)
			((ParameterDecorator) this.parameterDescriptors.get(i)).setParameter((JavaParameter) mp.get(i));
	}


	protected List getMethodParameters() {
		// Get the method
		Method m = null;
		Object d = getEModelElement();
		if (d instanceof Method)
			m = (Method) d;
		else 
			if (d instanceof MethodProxy)
				m = ((MethodProxy) d).getMethod();
			else
				return null;	// Not decorating correct object.
		if (m == null)
			return null;	// Couldn't find the method.
		List p = m.getParameters();
		return p;
	}
			
			
	
	public void setDescriptorProxy(IBeanProxy descriptorProxy) {
		if (fRetrievedParms) {
			this.getParameterDescriptorsGen().clear();
			fRetrievedParms = fRetrievedParmsSuccessful = false;
		}
		super.setDescriptorProxy(descriptorProxy);
	}


	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean isParmsExplicit() {
		return parmsExplicit;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setParmsExplicit(boolean newParmsExplicit) {
		boolean oldParmsExplicit = parmsExplicit;
		parmsExplicit = newParmsExplicit;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, BeaninfoPackage.METHOD_DECORATOR__PARMS_EXPLICIT, oldParmsExplicit, parmsExplicit));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String toString() {
		if (eIsProxy()) return super.toString();

		StringBuffer result = new StringBuffer(super.toString());
		result.append(" (parmsExplicit: ");
		result.append(parmsExplicit);
		result.append(')');
		return result.toString();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList getParameterDescriptorsGen() {
		if (parameterDescriptors == null) {
			parameterDescriptors = new EObjectContainmentEList(ParameterDecorator.class, this, BeaninfoPackage.METHOD_DECORATOR__PARAMETER_DESCRIPTORS);
		}
		return parameterDescriptors;
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
				case BeaninfoPackage.METHOD_DECORATOR__PARAMETER_DESCRIPTORS:
					return ((InternalEList)getParameterDescriptors()).basicRemove(otherEnd, msgs);
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
			case BeaninfoPackage.METHOD_DECORATOR__ATTRIBUTES_EXPLICIT:
				return isAttributesExplicit() ? Boolean.TRUE : Boolean.FALSE;
			case BeaninfoPackage.METHOD_DECORATOR__ATTRIBUTES:
				return getAttributes();
			case BeaninfoPackage.METHOD_DECORATOR__PARMS_EXPLICIT:
				return isParmsExplicit() ? Boolean.TRUE : Boolean.FALSE;
			case BeaninfoPackage.METHOD_DECORATOR__PARAMETER_DESCRIPTORS:
				return getParameterDescriptors();
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
			case BeaninfoPackage.METHOD_DECORATOR__ATTRIBUTES_EXPLICIT:
				setAttributesExplicit(((Boolean)newValue).booleanValue());
				return;
			case BeaninfoPackage.METHOD_DECORATOR__ATTRIBUTES:
				getAttributes().clear();
				getAttributes().addAll((Collection)newValue);
				return;
			case BeaninfoPackage.METHOD_DECORATOR__PARMS_EXPLICIT:
				setParmsExplicit(((Boolean)newValue).booleanValue());
				return;
			case BeaninfoPackage.METHOD_DECORATOR__PARAMETER_DESCRIPTORS:
				getParameterDescriptors().clear();
				getParameterDescriptors().addAll((Collection)newValue);
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
			case BeaninfoPackage.METHOD_DECORATOR__ATTRIBUTES_EXPLICIT:
				setAttributesExplicit(ATTRIBUTES_EXPLICIT_EDEFAULT);
				return;
			case BeaninfoPackage.METHOD_DECORATOR__ATTRIBUTES:
				getAttributes().clear();
				return;
			case BeaninfoPackage.METHOD_DECORATOR__PARMS_EXPLICIT:
				setParmsExplicit(PARMS_EXPLICIT_EDEFAULT);
				return;
			case BeaninfoPackage.METHOD_DECORATOR__PARAMETER_DESCRIPTORS:
				getParameterDescriptors().clear();
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
				return mergeIntrospection != MERGE_INTROSPECTION_EDEFAULT;
			case BeaninfoPackage.METHOD_DECORATOR__ATTRIBUTES_EXPLICIT:
				return attributesExplicit != ATTRIBUTES_EXPLICIT_EDEFAULT;
			case BeaninfoPackage.METHOD_DECORATOR__ATTRIBUTES:
				return attributes != null && !attributes.isEmpty();
			case BeaninfoPackage.METHOD_DECORATOR__PARMS_EXPLICIT:
				return parmsExplicit != PARMS_EXPLICIT_EDEFAULT;
			case BeaninfoPackage.METHOD_DECORATOR__PARAMETER_DESCRIPTORS:
				return parameterDescriptors != null && !parameterDescriptors.isEmpty();
		}
		return eDynamicIsSet(eFeature);
	}

}
