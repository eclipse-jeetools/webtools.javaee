package org.eclipse.jem.internal.beaninfo;
/*******************************************************************************
 * Copyright (c)  2001, 2003 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials 
 * are made available under the terms of the Common Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/cpl-v10.html
 * 
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *******************************************************************************/
/*
 *  $RCSfile: MethodDecorator.java,v $
 *  $Revision: 1.1.4.1 $  $Date: 2003/12/16 19:28:47 $ 
 */


import org.eclipse.emf.common.util.EList;
/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Method Decorator</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.eclipse.jem.internal.beaninfo.MethodDecorator#isParmsExplicit <em>Parms Explicit</em>}</li>
 *   <li>{@link org.eclipse.jem.internal.beaninfo.MethodDecorator#getParameterDescriptors <em>Parameter Descriptors</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.eclipse.jem.internal.beaninfo.BeaninfoPackage#getMethodDecorator()
 * @model 
 * @generated
 */


public interface MethodDecorator extends FeatureDecorator{
	/**
	 * Returns the value of the '<em><b>Parms Explicit</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Parms Explicit</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * If the parameterDescriptors feature is explicitly set, ie. not through the method descriptor proxy, then this flag must be set true. If it is true, then the parameterDescriptors will not be brought over from the descriptor proxy, nor will default ones be created if there aren't any specified.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Parms Explicit</em>' attribute.
	 * @see #setParmsExplicit(boolean)
	 * @see org.eclipse.jem.internal.beaninfo.BeaninfoPackage#getMethodDecorator_ParmsExplicit()
	 * @model 
	 * @generated
	 */
	boolean isParmsExplicit();

	/**
	 * Sets the value of the '{@link org.eclipse.jem.internal.beaninfo.MethodDecorator#isParmsExplicit <em>Parms Explicit</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Parms Explicit</em>' attribute.
	 * @see #isParmsExplicit()
	 * @generated
	 */
	void setParmsExplicit(boolean value);

	/**
	 * Returns the value of the '<em><b>Parameter Descriptors</b></em>' containment reference list.
	 * The list contents are of type {@link org.eclipse.jem.internal.beaninfo.ParameterDecorator}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Parameter Descriptors</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Parameter Descriptors</em>' containment reference list.
	 * @see org.eclipse.jem.internal.beaninfo.BeaninfoPackage#getMethodDecorator_ParameterDescriptors()
	 * @model type="org.eclipse.jem.internal.beaninfo.ParameterDecorator" containment="true"
	 * @generated
	 */
	EList getParameterDescriptors();

}
