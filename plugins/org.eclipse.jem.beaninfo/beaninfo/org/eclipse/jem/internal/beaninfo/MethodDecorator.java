/*******************************************************************************
 * Copyright (c) 2001, 2004 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *******************************************************************************/
package org.eclipse.jem.internal.beaninfo;
/*
 *  $RCSfile: MethodDecorator.java,v $
 *  $Revision: 1.6 $  $Date: 2005/04/14 19:05:36 $ 
 */


import org.eclipse.emf.common.util.EList;
/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Method Decorator</b></em>'.
 * <!-- end-user-doc -->
 *
 * <!-- begin-model-doc -->
 * Equivalent to MethodDecorator in java.
 * <!-- end-model-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.eclipse.jem.internal.beaninfo.MethodDecorator#isParmsExplicitEmpty <em>Parms Explicit Empty</em>}</li>
 *   <li>{@link org.eclipse.jem.internal.beaninfo.MethodDecorator#getParameterDescriptors <em>Parameter Descriptors</em>}</li>
 *   <li>{@link org.eclipse.jem.internal.beaninfo.MethodDecorator#getSerParmDesc <em>Ser Parm Desc</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.eclipse.jem.internal.beaninfo.BeaninfoPackage#getMethodDecorator()
 * @model
 * @generated
 */


public interface MethodDecorator extends FeatureDecorator{
	/**
	 * Returns the value of the '<em><b>Parms Explicit Empty</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Set true if the parms feature is explicitly set as empty and is not to have parameters merged in from BeanInfo or reflection.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Parms Explicit Empty</em>' attribute.
	 * @see #setParmsExplicitEmpty(boolean)
	 * @see org.eclipse.jem.internal.beaninfo.BeaninfoPackage#getMethodDecorator_ParmsExplicitEmpty()
	 * @model
	 * @generated
	 */
	boolean isParmsExplicitEmpty();

	/**
	 * Sets the value of the '{@link org.eclipse.jem.internal.beaninfo.MethodDecorator#isParmsExplicitEmpty <em>Parms Explicit Empty</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Parms Explicit Empty</em>' attribute.
	 * @see #isParmsExplicitEmpty()
	 * @generated
	 */
	void setParmsExplicitEmpty(boolean value);

	/**
	 * Returns the value of the '<em><b>Parameter Descriptors</b></em>' reference list.
	 * The list contents are of type {@link org.eclipse.jem.internal.beaninfo.ParameterDecorator}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Parameter Descriptors</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * This is the parameter descriptors list.
	 * <p>
	 * Note: This is a derived setting, which means it will not notify out changes to it. To here changes to it, listen on "serParmDesc" notifications instead.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Parameter Descriptors</em>' reference list.
	 * @see org.eclipse.jem.internal.beaninfo.BeaninfoPackage#getMethodDecorator_ParameterDescriptors()
	 * @model type="org.eclipse.jem.internal.beaninfo.ParameterDecorator" transient="true" volatile="true" derived="true"
	 * @generated
	 */
	EList getParameterDescriptors();

	/**
	 * Returns the value of the '<em><b>Ser Parm Desc</b></em>' containment reference list.
	 * The list contents are of type {@link org.eclipse.jem.internal.beaninfo.ParameterDecorator}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Ser Parm Desc</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * This is a private feature. It is used internally only. 
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Ser Parm Desc</em>' containment reference list.
	 * @see org.eclipse.jem.internal.beaninfo.BeaninfoPackage#getMethodDecorator_SerParmDesc()
	 * @model type="org.eclipse.jem.internal.beaninfo.ParameterDecorator" containment="true"
	 * @generated
	 */
	EList getSerParmDesc();

}
