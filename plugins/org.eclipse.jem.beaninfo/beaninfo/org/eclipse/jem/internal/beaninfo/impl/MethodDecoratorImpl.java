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
 *  $Revision: 1.12 $  $Date: 2007/03/14 01:22:51 $ 
 */


import java.util.*;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.*;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.util.EObjectContainmentEList;
import org.eclipse.emf.ecore.util.InternalEList;

import org.eclipse.jem.internal.beaninfo.*;
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
		return BeaninfoPackage.Literals.METHOD_DECORATOR;
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
		// The list is expected to implement org.eclipse.emf.ecore.util.InternalEList and org.eclipse.emf.ecore.EStructuralFeature.Setting
		// so it's likely that an appropriate subclass of org.eclipse.emf.ecore.util.EcoreEList should be used.
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
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case BeaninfoPackage.METHOD_DECORATOR__SER_PARM_DESC:
				return ((InternalEList)getSerParmDesc()).basicRemove(otherEnd, msgs);
		}
		return super.eInverseRemove(otherEnd, featureID, msgs);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case BeaninfoPackage.METHOD_DECORATOR__PARMS_EXPLICIT_EMPTY:
				return isParmsExplicitEmpty() ? Boolean.TRUE : Boolean.FALSE;
			case BeaninfoPackage.METHOD_DECORATOR__PARAMETER_DESCRIPTORS:
				return getParameterDescriptors();
			case BeaninfoPackage.METHOD_DECORATOR__SER_PARM_DESC:
				return getSerParmDesc();
		}
		return super.eGet(featureID, resolve, coreType);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void eSet(int featureID, Object newValue) {
		switch (featureID) {
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
		super.eSet(featureID, newValue);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void eUnset(int featureID) {
		switch (featureID) {
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
		super.eUnset(featureID);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * Overriden
	 */
	public boolean eIsSet(int featureID) {
		switch (featureID) {
			case BeaninfoPackage.METHOD_DECORATOR__SOURCE:
				return isSourceSet();	// Override so that if set to the same as classname, then it is considered not set.
			case BeaninfoPackage.METHOD_DECORATOR__PARAMETER_DESCRIPTORS:
				return eIsSet(BeaninfoPackage.eINSTANCE.getMethodDecorator_SerParmDesc());	// Let default serParmDesc is set work.
			case BeaninfoPackage.METHOD_DECORATOR__SER_PARM_DESC:
				if ((getImplicitlySetBits() & METHOD_PARAMETERS_DEFAULT) != 0)
					return false;	// Not considered set if initialized by default.
			case BeaninfoPackage.METHOD_DECORATOR__PARMS_EXPLICIT_EMPTY:
				return ((eFlags & PARMS_EXPLICIT_EMPTY_EFLAG) != 0) != PARMS_EXPLICIT_EMPTY_EDEFAULT;
		}
		return super.eIsSet(featureID);
	}

}
