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
 *  $Revision: 1.12 $  $Date: 2007/03/14 01:22:51 $ 
 */
package org.eclipse.jem.internal.beaninfo.impl;



import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.ecore.*;
import org.eclipse.emf.ecore.impl.ENotificationImpl;

import org.eclipse.jem.internal.beaninfo.BeaninfoPackage;
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

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected EClass eStaticClass() {
		return BeaninfoPackage.Literals.PARAMETER_DECORATOR;
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
			InternalEObject oldParameter = (InternalEObject)parameter;
			parameter = (JavaParameter)eResolveProxy(oldParameter);
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
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case BeaninfoPackage.PARAMETER_DECORATOR__NAME:
				return getName();
			case BeaninfoPackage.PARAMETER_DECORATOR__PARAMETER:
				if (resolve) return getParameter();
				return basicGetParameter();
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
			case BeaninfoPackage.PARAMETER_DECORATOR__NAME:
				setName((String)newValue);
				return;
			case BeaninfoPackage.PARAMETER_DECORATOR__PARAMETER:
				setParameter((JavaParameter)newValue);
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
			case BeaninfoPackage.PARAMETER_DECORATOR__NAME:
				setName(NAME_EDEFAULT);
				return;
			case BeaninfoPackage.PARAMETER_DECORATOR__PARAMETER:
				setParameter((JavaParameter)null);
				return;
		}
		super.eUnset(featureID);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean eIsSet(int featureID) {
		switch (featureID) {
			case BeaninfoPackage.PARAMETER_DECORATOR__NAME:
				return NAME_EDEFAULT == null ? name != null : !NAME_EDEFAULT.equals(name);
			case BeaninfoPackage.PARAMETER_DECORATOR__PARAMETER:
				return parameter != null;
		}
		return super.eIsSet(featureID);
	}


}
